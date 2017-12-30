package itg8.com.adminserviceapp.login.mvp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.common.Prefs;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class LoginModuleImp implements LoginMvp.LoginModule {
    @Override
    public void onDestroy() {

    }

    @Override
    public void onSendToServer(String url, String username, String password, final LoginMvp.LoginListener listener) {
        Call<ResponseBody> call = AppApplication.getInstance().getRetroController().checkAuthentication(url, "password",username, password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.code() == 400) {
                    listener.onError("Invalid Credential");
                    return;
                }
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        try {
                            String res = response.body().string();
                            JSONObject object = new JSONObject(res);
                            if (object.has("access_token")) {
                                Prefs.putString(CommonMethod.HEADER, object.getString("access_token"));
                                AppApplication.getInstance().resetRetroAfterLogin();
//                                listener.onSuccess();
                                if (object.has("Firstlogin")) {
                                    if (object.getString("Firstlogin").equalsIgnoreCase("0")) {
                                        listener.onFirstTimeLogin("Success");
                                    }
                                    else {
                                        listener.onSuccess();
                                    }
                                } else {
                                    listener.onError("Invalid User");
                                }
                            }else
                            {
                                listener.onError("Invalid User");
                            }



                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    listener.onError(response.message());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof NoConnectivityException) {
                    listener.onNoInternetConnect(true);

                } else {
                    listener.onError(t);
                }

            }
        });

    }
}
