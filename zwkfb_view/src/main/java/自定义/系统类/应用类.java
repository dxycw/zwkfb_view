package 自定义.系统类;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import java.io.File;
import java.util.List;

import 安卓.组件.吐司;

public class 应用类 {

    public static boolean 判断是否有应用(Context 上下文, String 包名) {
        // 尝试获取应用的信息
        try {
            上下文.getPackageManager().getPackageInfo(包名, 0);
            // 如果没有抛出异常，则说明应用已安装
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            // 如果抛出NameNotFoundException，则说明应用未安装
            return false;
        }
    }


    public static void 打开应用(Context 上下文, String 包名) {
        try {
            // 创建Intent
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            intent.setData(Uri.parse("market://details?id=" + 包名));
            上下文.startActivity(intent); // 启动Intent
        } catch (Exception e) {
            // 如果没有应用可以处理，则提示用户
            Toast.makeText(上下文, "没有安装此应用", Toast.LENGTH_SHORT).show();
        }
    }



    public static boolean 打开应用商店(Context 上下文) {
        boolean 是否安装应用商店 = false;
        String 应用商店包名 = "";
        if (判断是否有应用(上下文, "com.android.vending")) { //谷歌商店
            应用商店包名 = "com.android.vending";
            是否安装应用商店 = true;
        }
        if (判断是否有应用(上下文,"com.xiaomi.market") && !是否安装应用商店) { //小米商店
            应用商店包名 = "com.xiaomi.market";
            是否安装应用商店 = true;
        }
        if (判断是否有应用(上下文,"com.bbk.appstore") && !是否安装应用商店) { //vivo应用商店
            应用商店包名 = "com.bbk.appstore";
            是否安装应用商店 = true;
        }
        if (判断是否有应用(上下文,"com.tencent.android.qqdownloader") && !是否安装应用商店) { //腾讯应用宝
            应用商店包名 = "com.tencent.android.qqdownloader";
            是否安装应用商店 = true;
        }
        if (判断是否有应用(上下文,"com.baidu.appsearch") && !是否安装应用商店) { //百度应用商店
            应用商店包名 = "com.baidu.appsearch";
            是否安装应用商店 = true;
        }
        if (判断是否有应用(上下文,"com.huawei.appmarket") && !是否安装应用商店) { //华为应用商店
            应用商店包名 = "com.huawei.appmarket";
            是否安装应用商店 = true;
        }
        if (判断是否有应用(上下文,"com.qihoo.appstore") && !是否安装应用商店) { //360应用商店
            应用商店包名 = "com.qihoo.appstore";
            是否安装应用商店 = true;
        }
        if (判断是否有应用(上下文,"com.coolapk.market") && !是否安装应用商店) { //酷安
            应用商店包名 = "com.coolapk.market";
            是否安装应用商店 = true;
        }

        if (!是否安装应用商店) {
            return false;
        } else {
            try {
                // 创建Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_LAUNCHER);
                intent.setData(Uri.parse("market://details?id=$应用商店包名"));
                上下文.startActivity(intent); // 启动Intent
                return true;
            } catch (Exception e) {
                // 如果没有应用可以处理，则提示用户
                Toast.makeText(上下文, "无法打开应用商店", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }


    /**
     * 获取指定应用的图标。
     * 注意要在“setImageDrawable()”方法使用这个方法。
     * @param 应用包名  应用的包名。
     * @return 应用图标 Drawable 对象。
     */
    public static Drawable 取应用图标(Context 上下文, String 应用包名) {
        try {
            ApplicationInfo appInfo = 上下文.getPackageManager().getApplicationInfo(应用包名, 0);
            return appInfo.loadIcon(上下文.getPackageManager());
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }


    /**
     * 获取当前应用的名称。
     * 比如：QQ
     * @return 应用名称。
     */
    public static String 取应用名称(Context 上下文) {
        return 上下文.getPackageManager().getApplicationLabel(上下文.getApplicationInfo()).toString();
    }

    /**
     * 获取当前应用的版本名称。
     * 比如：1.0.0
     * @return 版本名称。
     */
    public static String 取应用版本名称(Context 上下文) {
        try {
            PackageInfo info = 上下文.getPackageManager().getPackageInfo(上下文.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            return "Unknown";
        }
    }

    /**
     * 获取当前应用的版本号。
     * 比如：1
     * @return 版本号。
     */
    public static int 取应用版本号(Context 上下文) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                 return 上下文.getPackageManager().getPackageInfo(上下文.getPackageName(),
                        PackageManager.PackageInfoFlags.of(0)
                ).versionCode;
            } else {
                 return 上下文.getPackageManager().getPackageInfo(上下文.getPackageName(), 0).versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }


    //==================================================================================

    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public static void 注册APK下载监听(Context 上下文, Long 下载Id, String 文件名) {
        // 创建广播接收器
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
            // 获取下载完成的 ID
                Long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                // 如果下载完成的 ID 和传入的 ID 相同
                if (id.equals(下载Id)) {
                    // 下载完成，尝试安装 APK
                    安装APK(上下文,文件名);
                    // 注销广播接收器
                    try {
                        context.unregisterReceiver(this);
                    } catch (Exception e) {
                        // 忽略注销异常

                    }
                }
            }
        };

        // 注册广播接收器
        IntentFilter filter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        // 如果 Android 版本大于等于 Android 13
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ 需要指定 RECEIVER_EXPORTED 或 RECEIVER_NOT_EXPORTED
            上下文.registerReceiver(receiver, filter, Context.RECEIVER_EXPORTED);
        } else {
            上下文.registerReceiver(receiver, filter);
        }
    }

    //==================================================================================

    public static void 安装APK(Context 上下文, String 文件名) {
        try {
            File file = new File(
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    文件名
            );
            if (!file.exists()) {
                吐司.制作文本(上下文, "APK文件不存在", 吐司.长度_短).显示();
                return;
            }
            // 检查安装权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (!上下文.getPackageManager().canRequestPackageInstalls()) {
                    吐司.制作文本(上下文, "请在设置中允许安装未知应用", 吐司.长度_短).显示();
                    // 可以引导用户到设置页面
                    try {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES);
                        intent.setData(Uri.parse("package:" + 上下文.getPackageName()));
                        上下文.startActivity(intent);
                    } catch (Exception e) {
                        // 忽略异常
                    }
                    return;
                }
            }
            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            try {
                Uri uri = FileProvider.getUriForFile(上下文, "${包名}.fileprovider", file);
                installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } catch (Exception e) {
                吐司.制作文本(上下文, "文件提供者配置错误，请检查 AndroidManifest.xml", 吐司.长度_短).显示();
                return;
            }

            // 检查是否有应用可以处理此 Intent
            List<ResolveInfo> resolveInfo = 上下文.getPackageManager().queryIntentActivities(installIntent, PackageManager.MATCH_DEFAULT_ONLY);
            if (resolveInfo != null) {
                上下文.startActivity(installIntent);
                吐司.制作文本(上下文, "正在启动安装程序...", 吐司.长度_短).显示();
            } else {
                吐司.制作文本(上下文, "未找到APK安装程序", 吐司.长度_短).显示();
            }
        } catch (SecurityException e) {
            吐司.制作文本(上下文, "权限不足，请检查存储权限", 吐司.长度_短).显示();
        } catch (Exception e) {
            吐司.制作文本(上下文, "安装失败：" +e.getMessage(), 吐司.长度_短).显示();
        }
    }

    //==================================================================================

}
