package itg8.com.adminserviceapp.common;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Android itg 8 on 12/23/2017.
 */

public abstract class BaseFragment extends Fragment {





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

   public RecyclerView.OnScrollListener  getRecyclerViewScroll(final LinearLayoutManager linearLayoutManager, final int from){

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

               if (!isLoading() && !isFinished())
               {
                   if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount && firstVisibleItemPosition >= 0)
                   {

                       int page=getPage();
                       page++;
                       Logs.d("Page:"+page);

                       getItems(page,from);

                   }
               }

           }
       };
    }



    public abstract void getItems(int page,int from);

   public abstract int getPage();

   public abstract boolean isLoading();
   public abstract boolean isFinished();










}
