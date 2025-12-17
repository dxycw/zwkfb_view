package 商业.谷歌.安卓.材质.文本字段

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.MaterialAutoCompleteTextView

/**
 * 创建时间：2025年11月25日.
 *
 * 描述：材质自动完成文本视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 材质自动完成文本视图 : MaterialAutoCompleteTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    )
}