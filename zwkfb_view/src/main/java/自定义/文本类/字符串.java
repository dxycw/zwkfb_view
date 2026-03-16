package 自定义.文本类;

import android.annotation.SuppressLint;

import kotlin.text.Regex;

public class 字符串 {

    public static String 去除HTML标签(CharSequence 文本){
        Regex regex = new Regex("<[^>]+>");
        return regex.replace(文本, "");
    }


    /**
     * 时间格式转换 ,比如：00:00:00
     * @param 时间 总秒数
     * @return 时间格式字符串
     */
    @SuppressLint("DefaultLocale")
    public static String 取总秒数转时间格式(long 时间) {
        long 总秒数 = 时间 / 1000;
        long h = 总秒数 / 3600; // 小时
        long m = (总秒数 % 3600); // 60  // 分钟
        long s = 总秒数 % 60;  // 秒
        return  (h > 0)? String.format("%d:%02d:%02d", h, m, s) : String.format("%02d:%02d", m, s);
    }

}
