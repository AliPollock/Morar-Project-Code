package com.example.timebookerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "user_details.db";
    private static final String USER_TABLE_NAME = "user_details";
    private static final String USERCOL1 = "username";
    private static final String USERCOL2 = "email";
    private static final String USERCOL3 = "password";
    private static final String USERCOL4 = "usertype";
    private static final String PROJECT_NAME = "projectName";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDB) {
        String createUserTable = "CREATE TABLE " + USER_TABLE_NAME + "(" + USERCOL1 + " TEXT PRIMARY KEY, " + USERCOL2 + " TEXT," + USERCOL3 + " TEXT, " + USERCOL4 + " TEXT)";
        myDB.execSQL(createUserTable);

        String createProjectTable = "CREATE TABLE project ( "+ PROJECT_NAME + "TEXT PRIMARY KEY)";
        myDB.execSQL(createProjectTable);

        String createUserProjectTable = "CREATE TABLE userProject ( username TEXT , project TEXT, PRIMARY KEY (username, project))";
        myDB.execSQL(createUserProjectTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase myDB, int oldVersion, int newVersion) {
        myDB.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        onCreate(myDB);
    }

    public boolean addData(String username, String email, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("usertype", "timeBooker");

        long result = myDB.insert(USER_TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean addUserProject(String username, String project){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("project", project);

        long result = myDB.insert("userProject", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE USERNAME = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + USER_TABLE_NAME + " WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getUserData(String username, String columnName) {
        String result = "";
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + columnName + " FROM " + USER_TABLE_NAME + " WHERE USERNAME = '" + username + "'";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    result = cursor.getString(0);
                } while (cursor.moveToNext());
            }
        }
        return result;
    }

    public boolean addOriginalAdminAccount() {
        if (!this.checkUsername("admin")) {
            SQLiteDatabase myDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", "admin");
            contentValues.put("email", "admin@email.com");
            contentValues.put("password", "admin");
            contentValues.put("usertype", "admin");
            myDB.insert(USER_TABLE_NAME, null, contentValues);
            return true;
        } else {
            return false;
        }
    }
}
