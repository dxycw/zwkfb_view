package 安卓.内容;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;

import androidx.annotation.AnyRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Set;

/**
 * 创建时间：2025年11月23日.
 * <p>
 * 描述：意图
 * @author dxyc
 */
public class 意图 extends Intent {
    public 意图() {
        super();
    }
    public 意图(Intent 意图) {
        super(意图);
    }

    //=============================================================================================

    public 意图(String 动作) {
        super(动作);
    }

    public 意图(String 动作, Uri uri) {
        super(动作, uri);
    }

    public 意图(Context 包上下文, Class<?> 类) {
        super(包上下文, 类);
    }

    public 意图(String 动作, Uri uri, Context 包上下文, Class<?> 类) {
        super(动作, uri, 包上下文, 类);
    }

    //=============================================================================================

    public static final String 行动_主 = Intent.ACTION_MAIN;

    public static final String 行动_视图 = Intent.ACTION_VIEW;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final String 额外_来自_存储 = Intent.EXTRA_FROM_STORAGE;

    public static final String 行动_默认 = Intent.ACTION_DEFAULT;

    public static final String 行动_快速_视图 = Intent.ACTION_QUICK_VIEW;

    public static final String 行动_附加_数据 = Intent.ACTION_ATTACH_DATA;

    public static final String 行动_编辑 = Intent.ACTION_EDIT;

    public static final String 行动_插入_或_编辑 = Intent.ACTION_INSERT_OR_EDIT;

    public static final String 行动_选择 = Intent.ACTION_PICK;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final String 行动_创建_提醒 = Intent.ACTION_CREATE_REMINDER;

    public static final String 行动_创建_快捷方式 = Intent.ACTION_CREATE_SHORTCUT;

    public static final String 额外_快捷方式_意图 = Intent.EXTRA_SHORTCUT_INTENT;

    public static final String 额外_快捷方式_名 = Intent.EXTRA_SHORTCUT_NAME;

    public static final String 额外_快捷方式_图标 = Intent.EXTRA_SHORTCUT_ICON;

    public static final String 额外_快捷方式_图标_资源 = Intent.EXTRA_SHORTCUT_ICON_RESOURCE;

    public static final String 行动_申请_偏好 = Intent.ACTION_APPLICATION_PREFERENCES;

    public static final String 行动_显示_应用_信息 = Intent.ACTION_SHOW_APP_INFO;

    //=============================================================================================

    public static class 快捷方式图标资源 extends ShortcutIconResource {
        public String 包名 = this.packageName;
        public String 资源名 = this.resourceName;

        public static ShortcutIconResource 来自上下文(Context 上下文, @AnyRes int 资源Id) {
            return fromContext(上下文, 资源Id);
        }

        public static final Parcelable.Creator<ShortcutIconResource> 创造者 = ShortcutIconResource.CREATOR;

        public int 描述内容() {
            return this.describeContents();
        }

        public void 写入包裹(Parcel 目的, int 标志) {
            this.writeToParcel(目的, 标志);
        }

        @NonNull
        @Override
        public String toString() {
            return 资源名;
        }
    }

    //=============================================================================================

    public static final String 行动_选择器 = Intent.ACTION_CHOOSER;

    //=============================================================================================

    public static Intent 创建选择器(Intent 目标, CharSequence 标题) {
        return Intent.createChooser(目标, 标题);
    }

    public static Intent 创建选择器(Intent 目标, CharSequence 标题, IntentSender 发件人) {
        return Intent.createChooser(目标, 标题, 发件人);
    }

    //=============================================================================================

    public static final String 行动_取_内容 = Intent.ACTION_GET_CONTENT;

    public static final String 行动_拨号 = Intent.ACTION_DIAL;

    public static final String 行动_呼叫 = Intent.ACTION_CALL;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final String 行动_运营商_设置 = Intent.ACTION_CARRIER_SETUP;

    public static final String 行动_发送到 = Intent.ACTION_SENDTO;

    public static final String 行动_发送 = Intent.ACTION_SEND;

    public static final String 行动_发送_多个 = Intent.ACTION_SEND_MULTIPLE;

    public static final String 行动_回答 = Intent.ACTION_ANSWER;

    public static final String 行动_插入 = Intent.ACTION_INSERT;

    public static final String 行动_粘贴 = Intent.ACTION_PASTE;

    public static final String 行动_删除 = Intent.ACTION_DELETE;

    public static final String 行动_运行 = Intent.ACTION_RUN;

    public static final String 行动_同步 = Intent.ACTION_SYNC;

    public static final String 行动_选择_活动 = Intent.ACTION_PICK_ACTIVITY;

    public static final String 行动_搜索 = Intent.ACTION_SEARCH;

    public static final String 行动_系统_教程 = Intent.ACTION_SYSTEM_TUTORIAL;

    public static final String 行动_网络_搜索 = Intent.ACTION_WEB_SEARCH;

    public static final String 行动_协助 = Intent.ACTION_ASSIST;

    public static final String 额外_协助_包裹 = Intent.EXTRA_ASSIST_PACKAGE;

    public static final String 额外_协助_用户标识 = Intent.EXTRA_ASSIST_UID;

    public static final String 额外_协助_上下文 = Intent.EXTRA_ASSIST_CONTEXT;

    public static final String 额外_协助_输入_提示_键盘 = Intent.EXTRA_ASSIST_INPUT_HINT_KEYBOARD;

    public static final String 额外_协助_输入_设备_Id = Intent.EXTRA_ASSIST_INPUT_DEVICE_ID;

    public static final String 行动_所有_应用程序 = Intent.ACTION_ALL_APPS;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static final String 行动_显示_工作_应用程序 = Intent.ACTION_SHOW_WORK_APPS;

    public static final String 行动_置_壁纸 = Intent.ACTION_SET_WALLPAPER;

    public static final String 行动_错误_报告 = Intent.ACTION_BUG_REPORT;

    public static final String 行动_工厂_测试 = Intent.ACTION_FACTORY_TEST;

    public static final String 行动_呼叫_按钮 = Intent.ACTION_CALL_BUTTON;

    public static final String 行动_声音_命令 = Intent.ACTION_VOICE_COMMAND;

