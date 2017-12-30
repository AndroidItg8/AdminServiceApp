package itg8.com.adminserviceapp.tender.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Android itg 8 on 12/22/2017.
 */

public class TenderDocumentModel implements Parcelable {
     private String id;
     private String name;
      private boolean isChecked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeByte(this.isChecked ? (byte) 1 : (byte) 0);
    }

    public TenderDocumentModel() {
    }

    protected TenderDocumentModel(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.isChecked = in.readByte() != 0;
    }

    public static final Parcelable.Creator<TenderDocumentModel> CREATOR = new Parcelable.Creator<TenderDocumentModel>() {
        @Override
        public TenderDocumentModel createFromParcel(Parcel source) {
            return new TenderDocumentModel(source);
        }

        @Override
        public TenderDocumentModel[] newArray(int size) {
            return new TenderDocumentModel[size];
        }
    };
}
