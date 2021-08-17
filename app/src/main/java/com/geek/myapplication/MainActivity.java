package com.geek.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.geek.myapplication.adapters.FilmAdapter;
import com.geek.myapplication.data.models.Film;
import com.geek.myapplication.data.remote.RetrofitBuilder;
import com.geek.myapplication.databinding.ActivityMainBinding;
import com.geek.myapplication.ui.DescriptionActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private FilmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new FilmAdapter();

        getFilms();

        binding.rvFilms.setAdapter(adapter);
        clickFilm();

    }

    private void clickFilm() {
        adapter.setItemClickListener(film -> {
            Intent intent = new Intent(new Intent(MainActivity.this, DescriptionActivity.class));
            intent.putExtra("filmId", film.getId());
            startActivity(intent);

        });

    }

    private void getFilms() {
        RetrofitBuilder.getInstance().getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.setList(response.body());
                } else {
                    Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Check your internet connection", Toast.LENGTH_SHORT).show();

            }
        });
    }
}