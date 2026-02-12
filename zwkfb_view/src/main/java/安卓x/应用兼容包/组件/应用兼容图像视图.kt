package 安卓x.应用兼容包.组件

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：应用兼容图像视图
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 应用兼容图像视图 : AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}