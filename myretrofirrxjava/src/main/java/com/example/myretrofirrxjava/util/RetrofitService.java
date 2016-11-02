package com.example.myretrofirrxjava.util;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by ${lishu} on 2016/10/31.
 */

public interface RetrofitService {
 //发送get请求
    @GET("/microservice/weather?citypinyin=beijing")
    Call<WeatherBean>getWeather();

    @GET("login")
    Observable<User> login(@Path("mobile") String mobile, @Path("smsvcode") String smsvcode);

}

