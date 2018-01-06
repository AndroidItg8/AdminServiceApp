package itg8.com.adminserviceapp.ticket;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.ticket.adapter.ViewPagerAdapter;
import itg8.com.adminserviceapp.ticket.fragment.AcceptFragment;
import itg8.com.adminserviceapp.ticket.fragment.PendingFragment;
import itg8.com.adminserviceapp.ticket.fragment.CloseFragment;
import itg8.com.adminserviceapp.ticket.model.TicketModel;
import itg8.com.adminserviceapp.ticket.mvp.TicketMVP;
import itg8.com.adminserviceapp.ticket.mvp.TicketPresenterImp;

public class TicketActivity extends AppCompatActivity implements TicketMVP.TicketView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private Snackbar snackbar;
    private TicketMVP.TicketPresenter presenter;
    TicketPendingListListener ticketPendingListListener;
    TicketAcceptListListner ticketAcceptListListner;
    TicketCloseListListner ticketCloseListListner;
    private static final int RC_CODE = 345;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new TicketPresenterImp(this);
        init();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void init() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PendingFragment(), "Pending");
        adapter.addFragment(new AcceptFragment(), "Accept");
        adapter.addFragment(new CloseFragment(), "Close");
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
        showSnackbar(b, CommonMethod.FROM_ERROR, getString(R.string.no_internet));

    }

    @Override
    public void onError(String mesg) {
        showSnackbar(false, CommonMethod.FROM_ERROR, mesg);
    }

    @Override
    public void onNoMoreList(int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onNoMoreList(getString(R.string.no_more_list));
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onNoMoreList(getString(R.string.no_more_list));

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onNoMoreList(getString(R.string.no_more_list));
                break;
        }


    }

    @Override
    public void onShowPaginationLoading(boolean show, int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onShowPaginationLoading(show);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onShowPaginationLoading(show);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onShowPaginationLoading(show);
                break;
        }

    }

    @Override
    public void onPaginationError(boolean show, int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onPaginationError(show);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onPaginationError(show);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onPaginationError(show);
                break;
        }
    }


    @Override
    public void getTicketList(List<TicketModel> list, int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onPendingTicketList(list);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onCloseTicketList(list);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onAcceptTicketList(list);
                break;
        }


    }

    @Override
    public void onProgressHide(int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onProgressHide();
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onProgressHide();

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onProgressHide();
                break;
        }
    }

    @Override
    public void onProgressShow(int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onProgressShow();
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onProgressShow();

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onProgressShow();
                break;
        }

    }

    @Override
    public void onFinished(int status, boolean b) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onFinished(b);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onFinished(b);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onFinished(b);
                break;
        }
    }

    @Override
    public void isLoading(int status, boolean b) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.isLoading(b);
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.isLoading(b);

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.isLoading(b);
                break;
        }
    }

    @Override
    public void emptyList(int status) {
        switch (status)
        {
            case CommonMethod.TICKET_STATUS_OPEN:
                ticketPendingListListener.onEmptyList();
                break;
            case CommonMethod.TICKET_STATUS_CLOSE:
                ticketCloseListListner.onEmptyList();

                break;
            case CommonMethod.TICKET_STATUS_ASSIGN:
                ticketAcceptListListner.onEmptyList();
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
                .make(viewPager, message, Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setMaxLines(2);
        snackbar.show();


        snackbar.setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSnackbarOkClicked(view);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view) {
//        presenter.onSalesPersonAddClicked(view);
        hideSnackbar();
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    public void AcceptFragmentAttach(TicketAcceptListListner ticketAcceptListListner) {
        this.ticketAcceptListListner = ticketAcceptListListner;
        presenter.downloadTicketList(getString(R.string.url_ticket),CommonMethod.TICKET_STATUS_ASSIGN);
    }

    public void PendingFragmentAttach(TicketPendingListListener ticketPendingListListener) {
        this.ticketPendingListListener =ticketPendingListListener;
        presenter.downloadTicketList(getString(R.string.url_ticket),CommonMethod.TICKET_STATUS_OPEN);

    }

    public void CloseFragmentAttach(TicketCloseListListner ticketCloseListListner) {
        this.ticketCloseListListner = ticketCloseListListner;
        presenter.downloadTicketList(getString(R.string.url_ticket),CommonMethod.TICKET_STATUS_CLOSE);


    }

    public void onDetach() {
        presenter.onDestroyed();
    }

    public void onLoadMoreItem(int page, int from) {


    }

    public void refreshFragment(Fragment pendingFragment) {
       // setupViewPager(viewPager);
        if(this.ticketCloseListListner != null)
        {
            this.ticketCloseListListner.onRefreshCloseFragment();
        }
    }


    public interface TicketPendingListListener {
        void onPendingTicketList(List<TicketModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onNoMoreList(String Message);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();
    }

    public interface TicketAcceptListListner {
        void onAcceptTicketList(List<TicketModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onNoMoreList(String Message);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();
    }

    public interface TicketCloseListListner {
        void onCloseTicketList(List<TicketModel> list);
        void onPaginationError(boolean show);
        void onShowPaginationLoading(boolean show);
        void onNoMoreList(String Message);
        void onFinished(boolean b);
        void isLoading(boolean b);
        void onEmptyList();
        void onProgressHide();
        void onProgressShow();

        void onRefreshCloseFragment();
    }
}
