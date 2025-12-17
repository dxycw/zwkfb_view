@file:Suppress("DEPRECATION")

package 安卓.应用

import android.app.FragmentContainer
import android.view.View


/**
 * 创建时间：2025年12月2日.
 *
 * 描述：碎片容器
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 碎片容器 : FragmentContainer {
    constructor() : super()

    @Deprecated("Deprecated in Java")
    override fun <T : View?> onFindViewById(id: Int): T? = 查找视图Id回调(id)

    @Deprecated("Deprecated in Java")
    override fun onHasView(): Boolean = 有视图回调()


    open fun <T : View?> 查找视图Id回调(id: Int): T? = null

    open fun 有视图回调(): Boolean = false


}