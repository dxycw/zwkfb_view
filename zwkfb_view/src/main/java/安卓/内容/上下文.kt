package 安卓.内容

import android.Manifest
import android.annotation.SuppressLint
import android.content.AttributionSource
import android.content.BroadcastReceiver
import android.content.ComponentCallbacks
import android.content.ComponentName
import android.content.ContentResolver
import android.content.Context
import android.content.ContextParams
import android.content.Intent
import android.content.IntentFilter
import android.content.IntentSender
import android.content.ServiceConnection
import android.content.SharedPreferences
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.AssetManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.content.res.TypedArray
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.UserHandle
import android.util.AttributeSet
import android.view.Display
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.annotation.StyleableRes
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.util.concurrent.Executor
import java.util.function.IntConsumer

/**
 * 获取资产
 */
val Context.资产: AssetManager get() = this.assets

/**
 * 获取资产
 */
fun Context.取资产(): AssetManager = this.getAssets()

//=======================================================================

/**
 * 获取资源
 */
val Context.资源: Resources get() = this.resources

/**
 * 获取资源
 */
fun Context.取资源(): Resources = this.getResources()

//============================================================================

/**
 * 获取包管理器
 */
val Context.包管理器: PackageManager get() = this.packageManager

/**
 * 获取包管理器
 */
fun Context.取包管理器(): PackageManager = this.getPackageManager()

//============================================================================

/**
 * 获取内容解析器
 */
val Context.内容解析器: ContentResolver get() = this.contentResolver

/**
 * 获取内容解析器
 */
fun Context.取内容解析器(): ContentResolver = this.getContentResolver()

//============================================================================

/**
 * 获取主循环器
 */
val Context.主循环器: Looper get() = this.mainLooper

/**
 * 获取主循环器
 */
fun Context.取主循环器(): Looper = this.getMainLooper()

//============================================================================

/**
 * 获取主执行器
 */
val Context.主执行器: Executor
    @RequiresApi(Build.VERSION_CODES.P)
    get() = this.mainExecutor

/**
 * 获取主执行器
 */
@RequiresApi(Build.VERSION_CODES.P)
fun Context.取主执行器(): Executor = this.getMainExecutor()

//============================================================================

/**
 * 获取应用程序上下文
 */
val Context.应用程序上下文: Context
    get() = this.applicationContext

/**
 * 获取应用程序上下文
 */
fun Context.取应用程序上下文(): Context = this.getApplicationContext()

//============================================================================

/**
 * 注册组件回调
 */
fun Context.注册组件回调(回调: ComponentCallbacks) = this.registerComponentCallbacks(回调)

/**
 * 取消注册组件回调
 */
fun Context.取消注册组件回调(回调: ComponentCallbacks) = this.unregisterComponentCallbacks(回调)

//============================================================================

/**
 * 获取文本
 */
fun Context.取文本(@StringRes 资源id: Int): CharSequence = this.getText(资源id)

//============================================================================

/**
 * 获取字符串
 */
fun Context.取字符串(@StringRes 资源id: Int): CharSequence = this.getString(资源id)

/**
 * 获取字符串
 */
fun Context.取字符串(@StringRes 资源id: Int, vararg 格式参数: Any?): CharSequence = this.getString(资源id,格式参数)

//============================================================================

/**
 * 获取颜色
 */
fun Context.取颜色(@ColorRes id: Int): Int = this.getColor(id)

//============================================================================

/**
 * 获取可绘制对象
 */
@SuppressLint("UseCompatLoadingForDrawables")
fun Context.取可绘制对象(@DrawableRes id: Int): Drawable? = this.getDrawable(id)

//============================================================================

/**
 * 获取颜色状态列表
 */
@SuppressLint("UseCompatLoadingForDrawables")
fun Context.取颜色状态列表(@ColorRes id: Int): ColorStateList = this.getColorStateList(id)

//============================================================================

/**
 * 置主题
 */
fun Context.置主题(主题: Int) = this.setTheme(主题)

/**
 * 获取主题
 */
val Context.主题: Resources.Theme get() = this.theme

/**
 * 获取主题
 */
fun Context.取主题(): Resources.Theme = this.getTheme()

//=======================================================================

/**
 * 获取样式属性
 */
fun Context.取样式属性(@StyleableRes 属性: IntArray): TypedArray = this.obtainStyledAttributes(属性)

/**
 * 获取样式属性
 */
fun Context.取样式属性(@StyleRes 资源id : Int, @StyleableRes 属性: IntArray): TypedArray = this.obtainStyledAttributes(资源id,属性)