    @RequiresApi(api = Build.VERSION_CODES_FULL.BAKLAVA_1)
    public static final String 行动_停止_声音_命令 = Intent.ACTION_STOP_VOICE_COMMAND;

    public static final String 行动_搜索_长_按 = Intent.ACTION_SEARCH_LONG_PRESS;

    public static final String 行动_应用_错误 = Intent.ACTION_APP_ERROR;

    public static final String 行动_电源_使用_摘要 = Intent.ACTION_POWER_USAGE_SUMMARY;

    public static final String 行动_管理_网络_使用 = Intent.ACTION_MANAGE_NETWORK_USAGE;

    public static final String 行动_安装_包裹 = Intent.ACTION_INSTALL_PACKAGE;

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public static final String 行动_安装_失败 = Intent.ACTION_INSTALL_FAILURE;

    public static final String 额外_安装程序_包裹_名 = Intent.EXTRA_INSTALLER_PACKAGE_NAME;

    public static final String 额外_非_未知_源 = Intent.EXTRA_NOT_UNKNOWN_SOURCE;

    public static final String 额外_起源_URI = Intent.EXTRA_ORIGINATING_URI;

    public static final String 额外_推荐人 = Intent.EXTRA_REFERRER;

    public static final String 额外_推荐人_名 = Intent.EXTRA_REFERRER_NAME;

    public static final String 额外_允许_替换 = Intent.EXTRA_ALLOW_REPLACE;

    public static final String 额外_返回_结果 = Intent.EXTRA_RETURN_RESULT;

    public static final String 行动_卸载_包裹 = Intent.ACTION_UNINSTALL_PACKAGE;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final String 行动_自动_撤销_权限 = Intent.ACTION_AUTO_REVOKE_PERMISSIONS;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 行动_管理_未使用_应用程序 = Intent.ACTION_MANAGE_UNUSED_APPS;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 行动_视图_许可_使用 = Intent.ACTION_VIEW_PERMISSION_USAGE;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 行动_视图_许可_使用_为了_期间 = Intent.ACTION_VIEW_PERMISSION_USAGE_FOR_PERIOD;

    public static final String 额外_包裹_名 = Intent.EXTRA_PACKAGE_NAME;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static final String 额外_语言环境_列表 = Intent.EXTRA_LOCALE_LIST;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static final String 额外_暂停_包裹_附加内容 = Intent.EXTRA_SUSPENDED_PACKAGE_EXTRAS;

    @RequiresApi(api = Build.VERSION_CODES.O_MR1)
    public static final String 额外_分开_名 = Intent.EXTRA_SPLIT_NAME;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final String 额外_组件_名 = Intent.EXTRA_COMPONENT_NAME;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 额外_快捷方式_Id = Intent.EXTRA_SHORTCUT_ID;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 额外_许可_组_名 = Intent.EXTRA_PERMISSION_GROUP_NAME;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 额外_持续时间_毫秒 = Intent.EXTRA_DURATION_MILLIS;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static final String 行动_安全_中心 = Intent.ACTION_SAFETY_CENTER;

    public static final String 行动_屏幕_关 = Intent.ACTION_SCREEN_OFF;

    public static final String 行动_屏幕_开 = Intent.ACTION_SCREEN_ON;

    public static final String 行动_空闲状态_已停止 = Intent.ACTION_DREAMING_STOPPED;

    public static final String 行动_空闲状态_已开始 = Intent.ACTION_DREAMING_STARTED;

    public static final String 行动_用户_当前 = Intent.ACTION_USER_PRESENT;

    public static final String 行动_时间_周期 = Intent.ACTION_TIME_TICK;

    public static final String 行动_时间_已改变 = Intent.ACTION_TIME_CHANGED;

    public static final String 行动_日期_已改变 = Intent.ACTION_DATE_CHANGED;

    public static final String 行动_时区_已改变 = Intent.ACTION_TIMEZONE_CHANGED;

    public static final String 行动_已锁定_引导_已完成 = Intent.ACTION_LOCKED_BOOT_COMPLETED;

    public static final String 行动_引导_已完成 = Intent.ACTION_BOOT_COMPLETED;

    public static final String 行动_关闭_系统_对话框 = Intent.ACTION_CLOSE_SYSTEM_DIALOGS;

    public static final String 行动_包裹_安装 = Intent.ACTION_PACKAGE_INSTALL;

    public static final String 行动_包裹_已添加 = Intent.ACTION_PACKAGE_ADDED;

    public static final String 行动_包裹_已替换 = Intent.ACTION_PACKAGE_REPLACED;

    public static final String 行动_我的_包裹_已替换 = Intent.ACTION_MY_PACKAGE_REPLACED;

    public static final String 行动_包裹_已删除 = Intent.ACTION_PACKAGE_REMOVED;

    public static final String 行动_包裹_完全_已删除 = Intent.ACTION_PACKAGE_FULLY_REMOVED;

    public static final String 行动_包裹_已改变 = Intent.ACTION_PACKAGE_CHANGED;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 行动_包裹_不断 = Intent.ACTION_PACKAGE_UNSTOPPED;

    public static final String 行动_包裹_已重新启动 = Intent.ACTION_PACKAGE_RESTARTED;

    public static final String 行动_包裹_数据_已清除 = Intent.ACTION_PACKAGE_DATA_CLEARED;

    public static final String 行动_包裹_暂停 = Intent.ACTION_PACKAGES_SUSPENDED;

    public static final String 行动_包裹_恢复 = Intent.ACTION_PACKAGES_UNSUSPENDED;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static final String 行动_我的_包裹_暂停 = Intent.ACTION_MY_PACKAGE_SUSPENDED;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static final String 行动_我的_包裹_恢复 = Intent.ACTION_MY_PACKAGE_UNSUSPENDED;

    public static final String 行动_用户Id_已移除 = Intent.ACTION_UID_REMOVED;

    public static final String 行动_包裹_第一_启动 = Intent.ACTION_PACKAGE_FIRST_LAUNCH;

    public static final String 行动_包裹_需要_验证 = Intent.ACTION_PACKAGE_NEEDS_VERIFICATION;

    public static final String 行动_包裹_已验证 = Intent.ACTION_PACKAGE_VERIFIED;

