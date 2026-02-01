package 安卓.网页工具

import android.webkit.WebSettings
import 自定义.系统类.系统类.获取手机型号
import 自定义.系统类.系统类.获取手机系统版本号


/**
 * 创建时间：2025年11月26日.
 *
 * 描述：网页配置
 *
 * 版本：0.1.1
 * @author dxyc
 */
abstract class 网页配置 : WebSettings {
    constructor() : super()

    object Android {
        var 夸克UA: String =
            "Mozilla/5.0 (Linux; U; Android " + 获取手机系统版本号() + "; zh-CN; " + 获取手机型号() + " Build/AP3A.240905.015.A1) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/123.0.6312.80 Quark/7.5.1.691 Mobile Safari/537.36"
        var EdgUA: String =
            "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Mobile Safari/537.36 EdgA/131.0.0.0"
        var 百度UA: String =
            "Mozilla/5.0 (Linux; Android " + 获取手机系统版本号() + "; " + 获取手机型号() + " Build/AP3A.240905.015.A1; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/97.0.4692.98 Mobile Safari/537.36 T7/13.75 SP-engine/2.81.0 matrixstyle/0 lite baiduboxapp/6.43.0.11 (Baidu; P1 15) NABar/1.0"
    }

    class IOS

    class MacOS

    object Windows {
        var 夸克UA: String =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36 QuarkPC/1.9.5.161"
        var EdgUA: String =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/132.0.0.0 Safari/537.36 Edg/132.0.0.0"
    }

}

//======================================================================
//======================================================================



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

