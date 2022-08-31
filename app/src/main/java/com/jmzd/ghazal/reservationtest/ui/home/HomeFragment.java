package com.jmzd.ghazal.reservationtest.ui.home;

import android.annotation.SuppressLint;
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
import androidx.navigation.NavController;
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
import com.jmzd.ghazal.reservationtest.ui.TestFragment;

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
                new ViewModelProvider(this).get(HomeViewModel.class); //requireActivity

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        homeViewModel.getMoviesList().observe(getViewLifecycleOwner(), (Observer<ArrayList<MoviesList.Movie>>) movies -> {

           moviesAdapter = new MoviesAdapter(getContext(), movies);
            LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
            binding.recyclerView.setAdapter(moviesAdapter);
            binding.recyclerView.setLayoutManager(layoutManager);
            binding.recyclerView.setHasFixedSize(true);

        }) ;

        homeViewModel.getLooading().observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean){
                binding.progressCircular.setVisibility(View.VISIBLE);
            }else {
                binding.progressCircular.setVisibility(View.GONE);
            }
        });



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btn.setOnClickListener(view1 -> {

            //simple
//        Navigation.findNavController(view).navigate(R.id.action_nav_home_to_testFragment);

            // args
//            HomeFragmentDirections.ActionNavHomeToTestFragment action =
//                    HomeFragmentDirections.actionNavHomeToTestFragment();
//            action.setArgTest("test test test.....");
//            Navigation.findNavController(view).navigate(action);

            //bundle
            Bundle bundle = new Bundle();
            bundle.putString("link", "google.com");
            Navigation.findNavController(view).navigate(R.id.action_nav_home_to_testFragment, bundle);

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}