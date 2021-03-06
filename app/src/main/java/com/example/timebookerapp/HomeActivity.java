package com.example.timebookerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity implements AddUserProjectDialog.AddUserProjectDialogListener {


    private Button logoutBtn;
    private Button viewProjectsBtn;
    private TextView usernameDisplay;
    private Button addUserToProject;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setupUIViews();
        checkUserType();


        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

            }
        });

        viewProjectsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserProjectListActivity.class);
                startActivity(intent);
            }
        });

        addUserToProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAssignUserProjectDialog();

            }
        });


    }

    private void setupUIViews() {
        usernameDisplay = (TextView) findViewById(R.id.usernameDisplay);
        logoutBtn = (Button) findViewById(R.id.logoutbtn);
        viewProjectsBtn = (Button) findViewById(R.id.viewProjects);
        addUserToProject = (Button) findViewById(R.id.addUserToProjectBtn);
        addUserToProject.setVisibility(View.GONE);
        db = new DatabaseHelper(this);
    }

    public void checkUserType(){
        if (LoginActivity.timeBooker != null && LoginActivity.admin == null) {
            usernameDisplay.setText(LoginActivity.timeBooker.getUsername());
        } else if (LoginActivity.timeBooker == null && LoginActivity.admin != null) {
            usernameDisplay.setText(LoginActivity.admin.getUsername());
            addUserToProject.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this,"An error has occured, please login again", Toast.LENGTH_LONG).show();
        }
    }


    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void openAssignUserProjectDialog(){
        AddUserProjectDialog addUserProjectDialog = new AddUserProjectDialog();
        addUserProjectDialog.show(getSupportFragmentManager(), "Add user project");
    }

    /**
     * Function called by dialogue which will write the data gathered in the dialog to the database
     * @param username
     * @param project
     */

    @Override
    public void applyTexts(String username, String project) {
        db.addUserProject(username, project);
    }

    public void logout(){
        LoginActivity.timeBooker = null;
        LoginActivity.admin = null;
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}