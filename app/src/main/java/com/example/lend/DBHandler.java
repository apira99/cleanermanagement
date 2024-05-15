package com.example.lend;

import static android.provider.SimPhonebookContract.SimRecords.PHONE_NUMBER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.ref.Cleaner;
import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "LibraryManager.db";
    private static final int DATABASE_VERSION = 2;

    // User Table
    private static final String TABLE_USERS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";

    // Book Table
    private static final String TABLE_CLEANER = "Cleaner";
    private static final String CLEANER_ID = "CLEANER_ID";
    private static final String NAME = "NAME";
    private static final String CLEANER_PHONENUMBER = "PHONE_NUMBER";

    // SQL Statement to create the "Users" table
    private static final String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_USERNAME + " TEXT,"
            + KEY_PASSWORD + " TEXT,"
            + KEY_EMAIL + " TEXT" + ");";

    // SQL Statement to create the "Book" table
    private static final String CREATE_TABLE_CLEANER= "CREATE TABLE " + TABLE_CLEANER + "("
            + CLEANER_ID + " VARCHAR(13),"
            + NAME + " VARCHAR(30),"
            + CLEANER_PHONENUMBER + " VARCHAR(20),"
            + "PRIMARY KEY (" + CLEANER_ID + "));";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create Users table
        db.execSQL(CREATE_USERS_TABLE);

        // Create Book table
        db.execSQL(CREATE_TABLE_CLEANER);


}
    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop all tables in the reverse order of their creation dependencies
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLEANER);
        // Call onCreate to recreate the database with the updated schema
        onCreate(db);
    }

// CRUD methods for Users

    public void addUser(String username, String password, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_PASSWORD, password);
        values.put(KEY_EMAIL, email);
        db.insert(TABLE_USERS, null, values);

        long result = db.insert("users", null, values);
        if (result == -1) {
            Log.e("DB_ERROR", "Failed to insert data");
        } else {
            Log.d("DB_SUCCESS", "Data inserted successfully");
        }
        db.close();
    }

    public Cursor getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID, KEY_USERNAME, KEY_EMAIL},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        return cursor;
    }

    public int updateUser(int id, String username, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_EMAIL, email);
        return db.update(TABLE_USERS, values, KEY_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_USERS, KEY_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS, new String[]{KEY_ID},
                KEY_USERNAME + "=? AND " + KEY_PASSWORD + "=?",
                new String[]{username, password}, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        return cursorCount > 0;
    }

    // Books CRUD Operations
    public void addcleaner(String cleanerId, String name, String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CLEANER_ID, cleanerId);
        values.put(NAME, name);
        values.put(CLEANER_PHONENUMBER,phonenumber);
        db.insert(TABLE_CLEANER, null, values);
        db.close();
    }
    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_CLEANER;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }


    public int UpdateCleaner(String cleanerId, String name, String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,name);
        values.put(CLEANER_PHONENUMBER, phonenumber);
        return db.update(TABLE_CLEANER, values, CLEANER_ID + " = ?", new String[]{cleanerId});
    }



}
