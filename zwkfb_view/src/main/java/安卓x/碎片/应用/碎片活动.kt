package 安卓x.碎片.应用

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：碎片活动
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 碎片活动 : FragmentActivity {
    constructor() : super()
    constructor(内容布局Id: Int) : super(内容布局Id)
}

//=======================================================================

/**
 * 描述：支持碎片管理器
 *
 * 版本：0.1.1
 * @author dxyc
 */
val FragmentActivity.支持碎片管理器: FragmentManager
    get() = supportFragmentManager
/**
 * 描述：取支持碎片管理器
 *
 * 版本：0.1.1
 * @author dxyc
 */
fun FragmentActivity.取支持碎片管理器(): FragmentManager {
    return getSupportFragmentManager()
}
