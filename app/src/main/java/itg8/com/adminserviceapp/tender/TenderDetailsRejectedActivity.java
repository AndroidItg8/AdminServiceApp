package itg8.com.adminserviceapp.tender;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.tender.model.TenderModel;

public class TenderDetailsRejectedActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_title)
    TextView lblTitle;
    @BindView(R.id.lbl_description)
    TextView lblDescription;
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
        setAllEditText();
    }

    private void setAllEditText() {
        if(getIntent().hasExtra(CommonMethod.TENDER))
        {
            TenderModel model = getIntent().getParcelableExtra(CommonMethod.TENDER);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

}
