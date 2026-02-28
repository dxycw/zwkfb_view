package 安卓.内容;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.AttributionSource;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.ContextParams;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.util.AttributeSet;
import android.view.Display;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresPermission;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.StyleableRes;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.function.IntConsumer;

/**
 * 描述：用于访问应用程序全局信息的接口。这是一个抽象类，其实现由 Android 系统提供。它允许访问应用程序特定的资源和类，
 * 以及执行应用程序级别的操作回调，例如启动 Activity、发送和接收广播等。
 */
public abstract class 上下文 extends Context {

    /**
     *  默认设备 ID，即主设备（非虚拟设备）的 ID。
     */
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 设备_ID_默认 = Context.DEVICE_ID_DEFAULT;

    /**
     * 无效的设备ID。
     */
    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 设备_ID_无效 = Context.DEVICE_ID_INVALID;

    /**
     * 文件创建模式：默认模式，创建的文件只能由调用应用（或所有共享相同用户 ID 的应用）访问。
     */
    public static final int 模式_私有 = Context.MODE_PRIVATE;

    /**
     * 文件创建模式：全局可读模式，创建的文件可以被所有应用读取。
     */
    @SuppressLint("WorldReadableFiles")
    public static final int 模式_全局_可读 = Context.MODE_WORLD_READABLE;

    /**
     * 文件创建模式：全局可读可写模式，创建的文件可以被所有应用读取和写入。
     */

    @SuppressLint("WorldWriteableFiles")
    public static final int 模式_全局_可写 = Context.MODE_WORLD_WRITEABLE;

    /**
     * 文件创建模式：追加模式，创建的文件会追加内容。
     */
    public static final int 模式_追加 = Context.MODE_APPEND;

    /**
     * 文件创建模式：多进程模式，创建的文件可以被多个进程访问。
     */
    public static final int 模式_多_进程 = Context.MODE_MULTI_PROCESS;

    /**
     * 文件创建模式：启用写预览日志模式，创建的文件会在写入时启用写预览日志。
     */
    public static final int 模式_启用_写_预览_日志 = Context.MODE_ENABLE_WRITE_AHEAD_LOGGING;

    /**
     * 文件创建模式：无本地化排序规则模式，创建的文件将使用系统默认的排序规则。
     */
    public static final int 模式_无_本地化_排序规则 = Context.MODE_NO_LOCALIZED_COLLATORS;

    //=======================================================================

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public final static class 绑定服务标志{
        public static BindServiceFlags of(long 值) {
            return Context.BindServiceFlags.of(值);
        }
    }

    //=======================================================================

    /**
     * 绑定服务标志：自动创建标志，如果服务没有运行，则自动创建服务。
     */
    public static final int 绑定_自动_创建 = Context.BIND_AUTO_CREATE;

    /**
     * 绑定服务标志：调试解除标志，如果服务正在调试，则解除调试。
     */
    public static final int 绑定_调试_解除 = Context.BIND_DEBUG_UNBIND;

    /**
     * 绑定服务标志：非前台标志，如果服务正在运行，则将其切换到前台。
     */
    public static final int 绑定_非_前台 = Context.BIND_NOT_FOREGROUND;

    /**
     * 绑定服务标志：上客户标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 绑定_上方_客户 = Context.BIND_ABOVE_CLIENT;

    /**
     * 绑定服务标志：允许内存溢出管理标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 绑定_允许_内存溢出_管理 = Context.BIND_ALLOW_OOM_MANAGEMENT;

    /**
     * 绑定服务标志：放弃优先级标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 绑定_放弃_优先 = Context.BIND_WAIVE_PRIORITY;

    /**
     * 绑定服务标志：重要标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 绑定_重要 = Context.BIND_IMPORTANT;

    /**
     * 绑定服务标志：调整与活动标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 绑定_调整_与_活动 = Context.BIND_ADJUST_WITH_ACTIVITY;

    /**
     * 绑定服务标志：非感知标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public static final int 绑定_非_可感知 = Context.BIND_NOT_PERCEPTIBLE;

    /**
     * 绑定服务标志：允许活动开始标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 绑定_允许_活动_开始 = Context.BIND_ALLOW_ACTIVITY_STARTS;

    /**
     * 绑定服务标志：包含功能标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public static final int 绑定_包含_功能 = Context.BIND_INCLUDE_CAPABILITIES;

    /**
     * 绑定服务标志：共享孤立进程标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 绑定_共享_孤立_过程 = Context.BIND_SHARED_ISOLATED_PROCESS;

    /**
     * 绑定服务标志：包裹孤立进程标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final int 绑定_包裹_孤立_过程 = Context.BIND_PACKAGE_ISOLATED_PROCESS;

    /**
     * 绑定服务标志：外部服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 绑定_外部_服务 = Context.BIND_EXTERNAL_SERVICE;

    /**
     * 绑定服务标志：外部服务标志（长），如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final long 绑定_外部_服务_长 = Context.BIND_EXTERNAL_SERVICE_LONG;


    /**
     * 绑定服务标志：接收器可见至即时应用标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public static final int 接收器_可见_至_即时_应用 = Context.RECEIVER_VISIBLE_TO_INSTANT_APPS;

    /**
     * 绑定服务标志：接收器已导出标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    public static final int 接收器_已导出 = Context.RECEIVER_EXPORTED;

    /**
     * 绑定服务标志：接收器非已导出标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    public static final int 接收器_非_已导出 = Context.RECEIVER_NOT_EXPORTED;

    //=============================================================================================

    /**
     * 获取资产
     */
    public AssetManager 取资产() {
        return this.getAssets();
    }

    /**
     * 获取资源
     */
    public Resources 取资源() {
        return this.getResources();
    }

    /**
     * 获取包管理器
     */
    public PackageManager 取包管理器(){
        return this.getPackageManager();
    }

    /**
     * 获取内容解析器
     */
    public ContentResolver 取内容解析器(){
        return this.getContentResolver();
    }

    /**
     * 获取主循环器
     */
    public Looper 取主循环器(){
        return this.getMainLooper();
    }