/**
 * 获取样式属性
 */
fun Context.取样式属性(置: AttributeSet, @StyleableRes 属性: IntArray): TypedArray = this.obtainStyledAttributes(置,属性)

/**
 * 获取样式属性
 */
fun Context.取样式属性(置: AttributeSet, @StyleableRes 属性: IntArray, @AttrRes 默认样式属性: Int, @StyleRes 默认样式资源: Int): TypedArray =
    this.obtainStyledAttributes(置,属性, 默认样式属性 ,默认样式资源)

//=======================================================================

/**
 * 获取类加载器
 */
val Context.类加载器: ClassLoader get() = this.classLoader

/**
 * 获取类加载器
 */
fun Context.取类加载器(): ClassLoader = this.getClassLoader()

//=======================================================================

/**
 * 获取包名
 */
val Context.包名: String get() = this.packageName

/**
 * 获取包名
 */
fun Context.取包名(): String = this.getPackageName()

//========================================================================

/**
 * 获取操作包名
 */
val Context.操作包名: String
    @RequiresApi(Build.VERSION_CODES.Q)
    get() = this.opPackageName

/**
 * 获取操作包名
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun Context.取操作包名(): String = this.getOpPackageName()

//========================================================================

/**
 * 获取归属标签
 */
val Context.归属标签: String?
    @RequiresApi(Build.VERSION_CODES.R)
    get() = this.attributionTag

/**
 * 获取归属标签
 */
@RequiresApi(Build.VERSION_CODES.R)
fun Context.取归属标签(): String? = this.getAttributionTag()

//========================================================================

/**
 * 获取归属来源
 */
val Context.取归属来源: AttributionSource
    @RequiresApi(Build.VERSION_CODES.S)
    get() = this.attributionSource

/**
 * 获取归属来源
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.取归属来源(): AttributionSource = this.getAttributionSource()

//========================================================================

/**
 * 获取参数
 */
val Context.参数: ContextParams?
    @RequiresApi(Build.VERSION_CODES.S)
    get() = this.params

/**
 * 获取参数
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.取参数(): ContextParams? = this.getParams()

//========================================================================

/**
 * 获取应用信息
 */
val Context.应用信息: ApplicationInfo
    get() = this.applicationInfo

/**
 * 获取应用信息
 */
fun Context.取应用信息(): ApplicationInfo = this.getApplicationInfo()

//========================================================================

/**
 * 获取包资源路径
 */
val Context.包资源路径: String
    get() = this.packageResourcePath

/**
 * 获取包资源路径
 */
fun Context.取包资源路径(): String = this.getPackageResourcePath()

//========================================================================

/**
 * 获取包代码路径
 */
val Context.包代码路径: String
    get() = this.packageCodePath

/**
 * 获取包代码路径
 */
fun Context.取包代码路径(): String = this.getPackageCodePath()

//========================================================================

/**
 * 获取共享偏好
 */
fun Context.取共享偏好(名: String, 模式: Int): SharedPreferences = this.getSharedPreferences(名, 模式)

//========================================================================

/**
 * 移动共享偏好自
 */
fun Context.移动共享偏好自(源上下文: Context, 名: String): Boolean = this.moveSharedPreferencesFrom(源上下文, 名)

//========================================================================

/**
 * 删除共享偏好
 */
fun Context.删除共享偏好(名: String): Boolean = this.deleteSharedPreferences(名)

//========================================================================

/**
 * 打开文件输入
 */
fun Context.打开文件输入(名: String): FileInputStream = this.openFileInput(名)

//========================================================================

/**
 * 打开文件输出
 */
fun Context.打开文件输出(名: String, 模式: Int): FileOutputStream = this.openFileOutput(名, 模式)

//========================================================================

/**
 * 删除文件
 */
fun Context.删除文件(名: String): Boolean = this.deleteFile(名)

//========================================================================

/**
 * 获取文件流路径
 */
fun Context.取文件流路径(名: String): File = this.getFileStreamPath(名)

//========================================================================

/**
 * 获取数据目录
 */
val Context.数据目录: File
    get() = this.dataDir

/**
 * 获取数据目录
 */
fun Context.取数据目录(): File = this.getDataDir()

//========================================================================

/**
 * 获取文件目录
 */
val Context.文件目录: File
    get() = this.filesDir

/**
 * 获取文件目录
 */
fun Context.取文件目录(): File = this.getFilesDir()

