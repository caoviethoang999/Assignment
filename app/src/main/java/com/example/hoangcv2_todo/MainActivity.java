package com.example.hoangcv2_todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecylerFragment recylerFragment = new RecylerFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, recylerFragment).commit();
    }
}