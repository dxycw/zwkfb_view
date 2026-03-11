package 安卓.组件

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ColorFilter
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.widget.ImageView


//======================================================================

/**
 * 描述：颜色过滤器
 *
 * 版本：0.1.1
 * @param 颜色过滤器
 * @return 颜色过滤器
 */
var ImageView.颜色过滤器: ColorFilter
    get() = colorFilter
    set(颜色过滤器) {
        colorFilter = 颜色过滤器
    }
/**
 * 描述：取颜色过滤器
 *
 * 版本：0.1.1
 * @return 颜色过滤器
 */
fun ImageView.取颜色过滤器(): ColorFilter {
    return getColorFilter()
}
/**
 * 描述：置颜色过滤器
 *
 * 版本：0.1.1
 * @param 颜色过滤器
 */
fun ImageView.置颜色过滤器(颜色过滤器: ColorFilter) {
    setColorFilter(颜色过滤器)
}

//======================================================================
/**
 * 描述：置颜色过滤器
 *
 * 版本：0.1.1
 * @param 颜色 颜色
 */
fun ImageView.置颜色过滤器(颜色: Int) {
    setColorFilter(颜色)
}

/**
 * 描述：置颜色过滤器
 *
 * 版本：0.1.1
 * @param 颜色 颜色
 * @param 模式 模式
 */
fun ImageView.置颜色过滤器(颜色: Int, 模式: PorterDuff.Mode) {
    setColorFilter(颜色, 模式)
}

//======================================================================


