package com.example.jessicachandra.mypocketlist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Jessica Chandra on 26/01/2018.
 */

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "uangku.db";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // buat table autentikasi untuk ingat passcode user
        String sql = "create table autentikasi(passcode text primary key);";
        Log.d("Data", "onCreate: " + sql);
        db.execSQL(sql);
        Log.d("[SQL-table autentikasi]", "onCreate: " + sql);

        sql = "create table catatan(" +
                "id integer primary key AUTOINCREMENT, " +
                "nama text null, " +
                "deskripsi text null, " +
                "nilai real null, " +
                "pemasukan int null);";
        db.execSQL(sql);
        Log.d("[SQL-table catatan]", "onCreate: " + sql);

        sql = "create table pengeluaran(" +
                "id integer primary key AUTOINCREMENT, " +
                "nama text null, " +
                "deskripsi text null, " +
                "nilai real null, " +
                "pemasukan int null);";
        db.execSQL(sql);
        Log.d("[SQL-table pengeluaran]", "onCreate: " + sql);

        sql = "create table pemasukan(" +
                "id integer primary key AUTOINCREMENT, " +
                "nama text null, " +
                "deskripsi text null, " +
                "nilai real null, " +
                "pemasukan int null);";
        db.execSQL(sql);
        Log.d("[SQL-table pemasukan]", "onCreate: " + sql);
    }


    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

    }
}