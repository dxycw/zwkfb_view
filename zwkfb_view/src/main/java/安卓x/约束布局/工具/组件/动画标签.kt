package 安卓x.约束布局.工具.组件

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.utils.widget.MotionLabel

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：动画标签
 *
 * 版本：0.1.6
 * @author dxyc
 */
open class 动画标签 : MotionLabel {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}