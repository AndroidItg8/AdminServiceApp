package itg8.com.adminserviceapp.tender.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import itg8.com.adminserviceapp.R;
import itg8.com.adminserviceapp.common.CommonMethod;
import itg8.com.adminserviceapp.tender.model.DocName;
import itg8.com.adminserviceapp.tender.model.DocType;
import itg8.com.adminserviceapp.tender.model.DocumentModel;

/**
 * Created by Android itg 8 on 12/18/2017.
 */

public class TenderDocumentAdapter extends RecyclerView.Adapter<TenderDocumentAdapter.DocmentViewHolder> {


    DocumentItemClickedListener listener;
    private Context context;
    private List<DocumentModel> list;
    private String from;


    public TenderDocumentAdapter(Context context, List<DocumentModel> list, String from, DocumentItemClickedListener listener) {
        //, DocumentItemClickedListener listener
        this.context = context;
        this.list = list;
        this.from = from;
        this.listener = listener;
    }

    @Override
    public DocmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_doc_tender, parent, false);
        return new DocmentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DocmentViewHolder holder, int position) {
        holder.docModel = list.get(position);
        holder.model = holder.docModel.getDocName();
        holder.docTypeModel = holder.docModel.getDocType();
        holder.lblTitle.setText(CommonMethod.checkEmpty(holder.model.get(0).getTenderActualDocument())+""+ CommonMethod.checkEmpty(holder.docTypeModel.get(0).getDocumentTypeName()));

        if (holder.docModel.getSuperAdminStatus().equalsIgnoreCase("1")) {
            holder.chkDoc.setChecked(true);
        } else {
            holder.chkDoc.setChecked(false);
        }

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface DocumentItemClickedListener {
        void onDocumentClickedItem(int position, DocumentModel model);

        void onDocumnetCheckItem(int position, DocumentModel model, boolean b, List<DocumentModel> list);
    }

    public class DocmentViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.lbl_title)
        TextView lblTitle;
        @BindView(R.id.chk_doc)
        CheckBox chkDoc;
        @BindView(R.id.img_email)
        ImageView imgEmail;
        List<DocName> model;
        List<DocType> docTypeModel;
        DocumentModel docModel;


        public DocmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imgEmail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onDocumentClickedItem(getAdapterPosition(), list.get(getAdapterPosition()));
                }
            });
            chkDoc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    listener.onDocumnetCheckItem(getAdapterPosition(), list.get(getAdapterPosition()), b, list);
                }
            });
        }
    }
}
