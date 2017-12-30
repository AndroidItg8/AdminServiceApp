package itg8.com.adminserviceapp.sales;

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
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import itg8.com.adminserviceapp.sales.mvp.SalePersonPresenterImp;
import itg8.com.adminserviceapp.sales.mvp.SalesPersonMVP;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class SalesPersonActivity extends AppCompatActivity implements View.OnClickListener, SalesPersonMVP.SalesPersonView, SalesPersonAdapterAdapter.onItemClickedListener, EasyPermissions.PermissionCallbacks {

    private static final int RC_CALL = 123;
    private static final int RC_CODE = 345;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    @BindView(R.id.rl_top)
    RelativeLayout rlTop;
    @BindView(R.id.img_no)
    ImageView imgNo;
    @BindView(R.id.rl_no_item)
    RelativeLayout rlNoItem;
    private String[] permissions;
    private boolean canPhoneState;
    private boolean canPhoneCall;
    private Snackbar snackbar;
    private SalesPersonAdapterAdapter adapter;
    private SalesPersonMVP.SalesPersonPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_person);
        ButterKnife.bind(this);
         presenter =new SalePersonPresenterImp(this);
         callPresenter();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkStoragePerm();


    }

    public void callPresenter() {
        presenter.downloadSalesPersonList(getString(R.string.url_sales_person));

    }

    private void init(List<SalesPersonModel> list) {

        if(list.size()>0)
         {
             CommonMethod.showHideItem(recyclerView, rlNoItem);
             recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
             adapter=new SalesPersonAdapterAdapter(getApplicationContext(), list, this);
             recyclerView.setAdapter(adapter);

         }else
         {
             CommonMethod.showHideItem( rlNoItem,recyclerView);

         }
        fab.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == fab) {
            Intent intent = new Intent(getApplicationContext(),AddSalesPersonActivity.class);
            startActivity(intent);

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
        showSnackbar(b,CommonMethod.FROM_INTERNET,getString(R.string.no_internet));

    }

    @Override
    public void onError(String mesg) {
        showSnackbar(false,CommonMethod.FROM_ERROR,mesg);
    }

    @Override
    public void onNoMoreList(int from) {
        //        adapter.removeFooter();


    }

    @Override
    public void onShowPaginationLoading(boolean show, int from) {
//        if (adapter == null)
//            return;
//        if (show) {
//          //  adapter.addFooter();
//            adapter.notifyItemInserted(adapter.getItemCount() - 1);
////
//        } else {
//         //   adapter.removeFooter();
//           // adapter.notifyItemRemoved(adapter.getModelSize());
////
//
//        }

    }

    @Override
    public void onPaginationError(boolean show, int from) {

    }



    @Override
    public void getSalesPersonList(List<SalesPersonModel> list) {
//        adapter.addItems(list);
         init(list);

    }

    @Override
    public void onItemClicked(int position, SalesPersonModel model) {
        if (!canPhoneCall) {
            showSnackbar(false, CommonMethod.FROM_ERROR, getString(R.string.rationale_no_permission));
        } else {

             String telNo = "tel:" + model.getMobileno();
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
    public void onItemClickedListener(int position, SalesPersonModel model) {
        Intent intent = new Intent(getApplicationContext(), AddSalesPersonActivity.class);
        intent.putExtra(CommonMethod.SALES_PERSON,model);
        startActivityForResult(intent,RC_CODE);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RC_CODE && resultCode==RESULT_OK)
        {
            callPresenter();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
