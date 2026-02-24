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
import android.view.Window
import android.view.WindowManager
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import 安卓x.核心.视图.窗口兼容
import 自定义.系统类.是否为手机
import 自定义.系统类.是否处于横屏
import 自定义.系统类.是否是深色模式


object 状态栏沉浸式类 {

    fun 状态栏沉浸设置(上下文: Activity) {
        val 沉浸式 = 状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().状态栏字体图标自动深色模式(!上下文.是否是深色模式)
            .导航栏图标自动深色模式(!上下文.是否是深色模式)
        if (上下文.是否为手机()) {
            显示状态栏导航栏(上下文)
//            if (上下文.是否处于竖屏()) {
//            } else {
//                隐藏状态栏导航栏(上下文, 根视图)
//            }
        }
        沉浸式.刷新()
    }

    //=============================================================
    //=============================================================

    fun 隐藏状态栏导航栏(上下文 : Activity) {
        隐藏状态栏导航栏(上下文, 上下文.window)
    }

    fun 隐藏状态栏导航栏(上下文 : Activity, 窗口 : Window) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false)

        窗口.statusBarColor = Color.TRANSPARENT // 状态栏透明
        窗口.navigationBarColor = Color.TRANSPARENT // 导航栏透明

        // 获取控制器对象
        WindowCompat.getInsetsController(窗口, 窗口.decorView).apply {
            this.isAppearanceLightStatusBars = !上下文.是否是深色模式 // 状态栏字体颜色
            this.isAppearanceLightNavigationBars = !上下文.是否是深色模式 // 导航栏字体颜色
            this.hide(WindowInsetsCompat.Type.statusBars()) // 隐藏状态栏
            this.hide(WindowInsetsCompat.Type.navigationBars()) // 隐藏导航栏
            this.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 滑动呼出后再次自动隐藏
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (上下文.是否处于横屏()){
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                    insets
                }
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
                    view.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom)
                    insets
                }
            }
        }else{
            窗口.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//            窗口.decorView.systemUiVisibility = (
//                // 沉浸粘性模式：用户滑动后自动重新隐藏
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        // 隐藏导航栏
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        // 隐藏状态栏
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        // 布局延伸到系统栏下方（防止布局跳动）
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )

            ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                insets
            }
        }
    }

    //=============================================================

    fun 隐藏状态栏(上下文 : Activity) {
        隐藏状态栏(上下文, 上下文.window)
    }

    fun 隐藏状态栏(上下文 : Activity, 窗口 : Window) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false)

        窗口.statusBarColor = Color.TRANSPARENT // 状态栏透明
        窗口.navigationBarColor = Color.TRANSPARENT // 导航栏透明

        // 获取控制器对象
        窗口兼容.取内边距控制器(窗口, 窗口.decorView).apply {
            this.isAppearanceLightStatusBars = !上下文.是否是深色模式 // 状态栏字体颜色
            this.isAppearanceLightNavigationBars = !上下文.是否是深色模式 // 导航栏字体颜色
            this.hide(WindowInsetsCompat.Type.statusBars()) // 隐藏状态栏
            this.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 滑动呼出后再次自动隐藏
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (上下文.是否处于横屏()){
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                    insets
                }
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
                    view.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom)
                    insets
                }
            }
        }else{
//            窗口.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//            窗口.decorView.systemUiVisibility = (
//                // 沉浸粘性模式：用户滑动后自动重新隐藏
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        // 隐藏状态栏
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        // 布局延伸到系统栏下方（防止布局跳动）
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )
            窗口.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

            ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                insets
            }
        }
    }

    //=============================================================

    fun 隐藏导航栏(上下文 : Activity) {
        隐藏导航栏(上下文, 上下文.window)
    }

    fun 隐藏导航栏(上下文 : Activity, 窗口 : Window) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false)

        窗口.statusBarColor = Color.TRANSPARENT // 状态栏透明
        窗口.navigationBarColor = Color.TRANSPARENT // 导航栏透明

        // 获取控制器对象
        WindowCompat.getInsetsController(窗口, 窗口.decorView).apply {
            this.isAppearanceLightNavigationBars = !上下文.是否是深色模式 // 导航栏字体颜色
            this.hide(WindowInsetsCompat.Type.navigationBars()) // 隐藏导航栏
            this.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE // 滑动呼出后再次自动隐藏
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (上下文.是否处于横屏()){
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                    insets
                }
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
                    view.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom)
                    insets
                }
            }
        }else{
            窗口.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//            窗口.decorView.systemUiVisibility = (
//                // 沉浸粘性模式：用户滑动后自动重新隐藏
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        // 隐藏导航栏
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        // 隐藏状态栏
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        // 布局延伸到系统栏下方（防止布局跳动）
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )

            ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                insets
            }
        }
    }

    //=============================================================
    //=============================================================

    fun 显示状态栏导航栏(上下文: Activity) {
        显示状态栏导航栏(上下文, 上下文.window)
    }

    fun 显示状态栏导航栏(上下文: Activity, 窗口: Window) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false)

        窗口.statusBarColor = Color.TRANSPARENT // 状态栏透明
        窗口.navigationBarColor = Color.TRANSPARENT // 导航栏透明

        // 获取控制器对象
        WindowCompat.getInsetsController(窗口, 窗口.decorView).apply {
            this.isAppearanceLightStatusBars = !上下文.是否是深色模式 // 状态栏字体颜色
            this.isAppearanceLightNavigationBars = !上下文.是否是深色模式 // 导航栏字体颜色
            this.show(WindowInsetsCompat.Type.statusBars()) // 显示状态栏
            this.show(WindowInsetsCompat.Type.navigationBars()) // 显示导航栏
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (上下文.是否处于横屏()) {
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    view.setPadding(systemBar.left, 状态栏沉浸式.取状态栏高度(上下文), systemBar.right, systemBar.bottom)
                    insets
                }
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
                    view.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom)
                    insets
                }
            }
        }else{
            ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                insets
            }
        }

    }

    //=============================================================

    fun 显示状态栏(上下文: Activity) {
        显示状态栏(上下文, 上下文.window)
    }

    fun 显示状态栏(上下文: Activity, 窗口: Window) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false)

        窗口.statusBarColor = Color.TRANSPARENT // 状态栏透明
        窗口.navigationBarColor = Color.TRANSPARENT // 导航栏透明

        // 获取控制器对象
        WindowCompat.getInsetsController(窗口, 窗口.decorView).apply {
            this.isAppearanceLightStatusBars = !上下文.是否是深色模式 // 状态栏字体颜色
            this.isAppearanceLightNavigationBars = !上下文.是否是深色模式 // 导航栏字体颜色
            this.show(WindowInsetsCompat.Type.statusBars()) // 显示状态栏
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (上下文.是否处于横屏()) {
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    view.setPadding(systemBar.left, 状态栏沉浸式.取状态栏高度(上下文), systemBar.right, systemBar.bottom)
                    insets
                }
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
                    view.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom)
                    insets
                }
            }
        }else{
            ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                insets
            }
        }

    }

    //=============================================================

    fun 显示导航栏(上下文: Activity) {
        显示导航栏(上下文, 上下文.window)
    }

    fun 显示导航栏(上下文: Activity, 窗口: Window) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false)

        窗口.statusBarColor = Color.TRANSPARENT // 状态栏透明
        窗口.navigationBarColor = Color.TRANSPARENT // 导航栏透明

        // 获取控制器对象
        WindowCompat.getInsetsController(窗口, 窗口.decorView).apply {
            this.isAppearanceLightStatusBars = !上下文.是否是深色模式 // 状态栏字体颜色
            this.isAppearanceLightNavigationBars = !上下文.是否是深色模式 // 导航栏字体颜色
            this.show(WindowInsetsCompat.Type.navigationBars()) // 显示导航栏
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (上下文.是否处于横屏()) {
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    view.setPadding(systemBar.left, 状态栏沉浸式.取状态栏高度(上下文), systemBar.right, systemBar.bottom)
                    insets
                }
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                    val displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout())
                    view.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom)
                    insets
                }
            }
        }else{
            ViewCompat.setOnApplyWindowInsetsListener(窗口.decorView) { view, insets ->
                val systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom)
                insets
            }
        }

    }

    //=============================================================
    //=============================================================

    fun 置状态栏颜色(上下文: Activity, 颜色: Int) {
        上下文.window.statusBarColor = 颜色
    }

    //=============================================================

    fun 置状态栏字体颜色为深色模式(上下文: Activity, 值: Boolean) {
        val decorView = 上下文.window.decorView
        if (值) {
            decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
    }

    //=============================================================

    fun 置导航栏颜色(上下文: Activity, 颜色: Int) {
        上下文.window.navigationBarColor = 颜色
    }

    //=============================================================

    fun 置导航栏图标颜色为深色模式(上下文: Activity, 值: Boolean) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val decorView = 上下文.window.decorView
            if (值) {
                decorView.systemUiVisibility = decorView.systemUiVisibility or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            } else {
                decorView.systemUiVisibility = decorView.systemUiVisibility and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            }
        }
    }

    //=============================================================

    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    fun 用资源文件获取导航栏高度(上下文: Context): Int {
        val hasMenuKey = ViewConfiguration.get(上下文).hasPermanentMenuKey()
        if (!hasMenuKey) {
            val resources = 上下文.resources
            val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId)
            }
        }
        return 0
    }

    //=============================================================

    fun 用DisplayCutout获取导航栏高度(上下文: Activity): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val windowInsets = 上下文.window.decorView.getRootWindowInsets()
            if (windowInsets != null) {
                return windowInsets.systemWindowInsetBottom
            }
        }
        return 0
    }

    //=============================================================

    fun 获取导航栏高度(根视图: View): Int {
        var 导航栏高度 = 0
        根视图.getViewTreeObserver().addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val rect = Rect()
                    根视图.getWindowVisibleDisplayFrame(rect)
                    val screenHeight = 根视图.getRootView().height
                    val navigationBarHeight = screenHeight - rect.bottom
                    // 移除监听器
                    根视图.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                    // 使用 navigationBarHeight
                    导航栏高度 = navigationBarHeight
                }
            })
        return 导航栏高度
    }

    //=============================================================

    fun 获取导航栏高度(上下文: Context): Int {
        //PreferenceManager.getDefaultSharedPreferences(context);
        val prefs = 上下文.getSharedPreferences("preferences", Context.MODE_PRIVATE)
        var height = prefs.getInt("navigation_bar_height", -1)
        if (height == -1) {
            height = 用资源文件获取导航栏高度(上下文)
            prefs.edit { putInt("navigation_bar_height", height) }
        }
        return height
    }

}
