package com.example.my2rdemo.service;

import com.example.my2rdemo.entity.PhoneModel;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ${lishu} on 2016/11/1.
 */

public interface PhoneResult {
    @GET("/apistore/mobilenumber/mobilenumber")
    Observable<PhoneModel>getPhoneResult(@Header("apikey")String apikey, @Query("phone")String phone);
}
