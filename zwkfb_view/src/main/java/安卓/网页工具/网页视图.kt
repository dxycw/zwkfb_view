@file:Suppress("DEPRECATION")

package 安卓.网页工具

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageInfo
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Picture
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.net.Uri
import android.net.http.SslCertificate
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.print.PrintDocumentAdapter
import android.util.AttributeSet
import android.util.LongSparseArray
import android.util.SparseArray
import android.view.DragEvent
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.PointerIcon
import android.view.View
import android.view.ViewGroup
import android.view.ViewStructure
import android.view.WindowInsets
import android.view.accessibility.AccessibilityNodeProvider
import android.view.autofill.AutofillId
import android.view.autofill.AutofillValue
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.textclassifier.TextClassifier
import android.view.translation.TranslationCapability
import android.view.translation.ViewTranslationRequest
import android.view.translation.ViewTranslationResponse
import android.webkit.DownloadListener
import android.webkit.ValueCallback
import android.webkit.WebBackForwardList
import android.webkit.WebChromeClient
import android.webkit.WebMessage
import android.webkit.WebMessagePort
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebViewRenderProcess
import android.webkit.WebViewRenderProcessClient
import androidx.annotation.RequiresApi
import java.util.concurrent.Executor
import java.util.function.Consumer

/**
 * 创建时间：2025年12月16日.

 * 版本：0.2.0
 * @author dxyc
 */
