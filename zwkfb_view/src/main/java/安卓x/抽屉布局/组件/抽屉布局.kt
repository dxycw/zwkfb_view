package 安卓x.抽屉布局.组件

import android.content.Context
import android.util.AttributeSet
import androidx.drawerlayout.widget.DrawerLayout

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：抽屉布局
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 抽屉布局 : DrawerLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}