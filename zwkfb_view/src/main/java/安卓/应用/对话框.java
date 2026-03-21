package 安卓.应用;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.window.OnBackInvokedDispatcher;

import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：对话框
 * @author dxyc
 */

public class 对话框 extends Dialog {

    public 对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 对话框(@NonNull Context 上下文, boolean 可取消, @Nullable OnCancelListener 取消监听器) {
        super(上下文, 可取消, 取消监听器);
    }

    public 对话框(@NonNull Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }

    //=============================================================================================

    public final Context 取上下文() {
        return this.getContext();
    }

    public @Nullable ActionBar 取操作栏() {
        return this.getActionBar();
    }

    public final void 置所有者活动(@NonNull Activity 活动) {
        this.setOwnerActivity(活动);
    }

    public final @Nullable Activity 取所有者活动() {
        return this.getOwnerActivity();
    }

    public boolean 是否正在显示() {
        return this.isShowing();
    }

    public void 创建() {
        this.create();
    }

    public void 显示() {
        this.show();
    }

    public void 隐藏() {
        this.hide();
    }

    @Override
    public void dismiss() {
        this.关闭();
    }

    public void 关闭() {
        super.dismiss();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.创建(savedInstanceState);
    }

    protected void 创建(Bundle 已保存实例状态) {
        super.onCreate(已保存实例状态);
    }


    @Override
    protected void onStart() {
        this.开始();
    }

