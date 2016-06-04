package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class AccuCcInfo {

    /**
     * EpochTime : 1462756860
     * LocalObservationDateTime : 2016-05-09T09:21:00+08:00
     * Pressure : 1012.0
     * RealFeelTemperature : 21.1
     * RelativeHumidity : 52
     * UVIndex : 4
     * Visibility : 11.3
     * WindDirectionDegrees : 315
     * WindSpeed : 1.6
     */

    public String EpochTime;
    public String LocalObservationDateTime;
    public String Pressure;
    public String RealFeelTemperature;
    public String RelativeHumidity;
    public String UVIndex;
    public String Visibility;
    public String WindDirectionDegrees;
    public String WindSpeed;

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
