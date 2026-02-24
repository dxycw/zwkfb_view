package 安卓x.应用兼容包.应用

import android.annotation.SuppressLint
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
import androidx.appcompat.app.AlertDialog

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：警告对话框
 * @author dxyc
 */
open class 警告对话框 : AlertDialog {

    protected constructor(上下文: Context) : super(上下文)
    protected constructor(上下文: Context, 主题资源Id: Int) : super(上下文, 主题资源Id)
    protected constructor(上下文: Context, 可取消: Boolean, 取消监听器: DialogInterface.OnCancelListener?,
    ) : super(上下文, 可取消, 取消监听器)

    /**
     * 描述：取按钮
     */
    open fun 取按钮(哪个按钮: Int) : Button {
        return super.getButton(哪个按钮)
    }

    //===============================================================

    /**
     * 描述：取列表视图
     */
    open val 列表视图 : ListView get() = super.listView

    /**
     * 描述：取列表视图
     */
    open fun 取列表视图() : ListView {
        return super.listView
    }

    //===============================================================

    /**
     * 描述：设置警告对话框标题
     */
    open fun 置标题(标题: CharSequence){
        super.setTitle(标题)
    }

    //===============================================================

    /**
     * 描述：置自定义标题
     */
    open fun 置自定义标题(自定义标题视图: View) {
        super.setCustomTitle(自定义标题视图)
    }

    //===============================================================

    /**
     * 描述：设置警告对话框内容
     */
    open fun 置消息(消息: CharSequence) {
        super.setMessage(消息)
    }

    //===============================================================

    /**
     * 描述：置视图
     */
    open fun 置视图(视图: View) {
        super.setView(视图)
    }

    /**
     * 描述：置视图
     */
    open fun 置视图(视图: View,视图间距左: Int,视图间距上: Int,视图间距右: Int,视图间距下: Int) {
        super.setView(视图,视图间距左,视图间距上,视图间距右,视图间距下)
    }

    //===============================================================

    /**
     * 描述：置按钮
     */
    open fun 置按钮(哪个按钮: Int, 文本: CharSequence, 信息: Message) {
        super.setButton(哪个按钮,文本,信息)
    }

    /**
     * 描述：置按钮
     */
    open fun 置按钮(哪个按钮: Int, 文本: CharSequence, 监听器: DialogInterface.OnClickListener) {
        super.setButton(哪个按钮,文本,监听器)
    }

    //===============================================================

    /**
     * 描述：置图标
     */
    open fun 置图标(资源Id: Int) {
        super.setIcon(资源Id)
    }

    /**
     * 描述：置图标
     */
    open fun 置图标(图标: Drawable) {
        super.setIcon(图标)
    }

    //===============================================================

    /**
     * 描述：置图标属性
     */
    open fun 置图标属性(@AttrRes 属性Id: Int) {
        super.setIconAttribute(属性Id)
    }

    //===============================================================

    /**
     * 描述：创建回调
     */
    open fun 创建回调(保存实例状态: Bundle){
        super.onCreate(保存实例状态)
    }

    /**
     * 描述：按键按下回调
     */
    open fun 按键按下回调(键值: Int, 事件: KeyEvent) : Boolean{
        return super.onKeyDown(键值,事件)
    }

    /**
     * 描述：按键抬起回调
     */
    open fun 按键抬起回调(键值: Int, 事件: KeyEvent) : Boolean{
        return super.onKeyUp(键值,事件)
    }

    //===============================================================

    /**
     * 描述：构建器
     */
    open class 构建器 : Builder{

        constructor(上下文: Context) : super(上下文)
        constructor(上下文: Context, 主题资源Id: Int) : super(上下文, 主题资源Id)

        //===============================================================

        open val 上下文: Context get() = super.context

        open fun 取上下文(): Context = super.getContext()

        //===============================================================

        /**
         * 描述：设置警告对话框标题
         */
        open fun 置标题(@StringRes 标题Id: Int): 构建器 {
            super.setTitle(标题Id)
            return this
        }

        /**
         * 描述：设置警告对话框标题
         */
        open fun 置标题(标题: CharSequence): 构建器 {
            super.setTitle(标题)
            return this
        }

        //===============================================================

        /**
         * 描述：置自定义标题
         */
        open fun 置自定义标题(自定义标题视图: View): 构建器 {
            super.setCustomTitle(自定义标题视图)
            return this
        }

        //===============================================================

        /**
         * 描述：设置警告对话框消息
         */
        open fun 置消息(@StringRes 信息Id: Int): 构建器 {
            super.setMessage(信息Id)
            return this
        }

