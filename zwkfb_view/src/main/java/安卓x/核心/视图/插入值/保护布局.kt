package 安卓x.核心.视图.插入值

import android.content.Context
import android.util.AttributeSet
import androidx.core.view.insets.Protection
import androidx.core.view.insets.ProtectionLayout

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：保护布局
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 保护布局 : ProtectionLayout {
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

    constructor(context: Context, protections: List<Protection?>) : super(context, protections)
}