    /**
     * 获取主执行器
     */
    @RequiresApi(Build.VERSION_CODES.P)
    public Executor 取主执行器(){
        return this.getMainExecutor();
    }

    /**
     * 获取应用程序上下文
     */
    public Context 取应用程序上下文(){
        return this.getApplicationContext();
    }

    /**
     * 注册组件回调
     */
    public void 注册组件回调(ComponentCallbacks 回调){
        this.registerComponentCallbacks(回调);
    }

    /**
     * 取消注册组件回调
     */
    public void 取消注册组件回调(ComponentCallbacks 回调){
        this.unregisterComponentCallbacks(回调);
    }

    /**
     * 获取文本
     */
    public CharSequence 取文本(@StringRes int 资源id){
        return this.getText(资源id);
    }

    /**
     * 获取字符串
     */
    public CharSequence 取字符串(@StringRes int 资源id){
        return this.getString(资源id);
    }

    /**
     * 获取字符串
     */
    public CharSequence 取字符串(@StringRes int 资源id, Object... 格式参数){
        return this.getString(资源id,格式参数);
    }

    /**
     * 获取颜色
     */
    public int 取颜色(@ColorRes int id){
        return this.getColor(id);
    }

    /**
     * 获取可绘制对象
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public Drawable 取可绘制对象(@DrawableRes int id){
        return this.getDrawable(id);
    }


    /**
     * 获取颜色状态列表
     */
    @SuppressLint("UseCompatLoadingForDrawables")
    public ColorStateList 取颜色状态列表(@ColorRes int id){
        return this.getColorStateList(id);
    }

    /**
     * 置主题
     */
    public void 置主题(@StyleRes int 资源id){
        this.setTheme(资源id);
    }

    /**
     * 获取主题
     */
    public Resources.Theme 取主题(){
        return this.getTheme();
    }

    /**
     * 获取样式属性
     */
    public TypedArray 取样式属性(@StyleableRes int[] 属性){
        return this.obtainStyledAttributes(属性);
    }

    /**
     * 获取样式属性
     */
    public TypedArray 取样式属性(@StyleRes int 资源id, @StyleableRes int[] 属性){
        return this.obtainStyledAttributes(资源id, 属性);
    }

    /**
     * 获取样式属性
     */
    public TypedArray 取样式属性(AttributeSet 置 , @StyleableRes int[] 属性){
        return this.obtainStyledAttributes(置, 属性);
    }

    /**
     * 获取样式属性
     */
    public TypedArray 取样式属性(AttributeSet 置 , @StyleableRes int[] 属性, @AttrRes int 默认样式属性, @StyleRes int 默认样式资源){
        return this.obtainStyledAttributes(置,属性, 默认样式属性 ,默认样式资源);
    }

    /**
     * 获取类加载器
     */
    public ClassLoader 取类加载器(){
        return this.getClassLoader();
    }

    /**
     * 获取包名
     */
    public String 取包名(){
        return this.getPackageName();
    }

    /**
     * 获取操作包名
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public String 取操作包名(){
        return this.getOpPackageName();
    }

    /**
     * 获取归属标签
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public String 取归属标签(){
        return this.getAttributionTag();
    }

    /**
     * 获取归属来源
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public AttributionSource 取归属来源(){
        return this.getAttributionSource();
    }

    /**
     * 获取参数
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public ContextParams 取参数(){
        return this.getParams();
    }

    /**
     * 获取应用信息
     */
    public ApplicationInfo 取应用信息(){
        return this.getApplicationInfo();
    }

    /**
     * 获取包资源路径
     */
    public String 取包资源路径(){
        return this.getPackageResourcePath();
    }

    /**
     * 获取包代码路径
     */
    public String 取包代码路径(){
        return this.getPackageCodePath();
    }

    /**
     * 获取共享偏好
     */
    public SharedPreferences 取共享偏好(String 名, int 模式){
        return this.getSharedPreferences(名, 模式);
    }

    /**
     * 移动共享偏好自
     */
    public boolean 移动共享偏好自(Context 源上下文, String 名){
        return this.moveSharedPreferencesFrom(源上下文, 名);
    }

    /**
     * 删除共享偏好
     */
    public boolean 删除共享偏好(String 名){
        return this.deleteSharedPreferences(名);
    }

    /**
     * 打开文件输入
     */
    public FileInputStream 打开文件输入(String 名) throws FileNotFoundException {
        return this.openFileInput(名);
    }

    /**
     * 打开文件输出
     */
    public FileOutputStream 打开文件输出(String 名, int 模式) throws FileNotFoundException {
        return this.openFileOutput(名, 模式);
    }

    /**
     * 删除文件
     */
    public Boolean 删除文件(String 名){
        return this.deleteFile(名);
    }

    /**
     * 获取文件流路径
     */
    public File 取文件流路径(String 名){
        return this.getFileStreamPath(名);
    }

    /**
     * 获取数据目录
     */
    public File 取数据目录(){
        return this.getDataDir();
    }

    /**
     * 获取文件目录
     */
    public File 取文件目录(){
        return this.getFilesDir();
    }

    /**
     * 获取无备份文件目录
     */
    public File 取无备份文件目录(){
        return this.getNoBackupFilesDir();
    }

    /**
     * 获取外部文件目录
     */
    public File 取外部文件目录(String 类型){
        return this.getExternalFilesDir(类型);
    }

    /**
     * 取外部文件目录组
     */
    public File[] 取外部文件目录组(String 类型){
        return this.getExternalFilesDirs(类型);
    }

    /**
     * 获取Obb目录
     */
    public File 取Obb目录(){
        return this.getObbDir();
    }

    /**
     * 获取Obb目录组
     */
    public File[] 取Obb目录组(){
        return this.getObbDirs();
    }

    /**
     * 获取缓存目录
     */
    public File 取缓存目录(){
        return this.getCacheDir();
    }

    /**
     * 获取代码缓存目录
     */
    public File 取代码缓存目录(){
        return this.getCodeCacheDir();
    }

