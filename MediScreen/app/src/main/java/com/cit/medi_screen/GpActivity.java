package com.cit.medi_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GpActivity extends AppCompatActivity {
    private EditText gpName;
    private EditText gpAddress;
    private EditText gpPhone;
    private Button setGp;
    private EditText gpEmail;

    private SignInActivity signIn = new SignInActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);
        gpName = (EditText) findViewById(R.id.gpNameEditText);
        gpAddress = (EditText) findViewById(R.id.gpAddressEdditText);
        gpEmail = (EditText) findViewById(R.id.gpEmailEditText);
        gpPhone = (EditText) findViewById(R.id.gpPhoneEditText);
        setGp = (Button) findViewById(R.id.gpSetButton);
        setGp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insureIntent = new Intent(GpActivity.this, InsuranceActivity.class);

                String gpNameIn = gpName.getText().toString().trim();
                String gpAddressIn = gpAddress.getText().toString().trim();
                String gpEmailIn = gpEmail.getText().toString().trim();

                Integer gpPhoneInt = Integer.parseInt(gpPhone.getText().toString().trim());
                DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
                if (gpAddressIn.equals("") || gpNameIn.equals("") ||gpEmailIn.equals("")|| gpPhoneInt == null)
                    Toast.makeText(GpActivity.this, "Error please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    dbAccess.open();
                    long res = dbAccess.addGP(gpNameIn, gpAddressIn, gpPhoneInt,gpEmailIn);

                    if (res > 0) {
                        Toast.makeText(GpActivity.this, "You set your GP", Toast.LENGTH_SHORT).show();
                        if (signIn.getInsurerExists() == false)
                            startActivity(insureIntent);
                        else
                            Toast.makeText(GpActivity.this, "Insurer exists", Toast.LENGTH_SHORT).show();

                    } else
                        Toast.makeText(GpActivity.this, "Error setting your GP", Toast.LENGTH_SHORT).show();

                    dbAccess.close();
                }
            }
        });

    }
}
