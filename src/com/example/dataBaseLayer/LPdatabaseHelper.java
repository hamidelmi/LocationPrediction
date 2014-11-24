package com.example.dataBaseLayer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 *   Helper class to manage database which inherits from SQLiteOpenHelper
 */
public class LPdatabaseHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Location";

	public static final String  CREATE_SQL_TABEL = "";
	
	/**
	 *  constructor
	 */
	public LPdatabaseHelper (Context context ){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	/**
	 * OnCreate
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(CREATE_SQL_TABEL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
