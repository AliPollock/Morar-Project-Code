package com.example.timebookerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button loginBtn;
    private EditText editUsername;
    private EditText editPassword;
    private TextView registerLink;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUIViews();



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    String user_username = editUsername.getText().toString().trim();
                    String user_password = editPassword.getText().toString().trim();
                    Boolean checkUserPass = db.checkUsernamePassword(user_username, user_password);

                    if(checkUserPass){
                        toastMessage("Sign in successful.");
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        toastMessage("Invalid credentials.");
                    }
                } else {
                    toastMessage("You must put something in all of the fields!");
                }

            }
        });

        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setupUIViews() {
        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editUsername);
        loginBtn = (Button) findViewById(R.id.loginButton);
        registerLink = (TextView) findViewById(R.id.registerLink);
        db = new DatabaseHelper(this);
    }

    private Boolean validate(){
        Boolean result = false;
        String username = editUsername.getText().toString();
        String password = editPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()){
            toastMessage("You must complete all fields");
        } else {
            result = true;
        }
        return result;
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void login(View view) {
    }

    public void register(View view) {
    }
}