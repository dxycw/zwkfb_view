package 安卓x.碎片.应用

import androidx.annotation.StyleRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：对话框碎片
 * @author dxyc
 */
open class 对话框碎片 :DialogFragment{
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)
}

//======================================================================

fun DialogFragment.置样式(样式: Int, @StyleRes 主题: Int) {
    this.setStyle(样式, 主题)
}

//======================================================================

fun DialogFragment.显示(管理器: FragmentManager, 标记: String) =
    this.show(管理器, 标记)

fun DialogFragment.显示(事务: FragmentTransaction, 标记: String): Int =
    this.show(事务, 标记)


//======================================================================