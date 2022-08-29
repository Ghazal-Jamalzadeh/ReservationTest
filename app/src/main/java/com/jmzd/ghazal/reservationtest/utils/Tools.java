package com.jmzd.ghazal.reservationtest.utils;

import com.jmzd.ghazal.reservationtest.server.ApiClient;
import com.jmzd.ghazal.reservationtest.server.ApiServices;

public class Tools {

    private static ApiServices apiServices = null ;

    public static ApiServices createApiServices (){

        if (apiServices == null ){
        //client
        apiServices = ApiClient.getClient().create(ApiServices.class);
        }

        return apiServices ;
    }
}
