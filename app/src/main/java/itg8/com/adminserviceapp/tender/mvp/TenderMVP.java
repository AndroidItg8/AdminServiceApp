package itg8.com.adminserviceapp.tender.mvp;

import java.util.List;

import itg8.com.adminserviceapp.tender.model.PendingTenderModel;
import itg8.com.adminserviceapp.ticket.mvp.BaseListener;
import itg8.com.adminserviceapp.ticket.mvp.BaseModule;
import itg8.com.adminserviceapp.ticket.mvp.BasePresenter;
import itg8.com.adminserviceapp.ticket.mvp.BaseView;

/**
 * Created by Android itg 8 on 12/19/2017.
 */

public class TenderMVP {

    public interface TenderView extends BaseView {
        void onGetPendingTenderList(List<PendingTenderModel> list);
        void onGetSubmitedTenderList(List<PendingTenderModel> list);
        void onGetRejectedTenderList(List<PendingTenderModel> list);
        void onProgressHide(int from);
        void onProgressShow(int from);

        void onFinished(int from, boolean b);

        void isLoading(int from, boolean b);

        void emptyList(int from);
    }
    public interface TenderPresenter extends BasePresenter {
        void downloadTenderList(String url,int fromPending);
        void downloadSubmitedTenderList(String url,int fromAccept);
        void downloadRejectedTender(String url, int fromReject);
         void getItems(int page,  int from);

    }

    public  interface TenderModule extends BaseModule {
        void onDownloadedTenderList(String url, int page, int limit, int from, TenderListener listner);

        void onDownloadedSubmitedTenderList(String url, int page, int limit, int from, TenderListener listener);
        void onDownloadedRejectedTenderList(String url, int page, int limit, int from, TenderListener listener);
    }

    public interface TenderListener extends BaseListener {
        void onDownloadedTenderList(List<PendingTenderModel> list, int from, int page);
        void onDownloadedSubmitedTenderList(List<PendingTenderModel> list, int from, int page);
        void onDownloadedRejectedTenderList(List<PendingTenderModel> list, int from, int page);

        void emptyList(int from);
    }
}
