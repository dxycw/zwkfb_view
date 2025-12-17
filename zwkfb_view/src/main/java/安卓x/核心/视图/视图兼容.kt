@file:Suppress("DEPRECATION")

package 安卓x.核心.视图

import android.view.View
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat


/**
 * 创建时间：2025年11月18日.

 * 版本：0.0.7
 * @author dxyc
 */
open class 视图兼容 : ViewCompat() {

    companion object:ViewCompat() {
        /**
         * 版本：0.0.7
         *
         * 置应用窗口边距回调监听器
         * @param 视图 View
         * @param 监听器 OnApplyWindowInsetsListener
         */
        fun 置应用窗口边距回调监听器(视图: View, 监听器: OnApplyWindowInsetsListener) =
            setOnApplyWindowInsetsListener(视图,监听器)

    }

}

//================================================================

/**
 * 版本：0.0.7
 *
 * 置应用窗口边距回调监听器
 * @param 视图 View
 * @param 监听器 OnApplyWindowInsetsListener
 */
fun 置应用窗口边距回调监听器(视图: View, 监听器: OnApplyWindowInsetsListener) =
    ViewCompat.setOnApplyWindowInsetsListener(视图, 监听器)

//================================================================

