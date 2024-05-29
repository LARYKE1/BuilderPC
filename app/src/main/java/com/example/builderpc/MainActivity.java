package com.example.builderpc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buildPCButton = findViewById(R.id.buildPCButton);
        Button buildsBySomeoneElseButton = findViewById(R.id.buildsBySomeoneElseButton);
        Button oldBuildsButton = findViewById(R.id.oldBuildsButton);

        buildPCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Build PC activity
                startActivity(new Intent(MainActivity.this, BuildPC.class));
            }
        });

        buildsBySomeoneElseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Builds by Someone Else activity

            }
        });

        oldBuildsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start Old Builds activity

            }
        });
    }
    }


