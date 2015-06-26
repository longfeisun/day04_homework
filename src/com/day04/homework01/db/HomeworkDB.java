package com.day04.homework01.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.day04.homework01.model.City;
import com.day04.homework01.model.District;
import com.day04.homework01.model.Province;

/**
 * Created with IntelliJ IDEA
 * Project: day_04_homework01
 * User: longfeisun
 */
public class HomeworkDB {

    private static final String DB_NAME = "homework.db";
    private static final int DB_VERSION = 1;

    private static HomeworkDB homeworkDB;
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase db;


    private HomeworkDB(Context context) {
        dbOpenHelper = new DBOpenHelper(context, DB_NAME, null, DB_VERSION);
        db = dbOpenHelper.getWritableDatabase();
    }

    public static HomeworkDB getInstance(Context context) {

        if(homeworkDB == null){
            homeworkDB = new HomeworkDB(context);
        }
        return homeworkDB;
    }

    /**
     * 保存 省 信息到数据库
     * @param province
     */
    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_code",province.getProvinceCode());
            values.put("province_Name",province.getProvinceName());
            db.insert("province", null, values);
        }
    }


    /**
     * 保存 市 信息到数据库
     * @param city
     */
    public void saveCity(City city){

        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_code",city.getCityCode());
            values.put("city_name",city.getCityName());
            values.put("province_code",city.getProvinceCode());
            db.insert("city", null, values);
        }
    }

    /**
     * 保存 区 信息到数据库
     * @param district
     */
    public void saveDistrict(District district){
        if(district != null){
            ContentValues values = new ContentValues();
            values.put("district_code", district.getDistrictCode());
            values.put("district_name", district.getDistrictName());
            values.put("city_code", district.getCityCode());
        }
    }

    public void beginTransaction(){
        db.beginTransaction();
    }

    public void endTransaction(){
        db.endTransaction();
    }

}
