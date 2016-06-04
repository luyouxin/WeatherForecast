package com.lzh.weatherforecast.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.TextView;

import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.util.TitleUtil;
import com.lzh.weatherforecast.widget.TitleLayout;

/**
 * Created by lzh on 2016/5/9.
 */
public class AboutActivity extends Activity {
    private TextView versionTextView, developerTextView;
    private TitleLayout titleLayout;
    private PackageInfo packageInfo;
    private TitleUtil titleUtil;

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
    }

    private void initData() {
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
    }
}
