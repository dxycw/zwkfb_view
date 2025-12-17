package 商业.谷歌.安卓.材质.材质开关

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.materialswitch.MaterialSwitch

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：材质开关
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 材质开关 : MaterialSwitch {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}
