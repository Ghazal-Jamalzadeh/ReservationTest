package com.jmzd.ghazal.reservationtest.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmzd.ghazal.reservationtest.adapters.GenresAdapter;
import com.jmzd.ghazal.reservationtest.databinding.FragmentGalleryBinding;
import com.jmzd.ghazal.reservationtest.models.Genre;
import com.jmzd.ghazal.reservationtest.server.ApiClient;
import com.jmzd.ghazal.reservationtest.server.ApiServices;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GalleryFragment extends Fragment {

    //binding
    private FragmentGalleryBinding binding;
    //context
    private Context context = getContext();
    //adapters
    private GenresAdapter genresAdapter ;
    //api
    public ApiServices apiServices ;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        //client
        apiServices = ApiClient.getClient().create(ApiServices.class);

        //call api
        Call<ArrayList<Genre>> call1 = apiServices.getGenres();
        call1.enqueue(new Callback<ArrayList<Genre>>() {
            @Override
            public void onResponse(Call<ArrayList<Genre>> call, Response<ArrayList<Genre>> response) {
                assert response.body() != null;
                for (Genre genre :response.body()) {

                    Log.d("ghazal", "genre: " +genre.name);

                    genresAdapter = new GenresAdapter(getContext() , response.body());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
                    binding.recyclerView.setAdapter(genresAdapter);
                    binding.recyclerView.setLayoutManager(layoutManager);
                    binding.recyclerView.setHasFixedSize(true);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Genre>> call, Throwable t) {
                Log.d("ghazal", "onFailure: " + t.getMessage());
            }
        });



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}