package com.lzh.weatherforecast.bean;

import java.util.List;

/**
 * Created by lzh on 2016/5/9.
 */
public class AccuF5Info {

    /**
     * EffectiveEpochDate : 1463266800
     * EffectiveDate : 2016-05-15T07:00:00+08:00
     * DailyForecasts : [{"Date":"2016-05-09T07:00:00+08:00","EpochDate":"1462748400","Sun_Rise":"2016-05-09T05:05:00+08:00","Sun_EpochRise":"1462741500","Sun_Set":"2016-05-09T19:17:00+08:00","Sun_EpochSet":"1462792620","PrecipitationProbability":"60"},{"Date":"2016-05-10T07:00:00+08:00","EpochDate":"1462834800","Sun_Rise":"2016-05-10T05:04:00+08:00","Sun_EpochRise":"1462827840","Sun_Set":"2016-05-10T19:18:00+08:00","Sun_EpochSet":"1462879080","PrecipitationProbability":"0"},{"Date":"2016-05-11T07:00:00+08:00","EpochDate":"1462921200","Sun_Rise":"2016-05-11T05:03:00+08:00","Sun_EpochRise":"1462914180","Sun_Set":"2016-05-11T19:19:00+08:00","Sun_EpochSet":"1462965540","PrecipitationProbability":"23"},{"Date":"2016-05-12T07:00:00+08:00","EpochDate":"1463007600","Sun_Rise":"2016-05-12T05:02:00+08:00","Sun_EpochRise":"1463000520","Sun_Set":"2016-05-12T19:20:00+08:00","Sun_EpochSet":"1463052000","PrecipitationProbability":"40"},{"Date":"2016-05-13T07:00:00+08:00","EpochDate":"1463094000","Sun_Rise":"2016-05-13T05:01:00+08:00","Sun_EpochRise":"1463086860","Sun_Set":"2016-05-13T19:21:00+08:00","Sun_EpochSet":"1463138460","PrecipitationProbability":"0"}]
     */

    public String EffectiveEpochDate;
    public String EffectiveDate;

    public AccuF5Info(String effectiveEpochDate, String effectiveDate, List<DailyForecastsBean> dailyForecasts) {
        EffectiveEpochDate = effectiveEpochDate;
        EffectiveDate = effectiveDate;
        DailyForecasts = dailyForecasts;
    }

    /**
     * Date : 2016-05-09T07:00:00+08:00
     * EpochDate : 1462748400
     * Sun_Rise : 2016-05-09T05:05:00+08:00
     * Sun_EpochRise : 1462741500
     * Sun_Set : 2016-05-09T19:17:00+08:00
     * Sun_EpochSet : 1462792620
     * PrecipitationProbability : 60
     */

    public List<DailyForecastsBean> DailyForecasts;



    public class DailyForecastsBean {
        public String Date;
        public String EpochDate;
        public String Sun_Rise;
        public String Sun_EpochRise;
        public String Sun_Set;
        public String Sun_EpochSet;
        public String PrecipitationProbability;

        public DailyForecastsBean(String date, String precipitationProbability, String sun_EpochSet, String sun_Set, String sun_Rise, String epochDate, String sun_EpochRise) {
            Date = date;
            PrecipitationProbability = precipitationProbability;
            Sun_EpochSet = sun_EpochSet;
            Sun_Set = sun_Set;
            Sun_Rise = sun_Rise;
            EpochDate = epochDate;
            Sun_EpochRise = sun_EpochRise;
        }
    }
}
