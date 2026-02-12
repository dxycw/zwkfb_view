package 安卓.应用组件

import android.appwidget.AppWidgetHostView
import android.content.Context

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：应用组件容器视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 应用组件容器视图 : AppWidgetHostView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, animationIn: Int, animationOut: Int) : super(
        context,
        animationIn,
        animationOut
    )
}