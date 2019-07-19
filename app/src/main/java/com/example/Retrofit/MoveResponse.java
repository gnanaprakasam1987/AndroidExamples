package com.example.Retrofit;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoveResponse {


    @SerializedName("results")
    private List<Movie> results = null;


    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }
}
