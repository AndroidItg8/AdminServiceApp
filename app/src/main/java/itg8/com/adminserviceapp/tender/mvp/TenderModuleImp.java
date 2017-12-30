package itg8.com.adminserviceapp.tender.mvp;

import android.util.Log;

import java.io.IOException;
import java.util.List;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 12/19/2017.
 */

public class TenderModuleImp implements TenderMVP.TenderModule {
    @Override
    public void onDestroyed() {

    }

    @Override
    public void onDownloadedTenderList(String url, final int page, final int limit, final int from, final TenderMVP.TenderListener listner) {
        Call<List<PendingTenderModel>> call = AppApplication.getInstance().getRetroController().getPendingTenderList(url,page,limit);
        call.enqueue(new Callback<List<PendingTenderModel>>() {
            @Override
            public void onResponse(Call<List<PendingTenderModel>> call, Response<List<PendingTenderModel>> response) {

                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        listner.onDownloadedTenderList(response.body(),from,page);
                    }else {
                        if (page == 0) {
                            listner.emptyList(from);

                        } else {
                            listner.onNoMoreList(from);

                        }
                    }
                }else
                {
                    listner.onError("Download Failed",from);

                }
            }

            @Override
            public void onFailure(Call<List<PendingTenderModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof NoConnectivityException) {
                    // We had non-2XX http error
                    Log.d("TAG TenderModuleImp:","IN HTTPEXCEPTION: "+t.getMessage());
                    listner.onNoInternetConnection(true,from);
                }
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.d("TAG TenderModuleImp:","IN IOException: "+t.getMessage());
                }
//                listner.onPaginationError(from);
            }


        });




    }

    @Override
    public void onDownloadedSubmitedTenderList(String url, final int page, int limit, final int from, final TenderMVP.TenderListener listner) {
        Call<List<PendingTenderModel>> callSubmit = AppApplication.getInstance().getRetroController().getSubmittedTenderList(url,page,limit);
        callSubmit.enqueue(new Callback<List<PendingTenderModel>>() {
            @Override
            public void onResponse(Call<List<PendingTenderModel>> call, Response<List<PendingTenderModel>> response) {

                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        listner.onDownloadedSubmitedTenderList(response.body(),from, page);
                    }
                    else {
                        if (page == 0) {
                            listner.emptyList(from);

                        } else {
                            listner.onNoMoreList(from);

                        }
                    }

                }else
                {
                    listner.onError("Download Failed",from);

                }
            }

            @Override
            public void onFailure(Call<List<PendingTenderModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof NoConnectivityException) {
                    // We had non-2XX http error
                    Log.d("TAG TenderModuleImp:","IN HTTPEXCEPTION: "+t.getMessage());
                    listner.onNoInternetConnection(true,from);
                }
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.d("TAG TenderModuleImp:","IN IOException: "+t.getMessage());
                }
//                listner.onPaginationError(from);
            }
        });
    }

    @Override
    public void onDownloadedRejectedTenderList(String url, final int page, final int limit, final int from, final TenderMVP.TenderListener listner) {
        Call<List<PendingTenderModel>> callRejected = AppApplication.getInstance().getRetroController().getRejectedTenderList(url,page,limit,from);
        callRejected.enqueue(new Callback<List<PendingTenderModel>>() {
            @Override
            public void onResponse(Call<List<PendingTenderModel>> call, Response<List<PendingTenderModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        listner.onDownloadedRejectedTenderList(response.body(),from, page);

                    }else {
                        if (page == 0) {
                            listner.emptyList(from);

                        } else
                        {
                            listner.onNoMoreList(from);
                        }
                    }
                }else
                {
                    listner.onError("Download Failed",from);

                }
            }

            @Override
            public void onFailure(Call<List<PendingTenderModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof NoConnectivityException) {
                    // We had non-2XX http error
                    Log.d("TAG TenderModuleImp:","IN HTTPEXCEPTION: "+t.getMessage());
                    listner.onNoInternetConnection(true,from);
                }
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.d("TAG TenderModuleImp:","IN IOException: "+t.getMessage());
                }
//                listner.onPaginationError(from);
            }


        });

    }
}
