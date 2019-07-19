package com.example.RetrofitWithMVP.Model;

import com.example.RetrofitWithMVP.APICall.ApiInterface;
import com.example.RetrofitWithMVP.APICall.RetrofitInstance;
import com.example.RetrofitWithMVP.Presenter.MovieDetailBO;
import com.example.RetrofitWithMVP.Presenter.MovieDetailContract;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMovieIntractorImpl implements MovieDetailContract.MovieDetailIntractor {


    @Override
    public void getMovieDetailList(final onFinisherListener listener) {

        ApiInterface apiInterface = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);

        Call<List<MovieDetailBO>> list = apiInterface.getMovieList();

        list.enqueue(new Callback<List<MovieDetailBO>>() {
            @Override
            public void onResponse(Call<List<MovieDetailBO>> call, Response<List<MovieDetailBO>> response) {
                listener.onFinishedListerner(response.body());
            }

            @Override
            public void onFailure(Call<List<MovieDetailBO>> call, Throwable t) {
                listener.onFailure(t);

            }
        });
    }
}
