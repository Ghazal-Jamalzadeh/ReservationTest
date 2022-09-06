package com.jmzd.ghazal.reservationtest.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.jmzd.ghazal.reservationtest.databinding.RecyclerItemMoviesBinding;
import com.jmzd.ghazal.reservationtest.models.MoviesList;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private ArrayList<MoviesList.Movie> movies ;
    private Context context ;

    public MoviesAdapter(Context context , ArrayList<MoviesList.Movie> movies ) {

        this.context = context ;
        this.movies = movies ;

    }

    @NonNull
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext() ;
        RecyclerItemMoviesBinding v = RecyclerItemMoviesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MoviesAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.ViewHolder holder, int position) {

        Glide.with(context)
                .load(movies.get(position).poster)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.binding.poster);
        holder.binding.title.setText(movies.get(position).title);
        holder.binding.year.setText(movies.get(position).year);

        StringBuilder stringBuilder = new StringBuilder() ;
        for (String genre: movies.get(position).genres) {

            stringBuilder.append(genre) ;
            stringBuilder.append(" ");
        }
        holder.binding.genres.setText(stringBuilder);


    }

    @Override
    public int getItemViewType(int position) {
        return position ; 
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerItemMoviesBinding binding;

        public ViewHolder(@NonNull RecyclerItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}