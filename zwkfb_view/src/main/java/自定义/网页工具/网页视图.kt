package 自定义.网页工具

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.Rect
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.CookieManager
import android.webkit.SslErrorHandler
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.widget.FrameLayout
import 安卓.网页工具.下载监听器
import 安卓.网页工具.取浏览器配置
import 安卓.网页工具.可后退
import 安卓.网页工具.后退
import 安卓.网页工具.网页浏览器客户端
import 安卓.网页工具.网页视图客户端
import 安卓.网页工具.网页配置
import 安卓.网页工具.置下载监听事件
import 安卓.视图.置按键回调监听事件
import 自定义.主题类.主题类
import 自定义.对话框类.浏览器下载对话框
import 自定义.状态栏类.状态栏沉浸式
import 自定义.状态栏类.状态栏沉浸式类
import 自定义.系统类.是否为平板


/**
 * 描述：WebView的 “置下载监听事件()”
 * @param 事件 事件
 */
fun WebView.置下载监听事件(事件: 下载监听器) =
    this.setDownloadListener { url, userAgent, contentDisposition, mimetype, contentLength ->
        事件.开始下载回调(url, userAgent, contentDisposition, mimetype,contentLength)
    }

fun WebView.浏览器视频是否全屏(): Boolean {
    return 浏览器视频是否全屏
}

//================================================================================




