package 安卓x.约束布局.工具.组件

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.utils.widget.MotionTelltales

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：动画轨迹线
 *
 * 版本：0.1.1
 * @author dxyc
 */
open class 动画轨迹线 : MotionTelltales {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}