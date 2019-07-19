package com.example.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("movie/top_rated")
    Call<MoveResponse> getMoviesList(@Query("api_key") String apiKey);

}
