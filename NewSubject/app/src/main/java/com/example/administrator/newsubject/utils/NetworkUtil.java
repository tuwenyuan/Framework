package com.example.administrator.newsubject.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author yelin.wu 16/3/3 下午2:48.
 * @description
 */
public class NetworkUtil {
    /**
     * 判断网络是否可用
     * @return
     */
    public static boolean isAvailable(Context context){
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo == null ? false : activeNetworkInfo.isAvailable();
    }

    /**
     * 网络是否不可用
     * @param context
     * @return
     */
    public static boolean isNotAvailable(Context context){
        return !isAvailable(context);
    }

    /**
     * 判断当前连接是否为WiFi
     * @param context
     * @return 无网络与其它网络返回false
     */
    public static boolean isWifi(Context context){
        if (context == null) {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.isAvailable() == false) {
            return false;
        }
        int type = activeNetworkInfo.getType();
        return type == ConnectivityManager.TYPE_WIFI;
    }
}
