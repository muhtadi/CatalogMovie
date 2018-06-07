package com.example.muhtadi.catalogmovieproject.util;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static String URL = "https://api.themoviedb.org";

    public static Retrofit getRetrofit(Context context){

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