    /**
     * 获取外部缓存目录
     */
    public File 取外部缓存目录(){
        return this.getExternalCacheDir();
    }

    /**
     * 获取外部缓存目录组
     */
    public File[] 取外部缓存目录组(){
        return this.getExternalCacheDirs();
    }

    /**
     * 获取外部媒体目录组
     */
    public File[] 取外部媒体目录组(){
        return this.getExternalMediaDirs();
    }

    /**
     * 获取文件列表
     */
    public String[] 文件列表(){
        return this.fileList();
    }

    /**
     * 获取目录
     */
    public File 取目录(String 名, int 模式){
        return this.getDir(名, 模式);
    }

    /**
     * 打开或创建数据库
     */
    public SQLiteDatabase 打开或创建数据库(String 名, int 模式 , SQLiteDatabase.CursorFactory 工厂) {
        return this.openOrCreateDatabase(名, 模式, 工厂);
    }

    /**
     * 打开或创建数据库
     */
    public SQLiteDatabase 打开或创建数据库(String 名, int 模式 , SQLiteDatabase.CursorFactory 工厂, DatabaseErrorHandler 错误处理程序 ){
        return this.openOrCreateDatabase(名, 模式, 工厂, 错误处理程序);
    }

    /**
     * 移动数据库自
     */
    public boolean 移动数据库自(Context 源上下文, String 名){
        return this.moveDatabaseFrom(源上下文, 名);
    }

    /**
     * 删除数据库
     */
    public boolean 删除数据库(String 名){
        return this.deleteDatabase(名);
    }

    /**
     * 获取数据库路径
     */
    public File 取数据库路径(String 名){
        return this.getDatabasePath(名);
    }

    /**
     * 数据库列表
     */
    public String[] 数据库列表(){
        return this.databaseList();
    }

    /**
     * 获取壁纸
     */
    public Drawable 取壁纸(){
        return this.getWallpaper();
    }

    /**
     * 预览壁纸
     */
    public Drawable 预览壁纸(){
        return this.peekWallpaper();
    }

    /**
     * 获取壁纸期望的最小宽度
     */
    public int 取壁纸期望的最小宽度(){
        return this.getWallpaperDesiredMinimumWidth();
    }

    /**
     * 获取壁纸期望的最小高度
     */
    public int 取壁纸期望的最小高度(){
        return this.getWallpaperDesiredMinimumHeight();
    }

    /**
     * 设置壁纸
     */
    public void 置壁纸(Bitmap 位图) throws IOException {
        this.setWallpaper(位图);
    }

    /**
     * 设置壁纸
     */
    public void 置壁纸(InputStream 数据) throws IOException {
        this.setWallpaper(数据);
    }

    /**
     * 清除壁纸
     */
    public void 清除壁纸() throws IOException {
        this.clearWallpaper();
    }

    /**
     * 启动活动
     */
    public void 启动活动(@RequiresPermission Intent 意图){
        this.startActivity(意图);
    }

    /**
     * 启动活动
     */
    public void 启动活动(@RequiresPermission Intent 意图, Bundle 选项){
        this.startActivity(意图,选项);
    }

    /**
     * 启动多活动
     */
    public void 启动活动组(@RequiresPermission Intent[] 意图){
        this.startActivities(意图);
    }

    /**
     * 启动多活动
     */
    public void 启动活动组(@RequiresPermission Intent[] 意图, Bundle 选项){
        this.startActivities(意图,选项);
    }

    /**
     * 启动意图发送器
     */
    public void 启动意图发送器(IntentSender 意图, Intent 填充意图, int 标志掩码, int 标志值, int 额外标志) throws IntentSender.SendIntentException {
        this.startIntentSender(意图,填充意图, 标志掩码, 标志值, 额外标志);
    }

    /**
     * 启动意图发送器
     */
    public void 启动意图发送器(IntentSender 意图, Intent 填充意图, int 标志掩码, int 标志值, int 额外标志, Bundle 选项) throws IntentSender.SendIntentException {
        this.startIntentSender(意图,填充意图, 标志掩码, 标志值, 额外标志, 选项);
    }

    /**
     * 发送广播
     */
    public void 发送广播(@RequiresPermission Intent 意图){
        this.sendBroadcast(意图);
    }

    /**
     * 发送广播
     */
    public void 发送广播(@RequiresPermission Intent 意图, String 接收者权限){
        this.sendBroadcast(意图, 接收者权限);
    }

    /**
     * 发送广播带多个权限
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public void 发送广播带多个权限(Intent 意图, String[] 接收者权限){
        this.sendBroadcastWithMultiplePermissions(意图, 接收者权限);
    }

    /**
     * 发送广播
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 发送广播(Intent 意图, String 接收者权限, Bundle 选项){
        this.sendBroadcast(意图, 接收者权限, 选项);
    }

    /**
     * 发送有序广播
     */
    public void 发送有序广播(@RequiresPermission Intent 意图, String 接收者权限){
        this.sendOrderedBroadcast(意图, 接收者权限);
    }

    /**
     * 发送有序广播
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 发送有序广播(Intent 意图, String 接收者权限, Bundle 选项){
        this.sendOrderedBroadcast(意图, 接收者权限, 选项);
    }

    /**
     * 发送有序广播
     */
    public void 发送有序广播(@RequiresPermission Intent 意图, String 接收者权限, BroadcastReceiver 结果接收器, Handler 调度器,int 初始代码, String 初始数据,Bundle 初始附加项){
        this.sendOrderedBroadcast(意图, 接收者权限, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项);
    }

    /**
     * 发送有序广播
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 发送有序广播(Intent 意图, String 接收者权限, Bundle 选项, BroadcastReceiver 结果接收器, Handler 调度器, int 初始代码, String 初始数据, Bundle 初始附加项){
        this.sendOrderedBroadcast(意图, 接收者权限, 选项,结果接收器, 调度器,初始代码, 初始数据,  初始附加项);
    }

    /**
     * 发送广播为用户
     */
    @RequiresPermission("android.permission.INTERACT_ACROSS_USERS")
    public void 发送广播为用户(@RequiresPermission Intent 意图, UserHandle 用户){
        this.sendBroadcastAsUser(意图, 用户);
    }

