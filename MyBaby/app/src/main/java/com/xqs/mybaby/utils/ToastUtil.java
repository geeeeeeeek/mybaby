package com.xqs.mybaby.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/1/4 0004.
 */

public  class ToastUtil {

    public static void showShort(Context context, String text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT);
    }
}