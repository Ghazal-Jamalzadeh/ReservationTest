package com.jmzd.ghazal.reservationtest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jmzd.ghazal.reservationtest.databinding.RecyclerItemMoviesBinding;
import com.jmzd.ghazal.reservationtest.models.Genre;


import java.util.ArrayList;

public class GenresAdapter  extends RecyclerView.Adapter<GenresAdapter.ViewHolder> {

    private ArrayList<Genre> genres ;
    private Context context ;

    public GenresAdapter(Context context , ArrayList<Genre> genres) {

        this.context = context ;
        this.genres = genres ;

    }

    @NonNull
    @Override
    public GenresAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        this.context = parent.getContext() ;
        RecyclerItemMoviesBinding v = RecyclerItemMoviesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new GenresAdapter.ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull GenresAdapter.ViewHolder holder, int position) {

        holder.binding.title.setText(genres.get(position).name);

    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerItemMoviesBinding binding;

        public ViewHolder(@NonNull RecyclerItemMoviesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