    /**
     * 发送广播为用户
     */
    @RequiresPermission("android.permission.INTERACT_ACROSS_USERS")
    public void 发送广播为用户(@RequiresPermission Intent 意图, UserHandle 用户, String 接收者权限){
        this.sendBroadcastAsUser(意图, 用户, 接收者权限);
    }

    /**
     * 发送有序广播为用户
     */
    @RequiresPermission("android.permission.INTERACT_ACROSS_USERS")
    public void 发送有序广播为用户(@RequiresPermission Intent 意图, UserHandle 用户, String 接收者权限, BroadcastReceiver 结果接收器, Handler 调度器, int 初始代码, String 初始数据, Bundle 初始附加项){
        this.sendOrderedBroadcastAsUser(意图, 用户, 接收者权限, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项);
    }

    /**
     * 发送有序广播
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 发送有序广播(Intent 意图, String 接收者权限, String 接收器应用操作, BroadcastReceiver 结果接收器, Handler 调度器, int 初始代码, String 初始数据, Bundle 初始附加项){
        this.sendOrderedBroadcast(意图, 接收者权限, 接收器应用操作,结果接收器, 调度器,初始代码, 初始数据,  初始附加项);
    }

    /**
     * 发送粘性广播
     */
    @RequiresPermission(Manifest.permission.BROADCAST_STICKY)
    public void 发送粘性广播(@RequiresPermission Intent 意图){
        this.sendStickyBroadcast(意图);
    }

    /**
     * 发送粘性广播
     */
    @RequiresApi(api = Build.VERSION_CODES.S)
    @RequiresPermission(Manifest.permission.BROADCAST_STICKY)
    public void 发送粘性广播(@RequiresPermission Intent 意图, Bundle 选项){
        this.sendStickyBroadcast(意图, 选项);
    }

    /**
     * 发送有序粘性广播
     */
    @RequiresPermission(Manifest.permission.BROADCAST_STICKY)
    public void 发送有序粘性广播(@RequiresPermission Intent 意图, BroadcastReceiver 结果接收器, Handler 调度器, int 初始代码, String 初始数据, Bundle 初始附加项){
        this.sendStickyOrderedBroadcast(意图, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项);
    }

    /**
     * 移除粘性广播
     */
    @RequiresPermission(Manifest.permission.BROADCAST_STICKY)
    public void 移除粘性广播(@RequiresPermission Intent 意图){
        this.removeStickyBroadcast(意图);
    }

    /**
     * 发送粘性广播为用户
     */

    @RequiresPermission(allOf = {Manifest.permission.BROADCAST_STICKY, "android.permission.INTERACT_ACROSS_USERS"})
    public void 发送粘性广播为用户(@RequiresPermission Intent 意图, UserHandle 用户){
        this.sendStickyBroadcastAsUser(意图,用户);
    }

    /**
     * 发送有序粘性广播为用户
     */
    @RequiresPermission(allOf = {Manifest.permission.BROADCAST_STICKY, "android.permission.INTERACT_ACROSS_USERS"})
    public void 发送有序粘性广播为用户(@RequiresPermission Intent 意图, UserHandle 用户, BroadcastReceiver 结果接收器, Handler 调度器, int 初始代码, String 初始数据, Bundle 初始附加项){
        this.sendStickyOrderedBroadcastAsUser(意图, 用户, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项);
    }

    /**
     * 移除粘性广播为用户
     */
    @RequiresPermission(allOf = {Manifest.permission.BROADCAST_STICKY, "android.permission.INTERACT_ACROSS_USERS"})
    public void 移除粘性广播为用户(@RequiresPermission Intent 意图, UserHandle 用户){
        this.removeStickyBroadcastAsUser(意图,用户);
    }


    /**
     * 注册接收器
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public Intent 注册接收器(BroadcastReceiver 接收器, IntentFilter 过滤器){
        return this.registerReceiver(接收器 , 过滤器);
    }

    /**
     * 注册接收器
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public Intent 注册接收器(BroadcastReceiver 接收器, IntentFilter 过滤器, @ContextCompat.RegisterReceiverFlags int 标志){
        return this.registerReceiver(接收器 ,过滤器,标志);
    }

    /**
     * 注册接收器
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public Intent 注册接收器(BroadcastReceiver 接收器, IntentFilter 过滤器, String 广播权限, Handler 调度器){
        return this.registerReceiver(接收器 ,过滤器,广播权限, 调度器);
    }


    /**
     * 注册接收器
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public Intent 注册接收器(BroadcastReceiver 接收器, IntentFilter 过滤器, String 广播权限, Handler 调度器, @ContextCompat.RegisterReceiverFlags int 标志){
        return this.registerReceiver(接收器 ,过滤器,广播权限, 调度器, 标志);
    }


    /**
     * 取消注册接收器
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public void 取消注册接收器(BroadcastReceiver 接收器){
        this.unregisterReceiver(接收器);
    }

    /**
     * 启动服务
     */
    @SuppressLint("UnspecifiedRegisterReceiverFlag")
    public ComponentName 启动服务(Intent 服务){
        return this.startService(服务);
    }

    /**
     * 启动前台服务
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public ComponentName 启动前台服务(Intent 服务) {
        return this.startForegroundService(服务);
    }

    /**
     * 停止服务
     */
    public boolean 停止服务(Intent 服务){
        return this.stopService(服务);
    }

    /**
     * 绑定服务
     */
    public boolean 绑定服务(@RequiresPermission Intent 服务, ServiceConnection 连接, int 标志) {
        return this.bindService(服务, 连接, 标志);
    }