open class 网页视图 : WebView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, privateBrowsing: Boolean,
    ) : super(context, attrs, defStyleAttr, privateBrowsing)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    /**
     * 描述："网页视图传输"类
     */
    open inner class 网页视图传输: WebViewTransport {
        constructor() : super()

        /**
         * 描述："取网页视图"函数
         */
        override fun getWebView(): WebView? {
            return 取网页视图()
        }

        /**
         * 描述："置网页视图"函数
         * @param webview 网页视图
         */
        override fun setWebView(webview: WebView?) {
            置网页视图(webview)
        }

        /**
         * 描述："取网页视图"函数
         */
        fun 取网页视图(): WebView? = super.getWebView()
        /**
         * 描述："置网页视图"函数
         * @param 网页视图 网页视图
         */
        fun 置网页视图(网页视图: WebView?) = super.setWebView(网页视图)

    }
    /**
     * 描述："查找监听器"接口
     */
    interface 查找监听器 : FindListener{
        /**
         * 描述："收到查找结果"函数
         * @param activeMatchOrdinal 活动匹配序数
         * @param numberOfMatches 匹配数量
         * @param isDoneCounting 是否完成计数
         */
        override fun onFindResultReceived(
            activeMatchOrdinal: Int, numberOfMatches: Int, isDoneCounting: Boolean
        ) {
            收到查找结果(activeMatchOrdinal, numberOfMatches, isDoneCounting)
        }
        /**
         * 描述："收到查找结果"函数
         * @param 活动匹配序数 活动匹配序数
         * @param 匹配数量 匹配数量
         * @param 是否完成计数 是否完成计数
         */
        fun 收到查找结果(活动匹配序数: Int, 匹配数量: Int, 是否完成计数: Boolean){}
    }

    /**
     * 描述："视觉状态回调"类
     */
    open class 视觉状态回调 : VisualStateCallback{
        constructor() : super()
        /**
         * 描述："完成回调"函数
         * @param requestId 请求ID
         */
        override fun onComplete(requestId: Long) {
            完成回调(requestId)
        }

        /**
         * 描述："完成回调"函数
         * @param 请求Id 请求ID
         */
        open fun 完成回调(请求Id : Long) {}

    }

    companion object{
        /**
         * 描述："清除客户端证书首选项()"函数
         * @param 被清除时 被清除时
         */
        fun 清除客户端证书首选项(被清除时: Runnable) =
            clearClientCertPreferences(被清除时)

        /**
         * 描述："启动安全浏览()"函数,仅支持Android 8.1及更高版本使用
         * @param 上下文 上下文
         * @param 回调 回调
         */
        @RequiresApi(Build.VERSION_CODES.O_MR1)
        fun 启动安全浏览(上下文: Context, 回调: ValueCallback<Boolean>) =
            startSafeBrowsing(上下文,回调)

        /**
         * 描述："置安全浏览白名单列表()"函数,仅支持Android 8.1及更高版本使用
         * @param 白名单 白名单
         * @param 回调 回调
         */
        @RequiresApi(Build.VERSION_CODES.O_MR1)
        fun 置安全浏览白名单列表(白名单: List<String>, 回调: ValueCallback<Boolean>) =
            setSafeBrowsingWhitelist(白名单, 回调)

        /**
         * 描述："取安全浏览隐私政策网址()"函数,仅支持Android 8.1及更高版本使用
         */
        @RequiresApi(Build.VERSION_CODES.O_MR1)
        fun 取安全浏览隐私政策网址(): Uri = getSafeBrowsingPrivacyPolicyUrl()

        /**
         * 描述："启用慢速整文档绘制()"函数
         */
        fun 启用慢速整文档绘制() = enableSlowWholeDocumentDraw()

        /**
         * 描述："置网页内容调试启用()"函数
         * @param 启用 启用
         */
        fun 置网页内容调试启用(启用: Boolean) = setWebContentsDebuggingEnabled(启用)

        /**
         * 描述：“置数据目录后缀()”,仅支持Android 9及更高版本使用"函数
         * @param 后缀 后缀
         */
        @RequiresApi(Build.VERSION_CODES.P)
        fun 置数据目录后缀(后缀: String) = setDataDirectorySuffix(后缀)

        /**
         * 描述："禁用网页视图()"函数,仅支持Android 9及更高版本使用
         */
        @RequiresApi(Build.VERSION_CODES.P)
        fun 禁用网页视图() = disableWebView()


        /**
         * 描述："渲染器_优先级_已放弃"常量,仅支持Android 8.0及更高版本使用
         * @return 渲染器优先级
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 渲染器_优先级_已放弃: Int = RENDERER_PRIORITY_WAIVED

        /**
         * 描述："渲染器_优先级_绑定"常量,仅支持Android 8.0及更高版本使用
         * @return 渲染器优先级
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 渲染器_优先级_绑定: Int = RENDERER_PRIORITY_BOUND

        /**
         * 描述："渲染器_优先级_重要"常量,仅支持Android 8.0及更高版本使用
         * @return 渲染器优先级
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 渲染器_优先级_重要: Int = RENDERER_PRIORITY_IMPORTANT

        /**
         * 描述："取网页视图类加载器()"函数,仅支持Android 9.0及更高版本使用
         * @return 类加载器 类加载器
         */
        @RequiresApi(Build.VERSION_CODES.P)
        fun 取网页视图类加载器():ClassLoader = getWebViewClassLoader()

        /**
         * 描述："取当前网页视图包()"函数,仅支持Android 8.0及更高版本使用
         * @return 包信息 包信息
         */
        @RequiresApi(Build.VERSION_CODES.O)
        fun 取当前网页视图包(): PackageInfo? = getCurrentWebViewPackage()

    }

    @Deprecated("Deprecated in Java")
    override fun onChildViewAdded(parent: View?, child: View?) {
        super.onChildViewAdded(parent, child)
    }

    @Deprecated("Deprecated in Java")
    override fun onChildViewRemoved(parent: View?, child: View?) {
        super.onChildViewRemoved(parent, child)
    }

    @Deprecated("Deprecated in Java")
    override fun onGlobalFocusChanged(oldFocus: View?, newFocus: View?) {
        super.onGlobalFocusChanged(oldFocus, newFocus)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun setOverScrollMode(mode: Int) {
        super.setOverScrollMode(mode)
    }

    override fun setScrollBarStyle(style: Int) {
        super.setScrollBarStyle(style)
    }

    override fun computeHorizontalScrollRange(): Int {
        return super.computeHorizontalScrollRange()
    }

    override fun computeHorizontalScrollOffset(): Int {
        return super.computeHorizontalScrollOffset()
    }

    override fun computeVerticalScrollRange(): Int {
        return super.computeVerticalScrollRange()
    }
    override fun computeVerticalScrollOffset(): Int {
        return super.computeVerticalScrollOffset()
    }

    override fun computeVerticalScrollExtent(): Int {
        return super.computeVerticalScrollExtent()
    }

    override fun computeScroll() {
        super.computeScroll()
    }

    override fun onHoverEvent(event: MotionEvent?): Boolean {
        return super.onHoverEvent(event)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return super.onTouchEvent(event)
    }

    override fun onGenericMotionEvent(event: MotionEvent?): Boolean {
        return super.onGenericMotionEvent(event)
    }

    override fun onTrackballEvent(event: MotionEvent?): Boolean {
        return super.onTrackballEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    override fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent?): Boolean {
        return super.onKeyMultiple(keyCode, repeatCount, event)
    }

    override fun getAccessibilityNodeProvider(): AccessibilityNodeProvider? {
        return super.getAccessibilityNodeProvider()
    }

    override fun shouldDelayChildPressedState(): Boolean {
        return super.shouldDelayChildPressedState()
    }

    override fun getAccessibilityClassName(): CharSequence? {
        return super.getAccessibilityClassName()
    }

    override fun onProvideVirtualStructure(structure: ViewStructure?) {
        super.onProvideVirtualStructure(structure)
    }


    override fun onProvideAutofillVirtualStructure(structure: ViewStructure?, flags: Int) {
        super.onProvideAutofillVirtualStructure(structure, flags)
    }

    override fun onProvideContentCaptureStructure(structure: ViewStructure, flags: Int) {
        super.onProvideContentCaptureStructure(structure, flags)
    }


    override fun autofill(values: SparseArray<AutofillValue?>) {
        super.autofill(values)
    }

    override fun isVisibleToUserForAutofill(virtualId: Int): Boolean {
        return super.isVisibleToUserForAutofill(virtualId)
    }

    override fun onCreateVirtualViewTranslationRequests(
        virtualIds: LongArray,
        supportedFormats: IntArray,
        requestsCollector: Consumer<ViewTranslationRequest?>
    ) {
        super.onCreateVirtualViewTranslationRequests(
            virtualIds,
            supportedFormats,
            requestsCollector
        )
    }

    override fun dispatchCreateViewTranslationRequest(
        viewIds: Map<AutofillId?, LongArray?>,
        supportedFormats: IntArray,
        capability: TranslationCapability?,
        requests: List<ViewTranslationRequest?>
    ) {
        super.dispatchCreateViewTranslationRequest(viewIds, supportedFormats, capability, requests)
    }

    override fun onVirtualViewTranslationResponses(response: LongSparseArray<ViewTranslationResponse?>) {
        super.onVirtualViewTranslationResponses(response)
    }

    override fun onOverScrolled(scrollX: Int, scrollY: Int, clampedX: Boolean, clampedY: Boolean) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY)
    }

    override fun onWindowVisibilityChanged(visibility: Int) {
        super.onWindowVisibilityChanged(visibility)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }

    override fun performLongClick(): Boolean {
        return super.performLongClick()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onCreateInputConnection(outAttrs: EditorInfo?): InputConnection? {
        return super.onCreateInputConnection(outAttrs)
    }

    override fun onDragEvent(event: DragEvent?): Boolean {
        return super.onDragEvent(event)
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
    }


    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
    }


    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)
    }


    override fun onSizeChanged(w: Int, h: Int, ow: Int, oh: Int) {
        super.onSizeChanged(w, h, ow, oh)
    }


    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        return super.dispatchKeyEvent(event)
    }

    override fun requestFocus(direction: Int, previouslyFocusedRect: Rect?): Boolean {
        return super.requestFocus(direction, previouslyFocusedRect)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }


    override fun requestChildRectangleOnScreen(
        child: View,
        rect: Rect?,
        immediate: Boolean
    ): Boolean {
        return super.requestChildRectangleOnScreen(child, rect, immediate)
    }

    override fun setBackground(background: Drawable?) {
        super.setBackground(background)
    }

    override fun setBackgroundResource(resId: Int) {
        super.setBackgroundResource(resId)
    }

    override fun setLayerType(layerType: Int, paint: Paint?) {
        super.setLayerType(layerType, paint)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
    }

    override fun onStartTemporaryDetach() {
        super.onStartTemporaryDetach()
    }


    override fun onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach()
    }

    override fun getHandler(): Handler? {
        return super.getHandler()
    }

    override fun findFocus(): View? {
        return super.findFocus()
    }

    override fun onCheckIsTextEditor(): Boolean {
        return super.onCheckIsTextEditor()
    }


    override fun onApplyWindowInsets(insets: WindowInsets?): WindowInsets? {
        return super.onApplyWindowInsets(insets)
    }

    override fun onResolvePointerIcon(event: MotionEvent, pointerIndex: Int): PointerIcon? {
        return super.onResolvePointerIcon(event, pointerIndex)
    }

}

