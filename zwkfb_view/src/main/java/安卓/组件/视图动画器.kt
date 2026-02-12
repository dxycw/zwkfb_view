package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.ViewAnimator

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：视图动画器
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 视图动画器 : ViewAnimator {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}