package 安卓x.约束布局.工具.组件

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.utils.widget.MotionButton

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：动画按钮
 *
 * 版本：0.1.6
 * @author dxyc
 */
open class 动画按钮 : MotionButton {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}