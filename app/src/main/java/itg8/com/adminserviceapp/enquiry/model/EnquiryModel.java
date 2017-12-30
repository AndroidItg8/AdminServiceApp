
package itg8.com.adminserviceapp.enquiry.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class EnquiryModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("userName")
    @Expose
    private String userName;
    @SerializedName("productName")
    @Expose
    private String productName;
    @SerializedName("Query")
    @Expose
    private String Query;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private Object status;
    @SerializedName("contact")
    @Expose
    private String contact;
    public final static Parcelable.Creator<EnquiryModel> CREATOR = new Creator<EnquiryModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public EnquiryModel createFromParcel(Parcel in) {
            EnquiryModel instance = new EnquiryModel();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.userName = ((String) in.readValue((String.class.getClassLoader())));
            instance.productName = ((String) in.readValue((String.class.getClassLoader())));
            instance.Query = ((String) in.readValue((String.class.getClassLoader())));
            instance.description = ((String) in.readValue((String.class.getClassLoader())));
            instance.status = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.contact = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public EnquiryModel[] newArray(int size) {
            return (new EnquiryModel[size]);
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
     *     The userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     *     The userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * @return
     *     The productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 
     * @param productName
     *     The productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 
     * @return
     *     The Query
     */
    public String getQuery() {
        return Query;
    }

    /**
     * 
     * @param Query
     *     The Query
     */
    public void setQuery(String Query) {
        this.Query = Query;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Object getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Object status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * 
     * @param contact
     *     The contact
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(userName);
        dest.writeValue(productName);
        dest.writeValue(Query);
        dest.writeValue(description);
        dest.writeValue(status);
        dest.writeValue(contact);
    }

    public int describeContents() {
        return  0;
    }

}
