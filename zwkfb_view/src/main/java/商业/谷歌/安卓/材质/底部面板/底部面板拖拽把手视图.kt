package 商业.谷歌.安卓.材质.底部面板

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.bottomsheet.BottomSheetDragHandleView

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：底部动作条拖拽把手视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 底部面板拖拽把手视图 : BottomSheetDragHandleView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}