//======================================================================
//======================================================================

/**
 * 描述：WebView.WebViewTransport的 “网页视图”
 * @param 网页视图 网页视图
 * @return 网页视图
 */
var WebView.WebViewTransport.网页视图 : WebView?
    get() = this.getWebView()
    set(值) {this.setWebView(值)}

/**
 * 描述：WebView.WebViewTransport的 “置网页视图()”
 * @param 网页视图 网页视图
 */
fun WebView.WebViewTransport.置网页视图(网页视图: WebView) =
    this.setWebView(网页视图)

/**
 * 描述：WebView.WebViewTransport的 “取网页视图()”
 * @return 网页视图
 */
fun WebView.WebViewTransport.取网页视图(): WebView? = this.getWebView()

//======================================================================

/**
 * 描述：WebView.FindListener的 “收到查找结果()”
 * @param 活动匹配序数 活动匹配序数
 * @param 匹配数量 匹配数量
 * @param 是否完成计数 是否完成计数
 */
fun WebView.FindListener.收到查找结果(活动匹配序数: Int, 匹配数量: Int, 是否完成计数: Boolean) =
    this.onFindResultReceived(活动匹配序数, 匹配数量, 是否完成计数)

//======================================================================

/**
 * 描述：WebView.VisualStateCallback的 “完成回调()”
 * @param 请求Id 请求Id
 */
fun WebView.VisualStateCallback.完成回调(请求Id : Long) = this.onComplete(请求Id)

//======================================================================
//======================================================================

/**
 * 描述：WebView的 “置水平滚动条覆盖()”
 * @param 覆盖 覆盖
 */
fun WebView.置水平滚动条覆盖(覆盖: Boolean) = this.setHorizontalScrollbarOverlay(覆盖)

/**
 * 描述：WebView的 “置垂直滚动条覆盖()”
 * @param 覆盖 覆盖
 */
fun WebView.置垂直滚动条覆盖(覆盖: Boolean) = this.setVerticalScrollbarOverlay(覆盖)

//======================================================================

/**
 * 描述：WebView的 “覆盖水平滚动条()”
 * @return 覆盖水平滚动条
 */
fun WebView.覆盖水平滚动条(): Boolean = this.overlayHorizontalScrollbar()

/**
 * 描述：WebView的 “覆盖垂直滚动条()”
 * @return 覆盖垂直滚动条
 */
fun WebView.覆盖垂直滚动条(): Boolean = this.overlayVerticalScrollbar()

//======================================================================

/**
 * 描述：WebView的 “取证书()”
 * @param 证书 证书
 * @return 取证书
 */
var WebView.证书: SslCertificate?
    get() = this.certificate
    set(值) {this.certificate = 值}

/**
 * 描述：WebView的 “取证书()”
 * @return 取证书
 */
fun WebView.取证书(): SslCertificate? = this.getCertificate()

/**
 * 描述：WebView的 “置证书()”
 * @param 证书 证书
 */
fun WebView.置证书(证书: SslCertificate?) = this.setCertificate(证书)

//======================================================================

/**
 * 描述：WebView的 “保存密码()”
 * @param 主机 主机
 * @param 用户名 用户名
 * @param 密码 密码
 */
fun WebView.保存密码(主机: String,用户名: String,密码: String) =
    this.savePassword(主机,用户名,密码)

//======================================================================

/**
 * 描述：WebView的 “置HTTP认证用户名和密码()”
 * @param 主机 主机
 * @param 领域 领域
 * @param 用户名 用户名
 * @param 密码 密码
 */
fun WebView.置HTTP认证用户名和密码(主机: String,领域: String,用户名: String,密码: String) =
    this.setHttpAuthUsernamePassword(主机,领域,用户名,密码)

/**
 * 描述：WebView的 “取HTTP认证用户名和密码()”
 * @param 主机 主机
 * @param 领域 领域
 * @return 取HTTP认证用户名和密码
 */
fun WebView.取HTTP认证用户名和密码(主机: String,领域: String): Array<String>? =
    this.getHttpAuthUsernamePassword(主机,领域)

//======================================================================

/**
 * 描述：WebView的 “摧毁()”
 */
fun WebView.摧毁() = this.destroy()

//======================================================================

/**
 * 描述：WebView的 “置网络可用()”
 * @param 网络连接 网络连接
 */
fun WebView.置网络可用(网络连接: Boolean) = this.setNetworkAvailable(网络连接)

//======================================================================

/**
 * 描述：WebView的 “保存状态()”
 * @param 输出状态 输出状态
 * @return 保存状态
 */
fun WebView.保存状态(输出状态: Bundle): WebBackForwardList? = this.saveState(输出状态)

//======================================================================

/**
 * 描述：WebView的 “恢复状态()”
 * @param 输入状态 输入状态
 * @return 恢复状态
 */
fun WebView.恢复状态(输入状态: Bundle):WebBackForwardList? = this.restoreState(输入状态)

//======================================================================

/**
 * 描述：WebView的 “加载网址()”
 * @param 网址 网址
 * @param 附加HTTP头 附加HTTP头
 */
fun WebView.加载网址(网址: String,附加HTTP头: Map<String, String>) =
    this.loadUrl(网址,附加HTTP头)

/**
 * 描述：WebView的 “加载网址()”
 * @param 网址 网址
 */
fun WebView.加载网址(网址: String) = this.loadUrl(网址)

//======================================================================

/**
 * 描述：WebView的 “请求网址()”
 * @param 网址 网址
 * @param 请求数据 请求数据
 */
fun WebView.请求网址(网址: String, 请求数据: ByteArray) = this.postUrl(网址, 请求数据)

