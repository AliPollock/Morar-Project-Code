package com.example.timebookerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
        implements AddUserProjectDialog.AddUserProjectDialogListener,
        RemoveUserProjectDialog.RemoveUserProjectDialogListener,
        CreateProjectDialog.CreateProjectDialogListener,
        DeleteProjectDialog.DeleteProjectDialogListener,
        ChangeUserTypeDialog.ChangeUserTypeDialogListener {


    private Button logoutBtn;
    private Button viewProjectsBtn;
    private TextView usernameDisplay;
    private Button addUserToProject;
    private Button removeUserFromProject;
    private Button createProject;
    private Button deleteProject;
    private Button changeUserType;
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

        removeUserFromProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRemoveUserProjectDialog();

            }
        });

        createProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateProjectDialog();
            }
        });

        deleteProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteProjectDialog();
            }
        });

        changeUserType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeUserTypeDialog();
            }
        });

    }

    private void setupUIViews() {
        usernameDisplay = (TextView) findViewById(R.id.usernameDisplay);
        logoutBtn = (Button) findViewById(R.id.logoutbtn);
        deleteProject = (Button) findViewById(R.id.deleteProject);
        createProject = (Button) findViewById(R.id.createProject);
        viewProjectsBtn = (Button) findViewById(R.id.viewProjects);
        addUserToProject = (Button) findViewById(R.id.addUserToProjectBtn);
        addUserToProject.setVisibility(View.GONE);
        removeUserFromProject = (Button) findViewById(R.id.removeUserFromProjectBtn);
        removeUserFromProject.setVisibility(View.GONE);
        changeUserType = (Button) findViewById(R.id.changeUserType);
        changeUserType.setVisibility(View.GONE);
        db = new DatabaseHelper(this);
    }

    public void checkUserType(){
        if (LoginActivity.timeBooker != null && LoginActivity.admin == null) {
            usernameDisplay.setText(LoginActivity.timeBooker.getUsername());
        } else if (LoginActivity.timeBooker == null && LoginActivity.admin != null) {
            usernameDisplay.setText(LoginActivity.admin.getUsername());
            addUserToProject.setVisibility(View.VISIBLE);
            removeUserFromProject.setVisibility(View.VISIBLE);
            changeUserType.setVisibility(View.VISIBLE);
        } else {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            Toast.makeText(this,"An error has occurred, please login again", Toast.LENGTH_LONG).show();
        }
    }



    /**
     * Functions that open the dialogs
     */

    public void openAssignUserProjectDialog(){
        AddUserProjectDialog addUserProjectDialog = new AddUserProjectDialog();
        addUserProjectDialog.show(getSupportFragmentManager(), "Add user project");
    }

    public void openRemoveUserProjectDialog(){
        RemoveUserProjectDialog removeUserProjectDialog = new RemoveUserProjectDialog();
        removeUserProjectDialog.show(getSupportFragmentManager(), "Remove user project");
    }

    public void openCreateProjectDialog(){
        CreateProjectDialog createProjectDialog = new CreateProjectDialog();
        createProjectDialog.show(getSupportFragmentManager(), "Create project");
    }

    public void openDeleteProjectDialog(){
        DeleteProjectDialog deleteProjectDialog = new DeleteProjectDialog();
        deleteProjectDialog.show(getSupportFragmentManager(), "Delete project");
    }

    public void changeUserTypeDialog(){
        ChangeUserTypeDialog changeUserTypeDialog = new ChangeUserTypeDialog();
        changeUserTypeDialog.show(getSupportFragmentManager(), "Add user project");
    }

    /**
     * Function called by dialogue which will write the data gathered in the dialog to the database
     * @param username
     * @param project
     */

    /**
     * Functions that use dialog text to edit the database
     */

    @Override
    public void applyAddTexts(String username, String project) {
        if (db.addUserProject(username, project) == true) {
            toastMessage("User " + username + " has been added to the " + project + " project.");
        } else {
            toastMessage("There was an error adding " + username + "to the " + project + " project");
        }
    }

    @Override
    public void applyRemoveTexts(String username, String project) {
        if (db.removeUserProject(username, project) == true) {
            toastMessage("User " + username + " has been removed from the " + project + " project.");
        } else {
            toastMessage("There was an error removing " + username + "from the " + project + " project");
        }
    }

    @Override
    public void applyCreateTexts(String project) {
        if (db.addProject(project) == true) {
            toastMessage(" project: " + project + " has been created");
        } else {
            toastMessage("There was an error creating the " + project + " project");
        }
    }

    @Override
    public void applyDeleteTexts(String project) {
        if (db.deleteProject(project) == true) {
            toastMessage(" project: " + project + " has been deleted");
        } else {
            toastMessage("There was an error deleting the " + project + " project");
        }
    }

    @Override
    public void applyChangeUserTypeTexts(String user) {
        Boolean checkUser = db.checkUsername(user);

        if (!checkUser) {
            toastMessage("User does not exist");
        } else if (db.getUserType(user).equals("admin")) {
            toastMessage("User is already an admin");
        } else {
            db.changeUserType(user);
            toastMessage("User successfully changed to Admin");
        }
    }


    public void logout(){
        LoginActivity.timeBooker = null;
        LoginActivity.admin = null;
        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    public void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
