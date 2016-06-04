package com.lzh.weatherforecast.activity;

import com.lzh.weatherforecast.bean.CityInfo;

import java.util.List;

/**
 * Created by lzh on 2016/6/3.
 */
public interface GetDBCityInfoListener {
    void onGetDBCityInfoSuccess(List<CityInfo> cityInfos);

    void onGetDBCityInfoError(int status);
}
