package 安卓x.应用兼容包.组件

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSpinner

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：应用兼容下拉框
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 应用兼容下拉框 : AppCompatSpinner {
    constructor(context: Context) : super(context)
    constructor(context: Context, mode: Int) : super(context, mode)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, mode: Int) : super(
        context,
        attrs,
        defStyleAttr,
        mode
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        mode: Int,
        popupTheme: Resources.Theme?,
    ) : super(context, attrs, defStyleAttr, mode, popupTheme)
}