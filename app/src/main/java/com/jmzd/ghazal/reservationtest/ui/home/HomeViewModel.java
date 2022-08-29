package com.jmzd.ghazal.reservationtest.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jmzd.ghazal.reservationtest.adapters.MoviesAdapter;
import com.jmzd.ghazal.reservationtest.models.MoviesList;
import com.jmzd.ghazal.reservationtest.repository.HomeRepository;
import com.jmzd.ghazal.reservationtest.utils.Tools;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public MutableLiveData<ArrayList<MoviesList.Movie>> moviesListLiveData = new MutableLiveData<>() ;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public void callGetMoviesList(){

        HomeRepository homeRepository = new HomeRepository() ;

        //call api
        Call<MoviesList> call1 =  homeRepository.callMoviesApi();

        //response
        call1.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                assert response.body() != null;

                Log.d("ghazalTest", "onResponse: size  " + response.body().movies.size());

                for (MoviesList.Movie movie :response.body().movies) {

                    Log.d("ghazalTest", "movie: " + movie.title);

                }

                moviesListLiveData.setValue(response.body().movies);

            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
                Log.d("ghazal", "onFailure: " + t.getMessage());
            }
        });

    }

    public LiveData<ArrayList<MoviesList.Movie>> getMoviesList (){
        return moviesListLiveData ;
    }

    public LiveData<String> getText() {
        return mText;
    }
}