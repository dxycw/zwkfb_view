package 安卓.应用;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.ComponentCaller;
import android.app.Dialog;
import android.app.DirectAction;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureUiState;
import android.app.SharedElementCallback;
import android.app.TaskStackBuilder;
import android.app.VoiceInteractor;
import android.app.assist.AssistContent;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.LocusId;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.OutcomeReceiver;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toolbar;
import android.window.OnBackInvokedDispatcher;
import android.window.SplashScreen;

import androidx.annotation.AnimRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.Consumer;

/**
 * 创建时间：2025年11月18日.
 * <p>
 * 描述：活动
 * @author dxyc
 */
public class 活动 extends Activity {

    /** 标准活动结果：操作已取消。 */
    public static final int 结果_已取消 = Activity.RESULT_CANCELED;
    /** 标准活动结果：操作成功。 */
    public static final int 结果_确定 = Activity.RESULT_OK;
    /** 用户定义活动结果的开始。 */
    public static final int 结果_第一_用户 = Activity.RESULT_FIRST_USER;


    /** 请求类型 {@link #requestFullscreenMode(int, OutcomeReceiver)}, 请求退出*请求的全屏模式并恢复到之前的多窗口模式。
     */
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 全屏_模式_请求_退出 = Activity.FULLSCREEN_MODE_REQUEST_EXIT;
    /** 请求类型 {@link #requestFullscreenMode(int, OutcomeReceiver)}, 请求从多窗口模式进入全屏模式。
     */
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 全屏_模式_请求_进入 = Activity.FULLSCREEN_MODE_REQUEST_ENTER;


    /**
     * 请求类型 {@link #overrideActivityTransition(int, int, int)} 或
     * {@link #overrideActivityTransition(int, int, int, int)}, 覆盖开启过渡。
     */
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 覆盖_过渡_打开 = Activity.OVERRIDE_TRANSITION_OPEN;
    /**
     * 请求类型 {@link #overrideActivityTransition(int, int, int)} 或
     * {@link #overrideActivityTransition(int, int, int, int)}, 覆盖关闭过渡。
     */
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 覆盖_过渡_关闭 = Activity.OVERRIDE_TRANSITION_CLOSE;

    //===========================================================================================

    public Intent 取意图() {
        return this.getIntent();
    }

    public void 置意图(Intent 新意图) {
        this.setIntent(新意图);
    }


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public ComponentCaller 取调用者() {
        return this.getCaller();
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 置意图(Intent 新意图, ComponentCaller 新调用者) {
        this.setIntent(新意图, 新调用者);
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    public void 置焦点上下文(LocusId 焦点Id , Bundle 数据包) {
        this.setLocusContext(焦点Id, 数据包);
    }

    @RequiresApi(api = Build.VERSION_CODES.BAKLAVA)
    public final void 请求浏览器打开提示() {
        this.requestOpenInBrowserEducation();
    }

    public final Application 取应用程() {
        return this.getApplication();
    }

    public final boolean 是否子项() {
        return this.isChild();
    }

    public final Activity 取父级() {
        return this.getParent();
    }


    public WindowManager 取窗口管理器() {
        return this.getWindowManager();
    }

    public Window 取窗口() {
        return this.getWindow();
    }


    public LoaderManager 取加载器管理() {
        return this.getLoaderManager();
    }


    public View 取当前焦点() {
        return this.getCurrentFocus();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        this.附加基础上下文(newBase);
    }

    protected void 附加基础上下文(Context 新基础) {
        super.attachBaseContext(新基础);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 注册活动生命周期回调(Application.ActivityLifecycleCallbacks 回调) {
        this.registerActivityLifecycleCallbacks(回调);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 注销活动生命周期回调(Application.ActivityLifecycleCallbacks 回调) {
        this.unregisterActivityLifecycleCallbacks(回调);
    }


    public void 注册组件回调(ComponentCallbacks 回调) {
        this.registerComponentCallbacks(回调);
    }

    public void 注销组件回调(ComponentCallbacks 回调) {
        this.unregisterComponentCallbacks(回调);
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.创建(savedInstanceState);
    }

    protected void 创建(@Nullable Bundle 已保存实例状态) {
        super.onCreate(已保存实例状态);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public final SplashScreen 取启动屏() {
        return this.getSplashScreen();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        this.创建(savedInstanceState, persistentState);
    }

    public void 创建(@Nullable Bundle 已保存实例状态, @Nullable PersistableBundle 持久化状态) {
        super.onCreate(已保存实例状态, 持久化状态);
    }


    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        this.恢复实例状态(savedInstanceState);
    }


    protected void 恢复实例状态(@NonNull Bundle 已保存实例状态) {
        super.onRestoreInstanceState(已保存实例状态);
    }


    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        this.恢复实例状态(savedInstanceState, persistentState);
    }

    public void 恢复实例状态(@Nullable Bundle 已保存实例状态, @Nullable PersistableBundle 持久化状态) {
        super.onRestoreInstanceState(已保存实例状态, 持久化状态);
    }


    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.创建后(savedInstanceState);
    }

    public void 创建后(@Nullable Bundle 已保存实例状态) {
        super.onPostCreate(已保存实例状态);
    }


    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        this.创建后(savedInstanceState, persistentState);
    }


    public void 创建后(@Nullable Bundle 已保存实例状态, @Nullable PersistableBundle 持久化状态) {
        super.onPostCreate(已保存实例状态, 持久化状态);
    }


    @Override
    protected void onStart() {
        super.onStart();
        this.开始();
    }

    protected void 开始() {
        super.onStart();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        this.重启();
    }

    protected void 重启 () {
        super.onRestart();
    }


    @Override
    public void onStateNotSaved() {
        super.onStateNotSaved();
        this.状态未保存();
    }

    public void 状态未保存() {
        super.onStateNotSaved();
    }


    @Override
    protected void onResume() {
        super.onResume();
        this.恢复();
    }

    protected void 恢复() {
        super.onResume();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        this.恢复后();
    }

    protected void 恢复后() {
        super.onPostResume();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 顶部恢复活动已更改(boolean 是否顶部恢复活动) {
        this.onTopResumedActivityChanged(是否顶部恢复活动);
    }

    public boolean 是否语音交互() {
        return this.isVoiceInteraction();
    }

    public boolean 是否语音交互根() {
        return this.isVoiceInteractionRoot();
    }

    public VoiceInteractor 取语音交互器() {
        return this.getVoiceInteractor();
    }

    public boolean 是否支持本地语音交互() {
        return this.isLocalVoiceInteractionSupported();
    }

    public void 开始本地语音交互(Bundle 私有选项) {
        this.startLocalVoiceInteraction(私有选项);
    }


    @Override
    public void onLocalVoiceInteractionStarted() {
        super.onLocalVoiceInteractionStarted();
        this.本地语音交互已启动();
    }

    public void 本地语音交互已启动() {
        super.onLocalVoiceInteractionStarted();
    }

    @Override
    public void onLocalVoiceInteractionStopped() {
        super.onLocalVoiceInteractionStopped();
        this.本地语音交互已停止();
    }

    public void 本地语音交互已停止() {
        super.onLocalVoiceInteractionStopped();
    }

    public void 停止本地语音交互() {
        this.stopLocalVoiceInteraction();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.新意图(intent);
    }

    protected void 新意图(Intent 意图) {
        super.onNewIntent(意图);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @Override
    public void onNewIntent(@NonNull Intent intent, @NonNull ComponentCaller caller) {
        super.onNewIntent(intent, caller);
        this.新意图(intent, caller);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 新意图(@NonNull Intent 意图, @NonNull ComponentCaller 调用者) {
        super.onNewIntent(意图, 调用者);
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        this.保存实例状态(outState);
    }

    protected void 保存实例状态(@NonNull Bundle 输出状态) {
        super.onSaveInstanceState(输出状态);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        this.保存实例状态(outState, outPersistentState);
    }

    public void 保存实例状态(@NonNull Bundle 输出状态, @NonNull PersistableBundle 输出持久状态) {
        super.onSaveInstanceState(输出状态, 输出持久状态);
    }


    @Override
    protected void onPause() {
        super.onPause();
        this.暂停();
    }

    protected void 暂停() {
        super.onPause();
    }


    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        this.用户离开提示();
    }

    protected void 用户离开提示() {
        super.onUserLeaveHint();
    }


    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        return this.创建缩略图(outBitmap, canvas);
    }

    public boolean 创建缩略图(Bitmap 输出位图, Canvas 画布) {
        return super.onCreateThumbnail(输出位图, 画布);
    }


    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        return this.创建描述();
    }


    public CharSequence 创建描述() {
        return super.onCreateDescription();
    }


    @Override
    public void onProvideAssistData(Bundle data) {
        super.onProvideAssistData(data);
        this.提供辅助数据(data);
    }

    public void 提供辅助数据(Bundle 数据) {
        super.onProvideAssistData(数据);
    }


    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        super.onProvideAssistContent(outContent);
        this.提供辅助内容(outContent);
    }

