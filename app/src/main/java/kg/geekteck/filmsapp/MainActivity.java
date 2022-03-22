package kg.geekteck.filmsapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import kg.geekteck.filmsapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        kg.geekteck.filmsapp.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}