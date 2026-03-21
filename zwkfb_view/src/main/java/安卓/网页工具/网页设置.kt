package 安卓.网页工具

import android.webkit.WebSettings
import 安卓.操作系统.构建



/**
 * 描述：置是否启用JavaScript
 * @param javaScript启动 是否启用JavaScript
 * @return 是否启用JavaScript
 */
var WebSettings.javaScript启动: Boolean
    get() = this.javaScriptEnabled
    set(启动) { this.javaScriptEnabled = 启动 }
/**
 * 描述：取是否启用JavaScript
 * @return 是否启用JavaScript
 */
fun WebSettings.取javaScript启动(): Boolean = this.getJavaScriptEnabled()
/**
 * 描述：置是否启用JavaScript
 * @param 启动 是否启用JavaScript
 */
fun WebSettings.置javaScript启动(启动: Boolean) { this.setJavaScriptEnabled(启动) }

//======================================================================

