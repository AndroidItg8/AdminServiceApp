package itg8.com.adminserviceapp.enquiry.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itg8.com.adminserviceapp.common.BaseWeakPresenter;
import itg8.com.adminserviceapp.common.Logs;
import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class EnquiryPresenterImp extends BaseWeakPresenter<EnquiryMVP.EnquiryView> implements EnquiryMVP.EnquiryPresenter, EnquiryMVP.EnquiryListener {

    private EnquiryMVP.EnquiryModule module;
    private int page=0;
    private int limit=10;
    private String url;
    private boolean isLoading;
    private boolean isFinished=false;

    public EnquiryPresenterImp(EnquiryMVP.EnquiryView enguiryView) {
        super(enguiryView);
        module= new EnquiryModuleImp();


    }
    @Override
    public void onDestroyed() {
        if (hasView()) {
            module.onDestroyed();
        }

    }

    @Override
    public void onNoMoreList(int from) {
        if(hasView()){
            getView().onProgressHide();
            getView().onShowPaginationLoading(false,1);
            getView().onNoMoreList(1);
            isFinished=true;
            isLoading=false;
        }


    }
    @Override
    public void onShowPaginationLoading(boolean show) {
            if(hasView()){
                getView().onShowPaginationLoading(false,1);
                getView().onPaginationError(true,1);
            }
    }

    @Override
    public void onLoadMorePendingTender(int from, int page) {
        getItems(page,limit);
    }

    @Override
    public void onError(String mesg, int from) {
        if (hasView()) {

            getView().onProgressHide();
            getView().onError(mesg);
        }
    }

    @Override
    public void onNoInternetConnection(boolean b, int from) {
        if (hasView()) {

            getView().onProgressHide();
            getView().onNoInternet(b);
        }
    }

    @Override
    public void onPaginationError(int from) {
        if (hasView()) {

            getView().onProgressHide();
            getView().onPaginationError(true, from);
        }
    }

    @Override
    public void downloadEnquiryList(String url) {
        this.url =url;
        onLoadMorePendingTender(0,0);

    }

    @Override
    public RecyclerView.OnScrollListener recyclerViewScrllListener(final LinearLayoutManager linearLayoutManager) {

        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                Logs.d("visibleItemCount:"+visibleItemCount);

                int totalItemCount = linearLayoutManager.getItemCount();
                Logs.d("totalItemCount:"+totalItemCount);

                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                Logs.d("firstVisibleItemPosition:"+firstVisibleItemPosition);

                if (!isLoading && !isFinished)
                {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0)
                    {

                        page++;
                        Logs.d("Page:"+page);

                        getItems(page,limit);
                    }
                }

            }
        };
    }

    private void getItems(int page, int limit) {
        if(hasView()){
            getView().onPaginationError(false,1);
            getView().onShowPaginationLoading(true,1);
            isLoading=true;
            module.onDownloadedEnquiryList(url,page,limit,this);

        }
    }

    @Override
    public void onDownloadedEnquiryList(List<EnquiryModel> list) {
        if(hasView()){
//            getView().onShowPaginationLoading(false,1);
            getView().onProgressHide();
            if(list.size()>0)
                getView().getEnguiryList(list);
            else {
                getView().onNoMoreList(1);
                isFinished=true;
            }
            isLoading=false;
        }

    }

    @Override
    public void emptyList() {
        if(hasView())
        {
            isFinished=true;
            isLoading=false;
            getView().onProgressHide();
            getView().emptyList();
        }

    }
}
