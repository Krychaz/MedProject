package com.cit.medi_screen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GpActivity extends AppCompatActivity {
    private EditText gpName;
    private EditText gpAddress;
    private Button setGp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);
        gpName = (EditText) findViewById(R.id.gpNameEditText);
        gpAddress = (EditText) findViewById(R.id.gpAddressEdditText);
        setGp = (Button) findViewById(R.id.gpSetButton);
        setGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gpNameIn = gpName.getText().toString().trim();
                String gpAddressIn = gpAddress.getText().toString().trim();
                DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
                dbAccess.open();
                long res = dbAccess.addGP(gpNameIn, gpAddressIn);
                if (gpAddressIn.equals("") || gpNameIn.equals(""))
                    Toast.makeText(GpActivity.this, "Error please enter email and password ", Toast.LENGTH_SHORT).show();
                else {
                    if (res > 0)
                        Toast.makeText(GpActivity.this, "You set your GP", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(GpActivity.this, "Error setting your GP", Toast.LENGTH_SHORT).show();

                    dbAccess.close();
                }
            }
        });

    }
}
