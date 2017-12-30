package itg8.com.adminserviceapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Prefs;
import itg8.com.adminserviceapp.enquiry.EnquiryActivity;
import itg8.com.adminserviceapp.feedback.FeedbackActivity;
import itg8.com.adminserviceapp.gst.GSTActivity;
import itg8.com.adminserviceapp.login.LoginActivity;
import itg8.com.adminserviceapp.sales.SalesPersonActivity;
import itg8.com.adminserviceapp.tender.TenderActivity;
import itg8.com.adminserviceapp.ticket.TicketActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.img_itech)
    ImageView imgItech;
    @BindView(R.id.lbl_app)
    TextView lblApp;
    @BindView(R.id.lbl_iso)
    TextView lblIso;
    @BindView(R.id.ll_iso)
    LinearLayout llIso;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.img_activity)
    ImageView imgActivity;
    @BindView(R.id.lbl_ticket)
    TextView lblTicket;
    @BindView(R.id.card_ticket)
    CardView cardTicket;
    @BindView(R.id.img_acc_gst)
    ImageView imgAccGst;
    @BindView(R.id.lbl_gst)
    TextView lblGst;
    @BindView(R.id.card_gst)
    CardView cardGst;
    @BindView(R.id.ll_first)
    LinearLayout llFirst;
    @BindView(R.id.img_sleep)
    ImageView imgSleep;
    @BindView(R.id.lbl_sales_person)
    TextView lblSalesPerson;
    @BindView(R.id.card_sales_person)
    CardView cardSalesPerson;
    @BindView(R.id.img_enquiry)
    ImageView imgEnquiry;
    @BindView(R.id.lbl_enquiry)
    TextView lblEnquiry;
    @BindView(R.id.card_enquiry)
    CardView cardEnquiry;
    Intent intent;
    @BindView(R.id.img_tender)
    ImageView imgTender;
    @BindView(R.id.lbl_tender)
    TextView lblTender;
    @BindView(R.id.card_tender)
    CardView cardTender;
    private boolean isDestroyed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
        checkLogin();
        cardEnquiry.setOnClickListener(this);
        cardGst.setOnClickListener(this);
        cardSalesPerson.setOnClickListener(this);
        cardTicket.setOnClickListener(this);
         cardTender.setOnClickListener(this);
    }

    private void checkLogin() {
        if (Prefs.getString(CommonMethod.HEADER) == null) {
            callLoginActivity();
            isDestroyed = true;
            finish();
        }
    }

    private void callLoginActivity() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_feedback) {
            startActivity(new Intent(getApplicationContext(), FeedbackActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.card_enquiry:
                intent = new Intent(getApplicationContext(), EnquiryActivity.class);
                break;
            case R.id.card_gst:
                intent = new Intent(getApplicationContext(), GSTActivity.class);
                break;
            case R.id.card_sales_person:
                intent = new Intent(getApplicationContext(), SalesPersonActivity.class);
                break;
            case R.id.card_ticket:
                intent = new Intent(getApplicationContext(), TicketActivity.class);
                break;
            case R.id.card_tender:
                intent = new Intent(getApplicationContext(), TenderActivity.class);
                break;
        }
        callActivity(intent);

    }

    private void callActivity(Intent intent) {
        startActivity(intent);
    }


}
