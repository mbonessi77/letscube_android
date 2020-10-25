package com.example.letscube.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
    @SerializedName("class") private String myClass;
    @SerializedName("url") private String url;
    @SerializedName("id") private String id;
    @SerializedName("wca_id") private String wcaId;
    @SerializedName("name") private String name;
    @SerializedName("gender") private char gender;
    @SerializedName("country_iso2") private String country;
    @SerializedName("delegate_status") private String delegateStatus;
    @SerializedName("created_at") private String createdAt;
    @SerializedName("updated_at") private String updatedAt;
    @SerializedName("avatar") private Avatar avatar;

    protected User(Parcel in) {
        myClass = in.readString();
        url = in.readString();
        id = in.readString();
        wcaId = in.readString();
        name = in.readString();
        gender = (char) in.readInt();
        country = in.readString();
        delegateStatus = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        avatar = in.readParcelable(Avatar.class.getClassLoader());
    }

    public User(String name)
    {
        this.name = name;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getMyClass() {
        return myClass;
    }

    public void setMyClass(String myClass) {
        this.myClass = myClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWcaId() {
        return wcaId;
    }

    public void setWcaId(String wcaId) {
        this.wcaId = wcaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDelegateStatus() {
        return delegateStatus;
    }

    public void setDelegateStatus(String delegateStatus) {
        this.delegateStatus = delegateStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(myClass);
        dest.writeString(url);
        dest.writeString(id);
        dest.writeString(wcaId);
        dest.writeString(name);
        dest.writeInt((int) gender);
        dest.writeString(country);
        dest.writeString(delegateStatus);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeParcelable(avatar, flags);
    }
}
