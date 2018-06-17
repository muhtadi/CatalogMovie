package com.example.muhtadi.catalogmovieproject;

import android.content.Context;
import android.content.Intent;
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
    private RecyclerViewItemClickListener recyclerViewItemClickListener;
    private String url_image = "http://image.tmdb.org/t/p/w342/";

    public MovieAdapter(List<MoviePOJO.ResultArray> movieListResult, int tampilanList, Context context) {
        this.movieListResult = movieListResult;
        this.tampilanList = tampilanList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(tampilanList, parent, false);
        final mViewHolder mviewHolder = new mViewHolder(view);
        mviewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = mviewHolder.getAdapterPosition();
                if (adapterPosition != RecyclerView.NO_POSITION){
                    recyclerViewItemClickListener.onItemClick(adapterPosition, mviewHolder.itemView);
                }else{

                }
            }
        });
        return mviewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final MoviePOJO.ResultArray resultArray = movieListResult.get(position);
        final MovieAdapter.mViewHolder mviewHolder = (MovieAdapter.mViewHolder) holder;
        final String sourcePoster = resultArray.getPoster_path();
        final String posterImage = url_image+sourcePoster;

        Glide
                .with(context)
                .load(posterImage)
                .centerCrop()
                .override(250, 350)
                .placeholder(R.drawable.ic_launcher_background)
                .into(mviewHolder.imageView);

        mviewHolder.tvTitle.setText(resultArray.getOriginal_title());
        mviewHolder.tvVote.setText(String.valueOf(resultArray.getVote_average()));
        mviewHolder.tvRelease.setText(resultArray.getRelease_date());

        mviewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detail = new Intent(v.getContext(), MovieDetailActivity.class);
                detail.putExtra("backdrop", resultArray.getBackdrop_path());
                detail.putExtra("title", resultArray.getOriginal_title());
                detail.putExtra("vote", resultArray.getVote_average());
                detail.putExtra("release", resultArray.getRelease_date());
                detail.putExtra("overview", resultArray.getOverview());
                v.getContext().startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieListResult == null ? 0: movieListResult.size();
    }



    static class mViewHolder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        ImageView imageView;
        TextView tvTitle, tvVote, tvRelease;

        public mViewHolder(View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.ll_list);
            imageView = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvVote = itemView.findViewById(R.id.tv_vote);
            tvRelease = itemView.findViewById(R.id.tv_release);
        }
    }
}