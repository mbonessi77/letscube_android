package com.example.letscube.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Avatar implements Parcelable {
    @SerializedName("url") private String url;
    @SerializedName("thumb_url") private String thumbUrl;
    @SerializedName("is_default") private boolean isDefault;

    protected Avatar(Parcel in) {
        url = in.readString();
        thumbUrl = in.readString();
        isDefault = in.readByte() != 0;
    }

    public static final Creator<Avatar> CREATOR = new Creator<Avatar>() {
        @Override
        public Avatar createFromParcel(Parcel in) {
            return new Avatar(in);
        }

        @Override
        public Avatar[] newArray(int size) {
            return new Avatar[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeString(thumbUrl);
        dest.writeByte((byte) (isDefault ? 1 : 0));
    }
}
