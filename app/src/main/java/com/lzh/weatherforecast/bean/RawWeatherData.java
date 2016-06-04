package com.lzh.weatherforecast.bean;

import java.util.List;

/**
 * Created by lzh on 2016/5/5.
 */
public class RawWeatherData {
    public ForecastInfo forecast;
    public RealTimeWeatherInfo realtime;
    public List<AlertInfo> alert;
    public AqiInfo aqi;
    public List<IndexWeatherInfo> index;
    public AccuCcInfo accu_cc;
    public AccuF5Info accu_f5;
    public TodayInfo today;
    public YestodayInfo yestoday;

}
