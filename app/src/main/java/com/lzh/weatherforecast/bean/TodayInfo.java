package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class TodayInfo {

    /**
     * cityCode : 101010100
     * date : 2016-05-09
     * humidityMax : 64
     * humidityMin : 50
     * precipitationMax : 0
     * precipitationMin : 0
     * tempMax : 19
     * tempMin : 15
     * weatherEnd : 小雨
     * weatherStart : 多云
     * windDirectionEnd : 东北风
     * windDirectionStart : 西南风
     * windMax : 3
     * windMin : 0
     */

    public String cityCode;
    public String date;
    public String humidityMax;
    public String humidityMin;
    public String precipitationMax;
    public String precipitationMin;
    public String tempMax;
    public String tempMin;
    public String weatherEnd;
    public String weatherStart;
    public String windDirectionEnd;
    public String windDirectionStart;
    public String windMax;
    public String windMin;

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
