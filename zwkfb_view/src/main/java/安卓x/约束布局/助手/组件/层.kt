package 安卓x.约束布局.助手.组件

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.helper.widget.Layer

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：层
 *
 * 版本：0.1.1
 * @author dxyc
 */
open class 层 : Layer {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}