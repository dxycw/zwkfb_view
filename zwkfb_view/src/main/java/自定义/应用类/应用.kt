@file:Suppress("DEPRECATION")

package 自定义.应用类

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.util.TypedValue
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toDrawable
import androidx.core.net.toUri
import 安卓.内容.主题
import 安卓.内容.包名
import 安卓.内容.包管理器
import 安卓.内容.资源
import 安卓.组件.吐司
import 安卓.组件.显示
import java.io.File
import kotlin.apply
import kotlin.toString



//==================================================================================

fun Context.判断是否有应用(包名: String): Boolean {
    // 尝试获取应用的信息
    try {
        包管理器.getPackageInfo(包名, 0)
        // 如果没有抛出异常，则说明应用已安装
        return true
    } catch (e: PackageManager.NameNotFoundException) {
        // 如果抛出NameNotFoundException，则说明应用未安装
        return false
    }
}

//==================================================================================

@SuppressLint("UnsafeImplicitIntentLaunch")
fun Context.打开应用(包名: String) {
    try {
        // 创建Intent
        val intent = Intent(Intent.ACTION_VIEW)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        intent.setData(("market://details?id=$包名").toUri())
        startActivity(intent) // 启动Intent
    } catch (e: Exception) {
        // 如果没有应用可以处理，则提示用户
        Toast.makeText(this, "没有安装此应用", Toast.LENGTH_SHORT).show()
    }
}

//==================================================================================

fun Context.打开应用商店(): Boolean {
    var 是否安装应用商店 = false
    var 应用商店包名 = ""
    if (判断是否有应用("com.android.vending")) { //谷歌商店
        应用商店包名 = "com.android.vending"
        是否安装应用商店 = true
    }
    if (判断是否有应用("com.xiaomi.market") && !是否安装应用商店) { //小米商店
        应用商店包名 = "com.xiaomi.market"
        是否安装应用商店 = true
    }
    if (判断是否有应用("com.bbk.appstore") && !是否安装应用商店) { //vivo应用商店
        应用商店包名 = "com.bbk.appstore"
        是否安装应用商店 = true
    }
    if (判断是否有应用("com.tencent.android.qqdownloader") && !是否安装应用商店) { //腾讯应用宝
        应用商店包名 = "com.tencent.android.qqdownloader"
        是否安装应用商店 = true
    }
    if (判断是否有应用("com.baidu.appsearch") && !是否安装应用商店) { //百度应用商店
        应用商店包名 = "com.baidu.appsearch"
        是否安装应用商店 = true
    }
    if (判断是否有应用("com.huawei.appmarket") && !是否安装应用商店) { //华为应用商店
        应用商店包名 = "com.huawei.appmarket"
        是否安装应用商店 = true
    }
    if (判断是否有应用("com.qihoo.appstore") && !是否安装应用商店) { //360应用商店
        应用商店包名 = "com.qihoo.appstore"
        是否安装应用商店 = true
    }
    if (判断是否有应用("com.coolapk.market") && !是否安装应用商店) { //酷安
        应用商店包名 = "com.coolapk.market"
        是否安装应用商店 = true
    }

    if (!是否安装应用商店) {
        return false
    } else {
        try {
            // 创建Intent
            val intent = Intent(Intent.ACTION_VIEW)
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.setData(("market://details?id=$应用商店包名").toUri())
            startActivity(intent) // 启动Intent
            return true
        } catch (e: Exception) {
            // 如果没有应用可以处理，则提示用户
//                Toast.makeText(上下文, "无法打开应用商店", Toast.LENGTH_SHORT).show();
            return false
        }
    }
}

//==================================================================================

/**
 * 获取指定应用的图标。
 * 注意要在“setImageDrawable()”方法使用这个方法。
 * @param 应用包名  应用的包名。
 * @return 应用图标 Drawable 对象。
 */
fun Context.取应用图标(应用包名: String): Drawable? {
    try {
        val appInfo = 包管理器.getApplicationInfo(应用包名, 0)
        return appInfo.loadIcon(包管理器)
    } catch (e: PackageManager.NameNotFoundException) {
        return null
    }
}

//==================================================================================

/**
 * 获取当前应用的名称。
 * 比如：QQ
 * @return 应用名称。
 */
val Context.应用名称: String
    get() = 包管理器.getApplicationLabel(applicationInfo).toString()

/**
 * 获取当前应用的名称。
 * 比如：QQ
 * @return 应用名称。
 */
fun Context.取应用名称(): String {
    return 包管理器.getApplicationLabel(applicationInfo).toString()
}