        /**
         * 描述：设置警告对话框消息
         */
        open fun 置消息(消息: CharSequence): 构建器 {
            super.setMessage(消息)
            return this
        }

        //===============================================================

        /**
         * 描述：置图标
         */
        open fun 置图标(@DrawableRes 图标Id: Int): 构建器 {
            super.setIcon(图标Id)
            return this
        }

        /**
         * 描述：置图标
         */
        open fun 置图标(图标: Drawable): 构建器 {
            super.setIcon(图标)
            return this
        }

        //===============================================================

        /**
         * 描述：置图标属性
         */
        open fun 置图标属性(@AttrRes 属性Id: Int): 构建器 {
            super.setIconAttribute(属性Id)
            return this
        }

        //===============================================================

        /**
         * 描述：置确定按钮
         */
        open fun 置确定按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener): 构建器 {
            super.setPositiveButton(文本Id, 监听器)
            return this
        }


        /**
         * 描述：置确定按钮
         */
        open fun 置确定按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener): 构建器 {
            super.setPositiveButton(文本,监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置确定按钮图标
         */
        open fun 置确定按钮图标(图标: Drawable): 构建器 {
            super.setPositiveButtonIcon(图标)
            return this
        }

        //===============================================================

        /**
         * 描述：置取消按钮
         */
        open fun 置取消按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setNegativeButton(文本Id,监听器)
            return this
        }

        /**
         * 描述：置取消按钮
         */
        open fun 置取消按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setNegativeButton(文本,监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置取消按钮图标
         */
        open fun 置取消按钮图标(图标: Drawable): 构建器 {
            super.setNegativeButtonIcon(图标)
            return this
        }

        //===============================================================

        /**
         * 描述：置忽略按钮
         */
        open fun 置忽略按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setNeutralButton(文本Id,监听器)
            return this
        }

        /**
         * 描述：置忽略按钮
         */
        open fun 置忽略按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setNeutralButton(文本,监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置忽略按钮图标
         */
        open fun 置忽略按钮图标(图标: Drawable): 构建器 {
            super.setNeutralButtonIcon(图标)
            return this
        }

        //===============================================================

        /**
         * 描述：置可取消
         */
        open fun 置可取消(可取消: Boolean) : 构建器 {
            super.setCancelable(可取消)
            return this
        }

        //===============================================================

        /**
         * 描述：置取消监听器
         */
        open fun 置取消监听器(取消监听器: DialogInterface.OnCancelListener) : 构建器 {
            super.setOnCancelListener(取消监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置关闭监听器
         */
        open fun 置关闭监听器(关闭监听器: DialogInterface.OnDismissListener) : 构建器 {
            super.setOnDismissListener(关闭监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置按键监听器
         */
        open fun 置按键监听器(按键监听器: DialogInterface.OnKeyListener) : 构建器 {
            super.setOnKeyListener(按键监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置项目
         */
        open fun 置项目(@ArrayRes 项目Id: Int, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setItems(项目Id,监听器)
            return this
        }

        /**
         * 描述：置项目
         */
        open fun 置项目(项目: Array<CharSequence>, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setItems(项目,监听器)
            return this
        }


        //===============================================================

        /**
         * 描述：置适配器
         */
        open fun 置适配器(适配器: ListAdapter, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setAdapter(适配器,监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置光标
         */
        open fun 置光标(光标: Cursor, 监听器: DialogInterface.OnClickListener, 标签列: String) : 构建器 {
            super.setCursor(光标,监听器,标签列)
            return this
        }

        //===============================================================

        /**
         * 描述：置多选项目
         */
        open fun 置多选项目(@ArrayRes 项目Id: Int, 已选中项目: BooleanArray, 监听器: DialogInterface.OnMultiChoiceClickListener) : 构建器 {
            super.setMultiChoiceItems(项目Id,已选中项目,监听器)
            return this
        }

        /**
         * 描述：置多选项目
         */
        open fun 置多选项目(项目: Array<CharSequence>, 已选中项目: BooleanArray, 监听器: DialogInterface.OnMultiChoiceClickListener) : 构建器 {
            super.setMultiChoiceItems(项目,已选中项目,监听器)
            return this
        }

        /**
         * 描述：置多选项目
         */
        open fun 置多选项目(光标 : Cursor, 是否选中列: String, 标签列: String, 监听器: DialogInterface.OnMultiChoiceClickListener) : 构建器 {
            super.setMultiChoiceItems(光标,是否选中列,标签列,监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置单选项目
         */
        open fun 置单选项目(@ArrayRes 项目Id: Int, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setSingleChoiceItems(项目Id,已选中项目,监听器)
            return this
        }

        /**
         * 描述：置单选项目
         */
        open fun 置单选项目(光标 : Cursor, 已选中项目: Int, 标签列: String, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setSingleChoiceItems(光标,已选中项目,标签列,监听器)
            return this
        }

        /**
         * 描述：置单选项目
         */
        open fun 置单选项目(项目: Array<CharSequence>, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setSingleChoiceItems(项目,已选中项目,监听器)
            return this
        }

        /**
         * 描述：置单选项目
         */
        open fun 置单选项目(适配器: ListAdapter, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : 构建器 {
            super.setSingleChoiceItems(适配器,已选中项目,监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置项目选中监听器
         */
        open fun 置项目选中监听器(监听器: AdapterView.OnItemSelectedListener) : 构建器 {
            super.setOnItemSelectedListener(监听器)
            return this
        }

        //===============================================================

        /**
         * 描述：置视图
         */
        open fun 置视图(布局资源Id: Int) : 构建器 {
            super.setView(布局资源Id)
            return this
        }

        /**
         * 描述：置视图
         */
        open fun 置视图(视图: View) : 构建器 {
            super.setView(视图)
            return this
        }

        /**
         * 描述：置视图
         */
        @SuppressLint("RestrictedApi")
        open fun 置视图(视图: View, 视图左侧间距: Int, 视图顶部间距: Int, 视图右侧间距: Int, 视图底部间距: Int) : 构建器 {
            super.setView(视图, 视图左侧间距, 视图顶部间距, 视图右侧间距, 视图底部间距)
            return this
        }

        //===============================================================

        open fun 置强制反转背景(是否强制反转背景: Boolean) : 构建器 {
            super.setInverseBackgroundForced(是否强制反转背景)
            return this
        }

        //===============================================================

        @SuppressLint("RestrictedApi")
        open fun 置测量时回收启用(是否启用: Boolean) : 构建器 {
            super.setRecycleOnMeasureEnabled(是否启用)
            return this
        }

        //===============================================================

        open fun 创建(): 警告对话框 {
            super.create()
            return 警告对话框(上下文)
        }

        //===============================================================

        /**
         * 使用此构建器提供的参数创建一个 `警告对话框`，并立即显示该对话框。
         * 调用此方法在功能上等同于：
         * ```
         *  警告对话框 对话框 = 构建器.创建();
         *  对话框.显示();
         * ```
         */
        open fun 显示(): 警告对话框 {
            super.show()
            return 警告对话框(上下文)
        }

    }

}

//===============================================================

/**
 * 描述：取按钮
 */
fun AlertDialog.取按钮(哪个按钮: Int) : Button = this.getButton(哪个按钮)

//===============================================================

/**
 * 描述：取列表视图
 */
val AlertDialog.列表视图 : ListView get() = this.listView

/**
 * 描述：取列表视图
 */
fun AlertDialog.取列表视图() : ListView = this.getListView()

//===============================================================

/**
 * 描述：设置警告对话框标题
 */
fun AlertDialog.置标题(标题: CharSequence) = this.setTitle(标题)

//===============================================================

/**
 * 描述：置自定义标题
 */
fun AlertDialog.置自定义标题(自定义标题视图: View) = this.setCustomTitle(自定义标题视图)

//===============================================================

/**
 * 描述：设置警告对话框内容
 */
fun AlertDialog.置消息(消息: CharSequence)  = this.setMessage(消息)

//===============================================================

/**
 * 描述：置视图
 */
fun AlertDialog.置视图(视图: View) = this.setView(视图)

/**
 * 描述：置视图
 */
fun AlertDialog.置视图(视图: View,视图间距左: Int,视图间距上: Int,视图间距右: Int,视图间距下: Int) =
    this.setView(视图,视图间距左,视图间距上,视图间距右,视图间距下)

//===============================================================

/**
 * 描述：置按钮
 */
fun AlertDialog.置按钮(哪个按钮: Int, 文本: CharSequence, 信息: Message) =
    this.setButton(哪个按钮,文本,信息)

/**
 * 描述：置按钮
 */
fun AlertDialog.置按钮(哪个按钮: Int, 文本: CharSequence, 监听器: DialogInterface.OnClickListener) =
    this.setButton(哪个按钮,文本,监听器)

//===============================================================

/**
 * 描述：置图标
 */
fun AlertDialog.置图标(@DrawableRes 资源Id: Int) = this.setIcon(资源Id)
/**
 * 描述：置图标
 */
fun AlertDialog.置图标(图标: Drawable) = this.setIcon(图标)

//===============================================================

/**
 * 描述：置图标属性
 */
fun AlertDialog.置图标属性(@AttrRes 属性Id: Int) = this.setIconAttribute(属性Id)



//===============================================================
//===============================================================


/**
 * 描述：取上下文
 */
val AlertDialog.Builder.上下文 : Context get() = this.context

/**
 * 描述：取上下文
 */
fun AlertDialog.Builder.取上下文() : Context = this.getContext()

//===============================================================

/**
 * 描述：设置警告对话框标题
 */
fun AlertDialog.Builder.置标题(@StringRes 标题Id: Int) : AlertDialog.Builder = this.setTitle(标题Id)

/**
 * 描述：设置警告对话框标题
 */
fun AlertDialog.Builder.置标题(标题: CharSequence) : AlertDialog.Builder = this.setTitle(标题)

//===============================================================

/**
 * 描述：置自定义标题
 */
fun AlertDialog.Builder.置自定义标题(自定义标题视图: View): AlertDialog.Builder = this.setCustomTitle(自定义标题视图)

//===============================================================

/**
 * 描述：设置警告对话框消息
 */
fun AlertDialog.Builder.置消息(@StringRes 信息Id: Int) : AlertDialog.Builder = this.setMessage(信息Id)

/**
 * 描述：设置警告对话框消息
 */
fun AlertDialog.Builder.置消息(消息: CharSequence) : AlertDialog.Builder = this.setMessage(消息)

//===============================================================

/**
 * 描述：置图标
 */
fun AlertDialog.Builder.置图标(@DrawableRes 图标Id: Int) : AlertDialog.Builder = this.setIcon(图标Id)

/**
 * 描述：置图标
 */
fun AlertDialog.Builder.置图标(图标: Drawable) : AlertDialog.Builder = this.setIcon(图标)

//===============================================================

/**
 * 描述：置图标属性
 */
fun AlertDialog.Builder.置图标属性(@AttrRes 属性Id: Int) : AlertDialog.Builder = this.setIconAttribute(属性Id)

//===============================================================

/**
 * 描述：置确定按钮
 */
fun AlertDialog.Builder.置确定按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setPositiveButton(文本Id,监听器)

/**
 * 描述：置确定按钮
 */
fun AlertDialog.Builder.置确定按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setPositiveButton(文本,监听器)

//===============================================================

/**
 * 描述：置确定按钮图标
 */
fun AlertDialog.Builder.置确定按钮图标(图标: Drawable): AlertDialog.Builder =
    this.setPositiveButtonIcon(图标)


//===============================================================

/**
 * 描述：置取消按钮
 */
fun AlertDialog.Builder.置取消按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setNegativeButton(文本Id,监听器)

/**
 * 描述：置取消按钮
 */
fun AlertDialog.Builder.置取消按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setNegativeButton(文本,监听器)

//===============================================================

/**
 * 描述：置取消按钮图标
 */
fun AlertDialog.Builder.置取消按钮图标(图标: Drawable): AlertDialog.Builder =
    this.setNegativeButtonIcon(图标)

//===============================================================

/**
 * 描述：置忽略按钮
 */
fun AlertDialog.Builder.置忽略按钮(@StringRes 文本Id: Int, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setNeutralButton(文本Id,监听器)
/**
 * 描述：置忽略按钮
 */
fun AlertDialog.Builder.置忽略按钮(文本: CharSequence, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setNeutralButton(文本,监听器)

//===============================================================

/**
 * 描述：置忽略按钮图标
 */
fun AlertDialog.Builder.置忽略按钮图标(图标: Drawable): AlertDialog.Builder =
    this.setNeutralButtonIcon(图标)

//===============================================================

/**
 * 描述：置可取消
 */
fun AlertDialog.Builder.置可取消(可取消: Boolean) : AlertDialog.Builder = this.setCancelable(可取消)

//===============================================================

/**
 * 描述：置取消监听器
 */
fun AlertDialog.Builder.置取消监听器(取消监听器: DialogInterface.OnCancelListener) : AlertDialog.Builder =
    this.setOnCancelListener(取消监听器)

//===============================================================


/**
 * 描述：置关闭监听器
 */
fun AlertDialog.Builder.置关闭监听器(关闭监听器: DialogInterface.OnDismissListener) : AlertDialog.Builder =
    this.setOnDismissListener(关闭监听器)

//===============================================================

/**
 * 描述：置按键监听器
 */
fun AlertDialog.Builder.置按键监听器(按键监听器: DialogInterface.OnKeyListener) : AlertDialog.Builder =
    this.setOnKeyListener(按键监听器)

//===============================================================

/**
 * 描述：置项目
 */
fun AlertDialog.Builder.置项目(@ArrayRes 项目Id: Int, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setItems(项目Id,监听器)

/**
 * 描述：置项目
 */
fun AlertDialog.Builder.置项目(项目: Array<CharSequence>, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setItems(项目,监听器)

//===============================================================

/**
 * 描述：置适配器
 */
fun AlertDialog.Builder.置适配器(适配器: ListAdapter, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setAdapter(适配器,监听器)

//===============================================================

/**
 * 描述：置光标
 */
fun AlertDialog.Builder.置光标(光标: Cursor, 监听器: DialogInterface.OnClickListener, 标签列: String) : AlertDialog.Builder =
    this.setCursor(光标,监听器,标签列)

//===============================================================

/**
 * 描述：置多选项目
 */
fun AlertDialog.Builder.置多选项目(@ArrayRes 项目Id: Int, 已选中项目: BooleanArray, 监听器: DialogInterface.OnMultiChoiceClickListener) : AlertDialog.Builder =
    this.setMultiChoiceItems(项目Id,已选中项目,监听器)

/**
 * 描述：置多选项目
 */
fun AlertDialog.Builder.置多选项目(项目: Array<CharSequence>, 已选中项目: BooleanArray, 监听器: DialogInterface.OnMultiChoiceClickListener) : AlertDialog.Builder =
    this.setMultiChoiceItems(项目,已选中项目,监听器)

/**
 * 描述：置多选项目
 */
fun AlertDialog.Builder.置多选项目(光标 : Cursor, 是否选中列: String, 标签列: String, 监听器: DialogInterface.OnMultiChoiceClickListener) : AlertDialog.Builder =
    this.setMultiChoiceItems(光标,是否选中列,标签列,监听器)

//===============================================================

/**
 * 描述：置单选项目
 */
fun AlertDialog.Builder.置单选项目(@ArrayRes 项目Id: Int, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setSingleChoiceItems(项目Id,已选中项目,监听器)

/**
 * 描述：置单选项目
 */
fun AlertDialog.Builder.置单选项目(光标 : Cursor, 已选中项目: Int, 标签列: String, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setSingleChoiceItems(光标,已选中项目,标签列,监听器)

/**
 * 描述：置单选项目
 */
fun AlertDialog.Builder.置单选项目(项目: Array<CharSequence>, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setSingleChoiceItems(项目,已选中项目,监听器)

/**
 * 描述：置单选项目
 */
fun AlertDialog.Builder.置单选项目(适配器: ListAdapter, 已选中项目: Int, 监听器: DialogInterface.OnClickListener) : AlertDialog.Builder =
    this.setSingleChoiceItems(适配器,已选中项目,监听器)

//===============================================================

/**
 * 描述：置项目选中监听器
 */
fun AlertDialog.Builder.置项目选中监听器(监听器: AdapterView.OnItemSelectedListener) : AlertDialog.Builder =
    this.setOnItemSelectedListener(监听器)

//===============================================================

/**
 * 描述：置视图
 */
fun AlertDialog.Builder.置视图(布局资源Id: Int) : AlertDialog.Builder =
    this.setView(布局资源Id)

/**
 * 描述：置视图
 */
fun AlertDialog.Builder.置视图(视图: View) : AlertDialog.Builder =
    this.setView(视图)

//===============================================================

/**
 * 描述：置视图
 */
@SuppressLint("RestrictedApi")
fun AlertDialog.Builder.置视图(视图: View, 视图左侧间距: Int, 视图顶部间距: Int, 视图右侧间距: Int, 视图底部间距: Int) : AlertDialog.Builder =
    this.setView(视图, 视图左侧间距, 视图顶部间距, 视图右侧间距, 视图底部间距)

//===============================================================

@SuppressLint("RestrictedApi")
fun AlertDialog.Builder.置测量时回收启用(是否启用: Boolean) : AlertDialog.Builder =
    this.setRecycleOnMeasureEnabled(是否启用)


//===============================================================

/**
 * 描述：创建警告对话框
 */
fun AlertDialog.Builder.创建(): AlertDialog = this.create()

//===============================================================

/**
 * 描述：显示警告对话框
 */
fun AlertDialog.Builder.显示(): AlertDialog = this.show()

//===============================================================
