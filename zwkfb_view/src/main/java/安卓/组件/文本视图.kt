package 安卓.组件

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.widget.RemoteViews.RemoteView
import android.widget.TextView

/**
 * 创建时间：2025年11月18日.
 *
 * 描述：文本视图
 *
 * 版本：0.0.7
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
@RemoteView
open class 文本视图 : TextView {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)

//    private var 文本 : CharSequence = ""
//    private fun init(attrs: AttributeSet?) {
//        val a = context.obtainStyledAttributes(attrs,R.styleable.文本视图)
//        文本 = a.getString(R.styleable.文本视图_文本) ?: ""
//        a.recycle()
//
//        setText(this.文本)
//
//    }

}

//==============================================================
/**
 * 创建时间：2025年11月18日.
 *
 * 描述：文本
 *
 * 版本：0.0.7
 * @return 文本视图所显示的文本。
 */
var TextView.文本: CharSequence
    get() = this.text
    set(文本) { this.text = 文本 }

/**
 * 创建时间：2025年11月18日.
 *
 * 描述：取文本
 *
 * 版本：0.0.7
 * @return 文本视图所显示的文本。
 */
fun TextView.取文本(): CharSequence =
    this.getText()

/**
 * 创建时间：2025年11月18日.
 *
 * 描述：置文本
 *
 * 版本：0.0.7
 * @param 文本 显示的文本。
 */
fun TextView.置文本(文本: CharSequence) {
    this.setText(文本)
}

//==============================================================

/**
 * 创建时间：2025年11月20日.
 *
 * 描述：文本大小
 *
 * 版本：0.0.8
 */
var TextView.文本大小: Float
    get() = this.textSize
    set(大小) { this.textSize = 大小 }

/**
 * 创建时间：2025年11月20日.
 *
 * 描述：取文本大小
 *
 * 版本：0.0.8
 * @return 文本大小 显示的文本大小。
 */
fun TextView.取文本大小(): Float =
    this.getTextSize()

/**
 * 创建时间：2025年11月20日.
 *
 * 描述：置文本大小
 *
 * 版本：0.0.8
 * @param 大小 文本大小。
 */
fun TextView.置文本大小(大小: Float) {
    this.setTextSize(大小)
}

//==============================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：文本颜色
 *
 * 版本：0.1.0
 * @param 颜色 显示的文本颜色。
 */
fun TextView.置文本颜色(颜色: Int){ this.setTextColor(颜色) }

//==============================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：文本颜色
 *
 * 版本：0.1.0
 * @param 颜色集 显示的文本颜色。
 * @return 显示的文本颜色。
 */
var TextView.文本颜色: ColorStateList
    get() = this.getTextColors()
    set(颜色集) { this.setTextColor(颜色集) }

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：文本颜色
 *
 * 版本：0.1.0
 * @return 显示的文本颜色。
 */
fun TextView.取文本颜色(): ColorStateList = this.getTextColors()
/**
 * 创建时间：2025年11月24日.
 *
 * 描述：文本颜色
 *
 * 版本：0.1.0
 * @param 颜色集 显示的文本颜色。
 */
fun TextView.置文本颜色(颜色集: ColorStateList){ this.setTextColor(颜色集) }

//==============================================================


