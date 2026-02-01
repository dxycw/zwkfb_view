package 自定义.权限类

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

//==============================================================================================

fun 检查并申请存储权限(上下文: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        // Android 13+
        val 读取图片权限 =ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_MEDIA_IMAGES)
        val 读取视频权限 =ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_MEDIA_VIDEO)
        val 读取音频权限 =ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_MEDIA_AUDIO)
        if (读取图片权限 != PackageManager.PERMISSION_GRANTED || 读取视频权限 != PackageManager.PERMISSION_GRANTED ||
            读取音频权限 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(上下文,
                arrayOf(
                    Manifest.permission.READ_MEDIA_IMAGES, // 读取图片权限
                    Manifest.permission.READ_MEDIA_VIDEO,  // 读取视频权限
                    Manifest.permission.READ_MEDIA_AUDIO   // 读取音频权限
                ),
                1001 // 请求码
            )
        }
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
        // Android 11-12
        val 读权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_EXTERNAL_STORAGE)
        if (读权限 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(上下文,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), // 读取存储权限
                1001 // 请求码
            )
        }
    }else{
        // Android 10 及以下
        val 写权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (写权限 != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(上下文,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), // 写入存储权限
                1001 // 请求码
            )
        }
    }
}

//==============================================================================================

fun 检查并申请相机权限(上下文: Activity) {
    //所有版本都支持相机权限
    val 相机权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.CAMERA)
    if (相机权限 != PackageManager.PERMISSION_GRANTED ) {
        ActivityCompat.requestPermissions(上下文,
            arrayOf(Manifest.permission.CAMERA),// 相机权限
            1002 // 请求码
        )
    }
}

//==============================================================================================

fun 检查并申请通知权限(上下文: Activity) {
    //通知权限需要13.0以上
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val 通知权限 =  ContextCompat.checkSelfPermission(上下文, Manifest.permission.POST_NOTIFICATIONS)
        if (通知权限 != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(上下文,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),// 通知权限
                1003 // 请求码
            )
        }
    }
}
//==============================================================================================