package kg.geekteck.filmsapp;

import android.app.Application;

import kg.geekteck.filmsapp.data.remote.FilmApi;
import kg.geekteck.filmsapp.data.remote.RetrofitClient;

public class App extends Application {

    public static FilmApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitClient retrofitClient = new RetrofitClient();
        api = retrofitClient.createFilmApi();
    }
}
