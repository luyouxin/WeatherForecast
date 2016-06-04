package com.lzh.weatherforecast.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.lzh.weatherforecast.R;
import com.lzh.weatherforecast.util.TitleUtil;
import com.lzh.weatherforecast.widget.TitleLayout;

/**
 * Created by lzh on 2016/5/13.
 */
public class FeedBackActivity extends Activity implements View.OnClickListener {
    private TitleLayout titleLayout;
    private TitleUtil titleUtil;
    private RelativeLayout positiveLayout, suggestLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
        setListener();
    }

    private void initView() {
        titleLayout = (TitleLayout) findViewById(R.id.title_layout);
        positiveLayout = (RelativeLayout) findViewById(R.id.positive_comment_layout);
        suggestLayout = (RelativeLayout) findViewById(R.id.suggest_layout);
        titleUtil = new TitleUtil(this);
        titleUtil.initTitle();
        titleLayout.setTitleMessage(getResources().getString(R.string.feedback));
    }

    private void setListener() {
        positiveLayout.setOnClickListener(this);
        suggestLayout.setOnClickListener(this);
        titleLayout.setLeftListener(new TitleLayout.TitleOnClickListener() {
            @Override
            public void OnClick() {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.positive_comment_layout:
                String uriString = "market://details?id=" + "com.tencent.mm";
                Intent marketIntent = new Intent("android.intent.action.VIEW");
                marketIntent.setData(Uri.parse(uriString));
                startActivity(marketIntent);
                break;
            case R.id.suggest_layout:
                Intent intent = new Intent(FeedBackActivity.this, CommentActivity.class);
                startActivity(intent);
                break;
        }
    }
}
