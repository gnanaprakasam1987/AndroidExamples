package com.example.RetrofitWithMVP.APICall;

import com.example.RetrofitWithMVP.Presenter.MovieDetailBO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/photos")
    Call<List<MovieDetailBO>> getMovieList();


}
