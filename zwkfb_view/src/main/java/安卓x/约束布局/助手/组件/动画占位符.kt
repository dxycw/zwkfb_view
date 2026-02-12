package 安卓x.约束布局.助手.组件

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.helper.widget.MotionPlaceholder

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：动画占位符
 *
 * 版本：0.1.6
 * @author dxyc
 */
open class 动画占位符 : MotionPlaceholder {
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