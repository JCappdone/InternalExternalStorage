package com.example.shriji.app60;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

/**
 * Created by shriji on 19/3/18.
 */

public class DataModel implements Parcelable {

    private String mId;
    private String mName;
    private String mImage;

    public DataModel() {
    }

    public DataModel(String mId, String mName, String mImage) {
        if (mId == null) {
            mId = UUID.randomUUID().toString();
        }
        this.mId = mId;
        this.mName = mName;
        this.mImage = mImage;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImage() {
        return mImage;
    }

    public void setmImage(String mImage) {
        this.mImage = mImage;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mId);
        dest.writeString(this.mName);
        dest.writeString(this.mImage);
    }

    protected DataModel(Parcel in) {
        this.mId = in.readString();
        this.mName = in.readString();
        this.mImage = in.readString();
    }

    public static final Parcelable.Creator<DataModel> CREATOR = new Parcelable.Creator<DataModel>() {
        @Override
        public DataModel createFromParcel(Parcel source) {
            return new DataModel(source);
        }

        @Override
        public DataModel[] newArray(int size) {
            return new DataModel[size];
        }
    };

    @Override
    public String toString() {
        return "DataModel{" +
                "mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mImage='" + mImage + '\'' +
                '}';
    }
}
