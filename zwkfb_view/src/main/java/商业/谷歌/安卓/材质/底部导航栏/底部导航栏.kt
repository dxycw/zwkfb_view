package 商业.谷歌.安卓.材质.底部导航栏

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomappbar.BottomAppBar

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：底部导航栏
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 底部导航栏 : BottomAppBar {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}