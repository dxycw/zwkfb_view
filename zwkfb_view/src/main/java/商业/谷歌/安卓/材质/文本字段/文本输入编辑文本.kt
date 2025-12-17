package 商业.谷歌.安卓.材质.文本字段

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

/**
 * 创建时间：2025年11月25日.
 *
 * 描述：文本输入编辑文本
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 文本输入编辑文本 : TextInputEditText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}