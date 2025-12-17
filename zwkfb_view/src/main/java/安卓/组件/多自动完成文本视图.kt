package 安卓.组件

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.MultiAutoCompleteTextView

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：多自动完成文本视图
 *
 * 版本：0.0.8
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
open class 多自动完成文本视图 : MultiAutoCompleteTextView {
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