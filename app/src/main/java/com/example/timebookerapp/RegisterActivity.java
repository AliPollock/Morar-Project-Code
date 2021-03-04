package com.example.timebookerapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private Button btnRegister;
    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private TextView loginLink;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        setupUIViews();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    String user_username = editUsername.getText().toString().trim();
                    String user_email = editEmail.getText().toString().trim();
                    String user_password = editPassword.getText().toString().trim();
                    Boolean checkUser = db.checkUsername(user_username);

                    if (!checkUser) {
                        Boolean insert = db.addData(user_username, user_email, user_password);
                        if(insert) {
                            toastMessage("Registered successfully!");
                            Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            toastMessage("Registration failed");
                        }
                    } else {
                        toastMessage("username already exists, please chose another one");
                    }
                } else {
                    toastMessage("You must put something in all of the fields!");
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setupUIViews() {
        editUsername = (EditText) findViewById(R.id.enterUsername);
        editEmail = (EditText) findViewById(R.id.enterEmail);
        editPassword = (EditText) findViewById(R.id.enterPassword);
        btnRegister = (Button) findViewById(R.id.registerButton);
        loginLink = (TextView) findViewById(R.id.loginLink);

        db = new DatabaseHelper(this);

    }


    private Boolean validate(){
        Boolean result = false;
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();
        String email = editEmail.getText().toString();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()){
            toastMessage("You must complete all fields");
        } else {
            result = true;
        }
        return result;
    }


    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void register(View view) {
    }

    public void login(View view) {
    }
}