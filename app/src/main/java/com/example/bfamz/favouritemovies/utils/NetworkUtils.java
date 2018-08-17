package com.example.bfamz.favouritemovies.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.bfamz.favouritemovies.model.Movie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class NetworkUtils extends AsyncTask<URL, Void, Movie> {

    public AsyncResponse mMovie;

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    private static final String POPULAR_URL =
            "https://api.themoviedb.org/3/movie/popular?api_key=282c193fdf9a5c0d3bf4eae003ef7b97&language=en-US&page=1";

    // Create an interface to get the results from onPostExecute
    public interface AsyncResponse {
        void processFinish(Movie movie);
    }

    @Override
    protected Movie doInBackground(URL... urls) {
        // Create a URL object
        URL url = createUrl(POPULAR_URL);

        // Perform HTTP request to the Url and receive a Json response back
        String jsonResponse = "";
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Unable to makeHttpRequest", e);
            e.printStackTrace();
        }

        // Extract relevant features from the jsonResponse and create a Movie object
        Movie movie = JsonUtils.parseMovieJson(jsonResponse);
        return movie;
    }

    @Override
    protected void onPostExecute(Movie movie) {
        mMovie.processFinish(movie);
    }

    private URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL", e);
        }
        return url;
    }

    // Make an HTTP request to the given url and return a string as response
    private String makeHttpRequest(URL url) throws IOException{
        String jsonResponse = "";
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving Json results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }
}
