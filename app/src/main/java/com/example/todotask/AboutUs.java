package com.example.todotask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);
        Objects.requireNonNull(getSupportActionBar()).hide();

        FloatingActionButton back = findViewById(R.id.backButton);
        back.setOnClickListener(view -> startActivity(new Intent(AboutUs.this, MainActivity.class)));
    }
}