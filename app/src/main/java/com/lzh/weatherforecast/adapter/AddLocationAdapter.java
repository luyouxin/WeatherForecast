package com.lzh.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzh.weatherforecast.bean.CityInfo;
import com.lzh.weatherforecast.R;

import java.util.List;

/**
 * Created by lzh on 2016/4/26.
 */
public class AddLocationAdapter extends BaseAdapter {
    private List<CityInfo> mList;
    private Context mContext;

    public AddLocationAdapter(List<CityInfo> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.add_location_item, null);
        TextView cityName = (TextView) convertView.findViewById(R.id.city);
        cityName.setText(mList.get(position).name);
        return convertView;
    }
}
