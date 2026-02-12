package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.PopupWindow

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：高级吐司
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 高级吐司 :PopupWindow{
    constructor() : super()
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

    constructor(contentView: View?) : super(contentView)
    constructor(contentView: View?, width: Int, height: Int) : super(contentView, width, height)
    constructor(
        contentView: View?,
        width: Int,
        height: Int,
        focusable: Boolean,
    ) : super(contentView, width, height, focusable)

    constructor(width: Int, height: Int) : super(width, height)
}