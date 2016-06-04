package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/6/4.
 */
public class FutureWeather {
    public String time;
    public String weatherTitle;
    public String tempRange;

    public FutureWeather(String time, String weatherTitle, String tempRange) {
        this.time = time;
        this.weatherTitle = weatherTitle;
        this.tempRange = tempRange;
    }
}
