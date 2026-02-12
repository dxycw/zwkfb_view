@file:Suppress("DEPRECATION")

package 安卓x.碎片.应用

import android.content.Context
import android.util.AttributeSet
import androidx.fragment.app.FragmentTabHost

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：碎片标签页容器
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 碎片标签页容器 : FragmentTabHost {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
}