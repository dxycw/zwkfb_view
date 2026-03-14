package 自定义.网络类;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class 网络类 {


    /**
     * 判断当前是否有网络连接。
     * @return true：有网络连接；false：没有网络连接。
     */
    @SuppressLint("MissingPermission")
    public static boolean 是否有网(Context 上下文) {
        ConnectivityManager connectivityManager = (ConnectivityManager)上下文.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    /**
     * 判断当前是否有网络连接，并返回网络类型。
     * @return 网络类型。
     */
    @SuppressLint("MissingPermission")
    public static String 是否有网及网络类型(Context 上下文) {
        ConnectivityManager connectivityManager = (ConnectivityManager)上下文.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
             if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                int type = activeNetworkInfo.getType();
                 return switch (type) {
                     case ConnectivityManager.TYPE_WIFI -> "Wi-Fi";
                     case ConnectivityManager.TYPE_MOBILE -> "移动数据";
                     default -> "其他网络";
                 };
            } else {
                 return "未连接";
            }
        } else {
            return "网络不可用";
        }
    }



}


