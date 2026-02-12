@file:Suppress("DEPRECATION")

package 自定义.状态栏类

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.os.Build
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.content.edit
import 自定义.系统类.是否为手机
import 自定义.系统类.是否处于横屏
import 自定义.系统类.是否处于竖屏
import 自定义.系统类.是否是深色模式


object 状态栏沉浸式类 {

    fun 状态栏沉浸设置(上下文: ComponentActivity) {
        状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().状态栏字体图标自动深色模式(!上下文.是否是深色模式)
            .导航栏图标自动深色模式(!上下文.是否是深色模式).刷新()
        if (上下文.是否为手机()) {
            if (上下文.是否处于竖屏()) {
                状态栏沉浸式类.显示状态栏导航栏(上下文)
            } else {
                状态栏沉浸式类.隐藏状态栏导航栏(上下文)
            }
        }
    }

    fun 沉浸式状态栏(上下文: Activity?) {
        (上下文 as ComponentActivity).enableEdgeToEdge()
    }

    fun 沉浸式状态栏(上下文: ComponentActivity) {
        上下文.enableEdgeToEdge()
    }

    fun 修复状态栏导航栏高度(视图: View) {
        ViewCompat.setOnApplyWindowInsetsListener(视图,
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            })
    }

    fun 修复状态栏高度(视图: View) {
        ViewCompat.setOnApplyWindowInsetsListener(视图,
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
                insets
            })
    }

    fun 修复导航栏高度(视图: View) {
        ViewCompat.setOnApplyWindowInsetsListener(视图,
            OnApplyWindowInsetsListener { v: View?, insets: WindowInsetsCompat? ->
                val systemBars = insets!!.getInsets(WindowInsetsCompat.Type.systemBars())
                v!!.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
                insets
            })
    }

    @JvmStatic
    fun 隐藏状态栏导航栏(上下文: ComponentActivity) {
        // 隐藏状态栏和导航栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, false) // 设置内容
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars()) // 隐藏状态栏和导航栏
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 设置状态栏和导航栏的显示方式
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            // 兼容处理，例如使用老的API或者第三方库
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    fun 隐藏状态栏导航栏(上下文: Activity) {
        // 隐藏状态栏和导航栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, false) // 设置内容
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
        windowInsetsController.hide(WindowInsetsCompat.Type.systemBars()) // 隐藏状态栏和导航栏
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 设置状态栏和导航栏的显示方式
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            // 兼容处理，例如使用老的API或者第三方库
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN)
            window.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }
    }

    @JvmStatic
    fun 显示状态栏导航栏(上下文: ComponentActivity) {
        // 显示状态栏和导航栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // 获取控制器对象
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // 显示状态栏和导航栏
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        // 设置状态栏和导航栏的显示方式
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // 设置状态栏颜色（API 21及以上）
        window.statusBarColor = Color.TRANSPARENT
    }

    fun 显示状态栏导航栏(上下文: Activity) {
        // 显示状态栏和导航栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, false)
        // 获取控制器对象
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // 显示状态栏和导航栏
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        // 设置状态栏和导航栏的显示方式
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        // 设置状态栏颜色（API 21及以上）
        window.statusBarColor = Color.TRANSPARENT
    }

    fun 隐藏状态栏(上下文: ComponentActivity) {
        // 隐藏状态栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, false) // 设置内容
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
        windowInsetsController.hide(WindowInsetsCompat.Type.statusBars()) // 隐藏状态栏
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    fun 显示状态栏(上下文: ComponentActivity) {
        // 显示状态栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, true) // 设置内容
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
        windowInsetsController.show(WindowInsetsCompat.Type.statusBars())
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    fun 隐藏导航栏(上下文: ComponentActivity) {
        // 隐藏导航栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, false) // 设置内容
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars()) // 隐藏导航栏
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 设置状态栏和导航栏的显示方式
    }

    fun 显示导航栏(上下文: ComponentActivity) {
        // 显示导航栏
        val window = 上下文.window // 获取窗口对象
        WindowCompat.setDecorFitsSystemWindows(window, true) // 设置内容
        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView) // 获取控制器对象
        windowInsetsController.show(WindowInsetsCompat.Type.navigationBars()) // 显示导航栏
        windowInsetsController.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }

    fun 置状态栏颜色(上下文: ComponentActivity, 颜色: Int) {
        上下文.window.statusBarColor = 颜色
    }

    fun 置状态栏字体颜色为深色模式(上下文: ComponentActivity, 值: Boolean) {
        val decorView = 上下文.window.decorView
        if (值) {
            decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

    fun 置导航栏颜色(上下文: ComponentActivity, 颜色: Int) {
        上下文.window.navigationBarColor = 颜色
    }

    fun 置导航栏图标颜色为深色模式(上下文: ComponentActivity, 值: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val decorView = 上下文.window.decorView
            if (值) {
                decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }
    }

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    fun 用资源文件获取导航栏高度(context: Context): Int {
        val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
        if (!hasMenuKey) {
            val resources = context.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId)
            }
        }
        return 0
    }


    fun 用DisplayCutout获取导航栏高度(activity: Activity): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val windowInsets = activity.window.decorView.getRootWindowInsets()
            if (windowInsets != null) {
                return windowInsets.systemWindowInsetBottom
            }
        }
        return 0
    }


    var 导航栏高度: Int = 0
    fun 获取导航栏高度(rootView: View): Int {
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val rect = Rect()
                    rootView.getWindowVisibleDisplayFrame(rect)
                    val screenHeight = rootView.getRootView().height
                    val navigationBarHeight = screenHeight - rect.bottom
                    // 移除监听器
                    rootView.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                    // 使用 navigationBarHeight
                    导航栏高度 = navigationBarHeight
                }
            })
        return 导航栏高度
    }

    fun 获取导航栏高度(context: Context): Int {
        //PreferenceManager.getDefaultSharedPreferences(context);
        val prefs = context.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        var height = prefs.getInt("navigation_bar_height", -1)
        if (height == -1) {
            height = calculateNavigationBarHeight(context)
            prefs.edit { putInt("navigation_bar_height", height) }
        }
        return height
    }

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    internal fun calculateNavigationBarHeight(context: Context): Int {
        val hasMenuKey = ViewConfiguration.get(context).hasPermanentMenuKey()
        if (!hasMenuKey) {
            val resources = context.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId)
            }
        }
        return 0
    }
}
