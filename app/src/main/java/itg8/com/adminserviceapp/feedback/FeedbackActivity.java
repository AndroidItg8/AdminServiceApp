package itg8.com.adminserviceapp.feedback;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.feedback.model.FeedbackModel;
import itg8.com.adminserviceapp.feedback.mvp.FeedbackMVP;
import itg8.com.adminserviceapp.feedback.mvp.FeedbackPresenterImp;

public class FeedbackActivity extends AppCompatActivity implements FeedbackMVP.FeedbackView, FeedbackAdapter.ItemClickedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    @BindView(R.id.img_no)
    ImageView imgNo;
    @BindView(R.id.rl_no_item)
    RelativeLayout rlNoItem;
    private FeedbackMVP.FeedbackPresenter presenter;
    private Snackbar snackbar;
    private FeedbackAdapter adapter;
    private boolean isViewVisible=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        isViewVisible=true;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new FeedbackPresenterImp(this);
        init();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isViewVisible=false;
        presenter.onDestroyed();
    }

    private void init() {
        CommonMethod.showHideItem(recyclerView, rlNoItem);
        presenter.downloadFeedbackList(getString(R.string.url_feedback));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(presenter.recyclerViewScrllListener(linearLayoutManager));
        adapter = new FeedbackAdapter(getApplicationContext(),this);
        recyclerView.setAdapter(adapter);
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
        hideSnackbar();
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void onProgressHide() {
        if(isViewVisible)
            progressView.setVisibility(View.GONE);

    }

    @Override
    public void onProgressShow() {
        if(isViewVisible)
            progressView.setVisibility(View.VISIBLE);

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
    public void onNoMoreList(int from) {
        adapter.removeFooter();


    }

    @Override
    public void onShowPaginationLoading(boolean show, int from) {
        if (adapter == null)
            return;
        if (show) {
            adapter.addFooter();
        } else {
            adapter.removeFooter();
        }

    }

    @Override
    public void onPaginationError(boolean show, int from) {


    }


    @Override
    public void getFeedbackList(List<FeedbackModel> list) {
        if(isViewVisible)
          adapter.addItems(list);

    }

    @Override
    public void emptyList() {
        if(isViewVisible)
            CommonMethod.showHideItem(rlNoItem, recyclerView);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClicked(int position, FeedbackModel model) {
        Intent intent = new Intent(getApplicationContext(),FeedbackDetailsActivity.class);
        intent.putExtra(CommonMethod.FEEDBACK,model);
        startActivity(intent);
    }
}