//======================================================================

/**
 * 描述：WebView的 “加载数据()”
 * @param 数据 数据
 * @param 媒体类型 媒体类型
 * @param 编码 编码
 */
fun WebView.加载数据(数据: String, 媒体类型: String , 编码: String) =
    this.loadData(数据, 媒体类型, 编码)

//======================================================================

/**
 * 描述：WebView的 “加载数据基础网址()”
 * @param 基础网址 基础网址
 * @param 数据 数据
 * @param 编码 编码
 * @param 模式 模式
 * @param 历史网址 历史网址
 */
fun WebView.加载数据基础网址(基础网址: String,数据: String, 编码: String, 模式: String,历史网址: String) =
    this.loadDataWithBaseURL(基础网址, 数据, 编码, 模式, 历史网址)

//======================================================================

/**
 * 描述：WebView的 “执行JavaScript()”
 * @param 脚本 脚本
 * @param 结果回调 结果回调
 */
fun WebView.执行JavaScript(脚本: String, 结果回调: ValueCallback<String>) =
    this.evaluateJavascript(脚本, 结果回调)

//======================================================================

/**
 * 描述：WebView的 “保存网页存档()”
 * @param 文件名 文件名
 */
fun WebView.保存网页存档(文件名: String) =
    this.saveWebArchive(文件名)

/**
 * 描述：WebView的 “保存网页存档()”
 * @param 文件名 文件名
 * @param 自动命名 自动命名
 * @param 回调 回调
 */
fun WebView.保存网页存档(文件名: String,自动命名: Boolean, 回调: ValueCallback<String>) =
    this.saveWebArchive(文件名,自动命名, 回调)

//======================================================================

/**
 * 描述：WebView的 “停止加载()”
 */
fun WebView.停止加载() = this.stopLoading()

//======================================================================

/**
 * 描述：WebView的 “重载()”
 */
fun WebView.重载() = this.reload()

//======================================================================

/**
 * 描述：WebView的 “可后退()”
 * @return 可后退
 */
fun WebView.可后退(): Boolean = this.canGoBack()

/**
 * 描述：WebView的 “后退()”
 */
fun WebView.后退() = this.goBack()

//======================================================================

/**
 * 描述：WebView的 “可前进()”
 * @return 可前进
 */
fun WebView.可前进(): Boolean = this.canGoForward()

/**
 * 描述：WebView的 “前进()”
 */
fun WebView.前进() = this.goForward()

//======================================================================

/**
 * 描述：WebView的 “可后退或前进()”
 * @param 步数 步数
 * @return 可后退或前进
 */
fun WebView.可后退或前进(步数: Int): Boolean = this.canGoBackOrForward(步数)

/**
 * 描述：WebView的 “后退或前进()”
 * @param 步数 步
 */
fun WebView.后退或前进(步数: Int) = this.goBackOrForward(步数)

//======================================================================
/**
 * 描述：WebView的 “是否启用了隐私浏览()”
 * @return 是否启用了隐私浏览
 */
fun WebView.是否启用了隐私浏览(): Boolean = this.isPrivateBrowsingEnabled()

//======================================================================

/**
 * 描述：WebView的 “向上翻页()”
 * @param 上 是否向上翻页
 * @return 向上翻页
 */
fun WebView.向上翻页(上: Boolean): Boolean = this.pageUp(上)

/**
 * 描述：WebView的 “向下翻页()”
 * @param 下 是否向下翻页
 * @return 向下翻页
 */
fun WebView.向下翻页(下: Boolean): Boolean = this.pageDown(下)

//======================================================================

/**
 * 描述：WebView的 “提交视觉状态回调()”
 * @param 请求Id 请求Id
 * @param 回调 回调
 */
fun WebView.提交视觉状态回调(请求Id: Long,回调: WebView.VisualStateCallback) =
    this.postVisualStateCallback(请求Id,回调)

//======================================================================

/**
 * 描述：WebView的 “清除视图()”
 */
fun WebView.清除视图() = this.clearView()

//======================================================================

/**
 * 描述：WebView的 “捕获照片()”
 * @return 捕获照片
 */
fun WebView.捕获照片(): Picture = this.capturePicture()

//======================================================================

 /**
 * 描述：WebView的 “创建打印文档适配器()”
 * @return 打印文档适配器
 */
fun WebView.创建打印文档适配器(): PrintDocumentAdapter =
    this.createPrintDocumentAdapter()

/**
 * 描述：WebView的 “创建打印文档适配器()”
 * @param 文档名称 文档名称
 * @return 打印文档适配器
 */
fun WebView.创建打印文档适配器(文档名称: String): PrintDocumentAdapter =
    this.createPrintDocumentAdapter(文档名称)

//======================================================================

/**
 * 描述：WebView的 “取缩放()”
 * @return 缩放
 */
fun WebView.取缩放(): Float = this.getScale()

//======================================================================

/**
 * 描述：WebView的 “置初始缩放()”
 * @param 百分比缩放 百分比缩放
 */
fun WebView.置初始缩放(百分比缩放: Int) = this.setInitialScale(百分比缩放)

//======================================================================

/**
 * 描述：WebView的 “调用缩放选择器()”
 */
fun WebView.调用缩放选择器() = this.invokeZoomPicker()

//======================================================================
/**
 * 描述：WebView的 “取点击测试结果()”
 * @return 点击测试结果
 */
fun WebView.取点击测试结果(): WebView.HitTestResult = this.getHitTestResult()

//======================================================================

/**
 * 描述：WebView的 “请求焦点节点超链接()”
 * @param 超链接消息 超链接消息
 */
fun WebView.请求焦点节点超链接(超链接消息 : Message) =
    this.requestFocusNodeHref(超链接消息)

//======================================================================
/**
 * 描述：WebView的 “请求图像引用()”
 * @param 消息 消息
 */
fun WebView.请求图像引用(消息 : Message) = this.requestImageRef(消息)

//======================================================================
/**
 * 描述：WebView的 “取网址()”
 * @return 网址
 */
fun WebView.取网址(): String? = this.getUrl()

//======================================================================
/**
 * 描述：WebView的 “取原始网址()”
 * @return 原始网址
 */
