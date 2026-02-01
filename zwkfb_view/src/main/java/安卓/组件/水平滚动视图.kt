package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.HorizontalScrollView

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：水平滚动视图
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 水平滚动视图 : HorizontalScrollView {
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

//======================================================================

/**
 * 版本：0.1.1
 *
 * 描述：刷新布局
 */
fun HorizontalScrollView.刷新布局() = this.requestLayout()

