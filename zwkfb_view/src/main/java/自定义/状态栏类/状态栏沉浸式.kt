//package 自定义.状态栏类
//
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.app.Dialog
//import android.content.Context
//import android.graphics.Color
//import android.view.View
//import android.view.Window
//import android.view.WindowManager
//import androidx.annotation.ColorRes
//import androidx.annotation.FloatRange
//import androidx.core.view.ViewCompat
//import androidx.fragment.app.DialogFragment
//import androidx.fragment.app.Fragment
//import com.gyf.immersionbar.BarHide
//import com.gyf.immersionbar.BarParams
//import com.gyf.immersionbar.ImmersionBar
//import com.gyf.immersionbar.NavigationBarType
//import com.gyf.immersionbar.NotchCallback
//import com.gyf.immersionbar.OnBarListener
//import com.gyf.immersionbar.OnKeyboardListener
//import com.gyf.immersionbar.OnNavigationBarListener
//import 安卓.操作系统.构建
//import 自定义.主题类.是否是深色模式
//
////class 状态栏沉浸式 {
//        //======================================================================
//
//        @SuppressLint("StaticFieldLeak")
//        private lateinit var 根视图: View
//
//        fun 是否隐藏状态栏导航栏(上下文: Activity, 是否隐藏: Boolean = false, 自动深色: Boolean = false, 深色颜色: Boolean = false) {
//            // 获取根视图
//            根视图 = 上下文.findViewById(android.R.id.content)
//            val 沉浸设置 = 初始化沉浸式(上下文)
//            if (是否隐藏) {
//                沉浸设置.隐藏状态栏导航栏()
//                沉浸设置.全屏(true)
//                沉浸设置.状态栏导航栏透明()
//                沉浸设置.状态栏导航栏自动深色模式(自动深色)
//                沉浸设置.刷新()
//
//                if (是否是手势(上下文)) { //有
//                    //隐藏状态栏和导航栏
//                    上下文.window.decorView.setSystemUiVisibility(
//                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE // 让内容在状态栏和导航栏之间留白
//                                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // 让内容显示在状态栏和导航栏之间
//                                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 让内容显示在状态栏和导航栏后面
//                                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
//                                //| View.SYSTEM_UI_FLAG_FULLSCREEN // 隐藏状态栏
//                                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
//                    ) // 保持沉浸模式，即使用户交互也不会退出
//                }
//                上下文.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//                // 设置 OnApplyWindowInsetsListener
//                根视图.setOnApplyWindowInsetsListener { v, insets ->
//                    // 处理底部导航栏的内边距
//                    v.setPadding(0, 0, 0, 0)
//                    insets.consumeSystemWindowInsets()
//                }
//            } else {
//                沉浸设置.显示状态栏导航栏()
//                if (是否是手势(上下文)) { //有
//                    if (自动深色) {
//                        沉浸设置.状态栏字体图标自动深色模式(!上下文.是否是深色模式)
//                        if (构建.MANUFACTURER == "Xiaomi") { //制造商
//                            沉浸设置.状态栏导航栏透明()
//                            沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式)
//                        } else {
//                            if (上下文.是否是深色模式) {
//                                沉浸设置.导航栏颜色("#000000")
//                            } else {
//                                沉浸设置.导航栏颜色("#ffffff")
//                            }
//                        }
//                    } else {
//                        沉浸设置.状态栏字体图标自动深色模式(深色颜色)
//                        if (深色颜色) {
//                            沉浸设置.导航栏颜色(Color.WHITE)
//                        } else {
//                            沉浸设置.导航栏颜色(Color.BLACK)
//                        }
//                    }
//                } else { //没有
//                    if (自动深色) {
//                        沉浸设置.状态栏字体图标自动深色模式(!上下文.是否是深色模式)
//                        沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式)
//                    } else {
//                        沉浸设置.状态栏字体图标自动深色模式(深色颜色)
//                        沉浸设置.导航栏图标自动深色模式(深色颜色)
//                    }
//                }
//                沉浸设置.刷新()
//
//                // 设置 OnApplyWindowInsetsListener
//                根视图.setOnApplyWindowInsetsListener { v, insets ->
//                    // 处理底部导航栏的内边距
//                    v.setPadding(0, insets.systemWindowInsetTop, 0, insets.systemWindowInsetBottom)
//                    insets.consumeSystemWindowInsets()
//                }
//            }
//        }
//
//        fun 刷新是否隐藏状态栏导航栏布局() = ViewCompat.requestApplyInsets(根视图)
//
//        fun 修复对话框子窗口导航栏遮盖问题(上下文: Activity, 对话框子窗口上下文: Fragment) {
//            val 沉浸设置 = 状态栏沉浸式.初始化沉浸式(对话框子窗口上下文)
//            沉浸设置.状态栏导航栏透明()
//            if (状态栏沉浸式.是否是手势(上下文)) { //有
//                if (构建.MANUFACTURER == "Xiaomi") {
//                    沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式)
//                } else {
//                    if (上下文.是否是深色模式) {
//                        沉浸设置.导航栏颜色("#000000")
//                    } else {
//                        沉浸设置.导航栏颜色("#ffffff")
//                    }
//                }
//            } else { //没有
//                沉浸设置.导航栏图标自动深色模式(!上下文.是否是深色模式) // 设置导航栏图标为深色
//            }
//            沉浸设置.刷新()
//        }
//
//        // ================================================================================
//
//        fun 隐藏对话框子窗口状态栏(上下文: Activity, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 显示对话框子窗口状态栏(上下文: Activity, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 隐藏对话框子窗口状态栏(上下文: Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 显示对话框子窗口状态栏(上下文: Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 隐藏对话框子窗口状态栏(上下文: android.app.Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 显示对话框子窗口状态栏(上下文: android.app.Fragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 隐藏对话框子窗口状态栏(上下文: DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 显示对话框子窗口状态栏(上下文: DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().显示状态栏导航栏()
//                .状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 隐藏对话框子窗口状态栏(上下文: android.app.DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 显示对话框子窗口状态栏(上下文: android.app.DialogFragment, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 隐藏对话框子窗口状态栏(上下文: Activity, 对话框上下文: Dialog, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文, 对话框上下文).状态栏导航栏透明().隐藏状态栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//        fun 显示对话框子窗口状态栏(上下文: Activity, 对话框上下文: Dialog, 状态栏字体图标自动深色模式: Boolean = true) {
//            状态栏沉浸式.初始化沉浸式(上下文, 对话框上下文).状态栏导航栏透明().显示状态栏导航栏().状态栏字体图标自动深色模式(状态栏字体图标自动深色模式).刷新()
//        }
//
//    }
//
//    //========================================================================

////}

//