    public static final String 行动_外部_应用程序_可用 = Intent.ACTION_EXTERNAL_APPLICATIONS_AVAILABLE;

    public static final String 行动_外部_应用程序_不可用 = Intent.ACTION_EXTERNAL_APPLICATIONS_UNAVAILABLE;

    public static final String 行动_壁纸_已改变 = Intent.ACTION_WALLPAPER_CHANGED;

    public static final String 行动_配置_已改变 = Intent.ACTION_CONFIGURATION_CHANGED;

    public static final String 行动_语言环境_已改变 = Intent.ACTION_LOCALE_CHANGED;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static final String 行动_应用程序_语言环境_已改变 = Intent.ACTION_APPLICATION_LOCALE_CHANGED;

    public static final String 行动_电池_已改变 = Intent.ACTION_BATTERY_CHANGED;

    public static final String 行动_电池_低 = Intent.ACTION_BATTERY_LOW;

    public static final String 行动_电池_正常 = Intent.ACTION_BATTERY_OKAY;

    public static final String 行动_电源_已连接 = Intent.ACTION_POWER_CONNECTED;

    public static final String 行动_电源_已断开 = Intent.ACTION_POWER_DISCONNECTED;

    public static final String 行动_关闭 = Intent.ACTION_SHUTDOWN;

    public static final String 行动_设备_存储_低 = Intent.ACTION_DEVICE_STORAGE_LOW;

    public static final String 行动_设备_存储_好的 = Intent.ACTION_DEVICE_STORAGE_OK;

    public static final String 行动_管理_包裹_存储 = Intent.ACTION_MANAGE_PACKAGE_STORAGE;

    public static final String 行动_ums_已连接 = Intent.ACTION_UMS_CONNECTED;

    public static final String 行动_ums_已断开 = Intent.ACTION_UMS_DISCONNECTED;

    public static final String 行动_媒体_已移除 = Intent.ACTION_MEDIA_REMOVED;

    public static final String 行动_媒体_未安装 = Intent.ACTION_MEDIA_UNMOUNTED;

    public static final String 行动_媒体_检查 = Intent.ACTION_MEDIA_CHECKING;

    public static final String 行动_媒体_无文件系统 = Intent.ACTION_MEDIA_NOFS;

    public static final String 行动_媒体_已安装 = Intent.ACTION_MEDIA_MOUNTED;

    public static final String 行动_媒体_已共享 = Intent.ACTION_MEDIA_SHARED;

    public static final String 行动_媒体_不良_移除 = Intent.ACTION_MEDIA_BAD_REMOVAL;

    public static final String 行动_媒体_不可卸载 = Intent.ACTION_MEDIA_UNMOUNTABLE;

    public static final String 行动_媒体_弹出 = Intent.ACTION_MEDIA_EJECT;

    public static final String 行动_媒体_扫描仪_已开始 = Intent.ACTION_MEDIA_SCANNER_STARTED;

    public static final String 行动_媒体_扫描仪_已完成 = Intent.ACTION_MEDIA_SCANNER_FINISHED;

    public static final String 行动_媒体_扫描仪_扫描_文件 = Intent.ACTION_MEDIA_SCANNER_SCAN_FILE;

    public static final String 行动_媒体_按钮 = Intent.ACTION_MEDIA_BUTTON;

    public static final String 行动_相机_按钮 = Intent.ACTION_CAMERA_BUTTON;

    public static final String 行动_谷歌通话_服务_已连接 = Intent.ACTION_GTALK_SERVICE_CONNECTED;

    public static final String 行动_谷歌通话_服务_已断开 = Intent.ACTION_GTALK_SERVICE_DISCONNECTED;

    public static final String 行动_输入_方法_已改变 = Intent.ACTION_INPUT_METHOD_CHANGED;

    public static final String 行动_飞行模式_模式_已改变 = Intent.ACTION_AIRPLANE_MODE_CHANGED;

    public static final String 行动_提供者_已改变 = Intent.ACTION_PROVIDER_CHANGED;

    public static final String 行动_耳机_插头 = Intent.ACTION_HEADSET_PLUG;

    public static final String 行动_申请_限制_已改变 = Intent.ACTION_APPLICATION_RESTRICTIONS_CHANGED;

    public static final String 行动_新_外拨_呼叫 = Intent.ACTION_NEW_OUTGOING_CALL;

    public static final String 行动_重启 = Intent.ACTION_REBOOT;

    public static final String 行动_停靠_事件 = Intent.ACTION_DOCK_EVENT;

    public static final String 行动_取_限制_记录 = Intent.ACTION_GET_RESTRICTION_ENTRIES;

    public static final String 行动_用户_初始化 = Intent.ACTION_USER_INITIALIZE;

    public static final String 行动_用户_前景 = Intent.ACTION_USER_FOREGROUND;

    public static final String 行动_用户_背景 = Intent.ACTION_USER_BACKGROUND;

    public static final String 行动_用户_已解锁 = Intent.ACTION_USER_UNLOCKED;

    public static final String 行动_已管理_个人资料_已添加 = Intent.ACTION_MANAGED_PROFILE_ADDED;

    public static final String 行动_已管理_个人资料_已移除 = Intent.ACTION_MANAGED_PROFILE_REMOVED;

    public static final String 行动_已管理_个人资料_已解锁 = Intent.ACTION_MANAGED_PROFILE_UNLOCKED;

    public static final String 行动_已管理_个人资料_已可用 = Intent.ACTION_MANAGED_PROFILE_AVAILABLE;

    public static final String 行动_已管理_个人资料_已不可用 = Intent.ACTION_MANAGED_PROFILE_UNAVAILABLE;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 行动_个人资料_已可用 = Intent.ACTION_PROFILE_AVAILABLE;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 行动_个人资料_已不可用 = Intent.ACTION_PROFILE_UNAVAILABLE;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 行动_个人资料_可访问 = Intent.ACTION_PROFILE_ACCESSIBLE;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 行动_个人资料_不可访问 = Intent.ACTION_PROFILE_INACCESSIBLE;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 行动_个人资料_已移除 = Intent.ACTION_PROFILE_REMOVED;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 行动_个人资料_已添加 = Intent.ACTION_PROFILE_ADDED;

