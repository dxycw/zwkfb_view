package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：相对布局
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 相对布局 : RelativeLayout {
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