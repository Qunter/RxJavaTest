package com.example.administrator.rxjavatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 下一步以ImageView测试
 */
public class MainActivity extends AppCompatActivity {
    private String TAG="";
    private ImageView mImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建被观测者 Observable：
        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        });
        //创建观测者 Observer
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(TAG, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error");
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "complete");
            }
        };
        //建立观测者observable与被观测者observer之间连接
        observable.subscribe(observer);
    }
}
