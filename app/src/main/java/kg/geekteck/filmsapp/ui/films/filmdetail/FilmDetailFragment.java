package kg.geekteck.filmsapp.ui.films.filmdetail;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import kg.geekteck.filmsapp.App;
import kg.geekteck.filmsapp.data.models.Film;
import kg.geekteck.filmsapp.databinding.FragmentFilmDetailBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmDetailFragment extends Fragment {
    private FragmentFilmDetailBinding binding;

    private static final String TAG = "FilmDetailFragment";

    public FilmDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String id = "";
        if (bundle!=null) {
            id = bundle.getString("film");
        }else {
            Log.d(TAG, "onViewCreated: Bundle is empty");
        }

        setDate(id);
    }

    private void setDate(String id){
        App.api.getFilmDetail(id).enqueue(new Callback<Film>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                if (response.isSuccessful() && response.body()!=null){
                    Uri uri = response.body().getMovieBanner();
                    Glide.with(requireContext()).load(uri)
                            .into(binding.ivMovieBanner);
                    binding.tvTitle.setText(response.body().getTitle());
                    binding.tvOriginalTitle.setText(response.body().getOriginalTitleRomanised());
                    binding.tvOriginal.setText(response.body().getOriginalTitle());
                    binding.tvDirector.setText(response.body().getDirector());
                    binding.tvReleased.setText(response.body().getReleaseDate());
                    binding.tvDescription.setText(response.body().getDescription());
                }else {
                    Log.d(TAG, "onResponse: "+response.message());
                    System.out.println("onResponse: "+response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
                System.out.println("onFailure: "+t.getLocalizedMessage());

            }
        });
    }
}