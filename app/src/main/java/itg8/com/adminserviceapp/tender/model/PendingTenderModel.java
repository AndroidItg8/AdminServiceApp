
package itg8.com.adminserviceapp.tender.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class PendingTenderModel implements Parcelable
{

    @SerializedName("pkid")
    @Expose
    private int pkid;
    @SerializedName("Title")
    @Expose
    private String Title;
    @SerializedName("TenderID")
    @Expose
    private String TenderID;
    @SerializedName("Description")
    @Expose
    private String Description;
    @SerializedName("TenderValueIn")
    @Expose
    private String TenderValueIn;
    @SerializedName("TenderFeeIn")
    @Expose
    private String TenderFeeIn;
    @SerializedName("EMDException")
    @Expose
    private String EMDException;
    @SerializedName("EMDAMT")
    @Expose
    private String EMDAMT;
    @SerializedName("EMDStatus")
    @Expose
    private String EMDStatus;
    @SerializedName("ENDRefundDate")
    @Expose
    private String ENDRefundDate;
    @SerializedName("OpenDate")
    @Expose
    private String OpenDate;
    @SerializedName("ClosedDate")
    @Expose
    private String ClosedDate;
    @SerializedName("Location")
    @Expose
    private String Location;
    @SerializedName("TenderFile")
    @Expose
    private String TenderFile;
    @SerializedName("AddedDate")
    @Expose
    private Object AddedDate;
    @SerializedName("SAdminstatu")
    @Expose
    private Object SAdminstatu;
    @SerializedName("TenderFillorNot")
    @Expose
    private String TenderFillorNot;
    @SerializedName("TenderStatus")
    @Expose
    private String TenderStatus;
    @SerializedName("mid")
    @Expose
    private Object mid;
    @SerializedName("mdate")
    @Expose
    private Object mdate;
    @SerializedName("Documents")
    @Expose
    private List<DocumentModel> Documents = new ArrayList<DocumentModel>();
    public final static Parcelable.Creator<PendingTenderModel> CREATOR = new Creator<PendingTenderModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public PendingTenderModel createFromParcel(Parcel in) {
            PendingTenderModel instance = new PendingTenderModel();
            instance.pkid = ((int) in.readValue((int.class.getClassLoader())));
            instance.Title = ((String) in.readValue((String.class.getClassLoader())));
            instance.TenderID = ((String) in.readValue((String.class.getClassLoader())));
            instance.Description = ((String) in.readValue((String.class.getClassLoader())));
            instance.TenderValueIn = ((String) in.readValue((String.class.getClassLoader())));
            instance.TenderFeeIn = ((String) in.readValue((String.class.getClassLoader())));
            instance.EMDException = ((String) in.readValue((String.class.getClassLoader())));
            instance.EMDAMT = ((String) in.readValue((String.class.getClassLoader())));
            instance.EMDStatus = ((String) in.readValue((String.class.getClassLoader())));
            instance.ENDRefundDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.OpenDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.ClosedDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.Location = ((String) in.readValue((String.class.getClassLoader())));
            instance.TenderFile = ((String) in.readValue((String.class.getClassLoader())));
            instance.AddedDate = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.SAdminstatu = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.TenderFillorNot = ((String) in.readValue((String.class.getClassLoader())));
            instance.TenderStatus = ((String) in.readValue((String.class.getClassLoader())));
            instance.mid = ((Object) in.readValue((Object.class.getClassLoader())));
            instance.mdate = ((Object) in.readValue((Object.class.getClassLoader())));
            in.readList(instance.Documents, (DocumentModel.class.getClassLoader()));
            return instance;
        }

        public PendingTenderModel[] newArray(int size) {
            return (new PendingTenderModel[size]);
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
     *     The Title
     */
    public String getTitle() {
        return Title;
    }

    /**
     * 
     * @param Title
     *     The Title
     */
    public void setTitle(String Title) {
        this.Title = Title;
    }

    /**
     * 
     * @return
     *     The TenderID
     */
    public String getTenderID() {
        return TenderID;
    }

    /**
     * 
     * @param TenderID
     *     The TenderID
     */
    public void setTenderID(String TenderID) {
        this.TenderID = TenderID;
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
     *     The TenderValueIn
     */
    public String getTenderValueIn() {
        return TenderValueIn;
    }

    /**
     * 
     * @param TenderValueIn
     *     The TenderValueIn
     */
    public void setTenderValueIn(String TenderValueIn) {
        this.TenderValueIn = TenderValueIn;
    }

    /**
     * 
     * @return
     *     The TenderFeeIn
     */
    public String getTenderFeeIn() {
        return TenderFeeIn;
    }

    /**
     * 
     * @param TenderFeeIn
     *     The TenderFeeIn
     */
    public void setTenderFeeIn(String TenderFeeIn) {
        this.TenderFeeIn = TenderFeeIn;
    }

    /**
     * 
     * @return
     *     The EMDException
     */
    public String getEMDException() {
        return EMDException;
    }

    /**
     * 
     * @param EMDException
     *     The EMDException
     */
    public void setEMDException(String EMDException) {
        this.EMDException = EMDException;
    }

    /**
     * 
     * @return
     *     The EMDAMT
     */
    public String getEMDAMT() {
        return EMDAMT;
    }

    /**
     * 
     * @param EMDAMT
     *     The EMDAMT
     */
    public void setEMDAMT(String EMDAMT) {
        this.EMDAMT = EMDAMT;
    }

    /**
     * 
     * @return
     *     The EMDStatus
     */
    public String getEMDStatus() {
        return EMDStatus;
    }

    /**
     * 
     * @param EMDStatus
     *     The EMDStatus
     */
    public void setEMDStatus(String EMDStatus) {
        this.EMDStatus = EMDStatus;
    }

    /**
     * 
     * @return
     *     The ENDRefundDate
     */
    public String getENDRefundDate() {
        return ENDRefundDate;
    }

    /**
     * 
     * @param ENDRefundDate
     *     The ENDRefundDate
     */
    public void setENDRefundDate(String ENDRefundDate) {
        this.ENDRefundDate = ENDRefundDate;
    }

    /**
     * 
     * @return
     *     The OpenDate
     */
    public String getOpenDate() {
        return OpenDate;
    }

    /**
     * 
     * @param OpenDate
     *     The OpenDate
     */
    public void setOpenDate(String OpenDate) {
        this.OpenDate = OpenDate;
    }

    /**
     * 
     * @return
     *     The ClosedDate
     */
    public String getClosedDate() {
        return ClosedDate;
    }

    /**
     * 
     * @param ClosedDate
     *     The ClosedDate
     */
    public void setClosedDate(String ClosedDate) {
        this.ClosedDate = ClosedDate;
    }

    /**
     * 
     * @return
     *     The Location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * 
     * @param Location
     *     The Location
     */
    public void setLocation(String Location) {
        this.Location = Location;
    }

    /**
     * 
     * @return
     *     The TenderFile
     */
    public String getTenderFile() {
        return TenderFile;
    }

    /**
     * 
     * @param TenderFile
     *     The TenderFile
     */
    public void setTenderFile(String TenderFile) {
        this.TenderFile = TenderFile;
    }

    /**
     * 
     * @return
     *     The AddedDate
     */
    public Object getAddedDate() {
        return AddedDate;
    }

    /**
     * 
     * @param AddedDate
     *     The AddedDate
     */
    public void setAddedDate(Object AddedDate) {
        this.AddedDate = AddedDate;
    }

    /**
     * 
     * @return
     *     The SAdminstatu
     */
    public Object getSAdminstatu() {
        return SAdminstatu;
    }

    /**
     * 
     * @param SAdminstatu
     *     The SAdminstatu
     */
    public void setSAdminstatu(Object SAdminstatu) {
        this.SAdminstatu = SAdminstatu;
    }

    /**
     * 
     * @return
     *     The TenderFillorNot
     */
    public String getTenderFillorNot() {
        return TenderFillorNot;
    }

    /**
     * 
     * @param TenderFillorNot
     *     The TenderFillorNot
     */
    public void setTenderFillorNot(String TenderFillorNot) {
        this.TenderFillorNot = TenderFillorNot;
    }

    /**
     * 
     * @return
     *     The TenderStatus
     */
    public String getTenderStatus() {
        return TenderStatus;
    }

    /**
     * 
     * @param TenderStatus
     *     The TenderStatus
     */
    public void setTenderStatus(String TenderStatus) {
        this.TenderStatus = TenderStatus;
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
     *     The Documents
     */
    public List<DocumentModel> getDocuments() {
        return Documents;
    }

    /**
     * 
     * @param Documents
     *     The Documents
     */
    public void setDocuments(List<DocumentModel> Documents) {
        this.Documents = Documents;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pkid);
        dest.writeValue(Title);
        dest.writeValue(TenderID);
        dest.writeValue(Description);
        dest.writeValue(TenderValueIn);
        dest.writeValue(TenderFeeIn);
        dest.writeValue(EMDException);
        dest.writeValue(EMDAMT);
        dest.writeValue(EMDStatus);
        dest.writeValue(ENDRefundDate);
        dest.writeValue(OpenDate);
        dest.writeValue(ClosedDate);
        dest.writeValue(Location);
        dest.writeValue(TenderFile);
        dest.writeValue(AddedDate);
        dest.writeValue(SAdminstatu);
        dest.writeValue(TenderFillorNot);
        dest.writeValue(TenderStatus);
        dest.writeValue(mid);
        dest.writeValue(mdate);
        dest.writeList(Documents);
    }

    public int describeContents() {
        return  0;
    }

}
