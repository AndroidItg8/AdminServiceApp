package itg8.com.adminserviceapp.gst.mvp;

import java.util.List;

import itg8.com.adminserviceapp.sales.model.SalesPersonModel;
import itg8.com.adminserviceapp.ticket.mvp.BaseListener;
import itg8.com.adminserviceapp.ticket.mvp.BaseModule;
import itg8.com.adminserviceapp.ticket.mvp.BasePresenter;
import itg8.com.adminserviceapp.ticket.mvp.BaseView;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface GSTMVP {
    public interface GSTView extends BaseView {
        void getGSTList(List<SalesPersonModel> list);
    }

    public interface GSTPresenter extends BasePresenter {
        void downloadGSTList(String url);
    }

    public  interface GSTModule extends BaseModule {
        void onDownloadedGSTList(String url,int page, int limit, GSTMVP.GSTListener listner);
    }

    public interface GSTListener extends BaseListener {
        void onDownloadedGSTList(List<SalesPersonModel> list);
    }
}