    /**
     * 绑定服务
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public boolean 绑定服务(@RequiresPermission Intent 服务, ServiceConnection 连接, Context.BindServiceFlags 标志){
        return this.bindService(服务, 连接, 标志);
    }

    /**
     * 绑定服务
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public boolean 绑定服务(@RequiresPermission Intent 服务, int 标志, Executor 执行者, ServiceConnection 连接){
        return this.bindService(服务, 标志, 执行者, 连接);
    }

    /**
     * 绑定服务
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public boolean 绑定服务(@RequiresPermission Intent 服务, Context.BindServiceFlags 标志, Executor  执行者, ServiceConnection 连接) {
        return this.bindService(服务, 标志, 执行者, 连接);
    }

    /**
     * 绑定隔离服务
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public boolean 绑定隔离服务(@RequiresPermission Intent 服务, int 标志, String 实例名, Executor 执行者, ServiceConnection 连接) {
        return this.bindIsolatedService(服务, 标志, 实例名, 执行者, 连接);
    }

    /**
     * 绑定隔离服务
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public boolean 绑定隔离服务(@RequiresPermission Intent 服务, Context.BindServiceFlags 标志, String 实例名, Executor 执行者, ServiceConnection 连接) {
        return this.bindIsolatedService(服务, 标志, 实例名, 执行者, 连接);
    }

    /**
     * 绑定服务为用户
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public boolean 绑定服务为用户(@RequiresPermission Intent 服务, ServiceConnection 连接,int  标志, UserHandle 用户){
        return this.bindServiceAsUser(服务, 连接, 标志, 用户);
    }


    /**
     * 绑定服务为用户
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public boolean 绑定服务为用户(@RequiresPermission Intent 服务, ServiceConnection 连接, Context.BindServiceFlags 标志, UserHandle 用户){
        return this.bindServiceAsUser(服务, 连接, 标志, 用户);
    }

    /**
     * 更新服务组
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public void 更新服务组(ServiceConnection 连接, int 组, int 重要性) {
        this.updateServiceGroup(连接, 组, 重要性);
    }

    /**
     * 解绑服务
     */
    public void 解绑服务(ServiceConnection 连接) {
        this.unbindService(连接);
    }

    /**
     * 启动仪器化
     */
    public boolean 启动仪器化(ComponentName 类名, String 配置文件, Bundle 参数){
        return this.startInstrumentation(类名, 配置文件, 参数);
    }

    /**
     * 获取系统服务
     */
    public Object 取系统服务(String 名){
        return this.getSystemService(名);
    }

    /**
     * 获取系统服务
     */
    public final <T> T 取系统服务(Class<T> 服务类){
        return this.getSystemService(服务类);
    }

    /**
     * 取系统服务名
     */
    public String 取系统服务名(Class<?> 服务类){
        return this.getSystemServiceName(服务类);
    }

    //==============================================================================================

