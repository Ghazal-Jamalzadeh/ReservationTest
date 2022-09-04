package com.jmzd.ghazal.reservationtest.repository;

import com.jmzd.ghazal.reservationtest.models.MoviesList;
import com.jmzd.ghazal.reservationtest.models.ResponseCoinCap;
import com.jmzd.ghazal.reservationtest.utils.Tools;

import retrofit2.Call;

public class HomeRepository {

    public Call<MoviesList> callMoviesApi (){

        //call api
        Call<MoviesList> call1 =  Tools.getApiServicesInstance().getMovies(1);

        return call1 ;
    }

    public Call<ResponseCoinCap> callCoinCap (){

        //call api
        Call <ResponseCoinCap> call2 = Tools.getApiServicesInstance().getCoinCap() ;

        return call2 ;
    }

}
