package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class AccuCcInfo {



    public String EpochTime;
    public String LocalObservationDateTime;
    public String Pressure; //气压
    public String RealFeelTemperature;//体感温度
    public String RelativeHumidity;//相对湿度
    public String UVIndex;//紫外线强度
    public String Visibility;//可见度，单位KM
    public String WindDirectionDegrees;//风向程度，这个不确定
    public String WindSpeed;//风速

    public AccuCcInfo(String epochTime, String windSpeed, String windDirectionDegrees, String visibility, String UVIndex, String relativeHumidity, String realFeelTemperature, String pressure, String localObservationDateTime) {
        EpochTime = epochTime;
        WindSpeed = windSpeed;
        WindDirectionDegrees = windDirectionDegrees;
        Visibility = visibility;
        this.UVIndex = UVIndex;
        RelativeHumidity = relativeHumidity;
        RealFeelTemperature = realFeelTemperature;
        Pressure = pressure;
        LocalObservationDateTime = localObservationDateTime;
    }

    
}