    public static final String 行动_快速_时钟 = Intent.ACTION_QUICK_CLOCK;

    public static final String 行动_打开_文件 = Intent.ACTION_OPEN_DOCUMENT;

    public static final String 行动_创建_文件 = Intent.ACTION_CREATE_DOCUMENT;

    public static final String 行动_打开_文件_树 = Intent.ACTION_OPEN_DOCUMENT_TREE;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 行动_翻译 = Intent.ACTION_TRANSLATE;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 行动_定义 = Intent.ACTION_DEFINE;


    public static final String 行动_过程_文本 = Intent.ACTION_PROCESS_TEXT;

    public static final String 额外_过程_文本 = Intent.EXTRA_PROCESS_TEXT;

    public static final String 额外_过程_文本_只读 = Intent.EXTRA_PROCESS_TEXT_READONLY;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 行动_视图_轨迹 = Intent.ACTION_VIEW_LOCUS;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 行动_创建_注意 = Intent.ACTION_CREATE_NOTE;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 额外_使用_触控笔_模式 = Intent.EXTRA_USE_STYLUS_MODE;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 行动_启动_捕获_内容_活动_为了_注意 = Intent.ACTION_LAUNCH_CAPTURE_CONTENT_ACTIVITY_FOR_NOTE;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 额外_捕获_内容_为了_注意_状态_代码 = Intent.EXTRA_CAPTURE_CONTENT_FOR_NOTE_STATUS_CODE;

    //============================================================================================

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 捕获_内容_为了_注意_成功 = Intent.CAPTURE_CONTENT_FOR_NOTE_SUCCESS;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 捕获_内容_为了_注意_失败 = Intent.CAPTURE_CONTENT_FOR_NOTE_FAILED;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 捕获_内容_为了_注意_用户_已取消 = Intent.CAPTURE_CONTENT_FOR_NOTE_USER_CANCELED;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 捕获_内容_为了_注意_窗口_模式_不支持 = Intent.CAPTURE_CONTENT_FOR_NOTE_WINDOW_MODE_UNSUPPORTED;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final int 捕获_内容_为了_注意_被阻止_由_管理员 = Intent.CAPTURE_CONTENT_FOR_NOTE_BLOCKED_BY_ADMIN;

    //============================================================================================

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 行动_取消存档_包裹 = Intent.ACTION_UNARCHIVE_PACKAGE;

    public static final String 类别_默认 = Intent.CATEGORY_DEFAULT;

    public static final String 类别_可浏览 = Intent.CATEGORY_BROWSABLE;

    public static final String 类别_声音 = Intent.CATEGORY_VOICE;

    public static final String 类别_替代 = Intent.CATEGORY_ALTERNATIVE;

    public static final String 类别_已选择_替代 = Intent.CATEGORY_SELECTED_ALTERNATIVE;

    public static final String 类别_制表符 = Intent.CATEGORY_TAB;

    public static final String 类别_启动器 = Intent.CATEGORY_LAUNCHER;

    public static final String 类别_后仰_启动器 = Intent.CATEGORY_LEANBACK_LAUNCHER;

    public static final String 类别_信息 = Intent.CATEGORY_INFO;

    public static final String 类别_家 = Intent.CATEGORY_HOME;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 类别_二级_家 = Intent.CATEGORY_SECONDARY_HOME;

    public static final String 类别_偏好 = Intent.CATEGORY_PREFERENCE;

    public static final String 类别_开发_偏好 = Intent.CATEGORY_DEVELOPMENT_PREFERENCE;

    public static final String 类别_嵌入 = Intent.CATEGORY_EMBED;

    public static final String 类别_应用_市场 = Intent.CATEGORY_APP_MARKET;

    public static final String 类别_猴子 = Intent.CATEGORY_MONKEY;

    public static final String 类别_测试 = Intent.CATEGORY_TEST;

    public static final String 类别_单位_测试 = Intent.CATEGORY_UNIT_TEST;

    public static final String 类别_示例_代码 = Intent.CATEGORY_SAMPLE_CODE;

    public static final String 类别_可打开 = Intent.CATEGORY_OPENABLE;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final String 类别_类型化_可打开 = Intent.CATEGORY_TYPED_OPENABLE;

    public static final String 类别_框架_插桩_测试 = Intent.CATEGORY_FRAMEWORK_INSTRUMENTATION_TEST;

    public static final String 类别_汽车_停靠 = Intent.CATEGORY_CAR_DOCK;

    public static final String 类别_桌面_停靠 = Intent.CATEGORY_DESK_DOCK;

    public static final String 类别_左下_桌面_停靠 = Intent.CATEGORY_LE_DESK_DOCK;

    public static final String 类别_右上_桌面_停靠 = Intent.CATEGORY_HE_DESK_DOCK;

    public static final String 类别_汽车_模式 = Intent.CATEGORY_CAR_MODE;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final String 类别_VR_模式  = Intent.CATEGORY_VR_HOME;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final String 类别_无障碍_快捷方式_目标 = Intent.CATEGORY_ACCESSIBILITY_SHORTCUT_TARGET;

    public static final String 类别_应用_浏览器 = Intent.CATEGORY_APP_BROWSER;

    public static final String 类别_应用_计算器 = Intent.CATEGORY_APP_CALCULATOR;

    public static final String 类别_应用_日历 = Intent.CATEGORY_APP_CALENDAR;

    public static final String 类别_应用_联系人 = Intent.CATEGORY_APP_CONTACTS;

    public static final String 类别_应用_电子邮件 = Intent.CATEGORY_APP_EMAIL;

    public static final String 类别_应用_图库 = Intent.CATEGORY_APP_GALLERY;

    public static final String 类别_应用_地图 = Intent.CATEGORY_APP_MAPS;

    public static final String 类别_应用_消息 = Intent.CATEGORY_APP_MESSAGING;

    public static final String 类别_应用_音乐 = Intent.CATEGORY_APP_MUSIC;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 类别_应用_文件 = Intent.CATEGORY_APP_FILES;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static final String 类别_应用_天气 = Intent.CATEGORY_APP_WEATHER;

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public static final String 类别_应用_健身 = Intent.CATEGORY_APP_FITNESS;

