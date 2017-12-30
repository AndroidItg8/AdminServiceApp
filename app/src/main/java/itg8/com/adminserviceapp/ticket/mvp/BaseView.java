package itg8.com.adminserviceapp.ticket.mvp;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface BaseView {
    void onProgressHide();
    void onProgressShow();
    void onNoInternet(boolean b);
     void onError(String mesg);
    void onNoMoreList(int from);
    void onShowPaginationLoading(boolean show, int from);
    void onPaginationError(boolean show, int from);
}
