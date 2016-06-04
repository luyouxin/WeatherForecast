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

    private String code;
    private String details;
    private String index;
    private String name;
    private String otherName;

    public IndexWeatherInfo(String code, String otherName, String name, String index, String details) {
        this.code = code;
        this.otherName = otherName;
        this.name = name;
        this.index = index;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }
}
