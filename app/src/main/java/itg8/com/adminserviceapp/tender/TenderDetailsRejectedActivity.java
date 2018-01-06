package itg8.com.adminserviceapp.tender;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.enquiry.model.StatusModel;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TenderDetailsRejectedActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int FROM_SUCCESS = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.view)
    View view;
    @BindView(R.id.lbl_open_date_value)
    TextView lblOpenDateValue;
    @BindView(R.id.lbl_open_date)
    TextView lblOpenDate;
    @BindView(R.id.lbl_status_value)
    TextView lblStatusValue;
    @BindView(R.id.lbl_status)
    TextView lblStatus;
    @BindView(R.id.lbl_tender_fee_value)
    TextView lblTenderFeeValue;
    @BindView(R.id.lbl_tender_fee)
    TextView lblTenderFee;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.lbl_tender_value)
    TextView lblTenderValue;
    @BindView(R.id.lbl_tender)
    TextView lblTender;
    @BindView(R.id.lbl_location_place)
    TextView lblLocationPlace;
    @BindView(R.id.lbl_location)
    TextView lblLocation;
    @BindView(R.id.lbl_emd_value)
    TextView lblEmdValue;
    @BindView(R.id.lbl_emd)
    TextView lblEmd;
    @BindView(R.id.btn_refund)
    Button btnRefund;
    @BindView(R.id.lbl_Refund_date_value)
    TextView lblRefundDateValue;
    @BindView(R.id.lbl_Refund_date)
    TextView lblRefundDate;
    @BindView(R.id.progressView)
    ProgressBar progressView;

    private RelativeLayout rl_top;
    private PendingTenderModel model;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tender_details_rejected);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();

    }

    private void init() {
        checkInit();
        btnRefund.setOnClickListener(this);
    }

    private void checkInit() {
        if (getIntent().hasExtra(CommonMethod.TENDER)) {
            model = getIntent().getParcelableExtra(CommonMethod.TENDER);
            setAllEditText(model);
        }
    }

    private void setAllEditText(PendingTenderModel model) {
        rl_top = (RelativeLayout) findViewById(R.id.include);
        TextView title = rl_top.findViewById(R.id.lbl_title);
        TextView description = rl_top.findViewById(R.id.lbl_description);
        TextView closeDate = rl_top.findViewById(R.id.lbl_open_date_value);
        TextView tenderFeeValue = rl_top.findViewById(R.id.lbl_tender_fee_value);
        title.setText(CommonMethod.checkEmpty(model.getTitle()));
        description.setText(CommonMethod.checkEmpty(model.getDescription()));
        Calendar calendar = (CommonMethod.convertStringToDate(model.getClosedDate()));
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DATE));
        closeDate.setText(day + "-" + month + "-" + year);
        tenderFeeValue.setText(model.getTenderFeeIn() != null ? getString(R.string.rps) + model.getTenderFeeIn() : "NOT AVAILABLE");
        lblEmdValue.setText(model.getEMDAMT() != null ? getString(R.string.rps) + model.getEMDAMT() : "NOT AVAILABLE");
        lblTenderValue.setText(model.getTenderValueIn() != null ? getString(R.string.rps) + model.getTenderValueIn() : "NOT AVAILABLE");
        lblLocationPlace.setText(CommonMethod.checkEmpty(model.getLocation()));
        if (model.getEMDStatus()) {

            btnRefund.setText("REFUNDED");
            btnRefund.setVisibility(View.GONE);
            btnRefund.setEnabled(false);
            showModelView(model);
        }
         else{
                btnRefund.setText(" NOT REFUNDED");
            btnRefund.setVisibility(View.VISIBLE);

            btnRefund.setEnabled(true);
                hideModelView();
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
    public void onClick(View view) {

        switch (view.getId()) {
            /*** NOT REFUNDED**/
            case R.id.btn_refund:
                sendFundValueToServer();
                break;


        }

    }

    private void sendFundValueToServer() {
        model.setEMDStatus(true);
        model.setENDRefundDate(CommonMethod.currentDate());
        CommonMethod.showHideItem(progressView, btnRefund);

        Call<StatusModel> call = AppApplication.getInstance().getRetroController().updateTender(getString(R.string.url_update_tender), model);
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.isSuccessful()) {
                    CommonMethod.showHideItem(btnRefund, progressView);

                    if (response.body().isFlag()) {
                        showSnackbar(false, FROM_SUCCESS, "Updated Successfully");
                    } else {
                        showSnackbar(false, CommonMethod.FROM_ERROR, response.body().getStatus());
                    }
                } else {
                    CommonMethod.showHideItem(btnRefund, progressView);
                    showSnackbar(false, CommonMethod.FROM_ERROR, response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                t.printStackTrace();
                CommonMethod.showHideItem(btnRefund, progressView);
                if (t instanceof NoConnectivityException) {
                    showSnackbar(true, CommonMethod.FROM_ERROR, getString(R.string.no_internet));
                } else {
                    showSnackbar(false, CommonMethod.FROM_ERROR, t.getMessage());
                }

            }
        });


    }

    private void showSnackbar(boolean isConnected, final int from, String message) {


            int color = Color.WHITE;
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
                onSnackbarOkClicked(view, from);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view, int from) {
        if (from == FROM_SUCCESS) {
            setResult(RESULT_OK);
            finish();
            onBackPressed();
        }
        hideSnackbar();

    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    private void hideModelView() {
        TextView refundedStatusValue = rl_top.findViewById(R.id.lbl_status_value);
        TextView refundedStatus = rl_top.findViewById(R.id.lbl_status);
        TextView refundedDateValue = rl_top.findViewById(R.id.lbl_Refund_date_value);
        TextView refundedDate = rl_top.findViewById(R.id.lbl_Refund_date);
        hideView(refundedDate, refundedDateValue);
        showView(refundedStatusValue,refundedStatus);
        refundedStatusValue.setText("NO");
        refundedStatusValue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorRed));


    }

    private void showModelView(PendingTenderModel model) {
        TextView refundedStatusValue = rl_top.findViewById(R.id.lbl_status_value);
        TextView refundedStatus = rl_top.findViewById(R.id.lbl_status);
        TextView refundedDateValue = rl_top.findViewById(R.id.lbl_Refund_date_value);
        TextView refundedDate = rl_top.findViewById(R.id.lbl_Refund_date);
        showView(refundedDate, refundedDateValue, refundedStatusValue, refundedStatus);
        refundedStatusValue.setText(model.getEMDStatus()?"YES":"NO");
//        Calendar calendar = Calendar.getInstance();
//        int date = calendar.get(Calendar.DATE);
//        int month = calendar.get(Calendar.MONTH);
//        int year = calendar.get(Calendar.YEAR);
//      //  date + "-" + month + "-" + year;

        refundedDateValue.setText((model.getENDRefundDate()));
        refundedStatusValue.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorGreen));
    }

    private void hideView(TextView... textViews) {
        for (TextView tex :
                textViews) {
            tex.setVisibility(View.GONE);
        }

    }

    private void showView(TextView... textViews) {
        for (TextView tex :
                textViews) {
            tex.setVisibility(View.VISIBLE);
        }

    }
}
