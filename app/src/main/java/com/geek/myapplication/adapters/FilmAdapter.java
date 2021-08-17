package com.geek.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geek.myapplication.data.models.Film;
import com.geek.myapplication.databinding.ItemFilmBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.MyViewHolder> {

    private List<Film> list = new ArrayList<>();
    private ItemFilmBinding binding;
    private  static ItemClickListener itemClickListener;

    /*public List<Film> getFilmId(){
        return list
    }
*/
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setList(List<Film> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull ItemFilmBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void onBind(Film film) {
            binding.film.setText(film.getTitle());
            binding.releaseDate.setText(film.getReleaseDate());

            binding.getRoot().setOnClickListener(v -> {
                itemClickListener.itemClick(film);
            });
        }
    }

    public interface ItemClickListener {
        void itemClick(Film film);
    }
}