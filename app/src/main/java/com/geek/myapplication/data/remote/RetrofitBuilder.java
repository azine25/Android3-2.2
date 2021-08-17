package com.geek.myapplication.data.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static GhidliApi instance;
    private RetrofitBuilder(){}
    public static GhidliApi getInstance(){
        if(instance == null) {
            instance = createRetrofit();
        }
        return instance;
    }

    private static GhidliApi createRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("https://ghibliapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GhidliApi.class);
    }

}
