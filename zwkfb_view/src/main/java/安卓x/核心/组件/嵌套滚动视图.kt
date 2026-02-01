package 安卓x.核心.组件

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.NestedScrollView

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：嵌套滚动视图
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 嵌套滚动视图 : NestedScrollView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}