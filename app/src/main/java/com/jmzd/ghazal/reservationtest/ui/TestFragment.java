package com.jmzd.ghazal.reservationtest.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jmzd.ghazal.reservationtest.R;
import com.jmzd.ghazal.reservationtest.adapters.MoviesAdapter;
import com.jmzd.ghazal.reservationtest.databinding.FragmentHomeBinding;
import com.jmzd.ghazal.reservationtest.databinding.FragmentTestBinding;
import com.jmzd.ghazal.reservationtest.models.MoviesList;
import com.jmzd.ghazal.reservationtest.ui.home.HomeViewModel;

import java.util.ArrayList;

public class TestFragment extends Fragment {

    //binding
    private FragmentTestBinding binding;
    //context
    private Context context = getContext();
    //adapters
    private MoviesAdapter moviesAdapter ;
    //ViewModel
    HomeViewModel homeViewModel ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class); //requireActivity

        binding = FragmentTestBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //get bundle
        binding.txtBundle.setText(getArguments().getString("link"));

        //get arguments
        String amount = TestFragmentArgs.fromBundle(getArguments()).getArgTest();
        binding.txtArgs.setText(amount);

        homeViewModel.getMoviesList().observe(getViewLifecycleOwner(), (Observer<ArrayList<MoviesList.Movie>>) movies -> {

//            for (int i = 0; i < movies.size() ; i++) {

            Log.d("ghazalTest", "Test Fragment : " + 0 + ":" + movies.get(0).title);

//            }

        }) ;



        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.txt.setText(String.valueOf(homeViewModel.counter));

        homeViewModel.getText().observe(getViewLifecycleOwner(), integer -> binding.txt.setText(String.valueOf(integer)));

        binding.btn.setOnClickListener(view1 -> {

         homeViewModel.addNumber() ;

        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}