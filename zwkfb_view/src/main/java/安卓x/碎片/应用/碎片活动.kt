package 安卓x.碎片.应用

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager



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
