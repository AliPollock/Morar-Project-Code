package com.example.timebookerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DateFormatSymbols;

public class UserProjectListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listViewProject;
    String[] projects;
    DatabaseHelper db;
    private Button homeBtn;
    public static Project currentProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_project_list);

        setupUIViews();

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProjectListActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String project = parent.getItemAtPosition(position).toString();
        currentProject = new Project(project);
        Intent intent = new Intent(UserProjectListActivity.this, ProjectActivity.class);
        startActivity(intent);
    }

    private void setupUIViews() {
        db = new DatabaseHelper(this);
        listViewProject = findViewById(R.id.listViewProject);
        homeBtn = findViewById(R.id.homeBtn);

        if (LoginActivity.timeBooker != null && LoginActivity.admin == null) {
            projects = db.getUserProjects(LoginActivity.timeBooker.getUsername());
        } else if (LoginActivity.timeBooker == null && LoginActivity.admin != null) {
            projects = db.getUserProjects(LoginActivity.admin.getUsername());
        } else {
            Intent intent = new Intent(UserProjectListActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this,"An error has occured, please login again", Toast.LENGTH_LONG).show();
        }
        ArrayAdapter<String> projectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projects);
        listViewProject.setAdapter(projectAdapter);
        listViewProject.setOnItemClickListener(this);
    }

}