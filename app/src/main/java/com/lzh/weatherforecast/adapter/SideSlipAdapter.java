package com.lzh.weatherforecast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzh.weatherforecast.bean.SideSlipInfo;
import com.lzh.weatherforecast.R;

import java.util.List;


/**
 * Created by lzh on 2016/4/12.
 */
public class SideSlipAdapter extends BaseAdapter {
    private SideSlipInfo mSideSlip;
    private Context mContext;
    private List<SideSlipInfo> mList;

    public SideSlipAdapter(List<SideSlipInfo> list, Context context) {
        mContext = context;
        mList = list;
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.sideslip_list_item, null);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview);
        TextView textView = (TextView) convertView.findViewById(R.id.title);
        imageView.setImageDrawable(mContext.getResources().getDrawable(mList.get(position).resourceID));
        textView.setText(mList.get(position).title);
        return convertView;
    }
}
