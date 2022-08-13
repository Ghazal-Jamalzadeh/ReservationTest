package com.jmzd.ghazal.reservationtest.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesList {

    @SerializedName("data")
    public ArrayList<Movie> movies = new ArrayList();

    public class Movie{

        @SerializedName("id")
        public String id;

        @SerializedName("title")
        public String title;

    }


}
