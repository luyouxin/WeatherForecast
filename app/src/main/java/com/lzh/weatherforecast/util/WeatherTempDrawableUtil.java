package com.lzh.weatherforecast.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.lzh.weatherforecast.R;

/**
 * Created by lzh on 2016/6/4.
 */
public class WeatherTempDrawableUtil {
    public static WeatherTempDrawableUtil instance;

    public static WeatherTempDrawableUtil getInstance() {
        if (instance == null) {
            synchronized (WeatherDrawableUtil.class) {
                if (instance == null) {
                    instance = new WeatherTempDrawableUtil();
                }
            }
        }
        return instance;
    }


    public Drawable getTempDrawable(int currentTemp, Context mContext) {
        switch (currentTemp) {
            case -40:
                return mContext.getDrawable(R.drawable.notification_icon__40_white);
            case -39:
                return mContext.getDrawable(R.drawable.notification_icon__39_white);
            case -38:
                return mContext.getDrawable(R.drawable.notification_icon__38_white);
            case -37:
                return mContext.getDrawable(R.drawable.notification_icon__37_white);
            case -36:
                return mContext.getDrawable(R.drawable.notification_icon__36_white);
            case -35:
                return mContext.getDrawable(R.drawable.notification_icon__35_white);
            case -34:
                return mContext.getDrawable(R.drawable.notification_icon__34_white);
            case -33:
                return mContext.getDrawable(R.drawable.notification_icon__33_white);
            case -32:
                return mContext.getDrawable(R.drawable.notification_icon__32_white);
            case -31:
                return mContext.getDrawable(R.drawable.notification_icon__31_white);
            case -30:
                return mContext.getDrawable(R.drawable.notification_icon__30_white);
            case -29:
                return mContext.getDrawable(R.drawable.notification_icon__29_white);
            case -28:
                return mContext.getDrawable(R.drawable.notification_icon__28_white);
            case -27:
                return mContext.getDrawable(R.drawable.notification_icon__27_white);
            case -26:
                return mContext.getDrawable(R.drawable.notification_icon__26_white);
            case -25:
                return mContext.getDrawable(R.drawable.notification_icon__25_white);
            case -24:
                return mContext.getDrawable(R.drawable.notification_icon__24_white);
            case -23:
                return mContext.getDrawable(R.drawable.notification_icon__23_white);
            case -22:
                return mContext.getDrawable(R.drawable.notification_icon__22_white);
            case -21:
                return mContext.getDrawable(R.drawable.notification_icon__21_white);
            case -20:
                return mContext.getDrawable(R.drawable.notification_icon__20_white);
            case -19:
                return mContext.getDrawable(R.drawable.notification_icon__19_white);
            case -18:
                return mContext.getDrawable(R.drawable.notification_icon__18_white);
            case -17:
                return mContext.getDrawable(R.drawable.notification_icon__17_white);
            case -16:
                return mContext.getDrawable(R.drawable.notification_icon__16_white);
            case -15:
                return mContext.getDrawable(R.drawable.notification_icon__15_white);
            case -14:
                return mContext.getDrawable(R.drawable.notification_icon__14_white);
            case -13:
                return mContext.getDrawable(R.drawable.notification_icon__13_white);
            case -12:
                return mContext.getDrawable(R.drawable.notification_icon__12_white);
            case -11:
                return mContext.getDrawable(R.drawable.notification_icon__11_white);
            case -10:
                return mContext.getDrawable(R.drawable.notification_icon__10_white);
            case -9:
                return mContext.getDrawable(R.drawable.notification_icon__9_white);
            case -8:
                return mContext.getDrawable(R.drawable.notification_icon__8_white);
            case -7:
                return mContext.getDrawable(R.drawable.notification_icon__7_white);
            case -6:
                return mContext.getDrawable(R.drawable.notification_icon__6_white);
            case -5:
                return mContext.getDrawable(R.drawable.notification_icon__5_white);
            case -4:
                return mContext.getDrawable(R.drawable.notification_icon__4_white);
            case -3:
                return mContext.getDrawable(R.drawable.notification_icon__3_white);
            case -2:
                return mContext.getDrawable(R.drawable.notification_icon__2_white);
            case -1:
                return mContext.getDrawable(R.drawable.notification_icon__1_white);
            case 0:
                return mContext.getDrawable(R.drawable.notification_icon_0_white);
            case 1:
                return mContext.getDrawable(R.drawable.notification_icon_1_white);
            case 2:
                return mContext.getDrawable(R.drawable.notification_icon_2_white);
            case 3:
                return mContext.getDrawable(R.drawable.notification_icon_3_white);
            case 4:
                return mContext.getDrawable(R.drawable.notification_icon_4_white);
            case 5:
                return mContext.getDrawable(R.drawable.notification_icon_5_white);
            case 6:
                return mContext.getDrawable(R.drawable.notification_icon_6_white);
            case 7:
                return mContext.getDrawable(R.drawable.notification_icon_7_white);
            case 8:
                return mContext.getDrawable(R.drawable.notification_icon_8_white);
            case 9:
                return mContext.getDrawable(R.drawable.notification_icon_9_white);
            case 10:
                return mContext.getDrawable(R.drawable.notification_icon_10_white);
            case 11:
                return mContext.getDrawable(R.drawable.notification_icon_11_white);
            case 12:
                return mContext.getDrawable(R.drawable.notification_icon_12_white);
            case 13:
                return mContext.getDrawable(R.drawable.notification_icon_13_white);
            case 14:
                return mContext.getDrawable(R.drawable.notification_icon_14_white);
            case 15:
                return mContext.getDrawable(R.drawable.notification_icon_15_white);
            case 16:
                return mContext.getDrawable(R.drawable.notification_icon_16_white);
            case 17:
                return mContext.getDrawable(R.drawable.notification_icon_17_white);
            case 18:
                return mContext.getDrawable(R.drawable.notification_icon_18_white);
            case 19:
                return mContext.getDrawable(R.drawable.notification_icon_19_white);
            case 20:
                return mContext.getDrawable(R.drawable.notification_icon_20_white);
            case 21:
                return mContext.getDrawable(R.drawable.notification_icon_21_white);
            case 22:
                return mContext.getDrawable(R.drawable.notification_icon_22_white);
            case 23:
                return mContext.getDrawable(R.drawable.notification_icon_23_white);
            case 24:
                return mContext.getDrawable(R.drawable.notification_icon_24_white);
            case 25:
                return mContext.getDrawable(R.drawable.notification_icon_25_white);
            case 26:
                return mContext.getDrawable(R.drawable.notification_icon_26_white);
            case 27:
                return mContext.getDrawable(R.drawable.notification_icon_27_white);
            case 28:
                return mContext.getDrawable(R.drawable.notification_icon_28_white);
            case 29:
                return mContext.getDrawable(R.drawable.notification_icon_29_white);
            case 30:
                return mContext.getDrawable(R.drawable.notification_icon_30_white);
            case 31:
                return mContext.getDrawable(R.drawable.notification_icon_31_white);
            case 32:
                return mContext.getDrawable(R.drawable.notification_icon_32_white);
            case 33:
                return mContext.getDrawable(R.drawable.notification_icon_33_white);
            case 34:
                return mContext.getDrawable(R.drawable.notification_icon_34_white);
            case 35:
                return mContext.getDrawable(R.drawable.notification_icon_35_white);
            case 36:
                return mContext.getDrawable(R.drawable.notification_icon_36_white);
            case 37:
                return mContext.getDrawable(R.drawable.notification_icon_37_white);
            case 38:
                return mContext.getDrawable(R.drawable.notification_icon_38_white);
            case 39:
                return mContext.getDrawable(R.drawable.notification_icon_39_white);
            case 40:
                return mContext.getDrawable(R.drawable.notification_icon_40_white);
            case 41:
                return mContext.getDrawable(R.drawable.notification_icon_41_white);
            case 42:
                return mContext.getDrawable(R.drawable.notification_icon_42_white);
            case 43:
                return mContext.getDrawable(R.drawable.notification_icon_43_white);
            case 44:
                return mContext.getDrawable(R.drawable.notification_icon_44_white);
            case 45:
                return mContext.getDrawable(R.drawable.notification_icon_45_white);
            case 46:
                return mContext.getDrawable(R.drawable.notification_icon_46_white);
            case 47:
                return mContext.getDrawable(R.drawable.notification_icon_47_white);
            case 48:
                return mContext.getDrawable(R.drawable.notification_icon_48_white);
            case 49:
                return mContext.getDrawable(R.drawable.notification_icon_49_white);
            case 50:
                return mContext.getDrawable(R.drawable.notification_icon_50_white);

        }
        return null;

    }
}
