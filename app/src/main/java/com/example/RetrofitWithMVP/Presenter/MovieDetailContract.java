package com.example.RetrofitWithMVP.Presenter;

import java.util.List;

public class MovieDetailContract {

    public interface MovieView {
        void showProgressDialog();

        void loadAdapterData(List<MovieDetailBO> moveList);

        void showErrorMessage(Throwable throwable);


    }

    interface MoviePresenter {
        void setView(MovieView movieView);

        void downloadMovieDetailsFromServer();


    }

    public interface MovieDetailIntractor {

        interface onFinisherListener {
            void onFinishedListerner(List<MovieDetailBO> movieList);

            void onFailure(Throwable e);
        }

        void getMovieDetailList(onFinisherListener listener);

    }
}
