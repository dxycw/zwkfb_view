package 安卓.组件

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.widget.Spinner

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：下拉列表
 *
 * 版本：0.0.8
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
open class 下拉框 : Spinner {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, mode: Int) : super(
        context,
        attrs,
        defStyleAttr,
        mode
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
        mode: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes, mode)

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
        mode: Int,
        popupTheme: Resources.Theme?,
    ) : super(context, attrs, defStyleAttr, defStyleRes, mode, popupTheme)

    constructor(context: Context?, mode: Int) : super(context, mode)
}