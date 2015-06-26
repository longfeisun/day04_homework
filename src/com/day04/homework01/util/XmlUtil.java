package com.day04.homework01.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.XmlResourceParser;
import com.day04.homework01.R;
import com.day04.homework01.db.HomeworkDB;
import com.day04.homework01.model.City;
import com.day04.homework01.model.District;
import com.day04.homework01.model.Province;

/**
 * Created with IntelliJ IDEA
 * Project: day_04_homework01
 * User: longfeisun
 */
public class XmlUtil {

    /**
     * 从本地xml文件中 省市区 信息保存到数据库
     * @param context
     */
    public static void initWeatherCityXMl(Context context) {
        HomeworkDB db = HomeworkDB.getInstance(context);
        XmlResourceParser parser = context.getResources().getXml(R.xml.citys_weather);
        Province province = null;
        City city = null;
        District district = null;
        db.beginTransaction();
        try {
            //取第一个事件
            int eventType = parser.getEventType();

            // 只要没有取到文档结束标识就继续
            while (eventType != XmlResourceParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlResourceParser.START_DOCUMENT:

                        break;
                    case XmlResourceParser.START_TAG:
                        String startTagName = parser.getName();
                        if ("p".equals(startTagName)) {
                            province = new Province();
                            String provinceCode = parser.getAttributeValue(0);
                            province.setProvinceCode(provinceCode);
                        } else if ("pn".equals(startTagName)) {
                            String provinceName = parser.nextText();
                            province.setProvinceName(provinceName);
                        } else if ("c".equals(startTagName)) {
                            city = new City();
                            String cityCode = parser.getAttributeValue(0);
                            city.setCityCode(cityCode);
                            city.setProvinceCode(province.getProvinceCode());
                        } else if ("cn".equals(startTagName)) {
                            String cityName = parser.nextText();
                            city.setCityName(cityName);
                        } else if ("d".equals(startTagName)) {
                            district = new District();
                            String districtCode = parser.getAttributeName(0);
                            String districtName = parser.nextText();

                            district.setDistrictCode(districtCode);
                            district.setDistrictName(districtName);
                            district.setCityCode(city.getCityCode());

                            if(district != null){
                                db.saveDistrict(district);
                                district = null;
                            }
                        }
                        break;
                    case XmlResourceParser.END_TAG:
                        String endTagName = parser.getName();

                        if("c".equals(endTagName)){
                            if(city != null){
                                db.saveCity(city);
                                city = null;
                            }
                        } else  if ("p".equals(endTagName)){
                            if(province != null){
                                db.saveProvince(province);
                                province = null;
                            }
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
            SharedPreferences pref = context.getSharedPreferences("homework.xml", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("hasInitWeatherCity", true);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }


    }


}
