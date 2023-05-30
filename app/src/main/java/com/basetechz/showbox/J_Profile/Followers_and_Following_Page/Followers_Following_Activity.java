package com.basetechz.showbox.J_Profile.Followers_and_Following_Page;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.basetechz.showbox.databinding.ActivityFollowersFollowingBinding;

public class Followers_Following_Activity extends AppCompatActivity {

    ActivityFollowersFollowingBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFollowersFollowingBinding.inflate(getLayoutInflater());


    }
}