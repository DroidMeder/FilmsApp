package kg.geekteck.filmsapp.ui.films;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.List;

import kg.geekteck.filmsapp.App;
import kg.geekteck.filmsapp.R;
import kg.geekteck.filmsapp.data.models.Film;
import kg.geekteck.filmsapp.databinding.FragmentFilmsBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment implements Click{
    private FragmentFilmsBinding binding;
    private FilmsAdaptor adaptor;

    private static final String TAG = "FilmsFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adaptor = new FilmsAdaptor(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFilmsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rec.setAdapter(adaptor);
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(@NonNull Call<List<Film>> call, @NonNull Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() != null){
                    adaptor.setFilms(response.body());
                }else {
                    Log.d(TAG, "onResponse: "+response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Film>> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void onItemClicked(String position) {
        Bundle bundle = new Bundle();

        bundle.putString("film", position);
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment);
        navController.navigate(R.id.filmDetailFragment, bundle);
    }
}