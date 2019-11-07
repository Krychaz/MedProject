package com.cit.medi_screen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MedicalHistoryActivity extends AppCompatActivity {
    private Button setMedHis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_history);
        setMedHis = (Button) findViewById(R.id.setMedHisButton);
        setMedHis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(MedicalHistoryActivity.this, HomeActivity.class);
                startActivity(home);

            }
        });

    }
}