    public static final String 额外_模板 = Intent.EXTRA_TEMPLATE;

    public static final String 额外_文本 = Intent.EXTRA_TEXT;

    public static final String 额外_HTML_文本 = Intent.EXTRA_HTML_TEXT;

    public static final String 额外_流 = Intent.EXTRA_STREAM;

    public static final String 额外_电子邮件 = Intent.EXTRA_EMAIL;

    public static final String 额外_抄送 = Intent.EXTRA_CC;

    public static final String 额外_密送 = Intent.EXTRA_BCC;

    public static final String 额外_主题 = Intent.EXTRA_SUBJECT;

    public static final String 额外_意图 = Intent.EXTRA_INTENT;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 额外_归属_标签 = Intent.EXTRA_ATTRIBUTION_TAGS;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 额外_开始_时间 = Intent.EXTRA_START_TIME;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 额外_结束_时间 = Intent.EXTRA_END_TIME;

    public static final String 额外_替代_意图 = Intent.EXTRA_ALTERNATE_INTENTS;

    public static final String 额外_排除_组件 = Intent.EXTRA_EXCLUDE_COMPONENTS;

    public static final String 额外_选择器_目标 = Intent.EXTRA_CHOOSER_TARGETS;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 额外_选择器_精炼_意图_发送者 = Intent.EXTRA_CHOOSER_REFINEMENT_INTENT_SENDER;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 额外_选择器_自定义_操作 = Intent.EXTRA_CHOOSER_CUSTOM_ACTIONS;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 额外_选择器_修改_分享_操作 = Intent.EXTRA_CHOOSER_MODIFY_SHARE_ACTION;


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_选择器_内容_类型_提示 = Intent.EXTRA_CHOOSER_CONTENT_TYPE_HINT;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final int 选择器_内容_类型_专辑 = Intent.CHOOSER_CONTENT_TYPE_ALBUM;


    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_选择器_额外_内容_URI = Intent.EXTRA_CHOOSER_ADDITIONAL_CONTENT_URI;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_选择器_聚焦_项目_位置 = Intent.EXTRA_CHOOSER_FOCUSED_ITEM_POSITION;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_内容_注释 = Intent.EXTRA_CONTENT_ANNOTATIONS;

    public static final String 额外_结果_接收器 = Intent.EXTRA_RESULT_RECEIVER;

    public static final String 额外_标题 = Intent.EXTRA_TITLE;

    public static final String 额外_初始_意图 = Intent.EXTRA_INITIAL_INTENTS;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_元数据_文本 = Intent.EXTRA_METADATA_TEXT;

    public static final String 额外_替换_附加内容 = Intent.EXTRA_REPLACEMENT_EXTRAS;

    public static final String 额外_选择器_组件_意图_发送者 = Intent.EXTRA_CHOSEN_COMPONENT_INTENT_SENDER;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_选择器_结果_意图_发送者 = Intent.EXTRA_CHOOSER_RESULT_INTENT_SENDER;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_选择器_结果 = Intent.EXTRA_CHOOSER_RESULT;

    public static final String 额外_选择器_组件 = Intent.EXTRA_CHOSEN_COMPONENT;

    public static final String 额外_键_事件 = Intent.EXTRA_KEY_EVENT;

    public static final String 额外_不要_结束_应用 = Intent.EXTRA_DONT_KILL_APP;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public static final String 额外_用户_已启动 = Intent.EXTRA_USER_INITIATED;

    public static final String 额外_电话_数字 = Intent.EXTRA_PHONE_NUMBER;

    public static final String 额外_UID = Intent.EXTRA_UID;

