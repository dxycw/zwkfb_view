package 自定义.状态栏类

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.view.ViewCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.gyf.immersionbar.BarHide
import com.gyf.immersionbar.BarParams
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.NavigationBarType
import com.gyf.immersionbar.NotchCallback
import com.gyf.immersionbar.OnBarListener
import com.gyf.immersionbar.OnKeyboardListener
import com.gyf.immersionbar.OnNavigationBarListener
import 安卓.操作系统.构建
import 自定义.系统类.是否是深色模式

class 状态栏沉浸式 {

    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var 沉浸式配置: ImmersionBar
        fun 初始化沉浸式(上下文: Activity): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: Activity, 是否仅: Boolean): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文, 是否仅)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: Fragment): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: Fragment, 是否仅: Boolean): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文, 是否仅)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: android.app.Fragment): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: android.app.Fragment, 是否仅: Boolean): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文, 是否仅)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: DialogFragment): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: DialogFragment, 是否仅: Boolean): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文, 是否仅)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: android.app.DialogFragment): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: android.app.DialogFragment, 是否仅: Boolean): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文, 是否仅)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: Activity, 对话框上下文: Dialog): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文, 对话框上下文)
            return 状态栏沉浸式()
        }

        fun 初始化沉浸式(上下文: Activity, 对话框上下文: Dialog, 是否仅: Boolean): 状态栏沉浸式 {
            沉浸式配置 = ImmersionBar.with(上下文, 对话框上下文, 是否仅)
            return 状态栏沉浸式()
        }

        // ================================================================================

        //以下方法可以直接调用
        fun 销毁(上下文: Fragment) {
            ImmersionBar.destroy(上下文)
        }

        fun 销毁(上下文: Activity, 对话框上下文: Dialog) {
            ImmersionBar.destroy(上下文, 对话框上下文)
        }

        fun 销毁(上下文: Activity, 对话框上下文: Dialog, 是否仅: Boolean) {
            ImmersionBar.destroy(上下文, 对话框上下文, 是否仅)
        }

        // ================================================================================

        fun 隐藏状态栏(窗口: Window) {
            ImmersionBar.hideStatusBar(窗口)
        }

        fun 显示状态栏(窗口: Window) {
            ImmersionBar.showStatusBar(窗口)
        }

        // ================================================================================

        fun 手机是否支持状态栏字体变色(): Boolean {
            return ImmersionBar.isSupportStatusBarDarkFont()
        }

        fun 手机是否支持导航栏图标变色(): Boolean {
            return ImmersionBar.isSupportNavigationIconDark()
        }

        // ================================================================================

        fun 是否是刘海屏(上下文: Activity): Boolean {
            return ImmersionBar.hasNotchScreen(上下文)
        }

        fun 是否是刘海屏(上下文: Fragment): Boolean {
            return ImmersionBar.hasNotchScreen(上下文)
        }

        fun 是否是刘海屏(视图: View): Boolean {
            return ImmersionBar.hasNotchScreen(视图)
        }

        // ================================================================================

        fun 刘海屏高度(上下文: Activity): Int {
            return ImmersionBar.getNotchHeight(上下文)
        }

        fun 刘海屏高度(上下文: Fragment): Int {
            return ImmersionBar.getNotchHeight(上下文)
        }

        fun 刘海屏高度(上下文: Activity, 回调: NotchCallback?) {
            ImmersionBar.getNotchHeight(上下文, 回调)
        }

        fun 刘海屏高度(上下文: Fragment, 回调: NotchCallback?) {
            ImmersionBar.getNotchHeight(上下文, 回调)
        }

        // ================================================================================

        fun 是否是手势(上下文: Context?): Boolean {
            return ImmersionBar.isGesture(上下文)
        }

        fun 是否是手势(上下文: Fragment?): Boolean {
            return ImmersionBar.isGesture(上下文)
        }

        fun 是否是手势(上下文: android.app.Fragment?): Boolean {
            return ImmersionBar.isGesture(上下文)
        }

        // ================================================================================

        fun 是否有导航栏(上下文: Activity): Boolean {
            return ImmersionBar.hasNavigationBar(上下文)
        }

        fun 是否有导航栏(上下文: Context): Boolean {
            return ImmersionBar.hasNavigationBar(上下文)
        }

        fun 是否有导航栏(上下文: Fragment): Boolean {
            return ImmersionBar.hasNavigationBar(上下文)
        }

        // ================================================================================

        fun 导航栏是否在底部(上下文: Activity): Boolean {
            return ImmersionBar.isNavigationAtBottom(上下文)
        }

        fun 导航栏是否在底部(上下文: Fragment): Boolean {
            return ImmersionBar.isNavigationAtBottom(上下文)
        }

        // ================================================================================

        fun 获取导航栏高度(上下文: Activity): Int {
            return ImmersionBar.getNavigationBarHeight(上下文)
        }

        fun 获取导航栏高度(上下文: Context): Int {
            return ImmersionBar.getNavigationBarHeight(上下文)
        }

        fun 获取导航栏高度(上下文: Fragment): Int {
            return ImmersionBar.getNavigationBarHeight(上下文)
        }

        // ================================================================================

        fun 获取导航栏宽度(上下文: Activity): Int {
            return ImmersionBar.getNavigationBarWidth(上下文)
        }

        fun 获取导航栏宽度(上下文: Context): Int {
            return ImmersionBar.getNavigationBarWidth(上下文)
        }

        fun 获取导航栏宽度(上下文: Fragment): Int {
            return ImmersionBar.getNavigationBarWidth(上下文)
        }

        // ================================================================================

        fun 获取状态栏高度(上下文: Activity): Int {
            return ImmersionBar.getStatusBarHeight(上下文)
        }

        fun 获取状态栏高度(上下文: Context): Int {
            return ImmersionBar.getStatusBarHeight(上下文)
        }

        fun 获取状态栏高度(上下文: Fragment): Int {
            return ImmersionBar.getStatusBarHeight(上下文)
        }

        // ================================================================================

        fun 获取ActionBar高度(上下文: Activity): Int {
            return ImmersionBar.getActionBarHeight(上下文)
        }

        fun 获取ActionBar高度(上下文: Fragment): Int {
            return ImmersionBar.getActionBarHeight(上下文)
        }

        // ================================================================================

        fun 是否使用fitsSystemWindows(视图: View?): Boolean {
            return ImmersionBar.checkFitsSystemWindows(视图)
        }

        // ================================================================================

        fun 适配系统窗口(上下文: Activity?) {
            ImmersionBar.setFitsSystemWindows(上下文)
        }

        fun 适配系统窗口(上下文: Activity?, 适配: Boolean) {
            ImmersionBar.setFitsSystemWindows(上下文, 适配)
        }

        fun 适配系统窗口(上下文: Fragment?) {
            ImmersionBar.setFitsSystemWindows(上下文)
        }

        fun 适配系统窗口(上下文: Fragment?, 适配: Boolean) {
            ImmersionBar.setFitsSystemWindows(上下文, 适配)
        }

        // ================================================================================

        fun 标题栏位置添加视图高度(上下文: Activity, vararg 视图: View?) {
            ImmersionBar.setStatusBarView(上下文, *视图)
        }

        fun 标题栏位置添加视图高度(上下文: Activity?, 高度: Int, vararg 视图: View?) {
            ImmersionBar.setStatusBarView(上下文, 高度, *视图)
        }

        fun 标题栏位置添加视图高度(上下文: Fragment?, vararg 视图: View?) {
            ImmersionBar.setStatusBarView(上下文, *视图)
        }

        fun 标题栏位置添加视图高度(上下文: Fragment?, 高度: Int, vararg 视图: View?) {
            ImmersionBar.setStatusBarView(上下文, 高度, *视图)
        }

        // ================================================================================

        fun 标题栏位置添加状态栏外高度(上下文: Activity, vararg 视图: View?) {
            ImmersionBar.setTitleBarMarginTop(上下文, *视图)
        }

        fun 标题栏位置添加状态栏外高度(上下文: Activity?, 高度: Int, vararg 视图: View?) {
            ImmersionBar.setTitleBarMarginTop(上下文, 高度, *视图)
        }

        fun 标题栏位置添加状态栏外高度(上下文: Fragment?, vararg 视图: View?) {
            ImmersionBar.setTitleBarMarginTop(上下文, *视图)
        }

        fun 标题栏位置添加状态栏外高度(上下文: Fragment?, 高度: Int, vararg 视图: View?) {
            ImmersionBar.setTitleBarMarginTop(上下文, 高度, *视图)
        }

        // ================================================================================

        fun 标题栏位置添加状态栏内高度(上下文: Activity, vararg 视图: View?) {
            ImmersionBar.setTitleBar(上下文, *视图)
        }

        fun 标题栏位置添加状态栏内高度(上下文: Activity?, 高度: Int, vararg 视图: View?) {
            ImmersionBar.setTitleBar(上下文, 高度, *视图)
        }

        fun 标题栏位置添加状态栏内高度(上下文: Fragment?, vararg 视图: View?) {
            ImmersionBar.setTitleBar(上下文, *视图)
        }

        fun 标题栏位置添加状态栏内高度(上下文: Fragment?, 高度: Int, vararg 视图: View?) {
            ImmersionBar.setTitleBar(上下文, 高度, *视图)
        }

        //======================================================================

        @SuppressLint("StaticFieldLeak")
        private lateinit var 根视图: View

        fun 是否隐藏状态栏导航栏(上下文: Activity, 是否隐藏: Boolean = false, 自动深色: Boolean = false, 深色颜色: Boolean = false) {
            // 获取根视图
            根视图 = 上下文.findViewById(android.R.id.content)
            val 沉浸设置 = 状态栏沉浸式.初始化沉浸式(上下文)
            if (是否隐藏) {
                沉浸设置.隐藏状态栏导航栏()
                沉浸设置.是否全屏显示(true)
                沉浸设置.状态栏导航栏透明()
                沉浸设置.状态栏导航栏自动深色模式(自动深色)
                沉浸设置.刷新()

                if (状态栏沉浸式.是否是手势(上下文)) { //有
                    //隐藏状态栏和导航栏
                    上下文.window.decorView.setSystemUiVisibility(
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE // 让内容在状态栏和导航栏之间留白
                                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // 让内容显示在状态栏和导航栏之间
                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 让内容显示在状态栏和导航栏后面
                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
                                //| View.SYSTEM_UI_FLAG_FULLSCREEN // 隐藏状态栏
                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                    ) // 保持沉浸模式，即使用户交互也不会退出
                }
                上下文.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                // 设置 OnApplyWindowInsetsListener
                根视图.setOnApplyWindowInsetsListener { v, insets ->
                    // 处理底部导航栏的内边距
                    v.setPadding(0, 0, 0, 0)
                    insets.consumeSystemWindowInsets()
                }
            } else {
                沉浸设置.显示状态栏导航栏()
                if (状态栏沉浸式.是否是手势(上下文)) { //有
                    if (自动深色) {
                        沉浸设置.状态栏字体图标自动深色模式(!上下文.是否是深色模式)
                        if (构建.制造商() == "Xiaomi") {
                            沉浸设置.状态栏导航栏透明()
                            沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式)
                        } else {
                            if (上下文.是否是深色模式) {
                                沉浸设置.导航栏颜色("#000000")
                            } else {
                                沉浸设置.导航栏颜色("#ffffff")
                            }
                        }
                    } else {
                        沉浸设置.状态栏字体图标自动深色模式(深色颜色)
                        if (深色颜色) {
                            沉浸设置.导航栏颜色(Color.WHITE)
                        } else {
                            沉浸设置.导航栏颜色(Color.BLACK)
                        }
                    }
                } else { //没有
                    if (自动深色) {
                        沉浸设置.状态栏字体图标自动深色模式(!上下文.是否是深色模式)
                        沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式)
                    } else {
                        沉浸设置.状态栏字体图标自动深色模式(深色颜色)
                        沉浸设置.导航栏图标自动深色模式(深色颜色)
                    }
                }
                沉浸设置.刷新()

                // 设置 OnApplyWindowInsetsListener
                根视图.setOnApplyWindowInsetsListener { v, insets ->
                    // 处理底部导航栏的内边距
                    v.setPadding(0, insets.systemWindowInsetTop, 0, insets.systemWindowInsetBottom)
                    insets.consumeSystemWindowInsets()
                }
            }
        }

        fun 刷新是否隐藏状态栏导航栏布局() = ViewCompat.requestApplyInsets(根视图)

        fun 修复对话框子窗口导航栏遮盖问题(上下文: Activity, 对话框子窗口上下文: Fragment) {
            val 沉浸设置 = 状态栏沉浸式.初始化沉浸式(对话框子窗口上下文)
            沉浸设置.状态栏导航栏透明()
            if (状态栏沉浸式.是否是手势(上下文)) { //有
                if (构建.制造商() == "Xiaomi") {
                    沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式)
                } else {
                    if (上下文.是否是深色模式) {
                        沉浸设置.导航栏颜色("#000000")
                    } else {
                        沉浸设置.导航栏颜色("#ffffff")
                    }
                }
            } else { //没有
                沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式) // 设置导航栏图标为深色
            }
            沉浸设置.刷新()
        }

        // ================================================================================

        fun 隐藏对话框子窗口状态栏(上下文: Activity, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 显示对话框子窗口状态栏(上下文: Activity, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 隐藏对话框子窗口状态栏(对话框子窗口上下文: Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 显示对话框子窗口状态栏(对话框子窗口上下文: Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 隐藏对话框子窗口状态栏(对话框子窗口上下文: android.app.Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 显示对话框子窗口状态栏(对话框子窗口上下文: android.app.Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 隐藏对话框子窗口状态栏(对话框子窗口上下文: DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 显示对话框子窗口状态栏(对话框子窗口上下文: DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().显示状态栏导航栏()
                .状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 隐藏对话框子窗口状态栏(对话框子窗口上下文: android.app.DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 显示对话框子窗口状态栏(对话框子窗口上下文: android.app.DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(对话框子窗口上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 隐藏对话框子窗口状态栏(上下文: Activity, 对话框上下文: Dialog, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(上下文, 对话框上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

        fun 显示对话框子窗口状态栏(上下文: Activity, 对话框上下文: Dialog, 状态栏字体图标自动深色模式: Boolean = true) {
            状态栏沉浸式.初始化沉浸式(上下文, 对话框上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
        }

    }

    //========================================================================

    fun 刷新() = 沉浸式配置.init()

    fun 恢复默认(): 状态栏沉浸式 {
        沉浸式配置.reset()
        return this
    }

    //========================================================================

    fun 运行() = 沉浸式配置.run()

    fun 状态栏透明(): 状态栏沉浸式 {
        沉浸式配置.transparentStatusBar()
        return this
    }

    fun 状态栏透明度(透明度: Float): 状态栏沉浸式 {
        沉浸式配置.statusBarAlpha(透明度)
        return this
    }
    //========================================================================

    fun 状态栏颜色(颜色: Int): 状态栏沉浸式 {
        沉浸式配置.statusBarColor(颜色)
        return this
    }

    fun 状态栏颜色(颜色: Int, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.statusBarColor(颜色, 透明度)
        return this
    }

    fun 状态栏颜色(颜色: Int, 改变后的颜色: Int, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.statusBarColor(颜色, 改变后的颜色, 透明度)
        return this
    }

    fun 状态栏颜色(颜色: String?): 状态栏沉浸式 {
        沉浸式配置.statusBarColor(颜色)
        return this
    }

    fun 状态栏颜色(颜色: String?, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.statusBarColor(颜色, 透明度)
        return this
    }

    fun 状态栏颜色(颜色: String?, 改变后的颜色: String?, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.statusBarColor(颜色, 改变后的颜色, 透明度)
        return this
    }

    //========================================================================

    fun 状态栏自动深色模式(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.autoStatusBarDarkModeEnable(值)
        return this
    }

    fun 状态栏自动深色模式(值: Boolean, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.autoStatusBarDarkModeEnable(值, 透明度)
        return this
    }

    //========================================================================

    fun 状态栏字体图标自动深色模式(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.statusBarDarkFont(值)
        return this
    }

    fun 状态栏字体图标自动深色模式(值: Boolean, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.statusBarDarkFont(值, 透明度)
        return this
    }

    //========================================================================

    fun FlymeOS系统手机状态栏字体颜色(值: Int): 状态栏沉浸式 {
        沉浸式配置.flymeOSStatusBarFontColor(值)
        return this
    }

    fun FlymeOS系统手机状态栏字体颜色(值: String?): 状态栏沉浸式 {
        沉浸式配置.flymeOSStatusBarFontColor(值)
        return this
    }

    //========================================================================

    fun 导航栏透明(): 状态栏沉浸式 {
        沉浸式配置.transparentNavigationBar()
        return this
    }

    fun 导航栏透明度(透明度: Float): 状态栏沉浸式 {
        沉浸式配置.navigationBarAlpha(透明度)
        return this
    }

    //========================================================================

    fun 导航栏颜色(颜色: Int): 状态栏沉浸式 {
        沉浸式配置.navigationBarColor(颜色)
        return this
    }

    fun 导航栏颜色(颜色: Int, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.navigationBarColor(颜色, 透明度)
        return this
    }

    fun 导航栏颜色(颜色: Int, 改变后的颜色: Int, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.navigationBarColor(颜色, 改变后的颜色, 透明度)
        return this
    }

    fun 导航栏颜色(颜色: String?): 状态栏沉浸式 {
        沉浸式配置.navigationBarColor(颜色)
        return this
    }

    fun 导航栏颜色(颜色: String?, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.navigationBarColor(颜色, 透明度)
        return this
    }

    fun 导航栏颜色(颜色: String?, 改变后的颜色: String?, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.navigationBarColor(颜色, 改变后的颜色, 透明度)
        return this
    }

    //========================================================================

    fun 导航栏自动深色模式(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.autoNavigationBarDarkModeEnable(值)
        return this
    }

    fun 导航栏自动深色模式(值: Boolean, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.autoNavigationBarDarkModeEnable(值, 透明度)
        return this
    }

    //========================================================================

    fun 导航栏图标自动深色模式(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.navigationBarDarkIcon(值)
        return this
    }

    fun 导航栏图标自动深色模式(值: Boolean, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.navigationBarDarkIcon(值, 透明度)
        return this
    }

    //========================================================================

    fun 状态栏导航栏透明(): 状态栏沉浸式 {
        沉浸式配置.transparentBar()
        return this
    }

    fun 状态栏导航栏透明度(透明度: Float): 状态栏沉浸式 {
        沉浸式配置.barAlpha(透明度)
        return this
    }

    //========================================================================

    fun 状态栏导航栏颜色(颜色: Int): 状态栏沉浸式 {
        沉浸式配置.barColor(颜色)
        return this
    }

    fun 状态栏导航栏颜色(颜色: Int, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.barColor(颜色, 透明度)
        return this
    }

    fun 状态栏导航栏颜色(颜色: Int, 改变后的颜色: Int, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.barColor(颜色, 改变后的颜色, 透明度)
        return this
    }

    fun 状态栏导航栏颜色(颜色: String?): 状态栏沉浸式 {
        沉浸式配置.barColor(颜色)
        return this
    }

    fun 状态栏导航栏颜色(颜色: String?, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.barColor(颜色, 透明度)
        return this
    }

    fun 状态栏导航栏颜色(颜色: String?, 改变后的颜色: String?, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.barColor(颜色, 改变后的颜色, 透明度)
        return this
    }

    //========================================================================

    fun 状态栏导航栏自动深色模式(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.autoDarkModeEnable(值)
        return this
    }

    fun 状态栏导航栏自动深色模式(值: Boolean, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.autoDarkModeEnable(值, 透明度)
        return this
    }

    //========================================================================

    fun 显示状态栏导航栏(): 状态栏沉浸式 {
        沉浸式配置.hideBar(BarHide.FLAG_SHOW_BAR) //显示状态栏和导航栏
        return this
    }

    fun 隐藏状态栏导航栏(): 状态栏沉浸式 {
        沉浸式配置.hideBar(BarHide.FLAG_HIDE_BAR) //隐藏状态栏和导航栏
        return this
    }

    //========================================================================

    fun 隐藏状态栏(): 状态栏沉浸式 {
        沉浸式配置.hideBar(BarHide.FLAG_HIDE_STATUS_BAR) //隐藏状态栏
        return this
    }

    fun 隐藏导航栏(): 状态栏沉浸式 {
        沉浸式配置.hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR) //隐藏导航栏
        return this
    }

    //========================================================================

    fun 视图透明度(透明度: Float): 状态栏沉浸式 {
        沉浸式配置.viewAlpha(透明度)
        return this
    }

    fun 状态栏根据透明度最后变换成的颜色(颜色: Int): 状态栏沉浸式 {
        沉浸式配置.statusBarColorTransform(颜色)
        return this
    }

    fun 状态栏根据透明度最后变换成的颜色(颜色: String?): 状态栏沉浸式 {
        沉浸式配置.statusBarColorTransform(颜色)
        return this
    }

    fun 导航栏根据透明度最后变换成的颜色(颜色: Int): 状态栏沉浸式 {
        沉浸式配置.navigationBarColorTransform(颜色)
        return this
    }

    fun 导航栏根据透明度最后变换成的颜色(颜色: String?): 状态栏沉浸式 {
        沉浸式配置.navigationBarColorTransform(颜色)
        return this
    }

    fun 状态栏和导航栏根据透明度最后变换成的颜色(颜色: Int): 状态栏沉浸式 {
        沉浸式配置.barColorTransform(颜色)
        return this
    }

    fun 状态栏和导航栏根据透明度最后变换成的颜色(颜色: String?): 状态栏沉浸式 {
        沉浸式配置.barColorTransform(颜色)
        return this
    }

    fun 添加视图变色(视图: View): 状态栏沉浸式 {
        沉浸式配置.addViewSupportTransformColor(视图)
        return this
    }

    fun 添加视图变色(视图: View, 变换后的颜色: Int): 状态栏沉浸式 {
        沉浸式配置.addViewSupportTransformColor(视图, 变换后的颜色)
        return this
    }

    fun 添加视图变色(视图: View, 变换前的颜色: Int, 变换后的颜色: Int): 状态栏沉浸式 {
        沉浸式配置.addViewSupportTransformColor(视图, 变换前的颜色, 变换后的颜色)
        return this
    }

    fun 状态栏沉浸式添加视图变色(视图: View, 变换后的颜色: String?): 状态栏沉浸式 {
        沉浸式配置.addViewSupportTransformColor(视图, 变换后的颜色)
        return this
    }

    fun 添加视图变色(视图: View, 变换前的颜色: String?, 变换后的颜色: String?): 状态栏沉浸式 {
        沉浸式配置.addViewSupportTransformColor(视图, 变换前的颜色, 变换后的颜色)
        return this
    }

    fun 是否全屏显示(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.fullScreen(值)
        return this
    }

    fun 适配系统窗口(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.fitsSystemWindows(值)
        return this
    }

    fun 适配系统窗口(值: Boolean, 背景色: Int): 状态栏沉浸式 {
        沉浸式配置.fitsSystemWindows(值, 背景色)
        return this
    }

    fun 适配系统窗口(值: Boolean, 背景色: Int, 改变后的背景色: Int, 透明度: Float): 状态栏沉浸式 {
        沉浸式配置.fitsSystemWindows(值, 背景色, 改变后的背景色, 透明度)
        return this
    }

    fun 是否修复状态栏与布局重叠(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.fitsLayoutOverlapEnable(值)
        return this
    }

    fun 状态栏下面的组件(组件: View?): 状态栏沉浸式 {
        沉浸式配置.statusBarView(组件)
        return this
    }

    fun 状态栏下面的组件(组件: Int): 状态栏沉浸式 {
        沉浸式配置.statusBarView(组件)
        return this
    }

    fun 状态栏下面的组件(组件: Int, 视图: View): 状态栏沉浸式 {
        沉浸式配置.statusBarView(组件, 视图)
        return this
    }

    fun 解决状态栏与布局顶部重叠(组件: View?): 状态栏沉浸式 {
        沉浸式配置.titleBar(组件)
        return this
    }

    fun 解决状态栏与布局顶部重叠(组件: View?, 值: Boolean): 状态栏沉浸式 {
        沉浸式配置.titleBar(组件, 值)
        return this
    }

    fun 解决状态栏与布局顶部重叠(组件: Int): 状态栏沉浸式 {
        沉浸式配置.titleBar(组件)
        return this
    }

    fun 解决状态栏与布局顶部重叠(组件: Int, 值: Boolean): 状态栏沉浸式 {
        沉浸式配置.titleBar(组件, 值)
        return this
    }

    fun 解决状态栏与布局顶部重叠(组件: Int, 视图: View): 状态栏沉浸式 {
        沉浸式配置.titleBar(组件, 视图)
        return this
    }

    fun 解决状态栏与布局顶部重叠(组件: Int, 视图: View, 值: Boolean): 状态栏沉浸式 {
        沉浸式配置.titleBar(组件, 视图, 值)
        return this
    }

    fun 解决布局与状态栏重叠(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.applySystemFits(值)
        return this
    }

    fun 绘制标题栏距离顶部的高度为状态栏的高度(组件: Int): 状态栏沉浸式 {
        沉浸式配置.titleBarMarginTop(组件)
        return this
    }

    fun 绘制标题栏距离顶部的高度为状态栏的高度(组件: Int, 视图: View): 状态栏沉浸式 {
        沉浸式配置.titleBarMarginTop(组件, 视图)
        return this
    }

    fun 绘制标题栏距离顶部的高度为状态栏的高度(视图: View?): 状态栏沉浸式 {
        沉浸式配置.titleBarMarginTop(视图)
        return this
    }

    fun 支持actionBar的界面(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.supportActionBar(值)
        return this
    }

    fun 启用或禁用状态栏颜色的变化效果(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.statusBarColorTransformEnable(值)
        return this
    }

    fun 添加Tag(标签: String?): 状态栏沉浸式 {
        沉浸式配置.addTag(标签)
        return this
    }

    fun 恢复Tag(标签: String?): 状态栏沉浸式 {
        沉浸式配置.getTag(标签)
        return this
    }

    fun 解决软键盘与底部输入框冲突(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.keyboardEnable(值)
        return this
    }

    fun 解决软键盘与底部输入框冲突(值: Boolean, 键盘模式: Int): 状态栏沉浸式 {
        沉浸式配置.keyboardEnable(值, 键盘模式)
        return this
    }

    fun 键盘模式(键盘模式: Int): 状态栏沉浸式 {
        沉浸式配置.keyboardMode(键盘模式)
        return this
    }

    /**
     * 修改导航栏颜色的方法
     * @param 值 是否启用导航栏颜色的布尔值
     * @return 返回当前状态栏沉浸式的实例，支持链式调用
     */
    fun 是否修改导航栏颜色(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.navigationBarEnable(值)
        return this
    }

    fun 是否修改4点4设备导航栏颜色(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.navigationBarWithKitkatEnable(值)
        return this
    }

    fun 是否修改华为emui3点1导航栏颜色(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.navigationBarWithEMUI3Enable(值)
        return this
    }

    fun 是否使用沉浸式(值: Boolean): 状态栏沉浸式 {
        沉浸式配置.barEnable(值)
        return this
    }

    fun 导航栏显示隐藏监听(监听事件: OnNavigationBarListener?): 状态栏沉浸式 {
        沉浸式配置.setOnNavigationBarListener(监听事件)
        return this
    }

    fun 状态栏变化监听(值: Boolean, 监听事件: NavigationBarType?): 状态栏沉浸式 {
        沉浸式配置.onNavigationBarChange(值, 监听事件)
        return this
    }

    fun 切换横竖屏监听(监听事件: OnBarListener?): 状态栏沉浸式 {
        沉浸式配置.setOnBarListener(监听事件)
        return this
    }

    fun 软键盘弹出关闭监听(监听事件: OnKeyboardListener?): 状态栏沉浸式 {
        沉浸式配置.setOnKeyboardListener(监听事件)
        return this
    }

    fun 移除对所有视图的沉浸式状态栏支持(): 状态栏沉浸式 {
        沉浸式配置.removeSupportAllView()
        return this
    }

    fun 移除视图的沉浸式状态栏支持(视图: View): 状态栏沉浸式 {
        沉浸式配置.removeSupportView(视图)
        return this
    }

    fun 获取状态栏参数(): BarParams? {
        return 沉浸式配置.getBarParams()
    }
}