//==================================================================================

/**
 * 获取当前应用的版本名称。
 * 比如：1.0.0
 * @return 版本名称。
 */
val Context.应用版本名称: String
    get(){
        try {
            val info = 包管理器.getPackageInfo(包名, 0) // 获取包信息
            return info.versionName ?: "Unknown" // 返回版本名称
        } catch (e: PackageManager.NameNotFoundException) {
            return "Unknown"
        }
    }

/**
 * 获取当前应用的版本名称。
 * 比如：1.0.0
 * @return 版本名称。
 */
fun Context.取应用版本名称(): String {
    try {
        val info = 包管理器.getPackageInfo(包名, 0) // 获取包信息
        return info.versionName ?: "Unknown" // 返回版本名称
    } catch (e: PackageManager.NameNotFoundException) {
        return "Unknown"
    }
}

//==================================================================================

/**
 * 获取当前应用的版本号。
 * 比如：1
 * @return 版本号。
 */
val Context.应用版本号: Int
    get(){
        return try {
            val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                包管理器.getPackageInfo(包名,
                    PackageManager.PackageInfoFlags.of(0)
                )
            } else { 包管理器.getPackageInfo(包名, 0) }
            packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) { 0 }
    }

/**
 * 获取当前应用的版本号。
 * 比如：1
 * @return 版本号。
 */
fun Context.取应用版本号(): Int {
    return try {
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            包管理器.getPackageInfo(包名,
                PackageManager.PackageInfoFlags.of(0)
            )
        } else { 包管理器.getPackageInfo(包名, 0) }
        packageInfo.versionCode
    } catch (e: PackageManager.NameNotFoundException) { 0 }
}

//==================================================================================

@SuppressLint("UnspecifiedRegisterReceiverFlag")
fun Context.注册APK下载监听(下载Id: Long, 文件名: String) {
    // 创建广播接收器
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // 获取下载完成的 ID
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            // 如果下载完成的 ID 和传入的 ID 相同
            if (id == 下载Id) {
                // 下载完成，尝试安装 APK
                安装APK(文件名)
                // 注销广播接收器
                try {
                    context.unregisterReceiver(this)
                } catch (e: Exception) {
                    // 忽略注销异常
                }
            }
        }
    }
    // 注册广播接收器
    val filter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
    // 如果 Android 版本大于等于 Android 13
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        // Android 13+ 需要指定 RECEIVER_EXPORTED 或 RECEIVER_NOT_EXPORTED
        registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED)
    } else {
        registerReceiver(receiver, filter)
    }
}

//==================================================================================

@SuppressLint("UnsafeImplicitIntentLaunch", "QueryPermissionsNeeded", "ObsoleteSdkInt")
fun Context.安装APK(文件名: String) {
    try {
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
            文件名
        )
        if (!file.exists()) {
            吐司.制作文本(this, "APK文件不存在", 吐司.短).显示()
            return
        }
        // 检查安装权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!包管理器.canRequestPackageInstalls()) {
                吐司.制作文本(this, "请在设置中允许安装未知应用", 吐司.长).显示()
                // 可以引导用户到设置页面
                try {
                    val intent = Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES)
                    intent.data = "package:${包名}".toUri()
                    startActivity(intent)
                } catch (e: Exception) {
                    // 忽略异常
                }
                return
            }
        }
        val uri: Uri =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                // Android 7.0 及以上使用 FileProvider
                try {
                    FileProvider.getUriForFile(this, "${包名}.fileprovider", file)
                } catch (e: Exception) {
                    吐司.制作文本(this, "文件提供者配置错误，请检查 AndroidManifest.xml", 吐司.长).显示()
                    return
                }
            } else {
                // Android 7.0 以下使用 file:// URI
                Uri.fromFile(file)
            }
        val installIntent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(uri, "application/vnd.android.package-archive")
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        // 检查是否有应用可以处理此 Intent
        val resolveInfo = 包管理器.queryIntentActivities(installIntent, PackageManager.MATCH_DEFAULT_ONLY)
        if (resolveInfo.isNotEmpty()) {
            startActivity(installIntent)
            吐司.制作文本(this, "正在启动安装程序...", 吐司.短).显示()
        } else {
            吐司.制作文本(this, "未找到APK安装程序", 吐司.短).显示()
        }
    } catch (e: SecurityException) {
        吐司.制作文本(this, "权限不足，请检查存储权限", 吐司.短).显示()
    } catch (e: Exception) {
        吐司.制作文本(this, "安装失败：${e.message}", 吐司.短).显示()
    }
}

//==================================================================================