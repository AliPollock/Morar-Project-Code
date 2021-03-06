package com.example.timebookerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.DateFormatSymbols;

public class UserProjectListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listViewProject;
    String[] projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_project_list);
        listViewProject = findViewById(R.id.listViewProject);
        projects = new DateFormatSymbols().getMonths();
        ArrayAdapter<String> projectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projects);
        listViewProject.setAdapter(projectAdapter);
        listViewProject.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String project = parent.getItemAtPosition(position).toString();
        Toast.makeText(getApplicationContext(), "clicked: " + project, Toast.LENGTH_LONG).show();
    }
}