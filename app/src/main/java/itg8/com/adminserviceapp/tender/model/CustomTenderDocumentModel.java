package itg8.com.adminserviceapp.tender.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android itg 8 on 12/26/2017.
 */

public class CustomTenderDocumentModel implements Parcelable {
    private Integer pkid;
    private String Tender_fkid;
    private Integer Cover_fkid;
    private Integer DocuType_fkid;
    private Integer ActualDoc_fkid;
    private Integer SuperAdminStatus;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private boolean isCheck;


    public String getEmployeeStatus() {
        return EmployeeStatus;
    }

    public void setEmployeeStatus(String employeeStatus) {
        EmployeeStatus = employeeStatus;
    }

    private String EmployeeStatus;

    public Integer getPkid() {
        return pkid;
    }

    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    public String getTender_fkid() {
        return Tender_fkid;
    }

    public void setTender_fkid(String tender_fkid) {
        Tender_fkid = tender_fkid;
    }

    public Integer getCover_fkid() {
        return Cover_fkid;
    }

    public void setCover_fkid(Integer cover_fkid) {
        Cover_fkid = cover_fkid;
    }

    public Integer getDocuType_fkid() {
        return DocuType_fkid;
    }

    public void setDocuType_fkid(Integer docuType_fkid) {
        DocuType_fkid = docuType_fkid;
    }

    public Integer getActualDoc_fkid() {
        return ActualDoc_fkid;
    }

    public void setActualDoc_fkid(Integer actualDoc_fkid) {
        ActualDoc_fkid = actualDoc_fkid;
    }

    public Integer getSuperAdminStatus() {
        return SuperAdminStatus;
    }

    public void setSuperAdminStatus(Integer superAdminStatus) {
        SuperAdminStatus = superAdminStatus;
    }

    public static Creator<CustomTenderDocumentModel> getCREATOR() {
        return CREATOR;
    }

    public CustomTenderDocumentModel() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.pkid);
        dest.writeString(this.Tender_fkid);
        dest.writeValue(this.Cover_fkid);
        dest.writeValue(this.DocuType_fkid);
        dest.writeValue(this.ActualDoc_fkid);
        dest.writeValue(this.SuperAdminStatus);
        dest.writeByte(this.isCheck ? (byte) 1 : (byte) 0);
        dest.writeString(this.EmployeeStatus);
    }

    protected CustomTenderDocumentModel(Parcel in) {
        this.pkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.Tender_fkid = in.readString();
        this.Cover_fkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.DocuType_fkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ActualDoc_fkid = (Integer) in.readValue(Integer.class.getClassLoader());
        this.SuperAdminStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isCheck = in.readByte() != 0;
        this.EmployeeStatus = in.readString();
    }

    public static final Creator<CustomTenderDocumentModel> CREATOR = new Creator<CustomTenderDocumentModel>() {
        @Override
        public CustomTenderDocumentModel createFromParcel(Parcel source) {
            return new CustomTenderDocumentModel(source);
        }

        @Override
        public CustomTenderDocumentModel[] newArray(int size) {
            return new CustomTenderDocumentModel[size];
        }
    };
}
