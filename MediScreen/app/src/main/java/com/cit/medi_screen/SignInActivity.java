package com.cit.medi_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private Button signUp;
    private DatabaseAccess dbAccess;
    private boolean gpExists;
    private boolean medHisExists;


    private boolean insurerExists;


    private static String loggedInEmail;
    private static String loggedInPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwdEditText);
        login = findViewById(R.id.loginButton);
        signUp = findViewById(R.id.signUpButton);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(signUpIntent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gpIntent = new Intent(SignInActivity.this, GpActivity.class);
                Intent insureIntent = new Intent(SignInActivity.this, InsuranceActivity.class);
                Intent medHisIntent = new Intent(SignInActivity.this, MedicalHistoryActivity.class);
                Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);

                dbAccess = getDBAccess();
                dbAccess.open();
                String emailIn = email.getText().toString().trim();
                String passwdIn = password.getText().toString().trim();
                String email = dbAccess.getEmail(emailIn);
                String passwd = dbAccess.getPassword(emailIn);
                if (emailIn.equals("") && passwdIn.equals(""))
                    Toast.makeText(SignInActivity.this, "Error please enter email and password ", Toast.LENGTH_SHORT).show();
                else if (email.equals(emailIn) && passwd.equals(passwdIn)) {
                    Toast.makeText(SignInActivity.this, "Sucess", Toast.LENGTH_SHORT).show();

                    loggedInEmail = email;
                    loggedInPassword = passwd;
                    gpExists = gpExists();
                    gpExists = gpExists();
                    if (gpExists) {
                        Toast.makeText(SignInActivity.this, "gp exists", Toast.LENGTH_SHORT).show();
                        insurerExists = insurerExists();
                        if (insurerExists) {
                            Toast.makeText(SignInActivity.this, "insurer exists", Toast.LENGTH_SHORT).show();
                            medHisExists = medHisExists();
                            if (medHisExists) {
                                Toast.makeText(SignInActivity.this, "medical history exists", Toast.LENGTH_SHORT).show();
                                startActivity(homeIntent);
                            } else
                                startActivity(medHisIntent);

                        } else
                            startActivity(insureIntent);

                    } else
                        startActivity(gpIntent);
                } else {
                    Toast.makeText(SignInActivity.this, "Error incorrect email and/or password", Toast.LENGTH_SHORT).show();
                }
                dbAccess.close();


            }
        });
    }

    private DatabaseAccess getDBAccess() {
        return DatabaseAccess.getInstance(getApplicationContext());
    }

    public boolean gpExists() {
        dbAccess.open();
        return dbAccess.gpExists();
    }

    public boolean medHisExists() {
        dbAccess.open();
        return dbAccess.medHisExists();
    }

    public boolean insurerExists() {
        dbAccess.open();
        return dbAccess.insureExists();
    }

    public static String getLoggedInEmail() {
        return loggedInEmail;
    }

    public String getLoggedInPassword() {
        return loggedInPassword;
    }

    public boolean getInsurerExists() {
        return insurerExists;
    }
}


