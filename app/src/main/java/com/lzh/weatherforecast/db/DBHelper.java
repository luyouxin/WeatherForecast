package com.lzh.weatherforecast.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lzh on 2016/4/29.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "my_city.db";
    private static final int version = 2;
    private String createTable = "create table my_city (" + "_id integer primary key autoincrement,name vachar,city_id vacher )";
    private Context mContext;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
