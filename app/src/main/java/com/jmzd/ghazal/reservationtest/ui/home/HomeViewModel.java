package com.jmzd.ghazal.reservationtest.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jmzd.ghazal.reservationtest.models.MoviesList;
import com.jmzd.ghazal.reservationtest.repository.HomeRepository;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private static final String TAG = "ghazalTest";
    public int counter = 0 ;
    private MutableLiveData<Integer> mText = new MutableLiveData<>();
    public MutableLiveData<ArrayList<MoviesList.Movie>> moviesListLiveData ;
    private MutableLiveData<Boolean> loading = new MutableLiveData<>() ;
    HomeRepository homeRepository = new HomeRepository() ;


    public void addNumber(){
        counter ++ ;
        mText.setValue(counter);
    }

    public void callGetMoviesList(){

        loading.setValue(true);
        //call api
        Call<MoviesList> call1 =  homeRepository.callMoviesApi();

        //response
        call1.enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response) {
                assert response.body() != null;

                moviesListLiveData.setValue(response.body().movies);
                loading.setValue(false);

            }

            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {

                loading.setValue(false);
            }
        });

    }

    public LiveData<Boolean> getLooading (){
        return loading ;
    }

//    public LiveData<ArrayList<MoviesList.Movie>> getMoviesList (){
//        return moviesListLiveData ;
//    }

    public LiveData<Integer> getText() {
        return mText;
    }

    public LiveData<ArrayList<MoviesList.Movie>> getMoviesList() {
        Log.d(TAG, "getMoviesList called  ");
        if (moviesListLiveData == null) {
            moviesListLiveData = new MutableLiveData<>() ;
            //call api
            callGetMoviesList();
            Log.d(TAG, " api called  ");
        }
        return moviesListLiveData;
    }
}