package com.lzh.weatherforecast.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzh.weatherforecast.adapter.AddLocationAdapter;
import com.lzh.weatherforecast.adapter.SearchCityAdapter;
import com.lzh.weatherforecast.bean.CityInfo;
import com.lzh.weatherforecast.db.DBManger;
import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.util.AppManager;
import com.lzh.weatherforecast.util.DBUtil;
import com.lzh.weatherforecast.util.TitleUtil;
import com.lzh.weatherforecast.widget.CustomDialog;
import com.lzh.weatherforecast.widget.TitleLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzh on 2016/4/26.
 */
public class AddLocationActivity extends Activity {
    private TitleLayout titleLayout;
    private EditText keyEditText;
    private GridView gridView;
    private DBUtil dbUtil;
    private List<CityInfo> cityInfoList;
    private CityInfo cityInfo;
    private SQLiteDatabase sqLiteDatabase;
    private AddLocationAdapter adapter;
    private TitleUtil titleUtil;
    private List<CityInfo> hotCityInfoList;
    private ListView listView;
    private SearchCityAdapter searchCityAdapter;
    private TextView searchResultTitlte, titleTips;
    private static final int ITEM_CLICK = 0x01;
    private CustomDialog dialog;
    private DBManger dbManger;
    private int choosePosition;
    private String type;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ITEM_CLICK:
                    initSearchList(cityInfoList);
                    choosePosition = msg.arg1;
                    cityInfoList.get(msg.arg1).isCheck = true;
                    searchCityAdapter.notifyDataSetChanged();
                    showDiaolg(cityInfoList.get(msg.arg1).name, "common");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);
        initView();
        setListener();
    }

    private void initView() {
        AppManager.getAppManager().addActivity(this);
        type = getIntent().getStringExtra("type");
        dialog = new CustomDialog(this);
        dbManger = new DBManger(this);
        titleUtil = new TitleUtil(this);
        titleUtil.initTitle();
        titleLayout = (TitleLayout) findViewById(R.id.title_layout);
        titleLayout.setTitleMessage(getResources().getString(R.string.add_address));
        keyEditText = (EditText) findViewById(R.id.key);
        titleLayout.setRightTextMessage(getString(R.string.search));
        gridView = (GridView) findViewById(R.id.gridview);
        listView = (ListView) findViewById(R.id.listview);
        titleTips = (TextView) findViewById(R.id.title_tips);
        searchResultTitlte = (TextView) findViewById(R.id.search_result);
        hotCityInfoList = new ArrayList<CityInfo>();
        dbUtil = new DBUtil(this);
        sqLiteDatabase = dbUtil.getSqLiteDatabase(getPackageName());
        hotCityInfoList = dbUtil.queryHotCity(sqLiteDatabase);
        adapter = new AddLocationAdapter(hotCityInfoList, this);
        gridView.setAdapter(adapter);
        titleLayout.setLeftListener(new TitleLayout.TitleOnClickListener() {
            @Override
            public void OnClick() {

                if (type.equals("main")) {
                    Intent mainIntent = new Intent(AddLocationActivity.this, MainActivity.class);
                    startActivity(mainIntent);
                    finish();
                } else {
                    Intent intent = new Intent(AddLocationActivity.this, LocationActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void setListener() {
        titleLayout.setRightListener(new TitleLayout.TitleOnClickListener() {
            @Override
            public void OnClick() {
                hideKeyBoard(titleLayout);
                if (cityInfoList != null) {
                    cityInfoList.clear();
                }
                titleTips.setText(getResources().getString(R.string.choose_city));
                String key = keyEditText.getText().toString().trim();
                if (null != key && !key.equals("")) {
                    gridView.setVisibility(View.GONE);
                    listView.setVisibility(View.VISIBLE);
                    cityInfoList = searchCity(key);
                    updateListView(cityInfoList);
                } else {
                    showToast(getResources().getString(R.string.search_tips));
                }
            }
        });
        //点击右边的删除图标删除输入框文字信息
        keyEditText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Drawable drawable = keyEditText.getCompoundDrawables()[2];
                if (drawable == null)
                    return false;

                if (event.getAction() != MotionEvent.ACTION_UP)
                    return false;

                if (event.getX() > keyEditText.getWidth() - keyEditText.getPaddingRight()
                        - drawable.getIntrinsicWidth()) {
                    keyEditText.setText("");
                }

                return false;
            }
        });
        keyEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0 || s.equals(getResources().getString(R.string.search_tips))) {
                    listView.setVisibility(View.GONE);
                    gridView.setVisibility(View.VISIBLE);
                    titleTips.setText(getResources().getString(R.string.choose_hotcity));
                    searchResultTitlte.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choosePosition = position;
                showDiaolg(hotCityInfoList.get(position).name, "hotcity");
            }
        });
    }

    private void initSearchList(List<CityInfo> list) {
        for (CityInfo cityInfo : list) {
            cityInfo.isCheck = false;
        }
    }


    private void updateListView(List<CityInfo> list) {
        if (cityInfoList.size() == 0) {
            searchResultTitlte.setVisibility(View.VISIBLE);
            searchResultTitlte.setText(getResources().getString(R.string.not_find_about_city));
        } else {
            searchResultTitlte.setVisibility(View.GONE);
            searchCityAdapter = new SearchCityAdapter(list, AddLocationActivity.this, mHandler);
            listView.setAdapter(searchCityAdapter);
        }
    }

    //模糊查询city
    private List<CityInfo> searchCity(String key) {
        String[] columns = new String[]{"name", "city_num"};
        String selection = " name like ?";
        String[] selectionArgs = new String[]{"%" + key + "%"};
        return dbUtil.queryCommonCityList(sqLiteDatabase, columns, selection, selectionArgs);
    }

    //隐藏键盘
    private void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) view
                .getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(
                    view.getApplicationWindowToken(), 0);
        }
    }

    private void showDiaolg(String content, String type) {
        dialog.show();
        dialog.setTitleMessage(getResources().getString(R.string.notification));
        dialog.setContentText(content);
        if (type.equals("hotcity")) {
            cityInfo = hotCityInfoList.get(choosePosition);
        } else {
            cityInfo = cityInfoList.get(choosePosition);
        }
        dialog.setCancleBtnOnClickListener(new CustomDialog.OnClickListener() {
            @Override
            public void onClick() {
                dialog.dismiss();
            }
        });
        dialog.setOkBtnOnClickListener(new CustomDialog.OnClickListener() {
            @Override
            public void onClick() {
                if (searchAlreadyCity(cityInfo.cityID)) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name", cityInfo.name);
                    contentValues.put("city_id", cityInfo.cityID);
                    long id = dbManger.insert(null, contentValues);
                    if (id != -1) {
                        showToast(getResources().getString(R.string.add_location_success));
                    } else {
                        showToast(getResources().getString(R.string.add_location_failed));
                    }
                } else {
                    showToast(getResources().getString(R.string.already_exist));
                }
                dialog.dismiss();
            }
        });
    }

    //模糊查询city
    private boolean searchAlreadyCity(String cityID) {
        String[] columns = new String[]{"name", "city_id"};
        String selection = " city_id=?";
        String[] selectionArgs = new String[]{cityID};
        if (dbManger.querySingle(columns, selection, selectionArgs) != null) {
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (type.equals("main")) {
                Intent mainIntent = new Intent(AddLocationActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            } else {
                Intent intent = new Intent(AddLocationActivity.this, LocationActivity.class);
                startActivity(intent);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void showToast(String message) {
        Toast.makeText(AddLocationActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
