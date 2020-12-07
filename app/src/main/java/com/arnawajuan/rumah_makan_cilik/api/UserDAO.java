package com.arnawajuan.rumah_makan_cilik.api;

import com.google.gson.annotations.SerializedName;

public class UserDAO {
    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public UserDAO(int id, String name, String phone_number, String email, String password) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
