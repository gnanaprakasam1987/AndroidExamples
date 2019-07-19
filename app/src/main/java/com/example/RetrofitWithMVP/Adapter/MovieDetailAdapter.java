package com.example.RetrofitWithMVP.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.RetrofitWithMVP.Presenter.MovieDetailBO;
import com.example.gnanaprakasamd.androidexamples.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MyViewHolder> {

    private List<MovieDetailBO> dataList;
    private Context context;

    public MovieDetailAdapter(Context context,List<MovieDetailBO> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {

        @BindView(R.id.tv_title)TextView tvTitle;
        @BindView(R.id.media_image)
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_movie_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MovieDetailBO movieDetailBO = dataList.get(position);
        holder.tvTitle.setText(movieDetailBO.getTitle());

        Glide.with(context).load(movieDetailBO.getUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
