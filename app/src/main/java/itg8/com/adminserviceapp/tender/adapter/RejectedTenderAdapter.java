package itg8.com.adminserviceapp.tender.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.sales.ProgressHolder;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;

/**
 * Created by Android itg 8 on 12/21/2017.
 */

public class RejectedTenderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int NORMAL_VIEW = 0;
    private static final int LOADING_VIEW = 1;

    private Context context;
    private List<PendingTenderModel> list;
    ItemClickedListener listener;

    public RejectedTenderAdapter(Context context, ItemClickedListener listener) {
        this.context = context;
        this.list = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_rejected_tender, parent, false);
            holder = new RejectedViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
            holder = new ProgressHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof RejectedViewHolder) {
            ((RejectedViewHolder) holder).model = list.get(position);
            ((RejectedViewHolder) holder).lblTitle.setText(CommonMethod.checkEmpty(((RejectedViewHolder) holder).model.getTitle()));
            ((RejectedViewHolder) holder).lblDescription.setText(CommonMethod.checkEmpty(((RejectedViewHolder) holder).model.getDescription()));
            ((RejectedViewHolder) holder).lblOpenDateValue.setText(CommonMethod.checkEmpty(((RejectedViewHolder) holder).model.getClosedDate()));
            ((RejectedViewHolder) holder).lblStatusValue.setText(((RejectedViewHolder) holder).model.getEMDStatus() ? "YES" : "NO");
            ((RejectedViewHolder) holder).lblTenderFeeValue.setText(CommonMethod.checkEmpty(((RejectedViewHolder) holder).model.getOpenDate()));
            if (((RejectedViewHolder) holder).model.getEMDStatus()) {
                ((RejectedViewHolder) holder).lblStatusValue.setTextColor(ContextCompat.getColor(context, R.color.colorGreen));

                ((RejectedViewHolder) holder).lblRefundDate.setVisibility(View.VISIBLE);
                ((RejectedViewHolder) holder).lblRefundDateValue.setVisibility(View.VISIBLE);
                ((RejectedViewHolder) holder).lblRefundDateValue.setText(((RejectedViewHolder) holder).model.getENDRefundDate() != null ? ((RejectedViewHolder) holder).model.getENDRefundDate() : "NO AVAILABLE");

            }else
            {
                ((RejectedViewHolder) holder).lblRefundDate.setVisibility(View.GONE);
                ((RejectedViewHolder) holder).lblRefundDateValue.setVisibility(View.GONE);
                ((RejectedViewHolder) holder).lblStatusValue.setTextColor(ContextCompat.getColor(context, R.color.colorRed));

            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? LOADING_VIEW : NORMAL_VIEW;
    }

    public class RejectedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_title)
        TextView lblTitle;
        @BindView(R.id.lbl_description)
        TextView lblDescription;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.lbl_open_date_value)
        TextView lblOpenDateValue;
        @BindView(R.id.lbl_open_date)
        TextView lblOpenDate;
        @BindView(R.id.lbl_tender_fee_value)
        TextView lblTenderFeeValue;
        @BindView(R.id.lbl_tender_fee)
        TextView lblTenderFee;
        @BindView(R.id.lbl_status_value)
        TextView lblStatusValue;
        @BindView(R.id.lbl_status)
        TextView lblStatus;
        @BindView(R.id.lbl_Refund_date_value)
        TextView lblRefundDateValue;
        @BindView(R.id.lbl_Refund_date)
        TextView lblRefundDate;
        PendingTenderModel model;

        public RejectedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition(), list.get(getAdapterPosition()));

                }
            });
        }
    }

    public interface ItemClickedListener {
        void onItemClicked(int position, PendingTenderModel model);
    }

    public void addItems(List<PendingTenderModel> o) {
        list.addAll(o);
        notifyDataSetChanged();

    }

    public void addFooter() {
        list.add(null);
        notifyItemInserted(list.size() - 1);
    }

    public void removeFooter() {
        final int itemRemoved = list.size() - 1;
        list.remove(itemRemoved);
        notifyItemRemoved(itemRemoved);
        notifyItemRangeChanged(itemRemoved, list.size());
    }


}