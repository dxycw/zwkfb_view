package 安卓x.核心.视图


import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat

/**
 * 创建时间：2025年11月18日.
 * @author dxyc
 */
open class 窗口内边距兼容 : WindowInsetsCompat {
    constructor(src: WindowInsetsCompat?) : super(src)


    open class 构建器 { //: Builder
        private var 构建器: Builder? = null
        constructor() : super(){
            构建器 = Builder()
        }
        constructor(insets: WindowInsetsCompat) : super(){
            构建器 = Builder(insets)
        }

        open fun 置系统窗口插入(插入: Insets): 构建器 {
            构建器?.setSystemWindowInsets(插入)
            return this
        }

        open fun 置系统手势插入(插入: Insets): 构建器 {
            构建器?.setSystemGestureInsets(插入)
            return this
        }

    }

    object 类型{

        /**
         * 一种表示用于显示状态的任何系统栏的内嵌类型
         * @return 状态栏
         */
        fun 状态栏():Int = Type.statusBars()

        /**
         * 表示任何导航系统栏的插入类型
         * @return 导航栏
         */
        fun 导航栏():Int = Type.navigationBars()

        /**
         * 表示标题栏窗口的嵌入类型
         * @return 标题栏
         */
        fun 标题栏():Int = Type.captionBar()

        /**
         * 表示输入法窗口的插入类型
         * @return 输入法
         */
        fun 输入法():Int = Type.ime()

        /**
         * 返回表示系统手势内边距的 Insets 类型。
         * 系统手势内边距代表窗口中系统手势具有优先权的区域，在这些区域系统可能会消费部分或全部触摸输入，例如由于系统栏占据该区域，或该区域被保留给纯触摸手势使用。
         * 简单的点击操作保证能够传递到窗口，即使在系统手势内边距区域内，只要点击位置在系统窗口内边距之外。
         * 当请求了 View.SYSTEM_UI_FLAG_LAYOUT_STABLE 标志时，即使由于 View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN 或 View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
         * 导致系统手势处于非活动状态，仍会返回该内边距值。
         * @return 系统手势
         */
        fun 系统手势():Int = Type.systemGestures()

        /**
         * 强制系统手势
         * @return 强制系统手势
         */
        fun 强制系统手势():Int = Type.mandatorySystemGestures()

        /**
         * 可点击元素
         * @return 可点击元素
         */
        fun 可点击元素():Int = Type.tappableElement()

        /**
         * 屏幕缺口, 表示屏幕上的任何缺口区域，例如刘海屏或水滴屏和挖孔屏。
         * @return 屏幕缺口
         */
        fun 屏幕缺口():Int = Type.displayCutout()

        /**
         * 系统覆盖
         * @return 系统覆盖
         */
        fun 系统覆盖():Int = Type.systemOverlays()

        /**
         * 系统栏
         * @return 系统栏
         */
        fun 系统栏():Int = Type.systemBars()

    }

    object 侧面{
        /**
         * 全
         * @return 全
         */
        fun 全():Int = Side.all()
    }

}

//======================================================

 /**
 * 取边距
 * @param 类型掩码 类型掩码
 * @return 边距
 */
fun WindowInsetsCompat.取边距(类型掩码: Int) : Insets =
    getInsets(类型掩码)

//======================================================



