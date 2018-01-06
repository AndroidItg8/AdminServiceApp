package itg8.com.adminserviceapp.enquiry;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;

public class EnquiryDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_enquiry)
    TextView lblEnquiry;
    @BindView(R.id.input_product)
    EditText inputProduct;
    @BindView(R.id.input_name)
    EditText inputName;
    @BindView(R.id.input_number)
    EditText inputNumber;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_number2)
    EditText inputNumber2;
    @BindView(R.id.input_description)
    EditText inputDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        checkIntent();
    }

    private void checkIntent() {
        EnquiryModel model=null;
        if (getIntent().hasExtra(CommonMethod.ENQUIRY)) {
           model = getIntent().getParcelableExtra(CommonMethod.ENQUIRY);

        }else
        {
            model = getIntent().getParcelableExtra(CommonMethod.FROM_NOTIFICATION);

        }
        setAllEditText(model);
    }

    private void setAllEditText(EnquiryModel model) {
        inputProduct.setText(CommonMethod.checkEmpty(model.getProduct().get(0).getItemName()));
        inputName.setText(CommonMethod.checkEmpty(model.getCust().get(0).getCustomerName()));
        inputEmail.setText(CommonMethod.checkEmpty(model.getCust().get(0).getEmail()));
        inputNumber.setText(CommonMethod.checkEmpty(model.getCust().get(0).getMobileno()));
        inputNumber2.setText(CommonMethod.checkEmpty(model.getCust().get(0).getTelephoneno()));
        inputDescription.setText(CommonMethod.checkEmpty(model.getDescription()));


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
