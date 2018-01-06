package itg8.com.adminserviceapp.enquiry.mvp;

import java.util.List;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class EnquiryModuleImp implements EnquiryMVP.EnquiryModule {
    @Override
    public void onDestroyed() {

    }

    @Override
    public void onDownloadedEnquiryList(String url, final int page, int limit, final EnquiryMVP.EnquiryListener listner) {
        Call<List<EnquiryModel>> call = AppApplication.getInstance().getRetroController().getEnquiryList(url,page,limit);
         call.enqueue(new Callback<List<EnquiryModel>>() {
             @Override
             public void onResponse(Call<List<EnquiryModel>> call, Response<List<EnquiryModel>> response) {
                 if(response.isSuccessful())
                 {
                     if(response.body()!= null)
                     {
                         listner.onDownloadedEnquiryList(response.body(),page);
                     }else {
                         if(page==0)
                         {
                             listner.emptyList();
                         }else
                         {
                             listner.onNoMoreList(1);
                         }
                     }
                 }else
                 {
                     listner.onError("Download Failed",1);

                 }
             }



             @Override
             public void onFailure(Call<List<EnquiryModel>> call, Throwable t) {

                 t.printStackTrace();
                 if(t instanceof NoConnectivityException)
                 {
                     listner.onNoInternetConnection(true,1);
                 }else
                 {
                     listner.onError(t.getMessage(),1);
                 }
             }
         });


    }
}
