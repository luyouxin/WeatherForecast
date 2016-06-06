package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class YestodayInfo {

    /**
     * cityCode : 101010100
     * date : 2016-05-08
     * humidityMax : 55
     * humidityMin : 29
     * precipitationMax : 0
     * precipitationMin : 0
     * tempMax : 26
     * tempMin : 12
     * weatherEnd : 多云
     * weatherStart : 晴
     * windDirectionEnd : 西南风
     * windDirectionStart : 南风
     * windMax : 4
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

    public YestodayInfo(String cityCode, String windMin, String windMax, String windDirectionStart, String windDirectionEnd, String weatherStart, String weatherEnd, String tempMin, String tempMax, String precipitationMin, String precipitationMax, String humidityMin, String humidityMax, String date) {
        this.cityCode = cityCode;
        this.windMin = windMin;
        this.windMax = windMax;
        this.windDirectionStart = windDirectionStart;
        this.windDirectionEnd = windDirectionEnd;
        this.weatherStart = weatherStart;
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
