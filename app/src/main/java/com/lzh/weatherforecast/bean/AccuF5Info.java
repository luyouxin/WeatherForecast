package com.lzh.weatherforecast.bean;

import java.util.List;

/**
 * Created by lzh on 2016/5/9.
 */
public class AccuF5Info {


    public String EffectiveEpochDate;
    public String EffectiveDate;

    public AccuF5Info(String effectiveEpochDate, String effectiveDate, List<DailyForecastsBean> dailyForecasts) {
        EffectiveEpochDate = effectiveEpochDate;
        EffectiveDate = effectiveDate;
        DailyForecasts = dailyForecasts;
    }

    public List<DailyForecastsBean> DailyForecasts;

    public class DailyForecastsBean {
        public String Date;  //日期
        public String EpochDate;
        public String Sun_Rise;//日出时间
        public String Sun_EpochRise;
        public String Sun_Set;//日落时间
        public String Sun_EpochSet;
        public String PrecipitationProbability;//降雨概率

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
