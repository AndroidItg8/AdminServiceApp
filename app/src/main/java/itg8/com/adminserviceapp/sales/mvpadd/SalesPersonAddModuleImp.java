package itg8.com.adminserviceapp.sales.mvpadd;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.enquiry.model.StatusModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.Url;

/**
 * Created by Android itg 8 on 12/15/2017.
 */

public class SalesPersonAddModuleImp implements SalesPersonAddMVP.SalesPersonAddModule {
    private Call<StatusModel> call;
    private Call<StatusModel> callUpdate;
    private Call<StatusModel> callDelete;

    @Override
    public void onDestroy() {
        if(call!= null)
        {
            call.cancel();
        }
        if(callUpdate!= null)
        {
            callUpdate.cancel();
        }
        if(callDelete!= null)
        {
            callDelete.cancel();
        }

    }

    @Override
    public void onButtonClicked(String url, String person, String mobile, String anotherMobile, String email, String address, final SalesPersonAddMVP.SalesPersonAddListener listener) {
        call = AppApplication.getInstance().getRetroController().addEngg(url, email, "123456", "123456", "Employee", mobile, person, address,anotherMobile);

        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().isFlag()) {
                        listener.onSuccess("Sales Person Added Successfully..");

                    } else {
                        listener.onError(response.body().getStatus());
                    }
                } else {
                    listener.onError(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof NoConnectivityException)
                {
                    listener.onNoInternetConnect(true);
                }else
                {
                    listener.onError(t.getMessage());
                }
            }
        });

    }

    @Override
    public void onUpdateSalesPerson(String url, int pkid, String name, String mobile, String anotherMobile, String email, String address, final SalesPersonAddMVP.SalesPersonAddListener listener) {
         callUpdate = AppApplication.getInstance().getRetroController().updateEngg(url,pkid ,email, "Employee", mobile, name, address);
        callUpdate.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().isFlag()) {
                        listener.onUpadteSuccess(" Updated Successfully..");

                    } else {
                        listener.onError(response.body().getStatus());
                    }
                } else {
                    listener.onError(response.body().getStatus());
                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof NoConnectivityException)
                {
                    listener.onNoInternetConnect(true);
                }else
                {
                    listener.onError(t.getMessage());
                }
            }
        });
    }

    @Override
    public void onDeleteEngg(String url, int pkid, final SalesPersonAddMVP.SalesPersonAddListener listener) {
       callDelete = AppApplication.getInstance().getRetroController().deleteEngg(url,pkid);
       callDelete.enqueue(new Callback<StatusModel>() {
           @Override
           public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
               if (response.isSuccessful()) {
                   if (response.body().isFlag()) {
                       listener.onSuccessDelete("Sales Person Deleted Successfully..");

                   } else {
                       listener.onError(response.body().getStatus());
                   }
               } else {
                   listener.onError(response.body().getStatus());
               }
           }


           @Override
           public void onFailure(Call<StatusModel> call, Throwable t) {
            t.printStackTrace();
            if(t instanceof NoConnectivityException)
            {
                listener.onNoInternetConnect(true);
            }else
            {
                listener.onError(t.getMessage());
            }
           }
       });

    }


}
