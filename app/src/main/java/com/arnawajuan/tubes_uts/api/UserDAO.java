package com.arnawajuan.tubes_uts.api;

import com.google.gson.annotations.SerializedName;

public class UserDAO {
    @SerializedName("nama")
    private String nama;

    @SerializedName("email")
    private String email;

    @SerializedName("phone_number")
    private String phone_number;

    @SerializedName("password")
    private String password;

    public UserDAO(String nama,String email,String phone_number, String password){
        this.nama=nama;
        this.email=email;
        this.phone_number=phone_number;
        this.password=password;
    }
    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNim(String email) {
        this.email = email;
    }

    public void setProdi(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
