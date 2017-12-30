package itg8.com.adminserviceapp.gst.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.sales.ProgressHolder;

/**
 * Created by Android itg 8 on 12/14/2017.
 */

public class PurchaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private static final int LOADING_VIEW = 1;
    private static final int NORMAL_VIEW = 2;

    public PurchaseAdapter(Context context) {

        this.context = context;
        //        list = new ArrayList<>();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_sales, parent, false);
            holder = new PurchaseAdapter.PurchaseViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
            holder = new ProgressHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class PurchaseViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_date)
        TextView lblDate;
        @BindView(R.id.lbl_otp)
        TextView lblOtp;
        @BindView(R.id.lbl_year)
        TextView lblYear;
        @BindView(R.id.lbl_productName)
        TextView lblProductName;
        @BindView(R.id.lbl_invoiceNumber)
        TextView lblInvoiceNumber;
        @BindView(R.id.lbl_problem)
        TextView lblProblem;
        public PurchaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
}
