package com.google.samples.quickstart.signin.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by brayg_000 on 09/08/2016.
 */
public class DBHandler extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "shopsInfo";
    // Contacts table name
    private static final String TABLE_SHOPS = "shops";
    // Shops Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_SH_ADDR = "shop_address";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE" + TABLE_SHOPS + "("
                + KEY_ID + "INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_SH_ADDR + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
// Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHOPS);
// Creating tables again
        onCreate(db);
    }

    // Adding new user
    public void addShop(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName()); // User Name
        values.put(KEY_SH_ADDR, user.getAddress()); // User Phone Number

// Inserting Row
        db.insert(TABLE_SHOPS, null, values);
        db.close(); // Closing database connection
    }

    // Getting one shop
    public User getShop(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SHOPS, new String[]{KEY_ID,
                        KEY_NAME, KEY_SH_ADDR}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User contact = new User(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
// return shop
        return contact;
    }

    // Getting All Shops
    public List<User> getAllShops() {
        List<User> userList = new ArrayList<User>();
// Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_SHOPS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(0)));
                user.setName(cursor.getString(1));
                user.setAddress(cursor.getString(2));
// Adding contact to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

// return contact list
        return userList;
    }

    // Getting shops Count
    public int getShopsCount() {
        String countQuery = "SELECT * FROM " + TABLE_SHOPS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

// return count
        return cursor.getCount();
    }

    // Updating a user
    public int updateShop(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getName());
        values.put(KEY_SH_ADDR, user.getAddress());

// updating row
        return db.update(TABLE_SHOPS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
    }

    // Deleting a user
    public void deleteShop(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHOPS, KEY_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }
}
