package 自定义.主题类;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatDelegate;

public class 主题类 {

    public static int 白色主题 = 0;
    public static int 黑色主题 = 1;
    public static int 跟随系统 = 2;

    public static void 主题配置(Context 上下文, int 主题设置) {
        切换主题(上下文, 主题设置);
    }

    public static int 取背景颜色(Context 上下文) {
        TypedValue typedValue = new TypedValue();
        上下文.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true);
        return typedValue.data;
    }

    public static boolean 是否是深色模式(Context 上下文) {
        return (上下文.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
    }

    public static int 图标主题颜色(Context 上下文) {
        return 是否是深色模式(上下文)? Color.WHITE : Color.BLACK;
    }

    public static int 边框主题颜色(Context 上下文) {
        return 是否是深色模式(上下文)? Color.WHITE : Color.LTGRAY;
    }

    public static void 切换主题(Context 上下文, int 切换主题) {
        if (切换主题 == 0) {
            //白色主题
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //            //设置状态栏字体黑色
//            上下文.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (切换主题 == 1) {
            //黑色主题
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //            //设置状态栏字体白色
//            上下文.getWindow().getDecorView().setSystemUiVisibility(View.ACCESSIBILITY_LIVE_REGION_NONE);
        } else if (切换主题 == 2) {
            // 设置应用主题模式为跟随系统
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        }
    }


//    public static void 自定义主题(Activity 上下文, String 主题名){
//        int 主题 = 0;
//        if(主题名.equals("白色主题") || 主题名.isEmpty()){
//            主题 = zwkfb.view.R.style.Theme_灵阁;
//        }
//        上下文.setTheme(主题);
//    }

}
