package com.cit.medi_screen;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.cit.medi_screen.pgtest.PgConnection;

import java.sql.Connection;

public class SignInActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button login;
    private Button signUp;
    private DatabaseAccess dbAccess;
    private boolean gpExists;
    private boolean medHisExists;


    private boolean insurerExists;

    private Button test;
    private static final String TAG = "MyActivity";

    private static String loggedInEmail;
    private static String loggedInPassword;


    private static PgConnection pg = new PgConnection();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        test = findViewById(R.id.button);

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Connection con = pg.getCon();
                Log.v(TAG, String.valueOf(con));
                Toast.makeText(SignInActivity.this,String.valueOf(con),Toast.LENGTH_LONG).show();

            }
        });

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
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                Intent gpIntent = new Intent(SignInActivity.this, GpActivity.class);
                Intent insureIntent = new Intent(SignInActivity.this, InsuranceActivity.class);
                Intent medHisIntent = new Intent(SignInActivity.this, MedicalHistoryActivity.class);
                Intent homeIntent = new Intent(SignInActivity.this, HomeActivity.class);


                //  String consString = con.toString();

              //  dbAccess = getDBAccess();
              //  dbAccess.open();
                dbAccess = new DatabaseAccess();
                String emailIn = email.getText().toString().trim();
                String passwdIn = password.getText().toString().trim();
                String email = dbAccess.getEmail(emailIn);
                Log.wtf(TAG,email);

                String passwd = dbAccess.getPassword(emailIn);

                Log.wtf(TAG,passwd);

                if (email.equals("error") || passwd.equals("error"))
                    Toast.makeText(SignInActivity.this, "Error unable to connect to DataBase", Toast.LENGTH_SHORT).show();

                else if (emailIn.equals("") && passwdIn.equals(""))
                    Toast.makeText(SignInActivity.this, "Error please enter email and password ", Toast.LENGTH_SHORT).show();
                else if (email.equals(emailIn) && passwd.equals(passwdIn)) {
                    Toast.makeText(SignInActivity.this, "Sucess", Toast.LENGTH_SHORT).show();

                    loggedInEmail = email;
                    loggedInPassword = passwd;
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
              //  dbAccess.close();


            }
        });
    }
/*
    private DatabaseAccess getDBAccess() {
        return DatabaseAccess.getInstance(getApplicationContext());
    }

 */

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean gpExists() {
        //dbAccess.open();
        return dbAccess.gpExists();
    }

    public boolean medHisExists() {
       // dbAccess.open();
        return dbAccess.medHisExists();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean insurerExists() {
       // dbAccess.open();
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