package com.example.DaggerExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.DaggerExample.BO.UserMasterBO;
import com.example.gnanaprakasamd.androidexamples.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DaggerExampleActivity extends AppCompatActivity {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private static final String TAG = DaggerExampleActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger_example);


        Observable<ArrayList<UserMasterBO>> userList = downloadUserList();

        compositeDisposable.add((Disposable) downloadUserList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getUserObserver()));
    }

    private DisposableObserver<ArrayList<UserMasterBO>> getUserObserver() {
        return new DisposableObserver<ArrayList<UserMasterBO>>() {

            @Override
            public void onNext(ArrayList<UserMasterBO> s) {
                Log.d(TAG, "Name: " + s.size());
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


    private Observable<ArrayList<UserMasterBO>>  downloadUserList()
    {
        ArrayList<UserMasterBO> userMasterBOArrayList = new ArrayList<>();
        UserMasterBO userMasterBO = new UserMasterBO();

        userMasterBO.setmUserName("GP");
        userMasterBO.setmPassword("welcome123");

        userMasterBOArrayList.add(userMasterBO);

        UserMasterBO userMasterBO1 = new UserMasterBO();

        userMasterBO1.setmUserName("Android");
        userMasterBO1.setmPassword("welcome123");

        userMasterBOArrayList.add(userMasterBO1);


        return  Observable.fromArray(userMasterBOArrayList);
    }

}
