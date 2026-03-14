package 安卓.视图

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup



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
