package com.lzh.weatherforecast.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.util.AppManager;
import com.lzh.weatherforecast.util.TitleUtil;
import com.lzh.weatherforecast.widget.TitleLayout;

/**
 * Created by lzh on 2016/5/9.
 */
public class AboutActivity extends Activity implements View.OnClickListener {
    private TextView versionTextView, developerTextView;
    private TitleLayout titleLayout;
    private PackageInfo packageInfo;
    private TitleUtil titleUtil;
    private RelativeLayout loginOutLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        initView();
        initData();
        setListener();
    }

    private void initView() {
        versionTextView = (TextView) findViewById(R.id.version);
        developerTextView = (TextView) findViewById(R.id.developer_text);
        titleLayout = (TitleLayout) findViewById(R.id.title_layout);
        loginOutLayout = (RelativeLayout) findViewById(R.id.login_out_layout);
    }

    private void initData() {
        AppManager.getAppManager().addActivity(this);
        titleUtil = new TitleUtil(this);
        titleUtil.initTitle();
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            versionTextView.setText("Version " + packageInfo.versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        developerTextView.setText(getResources().getString(R.string.developer_message));
        titleLayout.setTitleMessage(getResources().getString(R.string.about));
    }

    private void setListener() {
        titleLayout.setLeftListener(new TitleLayout.TitleOnClickListener() {
            @Override
            public void OnClick() {
                finish();
            }
        });
        loginOutLayout.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_out_layout:
                AppManager.getAppManager().AppExit(this);
                break;
        }
    }
}
