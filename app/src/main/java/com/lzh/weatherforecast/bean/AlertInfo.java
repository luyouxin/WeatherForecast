package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class AlertInfo {
    public String abnormal;
    public String city_code;
    public String detail;
    public String holiday;
    public String level;
    public String pub_time;
    public String title;
    public String type;

    public AlertInfo(String type, String title, String pub_time, String level, String holiday, String detail, String abnormal, String city_code) {
        this.type = type;
        this.title = title;
        this.pub_time = pub_time;
        this.level = level;
        this.holiday = holiday;
        this.detail = detail;
        this.abnormal = abnormal;
        this.city_code = city_code;
    }

}