//========================================================================

/**
 * 获取无备份文件目录
 */
val Context.无备份文件目录: File
    get() = this.noBackupFilesDir

/**
 * 获取无备份文件目录
 */
fun Context.取无备份文件目录(): File = this.getNoBackupFilesDir()

//========================================================================

/**
 * 获取外部文件目录
 */
fun Context.取外部文件目录(类型 : String?): File? = this.getExternalFilesDir(类型)

/**
 * 获取外部文件目录
 */
fun Context.取外部文件目录(类型 : String): Array<File> = this.getExternalFilesDirs(类型)

//========================================================================

/**
 * 获取obb目录
 */
val Context.obb目录: File get() = this.obbDir

/**
 * 获取Obb目录
 */
fun Context.取Obb目录(): File = this.getObbDir()

/**
 * 获取obb目录组
 */
val Context.obb目录组: Array<File> get() = this.obbDirs

/**
 * 获取Obb目录组
 */
fun Context.取Obb目录组(): Array<File> = this.getObbDirs()

//========================================================================

/**
 * 获取缓存目录
 */
val Context.缓存目录: File get() = this.cacheDir

/**
 * 获取缓存目录
 */
fun Context.取缓存目录(): File = this.getCacheDir()

//========================================================================

/**
 * 获取代码缓存目录
 */
val Context.代码缓存目录: File get() = this.codeCacheDir

/**
 * 获取代码缓存目录
 */
fun Context.取代码缓存目录(): File = this.getCodeCacheDir()

//========================================================================

/**
 * 获取外部缓存目录
 */
val Context.外部缓存目录: File? get() = this.externalCacheDir

/**
 * 获取外部缓存目录
 */
fun Context.取外部缓存目录(): File? = this.getExternalCacheDir()

/**
 * 获取外部缓存目录组
 */
val Context.外部缓存目录组: Array<File> get() = this.externalCacheDirs

/**
 * 获取外部缓存目录组
 */
fun Context.取外部缓存目录组(): Array<File> = this.getExternalCacheDirs()

//========================================================================

/**
 * 获取外部媒体目录组
 */
val Context.外部媒体目录组: Array<File> get() = this.externalMediaDirs

/**
 * 获取外部媒体目录组
 */
fun Context.取外部媒体目录组(): Array<File> = this.getExternalMediaDirs()

//========================================================================

/**
 * 获取文件列表
 */
val Context.文件列表: Array<String> get() = this.fileList()

/**
 * 获取文件列表
 */
fun Context.取文件列表(): Array<String> = this.fileList()

//========================================================================

/**
 * 获取目录
 */
fun Context.取目录(名: String, 模式: Int): File = this.getDir(名, 模式)

//========================================================================

/**
 * 打开或创建数据库
 */
fun Context.打开或创建数据库(名: String, 模式: Int ,工厂: SQLiteDatabase.CursorFactory?): SQLiteDatabase =
    this.openOrCreateDatabase(名, 模式, 工厂)

/**
 * 打开或创建数据库
 */
fun Context.打开或创建数据库(名: String, 模式: Int ,工厂: SQLiteDatabase.CursorFactory? ,错误处理程序 : DatabaseErrorHandler?): SQLiteDatabase =
    this.openOrCreateDatabase(名, 模式, 工厂, 错误处理程序)

//========================================================================

/**
 * 移动数据库自
 */
fun Context.移动数据库自(源上下文: Context, 名: String): Boolean =
    this.moveDatabaseFrom(源上下文, 名)

//========================================================================

/**
 * 删除数据库
 */
fun Context.删除数据库(名: String): Boolean = this.deleteDatabase(名)

//========================================================================

/**
 * 获取数据库路径
 */
fun Context.取数据库路径(名: String): File = this.getDatabasePath(名)

//========================================================================

/**
 * 数据库列表
 */
fun Context.数据库列表(): Array<String> = this.databaseList()

//========================================================================

/**
 * 获取壁纸
 */
val Context.壁纸: Drawable get() = this.wallpaper

/**
 * 获取壁纸
 */
fun Context.取壁纸(): Drawable = this.getWallpaper()

//========================================================================

/**
 * 预览壁纸
 */
fun Context.预览壁纸(): Drawable = this.peekWallpaper()

//========================================================================

/**
 * 获取壁纸期望的最小宽度
 */
val Context.壁纸期望的最小宽度: Int get() = this.wallpaperDesiredMinimumWidth

/**
 * 获取壁纸期望的最小宽度
 */
