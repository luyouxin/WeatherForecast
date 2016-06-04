package com.lzh.weatherforecast.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.bean.LocationWeatherInfo;
import com.lzh.weatherforecast.db.DBManger;
import com.lzh.weatherforecast.util.WeatherDrawableUtil;

import java.util.List;

/**
 * Created by lzh on 2016/5/5.
 */
public class LocationAdapter extends BaseAdapter {
    private List<LocationWeatherInfo> mList;
    private Context mContext;
    private DBManger dbManger;
    private Handler mHandler;
    private static final int DELETE = 0x03;
    public LocationAdapter(List<LocationWeatherInfo> mList, Context context, Handler handler) {
        this.mList = mList;
        this.mContext = context;
        dbManger = new DBManger(mContext);
        mHandler = handler;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final int location = position;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_location_item, null);
            viewHolder = new ViewHolder();
            viewHolder.currentLocationImageView = (ImageView) convertView.findViewById(R.id.current_image);
            viewHolder.locatinTextView = (TextView) convertView.findViewById(R.id.location_name);
            viewHolder.weatherImageView = (ImageView) convertView.findViewById(R.id.weather_image);
            viewHolder.weatherTextView = (TextView) convertView.findViewById(R.id.weather_message);
            viewHolder.weateherTempTextView = (TextView) convertView.findViewById(R.id.weather_temp);
            viewHolder.deleteImageView = (ImageView) convertView.findViewById(R.id.delete);
            convertView.setTag(viewHolder);
            //viewHolder.currentLocationImageView.setTag(mList.get(position));
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            //viewHolder.currentLocationImageView.setTag(mList.get(position));
        }
        if (mList.get(position).isCurrentLocation) {
            viewHolder.currentLocationImageView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.currentLocationImageView.setVisibility(View.GONE);
        }
        if (mList.get(position).isDelete) {
            viewHolder.deleteImageView.setVisibility(View.VISIBLE);
        }
        viewHolder.weatherImageView.setImageDrawable(WeatherDrawableUtil.getInstance().getDayDrawable(mList.get(position).imgTitle,mContext));
        viewHolder.locatinTextView.setText(mList.get(position).name);
        viewHolder.weatherTextView.setText(mList.get(position).weather);
        viewHolder.weateherTempTextView.setText(mList.get(position).temp);
        viewHolder.deleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = dbManger.delete("city_id=?", new String[]{mList.get(location).cidID});
                Message msg = mHandler.obtainMessage();
                msg.what = DELETE;
                msg.arg1 = i;
                msg.arg2=location;
                mHandler.sendMessage(msg);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView deleteImageView;
        ImageView currentLocationImageView;
        TextView locatinTextView;
        ImageView weatherImageView;
        TextView weatherTextView;
        TextView weateherTempTextView;
    }
}
