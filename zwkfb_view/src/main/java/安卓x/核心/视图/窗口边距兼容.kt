package 安卓x.核心.视图


import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat

/**
 * 创建时间：2025年11月18日.

 * 版本：0.0.7
 * @author dxyc
 */
open class 窗口边距兼容 : WindowInsetsCompat {
    constructor(src: WindowInsetsCompat?) : super(src)

    /**
     * 版本：0.0.7
     *
     * 类型
     */
    object 类型 {
        /**
         * 版本：0.0.7
         *
         * 系统栏
         * @return 系统栏
         */
        fun 系统栏():Int = Type.systemBars()

    }
}

//======================================================

 /**
 * 版本：0.0.7
 *
 * 取边距
 * @param 类型掩码 类型掩码
 * @return 边距
 */
fun WindowInsetsCompat.取边距(类型掩码: Int) : Insets =
    getInsets(类型掩码)

//======================================================



