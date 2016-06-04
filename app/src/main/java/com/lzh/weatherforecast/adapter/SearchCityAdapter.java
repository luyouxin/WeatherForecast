package com.lzh.weatherforecast.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzh.weatherforecast.bean.CityInfo;
import com.lzh.weatherforecast.R;

import java.util.List;

/**
 * Created by lzh on 2016/4/28.
 */
public class SearchCityAdapter extends BaseAdapter {
    private List<CityInfo> mList;
    private Context mContext;
    private Handler mHandler;
    private static final int ITEM_CLICK = 0x01;

    public SearchCityAdapter(List<CityInfo> mList, Context mContext, Handler handler) {
        this.mList = mList;
        this.mContext = mContext;
        this.mHandler = handler;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        CityInfo cityInfo = mList.get(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.search_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.cityNameTextView = (TextView) convertView.findViewById(R.id.city_name);
            viewHolder.cityCheckImageView = (ImageView) convertView.findViewById(R.id.city_check);
            viewHolder.cityLayout = (RelativeLayout) convertView.findViewById(R.id.city_layout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.cityCheckImageView.setTag(cityInfo);
        }
        viewHolder.cityCheckImageView.setTag(cityInfo);
        viewHolder.cityNameTextView.setText(mList.get(position).name);
        if (cityInfo.isCheck) {
            viewHolder.cityCheckImageView.setVisibility(View.VISIBLE);
        } else {
            viewHolder.cityCheckImageView.setVisibility(View.GONE);
        }
        viewHolder.cityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg = mHandler.obtainMessage();
                msg.arg1 = position;
                msg.what = ITEM_CLICK;
                mHandler.sendMessage(msg);
            }
        });
        return convertView;
    }

    class ViewHolder {
        TextView cityNameTextView;
        ImageView cityCheckImageView;
        RelativeLayout cityLayout;
    }
}
