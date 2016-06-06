package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class RealTimeWeatherInfo {

    /**
     * SD : 50%
     * WD : 东北风
     * WS : 1级
     * WSE :
     * city :
     * cityid : 101010100
     * isRadar : 1
     * radar : JC_RADAR_AZ9010_JB
     * temp : 19
     * time : 09:20
     * weather : 小雨
     */

    public String SD;
    public String WD;
    public String WS;
    public String WSE;
    public String city;
    public String cityid;
    public String isRadar;
    public String radar;
    public String temp;//温度
    public String time;//时间
    public String weather;

    public RealTimeWeatherInfo(String SD, String radar, String cityid, String city, String WSE, String WS, String WD, String isRadar, String temp, String time, String weather) {
        this.SD = SD;
        this.radar = radar;
        this.cityid = cityid;
        this.city = city;
        this.WSE = WSE;
        this.WS = WS;
        this.WD = WD;
        this.isRadar = isRadar;
        this.temp = temp;
        this.time = time;
        this.weather = weather;
    }

   
}
