package com.example.administrator.myrxjavaexzmple;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;

/**
 * 关于Rxjava的同步理解和代码用例
 */
public class MainActivity extends AppCompatActivity {

    private  String[]names={"小乔","鲁班七号","杨戬","嬴政","老夫子","雅典娜","后羿","牛魔","姜子牙","甄姬","安琪拉"};

    int drables=R.drawable.gm3_detail_bg4;
    private Button btn;
    private TextView tv;
    private Button btn2;
    private Button bt_to2main;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);
        btn2 = (Button) findViewById(R.id.btn2);
        tv = (TextView) findViewById(R.id.tv);
        bt_to2main= (Button) findViewById(R.id.bt_to2main);
        bt_to2main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
                finish();
            }
        });
        imageView = (ImageView) findViewById(R.id.imge);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("ls", "开始创建被观察者");
                //创建observable
               Observable.create(new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        subscriber.onNext("hello world");
                        subscriber.onCompleted();
                    }
                }).subscribe(new Observer<String>() {
                   @Override
                   public void onCompleted() {

                   }

                   @Override
                   public void onError(Throwable e) {

                   }

                   @Override
                   public void onNext(String s) {

                       tv.setText(s);

                   }
               });

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable.just("HELLO,LISHU").subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tv.setText(s);
                    }
                });
//                Observable.create(new Observable.OnSubscribe<Drawable>() {
//                    @Override
//                    public void call(Subscriber<? super Drawable> subscriber) {
//                        Drawable drawable = getTheme().getDrawable(drables);
//                        subscriber.onNext(drawable);
//                        subscriber.onCompleted();
//                    }
//                }).subscribe(new Observer<Drawable>() {
//                    @Override
//                    public void onNext(Drawable drawable) {
//                        imageView.setImageDrawable(drawable);
//                    }
//
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(MainActivity.this, "Error!", Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        });
    }
}