fun Context.取壁纸期望的最小宽度(): Int = this.getWallpaperDesiredMinimumWidth()

//========================================================================

/**
 * 获取壁纸期望的最小高度
 */
val Context.壁纸期望的最小高度: Int get() = this.wallpaperDesiredMinimumHeight

/**
 * 获取壁纸期望的最小高度
 */
fun Context.取壁纸期望的最小高度(): Int = this.getWallpaperDesiredMinimumHeight()

//========================================================================

/**
 * 设置壁纸
 */
fun Context.置壁纸(位图: Bitmap) = this.setWallpaper(位图)

/**
 * 设置壁纸
 */
fun Context.置壁纸(数据: InputStream) = this.setWallpaper(数据)

//========================================================================

/**
 * 清除壁纸
 */
fun Context.清除壁纸() = this.clearWallpaper()

//========================================================================

/**
 * 启动活动
 */
fun Context.启动活动(@RequiresPermission 意图: Intent) = this.startActivity(意图)

/**
 * 启动活动
 */
fun Context.启动活动(意图: Intent, 选项: Bundle?) = this.startActivity(意图,选项)

//========================================================================

/**
 * 启动多活动
 */
fun Context.启动多活动(意图: Array<Intent>) = this.startActivities(意图)

/**
 * 启动多活动
 */
fun Context.启动多活动(意图: Array<Intent>, 选项: Bundle?) = this.startActivities(意图,选项)

//============================================================================

/**
 * 启动意图发送器
 */
fun Context.启动意图发送器(意图: IntentSender, 填充意图: Intent, 标志掩码: Int, 标志值: Int, 额外标志: Int) =
    this.startIntentSender(意图,填充意图, 标志掩码, 标志值, 额外标志)

/**
 * 启动意图发送器
 */
fun Context.启动意图发送器(意图: IntentSender, 填充意图: Intent, 标志掩码: Int, 标志值: Int, 额外标志: Int, 选项: Bundle?) =
    this.startIntentSender(意图,填充意图, 标志掩码, 标志值, 额外标志, 选项)

//============================================================================

/**
 * 发送广播
 */
fun Context.发送广播(@RequiresPermission 意图: Intent) = this.sendBroadcast(意图)

/**
 * 发送广播
 */
fun Context.发送广播(@RequiresPermission 意图: Intent, 接收者权限 : String?) = this.sendBroadcast(意图, 接收者权限)

//============================================================================

/**
 * 发送广播带多个权限
 */
@RequiresApi(Build.VERSION_CODES.R)
fun Context.发送广播带多个权限(意图: Intent, 接收者权限 : Array<String>) =
    this.sendBroadcastWithMultiplePermissions(意图, 接收者权限)

//============================================================================

/**
 * 发送广播
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.发送广播(意图: Intent, 接收者权限 : String?, 选项: Bundle?) =
    this.sendBroadcast(意图, 接收者权限, 选项)

//============================================================================

/**
 * 发送有序广播
 */
fun Context.发送有序广播(@RequiresPermission 意图: Intent, 接收者权限 : String?) =
    this.sendOrderedBroadcast(意图, 接收者权限)

/**
 * 发送有序广播
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.发送有序广播(意图: Intent, 接收者权限 : String?, 选项: Bundle?) =
    this.sendOrderedBroadcast(意图, 接收者权限, 选项)

/**
 * 发送有序广播
 */
fun Context.发送有序广播(@RequiresPermission 意图: Intent, 接收者权限 : String?, 结果接收器 : BroadcastReceiver?, 调度器 : Handler?,初始代码 : Int, 初始数据 : String?,  初始附加项: Bundle?) =
    this.sendOrderedBroadcast(意图, 接收者权限, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项)

/**
 * 发送有序广播
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.发送有序广播(意图: Intent, 接收者权限 : String?, 选项: Bundle?, 结果接收器 : BroadcastReceiver?, 调度器 : Handler?,初始代码 : Int, 初始数据 : String?,  初始附加项: Bundle?) =
    this.sendOrderedBroadcast(意图, 接收者权限, 选项,结果接收器, 调度器,初始代码, 初始数据,  初始附加项)

//============================================================================

/**
 * 发送广播为用户
 */
@RequiresPermission("android.permission.INTERACT_ACROSS_USERS")
fun Context.发送广播为用户(@RequiresPermission 意图: Intent, 用户 : UserHandle?) =
    this.sendBroadcastAsUser(意图, 用户)

/**
 * 发送广播为用户
 */
