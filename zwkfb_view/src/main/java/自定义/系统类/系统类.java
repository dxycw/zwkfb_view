package 自定义.系统类;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;

import java.io.File;
import java.io.IOException;

public class 系统类 {

    public static String 取设备的架构() {
        return Build.SUPPORTED_ABIS[0]; // 获取设备架构 例如：arm64-v8a
    }

    public static String 取手机品牌() {
        return Build.BRAND; // 获取手机品牌 例如：Xiaomi
    }

    public static String 取手机厂商() {
        return Build.MANUFACTURER; // 获取厂商 例如：Xiaomi
    }

    public static String 取手机型号() {
        return Build.MODEL; // 获取手机型号 例如：MI 6
    }

    public static String 取手机系统版本号() {
        return Build.VERSION.RELEASE; // 获取系统版本号 例如：8.0
    }

    @SuppressLint("HardwareIds")
    public static String 取手机唯一标识() {
        return Build.SERIAL; // 获取手机唯一标识 例如：9d0c0d0c0d0c0d0c
    }

    @SuppressLint("HardwareIds")
    public static String 取设备唯一标识(Context 上下文) {
        return Settings.Secure.getString(上下文.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    //==============================================================================================

    public static boolean 是否处于竖屏(Context 上下文) {
        Configuration config = 上下文.getResources().getConfiguration();
        return config.orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static boolean 是否处于横屏(Context 上下文) {
        Configuration config = 上下文.getResources().getConfiguration();
        return config.orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    //==============================================================================================

    public static void 切换竖屏(Activity 活动) {
        活动.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    public static void 切换横屏(Activity 活动) {
        活动.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    //==============================================================================================

    public static boolean 是否为模拟器(Context 上下文) {
        String[] strArr = new String[]{"Qualcomm", "Intel", "MTK", "MediaTek", "Exynos", "ARM"};
        return java.util.Arrays.stream(strArr).anyMatch(Build.MANUFACTURER::contains);
    }

    public static boolean 是否为手机(Context 上下文) {
        return (上下文.getResources().getConfiguration().screenLayout
        & Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static boolean 是否为平板(Context 上下文) {
        return (上下文.getResources().getConfiguration().screenLayout
        & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    //==============================================================================================

    /**
     * 分享文本
     * @param 文本 要分享的文本
     */
    public static void 分享文本(Context 上下文, String 文本) {
        // 创建一个分享的Intent
        Intent 分享 = new Intent(Intent.ACTION_SEND);
        // 设置分享的内容类型
        分享.setType("text/plain");
        // 添加分享的内容
        分享.putExtra(Intent.EXTRA_TEXT, 文本);
        // 创建一个选择器Intent，用于显示分享对话框
        上下文.startActivity(Intent.createChooser(分享, null));
    }

    //==============================================================================================

    /**
     * 判断给定的URL是否为下载链接
     * @param url 需要判断的URL字符串
     * @return 如果URL以常见的下载文件扩展名结尾，则返回true，否则返回false
     */
    public static boolean 是否是下载链接(String url) {
        // 定义常见地下载文件扩展名数组
        String[] 扩展名 = new String[] {".zip", ".pdf", ".docx", ".xlsx", ".pptx", ".jpg", ".png", ".gif", ".apk"};
        // 遍历扩展名数组，检查URL是否以任何扩展名结尾
        for (String i : 扩展名) {
            // 使用非空断言操作符(!!)确保i不为null
            if (url.endsWith(i)) {
                return true;
            }
        }
        return false;
    }

    //==============================================================================================

    public static void 下载(Context 上下文, String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //设置在什么网络情况下进行下载
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        //设置通知栏标题
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        request.setTitle("下载");
        request.setAllowedOverRoaming(false);
        //设置文件存放目录
        request.setDestinationInExternalFilesDir(上下文, Environment.DIRECTORY_DOWNLOADS, "mydown");
    }

    //==============================================================================================


    public static void 打开相册(Activity 活动, int 请求码) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        活动.startActivityForResult(intent, 请求码);
    }

    //==============================================================================================


    // 选择文件及拍照
    public static String 选择文件及拍照功能(Activity 活动, int 请求码) {
        var 图片路径 = "";
        String saveName = Environment.getExternalStorageDirectory()
                .getPath() + "/" + Environment.DIRECTORY_DCIM + "/Camera/";
        //打开相机intent
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(活动.getPackageManager()) != null) {
            File photoFile = new File(saveName + 文件类.随机文件名() + ".jpg");
            if (!photoFile.exists()) {
                try {
                    photoFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "";
                }
                图片路径 = "file:" + photoFile.getAbsolutePath(); // 拍照
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile)); // 把Uri赋值给takePictureIntent
            } else {
                intent = null;
            }
        }
        Intent[] takeoutArray = new Intent[]{intent};

        // 选择所有文件
        Intent intent2 = new Intent(Intent.ACTION_GET_CONTENT);
        intent2.setType("*/*"); // 如果需要特定类型的文件，可以修改这个参数，例如：application/pdf

        //使用系统选择器
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        // 设置拍照
        chooserIntent.putExtra(Intent.EXTRA_INTENT, intent); // 选择文件
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, takeoutArray); // 额外的intent
        // 设置选择文件
        chooserIntent.putExtra(Intent.EXTRA_INTENT, intent2);
        活动.startActivityForResult(chooserIntent, 请求码);
        return 图片路径;
    }

}
