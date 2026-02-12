@file:Suppress("DEPRECATION")

package 安卓.输入法服务

import android.content.Context
import android.inputmethodservice.KeyboardView
import android.util.AttributeSet

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：键盘视图
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 键盘视图 : KeyboardView {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}