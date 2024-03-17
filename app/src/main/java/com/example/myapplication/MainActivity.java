package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button existingUserButton, newUserButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        existingUserButton = findViewById(R.id.getRecExistingUserButton);
        newUserButton = findViewById(R.id.getRecNewUserButton);

        existingUserButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, ExistingUserActivity.class);
            startActivity(intent);
        });

        newUserButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewRecommendationActivity.class);
            startActivity(intent);
        });
    }
}