package 安卓.应用

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi

//=====================================================================

/**
 * 描述：设置内容视图
 */
fun Activity.置内容视图(布局: Int) = this.setContentView(布局)
/**
 * 描述：设置内容视图
 */
fun Activity.置内容视图(视图: View) = this.setContentView(视图)

/**
 * 描述：设置内容视图
 */
fun Activity.置内容视图(视图: View, 布局参数: ViewGroup.LayoutParams)
        = this.setContentView(视图,布局参数)

//=====================================================================
/**
 * 描述：添加内容视图
 */
fun Activity.添加内容视图(视图: View, 布局参数: ViewGroup.LayoutParams)
        = this.addContentView(视图,布局参数)

//=====================================================================

/**
 * 描述：查找视图ID
 * @param id 视图ID。
 * @return 视图。
 */
fun <T : View> Activity.查找视图Id(id: Int): T = this.findViewById<T>(id)

//=====================================================================

/**
 * 描述：标题
 * @param 标题 标题。
 * @return 标题。
 */
var Activity.标题: CharSequence
    get() = this.title
    set(标题) { this.title = 标题 }

/**
 * 描述：取标题
 * @return 标题。
 */
fun Activity.取标题():CharSequence = this.getTitle()

/**
 * 描述：置标题
 * @param 标题 标题。
 */
fun Activity.置标题(标题: CharSequence) = this.setTitle(标题)

/**
 * 描述：置标题
 * @param 标题Id 标题Id。
 */
fun Activity.置标题(标题Id: Int) = this.setTitle(标题Id)

//=====================================================================

/**
 * 描述：标题颜色
 * @param 标题颜色 标题颜色。
 * @return 标题颜色。
 */
var Activity.标题颜色: Int
    get() = this.titleColor
    set(标题颜色) { this.titleColor = 标题颜色 }

/**
 * 描述：标题颜色
 * @return 标题颜色。
 */
fun Activity.取标题颜色():Int = this.getTitleColor()

/**
 * 描述：标题颜色
 * @param 标题颜色 标题颜色。
 */
fun Activity.置标题颜色(标题颜色: Int) = this.setTitleColor(标题颜色)

//=====================================================================

/**
 * 描述：切换窗口
 * @param 意图 意图。
 */
fun Activity.启动窗口(意图: Intent) = this.startActivity(意图)

/**
 * 描述：切换窗口
 * @param 意图 意图。
 * @param 保存数据状态 保存数据状态。
 */
fun Activity.启动窗口(意图: Intent, 保存数据状态: Bundle?) =
    this.startActivity(意图,保存数据状态)

//=====================================================================

/**
 * 描述：切换多窗口
 * @param 意图 意图。
 */
fun Activity.启动多窗口(意图: Array<Intent>) = this.startActivities(意图)

/**
 * 描述：切换多窗口
 * @param 意图 意图。
 * @param 保存数据状态 保存数据状态。
 */
fun Activity.启动多窗口(意图: Array<Intent>,保存数据状态: Bundle?) =
    this.startActivities(意图,保存数据状态)

//=====================================================================

/**
 * 描述：窗口切换动画
 * @param 进入动画 进入动画。
 * @param 退出动画 退出动画。
 */
fun Activity.窗口切换动画(进入动画: Int, 退出动画: Int) =
    this.overridePendingTransition(进入动画, 退出动画)

/**
 * 描述：窗口切换动画,仅支持 Android 14及以上版本才能使用
 * @param 覆盖类型 覆盖类型。
 * @param 进入动画 进入动画。
 * @param 退出动画 退出动画。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun Activity.窗口切换动画(覆盖类型: Int, 进入动画: Int, 退出动画: Int) =
    this.overrideActivityTransition(覆盖类型,进入动画, 退出动画)

//=====================================================================

 /**
 * 描述：关闭窗口
 */
fun Activity.关闭() = this.finish()

/**
 * 描述：重建窗口
 */
fun Activity.重建() = this.recreate()

//=====================================================================
/**
 * 描述：在主线程执行
 * @param 运行器 运行器。
 */
fun Activity.主线程执行(运行器: Runnable) = this.runOnUiThread(运行器)


