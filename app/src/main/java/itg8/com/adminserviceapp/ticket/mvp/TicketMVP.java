package itg8.com.adminserviceapp.ticket.mvp;

import java.util.List;

import itg8.com.adminserviceapp.ticket.model.TicketModel;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface TicketMVP {
    public interface TicketView extends BaseView{
         void getTicketList(List<TicketModel> list, int status);
        void onProgressHide(int status);
        void onProgressShow(int status);
        void onFinished(int from, boolean b);
        void isLoading(int from, boolean b);
        void emptyList(int from);
    }
     public interface TicketPresenter extends BasePresenter{
       void downloadTicketList(String url,int status);
         void getItems(int page,  int from);

     }

     public  interface TicketModule extends BaseModule{
         void onDownloadedOpenTicketList(String url,int page, int limit, int status,TicketListener listner);
         void onDownloadedCloseTicketList(String url,int page, int limit, int status,TicketListener listner);
         void onDownloadedAcceptTicketList(String url,int page, int limit, int status,TicketListener listner);


     }

     public interface TicketListener extends BaseListener{
         void onDownloadedOpenTicketList(List<TicketModel> list,int page,int status);
         void onDownloadedCloseTicketList(List<TicketModel> list,int page,int status);
         void onDownloadedAcceptTicketList(List<TicketModel> list,int page,int status);
         void emptyList(int from);

     }
}