@SuppressLint("SetJavaScriptEnabled")
fun WebView.默认配置(上下文: Activity, 网页第三方链接头: ArrayList<String?>?) {
    var 网页链接头: ArrayList<String>? = null
    var 网页视频控件: View? = null
    var 网页视频回调: WebChromeClient.CustomViewCallback? = null
    置网页跳转拦截事件(object : 网页跳转拦截事件请求{
        override fun 网页跳转拦截(浏览器: WebView?, 请求: WebResourceRequest?): Boolean {
            val url = 请求!!.url.toString()
            网页链接头 = arrayListOf("snssdk1128://", "baiduboxapp://", "baiduboxlite://", "baiduhaokan://",
                "market://", "bilibili://", "wvhzpj://", "freereader://", "mttbrowser://", "baiduhaokan://", "sohunews://")
            // 检查URL是否以http或https开头
            for (值 in 网页链接头) {
                if (url.startsWith(值)) {
                    try {
                        //判断是否有应用，如果有运行此代码
                        // 上面的参数中，url对应文件下载地址，mimetype对应下载文件的MIME类型
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));//创建 Intent
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 设置启动模式
//                    上下文.startActivity(intent);// 启动 Intent
                        return true
                    } catch (e: Exception) { // 捕获异常
                        //判断是否有应用，如果没有运行此代码

//                    if (!url.startsWith("market://") || !url.startsWith("freereader://")){
//                        new 对话框.构建器(上下文)
//                                .置标题("提示").置内容("是否打开外部应用")
//                                .setPositiveButton("确定", (dialog, which) -> {
//                                    //if (!公用设置.打开应用商店(上下文)){
//                                    //if (url.startsWith("baiduhaokan://")){
//                                    //    view.loadUrl("http://xbox.m.baidu.com/mo/home");
//                                    //}//else if (url.startsWith("wvhzpj://") ){
//                                    //    view.loadUrl("https://www.csdn.net/apps/download/");
//                                    //}
//
//                                    //Toast.makeText(上下文, String.valueOf(公用设置.是否是下载链接(url)), Toast.LENGTH_SHORT).show();
//                                    // }
//                                })
//                                .setPositiveButton("取消", (dialog, which) -> {
//                                    dialog.dismiss();
//                                })
//                                .show();
//                    }

                        return true
                    }
                } else {
                    return false
                }
            }
            return false
        }
    })
    置接收SSL错误事件(object : 接收SSL错误事件{
        override fun 接收SSL错误(浏览器: WebView?, 线程: SslErrorHandler?, 错误: SslError?) {
            线程!!.proceed()
        }
    })
    置显示自定义视图事件(object : 显示自定义视图事件{
        override fun 显示自定义视图(视图: View?, 回调: WebChromeClient.CustomViewCallback?) {
            if (网页视频控件 != null) {
                回调!!.onCustomViewHidden() // 隐藏自定义视图
                return
            }
            网页视频控件 = 视图
            网页视频回调 = 回调
            val decor = 上下文.window.decorView as FrameLayout //获取activity的根布局
            decor.addView(视图, FrameLayout.LayoutParams(-1, -1))

            上下文.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) //横屏
            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().状态栏字体图标自动深色模式(!主题类.是否是深色模式(上下文))
                .导航栏图标自动深色模式(!主题类.是否是深色模式(上下文)).刷新()
            状态栏沉浸式类.隐藏状态栏导航栏(上下文)
        }
    })
    置隐藏自定义视图事件(object : 隐藏自定义视图事件 {
        @SuppressLint("SourceLockedOrientationActivity")
        override fun 隐藏自定义视图() {
            if (网页视频控件 == null) {
                return
            }

            val decor = 上下文.window.decorView as FrameLayout //获取activity的根布局
            decor.removeView(网页视频控件)
            网页视频控件 = null
            网页视频回调!!.onCustomViewHidden() //隐藏自定义视图
            网页视频回调 = null

            上下文.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) //默认
            状态栏沉浸式.初始化沉浸式(上下文).状态栏导航栏透明().状态栏字体图标自动深色模式(!主题类.是否是深色模式(上下文))
                .导航栏图标自动深色模式(!主题类.是否是深色模式(上下文)).刷新()

            状态栏沉浸式类.显示状态栏导航栏(上下文)
        }
    })
    置按键回调监听事件(View.OnKeyListener { v, 键值, 事件 ->
        if (键值 == KeyEvent.KEYCODE_BACK && this.可后退()) {
            this.后退()
            return@OnKeyListener true
        }
        false
    })
    置下载监听事件(object :下载监听器 {
        override fun 开始下载回调(
            url: String?,
            userAgent: String?,
            contentDisposition: String?,
            mimetype: String?,
            contentLength: Long,
        ) {
//            网址: String?, 用户代理: String?, 内容处理: String?, 文件类型: String?, 内容长度: Long
            super.开始下载回调(url, userAgent, contentDisposition, mimetype, contentLength)
            浏览器下载对话框(上下文, url, userAgent!!, contentDisposition!!, mimetype!!)
        }

    })

    val 网页设置 = this.取浏览器配置()
    网页设置.javaScriptEnabled = true //启用 JavaScript 支持。
    // 设置User-Agent
    网页设置.setUserAgentString(if (上下文.是否为平板()) 网页配置.Windows.夸克UA else 网页配置.Android.夸克UA)
    网页设置.useWideViewPort = true //启用支持自定义窗口。
    网页设置.loadWithOverviewMode = true //启用支持内容大小。
    网页设置.domStorageEnabled = true //启用 DOM 存储。
    网页设置.allowFileAccess = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R //启用文件访问。
    网页设置.setSupportZoom(true) //启用缩放。
    网页设置.builtInZoomControls = true //启用缩放控制。
    网页设置.displayZoomControls = false //禁用缩放控制。
    网页设置.databaseEnabled = true //启用数据库。
    网页设置.mixedContentMode = WebSettings.LOAD_CACHE_ONLY //混合内容
    网页设置.javaScriptCanOpenWindowsAutomatically = true //启用 JavaScript 自动打开窗口。
    网页设置.defaultTextEncodingName = "utf-8" //设置编码格式。
    网页设置.cacheMode = WebSettings.LOAD_NO_CACHE //缓存模式。
    网页设置.pluginState = WebSettings.PluginState.ON //启用插件。
    网页设置.savePassword = true //保存密码。
    网页设置.saveFormData = true //保存表单数据。
    网页设置.layoutAlgorithm = WebSettings.LayoutAlgorithm.NORMAL //NARROW_COLUMNS
    网页设置.loadsImagesAutomatically = true //启用图片。
    网页设置.setGeolocationDatabasePath(上下文.getDir("database", 0).path) //启用定位数据库
    网页设置.setGeolocationEnabled(true) //启用定位
    网页设置.mediaPlaybackRequiresUserGesture = true //启用视频播放时需要用户手势
    网页设置.allowFileAccessFromFileURLs = false //允许从文件URL访问文件
    网页设置.allowUniversalAccessFromFileURLs = Build.VERSION.SDK_INT < 30 //允许从文件URL访问文件
    //这个一定要设置为false 否则会弹出权限请求框
    网页设置.setSupportMultipleWindows(false) //启用多窗口

    val instance = CookieManager.getInstance() //启用Cookie
    instance.setAcceptCookie(true) //启用Cookie
    instance.setAcceptThirdPartyCookies(this, true) //启用第三方Cookie
    可用Cookie() //启用Cookie
}

