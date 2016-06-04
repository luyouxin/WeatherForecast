package com.lzh.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.bean.FutureWeather;
import com.lzh.weatherforecast.bean.RawWeatherData;
import com.lzh.weatherforecast.util.WeatherDrawableUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzh on 2016/6/4.
 */
public class FutureWeatherAdapter extends BaseAdapter {
    private RawWeatherData rawWeatherData;
    private List<FutureWeather> futureWeatherList;
    private Context mContext;

    public FutureWeatherAdapter(RawWeatherData rawWeatherData, Context context) {
        this.rawWeatherData = rawWeatherData;
        futureWeatherList = new ArrayList<FutureWeather>();
        mContext = context;
        futureWeatherList.add(new FutureWeather(rawWeatherData.accu_f5.DailyForecasts.get(0).Date, rawWeatherData.forecast.img_title1, rawWeatherData.forecast.weather1));
        futureWeatherList.add(new FutureWeather(rawWeatherData.accu_f5.DailyForecasts.get(1).Date, rawWeatherData.forecast.img_title2, rawWeatherData.forecast.weather2));
        futureWeatherList.add(new FutureWeather(rawWeatherData.accu_f5.DailyForecasts.get(2).Date, rawWeatherData.forecast.img_title3, rawWeatherData.forecast.weather3));
        futureWeatherList.add(new FutureWeather(rawWeatherData.accu_f5.DailyForecasts.get(3).Date, rawWeatherData.forecast.img_title4, rawWeatherData.forecast.weather4));
        futureWeatherList.add(new FutureWeather(rawWeatherData.accu_f5.DailyForecasts.get(4).Date, rawWeatherData.forecast.img_title5, rawWeatherData.forecast.weather5));
    }

    @Override
    public int getCount() {
        return futureWeatherList.size();
    }

    @Override
    public Object getItem(int position) {
        return futureWeatherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);
            viewHolder.dateTextView = (TextView) convertView.findViewById(R.id.date);
            viewHolder.weatherImageView = (ImageView) convertView.findViewById(R.id.weather_image);
            viewHolder.tempRangeTextView = (TextView) convertView.findViewById(R.id.temp_range);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.dateTextView.setText(futureWeatherList.get(position).time.substring(6, 10));
        viewHolder.tempRangeTextView.setText(futureWeatherList.get(position).tempRange);
        viewHolder.weatherImageView.setImageDrawable(WeatherDrawableUtil.getInstance().getDayDrawable(futureWeatherList.get(position).weatherTitle, mContext));
        return convertView;
    }

    class ViewHolder {
        TextView dateTextView;
        ImageView weatherImageView;
        TextView tempRangeTextView;
    }
}
