package kg.geekteck.filmsapp.ui.films;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import kg.geekteck.filmsapp.data.models.Film;
import kg.geekteck.filmsapp.databinding.ItemFilmBinding;

public class FilmsAdaptor extends RecyclerView.Adapter<FilmsAdaptor.FilmsViewHolder> {
    private List<Film> films = new ArrayList<>();
    private final Click itemClickListener;


    public FilmsAdaptor(Click itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemFilmBinding binding = ItemFilmBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new FilmsViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmsViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.onBind(films.get(position));
        holder.itemView.setOnClickListener(view -> {
            itemClickListener.onItemClicked(films.get(position).getId());
            System.out.println(films.get(position).getId());
        });
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    protected static class FilmsViewHolder extends RecyclerView.ViewHolder{
        private final ItemFilmBinding binding;

        public FilmsViewHolder(@NonNull ItemFilmBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void onBind(Film film) {
            binding.tvTitle.setText(film.getTitle());
            binding.tvDescription.setText(film.getDescription());
        }
    }
}
