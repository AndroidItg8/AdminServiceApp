package itg8.com.adminserviceapp.sales;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;

public class SalesDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_personName)
    TextView lblPersonName;
    @BindView(R.id.lbl_mobile)
    TextView lblMobile;
    @BindView(R.id.lbl_email)
    TextView lblEmail;
    @BindView(R.id.lbl_address)
    TextView lblAddress;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    private SalesPersonModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();


    }

    private void init() {
        if (getIntent().hasExtra(CommonMethod.SALES_PERSON)) {
            model = getIntent().getParcelableExtra(CommonMethod.SALES_PERSON);

            lblPersonName.setText(model.getCustomerName());
            lblEmail.setText(CommonMethod.checkEmpty(model.getEmail()));

            String address = (CommonMethod.checkNull(model.getAddressLine1())) +
                    (CommonMethod.checkNull(model.getAddressLine2())) +
                    ((CommonMethod.checkNull(model.getAddressLine3())));

            if (TextUtils.isEmpty(address))
                lblAddress.setText("NOT AVAILABLE");
            else
                lblAddress.setText((CommonMethod.checkNull(model.getAddressLine1())) +
                        "\n" + (CommonMethod.checkNull(model.getAddressLine2())) +
                        "\n" + ((CommonMethod.checkNull(model.getAddressLine3())))
                );
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
