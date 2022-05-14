package com.example.cinema_mobile_app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import com.example.cinema_mobile_app.components.MovieItem;
import com.example.cinema_mobile_app.components.reyclerAdapter.MovieItemAdapter;
import com.example.cinema_mobile_app.services.cinema_server.schedule.Schedule;
import com.example.cinema_mobile_app.utils.VolleyResponseListener;

import org.json.JSONArray;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private ArrayList<MovieItem> moviesList;
    RecyclerView movieRecyclerView;
    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesList = new ArrayList<>(moviesList);
        movieRecyclerView = findViewById(R.id.one_movie_item);
        fetchSchedule();
        setMovieAdapter();
    }

    private void setMovieAdapter() {
        MovieItemAdapter movieItemAdapter = new MovieItemAdapter(moviesList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        movieRecyclerView.setLayoutManager(layoutManager);
        movieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        movieRecyclerView.setAdapter(movieItemAdapter);
    }

    private void fetchSchedule() {
        moviesList.add(new MovieItem(
                "Doctor Strange in the Multiverse of Madness",
                "16:00",
                "https://m.media-amazon.com/images/M/MV5BNWM0ZGJlMzMtZmYwMi00NzI3LTgzMzMtNjMzNjliNDRmZmFlXkEyXkFqcGdeQXVyMTM1MTE1NDMx._V1_.jpg"
        ));
        moviesList.add(new MovieItem(
                "Fantastic Beasts: The Secrets of Dumbledore",
                "12:00",
                "https://m.media-amazon.com/images/M/MV5BZGQ1NjQyNDMtNzFlZS00ZGIzLTliMWUtNGJkMGMzNTBjNDg0XkEyXkFqcGdeQXVyMTE1NDI5MDQx._V1_.jpg"
        ));
    }



}