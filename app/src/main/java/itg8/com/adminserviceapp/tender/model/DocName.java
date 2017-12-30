package itg8.com.adminserviceapp.tender.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocName implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private Integer pkid;
    @SerializedName("TenderActualDocument")
    @Expose
    private String tenderActualDocument;
    @SerializedName("description")
    @Expose
    private Object description;
    public final static Parcelable.Creator<DocName> CREATOR = new Creator<DocName>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DocName createFromParcel(Parcel in) {
            return new DocName(in);
        }

        public DocName[] newArray(int size) {
            return (new DocName[size]);
        }

    }
            ;

    protected DocName(Parcel in) {
        this.pkid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.tenderActualDocument = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public DocName() {
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getTenderActualDocument() {
        return tenderActualDocument;
    }

    public void setTenderActualDocument(String tenderActualDocument) {
        this.tenderActualDocument = tenderActualDocument;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(tenderActualDocument);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}