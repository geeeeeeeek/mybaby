package com.xqs.mybaby.utils;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;


public class Utils {

	//日志开关
	public static boolean LOGSWITCH = true;


    /**
	 * 检查版本
	 * @param newCode
	 * @param localCode
	 * @return
     */
	public static boolean compareAppVersionCode(String newCode , String localCode){
		boolean hasUpdate = false;
		try{
			long sVersion = Long.parseLong(newCode);
			long lVersion = Long.parseLong(localCode);
			if(sVersion > lVersion){
				hasUpdate = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		return hasUpdate;
	}


    public static  void log(String Tag,String content)
    {
        if(LOGSWITCH)
        {
            android.util.Log.w(Tag,content);
        }
    }

	public static void hideKeyboard(Activity activity) {
		if (activity.getCurrentFocus() != null
				&& activity.getCurrentFocus().getWindowToken() != null) {
			InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
			manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}




}
