package 自定义.颜色类

import android.annotation.SuppressLint
import android.graphics.Color

object 颜色类 {
    var 白色: Int = Color.WHITE
    var 黑色: Int = Color.BLACK
    var 红色: Int = Color.RED
    var 洋红色: Int = Color.MAGENTA
    var 绿色: Int = Color.GREEN
    var 蓝色: Int = Color.BLUE
    var 橙色: String = "#FFA500"
    var 灰色: Int = Color.GRAY
    var 深灰色: Int = Color.DKGRAY
    var 粉色: Int = Color.LTGRAY
    var 黄色: Int = Color.YELLOW
    var 青色: Int = Color.CYAN
    var 透明: Int = Color.TRANSPARENT
}

fun 颜色类.置RGB颜色(红: Int,绿: Int,蓝: Int): Int =
    Color.rgb(红, 绿, 蓝)

fun 颜色类.置ARGB颜色(透明度: Int, 红: Int, 绿: Int, 蓝: Int): Int =
    Color.argb(透明度, 红, 绿, 蓝)

//==================以下函数android 8.0及以上版本===========================================

@SuppressLint("NewApi")
fun 颜色类.置RGB颜色(红: Float, 绿: Float, 蓝: Float): Int =
    Color.rgb(红, 绿, 蓝)

@SuppressLint("NewApi")
fun 颜色类.置ARGB颜色(透明度: Float, 红: Float, 绿: Float, 蓝: Float): Int =
    Color.argb(透明度, 红, 绿, 蓝)