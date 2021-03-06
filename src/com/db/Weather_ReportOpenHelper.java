package com.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Weather_ReportOpenHelper extends SQLiteOpenHelper {
	//Province表建表语句
	public static final String CREATE_PROVINCE="create table Province("
			+ "_id integer primary key autoincrement,"
			+"province_name text,"
			+ "province_code text)";
	//City表建表
	public 	static final String CREATE_CITY="create table City("
			+"_id integer primary key autoincrement,"
			+ "city_name text,"
			+ "city_code text,"
			+ "province_id integer)";
	//County建表语句
	public static final String CREATE_COUNTY="create table County("
			+ "_id integer primary key autoincrement,"
			+ "county_name text,"
			+ "county_code text,"
			+ "city_id integer)";

	public Weather_ReportOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_PROVINCE);//创建Province表
		db.execSQL(CREATE_CITY);//创建城市表
		db.execSQL(CREATE_COUNTY);//创建县城表
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
