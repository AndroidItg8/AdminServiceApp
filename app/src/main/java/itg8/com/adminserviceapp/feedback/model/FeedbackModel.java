
package itg8.com.adminserviceapp.feedback.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class FeedbackModel implements Parcelable
{

    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("ticket_fkid")
    @Expose
    private int ticketFkid;
    @SerializedName("TicketDetail")
    @Expose
    private itg8.com.adminserviceapp.feedback.model.TicketDetail TicketDetail;
    @SerializedName("title")
    @Expose
    private String title;
    public final static Parcelable.Creator<FeedbackModel> CREATOR = new Creator<FeedbackModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public FeedbackModel createFromParcel(Parcel in) {
            FeedbackModel instance = new FeedbackModel();
            instance.Description = ((String) in.readValue((String.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.rating = ((int) in.readValue((int.class.getClassLoader())));
            instance.ticketFkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.TicketDetail = ((itg8.com.adminserviceapp.feedback.model.TicketDetail) in.readValue((TicketDetail.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public FeedbackModel[] newArray(int size) {
            return (new FeedbackModel[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The Description
     */
    public String getDescription() {
        return Description;
    }

    /**
     * 
     * @param Description
     *     The Description
     */
    public void setDescription(String Description) {
        this.Description = Description;
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
     *     The rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * 
     * @param rating
     *     The rating
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * 
     * @return
     *     The ticketFkid
     */
    public int getTicketFkid() {
        return ticketFkid;
    }

    /**
     * 
     * @param ticketFkid
     *     The ticket_fkid
     */
    public void setTicketFkid(int ticketFkid) {
        this.ticketFkid = ticketFkid;
    }

    /**
     * 
     * @return
     *     The TicketDetail
     */
    public itg8.com.adminserviceapp.feedback.model.TicketDetail getTicketDetail() {
        return TicketDetail;
    }

    /**
     * 
     * @param TicketDetail
     *     The TicketDetail
     */
    public void setTicketDetail(itg8.com.adminserviceapp.feedback.model.TicketDetail TicketDetail) {
        this.TicketDetail = TicketDetail;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Description);
        dest.writeValue(mdate);
        dest.writeValue(mid);
        dest.writeValue(pkid);
        dest.writeValue(rating);
        dest.writeValue(ticketFkid);
        dest.writeValue(TicketDetail);
        dest.writeValue(title);
    }

    public int describeContents() {
        return  0;
    }

}
