package com.geek.myapplication.data.remote;

import com.geek.myapplication.data.models.Film;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface GhidliApi {
    @GET("/films/{id}")
    Call<Film> getFilm(
            @Path("id") String id
    );

    @GET("/films")
    Call<List<Film>> getFilms();

}
