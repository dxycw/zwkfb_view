package 商业.谷歌.安卓.材质.停靠标题栏

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.dockedtoolbar.DockedToolbarLayout

/**
 * 创建时间：2025年12月1日.
 *
 * 描述：停靠标题栏布局
 *
 * 版本：0.1.4
 * @author dxyc
 */
open class 停靠标题栏布局 : DockedToolbarLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}