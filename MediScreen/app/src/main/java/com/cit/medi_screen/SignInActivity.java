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


    private static String loggedInEmail;
    private static String loggedInPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
                Intent gpIntent = new Intent(SignInActivity.this, GpActivity.class);

                DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
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
                    boolean gpExists = dbAccess.gpExists();
                    if (gpExists)
                        Toast.makeText(SignInActivity.this, "gp exists", Toast.LENGTH_SHORT).show();


                    else
                        startActivity(gpIntent);
                } else {
                    Toast.makeText(SignInActivity.this, "Error incorrect email and/or password", Toast.LENGTH_SHORT).show();
                }
                dbAccess.close();


            }
        });
    }

    public static String getLoggedInEmail() {
        return loggedInEmail;
    }

    public String getLoggedInPassword() {
        return loggedInPassword;
    }

}


