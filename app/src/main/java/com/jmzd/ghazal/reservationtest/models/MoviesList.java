package com.jmzd.ghazal.reservationtest.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MoviesList {

    @SerializedName("data")
    public ArrayList<Movie> movies = new ArrayList();

    @SerializedName("metadata")
    public Metadata metadata = new Metadata();

    public class Movie{

        @SerializedName("id")
        public String id;

        @SerializedName("title")
        public String title;

        @SerializedName("poster")
        public String poster ;

    }

    public class Metadata{

        @SerializedName("current_page")
        public int current_page ;

    }


}
