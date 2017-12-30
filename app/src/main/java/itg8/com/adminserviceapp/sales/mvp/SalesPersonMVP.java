package itg8.com.adminserviceapp.sales.mvp;


import android.view.View;

import java.util.List;

import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import itg8.com.adminserviceapp.ticket.mvp.BaseListener;
import itg8.com.adminserviceapp.ticket.mvp.BaseModule;
import itg8.com.adminserviceapp.ticket.mvp.BasePresenter;
import itg8.com.adminserviceapp.ticket.mvp.BaseView;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface SalesPersonMVP {
     public interface SalesPersonView extends BaseView {
         void getSalesPersonList(List<SalesPersonModel> list);
     }

    public interface SalesPersonPresenter extends BasePresenter {
        void downloadSalesPersonList(String url);

    }

    public  interface SalesPersonModule extends BaseModule {
        void onDownloadedSalesPersonList(String url,int page, int limit, SalesPersonMVP.SalesPersonListener listner);
    }

    public interface SalesPersonListener extends BaseListener {
        void onDownloadedSalesPersonList(List<SalesPersonModel> list);
    }
}
