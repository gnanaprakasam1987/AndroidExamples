package com.example.Retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.gnanaprakasamd.androidexamples.R;

import java.lang.annotation.RetentionPolicy;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitExample extends AppCompatActivity {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public static final String API_KEY = "12345678910111213";
    private static final String TAG = RetrofitExample.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_example);

        //Retrofit Instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        Call<MoveResponse> moveResponseCall =apiInterface.getMoviesList(API_KEY);
        moveResponseCall.enqueue(new Callback<MoveResponse>() {
            @Override
            public void onResponse(Call<MoveResponse> call, Response<MoveResponse> response) {
//                if (response.body().getResults() != null) {
              //      List<Movie> movies = response.body().getResults();
                    Log.d(TAG, "Number of movies received: ");
              //  }

            }

            @Override
            public void onFailure(Call<MoveResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });


    }
}
