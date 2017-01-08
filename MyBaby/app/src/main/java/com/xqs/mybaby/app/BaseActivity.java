
package com.xqs.mybaby.app;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.xqs.mybaby.R;
import com.xqs.mybaby.app.BaseApp;

import org.greenrobot.eventbus.EventBus;

/**
 * 基类Activity
 *
 */
public class BaseActivity extends Activity {

	
	public static BaseApp app;

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		app = (BaseApp) this.getApplication();
		
	}


	
	public Dialog showDialog(final Context context) {
		Dialog dialog = ProgressDialog.show(context,null,  getString(R.string.load_message));
		dialog.setCancelable(true);
		dialog.show();
		return dialog;
	}

	@Override
	protected void onStart() {
		super.onStart();
		//注册eventbus
		EventBus.getDefault().register(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
		//注销eventbus
		EventBus.getDefault().unregister(this);
	}



}
