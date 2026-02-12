package 安卓.组件

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.PointerIcon
import android.widget.Button
import android.widget.RemoteViews.RemoteView

/**
 * 创建时间：2025年11月18日.
 *
 * 描述：按钮
 *
 * 版本：0.0.7
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
@RemoteView
open class 按钮 : Button {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    )
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int, ) :
            super(context, attrs, defStyleAttr, defStyleRes)
}


//================================================================================

fun Button.取可访问性类名():CharSequence = this.getAccessibilityClassName()

fun Button.解析指针图标回调(事件: MotionEvent, 指针索引: Int): PointerIcon =
    this.onResolvePointerIcon(事件, 指针索引)

