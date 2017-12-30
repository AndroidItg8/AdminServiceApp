package itg8.com.adminserviceapp.feedback.mvp;

import java.util.List;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.feedback.model.FeedbackModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class FeedbackModuleImp implements FeedbackMVP.FeedbackModule {
    @Override
    public void onDestroyed() {

    }

    @Override
    public void onDownloadedFeedbackList(String url, final int page, int limit, final FeedbackMVP.FeedbackListener listner) {
        Call<List<FeedbackModel>> call = AppApplication.getInstance().getRetroController().getFeedbackList(url,page,limit);
        call.enqueue(new Callback<List<FeedbackModel>>() {
            @Override
            public void onResponse(Call<List<FeedbackModel>> call, Response<List<FeedbackModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body()!= null)
                    {
                        listner.onDownloadedFeedbackList(response.body(),page);
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
            public void onFailure(Call<List<FeedbackModel>> call, Throwable t) {
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
