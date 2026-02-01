package 安卓.应用

import android.app.ActionBar
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Message
import android.view.ActionMode
import android.view.ContextMenu
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.SearchEvent
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import android.window.OnBackInvokedDispatcher
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：对话框
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 对话框 :Dialog{
    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?,
    ) : super(context, cancelable, cancelListener)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    override fun onCreate(savedInstanceState: Bundle?) {
        创建回调(savedInstanceState)
    }

    override fun onStart() {
        启动回调()
    }

    override fun onStop() {
        停止回调()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyLongPress(keyCode: Int, event: KeyEvent): Boolean {
        return super.onKeyLongPress(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return super.onKeyUp(keyCode, event)
    }

    override fun onKeyMultiple(keyCode: Int, repeatCount: Int, event: KeyEvent): Boolean {
        return super.onKeyMultiple(keyCode, repeatCount, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onKeyShortcut(keyCode: Int, event: KeyEvent): Boolean {
        return super.onKeyShortcut(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return super.onTouchEvent(event)
    }

    override fun onTrackballEvent(event: MotionEvent): Boolean {
        return super.onTrackballEvent(event)
    }

    override fun onGenericMotionEvent(event: MotionEvent): Boolean {
        return super.onGenericMotionEvent(event)
    }

    override fun onWindowAttributesChanged(params: WindowManager.LayoutParams?) {
        super.onWindowAttributesChanged(params)
    }

    override fun onContentChanged() {
        super.onContentChanged()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        return super.dispatchKeyEvent(event)
    }

    override fun dispatchKeyShortcutEvent(event: KeyEvent): Boolean {
        return super.dispatchKeyShortcutEvent(event)
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return super.dispatchTouchEvent(ev)
    }

    override fun dispatchTrackballEvent(ev: MotionEvent): Boolean {
        return super.dispatchTrackballEvent(ev)
    }

    override fun dispatchGenericMotionEvent(ev: MotionEvent): Boolean {
        return super.dispatchGenericMotionEvent(ev)
    }

    override fun dispatchPopulateAccessibilityEvent(event: AccessibilityEvent): Boolean {
        return super.dispatchPopulateAccessibilityEvent(event)
    }

    override fun onCreatePanelView(featureId: Int): View? {
        return super.onCreatePanelView(featureId)
    }

    override fun onCreatePanelMenu(featureId: Int, menu: Menu): Boolean {
        return super.onCreatePanelMenu(featureId, menu)
    }

    override fun onPreparePanel(featureId: Int, view: View?, menu: Menu): Boolean {
        return super.onPreparePanel(featureId, view, menu)
    }

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        return super.onMenuOpened(featureId, menu)
    }

    override fun onMenuItemSelected(featureId: Int, item: MenuItem): Boolean {
        return super.onMenuItemSelected(featureId, item)
    }

    override fun onPanelClosed(featureId: Int, menu: Menu) {
        super.onPanelClosed(featureId, menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
    }

    override fun openOptionsMenu() {
        super.openOptionsMenu()
    }

    override fun closeOptionsMenu() {
        super.closeOptionsMenu()
    }

    override fun invalidateOptionsMenu() {
        super.invalidateOptionsMenu()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun registerForContextMenu(view: View) {
        super.registerForContextMenu(view)
    }

    override fun unregisterForContextMenu(view: View) {
        super.unregisterForContextMenu(view)
    }

    override fun openContextMenu(view: View) {
        super.openContextMenu(view)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return super.onContextItemSelected(item)
    }

    override fun onContextMenuClosed(menu: Menu) {
        super.onContextMenuClosed(menu)
    }

    override fun onSearchRequested(searchEvent: SearchEvent): Boolean {
        return super.onSearchRequested(searchEvent)
    }

    override fun onSearchRequested(): Boolean {
        return super.onSearchRequested()
    }

    override fun onWindowStartingActionMode(callback: ActionMode.Callback?): ActionMode? {
        return super.onWindowStartingActionMode(callback)
    }

    override fun onWindowStartingActionMode(
        callback: ActionMode.Callback?,
        type: Int
    ): ActionMode? {
        return super.onWindowStartingActionMode(callback, type)
    }

    override fun onActionModeStarted(mode: ActionMode?) {
        super.onActionModeStarted(mode)
    }

    override fun onActionModeFinished(mode: ActionMode?) {
        super.onActionModeFinished(mode)
    }

    override fun cancel() {
        super.cancel()
    }

    //===============================================================
    //===============================================================

    open fun 创建回调(保存实例状态: Bundle?){
        super.onCreate(保存实例状态)
    }

    open fun 启动回调(){
        super.onStart()
    }

    open fun 停止回调(){
        super.onStop()
    }



}

//===============================================================
//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取上下文
 *
 * 版本：0.1.6
 */
fun Dialog.取上下文() : Context = this.getContext()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取操作栏
 *
 * 版本：0.1.6
 */
fun Dialog.取操作栏() : ActionBar? = this.getActionBar()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置所属活动
 *
 * 版本：0.1.6
 */
fun Dialog.置所属活动(活动 : Activity)  = this.setOwnerActivity(活动)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取所属活动
 *
 * 版本：0.1.6
 */
fun Dialog.取所属活动() : Activity? = this.getOwnerActivity()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：是否正在显示
 *
 * 版本：0.1.6
 */
fun Dialog.是否正在显示(): Boolean = this.isShowing()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：创建警告对话框
 *
 * 版本：0.1.6
 */
fun Dialog.创建() = this.create()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：显示警告对话框
 *
 * 版本：0.1.6
 */
fun Dialog.显示() = this.show()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：隐藏警告对话框
 *
 * 版本：0.1.6
 */
fun Dialog.隐藏() = this.hide()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：关闭警告对话框
 *
 * 版本：0.1.6
 */
fun Dialog.关闭() = this.dismiss()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：保存实例状态回调
 *
 * 版本：0.1.6
 */
fun Dialog.保存实例状态回调(): Bundle = this.onSaveInstanceState()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：恢复实例状态
 *
 * 版本：0.1.6
 */
fun Dialog.恢复实例状态(保存实例状态: Bundle) = this.onRestoreInstanceState(保存实例状态)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取窗口
 *
 * 版本：0.1.6
 */
fun Dialog.取窗口(): Window? = this.getWindow()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取当前焦点
 *
 * 版本：0.1.6
 */
fun Dialog.取当前焦点(): View? = this.getCurrentFocus()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：查找视图Id
 *
 * 版本：0.1.6
 */
fun <T : View> Dialog.查找视图Id(@IdRes id: Int): T? = this.findViewById<T>(id)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：需要视图ID,仅支持Android 9及以上使用
 *
 * 版本：0.1.6
 */
@RequiresApi(Build.VERSION_CODES.P)
fun <T : View> Dialog.需要视图Id(@IdRes id: Int): T = this.requireViewById<T>(id)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置内容视图
 *
 * 版本：0.1.6
 */
fun Dialog.置内容视图(@LayoutRes 布局资源Id :Int) = this.setContentView(布局资源Id)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置内容视图
 *
 * 版本：0.1.6
 */
fun Dialog.置内容视图(视图 :View) = this.setContentView(视图)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置内容视图
 *
 * 版本：0.1.6
 */
fun Dialog.置内容视图(视图 :View, 参数 : ViewGroup.LayoutParams) = this.setContentView(视图,参数)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：添加内容视图
 *
 * 版本：0.1.6
 */
fun Dialog.添加内容视图(视图 :View, 参数 : ViewGroup.LayoutParams) = this.addContentView(视图,参数)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置标题
 *
 * 版本：0.1.6
 */
fun Dialog.置标题(标题 : CharSequence) = this.setTitle(标题)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置标题
 *
 * 版本：0.1.6
 */
fun Dialog.置标题(@StringRes 标题Id :Int) = this.setTitle(标题Id)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取搜索事件
 *
 * 版本：0.1.6
 */
fun Dialog.取搜索事件() :SearchEvent? = this.getSearchEvent()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：拿按键事件
 *
 * 版本：0.1.6
 */
fun Dialog.拿按键事件(取 :Boolean) = this.takeKeyEvents(取)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：请求窗口特性
 *
 * 版本：0.1.6
 */
fun Dialog.请求窗口特性(特性Id :Int) :Boolean = this.requestWindowFeature(特性Id)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置特性图形资源
 *
 * 版本：0.1.6
 */
fun Dialog.置特性图形资源(特性Id :Int, @DrawableRes 资源Id :Int) = this.setFeatureDrawableResource(特性Id,资源Id)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置特性图形Uri
 *
 * 版本：0.1.6
 */
fun Dialog.置特性图形Uri(特性Id :Int, uri : Uri) = this.setFeatureDrawableUri(特性Id,uri)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置特性图形
 *
 * 版本：0.1.6
 */
fun Dialog.置特性图形(特性Id :Int, 图形 : Drawable) = this.setFeatureDrawable(特性Id,图形)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置特性图形透明度
 *
 * 版本：0.1.6
 */
fun Dialog.置特性图形透明度(特性Id :Int, 透明度 : Int) = this.setFeatureDrawableAlpha(特性Id,透明度)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取布局填充器
 *
 * 版本：0.1.6
 */
fun Dialog.取布局填充器(): LayoutInflater = this.getLayoutInflater()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置可取消
 *
 * 版本：0.1.6
 */
fun Dialog.置可取消(标志 : Boolean) = this.setCancelable(标志)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置取消触摸外部
 *
 * 版本：0.1.6
 */
fun Dialog.置取消触摸外部(取消 : Boolean) = this.setCanceledOnTouchOutside(取消)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置可取消监听器
 *
 * 版本：0.1.6
 */
fun Dialog.置可取消监听器(监听器 : DialogInterface.OnCancelListener?) = this.setOnCancelListener(监听器)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置取消消息
 *
 * 版本：0.1.6
 */
fun Dialog.置取消消息(信息 : Message) = this.setCancelMessage(信息)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置关闭监听器
 *
 * 版本：0.1.6
 */
fun Dialog.置关闭监听器(监听器 : DialogInterface.OnDismissListener?) = this.setOnDismissListener(监听器)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置显示监听器
 *
 * 版本：0.1.6
 */
fun Dialog.置显示监听器(监听器 : DialogInterface.OnShowListener?) = this.setOnShowListener(监听器)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置关闭消息
 *
 * 版本：0.1.6
 */
fun Dialog.置关闭消息(信息 : Message) = this.setDismissMessage(信息)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置音量控制流
 *
 * 版本：0.1.6
 */
fun Dialog.置音量控制流(流类型 : Int) = this.setVolumeControlStream(流类型)

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取音量控制流
 *
 * 版本：0.1.6
 */
fun Dialog.取音量控制流() : Int = this.getVolumeControlStream()

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：置按键监听器
 *
 * 版本：0.1.6
 */
fun Dialog.置按键监听器(监听器 : DialogInterface.OnKeyListener?) = this.setOnKeyListener(监听器)

//===============================================================

/**
 * 创建时间：2025年12月6日.
 *
 * 描述：取返回事件调度器,仅支持Android 13及以上使用
 *
 * 版本：0.1.6
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun Dialog.取返回事件调度器(): OnBackInvokedDispatcher = this.getOnBackInvokedDispatcher()

//===============================================================
