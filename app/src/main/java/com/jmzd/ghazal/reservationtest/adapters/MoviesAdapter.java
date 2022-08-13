package com.jmzd.ghazal.reservationtest.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmzd.ghazal.reservationtest.databinding.RecyclerItemMoviesBinding;
import com.jmzd.ghazal.reservationtest.models.MoviesList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private MoviesList moviesList ;
    private Context context ;

    public MoviesAdapter(Context context , MoviesList moviesList) {

        this.context = context ;
        this.moviesList = moviesList ;

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

        holder.binding.title.setText(moviesList.movies.get(position).title);

    }

    @Override
    public int getItemCount() {
        return moviesList.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerItemMoviesBinding binding;

        public ViewHolder(@NonNull RecyclerItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}