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
import itg8.com.adminserviceapp.common.Logs;
import itg8.com.adminserviceapp.sales.ProgressHolder;
import itg8.com.adminserviceapp.tender.model.PendingTenderModel;

/**
 * Created by Android itg 8 on 12/18/2017.
 */

public class PendingTenderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LOADING_VIEW = 1;
    private static final int NORMAL_VIEW = 0;
    ItemClickedListener listener;

    private Context context;
    private List<PendingTenderModel> list;

    public PendingTenderAdapter(Context context, ItemClickedListener listener, int fromPending) {
        this.context = context;
        this.list = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_tender, parent, false);
            holder = new PendingViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
            holder = new ProgressHolder(view);
        }
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PendingViewHolder) {
            ((PendingViewHolder) holder).model = list.get(position);
            ((PendingViewHolder) holder).lblTitle.setText(CommonMethod.checkEmpty(((PendingViewHolder) holder).model.getTitle()));
            ((PendingViewHolder) holder).lblDescription.setText(CommonMethod.checkEmpty(((PendingViewHolder) holder).model.getDescription()));
            ((PendingViewHolder) holder).lblEndDateValue.setText(CommonMethod.checkEmpty(((PendingViewHolder) holder).model.getTenderFeeIn()));
            ((PendingViewHolder) holder).lblOpenDateValue.setText(CommonMethod.checkEmpty(((PendingViewHolder) holder).model.getClosedDate()));
            ((PendingViewHolder) holder).lblPrice.setText(CommonMethod.checkEmpty(((PendingViewHolder) holder).model.getEMDAMT()));
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
        Logs.d("RemoveFooterB4:" + list.size());

        final int itemRemoved = list.size() - 1;
        list.remove(itemRemoved);
        notifyItemRemoved(itemRemoved);
        notifyItemRangeChanged(itemRemoved, list.size());
        Logs.d("RemoveFooterAfter:" + list.size());
    }

    public interface ItemClickedListener {
        void onItemClicked(int position, PendingTenderModel model);
    }

    public class PendingViewHolder extends RecyclerView.ViewHolder {
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
        @BindView(R.id.lbl_price)
        TextView lblPrice;
        @BindView(R.id.lbl_price_symbol)
        TextView lblPriceSymbol;
        @BindView(R.id.lbl_end_date_value)
        TextView lblEndDateValue;
        @BindView(R.id.lbl_end_date)
        TextView lblEndDate;

        PendingTenderModel model;

        public PendingViewHolder(View itemView) {
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
