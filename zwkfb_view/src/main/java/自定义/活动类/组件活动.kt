package 自定义.活动类

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

//====================================================================================

/**
 * 模式：注册返回键按下事件
 * @param 生命周期 生命周期所有者
 * @param 按返回键回调 按返回键回调
 */
fun ComponentActivity.注册返回键按下事件(生命周期: LifecycleOwner, 按返回键回调: OnBackPressedCallback)
        = this.onBackPressedDispatcher.addCallback(owner = 生命周期, onBackPressedCallback = 按返回键回调)

//====================================================================================
/**
 * 模式：注册返回键按下事件
 * @param 初始启用状态 初始启用状态
 * @param 内容 布局代码
 */
fun ComponentActivity.注册返回键按下事件(初始启用状态: Boolean = true, 内容: () -> Unit = {})
        = this.onBackPressedDispatcher.addCallback(owner = this,
    onBackPressedCallback = object : OnBackPressedCallback(初始启用状态) {
        override fun handleOnBackPressed() { 内容() }
    })

//====================================================================================

/**
 * 描述：注册返回键按下事件按两次返回桌面
 * @param 信息 信息
 */
fun ComponentActivity.注册返回键按下事件按两次返回桌面(信息: CharSequence = "再按一次返回桌面") =
    this.注册返回键按下事件(true){ 按两次返回桌面(信息) }

