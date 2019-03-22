package com.example.DaggerExample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.DaggerExample.BO.UserBO;
import com.example.gnanaprakasamd.androidexamples.R;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJavaExampleActivity extends AppCompatActivity {

    /* CompositeDisposable can maintain list to request in pool and while clear at once a time*/
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private static final String TAG = RxJavaExampleActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dragger_example);




        compositeDisposable.add((Disposable) downloadUserList()
                .subscribeOn(Schedulers.io()) // tells the observable to run the task in background thread
                .observeOn(AndroidSchedulers.mainThread()) //tells the observer to receive the data in UI thread, so that we can update UI
                .subscribeWith(getUserObserver()));
    }

    private DisposableObserver<ArrayList<UserBO>> getUserObserver() {
        return new DisposableObserver<ArrayList<UserBO>>() {

            @Override
            public void onNext(ArrayList<UserBO> s) {
                Log.d(TAG, "Name: " + s.size());
                for(UserBO userBO : s){
                    Log.d(TAG, "Name: " +userBO.getmUserName());
                }
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


    private Observable<ArrayList<UserBO>>  downloadUserList()
    {
        ArrayList<UserBO> userMasterBOArrayList = new ArrayList<>();
        UserBO userMasterBO = new UserBO();

        userMasterBO.setmUserName("GP");
        userMasterBO.setmPassword("welcome123");

        userMasterBOArrayList.add(userMasterBO);

        UserBO userMasterBO1 = new UserBO();

        userMasterBO1.setmUserName("Android");
        userMasterBO1.setmPassword("welcome123");

        userMasterBOArrayList.add(userMasterBO1);


        return  Observable.fromArray(userMasterBOArrayList);
    }

}
