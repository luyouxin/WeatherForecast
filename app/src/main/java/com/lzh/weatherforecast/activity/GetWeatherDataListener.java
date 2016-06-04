package com.lzh.weatherforecast.activity;

import com.lzh.weatherforecast.bean.RawWeatherData;

/**
 * Created by lzh on 2016/6/2.
 */
public interface GetWeatherDataListener {
    void onGetWeatherDataSuccess(RawWeatherData rawWeatherData);

    void onGetWeatherDataError(int status);
}
