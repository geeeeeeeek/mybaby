package com.xqs.mybaby.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2017/1/3 0003.
 */

public class NetworkUtil {

    /**
     * 判断是否有网络连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            //判断NetworkInfo对象是否为空
            if (networkInfo != null)
                return networkInfo.isAvailable();
        }
        return false;
    }


    //是否wifi
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
        // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        // 获取NetworkInfo对象
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        //判断NetworkInfo对象是否为空 并且类型是否为WIFI
        if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
        return networkInfo.isAvailable();
        }
     return false;
    }


    /**
     * 判断MOBILE网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            //获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取NetworkInfo对象
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            //判断NetworkInfo对象是否为空 并且类型是否为MOBILE
            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
                return networkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 获取当前网络连接的类型信息 原生
     */
    public static int getConnectedType(Context context) {
        if (context != null) {
            //获取手机所有连接管理对象
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取NetworkInfo对象
            NetworkInfo networkInfo = manager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isAvailable()) {
                //返回NetworkInfo的类型
                return networkInfo.getType();
            }
        }
        return -1;
    }


}