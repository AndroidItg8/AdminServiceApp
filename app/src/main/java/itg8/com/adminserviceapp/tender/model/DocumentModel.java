package itg8.com.adminserviceapp.tender.model;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocumentModel implements Parcelable
{

    @SerializedName("Cover_fkid")
    @Expose
    private Integer coverFkid;
    @SerializedName("Cover")
    @Expose
    private List<Cover> cover = null;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("DocuPath")
    @Expose
    private String docuPath;
    @SerializedName("DocuType_fkid")
    @Expose
    private Integer docuTypeFkid;
    @SerializedName("DocType")
    @Expose
    private List<DocType> docType = null;
    @SerializedName("ActualDoc_fkid")
    @Expose
    private Integer actualDocFkid;
    @SerializedName("DocName")
    @Expose
    private List<DocName> docName = null;
    @SerializedName("SuperAdminStatus")
    @Expose
    private String superAdminStatus;
    @SerializedName("pkid")
    @Expose
    private Integer pkid;
    @SerializedName("Tender_fkid")
    @Expose
    private String tenderFkid;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;

    public DocumentModel() {
    }

    public Integer getCoverFkid() {
        return coverFkid;
    }

    public void setCoverFkid(Integer coverFkid) {
        this.coverFkid = coverFkid;
    }

    public List<Cover> getCover() {
        return cover;
    }

    public void setCover(List<Cover> cover) {
        this.cover = cover;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDocuPath() {
        return docuPath;
    }

    public void setDocuPath(String docuPath) {
        this.docuPath = docuPath;
    }

    public Integer getDocuTypeFkid() {
        return docuTypeFkid;
    }

    public void setDocuTypeFkid(Integer docuTypeFkid) {
        this.docuTypeFkid = docuTypeFkid;
    }

    public List<DocType> getDocType() {
        return docType;
    }

    public void setDocType(List<DocType> docType) {
        this.docType = docType;
    }

    public Integer getActualDocFkid() {
        return actualDocFkid;
    }

    public void setActualDocFkid(Integer actualDocFkid) {
        this.actualDocFkid = actualDocFkid;
    }

    public List<DocName> getDocName() {
        return docName;
    }

    public void setDocName(List<DocName> docName) {
        this.docName = docName;
    }

    public String getSuperAdminStatus() {
        return superAdminStatus;
    }

    public void setSuperAdminStatus(String superAdminStatus) {
        this.superAdminStatus = superAdminStatus;
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getTenderFkid() {
        return tenderFkid;
    }

    public void setTenderFkid(String tenderFkid) {
        this.tenderFkid = tenderFkid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.coverFkid);
        dest.writeTypedList(this.cover);
        dest.writeString(this.date);
        dest.writeString(this.docuPath);
        dest.writeValue(this.docuTypeFkid);
        dest.writeTypedList(this.docType);
        dest.writeValue(this.actualDocFkid);
        dest.writeTypedList(this.docName);
        dest.writeString(this.superAdminStatus);
        dest.writeValue(this.pkid);
        dest.writeString(this.tenderFkid);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    protected DocumentModel(Parcel in) {
        this.coverFkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cover = in.createTypedArrayList(Cover.CREATOR);
        this.date = in.readString();
        this.docuPath = in.readString();
        this.docuTypeFkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.docType = in.createTypedArrayList(DocType.CREATOR);
        this.actualDocFkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.docName = in.createTypedArrayList(DocName.CREATOR);
        this.superAdminStatus = in.readString();
        this.pkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.tenderFkid = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Creator<DocumentModel> CREATOR = new Creator<DocumentModel>() {
        @Override
        public DocumentModel createFromParcel(Parcel source) {
            return new DocumentModel(source);
        }

        @Override
        public DocumentModel[] newArray(int size) {
            return new DocumentModel[size];
        }
    };
}