fun WebView.取原始网址(): String? = this.getOriginalUrl()

//======================================================================
/**
 * 描述：WebView的 “取标题()”
 * @return 标题
 */
fun WebView.取标题(): String? = this.getTitle()

//======================================================================
/**
 * 描述：WebView的 “取网站图标()”
 * @return 网站图标
 */
fun WebView.取网站图标(): Bitmap? = this.getFavicon()

//======================================================================
/**
 * 描述：WebView的 “取进度()”
 * @return 进度
 */
fun WebView.取进度(): Int = this.getProgress()

//======================================================================

/**
 * 描述：WebView的 “取内容高度()”
 * @return 内容高度
 */
fun WebView.取内容高度(): Int = this.getContentHeight()

//======================================================================

/**
 * 描述：WebView的 “暂停定时器()”
 */
fun WebView.暂停定时器() = this.pauseTimers()

//======================================================================

/**
 * 描述：WebView的 “恢复定时器()”
 */
fun WebView.恢复定时器() = this.resumeTimers()

//======================================================================

/**
 * 描述：WebView的 “暂停回调()”
 */
fun WebView.暂停回调() = this.onPause()

//======================================================================
/**
 * 描述：WebView的 “恢复回调()”
 */
fun WebView.恢复回调() = this.onResume()

//======================================================================
/**
 * 描述：WebView的 “释放内存()”
 */
fun WebView.释放内存() = this.freeMemory()

//======================================================================
/**
 * 描述：WebView的 “清除表单数据()”
 */
fun WebView.清除表单数据() = this.clearFormData()

//======================================================================
/**
 * 描述：WebView的 “清除历史记录()”
 */
fun WebView.清除历史记录() = this.clearHistory()

//======================================================================
/**
 * 描述：WebView的 “清除SSL首选项()”
 */
fun WebView.清除SSL首选项() = this.clearSslPreferences()

//======================================================================
/**
 * 描述：WebView的 “复制前进后退列表()”
 * @return 前进后退列表
 */
fun WebView.复制前进后退列表():WebBackForwardList = this.copyBackForwardList()

//======================================================================
/**
 * 描述：WebView的 “置查找监听器()”
 * @param 监听器 查找监听器
 */
fun WebView.置查找监听器(监听器: WebView.FindListener?) = this.setFindListener(监听器)

//======================================================================
/**
 * 描述：WebView的 “查找下一个()”
 * @param 前进 是否查找下一个
 */
fun WebView.查找下一个(前进: Boolean) = this.findNext(前进)

//======================================================================
/**
 * 描述：WebView的 “查找全部()”
 * @param 查找 查找文本
 */
fun WebView.查找全部(查找: String) = this.findAll(查找)

//======================================================================
/**
 * 描述：WebView的 “异步查找全部()”
 * @param 查找 查找文本
 */
fun WebView.异步查找全部(查找: String) =
    this.findAllAsync(查找)

//======================================================================
/**
 * 描述：WebView的 “显示查找对话框()”
 * @param 文本 查找文本
 * @param 显示输入法 是否显示输入法
 * @return 是否成功显示查找对话框
 */
fun WebView.显示查找对话框(文本: String,显示输入法: Boolean): Boolean =
    this.showFindDialog(文本,显示输入法)

//======================================================================
/**
 * 描述：WebView的 “清除匹配()”
 */
fun WebView.清除匹配() = this.clearMatches()

//======================================================================
/**
 * 描述：WebView的 “文档是否包含图片()”
 * @param 响应 响应
 */
fun WebView.文档是否包含图片(响应: Message) = this.documentHasImages(响应)

//======================================================================

/**
 * 描述：WebView的 “网页视图客户端”,仅支持Android 8.0 (API 26) 及以上版本
 * @param 值 网页视图客户端
 * @return 网页视图客户端
 */
var WebView.网页视图客户端: WebViewClient
    @RequiresApi(Build.VERSION_CODES.O)
    get() = this.webViewClient
    set(值) {this.webViewClient = 值}

/**
 * 描述：WebView的 “置网页视图客户端()”
 * @param 事件 网页视图客户端
 */
fun WebView.置网页视图客户端(事件: WebViewClient)  = this.setWebViewClient(事件)

/**
 * 描述：WebView的 “取网页视图客户端()”,仅支持Android 8.0 (API 26) 及以上版本
 * @return 网页视图客户端
 */
@RequiresApi(Build.VERSION_CODES.O)
fun WebView.取网页视图客户端() : WebViewClient = this.getWebViewClient()

//======================================================================

/**
 * 描述：WebView的 “取网页视图渲染进程()”,仅支持Android 10.0 (API 29) 及以上版本
 * @return 网页视图渲染进程
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun WebView.取网页视图渲染进程(): WebViewRenderProcess?  = this.getWebViewRenderProcess()

//======================================================================

/**
 * 描述：WebView的 “置网页视图渲染进程客户端()”,仅支持Android 10.0 (API 29) 及以上版本
 * @param 执行器 执行器
 * @param 网页视图渲染进程客户端 网页视图渲染进程客户端
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun WebView.置网页视图渲染进程客户端(
    执行器: Executor,
    网页视图渲染进程客户端: WebViewRenderProcessClient
) =
    this.setWebViewRenderProcessClient(执行器,网页视图渲染进程客户端)

/**
 * 描述：WebView的 “置网页视图渲染进程客户端()”,仅支持Android 10.0 (API 29) 及以上版本
 * @param 网页视图渲染进程客户端 网页视图渲染进程客户端
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun WebView.置网页视图渲染进程客户端(
    网页视图渲染进程客户端: WebViewRenderProcessClient
) = this.setWebViewRenderProcessClient(网页视图渲染进程客户端)

//======================================================================

/**
 * 描述：WebView的 “取网页视图渲染进程客户端()”,仅支持Android 10.0 (API 29) 及以上版本
 * @return 网页视图渲染进程客户端
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun WebView.取网页视图渲染进程客户端(): WebViewRenderProcessClient? =
    this.getWebViewRenderProcessClient()

//======================================================================

/**
 * 描述：WebView的 “置下载监听事件()”
 * @param 事件 事件
 */
fun WebView.置下载监听事件(事件: DownloadListener) =
    this.setDownloadListener(事件)

