package com.example.bfamz.favouritemovies.utils;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bfamz.favouritemovies.R;
import com.example.bfamz.favouritemovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> mMoviesList;
    private Context mContext;
    private Movie movie;

    public MovieAdapter(ArrayList<Movie> moviesList, Context context) {
        this.mMoviesList = moviesList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        movie = mMoviesList.get(position);
//        Picasso.get().load("https://image.tmdb.org/t/p/w185/uC6TTUhPpQCmgldGyYveKRAu8JN.jpg").into(holder.moviePoster);
        Picasso.get().load(movie.getMoviePoster()).into(holder.moviePoster);
    }

    @Override
    public int getItemCount() {

        return mMoviesList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView moviePoster;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moviePoster = itemView.findViewById(R.id.imageView);
        }
    }
}
