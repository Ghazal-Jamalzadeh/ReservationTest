package com.jmzd.ghazal.reservationtest.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.JsonObject;
import com.jmzd.ghazal.reservationtest.R;
import com.jmzd.ghazal.reservationtest.adapters.MoviesAdapter;
import com.jmzd.ghazal.reservationtest.databinding.FragmentHomeBinding;
import com.jmzd.ghazal.reservationtest.models.MoviesList;
import com.jmzd.ghazal.reservationtest.server.ApiClient;
import com.jmzd.ghazal.reservationtest.server.ApiServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    //binding
    private FragmentHomeBinding binding;
    //context
    private Context context = getContext();
    //adapters
    private MoviesAdapter moviesAdapter ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.callGetMoviesList();

        homeViewModel.getText().observe(getViewLifecycleOwner(), s -> {
            Log.d("ghazalTest", "string  " + s );
        });

        homeViewModel.getMoviesList().observe(getViewLifecycleOwner(), (Observer<ArrayList<MoviesList.Movie>>) movies -> {

            moviesAdapter = new MoviesAdapter(getContext(), movies);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            binding.recyclerView.setAdapter(moviesAdapter);
            binding.recyclerView.setLayoutManager(layoutManager);
            binding.recyclerView.setHasFixedSize(true);

        }) ;



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn.setOnClickListener(view1 -> {

        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_testFragment);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}