package itg8.com.adminserviceapp.sales.mvp;

import java.util.List;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class SalesPersonModuleImp implements SalesPersonMVP.SalesPersonModule {
    @Override
    public void onDestroyed() {

    }

    @Override
    public void onDownloadedSalesPersonList(String url, int page, final int limit, final SalesPersonMVP.SalesPersonListener listner) {
        Call<List<SalesPersonModel>> call = AppApplication.getInstance().getRetroController().getSalesPersonList(url);
        call.enqueue(new Callback<List<SalesPersonModel>>() {
            @Override
            public void onResponse(Call<List<SalesPersonModel>> call, Response<List<SalesPersonModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        listner.onDownloadedSalesPersonList(response.body());
                    }else {
                        listner.onError("Download Failed",0);
                    }
                }else
                {
                    listner.onError("Download Failed",0);

                }
            }

            @Override
            public void onFailure(Call<List<SalesPersonModel>> call, Throwable t) {
                 t.printStackTrace();
                 if(t instanceof NoConnectivityException)
                 {
                     listner.onNoInternetConnection(true,0);
                 }else
                 {
                     listner.onError(t.getMessage(),0);
                 }

            }
        });

    }
}
