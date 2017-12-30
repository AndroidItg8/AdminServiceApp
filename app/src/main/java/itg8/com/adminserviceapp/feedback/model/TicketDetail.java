
package itg8.com.adminserviceapp.feedback.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class TicketDetail implements Parcelable
{

    @SerializedName("AssignDate")
    @Expose
    private String AssignDate;
    @SerializedName("AssignedContactno")
    @Expose
    private String AssignedContactno;
    @SerializedName("Assignedpersonname")
    @Expose
    private String Assignedpersonname;
    @SerializedName("Customer_fkid")
    @Expose
    private String CustomerFkid;
    @SerializedName("cust")
    @Expose
    private List<Cust> cust = new ArrayList<Cust>();
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("Invoice_fkid")
    @Expose
    private int InvoiceFkid;
    @SerializedName("lastModifieddate")
    @Expose
    private Object lastModifieddate;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("OtherProblem")
    @Expose
    private Object OtherProblem;
    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("Problem_fkid")
    @Expose
    private Object ProblemFkid;
    @SerializedName("Problem")
    @Expose
    private List<itg8.com.adminserviceapp.ticket.model.Problem> Problem = new ArrayList<itg8.com.adminserviceapp.ticket.model.Problem>();
    @SerializedName("Product_fkid")
    @Expose
    private Object ProductFkid;
    @SerializedName("Product")
    @Expose
    private List<itg8.com.adminserviceapp.ticket.model.Product> Product = new ArrayList<itg8.com.adminserviceapp.ticket.model.Product>();
    @SerializedName("SolveDate")
    @Expose
    private Object SolveDate;
    @SerializedName("Status")
    @Expose
    private int Status;
    public final static Parcelable.Creator<TicketDetail> CREATOR = new Creator<TicketDetail>() {


        @SuppressWarnings({
            "unchecked"
        })
        public TicketDetail createFromParcel(Parcel in) {
            TicketDetail instance = new TicketDetail();
            instance.AssignDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.AssignedContactno = ((String) in.readValue((String.class.getClassLoader())));
            instance.Assignedpersonname = ((String) in.readValue((String.class.getClassLoader())));
            instance.CustomerFkid = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.cust, (itg8.com.adminserviceapp.feedback.model.Cust.class.getClassLoader()));
            instance.Description = ((String) in.readValue((String.class.getClassLoader())));
            instance.InvoiceFkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.lastModifieddate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.OtherProblem = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.ProblemFkid = ((Object) in.readValue((Object.class.getClassLoader())));
            in.readList(instance.Problem, (itg8.com.adminserviceapp.ticket.model.Problem.class.getClassLoader()));
            instance.ProductFkid = ((Object) in.readValue((Object.class.getClassLoader())));
            in.readList(instance.Product, (itg8.com.adminserviceapp.ticket.model.Product.class.getClassLoader()));
            instance.SolveDate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.Status = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public TicketDetail[] newArray(int size) {
            return (new TicketDetail[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The AssignDate
     */
    public String getAssignDate() {
        return AssignDate;
    }

    /**
     * 
     * @param AssignDate
     *     The AssignDate
     */
    public void setAssignDate(String AssignDate) {
        this.AssignDate = AssignDate;
    }

    /**
     * 
     * @return
     *     The AssignedContactno
     */
    public String getAssignedContactno() {
        return AssignedContactno;
    }

    /**
     * 
     * @param AssignedContactno
     *     The AssignedContactno
     */
    public void setAssignedContactno(String AssignedContactno) {
        this.AssignedContactno = AssignedContactno;
    }

    /**
     * 
     * @return
     *     The Assignedpersonname
     */
    public String getAssignedpersonname() {
        return Assignedpersonname;
    }

    /**
     * 
     * @param Assignedpersonname
     *     The Assignedpersonname
     */
    public void setAssignedpersonname(String Assignedpersonname) {
        this.Assignedpersonname = Assignedpersonname;
    }

    /**
     * 
     * @return
     *     The CustomerFkid
     */
    public String getCustomerFkid() {
        return CustomerFkid;
    }

    /**
     * 
     * @param CustomerFkid
     *     The Customer_fkid
     */
    public void setCustomerFkid(String CustomerFkid) {
        this.CustomerFkid = CustomerFkid;
    }

    /**
     * 
     * @return
     *     The cust
     */
    public List<Cust> getCust() {
        return cust;
    }

    /**
     * 
     * @param cust
     *     The cust
     */
    public void setCust(List<Cust> cust) {
        this.cust = cust;
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
     *     The InvoiceFkid
     */
    public int getInvoiceFkid() {
        return InvoiceFkid;
    }

    /**
     * 
     * @param InvoiceFkid
     *     The Invoice_fkid
     */
    public void setInvoiceFkid(int InvoiceFkid) {
        this.InvoiceFkid = InvoiceFkid;
    }

    /**
     * 
     * @return
     *     The lastModifieddate
     */
    public Object getLastModifieddate() {
        return lastModifieddate;
    }

    /**
     * 
     * @param lastModifieddate
     *     The lastModifieddate
     */
    public void setLastModifieddate(Object lastModifieddate) {
        this.lastModifieddate = lastModifieddate;
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
     *     The OtherProblem
     */
    public Object getOtherProblem() {
        return OtherProblem;
    }

    /**
     * 
     * @param OtherProblem
     *     The OtherProblem
     */
    public void setOtherProblem(Object OtherProblem) {
        this.OtherProblem = OtherProblem;
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
     *     The ProblemFkid
     */
    public Object getProblemFkid() {
        return ProblemFkid;
    }

    /**
     * 
     * @param ProblemFkid
     *     The Problem_fkid
     */
    public void setProblemFkid(Object ProblemFkid) {
        this.ProblemFkid = ProblemFkid;
    }

    /**
     * 
     * @return
     *     The Problem
     */
    public List<itg8.com.adminserviceapp.ticket.model.Problem> getProblem() {
        return Problem;
    }

    /**
     * 
     * @param Problem
     *     The Problem
     */
    public void setProblem(List<itg8.com.adminserviceapp.ticket.model.Problem> Problem) {
        this.Problem = Problem;
    }

    /**
     * 
     * @return
     *     The ProductFkid
     */
    public Object getProductFkid() {
        return ProductFkid;
    }

    /**
     * 
     * @param ProductFkid
     *     The Product_fkid
     */
    public void setProductFkid(Object ProductFkid) {
        this.ProductFkid = ProductFkid;
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
     *     The SolveDate
     */
    public Object getSolveDate() {
        return SolveDate;
    }

    /**
     * 
     * @param SolveDate
     *     The SolveDate
     */
    public void setSolveDate(Object SolveDate) {
        this.SolveDate = SolveDate;
    }

    /**
     * 
     * @return
     *     The Status
     */
    public int getStatus() {
        return Status;
    }

    /**
     * 
     * @param Status
     *     The Status
     */
    public void setStatus(int Status) {
        this.Status = Status;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(AssignDate);
        dest.writeValue(AssignedContactno);
        dest.writeValue(Assignedpersonname);
        dest.writeValue(CustomerFkid);
        dest.writeList(cust);
        dest.writeValue(Description);
        dest.writeValue(InvoiceFkid);
        dest.writeValue(lastModifieddate);
        dest.writeValue(mdate);
        dest.writeValue(mid);
        dest.writeValue(OtherProblem);
        dest.writeValue(pkid);
        dest.writeValue(ProblemFkid);
        dest.writeList(Problem);
        dest.writeValue(ProductFkid);
        dest.writeList(Product);
        dest.writeValue(SolveDate);
        dest.writeValue(Status);
    }

    public int describeContents() {
        return  0;
    }

}
