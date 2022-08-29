package com.jmzd.ghazal.reservationtest.repository;

import android.util.proto.ProtoOutputStream;

import com.jmzd.ghazal.reservationtest.models.MoviesList;
import com.jmzd.ghazal.reservationtest.server.ApiClient;
import com.jmzd.ghazal.reservationtest.server.ApiServices;
import com.jmzd.ghazal.reservationtest.utils.Tools;

import retrofit2.Call;

public class HomeRepository {

    public Call<MoviesList> callMoviesApi (){

        //call api
        Call<MoviesList> call1 =  Tools.createApiServices().getMovies(1);

        return call1 ;
    }

}
