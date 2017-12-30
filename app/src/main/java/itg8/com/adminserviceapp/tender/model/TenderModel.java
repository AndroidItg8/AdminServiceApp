package itg8.com.adminserviceapp.tender.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Android itg 8 on 12/18/2017.
 */

public class TenderModel implements Parcelable {
     private String id;

    public List<TenderDocumentModel> getList() {
        return list;
    }

    public void setList(List<TenderDocumentModel> list) {
        this.list = list;
    }

    private List<TenderDocumentModel> list;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    private boolean isChecked;

    public TenderModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
        dest.writeString(this.name);
    }

    protected TenderModel(Parcel in) {
        this.id = in.readString();
        this.isChecked = in.readByte() != 0;
        this.name = in.readString();
    }

    public static final Creator<TenderModel> CREATOR = new Creator<TenderModel>() {
        @Override
        public TenderModel createFromParcel(Parcel source) {
            return new TenderModel(source);
        }

        @Override
        public TenderModel[] newArray(int size) {
            return new TenderModel[size];
        }
    };
}
