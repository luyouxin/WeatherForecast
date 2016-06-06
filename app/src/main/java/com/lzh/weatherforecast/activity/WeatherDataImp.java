package com.lzh.weatherforecast.activity;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.lzh.weatherforecast.bean.CityInfo;
import com.lzh.weatherforecast.bean.RawWeatherData;
import com.lzh.weatherforecast.db.DBManger;
import com.lzh.weatherforecast.util.OkHttpClientManager;
import com.lzh.weatherforecast.util.ResultCallback;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by lzh on 2016/6/2.
 */
public class WeatherDataImp implements IGetWeatherData {
    private static WeatherDataImp instance;
    private static Gson gson;
    private String headUrl = "http://weatherapi.market.xiaomi.com/wtr-v2/weather?";
    private String imeiUrl = "&imei=";
    private String footUrl = "&device=pisces&miuiVersion" +
            "=JXCCNBD20.0&modDevice=&source=miuiWeatherApp";
    private static final int ONERROR = 1;

    public static WeatherDataImp getInstance() {
        if (instance == null) {
            synchronized (WeatherDataImp.class) {
                if (instance == null) {
                    instance = new WeatherDataImp();
                    gson = new Gson();
                }
            }
        }
        return instance;
    }

    @Override
    public void getWeatherData(String imei, final List<CityInfo> list, final GetWeatherDataListener listener) {
        for (int i = 0; i < list.size(); i++) {
            OkHttpClientManager.getInstance().getEnqueue(headUrl + "cityId=" + list.get(i).cityID + imeiUrl + imei + footUrl, new ResultCallback() {
                @Override
                public void onError(Call call, Exception e) {
                    listener.onGetWeatherDataError(ONERROR);
                }

                @Override
                public void onResponse(Response response) {
                    if (response.code() == 200) {
                        try {
                            listener.onGetWeatherDataSuccess(gson.fromJson(response.body().string(), RawWeatherData.class));
                            Log.d("CESHI",response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else {
                        listener.onGetWeatherDataError(response.code());
                    }

                }
            });
        }
        ;
    }

    public void getDbCityInfo(Context context, final GetDBCityInfoListener listener) {
        final DBManger dbManger = new DBManger(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] columns = new String[]{"name", "city_id"};
                List<CityInfo> cityInfoList = dbManger.query(columns, null, null);
                listener.onGetDBCityInfoSuccess(cityInfoList);
            }
        }).start();
    }
}
