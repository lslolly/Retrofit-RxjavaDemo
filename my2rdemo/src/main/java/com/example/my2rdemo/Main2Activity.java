package com.example.my2rdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.my2rdemo.entity.PhoneModel;
import com.example.my2rdemo.service.PhoneResult;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.my2rdemo.R.id.btn2_click;

public class Main2Activity extends AppCompatActivity {

    private Button bt2_click;
    private TextView tv2_result;
    private TextView tv2_result1;
    private EditText et2_num;

    private String etn2_num;

    private static final String BASE_URL2 = "http://apis.baidu.com";
    private static final String APIKEY2 = "8e13586b86e4b7f3758ba3bd6c9c9135";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bt2_click = (Button) findViewById(btn2_click);
        tv2_result = (TextView) findViewById(R.id.tv2_click_result);
        et2_num = (EditText) findViewById(R.id.et2_phone);
        tv2_result1 = (TextView) findViewById(R.id.tv2_click_result1);

        bt2_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL2)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                etn2_num =et2_num.getText().toString().trim();
                if(etn2_num.isEmpty()||"".equals(etn2_num)){

                    et2_num.setError(Html.fromHtml("<font color=#ff0000>" + "请输入手机号" + "</font>"));

                }
                PhoneResult result = retrofit.create(PhoneResult.class);
                result.getPhoneResult(APIKEY2,etn2_num)
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<PhoneModel>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(PhoneModel phoneModel) {
                                if(phoneModel!=null&&phoneModel.getErrNum()==0){
                                    PhoneModel.RetDataBean dataBean = phoneModel.getRetData();
                                    tv2_result.setText("检测到话号码为："+dataBean.getPhone());

                                    tv2_result1.setText("归属地是："+dataBean.getProvince()+dataBean.getCity());
                                }


//                                if (result != null && result.getErrNum() == 0) {
//                                    PhoneResult.RetDataEntity entity = result.getRetData();
//                                    resultView.append("地址：" + entity.getCity());
//                                }

                            }
                        });


            }
        });
    }
}
