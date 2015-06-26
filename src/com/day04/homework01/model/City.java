package com.day04.homework01.model;

/**
 * Created with IntelliJ IDEA
 * Project: day_04_homework01
 * User: longfeisun
 */
public class City {
    private int id;
    private String cityCode;
    private String cityName;
    private String provinceCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityCode='" + cityCode + '\'' +
                ", id=" + id +
                ", cityName='" + cityName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                '}';
    }
}