@RequiresPermission("android.permission.INTERACT_ACROSS_USERS")
fun Context.发送广播为用户(@RequiresPermission 意图: Intent, 用户 : UserHandle?, 接收者权限 : String?) =
    this.sendBroadcastAsUser(意图, 用户, 接收者权限)

//============================================================================

/**
 * 发送有序广播为用户
 */
@RequiresPermission("android.permission.INTERACT_ACROSS_USERS")
fun Context.发送有序广播为用户(@RequiresPermission 意图: Intent, 用户 : UserHandle?, 接收者权限 : String?, 结果接收器 : BroadcastReceiver?, 调度器 : Handler?, 初始代码 : Int, 初始数据 : String?, 初始附加项: Bundle?) =
    this.sendOrderedBroadcastAsUser(意图, 用户, 接收者权限, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项)

//============================================================================

/**
 * 发送有序广播
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.发送有序广播(意图: Intent, 接收者权限 : String?, 接收器应用操作: String?, 结果接收器 : BroadcastReceiver?, 调度器 : Handler?,初始代码 : Int, 初始数据 : String?,  初始附加项: Bundle?) =
    this.sendOrderedBroadcast(意图, 接收者权限, 接收器应用操作,结果接收器, 调度器,初始代码, 初始数据,  初始附加项)

//============================================================================

/**
 * 发送粘性广播
 */
@RequiresPermission(Manifest.permission.BROADCAST_STICKY)
fun Context.发送粘性广播(@RequiresPermission 意图: Intent) = this.sendStickyBroadcast(意图)

/**
 * 发送粘性广播
 */
@RequiresApi(Build.VERSION_CODES.S)
@RequiresPermission(Manifest.permission.BROADCAST_STICKY)
fun Context.发送粘性广播(@RequiresPermission 意图: Intent, 选项: Bundle?) = this.sendStickyBroadcast(意图, 选项)

//============================================================================

/**
 * 发送有序粘性广播
 */
@RequiresPermission(Manifest.permission.BROADCAST_STICKY)
fun Context.发送有序粘性广播(@RequiresPermission 意图: Intent, 结果接收器 : BroadcastReceiver, 调度器: Handler, 初始代码 : Int, 初始数据 : String?, 初始附加项: Bundle?) =
    this.sendStickyOrderedBroadcast(意图, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项)

//============================================================================

/**
 * 移除粘性广播
 */
@RequiresPermission(Manifest.permission.BROADCAST_STICKY)
fun Context.移除粘性广播(@RequiresPermission 意图: Intent) = this.removeStickyBroadcast(意图)

//============================================================================

/**
 * 发送粘性广播为用户
 */
@RequiresPermission(allOf = [Manifest.permission.BROADCAST_STICKY, "android.permission.INTERACT_ACROSS_USERS"])
fun Context.发送粘性广播为用户(@RequiresPermission 意图: Intent, 用户 : UserHandle) = this.sendStickyBroadcastAsUser(意图,用户)

//============================================================================

/**
 * 发送有序粘性广播为用户
 */
@RequiresPermission(allOf = [Manifest.permission.BROADCAST_STICKY, "android.permission.INTERACT_ACROSS_USERS"])
fun Context.发送有序粘性广播为用户(@RequiresPermission 意图: Intent, 用户 : UserHandle?, 结果接收器 : BroadcastReceiver, 调度器: Handler, 初始代码 : Int, 初始数据 : String?, 初始附加项: Bundle?) =
    this.sendStickyOrderedBroadcastAsUser(意图, 用户, 结果接收器, 调度器,初始代码, 初始数据,  初始附加项)

//============================================================================

/**
 * 移除粘性广播为用户
 */
@RequiresPermission(allOf = [Manifest.permission.BROADCAST_STICKY, "android.permission.INTERACT_ACROSS_USERS"])
fun Context.移除粘性广播为用户(@RequiresPermission 意图: Intent, 用户 : UserHandle) = this.removeStickyBroadcastAsUser(意图,用户)

//============================================================================

/**
 * 注册接收器
 */
@SuppressLint("UnspecifiedRegisterReceiverFlag")
fun Context.注册接收器(接收器 : BroadcastReceiver?, 过滤器 : IntentFilter): Intent? = this.registerReceiver(接收器 ,过滤器)

/**
 * 注册接收器
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Context.注册接收器(接收器 : BroadcastReceiver?, 过滤器 : IntentFilter, @ContextCompat.RegisterReceiverFlags 标志 : Int): Intent? =
    this.registerReceiver(接收器 ,过滤器,标志)

/**
 * 注册接收器
 */
