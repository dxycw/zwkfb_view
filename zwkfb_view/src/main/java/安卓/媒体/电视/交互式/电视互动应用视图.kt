package 安卓.媒体.电视.交互式

import android.content.Context
import android.media.tv.interactive.TvInteractiveAppView
import android.os.Build
import android.util.AttributeSet
import androidx.annotation.RequiresApi

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：电视互动应用视图，仅支持Android 13.0及以上版本
 *
 * 版本：0.1.0
 * @author dxyc
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
open class 电视互动应用视图 : TvInteractiveAppView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}