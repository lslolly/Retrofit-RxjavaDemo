package com.example.myretrofirrxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.myretrofirrxjava.util.RetrofitService;
import com.example.myretrofirrxjava.util.User;
import com.example.myretrofirrxjava.util.WeatherBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button btn_login;
    private TextView tv_result;
    private RetrofitService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result= (TextView) findViewById(R.id.tv_result);
        btn_login= (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ls","哈哈哈哈哈啊哈哈哈错误的。。。。");

                getData1();

            }
        });
    }

    private void getData1() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.foo.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        RetrofitService loginService = retrofit.create(RetrofitService .class);

        loginService.login("1234567890123", "789")
        .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        // 这里好像没啥用处
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 在这里处理异常
                        Log.e("ls","哈哈哈哈哈啊哈哈哈错误的。。。。");
                    }

                    @Override
                    public void onNext(User user) {
                        // 在这里处理接口返回的数据
                        Log.e("ls","哈哈哈哈哈啊哈哈哈错误的。。。。"+user.getNickName());

                        tv_result.setText("名字是："+user.getNickName());
                    }
                });


    }

    private void getData() {

        String baseUrl = "http://apistore.baidu.com/";
        //http://op.juhe.cn/onebox/weather/query

        //创建一个retrofit
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //利用接口创建一个Call对象
        Call<WeatherBean>weatherBeanCall = service.getWeather();
        //请求入队，回调请求成功或者失败
        weatherBeanCall.enqueue(new Callback<WeatherBean>() {
            @Override
            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {

                WeatherBean jo = response.body();
                Log.i("ls--","请求成功："+response.body().getRetData().getWeather());
                tv_result.setText("请求成功，当前天气为："+response.body().getRetData().getWeather());

            }

            @Override
            public void onFailure(Call<WeatherBean> call, Throwable t) {
                Log.i("ls--","请求失败！");
                tv_result.setText("请求失败！"+t.toString());

            }
        });

    }




}
