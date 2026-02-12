@file:Suppress("DEPRECATION")

package 自定义.状态栏类

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewConfiguration
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.content.edit
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import 自定义.系统类.是否为手机
import 自定义.系统类.是否处于横屏
import 自定义.系统类.是否是深色模式


//=====================================================================

fun Activity.状态栏沉浸设置() {
    状态栏沉浸式.初始化沉浸式(this).状态栏导航栏透明().状态栏字体图标自动深色模式(!是否是深色模式)
        .导航栏图标自动深色模式(!是否是深色模式).刷新()
    if (是否为手机()) {
        if (是否处于横屏()) { 隐藏状态栏导航栏() }
        else { 显示状态栏导航栏() }
    }
}

//=====================================================================

fun Activity.隐藏状态栏和导航栏() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // 30+ 新 API
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.insetsController?.apply {
            hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        // 21~29 旧 API
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_FULLSCREEN               // 隐藏状态栏
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION       // 隐藏导航栏
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY      // 滑动呼出后再次自动隐藏
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN     // 内容扩展到状态栏区域
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                )
    }
}


//=====================================================================

fun Activity.隐藏状态栏导航栏() {
    WindowCompat.setDecorFitsSystemWindows(window, false) // 设置内容
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
    windowInsetsController.hide(WindowInsetsCompat.Type.systemBars()) // 隐藏状态栏和导航栏
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 设置状态栏和导航栏的显示方式
    window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

fun Activity.显示状态栏导航栏() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
    windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    window.statusBarColor = Color.TRANSPARENT
    window.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
}

//=====================================================================

fun Activity.隐藏状态栏() {
    // 隐藏状态栏
    val window = this.window // 获取窗口对象
    WindowCompat.setDecorFitsSystemWindows(window, false) // 设置内容
    val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
    windowInsetsController.hide(WindowInsetsCompat.Type.statusBars()) // 隐藏状态栏
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}

fun Activity.显示状态栏() {
    // 显示状态栏
    val window = this.window // 获取窗口对象
    WindowCompat.setDecorFitsSystemWindows(window, true) // 设置内容
    val windowInsetsController =
        WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
    windowInsetsController.show(WindowInsetsCompat.Type.statusBars())
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}

//=====================================================================

fun Activity.隐藏导航栏() {
    // 隐藏导航栏
    val window = this.window // 获取窗口对象
    WindowCompat.setDecorFitsSystemWindows(window, false) // 设置内容
    val windowInsetsController =
        WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
    windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars()) // 隐藏导航栏
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 设置状态栏和导航栏的显示方式
}

fun Activity.显示导航栏() {
    // 显示导航栏
    val window = this.window // 获取窗口对象
    WindowCompat.setDecorFitsSystemWindows(window, true) // 设置内容
    val windowInsetsController =
        WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
    windowInsetsController.show(WindowInsetsCompat.Type.navigationBars()) // 显示导航栏
    windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
}

//=====================================================================

fun Activity.状态栏颜色(颜色: Int) {
    this.window.statusBarColor = 颜色
}

fun Activity.导航栏颜色(颜色: Int) {
    this.window.navigationBarColor = 颜色
}

//=====================================================================

fun Activity.置状态栏字体颜色为深色模式(值: Boolean) {
    val decorView = this.window.decorView
    if (值) {
        decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    } else {
        decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
    }
}

fun Activity.置导航栏图标颜色为深色模式(值: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val decorView = this.window.decorView
        if (值) {
            decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
    }
}

//=====================================================================

fun Activity.用DisplayCutout获取导航栏高度(): Int {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val windowInsets = this.window.decorView.getRootWindowInsets()
        if (windowInsets != null) {
            return windowInsets.systemWindowInsetBottom
        }
    }
    return 0
}

//=====================================================================

@SuppressLint("DiscouragedApi", "InternalInsetResource")
fun Activity.用资源文件获取导航栏高度(): Int {
    val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
    if (!hasMenuKey) {
        val resources = this.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId)
        }
    }
    return 0
}

//=====================================================================

fun Activity.获取导航栏高度(): Int {
    val prefs = this.getSharedPreferences("preferences", Context.MODE_PRIVATE)
    var height = prefs.getInt("navigation_bar_height", -1)
    if (height == -1) {
        height = calculateNavigationBarHeight()
        prefs.edit { putInt("navigation_bar_height", height) }
    }
    return height
}

//=====================================================================

@SuppressLint("DiscouragedApi", "InternalInsetResource")
fun Activity.calculateNavigationBarHeight(): Int {
    val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
    if (!hasMenuKey) {
        val resources = this.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            return resources.getDimensionPixelSize(resourceId)
        }
    }
    return 0
}

//=====================================================================