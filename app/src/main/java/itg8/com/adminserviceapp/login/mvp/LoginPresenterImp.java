package itg8.com.adminserviceapp.login.mvp;

import android.text.TextUtils;
import android.view.View;

import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.BaseWeakPresenter;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class LoginPresenterImp extends BaseWeakPresenter<LoginMvp.LoginView> implements LoginMvp.LoginListener,LoginMvp.LoginPresenter {
    private final LoginMvp.LoginModule module;
    private boolean isValidate=false;

    public LoginPresenterImp(LoginMvp.LoginView loginView) {
        super(loginView);
        module = new LoginModuleImp();
    }

    @Override
    public void onDestroy() {
         if(hasView())
         {
             module.onDestroy();

         }

    }

    @Override
    public void onLoginClicked(View view) {
         String name = getView().getUsername();
         String password = getView().getPassword();
          boolean isValid =true;
        if (TextUtils.isEmpty(name)) {
            isValid = false;
            getView().onUsernameInvalid(view.getContext().getString(R.string.empty));
        }
        if (TextUtils.isEmpty(password)) {
            isValid = false;
            getView().onPasswordInvalid(view.getContext().getString(R.string.empty));
        }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
                isValid = false;
                getView().onUsernameInvalid(view.getContext().getString(R.string.invalid_email));
            }



        if (password.length() < 6) {
            isValid = false;
            getView().onPasswordInvalid(view.getContext().getString(R.string.invalid_pass));
        }
        if (isValid) {
            getView().showProgress();
            module.onSendToServer(view.getContext().getString(R.string.url_login), name, password, this);

        }

    }


    @Override
    public void onSuccess() {
        if(hasView())
        {
            getView().hideProgress();
            getView().onSuccess();
        }
    }



    @Override
    public void onError(Object t) {
         if(hasView())
         {
             getView().hideProgress();
             getView().onError(t);
         }

    }





    @Override
    public void onFirstTimeLogin(String success) {
        if(hasView())
        {
            getView().hideProgress();
            getView().onFirstTimeLogin(success);
        }
    }

    @Override
    public void onNoInternetConnect(boolean b) {
         if(hasView())
         {
             getView().hideProgress();
             getView().onNoInternetConnect(b);
         }

    }
}
