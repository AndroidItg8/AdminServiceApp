
package itg8.com.adminserviceapp.enquiry.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class EnquiryModel implements Parcelable
{

    @SerializedName("Contactno")
    @Expose
    private String Contactno;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("lastmodifieddate")
    @Expose
    private String lastmodifieddate;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("product_fkid")
    @Expose
    private int productFkid;
    @SerializedName("Product")
    @Expose
    private List<itg8.com.adminserviceapp.ticket.model.Product> Product = new ArrayList<itg8.com.adminserviceapp.ticket.model.Product>();
    @SerializedName("Query")
    @Expose
    private String Query;
    @SerializedName("Status")
    @Expose
    private Object Status;
    @SerializedName("user_fkid")
    @Expose
    private String userFkid;
    @SerializedName("Cust")
    @Expose
    private List<itg8.com.adminserviceapp.ticket.model.Cust> Cust = new ArrayList<itg8.com.adminserviceapp.ticket.model.Cust>();
    public final static Parcelable.Creator<EnquiryModel> CREATOR = new Creator<EnquiryModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public EnquiryModel createFromParcel(Parcel in) {
            EnquiryModel instance = new EnquiryModel();
            instance.Contactno = ((String) in.readValue((String.class.getClassLoader())));
            instance.Description = ((String) in.readValue((String.class.getClassLoader())));
            instance.lastmodifieddate = ((String) in.readValue((String.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.productFkid = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.Product, (itg8.com.adminserviceapp.ticket.model.Product.class.getClassLoader()));
            instance.Query = ((String) in.readValue((String.class.getClassLoader())));
            instance.Status = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.userFkid = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.Cust, (itg8.com.adminserviceapp.ticket.model.Cust.class.getClassLoader()));
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
     *     The Contactno
     */
    public String getContactno() {
        return Contactno;
    }

    /**
     * 
     * @param Contactno
     *     The Contactno
     */
    public void setContactno(String Contactno) {
        this.Contactno = Contactno;
    }

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
     *     The lastmodifieddate
     */
    public String getLastmodifieddate() {
        return lastmodifieddate;
    }

    /**
     * 
     * @param lastmodifieddate
     *     The lastmodifieddate
     */
    public void setLastmodifieddate(String lastmodifieddate) {
        this.lastmodifieddate = lastmodifieddate;
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
     *     The productFkid
     */
    public int getProductFkid() {
        return productFkid;
    }

    /**
     * 
     * @param productFkid
     *     The product_fkid
     */
    public void setProductFkid(int productFkid) {
        this.productFkid = productFkid;
    }

    /**
     * 
     * @return
     *     The Product
     */
    public List<itg8.com.adminserviceapp.ticket.model.Product> getProduct() {
        return Product;
    }

    /**
     * 
     * @param Product
     *     The Product
     */
    public void setProduct(List<itg8.com.adminserviceapp.ticket.model.Product> Product) {
        this.Product = Product;
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
     *     The Status
     */
    public Object getStatus() {
        return Status;
    }

    /**
     * 
     * @param Status
     *     The Status
     */
    public void setStatus(Object Status) {
        this.Status = Status;
    }

    /**
     * 
     * @return
     *     The userFkid
     */
    public String getUserFkid() {
        return userFkid;
    }

    /**
     * 
     * @param userFkid
     *     The user_fkid
     */
    public void setUserFkid(String userFkid) {
        this.userFkid = userFkid;
    }

    /**
     * 
     * @return
     *     The Cust
     */
    public List<itg8.com.adminserviceapp.ticket.model.Cust> getCust() {
        return Cust;
    }

    /**
     * 
     * @param Cust
     *     The Cust
     */
    public void setCust(List<itg8.com.adminserviceapp.ticket.model.Cust> Cust) {
        this.Cust = Cust;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(Contactno);
        dest.writeValue(Description);
        dest.writeValue(lastmodifieddate);
        dest.writeValue(mdate);
        dest.writeValue(mid);
        dest.writeValue(pkid);
        dest.writeValue(productFkid);
        dest.writeList(Product);
        dest.writeValue(Query);
        dest.writeValue(Status);
        dest.writeValue(userFkid);
        dest.writeList(Cust);
    }

    public int describeContents() {
        return  0;
    }

}
