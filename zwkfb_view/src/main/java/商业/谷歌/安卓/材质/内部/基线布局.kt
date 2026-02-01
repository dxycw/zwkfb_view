package 商业.谷歌.安卓.材质.内部

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.google.android.material.internal.BaselineLayout

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：基线布局
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 基线布局 : BaselineLayout {
    @SuppressLint("RestrictedApi")
    constructor(context: Context?) : super(context)
    @SuppressLint("RestrictedApi")
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    @SuppressLint("RestrictedApi")
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}