    @RequiresApi(api = Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    public static final String 额外_包裹 = Intent.EXTRA_PACKAGES;

    public static final String 额外_数据_已删除 = Intent.EXTRA_DATA_REMOVED;

    public static final String 额外_替换 = Intent.EXTRA_REPLACING;

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public static final String 额外_档案 = Intent.EXTRA_ARCHIVAL;

    public static final String 额外_警报_计算 = Intent.EXTRA_ALARM_COUNT;

    public static final String 额外_停靠_状态 = Intent.EXTRA_DOCK_STATE;

    public static final int 额外_停靠_状态_已取消停靠 = Intent.EXTRA_DOCK_STATE_UNDOCKED;

    public static final int 额外_停靠_状态_桌面 = Intent.EXTRA_DOCK_STATE_DESK;

    public static final int 额外_停靠_状态_汽车 = Intent.EXTRA_DOCK_STATE_CAR;

    public static final int 额外_停靠_状态_左下_桌面 = Intent.EXTRA_DOCK_STATE_LE_DESK;

    public static final int 额外_停靠_状态_他_桌面 = Intent.EXTRA_DOCK_STATE_HE_DESK;

    public static final String 元数据_停靠_家 = Intent.METADATA_DOCK_HOME;

    public static final String 额外_错误_报告 = Intent.EXTRA_BUG_REPORT;

    public static final String 额外_远程_意图_令牌 = Intent.EXTRA_REMOTE_INTENT_TOKEN;

    public static final String 额外_已改变_组件_名 = Intent.EXTRA_CHANGED_COMPONENT_NAME;

    public static final String 额外_已改变_组件_名_列表 = Intent.EXTRA_CHANGED_COMPONENT_NAME_LIST;

    public static final String 额外_已改变_包裹_列表 = Intent.EXTRA_CHANGED_PACKAGE_LIST;

    public static final String 额外_已改变_用户Id_列表 = Intent.EXTRA_CHANGED_UID_LIST;

    public static final String 额外_本地_仅 = Intent.EXTRA_LOCAL_ONLY;

    public static final String 额外_允许_多个 = Intent.EXTRA_ALLOW_MULTIPLE;

    public static final String 额外_用户 = Intent.EXTRA_USER;

    public static final String 额外_限制_列表 = Intent.EXTRA_RESTRICTIONS_LIST;

    public static final String 额外_限制_捆 = Intent.EXTRA_RESTRICTIONS_BUNDLE;

    public static final String 额外_限制_意图 = Intent.EXTRA_RESTRICTIONS_INTENT;

    public static final String 额外_MIME_类型 = Intent.EXTRA_MIME_TYPES;

    public static final String 额外_关闭_用户空间_仅 = Intent.EXTRA_SHUTDOWN_USERSPACE_ONLY;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final String 额外_时间 = Intent.EXTRA_TIME;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final String 额外_时区 = Intent.EXTRA_TIMEZONE;

    public static final String 额外_索引 = Intent.EXTRA_INDEX;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final String 额外_快速_视图_特性 = Intent.EXTRA_QUICK_VIEW_FEATURES;

    public static final String 额外_快速_模式 = Intent.EXTRA_QUIET_MODE;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 额外_内容_查询 = Intent.EXTRA_CONTENT_QUERY;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 额外_自动_启动_单一_选择 = Intent.EXTRA_AUTO_LAUNCH_SINGLE_CHOICE;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String 额外_轨迹_Id = Intent.EXTRA_LOCUS_ID;

    public static final int 标志_授予_阅读_URI_许可 = Intent.FLAG_GRANT_READ_URI_PERMISSION;
    public static final int 标志_授予_写_URI_许可 = Intent.FLAG_GRANT_WRITE_URI_PERMISSION;

    public static final int 标志_从_背景 = Intent.FLAG_FROM_BACKGROUND;

    public static final int 标志_调试_日志_决议 = Intent.FLAG_DEBUG_LOG_RESOLUTION;

    public static final int 标志_排除_停止_包裹 = Intent.FLAG_EXCLUDE_STOPPED_PACKAGES;

    public static final int 标志_包含_停止_包裹 = Intent.FLAG_INCLUDE_STOPPED_PACKAGES;

    public static final int 标志_授予_可持久化_URI_许可 = Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION;

    public static final int 标志_授予_前缀_URI_许可 = Intent.FLAG_GRANT_PREFIX_URI_PERMISSION;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final int 标志_直接_引导_自动 = Intent.FLAG_DIRECT_BOOT_AUTO;

    public static final int 标志_活动_不_历史 = Intent.FLAG_ACTIVITY_NO_HISTORY;

    public static final int 标志_活动_单一_顶部 = Intent.FLAG_ACTIVITY_SINGLE_TOP;

    public static final int 标志_活动_新_任务 = Intent.FLAG_ACTIVITY_NEW_TASK;

    public static final int 标志_活动_多个_任务 = Intent.FLAG_ACTIVITY_MULTIPLE_TASK;

    public static final int 标志_活动_清除_顶部 = Intent.FLAG_ACTIVITY_CLEAR_TOP;

    public static final int 标志_活动_前进_结果 = Intent.FLAG_ACTIVITY_FORWARD_RESULT;

    public static final int 标志_活动_上一个_是_顶部 = Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP;

    public static final int 标志_活动_排除_从_最近的 = Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS;

    public static final int 标志_活动_带来_至_前面 = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT;

    public static final int 标志_活动_重置_任务_如果_需要 = Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED;

    public static final int 标志_活动_已启动_从_历史 = Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY;

    public static final int 标志_活动_清除_什么时候_任务_重置 = Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET;

    public static final int 标志_活动_新_文件 = Intent.FLAG_ACTIVITY_NEW_DOCUMENT;

    public static final int 标志_活动_不_用户_行动 = Intent.FLAG_ACTIVITY_NO_USER_ACTION;
    public static final int 标志_活动_重新排序_至_前面 = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT;

    public static final int 标志_活动_不_动画 = Intent.FLAG_ACTIVITY_NO_ANIMATION;

    public static final int 标志_活动_清除_任务 = Intent.FLAG_ACTIVITY_CLEAR_TASK;

    public static final int 标志_活动_任务_开_家 = Intent.FLAG_ACTIVITY_TASK_ON_HOME;

    public static final int 标志_活动_保留_在_最近的 = Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS;

    public static final int 标志_活动_启动_相邻 = Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT;

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static final int 标志_活动_比赛_外部 = Intent.FLAG_ACTIVITY_MATCH_EXTERNAL;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final int 标志_活动_要求_否_浏览器 = Intent.FLAG_ACTIVITY_REQUIRE_NON_BROWSER;

    @RequiresApi(api = Build.VERSION_CODES.R)
    public static final int 标志_活动_要求_默认 = Intent.FLAG_ACTIVITY_REQUIRE_DEFAULT;

    public static final int 标志_接收器_已注册_仅 = Intent.FLAG_RECEIVER_REGISTERED_ONLY;

    public static final int 标志_接收器_替换_待处理 = Intent.FLAG_RECEIVER_REPLACE_PENDING;

    public static final int 标志_接收器_前景 = Intent.FLAG_RECEIVER_FOREGROUND;

    public static final int 标志_接收器_不_中止 = Intent.FLAG_RECEIVER_NO_ABORT;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final int 标志_接收器_可见_至_即时_应用程序 = Intent.FLAG_RECEIVER_VISIBLE_TO_INSTANT_APPS;

    public static final int URI_意图_方案 = Intent.URI_INTENT_SCHEME;

    public static final int URI_安卓_应用_方案 = Intent.URI_ANDROID_APP_SCHEME;

    public static final int URI_允许_不安全 = Intent.URI_ALLOW_UNSAFE;

    //============================================================================================

    @NonNull
    public Object 克隆() {
        return this.clone();
    }

    @NonNull
    public Intent 克隆过滤器() {
        return this.cloneFilter();
    }

    //============================================================================================

    public static Intent 制作主活动(ComponentName 主活动) {
        return Intent.makeMainActivity(主活动);
    }

    public static Intent 制作主选择器活动(String 选择器操作, String 选择器类别) {
        return Intent.makeMainSelectorActivity(选择器操作, 选择器类别);
    }

    public static Intent 制作重启活动任务(ComponentName 主活动) {
        return Intent.makeRestartActivityTask(主活动);
    }

    public static Intent 取意图(String uri) throws URISyntaxException {
        return Intent.getIntent(uri);
    }

    public static Intent 解析Uri(String uri, int 标志) throws URISyntaxException {
        return Intent.parseUri(uri, 标志);
    }

    public static Intent 取旧意图(String uri) throws URISyntaxException {
        return Intent.getIntentOld(uri);
    }

    //============================================================================================

    public String 取动作() {
        return this.getAction();
    }

    public Uri 取数据() {
        return this.getData();
    }

    public String 取数据字符串() {
        return this.getDataString();
    }

    public String 取方案() {
        return this.getScheme();
    }

    public String 取类型() {
        return this.getType();
    }

    public String 解析类型(@NonNull Context 上下文) {
        return this.resolveType(上下文);
    }

    public String 解析类型(@NonNull ContentResolver 解析) {
        return this.resolveType(解析);
    }

    public String 解析类型如有需要(@NonNull ContentResolver 解析) {
        return this.resolveTypeIfNeeded(解析);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public String 取标识符() {
        return this.getIdentifier();
    }

    public boolean 有类别(String 类别) {
        return this.hasCategory(类别);
    }

    public Set<String> 取类别() {
        return this.getCategories();
    }

    public Intent 取选择器() {
        return this.getSelector();
    }

    public ClipData 取剪贴板数据() {
        return this.getClipData();
    }

    public void 置额外类加载器(ClassLoader 加载器) {
        this.setExtrasClassLoader(加载器);
    }

    public boolean 有额外(String 名) {
        return this.hasExtra(名);
    }

    public boolean 有文件描述符() {
        return this.hasFileDescriptors();
    }

    public boolean 取布尔额外(String 名, boolean 默认值) {
        return this.getBooleanExtra(名, 默认值);
    }

    public byte 取字节额外(String 名, byte 默认值) {
        return this.getByteExtra(名, 默认值);
    }

    public short 取短额外(String 名, short 默认值) {
        return this.getShortExtra(名, 默认值);
    }

    public char 取字符额外(String 名, char 默认值) {
        return this.getCharExtra(名, 默认值);
    }

    public int 取整数额外(String 名, int 默认值) {
        return this.getIntExtra(名, 默认值);
    }

    public long 取长整数额外(String 名, long 默认值) {
        return this.getLongExtra(名, 默认值);
    }

    public float 取浮点额外(String 名, float 默认值) {
        return this.getFloatExtra(名, 默认值);
    }

    public double 取双精度额外(String 名, double 默认值) {
        return this.getDoubleExtra(名, 默认值);
    }

    public String 取字符串额外(String 名) {
        return this.getStringExtra(名);
    }

    public CharSequence 取字符序列额外(String 名) {
        return this.getCharSequenceExtra(名);
    }

    public <T extends Parcelable> T 取可包裹额外(String 名) {
        return this.getParcelableExtra(名);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public <T> T 取可包裹额外(String 名, @NonNull Class<T> 类) {
        return this.getParcelableExtra(名, 类);
    }

    public Parcelable[] 取可包裹数组额外(String 名) {
        return this.getParcelableArrayExtra(名);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public <T> T[] 取可包裹数组额外(String 名, @NonNull Class<T> 类) {
        return this.getParcelableArrayExtra(名, 类);
    }

    public <T extends Parcelable> ArrayList<T> 取可包裹数组列表额外(String 名) {
        return this.getParcelableArrayListExtra(名);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public <T> ArrayList<T> 取可包裹数组列表额外(String 名, @NonNull Class<? extends T> 类) {
        return this.getParcelableArrayListExtra(名, 类);
    }

    public Serializable 取可序列化额外(String 名){
        return this.getSerializableExtra(名);
    }

    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    public <T extends Serializable> T 取可序列化额外(String 名, @NonNull Class<T> 类) {
        return this.getSerializableExtra(名, 类);
    }

    public ArrayList<Integer> 取整数数组列表额外(String 名) {
        return this.getIntegerArrayListExtra(名);
    }

    public ArrayList<String> 取字符串数组列表额外(String 名) {
        return this.getStringArrayListExtra(名);
    }

    public ArrayList<CharSequence> 取字符序列数组列表额外(String 名) {
        return this.getCharSequenceArrayListExtra(名);
    }

    public boolean[] 取布尔数组额外(String 名) {
        return this.getBooleanArrayExtra(名);
    }

    public byte[] 取字节数组额外(String 名) {
        return this.getByteArrayExtra(名);
    }

    public short[] 取短数组额外(String 名) {
        return this.getShortArrayExtra(名);
    }

    public char[] 取字符数组额外(String 名) {
        return this.getCharArrayExtra(名);
    }

    public int[] 取整数数组额外(String 名) {
        return this.getIntArrayExtra(名);
    }

    public long[] 取长整数数组额外(String 名) {
        return this.getLongArrayExtra(名);
    }

    public float[] 取浮点数组额外(String 名) {
        return this.getFloatArrayExtra(名);
    }

    public double[] 取双精度数组额外(String 名) {
        return this.getDoubleArrayExtra(名);
    }

    public String[] 取字符串数组额外(String 名) {
        return this.getStringArrayExtra(名);
    }

    public CharSequence[] 取字符序列数组额外(String 名) {
        return this.getCharSequenceArrayExtra(名);
    }

    public Bundle 取附加额外(String 名) {
        return this.getBundleExtra(名);
    }

    public Bundle 取额外() {
        return this.getExtras();
    }

    public int 取标志() {
        return this.getFlags();
    }

    public String 取包() {
        return this.getPackage();
    }

    public ComponentName 取组件() {
        return this.getComponent();
    }

    public Rect 取源边界() {
        return this.getSourceBounds();
    }

    public ComponentName 解析活动(@NonNull PackageManager 包管理器) {
        return this.resolveActivity(包管理器) ;
    }

    public ActivityInfo 解析活动信息(@NonNull PackageManager 包管理器, int 标志) {
        return this.resolveActivityInfo(包管理器, 标志);
    }

    public @NonNull Intent 置动作(String 动作) {
        return this.setAction(动作);
    }

    public @NonNull Intent 置数据(Uri 数据) {
        return this.setData(数据);
    }

    public @NonNull Intent 置数据并规范化(@NonNull Uri 数据) {
        return setDataAndNormalize(数据);
    }

    public @NonNull Intent 置类型(String 类型) {
        return this.setType(类型);
    }

    public @NonNull Intent 置类型并规范化(String 类型) {
        return this.setTypeAndNormalize(类型);
    }

    public @NonNull Intent 置数据和类型(Uri 数据, String 类型) {
        return this.setDataAndType(数据, 类型);
    }

    public @NonNull Intent 置数据和类型并规范化(@NonNull Uri 数据, String 类型) {
        return this.setDataAndTypeAndNormalize(数据, 类型);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public @NonNull Intent 置标识符(String 标识符) {
        return this.setIdentifier(标识符);
    }

    public @NonNull Intent 添加类别(String 类别) {
        return this.addCategory(类别);
    }

    public void 移除类别(String 类别) {
        this.removeCategory(类别);
    }

    public void 置选择器(Intent 选择器) {
        this.setSelector(选择器);
    }

    public void 置剪贴板数据(ClipData 剪贴板) {
        this.setClipData(剪贴板);
    }

    public @NonNull Intent 放置额外(String 名, boolean 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, byte 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, char 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, short 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, int 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, long 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, float 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, double 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, String 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, CharSequence 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, Parcelable 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, Parcelable[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置可打包数组列表额外(String 名, ArrayList<? extends Parcelable> 值) {
        return this.putParcelableArrayListExtra(名, 值);
    }

    public @NonNull Intent 放置整数数组列表额外(String 名, ArrayList<Integer> 值) {
        return this.putIntegerArrayListExtra(名, 值);
    }

    public @NonNull Intent 放置字符串数组列表额外(String 名, ArrayList<String> 值) {
        return this.putStringArrayListExtra(名, 值);
    }

    public @NonNull Intent 放置字符序列数组列表额外(String 名, ArrayList<CharSequence> 值) {
        return this.putCharSequenceArrayListExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, Serializable 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, boolean[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, byte[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, short[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, char[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, int[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, long[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, float[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, double[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, String[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, CharSequence[] 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(String 名, Bundle 值) {
        return this.putExtra(名, 值);
    }

    public @NonNull Intent 放置额外(@NonNull Intent 源) {
        return this.putExtras(源);
    }

    public @NonNull Intent 放置额外(@NonNull Bundle 额外) {
        return this.putExtras(额外);
    }

    public @NonNull Intent 替换额外(@NonNull Intent 源) {
        return this.replaceExtras(源);
    }

    public @NonNull Intent 替换额外(@NonNull Bundle 额外) {
        return this.replaceExtras(额外);
    }

    public void 移除额外(String 名) {
        this.removeExtra(名);
    }


    public @NonNull Intent 置标志(int 标志) {
        return this.setFlags(标志);
    }

    public @NonNull Intent 添加标志(int 标志) {
        return this.addFlags(标志);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void 移除标志(int 标志) {
        this.removeFlags(标志);
    }

    public @NonNull Intent 置包(String 包名) {
        return this.setPackage(包名);
    }

    public @NonNull Intent 置组件(ComponentName 组件) {
        return this.setComponent(组件);
    }

    public @NonNull Intent 置类名(@NonNull Context 包上下文, @NonNull String 类名) {
        return this.setClassName(包上下文, 类名);
    }

    public @NonNull Intent 置类名(@NonNull String 包名, @NonNull String 类名) {
        return this.setClassName(包名, 类名);
    }

    public @NonNull Intent 置类(@NonNull Context 包上下文, @NonNull Class<?> 类) {
        return this.setClass(包上下文, 类);
    }

    public void 置源边界(Rect r) {
        this.setSourceBounds(r);
    }

    //============================================================================================

    public static final int 填充_在_行动 = Intent.FILL_IN_ACTION;

    public static final int 填充_在_数据 = Intent.FILL_IN_DATA;

    public static final int 填充_在_类别 = Intent.FILL_IN_CATEGORIES;

    public static final int 填充_在_组件 = Intent.FILL_IN_COMPONENT;

    public static final int 填充_在_包裹 = Intent.FILL_IN_PACKAGE;

    public static final int 填充_在_源_界限 = Intent.FILL_IN_SOURCE_BOUNDS;

    public static final int 填充_在_选择器 = Intent.FILL_IN_SELECTOR;

    public static final int 填充_在_剪贴板_数据 = Intent.FILL_IN_CLIP_DATA;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final int 填充_在_标识符 = Intent.FILL_IN_IDENTIFIER;

    //============================================================================================

    public int 填充在(@NonNull Intent 其他, int 标志) {
        return this.fillIn(其他, 标志);
    }

    //============================================================================================

    public static final class 过滤器比较 {

        private final FilterComparison 过滤器比较;

        public 过滤器比较(Intent 意图) {
            过滤器比较 = new FilterComparison(意图);
        }

        public Intent 取意图() {
            return 过滤器比较.getIntent();
        }

        @Override
        public boolean equals(@Nullable Object obj) {
            if (obj instanceof FilterComparison) {
                return 过滤器比较.equals(obj);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return 过滤器比较.hashCode();
        }
    }

    //============================================================================================

    public boolean 过滤等于(Intent 其他) {
        return this.filterEquals(其他);
    }

    public int 过滤哈希码() {
        return this.filterHashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public String 到URI() {
        return this.toURI();
    }

    public String 到Uri(int 标志) {
        return this.toUri(标志);
    }

    public int 描述内容() {
        return this.describeContents();
    }

    @RequiresApi(api = Build.VERSION_CODES.BAKLAVA)
    public void 移除启动安全保护() {
        this.removeLaunchSecurityProtection();
    }

    public void 写到包裹(Parcel out, int 标志) {
        this.writeToParcel(out, 标志);
    }

    //============================================================================================

    public static final @NonNull Parcelable.Creator<Intent> 创造者 = Intent.CREATOR;

    //============================================================================================

    public void 读自包裹(Parcel in) {
        this.readFromParcel(in);
    }

    //============================================================================================

    public static @NonNull Intent 解析意图(@NonNull Resources 资源, @NonNull XmlPullParser 解析器, AttributeSet 属性) throws XmlPullParserException, IOException {
        return Intent.parseIntent(资源, 解析器, 属性);
    }

    public static @Nullable String 规范化MIME类型(@Nullable String 类型) {
        return Intent.normalizeMimeType(类型);
    }

    //============================================================================================

    @RequiresApi(api = Build.VERSION_CODES.VANILLA_ICE_CREAM)
    public boolean 是否不匹配过滤器() {
        return this.isMismatchingFilter();
    }


}
