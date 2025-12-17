package 商业.谷歌.安卓.材质.按钮

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButtonGroup

/**
 * 创建时间：2025年12月1日.
 *
 * 描述：材质按钮组
 *
 * 版本：0.1.4
 * @author dxyc
 */
open class 材质按钮组 : MaterialButtonGroup {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}