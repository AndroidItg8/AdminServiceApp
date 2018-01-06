package itg8.com.adminserviceapp.ticket.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Android itg 8 on 1/4/2018.
 */

public class TempStatusModel {

    @SerializedName("pkid")
    private int pkid;
    @SerializedName("Assignedpersonname")
    private String Assignedpersonname;
    @SerializedName("Status")
    private int Status;

    public TempStatusModel() {
    }

    public int getPkid() {
        return pkid;
    }

    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    public String getAssignedpersonname() {
        return Assignedpersonname;
    }

    public void setAssignedpersonname(String assignedpersonname) {
        Assignedpersonname = assignedpersonname;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }


}
