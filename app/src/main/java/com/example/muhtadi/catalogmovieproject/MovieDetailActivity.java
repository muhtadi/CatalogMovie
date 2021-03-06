package com.example.muhtadi.catalogmovieproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;

import org.w3c.dom.Text;

public class MovieDetailActivity extends AppCompatActivity {

    ImageView ivBackdrop;
    TextView tvTitleDetail, tvVoteDetail, tvReleaseDetail, tvOverview;
    String imageUrl = "http://image.tmdb.org/t/p/w342/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ivBackdrop = findViewById(R.id.iv_backdrop);
        tvTitleDetail = findViewById(R.id.tv_title_detail);
        tvVoteDetail = findViewById(R.id.tv_vote_detail);
        tvReleaseDetail = findViewById(R.id.tv_release_detail);
        tvOverview = findViewById(R.id.tv_overview);

        Intent movieDetailIntent = getIntent();
        final String backdropUrl = movieDetailIntent.getStringExtra("backdrop");
        final String detailBackdrop = imageUrl+backdropUrl;
        final String detailTitle = movieDetailIntent.getStringExtra("title");
        final Double detailVote = movieDetailIntent.getDoubleExtra("vote",0.0);
        final String detailRelease = movieDetailIntent.getStringExtra("release");
        final String detailOverview = movieDetailIntent.getStringExtra("overview");

        Glide.with(this).load(detailBackdrop).placeholder(R.drawable.ic_broken_image_black_24dp).into(ivBackdrop);
        tvTitleDetail.setText(detailTitle);
        tvVoteDetail.setText(String.valueOf(detailVote));
        tvReleaseDetail.setText(detailRelease);
        tvOverview.setText(detailOverview);
    }
}
