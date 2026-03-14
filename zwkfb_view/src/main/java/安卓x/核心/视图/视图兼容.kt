package 安卓x.核心.视图

import android.view.View
import androidx.core.view.OnApplyWindowInsetsListener
import androidx.core.view.ViewCompat




//================================================================

/**
 * 描述：置应用窗口边距回调监听器
 * @param 视图 View
 * @param 监听器 OnApplyWindowInsetsListener
 */
fun 置应用窗口边距回调监听器(视图: View, 监听器: OnApplyWindowInsetsListener) =
    ViewCompat.setOnApplyWindowInsetsListener(视图, 监听器)

//================================================================

