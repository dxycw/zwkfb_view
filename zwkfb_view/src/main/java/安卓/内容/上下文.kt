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
import 安卓x.约束布局.组件.组
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.util.concurrent.Executor
import java.util.function.IntConsumer

/**
 * 描述：用于访问应用程序全局信息的接口。这是一个抽象类，其实现由 Android 系统提供。它允许访问应用程序特定的资源和类，
 * 以及执行应用程序级别的操作回调，例如启动 Activity、发送和接收广播等。
 */
abstract class 上下文 : Context {
    constructor() : super()

    companion object{
        /**
         * 默认设备 ID，即主设备（非虚拟设备）的 ID。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 设备_ID_默认 = DEVICE_ID_DEFAULT

        /**
         * 无效的设备ID。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 设备_ID_无效 = DEVICE_ID_INVALID

        /**
         * 文件创建模式：默认模式，创建的文件只能由调用应用（或所有共享相同用户 ID 的应用）访问。
         */
        const val 模式_私有 = MODE_PRIVATE

        /**
         * 文件创建模式：全局可读模式，创建的文件可以被所有应用读取。
         */
        @SuppressLint("WorldReadableFiles")
        const val 模式_全局_可读 = MODE_WORLD_READABLE

        /**
         * 文件创建模式：全局可读可写模式，创建的文件可以被所有应用读取和写入。
         */
        @SuppressLint("WorldReadableFiles")
        const val 模式_全局_可写 = MODE_WORLD_WRITEABLE

        /**
         * 文件创建模式：追加模式，创建的文件会追加内容。
         */
        const val 模式_追加 = MODE_APPEND

        /**
         * 文件创建模式：多进程模式，创建的文件可以被多个进程访问。
         */
        const val 模式_多_进程 = MODE_MULTI_PROCESS

        /**
         * 文件创建模式：启用写预览日志模式，创建的文件会在写入时启用写预览日志。
         */
        const val 模式_启用_写_预览_日志 = MODE_ENABLE_WRITE_AHEAD_LOGGING

        /**
         * 文件创建模式：无本地化排序规则模式，创建的文件将使用系统默认的排序规则。
         */
        const val 模式_无_本地化_排序规则 = MODE_NO_LOCALIZED_COLLATORS


        /**
         * 绑定服务标志：自动创建标志，如果服务没有运行，则自动创建服务。
         */
        const val 绑定_自动_创建 = BIND_AUTO_CREATE

        /**
         * 绑定服务标志：调试解除标志，如果服务正在调试，则解除调试。
         */
        const val 绑定_调试_解除 = BIND_DEBUG_UNBIND

        /**
         * 绑定服务标志：非前台标志，如果服务正在运行，则将其切换到前台。
         */
        const val 绑定_非_前台 = BIND_NOT_FOREGROUND

        /**
         * 绑定服务标志：上客户标志，如果服务正在运行，则将其切换到客户。
         */
        const val 绑定_上方_客户 = BIND_ABOVE_CLIENT

        /**
         * 绑定服务标志：允许内存溢出管理标志，如果服务正在运行，则将其切换到客户。
         */
        const val 绑定_允许_内存溢出_管理 = BIND_ALLOW_OOM_MANAGEMENT

        /**
         * 绑定服务标志：放弃优先级标志，如果服务正在运行，则将其切换到客户。
         */
        const val 绑定_放弃_优先 = BIND_WAIVE_PRIORITY

        /**
         * 绑定服务标志：重要标志，如果服务正在运行，则将其切换到客户。
         */
        const val 绑定_重要 = BIND_IMPORTANT

        /**
         * 绑定服务标志：调整与活动标志，如果服务正在运行，则将其切换到客户。
         */
        const val 绑定_调整_与_活动 = BIND_ADJUST_WITH_ACTIVITY

        /**
         * 绑定服务标志：非感知标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.Q)
        const val 绑定_非_可感知 = BIND_NOT_PERCEPTIBLE

        /**
         * 绑定服务标志：允许活动开始标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 绑定_允许_活动_开始 = BIND_ALLOW_ACTIVITY_STARTS

        /**
         * 绑定服务标志：包含功能标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.Q)
        const val 绑定_包含_功能 = BIND_INCLUDE_CAPABILITIES

        /**
         * 绑定服务标志：共享孤立进程标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 绑定_共享_孤立_过程 = BIND_SHARED_ISOLATED_PROCESS

        /**
         * 绑定服务标志：包裹孤立进程标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        const val 绑定_包裹_孤立_过程 = BIND_PACKAGE_ISOLATED_PROCESS

        /**
         * 绑定服务标志：外部服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 绑定_外部_服务 = BIND_EXTERNAL_SERVICE

        /**
         * 绑定服务标志：外部服务标志（长），如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 绑定_外部_服务_长 = BIND_EXTERNAL_SERVICE_LONG

        /**
         * 绑定服务标志：接收器可见至即时应用标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 接收器_可见_至_即时_应用 = RECEIVER_VISIBLE_TO_INSTANT_APPS

        /**
         * 绑定服务标志：接收器已导出标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        const val 接收器_已导出 = RECEIVER_EXPORTED

        /**
         * 绑定服务标志：接收器非已导出标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        const val 接收器_非_已导出 = RECEIVER_NOT_EXPORTED



        /**
         * 绑定服务标志：接收器已导出标志，如果服务正在运行，则将其切换到客户。
         */
        const val 电源_服务 = POWER_SERVICE

        /**
         * 绑定服务标志：窗口服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 窗口_服务 = WINDOW_SERVICE

        /**
         * 绑定服务标志：布局加载器服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 布局_加载器_服务 = LAYOUT_INFLATER_SERVICE

        /**
         * 绑定服务标志：账户服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 账户_服务 = ACCOUNT_SERVICE

        /**
         * 绑定服务标志：活动服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 活动_服务 = ACTIVITY_SERVICE

        /**
         * 绑定服务标志：闹钟服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 报警_服务 = ALARM_SERVICE

        /**
         * 绑定服务标志：通知服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 通知_服务 = NOTIFICATION_SERVICE

        /**
         * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 无障碍_服务 = ACCESSIBILITY_SERVICE

        /**
         * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 字幕_服务 = CAPTIONING_SERVICE

        /**
         * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 锁屏_服务 = KEYGUARD_SERVICE

        /**
         * 绑定服务标志：位置服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 位置_服务 = LOCATION_SERVICE

        /**
         * 绑定服务标志：搜索服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 搜索_服务 = SEARCH_SERVICE

        /**
         * 绑定服务标志：传感器服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 传感器_服务 = SENSOR_SERVICE

        /**
         * 绑定服务标志：密钥库服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        const val 密钥库_服务 = KEYSTORE_SERVICE

        /**
         * 绑定服务标志：存储服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 存储_服务 = STORAGE_SERVICE

        /**
         * 绑定服务标志：存储统计服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 存储_统计_服务 = STORAGE_STATS_SERVICE

        /**
         * 绑定服务标志：壁纸服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 壁纸_服务 = WALLPAPER_SERVICE

        /**
         * 绑定服务标志：振动器管理服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 振动器_管理_服务 = VIBRATOR_MANAGER_SERVICE

        /**
         * 绑定服务标志：振动器服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 振动器_服务 = VIBRATOR_SERVICE

        /**
         * 绑定服务标志：状态栏服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        const val 状态_栏_服务 = STATUS_BAR_SERVICE

        /**
         * 绑定服务标志：连接服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 连接_服务 = CONNECTIVITY_SERVICE

        /**
         * 绑定服务标志： tethering 服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        const val 共享网络_服务 = TETHERING_SERVICE

        /**
         * 绑定服务标志：IP安全服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.P)
        const val IP安全_服务 = IPSEC_SERVICE

        /**
         * 绑定服务标志：VPN管理服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.R)
        const val VPN_管理_服务 = VPN_MANAGEMENT_SERVICE

        /**
         * 绑定服务标志：连接性诊断服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.R)
        const val 连接性_诊断_服务 = CONNECTIVITY_DIAGNOSTICS_SERVICE

        /**
         * 绑定服务标志：网络统计服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 网络_统计_服务 = NETWORK_STATS_SERVICE

        /**
         * 绑定服务标志：WIFI服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val WIFI_服务 = WIFI_SERVICE

        /**
         * 绑定服务标志：WIFI点对点服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val WIFI_点对点_服务 = WIFI_P2P_SERVICE

        /**
         * 绑定服务标志：WIFI感知服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val WIFI_感知_服务 = WIFI_AWARE_SERVICE

        /**
         * 绑定服务标志：WIFI RTT定位服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.P)
        const val WIFI_RTT_定位_服务 = WIFI_RTT_RANGING_SERVICE

        /**
         * 绑定服务标志：网络服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 网络服务发现_服务 = NSD_SERVICE

        /**
         * 绑定服务标志：音频服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 音频_服务 = AUDIO_SERVICE

        /**
         * 绑定服务标志：指纹服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 指纹_服务 = FINGERPRINT_SERVICE

        /**
         * 绑定服务标志：生物识别服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.Q)
        const val 生物识别_服务 = BIOMETRIC_SERVICE

        /**
         * 绑定服务标志：媒体通信服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 媒体_通信_服务 = MEDIA_COMMUNICATION_SERVICE

        /**
         * 绑定服务标志：媒体路由服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 媒体_路由_服务 = MEDIA_ROUTER_SERVICE

        /**
         * 绑定服务标志：媒体会话服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 媒体_会话_服务 = MEDIA_SESSION_SERVICE

        /**
         * 绑定服务标志：电话服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 电话_服务 = TELEPHONY_SERVICE

        /**
         * 绑定服务标志：电话订阅服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 电话_订阅_服务 = TELEPHONY_SUBSCRIPTION_SERVICE

        /**
         * 绑定服务标志：电信服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 电信_服务 = TELECOM_SERVICE

        /**
         * 绑定服务标志：运营商配置服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 运营商_配置_服务 = CARRIER_CONFIG_SERVICE

        /**
         * 绑定服务标志：EUICC服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.P)
        const val EUICC_服务 = EUICC_SERVICE

        /**
         * 绑定服务标志：剪贴板服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 剪贴板_服务 = CLIPBOARD_SERVICE

        /**
         * 绑定服务标志：文本分类服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 文本_分类_服务 = TEXT_CLASSIFICATION_SERVICE

        /**
         * 绑定服务标志：输入法服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 输入法_服务 = INPUT_METHOD_SERVICE

        /**
         * 绑定服务标志：文本服务管理器服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 文本_服务_管理器_服务 = TEXT_SERVICES_MANAGER_SERVICE

        /**
         * 绑定服务标志：应用组件服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 应用组件_服务 = APPWIDGET_SERVICE

        /**
         * 绑定服务标志：日志箱服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 日志箱_服务 = DROPBOX_SERVICE


        /**
         * 绑定服务标志：设备策略服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 设备_策略_服务 = DEVICE_POLICY_SERVICE

        /**
         * 绑定服务标志：用户模式服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val UI_模式_服务 = UI_MODE_SERVICE

        /**
         * 绑定服务标志：下载服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 下载_服务 = DOWNLOAD_SERVICE

        /**
         * 绑定服务标志：电池服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 电池_服务 = BATTERY_SERVICE

        /**
         * 绑定服务标志：NFC服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val NFC_服务 = NFC_SERVICE

        /**
         * 绑定服务标志：蓝牙服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 蓝牙_服务 = BLUETOOTH_SERVICE

        /**
         * 绑定服务标志：USB服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val USB_服务 = USB_SERVICE

        /**
         * 绑定服务标志：输入服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 输入_服务 = INPUT_SERVICE

        /**
         * 绑定服务标志：显示服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 显示_服务 = DISPLAY_SERVICE

        /**
         * 绑定服务标志：用户服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 用户_服务 = USER_SERVICE

        /**
         * 绑定服务标志：启动器应用服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 启动器_应用_服务  = LAUNCHER_APPS_SERVICE

        /**
         * 绑定服务标志：限制服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 限制_服务 = RESTRICTIONS_SERVICE

        /**
         * 绑定服务标志：应用操作服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 应用_操作_服务 = APP_OPS_SERVICE

        /**
         * 绑定服务标志：角色服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.Q)
        const val 角色_服务 = ROLE_SERVICE

        /**
         * 绑定服务标志：相机服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 相机_服务 = CAMERA_SERVICE

        /**
         * 绑定服务标志：打印服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 打印_服务 = PRINT_SERVICE

        /**
         * 绑定服务标志：配套设备服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 配套_设备_服务 = COMPANION_DEVICE_SERVICE

        /**
         * 绑定服务标志：虚拟设备服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 虚拟_设备_服务 = VIRTUAL_DEVICE_SERVICE

        /**
         * 绑定服务标志：消费者红外服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 消费者_信息_服务 = CONSUMER_IR_SERVICE

        /**
         * 绑定服务标志：电视互动应用服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        const val 电视_互动_应用_服务 = TV_INTERACTIVE_APP_SERVICE

        /**
         * 绑定服务标志：电视输入服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 电视_输入_服务 = TV_INPUT_SERVICE

        /**
         * 绑定服务标志：电视广告服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        const val 电视_广告_服务 = TV_AD_SERVICE

        /**
         * 绑定服务标志：使用统计服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 使用_统计_服务 = USAGE_STATS_SERVICE

        /**
         * 绑定服务标志：作业调度服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 作业_调度_服务 = JOB_SCHEDULER_SERVICE

        /**
         * 绑定服务标志：持久数据块服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        const val 持久_数据_块_服务 = PERSISTENT_DATA_BLOCK_SERVICE

        /**
         * 绑定服务标志：媒体投影服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 媒体_投影_服务 = MEDIA_PROJECTION_SERVICE

        /**
         * 绑定服务标志：MIDI服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val MIDI_服务 = MIDI_SERVICE

        /**
         * 绑定服务标志：硬件属性服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 硬件_属性_服务 = HARDWARE_PROPERTIES_SERVICE

        /**
         * 绑定服务标志：性能提示服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 性能_提示_服务 = PERFORMANCE_HINT_SERVICE

        /**
         * 绑定服务标志：快捷方式服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.N_MR1)
        const val 快捷方式_服务 = SHORTCUT_SERVICE

        /**
         * 绑定服务标志：系统健康服务标志，如果服务正在运行，则将其切换到客户。
         */
        const val 系统_健康_服务 = SYSTEM_HEALTH_SERVICE

        /**
         * 绑定服务标志：错误报告服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 错误报告_服务 = BUGREPORT_SERVICE

        /**
         * 绑定服务标志：覆盖服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 覆盖_服务 = OVERLAY_SERVICE

        /**
         * 绑定服务标志：跨配置文件应用服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.P)
        const val 跨_配置文件_应用_服务 = CROSS_PROFILE_APPS_SERVICE

        /**
         * 绑定服务标志：电话 IMS 服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.R)
        const val 电话_IMS_服务 = TELEPHONY_IMS_SERVICE

        /**
         * 绑定服务标志：Blob 存储服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.R)
        const val BLOB_存储_服务 = BLOB_STORE_SERVICE

        /**
         * 绑定服务标志：应用搜索服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 应用_搜索_服务 = APP_SEARCH_SERVICE

        /**
         * 绑定服务标志：应用功能服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        const val 应用_功能_服务 = APP_FUNCTION_SERVICE

        /**
         * 绑定服务标志：高级保护服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        const val 高级_保护_服务 = ADVANCED_PROTECTION_SERVICE

        /**
         * 绑定服务标志：文件完整性服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.R)
        const val 文件_完整性_服务 = FILE_INTEGRITY_SERVICE

        /**
         * 绑定服务标志：人员服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 人员_服务 = PEOPLE_SERVICE

        /**
         * 绑定服务标志：媒体指标服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 媒体_指标_服务 = MEDIA_METRICS_SERVICE

        /**
         * 绑定服务标志：游戏服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 游戏_服务 = GAME_SERVICE

        /**
         * 绑定服务标志：域名验证服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 域名_验证_服务 = DOMAIN_VERIFICATION_SERVICE

        /**
         * 绑定服务标志：显示哈希服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.S)
        const val 显示_哈希_服务 = DISPLAY_HASH_SERVICE

        /**
         * 绑定服务标志：本地服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        const val 本地_服务 = LOCALE_SERVICE

        /**
         * 绑定服务标志：健康连接服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 健康连接_服务 = HEALTHCONNECT_SERVICE

        /**
         * 绑定服务标志：凭证服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 凭证_服务 = CREDENTIAL_SERVICE

        /**
         * 绑定服务标志：设备锁服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 设备_锁_服务 = DEVICE_LOCK_SERVICE

        /**
         * 绑定服务标志：语法变位服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 语法_变位_服务 = GRAMMATICAL_INFLECTION_SERVICE

        /**
         * 绑定服务标志：卫星服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        const val 卫星_服务 = SATELLITE_SERVICE

        /**
         * 绑定服务标志：安全状态服务标志，如果服务正在运行，则将其切换到客户
         */
        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        const val 安全_状态_服务 = SECURITY_STATE_SERVICE

        /**
         * 绑定服务标志：联系人密钥服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        const val 联系人_密钥_服务 = CONTACT_KEYS_SERVICE

        /**
         * 绑定服务标志：分析服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
        const val 分析_服务 = PROFILING_SERVICE

        /**
         * 绑定服务标志：媒体质量服务标志，如果服务正在运行，则将其切换到客户。
         */
        @RequiresApi(Build.VERSION_CODES.BAKLAVA)
        const val 媒体_质量_服务 = MEDIA_QUALITY_SERVICE



        /**
         * 绑定服务标志：包含代码标志，如果服务正在运行，则将其切换
         */
        const val 上下文_包含_代码 = CONTEXT_INCLUDE_CODE

        /**
         * 绑定服务标志：忽略安全标志，如果服务正在运行，则将其切换到客户。
         */
        const val 上下文_忽略_安全 = CONTEXT_IGNORE_SECURITY

        /**
         * 绑定服务标志：受限标志，如果服务正在运行，则将其切换到客户。
         */
        const val 上下文_受限 = CONTEXT_RESTRICTED
    }

    object 绑定服务标志{
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        fun of(值: Long): BindServiceFlags = BindServiceFlags.of(值)
    }

}

//============================================================================

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
 * 打开文件输入
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


