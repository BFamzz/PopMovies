package com.example.bfamz.favouritemovies.utils;

import android.util.Log;

import com.example.bfamz.favouritemovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonUtils {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185";

    public JsonUtils() {
    }

    public static Movie parseMovieJson(String json) {
        Movie movie = null;
        try {
            JSONObject movies = new JSONObject(json);
            JSONArray movieResults = movies.optJSONArray("results");

            // Iterate through the array
            for (int i=0; i<movieResults.length(); i++) {
                JSONObject favouriteMovies = movieResults.optJSONObject(i);
                String title = favouriteMovies.optString("original_title");
                String overview = favouriteMovies.optString("overview");
                String posterPath = favouriteMovies.optString("poster_path");
                String moviePoster = BASE_URL + posterPath;
                String releaseDate = favouriteMovies.optString("release_date");
                double rating = favouriteMovies.optDouble("vote_average");

                movie = new Movie(title, overview, releaseDate, moviePoster, rating);
            }

        } catch (JSONException e) {
            Log.v("JsonUtils", "Unable to parse movie Json", e);
        }
        return movie;
    }
}
