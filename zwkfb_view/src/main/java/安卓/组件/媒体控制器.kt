package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.widget.MediaController

/**
 * 创建时间：2025年11月20日.
 *
 * 描述：媒体控制器
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 媒体控制器 : MediaController {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, useFastForward: Boolean) : super(context, useFastForward)
}