package com.jmzd.ghazal.reservationtest.models;

import com.google.gson.annotations.SerializedName;

public class BodyUserLogin {

    @SerializedName("grant_type")
    public String grant_type = "password";

    @SerializedName("password")
    public String password;

    @SerializedName("username")
    public String username;

}
