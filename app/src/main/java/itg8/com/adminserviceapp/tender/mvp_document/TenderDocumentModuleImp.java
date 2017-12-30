package itg8.com.adminserviceapp.tender.mvp_document;

import java.util.List;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.enquiry.model.StatusModel;
import itg8.com.adminserviceapp.tender.model.CustomTenderDocumentModel;
import itg8.com.adminserviceapp.tender.model.DocumentModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 12/25/2017.
 */

public class TenderDocumentModuleImp implements TenderDocumentMVP.TenderDocumentModule {
    @Override
    public void onDestroyed() {

    }

    @Override
    public void onDownloadedTenderList(String url, List<CustomTenderDocumentModel> list, final TenderDocumentMVP.TenderDocumentListener listner) {
        Call<StatusModel> call = AppApplication.getInstance().getRetroController().submitDocumentList(url, list);
        call.enqueue(new Callback<StatusModel>() {
            @Override
            public void onResponse(Call<StatusModel> call, Response<StatusModel> response) {
                if(response.isSuccessful())
                {
                    if(response.body().isFlag())
                    {
                        listner.onSuccess(response.body().getStatus());
                    }else
                    {
                        listner.onError(response.body().getStatus());

                    }
                }else
                {
                    listner.onError(response.body().getStatus());

                }
            }

            @Override
            public void onFailure(Call<StatusModel> call, Throwable t) {
                t.printStackTrace();
                if(t instanceof NoConnectivityException)
                {
                    listner.onNoInternetConnection(true);
                }else
                {
                    listner.onError(t.getMessage());
                }
            }
        });

    }
}
