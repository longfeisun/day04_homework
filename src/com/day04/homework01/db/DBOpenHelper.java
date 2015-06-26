package com.day04.homework01.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created with IntelliJ IDEA
 * Project: day_04_homework01
 * User: longfeisun
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    // 建表语句：省
    private static final String CREATE_PROVINCE = "create table province ("
                                                + " id integer primary key autoincrement , "
                                                + " province_code , "
                                                + " province_name "
                                                + ")";
    // 建表语句：市
    private static final String CREATE_CITY = "create table city ("
                                            + " id integer primary key autoincrement , "
                                            + " city_code , "
                                            + " city_name , "
                                            + " province_code "
                                            + ")";
    // 建表语句：区
    private static final String CREATE_DISTRICT = "create table district ("
                                                + " id integer primary key autoincrement , "
                                                + " district_code , "
                                                + " district_name , "
                                                + " city_code "
                                                + ")";



    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_DISTRICT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
