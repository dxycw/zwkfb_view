package 自定义.活动类

import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

/**
 * 创建时间：2025年11月26日.
 *
 * 版本：0.1.1
 * @author dxyc
 */

//=============================================================================

/**
 * 模式：注册返回键按下事件
 *
 * 版本：0.1.1
 * @param 生命周期 生命周期所有者
 * @param 按返回键回调 按返回键回调
 */
fun AppCompatActivity.注册返回键按下事件(生命周期: LifecycleOwner, 按返回键回调: OnBackPressedCallback) =
    this.onBackPressedDispatcher.addCallback(生命周期, 按返回键回调)


/**
 * 模式：注册返回键按下事件
 *
 * 版本：0.1.1
 * @param 初始启用状态 初始启用状态
 * @param 布局代码 布局代码
 */
fun AppCompatActivity.注册返回键按下事件(初始启用状态: Boolean = true, 布局代码: () -> Unit = {}) =
    this.onBackPressedDispatcher.addCallback(owner = this,
        onBackPressedCallback = object : OnBackPressedCallback(初始启用状态) {
            override fun handleOnBackPressed() { 布局代码() }
        })


//==========================================================================================
/**
 * 描述：注册返回键按下事件按两次返回桌面
 *
 * 版本：0.1.1
 * @param 信息 信息
 */
fun AppCompatActivity.注册返回键按下事件按两次返回桌面(信息: CharSequence = "再按一次返回桌面") =
    this.注册返回键按下事件(true){ 按两次返回桌面(信息) }

