package com.xqs.mybaby.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

	private int mVersion = 1;
	private String mDatabaseName;


	private Context mContext = null;

	private static DBManager mDBManager = null;


	private DBManager(Context mContext) {
		super();
		this.mContext = mContext;

	}

	public static DBManager getInstance(Context mContext, String databaseName) {
		if (null == mDBManager) {
			mDBManager = new DBManager(mContext);
		}
		mDBManager.mDatabaseName = databaseName;
		return mDBManager;
	}

	public String getDataBaseName(){
		return mDatabaseName;
	}

	public void setDataBaseName(String dbname){
		mDatabaseName = dbname;
	}
	
	public void closeDatabase(SQLiteDatabase dataBase, Cursor cursor) {
		if (null != dataBase) {
			dataBase.close();
		}
		if (null != cursor) {
			cursor.close();
		}
	}


	public SQLiteDatabase openDatabase() {
		return getDatabaseHelper().getWritableDatabase();
	}


	public MyDBHelper getDatabaseHelper() {
		return MyDBHelper.getInstance(mContext, this.mDatabaseName, null,
				this.mVersion);
	}

}
