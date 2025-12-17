package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：视频视图
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 视频视图 : VideoView {
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