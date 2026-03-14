package 自定义.系统类;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.provider.Settings;

import java.util.Locale;

public class 应用语言设置 {

    /* 保存用户手动选择的语言，空串表示“跟随系统” */
    @SuppressLint("CommitPrefEdits")
    public static void 置应用语言(Activity 上下文, String 语言) {
        PreferenceManager.getDefaultSharedPreferences(上下文).edit().putString("应用语言配置", 语言);
        上下文.recreate();
    }

    /* 读取保存的语言，可能为空串 */
    public static String 取应用语言(Context 上下文) {
        return PreferenceManager.getDefaultSharedPreferences(上下文).getString("应用语言配置", "");
    }

    public static void 打开系统设置语言界面(Context 上下文) {
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        上下文.startActivity(intent);
    }

    /* 包装一个新的 Context */
    public static Context 封装上下文(Context 上下文) {
        String 语言 = 取应用语言(上下文);
        Locale 地区 = switch (语言){
            case "" -> 取系统语言(); // 跟随系统    isBlank()：是否为空或空格，空串或空白串
            default -> Locale.forLanguageTag(语言); // 手动
        };
        Configuration 配置 = new Configuration();
        配置.setLocale(地区);
        配置.setLocales(new LocaleList(地区));
        return 上下文.createConfigurationContext(配置);
    }

    /* 获取系统首选语言 */
    private static Locale 取系统语言(){
        return Resources.getSystem().getConfiguration().getLocales().get(0);
    }



}
