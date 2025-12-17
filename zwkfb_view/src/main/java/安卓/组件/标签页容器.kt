@file:Suppress("DEPRECATION")

package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.TabHost

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：标签页容器
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 标签页容器 : TabHost {
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