package 自定义.网络类

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

/**
 * 判断当前是否有网络连接。
 * @return true：有网络连接；false：没有网络连接。
 */
val Context.是否有网: Boolean
    @SuppressLint("MissingPermission")
    get(){
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
        return false
    }


/**
 * 判断当前是否有网络连接。
 * @return true：有网络连接；false：没有网络连接。
 */
@SuppressLint("MissingPermission")
fun Context.是否有网(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (connectivityManager != null) {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
    return false
}


/**
 * 判断当前是否有网络连接，并返回网络类型。
 * @return 网络类型。
 */
val Context.是否有网及网络类型: String
    @SuppressLint("MissingPermission")
    get(){
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        if (connectivityManager != null) {
            val activeNetwork = connectivityManager.activeNetworkInfo
            return if (activeNetwork != null && activeNetwork.isConnected) {
                val type = activeNetwork.type
                when (type) {
                    ConnectivityManager.TYPE_WIFI -> { "Wi-Fi" }
                    ConnectivityManager.TYPE_MOBILE -> { "移动数据" }
                    else -> { "其他网络" }
                }
            } else { "未连接" }
        } else { return "网络不可用" }
    }

/**
 * 判断当前是否有网络连接，并返回网络类型。
 * @return 网络类型。
 */
@SuppressLint("MissingPermission")
fun Context.是否有网及网络类型(): String {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (connectivityManager != null) {
        val activeNetwork = connectivityManager.activeNetworkInfo
        return if (activeNetwork != null && activeNetwork.isConnected) {
            val type = activeNetwork.type
            when (type) {
                ConnectivityManager.TYPE_WIFI -> { "Wi-Fi" }
                ConnectivityManager.TYPE_MOBILE -> { "移动数据" }
                else -> { "其他网络" }
            }
        } else { "未连接" }
    } else { return "网络不可用" }
}