private fun WebView.可用Cookie() {
    val instance = CookieManager.getInstance()
    instance.setAcceptCookie(true)
    instance.setAcceptThirdPartyCookies(this, true)
}


fun WebView.修复浏览器弹出键盘输入框位置的Bug(上下文: Activity, 浏览器底部导航栏: View?, 浏览器进度条: View?) {
    // 设置 Activity 的软键盘模式为 adjustResize
    上下文.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    // 监听布局变化
    val activityRootView = 上下文.findViewById<View>(android.R.id.content)
    activityRootView.viewTreeObserver.addOnGlobalLayoutListener {
        val r = Rect()
        activityRootView.getWindowVisibleDisplayFrame(r) // 获取当前窗口可视区域
        val screenHeight = activityRootView.rootView.height
        val keyboardHeight = screenHeight - r.bottom // 键盘高度
        if (keyboardHeight > 100) {
            // 键盘弹出，增加 WebView 的底部内边距
            this.setPadding(
                0, 0, 0, (keyboardHeight
                        - (浏览器底部导航栏?.height ?: 0)
                        - 状态栏沉浸式类.用资源文件获取导航栏高度(上下文)
                        - (浏览器进度条?.height ?: 0))
            )
        } else {
            // 键盘关闭，重置 WebView 的底部内边距
            this.setPadding(0, 0, 0, 0)
        }
    }
}

////================================================================================
//
//private var 网页视图客户端: 网页视图客户端? = null
//private var 网页浏览器客户端: 网页浏览器客户端? = null

//================================================================================

private var 网页跳转拦截事件网址1: 网页跳转拦截事件网址? = null
private var 网页跳转拦截事件请求1: 网页跳转拦截事件请求? = null

private var 开始加载网页事件1: 开始加载网页事件? = null
private var 加载网页完成事件1: 加载网页完成事件? = null

private var 接收SSL错误事件1: 接收SSL错误事件? = null

private var 加载网页错误事件1: 加载网页错误事件? = null


//================================================================================

/**
 * 用于拦截网页跳转（注意：置网页跳转拦截事件(网页跳转拦截事件网址 事件)和置网页跳转拦截事件(网页跳转拦截事件请求 事件)两个方法只能使用一个）
 * @param 事件 网页跳转拦截事件网址
 */
fun WebView.置网页跳转拦截事件(事件: 网页跳转拦截事件网址?) {
    网页跳转拦截事件网址1 = 事件
    网页视图事件()
}

/**
 * 用于拦截网页跳转（注意：置网页跳转拦截事件(网页跳转拦截事件请求 事件)和置网页跳转拦截事件(网页跳转拦截事件请求 事件)两个方法只能使用一个）
 * @param 事件 网页跳转拦截事件请求
 */
