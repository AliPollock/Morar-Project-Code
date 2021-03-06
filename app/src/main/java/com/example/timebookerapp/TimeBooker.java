package com.example.timebookerapp;

public class TimeBooker extends User {

    public TimeBooker(String username, String password) {
        super(username, password);
        setAccountType("TimeBooker");
    }
}
