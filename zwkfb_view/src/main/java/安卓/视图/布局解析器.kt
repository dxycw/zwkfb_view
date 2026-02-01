package 安卓.视图

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：布局解析器
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 布局解析器 :LayoutInflater{
    constructor(context: Context?) : super(context)
    constructor(original: LayoutInflater?, newContext: Context?) : super(original, newContext)

    override fun cloneInContext(newContext: Context?): LayoutInflater {
        TODO("Not yet implemented")
    }
}

//======================================================================

/**
 * 版本：0.1.0
 *
 * 描述：解析器
 * @param 布局id 布局资源id
 * @param 根布局 根布局
 * @param 附加到根布局 是否附加到根布局
 * @return 解析后的视图
 */
fun LayoutInflater.解析器(布局id: Int, 根布局: ViewGroup?, 附加到根布局: Boolean): View =
    inflate(布局id, 根布局, 附加到根布局)


