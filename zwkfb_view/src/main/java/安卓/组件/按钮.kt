package 安卓.组件

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.PointerIcon
import android.widget.Button
import android.widget.RemoteViews.RemoteView





//================================================================================

fun Button.取可访问性类名():CharSequence = this.getAccessibilityClassName()

fun Button.解析指针图标回调(事件: MotionEvent, 指针索引: Int): PointerIcon =
    this.onResolvePointerIcon(事件, 指针索引)

