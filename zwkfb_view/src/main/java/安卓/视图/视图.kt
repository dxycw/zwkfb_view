package 安卓.视图

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.webkit.WebView

/**
 * 创建时间：2025年11月18日.
 *
 * 描述：视图。
 *
 * 版本：0.0.7
 * @author dxyc
 */
open class 视图 : View {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr)
    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}

//===============================================================
//===============================================================

/**
 * 描述：获取x坐标
 * @param 值 值。
 * @return x坐标
 */
var View.x: Float
    get() = this.x
    set(值) {this.x = 值}

/**
 * 描述：获取x坐标
 * @return x坐标
 */
fun View.取x(): Float = this.getX()

/**
 * 描述：置x坐标
 * @param 值 值。
 */
fun View.置x(值: Float) = this.setX(值)

//======================================================================

/**
 * 描述：获取x坐标
 * @param 值 值。
 * @return y坐标
 */
var View.y: Float
    get() = this.y
    set(值) {this.y = 值}

/**
 * 描述：获取y坐标
 * @return y坐标
 */
fun View.取y(): Float = this.getY()
/**
 * 描述：置y坐标
 * @param 值 值。
 */
fun View.置y(值: Float) = this.setY(值)

//======================================================================

/**
 * 描述：滑动x坐标
 * @param 值 值。
 * @return 滑动x坐标
 */
var View.滑动x: Int
    get() = this.scrollX
    set(值) {this.scrollX = 值}
/**
 * 描述：获取滑动x坐标
 * @return 滑动x坐标
 */
fun View.取滑动x(): Int = this.getScrollX()
/**
 * 描述：置滑动x坐标
 * @param 值 值。
 */
fun View.置滑动x(值: Int) = this.setScrollX(值)

//======================================================================
/**
 * 描述：滑动y坐标
 * @param 值 值。
 * @return 滑动y坐标
 */
var View.滑动y: Int
    get() = this.scrollY
    set(值) {this.scrollY = 值}
/**
 * 描述：获取滑动y坐标
 * @return 滑动y坐标
 */
fun View.取滑动y(): Int = this.getScrollY()
/**
 * 描述：置滑动y坐标
 * @param 值 值。
 */
fun View.置滑动y(值: Int) = this.setScrollY(值)

//======================================================================

/**
 * 描述：横向滑动
 * @param 启用 启用。
 * @return 横向滑动
 */
var View.横向滑动: Boolean
    get() = this.isHorizontalScrollBarEnabled
    set(启用) {this.isHorizontalScrollBarEnabled = 启用}

/**
 * 描述：获取横向滑动
 * @return 横向滑动
 */
fun View.取横向滑动(): Boolean = this.isHorizontalScrollBarEnabled()
/**
 * 描述：置横向滑动
 * @param 启用 启用。
 */
fun View.置横向滑动(启用: Boolean) = this.setHorizontalScrollBarEnabled(启用)

//======================================================================

/**
 * 描述：纵向滑动
 * @param 启用 启用。
 * @return 纵向滑动
 */
var View.纵向滑动: Boolean
    get() = this.isVerticalScrollBarEnabled
    set(启用) {this.isVerticalScrollBarEnabled = 启用}
/**
 * 描述：获取纵向滑动
 * @return 纵向滑动
 */
fun View.取纵向滑动(): Boolean = this.isVerticalScrollBarEnabled()

/**
 * 描述：置纵向滑动
 * @param 启用 启用。
 */
fun View.置纵向滑动(启用: Boolean) = this.setVerticalScrollBarEnabled(启用)

//======================================================================

/**
 * 描述：嵌套滑动
 * @param 启用 启用。
 * @return 嵌套滑动
 */
var View.嵌套滑动: Boolean
    get() = this.isNestedScrollingEnabled
    set(启用) {this.isNestedScrollingEnabled = 启用}

/**
 * 描述：获取嵌套滑动
 * @return 嵌套滑动 嵌套滑动。
 */
fun View.取嵌套滑动(): Boolean = this.isNestedScrollingEnabled()
/**
 * 描述：置嵌套滑动
 * @param 启用 启用
 */
fun View.置嵌套滑动(启用: Boolean) = this.setNestedScrollingEnabled(启用)

//======================================================================

/**
 * 描述：背景。
 * @param 绘制对象 绘制对象。
 * @return 背景 背景。
 */
var View.背景: Drawable
    get() = background
    set(绘制对象) { background = 绘制对象 }

/**
 * 版本：0.1.0
 *
 * 描述：获取背景。
 * @return 背景 背景。
 */
