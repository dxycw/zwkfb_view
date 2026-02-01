package 自定义.活动类

import android.app.Activity
import android.content.Context
import android.content.Intent

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：活动
 *
 * 版本：0.1.1
 * @author dxyc
 */

//==========================================================================================
/**
 * 描述：切换窗口
 *
 * 版本：0.1.1
 * @param 窗口 窗口。
 */
fun Context.切换窗口(窗口: Class<out Activity>) =
    this.startActivity(Intent(this, 窗口))

/**
 * 描述：切换窗口
 *
 * 版本：0.1.1
 * @param 窗口 窗口。
 */
fun Context.切换窗口(窗口: Activity) =
    this.startActivity(Intent(this, 窗口::class.java))