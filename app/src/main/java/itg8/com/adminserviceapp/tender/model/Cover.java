package itg8.com.adminserviceapp.tender.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cover implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private Integer pkid;
    @SerializedName("CoverName")
    @Expose
    private String coverName;
    @SerializedName("CoverDescription")
    @Expose
    private Object coverDescription;
    public final static Parcelable.Creator<Cover> CREATOR = new Creator<Cover>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Cover createFromParcel(Parcel in) {
            return new Cover(in);
        }

        public Cover[] newArray(int size) {
            return (new Cover[size]);
        }

    }
            ;

    protected Cover(Parcel in) {
        this.pkid = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.coverName = ((String) in.readValue((String.class.getClassLoader())));
        this.coverDescription = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    public Cover() {
    }

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public Object getCoverDescription() {
        return coverDescription;
    }

    public void setCoverDescription(Object coverDescription) {
        this.coverDescription = coverDescription;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(coverName);
        dest.writeValue(coverDescription);
    }

    public int describeContents() {
        return 0;
    }

}
