package com.cit.medi_screen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {
    private Button send;
    private RatingBar rating;
    private EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        rating = findViewById(R.id.ratingBar);
        send = findViewById(R.id.sendButton);
        message = findViewById(R.id.sendRatingEditText);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageTidy = message.getText().toString().trim();

                if (rating.getRating() == 0 || messageTidy.equals("")) {
                    Toast.makeText(RatingActivity.this, "please fill out rating and review.", Toast.LENGTH_SHORT).show();

                } else {
                    int starRating =Math.round(rating.getRating());
                    String subject = "Medi-Screen review with rating: " +  starRating+ "/5.";
                    String email = "Support@mediscreen.com";
                    Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email));
                    i.putExtra(Intent.EXTRA_SUBJECT, subject);
                    i.putExtra(Intent.EXTRA_TEXT, messageTidy);
                    try {
                        startActivity(Intent.createChooser(i, "Send mail..."));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(RatingActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}