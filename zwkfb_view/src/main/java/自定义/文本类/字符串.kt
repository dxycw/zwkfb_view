package 自定义.文本类

import android.annotation.SuppressLint
import kotlin.text.replace
import kotlin.text.trim

fun String.去除HTML标签(): String
        = replace(Regex("<[^>]+>"), "").trim()


/**
 * 时间格式转换 ,比如：00:00:00
 * @param 时间 总秒数
 * @return 时间格式字符串
 */
@SuppressLint("DefaultLocale")
fun 取总秒数转时间格式(时间: Long): String {
    val 总秒数 = 时间 / 1000
    val h = 总秒数 / 3600  // 小时
    val m = (总秒数 % 3600) / 60  // 分钟
    val s = 总秒数 % 60  // 秒
    return if (h > 0) String.format("%d:%02d:%02d", h, m, s)
    else        String.format("%02d:%02d", m, s)
}