package 安卓x.应用兼容.组件

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView




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


