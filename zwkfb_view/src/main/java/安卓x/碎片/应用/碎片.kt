package 安卓x.碎片.应用

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


/**
 * 创建时间：2025年11月22日.
 *
 * 描述：碎片
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 碎片 : Fragment {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return 创建视图回调(inflater, container, savedInstanceState)
    }

    open fun 创建视图回调(
        布局解析器: LayoutInflater,
        容器: ViewGroup?,
        保存实例状态: Bundle?
    ): View?{
        return super.onCreateView(布局解析器, 容器, 保存实例状态)
    }
}