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

    public static TimeBooker timeBooker;
    public static Admin admin;
    private Button loginBtn;
    private EditText editUsername;
    private EditText editPassword;
    private TextView registerLink;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = (EditText) findViewById(R.id.editUsername);
        editPassword = (EditText) findViewById(R.id.editPassword);
        loginBtn = (Button) findViewById(R.id.loginButton);
        registerLink = (TextView) findViewById(R.id.registerLink);
        db = new DatabaseHelper(this);

        setupUIViews();
        this.logout();

        //inserting original admin account
        Boolean insert = db.addOriginalAdminAccount();


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    String user_username = editUsername.getText().toString().trim();
                    String user_password = editPassword.getText().toString().trim();
                    Boolean checkUserPass = db.checkUsernamePassword(user_username, user_password);

                    if(checkUserPass){
                        String user_type = db.getUserData(user_username, "usertype");
                        if(user_type.equals("timeBooker")) {
                            timeBooker = new TimeBooker(user_username, user_password);
                        } else if (user_type.equals("admin")) {
                            admin = new Admin(user_username, user_password);
                        }
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                    } else {
                        toastMessage("Invalid credentials.");
                    }
                } else {}

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

    public void logout(){
        TimeBooker timeBooker = null;
        Admin admin = null;
    }
}