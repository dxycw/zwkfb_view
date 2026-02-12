package 安卓x.回收视图.组件

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：回收视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 回收视图 : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}