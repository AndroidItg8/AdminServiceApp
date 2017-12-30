
package itg8.com.adminserviceapp.enquiry.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class StatusModel implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("flag")
    @Expose
    private boolean flag;
    public final static Parcelable.Creator<StatusModel> CREATOR = new Creator<StatusModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StatusModel createFromParcel(Parcel in) {
            StatusModel instance = new StatusModel();
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.flag = ((boolean) in.readValue((boolean.class.getClassLoader())));
            return instance;
        }

        public StatusModel[] newArray(int size) {
            return (new StatusModel[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The flag
     */
    public boolean isFlag() {
        return flag;
    }

    /**
     * 
     * @param flag
     *     The flag
     */
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(flag);
    }

    public int describeContents() {
        return  0;
    }

}
