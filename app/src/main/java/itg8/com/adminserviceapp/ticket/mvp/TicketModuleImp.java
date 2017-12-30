package itg8.com.adminserviceapp.ticket.mvp;

import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import itg8.com.adminserviceapp.common.AppApplication;
import itg8.com.adminserviceapp.common.NoConnectivityException;
import itg8.com.adminserviceapp.ticket.model.TicketModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class TicketModuleImp implements TicketMVP.TicketModule {
    @Override
    public void onDestroyed() {

    }



    @Override
    public void onDownloadedOpenTicketList(String url, final int page, int limit, final int status, final TicketMVP.TicketListener listner) {
        Call<List<TicketModel>> call = AppApplication.getInstance().getRetroController().getOpenTicketList(url,page,limit,status);
        call.enqueue(new Callback<List<TicketModel>>() {
            @Override
            public void onResponse(Call<List<TicketModel>> call, Response<List<TicketModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        listner.onDownloadedOpenTicketList(response.body(), page,status);
                    }
                    else {
                        if (page == 0) {
                            listner.emptyList(status);

                        } else {
                            listner.onNoMoreList(status);
                        }
                    }
                }else
                {
                    listner.onError("Download Failed",status);

                }

            }

            @Override
            public void onFailure(Call<List<TicketModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof NoConnectivityException) {
                    // We had non-2XX http error
                    Log.d("TAG TenderModuleImp:","IN HTTPEXCEPTION: "+t.getMessage());
                    listner.onNoInternetConnection(true,status);
                }
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.d("TAG TenderModuleImp:","IN IOException: "+t.getMessage());
                }
            }
        });
    }

    @Override
    public void onDownloadedCloseTicketList(String url, final int page, int limit, final int status, final TicketMVP.TicketListener listner) {
        Call<List<TicketModel>> call = AppApplication.getInstance().getRetroController().getCloseTicketList(url,page,limit,status);
        call.enqueue(new Callback<List<TicketModel>>() {
            @Override
            public void onResponse(Call<List<TicketModel>> call, Response<List<TicketModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        listner.onDownloadedCloseTicketList(response.body(), page,status);
                    }
                    else {
                        if (page == 0) {
                            listner.emptyList(status);

                        } else {
                            listner.onNoMoreList(status);

                        }}
                }else
                {
                    listner.onError("Download Failed",status);

                }

            }

            @Override
            public void onFailure(Call<List<TicketModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof NoConnectivityException) {
                    // We had non-2XX http error
                    Log.d("TAG TenderModuleImp:","IN HTTPEXCEPTION: "+t.getMessage());
                    listner.onNoInternetConnection(true,status);
                }
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.d("TAG TenderModuleImp:","IN IOException: "+t.getMessage());
                }
            }
        });
    }

    @Override
    public void onDownloadedAcceptTicketList(String url, final int page, int limit, final int status, final TicketMVP.TicketListener listner) {
        Call<List<TicketModel>> call = AppApplication.getInstance().getRetroController().getAcceptTicketList(url,page,limit,status);
        call.enqueue(new Callback<List<TicketModel>>() {
            @Override
            public void onResponse(Call<List<TicketModel>> call, Response<List<TicketModel>> response) {
                if(response.isSuccessful())
                {
                    if(response.body().size()>0)
                    {
                        listner.onDownloadedAcceptTicketList(response.body(), page,status);
                    }
                    else {
                        if (page == 0) {
                            listner.emptyList(status);

                        } else {
                            listner.onNoMoreList(status);

                        }
                    }
                }else
                {
                    listner.onError("Download Failed",status);

                }

            }

            @Override
            public void onFailure(Call<List<TicketModel>> call, Throwable t) {
                t.printStackTrace();
                if (t instanceof NoConnectivityException) {
                    // We had non-2XX http error
                    Log.d("TAG TenderModuleImp:","IN HTTPEXCEPTION: "+t.getMessage());
                    listner.onNoInternetConnection(true,status);
                }
                if (t instanceof IOException) {
                    // A network or conversion error happened
                    Log.d("TAG TenderModuleImp:","IN IOException: "+t.getMessage());
                }
            }
        });
    }
}
