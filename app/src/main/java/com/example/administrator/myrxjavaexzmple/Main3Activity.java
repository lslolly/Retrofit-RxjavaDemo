package com.example.administrator.myrxjavaexzmple;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.myrxjavaexzmple.entity.Subject;
import com.example.administrator.myrxjavaexzmple.http.HttpMethods;
import com.example.administrator.myrxjavaexzmple.subscribers.ProgressSubscriber;
import com.example.administrator.myrxjavaexzmple.subscribers.SubscriberOnNextListener;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main3Activity extends AppCompatActivity {

    private Button click_me_BN;
    private TextView result_TV;
    private SubscriberOnNextListener getTopMovieOnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        click_me_BN= (Button) findViewById(R.id.click_me_BN);
        result_TV= (TextView) findViewById(R.id.result_TV);

        ButterKnife.bind(this);
        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>> (){
            @Override
            public void onNext(List<Subject> subjects) {
                result_TV.setText(subjects.toString());
            }
        };

    }
    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    //进行网络请求
    private void getMovie(){
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, Main3Activity.this), 0, 10);
    }
}
