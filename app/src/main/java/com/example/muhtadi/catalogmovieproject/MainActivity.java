package com.example.muhtadi.catalogmovieproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.muhtadi.catalogmovieproject.util.ApInterface;
import com.example.muhtadi.catalogmovieproject.util.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.et_cari);
        button = (Button)findViewById(R.id.btn_cari);
        progressBar = (ProgressBar)findViewById(R.id.pg_waiting);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyMovie();
            }
        });
    }

    public void MyMovie() {

        String cariMovie = editText.getText().toString();
        progressBar.setVisibility(View.VISIBLE);

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.rv_hasil);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        ApInterface apInterface = ApiClient.getRetrofit(getApplicationContext()).create(ApInterface.class);
        Call<MoviePOJO> call = apInterface.getMovieItems(cariMovie);

        call.enqueue(new Callback<MoviePOJO>() {
            @Override
            public void onResponse(Call<MoviePOJO> call, Response<MoviePOJO> response) {
                MoviePOJO data = response.body();
                //Log.d("test", "MyMovie: sampe sini bisa ");
                if (data.getResult().size()==0) {
                    Toast.makeText(getApplicationContext(), "maaf data yang anda cari tidak ditemukan", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }else {
                    recyclerView.setAdapter(new MovieAdapter(data.getResult(), R.layout.movie_list_item, getApplicationContext()));
                    //Log.e(TAG, "onResponse: hasil pemanggilan"+ call);
                    progressBar.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<MoviePOJO> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Maaf, terjadi kesalahan", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }
}