//======================================================================

/**
 * 描述：WebView的 “网页浏览器客户端”,仅支持Android 8.0 (API 26) 及以上版本
 * @param 值 网页浏览器客户端
 * @return 网页浏览器客户端
 */
var WebView.网页浏览器客户端 : WebChromeClient?
    @RequiresApi(Build.VERSION_CODES.O)
    get() = this.webChromeClient
    set(值) {this.webChromeClient = 值}

/**
 * 描述：WebView的 “置网页浏览器客户端()”
 * @param 事件 网页浏览器客户端
 */
fun WebView.置网页浏览器客户端(事件: WebChromeClient)  =
    this.setWebChromeClient(事件)

/**
 * 描述：WebView的 “取网页浏览器客户端()”,仅支持Android 8.0 (API 26) 及以上版本
 * @return 网页浏览器客户端
 */
@RequiresApi(Build.VERSION_CODES.O)
fun WebView.取网页浏览器客户端() : WebChromeClient? =
    this.getWebChromeClient()

//======================================================================
/**
 * 描述：WebView的 “置截图监听器()”
 * @param 监听器 截图监听器
 */
fun WebView.置截图监听器(监听器: WebView.PictureListener) = this.setPictureListener(监听器)

//======================================================================

/**
 * 描述：WebView的 “添加Javascript接口()”
 * @param 对象 接口对象
 * @param 名称 名称
 */
@SuppressLint("JavascriptInterface")
fun WebView.添加Javascript接口(对象: Any, 名称: String) =
    this.addJavascriptInterface(对象, 名称)

//======================================================================
/**
 * 描述：WebView的 “移除Javascript接口()”
 * @param 名称 名称
 */
fun WebView.移除Javascript接口(名称: String) = this.removeJavascriptInterface(名称)

//======================================================================
/**
 * 描述：WebView的 “创建网页消息通道()”
 * @return 网页消息通道
 */
fun WebView.创建网页消息通道(): Array<WebMessagePort> = this.createWebMessageChannel()

//======================================================================
/**
 * 描述：WebView的 “提交网页消息()”
 * @param 消息 消息
 * @param 目标源 目标源
 */
fun WebView.提交网页消息(消息: WebMessage, 目标源: Uri) = this.postWebMessage(消息, 目标源)

//======================================================================

/**
 * 描述：WebView的 “浏览器配置”
 * @return 浏览器配置
 */
val WebView.浏览器配置: WebSettings
    get() = this.settings

/**
 * 描述：WebView的 “取浏览器配置()”
 * @return 浏览器配置
 */
fun WebView.取浏览器配置(): WebSettings = this.getSettings()

//======================================================================

/**
 * 描述：WebView的 “子视图被添加()”
 * @param 父视图 父视图
 * @param 子视图 子视图
 */
fun WebView.子视图被添加(父视图: View, 子视图: View) = this.onChildViewAdded(父视图, 子视图)

//======================================================================
/**
 * 描述：WebView的 “子视图被移除()”
 * @param 父视图 父视图
 * @param 子视图 子视图
 */
fun WebView.子视图被移除(父视图: View, 子视图: View) = this.onChildViewRemoved(父视图, 子视图)

//======================================================================
/**
 * 描述：WebView的 “全局焦点变化()”
 * @param 老焦点 老焦点
 * @param 新焦点 新焦点
 */
fun WebView.全局焦点变化(老焦点: View, 新焦点: View) = this.onGlobalFocusChanged(老焦点, 新焦点)

//======================================================================
/**
 * 描述：WebView的 “置将轨迹球映射为方向键()”
 * @param 置映射 是否将轨迹球映射为方向键
 */
fun WebView.置将轨迹球映射为方向键(置映射: Boolean) = this.setMapTrackballToArrowKeys(置映射)

//======================================================================

/**
 * 描述：WebView的 “惯性滚动()”
 * @param 视图x 视图x
 * @param 视图y 视图y
 */
fun WebView.惯性滚动(视图x: Int, 视图y: Int) = this.flingScroll(视图x, 视图y)

//======================================================================

/**
 * 描述：WebView的 “可放大()”
 * @return 是否可放大
 */
fun WebView.可放大(): Boolean = this.canZoomIn()

/**
 * 描述：WebView的 “可缩小()”
 * @return 是否可缩小
 */
fun WebView.可缩小(): Boolean = this.canZoomOut()

//======================================================================

 /**
 * 描述：WebView的 “倍数缩放()”
 * @param 缩放比例 缩放比例
 */
fun WebView.倍数缩放(缩放比例: Float) = this.zoomBy(缩放比例)

//======================================================================
/**
 * 描述：WebView的 “放大()”
 * @return 是否放大成功
 */
fun WebView.放大(): Boolean = this.zoomIn()

/**
 * 描述：WebView的 “缩小()”
 * @return 是否缩小成功
 */
fun WebView.缩小(): Boolean = this.zoomOut()

//======================================================================
/**
 * 描述：WebView的 “置渲染器优先级策略()”,仅支持Android 8.0及更高版本使用
 * @param 渲染器请求的优先级 渲染器请求的优先级
 * @param 不可见时放弃 不可见时放弃
 */
@RequiresApi(Build.VERSION_CODES.O)
fun WebView.置渲染器优先级策略(渲染器请求的优先级: Int, 不可见时放弃: Boolean) =
    this.setRendererPriorityPolicy(渲染器请求的优先级,不可见时放弃)

//======================================================================

/**
 * 描述：WebView的 “取渲染器请求的优先级()”,仅支持Android 8.0及更高版本使用
 */
@RequiresApi(Build.VERSION_CODES.O)
fun WebView.取渲染器请求的优先级(): Int = this.getRendererRequestedPriority()

//======================================================================

/**
 * 描述：WebView的 “取不可见时渲染优先级是否放弃()”,仅支持Android 8.0及更高版本使用
 */
@RequiresApi(Build.VERSION_CODES.O)
fun WebView.取不可见时渲染优先级是否放弃() = this.getRendererPriorityWaivedWhenNotVisible()

//======================================================================

/**
 * 描述：WebView的 “文本分类器”,仅支持Android 8.1及更高版本使用
 * @param 文本分类器 文本分类器
 * @return 文本分类器
 */
