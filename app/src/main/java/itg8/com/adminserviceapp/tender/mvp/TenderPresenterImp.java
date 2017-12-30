package itg8.com.adminserviceapp.tender.mvp;

import java.util.List;

import itg8.com.adminserviceapp.common.BaseWeakPresenter;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;

/**
 * Created by Android itg 8 on 12/19/2017.
 */

public class TenderPresenterImp extends BaseWeakPresenter<TenderMVP.TenderView> implements TenderMVP.TenderPresenter, TenderMVP.TenderListener {


    private static final String TAG = TenderPresenterImp.class.getSimpleName();
    private final TenderMVP.TenderModule module;
    private int limit = 10;
    private String pendeingLoadUrl;
    private String rejectedLoadUrl;
    private String sumbitedLoadUrl;

    public TenderPresenterImp(TenderMVP.TenderView referent) {
        super(referent);
        module = new TenderModuleImp();
    }


    @Override
    public void onError(String mesg,int from) {
        if (hasView()) {
            getView().onProgressHide(from);
            getView().onError(mesg);
        }
    }

    @Override
    public void onNoInternetConnection(boolean b, int from) {
        if (hasView()) {
            getView().onProgressHide(from);
            getView().onNoInternet(b);
        }
    }


    @Override
    public void onDestroyed() {
        module.onDestroyed();
        if(hasView()) {
            detactView();
        }

    }



    @Override
    public void onNoMoreList(int from) {
        if(hasView()){
            getView().onShowPaginationLoading(false, from);
            getView().onProgressHide(from);
            getView().onNoMoreList(from);
        }
    }

    @Override
    public void onPaginationError(int from) {
        if(hasView()){
            getView().onShowPaginationLoading(false, from);
            getView().onPaginationError(true, from);
        }
    }

    @Override
    public void onShowPaginationLoading(boolean show) {

    }

    @Override
    public void onLoadMorePendingTender(int from, int page) {
        getItems( page,from);
    }


    @Override
    public void getItems(int page, int from) {
        if (hasView()) {
//            getView().onPaginationError(false, from);
            getView().onProgressShow(from);
            if(page != 0)
                getView().onShowPaginationLoading(true, from);

            switch (from) {
                case CommonMethod.FROM_PENDING:
                    module.onDownloadedTenderList(pendeingLoadUrl, page, limit, from,this);
                    break;
                case CommonMethod.FROM_REJECT:
                    module.onDownloadedRejectedTenderList(rejectedLoadUrl, page, limit,from ,this);
                    break;
                case CommonMethod.FROM_ACCEPT:
                    module.onDownloadedSubmitedTenderList(sumbitedLoadUrl, page, limit, from,this);
                    break;
            }

        }
    }



    @Override
    public void downloadTenderList(String url, int from) {
        this.pendeingLoadUrl = url;
        onLoadMorePendingTender(from,0);
    }

    @Override
    public void downloadSubmitedTenderList(String url, int from) {
        this.sumbitedLoadUrl = url;
        onLoadMorePendingTender(from, 0);
    }

    @Override
    public void downloadRejectedTender(String url, int from) {
        this.rejectedLoadUrl = url;
        onLoadMorePendingTender(from, 0);
    }


    @Override
    public void onDownloadedTenderList(List<PendingTenderModel> list, int from, int page) {
        if(hasView()){
            getView().onProgressHide(from);
            if(page !=0)
                getView().onShowPaginationLoading(false, from);
            if(list.size()>0)
                getView().onGetPendingTenderList(list);
            else {
                getView().onNoMoreList(from);
              getView().onFinished(from,true);
            }
            getView().isLoading(from,false);
        }

    }
    @Override
    public void emptyList(int from) {
        if(hasView())
        {
            getView().onProgressHide(from);
            getView().emptyList(from);
        }
    }

    @Override
    public void onDownloadedSubmitedTenderList(List<PendingTenderModel> list, int from, int page) {
        if(hasView()){
            getView().onProgressHide(from);

            if(page !=0)
                getView().onShowPaginationLoading(false, from);


            if(list.size()>0)
                getView().onGetSubmitedTenderList(list);
            else {
                getView().onNoMoreList(from);
                getView().onFinished(from,true);
            }
            getView().isLoading(from,false);
        }


    }

    @Override
    public void onDownloadedRejectedTenderList(List<PendingTenderModel> list, int from, int page) {
        if(hasView()){
            if(page !=0)
                getView().onShowPaginationLoading(false, from);

            getView().onProgressHide(from);
            if(list.size()>0)
                getView().onGetRejectedTenderList(list);
            else {
                getView().onNoMoreList(from);
                getView().onFinished(from,true);
            }
            getView().isLoading(from,false);
        }
    }


}
