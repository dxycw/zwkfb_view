package 安卓x.约束布局.组件

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ReactiveGuide

/**
 * 创建时间：2025年12月5日.
 *
 * 描述：反应指南
 *
 * 版本：0.1.6
 */
open class 反应引导 : ReactiveGuide {
    constructor(context: Context?) : super(context)
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