    protected void 开始() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        this.停止();
    }

    protected void 停止() {
        super.onStop();
    }

    @NonNull
    @Override
    public Bundle onSaveInstanceState() {
        return this.保存实例状态();
    }

    public Bundle 保存实例状态() {
        return super.onSaveInstanceState();
    }

    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        this.恢复实例状态(savedInstanceState);
    }

    public void 恢复实例状态(@NonNull Bundle 保存实例状态) {
        super.onRestoreInstanceState(保存实例状态);
    }


    public @Nullable Window 取窗口() {
        return this.getWindow();
    }


    public @Nullable View 取当前焦点() {
        return this.getCurrentFocus();
    }

    public <T extends View> T 查找视图Id(@IdRes int id) {
        return this.findViewById(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    @NonNull
    public final <T extends View> T 需要查找视图Id(@IdRes int id) {
        return this.requireViewById(id);
    }



    public void 置内容视图(@LayoutRes int 布局资源Id) {
        this.setContentView(布局资源Id);
    }


    public void 置内容视图(@NonNull View 视图) {
        this.setContentView(视图);
    }

    public void 置内容视图(@NonNull View 视图, @Nullable ViewGroup.LayoutParams 参数) {
        this.setContentView(视图, 参数);
    }

    public void 添加内容视图(@NonNull View 视图, @Nullable ViewGroup.LayoutParams 参数) {
        this.addContentView(视图, 参数);
    }



    public void 置标题(@Nullable CharSequence 标题) {
        this.setTitle(标题);
    }


    public void 置标题(@StringRes int 标题Id) {
        this.setTitle(标题Id);
    }


    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        return this.按键按下(keyCode, event);
    }

    public boolean 按键按下(int 键码, @NonNull KeyEvent 事件) {
        return super.onKeyDown(键码, 事件);
    }


    @Override
    public boolean onKeyLongPress(int keyCode, @NonNull KeyEvent event) {
        return this.按键长按(keyCode, event);
    }

    public boolean 按键长按(int 键码, @NonNull KeyEvent 事件) {
        return super.onKeyLongPress(键码, 事件);
    }


    @Override
    public boolean onKeyUp(int keyCode, @NonNull KeyEvent event) {
        return this.按键抬起(keyCode, event);
    }

    public boolean 按键抬起(int 键码, @NonNull KeyEvent 事件) {
        return super.onKeyUp(键码, 事件);
    }


    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, @NonNull KeyEvent event) {
        return this.按键重复(keyCode, repeatCount, event);
    }


    public boolean 按键重复(int 键码, int 重复次数, @NonNull KeyEvent 事件) {
        return super.onKeyMultiple(键码, 重复次数, 事件);
    }

    @Override
    public void onBackPressed() {
        this.按返回键();
    }

    public void 按返回键() {
        super.onBackPressed();
    }


    @Override
    public boolean onKeyShortcut(int keyCode, @NonNull KeyEvent event) {
        return this.按键快捷键(keyCode, event);
    }


    public boolean 按键快捷键(int 键码, @NonNull KeyEvent 事件) {
        return super.onKeyShortcut(键码, 事件);
    }


    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        return this.触摸事件(event);
    }

    public boolean 触摸事件(@NonNull MotionEvent 事件) {
        return super.onTouchEvent(事件);
    }


    @Override
    public boolean onTrackballEvent(@NonNull MotionEvent event) {
        return this.轨迹球事件(event);
    }

    public boolean 轨迹球事件(@NonNull MotionEvent 事件) {
        return super.onTrackballEvent(事件);
    }


    @Override
    public boolean onGenericMotionEvent(@NonNull MotionEvent event) {
        return this.通用运动事件(event);
    }

    public boolean 通用运动事件(@NonNull MotionEvent 事件) {
        return super.onGenericMotionEvent(事件);
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        this.窗口属性改变(params);
    }

    public void 窗口属性改变(WindowManager.LayoutParams 参数) {
        super.onWindowAttributesChanged(参数);
    }


    @Override
    public void onContentChanged() {
        this.内容已改变();
    }

    public void 内容已改变() {
        super.onContentChanged();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        this.窗口焦点改变(hasFocus);
    }

    public void 窗口焦点改变(boolean 焦点) {
        super.onWindowFocusChanged(焦点);
    }


    @Override
    public void onAttachedToWindow() {
        this.附加到窗口();
    }

    public void 附加到窗口() {
        super.onAttachedToWindow();
    }


    @Override
    public void onDetachedFromWindow() {
        this.分离从窗口();
    }

    public void 分离从窗口() {
        super.onDetachedFromWindow();
    }


    @Override
    public boolean dispatchKeyEvent(@NonNull KeyEvent event) {
        return this.分发按键事件(event);
    }

    public boolean 分发按键事件(@NonNull KeyEvent 事件) {
        return super.dispatchKeyEvent(事件);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(@NonNull KeyEvent event) {
        return this.分发键盘快捷键事件(event);
    }

    public boolean 分发键盘快捷键事件(@NonNull KeyEvent 事件) {
        return super.dispatchKeyShortcutEvent(事件);
    }


    @Override
    public boolean dispatchTouchEvent(@NonNull MotionEvent ev) {
        return this.分发触摸事件(ev);
    }

    public boolean 分发触摸事件(@NonNull MotionEvent 事件) {
        return super.dispatchTouchEvent(事件);
    }

    @Override
    public boolean dispatchTrackballEvent(@NonNull MotionEvent ev) {
        return this.分发轨迹球事件(ev);
    }

    public boolean 分发轨迹球事件(@NonNull MotionEvent 事件) {
        return super.dispatchTrackballEvent(事件);
    }


    @Override
    public boolean dispatchGenericMotionEvent(@NonNull MotionEvent ev) {
        return this.分发通用动作事件(ev);
    }

    public boolean 分发通用动作事件(@NonNull MotionEvent 事件) {
        return super.dispatchGenericMotionEvent(事件);
    }


    @Override
    public boolean dispatchPopulateAccessibilityEvent(@NonNull AccessibilityEvent event) {
        return this.分发填充辅助功能事件(event);
    }

    public boolean 分发填充辅助功能事件(@NonNull AccessibilityEvent 事件) {
        return super.dispatchPopulateAccessibilityEvent(事件);
    }


    @Override
    public View onCreatePanelView(int featureId) {
        return this.创建面板视图(featureId);
    }

    public View 创建面板视图(int 特征Id) {
        return super.onCreatePanelView(特征Id);
    }


    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        return this.创建面板菜单(featureId, menu);
    }

    public boolean 创建面板菜单(int 特征Id, @NonNull Menu 菜单) {
        return super.onCreatePanelMenu(特征Id, 菜单);
    }


    @Override
    public boolean onPreparePanel(int featureId, @Nullable View view, @NonNull Menu menu) {
        return this.准备面板(featureId, view, menu);
    }

    public boolean 准备面板(int 特征Id, @Nullable View 视图, @NonNull Menu 菜单) {
        return super.onPreparePanel(特征Id, 视图, 菜单);
    }


    @Override
    public boolean onMenuOpened(int featureId, @NonNull Menu menu) {
        return this.菜单已打开(featureId, menu);
    }

    public boolean 菜单已打开(int 特征Id, @NonNull Menu 菜单) {
        return super.onMenuOpened(特征Id, 菜单);
    }


    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
        return this.菜单项被选中(featureId, item);
    }

    public boolean 菜单项被选中(int 特征Id, @NonNull MenuItem 项目) {
        return super.onMenuItemSelected(特征Id, 项目);
    }


    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        this.面板关闭(featureId, menu);
    }

    public void 面板关闭(int 特征Id, @NonNull Menu 菜单) {
        super.onPanelClosed(特征Id, 菜单);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        return this.创建选项菜单(menu);
    }

    public boolean 创建选项菜单(@NonNull Menu 菜单) {
        return super.onCreateOptionsMenu(菜单);
    }

    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        return this.准备选项菜单(menu);
    }

    public boolean 准备选项菜单(@NonNull Menu 菜单) {
        return super.onPrepareOptionsMenu(菜单);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return this.选项菜单项被选中(item);
    }

    public boolean 选项菜单项被选中(@NonNull MenuItem 项目) {
        return super.onOptionsItemSelected(项目);
    }


    @Override
    public void onOptionsMenuClosed(@NonNull Menu menu) {
        this.选项菜单关闭(menu);
    }

    public void 选项菜单关闭(@NonNull Menu 菜单) {
        super.onOptionsMenuClosed(菜单);
    }


    public void 打开选项菜单() {
        this.openOptionsMenu();
    }

    public void 关闭选项菜单() {
        this.closeOptionsMenu();
    }

    public void 无效使选项菜单() {
        this.invalidateOptionsMenu();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        this.创建上下文菜单(menu, v, menuInfo);
    }

    public void 创建上下文菜单(ContextMenu 菜单, View 视图, ContextMenu.ContextMenuInfo 菜单信息) {
        super.onCreateContextMenu(菜单, 视图, 菜单信息);
    }


    public void 注册上下文菜单(@NonNull View 视图) {
        this.registerForContextMenu(视图);
    }

    public void 取消注册上下文菜单(@NonNull View 视图) {
        this.unregisterForContextMenu(视图);
    }

    public void 打开上下文菜单(@NonNull View 视图) {
        this.openContextMenu(视图);
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return this.上下文菜单项选中(item);
    }

    public boolean 上下文菜单项选中(@NonNull MenuItem 项目) {
        return super.onContextItemSelected(项目);
    }


    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        this.上下文菜单关闭(menu);
    }

    public void 上下文菜单关闭(@NonNull Menu 菜单) {
        super.onContextMenuClosed(菜单);
    }


    @Override
    public boolean onSearchRequested(@NonNull SearchEvent searchEvent) {
        return this.请求搜索(searchEvent);
    }

    public boolean 请求搜索(@NonNull SearchEvent 搜索事件) {
        return super.onSearchRequested(搜索事件);
    }

    @Override
    public boolean onSearchRequested() {
        return this.请求搜索();
    }

    public boolean 请求搜索() {
        return super.onSearchRequested();
    }


    public final @Nullable SearchEvent 取搜索事件() {
        return this.getSearchEvent();
    }


    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.窗口开始操作模式(callback);
    }

    public ActionMode 窗口开始操作模式(ActionMode.Callback 回调) {
        return super.onWindowStartingActionMode(回调);
    }


    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return this.窗口开始操作模式(callback, type);
    }

    public ActionMode 窗口开始操作模式(ActionMode.Callback 回调, int 类型) {
        return super.onWindowStartingActionMode(回调, 类型);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onActionModeStarted(ActionMode mode) {
        this.操作模式开始(mode);
    }

    public void 操作模式开始(ActionMode 模式) {
        super.onActionModeStarted(模式);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onActionModeFinished(ActionMode mode) {
        this.操作模式完成(mode);
    }

    public void 操作模式完成(ActionMode 模式) {
        super.onActionModeFinished(模式);
    }


    public void 接收按键事(boolean 取) {
        this.takeKeyEvents(取);
    }


    public final boolean 请求窗口特性(int 特性Id) {
        return this.requestWindowFeature(特性Id);
    }

    public final void 置特性可绘制资源(int 特性Id, @DrawableRes int 资源Id) {
        this.setFeatureDrawableResource(特性Id, 资源Id);
    }

    public final void 置特性可绘制网址(int 特性Id, @Nullable Uri 网址) {
        this.setFeatureDrawableUri(特性Id, 网址);
    }

    public final void 置特性可绘制(int 特性Id, @Nullable Drawable 可绘制) {
        this.setFeatureDrawable(特性Id, 可绘制);
    }

    public final void 置特性可绘制透明度(int 特性Id, int 透明度) {
        this.setFeatureDrawableAlpha(特性Id, 透明度);
    }


    public @NonNull LayoutInflater 取布局填充器() {
        return this.getLayoutInflater();
    }

    public void 置可取消(boolean 标志) {
        this.setCancelable(标志);
    }

    public void 置触摸外部取消(boolean 取消) {
        this.setCanceledOnTouchOutside(取消);
    }


    @Override
    public void cancel() {
        this.取消();
    }

    public void 取消() {
        super.cancel();
    }

    public void 置取消监听器(@Nullable OnCancelListener 监听器) {
        this.setOnCancelListener(监听器);
    }

    public void 置取消消息(@Nullable Message 信息) {
        this.setCancelMessage(信息);
    }

    public void 置关闭监听器(@Nullable OnDismissListener 监听器) {
        this.setOnDismissListener(监听器);
    }

    public void 置显示监听器(@Nullable OnShowListener 监听器) {
        this.setOnShowListener(监听器);
    }

    public void 置关闭消息(@Nullable Message 信息) {
        this.setDismissMessage(信息);
    }

    public final void 置音量控制流(int 流类型) {
        this.setVolumeControlStream(流类型);
    }

    public final int 取音量控制流() {
        return this.getVolumeControlStream();
    }


    public void 置按键监听器(@Nullable OnKeyListener 按键监听器) {
        this.setOnKeyListener(按键监听器);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @NonNull
    public OnBackInvokedDispatcher 取返回调用调度器() {
        return this.getOnBackInvokedDispatcher();
    }

}
