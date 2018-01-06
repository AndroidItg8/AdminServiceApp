package itg8.com.adminserviceapp.enquiry.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;
import itg8.com.adminserviceapp.ticket.mvp.BaseListener;
import itg8.com.adminserviceapp.ticket.mvp.BaseModule;
import itg8.com.adminserviceapp.ticket.mvp.BasePresenter;
import itg8.com.adminserviceapp.ticket.mvp.BaseView;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface EnquiryMVP {

    public interface EnquiryView extends BaseView {
        void getEnguiryList(List<EnquiryModel> list);

        void emptyList();
    }

    public interface EnquiryPresenter extends BasePresenter {
        void downloadEnquiryList(String url);

        RecyclerView.OnScrollListener recyclerViewScrllListener(LinearLayoutManager linearLayoutManager);
    }

    public  interface EnquiryModule extends BaseModule {
        void onDownloadedEnquiryList(String url, int page, int limit, EnquiryListener listner);
    }

    public interface EnquiryListener extends BaseListener {
        void onDownloadedEnquiryList(List<EnquiryModel> list, int page);

        void emptyList();
    }
}
