package itg8.com.adminserviceapp.sales.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itg8.com.adminserviceapp.common.BaseWeakPresenter;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class SalePersonPresenterImp extends BaseWeakPresenter<SalesPersonMVP.SalesPersonView> implements SalesPersonMVP.SalesPersonListener, SalesPersonMVP.SalesPersonPresenter {
    private SalesPersonMVP.SalesPersonModule module;
    private int page = 0;
    private int limit = 10;

    public SalePersonPresenterImp(SalesPersonMVP.SalesPersonView salesPersonView) {
        super(salesPersonView);
        module = new SalesPersonModuleImp();
    }

    @Override
    public void onDestroyed() {
        if (hasView()) {
            module.onDestroyed();
            detactView();
        }

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
    public void onNoInternetConnection(boolean b, int from) {
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
    public void onNoMoreList(int from) {

    }

    @Override
    public void downloadSalesPersonList(String url) {
        if (hasView()) {
            getView().onProgressShow();
            module.onDownloadedSalesPersonList(url, page, limit, this);
        }

    }

    @Override
    public void onDownloadedSalesPersonList(List<SalesPersonModel> list) {
        if (hasView()) {
            getView().onProgressHide();
            getView().getSalesPersonList(list);
        }
    }
}
