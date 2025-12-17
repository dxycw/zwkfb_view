package 安卓x.活动

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.VisibleForTesting


/**
 * 创建时间：2025年11月18日.

 * 版本：0.0.7
 * @author dxyc
 */

//================================================================================

/**
 * 版本：0.0.7
 *
 * 在平台 API 29 及以上版本中使用的浅色遮罩色。
 */
@VisibleForTesting
internal val 默认浅色遮罩色 = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)

/**
 * 版本：0.0.7
 *
 * 平台中使用的深色遮罩色。
 */
@VisibleForTesting
internal val 默认深色遮罩色 = Color.argb(0x80, 0x1b, 0x1b, 0x1b)

//================================================================================

/**
 * 版本：0.0.7
 *
 * 启用边缘到边缘。
 * @param 状态栏样式 状态栏样式。
 * @param 导航栏样式 导航栏样式。
 */
fun ComponentActivity.启用边缘到边缘(
    状态栏样式: SystemBarStyle = SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
    导航栏样式: SystemBarStyle = SystemBarStyle.auto(默认浅色遮罩色, 默认深色遮罩色)
) = enableEdgeToEdge(状态栏样式, 导航栏样式)

//================================================================================

