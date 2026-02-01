package 商业.谷歌.安卓.材质.图像视图

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.imageview.ShapeableImageView

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：可塑形图像视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 可塑形图像视图 : ShapeableImageView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )
}