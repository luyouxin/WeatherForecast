package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/5.
 */
public class LocationWeatherInfo {
    public String name;//地点名字
    public boolean isCurrentLocation;//是否是当前位置
    public String temp;//温度
    public String weather;//天气
    public boolean isDelete;
    public String imgTitle;
    public String cidID;
    public LocationWeatherInfo(String name, String imgTitle, boolean isDelete, String weather, String temp, boolean isCurrentLocation,String cidID) {
        this.name = name;
        this.imgTitle = imgTitle;
        this.isDelete = isDelete;
        this.weather = weather;
        this.temp = temp;
        this.isCurrentLocation = isCurrentLocation;
        this.cidID=cidID;
    }
}
