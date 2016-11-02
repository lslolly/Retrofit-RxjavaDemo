package com.example.my2rdemo.service;

import com.example.my2rdemo.entity.LoginModel;
import com.example.my2rdemo.entity.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ${lishu} on 2016/11/1.
 */

public interface LoginService {
    @GET("/users/{user}")
    Call<LoginModel>login(@Path("user") String user);


    Call<LoginModel> post (@Body User user);
}
