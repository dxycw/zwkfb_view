package 商业.谷歌.安卓.材质.底部导航

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.google.android.material.bottomnavigation.BottomNavigationView
import 安卓.组件.颜色过滤器

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：底部导航视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 底部导航视图 : BottomNavigationView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}

//==============================================================================

/**
 * 描述：项选中指示器颜色
 *
 * 版本：0.1.1
 * @param 颜色状态列表 项选中指示器颜色
 * @return 颜色状态列表
 */
var BottomNavigationView.项选中指示器颜色: ColorStateList?
    get() = itemActiveIndicatorColor
    set(颜色状态列表) { itemActiveIndicatorColor = 颜色状态列表 }

/**
 * 描述：项选中指示器颜色
 *
 * 版本：0.1.1
 * @return 颜色状态列表
 */
fun BottomNavigationView.取项选中指示器颜色() : ColorStateList? =
     getItemActiveIndicatorColor()

/**
 * 描述：项选中指示器颜色
 *
 * 版本：0.1.0
 * @param 颜色状态列表 颜色状态列表
 * @return 颜色状态列表
 */
fun BottomNavigationView.置项选中指示器颜色(颜色状态列表: ColorStateList) =
    setItemActiveIndicatorColor(颜色状态列表)


//==============================================================================


