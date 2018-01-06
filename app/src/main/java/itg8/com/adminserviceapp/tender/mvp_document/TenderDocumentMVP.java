package itg8.com.adminserviceapp.tender.mvp_document;


import java.util.List;

import itg8.com.adminserviceapp.tender.model.CustomTenderDocumentModel;
import itg8.com.adminserviceapp.tender.model.DocumentModel;

import itg8.com.adminserviceapp.ticket.mvp.BaseModule;
/**
 * Created by Android itg 8 on 12/25/2017.
 */

public interface TenderDocumentMVP {
    public interface TenderDocumentView  {
        void onProgressHide();
        void onProgressShow();
        void onNoInternet(boolean b);
        void onError(String mesg);
        void onSuccess(String message);

    }
    public interface TenderDocumentPresenter  {
        void onDestroyed();
        void sendTenderDocumentModelList(String url, List<CustomTenderDocumentModel> list);


    }

    public  interface TenderDocumentModule extends BaseModule {
        void onDownloadedTenderList(String url, List<CustomTenderDocumentModel> list,TenderDocumentMVP.TenderDocumentListener listner);

    }

    public interface TenderDocumentListener  {
        void onError(String mesg);
        void onNoInternetConnection(boolean b);
         void onSuccess(String message);

    }
}
