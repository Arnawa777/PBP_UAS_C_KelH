package com.arnawajuan.rumah_makan_cilik.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserResponse {
    @SerializedName("user")
    private UserDAO user = null;

    @SerializedName("data")
    @Expose
    private List<UserDAO> users;

    @SerializedName("message")
    @Expose
    private String message;

    public List<UserDAO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDAO> users) {
        this.users = users;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public UserDAO getUser() {
        return user;
    }



}
