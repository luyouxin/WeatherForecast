package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class AqiInfo {

    /**
     * city : 北京
     * city_id : 101010100
     * pub_time : 2016-05-09 08:00
     * aqi : 73
     * pm25 : 46
     * pm10 : 88
     * so2 : 7
     * no2 : 63
     * src : 中国环境监测总站
     * spot :
     */

    public String city;
    public String city_id;
    public String pub_time;
    public String aqi;
    public String pm25;
    public String pm10;
    public String so2;
    public String no2;
    public String src;
    public String spot;

    public AqiInfo(String city, String spot, String so2, String pm10, String pm25, String pub_time, String city_id, String aqi, String no2, String src) {
        this.city = city;
        this.spot = spot;
        this.so2 = so2;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.pub_time = pub_time;
        this.city_id = city_id;
        this.aqi = aqi;
        this.no2 = no2;
        this.src = src;
    }

    
}
