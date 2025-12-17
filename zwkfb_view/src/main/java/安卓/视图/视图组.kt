package 安卓.视图

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup

/**
 * 创建时间：2025年11月18日.

 * 版本：0.0.7
 * @author dxyc
 */
open class 视图组 : ViewGroup {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    /**
     * 版本：0.0.7
     *
     * 布局回调。
     */
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int,
    ) { 布局回调(changed, l, t, r, b) }


    /**
     * 版本：0.0.7
     *
     * 布局回调。
     */
    open fun 布局回调(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {}

    /**
     * 版本：0.1.0
     *
     * 布局参数。
     */
    class 布局参数 : ViewGroup.LayoutParams {
        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs)
        constructor(source: LayoutParams?) : super(source)
        constructor(width: Int, height: Int) : super(width, height)
    }

    /**
     * 版本：0.1.0
     *
     * 外边框布局参数。
     */
    class 外边框布局参数 : ViewGroup.MarginLayoutParams {
        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs)
        constructor(source: LayoutParams?) : super(source)
        constructor(source: MarginLayoutParams?) : super(source)
        constructor(width: Int, height: Int) : super(width, height)
    }


}

//==============================================================

/**
 * 描述：添加视图。
 * @param 视图 视图。
 */
fun ViewGroup.添加视图(视图: View) = this.addView(视图)
/**
 * 描述：添加视图。
 * @param 视图 视图。
 * @param 索引 索引。
 */
fun ViewGroup.添加视图(视图: View, 索引: Int) =
    this.addView(视图,索引)
/**
 * 描述：添加视图。
 * @param 视图 视图。
 * @param 宽度 宽度。
 * @param 高度 高度。
 */
fun ViewGroup.添加视图(视图: View, 宽度: Int, 高度: Int) =
    this.addView(视图,宽度,高度)
/**
 * 描述：添加视图。
 * @param 视图 视图。
 * @param 布局参数 布局参数。
 */
fun ViewGroup.添加视图(视图: View, 布局参数: ViewGroup.LayoutParams) =
    this.addView(视图,布局参数)
/**
 * 描述：添加视图。
 * @param 视图 视图。
 * @param 索引 索引。
 * @param 布局参数 布局参数。
 */
fun ViewGroup.添加视图(视图: View, 索引: Int, 布局参数: ViewGroup.LayoutParams) =
    this.addView(视图,索引,布局参数)

//==============================================================

/**
 * 描述：更新视图布局。
 * @param 视图 视图。
 * @param 布局参数 布局参数。
 */
fun ViewGroup.更新视图布局(视图: View, 布局参数: ViewGroup.LayoutParams) =
    this.updateViewLayout(视图,布局参数)

//==============================================================

/**
 * 描述：移除所有视图
 */
fun ViewGroup.移除所有视图() = this.removeAllViews()
