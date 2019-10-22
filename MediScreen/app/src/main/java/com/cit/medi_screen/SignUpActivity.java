package com.cit.medi_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class SignUpActivity extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    EditText passwordRepeat;


    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        firstName = (EditText) findViewById(R.id.firstNameEditText);
        lastName = (EditText) findViewById(R.id.lastNameEditText);
        email = (EditText) findViewById(R.id.emailEditText);
        password = (EditText) findViewById(R.id.passwdEditText);
        passwordRepeat = (EditText) findViewById(R.id.passwdRepeatEditText);
        signUp = (Button) findViewById(R.id.signUpButtonComplete);
        signUp.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          DatabaseAccess dbAcess = DatabaseAccess.getInstance(getApplicationContext());
                                          dbAcess.open();
                                          String fName = firstName.getText().toString().trim();
                                          String lName = lastName.getText().toString().trim();
                                          String emailIn = email.getText().toString().trim();
                                          String passwd = password.getText().toString().trim();
                                          String passwdRpt = passwordRepeat.getText().toString().trim();
                                          if (fName.equals("") || lName.equals("") || emailIn.equals("") || passwd.equals("") || passwdRpt.equals(""))
                                              Toast.makeText(SignUpActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                                          else {
                                              if (passwd.equals(passwdRpt)) {
                                                  long val = dbAcess.addUser(fName, lName, emailIn, passwd);
                                                  if (val > 0) {
                                                      Toast.makeText(SignUpActivity.this, "You have registered", Toast.LENGTH_SHORT).show();
                                                      dbAcess.close();
                                                      Intent moveToSignIn = new Intent(SignUpActivity.this, SignInActivity.class);
                                                      startActivity(moveToSignIn);

                                                  } else {
                                                      Toast.makeText(SignUpActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
                                                      dbAcess.close();
                                                  }

                                              } else
                                                  Toast.makeText(SignUpActivity.this, "Error: Passwords do not match", Toast.LENGTH_SHORT).show();


                                          }
                                      }
                                  }


        );


    }

}
