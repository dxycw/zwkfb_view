package 自定义.系统类

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.graphics.Color
import android.util.TypedValue
import androidx.appcompat.app.AppCompatDelegate


val Context.是否是深色模式: Boolean
    get() = (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES



val Context.图标主题颜色: Int
    get() = if (是否是深色模式) Color.WHITE else Color.BLACK

val Context.边框主题颜色: Int
    get() = if (是否是深色模式) Color.WHITE else Color.LTGRAY



fun Context.主题配置(是否自定义主题设置: Boolean, 是否深色浅色跟随系统主题设置: Int) {
    if (是否自定义主题设置){
        //主题类.自定义主题(上下文,应用配置模块.获取是否自定义主题颜色设置());
    }else{
        主题类.主题配置(this, 是否深色浅色跟随系统主题设置)
    }
}


object 主题类 {
    var 跟随系统: Int = 0
    var 白色主题: Int = 1
    var 黑色主题: Int = 2


    fun 主题配置(上下文: Context?, 主题设置: Int) {
        切换主题(上下文, 主题设置)
    }

    fun 获取主题背景颜色(上下文: Context): Int {
        val typedValue = TypedValue()
        上下文.theme.resolveAttribute(android.R.attr.colorBackground, typedValue, true)
        return typedValue.data
    }

    fun 获取主题背景颜色(上下文: Activity): Int {
        val typedValue = TypedValue()
        上下文.getTheme().resolveAttribute(android.R.attr.colorBackground, typedValue, true)
        return typedValue.data
    }

    @SuppressLint("InlinedApi")
    fun 切换主题(上下文: Context?, 切换主题: Int) {
        if (切换主题 == 0) {
            //白色主题
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            //            //设置状态栏字体黑色
//            上下文.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (切换主题 == 1) {
            //黑色主题
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            //            //设置状态栏字体白色
//            上下文.getWindow().getDecorView().setSystemUiVisibility(View.ACCESSIBILITY_LIVE_REGION_NONE);
        } else if (切换主题 == 2) {
            // 设置应用主题模式为跟随系统
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }

    @SuppressLint("InlinedApi")
    fun 切换主题(上下文: Activity?, 切换主题: Int) {
        if (切换主题 == 0) {
            //白色主题
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            //            //设置状态栏字体黑色
//            上下文.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (切换主题 == 1) {
            //黑色主题
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            //            //设置状态栏字体白色
//            上下文.getWindow().getDecorView().setSystemUiVisibility(View.ACCESSIBILITY_LIVE_REGION_NONE);
        } else if (切换主题 == 2) {
            // 设置应用主题模式为跟随系统
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
//    public static void 自定义主题(Activity 上下文,String 主题名){
    //        int 主题 = 0;
    //        if(主题名.equals("白色主题") || 主题名.isEmpty()){
    //            主题 = R.style.Theme_灵阁;
    //        }
    //        上下文.setTheme(主题);
    //    }
}