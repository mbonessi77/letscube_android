package com.example.letscube.model;

import com.google.gson.annotations.SerializedName;

public class AccessToken {
    @SerializedName("access_token") private String token;
    @SerializedName("token_type") private String type;
    @SerializedName("expires_in") private int expiresIn;
    @SerializedName("refresh_token") private String refreshToken;
    @SerializedName("scope") private String scope;
    @SerializedName("created_at") private long createdAt;

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getScope() {
        return scope;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
