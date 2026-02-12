package 安卓.组件

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Toolbar

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：标题栏
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 标题栏 : Toolbar {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)
}

//======================================================================

/**
 * 版本：0.1.1
 *
 * 描述：置导航单击回调监听事件
 * @param 回调 导航单击回调监听事件
 */
fun Toolbar.置导航单击回调监听事件(回调: View.OnClickListener){
    this.setNavigationOnClickListener(回调)
}

