package itg8.com.adminserviceapp.feedback;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.feedback.model.FeedbackModel;

import static itg8.com.adminserviceapp.common.CommonMethod.checkEmpty;
import static itg8.com.adminserviceapp.common.CommonMethod.checkNull;

public class FeedbackDetailsActivity extends AppCompatActivity {

    private static final String NOT_AVAILABLE = "NOT AVAILABLE";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_problem)
    TextView lblProblem;
    @BindView(R.id.lbl_description)
    TextView lblDescription;
    @BindView(R.id.lbl_engg_name)
    TextView lblEnggName;
    @BindView(R.id.lbl_engg_name_value)
    TextView lblEnggNameValue;
    @BindView(R.id.lbl_rating)
    TextView lblRating;
    @BindView(R.id.rl_problem)
    RelativeLayout rlProblem;
    @BindView(R.id.lbl_product)
    TextView lblProduct;
    @BindView(R.id.lbl_title)
    TextView lblTitle;
    @BindView(R.id.lbl_invoiceNumber)
    TextView lblInvoiceNumber;
    @BindView(R.id.lbl_invoiceNumber_value)
    TextView lblInvoiceNumberValue;
    @BindView(R.id.rl_product)
    RelativeLayout rlProduct;
    @BindView(R.id.lbl_cust)
    TextView lblCust;
    @BindView(R.id.lbl_cus_name_value)
    TextView lblCusNameValue;
    @BindView(R.id.lbl_cus_name)
    TextView lblCusName;
    @BindView(R.id.lbl_cus_mobile_value)
    TextView lblCusMobileValue;
    @BindView(R.id.lbl_cust_mobile)
    TextView lblCustMobile;
    @BindView(R.id.lbl_cust_address_value)
    TextView lblCustAddressValue;
    @BindView(R.id.lbl_cust_address)
    TextView lblCustAddress;
    @BindView(R.id.lbl_cust_email_value)
    TextView lblCustEmailValue;
    @BindView(R.id.lbl_cust_email)
    TextView lblCustEmail;
    @BindView(R.id.rl_cust)
    RelativeLayout rlCust;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.rating)
    RatingBar rating;
    @BindView(R.id.lbl_rating_ddescription)
    TextView lblRatingDdescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();


    }

    private void init() {
        if (getIntent().hasExtra(CommonMethod.FEEDBACK)) {
            FeedbackModel model = getIntent().getParcelableExtra(CommonMethod.FEEDBACK);
            setAllEditText(model);
        }
    }

    private void setAllEditText(FeedbackModel model) {
        String title = checkNull(model.getTicketDetail().getProduct()) ? checkEmpty(model.getTicketDetail().getProduct().get(0).getItemName()) : "Product Name:" + " " + NOT_AVAILABLE;
        String problem = checkNull(model.getTicketDetail().getProblem()) ? checkEmpty(model.getTicketDetail().getProblem().get(0).getProblem()) : "Problem:" + " " + NOT_AVAILABLE;
        String customerName = checkNull(model.getTicketDetail().getCust()) ? checkEmpty(model.getTicketDetail().getCust().get(0).getCustomerName()) : NOT_AVAILABLE;
        String customerEmail = checkNull(model.getTicketDetail().getCust()) ? checkEmpty(model.getTicketDetail().getCust().get(0).getEmail()) : NOT_AVAILABLE;
        String customerMobile = checkNull(model.getTicketDetail().getCust()) ? checkEmpty(model.getTicketDetail().getCust().get(0).getMobileno()) : NOT_AVAILABLE;

        String address = (CommonMethod.checkNull(model.getTicketDetail().getCust().get(0).getAddressLine1())) +
                (CommonMethod.checkNull(model.getTicketDetail().getCust().get(0).getAddressLine2())) +
                ((CommonMethod.checkNull(model.getTicketDetail().getCust().get(0).getAddressLine3())));

        if (TextUtils.isEmpty(address)) {
            lblCustAddressValue.setText("NOT AVAILABLE");

        } else {
            lblCustAddressValue.setText(((CommonMethod.checkNull(model.getTicketDetail().getCust().get(0).getAddressLine1())) +
                    "\n" + (CommonMethod.checkNull(model.getTicketDetail().getCust().get(0).getAddressLine2())) +
                    "\n" + ((CommonMethod.checkNull(model.getTicketDetail().getCust().get(0).getAddressLine3()))))
            );
        }


//        String customerAddress = checkNull(model.getTicketDetail().getCust()) ? CommonMethod.checkEmpty(addess) : NOT_AVAILABLE;

        lblTitle.setText(title);
        lblDescription.setText(problem);
        lblCusNameValue.setText(customerName);
        lblCustEmailValue.setText(customerEmail);
        lblCusMobileValue.setText(customerMobile);
        rating.setRating(CommonMethod.checkRating(model.getRating()));
        lblRatingDdescription.setText(CommonMethod.checkEmpty(model.getDescription()));
        lblInvoiceNumberValue.setText(String.valueOf(model.getTicketDetail().getInvoiceFkid()));
    }

}
