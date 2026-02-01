@file:Suppress("DEPRECATION")

package 商业.谷歌.安卓.材质.文本视图

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

/**
 * 创建时间：2025年11月25日.
 *
 * 描述：材质文本视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 材质文本视图 : MaterialTextView {
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