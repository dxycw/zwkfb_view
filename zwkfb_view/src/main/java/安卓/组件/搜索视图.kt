package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.SearchView

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：搜索视图
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 搜索视图 : SearchView {
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