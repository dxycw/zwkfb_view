package 安卓x.碎片.应用

import android.view.View
import androidx.fragment.app.FragmentContainer

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：碎片容器
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 碎片容器 :FragmentContainer {
    constructor() : super()

    override fun onFindViewById(id: Int): View? = 查找视图Id回调(id)

    override fun onHasView(): Boolean = 有视图回调()

    open fun 查找视图Id回调(id: Int): View? = null

    open fun 有视图回调(): Boolean = false

}