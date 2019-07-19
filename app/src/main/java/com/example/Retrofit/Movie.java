package com.example.Retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {
    @SerializedName(("id"))
    private Integer id;
    @SerializedName("title")
    private String title;

    public Movie(Integer ids,String title)
    {
        this.id = ids;
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
