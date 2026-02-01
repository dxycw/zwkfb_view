package 安卓.手势

import android.content.Context
import android.gesture.GestureOverlayView
import android.util.AttributeSet

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：手势叠加视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 手势叠加视图 : GestureOverlayView {
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