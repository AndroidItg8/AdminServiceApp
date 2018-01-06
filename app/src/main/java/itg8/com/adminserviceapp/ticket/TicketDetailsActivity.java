package itg8.com.adminserviceapp.ticket;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Logs;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.enquiry.model.StatusModel;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import itg8.com.adminserviceapp.ticket.adapter.CustomSpinnerAdapter;
import itg8.com.adminserviceapp.ticket.model.TempStatusModel;
import itg8.com.adminserviceapp.ticket.model.TicketModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static itg8.com.adminserviceapp.common.CommonMethod.checkEmpty;
import static itg8.com.adminserviceapp.common.CommonMethod.checkNull;

public class TicketDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int DEFAULT_VALUE = 2515415;
    private static final String NOT_AVAILABLE = "Not Available";
    private static final int FROM_SAVE = 0;
    private static final int FROM_FAILED = 1;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.lbl_title)
    TextView lblTitle;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.lbl_description)
    TextView lblDescription;
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

    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.lbl_invoiceNumber)
    TextView lblInvoiceNumber;
    @BindView(R.id.lbl_invoiceNumber_value)
    TextView lblInvoiceNumberValue;
    @BindView(R.id.rl_product)
    RelativeLayout rlProduct;
    @BindView(R.id.rl_cust)
    RelativeLayout rlCust;
    @BindView(R.id.lbl_Engg)
    TextView lblEngg;
    @BindView(R.id.eng_pending_spinner)
    Spinner engPendingSpinner;
    @BindView(R.id.btn_pending_sync)
    Button btnPendingSync;
    @BindView(R.id.progressView_pending)
    ProgressBar progressViewPending;

    @BindView(R.id.lbl_engg_name_Assign_value)
    TextView lblEnggNameAssignValue;

    @BindView(R.id.btn_assign_close)
    Button btnAssignClose;
    @BindView(R.id.progressView_assign)
    ProgressBar progressViewAssign;
    @BindView(R.id.lbl_engg_name_value)
    TextView lblEnggNameValue;
    @BindView(R.id.lbl_engg_name)
    TextView lblEnggName;
    @BindView(R.id.lbl_engg_mobile_value)
    TextView lblEnggMobileValue;
    @BindView(R.id.lbl_engg_mobile)
    TextView lblEnggMobile;
    @BindView(R.id.rl_engg)
    RelativeLayout rlEngg;


    private Snackbar snackbar;
    private TicketModel model;
    private RelativeLayout rl_pending;
    private RelativeLayout rl_assign;
    private RelativeLayout rl_close;
    private Button btnCloseAssign;
    private ProgressBar progressView_Assign;
    private TextView enggNameAssign;
    private Spinner engSpinner;
    private Button btnSyncPending;
    private ProgressBar progressView_Pending;
    private TextView enggMobileNumberAssign;
    private TextView enggNameClose;
    private TextView engMobileClose;
    private int selectedPosition = -1;
    private CustomSpinnerAdapter adapter;
    private TempStatusModel tempStatusModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();


    }


    private void init() {
        if (getIntent().hasExtra(CommonMethod.TICKET)) {
            TicketModel model = getIntent().getParcelableExtra(CommonMethod.TICKET);
            checkFromwichFragment(model, getIntent().getIntExtra(CommonMethod.from, DEFAULT_VALUE));
        }
    }

    private void checkFromwichFragment(TicketModel model, int from) {
        setAllBasicDetails(model);
        tempStatusModel = new TempStatusModel();
        String title = null;
        rl_pending = (RelativeLayout) findViewById(R.id.include_pending);
        rl_assign = (RelativeLayout) findViewById(R.id.include_assign);
        rl_close = (RelativeLayout) findViewById(R.id.include_close);
        this.model = model;

        if (from == CommonMethod.TICKET_STATUS_OPEN) {
            setPendingEnggDetails(model);
            title = "Pending Ticket";

        }
        if (from == CommonMethod.TICKET_STATUS_ASSIGN) {
            setEnggAssignDetails(model);
            title = "Accept Ticket";

        }
        if (from == CommonMethod.TICKET_STATUS_CLOSE) {
            setCloseTicketDetails(model);
            title = "Close Ticket";
        }
        getSupportActionBar().setTitle(title);
    }

    private void setCloseTicketDetails(TicketModel model) {
        showItem(rl_close);
        hideItem(rl_pending, rl_assign);
        findViewByIds(rl_pending, rl_close, rl_assign);
        enggNameClose.setText(checkEmpty(model.getAssignedpersonname()));
        engMobileClose.setText(checkEmpty(model.getAssignedContactno()));
    }

    private void setEnggAssignDetails(final TicketModel model) {
        showItem(rl_assign);
        hideItem(rl_close, rl_pending);
        findViewByIds(rl_pending, rl_close, rl_assign);
        Logs.d("ASSIGN MODEL:" + new Gson().toJson(model));
        enggNameAssign.setText(checkEmpty(model.getAssignedpersonname()));
        enggMobileNumberAssign.setText(checkEmpty(model.getAssignedContactno()));


    }

    private void setPendingEnggDetails(final TicketModel model) {
        showItem(rl_pending);
        hideItem(rl_assign, rl_close);
        findViewByIds(rl_pending, rl_close, rl_assign);
        onDownloadedSalesPersonList(getString(R.string.url_sales_person), 0, 100, model, CommonMethod.FROM_PENDING);
    }

    private void findViewByIds(RelativeLayout rl_pending, RelativeLayout rl_close, RelativeLayout rl_assign) {
        engSpinner = rl_pending.findViewById(R.id.eng_pending_spinner);
        btnSyncPending = rl_pending.findViewById(R.id.btn_pending_sync);
        engSpinner = rl_pending.findViewById(R.id.eng_pending_spinner);
        progressView_Pending = rl_pending.findViewById(R.id.progressView_pending);
        btnCloseAssign = rl_assign.findViewById(R.id.btn_assign_close);
        enggMobileNumberAssign = rl_assign.findViewById(R.id.lbl_engg_mobile_value);
        enggNameAssign = rl_assign.findViewById(R.id.lbl_engg_name_Assign_value);
        progressView_Assign = rl_assign.findViewById(R.id.progressView_assign);
        enggNameAssign.setOnClickListener(this);
        btnCloseAssign.setOnClickListener(this);
        btnSyncPending.setOnClickListener(this);
        enggNameClose = rl_close.findViewById(R.id.lbl_engg_name_value);
        engMobileClose = rl_close.findViewById(R.id.lbl_engg_mobile_value);
    }

    private void hideItem(View hide1, View hide2) {
        hide1.setVisibility(View.GONE);
        hide2.setVisibility(View.GONE);

    }

    private void showItem(View show) {
        show.setVisibility(View.VISIBLE);

    }

    private void setAllBasicDetails(TicketModel model) {
        String title = checkNull(model.getProduct()) ? checkEmpty(model.getProduct().get(0).getItemName()) : "Product Name:" + " " + NOT_AVAILABLE;
        String problem = checkNull(model.getProblem()) ? checkEmpty(model.getProblem().get(0).getProblem()) : "Problem:" + " " + NOT_AVAILABLE;
        String customerName = checkNull(model.getCust()) ? checkEmpty(model.getCust().get(0).getCustomerName()) : "Customer Name:" + " " + NOT_AVAILABLE;
        String customerEmail = checkNull(model.getCust()) ? checkEmpty(model.getCust().get(0).getEmail()) : "Email:" + " " + NOT_AVAILABLE;
        String customerMobile = checkNull(model.getCust()) ? checkEmpty(model.getCust().get(0).getMobileno()) : "Mobile:" + " " + NOT_AVAILABLE;
        String customerAddress = checkNull(model.getCust()) ? checkEmpty(model.getCust().get(0).getAddressLine1()) : "Address:" + " " + NOT_AVAILABLE;
        lblTitle.setText(title);
        lblDescription.setText(problem);
        lblCusNameValue.setText(customerName);
        lblCustEmailValue.setText(customerEmail);
        lblCustAddressValue.setText(customerMobile);
        lblCustAddressValue.setText(customerAddress);
        lblInvoiceNumberValue.setText(String.valueOf(model.getInvoiceFkid()));
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
            case R.id.btn_pending_sync:
                checkSpiinerItemSelecte();
                break;
            case R.id.lbl_engg_name_Assign_value:
                changeTicketStatus(model);
                break;
            case R.id.btn_assign_close:
                setChangeVisibility(model);
                break;
        }

    }

    private void checkSpiinerItemSelecte() {
        if (selectedPosition <= 0) {
            showSnackbar(false, CommonMethod.FROM_ERROR, "Select Engineer First", FROM_FAILED);
        } else {
            setAssignPerson(selectedPosition);
            assignEnggPersonToServer(model, CommonMethod.FROM_PENDING);

        }
    }

    private void changeTicketStatus(TicketModel model) {
        showItem(rl_pending);
        hideItem(rl_close, rl_assign);
        model.setStatus(CommonMethod.TICKET_STATUS_ASSIGN);
//        tempStatusModel.setStatus(CommonMethod.TICKET_STATUS_ASSIGN);
        onDownloadedSalesPersonList(getString(R.string.url_sales_person), 0, 100, model, CommonMethod.FROM_ASSIGN);
    }

    private void setChangeVisibility(TicketModel model) {
        model.setStatus(CommonMethod.TICKET_STATUS_CLOSE);
        assignEnggPersonToServer(model, CommonMethod.FROM_ASSIGN);

    }

    private void assignEnggPersonToServer(TicketModel model, final int from) {
        Call<StatusModel> call = AppApplication.getInstance().getRetroController().updateTicket(getString(R.string.url_update_ticket), model);
        checkShowFrom(from);
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().isFlag()) {
                        checkHideFrom(from);
                        showSnackbar(false, CommonMethod.FROM_ERROR, response.body().getStatus(),FROM_SAVE);
                    } else {
                        checkHideFrom(from);
                        showSnackbar(false, CommonMethod.FROM_ERROR, response.body().getStatus(),FROM_FAILED);
                    }
                } else {
                    checkHideFrom(from);
                    showSnackbar(false, CommonMethod.FROM_ERROR, response.body().getStatus(),FROM_FAILED);
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                t.printStackTrace();
                checkHideFrom(from);


                if (t instanceof NoConnectivityException) {
                    showSnackbar(true, CommonMethod.FROM_ERROR, getString(R.string.no_internet),FROM_FAILED);
                } else {
                    showSnackbar(false, CommonMethod.FROM_ERROR, t.getMessage(),FROM_FAILED);
                }

            }
        });

    }

    public void onDownloadedSalesPersonList(String url, int page, final int limit, final TicketModel model, final int from) {
        checkShowFrom(from);
        Call<List<SalesPersonModel>> call = AppApplication.getInstance().getRetroController().getSalesPersonList(url);
        call.enqueue(new Callback<List<SalesPersonModel>>() {
            @Override
            public void onResponse(Call<List<SalesPersonModel>> call, Response<List<SalesPersonModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        checkHideFrom(from);
                        setDataToAdapter(response.body(), model);
                    } else {
                        checkHideFrom(from);
                        showSnackbar(false, CommonMethod.FROM_ERROR, "Download Failed", FROM_SAVE);

                    }
                } else {
                    checkHideFrom(from);
                    showSnackbar(false, CommonMethod.FROM_ERROR, "Download Failed", FROM_FAILED);
                }
            }


            @Override
            public void onFailure(Call<List<SalesPersonModel>> call, Throwable t) {
                t.printStackTrace();
                checkHideFrom(from);
                if (t instanceof NoConnectivityException) {
                    showSnackbar(true, CommonMethod.FROM_ERROR, getString(R.string.no_internet), FROM_FAILED);
                } else {
                    showSnackbar(false, CommonMethod.FROM_ERROR, t.getMessage(), FROM_FAILED);
                }

            }
        });

    }

    private void checkHideFrom(int from) {
        if (from == CommonMethod.FROM_PENDING)
            hideItem(progressView_Pending);
        else
            hideItem(progressView_Assign);
    }

    private void checkShowFrom(int from) {
        if (from == CommonMethod.FROM_PENDING) {
            showItem(progressView_Pending);
        } else {
            showItem(progressView_Assign);
        }
    }

    private void hideItem(View hide) {
        hide.setVisibility(View.GONE);

    }

    private void showSnackbar(boolean isConnected, int from, String message, final int fromSave) {
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
                onSnackbarOkClicked(view,fromSave);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view, int fromSave) {
         if (fromSave==FROM_SAVE)
         {
             setResult(RESULT_OK);
         }
        hideSnackbar();
         finish();
         onBackPressed();
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }


    private void setDataToAdapter(final List<SalesPersonModel> body, final TicketModel model) {
        hideItem(progressView_Pending);

        SalesPersonModel models = new SalesPersonModel();
        models.setCustomerName("Select Engineer Name");
        body.add(0, models);
        adapter = new CustomSpinnerAdapter(getApplicationContext(), body);
        engSpinner.setAdapter(adapter);

        engSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedPosition = position;


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void setAssignPerson(int position) {
        SalesPersonModel models = adapter.getItem(position);
        String name = models.getCustomerName();
        model.setAssignedpersonname(name);
//        tempStatusModel.setAssignedpersonname(name);
        //  model.setStatus(CommonMethod.TICKET_STATUS_CLOSE);
        //Change Now


    }

}