@SuppressLint("UnspecifiedRegisterReceiverFlag")
fun Context.注册接收器(接收器 : BroadcastReceiver, 过滤器 : IntentFilter, 广播权限 : String?, 调度器 : Handler?): Intent? =
    this.registerReceiver(接收器 ,过滤器,广播权限, 调度器)

/**
 * 注册接收器
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Context.注册接收器(接收器 : BroadcastReceiver, 过滤器 : IntentFilter, 广播权限 : String?, 调度器 : Handler?, @ContextCompat.RegisterReceiverFlags 标志 : Int): Intent? =
    this.registerReceiver(接收器 ,过滤器,广播权限, 调度器, 标志)

//============================================================================

/**
 * 取消注册接收器
 */
@SuppressLint("UnspecifiedRegisterReceiverFlag")
fun Context.取消注册接收器(接收器 : BroadcastReceiver) = this.unregisterReceiver(接收器)

//============================================================================

/**
 * 启动服务
 */
@SuppressLint("UnspecifiedRegisterReceiverFlag")
fun Context.启动服务(服务 : Intent): ComponentName? = this.startService(服务)

//============================================================================

/**
 * 启动前台服务
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Context.启动前台服务(服务 : Intent): ComponentName? = this.startForegroundService(服务)

//============================================================================

/**
 * 停止服务
 */
fun Context.停止服务(服务 : Intent): Boolean = this.stopService(服务)

//============================================================================

/**
 * 绑定服务
 */
fun Context.绑定服务(@RequiresPermission 服务 : Intent, 连接: ServiceConnection, 标志: Int): Boolean =
    this.bindService(服务, 连接, 标志)

/**
 * 绑定服务
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.绑定服务(@RequiresPermission 服务 : Intent, 连接: ServiceConnection, 标志: Context.BindServiceFlags): Boolean =
    this.bindService(服务, 连接, 标志)

/**
 * 绑定服务
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun Context.绑定服务(@RequiresPermission 服务 : Intent, 标志: Int, 执行者: Executor, 连接: ServiceConnection): Boolean =
    this.bindService(服务, 标志, 执行者, 连接)

/**
 * 绑定服务
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.绑定服务(@RequiresPermission 服务 : Intent, 标志: Context.BindServiceFlags, 执行者: Executor, 连接: ServiceConnection): Boolean =
    this.bindService(服务, 标志, 执行者, 连接)

//============================================================================

/**
 * 绑定隔离服务
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.绑定隔离服务(@RequiresPermission 服务 : Intent, 标志: Int, 实例名: String, 执行者: Executor, 连接: ServiceConnection): Boolean =
    this.bindIsolatedService(服务, 标志, 实例名, 执行者, 连接)

/**
 * 绑定隔离服务
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.绑定隔离服务(@RequiresPermission 服务 : Intent, 标志: Context.BindServiceFlags, 实例名: String, 执行者: Executor, 连接: ServiceConnection): Boolean =
    this.bindIsolatedService(服务, 标志, 实例名, 执行者, 连接)

//============================================================================

/**
 * 绑定服务为用户
 */
@RequiresApi(Build.VERSION_CODES.R)
fun Context.绑定服务为用户(@RequiresPermission 服务 : Intent, 连接: ServiceConnection, 标志: Int, 用户: UserHandle): Boolean =
    this.bindServiceAsUser(服务, 连接, 标志, 用户)

/**
 * 绑定服务为用户
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.绑定服务为用户(@RequiresPermission 服务 : Intent, 连接: ServiceConnection, 标志: Context.BindServiceFlags, 用户: UserHandle): Boolean =
    this.bindServiceAsUser(服务, 连接, 标志, 用户)

//============================================================================

/**
 * 更新服务组
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun Context.更新服务组(连接: ServiceConnection, 组: Int, 重要性: Int) =
    this.updateServiceGroup(连接, 组, 重要性)

//============================================================================

/**
 * 解绑服务
 */
fun Context.解绑服务(连接: ServiceConnection) = this.unbindService(连接)

//============================================================================

/**
 * 启动仪器化
 */
fun Context.启动仪器化(类名: ComponentName, 配置文件: String?, 参数: Bundle?): Boolean =
    this.startInstrumentation(类名, 配置文件, 参数)

//============================================================================

/**
 * 获取系统服务
 */
fun Context.取系统服务(名: String): Any = this.getSystemService(名)

