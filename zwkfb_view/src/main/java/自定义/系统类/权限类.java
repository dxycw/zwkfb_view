package 自定义.系统类;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class 权限类 {

    public static void 检查并申请存储权限(Activity 上下文) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+
            int 读取图片权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_MEDIA_IMAGES);
            int 读取视频权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_MEDIA_VIDEO);
            int 读取音频权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_MEDIA_AUDIO);
            if (读取图片权限 != PackageManager.PERMISSION_GRANTED || 读取视频权限 != PackageManager.PERMISSION_GRANTED ||
                    读取音频权限 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(上下文,
                        new String[]{
                                Manifest.permission.READ_MEDIA_IMAGES, // 读取图片权限
                                Manifest.permission.READ_MEDIA_VIDEO,  // 读取视频权限
                                Manifest.permission.READ_MEDIA_AUDIO   // 读取音频权限
                        },
                        1001 // 请求码
                );
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            // Android 11-12
            int 读权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (读权限 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(上下文,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, // 读取存储权限
                        1001 // 请求码
                );
            }
        }else{
            // Android 10 及以下
            int 写权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (写权限 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(上下文,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, // 写入存储权限
                        1001 // 请求码
                );
            }
        }
    }

    //==============================================================================================

    public static void 检查并申请相机权限(Activity 上下文) {
        //所有版本都支持相机权限
        int 相机权限 = ContextCompat.checkSelfPermission(上下文, Manifest.permission.CAMERA);
        if (相机权限 != PackageManager.PERMISSION_GRANTED ) {
            ActivityCompat.requestPermissions(上下文,
                    new String[]{Manifest.permission.CAMERA},// 相机权限
                    1002 // 请求码
            );
        }
    }

    //==============================================================================================

    public static void 检查并申请通知权限(Activity 上下文) {
        //通知权限需要13.0以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            int 通知权限 =  ContextCompat.checkSelfPermission(上下文, Manifest.permission.POST_NOTIFICATIONS);
            if (通知权限 != PackageManager.PERMISSION_GRANTED ) {
                ActivityCompat.requestPermissions(上下文,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},// 通知权限
                        1003 // 请求码
                );
            }
        }
    }

}
