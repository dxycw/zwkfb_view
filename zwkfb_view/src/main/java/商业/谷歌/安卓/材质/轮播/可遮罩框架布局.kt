package 商业.谷歌.安卓.材质.轮播

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.carousel.MaskableFrameLayout

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：可遮罩框架布局
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 可遮罩框架布局 : MaskableFrameLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}