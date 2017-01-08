
package com.xqs.mybaby.app;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import org.greenrobot.eventbus.EventBus;


public class BaseFragmentActivity extends FragmentActivity {

	public static BaseApp app;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		app = BaseApp.getInstance();


	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}


}
