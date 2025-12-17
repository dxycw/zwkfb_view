package 自定义.系统类

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import androidx.core.net.toUri
import 安卓.内容.切换窗口
import 安卓.内容.资源
import 自定义.文件类.随机文件名
import java.io.File
import java.io.IOException


object 系统类 {
    fun 获取设备的架构(): String? {
        return Build.SUPPORTED_ABIS[0] // 获取设备架构 例如：arm64-v8a
    }

    fun 获取手机品牌(): String? {
        return Build.BRAND // 获取手机品牌 例如：Xiaomi
    }

    fun 获取手机厂商(): String? {
        return Build.MANUFACTURER // 获取厂商 例如：Xiaomi
    }

    @JvmStatic
    fun 获取手机型号(): String? {
        return Build.MODEL // 获取手机型号 例如：MI 6
    }

    @JvmStatic
    fun 获取手机系统版本号(): String? {
        return Build.VERSION.RELEASE // 获取系统版本号 例如：8.0
    }

    fun 获取手机唯一标识(): String? {
        return Build.SERIAL // 获取手机唯一标识 例如：9d0c0d0c0d0c0d0c
    }

    fun 获取设备唯一标识(上下文: Context): String? {
        return Settings.Secure.getString(上下文.contentResolver, Settings.Secure.ANDROID_ID)
    }

}


fun Context.获取设备唯一标识(): String? {
    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
}

//==============================================================================================

fun Context.是否处于竖屏(): Boolean {
    val config = 资源.configuration
    return config.orientation == Configuration.ORIENTATION_PORTRAIT
}

fun Context.是否处于横屏(): Boolean {
    val config = 资源.configuration
    return config.orientation == Configuration.ORIENTATION_LANDSCAPE
}

//==============================================================================================

fun Activity.切换竖屏() {
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
}

fun Activity.切换横屏() {
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
}

//==============================================================================================

fun Context.是否为模拟器(): Boolean {
    val strArr = arrayOf("Qualcomm", "Intel", "MTK", "MediaTek", "Exynos", "ARM")
    return strArr.any { Build.MANUFACTURER.contains(it) }
}

fun Context.是否为手机(): Boolean {
    return (resources.configuration.screenLayout
            and Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE
}

fun Context.是否为平板(): Boolean {
    return (resources.configuration.screenLayout
            and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
}

//==============================================================================================

/**
 * 分享文本
 * @param 文本 要分享的文本
 */
fun Context.分享文本(文本: String?) {
    // 创建一个分享的Intent
    val 分享 = Intent(Intent.ACTION_SEND)
    // 设置分享的内容类型
    分享.setType("text/plain")
    // 添加分享的内容
    分享.putExtra(Intent.EXTRA_TEXT, 文本)
    // 创建一个选择器Intent，用于显示分享对话框
    startActivity(Intent.createChooser(分享, null))
}


//==============================================================================

/**
 * 判断给定的URL是否为下载链接
 * @param url 需要判断的URL字符串
 * @return 如果URL以常见的下载文件扩展名结尾，则返回true，否则返回false
 */
fun 是否是下载链接(url: String): Boolean {
    // 定义常见的下载文件扩展名数组
    val 扩展名 =
        arrayOf<String?>(".zip", ".pdf", ".docx", ".xlsx", ".pptx", ".jpg", ".png", ".gif", ".apk")
    // 遍历扩展名数组，检查URL是否以任何扩展名结尾
    for (i in 扩展名) {
        // 使用非空断言操作符(!!)确保i不为null
        if (url.endsWith(i!!)) {
            return true
        }
    }
    return false
}

//==================================================================

fun Context.下载(url: String?) {
    val request = DownloadManager.Request(url?.toUri())
    //设置在什么网络情况下进行下载
    request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI)
    //设置通知栏标题
    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE)
    request.setTitle("下载")
    request.setAllowedOverRoaming(false)
    //设置文件存放目录
    request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS, "mydown")
}

//==================================================================

fun Activity.打开相册(请求码: Int) {
    val intent = Intent(Intent.ACTION_PICK)
    intent.type = "image/*"
    startActivityForResult(intent, 请求码)
}

//==================================================================

// 选择文件及拍照
fun Activity.选择文件及拍照功能(请求码: Int): String {
    var 图片路径 = ""
    val saveName = Environment.getExternalStorageDirectory()
        .path + "/" + Environment.DIRECTORY_DCIM + "/Camera/"
    //打开相机intent
    var intent: Intent? = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
    if (intent!!.resolveActivity(packageManager) != null) {
        val photoFile: File? = File(saveName + 随机文件名() + ".jpg")
        if (!photoFile!!.exists()) {
            try {
                photoFile.createNewFile()
            } catch (e: IOException) {
                e.printStackTrace()
                return ""
            }
            图片路径 = "file:" + photoFile.absolutePath // 拍照
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile)) // 把Uri赋值给takePictureIntent
        } else {
            intent = null
        }
    }
    val takeoutArray = if (intent != null) {
        arrayOf(intent)
    } else {
        arrayOfNulls<Intent>(0)
    }

    // 选择所有文件
    val intent2 = Intent(Intent.ACTION_GET_CONTENT)
    intent2.setType("*/*") // 如果需要特定类型的文件，可以修改这个参数，例如：application/pdf

    //使用系统选择器
    val chooserIntent = Intent(Intent.ACTION_CHOOSER)
    // 设置拍照
    chooserIntent.putExtra(Intent.EXTRA_INTENT, intent) // 选择文件
    chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, takeoutArray) // 额外的intent
    // 设置选择文件
    chooserIntent.putExtra(Intent.EXTRA_INTENT, intent2)
    startActivityForResult(chooserIntent, 请求码)
    return 图片路径
}

//==================================================================

