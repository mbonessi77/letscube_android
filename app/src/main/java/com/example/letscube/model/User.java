package com.example.letscube.model;

import com.google.gson.annotations.SerializedName;

public class User {
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
    @SerializedName("avatar") private model.Avatar avatar;

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

    public model.Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(model.Avatar avatar) {
        this.avatar = avatar;
    }
}
