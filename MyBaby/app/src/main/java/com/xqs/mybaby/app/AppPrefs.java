package com.xqs.mybaby.app;

import android.content.Context;


/**
 *
 * 存储数据到手机本地
 *
 */
public class AppPrefs extends BasePrefs {

	private static final String PREFS_NAME = "AppPrefs";
	
	private static final String FIRST = "first";
	private static final String LAST_VERSION = "last_version";


	private AppPrefs(Context context) {
		super(context, PREFS_NAME);
	}
	
	public static AppPrefs get(Context context) {
		return new AppPrefs(context);
	}
	
	public int getFirst() {
		return getInt(FIRST, 1);
	}
	
	public void setFirst(int v) {
		putInt(FIRST, v);
		save();
	}
	
	public String getLastVersion() {
		return getString(LAST_VERSION, null);
	}
	
	public void setLastVersion(String v) {
		putString(LAST_VERSION, v);
	}
	

}
