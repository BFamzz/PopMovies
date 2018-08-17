package com.example.bfamz.favouritemovies.data;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bfamz.favouritemovies.activities.MainActivity;
import com.example.bfamz.favouritemovies.model.Movie;

import org.json.JSONArray;
import org.json.JSONObject;

public class VolleyRequests {

    private static final String BASE_URL = "https://image.tmdb.org/t/p/w185";
    private static final String LOG_TAG = VolleyRequests.class.getSimpleName();

    public VolleyRequests() {
    }

    public static void getMovies(final MainActivity mainActivity) {
        if (mainActivity != null) {
            String url = "https://api.themoviedb.org/3/movie/popular?api_key=282c193fdf9a5c0d3bf4eae003ef7b97&language=en-US&page=1";
            JsonObjectRequest request = new JsonObjectRequest(url, null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            JSONArray movies = response.optJSONArray("results");
                            JSONObject movieObject;
                            for (int i = 0; i < movies.length(); i++) {
                                movieObject = movies.optJSONObject(i);
                                String title = movieObject.optString("original_title");
                                String overview = movieObject.optString("overview");
                                String posterPath = movieObject.optString("poster_path");
                                String moviePoster = BASE_URL + posterPath;
                                String releaseDate = movieObject.optString("release_date");
                                double rating = movieObject.optDouble("vote_average");
                                Movie movie = new Movie(title, overview, releaseDate, moviePoster, rating);
                                mainActivity.movies.add(movie);
                                mainActivity.movieAdapter.notifyDataSetChanged();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.v(LOG_TAG, "Error getting Json data", error);
                }
            });
            mainActivity.mRequestQueue.add(request);
        }
    }
}

