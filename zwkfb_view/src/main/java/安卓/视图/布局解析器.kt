package 安卓.视图

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


//======================================================================

/**
 * 描述：解析器
 * @param 布局id 布局资源id
 * @param 根布局 根布局
 * @param 附加到根布局 是否附加到根布局
 * @return 解析后的视图
 */
fun LayoutInflater.解析器(布局id: Int, 根布局: ViewGroup?, 附加到根布局: Boolean): View =
    inflate(布局id, 根布局, 附加到根布局)


