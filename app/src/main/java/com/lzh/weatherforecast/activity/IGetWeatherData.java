package com.lzh.weatherforecast.activity;

import com.lzh.weatherforecast.bean.CityInfo;

import java.util.List;

/**
 * Created by lzh on 2016/6/2.
 */
public interface IGetWeatherData {
    void getWeatherData(String imei,List<CityInfo>list, GetWeatherDataListener listener);
}
