package 安卓x.应用兼容包.组件

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.DialogTitle

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：对话框标题
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 对话框标题 : DialogTitle {
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context) : super(context)
}