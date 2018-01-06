package itg8.com.adminserviceapp.feedback;

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
import itg8.com.adminserviceapp.feedback.model.FeedbackModel;
import itg8.com.adminserviceapp.sales.ProgressHolder;

import static itg8.com.adminserviceapp.common.CommonMethod.checkEmpty;
import static itg8.com.adminserviceapp.common.CommonMethod.checkNull;

/**
 * Created by Android itg 8 on 12/13/2017.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String NOT_AVAILABLE = "NOT_AVAILABLE";
    private final List<FeedbackModel> list;



    private Context context;
    private static final int LOADING_VIEW = 1;
    private static final int NORMAL_VIEW = 2;
    ItemClickedListener listener;

    public FeedbackAdapter(Context context, ItemClickedListener listener) {
        this.context = context;
        this.listener = listener;
        list = new ArrayList<>();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_feedback, parent, false);
            holder = new FeedbackViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
            holder = new ProgressHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, int position) {
        if (holders instanceof FeedbackViewHolder) {
            FeedbackViewHolder holder = (FeedbackViewHolder) holders;
            {
                String problem = checkNull(list.get(position).getTicketDetail().getProblem()) ? checkEmpty(list.get(position).getTicketDetail().getProblem().get(0).getProblem()) : NOT_AVAILABLE;
                String product = checkNull(list.get(position).getTicketDetail().getProduct()) ? checkEmpty(list.get(position).getTicketDetail().getProduct().get(0).getItemName()) : NOT_AVAILABLE;
                String status = (checkEmpty(list.get(position).getTicketDetail().getAssignedpersonname()));
                String invoiceNumber = checkEmpty(String.valueOf(list.get(position).getTicketDetail().getInvoiceFkid()));

                Calendar date = (CommonMethod.ConvertStringToDateWithoutMillies(list.get(position).getTicketDetail().getAssignDate()));
                String year = String.valueOf(date.get(Calendar.YEAR));
                String month = String.valueOf(date.get(Calendar.MONTH) + 1);
                String day = String.valueOf(date.get(Calendar.DATE));
                holder.lblOtp.setText(day);
                holder.lblProblem.setText(problem);
                holder.lblProductName.setText(product);
                holder.lblEnggNameValue.setText(status);
                holder.lblInvoiceNumberValue.setText(invoiceNumber);
                holder.lblYear.setText(month + "-" + year);

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

    public class FeedbackViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_date)
        TextView lblDate;
        @BindView(R.id.lbl_otp)
        TextView lblOtp;
        @BindView(R.id.lbl_year)
        TextView lblYear;
        @BindView(R.id.lbl_productName)
        TextView lblProductName;

        @BindView(R.id.lbl_problem)
        TextView lblProblem;
        @BindView(R.id.lbl_invoiceNumber)
        TextView lblInvoiceNumber;
        @BindView(R.id.lbl_invoiceNumber_value)
        TextView lblInvoiceNumberValue;
        @BindView(R.id.lbl_engg_name_value)
        TextView lblEnggNameValue;
        @BindView(R.id.lbl_engg_name)
        TextView lblEnggName;


        public FeedbackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition(),list.get(getAdapterPosition()));
                }
            });
        }
    }

    public void addItems(List<FeedbackModel> o) {
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

    public interface ItemClickedListener{
        void onItemClicked(int position,FeedbackModel model );
    }
}
