package com.jmzd.ghazal.reservationtest.models;

import com.google.gson.annotations.SerializedName;

public class ResponseUserLogin {

    @SerializedName("token_type")
    public String token_type;

    @SerializedName("expries_in")
    public int expries_in;

    @SerializedName("access_token")
    public String access_token;

    @SerializedName("refresh_token")
    public String refresh_token;

}
