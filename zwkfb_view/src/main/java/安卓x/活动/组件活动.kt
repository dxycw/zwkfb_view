package 安卓x.活动

import android.annotation.SuppressLint
import android.app.ComponentCaller
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.ComponentActivity

/**
 * 创建时间：2025年11月21日
 *
 * 描述：组件活动
 *
 * 版本：0.0.9
 * @author dxyc
 */
open class 组件活动 : ComponentActivity {
    constructor() : super()
    constructor(contentLayoutId: Int) : super(contentLayoutId)

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        创建回调(savedInstanceState)
    }

    @SuppressLint("MissingSuperCall")
    override fun onResume() {
        恢复回调()
    }

    @SuppressLint("MissingSuperCall")
    override fun onPause() {
        暂停回调()
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        销毁回调()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return 按键按下回调(keyCode, event)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, caller: ComponentCaller) {
        活动结果回调(requestCode, resultCode, data,caller)
    }

    @SuppressLint("MissingSuperCall")
    override fun onConfigurationChanged(newConfig: Configuration) {
        配置改变回调(newConfig)
    }


    /**
     * 创建回调
     *
     * 版本：0.1.3
     */
    open fun 创建回调(保存实例状态: Bundle?) {
        super.onCreate(保存实例状态)
    }
    /**
     * 恢复回调
     *
     * 版本：0.1.3
     */
    open fun 恢复回调() {
        super.onResume()
    }
    /**
     * 暂停回调
     *
     * 版本：0.1.3
     */
    open fun 暂停回调() {
        super.onPause()
    }

    /**
     * 销毁回调
     *
     * 版本：0.1.3
     */
    open fun 销毁回调() {
        super.onDestroy()
    }
    /**
     * 按键按下回调
     *
     * 版本：0.1.3
     */
    open fun 按键按下回调(按键码: Int, 按键事件: KeyEvent?): Boolean {
        return super.onKeyDown(按键码,按键事件)
    }
    /**
     * 活动结果回调
     *
     * 版本：0.1.3
     */
    @SuppressLint("NewApi")
    open fun 活动结果回调(请求标识: Int, 结果状态: Int, 数据: Intent?, caller: ComponentCaller) {
        super.onActivityResult(请求标识, 结果状态, 数据, caller)
    }
    /**
     * 配置改变回调
     *
     * 版本：0.1.3
     */
    open fun 配置改变回调(配置信息: Configuration) {
        super.onConfigurationChanged(配置信息)
    }

}