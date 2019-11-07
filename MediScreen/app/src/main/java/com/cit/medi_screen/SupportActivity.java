package com.cit.medi_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SupportActivity extends AppCompatActivity {
    private EditText emailBody;
    private Button sendGP;
    private Button sendInsurer;
    private DatabaseAccess db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        emailBody = findViewById(R.id.sendEmailEditText);
        sendGP = findViewById(R.id.sendGPButton);
        sendInsurer = findViewById(R.id.sendInsurerButton);
        sendGP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = getDBAccess();
                db.open();
                String emailBodyTidy = emailBody.getText().toString().trim();

                String email = db.getGPEmail();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                i.putExtra(Intent.EXTRA_SUBJECT, "support");
                i.putExtra(Intent.EXTRA_TEXT, emailBodyTidy);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SupportActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sendInsurer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = getDBAccess();
                db.open();
                String emailBodyTidy = emailBody.getText().toString().trim();

                String email = db.getInsureEmail();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
                i.putExtra(Intent.EXTRA_SUBJECT, "support");
                i.putExtra(Intent.EXTRA_TEXT, emailBodyTidy);
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(SupportActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private DatabaseAccess getDBAccess() {
        return DatabaseAccess.getInstance(getApplicationContext());
    }

}