    public void 提供辅助内容(AssistContent 输出内容) {
        super.onProvideAssistContent(输出内容);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onGetDirectActions(@NonNull CancellationSignal cancellationSignal, @NonNull Consumer<List<DirectAction>> callback) {
        super.onGetDirectActions(cancellationSignal, callback);
        this.取直接操作(cancellationSignal,callback);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 取直接操作(@NonNull CancellationSignal 取消信号, @NonNull Consumer<List<DirectAction>> 回调) {
        super.onGetDirectActions(取消信号, 回调);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onPerformDirectAction(@NonNull String actionId, @NonNull Bundle arguments, @NonNull CancellationSignal cancellationSignal, @NonNull Consumer<Bundle> resultListener) {
        super.onPerformDirectAction(actionId, arguments, cancellationSignal, resultListener);
        this.执行直接操作(actionId, arguments, cancellationSignal, resultListener);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 执行直接操作(@NonNull String 动作Id, @NonNull Bundle 参数, @NonNull CancellationSignal 取消信号, @NonNull Consumer<Bundle> 结果监听器) {
        super.onPerformDirectAction(动作Id, 参数, 取消信号, 结果监听器);
    }



    public final void 请求显示键盘快捷键() {
        this.requestShowKeyboardShortcuts();
    }

    public final void 关闭键盘快捷键帮助() {
        this.dismissKeyboardShortcutsHelper();
    }


    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
        this.提供键盘快捷键(data, menu, deviceId);
    }

    public void 提供键盘快捷键(List<KeyboardShortcutGroup> 数据, Menu 菜单, int 设备Id) {
        super.onProvideKeyboardShortcuts(数据, 菜单, 设备Id);
    }


    public boolean 显示辅助(Bundle 参数) {
        return this.showAssist(参数);
    }


    @Override
    protected void onStop() {
        super.onStop();
        this.停止();
    }

    protected void 停止() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.销毁();
    }

    protected void 销毁() {
        super.onDestroy();
    }


    @Override
    public void reportFullyDrawn() {
        super.reportFullyDrawn();
        this.报告完全绘制();
    }

    public void 报告完全绘制() {
        super.reportFullyDrawn();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        this.多窗口模式已改变(isInMultiWindowMode, newConfig);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 多窗口模式已改变(boolean 是否多窗口模式, Configuration 新配置) {
        super.onMultiWindowModeChanged(是否多窗口模式, 新配置);
    }


    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        this.多窗口模式已改变(isInMultiWindowMode);
    }

    public void 多窗口模式已改变(boolean 是否多窗口模式) {
        super.onMultiWindowModeChanged(是否多窗口模式);
    }


    public boolean 是否在多窗口模式() {
        return this.isInMultiWindowMode();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        this.画中画模式改变(isInPictureInPictureMode, newConfig);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 画中画模式改变(boolean 是否处于画中画模式, Configuration 新配置) {
        super.onPictureInPictureModeChanged(是否处于画中画模式, 新配置);
    }


    @RequiresApi(api = Build.VERSION_CODES.S)
    @Override
    public void onPictureInPictureUiStateChanged(@NonNull PictureInPictureUiState pipState) {
        super.onPictureInPictureUiStateChanged(pipState);
        this.画中画界面状态已改变(pipState);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void 画中画界面状态已改变(@NonNull PictureInPictureUiState pip状态) {
        super.onPictureInPictureUiStateChanged(pip状态);
    }


    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        this.画中画模式改变(isInPictureInPictureMode);
    }

    public void 画中画模式改变(boolean 是否处于画中画模式) {
        super.onPictureInPictureModeChanged(是否处于画中画模式);
    }


    @Override
    public boolean isInPictureInPictureMode() {
        return this.是否处于画中画模式();
    }

    public boolean 是否处于画中画模式() {
        return super.isInPictureInPictureMode();
    }


    @Override
    public void enterPictureInPictureMode() {
        super.enterPictureInPictureMode();
        this.进入画中画模式();
    }

    public void 进入画中画模式() {
        super.enterPictureInPictureMode();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean enterPictureInPictureMode(@NonNull PictureInPictureParams params) {
        return this.进入画中画模式(params);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean 进入画中画模式(@NonNull PictureInPictureParams 参数) {
        return super.enterPictureInPictureMode(参数);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 置画中画参数(@NonNull PictureInPictureParams 参数) {
        this.setPictureInPictureParams(参数);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public int 取最大数量画中画操作() {
        return this.getMaxNumPictureInPictureActions();
    }


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    public boolean onPictureInPictureRequested() {
        return this.请求画中画();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public boolean 请求画中画() {
        return super.onPictureInPictureRequested();
    }


    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @Override
    public void requestFullscreenMode(int request, @Nullable OutcomeReceiver<Void, Throwable> approvalCallback) {
        super.requestFullscreenMode(request, approvalCallback);
        this.请求全屏模式(request, approvalCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 请求全屏模式(int 请求, @Nullable OutcomeReceiver<Void, Throwable> 审批回调) {
        super.requestFullscreenMode(请求, 审批回调);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void 置大覆盖层停靠(boolean 应停靠大覆盖层) {
        this.setShouldDockBigOverlays(应停靠大覆盖层);
    }


    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public boolean 应停靠大覆盖层() {
        return this.shouldDockBigOverlays();
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.配置已改变(newConfig);
    }

    public void 配置已改变(@NonNull Configuration 新配置) {
        super.onConfigurationChanged(新配置);
    }


    public int 取改变配置() {
        return this.getChangingConfigurations();
    }



    public Object 取上次非配置实例() {
        return this.getLastNonConfigurationInstance();
    }


    @Override
    public Object onRetainNonConfigurationInstance() {
        return this.保留非配置实例();
    }

    public Object 保留非配置实例() {
        return super.onRetainNonConfigurationInstance();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.内存不足();
    }

    public void 内存不足() {
        super.onLowMemory();
    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        this.内存回收(level);
    }

    public void 内存回收(int 等级) {
        super.onTrimMemory(等级);
    }


    public FragmentManager 取碎片管理器() {
        return this.getFragmentManager();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        this.附加碎片(fragment);
    }

    public void 附加碎片(Fragment 碎片) {
        super.onAttachFragment(碎片);
    }


    public final Cursor 托管查询(Uri 网址, String[] 投影, String 选择, String[] 选择参数, String 排序顺序) {
        return this.managedQuery(网址, 投影, 选择, 选择参数, 排序顺序);
    }



    public void 开始管理光标(Cursor 光标) {
        this.startManagingCursor(光标);
    }

    public void 停止管理光标(Cursor 光标) {
        this.stopManagingCursor(光标);
    }



    public <T extends View> T 查找视图通过Id(@IdRes int id) {
        return this.findViewById(id);
    }


    public final <T extends View> T 要求视图通过Id(@IdRes int id) {
        return this.findViewById(id);
    }

    public ActionBar 取操作栏() {
        return this.getActionBar();
    }

    public void 置操作栏(@Nullable Toolbar 工具栏) {
        this.setActionBar(工具栏);
    }


    public void 置内容视图(@LayoutRes int 布局资源ID) {
        this.setContentView(布局资源ID);
    }

    public void 置内容视图(View 视图) {
        this.setContentView(视图);
    }

    public void 置内容视图(View 视图, ViewGroup.LayoutParams 参数) {
        this.setContentView(视图, 参数);
    }

    public void 添加内容视图(View 视图, ViewGroup.LayoutParams 参数) {
        this.addContentView(视图, 参数);
    }

    public TransitionManager 取内容过渡管理器() {
        return this.getContentTransitionManager();
    }

    public void 置内容过渡管理器(TransitionManager 内容过渡管理器) {
        this.setContentTransitionManager(内容过渡管理器);
    }

    public Scene 取内容场景() {
        return this.getContentScene();
    }

    public void 置完成触摸外部(boolean 完成) {
        this.setFinishOnTouchOutside(完成);
    }

    public static final int 默认_键_禁用 = Activity.DEFAULT_KEYS_DISABLE;

    public static final int 默认_键_拨号器 = Activity.DEFAULT_KEYS_DIALER;

    public static final int 默认_键_快捷方式 = Activity.DEFAULT_KEYS_SHORTCUT;

    public static final int 默认_键_搜索_本地 = Activity.DEFAULT_KEYS_SEARCH_LOCAL;

    public static final int 默认_键_搜索_全球 = Activity.DEFAULT_KEYS_SEARCH_GLOBAL;


    public final void 置默认键模式(int 模式) {
        this.setDefaultKeyMode(模式);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return this.按键按下(keyCode, event);
    }

    public boolean 按键按下(int 键码, KeyEvent 事件) {
        return super.onKeyDown(键码, 事件);
    }


    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return this.按键长按(keyCode, event);
    }

    public boolean 按键长按(int 键码, KeyEvent 事件) {
        return super.onKeyLongPress(键码, 事件);
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return this.按键抬起(keyCode, event);
    }

    public boolean 按键抬起(int 键码, KeyEvent 事件) {
        return super.onKeyUp(键码, 事件);
    }


    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return this.按键多次(keyCode, repeatCount, event);
    }

    public boolean 按键多次(int 键码, int 重复次数, KeyEvent 事件) {
        return super.onKeyMultiple(键码, 重复次数, 事件);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.按返回键();
    }

    public void 按返回键() {
        super.onBackPressed();
    }


    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        return this.按键快捷键(keyCode, event);
    }

    public boolean 按键快捷键(int 键码, KeyEvent 事件) {
        return super.onKeyShortcut(键码, 事件);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.触摸事件(event);
    }

    public boolean 触摸事件(MotionEvent 事件) {
        return super.onTouchEvent(事件);
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        return this.轨迹球事件(event);
    }

    public boolean 轨迹球事件(MotionEvent 事件) {
        return super.onTrackballEvent(事件);
    }


    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return this.通用动作事件(event);
    }

    public boolean 通用动作事件(MotionEvent 事件) {
        return super.onGenericMotionEvent(事件);
    }


    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        this.用户交互();
    }

    public void 用户交互() {
        super.onUserInteraction();
    }


    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        super.onWindowAttributesChanged(params);
        this.窗口属性改变(params);
    }

    public void 窗口属性改变(WindowManager.LayoutParams 参数) {
        super.onWindowAttributesChanged(参数);
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        this.内容改变();
    }

    public void 内容改变() {
        super.onContentChanged();
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        this.窗口焦点改变(hasFocus);
    }

    public void 窗口焦点改变(boolean 得焦点) {
        super.onWindowFocusChanged(得焦点);
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.附加到窗口();
    }

    public void 附加到窗口() {
        super.onAttachedToWindow();
    }


    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.从窗口分离();
    }


    public void 从窗口分离() {
        super.onDetachedFromWindow();
    }


    @Override
    public boolean hasWindowFocus() {
        return this.是否有窗口焦点();
    }

    public boolean 是否有窗口焦点() {
        return super.hasWindowFocus();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return this.分发按键事件(event);
    }

    public boolean 分发按键事件(KeyEvent 事件) {
        return super.dispatchKeyEvent(事件);
    }


    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return this.分发键盘快捷键事件(event);
    }

    public boolean 分发键盘快捷键事件(KeyEvent 事件) {
        return super.dispatchKeyShortcutEvent(事件);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return this.分发触摸事件(ev);
    }

    public boolean 分发触摸事件(MotionEvent 事件) {
        return super.dispatchTouchEvent(事件);
    }


    @Override
    public boolean dispatchTrackballEvent(MotionEvent ev) {
        return this.分发轨迹球事件(ev);
    }

    public boolean 分发轨迹球事件(MotionEvent 事件) {
        return super.dispatchTrackballEvent(事件);
    }


    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        return this.分发通用动作事件(ev);
    }


    public boolean 分发通用动作事件(MotionEvent 事件) {
        return super.dispatchGenericMotionEvent(事件);
    }


    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return this.分发填充辅助功能事件(event);
    }

    public boolean 分发填充辅助功能事件(AccessibilityEvent 事件) {
        return super.dispatchPopulateAccessibilityEvent(事件);
    }


    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        return this.创建面板视图(featureId);
    }

