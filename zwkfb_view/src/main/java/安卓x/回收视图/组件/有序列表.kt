package 安卓x.回收视图.组件

import androidx.recyclerview.widget.SortedList

/**
 * 创建时间：2025年12月12日.
 *
 * 描述：有序列表
 *
 * 版本：0.1.7
 * @author dxyc
 */
open class 有序列表<T> : SortedList<T> {
    constructor(klass: Class<T?>, callback: Callback<T?>) : super(klass, callback)
    constructor(klass: Class<T?>, callback: Callback<T?>, initialCapacity: Int) : super(
        klass,
        callback,
        initialCapacity
    )
}