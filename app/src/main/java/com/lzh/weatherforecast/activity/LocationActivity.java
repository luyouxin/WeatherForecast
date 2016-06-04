package com.lzh.weatherforecast.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.view.animation.ScaleAnimation;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.adapter.LocationAdapter;
import com.lzh.weatherforecast.bean.CityInfo;
import com.lzh.weatherforecast.bean.LocationWeatherInfo;
import com.lzh.weatherforecast.bean.RawWeatherData;
import com.lzh.weatherforecast.db.DBManger;
import com.lzh.weatherforecast.util.PreferenceUtil;
import com.lzh.weatherforecast.util.TitleUtil;
import com.lzh.weatherforecast.widget.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzh on 2016/5/5.
 */
public class LocationActivity extends Activity implements View.OnClickListener, GetWeatherDataListener, GetDBCityInfoListener {
    private TitleUtil titleUtil;
    private TitleLayout titleLayout;
    private GridView gridView;
    private List<LocationWeatherInfo> mList;
    private DBManger dbManger;
    private LocationWeatherInfo locationWeatherInfo;
    private List<CityInfo> cityInfoList;
    private LocationAdapter adapter;
    private RelativeLayout addLayout;
    private Gson gson;
    private List<RawWeatherData> rawWeatherDataList;
    private RawWeatherData rawWeatherData;
    private static final int PARSEJSON = 0x01;
    private static final int UPDATEGRIDVIEW = 0x02;
    private static final int DELETE = 0x03;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case PARSEJSON:
                    rawWeatherData = (RawWeatherData) msg.obj;
                    String name = rawWeatherData.forecast.city;
                    String temp = rawWeatherData.forecast.temp1;
                    String weather = rawWeatherData.forecast.weather1;
                    String imgTitle = rawWeatherData.forecast.img_title1;
                    String cityid = rawWeatherData.forecast.cityid;
                    boolean isCurrentLocation = false;
                    if (PreferenceUtil.getInstance().getString("location").contains(name)) {
                        isCurrentLocation = true;
                    }
                    locationWeatherInfo = new LocationWeatherInfo(name, imgTitle, false, weather, temp, isCurrentLocation, cityid);
                    mList.add(locationWeatherInfo);
                    adapter.notifyDataSetChanged();
                    break;
                case UPDATEGRIDVIEW:
                    updateGridView(cityInfoList);
                    break;
                case DELETE:
                    if (msg.arg1 == 1) {
                        mList.remove(msg.arg2);
                        for (LocationWeatherInfo locationWeatherInfo : mList) {
                            locationWeatherInfo.isDelete = false;
                        }
                        gridView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                        mList.toString();
                        Toast.makeText(LocationActivity.this, getResources().getString(R.string.delete_success), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LocationActivity.this, getResources().getString(R.string.delete_failed), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        initView();
        setListener();
        initData();
        setListener();
    }

    private void initView() {
        cityInfoList=new ArrayList<CityInfo>();
        titleUtil = new TitleUtil(this);
        titleUtil.initTitle();
        titleLayout = (TitleLayout) findViewById(R.id.title_layout);
        titleLayout.setTitleMessage(getResources().getString(R.string.address));
        titleLayout.setRightImageViewBg(R.drawable.ic_delete);
        titleLayout.setRightImageViewVisibility(View.VISIBLE);
        gridView = (GridView) findViewById(R.id.gridview);
        addLayout = (RelativeLayout) findViewById(R.id.add_layout);
        addLayout.setOnClickListener(this);
    }

    private void initData() {
        gson = new Gson();
        mList = new ArrayList<LocationWeatherInfo>();
        rawWeatherDataList = new ArrayList<RawWeatherData>();
        adapter = new LocationAdapter(mList, this, mHandler);
        gridView.setAdapter(adapter);
        dbManger = new DBManger(this);
        WeatherDataImp.getInstance().getDbCityInfo(this, this);
    }

    protected LayoutAnimationController getAnimationController() {
        LayoutAnimationController controller;
        Animation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);//从0.5倍放大到1倍
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);//加载动画资源文件
        shake.setDuration(500);
        controller = new LayoutAnimationController(shake, 0.1f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return controller;
    }

    @Override
    public void onGetDBCityInfoSuccess(List<CityInfo> cityInfos) {
        cityInfoList.addAll(cityInfos);
        Message msg = mHandler.obtainMessage();
        msg.what = UPDATEGRIDVIEW;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onGetDBCityInfoError(int status) {

    }


    private void updateGridView(List<CityInfo> list) {
        WeatherDataImp.getInstance().getWeatherData(list, this);
    }

    @Override
    public void onGetWeatherDataSuccess(RawWeatherData rawWeatherData) {
        Message message = mHandler.obtainMessage();
        message.obj = rawWeatherData;
        message.what = PARSEJSON;
        mHandler.sendMessage(message);
    }

    @Override
    public void onGetWeatherDataError(int status) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_layout:
                Intent addIntent = new Intent(LocationActivity.this, AddLocationActivity.class);
                addIntent.putExtra("type", "add");
                startActivity(addIntent);
                break;
        }
    }

    private void setListener() {
        titleLayout.setLeftListener(new TitleLayout.TitleOnClickListener() {
            @Override
            public void OnClick() {
                finish();
            }
        });
        titleLayout.setRightImageViewListener(new TitleLayout.TitleOnClickListener() {
            @Override
            public void OnClick() {
                for (LocationWeatherInfo locationWeatherInfo : mList) {
                    locationWeatherInfo.isDelete = true;
                }
                adapter.notifyDataSetChanged();
                gridView.setLayoutAnimation(getAnimationController());

            }
        });
    }
}
