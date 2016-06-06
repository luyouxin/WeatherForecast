package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class TodayInfo {



    public String cityCode; //城市id
    public String date;//日期
    public String humidityMax;//最大湿度;
    public String humidityMin;//最小湿度
    public String precipitationMax;
    public String precipitationMin;
    public String tempMax;//最高温度
    public String tempMin;//最低温度
    public String weatherEnd;//结束的天气
    public String weatherStart;//开始的天气
    public String windDirectionEnd;//结束的风向
    public String windDirectionStart;//开始的风向
    public String windMax;//最大风速
    public String windMin;//最小风速

    public TodayInfo(String cityCode, String windMax, String windMin, String windDirectionStart, String weatherStart, String windDirectionEnd, String weatherEnd, String tempMin, String tempMax, String precipitationMin, String precipitationMax, String humidityMin, String humidityMax, String date) {
        this.cityCode = cityCode;
        this.windMax = windMax;
        this.windMin = windMin;
        this.windDirectionStart = windDirectionStart;
        this.weatherStart = weatherStart;
        this.windDirectionEnd = windDirectionEnd;
        this.weatherEnd = weatherEnd;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.precipitationMin = precipitationMin;
        this.precipitationMax = precipitationMax;
        this.humidityMin = humidityMin;
        this.humidityMax = humidityMax;
        this.date = date;
    }
}
