package 安卓.应用

import android.app.MediaRouteButton
import android.content.Context
import android.util.AttributeSet

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：媒体路由按钮
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 媒体路由按钮 : MediaRouteButton {
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