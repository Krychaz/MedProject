package com.cit.medi_screen;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button login;
    Button signUp;
    Cursor c = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        /*final SignUpDatabaseHelper myDbHelper = new SignUpDatabaseHelper(SignInActivity.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
*/
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwdEditText);
        login = (Button) findViewById(R.id.loginButton);
        signUp = (Button) findViewById(R.id.signUpButton);
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
                DatabaseAccess dbAcess = DatabaseAccess.getInstance(getApplicationContext());
                dbAcess.open();
                String emailIn = email.getText().toString().trim();
                String passwd = password.getText().toString().trim();
                String email = dbAcess.getEmail(emailIn);

                if (email == emailIn) {
                    Toast.makeText(SignInActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                    dbAcess.close();
                } else {
                    Toast.makeText(SignInActivity.this, "Error incorrect email and/or password", Toast.LENGTH_SHORT).show();
                    dbAcess.close();

                }
            }
        });

    }
}


