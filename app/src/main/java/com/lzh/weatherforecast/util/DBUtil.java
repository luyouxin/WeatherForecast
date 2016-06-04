package com.lzh.weatherforecast.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lzh.weatherforecast.bean.CityInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzh on 2016/4/22.
 */
public class DBUtil {
    private String DB_NAME = "db_weather.db";
    private Context mContext;

    public DBUtil(Context mContext) {
        this.mContext = mContext;
    }

    //把assets目录下的db文件复制到dbpath下
    public void initDBManager(String packName) {
        String dbPath = "/data/data/" + packName
                + "/" + DB_NAME;
        if (!new File(dbPath).exists()) {
            try {
                FileOutputStream out = new FileOutputStream(dbPath);
                InputStream in = mContext.getAssets().open("db_weather.db");
                byte[] buffer = new byte[1024];
                int readBytes = 0;
                while ((readBytes = in.read(buffer)) != -1)
                    out.write(buffer, 0, readBytes);
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public SQLiteDatabase getSqLiteDatabase(String packName) {
        String dbPath = "/data/data/" + packName
                + "/" + DB_NAME;
        return SQLiteDatabase.openOrCreateDatabase(dbPath, null);
    }

    //查询普通city
    public CityInfo queryCommonCity(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {
        CityInfo city = null;
        try {
            String table = "citys";
            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                String name = cursor.getString(cursor
                        .getColumnIndex("name"));
                String cityID = cursor.getString(cursor.getColumnIndex("city_num"));
                city = new CityInfo(name, cityID);
                cursor.moveToNext();
                cursor.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return city;
    }

    //查询普通city
    public List<CityInfo> queryCommonCityList(SQLiteDatabase sqliteDB, String[] columns, String selection, String[] selectionArgs) {
        List<CityInfo> cityInfoList = new ArrayList<CityInfo>();
        CityInfo cityInfo = null;
        try {
            String table = "citys";
            Cursor cursor = sqliteDB.query(table, columns, selection, selectionArgs, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String name = cursor.getString(cursor
                        .getColumnIndex("name"));
                String cityID = cursor.getString(cursor.getColumnIndex("city_num"));
                cityInfo = new CityInfo(name, cityID);
                cityInfoList.add(cityInfo);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityInfoList;
    }

    //查询hotcity
    public List<CityInfo> queryHotCity(SQLiteDatabase sqliteDB) {
        List<CityInfo> cityInfoList = new ArrayList<CityInfo>();
        try {
            String table = "hotcity";
            Cursor cursor = sqliteDB.query(table, null, null, null, null, null, null);
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String name = cursor.getString(cursor
                        .getColumnIndex("name"));
                String cityID = cursor.getString(cursor.getColumnIndex("posID"));
                CityInfo cityInfo = new CityInfo(name, cityID);
                cityInfoList.add(cityInfo);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cityInfoList;
    }
    //搜索city

}
