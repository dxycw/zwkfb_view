package 自定义.活动类

import android.app.Activity
import android.content.Context
import android.content.Intent


//==========================================================================================
/**
 * 描述：启动活动
 * @param 活动 活动。
 */
fun Context.启动活动(活动: Class<out Activity>) =
    this.startActivity(Intent(this, 活动))

/**
 * 描述：启动活动
 * @param 活动 活动。
 */
fun Context.启动活动(活动: Activity) =
    this.startActivity(Intent(this, 活动::class.java))