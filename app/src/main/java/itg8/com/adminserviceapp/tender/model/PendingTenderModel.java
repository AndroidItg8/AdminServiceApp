
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
    private boolean EMDStatus;
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
    public boolean getEMDStatus() {
        return EMDStatus;
    }

    /**
     * 
     * @param EMDStatus
     *     The EMDStatus
     */
    public void setEMDStatus(boolean EMDStatus) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.pkid);
        dest.writeString(this.Title);
        dest.writeString(this.TenderID);
        dest.writeString(this.Description);
        dest.writeString(this.TenderValueIn);
        dest.writeString(this.TenderFeeIn);
        dest.writeString(this.EMDException);
        dest.writeString(this.EMDAMT);
        dest.writeByte(this.EMDStatus ? (byte) 1 : (byte) 0);
        dest.writeString(this.ENDRefundDate);
        dest.writeString(this.OpenDate);
        dest.writeString(this.ClosedDate);
        dest.writeString(this.Location);
        dest.writeString(this.TenderFile);
        dest.writeParcelable((Parcelable) this.AddedDate, flags);
        dest.writeParcelable((Parcelable) this.SAdminstatu, flags);
        dest.writeString(this.TenderFillorNot);
        dest.writeString(this.TenderStatus);
        dest.writeParcelable((Parcelable) this.mid, flags);
        dest.writeParcelable((Parcelable) this.mdate, flags);
        dest.writeTypedList(this.Documents);
    }

    public PendingTenderModel() {
    }

    protected PendingTenderModel(Parcel in) {
        this.pkid = in.readInt();
        this.Title = in.readString();
        this.TenderID = in.readString();
        this.Description = in.readString();
        this.TenderValueIn = in.readString();
        this.TenderFeeIn = in.readString();
        this.EMDException = in.readString();
        this.EMDAMT = in.readString();
        this.EMDStatus = in.readByte() != 0;
        this.ENDRefundDate = in.readString();
        this.OpenDate = in.readString();
        this.ClosedDate = in.readString();
        this.Location = in.readString();
        this.TenderFile = in.readString();
        this.AddedDate = in.readParcelable(Object.class.getClassLoader());
        this.SAdminstatu = in.readParcelable(Object.class.getClassLoader());
        this.TenderFillorNot = in.readString();
        this.TenderStatus = in.readString();
        this.mid = in.readParcelable(Object.class.getClassLoader());
        this.mdate = in.readParcelable(Object.class.getClassLoader());
        this.Documents = in.createTypedArrayList(DocumentModel.CREATOR);
    }

    public static final Creator<PendingTenderModel> CREATOR = new Creator<PendingTenderModel>() {
        @Override
        public PendingTenderModel createFromParcel(Parcel source) {
            return new PendingTenderModel(source);
        }

        @Override
        public PendingTenderModel[] newArray(int size) {
            return new PendingTenderModel[size];
        }
    };
}
