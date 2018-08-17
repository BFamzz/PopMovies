package com.example.bfamz.favouritemovies.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.example.bfamz.favouritemovies.R;
import com.example.bfamz.favouritemovies.data.VolleyRequests;
import com.example.bfamz.favouritemovies.model.Movie;
import com.example.bfamz.favouritemovies.utils.JsonUtils;
import com.example.bfamz.favouritemovies.utils.MovieAdapter;
import com.example.bfamz.favouritemovies.utils.NetworkUtils;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkUtils.AsyncResponse {

//    private static final String POPULAR_URL =
//            "https://api.themoviedb.org/3/movie/popular?api_key=282c193fdf9a5c0d3bf4eae003ef7b97&language=en-US&page=1";
//    private static final String TOP_RATED_URL =
//            "https://api.themoviedb.org/3/movie/top_rated?api_key=282c193fdf9a5c0d3bf4eae003ef7b97&language=en-US&page=1";
//
//    private static final int SORT_BY = 1;

    public MainActivity instance;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private NetworkUtils movieAsyncTask;
    private Movie mMovie;
    public MovieAdapter movieAdapter;
    public ArrayList<Movie> movies;

    public RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieAsyncTask = new NetworkUtils();
        movieAsyncTask.execute();
//        VolleyRequests.getMovies(instance);

        movies = new ArrayList<>();
        movies.add(mMovie);

        movieAdapter = new MovieAdapter(movies, this);

        recyclerView = findViewById(R.id.moviesRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void processFinish(Movie movie) {
        this.mMovie = movie;
    }
}
