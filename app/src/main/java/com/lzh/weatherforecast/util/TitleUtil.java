package com.lzh.weatherforecast.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.lzh.weatherforecast.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * Created by lzh on 2016/1/14.
 */
public class TitleUtil {
    private Activity mActivity;

    public TitleUtil(Activity activity) {
        mActivity = activity;
    }

    public void initTitle() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.blue);//通知栏所需颜色
    }

    public void initDrawableTitle(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintDrawable(drawable);
    }

    @TargetApi(19)
    public void setTranslucentStatus(boolean on) {
        Window win = mActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