/**
 * 获取系统服务
 */
fun <T> Context.取系统服务(服务类: Class<T>): T = this.getSystemService(服务类)

//============================================================================

/**
 * 取系统服务名
 */
fun Context.取系统服务名(服务类: Class<*>): String? = this.getSystemServiceName(服务类)

//============================================================================

/**
 * 检查权限
 */
fun Context.检查权限(权限: String, pid: Int , uid: Int): Int = this.checkPermission(权限, pid, uid)

//============================================================================

/**
 * 检查调用权限
 */
fun Context.检查调用权限(权限: String): Int = this.checkCallingPermission(权限)

//============================================================================

/**
 * 检查调用或自身权限
 */
fun Context.检查调用或自身权限(权限: String): Int = this.checkCallingOrSelfPermission(权限)

//============================================================================

/**
 * 检查自身权限
 */
fun Context.检查自身权限(权限: String): Int = this.checkSelfPermission(权限)

//============================================================================

/**
 * 执行权限
 */
fun Context.执行权限(权限: String, pid: Int , uid: Int, 信息: String) =
    this.enforcePermission(权限, pid, uid, 信息)

//============================================================================

/**
 * 强制执行调用权限
 */
fun Context.强制执行调用权限(权限: String, 信息: String) =
    this.enforceCallingPermission(权限, 信息)

//============================================================================

/**
 * 执行调用或自身权限
 */
fun Context.执行调用或自身权限(权限: String, 信息: String) =
    this.enforceCallingOrSelfPermission(权限, 信息)

//============================================================================

/**
 * 授予URI权限
 */
fun Context.授予URI权限(权限: String, uri: Uri, 模式标志: Int) =
    this.grantUriPermission(权限, uri,模式标志)

//============================================================================

/**
 * 撤销URI权限
 */
fun Context.撤销URI权限(uri: Uri, 模式标志: Int) = this.revokeUriPermission(uri,模式标志)

/**
 * 撤销URI权限
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Context.撤销URI权限(到包: String, uri: Uri, 模式标志: Int) =
    this.revokeUriPermission(到包,uri,模式标志)

//============================================================================

/**
 * 检查Uri权限
 */
fun Context.检查Uri权限(uri: Uri, pid: Int , uid: Int, 模式标志: Int) =
    this.checkUriPermission(uri, pid, uid, 模式标志)

//============================================================================

/**
 * 检查内容URI权限完整
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Context.检查内容URI权限完整(uri: Uri, pid: Int, uid: Int, 模式标志: Int) =
    this.checkContentUriPermissionFull(uri, pid, uid, 模式标志)

//============================================================================

/**
 * 检查Uri权限组
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun Context.检查Uri权限组(uris: List<Uri>, pid: Int, uid: Int, 模式标志: Int): IntArray =
    this.checkUriPermissions(uris, pid, uid, 模式标志)

//============================================================================

/**
 * 检查调用URI权限
 */
fun Context.检查调用URI权限(uri: Uri, 模式标志: Int): Int = this.checkCallingUriPermission(uri, 模式标志)

//============================================================================

/**
 * 检查调用URI权限组
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.检查调用URI权限组(uris: List<Uri>, 模式标志: Int): IntArray =
    this.checkCallingUriPermissions(uris, 模式标志)

//============================================================================

/**
 * 检查调用或自身URI权限
 */
fun Context.检查调用或自身URI权限(uri: Uri, 模式标志: Int): Int =
    this.checkCallingOrSelfUriPermission(uri, 模式标志)

//============================================================================

/**
 * 检查调用或自身URI权限组
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.检查调用或自身URI权限组(uris: List<Uri>, 模式标志: Int): IntArray =
    this.checkCallingOrSelfUriPermissions(uris, 模式标志)

//============================================================================

/**
 * 检查Uri权限
 */
fun Context.检查Uri权限(uri: Uri,读取权限 : String, 写入权限 : String, pid: Int, uid: Int, 模式标志: Int): Int =
    this.checkUriPermission(uri,读取权限,写入权限, pid, uid, 模式标志)

//============================================================================

/**
 * 强制URI权限
 */
fun Context.强制URI权限(uri: Uri, pid: Int, uid: Int, 模式标志: Int, 信息: String) =
    this.enforceUriPermission(uri, pid, uid, 模式标志, 信息)

//============================================================================

/**
 * 强制调用URI权限
 */
fun Context.强制调用URI权限(uri: Uri, 模式标志: Int, 信息: String) =
    this.enforceCallingUriPermission(uri,  模式标志, 信息)

