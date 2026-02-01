package 安卓.应用

import android.app.AlertDialog
import android.app.AlertDialog.Builder
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Message
import android.view.KeyEvent
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListAdapter
import android.widget.ListView
import androidx.annotation.ArrayRes
import androidx.annotation.AttrRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：警告对话框
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 警告对话框 :AlertDialog{
    constructor(context: Context?) : super(context)
    constructor(context: Context?, cancelable: Boolean, cancelListener: DialogInterface.OnCancelListener?,
    ) : super(context, cancelable, cancelListener)

    constructor(context: Context?, themeResId: Int) : super(context, themeResId)

    override fun onCreate(savedInstanceState: Bundle?) {
        创建回调(savedInstanceState)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return 按键按下回调(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return 按键抬起回调(keyCode, event)
    }

    /**
     * 创建时间：2025年12月4日.
     *
     * 描述：创建回调
     *
     * 版本：0.1.6
     */
    open fun 创建回调(保存实例状态: Bundle?){
        super.onCreate(保存实例状态)
    }

    /**
     * 创建时间：2025年12月4日.
     *
     * 描述：按键按下回调
     *
     * 版本：0.1.6
     */
    open fun 按键按下回调(键值: Int, 事件: KeyEvent) : Boolean{
        return super.onKeyDown(键值,事件)
    }

    /**
     * 创建时间：2025年12月4日.
     *
     * 描述：按键抬起回调
     *
     * 版本：0.1.6
     */
    open fun 按键抬起回调(键值: Int, 事件: KeyEvent) : Boolean{
        return super.onKeyUp(键值,事件)
    }

    //===============================================================

    /**
     * 创建时间：2025年12月4日.
     *
     * 描述：构建器
     *
     * 版本：0.1.6
     */
    open class 构建器 :Builder{
        constructor(context: Context?) : super(context)
        constructor(context: Context?, themeResId: Int) : super(context, themeResId)
    }

}

//===============================================================
//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：取按钮
 *
 * 版本：0.1.6
 */
fun AlertDialog.取按钮(哪个按钮: Int) : Button = this.getButton(哪个按钮)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：取列表视图
 *
 * 版本：0.1.6
 */
fun AlertDialog.取列表视图() : ListView = this.getListView()

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：设置警告对话框标题
 *
 * 版本：0.1.6
 */
fun AlertDialog.置标题(标题: CharSequence) = this.setTitle(标题)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置自定义标题
 *
 * 版本：0.1.6
 */
fun AlertDialog.置自定义标题(自定义标题视图: View) = this.setCustomTitle(自定义标题视图)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：设置警告对话框内容
 *
 * 版本：0.1.6
 */
fun AlertDialog.置消息(消息: CharSequence)  = this.setMessage(消息)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置视图
 *
 * 版本：0.1.6
 */
fun AlertDialog.置视图(视图: View) = this.setView(视图)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置视图
 *
 * 版本：0.1.6
 */
fun AlertDialog.置视图(视图: View,视图间距左: Int,视图间距上: Int,视图间距右: Int,视图间距下: Int) =
    this.setView(视图,视图间距左,视图间距上,视图间距右,视图间距下)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮(哪个按钮: Int, 文本: CharSequence, 信息: Message) =
    this.setButton(哪个按钮,文本,信息)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮(哪个按钮: Int, 文本: CharSequence, 监听器: DialogInterface.OnClickListener) =
    this.setButton(哪个按钮,文本,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮(文本: CharSequence, 信息: Message) =
    this.setButton(文本,信息)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮2
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮2(文本: CharSequence, 信息: Message) =
    this.setButton2(文本,信息)
/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮3
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮3(文本: CharSequence, 信息: Message) =
    this.setButton3(文本,信息)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) =
    this.setButton(文本,监听器)
/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮2
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮2(文本: CharSequence, 监听器: DialogInterface.OnClickListener) =
    this.setButton2(文本,监听器)
/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按钮3
 *
 * 版本：0.1.6
 */
fun AlertDialog.置按钮3(文本: CharSequence, 监听器: DialogInterface.OnClickListener) =
    this.setButton3(文本,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置图标
 *
 * 版本：0.1.6
 */
fun AlertDialog.置图标(@DrawableRes 资源Id: Int) = this.setIcon(资源Id)
/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置图标
 *
 * 版本：0.1.6
 */
fun AlertDialog.置图标(图标: Drawable) = this.setIcon(图标)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置图标属性
 *
 * 版本：0.1.6
 */
fun AlertDialog.置图标属性(@AttrRes 属性Id: Int) = this.setIconAttribute(属性Id)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置强制反色背景
 *
 * 版本：0.1.6
 */
fun AlertDialog.置强制反色背景(是否强制反色背景: Boolean) = this.setInverseBackgroundForced(是否强制反色背景)

//===============================================================
//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：取上下文
 *
 * 版本：0.1.6
 */
fun Builder.取上下文() : Context = this.getContext()

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：设置警告对话框标题
 *
 * 版本：0.1.6
 */
fun Builder.置标题(@StringRes 标题Id: Int) : Builder = this.setTitle(标题Id)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：设置警告对话框标题
 *
 * 版本：0.1.6
 */
fun Builder.置标题(标题: CharSequence) : Builder = this.setTitle(标题)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置自定义标题
 *
 * 版本：0.1.6
 */
fun Builder.置自定义标题(自定义标题视图: View): Builder = this.setCustomTitle(自定义标题视图)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：设置警告对话框消息
 *
 * 版本：0.1.6
 */
fun Builder.置消息(@StringRes 信息Id: Int) : Builder = this.setMessage(信息Id)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：设置警告对话框消息
 *
 * 版本：0.1.6
 */
fun Builder.置消息(消息: CharSequence) : Builder = this.setMessage(消息)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置图标
 *
 * 版本：0.1.6
 */
fun Builder.置图标(@DrawableRes 图标Id: Int) : Builder = this.setIcon(图标Id)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置图标
 *
 * 版本：0.1.6
 */
fun Builder.置图标(图标: Drawable) : Builder = this.setIcon(图标)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置图标属性
 *
 * 版本：0.1.6
 */
fun Builder.置图标属性(@AttrRes 属性Id: Int) : Builder = this.setIconAttribute(属性Id)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置确定按钮
 *
 * 版本：0.1.6
 */
fun Builder.置确定按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setPositiveButton(文本Id,监听器)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置确定按钮
 *
 * 版本：0.1.6
 */
fun Builder.置确定按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setPositiveButton(文本,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置取消按钮
 *
 * 版本：0.1.6
 */
fun Builder.置取消按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setNegativeButton(文本Id,监听器)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置取消按钮
 *
 * 版本：0.1.6
 */
fun Builder.置取消按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setNegativeButton(文本,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置忽略按钮
 *
 * 版本：0.1.6
 */
fun Builder.置忽略按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setNeutralButton(文本Id,监听器)
/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置忽略按钮
 *
 * 版本：0.1.6
 */
fun Builder.置忽略按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setNeutralButton(文本,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置可取消
 *
 * 版本：0.1.6
 */
fun Builder.置可取消(可取消: Boolean) : Builder = this.setCancelable(可取消)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置取消监听器
 *
 * 版本：0.1.6
 */
fun Builder.置取消监听器(取消监听器: DialogInterface.OnCancelListener) : Builder =
    this.setOnCancelListener(取消监听器)

//===============================================================


/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置关闭监听器
 *
 * 版本：0.1.6
 */
fun Builder.置关闭监听器(关闭监听器: DialogInterface.OnDismissListener) : Builder =
    this.setOnDismissListener(关闭监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置按键监听器
 *
 * 版本：0.1.6
 */
fun Builder.置按键监听器(按键监听器: DialogInterface.OnKeyListener) : Builder =
    this.setOnKeyListener(按键监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置项目
 *
 * 版本：0.1.6
 */
fun Builder.置项目(@ArrayRes 项目Id: Int, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setItems(项目Id,监听器)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置项目
 *
 * 版本：0.1.6
 */
fun Builder.置项目(项目: Array<CharSequence>, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setItems(项目,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置适配器
 *
 * 版本：0.1.6
 */
fun Builder.置适配器(适配器: ListAdapter, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setAdapter(适配器,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置光标
 *
 * 版本：0.1.6
 */
fun Builder.置光标(光标: Cursor, 监听器: DialogInterface.OnClickListener, 标签列: String) : Builder =
    this.setCursor(光标,监听器,标签列)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置多选项目
 *
 * 版本：0.1.6
 */
fun Builder.置多选项目(@ArrayRes 项目Id: Int, 已选中项目: BooleanArray, 监听器: DialogInterface.OnMultiChoiceClickListener) : Builder =
    this.setMultiChoiceItems(项目Id,已选中项目,监听器)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置多选项目
 *
 * 版本：0.1.6
 */
fun Builder.置多选项目(项目: Array<CharSequence>, 已选中项目: BooleanArray, 监听器: DialogInterface.OnMultiChoiceClickListener) : Builder =
    this.setMultiChoiceItems(项目,已选中项目,监听器)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置多选项目
 *
 * 版本：0.1.6
 */
fun Builder.置多选项目(光标 : Cursor, 是否选中列: String, 标签列: String, 监听器: DialogInterface.OnMultiChoiceClickListener) : Builder =
    this.setMultiChoiceItems(光标,是否选中列,标签列,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置单选项目
 *
 * 版本：0.1.6
 */
fun Builder.置单选项目(@ArrayRes 项目Id: Int, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setSingleChoiceItems(项目Id,已选中项目,监听器)
/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置单选项目
 *
 * 版本：0.1.6
 */
fun Builder.置单选项目(光标 : Cursor, 已选中项目: Int, 标签列: String, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setSingleChoiceItems(光标,已选中项目,标签列,监听器)
/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置单选项目
 *
 * 版本：0.1.6
 */
fun Builder.置单选项目(项目: Array<CharSequence>, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setSingleChoiceItems(项目,已选中项目,监听器)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置单选项目
 *
 * 版本：0.1.6
 */
fun Builder.置单选项目(适配器: ListAdapter, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : Builder =
    this.setSingleChoiceItems(适配器,已选中项目,监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置项目选中监听器
 *
 * 版本：0.1.6
 */
fun Builder.置项目选中监听器(监听器: AdapterView.OnItemSelectedListener) : Builder =
    this.setOnItemSelectedListener(监听器)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置视图
 *
 * 版本：0.1.6
 */
fun Builder.置视图(布局资源Id: Int) : Builder =
    this.setView(布局资源Id)

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：置视图
 *
 * 版本：0.1.6
 */
fun Builder.置视图(视图: View) : Builder =
    this.setView(视图)

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：创建警告对话框
 *
 * 版本：0.1.6
 */
fun Builder.创建(): AlertDialog = this.create()

//===============================================================

/**
 * 创建时间：2025年12月4日.
 *
 * 描述：显示警告对话框
 *
 * 版本：0.1.6
 */
fun Builder.显示(): AlertDialog = this.show()

//===============================================================
