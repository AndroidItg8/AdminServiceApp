package itg8.com.adminserviceapp.feedback.mvp;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import itg8.com.adminserviceapp.feedback.model.FeedbackModel;
import itg8.com.adminserviceapp.ticket.mvp.BaseListener;
import itg8.com.adminserviceapp.ticket.mvp.BaseModule;
import itg8.com.adminserviceapp.ticket.mvp.BasePresenter;
import itg8.com.adminserviceapp.ticket.mvp.BaseView;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public interface FeedbackMVP {
    public interface FeedbackView extends BaseView {
        void getFeedbackList(List<FeedbackModel> list);

        void emptyList();
    }

    public interface FeedbackPresenter extends BasePresenter {
        void downloadFeedbackList(String url);

        RecyclerView.OnScrollListener recyclerViewScrllListener(LinearLayoutManager linearLayoutManager);
    }

    public  interface FeedbackModule extends BaseModule {
        void onDownloadedFeedbackList(String url,int page, int limit, FeedbackMVP.FeedbackListener listner);
    }

    public interface FeedbackListener extends BaseListener {
        void onDownloadedFeedbackList(List<FeedbackModel> list, int page);

        void emptyList();

    }
}
