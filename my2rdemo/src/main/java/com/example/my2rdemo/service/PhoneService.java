package com.example.my2rdemo.service;

import com.example.my2rdemo.entity.PhoneModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by ${lishu} on 2016/11/1.
 */

public interface PhoneService {
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneModel>getResult(@Header("apikey") String apikey, @Query("phone") String phone);
}
