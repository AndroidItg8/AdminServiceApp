package itg8.com.adminserviceapp.tender.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DocType implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private Integer pkid;
    @SerializedName("DocumentTypeName")
    @Expose
    private String documentTypeName;
    @SerializedName("Description")
    @Expose
    private Object description;
    public final static Parcelable.Creator<DocType> CREATOR = new Creator<DocType>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DocType createFromParcel(Parcel in) {
            return new DocType(in);
        }

        public DocType[] newArray(int size) {
            return (new DocType[size]);
        }

    }
            ;

    protected DocType(Parcel in) {
        this.pkid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.documentTypeName = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public DocType() {
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

    public void setDocumentTypeName(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(documentTypeName);
        dest.writeValue(description);
    }

    public int describeContents() {
        return 0;
    }

}