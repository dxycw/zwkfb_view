package 安卓.组件

import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi


//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：显示
 */
fun Toast.显示() = show()

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：取消
 */
fun Toast.取消() = cancel()

//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：视图
 */
var Toast.视图: View?
    get() = view
    set(视图) { view = 视图 }

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：取视图
 */
fun Toast.取视图(): View? = getView()

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：置视图
 */
fun Toast.置视图(视图: View) = setView(视图)

//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：持续时间
 */
var Toast.持续时间: Int
    get() = duration
    set(持续时间) { duration = 持续时间  }
/**
 * 创建时间：2025年11月24日.
 *
 * 描述：取持续时间
 */
fun Toast.取持续时间(): Int = getDuration()
/**
 * 创建时间：2025年11月24日.
 *
 * 描述：描述置持续时间
 */
fun Toast.置持续时间(持续时间: Int) = setDuration(持续时间)

//======================================================================

fun Toast.置边距(水平边距: Float, 垂直边距: Float) {
    this.setMargin(水平边距, 垂直边距)
}

fun Toast.取水平边距(): Float {
    return this.getHorizontalMargin()
}

fun Toast.取垂直边距(): Float {
    return this.getVerticalMargin()
}

//======================================================================

fun Toast.置对齐方式(对齐方式: Int, x偏移: Int, y偏移: Int) {
    this.setGravity(对齐方式, x偏移, y偏移)
}

fun Toast.取对齐方式(): Int {
    return this.getGravity()
}

fun Toast.取x偏移(): Int {
    return this.getXOffset()
}

fun Toast.取y偏移(): Int {
    return this.getYOffset()
}

@RequiresApi(api = Build.VERSION_CODES.R)
fun Toast.添加回调(回调: Toast.Callback) {
    this.addCallback(回调)
}

@RequiresApi(api = Build.VERSION_CODES.R)
fun Toast.移除回调(回调: Toast.Callback) {
    this.removeCallback(回调)
}

//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：置文本
 */
fun Toast.置文本(资源Id: Int) = setText(资源Id)

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：置文本
 */
fun Toast.置文本(文本: CharSequence) = setText(文本)

//======================================================================



