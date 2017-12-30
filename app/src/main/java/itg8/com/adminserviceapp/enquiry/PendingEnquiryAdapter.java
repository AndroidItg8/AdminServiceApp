package itg8.com.adminserviceapp.enquiry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import itg8.com.adminserviceapp.enquiry.model.EnquiryModel;
import itg8.com.adminserviceapp.sales.ProgressHolder;

/**
 * Created by Android itg 8 on 12/13/2017.
 */

public class PendingEnquiryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int LOADING_VIEW = 1;
    private static final int NORMAL_VIEW = 2;
    ItemClickedListner listener;
    private Context applicationContext;
    private List<EnquiryModel> list;

    public PendingEnquiryAdapter(Context applicationContext,  ItemClickedListner listener) {
        this.applicationContext = applicationContext;
        this.listener = listener;
        this.list = new ArrayList<>();

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder;
        if (viewType == NORMAL_VIEW) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_enquiry, parent, false);
            holder = new EnquiryViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_progress, parent, false);
            holder = new ProgressHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holders, int position) {
        if (holders instanceof EnquiryViewHolder) {
            EnquiryViewHolder holder = (EnquiryViewHolder) holders;

            holder.lblProductName.setText(CommonMethod.checkEmpty(list.get(position).getProductName()));
            holder.lblPersonName.setText(CommonMethod.checkEmpty(list.get(position).getUserName()));
            holder.lblDescription.setText(CommonMethod.checkEmpty(list.get(position).getQuery()));
            holder.lblNumber.setText(CommonMethod.checkEmpty(list.get(position).getContact()));
            if (TextUtils.isEmpty(list.get(position).getContact())) {
                holder.lblNumber.setVisibility(View.GONE);
                holder.lblNumber.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            } else {
                holder.lblNumber.setVisibility(View.VISIBLE);
                holder.lblNumber.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_phone, 0, 0, 0);
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

    public interface ItemClickedListner {
        void onItemClicked(int position, EnquiryModel model);
    }

    public void addItems(List<EnquiryModel> o) {
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

    public class EnquiryViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_productName)
        TextView lblProductName;
        @BindView(R.id.lbl_personName)
        TextView lblPersonName;
        @BindView(R.id.lbl_email)
        TextView lblEmail;
        @BindView(R.id.lbl_description)
        TextView lblDescription;
        @BindView(R.id.lbl_number)
        TextView lblNumber;

        public EnquiryViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            lblNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClicked(getAdapterPosition(), list.get(getAdapterPosition()));
                }
            });

        }
    }
}
