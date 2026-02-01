package 商业.谷歌.安卓.材质.导航轨道

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.navigationrail.NavigationRailView

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：导航轨道视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 导航轨道视图 : NavigationRailView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}