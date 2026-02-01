package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewFlipper

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：视图翻转器
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 视图翻转器 : ViewFlipper {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}