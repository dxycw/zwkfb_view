package 商业.谷歌.安卓.材质.按钮

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：材质按钮
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 材质按钮 : MaterialButton {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )
}