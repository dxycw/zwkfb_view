package 安卓.输入法服务

import android.content.Context
import android.inputmethodservice.ExtractEditText
import android.util.AttributeSet

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：提取编辑文本
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 提取编辑文本 : ExtractEditText {
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