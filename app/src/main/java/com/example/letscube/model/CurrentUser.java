package com.example.letscube.model;

import com.google.gson.annotations.SerializedName;

public class CurrentUser {
    @SerializedName("me") User user;

    public User getUser() {
        return user;
    }
}
