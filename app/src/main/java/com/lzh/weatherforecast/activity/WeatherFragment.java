package com.lzh.weatherforecast.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.adapter.FutureWeatherAdapter;
import com.lzh.weatherforecast.bean.RawWeatherData;
import com.lzh.weatherforecast.util.WeatherDrawableUtil;
import com.lzh.weatherforecast.util.WeatherTempDrawableUtil;

import java.util.Calendar;

/**
 * Created by lzh on 2016/6/3.
 */
public class WeatherFragment extends Fragment {
    private TextView dateTextView, weeklyTextView, weatherTitleTextView, weatherRangeTextView, humidityTextView, windTextView, moreTextView, visibilityTextView,
            uvIndexTextView, sunRaiseTextView, sunSetTextView, pubTimeTextView;
    private ImageView weatherImageView, tempImageView;
    private GridView gridView;
    private Calendar calendar;
    private FutureWeatherAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_weather, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        gridView = (GridView) view.findViewById(R.id.gridview);
        dateTextView = (TextView) view.findViewById(R.id.date);
        weeklyTextView = (TextView) view.findViewById(R.id.weekly);
        weatherTitleTextView = (TextView) view.findViewById(R.id.weather_title);
        humidityTextView = (TextView) view.findViewById(R.id.humidity_text);
        windTextView = (TextView) view.findViewById(R.id.wind_text);
        moreTextView = (TextView) view.findViewById(R.id.more_text);
        visibilityTextView = (TextView) view.findViewById(R.id.visibility);
        uvIndexTextView = (TextView) view.findViewById(R.id.uv_index);
        sunRaiseTextView = (TextView) view.findViewById(R.id.sun_rise);
        sunSetTextView = (TextView) view.findViewById(R.id.sun_set);
        pubTimeTextView = (TextView) view.findViewById(R.id.pub_time);
        tempImageView = (ImageView) view.findViewById(R.id.current_temp_image);
        weatherImageView = (ImageView) view.findViewById(R.id.weather_image);
        weatherRangeTextView = (TextView) view.findViewById(R.id.temp_range);
    }

    public void updateGridView(RawWeatherData rawWeatherData) {
        adapter = new FutureWeatherAdapter(rawWeatherData, getActivity());
        gridView.setAdapter(adapter);
    }

    public void initData(RawWeatherData rawWeatherData) {
        calendar = Calendar.getInstance();
        dateTextView.setText(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        weeklyTextView.setText(getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)));
        weatherTitleTextView.setText(rawWeatherData.forecast.weather1);
        weatherRangeTextView.setText(rawWeatherData.forecast.temp1);
        weatherImageView.setImageDrawable(WeatherDrawableUtil.getInstance().getDayDrawable(rawWeatherData.forecast.img_title1, getActivity()));
        humidityTextView.setText(getResources().getString(R.string.relativeHumidity)+rawWeatherData.accu_cc.RelativeHumidity+"%");
        windTextView.setText(getResources().getString(R.string.wind_speed)+rawWeatherData.accu_cc.WindSpeed+"km/h");
        visibilityTextView.setText(rawWeatherData.accu_cc.Visibility + "km");
        uvIndexTextView.setText(rawWeatherData.accu_cc.UVIndex);
        sunRaiseTextView.setText(rawWeatherData.accu_f5.DailyForecasts.get(0).Sun_Rise.substring(11, 19));
        sunSetTextView.setText(rawWeatherData.accu_f5.DailyForecasts.get(0).Sun_Set.substring(11, 19));
        pubTimeTextView.setText(rawWeatherData.realtime.time);
        tempImageView.setImageDrawable(WeatherTempDrawableUtil.getInstance().getTempDrawable(Integer.parseInt(rawWeatherData.realtime.temp), getActivity()));
    }


    private String getDayOfWeek(int dayOfWeek) {
        switch (dayOfWeek) {
            case 1:
                return "星期日";
            case 2:
                return "星期一";

            case 3:
                return "星期二";

            case 4:
                return "星期三";

            case 5:
                return "星期四";

            case 6:
                return "星期五";

            case 7:
                return "星期六";
        }
        return null;
    }
}
