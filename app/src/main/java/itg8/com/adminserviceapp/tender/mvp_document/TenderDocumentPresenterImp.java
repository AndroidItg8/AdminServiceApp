package itg8.com.adminserviceapp.tender.mvp_document;

import java.util.List;

import itg8.com.adminserviceapp.common.BaseWeakPresenter;
import itg8.com.adminserviceapp.tender.model.CustomTenderDocumentModel;

/**
 * Created by Android itg 8 on 12/25/2017.
 */

public class TenderDocumentPresenterImp extends BaseWeakPresenter<TenderDocumentMVP.TenderDocumentView> implements TenderDocumentMVP.TenderDocumentPresenter, TenderDocumentMVP.TenderDocumentListener {

    private final TenderDocumentMVP.TenderDocumentModule module;

    public TenderDocumentPresenterImp(TenderDocumentMVP.TenderDocumentView tenderDocumentView) {
        super(tenderDocumentView);
        module = new TenderDocumentModuleImp();
    }

    @Override
    public void onDestroyed() {
        module.onDestroyed();
        if (hasView()) {
            detactView();
        }
    }

    @Override
    public void sendTenderDocumentModelList(String url, List<CustomTenderDocumentModel> list) {
        if(hasView())
        {
            getView().onProgressShow();
            module.onDownloadedTenderList(url,list, this);
        }

    }

    @Override
    public void onError(String mesg) {
        if(hasView())
        {
            getView().onProgressHide();
            getView().onError(mesg);
        }
    }

    @Override
    public void onNoInternetConnection(boolean b) {
        if(hasView())
        {
            getView().onProgressHide();
            getView().onNoInternet(b);
        }
    }

    @Override
    public void onSuccess(String message) {
        if(hasView())
        {
            getView().onProgressHide();
            getView().onSuccess(message);
        }
    }
}
