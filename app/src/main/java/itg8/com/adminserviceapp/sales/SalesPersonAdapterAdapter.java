package itg8.com.adminserviceapp.sales;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.sales.model.SalesPersonModel;

/**
 * Created by Android itg 8 on 12/13/2017.
 */

public class SalesPersonAdapterAdapter extends RecyclerView.Adapter<SalesPersonAdapterAdapter.SalesViewHolder> {

    private Context context;
    private List<SalesPersonModel> list;
    private static final int LOADING_VIEW = 1;
    private static final int NORMAL_VIEW = 2;
    onItemClickedListener listener;

    public SalesPersonAdapterAdapter(Context context, List<SalesPersonModel> list, onItemClickedListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
        //        list = new ArrayList<>();


    }

    @Override
    public SalesPersonAdapterAdapter.SalesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


//        RecyclerView.ViewHolder holder;
//        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_sales_person, parent, false);
          return new SalesViewHolder(view);
//        } else {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
//            holder = new ProgressHolder(view);
//        }
//        return holder;
    }

    @Override
    public void onBindViewHolder(SalesViewHolder holder, int position) {
         holder.lblPersonName.setText(CommonMethod.checkProfile(list.get(position).getCustomerName()));
         holder.lblMobile.setText(CommonMethod.checkEmpty(list.get(position).getMobileno()));


          if(TextUtils.isEmpty(list.get(position).getMobileno()))
          {
              holder.lblMobile.setClickable(false);

          }else {
              holder.lblMobile.setClickable(true);
          }


    }




    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SalesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_personName)
        TextView lblPersonName;

        @BindView(R.id.lbl_mobile)
        TextView lblMobile;
        public SalesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            lblMobile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition(), list.get(getAdapterPosition()));

                }
            });
             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View view) {
                     listener.onItemClickedListener(getAdapterPosition(),list.get(getAdapterPosition()));
                 }
             });
        }
    }


    //    public void addItems(List<ComplaintModel> o) {
//        models.addAll(o);
//        notifyDataSetChanged();
//
//    }
//
//    public void addFooter() {
//        models.add(null);
//        notifyItemInserted(models.size() - 1);
//    }
//
//    public void removeFooter() {
//        final int itemRemoved = models.size() - 1;
//        models.remove(itemRemoved);
//        notifyItemRemoved(itemRemoved);
//        notifyItemRangeChanged(itemRemoved, models.size());
//    }
    public interface onItemClickedListener {
        void onItemClicked(int position, SalesPersonModel model);
        void onItemClickedListener(int position, SalesPersonModel model);

    }

}
