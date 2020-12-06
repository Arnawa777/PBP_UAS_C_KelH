package com.arnawajuan.tubes_uts;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("user")
    Call<UserResponse> getAllUser();

    @POST("login")
    @FormUrlEncoded
    Call<UserResponse> loginUser(@Field("email") String email,
                                 @Field("password") String password);

    @POST("register")
    @FormUrlEncoded
    Call<UserResponse> register(@Field("name") String name,
                                  @Field("email") String email,
                                  @Field("phone_number") String phone_number,
                                  @Field("password") String password);

    @POST("user/update/{id}")
    @FormUrlEncoded
    Call<UserResponse> updateUser(@Path("id")String id,
                                  @Field("name") String name,
                                  @Field("email") String email,
                                  @Field("phone_number") String phone_number,
                                  @Field("password") String password);
    @POST("user/delete/{id}")
    Call<UserResponse> deleteUser(@Path("id")String id);
}
