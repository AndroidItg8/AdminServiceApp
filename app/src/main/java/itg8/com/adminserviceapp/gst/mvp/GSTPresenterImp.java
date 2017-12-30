package itg8.com.adminserviceapp.gst.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itg8.com.adminserviceapp.common.BaseWeakPresenter;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class GSTPresenterImp extends BaseWeakPresenter<GSTMVP.GSTView> implements GSTMVP.GSTPresenter,GSTMVP.GSTListener {
    private GSTMVP.GSTModule module;
    private int page=0;
    private int limit=10;

    public GSTPresenterImp(GSTMVP.GSTView gstView) {
        super(gstView);
        module = new GSTModuleImp();
    }

    @Override
    public void onDestroyed() {
        if (hasView()) {
            module.onDestroyed();
        }

    }

    @Override
    public void onNoMoreList(int from) {

    }



    @Override
    public void onShowPaginationLoading(boolean show) {

    }

    @Override
    public void onLoadMorePendingTender(int from, int page) {

    }




    @Override
    public void onError(String mesg, int from) {
        if (hasView()) {

            getView().onProgressHide();
            getView().onError(mesg);
        }
    }

    @Override
    public void onNoInternetConnection(boolean b , int from) {
        if (hasView()) {

            getView().onProgressHide();
            getView().onNoInternet(b);
        }
    }

    @Override
    public void onPaginationError(int from) {
        if (hasView()) {

            getView().onProgressHide();
            getView().onPaginationError(true, from);
        }
    }

    @Override
    public void downloadGSTList(String url) {
        if(hasView())
        {
            getView().onProgressShow();
            module.onDownloadedGSTList(url,page, limit, this);
        }

    }

    @Override
    public void onDownloadedGSTList(List<SalesPersonModel> list) {
        if(hasView())
        {
            getView().onProgressHide();
            getView().getGSTList(list);
        }

    }
}
