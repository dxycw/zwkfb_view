package 自定义.系统类;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class 屏幕类 {

    public static int sp转dp(Context 上下文, int sp) {
        float 参数 = 上下文.getResources().getDisplayMetrics().density;
        return (int)(sp / 参数);
    }

    public static int sp转px(Context 上下文, int sp) {
        float 参数 = 上下文.getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp * 参数);
    }

    public static int dp转sp(Context 上下文, int dp) {
        float scaledDensity = 上下文.getResources().getDisplayMetrics().scaledDensity;
        return (int)(dp * scaledDensity / 上下文.getResources().getDisplayMetrics().density);
    }

    public static int dp转px(Context 上下文, int dp) {
        float 密度 = 上下文.getResources().getDisplayMetrics().density;
        return (int) (dp * 密度);
    }

    public static int px转dp(Context 上下文, int px) {
        float 参数 = 上下文.getResources().getDisplayMetrics().density;
        return (int) (px / 参数);
    }

    public static int px转sp(Context 上下文, int px) {
        float 参数 = 上下文.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / 参数);
    }

    //============================================================================================

    public static int 取用户屏幕宽度(Context 上下文) {
        WindowManager windowManager = (WindowManager)上下文.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics(); // 初始化DisplayMetrics对象
        windowManager.getDefaultDisplay().getMetrics(displayMetrics); // 获取显示信息
        return displayMetrics.widthPixels;
    }

    public static int 取用户屏幕高度(Context 上下文) {
        // 获取WindowManager服务
        WindowManager windowManager = (WindowManager)上下文.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics(); // 初始化DisplayMetrics对象
        windowManager.getDefaultDisplay().getMetrics(displayMetrics); // 获取显示信息
        return displayMetrics.heightPixels;
    }


}
