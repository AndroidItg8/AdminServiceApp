package itg8.com.adminserviceapp.login.mvp;


import android.view.View;

/**
 * Created by Android itg 8 on 10/9/2017.
 */

public interface LoginMvp {
     public  interface  LoginView
     {
         String getUsername();
         String getPassword();
         void onSuccess();
         void onFail(String message);
         void onError(Object t);
         void onUsernameInvalid(String err);
         void onPasswordInvalid(String err);
         void showProgress();
         void hideProgress();
         void onNoInternetConnect(boolean b);
         void onFirstTimeLogin(String success);
     }
      public interface LoginPresenter
      {
          void onDestroy();
          void onLoginClicked(View view);

      }
      public interface LoginModule
      {
          void onDestroy();
          void onSendToServer( String url, String username, String password, LoginListener loginPresenterImp);

      }

    public interface LoginListener{
        void onSuccess();
        void onError(Object t);

        void onFirstTimeLogin(String success);
        void onNoInternetConnect(boolean b);
    }
}