fun WebView.置网页跳转拦截事件(事件: 网页跳转拦截事件请求?) {
    网页跳转拦截事件请求1 = 事件
    网页视图事件()
}

fun WebView.置开始加载网页事件(事件: 开始加载网页事件?) {
    开始加载网页事件1 = 事件
    网页视图事件()
}

fun WebView.置加载网页完成事件(事件: 加载网页完成事件?) {
    加载网页完成事件1 = 事件
    网页视图事件()
}

fun WebView.置接收SSL错误事件(事件: 接收SSL错误事件?) {
    接收SSL错误事件1 = 事件
    网页视图事件()
}

fun WebView.置加载网页错误事件(事件: 加载网页错误事件?) {
    加载网页错误事件1 = 事件
    网页视图事件()
}

//================================================================================

interface 网页跳转拦截事件网址 {
    fun 网页跳转拦截(浏览器: WebView?, 网址: String?): Boolean
}

interface 网页跳转拦截事件请求 {
    fun 网页跳转拦截(浏览器: WebView?, 请求: WebResourceRequest?): Boolean
}

interface 开始加载网页事件 {
    fun 开始加载网页(浏览器: WebView?, 网址: String?, 图标: Bitmap?)
}

interface 加载网页完成事件 {
    fun 加载网页完成(浏览器: WebView?, 网址: String?)
}

interface 接收SSL错误事件 {
    fun 接收SSL错误(浏览器: WebView?, 线程: SslErrorHandler?, 错误: SslError?)
}

interface 加载网页错误事件 {
    fun 加载网页错误(浏览器: WebView?, 错误代码: Int, 错误描述: String?, 网页地址: String?)
}

//================================================================================

private var 网页进度改变事件1: 网页进度改变事件? = null
private var 接收网页标题事件1: 接收网页标题事件? = null
private var 接收网页图标事件1: 接收网页图标事件? = null
private var 接收触摸图标网址事件1: 接收触摸图标网址事件? = null

private var 显示自定义视图事件1: 显示自定义视图事件? = null
private var 隐藏自定义视图事件1: 隐藏自定义视图事件? = null


private var 显示文件选择器事件1: 显示文件选择器事件? = null

//================================================================================
private var 浏览器视频是否全屏 = false
//================================================================================

fun WebView.置网页进度事件(事件: 网页进度改变事件?) {
    网页进度改变事件1 = 事件
    网页视图事件()
}

fun WebView.置接收到标题事件(事件: 接收网页标题事件?) {
    接收网页标题事件1 = 事件
    网页视图事件()
}

fun WebView.置接收到图标事件(事件: 接收网页图标事件?) {
    接收网页图标事件1 = 事件
    网页视图事件()
}

fun WebView.置接收到触摸图标网址事件(事件: 接收触摸图标网址事件?) {
    接收触摸图标网址事件1 = 事件
    网页视图事件()
}

fun WebView.置显示自定义视图事件(事件: 显示自定义视图事件?) {
    显示自定义视图事件1 = 事件
    网页视图事件()
}

fun WebView.置隐藏自定义视图事件(事件: 隐藏自定义视图事件?) {
    隐藏自定义视图事件1 = 事件
    网页视图事件()
}


fun WebView.置显示文件选择器事件(事件: 显示文件选择器事件?) {
    显示文件选择器事件1 = 事件
    网页视图事件()
}

//================================================================================

interface 网页进度改变事件 {
    fun 网页进度改变(浏览器: WebView?, 进度: Int)
}

interface 接收网页标题事件 {
    fun 接收标题(浏览器: WebView?, 标题: String?)
}

interface 接收网页图标事件 {
    fun 接收到图标(浏览器: WebView?, 图标: Bitmap?)
}

interface 接收触摸图标网址事件 {
    fun 接收触摸图标网址(浏览器: WebView?, 网址: String?, 预合成: Boolean)
}

