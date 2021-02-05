package com.example.testmixapirecycler.animationActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.testmixapirecycler.MainActivity;
import com.example.testmixapirecycler.R;
import com.example.testmixapirecycler.activityPokemonDetails.Activity_Pokemon_Details;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        (new Handler()).postDelayed(this::startingBlock, 2500);
    }
    public void startingBlock()
    {

        Intent intent = new Intent( AnimationActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
}