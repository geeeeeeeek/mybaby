/**
 * 
 */
package com.xqs.mybaby.app;

import android.app.Dialog;
import android.widget.Toast;
import com.xqs.mybaby.R;
import com.xqs.mybaby.app.config.Constant;
import com.xqs.mybaby.utils.NetworkUtil;


public abstract class BaseAsyncTask<Params, Progress, Result> extends
		android.os.AsyncTask<Params, Process, Result> {
	private Dialog mDialog;

	public BaseAsyncTask() {
	}

	public BaseAsyncTask(Dialog dialog) {
		this.mDialog = dialog;
	}

	@Override
	protected void onPreExecute() {
		if (mDialog != null) {
			mDialog.show();
		}
		super.onPreExecute();
	}

	@Override
	protected Result doInBackground(Params... arg0) {
		if (doCheck()) {
			return onExecute(arg0);
		}
		return null;
	}

	protected abstract Result onExecute(Params... arg0);

	private boolean doCheck() {
		if (NetworkUtil.isNetworkConnected(BaseApp.getInstance())) {
			return true;
		}
		return false;
	}

	@Override
	protected void onPostExecute(Result result) {
		if (mDialog != null) {
			mDialog.dismiss();
			mDialog = null;
		}
		super.onPostExecute(result);

		if (result == null) {
			showNetworkExceptionTip();
			return;
		}
		
		if(result instanceof Integer){
			if(((Integer)result) != Constant.RESULT_SUCCESS){
				showNetworkExceptionTip();
				return;
			}
		}

		if (isCancelled()) {
			return;
		}
	}

	private void showNetworkExceptionTip(){
		Toast.makeText(BaseApp.getInstance(), R.string.network_exception, Toast.LENGTH_SHORT).show();
	}
}
