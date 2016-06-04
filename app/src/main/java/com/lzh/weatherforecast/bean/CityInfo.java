package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/4/22.
 */
public class CityInfo {
    public String name; //城市名字
    public String cityID;//城市对应的city用于查询天气
    public boolean isCheck;//是否在列表上被选中

    public CityInfo(String name, String cityID) {
        this.name = name;
        this.cityID = cityID;
    }

    public CityInfo(String name, String cityID, boolean isCheck) {
        this.name = name;
        this.cityID = cityID;
        this.isCheck = isCheck;
    }
}
