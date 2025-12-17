package 安卓x.碎片.应用

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：碎片管理器
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 碎片管理器 : FragmentManager {
    constructor() : super()
}

//=======================================================================

/**
 * 描述：开启事务
 *
 * 版本：0.1.1
 * @author dxyc
 */
val FragmentManager.开启事务: FragmentTransaction
    get() = beginTransaction()

/**
 * 描述：开启事务
 *
 * 版本：0.1.1
 * @author dxyc
 */
fun FragmentManager.开启事务(): FragmentTransaction {
    return beginTransaction()
}

