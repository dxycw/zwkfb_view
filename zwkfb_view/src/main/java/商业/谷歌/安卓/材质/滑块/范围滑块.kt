package 商业.谷歌.安卓.材质.滑块

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.slider.RangeSlider

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：范围滑块
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 范围滑块 : RangeSlider {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}