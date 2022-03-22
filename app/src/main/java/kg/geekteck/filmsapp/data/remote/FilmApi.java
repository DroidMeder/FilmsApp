package kg.geekteck.filmsapp.data.remote;

import java.util.List;

import kg.geekteck.filmsapp.data.models.Film;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FilmApi {

    @GET("/films")
    Call<List<Film>> getFilms();

    @GET("/films/{id}")
    Call<Film> getFilmDetail(
            @Path("id") String id
    );
}
