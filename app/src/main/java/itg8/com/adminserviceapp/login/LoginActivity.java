package itg8.com.adminserviceapp.login;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Prefs;
import itg8.com.adminserviceapp.home.HomeActivity;
import itg8.com.adminserviceapp.login.mvp.LoginMvp;
import itg8.com.adminserviceapp.login.mvp.LoginPresenterImp;


public class LoginActivity extends AppCompatActivity implements LoginMvp.LoginView, View.OnClickListener {


    private static final String TAG = LoginActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.img_itech)
    ImageView imgItech;
    @BindView(R.id.txt_optLogin)
    TextView txtOptLogin;
    @BindView(R.id.edt_userName)
    EditText edtUserName;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_save)
    Button btnSave;
    @BindView(R.id.progressView)
    ProgressBar progressView;
    @BindView(R.id.card)
    CardView card;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.coordinator)
    CoordinatorLayout coordinator;

    private Snackbar snackbar;
    private LoginMvp.LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle("Profile");

        init();


    }





    private void init() {
        edtUserName.setText("admin@admin.com");
        edtPassword.setText("123456");
        presenter = new LoginPresenterImp(this);
        btnSave.setOnClickListener(this);





    }





    private void showSnackbar(boolean isConnected,int from, String message) {

        int color;
        if (!isConnected) {

            message = "Connected to Internet";
            color = Color.WHITE;
            hideSnackbar();

        } else {
            message = " Not connected to internet...Please try again";
            color = Color.RED;
        }
        snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_INDEFINITE);

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
        presenter.onLoginClicked(view);
    }

    public void hideSnackbar() {
        if (snackbar != null && snackbar.isShown()) {
            snackbar.dismiss();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public String getUsername() {
        return edtUserName.getText().toString();
    }

    @Override
    public String getPassword() {
        return edtPassword.getText().toString();
    }

    @Override
    public void onSuccess() {
        finish();
        callHomeActivity();
    }

    @Override
    public void onFail(String message) {
        showSnackbar(false, CommonMethod.FROM_ERROR, message);

    }

    @Override
    public void onError(Object t) {
        showSnackbar(false, CommonMethod.FROM_ERROR, t.toString());

    }

    @Override
    public void onUsernameInvalid(String err) {
        edtUserName.setError(err);
    }

    @Override
    public void onPasswordInvalid(String err) {
        edtPassword.setError(err);


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
        showSnackbar(b, CommonMethod.FROM_INTERNET, getString(R.string.no_internet));

    }

    @Override
    public void onFirstTimeLogin(String success) {
        finish();
        callHomeActivity();

    }

    private void callHomeActivity() {

        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_save:
                presenter.onLoginClicked(view);
                break;

        }
    }
}
