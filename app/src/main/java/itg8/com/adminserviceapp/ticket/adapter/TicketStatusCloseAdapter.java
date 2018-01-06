package itg8.com.adminserviceapp.ticket.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.common.Logs;
import itg8.com.adminserviceapp.sales.ProgressHolder;
import itg8.com.adminserviceapp.ticket.model.Cust;
import itg8.com.adminserviceapp.ticket.model.Problem;
import itg8.com.adminserviceapp.ticket.model.Product;
import itg8.com.adminserviceapp.ticket.model.TicketModel;

/**
 * Created by Android itg 8 on 12/13/2017.
 */

public class TicketStatusCloseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context activity;
    private static final int LOADING_VIEW = 1;
    private static final int NORMAL_VIEW = 2;
    private List<TicketModel> list;
    private RejectedViewHolder rejectedViewHolder;
    OnItemClickedListener listener;


    public TicketStatusCloseAdapter(Context activity, OnItemClickedListener listener) {
        this.activity = activity;
        this.listener = listener;
        list = new ArrayList<>();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket_rejected, parent, false);
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
            rejectedViewHolder = (RejectedViewHolder) holder;
            rejectedViewHolder.lblEngName.setVisibility(View.VISIBLE);
            if (list.get(position).getCust().size() > 0)
                rejectedViewHolder.custModel = list.get(position).getCust().get(0);
            if (list.get(position).getProduct().size() > 0)
                rejectedViewHolder.productModel = list.get(position).getProduct().get(0);
            if (list.get(position).getProblem().size() > 0)
                if (list.get(position).getProblem() != null)
                    rejectedViewHolder.lblProblem.setText(CommonMethod.checkEmpty(list.get(position).getProblem().get(0).getProblem()));
            else
                    rejectedViewHolder.lblProblem.setText("NOT AVAILABLE");

            rejectedViewHolder.lblInvoiceNumberValue.setText(String.valueOf(list.get(position).getInvoiceFkid()));
            rejectedViewHolder.lblEngNameValue.setText(CommonMethod.checkEmpty(list.get(position).getAssignedpersonname()));
            Calendar date = (CommonMethod.ConvertStringToDateWithoutMillies(list.get(position).getAssignDate()));
            String year = String.valueOf(date.get(Calendar.YEAR));
            String month = String.valueOf(date.get(Calendar.MONTH) + 1);
            String day = String.valueOf(date.get(Calendar.DATE));
            rejectedViewHolder.lblOtp.setText(day);
            rejectedViewHolder.lblYear.setText(month + "-" + year);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<TicketModel> o) {
        list.addAll(o);
        notifyDataSetChanged();

    }

    public void addFooter() {
        list.add(null);
        notifyItemInserted(list.size() - 1);
    }


    @Override
    public int getItemViewType(int position) {
        return list.get(position) == null ? LOADING_VIEW : NORMAL_VIEW;
    }

    public void removeFooter() {
        Logs.d("RemoveFooterB4:" + list.size());

        final int itemRemoved = list.size() - 1;
        list.remove(itemRemoved);
        notifyItemRemoved(itemRemoved);
        notifyItemRangeChanged(itemRemoved, list.size());
        Logs.d("RemoveFooterAfter:" + list.size());
    }

    public class RejectedViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.lbl_date)
        TextView lblDate;
        @BindView(R.id.lbl_otp)
        TextView lblOtp;
        @BindView(R.id.lbl_year)
        TextView lblYear;
        @BindView(R.id.lbl_invoiceNumber)
        TextView lblInvoiceNumber;
        @BindView(R.id.lbl_invoiceNumber_value)
        TextView lblInvoiceNumberValue;
        @BindView(R.id.lbl_problem)
        TextView lblProblem;
        @BindView(R.id.lbl_rejected)
        TextView lblRejected;
        @BindView(R.id.lbl_reason_rejected)
        TextView lblReasonRejected;
        @BindView(R.id.lbl_engName_value)
        TextView lblEngNameValue;
        @BindView(R.id.lbl_engName)
        TextView lblEngName;

        TicketModel model;
        Cust custModel;
        Problem problemModel;
        Product productModel;

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

    public interface OnItemClickedListener {
        void onItemClicked(int position, TicketModel ticketModel);
    }
}