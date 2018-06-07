package com.example.muhtadi.catalogmovieproject;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MoviePOJO.ResultArray> movieListResult;
    private int tampilanList;
    private Context context;

    public MovieAdapter(List<MoviePOJO.ResultArray> movieListResult, int tampilanList, Context context) {
        this.movieListResult = movieListResult;
        this.tampilanList = tampilanList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(tampilanList, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = viewHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION){
                    onItemClick(adapterPosition, viewHolder.itemView);
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final MoviePOJO.ResultArray resultArray = movieListResult.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        String sourcePoster = resultArray.getPoster_path();
        String posterImage = "http://image.tmdb.org/t/p/w185/"+sourcePoster;

        Glide.with(context).load(posterImage).override(250, 350).into(viewHolder.imageView);
        viewHolder.tvTitle.setText(resultArray.getOriginal_title());
        viewHolder.tvVote.setText((int) resultArray.getVote_average());
        viewHolder.tvRelease.setText(resultArray.getRelease_date());

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(v.getContext(), MovieDetailActivity.class);
                detail.putExtra("backdrop", resultArray.getBackdrop_path());
                detail.putExtra("title", resultArray.getOriginal_title());
                detail.putExtra("vote", resultArray.getVote_average());
                detail.putExtra("release", resultArray.getRelease_date());
                detail.putExtra("overview", resultArray.getOverview());
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    void onItemClick(int position, View view) {

    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        ImageView imageView;
        TextView tvTitle, tvVote, tvRelease;

        public ViewHolder(View itemView) {
            super(itemView);

            linearLayout = (LinearLayout)itemView.findViewById(R.id.ll_list);
            imageView = (ImageView)itemView.findViewById(R.id.iv_poster);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_title);
            tvVote = (TextView)itemView.findViewById(R.id.tv_vote);
            tvRelease = (TextView)itemView.findViewById(R.id.tv_release);
        }
    }
}