package itg8.com.adminserviceapp.tender;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Logs;
import itg8.com.adminserviceapp.tender.fragment.PendingTenderFragment;
import itg8.com.adminserviceapp.tender.fragment.RejectedTenderFragment;
import itg8.com.adminserviceapp.tender.fragment.SubmitedTenderFragment;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import itg8.com.adminserviceapp.tender.model.TenderModel;
import itg8.com.adminserviceapp.tender.mvp.TenderMVP;
import itg8.com.adminserviceapp.tender.mvp.TenderPresenterImp;
import itg8.com.adminserviceapp.ticket.adapter.ViewPagerAdapter;

public class TenderActivity extends AppCompatActivity implements TenderMVP.TenderView {


    private static final String TAG = TenderActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Snackbar snackbar;
    private TenderMVP.TenderPresenter presenter;
        public  TenderSubmitListListner submitListener;
    public  TenderExpiredListListner expireListener;
    private TenderModel model;
    private List<TenderModel> list;
    public TenderPendingListListener pendingListener;
    private int page=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new TenderPresenterImp(this);
        init();
    }

    private void init() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendingTenderFragment(), "Pending");
        adapter.addFragment(new SubmitedTenderFragment(), "Submitted");
        adapter.addFragment(new RejectedTenderFragment(), "Rejected");
        viewPager.setAdapter(adapter);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProgressHide() {
    }

    @Override
    public void onProgressShow() {
    }

    @Override
    public void onNoInternet(boolean b) {
        showSnackbar(b, CommonMethod.FROM_INTERNET, getString(R.string.no_internet));
    }

    @Override
    public void onError(String mesg) {
        showSnackbar(false, CommonMethod.FROM_ERROR, mesg);


    }

    @Override
    public void onNoMoreList(int from) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.onNoMoreList(getString(R.string.no_more_list));
                break;
                case CommonMethod.FROM_PENDING:
                pendingListener.onNoMoreList(getString(R.string.no_more_list));
                break;
                case CommonMethod.FROM_REJECT:
                expireListener.onNoMoreList(getString(R.string.no_more_list));
                break;


        }

    }

    @Override
    public void onShowPaginationLoading(boolean b, int from) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.onShowPaginationLoading(b);
                break;
            case CommonMethod.FROM_PENDING:
                pendingListener.onShowPaginationLoading(b);
                break;
            case CommonMethod.FROM_REJECT:
                expireListener.onShowPaginationLoading(b);
                break;


        }
    }

    @Override
    public void onPaginationError(boolean b, int from) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.onPaginationError(b);
                break;
            case CommonMethod.FROM_PENDING:
                pendingListener.onPaginationError(b);
                break;
            case CommonMethod.FROM_REJECT:
                expireListener.onPaginationError(b);
                break;


        }
    }

    private void showSnackbar(boolean isConnected, int from, String message) {

        int color = 0;
        if (from == CommonMethod.FROM_INTERNET) {
            if (isConnected) {

                color = Color.RED;
            }
        } else {
            color = Color.WHITE;

        }

        snackbar = Snackbar
                .make(fab, message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setMaxLines(2);
        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSnackbarOkClicked(view);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view) {
        hideSnackbar();
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    public void fragmentAttach(TenderPendingListListener  pendingListener) {
        this.pendingListener = pendingListener;
        presenter.downloadTenderList(getString(R.string.url_pending_tender),CommonMethod.FROM_PENDING);

    }

    public void submitedFragmentAttach(TenderSubmitListListner submitListener) {
         this.submitListener= submitListener;
         presenter.downloadSubmitedTenderList(getString(R.string.url_submited_tender),CommonMethod.FROM_ACCEPT);

    }

    public void rejectedFragmentAttachment(TenderExpiredListListner listner) {
         this.expireListener= listner;
         presenter.downloadRejectedTender(getString(R.string.url_tender_rejected),CommonMethod.FROM_REJECT);

    }

    @Override
    public void onGetPendingTenderList(List<PendingTenderModel> list) {
        pendingListener.onPendingTenderList(list);

    }

    @Override
    public void onGetSubmitedTenderList(List<PendingTenderModel> list) {
        submitListener.onSubmitTenderList(list);

    }

    @Override
    public void onGetRejectedTenderList(List<PendingTenderModel> list) {
        Logs.d("onGetRejectedTenderList:" + new Gson().toJson(list));
        expireListener.onExpiredTenderList(list);

    }

    @Override
    public void onProgressHide(int from) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.onProgressHide();
                break;
            case CommonMethod.FROM_PENDING:
                pendingListener.onProgressHide();
                break;
            case CommonMethod.FROM_REJECT:
                expireListener.onProgressHide();
                break;
        }

    }

    @Override
    public void onProgressShow(int from) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.onProgressShow();
                break;
            case CommonMethod.FROM_PENDING:
                pendingListener.onProgressShow();
                break;
            case CommonMethod.FROM_REJECT:
                expireListener.onProgressShow();
                break;
        }
    }

    @Override
    public void onFinished(int from, boolean b) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.onFinished(b);
                break;
            case CommonMethod.FROM_PENDING:
                pendingListener.onFinished(b);
                break;
            case CommonMethod.FROM_REJECT:
                expireListener.onFinished(b);
                break;
        }
    }

    @Override
    public void isLoading(int from, boolean b) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.isLoading(b);
                break;
            case CommonMethod.FROM_PENDING:
                pendingListener.isLoading(b);
                break;
            case CommonMethod.FROM_REJECT:
                expireListener.isLoading(b);
                break;
        }

    }

    @Override
    public void emptyList(int from) {
        switch (from)
        {
            case CommonMethod.FROM_ACCEPT:
                submitListener.onEmptyList();
                break;
            case CommonMethod.FROM_PENDING:
                pendingListener.onEmptyList();
                break;
            case CommonMethod.FROM_REJECT:
                expireListener.onEmptyList();
                break;
        }
    }

    public void onLoadMoreItem(int page, int from) {
        presenter.getItems(page,from);
    }

    public void onDetach() {
        presenter.onDestroyed();
    }

    public void refreshSubmitFragment(Fragment pendingTenderFragment) {
        if(this.submitListener!= null)
            presenter.downloadSubmitedTenderList(getString(R.string.url_submited_tender),CommonMethod.FROM_ACCEPT);
    }

    public void refreshRejectFragment(SubmitedTenderFragment submitedTenderFragment) {
       if( this.expireListener!= null)
        presenter.downloadRejectedTender(getString(R.string.url_tender_rejected),CommonMethod.FROM_REJECT);

    }


    public interface TenderPendingListListener {
        void onPendingTenderList(List<PendingTenderModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onNoMoreList(String Message);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();
  }

    public interface TenderSubmitListListner {
        void onSubmitTenderList(List<PendingTenderModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onNoMoreList(String Message);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();
    }

    public interface TenderExpiredListListner {
        void onExpiredTenderList(List<PendingTenderModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onNoMoreList(String Message);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();
    }





}

