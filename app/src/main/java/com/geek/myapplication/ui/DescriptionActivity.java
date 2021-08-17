package com.geek.myapplication.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.geek.myapplication.R;
import com.geek.myapplication.data.models.Film;
import com.geek.myapplication.data.remote.RetrofitBuilder;
import com.geek.myapplication.databinding.ActivityDescriptionBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DescriptionActivity extends AppCompatActivity {

    private ActivityDescriptionBinding binding;
    private String getID;
    Film film;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getID = getIntent().getStringExtra("filmId");

        setFilm();

    }

    private void setFilm() {
        RetrofitBuilder.getInstance().getFilm(getID).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    film = new Film(
                            response.body().getId(),
                            response.body().getTitle(),
                            response.body().getDescription(),
                            response.body().getProducer(),
                            response.body().getReleaseDate()
                    );
                    settingFilm(film);
                } else {
                    Toast.makeText(DescriptionActivity.this, "😑", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Toast.makeText(DescriptionActivity.this, "😭", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void settingFilm(Film film) {
        binding.title.setText(film.getTitle());
        binding.producer.setText(film.getProducer());
        binding.releaseDate.setText("Release date: " + film.getReleaseDate());
        binding.description.setText(film.getDescription());
    }
}