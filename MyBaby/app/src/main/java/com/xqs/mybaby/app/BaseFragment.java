
package com.xqs.mybaby.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;


public class BaseFragment extends Fragment {


    public BaseApp app;
    
	public BaseFragment() {
    }
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        
        app = BaseApp.getInstance();
	}

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }


}
