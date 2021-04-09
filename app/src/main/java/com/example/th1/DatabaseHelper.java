package com.example.th1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "triplist.db";
    public static final String TABLE_NAME = "saved";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "START_POINT";
    public static final String COL_3 = "END_POINT";
    public static final String COL_5 = "DISTANCE";
    public static final String COL_4 = "FUEL_CONS";
    public static final String COL_6 = "FUEL_NEEDED";
    public static final String COL_7 = "FUEL_COST";
    
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    //creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "START_POINT TEXT, END_POINT TEXT, DISTANCE INTEGER, FUEL_CONS DOUBLE, FUEL_NEEDED DOUBLE, FUEL_COST DOUBLE)");
    }


    // called when the database needs to be upgraded to the new schema version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + DATABASE_NAME + "'");
        onCreate(db);
    }


    //adding a record
    public boolean insertData(String start, String end, int d, double fc, double totalf, double totalcost) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, start);
        contentValues.put(COL_3, end);
        contentValues.put(COL_4, d);
        contentValues.put(COL_5, fc);
        contentValues.put(COL_6, totalf);
        contentValues.put(COL_7, totalcost);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }


    //retrieving single record matching the id
    public Cursor viewRecord(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE ID=" +id, null);
        return res;
    }


    //updating the record matching the id
    public boolean updateRecord(String id, String start, String end,  int d, double fc, double totalf, double totalcost){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_2, start);
        contentValues.put(COL_3, end);
        contentValues.put(COL_4, d);
        contentValues.put(COL_5, fc);
        contentValues.put(COL_6, totalf);
        contentValues.put(COL_7, totalcost);
        sqLiteDatabase.update(TABLE_NAME, contentValues,"ID = ?",
                new String[] {id});
        return true;
    }


    //retrieving all records
    public Cursor viewAllRecords() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }



}
