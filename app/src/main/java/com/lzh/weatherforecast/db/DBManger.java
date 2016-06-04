package com.lzh.weatherforecast.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lzh.weatherforecast.bean.CityInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzh on 2016/5/4.
 */
public class DBManger {
    private DBHelper dbHelper;
    private SQLiteDatabase sqLiteDatabase;
    private List<CityInfo> list;
    private String tableName = "my_city";

    public DBManger(Context context) {
        dbHelper = new DBHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public long insert(String nullColumnHack, ContentValues values) {
        return sqLiteDatabase.insert(tableName, nullColumnHack, values);
    }

    public int delete(String whereClause, String[] whereArgs) {
    return     sqLiteDatabase.delete(tableName, whereClause, whereArgs);
    }

    public List<CityInfo> query(String[] columns, String selection, String[] selectionArgs) {
        List<CityInfo> cityInfoList = new ArrayList<CityInfo>();
        CityInfo cityInfo = null;
        Cursor cursor = sqLiteDatabase.query(tableName, columns, selection, selectionArgs, null, null, "_id desc");
        if (cursor != null) {
            for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                String cityID = cursor.getString(cursor.getColumnIndex("city_id"));
                cityInfo = new CityInfo(name, cityID);
                cityInfoList.add(cityInfo);
            }
        }
        cursor.close();
        return cityInfoList;
    }

    public CityInfo querySingle(String[] columns, String selection, String[] selectionArgs) {
        CityInfo cityInfo = null;
        Cursor cursor = sqLiteDatabase.query(tableName, columns, selection, selectionArgs, null, null, null);
        cursor.getCount();
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String cityID = cursor.getString(cursor.getColumnIndex("city_id"));
            cityInfo = new CityInfo(name, cityID);
        }
        cursor.close();
        return cityInfo;
    }
}

