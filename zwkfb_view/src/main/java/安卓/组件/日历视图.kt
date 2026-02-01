package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.CalendarView

/**
 * 创建时间：2025年11月18日.
 *
 * 描述：日历视图
 *
 * 版本：0.0.7
 * @author dxyc
 */
open class 日历视图 : CalendarView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}