var WebView.文本分类器: TextClassifier
    @RequiresApi(Build.VERSION_CODES.O_MR1)
    get() = this.textClassifier
    @RequiresApi(Build.VERSION_CODES.O_MR1)
    set(文本分类器) = this.setTextClassifier(文本分类器)

/**
 * 描述：WebView的 “置文本分类器()”,仅支持Android 8.1及更高版本使用
 * @param 文本分类器 文本分类器
 */
@RequiresApi(Build.VERSION_CODES.O_MR1)
fun WebView.置文本分类器(文本分类器: TextClassifier) = this.setTextClassifier(文本分类器)

/**
 * 描述：WebView的 “取文本分类器()”,仅支持Android 8.1及更高版本使用
 */
@RequiresApi(Build.VERSION_CODES.O_MR1)
fun WebView.取文本分类器(): TextClassifier = this.getTextClassifier()

//======================================================================

/**
 * 描述：WebView的 “取网页视图循环器()”,仅支持Android 9.0及更高版本使用
 */
@RequiresApi(Build.VERSION_CODES.P)
fun WebView.取网页视图循环器(): Looper = this.getWebViewLooper()

//======================================================================
/**
 * 描述：WebView的 “置布局参数()”
 * @param 参数 布局参数
 */
fun WebView.置布局参数(参数: ViewGroup.LayoutParams) = this.setLayoutParams(参数)

//======================================================================
/**
 * 描述：WebView的 “置滚动超出模式()”
 * @param 模式 滚动超出模式
 */
fun WebView.置滚动超出模式(模式: Int) = this.setOverScrollMode(模式)

//======================================================================
/**
 * 描述：WebView的 “置滚动条样式()”
 * @param 样式 滚动条样式
 */
fun WebView.置滚动条样式(样式: Int) = this.setScrollBarStyle(样式)

//======================================================================

/**
 * 描述：WebView的 “计算滚动()”
 */
fun WebView.计算滚动() = this.computeScroll()

//======================================================================

/**
 * 描述：WebView的 “悬停事件处理()”
 * @param 事件 悬停事件
 * @return 是否处理成功
 */
fun WebView.悬停事件处理(事件: MotionEvent): Boolean = this.onHoverEvent(事件)

//======================================================================

/**
 * 描述：WebView的 “触摸事件处理()”
 * @param 事件 触摸事件
 * @return 是否处理成功
 */
fun WebView.触摸事件处理(事件: MotionEvent): Boolean = this.onTouchEvent(事件)

//======================================================================

/**
 * 描述：WebView的 “通用动作事件处理()”
 * @param 事件 通用动作事件
 * @return 是否处理成功
 */
fun WebView.通用动作事件处理(事件: MotionEvent): Boolean = this.onGenericMotionEvent(事件)

//======================================================================
/**
 * 描述：WebView的 “轨迹球事件处理()”
 * @param 事件 轨迹球事件
 * @return 是否处理成功
 */
fun WebView.轨迹球事件处理(事件: MotionEvent): Boolean = this.onTrackballEvent(事件)

//======================================================================
/**
 * 描述：WebView的 “按键按下处理()”
 * @param 按键 按键
 * @param 键位 键位
 * @return 是否处理成功
 */
fun WebView.按键按下处理(按键: Int, 键位: KeyEvent): Boolean = this.onKeyDown(按键, 键位)

//======================================================================
/**
 * 描述：WebView的 “按键抬起处理()”
 * @param 按键 按键
 * @param 键位 键位
 * @return 是否处理成功
 */
fun WebView.按键抬起处理(按键: Int, 键位: KeyEvent): Boolean = this.onKeyUp(按键, 键位)

//======================================================================
/**
 * 描述：WebView的 “按键重复处理()”
 * @param 按键 按键
 * @param 重复次数 重复次数
 * @param 键位 键位
 * @return 是否处理成功
 */
fun WebView.按键重复处理(按键: Int,重复次数: Int, 键位: KeyEvent): Boolean =
    this.onKeyMultiple(按键, 重复次数, 键位)

//======================================================================
/**
 * 描述：WebView的 “取无障碍节点提供者()”
 * @return 无障碍节点提供者
 */
fun WebView.取无障碍节点提供者():AccessibilityNodeProvider = this.getAccessibilityNodeProvider()

//======================================================================

/**
 * 描述：WebView的 “是否延迟子视图按下状态()”
 * @return 是否延迟子视图按下状态
 */
fun WebView.是否延迟子视图按下状态(): Boolean = this.shouldDelayChildPressedState()

//======================================================================
/**
 * 描述：WebView的 “取无障碍类名()”
 * @return 无障碍类名
 */
fun WebView.取无障碍类名(): CharSequence = this.getAccessibilityClassName()

//======================================================================
/**
 * 描述：WebView的 “提供虚拟结构()”
 * @param 结构 虚拟结构
 */
fun WebView.提供虚拟结构(结构: ViewStructure) = this.onProvideVirtualStructure(结构)

//======================================================================
/**
 * 描述：WebView的 “提供自动填充虚拟结构()”，仅支持Android 8.0及更高版本使用
 * @param 结构 虚拟结构
 * @param 标志位 标志位
 */
@RequiresApi(Build.VERSION_CODES.O)
fun WebView.提供自动填充虚拟结构(结构: ViewStructure, 标志位: Int) =
    this.onProvideAutofillVirtualStructure(结构,标志位)

//======================================================================
/**
 * 描述：WebView的 “提供内容捕获结构()”，仅支持Android 11.0及更高版本使用
 * @param 结构 虚拟结构
 * @param 标志位 标志位
 */
@RequiresApi(Build.VERSION_CODES.R)
fun WebView.提供内容捕获结构(结构: ViewStructure, 标志位: Int) =
    this.onProvideContentCaptureStructure(结构,标志位)

//======================================================================
/**
 * 描述：WebView的 “自动填充()”，仅支持Android 8.0及更高版本使用
 * @param 值集 值集
 */
@RequiresApi(Build.VERSION_CODES.O)
fun WebView.自动填充(值集: SparseArray<AutofillValue>) = this.autofill(值集)

//======================================================================

