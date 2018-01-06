package itg8.com.adminserviceapp.ticket.mvp;

import java.util.List;

import itg8.com.adminserviceapp.common.BaseWeakPresenter;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.ticket.model.TicketModel;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class TicketPresenterImp extends BaseWeakPresenter<TicketMVP.TicketView> implements TicketMVP.TicketPresenter, TicketMVP.TicketListener {


    private final TicketMVP.TicketModule module;
    private int page = 0;
    private int limit = 10;
    private String loadUrl;

    public TicketPresenterImp(TicketMVP.TicketView ticketView) {
        super(ticketView);
        module = new TicketModuleImp();
    }

    @Override
    public void onDestroyed() {
        if (hasView()) {
            module.onDestroyed();
            detactView();
        }

    }


    @Override
    public void onError(String mesg,int status) {
        if (hasView()) {
            getView().onProgressHide(status);
            getView().onError(mesg);
        }
    }

    @Override
    public void onNoInternetConnection(boolean b, int status) {
        if (hasView()) {
            getView().onProgressHide(status);
            getView().onNoInternet(b);
        }
    }


    @Override
    public void downloadTicketList(String url, int from) {
        this.loadUrl = url;
        onLoadMorePendingTender(from, 0);


    }


    @Override
    public void onDownloadedOpenTicketList(List<TicketModel> list, int page, int status) {
        if (hasView()) {
            if (page != 0)
                getView().onShowPaginationLoading(false, status);

            getView().onProgressHide(status);

            if (list.size() > 0)
                getView().getTicketList(list, status);
            else {
                getView().onNoMoreList(status);
                getView().onFinished(status, true);
            }
            getView().isLoading(status, false);
        }
    }

    @Override
    public void onDownloadedCloseTicketList(List<TicketModel> list, int page, int status) {
        if (hasView()) {
            getView().onProgressHide(status);

            if (page != 0)
                getView().onShowPaginationLoading(false, status);


            if (list.size() > 0)
                getView().getTicketList(list, status);
            else {
                getView().onNoMoreList(status);
                getView().onFinished(status, true);
            }
            getView().isLoading(status, false);
        }
    }

    @Override
    public void onDownloadedAcceptTicketList(List<TicketModel> list, int page, int status) {
        if (hasView()) {
            if (page != 0)
                getView().onShowPaginationLoading(false, status);

            getView().onProgressHide(status);

            if (list.size() > 0)
                getView().getTicketList(list, status);
            else {
                getView().onNoMoreList(status);
                getView().onFinished(status, true);
            }
            getView().isLoading(status, false);
        }
    }

    @Override
    public void onNoMoreList(int from) {
        if (hasView()) {
            getView().onShowPaginationLoading(false, from);
            getView().onProgressHide(from);
            getView().onNoMoreList(from);
        }
    }

    @Override
    public void onPaginationError(int from) {
        if (hasView()) {
            getView().onShowPaginationLoading(false, from);
            getView().onPaginationError(true, from);
        }
    }

    @Override
    public void onShowPaginationLoading(boolean show) {

    }

    @Override
    public void onLoadMorePendingTender(int from, int page) {
        getItems(page, from);
    }


    @Override
    public void getItems(int page, int from) {
        if (hasView()) {
//            getView().onPaginationError(false, from);
            if (page != 0)
                getView().onShowPaginationLoading(true, from);
            else
                getView().onProgressShow(from);
            switch (from) {
                case CommonMethod.TICKET_STATUS_OPEN:
                    module.onDownloadedOpenTicketList(loadUrl, page, limit, from, this);
                    break;
                case CommonMethod.TICKET_STATUS_ASSIGN:
                    module.onDownloadedAcceptTicketList(loadUrl, page, limit, from, this);
                    break;
                case CommonMethod.TICKET_STATUS_CLOSE:
                    module.onDownloadedCloseTicketList(loadUrl, page, limit, from, this);
                    break;
            }


        }
    }

    @Override
    public void emptyList(int from) {
        if (hasView()) {
            getView().onProgressHide(from);
            getView().emptyList(from);
        }
    }
}
