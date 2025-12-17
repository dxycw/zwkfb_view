package 安卓x.下拉刷新布局.组件

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：下拉刷新布局
 *
 * 版本：0.1.1
 * @author dxyc
 */
open class 下拉刷新布局 : SwipeRefreshLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
}