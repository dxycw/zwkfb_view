package 安卓x.核心.组件

import android.content.Context
import android.util.AttributeSet
import androidx.core.widget.ContentLoadingProgressBar

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：内容加载进度条
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 内容加载进度条 : ContentLoadingProgressBar {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
}