package com.example.timebookerapp;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private String accountType;
    public ArrayList<Project> projectList;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountType = "";
        this.projectList = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ArrayList<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(ArrayList<Project> projectList) {
        this.projectList = projectList;
    }
}
