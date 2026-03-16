package 自定义.网络类;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.GeolocationPermissions;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.RenderProcessGoneDetail;
import android.webkit.SafeBrowsingResponse;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.gyf.immersionbar.BarHide;

import java.util.ArrayList;

import 安卓.组件.吐司;
import 安卓.网页工具.下载监听器;
import 安卓.网页工具.网页配置;
import 自定义.主题类.主题类;
import 自定义.对话框类.对话框类;
import 自定义.状态栏类.状态栏沉浸式;
import 自定义.状态栏类.状态栏沉浸式类;
import 自定义.系统类.系统类;

public class 网页视图 extends 安卓.网页工具.网页视图{

    public 网页视图(@NonNull Context 上下文) {
        super(上下文);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, boolean 私密浏览) {
        super(上下文, 属性, 默认样式属性, 私密浏览);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }

    //================================================================================

    private int 外部存储权限 = 0;


    public int 取外部存储权限() {
        return 外部存储权限;
    }

    //================================================================================

    private String 下载网址1 = null;
    private String 用户代理1 = null;
    private String 内容处理1 = null;
    private String 文件类型1 = null;


    public String 取下载网址() {
        return 下载网址1;
    }

    public String 取用户代理() {
        return 用户代理1;
    }

    public String 取内容处理() {
        return 内容处理1;
    }

    public String 取文件类型() {
        return 文件类型1;
    }

    //================================================================================

