package itg8.com.adminserviceapp.tender.adapter;

import android.content.Context;
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

public class SubmittedTenderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int LOADING_VIEW = 0;
    private static final int NORMAL_VIEW = 1;
    SubmittedTenderAdapter.ItemClickedListener listener;
    private Context context;
    private List<PendingTenderModel> list;

    public SubmittedTenderAdapter(Context context, SubmittedTenderAdapter.ItemClickedListener listener) {
        this.context = context;
        this.list = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_tender, parent, false);
            holder = new SubmittedTenderAdapter.SubmitedViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
            holder = new ProgressHolder(view);
        }
        return holder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SubmittedTenderAdapter.SubmitedViewHolder) {
            ((SubmittedTenderAdapter.SubmitedViewHolder) holder).model = list.get(position);
            ((SubmittedTenderAdapter.SubmitedViewHolder) holder).lblTitle.setText(CommonMethod.checkEmpty(((SubmittedTenderAdapter.SubmitedViewHolder) holder).model.getTitle()));
            ((SubmittedTenderAdapter.SubmitedViewHolder) holder).lblDescription.setText(CommonMethod.checkEmpty(((SubmittedTenderAdapter.SubmitedViewHolder) holder).model.getDescription()));
            ((SubmittedTenderAdapter.SubmitedViewHolder) holder).lblEndDateValue.setText(CommonMethod.checkEmpty(((SubmittedTenderAdapter.SubmitedViewHolder) holder).model.getTenderFeeIn()));
            ((SubmitedViewHolder) holder).lblPrice.setText(CommonMethod.checkEmpty(((SubmittedTenderAdapter.SubmitedViewHolder) holder).model.getEMDAMT()));
            ((SubmittedTenderAdapter.SubmitedViewHolder) holder).lblOpenDateValue.setText(CommonMethod.checkEmpty(((SubmittedTenderAdapter.SubmitedViewHolder) holder).model.getOpenDate()));
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

    public interface ItemClickedListener {
        void onItemClicked(int position, PendingTenderModel model);
    }

    public class SubmitedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_title)
        TextView lblTitle;
        @BindView(R.id.lbl_description)
        TextView lblDescription;
        @BindView(R.id.view)
        View view;
        @BindView(R.id.lbl_open_date_value)
        TextView lblOpenDateValue;
        @BindView(R.id.lbl_price)
        TextView lblPrice;
        @BindView(R.id.lbl_end_date_value)
        TextView lblEndDateValue;
        PendingTenderModel model;

        public SubmitedViewHolder(View itemView) {
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
}