/**
 * 描述：WebView的 “是否可见对自动填充()”，仅支持Android 9.0及更高版本使用
 * @param 虚拟Id 虚拟Id
 * @return 是否可见对自动填充
 */
@RequiresApi(Build.VERSION_CODES.P)
fun WebView.是否可见对自动填充(虚拟Id: Int): Boolean = this.isVisibleToUserForAutofill(虚拟Id)

//======================================================================

/**
 * 描述：WebView的 “创建虚拟视图翻译请求()”，仅支持Android 12.0及更高版本使用
 * @param 虚拟Id 虚拟Id
 * @param 支持的格式 支持的格式
 * @param 请求收集器 请求收集器
 */
@SuppressLint("WrongConstant")
@RequiresApi(Build.VERSION_CODES.S)
fun WebView.创建虚拟视图翻译请求(虚拟Id: LongArray, 支持的格式: IntArray, 请求收集器: Consumer<ViewTranslationRequest>) =
    this.onCreateVirtualViewTranslationRequests(虚拟Id,支持的格式,请求收集器)

//======================================================================
/**
 * 描述：WebView的 “分发创建视图翻译请求()”，仅支持Android 12.0及更高版本使用
 * @param 视图Id 视图Id
 * @param 支持的格式 支持的格式
 * @param 能力 能力
 * @param 请求 请求
 */
@SuppressLint("WrongConstant")
@RequiresApi(Build.VERSION_CODES.S)
fun WebView.分发创建视图翻译请求(
    视图Id : Map<AutofillId, LongArray>,
    支持的格式: IntArray,
    能力 :TranslationCapability,
    请求 : List<ViewTranslationRequest>
) =
    this.dispatchCreateViewTranslationRequest(视图Id,支持的格式,能力,请求)

//======================================================================

/**
 * 描述：WebView的 “虚拟视图翻译响应回调()”，仅支持Android 12.0及更高版本使用
 * @param 响应 响应
 */
@RequiresApi(Build.VERSION_CODES.S)
fun WebView.虚拟视图翻译响应回调(响应: LongSparseArray<ViewTranslationResponse>) =
    this.onVirtualViewTranslationResponses(响应)

//======================================================================

 /**
 * 描述：WebView的 “执行长按()”
 * @return 是否成功
 */
fun WebView.执行长按(): Boolean = this.performLongClick()

//======================================================================

/**
 * 描述：WebView的 “创建输入连接()”
 * @param 输出属性 输出属性
 * @return 输入连接
 */
fun WebView.创建输入连接(输出属性:EditorInfo): InputConnection =
    this.onCreateInputConnection(输出属性)

//======================================================================
/**
 * 描述：WebView的 “拖拽事件处理()”
 * @param 事件 事件
 * @return 是否成功
 */
fun WebView.拖拽事件处理(事件:DragEvent): Boolean = this.onDragEvent(事件)

//======================================================================
/**
 * 描述：WebView的 “窗口焦点变化()”
 * @param 拥有窗口焦点 是否拥有窗口焦点
 */
fun WebView.窗口焦点变化(拥有窗口焦点: Boolean) = this.onWindowFocusChanged(拥有窗口焦点)

//======================================================================
/**
 * 描述：WebView的 “分发按键事件()”
 * @param 事件 事件
 * @return 是否成功
 */
fun WebView.分发按键事件(事件: KeyEvent): Boolean = this.dispatchKeyEvent(事件)

//======================================================================
/**
 * 描述：WebView的 “请求焦点()”
 * @param 方向 方向
 * @param 先前获得焦点的矩形区域 先前获得焦点的矩形区域
 * @return 是否成功
 */
fun WebView.请求焦点(方向: Int, 先前获得焦点的矩形区域: Rect): Boolean =
    this.requestFocus(方向,先前获得焦点的矩形区域)

//======================================================================
/**
 * 描述：WebView的 “请求将子视图矩形显示在屏幕内()”
 * @param 子视图 子视图
 * @param 矩形 矩形
 * @param 立即 是否立即
 * @return 是否成功
 */
fun WebView.请求将子视图矩形显示在屏幕内(子视图: View, 矩形: Rect, 立即: Boolean): Boolean =
    this.requestChildRectangleOnScreen(子视图,矩形,立即)

//======================================================================
/**
 * 描述：WebView的 “置背景颜色()”
 * @param 颜色 颜色
 */
fun WebView.置背景颜色(颜色: Int) = this.setBackgroundColor(颜色)

//======================================================================
/**
 * 描述：WebView的 “置图层类型()”
 * @param 图层类型 图层类型
 * @param 画笔 画笔
 */
fun WebView.置图层类型(图层类型: Int, 画笔: Paint) = this.setLayerType(图层类型, 画笔)

//======================================================================
/**
 * 描述：WebView的 “开始临时脱离时()”
 */
fun WebView.开始临时脱离时() = this.onStartTemporaryDetach()

//======================================================================
/**
 * 描述：WebView的 “结束临时脱离时()”
 */
fun WebView.结束临时脱离时() = this.onFinishTemporaryDetach()

//======================================================================
/**
 * 描述：WebView的 “取处理器()”
 * @return 处理器
 */
fun WebView.取处理器(): Handler = this.getHandler()

//======================================================================
/**
 * 描述：WebView的 “查找焦点()”
 * @return 焦点视图
 */
fun WebView.查找焦点(): View = this.findFocus()

//======================================================================

/**
 * 描述：WebView的 “检查是否为文本编辑器()”
 * @return 是否为文本编辑器
 */
fun WebView.检查是否为文本编辑器(): Boolean = this.onCheckIsTextEditor()

//======================================================================
/**
 * 描述：WebView的 “应用窗口边衬()”
 * @param 边衬 边衬
 * @return 边衬
 */
fun WebView.应用窗口边衬(边衬: WindowInsets): WindowInsets = this.onApplyWindowInsets(边衬)

//======================================================================
/**
 * 描述：WebView的 “解析指针图标()”
 * @param 事件 事件
 * @param 指针索引 指针索引
 * @return 指针图标
 */
fun WebView.解析指针图标(事件: MotionEvent, 指针索引: Int): PointerIcon? =
    this.onResolvePointerIcon(事件, 指针索引)

//======================================================================
