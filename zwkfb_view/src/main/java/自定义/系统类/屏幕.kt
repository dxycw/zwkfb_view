@file:Suppress("DEPRECATION")

package 自定义.系统类

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


//=======================================================================

fun Context.sp转dp(sp: Int): Int {
    val 参数 = resources.displayMetrics.density
    return (sp / 参数).toInt()
}

fun Context.sp转px(sp: Int): Int {
    val 参数 = resources.displayMetrics.scaledDensity
    return (sp * 参数).toInt()
}

fun Context.dp转sp(dp: Int): Int {
    val scaledDensity = resources.displayMetrics.scaledDensity
    return (dp * scaledDensity / resources.displayMetrics.density).toInt()
}

fun Context.dp转px( dp: Int): Int {
    val density = resources.displayMetrics.density
    return (dp * density).toInt()
}


fun Context.px转dp(px: Int): Int {
    val 参数 = resources.displayMetrics.density
    return (px / 参数).toInt()
}

fun Context.px转sp(px: Int): Int {
    val 参数 = resources.displayMetrics.scaledDensity
    return (px / 参数).toInt()
}

//=======================================================================

fun Context.获取用户屏幕宽度(): Int {
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics() // 初始化DisplayMetrics对象
    windowManager.defaultDisplay.getMetrics(displayMetrics) // 获取显示信息
    return displayMetrics.widthPixels
}

fun Context.获取用户屏幕高度(): Int {
    // 获取WindowManager服务
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val displayMetrics = DisplayMetrics() // 初始化DisplayMetrics对象
    windowManager.defaultDisplay.getMetrics(displayMetrics) // 获取显示信息
    return displayMetrics.heightPixels
}

/**
 * 设置控件四个方向相同的间距
 * @param  控件  要修改的控件
 * @param  间距  控件的上、下、左、右间距
 */
fun View.置控件间距(间距: Int) {
    var params = layoutParams as ViewGroup.MarginLayoutParams?
    if (params == null) {
        params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    if (间距 != -1) {
        params.topMargin = 间距
        params.bottomMargin = 间距
        params.leftMargin = 间距
        params.rightMargin = 间距
    }
    setLayoutParams(params)
}

/**
 * 设置控件四个方向的间距
 * @param  控件  要修改的控件
 * @param  上  控件和边的上间距
 * @param  下  控件和边的下间距
 * @param  左  控件和边的左间距
 * @param  右  控件和边的右间距
 */
fun View.置控件间距(上: Int, 下: Int, 左: Int, 右: Int) {
    var params = layoutParams as ViewGroup.MarginLayoutParams?
    if (params == null) {
        params = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }
    if (上 != -1) {
        params.topMargin = 上
    }
    if (下 != -1) {
        params.bottomMargin = 下
    }
    if (左 != -1) {
        params.leftMargin = 左
    }
    if (右 != -1) {
        params.rightMargin = 右
    }
    setLayoutParams(params)
}

//=======================================================================


