package com.example.my2rdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.my2rdemo.entity.LoginModel;
import com.example.my2rdemo.entity.PhoneModel;
import com.example.my2rdemo.entity.User;
import com.example.my2rdemo.service.LoginService;
import com.example.my2rdemo.service.PhoneService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_click;
    private TextView tv_result;
    private TextView tv_result1;
    private EditText et_num;

    private String etn_num;

    private  static  final String BASE_URL="http://apis.baidu.com";
    private  static  final  String APIKEY="8e13586b86e4b7f3758ba3bd6c9c9135";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_click = (Button) findViewById(R.id.btn_click);
        tv_result = (TextView) findViewById(R.id.tv_click_result);
        et_num= (EditText) findViewById(R.id.et_phone);
        tv_result1 = (TextView) findViewById(R.id.tv_click_result1);
        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                getData1();
//                getData2();
                getData3();






            }
        });
    }

    private void getData3() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        etn_num =et_num.getText().toString().trim();
        if(etn_num.isEmpty()||"".equals(etn_num)){

            et_num.setError(Html.fromHtml("<font color=#ff0000>" + "请输入手机号" + "</font>"));

        }
        PhoneService service= retrofit.create(PhoneService.class);
        Call<PhoneModel>phone = service.getResult(APIKEY,etn_num);
        phone.enqueue(new Callback<PhoneModel>() {
            @Override
            public void onResponse(Call<PhoneModel> call, Response<PhoneModel> response) {
                if(response.isSuccessful()){
                   PhoneModel model = response.body();
                    if(model!=null){
                        PhoneModel.RetDataBean bean = model.getRetData();
                        tv_result.setText("电话号码是："+bean.getPhone()+"省份是："+bean.getProvince()+"市："+bean.getCity());
                        Toast.makeText(MainActivity.this,"bean = "+bean,Toast.LENGTH_LONG).show();
                        Log.i("ls","bean ="+bean);
                    }
                }
            }

            @Override
            public void onFailure(Call<PhoneModel> call, Throwable t) {

            }
        });


    }

    private void getData2() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService service = retrofit.create(LoginService.class);
        Call<LoginModel>
                model =service.post(new User("lolly","111111"));
        model.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                Toast.makeText(MainActivity.this,"请求正确的结果是："+response.body().getLogin(),Toast.LENGTH_LONG).show();
                tv_result1.setText("请求结果为："+response.body().getLogin());


            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                Toast.makeText(MainActivity.this,"请求错误的结果是："+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });





    }

    private void getData1() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginService service = retrofit.create(LoginService.class);
        Call<LoginModel> modelCall = service.login("lslolly");
        modelCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                Toast.makeText(MainActivity.this,"请求正确的结果是："+response.body().getLogin(),Toast.LENGTH_LONG).show();
                tv_result.setText("请求结果为："+response.body().getLogin());
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(MainActivity.this,"请求错误的结果是："+t.getMessage(),Toast.LENGTH_LONG).show();

            }
        });
    }
}
