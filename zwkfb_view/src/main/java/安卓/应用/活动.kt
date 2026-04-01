package 安卓.应用

import android.Manifest
import android.annotation.SuppressLint
import android.app.ActionBar
import android.app.Activity
import android.app.Activity.ScreenCaptureCallback
import android.app.ActivityManager.TaskDescription
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.app.ComponentCaller
import android.app.Dialog
import android.app.DirectAction
import android.app.Fragment
import android.app.FragmentManager
import android.app.LoaderManager
import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.PictureInPictureUiState
import android.app.SharedElementCallback
import android.app.TaskStackBuilder
import android.app.VoiceInteractor
import android.app.assist.AssistContent
import android.content.ComponentCallbacks
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.IntentSender.SendIntentException
import android.content.LocusId
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.content.res.Resources.Theme
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.session.MediaController
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.CancellationSignal
import android.os.OutcomeReceiver
import android.os.PersistableBundle
import android.transition.Scene
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.ActionMode
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.DragAndDropPermissions
import android.view.DragEvent
import android.view.KeyEvent
import android.view.KeyboardShortcutGroup
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.MotionEvent
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.widget.Toolbar
import android.window.OnBackInvokedDispatcher
import android.window.SplashScreen
import androidx.annotation.AnimRes
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import java.io.FileDescriptor
import java.io.PrintWriter
import java.util.concurrent.Executor
import java.util.function.Consumer


//===========================================================================================

fun Activity.取意图(): Intent? {
    return this.getIntent()
}

