package com.example.login_api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_interface {
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterData> REGISTER_DATA_CALL(@Field("name") String name,@Field("email")String email,@Field("password")String password,@Field("confirmpassword")String confirmpassword);
}
