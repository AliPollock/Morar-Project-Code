package com.example.timebookerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectActivity extends AppCompatActivity {

    DatabaseHelper db;
    private Button homeBtn;
    private TextView projectTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        setupUIViews();



        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProjectActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }




    private void setupUIViews() {
        db = new DatabaseHelper(this);
        homeBtn = findViewById(R.id.projectHomeBtn);
        projectTitle = (TextView) findViewById(R.id.currentProject);
        projectTitle.setText(UserProjectListActivity.currentProject.getProjectName());
    }
}