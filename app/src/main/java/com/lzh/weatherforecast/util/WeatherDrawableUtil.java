package com.lzh.weatherforecast.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.lzh.weatherforecast.R;

/**
 * Created by lzh on 2016/5/7.
 */
public class WeatherDrawableUtil {
    public static WeatherDrawableUtil instance;

    public static WeatherDrawableUtil getInstance() {
        if (instance == null) {
            synchronized (WeatherDrawableUtil.class) {
                if (instance == null) {
                    instance = new WeatherDrawableUtil();
                }
            }
        }
        return instance;
    }


    public Drawable getDayDrawable(String weatherDescription,Context mContext) {
        if (weatherDescription.equals(mContext.getResources().getString(R.string.sunny))) {
            return mContext.getDrawable(R.drawable.img_00);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.partly_cloudy)) || weatherDescription.equals(mContext.getResources().getString(R.string.cloudy_partly))) {
            return mContext.getDrawable(R.drawable.img_01);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.cloudy)) || weatherDescription.equals(mContext.getResources().getString(R.string.sunny_cloudy))) {
            return mContext.getDrawable(R.drawable.img_02);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.shower))) {
            return mContext.getDrawable(R.drawable.img_03);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.thunderstorms))) {
            return mContext.getDrawable(R.drawable.img_04);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.thunderstorms_with_hail))) {
            return mContext.getDrawable(R.drawable.img_05);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.sleet))) {
            return mContext.getDrawable(R.drawable.img_06);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.light_rain))) {
            return mContext.getDrawable(R.drawable.img_07);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.moderate_rain))) {
            return mContext.getDrawable(R.drawable.img_08);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.heavy_rain))) {
            return mContext.getDrawable(R.drawable.img_09);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.rainstorm))) {
            return mContext.getDrawable(R.drawable.img_10);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.heavy_rainstorm))) {
            return mContext.getDrawable(R.drawable.img_11);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.very_rainstorm))) {
            return mContext.getDrawable(R.drawable.img_12);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.snow_shower))) {
            return mContext.getDrawable(R.drawable.img_13);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.snow))) {
            return mContext.getDrawable(R.drawable.img_14);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.mid_snow))) {
            return mContext.getDrawable(R.drawable.img_15);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.big_snow))) {
            return mContext.getDrawable(R.drawable.img_16);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.very_big_snow))) {
            return mContext.getDrawable(R.drawable.img_17);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.fog))) {
            return mContext.getDrawable(R.drawable.img_18);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.sleet_rain))) {
            return mContext.getDrawable(R.drawable.img_19);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.sandstorm))) {
            return mContext.getDrawable(R.drawable.img_20);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.light_rain_to_moderate_rain))) {
            return mContext.getDrawable(R.drawable.img_21);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.moderate_to_heavy_rain))) {
            return mContext.getDrawable(R.drawable.img_22);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.heavy_rain_to_rainstorm))) {
            return mContext.getDrawable(R.drawable.img_23);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.heavy_rain_storm))) {
            return mContext.getDrawable(R.drawable.img_24);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.very_heavy_rain_to_rainstorm))) {
            return mContext.getDrawable(R.drawable.img_25);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.snow_to_moderate_snow))) {
            return mContext.getDrawable(R.drawable.img_26);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.moderate_to_heavy_snow))) {
            return mContext.getDrawable(R.drawable.img_27);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.snow_blizzards))) {
            return mContext.getDrawable(R.drawable.img_28);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.dust))) {
            return mContext.getDrawable(R.drawable.img_29);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.jansa))) {
            return mContext.getDrawable(R.drawable.img_30);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.strong_sandstorm))) {
            return mContext.getDrawable(R.drawable.img_31);
        } else if (weatherDescription.equals(mContext.getResources().getString(R.string.haze))) {
            return mContext.getDrawable(R.drawable.img_53);
        }
        return mContext.getDrawable(R.drawable.undefined);
    }
}
