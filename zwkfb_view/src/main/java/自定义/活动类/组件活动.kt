package 自定义.活动类

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.LifecycleOwner

//====================================================================================

/**
 *  注册返回键按下事件
 * @author dxyc
 */
fun ComponentActivity.注册返回键按下事件(owner: LifecycleOwner, 返回键回调: OnBackPressedCallback)
        = this.onBackPressedDispatcher.addCallback(owner = owner, onBackPressedCallback = 返回键回调)

//====================================================================================
/**
 *  注册返回键按下事件
 * @author dxyc
 */
fun ComponentActivity.注册返回键按下事件(初始启用状态: Boolean = true, 内容: () -> Unit = {})
        = this.onBackPressedDispatcher.addCallback(owner = this,
    onBackPressedCallback = object : OnBackPressedCallback(初始启用状态) {
        override fun handleOnBackPressed() { 内容() }
    })

//====================================================================================
/**
 *  注册返回键按下事件按两次返回桌面
 * @author dxyc
 */
fun ComponentActivity.注册返回键按下事件按两次返回桌面() =
    this.注册返回键按下事件(true){ 按两次返回桌面() }