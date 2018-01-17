package com.example.rahuldebmohalder.problem1.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;


/**
 * Created by Rahul Deb Mohalder on 17-01-2018.
 */

public class DatabaseHandler  extends SQLiteOpenHelper {

    private static final String TAG = DatabaseHandler.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME= "storeinfo";

    private static final String TABLE_USER = "user";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE_NUMBER = "phoneNumber";
    private static final String KEY_IMAGE = "image";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                +  KEY_AGE + " TEXT," + KEY_EMAIL + " TEXT UNIQUE, "  + KEY_PHONE_NUMBER + " TEXT, " + KEY_IMAGE + " TEXT" + ")";

        db.execSQL(CREATE_LOGIN_TABLE);
        Log.e(TAG, "Create table on Database");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);

        onCreate(db);
    }

    public void addInfo(String name, String age, String email, String phonenumber, String image){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_AGE, age);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PHONE_NUMBER, phonenumber);
        values.put(KEY_IMAGE, image);

        long id = db.insert(TABLE_USER, null, values);
        db.close();

        Log.e(TAG, "New user info add into in row number: " + id);
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<>();
        String getQuery = "SELECT  * From " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(getQuery, null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            user.put("name", cursor.getString(1));
            user.put("age", cursor.getString(2));
            user.put("email", cursor.getString(3));
            user.put("phoneNumber", cursor.getString(4));
            user.put("image", cursor.getString(5));
        }
        cursor.close();
        db.close();

        Log.e(TAG, "Data from user table is "+user.toString());
        return user;

    }

    public void deleteUser(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USER, null, null);
        db.close();
        Log.e(TAG, "Dalete User data From Database");
    }
}