    /**
     * 绑定服务标志：接收器已导出标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 电源_服务 = Context.POWER_SERVICE;

    /**
     * 绑定服务标志：窗口服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 窗口_服务 = Context.WINDOW_SERVICE;

    /**
     * 绑定服务标志：布局加载器服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 布局_加载器_服务 = Context.LAYOUT_INFLATER_SERVICE;

    /**
     * 绑定服务标志：账户服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 账户_服务 = Context.ACCOUNT_SERVICE;

    /**
     * 绑定服务标志：活动服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 活动_服务 = Context.ACTIVITY_SERVICE;

    /**
     * 绑定服务标志：闹钟服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 报警_服务 = Context.ALARM_SERVICE;

    /**
     * 绑定服务标志：通知服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 通知_服务 = Context.NOTIFICATION_SERVICE;

    /**
     * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 无障碍_服务 = Context.ACCESSIBILITY_SERVICE;

    /**
     * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 字幕_服务 = Context.CAPTIONING_SERVICE;

    /**
     * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 锁屏_服务 = Context.KEYGUARD_SERVICE;

    /**
     * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 位置_服务 = Context.LOCATION_SERVICE;

    /**
     * 绑定服务标志：搜索服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 搜索_服务 = Context.SEARCH_SERVICE;

    /**
     * 绑定服务标志：传感器服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 传感器_服务 = Context.SENSOR_SERVICE;

    /**
     * 绑定服务标志：密钥库服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    public static final String 密钥库_服务 = Context.KEYSTORE_SERVICE;

    /**
     * 绑定服务标志：存储服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 存储_服务 = Context.STORAGE_SERVICE;

    /**
     * 绑定服务标志：存储统计服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public static final String 存储_统计_服务 = Context.STORAGE_STATS_SERVICE;

    /**
     * 绑定服务标志：壁纸服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 壁纸_服务 = Context.WALLPAPER_SERVICE;

    /**
     * 绑定服务标志：振动器管理服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 振动器_管理_服务 = Context.VIBRATOR_MANAGER_SERVICE;

    /**
     * 绑定服务标志：振动器服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 振动器_服务 = Context.VIBRATOR_SERVICE;

    /**
     * 绑定服务标志：状态栏服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    public static final String 状态_栏_服务 = Context.STATUS_BAR_SERVICE;

    /**
     * 绑定服务标志：连接服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 连接_服务 = Context.CONNECTIVITY_SERVICE;

    /**
     * 绑定服务标志： tethering 服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    public static final String 共享网络_服务 = Context.TETHERING_SERVICE;

    /**
     * 绑定服务标志：IP安全服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.P)
    public static final String IP安全_服务 = Context.IPSEC_SERVICE;

    /**
     * 绑定服务标志：VPN管理服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public static final String VPN_管理_服务 = Context.VPN_MANAGEMENT_SERVICE;

    /**
     * 绑定服务标志：连接性诊断服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public static final String 连接性_诊断_服务 = Context.CONNECTIVITY_DIAGNOSTICS_SERVICE;

    /**
     * 绑定服务标志：网络统计服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 网络_统计_服务 = Context.NETWORK_STATS_SERVICE;

    /**
     * 绑定服务标志：WIFI服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String WIFI_服务 = Context.WIFI_SERVICE;

    /**
     * 绑定服务标志：WIFI点对点服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String WIFI_点对点_服务 = Context.WIFI_P2P_SERVICE;

    /**
     * 绑定服务标志：WIFI感知服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public static final String WIFI_感知_服务 = Context.WIFI_AWARE_SERVICE;

    /**
     * 绑定服务标志：WIFI RTT定位服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.P)
    public static final String WIFI_RTT_定位_服务 = Context.WIFI_RTT_RANGING_SERVICE;

    /**
     * 绑定服务标志：网络服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 网络服务发现_服务 = Context.NSD_SERVICE;

    /**
     * 绑定服务标志：音频服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 音频_服务 = Context.AUDIO_SERVICE;

    /**
     * 绑定服务标志：指纹服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 指纹_服务 = Context.FINGERPRINT_SERVICE;

    /**
     * 绑定服务标志：生物识别服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public static final String 生物识别_服务 = Context.BIOMETRIC_SERVICE;

    /**
     * 绑定服务标志：媒体通信服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 媒体_通信_服务 = Context.MEDIA_COMMUNICATION_SERVICE;

    /**
     * 绑定服务标志：媒体路由服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 媒体_路由_服务 = Context.MEDIA_ROUTER_SERVICE;

    /**
     * 绑定服务标志：媒体会话服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 媒体_会话_服务 = Context.MEDIA_SESSION_SERVICE;

    /**
     * 绑定服务标志：电话服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 电话_服务 = Context.TELEPHONY_SERVICE;

    /**
     * 绑定服务标志：电话订阅服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 电话_订阅_服务 = Context.TELEPHONY_SUBSCRIPTION_SERVICE;

    /**
     * 绑定服务标志：电信服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 电信_服务 = Context.TELECOM_SERVICE;

    /**
     * 绑定服务标志：运营商配置服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 运营商_配置_服务 = Context.CARRIER_CONFIG_SERVICE;

    /**
     * 绑定服务标志：EUICC服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.P)
    public static final String EUICC_服务 = Context.EUICC_SERVICE;

    /**
     * 绑定服务标志：剪贴板服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 剪贴板_服务 = Context.CLIPBOARD_SERVICE;

    /**
     * 绑定服务标志：文本分类服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public static final String 文本_分类_服务 = Context.TEXT_CLASSIFICATION_SERVICE;

    /**
     * 绑定服务标志：输入法服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 输入法_服务 = Context.INPUT_METHOD_SERVICE;

    /**
     * 绑定服务标志：文本服务管理器服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 文本_服务_管理器_服务 = Context.TEXT_SERVICES_MANAGER_SERVICE;

    /**
     * 绑定服务标志：应用组件服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 应用组件_服务 = Context.APPWIDGET_SERVICE;

    /**
     * 绑定服务标志：日志箱服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 日志箱_服务 = Context.DROPBOX_SERVICE;


    /**
     * 绑定服务标志：设备策略服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 设备_策略_服务 = Context.DEVICE_POLICY_SERVICE;

    /**
     * 绑定服务标志：用户模式服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String UI_模式_服务 = Context.UI_MODE_SERVICE;

    /**
     * 绑定服务标志：下载服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 下载_服务 = Context.DOWNLOAD_SERVICE;

    /**
     * 绑定服务标志：电池服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 电池_服务 = Context.BATTERY_SERVICE;

    /**
     * 绑定服务标志：NFC服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String NFC_服务 = Context.NFC_SERVICE;

    /**
     * 绑定服务标志：蓝牙服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 蓝牙_服务 = Context.BLUETOOTH_SERVICE;

    /**
     * 绑定服务标志：USB服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String USB_服务 = Context.USB_SERVICE;

    /**
     * 绑定服务标志：输入服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 输入_服务 = Context.INPUT_SERVICE;

    /**
     * 绑定服务标志：显示服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 显示_服务 = Context.DISPLAY_SERVICE;

    /**
     * 绑定服务标志：用户服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 用户_服务 = Context.USER_SERVICE;

    /**
     * 绑定服务标志：启动器应用服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 启动器_应用_服务  = Context.LAUNCHER_APPS_SERVICE;

    /**
     * 绑定服务标志：限制服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 限制_服务 = Context.RESTRICTIONS_SERVICE;

    /**
     * 绑定服务标志：应用操作服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 应用_操作_服务 = Context.APP_OPS_SERVICE;

    /**
     * 绑定服务标志：角色服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.Q)
    public static final String 角色_服务 = Context.ROLE_SERVICE;

    /**
     * 绑定服务标志：相机服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 相机_服务 = Context.CAMERA_SERVICE;

    /**
     * 绑定服务标志：打印服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 打印_服务 = Context.PRINT_SERVICE;

    /**
     * 绑定服务标志：配套设备服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public static final String 配套_设备_服务 = Context.COMPANION_DEVICE_SERVICE;

    /**
     * 绑定服务标志：虚拟设备服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 虚拟_设备_服务 = Context.VIRTUAL_DEVICE_SERVICE;

    /**
     * 绑定服务标志：消费者红外服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 消费者_信息_服务 = Context.CONSUMER_IR_SERVICE;

    /**
     * 绑定服务标志：电视互动应用服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    public static final String 电视_互动_应用_服务 = Context.TV_INTERACTIVE_APP_SERVICE;

    /**
     * 绑定服务标志：电视输入服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 电视_输入_服务 = Context.TV_INPUT_SERVICE;

    /**
     * 绑定服务标志：电视广告服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    public static final String 电视_广告_服务 = Context.TV_AD_SERVICE;

    /**
     * 绑定服务标志：使用统计服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 使用_统计_服务 = Context.USAGE_STATS_SERVICE;

    /**
     * 绑定服务标志：作业调度服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 作业_调度_服务 = Context.JOB_SCHEDULER_SERVICE;

    /**
     * 绑定服务标志：持久数据块服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 持久_数据_块_服务 = Context.PERSISTENT_DATA_BLOCK_SERVICE;

    /**
     * 绑定服务标志：媒体投影服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 媒体_投影_服务 = Context.MEDIA_PROJECTION_SERVICE;

    /**
     * 绑定服务标志：MIDI服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String MIDI_服务 = Context.MIDI_SERVICE;

    /**
     * 绑定服务标志：硬件属性服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 硬件_属性_服务 = Context.HARDWARE_PROPERTIES_SERVICE;

    /**
     * 绑定服务标志：性能提示服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 性能_提示_服务 = Context.PERFORMANCE_HINT_SERVICE;

    /**
     * 绑定服务标志：快捷方式服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.N_MR1)
    public static final String 快捷方式_服务 = Context.SHORTCUT_SERVICE;

    /**
     * 绑定服务标志：系统健康服务标志，如果服务正在运行，则将其切换到客户。
     */
    public static final String 系统_健康_服务 = Context.SYSTEM_HEALTH_SERVICE;

