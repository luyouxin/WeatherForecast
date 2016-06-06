package com.lzh.weatherforecast.bean;

/**
 * Created by lzh on 2016/5/9.
 */
public class IndexWeatherInfo {

    /**
     * code : fs
     * details : 属弱紫外辐射天气，长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。
     * index : 弱
     * name : 防晒指数
     * otherName :
     */

    public String code;
    public String details;
    public String index;
    public String name;
    public String otherName;

    public IndexWeatherInfo(String code, String otherName, String name, String index, String details) {
        this.code = code;
        this.otherName = otherName;
        this.name = name;
        this.index = index;
        this.details = details;
    }
}
