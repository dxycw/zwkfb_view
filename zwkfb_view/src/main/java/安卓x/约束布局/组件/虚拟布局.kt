package 安卓x.约束布局.组件

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.VirtualLayout

/**
 * 创建时间：2025年12月12日.
 *
 * 描述：虚拟布局
 *
 * 版本：0.1.7
 * @author dxyc
 */
open class 虚拟布局 : VirtualLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}