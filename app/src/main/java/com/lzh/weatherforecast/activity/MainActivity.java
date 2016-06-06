package com.lzh.weatherforecast.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.adapter.SideSlipAdapter;
import com.lzh.weatherforecast.bean.CityInfo;
import com.lzh.weatherforecast.bean.FutureWeather;
import com.lzh.weatherforecast.bean.RawWeatherData;
import com.lzh.weatherforecast.bean.SideSlipInfo;
import com.lzh.weatherforecast.db.DBManger;
import com.lzh.weatherforecast.services.LocationService;
import com.lzh.weatherforecast.util.AppManager;
import com.lzh.weatherforecast.util.DBUtil;
import com.lzh.weatherforecast.util.PreferenceUtil;
import com.lzh.weatherforecast.util.TitleUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.socialization.Socialization;

public class MainActivity extends AppCompatActivity implements GetDBCityInfoListener, GetWeatherDataListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    public ActionBarDrawerToggle mDrawerToggle;
    private ListView listView;
    private SideSlipAdapter sideSlipAdapter;
    private List<SideSlipInfo> mList;
    private List<CityInfo> cityInfoList;
    private LocationService locationService;
    private Intent mIntent;
    private DBUtil dbManager;
    private SQLiteDatabase sqLiteDatabase;
    private TextView titleTextView;
    private TitleUtil titleUtil;
    private static final int GETDBSUCCESS = 0x01;
    private static final int UPDATE = 0x02;
    private List<Fragment> fragmentList;
    private List<RawWeatherData> rawWeatherDataList;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private HashMap<String, List<FutureWeather>> map;
    private List<FutureWeather> futureWeathers;
    private String imei;
    private long mExitTime = 0;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what) {
                case GETDBSUCCESS:
                    WeatherDataImp.getInstance().getWeatherData(imei, cityInfoList, MainActivity.this);
                    break;
                case UPDATE:
                    Fragment fragment = new WeatherFragment();
                    fragmentList.add(fragment);
                    rawWeatherDataList.add((RawWeatherData) msg.obj);
                    viewPager.setOffscreenPageLimit(0);
                    titleTextView.setText(rawWeatherDataList.get(viewPager.getCurrentItem()).aqi.city);
                    adapter.notifyDataSetChanged();
                    ((WeatherFragment) fragmentList.get(viewPager.getCurrentItem())).initData(rawWeatherDataList.get(viewPager.getCurrentItem()));
                    ((WeatherFragment) fragmentList.get(viewPager.getCurrentItem())).updateGridView(rawWeatherDataList.get(viewPager.getCurrentItem()));
                    Log.d("CESHI", "notifyDataSetChanged");
                    break;
            }
            super.handleMessage(msg);
        }
    };
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationService.LocationBinder binder = (LocationService.LocationBinder) service;
            locationService = binder.getLocationService();
            locationService.setChangeLocation(new ChangeLocation() {
                @Override
                public void getLocation(AMapLocation amapLocation) {
                    if (amapLocation.getErrorCode() == 0) {
                        PreferenceUtil.getInstance().init(MainActivity.this);
                        PreferenceUtil.getInstance().putString("location", amapLocation.getCity());
                    } else {
                        Toast.makeText(MainActivity.this, "location Error, ErrCode:"
                                + amapLocation.getErrorCode() + ", errInfo:"
                                + amapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(MainActivity.this, LocationService.class);
        bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        initView();
        initData();
        DBManger dbManger = new DBManger(this);
        WeatherDataImp.getInstance().getDbCityInfo(this, this);
    }


    private void initView() {
        titleUtil = new TitleUtil(this);
        titleUtil.initTitle();
        fragmentList = new ArrayList<Fragment>();
        rawWeatherDataList = new ArrayList<RawWeatherData>();
        map = new HashMap<String, List<FutureWeather>>();
        futureWeathers = new ArrayList<FutureWeather>();
        cityInfoList = new ArrayList<CityInfo>();
        listView = (ListView) findViewById(R.id.listview);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(onMenuItemClick);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        titleTextView = (TextView) toolbar.findViewById(R.id.title);

        drawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        drawerLayout.setDrawerListener(mDrawerToggle);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        adapter = new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawopen, R.string.drawclose) {
            @Override
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }
        };

        ActionBar actionBar = getSupportActionBar();

        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        mDrawerToggle.syncState();
        drawerLayout.setDrawerListener(mDrawerToggle);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeButtonEnabled(true);
        }
        mIntent = new Intent(MainActivity.this, LocationService.class);
        bindService(mIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    Intent addLocationIntent = new Intent(MainActivity.this, AddLocationActivity.class);
                    addLocationIntent.putExtra("type", "main");
                    startActivity(addLocationIntent);
                    finish();
                }
                if (position == 1) {
                    Intent locationIntent = new Intent(MainActivity.this, LocationActivity.class);
                    startActivity(locationIntent);
                }
                if (position == 2) {
                    Intent feedBackIntent = new Intent(MainActivity.this, FeedBackActivity.class);
                    startActivity(feedBackIntent);
                }
                if (position == 3) {
                    Intent aboutIntent = new Intent(MainActivity.this, AboutActivity.class);
                    startActivity(aboutIntent);
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                titleTextView.setText(rawWeatherDataList.get(viewPager.getCurrentItem()).aqi.city);
                ((WeatherFragment) fragmentList.get(viewPager.getCurrentItem())).initData(rawWeatherDataList.get(viewPager.getCurrentItem()));
                ((WeatherFragment) fragmentList.get(viewPager.getCurrentItem())).updateGridView(rawWeatherDataList.get(viewPager.getCurrentItem()));
            }
        });
    }

    @Override
    public void onGetDBCityInfoSuccess(List<CityInfo> cityInfos) {
        cityInfoList.addAll(cityInfos);
        Message msg = mHandler.obtainMessage();
        msg.what = GETDBSUCCESS;
        mHandler.sendMessage(msg);
    }

    @Override
    public void onGetDBCityInfoError(int status) {

    }

    @Override
    public void onGetWeatherDataSuccess(RawWeatherData rawWeatherData) {


        Message message = mHandler.obtainMessage();

        message.obj = rawWeatherData;
        message.what = UPDATE;
        mHandler.sendMessage(message);
    }

    @Override
    public void onGetWeatherDataError(int status) {

    }

    private void initData() {
        AppManager.getAppManager().addActivity(this);
        TelephonyManager telephonyManager = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId();
        mList = new ArrayList<SideSlipInfo>();
        SideSlipInfo addSideSlip = new SideSlipInfo(R.drawable.ic_add_location, getResources().getString(R.string.add_locaton));
        SideSlipInfo locationSideSlip = new SideSlipInfo(R.drawable.ic_setting_location, getResources().getString(R.string.location));
        //SideSlipInfo setingSideSlip = new SideSlipInfo(R.drawable.ic_setting, getResources().getString(R.string.setting));
        SideSlipInfo feedBackSideSlip = new SideSlipInfo(R.drawable.ic_feedback, getResources().getString(R.string.feedback));
        SideSlipInfo aboutSideSlip = new SideSlipInfo(R.drawable.ic_about, getResources().getString(R.string.about));
        mList.add(addSideSlip);
        mList.add(locationSideSlip);
        // mList.add(setingSideSlip);
        mList.add(feedBackSideSlip);
        mList.add(aboutSideSlip);
        sideSlipAdapter = new SideSlipAdapter(mList, MainActivity.this);
        listView.setAdapter(sideSlipAdapter);
    }

    private Toolbar.OnMenuItemClickListener onMenuItemClick = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String msg = "";
            switch (menuItem.getItemId()) {
                case R.id.action_share:
                    msg += "Click share";
                    break;
            }

            if (!msg.equals("")) {
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
            return true;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        unbindService(serviceConnection);
        super.onDestroy();
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");
        // 启动分享GUI
        oks.show(this);
        ShareSDK.registerService(Socialization.class);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }
}