//============================================================================

/**
 * 执行调用或自身URI权限
 */
fun Context.执行调用或自身URI权限(uri: Uri, 模式标志: Int, 信息: String) =
    this.enforceCallingOrSelfUriPermission(uri,  模式标志, 信息)

//============================================================================

/**
 * 强制URI权限
 */
fun Context.强制URI权限(uri: Uri,读取权限 : String, 写入权限 : String, pid: Int, uid: Int, 模式标志: Int, 信息: String) =
    this.enforceUriPermission(uri,读取权限,写入权限, pid, uid, 模式标志, 信息)

//============================================================================

/**
 * 应用终止时撤销自身权限
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun Context.应用终止时撤销自身权限(权限名: String) = this.revokeSelfPermissionOnKill(权限名)

//============================================================================

/**
 * 应用终止时撤销自身权限组
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun Context.应用终止时撤销自身权限组(权限组: Collection<String>) = this.revokeSelfPermissionsOnKill(权限组)

//============================================================================

/**
 * 创建包上下文
 */
fun Context.创建包上下文(包名: String, 标志: Int): Context? = this.createPackageContext(包名, 标志)

//============================================================================

/**
 * 创建上下文分包
 */
@RequiresApi(Build.VERSION_CODES.O)
fun Context.创建上下文分包(分包名: String): Context? = this.createContextForSplit(分包名)

//============================================================================

/**
 * 创建配置上下文
 */
fun Context.创建配置上下文(覆盖配置: Configuration): Context? = this.createConfigurationContext(覆盖配置)

//============================================================================

/**
 * 创建显示上下文
 */
fun Context.创建显示上下文(显示: Display): Context? = this.createDisplayContext(显示)

//============================================================================

/**
 * 创建设备上下文
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.创建设备上下文(设备id: Int): Context = this.createDeviceContext(设备id)

//============================================================================

/**
 * 创建窗口上下文
 */
@RequiresApi(Build.VERSION_CODES.R)
fun Context.创建窗口上下文(类型: Int, 选项: Bundle): Context = this.createWindowContext(类型,选项)

/**
 * 创建窗口上下文
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.创建窗口上下文(显示: Display, 类型: Int, 选项: Bundle): Context = this.createWindowContext(显示,类型,选项)

//============================================================================

/**
 * 创建上下文
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.创建上下文(上下文参数: ContextParams): Context = this.createContext(上下文参数)

//============================================================================

/**
 * 创建属性上下文
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.创建属性上下文(属性标签: String): Context = this.createAttributionContext(属性标签)

//============================================================================

/**
 * 创建设备保护存储上下文
 */
fun Context.创建设备保护存储上下文(): Context = this.createDeviceProtectedStorageContext()

//============================================================================

/**
 * 获取显示
 */
val Context.显示: Display
    @RequiresApi(Build.VERSION_CODES.R)
    get() = this.display

/**
 * 获取显示
 */
@RequiresApi(Build.VERSION_CODES.R)
fun Context.取显示(): Display = this.getDisplay()

//============================================================================

/**
 * 获取设备id
 */
val Context.设备id: Int
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    get() = this.getDeviceId()

/**
 * 获取设备id
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.取设备id(): Int = this.getDeviceId()

//============================================================================

/**
 * 注册设备id改变监听器
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.注册设备id改变监听器(执行者: Executor, 监听器: IntConsumer) = this.registerDeviceIdChangeListener(执行者,监听器)

//============================================================================

/**
 * 非注册设备id改变监听器
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Context.非注册设备id改变监听器(监听器: IntConsumer) = this.unregisterDeviceIdChangeListener(监听器)

//============================================================================

/**
 * 是否受限
 */
val Context.是否受限 get() = this.isRestricted

/**
 * 是否受限
 */
fun Context.是否受限() = this.isRestricted()

//============================================================================

/**
 * 是否设备加密存储
 */
val Context.是否设备加密存储 get() = this.isDeviceProtectedStorage

/**
 * 是否设备加密存储
 */
fun Context.是否设备加密存储() = this.isDeviceProtectedStorage()

//============================================================================

/**
 * 是否界面上下文
 */
val Context.是否界面上下文
    @RequiresApi(Build.VERSION_CODES.S)
    get() = this.isUiContext

/**
 * 是否界面上下文
 */
@RequiresApi(Build.VERSION_CODES.S)
fun Context.是否界面上下文() = this.isUiContext()

//============================================================================

