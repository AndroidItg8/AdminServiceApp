package itg8.com.adminserviceapp.sales;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import itg8.com.adminserviceapp.sales.mvpadd.SalesPersonAddMVP;
import itg8.com.adminserviceapp.sales.mvpadd.SalesPersonAddPresenterImp;

public class AddSalesPersonActivity extends AppCompatActivity implements View.OnClickListener, SalesPersonAddMVP.SalesPersonAddView {

    private static final boolean SET_FOCUS = true;
    private static final boolean REMOVE_FOCUS = false;
    private static final int FROM_SAVE = 0;
    private static final int FROM_FAIL = 1;
    BtnType type;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.input_personName)
    EditText inputPersonName;
    @BindView(R.id.input_layout_personName)
    TextInputLayout inputLayoutPersonName;
    @BindView(R.id.input_Mobile)
    EditText inputMobile;
    @BindView(R.id.input_layout_mobile)
    TextInputLayout inputLayoutMobile;
    @BindView(R.id.input_another_Mobile)
    EditText inputAnotherMobile;
    @BindView(R.id.input_layout_another_mobile)
    TextInputLayout inputLayoutAnotherMobile;
    @BindView(R.id.input_address)
    EditText inputAddress;
    @BindView(R.id.input_layout_address)
    TextInputLayout inputLayoutAddress;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.input_email)
    EditText inputEmail;
    @BindView(R.id.input_layout_email)
    TextInputLayout inputLayoutEmail;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    OnSnackbarClicked listener;
    private SalesPersonAddMVP.SalesPersonAddPresenter presenter;
    private Snackbar snackbar;
    private SalesPersonModel model;
    private EditText[] editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sales_person);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter = new SalesPersonAddPresenterImp(this);
        editText = new EditText[]{inputPersonName, inputAnotherMobile, inputMobile, inputAddress, inputEmail};
        init();


    }

    private void init() {
        btnSave.setOnClickListener(this);
        if (getIntent().hasExtra(CommonMethod.SALES_PERSON)) {
            model = getIntent().getParcelableExtra(CommonMethod.SALES_PERSON);
            invalidateOptionsMenu();

            setEditButtonWithContent();
        } else {
            setSaveButtonWithContent();
        }


    }

    private void setSaveButtonWithContent() {
        btnSave.setText("SAVE");
        changeFocusAllEditText(SET_FOCUS);
        type = BtnType.SAVE;
    }

    private void setEditButtonWithContent() {
        setAllEditTextByModel(model);
        btnSave.setText("EDIT");
        changeFocusAllEditText(REMOVE_FOCUS);
        type = BtnType.EDIT;
    }

    private void setUpdateButtonWithContent() {
        setAllEditTextByModel(model);
        btnSave.setText("UPDATE");
        changeFocusAllEditText(SET_FOCUS);
        type = BtnType.UPDATE;

    }

    private void setAllEditTextByModel(SalesPersonModel model) {
        inputPersonName.setText(model.getCustomerName());
        inputEmail.setText(CommonMethod.checkEmpty(model.getEmail()));
        inputMobile.setText(CommonMethod.checkEmpty(model.getMobileno()));
        inputAnotherMobile.setText(CommonMethod.checkEmpty(model.getMobileno()));

        String address = (CommonMethod.checkNull(model.getAddressLine1())) +
                (CommonMethod.checkNull(model.getAddressLine2())) +
                ((CommonMethod.checkNull(model.getAddressLine3())));

        if (TextUtils.isEmpty(address))
            inputAddress.setText("NOT AVAILABLE");
        else
            inputAddress.setText((CommonMethod.checkNull(model.getAddressLine1())) +
                    "\n" + (CommonMethod.checkNull(model.getAddressLine2())) +
                    "\n" + ((CommonMethod.checkNull(model.getAddressLine3())))
            );
    }

    private void changeFocusAllEditText(boolean setFocus) {
        for (EditText edit : editText) {
            edit.setFocusable(setFocus);
            edit.setFocusableInTouchMode(setFocus);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_delete:
                presenter.onDeleteSalesPerson(getString(R.string.url_delete_engg), model.getPkid());
                break;

        }
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                switch (type) {
                    case SAVE:
                        presenter.onSalesPersonAddClicked(view);
                        break;
                    case EDIT:
                        setUpdateButtonWithContent();
                        break;
                    case UPDATE:

                        presenter.onUpdatePersonClicked(view, model.getPkid());
                        break;
                }
                break;
        }
    }

    @Override
    public String getPersonName() {
        return inputPersonName.getText().toString().trim();
    }

    @Override
    public String getMobileNumber() {
        return inputMobile.getText().toString().trim();
    }

    @Override
    public String getAnotherMobileNumber() {
        return inputAnotherMobile.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return inputEmail.getText().toString().trim();
    }

    @Override
    public String getAddress() {
        return inputAddress.getText().toString().trim();
    }

    @Override
    public void onSuccess(String status) {
        showSnackbar(false, CommonMethod.FROM_ERROR, status, FROM_SAVE);
        finish();
        onBackPressed();
    }

    @Override
    public void onFail(String message) {
        showSnackbar(false, CommonMethod.FROM_ERROR, message, FROM_FAIL);

    }

    @Override
    public void onError(Object t) {

        showSnackbar(false, CommonMethod.FROM_ERROR, t.toString(), FROM_FAIL);
    }

    @Override
    public void onPersonNameInvalid(String err) {
        inputPersonName.setError(err);

    }

    @Override
    public void onAnotherMobileNumberInvalid(String err) {
        inputAnotherMobile.setError(err);

    }

    @Override
    public void onMobileNumberInvalid(String err) {
        inputMobile.setError(err);
    }

    @Override
    public void onEmailInvalid(String err) {
        inputEmail.setError(err);
    }

    @Override
    public void onAddressInvalid(String err) {
        inputAddress.setError(err);
    }

    @Override
    public void showProgress() {
        progressView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressView.setVisibility(View.GONE);
    }

    @Override
    public void onNoInternetConnect(boolean b) {
        showSnackbar(b, CommonMethod.FROM_INTERNET, getString(R.string.no_internet), FROM_FAIL);

    }

    @Override
    public void onSuceesUpdate(String status) {
        setEditButtonWithContent();
        showSnackbar(false, CommonMethod.FROM_ERROR, status, FROM_SAVE);

    }

    @Override
    public void onSuccessDelete(String success) {
        showSnackbar(false, CommonMethod.FROM_ERROR, success, FROM_SAVE);
        setAllAfterDeleteItem();
    }

    private void setAllAfterDeleteItem() {
        clearAllEditText();
        setSaveButtonWithContent();

    }

    private void clearAllEditText() {
        for (EditText editText : editText) {
            editText.setText("");
        }
    }

    private void showSnackbar(final boolean isConnected, int from, String message, final int success) {

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
                onSnackbarOkClicked(view, isConnected, success);

            }
        });
        snackbar.show();
    }

    private void onSnackbarOkClicked(View view, boolean isConnected, int success) {
        if (isConnected) {
            presenter.onSalesPersonAddClicked(view);
        }
//        } else {
////            finish();
////            onBackPressed();
//        }
        if (success == FROM_SAVE) {
            setResult(RESULT_OK);
            hideSnackbar();
            finish();
            onBackPressed();
        }
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sales, menu);
        if (model != null) {
            menu.findItem(R.id.action_delete).setVisible(true);
        }
        return true;
    }

    private enum BtnType {
        UPDATE,
        SAVE,
        EDIT
    }

    public interface OnSnackbarClicked {
        void onSnackbarItemClicked();
    }

}
