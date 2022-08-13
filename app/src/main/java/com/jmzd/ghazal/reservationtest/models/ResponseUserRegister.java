package com.jmzd.ghazal.reservationtest.models;

import com.google.gson.annotations.SerializedName;

public class ResponseUserRegister {

    @SerializedName("id")
    public int id;

    @SerializedName("name")
    public String name;

    @SerializedName("email")
    public String email;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("updated_at")
    public String updated_at;


}
