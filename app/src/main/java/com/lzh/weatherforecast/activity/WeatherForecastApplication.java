package com.lzh.weatherforecast.activity;

import android.app.Application;

import com.lzh.weatherforecast.util.DBUtil;

/**
 * Created by lzh on 2016/4/26.
 */
public class WeatherForecastApplication extends Application {
    private DBUtil dbManager;

    @Override
    public void onCreate() {
        dbManager = new DBUtil(this);
        dbManager.initDBManager(getPackageName());
        super.onCreate();
    }
}
