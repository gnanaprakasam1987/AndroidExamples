package com.example.RetrofitWithMVP.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.RetrofitWithMVP.Model.GetMovieIntractorImpl;
import com.example.RetrofitWithMVP.Adapter.MovieDetailAdapter;
import com.example.RetrofitWithMVP.Presenter.MovieDetailBO;
import com.example.RetrofitWithMVP.Presenter.MovieDetailContract;
import com.example.RetrofitWithMVP.Presenter.MovieDetailPresenterImpl;
import com.example.gnanaprakasamd.androidexamples.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoiveDetailActivity extends AppCompatActivity implements MovieDetailContract.MovieView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private static final String TAG = MoiveDetailActivity.class.getSimpleName();


    private MovieDetailPresenterImpl movieDetailPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moive_detail);
        ButterKnife.bind(this);

        movieDetailPresenter = new MovieDetailPresenterImpl(this, new GetMovieIntractorImpl());
        movieDetailPresenter.setView(this);
        movieDetailPresenter.downloadMovieDetailsFromServer();


    }

    @Override
    public void showProgressDialog() {

    }

    @Override
    public void loadAdapterData(List<MovieDetailBO> moveList) {
        if(moveList != null && moveList.size()> 0) {
            MovieDetailAdapter adapter = new MovieDetailAdapter(this, moveList);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showErrorMessage(Throwable errorMessage) {
        Toast.makeText(this,errorMessage.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
