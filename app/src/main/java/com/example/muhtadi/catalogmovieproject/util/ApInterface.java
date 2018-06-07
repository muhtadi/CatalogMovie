package com.example.muhtadi.catalogmovieproject.util;

import com.example.muhtadi.catalogmovieproject.MoviePOJO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApInterface {

    @GET("/3/search/movie?api_key=4e17eef1be3fae7b86e3a11965a36421")
    Call<MoviePOJO> getMovieItems (
            @Query("query") String name_movie
    );

}
