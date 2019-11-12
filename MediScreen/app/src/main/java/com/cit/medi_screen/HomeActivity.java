package com.cit.medi_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private Button call;
    private Button support;
    private Button rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        rating = findViewById(R.id.ratingsButton);
        call = findViewById(R.id.callButton);
        support = findViewById(R.id.supportButton);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(HomeActivity.this, CallActivity.class);
                startActivity(callIntent);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent supportIntent = new Intent(HomeActivity.this, SupportActivity.class);
                startActivity(supportIntent);
            }
        });

        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ratingIntent = new Intent(HomeActivity.this, RatingActivity.class);
                startActivity(ratingIntent);
            }
        });

    }
}
