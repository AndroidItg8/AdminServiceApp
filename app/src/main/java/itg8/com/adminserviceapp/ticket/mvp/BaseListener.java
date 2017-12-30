package itg8.com.adminserviceapp.ticket.mvp;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface BaseListener {
    void onError(String mesg, int from);
    void onNoInternetConnection(boolean b, int from);
     void onPaginationError(int from);
    void onNoMoreList(int from);

}
