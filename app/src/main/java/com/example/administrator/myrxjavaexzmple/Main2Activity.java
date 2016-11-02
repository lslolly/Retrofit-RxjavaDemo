package com.example.administrator.myrxjavaexzmple;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 关于Rxjava的异步理解和代码用例
 */
public class Main2Activity extends AppCompatActivity {

    private Button btn22;
    private  Button btn23;
    private ImageView imgyb;
    private int drawable1=R.drawable.app_logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imgyb = (ImageView) findViewById(R.id.imgyb);
        btn23= (Button) findViewById(R.id.btn_YB1);
        btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Observable.create(new Observable.OnSubscribe<Drawable>() {
                    @Override
                    public void call(Subscriber<? super Drawable> subscriber) {
                        Drawable drawab = getTheme().getDrawable(drawable1);
                        subscriber.onNext(drawab);
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(Main2Activity.this, "Error!", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNext(Drawable drawable) {

                        imgyb.setImageDrawable(drawable);
                    }
                })
                ;
            }
        });
        btn22= (Button) findViewById(R.id.btn_YB);
        btn22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Observable
                        .range(1, 3)
                        .map(new Func1<Integer, String>() {
                            @Override
                            public String call(Integer integer) {
                                return (integer % 2 == 0) ? "even" : "odd";
                            }
                        }).subscribe(new Action1<String>() {
                    @Override
                    public void call(String string) {
                        Log.i("ls---String====",string);
                    }
                });





//                Observable.just(1,2,3,4)
//                       .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Action1<Integer>() {
//                            @Override
//                            public void call(Integer numbers) {
//                                Log.i("thread========",Thread.currentThread().getName());
//                                Toast.makeText(Main2Activity.this,"numbers="+numbers,Toast.LENGTH_LONG).show();
//                            }
//                        });
            }
        });
    }
}
