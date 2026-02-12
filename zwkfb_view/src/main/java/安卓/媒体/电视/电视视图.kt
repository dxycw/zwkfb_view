package 安卓.媒体.电视

import android.content.Context
import android.media.tv.TvView
import android.util.AttributeSet

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：电视视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 电视视图 : TvView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}