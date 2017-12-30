
package itg8.com.adminserviceapp.ticket.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Problem implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("problem")
    @Expose
    private String problem;
    @SerializedName("lastmodifef")
    @Expose
    private Object lastmodifef;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    public final static Parcelable.Creator<Problem> CREATOR = new Creator<Problem>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Problem createFromParcel(Parcel in) {
            Problem instance = new Problem();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.problem = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastmodifef = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public Problem[] newArray(int size) {
            return (new Problem[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The pkid
     */
    public int getPkid() {
        return pkid;
    }

    /**
     * 
     * @param pkid
     *     The pkid
     */
    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    /**
     * 
     * @return
     *     The problem
     */
    public String getProblem() {
        return problem;
    }

    /**
     * 
     * @param problem
     *     The problem
     */
    public void setProblem(String problem) {
        this.problem = problem;
    }

    /**
     * 
     * @return
     *     The lastmodifef
     */
    public Object getLastmodifef() {
        return lastmodifef;
    }

    /**
     * 
     * @param lastmodifef
     *     The lastmodifef
     */
    public void setLastmodifef(Object lastmodifef) {
        this.lastmodifef = lastmodifef;
    }

    /**
     * 
     * @return
     *     The mid
     */
    public Object getMid() {
        return mid;
    }

    /**
     * 
     * @param mid
     *     The mid
     */
    public void setMid(Object mid) {
        this.mid = mid;
    }

    /**
     * 
     * @return
     *     The mdate
     */
    public Object getMdate() {
        return mdate;
    }

    /**
     * 
     * @param mdate
     *     The mdate
     */
    public void setMdate(Object mdate) {
        this.mdate = mdate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(problem);
        dest.writeValue(lastmodifef);
        dest.writeValue(mid);
        dest.writeValue(mdate);
    }

    public int describeContents() {
        return  0;
    }

}
