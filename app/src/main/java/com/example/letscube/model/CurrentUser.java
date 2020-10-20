package com.example.letscube.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class CurrentUser implements Parcelable {
    @SerializedName("me") User user;

    protected CurrentUser(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<CurrentUser> CREATOR = new Creator<CurrentUser>() {
        @Override
        public CurrentUser createFromParcel(Parcel in) {
            return new CurrentUser(in);
        }

        @Override
        public CurrentUser[] newArray(int size) {
            return new CurrentUser[size];
        }
    };

    public User getUser() {
        return user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
    }
}
