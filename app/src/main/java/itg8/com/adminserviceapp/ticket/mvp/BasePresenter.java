package itg8.com.adminserviceapp.ticket.mvp;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface BasePresenter {
     void onDestroyed();

    void onNoMoreList(int from);
    void onShowPaginationLoading(boolean show);
    void onLoadMorePendingTender(int from, int page);
//    void onLoadMoreSubmitTender(int from);
//    void onLoadMoreSubmitTender(int from);


}
