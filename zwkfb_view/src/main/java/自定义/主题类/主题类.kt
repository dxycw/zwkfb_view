package 自定义.主题类

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate


val Context.是否是深色模式: Boolean
    get() = 主题类.是否是深色模式(this)



val Context.图标主题颜色: Int
    get() = 主题类.图标主题颜色(this)

val Context.边框主题颜色: Int
    get() = 主题类.边框主题颜色(this)


fun Context.主题配置(主题设置: Int) {
    主题类.主题配置(this, 主题设置)
}

fun Context.取背景颜色(): Int {
    return 主题类.取背景颜色(this)
}

fun Context.切换主题(切换主题: Int) {
    if (切换主题 == 0) {
        //白色主题
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        //            //设置状态栏字体黑色
//            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    } else if (切换主题 == 1) {
        //黑色主题
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        //            //设置状态栏字体白色
//            this.getWindow().getDecorView().setSystemUiVisibility(View.ACCESSIBILITY_LIVE_REGION_NONE);
    } else if (切换主题 == 2) {
        // 设置应用主题模式为跟随系统
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    }
}