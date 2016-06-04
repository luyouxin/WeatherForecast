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

    private String cityCode;
    private String date;
    private String humidityMax;
    private String humidityMin;
    private String precipitationMax;
    private String precipitationMin;
    private String tempMax;
    private String tempMin;
    private String weatherEnd;
    private String weatherStart;
    private String windDirectionEnd;
    private String windDirectionStart;
    private String windMax;
    private String windMin;

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

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(String humidityMax) {
        this.humidityMax = humidityMax;
    }

    public String getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(String humidityMin) {
        this.humidityMin = humidityMin;
    }

    public String getPrecipitationMax() {
        return precipitationMax;
    }

    public void setPrecipitationMax(String precipitationMax) {
        this.precipitationMax = precipitationMax;
    }

    public String getPrecipitationMin() {
        return precipitationMin;
    }

    public void setPrecipitationMin(String precipitationMin) {
        this.precipitationMin = precipitationMin;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getWeatherEnd() {
        return weatherEnd;
    }

    public void setWeatherEnd(String weatherEnd) {
        this.weatherEnd = weatherEnd;
    }

    public String getWeatherStart() {
        return weatherStart;
    }

    public void setWeatherStart(String weatherStart) {
        this.weatherStart = weatherStart;
    }

    public String getWindDirectionEnd() {
        return windDirectionEnd;
    }

    public void setWindDirectionEnd(String windDirectionEnd) {
        this.windDirectionEnd = windDirectionEnd;
    }

    public String getWindDirectionStart() {
        return windDirectionStart;
    }

    public void setWindDirectionStart(String windDirectionStart) {
        this.windDirectionStart = windDirectionStart;
    }

    public String getWindMax() {
        return windMax;
    }

    public void setWindMax(String windMax) {
        this.windMax = windMax;
    }

    public String getWindMin() {
        return windMin;
    }

    public void setWindMin(String windMin) {
        this.windMin = windMin;
    }
}
