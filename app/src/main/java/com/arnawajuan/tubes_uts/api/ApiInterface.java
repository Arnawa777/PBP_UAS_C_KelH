package com.arnawajuan.tubes_uts.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("register")
    @FormUrlEncoded
    Call<UserResponse> register(@Field("nama") String nama,
                                @Field("email") String email,
                                @Field("phone_number") String phone_number,
                                @Field("password") String password);

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> login(@Field("email") String email,
                             @Field("password") String password);
}
