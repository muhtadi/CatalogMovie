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

    String detailBackdrop, detailTitle, detailRelease, detailOverview, backdropUrl;
    Double detailVote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ivBackdrop = (ImageView)findViewById(R.id.iv_backdrop);
        tvTitleDetail = (TextView) findViewById(R.id.tv_title_detail);
        tvVoteDetail = (TextView) findViewById(R.id.tv_vote_detail);
        tvReleaseDetail = (TextView) findViewById(R.id.tv_release_detail);
        tvOverview = (TextView) findViewById(R.id.tv_overview);

        Intent movieDetailIntent = new Intent();
        backdropUrl = movieDetailIntent.getStringExtra("bcakdrop");
        detailBackdrop = imageUrl+backdropUrl;
        detailTitle = movieDetailIntent.getStringExtra("title");
        detailVote = movieDetailIntent.getDoubleExtra("vote",0.0);
        detailRelease = movieDetailIntent.getStringExtra("release");
        detailOverview = movieDetailIntent.getStringExtra("overview");

        Glide.with(this).load(detailBackdrop).into(ivBackdrop);
        tvTitleDetail.setText(detailTitle);
        tvVoteDetail.setText(String.valueOf(detailVote));
        tvReleaseDetail.setText(detailRelease);
        tvOverview.setText(detailOverview);
    }
}
