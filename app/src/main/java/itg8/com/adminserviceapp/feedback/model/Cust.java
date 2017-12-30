
package itg8.com.adminserviceapp.feedback.model;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Cust implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("User_fkid")
    @Expose
    private String UserFkid;
    @SerializedName("Company_fkid")
    @Expose
    private int CompanyFkid;
    @SerializedName("customer_Name")
    @Expose
    private String customerName;
    @SerializedName("AddressLine1")
    @Expose
    private Object AddressLine1;
    @SerializedName("addressLine2")
    @Expose
    private Object addressLine2;
    @SerializedName("AddressLine3")
    @Expose
    private Object AddressLine3;
    @SerializedName("PrintName")
    @Expose
    private Object PrintName;
    @SerializedName("Group_fkid")
    @Expose
    private Object GroupFkid;
    @SerializedName("openBalance")
    @Expose
    private Object openBalance;
    @SerializedName("previousYearBalance")
    @Expose
    private Object previousYearBalance;
    @SerializedName("State")
    @Expose
    private Object State;
    @SerializedName("Fax")
    @Expose
    private Object Fax;
    @SerializedName("Telephoneno")
    @Expose
    private Object Telephoneno;
    @SerializedName("mobileno")
    @Expose
    private Object mobileno;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contactPersonName")
    @Expose
    private Object contactPersonName;
    @SerializedName("AdharNumber")
    @Expose
    private Object AdharNumber;
    @SerializedName("LastModifiedDate")
    @Expose
    private Object LastModifiedDate;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    @SerializedName("cid")
    @Expose
    private Object cid;
    @SerializedName("cdate")
    @Expose
    private Object cdate;
    @SerializedName("GSTNumber")
    @Expose
    private Object GSTNumber;
    @SerializedName("UserRole")
    @Expose
    private String UserRole;
    @SerializedName("ActualEmail")
    @Expose
    private Object ActualEmail;
    @SerializedName("Firstlogin")
    @Expose
    private int Firstlogin;
    @SerializedName("UserPIC")
    @Expose
    private Object UserPIC;
    public final static Parcelable.Creator<Cust> CREATOR = new Creator<Cust>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Cust createFromParcel(Parcel in) {
            Cust instance = new Cust();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.UserFkid = ((String) in.readValue((String.class.getClassLoader())));
            instance.CompanyFkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.customerName = ((String) in.readValue((String.class.getClassLoader())));
            instance.AddressLine1 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.addressLine2 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.AddressLine3 = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.PrintName = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.GroupFkid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.openBalance = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.previousYearBalance = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.State = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.Fax = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.Telephoneno = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mobileno = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.email = ((String) in.readValue((String.class.getClassLoader())));
            instance.contactPersonName = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.AdharNumber = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.LastModifiedDate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.cid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.cdate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.GSTNumber = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.UserRole = ((String) in.readValue((String.class.getClassLoader())));
            instance.ActualEmail = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.Firstlogin = ((int) in.readValue((int.class.getClassLoader())));
            instance.UserPIC = ((Object) in.readValue((Object.class.getClassLoader())));
            return instance;
        }

        public Cust[] newArray(int size) {
            return (new Cust[size]);
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
     *     The UserFkid
     */
    public String getUserFkid() {
        return UserFkid;
    }

    /**
     * 
     * @param UserFkid
     *     The User_fkid
     */
    public void setUserFkid(String UserFkid) {
        this.UserFkid = UserFkid;
    }

    /**
     * 
     * @return
     *     The CompanyFkid
     */
    public int getCompanyFkid() {
        return CompanyFkid;
    }

    /**
     * 
     * @param CompanyFkid
     *     The Company_fkid
     */
    public void setCompanyFkid(int CompanyFkid) {
        this.CompanyFkid = CompanyFkid;
    }

    /**
     * 
     * @return
     *     The customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 
     * @param customerName
     *     The customer_Name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 
     * @return
     *     The AddressLine1
     */
    public Object getAddressLine1() {
        return AddressLine1;
    }

    /**
     * 
     * @param AddressLine1
     *     The AddressLine1
     */
    public void setAddressLine1(Object AddressLine1) {
        this.AddressLine1 = AddressLine1;
    }

    /**
     * 
     * @return
     *     The addressLine2
     */
    public Object getAddressLine2() {
        return addressLine2;
    }

    /**
     * 
     * @param addressLine2
     *     The addressLine2
     */
    public void setAddressLine2(Object addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * 
     * @return
     *     The AddressLine3
     */
    public Object getAddressLine3() {
        return AddressLine3;
    }

    /**
     * 
     * @param AddressLine3
     *     The AddressLine3
     */
    public void setAddressLine3(Object AddressLine3) {
        this.AddressLine3 = AddressLine3;
    }

    /**
     * 
     * @return
     *     The PrintName
     */
    public Object getPrintName() {
        return PrintName;
    }

    /**
     * 
     * @param PrintName
     *     The PrintName
     */
    public void setPrintName(Object PrintName) {
        this.PrintName = PrintName;
    }

    /**
     * 
     * @return
     *     The GroupFkid
     */
    public Object getGroupFkid() {
        return GroupFkid;
    }

    /**
     * 
     * @param GroupFkid
     *     The Group_fkid
     */
    public void setGroupFkid(Object GroupFkid) {
        this.GroupFkid = GroupFkid;
    }

    /**
     * 
     * @return
     *     The openBalance
     */
    public Object getOpenBalance() {
        return openBalance;
    }

    /**
     * 
     * @param openBalance
     *     The openBalance
     */
    public void setOpenBalance(Object openBalance) {
        this.openBalance = openBalance;
    }

    /**
     * 
     * @return
     *     The previousYearBalance
     */
    public Object getPreviousYearBalance() {
        return previousYearBalance;
    }

    /**
     * 
     * @param previousYearBalance
     *     The previousYearBalance
     */
    public void setPreviousYearBalance(Object previousYearBalance) {
        this.previousYearBalance = previousYearBalance;
    }

    /**
     * 
     * @return
     *     The State
     */
    public Object getState() {
        return State;
    }

    /**
     * 
     * @param State
     *     The State
     */
    public void setState(Object State) {
        this.State = State;
    }

    /**
     * 
     * @return
     *     The Fax
     */
    public Object getFax() {
        return Fax;
    }

    /**
     * 
     * @param Fax
     *     The Fax
     */
    public void setFax(Object Fax) {
        this.Fax = Fax;
    }

    /**
     * 
     * @return
     *     The Telephoneno
     */
    public Object getTelephoneno() {
        return Telephoneno;
    }

    /**
     * 
     * @param Telephoneno
     *     The Telephoneno
     */
    public void setTelephoneno(Object Telephoneno) {
        this.Telephoneno = Telephoneno;
    }

    /**
     * 
     * @return
     *     The mobileno
     */
    public Object getMobileno() {
        return mobileno;
    }

    /**
     * 
     * @param mobileno
     *     The mobileno
     */
    public void setMobileno(Object mobileno) {
        this.mobileno = mobileno;
    }

    /**
     * 
     * @return
     *     The email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return
     *     The contactPersonName
     */
    public Object getContactPersonName() {
        return contactPersonName;
    }

    /**
     * 
     * @param contactPersonName
     *     The contactPersonName
     */
    public void setContactPersonName(Object contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    /**
     * 
     * @return
     *     The AdharNumber
     */
    public Object getAdharNumber() {
        return AdharNumber;
    }

    /**
     * 
     * @param AdharNumber
     *     The AdharNumber
     */
    public void setAdharNumber(Object AdharNumber) {
        this.AdharNumber = AdharNumber;
    }

    /**
     * 
     * @return
     *     The LastModifiedDate
     */
    public Object getLastModifiedDate() {
        return LastModifiedDate;
    }

    /**
     * 
     * @param LastModifiedDate
     *     The LastModifiedDate
     */
    public void setLastModifiedDate(Object LastModifiedDate) {
        this.LastModifiedDate = LastModifiedDate;
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

    /**
     * 
     * @return
     *     The cid
     */
    public Object getCid() {
        return cid;
    }

    /**
     * 
     * @param cid
     *     The cid
     */
    public void setCid(Object cid) {
        this.cid = cid;
    }

    /**
     * 
     * @return
     *     The cdate
     */
    public Object getCdate() {
        return cdate;
    }

    /**
     * 
     * @param cdate
     *     The cdate
     */
    public void setCdate(Object cdate) {
        this.cdate = cdate;
    }

    /**
     * 
     * @return
     *     The GSTNumber
     */
    public Object getGSTNumber() {
        return GSTNumber;
    }

    /**
     * 
     * @param GSTNumber
     *     The GSTNumber
     */
    public void setGSTNumber(Object GSTNumber) {
        this.GSTNumber = GSTNumber;
    }

    /**
     * 
     * @return
     *     The UserRole
     */
    public String getUserRole() {
        return UserRole;
    }

    /**
     * 
     * @param UserRole
     *     The UserRole
     */
    public void setUserRole(String UserRole) {
        this.UserRole = UserRole;
    }

    /**
     * 
     * @return
     *     The ActualEmail
     */
    public Object getActualEmail() {
        return ActualEmail;
    }

    /**
     * 
     * @param ActualEmail
     *     The ActualEmail
     */
    public void setActualEmail(Object ActualEmail) {
        this.ActualEmail = ActualEmail;
    }

    /**
     * 
     * @return
     *     The Firstlogin
     */
    public int getFirstlogin() {
        return Firstlogin;
    }

    /**
     * 
     * @param Firstlogin
     *     The Firstlogin
     */
    public void setFirstlogin(int Firstlogin) {
        this.Firstlogin = Firstlogin;
    }

    /**
     * 
     * @return
     *     The UserPIC
     */
    public Object getUserPIC() {
        return UserPIC;
    }

    /**
     * 
     * @param UserPIC
     *     The UserPIC
     */
    public void setUserPIC(Object UserPIC) {
        this.UserPIC = UserPIC;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(UserFkid);
        dest.writeValue(CompanyFkid);
        dest.writeValue(customerName);
        dest.writeValue(AddressLine1);
        dest.writeValue(addressLine2);
        dest.writeValue(AddressLine3);
        dest.writeValue(PrintName);
        dest.writeValue(GroupFkid);
        dest.writeValue(openBalance);
        dest.writeValue(previousYearBalance);
        dest.writeValue(State);
        dest.writeValue(Fax);
        dest.writeValue(Telephoneno);
        dest.writeValue(mobileno);
        dest.writeValue(email);
        dest.writeValue(contactPersonName);
        dest.writeValue(AdharNumber);
        dest.writeValue(LastModifiedDate);
        dest.writeValue(mid);
        dest.writeValue(mdate);
        dest.writeValue(cid);
        dest.writeValue(cdate);
        dest.writeValue(GSTNumber);
        dest.writeValue(UserRole);
        dest.writeValue(ActualEmail);
        dest.writeValue(Firstlogin);
        dest.writeValue(UserPIC);
    }

    public int describeContents() {
        return  0;
    }

}
