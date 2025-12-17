package 商业.谷歌.安卓.材质.加载指示器

import android.content.Context
import android.util.AttributeSet

/**
 * 创建时间：2025年12月1日.
 *
 * 描述：加载指示器
 *
 * 版本：0.1.4
 * @author dxyc
 */
open class 加载指示器 : LoadingIndicator {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}