fun View.取背景(): Drawable = getBackground()
/**
 * 版本：0.1.0
 *
 * 描述：设置背景。
 * @param 绘制对象 绘制对象。
 */
fun View.置背景(绘制对象: Drawable) = setBackground(绘制对象)

//===============================================================

 /**
 * 版本：0.1.0
 *
 * 描述：设置背景颜色。
 * @param 颜色 颜色。
 */
fun View.置背景颜色(颜色: Int) = setBackgroundColor(颜色)

//===============================================================
/**
 * 版本：0.1.0
 *
 * 描述：设置内边距。
 * @param 左 左。
 * @param 上 上。
 * @param 右 右。
 * @param 下 下。
 */
fun View.置内边距(左: Int, 上: Int, 右: Int, 下: Int) =
    setPadding(左, 上, 右, 下)

//===============================================================

/**
 * 版本：0.1.0
 *
 * 描述：内边距开始。
 * @return 内边距开始 内边距开始。
 */
val View.内边距开始: Int
    get() = paddingStart
/**
 * 版本：0.1.0
 *
 * 描述：获取内边距开始。
 * @return 内边距开始 内边距开始。
 */
fun View.取内边距开始(): Int {
    return paddingStart
}

/**
 * 版本：0.1.0
 *
 * 描述：内边距结束。
 * @return 内边距结束 内边距结束。
 */
val View.内边距结束: Int
    get() = paddingEnd

/**
 * 版本：0.1.0
 *
 * 描述：获取内边距结束。
 * @return 内边距结束 内边距结束。
 */
fun View.取内边距结束(): Int {
    return paddingEnd
}

//=========================================================

/**
 * 版本：0.1.0
 *
 * 描述：内边距上。
 * @return 内边距上 内边距上。
 */
val View.内边距上: Int
    get() = paddingTop

/**
 * 版本：0.1.0
 *
 * 描述：获取内边距上。
 * @return 内边距上 内边距上。
 */
fun View.取内边距上(): Int {
    return paddingTop
}

/**
 * 版本：0.1.0
 *
 * 描述：内边距下。
 * @return 内边距下 内边距下。
 */
val View.内边距下: Int
    get() = paddingBottom

/**
 * 版本：0.1.0
 *
 * 描述：获取内边距下。
 * @return 内边距下 内边距下。
 */
fun View.取内边距下(): Int {
    return paddingBottom
}

/**
 * 版本：0.1.0
 *
 * 描述：内边距左。
 * @return 内边距左 内边距左。
 */
val View.内边距左: Int
    get() = paddingLeft
/**
 * 版本：0.1.0
 *
 * 描述：获取内边距左。
 * @return 内边距左 内边距左。
 */
fun View.取内边距左(): Int {
    return paddingLeft
}

/**
 * 版本：0.1.0
 *
 * 描述：内边距右。
 * @return 内边距右 内边距右。
 */
val View.内边距右: Int
    get() = paddingRight

/**
 * 版本：0.1.0
 *
 * 描述：获取内边距右。
 * @return 内边距右 内边距右。
 */
fun View.取内边距右(): Int {
    return paddingRight
}

//=========================================================

/**
 * 版本：0.1.0
 *
 * 描述：设置回调监听事件。
 * @param 回调 回调。
 */
fun View.置单击回调监听事件(回调: View.OnClickListener) =
    this.setOnClickListener(回调)

/**
 * 版本：0.1.0
 *
 * 描述：设置长按回调监听事件。
 * @param 回调 回调。
 */
fun View.置长按回调监听事件(回调: View.OnLongClickListener) =
    this.setOnLongClickListener(回调)


/**
 * 版本：0.1.0
 *
 * 描述：设置按键回调监听事件。
 * @param 回调 回调。
 */
fun View.置按键回调监听事件(回调: View.OnKeyListener) =
    this.setOnKeyListener(回调)

/**
 * 版本：0.1.0
 *
 * 描述：设置焦点改变回调监听事件。
 * @param 回调 回调。
 */
fun View.置焦点改变回调监听事件(回调: View.OnFocusChangeListener) =
    this.setOnFocusChangeListener(回调)

/**
 * 版本：0.1.0
 *
 * 描述：设置触摸回调监听事件。
 * @param 回调 回调。
 */
fun View.置触摸回调监听事件(回调: View.OnTouchListener) =
    this.setOnTouchListener(回调)

/**
 * 版本：0.1.0
 *
 * 描述：设置滚动改变回调监听事件。
 * @param 回调 回调。
 */
fun View.置滚动改变回调监听事件(回调: View.OnScrollChangeListener) =
    this.setOnScrollChangeListener(回调)

//======================================= ==================