    /**
     * 绑定服务标志：错误报告服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 错误报告_服务 = Context.BUGREPORT_SERVICE;

    /**
     * 绑定服务标志：覆盖服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 覆盖_服务 = Context.OVERLAY_SERVICE;

    /**
     * 绑定服务标志：跨配置文件应用服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.P)
    public static final String 跨_配置文件_应用_服务 = Context.CROSS_PROFILE_APPS_SERVICE;

    /**
     * 绑定服务标志：电话 IMS 服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public static final String 电话_IMS_服务 = Context.TELEPHONY_IMS_SERVICE;

    /**
     * 绑定服务标志：Blob 存储服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public static final String BLOB_存储_服务 = Context.BLOB_STORE_SERVICE;

    /**
     * 绑定服务标志：应用搜索服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 应用_搜索_服务 = Context.APP_SEARCH_SERVICE;

    /**
     * 绑定服务标志：应用功能服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    public static final String 应用_功能_服务 = Context.APP_FUNCTION_SERVICE;

    /**
     * 绑定服务标志：高级保护服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    public static final String 高级_保护_服务 = Context.ADVANCED_PROTECTION_SERVICE;

    /**
     * 绑定服务标志：文件完整性服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public static final String 文件_完整性_服务 = Context.FILE_INTEGRITY_SERVICE;

    /**
     * 绑定服务标志：人员服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 人员_服务 = Context.PEOPLE_SERVICE;

    /**
     * 绑定服务标志：媒体指标服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 媒体_指标_服务 = Context.MEDIA_METRICS_SERVICE;

    /**
     * 绑定服务标志：游戏服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 游戏_服务 = Context.GAME_SERVICE;

    /**
     * 绑定服务标志：域名验证服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 域名_验证_服务 = Context.DOMAIN_VERIFICATION_SERVICE;

    /**
     * 绑定服务标志：显示哈希服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public static final String 显示_哈希_服务 = Context.DISPLAY_HASH_SERVICE;

    /**
     * 绑定服务标志：本地服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    public static final String 本地_服务 = Context.LOCALE_SERVICE;

    /**
     * 绑定服务标志：健康连接服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 健康连接_服务 = Context.HEALTHCONNECT_SERVICE;

    /**
     * 绑定服务标志：凭证服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 凭证_服务 = Context.CREDENTIAL_SERVICE;

    /**
     * 绑定服务标志：设备锁服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 设备_锁_服务 = Context.DEVICE_LOCK_SERVICE;

    /**
     * 绑定服务标志：语法变位服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 语法_变位_服务 = Context.GRAMMATICAL_INFLECTION_SERVICE;

    /**
     * 绑定服务标志：卫星服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    public static final String 卫星_服务 = Context.SATELLITE_SERVICE;

    /**
     * 绑定服务标志：安全状态服务标志，如果服务正在运行，则将其切换到客户
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 安全_状态_服务 = Context.SECURITY_STATE_SERVICE;

    /**
     * 绑定服务标志：联系人密钥服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 联系人_密钥_服务 = Context.CONTACT_KEYS_SERVICE;

    /**
     * 绑定服务标志：分析服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 分析_服务 = Context.PROFILING_SERVICE;

    /**
     * 绑定服务标志：媒体质量服务标志，如果服务正在运行，则将其切换到客户。
     */
    @RequiresApi(Build.VERSION_CODES.BAKLAVA)
    public static final String 媒体_质量_服务 = Context.MEDIA_QUALITY_SERVICE;

    //==============================================================================================

    /**
     * 检查权限
     */
    public int 检查权限(String 权限, int pid , int uid){
        return this.checkPermission(权限, pid, uid);
    }

    /**
     * 检查调用权限
     */
    public int 检查调用权限(String 权限){
        return this.checkCallingPermission(权限);
    }

    /**
     * 检查调用或自身权限
     */
    public int 检查调用或自身权限(String 权限){
        return this.checkCallingOrSelfPermission(权限);
    }

    /**
     * 检查自身权限
     */
    public int 检查自身权限(String 权限){
        return this.checkSelfPermission(权限);
    }

    /**
     * 执行权限
     */
    public void 执行权限(String 权限, int pid , int uid, String 信息){
        this.enforcePermission(权限, pid, uid, 信息);
    }

    /**
     * 强制执行调用权限
     */
    public void 强制执行调用权限(String 权限, String 信息){
        this.enforceCallingPermission(权限, 信息);
    }

    /**
     * 执行调用或自身权限
     */
    public void 执行调用或自身权限(String 权限, String 信息){
        this.enforceCallingOrSelfPermission(权限, 信息);
    }

    /**
     * 授予URI权限
     */
    public void 授予URI权限(String 权限, Uri uri, int 模式标志){
        this.grantUriPermission(权限, uri,模式标志);
    }

    /**
     * 撤销URI权限
     */
    public void 撤销URI权限(Uri uri, int 模式标志){
        this.revokeUriPermission(uri,模式标志);
    }

    /**
     * 撤销URI权限
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public void 撤销URI权限(String 到包, Uri uri, int 模式标志){
        this.revokeUriPermission(到包,uri,模式标志);
    }

    /**
     * 检查Uri权限
     */
    public int 检查Uri权限(Uri uri, int pid , int uid, int 模式标志){
        return this.checkUriPermission(uri, pid, uid, 模式标志);
    }

    /**
     * 检查内容URI权限完整
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public int 检查内容URI权限完整(Uri uri, int pid, int uid, int 模式标志){
        return this.checkContentUriPermissionFull(uri, pid, uid, 模式标志);
    }

    /**
     * 检查Uri权限组
     */
    @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public int[] 检查Uri权限组(List<Uri> uris, int pid, int uid, int 模式标志){
        return this.checkUriPermissions(uris, pid, uid, 模式标志);
    }

