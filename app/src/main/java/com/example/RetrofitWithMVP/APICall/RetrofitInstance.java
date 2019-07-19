package com.example.RetrofitWithMVP.APICall;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {


    public static Retrofit retrofit;
    private static final String BASEURL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance()
    {
        if(retrofit == null)

        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }
}
