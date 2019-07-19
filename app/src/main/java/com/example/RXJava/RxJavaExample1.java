package com.example.RXJava;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gnanaprakasamd.androidexamples.R;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RxJavaExample1 extends AppCompatActivity {

    private static final String TAG = RxJavaExample1.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java_example1);

        // observable
        Observable<String> animalsObservable = getAnimalsObservable();

        // observer
        Observer<String> animalsObserver = getAnimalsObserver();

        // observer subscribing to observable
        animalsObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);

    }

    private Observable<String> getName()
    {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {

            }
        });
    }
    private Observer<String> getAnimalsObserver() {
        return new Observer<String>() {


            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "Name: " + s);
            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }


    private Observable<String> getAnimalsObservable() {
        return Observable.just("Ant", "Bee", "Cat", "Dog", "Fox");
    }

}