interface 显示自定义视图事件 {
    fun 显示自定义视图(视图: View?, 回调: WebChromeClient.CustomViewCallback?)
}

interface 隐藏自定义视图事件 {
    fun 隐藏自定义视图()
}


interface 显示文件选择器事件 {
    fun 显示文件选择器(浏览器: WebView?, filePathCallback: ValueCallback<Array<Uri?>?>?, 文件选择器参数: WebChromeClient.FileChooserParams?): Boolean
}

//================================================================================

@SuppressLint("NewApi")
private fun WebView.网页视图事件(){
    网页视图客户端 = object : 网页视图客户端() {
        @Deprecated("Deprecated in Java")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (网页跳转拦截事件网址1 != null) {
                return 网页跳转拦截事件网址1!!.网页跳转拦截(view, url)
            }
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            if (网页跳转拦截事件请求1 != null) {
                return 网页跳转拦截事件请求1!!.网页跳转拦截(view, request)
            }
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            if (开始加载网页事件1 != null) {
                开始加载网页事件1!!.开始加载网页(view, url, favicon)
            }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            if (加载网页完成事件1 != null) {
                加载网页完成事件1!!.加载网页完成(view, url)
            }
        }

        override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
            super.onReceivedSslError(view, handler, error)
            if (接收SSL错误事件1 != null) {
                接收SSL错误事件1!!.接收SSL错误(view, handler, error)
            }
        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            if (加载网页错误事件1 != null) {
                加载网页错误事件1!!.加载网页错误(view, errorCode, description, failingUrl)
            }
        }

    }
    this.setWebViewClient(网页视图客户端)
    网页浏览器客户端 = object : 网页浏览器客户端() {

        override fun onHideCustomView() {
            super.onHideCustomView()
            if (隐藏自定义视图事件1 != null) {
                隐藏自定义视图事件1!!.隐藏自定义视图()
            }
            浏览器视频是否全屏 = false
        }

        override fun onProgressChanged(webView: WebView?, newProgress: Int) {
            super.onProgressChanged(webView, newProgress)
            if (网页进度改变事件1 != null) {
                网页进度改变事件1!!.网页进度改变(webView, newProgress)
            }
        }

        override fun onReceivedIcon(webView: WebView?, icon: Bitmap?) {
            super.onReceivedIcon(webView, icon)
            if (接收网页图标事件1 != null) {
                接收网页图标事件1!!.接收到图标(webView, icon)
            }
        }

        override fun onReceivedTouchIconUrl(webView: WebView?, url: String?, precomposed: Boolean) {
            super.onReceivedTouchIconUrl(webView, url, precomposed)
            if (接收触摸图标网址事件1 != null) {
                接收触摸图标网址事件1!!.接收触摸图标网址(webView, url, precomposed)
            }
        }

        override fun onReceivedTitle(webView: WebView?, title: String?) {
            super.onReceivedTitle(webView, title)
            if (接收网页标题事件1 != null) {
                接收网页标题事件1!!.接收标题(webView, title)
            }
        }

        override fun onShowCustomView(view: View?, customViewCallback: CustomViewCallback?) {
            super.onShowCustomView(view, customViewCallback)
            // 全屏视频播放时ActionBar消失
            if (显示自定义视图事件1 != null) {
                显示自定义视图事件1!!.显示自定义视图(view, customViewCallback)
            }
            浏览器视频是否全屏 = true
        }


        override fun onShowFileChooser(webView: WebView?, valueCallback: ValueCallback<Array<Uri?>?>?,
            fileChooserParams: FileChooserParams?): Boolean {
            if (显示文件选择器事件1 != null) {
                return 显示文件选择器事件1!!.显示文件选择器(webView, valueCallback, fileChooserParams)
            }
            return super.onShowFileChooser(webView, valueCallback, fileChooserParams)
        }
    }
    this.setWebChromeClient(网页浏览器客户端)
}