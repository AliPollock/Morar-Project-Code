package com.example.timebookerapp;

public class Admin extends User{

    public Admin(String username, String password) {
        super(username, password);
        setAccountType("Admin");
    }

    public void assignUserToProject( User user){

    }

    public void changeTimeBookerToAdmin(TimeBooker user){
        user.setAccountType("Admin");
    }

    public void changeAdminToTimeBooker(Admin user){
        user.setAccountType("timeBooker");
    }

    public boolean createTimeBookerAccount(){
        return false;
    }


}
