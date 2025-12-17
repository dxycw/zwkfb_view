package 安卓.网页工具

import android.webkit.WebSettings


/**
 * 创建时间：2025年11月26日.
 *
 * 描述：网页配置
 *
 * 版本：0.1.1
 * @author dxyc
 */
//class 网页配置 : WebSettings {
//    constructor() : super()
//}


//======================================================================

/**
 * 版本：0.1.1
 *
 * 描述：置是否启用JavaScript
 * @param 启动 是否启用JavaScript
 * @return 是否启用JavaScript
 */
var WebSettings.javaScript启动: Boolean
    get() = this.javaScriptEnabled
    set(启动) { this.javaScriptEnabled = 启动 }
/**
 * 版本：0.1.1
 *
 * 描述：取是否启用JavaScript
 * @return 是否启用JavaScript
 */
fun WebSettings.取javaScript启动(): Boolean = this.getJavaScriptEnabled()
/**
 * 版本：0.1.1
 *
 * 描述：置是否启用JavaScript
 * @param 启动 是否启用JavaScript
 */
fun WebSettings.置javaScript启动(启动: Boolean) { this.setJavaScriptEnabled(启动) }

//======================================================================

