package com.example.RetrofitWithMVP.Presenter;

import android.content.Context;

import java.util.List;

public class MovieDetailPresenterImpl implements MovieDetailContract.MoviePresenter, MovieDetailContract.MovieDetailIntractor.onFinisherListener {

    private Context mContext;
    private MovieDetailContract.MovieView movieView;
    private MovieDetailContract.MovieDetailIntractor movieDetailIntractor;

    public MovieDetailPresenterImpl(Context context, MovieDetailContract.MovieDetailIntractor movieIntractor) {
        this.mContext = context;
        this.movieDetailIntractor = movieIntractor;

    }

    @Override
    public void setView(MovieDetailContract.MovieView movieView) {
        this.movieView = movieView;
    }

    @Override
    public void downloadMovieDetailsFromServer() {
        movieDetailIntractor.getMovieDetailList(this);
    }


    @Override
    public void onFinishedListerner(List<MovieDetailBO> movieList) {

        if (movieView != null) {
            movieView.loadAdapterData(movieList);
        }
    }

    @Override
    public void onFailure(Throwable e) {
        if (movieView != null) {
            movieView.showErrorMessage(e);
        }
    }
}
