package com.example.beluxe;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String dbName = "Laundry.db";

    //list of tables
    private static final String loginNameTable = "tb_login";
    private static final String laundryNameTable = "tb_laundry";

    //database version
    private static final int DATABASE_VERSION = 1;


    //create table for login
    private static final String LOGIN_TABLE = "CREATE TABLE " +loginNameTable+" (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "username varchar(30)," +
            "password varchar(30))";

    //create table for laundry
    private static final String LAUNDRY_TABLE = "CREATE TABLE " +laundryNameTable+ " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nama varchar(30)," +
            "harga varchar(30)," +
            "kilo varchar(30))";

    // table field for laundry and also for login (id)
    public static final String COL_1 = "id";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "kilo";
    public static final String COL_4 = "harga";

    // table field for login
    public static final String COL_USER = "username";
    public static final String COL_PASS = "password";

    public DatabaseHelper(Context context) {
        super(context, dbName, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LOGIN_TABLE);
        db.execSQL(LAUNDRY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String TABLE_LOGIN = "DROP TABLE IF EXISTS " + loginNameTable;
        String TABLE_LAUNDRY = "DROP TABLE IF EXISTS " + laundryNameTable;
        db.execSQL(TABLE_LOGIN);
        db.execSQL(TABLE_LAUNDRY);
        onCreate(db);
    }

    public boolean insertDataLaundry(String nama, String kilo, String harga) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //retrieving value from each keys
        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, kilo);
        contentValues.put(COL_4, "Rp " + harga);
        long result = db.insert(laundryNameTable, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    //function for inserting data login
    public void insertLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //retrieving value from each keys
        contentValues.put(COL_USER, username);
        contentValues.put(COL_PASS, password);
        db.insert(loginNameTable, null, contentValues);
    }

    //function for checking or auth
    public boolean checkLogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a query to get the user's data
        String query = "SELECT * FROM "+ loginNameTable +" WHERE username = ? AND password = ?";

        // Execute the query
        Cursor cursor = db.rawQuery(query, new String[]{username, password});

        // Check if the user exists
        if (cursor.getCount() > 0) {
            // The user exists, so they are authenticated
            return true;
        } else {
            // The user does not exist, so they are not authenticated
            return false;
        }
    }

    public Cursor fetch() {
        String[] columns = new String[] {COL_2, COL_3};
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(laundryNameTable, columns, null, null, null, null, null);
        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public ArrayList<HashMap<String, String>> getAllLaundry() {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        String query = "SELECT * FROM " + laundryNameTable;
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery(query, null);
        if (cursor.moveToNext()) {
            do {
                HashMap<String, String> map = new HashMap<>();
                map.put("id", cursor.getString(0));
                map.put("nama", cursor.getString(1));
                map.put("kilo", cursor.getString(2));
                map.put("harga", cursor.getString(3));
                list.add(map);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void deleteLaundry(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(laundryNameTable, COL_1 + " = ?",
                new String[]{String.valueOf(id)});
    }

    public int updateLaundry(int id, String nama, String harga, String kilo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2, nama);
        contentValues.put(COL_3, kilo);
        contentValues.put(COL_4, harga);

        return db.update(laundryNameTable, contentValues, COL_1 + " = ?",
                new String[]{String.valueOf(id)}
        );
    }


}