fun Activity.置意图(新意图: Intent?) {
    this.setIntent(新意图)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.取调用者(): ComponentCaller? {
    return this.getCaller()
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.置意图(新意图: Intent?, 新调用者: ComponentCaller?) {
    this.setIntent(新意图, 新调用者)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.R)
fun Activity.置焦点上下文(焦点Id: LocusId?, 数据包: Bundle?) {
    this.setLocusContext(焦点Id, 数据包)
}

//===========================================================================================

@RequiresApi(api = Build.VERSION_CODES.BAKLAVA)
fun Activity.请求浏览器打开提示() {
    this.requestOpenInBrowserEducation()
}

//===========================================================================================

fun Activity.取应用程(): Application? {
    return this.getApplication()
}

//===========================================================================================

fun Activity.是否子项(): Boolean {
    return this.isChild()
}

fun Activity.取父级(): Activity? {
    return this.getParent()
}


fun Activity.取窗口管理器(): WindowManager? {
    return this.getWindowManager()
}

fun Activity.取窗口(): Window? {
    return this.getWindow()
}


fun Activity.取加载器管理(): LoaderManager? {
    return this.getLoaderManager()
}


fun Activity.取当前焦点(): View? {
    return this.getCurrentFocus()
}


@RequiresApi(api = Build.VERSION_CODES.Q)
fun Activity.注册活动生命周期回调(回调: ActivityLifecycleCallbacks) {
    this.registerActivityLifecycleCallbacks(回调)
}

@RequiresApi(api = Build.VERSION_CODES.Q)
fun Activity.注销活动生命周期回调(回调: ActivityLifecycleCallbacks) {
    this.unregisterActivityLifecycleCallbacks(回调)
}


fun Activity.注册组件回调(回调: ComponentCallbacks?) {
    this.registerComponentCallbacks(回调)
}

fun Activity.注销组件回调(回调: ComponentCallbacks?) {
    this.unregisterComponentCallbacks(回调)
}

@RequiresApi(api = Build.VERSION_CODES.S)
fun Activity.取启动屏(): SplashScreen {
    return this.getSplashScreen()
}


@RequiresApi(api = Build.VERSION_CODES.Q)
fun Activity.顶部恢复活动已更改(是否顶部恢复活动: Boolean) {
    this.onTopResumedActivityChanged(是否顶部恢复活动)
}

fun Activity.是否语音交互(): Boolean {
    return this.isVoiceInteraction()
}

fun Activity.是否语音交互根(): Boolean {
    return this.isVoiceInteractionRoot()
}

fun Activity.取语音交互器(): VoiceInteractor? {
    return this.getVoiceInteractor()
}

fun Activity.是否支持本地语音交互(): Boolean {
    return this.isLocalVoiceInteractionSupported()
}

fun Activity.开始本地语音交互(私有选项: Bundle?) {
    this.startLocalVoiceInteraction(私有选项)
}


fun Activity.本地语音交互已启动() {
    this.onLocalVoiceInteractionStarted()
}


fun Activity.本地语音交互已停止() {
    this.onLocalVoiceInteractionStopped()
}

fun Activity.停止本地语音交互() {
    this.stopLocalVoiceInteraction()
}



@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.新意图(意图: Intent, 调用者: ComponentCaller) {
    this.onNewIntent(意图, 调用者)
}


fun Activity.提供辅助数据(数据: Bundle?) {
    this.onProvideAssistData(数据)
}


fun Activity.提供辅助内容(输出内容: AssistContent?) {
    this.onProvideAssistContent(输出内容)
}


@RequiresApi(api = Build.VERSION_CODES.Q)
fun Activity.取直接操作(取消信号: CancellationSignal, 回调: Consumer<MutableList<DirectAction?>?>) {
    this.onGetDirectActions(取消信号, 回调)
}



@RequiresApi(api = Build.VERSION_CODES.Q)
fun Activity.执行直接操作(
    动作Id: String,
    参数: Bundle,
    取消信号: CancellationSignal,
    结果监听器: Consumer<Bundle?>,
) {
    this.onPerformDirectAction(动作Id, 参数, 取消信号, 结果监听器)
}


fun Activity.请求显示键盘快捷键() {
    this.requestShowKeyboardShortcuts()
}

fun Activity.关闭键盘快捷键帮助() {
    this.dismissKeyboardShortcutsHelper()
}


fun Activity.提供键盘快捷键(数据: MutableList<KeyboardShortcutGroup?>?, 菜单: Menu?, 设备Id: Int) {
    this.onProvideKeyboardShortcuts(数据, 菜单, 设备Id)
}


fun Activity.显示辅助(参数: Bundle?): Boolean {
    return this.showAssist(参数)
}



fun Activity.报告完全绘制() {
    this.reportFullyDrawn()
}



@RequiresApi(api = Build.VERSION_CODES.O)
fun Activity.多窗口模式已改变(是否多窗口模式: Boolean, 新配置: Configuration?) {
    this.onMultiWindowModeChanged(是否多窗口模式, 新配置)
}


fun Activity.多窗口模式已改变(是否多窗口模式: Boolean) {
    this.onMultiWindowModeChanged(是否多窗口模式)
}


fun Activity.是否在多窗口模式(): Boolean {
    return this.isInMultiWindowMode()
}


@RequiresApi(api = Build.VERSION_CODES.O)
fun Activity.画中画模式改变(是否处于画中画模式: Boolean, 新配置: Configuration?) {
    this.onPictureInPictureModeChanged(是否处于画中画模式, 新配置)
}



@RequiresApi(api = Build.VERSION_CODES.S)
fun Activity.画中画界面状态已改变(pip状态: PictureInPictureUiState) {
    this.onPictureInPictureUiStateChanged(pip状态)
}


fun Activity.画中画模式改变(是否处于画中画模式: Boolean) {
    this.onPictureInPictureModeChanged(是否处于画中画模式)
}


fun Activity.是否处于画中画模式(): Boolean {
    return this.isInPictureInPictureMode()
}




fun Activity.进入画中画模式() {
    this.enterPictureInPictureMode()
}



@RequiresApi(api = Build.VERSION_CODES.O)
fun Activity.进入画中画模式(参数: PictureInPictureParams): Boolean {
    return this.enterPictureInPictureMode(参数)
}


@RequiresApi(api = Build.VERSION_CODES.O)
fun Activity.置画中画参数(参数: PictureInPictureParams) {
    this.setPictureInPictureParams(参数)
}

@RequiresApi(api = Build.VERSION_CODES.O)
fun Activity.取最大数量画中画操作(): Int {
    return this.getMaxNumPictureInPictureActions()
}



@RequiresApi(api = Build.VERSION_CODES.R)
fun Activity.请求画中画(): Boolean {
    return this.onPictureInPictureRequested()
}



@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Activity.请求全屏模式(请求: Int, 审批回调: OutcomeReceiver<Void?, Throwable?>?) {
    this.requestFullscreenMode(请求, 审批回调)
}

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun Activity.置大覆盖层停靠(应停靠大覆盖层: Boolean) {
    this.setShouldDockBigOverlays(应停靠大覆盖层)
}


@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun Activity.应停靠大覆盖层(): Boolean {
    return this.shouldDockBigOverlays()
}




fun Activity.取改变配置(): Int {
    return this.getChangingConfigurations()
}


fun Activity.取上次非配置实例(): Any? {
    return this.getLastNonConfigurationInstance()
}



fun Activity.保留非配置实例(): Any? {
    return this.onRetainNonConfigurationInstance()
}



fun Activity.内存不足() {
    this.onLowMemory()
}



fun Activity.内存回收(等级: Int) {
    this.onTrimMemory(等级)
}


fun Activity.取碎片管理器(): FragmentManager? {
    return this.getFragmentManager()
}


fun Activity.附加碎片(碎片: Fragment?) {
    this.onAttachFragment(碎片)
}


fun Activity.托管查询(
    网址: Uri?,
    投影: Array<String?>?,
    选择: String?,
    选择参数: Array<String?>?,
    排序顺序: String?,
): Cursor? {
    return this.managedQuery(网址, 投影, 选择, 选择参数, 排序顺序)
}


fun Activity.开始管理光标(光标: Cursor?) {
    this.startManagingCursor(光标)
}

fun Activity.停止管理光标(光标: Cursor?) {
    this.stopManagingCursor(光标)
}


fun <T : View?> Activity.查找视图通过Id(@IdRes id: Int): T? {
    return this.findViewById<T?>(id)
}


fun <T : View?> Activity.要求视图通过Id(@IdRes id: Int): T? {
    return this.findViewById<T?>(id)
}

fun Activity.取操作栏(): ActionBar? {
    return this.getActionBar()
}

fun Activity.置操作栏(工具栏: Toolbar?) {
    this.setActionBar(工具栏)
}


fun Activity.置内容视图(@LayoutRes 布局资源ID: Int) {
    this.setContentView(布局资源ID)
}

fun Activity.置内容视图(视图: View?) {
    this.setContentView(视图)
}

fun Activity.置内容视图(视图: View?, 参数: ViewGroup.LayoutParams?) {
    this.setContentView(视图, 参数)
}

fun Activity.添加内容视图(视图: View?, 参数: ViewGroup.LayoutParams?) {
    this.addContentView(视图, 参数)
}

fun Activity.取内容过渡管理器(): TransitionManager? {
    return this.getContentTransitionManager()
}

fun Activity.置内容过渡管理器(内容过渡管理器: TransitionManager?) {
    this.setContentTransitionManager(内容过渡管理器)
}

fun Activity.取内容场景(): Scene? {
    return this.getContentScene()
}

fun Activity.置完成触摸外部(完成: Boolean) {
    this.setFinishOnTouchOutside(完成)
}


fun Activity.置默认键模式(模式: Int) {
    this.setDefaultKeyMode(模式)
}


fun Activity.用户交互() {
    this.onUserInteraction()
}



fun Activity.窗口属性改变(参数: WindowManager.LayoutParams?) {
    this.onWindowAttributesChanged(参数)
}


fun Activity.是否有窗口焦点(): Boolean {
    return this.hasWindowFocus()
}



fun Activity.分发按键事件(事件: KeyEvent?): Boolean {
    return this.dispatchKeyEvent(事件)
}

fun Activity.菜单打开(特性Id: Int, 菜单: Menu): Boolean {
    return this.onMenuOpened(特性Id, 菜单)
}



fun Activity.菜单项被选中(特性Id: Int, 项: MenuItem): Boolean {
    return this.onMenuItemSelected(特性Id, 项)
}



fun Activity.面板关闭(特性Id: Int, 菜单: Menu) {
    this.onPanelClosed(特性Id, 菜单)
}


fun Activity.使选项菜单失效() {
    this.invalidateOptionsMenu()
}


fun Activity.创建选项菜单(菜单: Menu?): Boolean {
    return this.onCreateOptionsMenu(菜单)
}


fun Activity.准备选项菜单(菜单: Menu?): Boolean {
    return this.onPrepareOptionsMenu(菜单)
}


fun Activity.选项菜单项被选中(项: MenuItem): Boolean {
    return this.onOptionsItemSelected(项)
}


fun Activity.导航向上(): Boolean {
    return this.onNavigateUp()
}



fun Activity.从子页面导航向上(子: Activity?): Boolean {
    return this.onNavigateUpFromChild(子)
}


fun Activity.创建向上导航任务栈(构建器: TaskStackBuilder?) {
    this.onCreateNavigateUpTaskStack(构建器)
}


fun  Activity.准备向上导航任务栈(构建器: TaskStackBuilder?) {
    this.onPrepareNavigateUpTaskStack(构建器)
}



fun Activity.选项菜单关闭(菜单: Menu?) {
    this.onOptionsMenuClosed(菜单)
}



fun Activity.打开选项菜单() {
    this.openOptionsMenu()
}



fun Activity.关闭选项菜单() {
    this.closeOptionsMenu()
}



fun Activity.创建上下文菜单(菜单: ContextMenu?, 视图: View?, 菜单信息: ContextMenuInfo?) {
    this.onCreateContextMenu(菜单, 视图, 菜单信息)
}

fun Activity.注册上下文菜单(视图: View?) {
    this.registerForContextMenu(视图)
}

fun Activity.取消注册上下文菜单(视图: View?) {
    this.unregisterForContextMenu(视图)
}


fun Activity.打开上下文菜单(视图: View?) {
    this.openContextMenu(视图)
}


fun Activity.关闭上下文菜单() {
    this.closeContextMenu()
}


fun Activity.上下文菜单项选中(项: MenuItem): Boolean {
    return this.onContextItemSelected(项)
}


fun Activity.上下文菜单关闭(菜单: Menu) {
    this.onContextMenuClosed(菜单)
}





fun Activity.显示对话框(id: Int) {
    this.showDialog(id)
}


fun Activity.显示对话框(id: Int, 参数: Bundle?) {
    this.showDialog(id, 参数)
}


fun Activity.关闭对话框(id: Int) {
    this.dismissDialog(id)
}

fun Activity.移除对话框(id: Int) {
    this.removeDialog(id)
}





fun Activity.开始搜索(初始查询: String?, 选择初始查询: Boolean, 应用搜索数据: Bundle?, 全局搜索: Boolean) {
    this.startSearch(初始查询, 选择初始查询, 应用搜索数据, 全局搜索)
}

fun Activity.触发搜索(查询: String?, 应用搜索数据: Bundle?) {
    this.triggerSearch(查询, 应用搜索数据)
}

fun Activity.捕获按键事件(取: Boolean) {
    this.takeKeyEvents(取)
}

fun Activity.请求窗口特性(特性Id: Int): Boolean {
    return this.requestWindowFeature(特性Id)
}

fun Activity.置特性可绘制资源(特性Id: Int, @DrawableRes 资源Id: Int) {
    this.setFeatureDrawableResource(特性Id, 资源Id)
}

fun Activity.置特性可绘制Uri(特性Id: Int, uri: Uri?) {
    this.setFeatureDrawableUri(特性Id, uri)
}

fun Activity.置特性可绘制(特性Id: Int, 可绘制对象: Drawable?) {
    this.setFeatureDrawable(特性Id, 可绘制对象)
}

fun Activity.置特性可绘制透明度(特性Id: Int, 透明度: Int) {
    this.setFeatureDrawableAlpha(特性Id, 透明度)
}

fun Activity.取布局加载器(): LayoutInflater {
    return this.getLayoutInflater()
}


fun Activity.取菜单加载器(): MenuInflater {
    return this.getMenuInflater()
}


fun Activity.置主题(资源id: Int) {
    this.setTheme(资源id)
}


fun Activity.请求权限(权限: Array<String?>, 请求代码: Int) {
    this.requestPermissions(权限, 请求代码)
}

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.请求权限(权限组: Array<String?>, 请求代码: Int, 设备Id: Int) {
    this.requestPermissions(权限组, 请求代码, 设备Id)
}


fun Activity.是否应该显示权限请求说明(权限: String): Boolean {
    return this.shouldShowRequestPermissionRationale(权限)
}


@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.是否应该显示权限请求说明(权限: String, 设备Id: Int): Boolean {
    return this.shouldShowRequestPermissionRationale(权限, 设备Id)
}


fun Activity.启动活动以获取结果(@RequiresPermission 意图: Intent?, 请求代码: Int) {
    this.startActivityForResult(意图, 请求代码)
}


fun Activity.启动活动以获取结果(@RequiresPermission 意图: Intent?, 请求代码: Int, 选项: Bundle?) {
    this.startActivityForResult(意图, 请求代码, 选项)
}


@RequiresApi(api = Build.VERSION_CODES.O)
fun Activity.是否活动转换正在运行(): Boolean {
    return this.isActivityTransitionRunning()
}


@Throws(SendIntentException::class)
fun Activity.启动意图发送器以获取结果(
    意图: IntentSender?, 请求代码: Int,
    填充意图: Intent?, 标志掩码: Int, 标志值: Int, 附加标志: Int,
) {
    this.startIntentSenderForResult(意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志)
}


@Throws(SendIntentException::class)
fun Activity.启动意图发送器以获取结果(
    意图: IntentSender?, 请求代码: Int,
    填充意图: Intent?, 标志掩码: Int, 标志值: Int, 附加标志: Int,
    选项: Bundle?,
) {
    this.startIntentSenderForResult(意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志, 选项)
}


fun Activity.启动活动(意图: Intent?) {
    this.startActivity(意图)
}

fun Activity.启动活动(意图: Intent?, 选项: Bundle?) {
    this.startActivity(意图, 选项)
}

fun Activity.启动多活动(意图组: Array<Intent?>?) {
    this.startActivities(意图组)
}

fun Activity.启动多活动(意图组: Array<Intent?>?, 选项: Bundle?) {
    this.startActivities(意图组, 选项)
}

@Throws(SendIntentException::class)
fun Activity.启动意图发送器(
    意图: IntentSender?, 填充意图: Intent?, 标志掩码: Int,
    标志值: Int, 附加标志: Int,
) {
    this.startIntentSender(意图, 填充意图, 标志掩码, 标志值, 附加标志)
}

@Throws(SendIntentException::class)
fun Activity.启动意图发送器(
    意图: IntentSender?, 填充意图: Intent?,
    标志掩码: Int, 标志值: Int, 附加标志: Int, 选项: Bundle?,
) {
    this.startIntentSender(意图, 填充意图, 标志掩码, 标志值, 附加标志, 选项)
}

fun Activity.启动活动如果需要(@RequiresPermission 意图: Intent, 请求代码: Int): Boolean {
    return this.startActivityIfNeeded(意图, 请求代码)
}

fun Activity.启动活动如果需要(@RequiresPermission 意图: Intent, 请求代码: Int, 选项: Bundle?): Boolean {
    return this.startActivityIfNeeded(意图, 请求代码, 选项)
}


fun Activity.启动下一个匹配活动(@RequiresPermission 意图: Intent): Boolean {
    return this.startNextMatchingActivity(意图)
}

fun Activity.启动下一个匹配活动(@RequiresPermission 意图: Intent, 选项: Bundle?): Boolean {
    return this.startNextMatchingActivity(意图, 选项)
}


fun Activity.启动活动从子(子: Activity, @RequiresPermission 意图: Intent?, 请求代码: Int) {
    this.startActivityFromChild(子, 意图, 请求代码)
}

fun Activity.启动活动从子(子: Activity, @RequiresPermission 意图: Intent?, 请求代码: Int, 选项: Bundle?) {
    this.startActivityFromChild(子, 意图, 请求代码, 选项)
}


fun Activity.启动活动从碎片(碎片: Fragment, @RequiresPermission 意图: Intent?, 请求代码: Int) {
    this.startActivityFromFragment(碎片, 意图, 请求代码)
}

fun Activity.启动活动从碎片(
    碎片: Fragment,
    @RequiresPermission 意图: Intent?,
    请求代码: Int,
    选项: Bundle?,
) {
    this.startActivityFromFragment(碎片, 意图, 请求代码, 选项)
}


@Throws(SendIntentException::class)
fun Activity.启动意图发送器从子(
    子: Activity?, 意图: IntentSender?, 请求代码: Int, 填充意图: Intent?, 标志掩码: Int,
    标志值: Int, 附加标志: Int,
) {
    this.startIntentSenderFromChild(子, 意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志)
}

@Throws(SendIntentException::class)
fun Activity.启动意图发送器从子(
    子: Activity?, 意图: IntentSender?, 请求代码: Int, 填充意图: Intent?, 标志掩码: Int,
    标志值: Int, 附加标志: Int, 选项: Bundle?,
) {
    this.startIntentSenderFromChild(子, 意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志, 选项)
}


@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Activity.覆盖活动过渡(覆盖类型: Int, @AnimRes 进入动画: Int, @AnimRes 退出动画: Int) {
    this.overrideActivityTransition(覆盖类型, 进入动画, 退出动画)
}


@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Activity.覆盖活动过渡(
    覆盖类型: Int,
    @AnimRes 进入动画: Int,
    @AnimRes 退出动画: Int,
    @ColorInt 背景颜色: Int,
) {
    this.overrideActivityTransition(覆盖类型, 进入动画, 退出动画, 背景颜色)
}


@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Activity.清除覆盖活动过渡(覆盖类型: Int) {
    this.clearOverrideActivityTransition(覆盖类型)
}

fun Activity.覆盖待处理过渡(进入动画: Int, 退出动画: Int) {
    this.overridePendingTransition(进入动画, 退出动画)
}

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun Activity.覆盖待处理过渡(进入动画: Int, 退出动画: Int, 背景颜色: Int) {
    this.overridePendingTransition(进入动画, 退出动画, 背景颜色)
}

fun Activity.置结果(结果代码: Int) {
    this.setResult(结果代码)
}

fun Activity.置结果(结果代码: Int, 数据: Intent?) {
    this.setResult(结果代码, 数据)
}


fun Activity.取来源(): Uri? {
    return this.getReferrer()
}


fun Activity.提供引荐来源(): Uri? {
    return this.onProvideReferrer()
}


fun Activity.取调用包名(): String? {
    return this.getCallingPackage()
}

fun Activity.取调用活动(): ComponentName? {
    return this.getCallingActivity()
}

@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Activity.取启动来源Uid(): Int {
    return this.getLaunchedFromUid()
}


@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Activity.取启动方包名(): String? {
    return this.getLaunchedFromPackage()
}

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.取初始调用(): ComponentCaller {
    return this.getInitialCaller()
}

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.取当前调用(): ComponentCaller {
    return this.getCurrentCaller()
}

fun Activity.置可见(可见: Boolean) {
    this.setVisible(可见)
}

fun Activity.是否正在完成(): Boolean {
    return this.isFinishing()
}

fun Activity.是否已销毁(): Boolean {
    return this.isDestroyed()
}

fun Activity.是否正在改变配置(): Boolean {
    return this.isChangingConfigurations()
}

fun Activity.重建() {
    this.recreate()
}

fun Activity.关闭() {
    this.finish()
}

fun Activity.关闭亲和性() {
    this.finishAffinity()
}

fun Activity.关闭从子(子: Activity?) {
    this.finishFromChild(子)
}

fun Activity.关闭过渡后() {
    this.finishAfterTransition()
}

fun Activity.关闭活动(请求代码: Int) {
    this.finishActivity(请求代码)
}


fun Activity.关闭活动从子(子: Activity, 请求代码: Int) {
    this.finishActivityFromChild(子, 请求代码)
}

fun Activity.完成并移除任务() {
    this.finishAndRemoveTask()
}

fun Activity.释放实例(): Boolean {
    return this.releaseInstance()
}


fun Activity.活动重新进入(结果代码: Int, 数据: Intent?) {
    this.onActivityReenter(结果代码, 数据)
}

fun Activity.创建待处理结果(结果代码: Int, 数据: Intent, 标志: Int): PendingIntent? {
    return this.createPendingResult(结果代码, 数据, 标志)
}

fun Activity.置请求方向(请求方向: Int) {
    this.setRequestedOrientation(请求方向)
}

fun Activity.取请求方向(): Int {
    return this.getRequestedOrientation()
}

fun Activity.取任务Id(): Int {
    return this.getTaskId()
}

fun Activity.是否任务根(): Boolean {
    return this.isTaskRoot()
}

fun Activity.移动任务到后台(非根: Boolean): Boolean {
    return this.moveTaskToBack(非根)
}


fun Activity.取本地类名(): String {
    return this.getLocalClassName()
}

fun Activity.取组件名(): ComponentName? {
    return this.getComponentName()
}

fun Activity.取偏好设置(模式: Int): SharedPreferences? {
    return this.getPreferences(模式)
}

@RequiresApi(api = Build.VERSION_CODES.S)
fun Activity.是否从气泡启动(): Boolean {
    return this.isLaunchedFromBubble()
}

fun Activity.取系统服务(名: String): Any? {
    return this.getSystemService(名)
}


fun Activity.置标题(标题: CharSequence?) {
    this.setTitle(标题)
}

fun Activity.置标题(标题Id: Int) {
    this.setTitle(标题Id)
}

fun Activity.置标题颜色(文本颜色: Int) {
    this.setTitleColor(文本颜色)
}

fun Activity.取标题(): CharSequence? {
    return this.getTitle()
}

fun Activity.取标题颜色(): Int {
    return this.getTitleColor()
}




fun Activity.置任务描述(任务描述: TaskDescription?) {
    this.setTaskDescription(任务描述)
}

fun Activity.置进度条可见(可见: Boolean) {
    this.setProgressBarVisibility(可见)
}

fun Activity.置不确定进度条可见(可见: Boolean) {
    this.setProgressBarIndeterminateVisibility(可见)
}


fun Activity.置不确定进度条(不确定: Boolean) {
    this.setProgressBarIndeterminate(不确定)
}

fun Activity.置进度(进度: Int) {
    this.setProgress(进度)
}

fun Activity.置次要进度(次要进度: Int) {
    this.setSecondaryProgress(次要进度)
}

fun Activity.置音量控制流(流类型: Int) {
    this.setVolumeControlStream(流类型)
}

fun Activity.取音量控制流(): Int {
    return this.getVolumeControlStream()
}


fun Activity.置媒体控制器(控制器: MediaController?) {
    this.setMediaController(控制器)
}

fun Activity.取媒体控制器(): MediaController? {
    return this.getMediaController()
}


fun Activity.运行主线程(动作: Runnable?) {
    this.runOnUiThread(动作)
}


fun Activity.转储(前缀: String, 文件描述符: FileDescriptor?, 作家: PrintWriter, 参数: Array<String?>?) {
    this.dump(前缀, 文件描述符, 作家, 参数)
}


fun Activity.是否沉浸式(): Boolean {
    return this.isImmersive()
}

@RequiresApi(api = Build.VERSION_CODES.R)
fun Activity.置半透明(半透明: Boolean): Boolean {
    return this.setTranslucent(半透明)
}


fun Activity.请求背后可见(可见: Boolean): Boolean {
    return this.requestVisibleBehind(可见)
}

fun Activity.后台可见取消() {
    this.onVisibleBehindCanceled()
}


fun Activity.进入动画完成() {
    this.onEnterAnimationComplete()
}

fun Activity.置沉浸式(i: Boolean) {
    this.setImmersive(i)
}


@Throws(PackageManager.NameNotFoundException::class)
fun Activity.置Vr模式启用(已启用: Boolean, 请求组件: ComponentName) {
    this.setVrModeEnabled(已启用, 请求组件)
}

fun Activity.开始操作模式(回调: ActionMode.Callback?): ActionMode? {
    return this.startActionMode(回调)
}

fun Activity.开始操作模式(回调: ActionMode.Callback?, 类型: Int): ActionMode? {
    return this.startActionMode(回调, 类型)
}


fun Activity.窗口启动操作模式(回调: ActionMode.Callback?): ActionMode? {
    return this.onWindowStartingActionMode(回调)
}


fun Activity.窗口启动操作模式(回调: ActionMode.Callback?, 类型: Int): ActionMode? {
    return this.onWindowStartingActionMode(回调, 类型)
}



fun Activity.操作模式启动(模式: ActionMode?) {
    this.onActionModeStarted(模式)
}



fun Activity.操作模式完成(模式: ActionMode?) {
    this.onActionModeFinished(模式)
}


fun Activity.是否应重新创建任务(目标意图: Intent?): Boolean {
    return this.shouldUpRecreateTask(目标意图)
}


fun Activity.导航向上到(上升意图: Intent?): Boolean {
    return this.navigateUpTo(上升意图)
}

fun Activity.导航向上到从子(子: Activity?, 上升意图: Intent?): Boolean {
    return this.navigateUpToFromChild(子, 上升意图)
}

fun Activity.取父活动意图(): Intent? {
    return this.getParentActivityIntent()
}

fun Activity.置进入共享元素回调(回调: SharedElementCallback?) {
    this.setEnterSharedElementCallback(回调)
}

fun Activity.置退出共享元素回调(回调: SharedElementCallback?) {
    this.setExitSharedElementCallback(回调)
}

fun Activity.延迟进入过渡() {
    this.postponeEnterTransition()
}

fun Activity.开始推迟进入过渡() {
    this.startPostponedEnterTransition()
}

fun Activity.请求拖放权限(事件: DragEvent?): DragAndDropPermissions? {
    return this.requestDragAndDropPermissions(事件)
}

fun Activity.开始锁定任务() {
    this.startLockTask()
}

fun Activity.停止锁定任务() {
    this.stopLockTask()
}

fun Activity.显示锁定任务逃逸消息() {
    this.showLockTaskEscapeMessage()
}

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun Activity.置最近截图启用(已启用: Boolean) {
    this.setRecentsScreenshotEnabled(已启用)
}

@RequiresApi(api = Build.VERSION_CODES.O_MR1)
fun Activity.置显示在锁定(显示在锁定: Boolean) {
    this.setShowWhenLocked(显示在锁定)
}

@RequiresApi(api = Build.VERSION_CODES.Q)
fun Activity.置继承锁定时显示(锁定时继承显示: Boolean) {
    this.setInheritShowWhenLocked(锁定时继承显示)
}

@RequiresApi(api = Build.VERSION_CODES.O_MR1)
fun Activity.置屏幕常亮(屏幕常亮: Boolean) {
    this.setTurnScreenOn(屏幕常亮)
}

@RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Activity.置允跨Uid活动切换从下方(允许: Boolean) {
    this.setAllowCrossUidActivitySwitchFromBelow(允许)
}

@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
fun Activity.取返回调用调度器(): OnBackInvokedDispatcher {
    return this.getOnBackInvokedDispatcher()
}

@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@RequiresPermission(Manifest.permission.DETECT_SCREEN_CAPTURE)
fun Activity.注册屏幕捕获回调(执行者: Executor, 回调: ScreenCaptureCallback) {
    this.registerScreenCaptureCallback(执行者, 回调)
}

@RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@RequiresPermission(Manifest.permission.DETECT_SCREEN_CAPTURE)
fun Activity.取消注册屏幕捕获回调(回调: ScreenCaptureCallback) {
    this.unregisterScreenCaptureCallback(回调)
}