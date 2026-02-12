package 安卓x.应用兼容包.组件

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView

/**
 * 创建时间：2025年11月22日.
 *
 * 描述：搜索视图
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 搜索视图 : SearchView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
}

//======================================================================

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置查询文本回调监听事件
 *
 * 版本：0.1.2
 * @param 事件
 */
fun SearchView.置查询文本回调监听事件(事件: SearchView.OnQueryTextListener) =
    this.setOnQueryTextListener(事件)


