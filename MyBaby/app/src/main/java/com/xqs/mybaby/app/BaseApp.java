
package com.xqs.mybaby.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;
import android.view.WindowManager;


public class BaseApp extends Application {

	private static BaseApp instance;
	private int defaultImageId;
	public String appVersion;
	public int  appVersionCode;
	public String screen;
	public int mScreenWidth = 0;
	public int mScreenHeight = 0;
	public float mDensity=1;

	@Override
	public void onCreate() {

		super.onCreate();

		instance = this;
		initConfig();

	}



	public static BaseApp getInstance() {
		return instance;
	}



	private void initConfig() {

		try {
			PackageManager packageManager = getPackageManager();
			PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
			appVersion = packInfo.versionName;
			appVersionCode=packInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}  
		

		
		WindowManager wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics dm = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(dm);
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
		screen = mScreenWidth + "*"+ mScreenHeight;
		mDensity=dm.density;

	}




}
