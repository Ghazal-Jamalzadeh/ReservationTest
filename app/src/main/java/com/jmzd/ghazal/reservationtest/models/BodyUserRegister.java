package com.jmzd.ghazal.reservationtest.models;

import com.google.gson.annotations.SerializedName;

public class BodyUserRegister {

    @SerializedName("email")
    public String email;

    @SerializedName("password")
    public String password;

    @SerializedName("name")
    public String name;

}
