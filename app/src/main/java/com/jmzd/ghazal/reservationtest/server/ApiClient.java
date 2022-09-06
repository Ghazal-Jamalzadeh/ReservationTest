package com.jmzd.ghazal.reservationtest.server;

import com.jmzd.ghazal.reservationtest.utils.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

//        HttpLoggingInterceptor interceptorHeader = new HttpLoggingInterceptor();
//        interceptorHeader.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        HttpLoggingInterceptor interceptorBody = new HttpLoggingInterceptor();
        interceptorBody.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.NETWORK_TIMEOUT, TimeUnit.SECONDS)
//                .addInterceptor(interceptorHeader)
                .addInterceptor(interceptorBody)
//                .addInterceptor(chain -> {
//            Request request = chain.request().newBuilder().addHeader("key", "value").build();
//            return chain.proceed(request);
//        })
                .build();


        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit;
    }

}