    public View 创建面板视图(int 特性Id) {
        return super.onCreatePanelView(特性Id);
    }


    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        return this.创建面板菜单(featureId, menu);
    }

    public boolean 创建面板菜单(int 特性Id, @NonNull Menu 菜单) {
        return super.onCreatePanelMenu(特性Id, 菜单);
    }


    @Override
    public boolean onPreparePanel(int featureId, @Nullable View view, @NonNull Menu menu) {
        return this.准备面板(featureId, view, menu);
    }


    public boolean 准备面板(int 特性Id, @Nullable View 视图, @NonNull Menu 菜单) {
        return super.onPreparePanel(特性Id, 视图, 菜单);
    }


    @Override
    public boolean onMenuOpened(int featureId, @NonNull Menu menu) {
        return this.菜单打开(featureId, menu);
    }

    public boolean 菜单打开(int 特性Id, @NonNull Menu 菜单) {
        return super.onMenuOpened(特性Id, 菜单);
    }


    @Override
    public boolean onMenuItemSelected(int featureId, @NonNull MenuItem item) {
        return this.菜单项被选中(featureId, item);
    }


    public boolean 菜单项被选中(int 特性Id, @NonNull MenuItem 项) {
        return super.onMenuItemSelected(特性Id, 项);
    }


    @Override
    public void onPanelClosed(int featureId, @NonNull Menu menu) {
        super.onPanelClosed(featureId, menu);
        this.面板关闭(featureId, menu);
    }


    public void 面板关闭(int 特性Id, @NonNull Menu 菜单) {
        super.onPanelClosed(特性Id, 菜单);
    }


    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
        this.使选项菜单失效();
    }

    public void 使选项菜单失效() {
        super.invalidateOptionsMenu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return this.创建选项菜单(menu);
    }


    public boolean 创建选项菜单(Menu 菜单) {
        return super.onCreateOptionsMenu(菜单);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return this.准备选项菜单(menu);
    }

    public boolean 准备选项菜单(Menu 菜单) {
        return super.onPrepareOptionsMenu(菜单);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return this.选项菜单项被选中(item);
    }


    public boolean 选项菜单项被选中(@NonNull MenuItem 项) {
        return super.onOptionsItemSelected(项);
    }


    @Override
    public boolean onNavigateUp() {
        return this.导航向上();
    }


    public boolean 导航向上() {
        return super.onNavigateUp();
    }


    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        return this.从子页面导航向上(child);
    }


    public boolean 从子页面导航向上(Activity 子) {
        return super.onNavigateUpFromChild(子);
    }


    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onCreateNavigateUpTaskStack(builder);
        this.创建向上导航任务栈(builder);
    }

    public void 创建向上导航任务栈(TaskStackBuilder 构建器) {
        super.onCreateNavigateUpTaskStack(构建器);
    }


    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        super.onPrepareNavigateUpTaskStack(builder);
        this.准备向上导航任务栈(builder);
    }

    public void 准备向上导航任务栈(TaskStackBuilder 构建器) {
        super.onPrepareNavigateUpTaskStack(构建器);
    }


    @Override
    public void onOptionsMenuClosed(Menu menu) {
        super.onOptionsMenuClosed(menu);
        this.选项菜单关闭(menu);
    }

    public void 选项菜单关闭(Menu 菜单) {
        super.onOptionsMenuClosed(菜单);
    }


    @Override
    public void openOptionsMenu() {
        super.openOptionsMenu();
        this.打开选项菜单();
    }

    public void 打开选项菜单() {
        super.openOptionsMenu();
    }


    @Override
    public void closeOptionsMenu() {
        super.closeOptionsMenu();
        this.关闭选项菜单();
    }

    public void 关闭选项菜单() {
        super.closeOptionsMenu();
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        this.创建上下文菜单(menu, v, menuInfo);
    }


    public void 创建上下文菜单(ContextMenu 菜单, View 视图, ContextMenu.ContextMenuInfo 菜单信息) {
        super.onCreateContextMenu(菜单, 视图, 菜单信息);
    }

    public void 注册上下文菜单(View 视图) {
        this.registerForContextMenu(视图);
    }

    public void 取消注册上下文菜单(View 视图) {
        this.unregisterForContextMenu(视图);
    }


    public void 打开上下文菜单(View 视图) {
        this.openContextMenu(视图);
    }


    public void 关闭上下文菜单() {
        this.closeContextMenu();
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        return this.上下文菜单项选中(item);
    }

    public boolean 上下文菜单项选中(@NonNull MenuItem 项) {
        return super.onContextItemSelected(项);
    }


    @Override
    public void onContextMenuClosed(@NonNull Menu menu) {
        super.onContextMenuClosed(menu);
        this.上下文菜单关闭(menu);
    }

    public void 上下文菜单关闭(@NonNull Menu 菜单) {
        super.onContextMenuClosed(菜单);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        return this.创建对话框(id);
    }

    protected Dialog 创建对话框(int id) {
        return super.onCreateDialog(id);
    }


    @Nullable
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        return this.创建对话框(id, args);
    }

    protected Dialog 创建对话框(int id, Bundle 参数) {
        return super.onCreateDialog(id, 参数);
    }


    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        this.准备对话框(id, dialog);
    }

    protected void 准备对话框(int id, Dialog 对话框) {
        super.onPrepareDialog(id, 对话框);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        super.onPrepareDialog(id, dialog, args);
        this.准备对话框(id, dialog, args);
    }


    protected void 准备对话框(int id, Dialog 对话框, Bundle 参数) {
        super.onPrepareDialog(id, 对话框, 参数);
    }



    public final void 显示对话框(int id) {
        this.showDialog(id);
    }


    public final void 显示对话框(int id, Bundle 参数) {
        this.showDialog(id, 参数);
    }


    public final void 关闭对话框(int id) {
        this.dismissDialog(id);
    }

    public final void 移除对话框(int id) {
        this.removeDialog(id);
    }


    @Override
    public boolean onSearchRequested(@Nullable SearchEvent searchEvent) {
        return this.请求搜索(searchEvent);
    }

    public boolean 请求搜索(@Nullable SearchEvent 搜索事件) {
        return super.onSearchRequested(搜索事件);
    }


    @Override
    public boolean onSearchRequested() {
        return this.请求搜索();
    }

    public boolean 请求搜索() {
        return super.onSearchRequested();
    }

    public final SearchEvent 取搜索事件() {
        return this.getSearchEvent();
    }


    public void 开始搜索(@Nullable String 初始查询, boolean 选择初始查询, @Nullable Bundle 应用搜索数据, boolean 全局搜索) {
        this.startSearch(初始查询, 选择初始查询, 应用搜索数据, 全局搜索);
    }

    public void 触发搜索(String 查询, @Nullable Bundle 应用搜索数据) {
        this.triggerSearch(查询, 应用搜索数据);
    }

    public void 捕获按键事件(boolean 取) {
        this.takeKeyEvents(取);
    }

    public final boolean 请求窗口特性(int 特性Id) {
        return this.requestWindowFeature(特性Id);
    }

    public final void 置特性可绘制资源(int 特性Id, @DrawableRes int 资源Id) {
        this.setFeatureDrawableResource(特性Id, 资源Id);
    }

    public final void 置特性可绘制Uri(int 特性Id, Uri uri) {
        this.setFeatureDrawableUri(特性Id, uri);
    }

    public final void 置特性可绘制(int 特性Id, Drawable 可绘制对象) {
        this.setFeatureDrawable(特性Id, 可绘制对象);
    }

    public final void 置特性可绘制透明度(int 特性Id, int 透明度) {
        this.setFeatureDrawableAlpha(特性Id, 透明度);
    }

    public LayoutInflater 取布局加载器() {
        return this.getLayoutInflater();
    }


    public MenuInflater 取菜单加载器() {
        return this.getMenuInflater();
    }


    public void 置主题(int 资源id) {
        this.setTheme(资源id);
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, int resid, boolean first) {
        super.onApplyThemeResource(theme, resid, first);
        this.应用主题资源(theme, resid, first);
    }

    protected void 应用主题资源(Resources.Theme 主题, int 资源id, boolean 第一) {
        super.onApplyThemeResource(主题, 资源id, 第一);
    }

    public final void 请求权限(@NonNull String[] 权限, int 请求代码) {
        this.requestPermissions(权限, 请求代码);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public final void 请求权限(@NonNull String[] 权限组, int 请求代码, int 设备Id) {
        this.requestPermissions(权限组, 请求代码, 设备Id);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        this.请求权限结果(requestCode, permissions, grantResults);
    }

    public void 请求权限结果(int 请求代码, @NonNull String[] 权限组, @NonNull int[] 授权结果) {
        super.onRequestPermissionsResult(请求代码, 权限组, 授权结果);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);
        this.请求权限结果(requestCode, permissions, grantResults, deviceId);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 请求权限结果(int 请求代码, @NonNull String[] 权限组, @NonNull int[] 授权结果, int 设备Id) {
        super.onRequestPermissionsResult(请求代码, 权限组, 授权结果, 设备Id);
    }


    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission) {
        return this.是否应该显示权限请求说明(permission);
    }

    public boolean 是否应该显示权限请求说明(@NonNull String 权限) {
        return super.shouldShowRequestPermissionRationale(权限);
    }


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @Override
    public boolean shouldShowRequestPermissionRationale(@NonNull String permission, int deviceId) {
        return this.是否应该显示权限请求说明(permission, deviceId);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public boolean 是否应该显示权限请求说明(@NonNull String 权限, int 设备Id) {
        return super.shouldShowRequestPermissionRationale(权限, 设备Id);
    }




    public void 启动活动以获取结果(@RequiresPermission Intent 意图, int 请求代码) {
        this.startActivityForResult(意图, 请求代码);
    }



    public void 启动活动以获取结果(@RequiresPermission Intent 意图, int 请求代码, @Nullable Bundle 选项) {
        this.startActivityForResult(意图, 请求代码, 选项);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean 是否活动转换正在运行() {
        return this.isActivityTransitionRunning();
    }



    public void 启动意图发送器以获取结果(IntentSender 意图, int 请求代码,
        @Nullable Intent 填充意图, int 标志掩码, int 标志值, int 附加标志) throws IntentSender.SendIntentException {
        this.startIntentSenderForResult(意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志);
    }


    public void 启动意图发送器以获取结果(IntentSender 意图, int 请求代码,
        @Nullable Intent 填充意图, int 标志掩码, int 标志值, int 附加标志,
        @Nullable Bundle 选项) throws IntentSender.SendIntentException {
        this.startIntentSenderForResult(意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志, 选项);
    }


    public void 启动活动(Intent 意图) {
        this.startActivity(意图);
    }

    public void 启动活动(Intent 意图, @Nullable Bundle 选项) {
        this.startActivity(意图, 选项);
    }

    public void 启动多活动(Intent[] 意图组) {
        this.startActivities(意图组);
    }

    public void 启动多活动(Intent[] 意图组, @Nullable Bundle 选项) {
        this.startActivities(意图组, 选项);
    }

    public void 启动意图发送器(IntentSender 意图, @Nullable Intent 填充意图, int 标志掩码,
        int 标志值, int 附加标志) throws IntentSender.SendIntentException {
        this.startIntentSender(意图, 填充意图, 标志掩码, 标志值, 附加标志);
    }

    public void 启动意图发送器(IntentSender 意图, @Nullable Intent 填充意图,
        int 标志掩码, int 标志值, int 附加标志, @Nullable Bundle 选项) throws IntentSender.SendIntentException {
        this.startIntentSender(意图, 填充意图, 标志掩码, 标志值, 附加标志, 选项);
    }

    public boolean 启动活动如果需要(@RequiresPermission @NonNull Intent 意图, int 请求代码) {
        return this.startActivityIfNeeded(意图, 请求代码);
    }

    public boolean 启动活动如果需要(@RequiresPermission @NonNull Intent 意图, int 请求代码, @Nullable Bundle 选项) {
        return this.startActivityIfNeeded(意图, 请求代码, 选项);
    }



    public boolean 启动下一个匹配活动(@RequiresPermission @NonNull Intent 意图) {
        return this.startNextMatchingActivity(意图);
    }

    public boolean 启动下一个匹配活动(@RequiresPermission @NonNull Intent 意图, @Nullable Bundle 选项) {
        return this.startNextMatchingActivity(意图, 选项);
    }


    public void 启动活动从子(@NonNull Activity 子, @RequiresPermission Intent 意图, int 请求代码) {
        this.startActivityFromChild(子, 意图, 请求代码);
    }

    public void 启动活动从子(@NonNull Activity 子, @RequiresPermission Intent 意图, int 请求代码, @Nullable Bundle 选项) {
        this.startActivityFromChild(子, 意图, 请求代码, 选项);
    }


    public void 启动活动从碎片(@NonNull Fragment 碎片, @RequiresPermission Intent 意图, int 请求代码) {
        this.startActivityFromFragment(碎片, 意图, 请求代码);
    }

    public void 启动活动从碎片(@NonNull Fragment 碎片, @RequiresPermission Intent 意图, int 请求代码, @Nullable Bundle 选项) {
        this.startActivityFromFragment(碎片, 意图, 请求代码, 选项);
    }


    public void 启动意图发送器从子(Activity 子, IntentSender 意图, int 请求代码, Intent 填充意图, int 标志掩码,
                                   int 标志值, int 附加标志) throws IntentSender.SendIntentException {
        this.startIntentSenderFromChild(子, 意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志);
    }

    public void 启动意图发送器从子(Activity 子, IntentSender 意图, int 请求代码, Intent 填充意图, int 标志掩码,
                                   int 标志值, int 附加标志, @Nullable Bundle 选项) throws IntentSender.SendIntentException {
        this.startIntentSenderFromChild(子, 意图, 请求代码, 填充意图, 标志掩码, 标志值, 附加标志, 选项);
    }




    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 覆盖活动过渡(int 覆盖类型, @AnimRes int 进入动画, @AnimRes int 退出动画) {
        this.overrideActivityTransition(覆盖类型, 进入动画, 退出动画);
    }


    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 覆盖活动过渡(int 覆盖类型, @AnimRes int 进入动画, @AnimRes int 退出动画, @ColorInt int 背景颜色) {
        this.overrideActivityTransition(覆盖类型 , 进入动画, 退出动画, 背景颜色);
    }



    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 清除覆盖活动过渡(int 覆盖类型) {
        this.clearOverrideActivityTransition(覆盖类型);
    }

    public void 覆盖待处理过渡(int 进入动画, int 退出动画) {
        this.overridePendingTransition(进入动画, 退出动画);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void 覆盖待处理过渡(int 进入动画, int 退出动画, int 背景颜色) {
        this.overridePendingTransition(进入动画, 退出动画, 背景颜色);
    }

    public final void 置结果(int 结果代码) {
        this.setResult(结果代码);
    }

    public final void 置结果(int 结果代码, Intent 数据) {
        this.setResult(结果代码, 数据);
    }



    public Uri 取来源() {
        return this.getReferrer();
    }


    @Override
    public Uri onProvideReferrer() {
        return this.提供引荐来源();
    }

    public Uri 提供引荐来源() {
        return super.onProvideReferrer();
    }


    public String 取调用包名() {
        return this.getCallingPackage();
    }

    public ComponentName 取调用活动() {
        return this.getCallingActivity();
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public int 取启动来源Uid() {
        return this.getLaunchedFromUid();
    }



    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public String 取启动方包名() {
        return this.getLaunchedFromPackage();
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public @NonNull ComponentCaller 取初始调用() {
        return this.getInitialCaller();
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public @NonNull ComponentCaller 取当前调用() {
        return this.getCurrentCaller();
    }

    public void 置可见(boolean 可见) {
        this.setVisible(可见);
    }

    public boolean 是否正在完成() {
        return this.isFinishing();
    }

    public boolean 是否已销毁() {
        return this.isDestroyed();
    }

    public boolean 是否正在改变配置() {
        return this.isChangingConfigurations();
    }

    public void 重建() {
        this.recreate();
    }

    public void 关闭() {
        this.finish();
    }

    public void 关闭亲和性() {
        this.finishAffinity();
    }

    public void 关闭从子(Activity 子) {
        this.finishFromChild(子);
    }

    public void 关闭过渡后() {
        this.finishAfterTransition();
    }

    public void 关闭活动(int 请求代码) {
        this.finishActivity(请求代码);
    }


    public void 关闭活动从子(@NonNull Activity 子, int 请求代码) {
        this.finishActivityFromChild(子, 请求代码);
    }

    public void 完成并移除任务() {
        this.finishAndRemoveTask();
    }

    public boolean 释放实例() {
        return this.releaseInstance();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.活动结果(requestCode, resultCode, data);
    }

    protected void 活动结果(int 请求代码, int 结果代码, Intent 数据) {
        super.onActivityResult(请求代码, 结果代码, 数据);
    }


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {
        super.onActivityResult(requestCode, resultCode, data, caller);
        this.活动结果(requestCode, resultCode, data, caller);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 活动结果(int 请求代码, int 结果代码, @Nullable Intent 数据, @NonNull ComponentCaller 调用方) {
        super.onActivityResult(请求代码, 结果代码, 数据, 调用方);
    }


    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        super.onActivityReenter(resultCode, data);
        this.活动重新进入(resultCode, data);
    }

    public void 活动重新进入(int 结果代码, Intent 数据) {
        super.onActivityReenter(结果代码, 数据);
    }

    public PendingIntent 创建待处理结果(int 结果代码, @NonNull Intent 数据, int 标志) {
        return this.createPendingResult(结果代码, 数据, 标志);
    }

    public void 置请求方向(int 请求方向) {
        this.setRequestedOrientation(请求方向);
    }

    public int 取请求方向() {
        return this.getRequestedOrientation();
    }

    public int 取任务Id() {
        return this.getTaskId();
    }

    public boolean 是否任务根() {
        return this.isTaskRoot();
    }

    public boolean 移动任务到后台(boolean 非根) {
        return this.moveTaskToBack(非根);
    }


    public String 取本地类名() {
        return this.getLocalClassName();
    }

    public ComponentName 取组件名() {
        return this.getComponentName();
    }

    public SharedPreferences 取偏好设置(int 模式) {
        return this.getPreferences(模式);
    }

    @RequiresApi(api = Build.VERSION_CODES.S)
    public boolean 是否从气泡启动() {
        return this.isLaunchedFromBubble();
    }

    public Object 取系统服务(@NonNull String 名) {
        return this.getSystemService(名);
    }


    public void 置标题(CharSequence 标题) {
        this.setTitle(标题);
    }

    public void 置标题(int 标题Id) {
        this.setTitle(标题Id);
    }

    public void 置标题颜色(int 文本颜色) {
        this.setTitleColor(文本颜色);
    }

    public final CharSequence 取标题() {
        return this.getTitle();
    }

    public final int 取标题颜色() {
        return this.getTitleColor();
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        super.onTitleChanged(title, color);
        this.标题已改变(title, color);
    }

    protected void 标题已改变(CharSequence 标题, int 颜色) {
        super.onTitleChanged(标题, 颜色);
    }


    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        super.onChildTitleChanged(childActivity, title);
        this.子标题已改变(childActivity, title);
    }

    protected void 子标题已改变(Activity 子活动, CharSequence 标题) {
        super.onChildTitleChanged(子活动, 标题);
    }

    public void 置任务描述(ActivityManager.TaskDescription 任务描述) {
        this.setTaskDescription(任务描述);
    }

    public final void 置进度条可见(boolean 可见) {
        this.setProgressBarVisibility(可见);
    }

    public final void 置不确定进度条可见(boolean 可见) {
        this.setProgressBarIndeterminateVisibility(可见);
    }


    public final void 置不确定进度条(boolean 不确定) {
        this.setProgressBarIndeterminate(不确定);
    }

    public final void 置进度(int 进度) {
        this.setProgress(进度);
    }

    public final void 置次要进度(int 次要进度) {
        this.setSecondaryProgress(次要进度);
    }

    public final void 置音量控制流(int 流类型) {
        this.setVolumeControlStream(流类型);
    }

    public final int 取音量控制流() {
        return this.getVolumeControlStream();
    }


    public final void 置媒体控制器(MediaController 控制器) {
        this.setMediaController(控制器);
    }

    public final MediaController 取媒体控制器() {
        return this.getMediaController();
    }


    public final void 运行主线程(Runnable 动作) {
        this.runOnUiThread(动作);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return this.创建视图(name, context, attrs);
    }

    public View 创建视图(@NonNull String 名, @NonNull Context 上下文, @NonNull AttributeSet 属性) {
        return super.onCreateView(名, 上下文, 属性);
    }


    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return this.创建视图(parent, name, context, attrs);
    }

    public View 创建视图(@Nullable View 父, @NonNull String 名, @NonNull Context 上下文, @NonNull AttributeSet 属性) {
        return super.onCreateView(父, 名, 上下文, 属性);
    }


    @Override
    public void dump(@NonNull String prefix, @Nullable FileDescriptor fd, @NonNull PrintWriter writer, @Nullable String[] args) {
        super.dump(prefix, fd, writer, args);
        this.转储(prefix, fd, writer, args);
    }

    public void 转储(@NonNull String 前缀, @Nullable FileDescriptor 文件描述符, @NonNull PrintWriter 作家, @Nullable String[] 参数) {
        super.dump(前缀, 文件描述符, 作家, 参数);
    }


    public boolean 是否沉浸式() {
        return this.isImmersive();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public boolean 置半透明(boolean 半透明) {
        return setTranslucent(半透明);
    }


    public boolean 请求背后可见(boolean 可见) {
        return this.requestVisibleBehind(可见);
    }


    @Override
    public void onVisibleBehindCanceled() {
        super.onVisibleBehindCanceled();
        this.后台可见取消();
    }

    public void 后台可见取消() {
        super.onVisibleBehindCanceled();
    }


    @Override
    public void onEnterAnimationComplete() {
        super.onEnterAnimationComplete();
        this.进入动画完成();
    }

    public void 进入动画完成() {
        super.onEnterAnimationComplete();
    }

    public void 置沉浸式(boolean i) {
        this.setImmersive(i);
    }



    public void 置Vr模式启用(boolean 已启用, @NonNull ComponentName 请求组件)
            throws PackageManager.NameNotFoundException {
        this.setVrModeEnabled(已启用, 请求组件);
    }

    public ActionMode 开始操作模式(ActionMode.Callback 回调) {
        return this.startActionMode(回调);
    }

    public ActionMode 开始操作模式(ActionMode.Callback 回调, int 类型) {
        return this.startActionMode(回调, 类型);
    }


    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return this.onWindowStartingActionMode(callback);
    }

    public ActionMode 窗口启动操作模式(ActionMode.Callback 回调) {
        return super.onWindowStartingActionMode(回调);
    }


    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        return this.窗口启动操作模式(callback, type);
    }

    public ActionMode 窗口启动操作模式(ActionMode.Callback 回调, int 类型) {
        return super.onWindowStartingActionMode(回调, 类型);
    }


    @Override
    public void onActionModeStarted(ActionMode mode) {
        super.onActionModeStarted(mode);
        this.操作模式启动(mode);
    }

    public void 操作模式启动(ActionMode 模式) {
        super.onActionModeStarted(模式);
    }


    @Override
    public void onActionModeFinished(ActionMode mode) {
        super.onActionModeFinished(mode);
        this.操作模式完成(mode);
    }

    public void 操作模式完成(ActionMode 模式) {
        super.onActionModeFinished(模式);
    }


    public boolean 是否应重新创建任务(Intent 目标意图) {
        return this.shouldUpRecreateTask(目标意图);
    }


    public boolean 导航向上到(Intent 上升意图) {
        return this.navigateUpTo(上升意图);
    }

    public boolean 导航向上到从子(Activity 子, Intent 上升意图) {
        return this.navigateUpToFromChild(子, 上升意图);
    }

    public Intent 取父活动意图() {
        return this.getParentActivityIntent();
    }

    public void 置进入共享元素回调(SharedElementCallback 回调) {
        this.setEnterSharedElementCallback(回调);
    }

    public void 置退出共享元素回调(SharedElementCallback 回调) {
        this.setExitSharedElementCallback(回调);
    }

    public void 延迟进入过渡() {
        this.postponeEnterTransition();
    }

    public void 开始推迟进入过渡() {
        this.startPostponedEnterTransition();
    }

    public DragAndDropPermissions 请求拖放权限(DragEvent 事件) {
        return this.requestDragAndDropPermissions(事件);
    }

    public void 开始锁定任务() {
        this.startLockTask();
    }

    public void 停止锁定任务() {
        this.stopLockTask();
    }

    public void 显示锁定任务逃逸消息() {
        this.showLockTaskEscapeMessage();
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public void 置最近截图启用(boolean 已启用) {
        this.setRecentsScreenshotEnabled(已启用);
    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public void 置显示在锁定(boolean 显示在锁定) {
        this.setShowWhenLocked(显示在锁定);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void 置继承锁定时显示(boolean 锁定时继承显示) {
        this.setInheritShowWhenLocked(锁定时继承显示);
    }

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public void 置屏幕常亮(boolean 屏幕常亮) {
        this.setTurnScreenOn(屏幕常亮);
    }

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public void 置允跨Uid活动切换从下方(boolean 允许) {
        this.setAllowCrossUidActivitySwitchFromBelow(允许);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public OnBackInvokedDispatcher 取返回调用调度器() {
        return this.getOnBackInvokedDispatcher();
    }

    @SuppressLint("NewApi")
    public interface 屏幕捕获回调 extends ScreenCaptureCallback {
        /**
         * Called when one of the monitored activities is captured.
         * This is not invoked if the activity window
         * has {@link WindowManager.LayoutParams#FLAG_SECURE} set.
         */
        default void onScreenCaptured(){
            this.屏幕已捕获();
        }
        void 屏幕已捕获();
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @RequiresPermission(Manifest.permission.DETECT_SCREEN_CAPTURE)
    public void 注册屏幕捕获回调(@NonNull Executor 执行者, @NonNull ScreenCaptureCallback 回调) {
        this.registerScreenCaptureCallback(执行者, 回调);
    }

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    @RequiresPermission(Manifest.permission.DETECT_SCREEN_CAPTURE)
    public void 取消注册屏幕捕获回调(@NonNull ScreenCaptureCallback 回调) {
        this.unregisterScreenCaptureCallback(回调);
    }


}
