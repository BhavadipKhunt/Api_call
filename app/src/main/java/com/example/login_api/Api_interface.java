package com.example.login_api;

import com.example.login_api.DataModels.LoginData;
import com.example.login_api.DataModels.ProductData;
import com.example.login_api.DataModels.RegisterData;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_interface {
    @FormUrlEncoded
    @POST("register.php")
    Call<RegisterData> REGISTER_DATA_CALL(@Field("name") String name, @Field("email")String email, @Field("password")String password);
    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData>LOGINMODEL_CALL(@Field("email") String email, @Field("password") String password);
    @FormUrlEncoded
    @POST("addProduct.php")
    Call<ProductData>PRODUCT_DATA_CALL(@Field("userid") Integer uid, @Field("pname") String pname, @Field("pprize") String price, @Field("pdes")String decription, @Field("productimage")String image);
}
