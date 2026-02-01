@file:Suppress("DEPRECATION")

package 商业.谷歌.安卓.材质.卡片

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import 商业.谷歌.安卓.材质.卡片.边框颜色

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：材质卡片视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 材质卡片视图 : MaterialCardView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}

//======================================================================

/**
 * 描述：边框颜色
 *
 * 版本：0.1.1
 *  @param 边框颜色 边框颜色
 *  @return 边框颜色 边框颜色
 */
var MaterialCardView.边框颜色: Int
    get() = this.strokeColor
    set(边框颜色) { this.strokeColor = 边框颜色 }

/**
 * 描述：边框颜色
 *
 * 版本：0.1.1
 *  @return 边框颜色 边框颜色
 */
fun MaterialCardView.取边框颜色(): Int =
    this.getStrokeColor()

/**
 * 描述：边框颜色
 *
 * 版本：0.1.1
 *  @param 边框颜色 边框颜色
 */
fun MaterialCardView.置边框颜色(边框颜色: Int) =
    this.setStrokeColor(边框颜色)

//======================================================================
/**
 * 描述：边框宽度
 *
 * 版本：0.1.1
 *  @param 边框宽度 边框宽度
 *  @return 边框宽度 边框宽度
 */
var MaterialCardView.边框宽度: Int
    get() = this.strokeWidth
    set(边框宽度) { this.strokeWidth = 边框宽度 }
/**
 * 描述：边框宽度
 *
 * 版本：0.1.1
 *  @return 边框宽度 边框宽度
 */
fun MaterialCardView.取边框宽度(): Int =
    this.getStrokeWidth()

/**
 * 描述：边框宽度
 *
 * 版本：0.1.1
 *  @param 边框宽度 边框宽度
 */
fun MaterialCardView.置边框宽度(边框宽度: Int) =
    this.setStrokeWidth(边框宽度)

//======================================================================

 /**
 * 描述：卡片背景颜色
 *
 * 版本：0.1.1
 *  @param 颜色 颜色
 *  @return 颜色 颜色
 */
var MaterialCardView.卡片背景颜色: ColorStateList
    get() = this.cardBackgroundColor
    set(颜色) { this.setCardBackgroundColor(颜色) }

/**
 * 描述：卡片背景颜色
 *
 * 版本：0.1.1
 *  @return 颜色 颜色
 */
fun MaterialCardView.取卡片背景颜色(): ColorStateList =
    this.getCardBackgroundColor()
/**
 * 描述：卡片背景颜色
 *
 * 版本：0.1.1
 *  @param 颜色 颜色
 */
fun MaterialCardView.置卡片背景颜色(颜色: Int) =
    this.setCardBackgroundColor(颜色)

/**
 * 描述：卡片背景颜色
 *
 * 版本：0.1.1
 *  @param 颜色 颜色
 */
fun MaterialCardView.置卡片背景颜色(颜色: ColorStateList) =
    this.setCardBackgroundColor(颜色)

//======================================================================
/**
 * 描述：卡片阴影
 *
 * 版本：0.1.1
 *  @param 阴影 阴影
 *  @return 阴影 阴影
 */
var MaterialCardView.卡片阴影: Float
    get() = this.cardElevation
    set(阴影) { this.cardElevation = 阴影 }
/**
 * 描述：卡片阴影
 *
 * 版本：0.1.1
 *  @return 阴影 阴影
 */
fun MaterialCardView.取卡片阴影(): Float =
    this.getCardElevation()

/**
 * 描述：卡片阴影
 *
 * 版本：0.1.1
 *  @param 阴影 阴影
 */
fun MaterialCardView.置卡片背景颜色(阴影: Float) =
    this.setCardElevation(阴影)


//======================================================================


