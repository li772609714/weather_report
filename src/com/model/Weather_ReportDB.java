package com.model;

import java.util.ArrayList;
import java.util.List;

import com.db.Weather_ReportOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Weather_ReportDB {
	// 数据库名
	public static final String DB_NAME = "weather_report";
	// 数据库版本
	public static final int VERSION = 1;
	private static Weather_ReportDB weather_ReportDB;
	private SQLiteDatabase db;

	// 将构造方法私有化
	private Weather_ReportDB(Context context) {
		Weather_ReportOpenHelper dbHelper = new Weather_ReportOpenHelper(
				context, DB_NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	// 获取Weather_ReportDB的实例
	public synchronized static Weather_ReportDB getInstance(Context context) {
		if (weather_ReportDB == null) {
			weather_ReportDB = new Weather_ReportDB(context);
		}
		return weather_ReportDB;
	}

	// 将Province实例存储到数据库中
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}

	// 将City实例存储到数据库中
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);

		}
	}

	// 将County实例存储到数据库中
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);

		}
	}

	// 从数据库中读取全国所有的省份信息
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex("province_code")));
				list.add(province);

			} while (cursor.moveToNext());
		}
		return list;
	}

	// 从数据库中读取某个省下所有城市信息
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id=?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());
		}
		return list;
	}
	//从数据库中读取某个城市下所有的县城信息
	public List<County> loadCounties(int cityId){
		List<County> list=new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?",
		new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
		do {
		County county = new County();
		county.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
		county.setCountyName(cursor.getString(cursor
		.getColumnIndex("county_name")));
		county.setCountyCode(cursor.getString(cursor
		.getColumnIndex("county_code")));
		county.setCityId(cityId);
		list.add(county);
		} while (cursor.moveToNext());
		}
		return list;
	}
}
