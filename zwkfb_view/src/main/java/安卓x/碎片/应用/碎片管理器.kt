package 安卓x.碎片.应用

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction



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

