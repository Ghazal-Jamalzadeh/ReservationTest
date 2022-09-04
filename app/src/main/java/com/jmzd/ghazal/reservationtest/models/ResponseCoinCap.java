package com.jmzd.ghazal.reservationtest.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseCoinCap {

    @SerializedName("data")
    public ArrayList<Coin> data = new ArrayList<Coin>() ;

    public class Coin {
        @SerializedName("symbol")
        public String symbol = "" ;

        @SerializedName("maxSupply")
        public String maxSupply = "" ;
    }
}
