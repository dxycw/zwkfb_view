@file:Suppress("DEPRECATION")

package 安卓.应用

import android.app.FragmentBreadCrumbs
import android.content.Context
import android.util.AttributeSet

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：碎片面包屑
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 碎片面包屑 :FragmentBreadCrumbs {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}