    @SuppressLint("SetJavaScriptEnabled")
    public void 默认配置(Activity 上下文, ArrayList<String> 网页第三方链接头) {
        ArrayList<String> 网页链接头 = new ArrayList<>();
        final View[] 网页视频控件 = {null};
        final WebChromeClient.CustomViewCallback[] 网页视频回调 = {null};
        this.置网页跳转拦截事件((网页跳转拦截事件请求) (网页视图, 请求) -> {
            String url = 请求.getUrl().toString();
            网页链接头.add("snssdk1128://");
            网页链接头.add("baiduboxapp://");
            网页链接头.add("baiduboxlite://");
            网页链接头.add("baiduhaokan://");
            网页链接头.add("market://");
            网页链接头.add("bilibili://");
            网页链接头.add("sohunews://");
            网页链接头.add("wvhzpj://");
            网页链接头.add("freereader://");
            网页链接头.add("mttbrowser://");
            网页链接头.add("baiduhaokan://");
            网页链接头.add("sohunews://");

            // 检查URL是否以http或https开头
            for (String 值 : 网页链接头) {
                if (url.startsWith(值)) {
                    try {
                        //判断是否有应用，如果有运行此代码
                        // 上面的参数中，url对应文件下载地址，mimetype对应下载文件的MIME类型
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));//创建 Intent
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 设置启动模式
//                    上下文.startActivity(intent);// 启动 Intent
                        return true;
                    } catch (Exception e) { // 捕获异常
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

                        return true;
                    }
                } else {
                    return false;
                }
            }
            return false;
        });
        this.置接收SSL错误事件((网页视图, 线程, 错误) -> 线程.proceed());
        this.置显示自定义视图事件((视图, 回调) -> {
            if (网页视频控件[0] != null) {
                回调.onCustomViewHidden(); // 隐藏自定义视图
                return;
            }
            网页视频控件[0] = 视图;
            网页视频回调[0] = 回调;
            FrameLayout decor = (FrameLayout)上下文.getWindow().getDecorView(); //获取activity的根布局
            decor.addView(视图, new FrameLayout.LayoutParams(-1, -1));

            上下文.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); //横屏
            状态栏沉浸式.初始沉浸式(上下文).透明栏().状态栏深色字体(!主题类.是否是深色模式(上下文))
                    .导航栏深色图标(!主题类.是否是深色模式(上下文)).隐藏栏(BarHide.FLAG_HIDE_BAR).初始化();
        });
        this.置隐藏自定义视图事件(() -> {
            if (网页视频控件[0] == null) {
                return;
            }

            FrameLayout decor = (FrameLayout)上下文.getWindow().getDecorView(); //获取activity的根布局
            decor.removeView(网页视频控件[0]);
            网页视频控件[0] = null;
            网页视频回调[0].onCustomViewHidden(); //隐藏自定义视图
            网页视频回调[0] = null;

            上下文.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED); //竖屏
            状态栏沉浸式.初始沉浸式(上下文).透明栏().自动状态栏深色模式启用(!主题类.是否是深色模式(上下文))
                    .自动导航栏深色模式启用(!主题类.是否是深色模式(上下文)).隐藏栏(BarHide.FLAG_SHOW_BAR).初始化();
        });
        this.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_BACK && this.canGoBack()) {
                this.goBack();
                return true;
            }
            return false;
        });
        this.置下载监听器(new 下载监听器() {

            private final BroadcastReceiver 下载完成广播 = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                    // 可以在这里处理下载完成后的逻辑，例如提示用户等
                    吐司.制作文本(context, "下载完成", 吐司.长度_短).显示();
                }

            };
            @SuppressLint("UnspecifiedRegisterReceiverFlag")
            @Override
            public void 开始下载回调(String 网址, String 用户代理, String 内容处置, String 媒体类型, long 内容长度) {
                下载网址1 = 网址;
                用户代理1 = 用户代理;
                内容处理1 = 内容处置;
                文件类型1 = 媒体类型;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
                    //高版本不用权限，直接下载
                    对话框类.材质浏览器下载对话框(上下文, 网址, 用户代理, 内容处置, 媒体类型);
                }else{
                    if (ContextCompat.checkSelfPermission(上下文, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(上下文, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                外部存储权限);
                    } else {
                        // 权限已授予，可以执行下载操作
                        对话框类.材质浏览器下载对话框(上下文, 网址, 用户代理, 内容处置, 媒体类型);
                        上下文.registerReceiver(下载完成广播, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    }
                }
            }

        });

        WebSettings 网页设置 = this.getSettings();
        网页设置.setJavaScriptEnabled(true); //启用 JavaScript 支持。
        // 设置User-Agent
        网页设置.setUserAgentString( (系统类.是否为平板(上下文))? 网页配置.Windows.INSTANCE.get夸克UA() : 网页配置.Android.INSTANCE.get夸克UA());
        网页设置.setUseWideViewPort(true); //启用支持自定义窗口。
        网页设置.setLoadWithOverviewMode(true);  //启用支持内容大小。
        网页设置.setDomStorageEnabled(true);  //启用 DOM 存储。
        网页设置.setAllowFileAccess(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R);  //启用文件访问。
        网页设置.setSupportZoom(true); //启用缩放。
        网页设置.setBuiltInZoomControls(true);  //启用缩放控制。
        网页设置.setDisplayZoomControls(false); //禁用缩放控制。
        网页设置.setDatabaseEnabled(true);  //启用数据库。
        网页设置.setMixedContentMode(WebSettings.LOAD_CACHE_ONLY);  //混合内容
        网页设置.setJavaScriptCanOpenWindowsAutomatically(true);  //启用 JavaScript 自动打开窗口。
        网页设置.setDefaultTextEncodingName("utf-8");  //设置编码格式。
        网页设置.setCacheMode(WebSettings.LOAD_NO_CACHE);  //缓存模式。
        网页设置.setPluginState(WebSettings.PluginState.ON); //启用插件。
        网页设置.setSavePassword(true); //保存密码。
        网页设置.setSaveFormData(true);  //保存表单数据。
        网页设置.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL); //NARROW_COLUMNS
        网页设置.setLoadsImagesAutomatically(true);  //启用图片。
        网页设置.setGeolocationDatabasePath(上下文.getDir("database", 0).getPath()); //启用定位数据库
        网页设置.setGeolocationEnabled(true); //启用定位
        网页设置.setMediaPlaybackRequiresUserGesture(true);  //启用视频播放时需要用户手势
        网页设置.setAllowFileAccessFromFileURLs(false); //允许从文件URL访问文件
        网页设置.setAllowUniversalAccessFromFileURLs(Build.VERSION.SDK_INT < 30); //允许从文件URL访问文件
        //这个一定要设置为false 否则会弹出权限请求框
        网页设置.setSupportMultipleWindows(false); //启用多窗口

        CookieManager instance = CookieManager.getInstance(); //启用Cookie
        instance.setAcceptCookie(true); //启用Cookie
        instance.setAcceptThirdPartyCookies(this, true); //启用第三方Cookie
        可用Cookie(); //启用Cookie
    }


    private void 可用Cookie() {
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        instance.setAcceptThirdPartyCookies(this, true);
    }

    //================================================================================

    public void 修复浏览器弹出键盘输入框位置的Bug(Activity 上下文, View 浏览器底部导航栏, View 浏览器进度条) {

        WebView webView = this;

        // 设置 Activity 的软键盘模式为 adjustResize
        上下文.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        // 监听布局变化
        View activityRootView = 上下文.findViewById(android.R.id.content);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect r = new Rect();
                activityRootView.getWindowVisibleDisplayFrame(r); // 获取当前窗口可视区域
                int screenHeight = activityRootView.getRootView().getHeight();
                int keyboardHeight = screenHeight - r.bottom; // 键盘高度
                if (keyboardHeight > 100) {
                    // 键盘弹出，增加 WebView 的底部内边距
                    webView.setPadding(0, 0, 0, (keyboardHeight - (浏览器底部导航栏.getHeight())
                    - 状态栏沉浸式类.用资源文件获取导航栏高度(上下文) - (浏览器进度条.getHeight())));
                } else {
                    // 键盘关闭，重置 WebView 的底部内边距
                    webView.setPadding(0, 0, 0, 0);
                }
            }
        });

    }

    //================================================================================

    /**
     * 用于拦截网页跳转（注意：置网页跳转拦截事件(网页跳转拦截事件网址 事件)和置网页跳转拦截事件(网页跳转拦截事件请求 事件)两个方法只能使用一个）
     * @param 事件 网页跳转拦截事件网址
     */
    public void 置网页跳转拦截事件(网页跳转拦截事件网址 事件) {
        网页跳转拦截事件网址1 = 事件;
        网页视图事件();
    }

    /**
     * 用于拦截网页跳转（注意：置网页跳转拦截事件(网页跳转拦截事件请求 事件)和置网页跳转拦截事件(网页跳转拦截事件请求 事件)两个方法只能使用一个）
     * @param 事件 网页跳转拦截事件请求
     */
    public void 置网页跳转拦截事件(网页跳转拦截事件请求 事件) {
        网页跳转拦截事件请求1 = 事件;
        网页视图事件();
    }

    public void 置开始加载网页事件(开始加载网页事件 事件) {
        开始加载网页事件1 = 事件;
        网页视图事件();
    }

    public void 置加载网页完成事件(加载网页完成事件 事件) {
        加载网页完成事件1 = 事件;
        网页视图事件();
    }


    public void 置接收SSL错误事件(接收SSL错误事件 事件) {
        接收SSL错误事件1 = 事件;
        网页视图事件();
    }

    public void 置加载网页错误事件(加载网页错误事件 事件) {
        加载网页错误事件1 = 事件;
        网页视图事件();
    }

    //================================================================================

    private 网页跳转拦截事件网址 网页跳转拦截事件网址1 = null;
    private 网页跳转拦截事件请求 网页跳转拦截事件请求1 = null;

    private 开始加载网页事件 开始加载网页事件1= null;
    private 加载网页完成事件 加载网页完成事件1 = null;

    private 接收SSL错误事件 接收SSL错误事件1 = null;

    private 加载网页错误事件 加载网页错误事件1 = null;

    //================================================================================


    public interface 网页跳转拦截事件网址 {
        boolean 网页跳转拦截(WebView 网页视图, String 网址);
    }

    public interface 网页跳转拦截事件请求 {
        boolean 网页跳转拦截(WebView 网页视图, WebResourceRequest 请求);
    }

    public interface 开始加载网页事件 {
        void 开始加载网页(WebView 网页视图, String 网址, Bitmap 图标);
    }

    public interface 加载网页完成事件 {
        void 加载网页完成(WebView 网页视图, String 网址);
    }

    public interface 接收SSL错误事件 {
        void 接收SSL错误(WebView 网页视图, SslErrorHandler 线程, SslError 错误);
    }

    public interface 加载网页错误事件 {
        void 加载网页错误(WebView 网页视图, int 错误代码, String 错误描述, String 网页网址);
    }


    //================================================================================


    private 网页进度改变事件 网页进度改变事件1 = null;
    private 接收网页标题事件 接收网页标题事件1 = null;
    private 接收网页图标事件 接收网页图标事件1 = null;
    private 接收触摸图标网址事件 接收触摸图标网址事件1 = null;

    private 显示自定义视图事件 显示自定义视图事件1 = null;
    private 隐藏自定义视图事件 隐藏自定义视图事件1 = null;


    private 显示文件选择器事件 显示文件选择器事件1 = null;

    //================================================================================

    public void 置网页进度事件(网页进度改变事件 事件) {
        网页进度改变事件1 = 事件;
        网页视图事件();
    }

    public void 置接收到标题事件(接收网页标题事件 事件) {
        接收网页标题事件1 = 事件;
        网页视图事件();
    }

    public void 置接收到图标事件(接收网页图标事件 事件) {
        接收网页图标事件1 = 事件;
        网页视图事件();
    }

    public void 置接收到触摸图标网址事件(接收触摸图标网址事件 事件) {
        接收触摸图标网址事件1 = 事件;
        网页视图事件();
    }

    public void 置显示自定义视图事件(显示自定义视图事件 事件) {
        显示自定义视图事件1 = 事件;
        网页视图事件();
    }

    public void 置隐藏自定义视图事件(隐藏自定义视图事件 事件) {
        隐藏自定义视图事件1 = 事件;
        网页视图事件();
    }


    public void 置显示文件选择器事件(显示文件选择器事件 事件) {
        显示文件选择器事件1 = 事件;
        网页视图事件();
    }

    //================================================================================

    public interface 网页进度改变事件 {
        void 网页进度改变(WebView 网页视图, int 进度);
    }

    public interface 接收网页标题事件 {
        void 接收标题(WebView 网页视图, String 标题);
    }

    public interface 接收网页图标事件 {
        void 接收到图标(WebView 网页视图, Bitmap 图标);
    }

    public interface 接收触摸图标网址事件 {
        void 接收触摸图标网址(WebView 网页视图, String 网址, boolean 预合成);
    }

    public interface 显示自定义视图事件 {
        void 显示自定义视图(View 视图, WebChromeClient.CustomViewCallback 回调);
    }

    public interface 隐藏自定义视图事件 {
        void 隐藏自定义视图();
    }


    public interface 显示文件选择器事件 {
        boolean 显示文件选择器(WebView 网页视图, ValueCallback<Uri[]> 文件路径回调, WebChromeClient.FileChooserParams 文件选择器参数);
    }

    //================================================================================

    private boolean 浏览器视频是否全屏 = false;

    public boolean 浏览器视频是否全屏() {
        return 浏览器视频是否全屏;
    }

    //================================================================================

    private void 网页视图事件(){
        this.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (网页跳转拦截事件网址1 != null) {
                    return 网页跳转拦截事件网址1.网页跳转拦截(view, url);
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                if (网页跳转拦截事件请求1 != null) {
                    return 网页跳转拦截事件请求1.网页跳转拦截(view, request);
                }
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public boolean shouldOverrideKeyEvent(WebView view, KeyEvent event) {
                return super.shouldOverrideKeyEvent(view, event);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                return super.shouldInterceptRequest(view, url);
            }

            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                return super.shouldInterceptRequest(view, request);
            }

            @Override
            public void onUnhandledKeyEvent(WebView view, KeyEvent event) {
                super.onUnhandledKeyEvent(view, event);
            }

            @Override
            public void onTooManyRedirects(WebView view, Message cancelMsg, Message continueMsg) {
                super.onTooManyRedirects(view, cancelMsg, continueMsg);
            }

            @Override
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                super.onScaleChanged(view, oldScale, newScale);
            }

            @Override
            public void onSafeBrowsingHit(WebView view, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
                super.onSafeBrowsingHit(view, request, threatType, callback);
            }

            @Override
            public boolean onRenderProcessGone(WebView view, RenderProcessGoneDetail detail) {
                return super.onRenderProcessGone(view, detail);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                super.onReceivedSslError(view, handler, error);
                if (接收SSL错误事件1 != null) {
                    接收SSL错误事件1.接收SSL错误(view, handler, error);
                }
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, @Nullable String account, String args) {
                super.onReceivedLoginRequest(view, realm, account, args);
            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
                super.onReceivedHttpError(view, request, errorResponse);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (加载网页错误事件1 != null) {
                    加载网页错误事件1.加载网页错误(view, errorCode, description, failingUrl);
                }
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

            @Override
            public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
                super.onReceivedClientCertRequest(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                if (开始加载网页事件1 != null) {
                    开始加载网页事件1.开始加载网页(view, url, favicon);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if (加载网页完成事件1 != null) {
                    加载网页完成事件1.加载网页完成(view, url);
                }
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onFormResubmission(WebView view, Message dontResend, Message resend) {
                super.onFormResubmission(view, dontResend, resend);
            }

            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
            }
        });
        this.setWebChromeClient(new WebChromeClient(){
            @Nullable
            @Override
            public Bitmap getDefaultVideoPoster() {
                return super.getDefaultVideoPoster();
            }

            @Nullable
            @Override
            public View getVideoLoadingProgressView() {
                return super.getVideoLoadingProgressView();
            }

            @Override
            public void getVisitedHistory(ValueCallback<String[]> callback) {
                super.getVisitedHistory(callback);
            }

            @Override
            public void onCloseWindow(WebView window) {
                super.onCloseWindow(window);
            }

            @Override
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                return super.onConsoleMessage(consoleMessage);
            }

            @Override
            public void onConsoleMessage(String message, int lineNumber, String sourceID) {
                super.onConsoleMessage(message, lineNumber, sourceID);
            }

            @Override
            public boolean onCreateWindow(WebView view, boolean isDialog, boolean isUserGesture, Message resultMsg) {
                return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg);
            }

            @Override
            public void onExceededDatabaseQuota(String url, String databaseIdentifier, long quota, long estimatedDatabaseSize, long totalQuota, WebStorage.QuotaUpdater quotaUpdater) {
                super.onExceededDatabaseQuota(url, databaseIdentifier, quota, estimatedDatabaseSize, totalQuota, quotaUpdater);
            }

            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
            }

            @Override
            public void onHideCustomView() {
                super.onHideCustomView();
                if (隐藏自定义视图事件1 != null) {
                    隐藏自定义视图事件1.隐藏自定义视图();
                }
                浏览器视频是否全屏 = false;
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }

            @Override
            public boolean onJsBeforeUnload(WebView view, String url, String message, JsResult result) {
                return super.onJsBeforeUnload(view, url, message, result);
            }

            @Override
            public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
                return super.onJsConfirm(view, url, message, result);
            }

            @Override
            public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
                return super.onJsPrompt(view, url, message, defaultValue, result);
            }

            @Override
            public boolean onJsTimeout() {
                return super.onJsTimeout();
            }

            @Override
            public void onPermissionRequest(PermissionRequest request) {
                super.onPermissionRequest(request);
            }

            @Override
            public void onPermissionRequestCanceled(PermissionRequest request) {
                super.onPermissionRequestCanceled(request);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (网页进度改变事件1 != null) {
                    网页进度改变事件1.网页进度改变(view, newProgress);
                }
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                if (接收网页图标事件1 != null) {
                    接收网页图标事件1.接收到图标(view, icon);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (接收网页标题事件1 != null) {
                    接收网页标题事件1.接收标题(view, title);
                }
            }

            @Override
            public void onReceivedTouchIconUrl(WebView view, String url, boolean precomposed) {
                super.onReceivedTouchIconUrl(view, url, precomposed);
                if (接收触摸图标网址事件1 != null) {
                    接收触摸图标网址事件1.接收触摸图标网址(view, url, precomposed);
                }
            }

            @Override
            public void onRequestFocus(WebView view) {
                super.onRequestFocus(view);
            }

            @Override
            public void onShowCustomView(View view, CustomViewCallback callback) {
                super.onShowCustomView(view, callback);
                // 全屏视频播放时ActionBar消失
                if (显示自定义视图事件1 != null) {
                    显示自定义视图事件1.显示自定义视图(view, callback);
                }
                浏览器视频是否全屏 = true;
            }

            @Override
            public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
                super.onShowCustomView(view, requestedOrientation, callback);
            }

            @Override
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
                if (显示文件选择器事件1 != null) {
                    return 显示文件选择器事件1.显示文件选择器(webView, filePathCallback, fileChooserParams);
                }
                return super.onShowFileChooser(webView, filePathCallback, fileChooserParams);
            }
        });
    }

}
