package 安卓x.协调布局.组件

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：协调布局
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 协调布局 : CoordinatorLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}