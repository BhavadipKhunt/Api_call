package com.example.login_api.DataModels;

import com.example.login_api.inter_face.Api_interface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retro_class {
    public  static Api_interface callapi(){
        Retrofit retrofit=new Retrofit.Builder()
                 .baseUrl("https://bhavadipandroid.000webhostapp.com/android/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
        Api_interface api_interface = retrofit.create(Api_interface.class);
        return api_interface;
    }
}
