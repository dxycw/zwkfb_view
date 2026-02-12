@file:Suppress("DEPRECATION")

package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.DigitalClock

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：数字时钟
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 数字时钟 : DigitalClock {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}