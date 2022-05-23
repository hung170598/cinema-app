package com.example.cinemaapp.ui.slideshow;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Movie;

import java.util.List;
import java.util.Objects;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private List<Movie> movies;
    private final SlideshowViewModel slideshowViewModel;

    public MovieAdapter(List<Movie> movies, SlideshowViewModel slideshowViewModel) {
        this.movies = movies;
        this.slideshowViewModel = slideshowViewModel;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        if (Objects.isNull(movie)) {
            return;
        }
        Glide.with(holder.imgMovie.getContext()).load(movie.getUrl()).into(holder.imgMovie);
    }

    @Override
    public int getItemCount() {
        return Objects.isNull(movies) ? 0 : movies.size();
    }



    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgMovie;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMovie = itemView.findViewById(R.id.banner);
        }
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