    /**
     * 检查调用URI权限
     */
    public int 检查调用URI权限(Uri uri, int 模式标志){
        return this.checkCallingUriPermission(uri, 模式标志);
    }

    /**
     * 检查调用URI权限组
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public int[] 检查调用URI权限组(List<Uri> uris, int 模式标志){
        return this.checkCallingUriPermissions(uris, 模式标志);
    }

    /**
     * 检查调用或自身URI权限
     */
    public int 检查调用或自身URI权限(Uri uri, int 模式标志){
        return this.checkCallingOrSelfUriPermission(uri, 模式标志);
    }

    /**
     * 检查调用或自身URI权限组
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public int[] 检查调用或自身URI权限组(List<Uri> uris, int 模式标志){
        return this.checkCallingOrSelfUriPermissions(uris, 模式标志);
    }

    /**
     * 检查Uri权限
     */
    public int 检查Uri权限(Uri uri, String 读取权限, String 写入权限, int pid, int uid, int 模式标志){
        return this.checkUriPermission(uri,读取权限,写入权限, pid, uid, 模式标志);
    }

    /**
     * 强制URI权限
     */
    public void 强制URI权限(Uri uri, int pid, int uid, int 模式标志, String 信息){
        this.enforceUriPermission(uri, pid, uid, 模式标志, 信息);
    }

    /**
     * 强制调用URI权限
     */
     public void 强制调用URI权限(Uri uri, int 模式标志, String 信息){
        this.enforceCallingUriPermission(uri,  模式标志, 信息);
     }

    /**
     * 执行调用或自身URI权限
     */
    public void 执行调用或自身URI权限(Uri uri, int 模式标志, String 信息){
        this.enforceCallingOrSelfUriPermission(uri,  模式标志, 信息);
    }

    /**
     * 强制URI权限
     */
    public void 强制URI权限(Uri uri, String 读取权限, String 写入权限, int pid, int uid, int 模式标志, String 信息){
        this.enforceUriPermission(uri,读取权限,写入权限, pid, uid, 模式标志, 信息);
    }

    /**
     * 应用终止时撤销自身权限
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    public void 应用终止时撤销自身权限(String 权限名){
        this.revokeSelfPermissionOnKill(权限名);
    }

    /**
     * 应用终止时撤销自身权限组
     */
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    public void 应用终止时撤销自身权限组(Collection<String> 权限组){
        this.revokeSelfPermissionsOnKill(权限组);
    }

    //==============================================================================================

    /**
     * 绑定服务标志：包含代码标志，如果服务正在运行，则将其切换
     */
    public static final int 上下文_包含_代码 = Context.CONTEXT_INCLUDE_CODE;

    /**
     * 绑定服务标志：忽略安全标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 上下文_忽略_安全 = Context.CONTEXT_IGNORE_SECURITY;

    /**
     * 绑定服务标志：受限标志，如果服务正在运行，则将其切换到客户。
     */
    public static final int 上下文_受限 = Context.CONTEXT_RESTRICTED;

    //==============================================================================================

    /**
     * 创建包上下文
     */
    public Context 创建包上下文(String 包名, int 标志) throws PackageManager.NameNotFoundException {
        return this.createPackageContext(包名, 标志);
    }

    /**
     * 创建上下文分包
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public Context 创建上下文分包(String 分包名) throws PackageManager.NameNotFoundException {
        return this.createContextForSplit(分包名);
    }

    /**
     * 创建配置上下文
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public Context 创建配置上下文(Configuration 覆盖配置) {
        return this.createConfigurationContext(覆盖配置);
    }

    /**
     * 创建显示上下文
     */
    @RequiresApi(Build.VERSION_CODES.O)
    public Context 创建显示上下文(Display 显示) {
        return this.createDisplayContext(显示);
    }

    /**
     * 创建设备上下文
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public Context 创建设备上下文(int 设备id) {
        return this.createDeviceContext(设备id);
    }

    /**
     * 创建窗口上下文
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public Context 创建窗口上下文(int 类型, Bundle 选项) {
        return this.createWindowContext(类型,选项);
    }

    /**
     * 创建窗口上下文
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public Context 创建窗口上下文(Display 显示, int 类型, Bundle 选项) {
        return this.createWindowContext(显示,类型,选项);
    }

    /**
     * 创建上下文
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public Context 创建上下文(ContextParams 上下文参数) {
        return this.createContext(上下文参数);
    }

    /**
     * 创建属性上下文
     */
    @RequiresApi(Build.VERSION_CODES.S)
    public Context 创建属性上下文(String 属性标签) {
        return this.createAttributionContext(属性标签);
    }

    /**
     * 创建设备保护存储上下文
     */
    public Context 创建设备保护存储上下文() {
        return this.createDeviceProtectedStorageContext();
    }

    /**
     * 获取显示
     */
    @RequiresApi(Build.VERSION_CODES.R)
    public Display 取显示() {
        return this.getDisplay();
    }

    /**
     * 获取设备id
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public int 取设备id() {
        return this.getDeviceId();
    }

    /**
     * 注册设备id改变监听器
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 注册设备id改变监听器(Executor 执行者, IntConsumer 监听器){
        this.registerDeviceIdChangeListener(执行者,监听器);
    }

    /**
     * 非注册设备id改变监听器
     */
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public void 非注册设备id改变监听器(IntConsumer 监听器){
        this.unregisterDeviceIdChangeListener(监听器);
    }

    /**
     * 是否受限
     */
    public boolean 是否受限(){
        return this.isRestricted();
    }

    /**
     * 是否设备加密存储
     */
    public boolean 是否设备加密存储(){
        return this.isDeviceProtectedStorage();
    }

    /**
     * 是否界面上下文
     */
    @RequiresApi(api = Build.VERSION_CODES.S)
    public boolean 是否界面上下文(){
        return this.isUiContext();
    }

}
