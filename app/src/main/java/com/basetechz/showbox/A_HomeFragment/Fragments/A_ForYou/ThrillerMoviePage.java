package com.basetechz.showbox.A_HomeFragment.Fragments.A_ForYou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.basetechz.showbox.R;
import com.basetechz.showbox.databinding.ActivityThrillerMoviePageBinding;

public class ThrillerMoviePage extends AppCompatActivity {
    ActivityThrillerMoviePageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityThrillerMoviePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());






    }
}