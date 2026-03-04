package 商业.谷歌.安卓.材质.提示栏

import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.View
import com.google.android.material.snackbar.Snackbar


//==================================================================

/**
 * 描述：持续时间
 */
var Snackbar.持续时间 : Int
    get() = duration
    set(值){ duration = 值 }

/**
 * 描述：取持续时间
 */
fun Snackbar.取持续时间() : Int = this.getDuration()
/**
 * 描述：置持续时间
 */
fun Snackbar.置持续时间(持续时间: Int): Snackbar = this.setDuration(持续时间)

//======================================================================

/**
 * 描述：置文本
 */
fun Snackbar.置文本(资源Id: Int) : Snackbar = this.setText(资源Id)

/**
 * 描述：置文本
 */
fun Snackbar.置文本(文本: CharSequence) : Snackbar = this.setText(文本)

//======================================================================

/**
 * 描述：置文本颜色
 */
fun Snackbar.置文本颜色(颜色: Int) : Snackbar = this.setTextColor(颜色)

/**
 * 描述：置文本颜色
 */
fun Snackbar.置文本颜色(颜色: ColorStateList) : Snackbar = this.setTextColor(颜色)

//======================================================================

/**
 * 描述：置背景色调
 */
fun Snackbar.置背景色调(颜色: Int) : Snackbar = this.setBackgroundTint(颜色)

/**
 * 描述：置背景色调列表
 */
fun Snackbar.置背景色调列表(颜色: ColorStateList) : Snackbar = this.setBackgroundTintList(颜色)

/**
 * 描述：置背景色调模式
 */
fun Snackbar.置背景色调模式(模式: PorterDuff.Mode) : Snackbar = this.setBackgroundTintMode(模式)

//======================================================================

/**
 * 描述：显示
 */
fun Snackbar.显示() = this.show()

/**
 * 描述：关闭
 */
fun Snackbar.关闭() = this.dismiss()

//======================================================================

/**
 * 描述：是否可见
 */
val Snackbar.是否可见 : Boolean get() = isShown

/**
 * 描述：是否可见
 */
fun Snackbar.是否可见() : Boolean = this.isShown()

//======================================================================

/**
 * 描述：置动作
 */
fun Snackbar.置动作(资源Id: Int, 事件: View.OnClickListener) : Snackbar = this.setAction(资源Id, 事件)

/**
 * 描述：置动作
 */
fun Snackbar.置动作(文本: CharSequence, 事件: View.OnClickListener) : Snackbar = this.setAction(文本, 事件)


//======================================================================

/**
 * 描述：置动作文本颜色
 */
fun Snackbar.置动作文本颜色(颜色: Int) : Snackbar = this.setActionTextColor(颜色)


/**
 * 描述：置动作文本颜色
 */
fun Snackbar.置动作文本颜色(颜色: ColorStateList) : Snackbar = this.setActionTextColor(颜色)

//======================================================================

