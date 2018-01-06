package itg8.com.adminserviceapp.enquiry;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
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
import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;
import itg8.com.adminserviceapp.enquiry.mvp.EnquiryMVP;
import itg8.com.adminserviceapp.enquiry.mvp.EnquiryPresenterImp;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class EnquiryActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks, EnquiryMVP.EnquiryView, PendingEnquiryAdapter.ItemClickedListner {

    private static final int RC_CALL = 234;
    private static final String TAG = "EnquiryActivity";
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
    private Snackbar snackbar;
    private String[] permissions;
    private boolean canPhoneState;
    private boolean canPhoneCall;
    private EnquiryMVP.EnquiryPresenter presneter;
    private PendingEnquiryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkStoragePerm();
        presneter = new EnquiryPresenterImp(this);
        checkStoragePerm();
        init();
    }


    private void init() {
        CommonMethod.showHideItem(recyclerView, rlNoItem);
        presneter.downloadEnquiryList(getString(R.string.url_enquiry));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addOnScrollListener(presneter.recyclerViewScrllListener(linearLayoutManager));
        adapter = new PendingEnquiryAdapter(getApplicationContext(), this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @AfterPermissionGranted(RC_CALL)
    private void checkStoragePerm() {
        permissions = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.CALL_PHONE};
        if ((EasyPermissions.hasPermissions(this, permissions[0]))) {
            canPhoneState = true;
        }
        if ((EasyPermissions.hasPermissions(this, permissions[1]))) {
            canPhoneCall = true;
        }
        if (!canPhoneState || !canPhoneCall) {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_no_permission), RC_CALL, permissions);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // EasyPermissions handles the request result.
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);


    }


    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        checkPrems(perms, true);
    }

    private void checkPrems(List<String> perms, boolean isGranted) {
        if (perms.contains(permissions[0])) {
            canPhoneState = isGranted;
        }
        if (perms.contains(permissions[1])) {
            canPhoneCall = isGranted;
        }

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        checkPrems(perms, false);
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
//        presenter.onSalesPersonAddClicked(view);
        hideSnackbar();
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public void onProgressHide() {
        progressView.setVisibility(View.GONE);

    }

    @Override
    public void onProgressShow() {
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
       // adapter.removeFooter();


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
    public void getEnguiryList(List<EnquiryModel> list) {
        adapter.addItems(list);
    }

    @Override
    public void emptyList() {
        CommonMethod.showHideItem(rlNoItem, recyclerView);

    }

    @Override
    public void onItemClicked(int position, EnquiryModel model) {
        if (!canPhoneCall) {
            showSnackbar(false, CommonMethod.FROM_ERROR, getString(R.string.rationale_no_permission));
        } else {

            String telNo = "tel:" + model.getCust().get(0).getMobileno();
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse(telNo));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            startActivity(callIntent);
        }

    }

    @Override
    public void onItemClickedListner(int position, EnquiryModel model) {
        Intent intent = new Intent(getApplicationContext(),EnquiryDetailsActivity.class);
        intent.putExtra(CommonMethod.ENQUIRY,model);
        startActivity(intent);
    }


}
