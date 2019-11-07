package com.cit.medi_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsuranceActivity extends AppCompatActivity {
    private EditText insureName;
    private EditText insurePhone;
    private EditText insureEmail;
    private Button setInsure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        insureName = (EditText) findViewById(R.id.insureNameEditText);
        insurePhone = (EditText) findViewById(R.id.insurePhoneEditText);
        insureEmail = (EditText) findViewById(R.id.insurerEmailEditText);

        setInsure = (Button) findViewById(R.id.insureSetButton);
        setInsure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent medHisIntent = new Intent(InsuranceActivity.this, MedicalHistoryActivity.class);

                String insureNameIn = insureName.getText().toString().trim();
                String insuremailIn = insureEmail.getText().toString().trim();

                Integer insurePhoneInt = Integer.parseInt(insurePhone.getText().toString().trim());
                DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
                dbAccess.open();
                if (insureNameIn.equals("")||insuremailIn.equals("") || insurePhoneInt == null)
                    Toast.makeText(InsuranceActivity.this, "Error please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    long res = dbAccess.addInsure(insureNameIn, insurePhoneInt,insuremailIn);

                    if (res > 0) {
                        Toast.makeText(InsuranceActivity.this, "You set your insurer", Toast.LENGTH_SHORT).show();
                        startActivity(medHisIntent);
                    } else
                        Toast.makeText(InsuranceActivity.this, "Error setting your insurer", Toast.LENGTH_SHORT).show();

                    dbAccess.close();
                }
            }
        });

    }
}
