package com.lzh.weatherforecast.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.lzh.weatherforecast.activity.ChangeLocation;

/**
 * Created by lzh on 2016/4/23.
 */
public class LocationService extends Service implements
        AMapLocationListener {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;
    private LocationBinder binder = new LocationBinder();
    private ChangeLocation changeLocation;

    @Override
    public void onCreate() {
        initLocation();
    }

    /**
     * 注册回调接口的方法，供外部调用
     */
    public void setChangeLocation(ChangeLocation changeLocationr) {
        this.changeLocation = changeLocationr;
    }

    private void initLocation() {
        mLocationClient = new AMapLocationClient(this.getApplicationContext());
        mLocationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        mLocationClient.setLocationListener(LocationService.this);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(10000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class LocationBinder extends Binder {
        public LocationService getLocationService() {
            return LocationService.this;
        }

    }

    @Override
    public void onDestroy() {
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }

    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null) {
            changeLocation.getLocation(amapLocation);
        }
    }
}
