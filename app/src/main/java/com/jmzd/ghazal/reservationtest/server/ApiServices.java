package com.jmzd.ghazal.reservationtest.server;

import com.google.gson.JsonObject;
import com.jmzd.ghazal.reservationtest.models.BodyGetUser;
import com.jmzd.ghazal.reservationtest.models.BodyUserLogin;
import com.jmzd.ghazal.reservationtest.models.BodyUserRegister;
import com.jmzd.ghazal.reservationtest.models.Genre;
import com.jmzd.ghazal.reservationtest.models.MoviesList;
import com.jmzd.ghazal.reservationtest.models.ResponseUserLogin;
import com.jmzd.ghazal.reservationtest.models.ResponseUserRegister;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServices {

    //https://moviesapi.ir/api/v1/movies?page=1
    @GET("/api/v1/movies?")
    Call<MoviesList> getMovies(@Query("page") int page);

    //https://moviesapi.ir/api/v1/genres
    @GET("/api/v1/genres")
    Call<ArrayList<Genre>> getGenres();

    //https://moviesapi.ir/api/v1/register
    //params (json) : email / password / name
    @POST("/api/v1/register")
    Call<ResponseUserRegister> registerUser(@Body BodyUserRegister bodyUserRegister);


    //https://moviesapi.ir/oauth/token
    //params (json) : grant_type / password / username
    @POST("/oauth/token")
    Call<ResponseUserLogin> loginUser(@Body BodyUserLogin bodyUserLogin);

    //https://moviesapi.ir/api/user
    @POST("/api/user")
    Call<ResponseUserRegister> getUser(@Header("authorization") String token);

    @FormUrlEncoded
    @POST("v1/EmergencyRequirement.php/?op=addPatient")
    Call<JsonObject> addPerson(@Field("BloodGroup") String bloodgroup,
                                      @Field("Address") String Address,
                                      @Field("City") String city,
                                      @Field("ContactNumber") String contactnumber,
                                      @Field("PatientName") String name,
                                      @Field("Time") String Time,
                                      @Field("DonatedBy") String donar);


}
