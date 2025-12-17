package 自定义.活动类

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import 安卓.内容.切换窗口
import 安卓.组件.吐司

/**
 * 创建时间：2025年11月26日.
 *
 * 版本：0.1.1
 * @author dxyc
 */


//==========================================================================================

/**
 * 切换窗口
 *
 * 版本：0.1.1
 */
fun Activity.切换窗口(窗口: Class<out Activity>) =
    this.startActivity(Intent(this, 窗口))

/**
 * 切换窗口
 *
 * 版本：0.1.1
 */
fun Activity.切换窗口(窗口: Activity) =
    this.startActivity(Intent(this, 窗口::class.java))

//=================================================================================
/**
 * 切换窗口到地址
 *
 * 版本：0.1.1
 */
@SuppressLint("UnsafeImplicitIntentLaunch")
fun Activity.切换窗口到地址(网址: Uri) =
    this.startActivity(Intent(Intent.ACTION_VIEW, 网址))

//==========================================================================================

/**
 * 返回桌面,直接退出到后台，不会关闭应用程序
 *
 * 版本：0.1.1
 */
fun Activity.返回桌面() =
    this.切换窗口(Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME))

//==========================================================================================

private var 第一次点击时间: Long = 0

/**
 * 按两次返回桌面
 *
 * @param 信息 再按一次返回桌面
 * 版本：0.1.1
 */
fun Activity.按两次返回桌面(信息: CharSequence = "再按一次返回桌面"){
    // 在这里处理返回键事件
    if ((System.currentTimeMillis() - 第一次点击时间) > 2000) {
        吐司.制作文本(this, 信息, 吐司.LENGTH_SHORT).show()
        第一次点击时间 = System.currentTimeMillis()
    } else { 返回桌面() } //返回桌面指令
}





