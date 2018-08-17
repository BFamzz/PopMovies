package com.example.bfamz.favouritemovies.model;

public class Movie {

    private String movieTitle;
    private String movieOverview;
    private String releaseDate;
    private String moviePoster;
    private double movieRating;

    public Movie(String movieTitle, String movieOverview, String releaseDate, String moviePoster, double movieRating) {
        this.movieTitle = movieTitle;
        this.movieOverview = movieOverview;
        this.releaseDate = releaseDate;
        this.moviePoster = moviePoster;
        this.movieRating = movieRating;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieOverview() {
        return movieOverview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getMoviePoster() {
        return moviePoster;
    }

    public double getMovieRating() {
        return movieRating;
    }
}
