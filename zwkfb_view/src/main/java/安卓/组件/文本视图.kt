package 安卓.组件

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.BlendMode
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.Rect
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.os.Parcel
import android.os.Parcelable
import android.text.Editable
import android.text.Highlights
import android.text.InputFilter
import android.text.Layout
import android.text.PrecomputedText
import android.text.Spannable
import android.text.TextDirectionHeuristic
import android.text.TextPaint
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.KeyListener
import android.text.method.MovementMethod
import android.text.method.TransformationMethod
import android.text.style.URLSpan
import android.util.AttributeSet
import android.view.ActionMode
import android.view.ContentInfo
import android.view.DragEvent
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.PointerIcon
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import android.view.autofill.AutofillValue
import android.view.inputmethod.CompletionInfo
import android.view.inputmethod.CorrectionInfo
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.ExtractedText
import android.view.inputmethod.ExtractedTextRequest
import android.view.inputmethod.InputConnection
import android.view.textclassifier.TextClassifier
import android.view.translation.ViewTranslationRequest
import android.widget.RemoteViews.RemoteView
import android.widget.Scroller
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.FloatRange
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.annotation.XmlRes
import androidx.core.widget.TextViewCompat
import java.util.Locale
import java.util.function.Consumer

/**
 * 创建时间：2025年11月18日.
 *
 * 描述：文本视图
 *
 * 版本：0.0.7
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
@RemoteView
open class 文本视图 : TextView {

    constructor(context: Context?) : this(context , null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context, attrs, defStyleAttr,0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int,
    ) : super(context, attrs, defStyleAttr, defStyleRes)

//    private var 文本 : CharSequence = ""
//    private fun init(attrs: AttributeSet?) {
//        val a = context.obtainStyledAttributes(attrs,R.styleable.文本视图)
//        文本 = a.getString(R.styleable.文本视图_文本) ?: ""
//        a.recycle()
//
//        setText(this.文本)
//
//    }

    companion object{

        /**
         * 文本视图 不会自动调整文字大小（默认情况下）。
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 自动_大小_文本_类型_无 = AUTO_SIZE_TEXT_TYPE_NONE

        /**
         * 文本视图 会同时在水平和垂直方向上缩放文字大小，以适应容器。
         */
        @RequiresApi(Build.VERSION_CODES.O)
        const val 自动_大小_文本_类型_一致 = AUTO_SIZE_TEXT_TYPE_UNIFORM

        /**
         * 聚焦搜索结果索引 无
         */
        @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
        const val 聚焦_搜索_结果_索引_无 = FOCUSED_SEARCH_RESULT_INDEX_NONE

    }

    interface 编辑器动作监听器 : OnEditorActionListener{
        override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            return 编辑器动作(v, actionId, event)
        }

        open fun 编辑器动作(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
            return onEditorAction(v, actionId, event)
        }
    }

    enum class 缓冲区类型{
        正常,可伸缩的,可编辑
    }

    object 已保存状态{
        /**
         * 创建者
         * @return 创建者
         */
        val 创造者 = SavedState.CREATOR
    }

}

//==============================================================
//==============================================================

/**
 * 描述：置自动大小文本类型为默认
 * @param 自动大小文本类型 自动大小文本类型
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.置自动大小文本类型为默认(@TextViewCompat.AutoSizeTextType 自动大小文本类型: Int) =
    this.setAutoSizeTextTypeWithDefaults(自动大小文本类型)

//==============================================================

/**
 * 描述：置自动大小文本类型为一致
 * @param 自动大小最小文本大小 自动大小最小文本大小
 * @param 自动大小最大文本大小 自动大小最大文本大小
 * @param 自动大小步长粒度 自动大小步长粒度
 * @param 单位 单位
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.置自动大小文本类型带配置统一(自动大小最小文本大小: Int, 自动大小最大文本大小: Int, 自动大小步长粒度: Int, 单位: Int) =
    this.setAutoSizeTextTypeUniformWithConfiguration(自动大小最小文本大小,
        自动大小最大文本大小, 自动大小步长粒度, 单位)

//==============================================================

/**
 * 描述：置自动大小文本类型为一致预设大小,仅支持Android 8.0 及以上版本。
 * @param 预设大小 预设大小
 * @param 单位 单位
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.置自动大小文本类型统一预设大小(预设大小: IntArray, 单位: Int) =
    this.setAutoSizeTextTypeUniformWithPresetSizes(预设大小, 单位)

//==============================================================

/**
 * 描述：取自动大小文本类型,仅支持Android 8.0 及以上版本。
 * @return 自动大小文本类型
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动大小文本类型(): Int = this.getAutoSizeTextType()

//==============================================================

/**
 * 描述：取自动大小步长粒度,仅支持Android 8.0 及以上版本。
 * @return 自动大小步长粒度
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动大小步长粒度(): Int = this.getAutoSizeStepGranularity()

//==============================================================

/**
 * 描述：取自动大小最小文本大小,仅支持Android 8.0 及以上版本。
 * @return 自动大小最小文本大小
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动大小最小文本大小(): Int = this.getAutoSizeMinTextSize()

//==============================================================

/**
 * 描述：取自动大小最大文本大小,仅支持Android 8.0 及以上版本。
 * @return 自动大小最大文本大小
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动大小最大文本大小(): Int = this.getAutoSizeMaxTextSize()

//==============================================================

/**
 * 描述：取自动大小文本可用大小,仅支持Android 8.0 及以上版本。
 * @return 自动大小文本可用大小
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动大小文本可用大小(): IntArray = this.getAutoSizeTextAvailableSizes()

//==============================================================

/**
 * 描述：置启用
 * @param 已启用 是否启用
 */
fun TextView.置启用(已启用: Boolean) = this.setEnabled(已启用)

//==============================================================

/**
 * 描述：置字体样式,仅支持Android 8.0 及以上版本。
 * @param 字体样式 字体样式
 * @param 样式 样式
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.置字体样式(字体样式: Typeface?, 样式: Int)
    = this.setTypeface(字体样式,样式)

//==============================================================

/**
 * 描述：文本
 * @param 文本 显示的文本。
 * @return 文本视图所显示的文本。
 */
var TextView.文本: CharSequence
    get() = this.text
    set(文本) { this.text = 文本 }

/**
 * 描述：取文本
 * @return 文本视图所显示的文本。
 */
fun TextView.取文本(): CharSequence = this.getText()

//==============================================================

/**
 * 描述：取文本长度
 * @return 文本长度
 */
fun TextView.长度(): Int = this.length()

//==============================================================

/**
 * 描述：取可编辑文本
 * @return 可编辑文本
 */
fun TextView.取可编辑文本(): Editable = this.getEditableText()

//==============================================================

/**
 * 描述：取行高
 * @return 行高
 */
fun TextView.取行高(): Int = this.getLineHeight()

//==============================================================

/**
 * 描述：取布局
 * @return 布局
 */
fun TextView.取布局(): Layout = this.getLayout()

//==============================================================

/**
 * 描述：取按键监听器
 * @return 按键监听器
 */
fun TextView.取按键监听器(): KeyListener = this.getKeyListener()

//==============================================================

/**
 * 描述：置按键监听器
 * @param 输入 按键监听器
 */
fun TextView.置按键监听器(输入: KeyListener) = this.setKeyListener(输入)

//==============================================================

/**
 * 描述：取移动方法
 * @return 移动方法
 */
fun TextView.取移动方法(): MovementMethod = this.getMovementMethod()

//==============================================================

/**
 * 描述：置移动方法
 * @param 移动 移动方法
 */
fun TextView.置移动方法(移动: MovementMethod) = this.setMovementMethod(移动)

//==============================================================

/**
 * 描述：取转换方法
 * @return 转换方法
 */
fun TextView.取转换方法(): TransformationMethod = this.getTransformationMethod()

//==============================================================
/**
 * 描述：置转换方法
 * @param 方法 转换方法
 */
fun TextView.置转换方法(方法: TransformationMethod) = this.setTransformationMethod(方法)

//==============================================================

/**
 * 描述：取复合内边距上
 * @return 复合内边距上
 */
fun TextView.取复合内边距上(): Int = this.getCompoundPaddingTop()

//==============================================================

/**
 * 描述：取复合内边距下
 * @return 复合内边距下
 */
fun TextView.取复合内边距下(): Int = this.getCompoundPaddingBottom()

//==============================================================

/**
 * 描述：取复合内边距左
 * @return 复合内边距左
 */
fun TextView.取复合内边距左(): Int = this.getCompoundPaddingLeft()

//==============================================================

/**
 * 描述：取复合内边距右
 * @return 复合内边距右
 */
fun TextView.取复合内边距右(): Int = this.getCompoundPaddingRight()

//==============================================================

/**
 * 描述：取复合内边距开始
 * @return 复合内边距开始
 */
fun TextView.取复合内边距开始(): Int = this.getCompoundPaddingStart()

//==============================================================

/**
 * 描述：取复合内边距结束
 * @return 复合内边距结束
 */
fun TextView.取复合内边距结束(): Int = this.getCompoundPaddingEnd()

//==============================================================

/**
 * 描述：取扩展内边距上
 * @return 扩展内边距上
 */
fun TextView.取扩展内边距上(): Int = this.getExtendedPaddingTop()

//==============================================================

/**
 * 描述：取扩展内边距下
 * @return 扩展内边距下
 */
fun TextView.取扩展内边距下(): Int = this.getExtendedPaddingBottom()

//==============================================================

/**
 * 描述：取总的内边距左
 * @return 总的内边距左
 */
fun TextView.取总的内边距左(): Int = this.getTotalPaddingLeft()

//==============================================================

/**
 * 描述：取总的内边距右
 * @return 总的内边距右
 */
fun TextView.取总的内边距右(): Int = this.getTotalPaddingRight()

//==============================================================

/**
 * 描述：取总的内边距开始
 * @return 总的内边距开始
 */
fun TextView.取总的内边距开始(): Int = this.getTotalPaddingStart()

//==============================================================

/**
 * 描述：取总的内边距结束
 * @return 总的内边距结束
 */
fun TextView.取总的内边距结束(): Int = this.getTotalPaddingEnd()

//==============================================================

/**
 * 描述：取总的内边距上
 * @return 总的内边距上
 */
fun TextView.取总的内边距上(): Int = this.getTotalPaddingTop()

//==============================================================

/**
 * 描述：取总的内边距下
 * @return 总的内边距下
 */
fun TextView.取总的内边距下(): Int = this.getTotalPaddingBottom()

//==============================================================

/**
 * 描述：置复合可绘制资源
 * @param 左 左可绘制资源
 * @param 上 上可绘制资源
 * @param 右 右可绘制资源
 * @param 下 下可绘制资源
 */
fun TextView.置复合可绘制资源(左: Drawable?, 上: Drawable?, 右: Drawable?, 下: Drawable?)
    = this.setCompoundDrawables(左, 上, 右, 下)

//==============================================================

/**
 * 描述：置复合可绘制资源固有尺寸作为边界
 * @param 左 左可绘制资源
 * @param 上 上可绘制资源
 * @param 右 右可绘制资源
 * @param 下 下可绘制资源
 */
fun TextView.置复合可绘制资源固有尺寸作为边界(@DrawableRes 左: Int, @DrawableRes 上: Int, @DrawableRes 右: Int, @DrawableRes 下: Int)
        = this.setCompoundDrawablesWithIntrinsicBounds(左, 上, 右, 下)

/**
 * 描述：置复合可绘制资源固有尺寸作为边界
 * @param 左 左可绘制资源
 * @param 上 上可绘制资源
 * @param 右 右可绘制资源
 * @param 下 下可绘制资源
 */
fun TextView.置复合可绘制资源固有尺寸作为边界(左: Drawable?, 上: Drawable?, 右: Drawable?, 下: Drawable?)
        = this.setCompoundDrawablesWithIntrinsicBounds(左, 上, 右, 下)

//==============================================================

/**
 * 描述：置复合可绘制资源相对
 * @param 开始 开始可绘制资源
 * @param 上 上可绘制资源
 * @param 结束 结束可绘制资源
 * @param 下 下可绘制资源
 */
fun TextView.置复合可绘制对象相对(开始: Drawable?, 上: Drawable?, 结束: Drawable?, 下: Drawable?)
        = this.setCompoundDrawablesRelative(开始, 上, 结束, 下)

//==============================================================
/**
 * 描述：置复合可绘制对象固有尺寸作为边界
 * @param 开始 开始可绘制资源
 * @param 上 上可绘制资源
 * @param 结束 结束可绘制资源
 * @param 下 下可绘制资源
 */
fun TextView.置复合可绘制对象固有尺寸作为边界(@DrawableRes 开始: Int, @DrawableRes 上: Int, @DrawableRes 结束: Int, @DrawableRes 下: Int)
        = this.setCompoundDrawablesRelativeWithIntrinsicBounds(开始, 上, 结束, 下)

/**
 * 描述：置复合可绘制对象固有尺寸作为边界
 * @param 开始 开始可绘制资源
 * @param 上 上可绘制资源
 * @param 结束 结束可绘制资源
 * @param 下 下可绘制资源
 */
fun TextView.置复合可绘制对象固有尺寸作为边界(开始: Drawable?, 上: Drawable?, 结束: Drawable?, 下: Drawable?)
        = this.setCompoundDrawablesRelativeWithIntrinsicBounds(开始, 上, 结束, 下)

//==============================================================
/**
 * 描述：取复合可绘制对象
 * @return 复合可绘制对象
 */
fun TextView.取复合可绘制对象(): Array<Drawable?> = this.getCompoundDrawables()

//==============================================================
/**
 * 描述：取复合可绘制对象相对
 * @return 复合可绘制对象相对
 */
fun TextView.取复合可绘制对象相对(): Array<Drawable?> = this.getCompoundDrawablesRelative()

//==============================================================
/**
 * 描述：置复合可绘制对象内边距
 * @param 填充 内边距
 */
fun TextView.置复合可绘制对象内边距(填充: Int) = this.setCompoundDrawablePadding(填充)

/**
 * 描述：取复合可绘制对象内边距
 * @return 复合可绘制对象内边距
 */
fun TextView.取复合可绘制对象内边距(): Int = this.getCompoundDrawablePadding()

//==============================================================
/**
 * 描述：置复合可绘制对象色调列表
 * @param 色调 色调列表
 */
@SuppressLint("UseCompatTextViewDrawableApis")
fun TextView.置复合可绘制对象色调列表(色调: ColorStateList?) = this.setCompoundDrawableTintList(色调)
/**
 * 描述：取复合可绘制对象色调列表
 * @return 复合可绘制对象色调列表
 */
fun TextView.取复合可绘制对象色调列表(): ColorStateList? = this.getCompoundDrawableTintList()

//==============================================================

/**
 * 描述：置复合可绘制对象色调模式
 * @param 色调 色调模式
 */
@SuppressLint("UseCompatTextViewDrawableApis")
fun TextView.置复合可绘制对象色调模式(色调: PorterDuff.Mode?) = this.setCompoundDrawableTintMode(色调)

/**
 * 描述：取复合可绘制对象色调模式
 * @return 复合可绘制对象色调模式
 */
fun TextView.取复合可绘制对象色调模式(): PorterDuff.Mode? = this.getCompoundDrawableTintMode()

//==============================================================
/**
 * 描述：置复合可绘制对象色调混合模式
 * @param 色调 色调混合模式
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置复合可绘制对象色调混合模式(色调: BlendMode?) = this.setCompoundDrawableTintBlendMode(色调)
/**
 * 描述：取复合可绘制对象色调混合模式
 * @return 复合可绘制对象色调混合模式
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.取复合可绘制对象色调混合模式(): BlendMode? = this.getCompoundDrawableTintBlendMode()

//==============================================================

/**
 * 描述：置内边距
 * @param 左 左内边距
 * @param 上 上内边距
 * @param 右 右内边距
 * @param 下 下内边距
 */
fun TextView.置内边距(左: Int, 上: Int, 右: Int, 下: Int) =
    this.setPadding(左, 上, 右, 下)

//==============================================================

/**
 * 描述：置内边距相对
 * @param 开始 左内边距
 * @param 上 上内边距
 * @param 结束 右内边距
 * @param 下 下内边距
 */
fun TextView.置内边距相对(开始: Int, 上: Int, 结束: Int, 下: Int) =
    this.setPaddingRelative(开始, 上, 结束, 下)

//==============================================================

/**
 * 描述：置首行基线到顶部高度
 * @param 首行基线到顶部高度 首行基线到顶部高度
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.置首行基线到顶部高度(首行基线到顶部高度: Int) =
    this.setFirstBaselineToTopHeight(首行基线到顶部高度)

/**
 * 描述：取首行基线到顶部高度
 * @return 首行基线到顶部高度
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.取首行基线到顶部高度(): Int = this.getFirstBaselineToTopHeight()

//==============================================================

/**
 * 描述：置末行基线到底部高度
 * @param 末行基线到底部高度 末行基线到底部高度
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.置末行基线到底部高度(末行基线到底部高度: Int) =
    this.setLastBaselineToBottomHeight(末行基线到底部高度)

/**
 * 描述：取末行基线到底部高度
 * @return 末行基线到底部高度
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.取末行基线到底部高度(): Int = this.getLastBaselineToBottomHeight()

//==============================================================

/**
 * 描述：取自动链接掩码
 * @return 自动链接掩码
 */
fun TextView.取自动链接掩码(): Int = this.getAutoLinkMask()

//==============================================================
/**
 * 描述：置文本选择手柄
 * @param 文本选择手柄 文本选择手柄
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本选择手柄(文本选择手柄: Drawable) = this.setTextSelectHandle(文本选择手柄)
/**
 * 描述：置文本选择手柄
 * @param 文本选择手柄 文本选择手柄
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本选择手柄(@DrawableRes 文本选择手柄: Int) = this.setTextSelectHandle(文本选择手柄)
/**
 * 描述：取文本选择手柄
 * @return 文本选择手柄
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.取文本选择手柄(): Drawable? = this.getTextSelectHandle()

//==============================================================
/**
 * 描述：置文本选择手柄左
 * @param 文本选择手柄左 文本选择手柄左
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本选择手柄左(文本选择手柄左: Drawable) = this.setTextSelectHandleLeft(文本选择手柄左)

/**
 * 描述：置文本选择手柄左
 * @param 文本选择手柄左 文本选择手柄左
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本选择手柄左(@DrawableRes 文本选择手柄左: Int) = this.setTextSelectHandleLeft(文本选择手柄左)

/**
 * 描述：取文本选择手柄左
 * @return 文本选择手柄左
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.取文本选择手柄左(): Drawable? = this.getTextSelectHandleLeft()

//==============================================================
/**
 * 描述：置文本选择手柄右
 * @param 文本选择手柄右 文本选择手柄右
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本选择手柄右(文本选择手柄右: Drawable) = this.setTextSelectHandleRight(文本选择手柄右)
/**
 * 描述：置文本选择手柄右
 * @param 文本选择手柄右 文本选择手柄右
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本选择手柄右(@DrawableRes 文本选择手柄右: Int) = this.setTextSelectHandleRight(文本选择手柄右)
/**
 * 描述：取文本选择手柄右
 * @return 文本选择手柄右
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.取文本选择手柄右(): Drawable? = this.getTextSelectHandleRight()

//==============================================================
/**
 * 描述：置文本光标可绘制对象
 * @param 文本光标可绘制对象 文本光标可绘制对象
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本光标可绘制对象(文本光标可绘制对象: Drawable?) = this.setTextCursorDrawable(文本光标可绘制对象)
/**
 * 描述：置文本光标可绘制对象
 * @param 文本光标可绘制对象 文本光标可绘制对象
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.置文本光标可绘制对象(@DrawableRes 文本光标可绘制对象: Int) = this.setTextCursorDrawable(文本光标可绘制对象)
/**
 * 描述：取文本光标可绘制对象
 * @return 文本光标可绘制对象
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.取文本光标可绘制对象(): Drawable? = this.getTextCursorDrawable()

//==============================================================

/**
 * 描述：置文本外观
 * @param 资源Id 资源Id
 */
fun TextView.置文本外观(@StyleRes 资源Id: Int) = this.setTextAppearance(资源Id)
/**
 * 描述：置文本外观
 * @param 上下文 上下文
 * @param 资源Id 资源Id
 */
fun TextView.置文本外观(上下文: Context, @StyleRes 资源Id: Int) =
    this.setTextAppearance(上下文, 资源Id)

//==============================================================

/**
 * 描述：取文本语言环境
 * @return 文本语言环境
 */
fun TextView.取文本语言环境(): Locale = this.getTextLocale()

//==============================================================
/**
 * 描述：取文本语言环境集
 * @return 文本语言环境集
 */
fun TextView.取文本语言环境集(): LocaleList = this.getTextLocales()

//==============================================================
/**
 * 描述：置文本语言环境
 * @param 语言环境 文本语言环境
 */
fun TextView.置文本语言环境(语言环境: Locale) = this.setTextLocale(语言环境)

//==============================================================
/**
 * 描述：置文本语言环境集
 * @param 语言环境集 文本语言环境集
 */
fun TextView.置文本语言环境集(语言环境集: LocaleList) = this.setTextLocales(语言环境集)

//==============================================================

/**
 * 描述：文本大小
 * @param 大小 文本大小。
 * @return 文本大小 显示的文本大小。
 */
var TextView.文本大小: Float
    get() = this.textSize
    set(大小) { this.textSize = 大小 }

/**
 * 描述：取文本大小
 * @return 文本大小 显示的文本大小。
 */
fun TextView.取文本大小(): Float = this.getTextSize()

/**
 * 描述：置文本大小
 * @param 大小 文本大小。
 */
fun TextView.置文本大小(大小: Float) = this.setTextSize(大小)

/**
 * 描述：置文本大小
 * @param 单位 字体大小单位
 * @param 大小 文本大小。
 */
fun TextView.置文本大小(单位: Int, 大小: Float) = this.setTextSize(单位, 大小)

//==============================================================
/**
 * 描述：取文本大小单位
 * @return 文本大小单位
 */
@RequiresApi(Build.VERSION_CODES.R)
fun TextView.取文本大小单位(): Int = this.getTextSizeUnit()

//==============================================================
/**
 * 描述：取文本缩放X
 * @return 文本缩放X
 */
fun TextView.取文本缩放X(): Float = this.getTextScaleX()

//==============================================================
/**
 * 描述：置文本缩放X
 * @param 大小 文本缩放X
 */
fun TextView.置文本缩放X(大小: Float) = this.setTextScaleX(大小)

//==============================================================

/**
 * 描述：置字体
 * @param 字体 字体
 */
fun TextView.置字体(字体: Typeface) = this.setTypeface(字体)

/**
 * 描述：取字体
 * @return 字体
 */
fun TextView.取字体(): Typeface? = this.getTypeface()

//==============================================================
/**
 * 描述：置优雅文本高度
 * @param 优雅 是否启用优雅文本高度
 */
fun TextView.置优雅文本高度(优雅: Boolean) = this.setElegantTextHeight(优雅)

//==============================================================

/**
 * 描述：置回退行间距
 * @param 启用 是否启用回退行间距
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.置回退行间距(启用: Boolean) = this.setFallbackLineSpacing(启用)

//==============================================================
/**
 * 描述：置使用边界作为宽度
 * @param 使用边界作为宽度 是否使用边界作为宽度
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.置使用边界作为宽度(使用边界作为宽度 : Boolean) =
    this.setUseBoundsForWidth(使用边界作为宽度)

/**
 * 描述：取使用边界作为宽度
 * @return 是否使用边界作为宽度
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.取使用边界作为宽度(): Boolean = this.getUseBoundsForWidth()

//==============================================================
/**
 * 描述：置开始悬伸的绘图偏移
 * @param 开始悬伸的绘图偏移 是否启用开始悬伸的绘图偏移
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.置开始悬伸的绘图偏移(开始悬伸的绘图偏移: Boolean) =
    this.setShiftDrawingOffsetForStartOverhang(开始悬伸的绘图偏移)

/**
 * 描述：取开始悬伸的绘图偏移
 * @return 是否启用开始悬伸的绘图偏移
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.取开始悬伸的绘图偏移(): Boolean =
    this.getShiftDrawingOffsetForStartOverhang()

//==============================================================
/**
 * 描述：置最小字体度量
 * @param 最小字体度量 最小字体度量
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.置最小字体度量(最小字体度量: Paint.FontMetrics) =
    this.setMinimumFontMetrics(最小字体度量)
/**
 * 描述：取最小字体度量
 * @return 最小字体度量
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.取最小字体度量(): Paint.FontMetrics? = this.getMinimumFontMetrics()

//==============================================================

/**
 * 描述：取是否首选用于最小使用的本地行高
 * @return 是否首选用于最小使用的本地行高
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.是否首选用于最小使用的本地行高(): Boolean  =
    this.isLocalePreferredLineHeightForMinimumUsed()
/**
 * 描述：置是否首选用于最小使用的本地行高
 * @param 标志 是否首选用于最小使用的本地行高
 */
@RequiresApi(Build.VERSION_CODES.VANILLA_ICE_CREAM)
fun TextView.置首选用于最小使用的本地行高(标志: Boolean)  =
    this.setLocalePreferredLineHeightForMinimumUsed(标志)

//==============================================================

/**
 * 描述：取是否回退行间距
 * @return 是否回退行间距
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.是否回退行间距(): Boolean = this.isFallbackLineSpacing()

//==============================================================
/**
 * 描述：取是否优雅的文本高度
 * @return 是否优雅的文本高度
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.是否优雅的文本高度(): Boolean = this.isElegantTextHeight()

//==============================================================
/**
 * 描述：取字符间距
 * @return 字符间距
 */
fun TextView.取字符间距(): Float = this.getLetterSpacing()
/**
 * 描述：置字符间距
 * @param 字符间距 字符间距
 */
fun TextView.置字符间距(字符间距: Float) = this.setLetterSpacing(字符间距)

//==============================================================

/**
 * 描述：取字体特性设置
 * @return 字体特性设置
 */
fun TextView.取字体特性设置(): String? = this.getFontFeatureSettings()

//==============================================================
/**
 * 描述：取字体可变设置
 * @return 字体可变设置
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取字体可变设置(): String? = this.getFontVariationSettings()

//==============================================================
/**
 * 描述：置换行策略
 * @param 换行策略 换行策略
 */
fun TextView.置换行策略(换行策略: Int) = this.setBreakStrategy(换行策略)
/**
 * 描述：取换行策略
 * @return 换行策略
 */
fun TextView.取换行策略(): Int = this.getBreakStrategy()

//==============================================================
/**
 * 描述：置断字频率
 * @param 断字频率 断字频率
 */
fun TextView.置断字频率(断字频率: Int) = this.setHyphenationFrequency(断字频率)
/**
 * 描述：取断字频率
 * @return 断字频率
 */
fun TextView.取断字频率(): Int = this.getHyphenationFrequency()

//==============================================================
/**
 * 描述：置换行样式
 * @param 换行样式 换行样式
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun TextView.置换行样式(换行样式: Int) = this.setLineBreakStyle(换行样式)
/**
 * 描述：取换行样式
 * @return 换行样式
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun TextView.取换行样式(): Int = this.getLineBreakStyle()

//==============================================================

 /**
 * 描述：置断词样式
 * @param 断词样式 断词样式
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun TextView.置断词样式(断词样式: Int) = this.setLineBreakWordStyle(断词样式)
/**
 * 描述：取断词样式
 * @return 断词样式
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun TextView.取断词样式(): Int = this.getLineBreakWordStyle()

//==============================================================
/**
 * 描述：取文本度量参数
 * @return 文本度量参数
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.取文本度量参数(): PrecomputedText.Params =
    this.getTextMetricsParams()
/**
 * 描述：置文本度量参数
 * @param 参数 文本度量参数
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.置文本度量参数(参数: PrecomputedText.Params) =
    this.setTextMetricsParams(参数)

//==============================================================
/**
 * 描述：置两端对齐模式
 * @param 两端对齐模式 两端对齐模式
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.置两端对齐模式(两端对齐模式: Int) = this.setJustificationMode(两端对齐模式)

//==============================================================

/**
 * 描述：取两端对齐模式
 * @return 两端对齐模式
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取两端对齐模式(): Int = this.getJustificationMode()

//==============================================================

/**
 * 描述：置字体特性设置
 * @param 字体特性设置 字体特性设置
 */
fun TextView.置字体特性设置(字体特性设置: String) =
    this.setFontFeatureSettings(字体特性设置)

//==============================================================
/**
 * 描述：置字体变体设置
 * @param 字体变体设置 字体变体设置
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.置字体变体设置(字体变体设置: String) =
    this.setFontVariationSettings(字体变体设置)

//==============================================================

/**
 * 描述：文本颜色
 * @param 颜色 显示的文本颜色。
 */
fun TextView.置文本颜色(@ColorInt 颜色: Int){ this.setTextColor(颜色) }

//==============================================================

/**
 * 描述：文本颜色
 * @param 颜色集 显示的文本颜色。
 * @return 显示的文本颜色。
 */
var TextView.文本颜色: ColorStateList
    get() = this.getTextColors()
    set(颜色集) { this.setTextColor(颜色集) }

/**
 * 描述：文本颜色
 * @param 颜色集 显示的文本颜色。
 */
fun TextView.置文本颜色(颜色集: ColorStateList) = this.setTextColor(颜色集)

/**
 * 描述：文本颜色
 * @return 显示的文本颜色。
 */
fun TextView.取文本颜色集(): ColorStateList = this.getTextColors()

//==============================================================

/**
 * 描述：光标文本颜色
 * @return 显示的光标文本颜色。
 */
fun TextView.取光标文本颜色集(): Int = this.getCurrentTextColor()

//==============================================================
/**
 * 描述：置高亮颜色
 * @param 颜色 显示的高亮颜色。
 */
fun TextView.置高亮颜色(@ColorInt 颜色: Int) = this.setHighlightColor(颜色)
/**
 * 描述：取高亮颜色
 * @return 显示的高亮颜色。
 */
fun TextView.取高亮颜色(): Int = this.getHighlightColor()

//==============================================================

/**
 * 描述：置获得焦点时显示软键盘
 * @param 显示 是否显示软键盘。
 */
fun TextView.置获得焦点时显示软键盘(显示: Boolean) = this.setShowSoftInputOnFocus(显示)

/**
 * 描述：取获得焦点时显示软键盘
 * @return 是否显示软键盘。
 */
fun TextView.取获得焦点时显示软键盘(): Boolean = this.getShowSoftInputOnFocus()

//==============================================================
/**
 * 描述：置阴影层
 * @param 半径 阴影半径。
 * @param 水平偏移 阴影水平偏移。
 * @param 垂直偏移 阴影垂直偏移。
 * @param 颜色 阴影颜色。
 */
fun TextView.置阴影层(半径: Float, 水平偏移: Float, 垂直偏移: Float, 颜色: Int) =
    this.setShadowLayer(半径, 水平偏移, 垂直偏移, 颜色)

//==============================================================

/**
 * 描述：取阴影半径
 * @return 阴影半径。
 */
fun TextView.取阴影半径(): Float = this.getShadowRadius()

//==============================================================

/**
 * 描述：取阴影水平偏移
 * @return 阴影水平偏移。
 */
fun TextView.取阴影水平偏移(): Float = this.getShadowDx()

//==============================================================
/**
 * 描述：取阴影垂直偏移
 * @return 阴影垂直偏移。
 */
fun TextView.取阴影垂直偏移(): Float = this.getShadowDy()

//==============================================================
/**
 * 描述：取阴影颜色
 * @return 阴影颜色。
 */
fun TextView.取阴影颜色(): Int = this.getShadowColor()

//==============================================================
/**
 * 描述：取画笔
 * @return 画笔。
 */
fun TextView.取画笔(): TextPaint? = this.getPaint()

//==============================================================
/**
 * 描述：置自动链接掩码
 * @param 掩码 自动链接掩码。
 */
fun TextView.置自动链接掩码(掩码: Int) = this.setAutoLinkMask(掩码)

//==============================================================
/**
 * 描述：置链接是否可单击
 * @param 是否可单击 是否可单击。
 */
fun TextView.置链接是否可单击(是否可单击: Boolean) = this.setLinksClickable(是否可单击)

/**
 * 描述：取链接是否可单击
 * @return 是否可单击。
 */
fun TextView.取链接是否可单击(): Boolean = this.getLinksClickable()

//==============================================================
/**
 * 描述：取网址集
 * @return 网址集。
 */
fun TextView.取网址集(): Array<URLSpan> = this.getUrls()

//==============================================================
/**
 * 描述：置提示文本颜色
 * @param 颜色 提示文本颜色。
 */
fun TextView.置提示文本颜色(@ColorInt 颜色: Int) = this.setHintTextColor(颜色)

/**
 * 描述：取提示文本颜色
 * @return 提示文本颜色。
 */
fun TextView.置提示文本颜色(颜色集: ColorStateList) = this.setHintTextColor(颜色集)

/**
 * 描述：取提示文本颜色
 * @return 提示文本颜色。
 */
fun TextView.取提示文本颜色集(): ColorStateList = this.getHintTextColors()

//==============================================================
/**
 * 描述：取当前提示文本颜色
 * @return 当前提示文本颜色。
 */
fun TextView.取当前提示文本颜色(): Int = this.getCurrentHintTextColor()

//==============================================================
/**
 * 描述：置链接文本颜色
 * @param 颜色 链接文本颜色。
 */
fun TextView.置链接文本颜色(@ColorInt 颜色: Int) = this.setLinkTextColor(颜色)
/**
 * 描述：置链接文本颜色
 * @param 颜色集 链接文本颜色集。
 */
fun TextView.置链接文本颜色(颜色集: ColorStateList) = this.setLinkTextColor(颜色集)

/**
 * 描述：取链接文本颜色
 * @return 链接文本颜色。
 */
fun TextView.取链接文本颜色集(): ColorStateList = this.getLinkTextColors()

//==============================================================
/**
 * 描述：置对齐方式
 * @param 对齐方式 对齐方式。
 */
fun TextView.置对齐方式(对齐方式: Int) = this.setGravity(对齐方式)

/**
 * 描述：取对齐方式
 * @return 对齐方式。
 */
fun TextView.取对齐方式(): Int = this.getGravity()

//==============================================================
/**
 * 描述：取画笔标志
 * @return 画笔标志。
 */
fun TextView.取画笔标志(): Int = this.getPaintFlags()

/**
 * 描述：置画笔标志
 * @param 标志 画笔标志。
 */
fun TextView.置画笔标志(标志: Int) = this.setPaintFlags(标志)

//==============================================================
/**
 * 描述：置水平滚动
 * @param 是否水平滚动 是否水平滚动。
 */
fun TextView.置水平滚动(是否水平滚动: Boolean) = this.setHorizontallyScrolling(是否水平滚动)

/**
 * 描述：取是否可水平滚动
 * @return 是否可水平滚动。
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.是否可水平滚动(): Boolean = this.isHorizontallyScrollable()

//==============================================================

 /**
 * 描述：置最小行数
 * @param 最小行数 最小行数。
 */
fun TextView.置最小行数(最小行数: Int) = this.setMinLines(最小行数)

/**
 * 描述：取最小行数
 * @return 最小行数。
 */
fun TextView.取最小行数(): Int = this.getMinLines()

//==============================================================
/**
 * 描述：置最小高度
 * @param 最小高度 最小高度。
 */
fun TextView.置最小高度(最小高度: Int) = this.setMinHeight(最小高度)

/**
 * 描述：取最小高度
 * @return 最小高度。
 */
fun TextView.取最小高度(): Int = this.getMinHeight()

//==============================================================

 /**
 * 描述：置最大行数
 * @param 最大行数 最大行数。
 */
fun TextView.置最大行数(最大行数: Int) = this.setMaxLines(最大行数)

/**
 * 描述：取最大行数
 * @return 最大行数。
 */
fun TextView.取最大行数(): Int = this.getMaxLines()

//==============================================================
/**
 * 描述：置最大高度
 * @param 最大高度 最大高度。
 */
fun TextView.置最大高度(最大高度: Int) = this.setMaxHeight(最大高度)

/**
 * 描述：取最大高度
 * @return 最大高度。
 */
fun TextView.取最大高度(): Int = this.getMaxHeight()

//==============================================================
/**
 * 描述：置行数
 * @param 行数 行数。
 */
fun TextView.置行数(行数: Int) = this.setLines(行数)

//==============================================================

/**
 * 描述：置高度
 * @param 高度 高度。
 */
fun TextView.置高度(高度: Int) = this.setHeight(高度)

//==============================================================
/**
 * 描述：置最小字宽数
 * @param 最小字宽数 最小字宽数。
 */
fun TextView.置最小字宽数(最小字宽数: Int) = this.setMinEms(最小字宽数)
/**
 * 描述：取最小字宽数
 * @return 最小字宽数。
 */
fun TextView.取最小字宽数(): Int = this.getMinEms()

//==============================================================
/**
 * 描述：置最小宽度
 * @param 最小宽度 最小宽度。
 */
fun TextView.置最小宽度(最小宽度: Int) = this.setMinWidth(最小宽度)
/**
 * 描述：取最小宽度
 * @return 最小宽度。
 */
fun TextView.取最小宽度(): Int = this.getMinWidth()

//==============================================================

/**
 * 描述：置最大字宽数
 * @param 最大字宽数 最大字宽数。
 */
fun TextView.置最大字宽数(最大字宽数: Int) = this.setMaxEms(最大字宽数)

/**
 * 描述：取最大字宽数
 * @return 最大字宽数。
 */
fun TextView.取最大字宽数(): Int = this.getMaxEms()

//==============================================================
/**
 * 描述：置最大宽度
 * @param 最大宽度 最大宽度。
 */
fun TextView.置最大宽度(最大宽度: Int) = this.setMaxWidth(最大宽度)

/**
 * 描述：取最大宽度
 * @return 最大宽度。
 */
fun TextView.取最大宽度(): Int = this.getMaxWidth()

//==============================================================
/**
 * 描述：置字宽数
 * @param 字宽数 字宽数。
 */
fun TextView.置字宽数(字宽数: Int) = this.setEms(字宽数)

//==============================================================
/**
 * 描述：置宽度
 * @param 宽度 宽度。
 */
fun TextView.置宽度(宽度: Int) = this.setWidth(宽度)

//==============================================================
/**
 * 描述：置行间距
 * @param 添加 添加。
 * @param 倍数 倍数。
 */
fun TextView.置行间距(添加: Float,倍数: Float) = this.setLineSpacing(添加, 倍数)

//==============================================================

/**
 * 描述：取行间距倍数
 * @return 行间距倍数。
 */
fun TextView.取行间距倍数(): Float = this.getLineSpacingMultiplier()

//==============================================================

/**
 * 描述：取额外行间距
 * @return 额外行间距。
 */
fun TextView.取额外行间距(): Float = this.getLineSpacingExtra()

//==============================================================

/**
 * 描述：置行高
 * @param 行高 行高。
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.置行高(行高: Int) = this.setLineHeight(行高)

/**
 * 描述：置行高
 * @param 单位 单位。
 * @param 行高 行高。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.置行高(单位: Int, @FloatRange(from = 0.0) 行高: Float) =
    this.setLineHeight(单位, 行高)

//==============================================================
/**
 * 描述：置高亮
 * @param 高亮 高亮。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.置高亮(高亮: Highlights) = this.setHighlights(高亮)

/**
 * 描述：取高亮
 * @return 高亮。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.取高亮(): Highlights? = this.getHighlights()

//==============================================================
/**
 * 描述：置搜索结果高亮
 * @param 范围 范围。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.置搜索结果高亮(vararg 范围: Int) = this.setSearchResultHighlights(*范围)
/**
 * 描述：取搜索结果高亮
 * @return 搜索结果高亮。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.取搜索结果高亮(): IntArray? = this.getSearchResultHighlights()

//==============================================================
/**
 * 描述：置聚焦搜索结果索引
 * @param 索引 索引。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.置聚焦搜索结果索引(索引: Int) = this.setFocusedSearchResultIndex(索引)
/**
 * 描述：取聚焦搜索结果索引
 * @return 聚焦搜索结果索引。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.取聚焦搜索结果索引(): Int = this.getFocusedSearchResultIndex()

//==============================================================
/**
 * 描述：置搜索结果高亮颜色
 * @param 颜色 颜色。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.置搜索结果高亮颜色(@ColorInt 颜色: Int) = this.setSearchResultHighlightColor(颜色)
/**
 * 描述：取搜索结果高亮颜色
 * @return 搜索结果高亮颜色。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.取搜索结果高亮颜色(): Int = this.getSearchResultHighlightColor()

//==============================================================
/**
 * 描述：置当前聚焦搜索结果的高亮颜色
 * @param 颜色 颜色。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.置当前聚焦搜索结果的高亮颜色(@ColorInt 颜色: Int) = this.setFocusedSearchResultHighlightColor(颜色)
/**
 * 描述：取当前聚焦搜索结果的高亮颜色
 * @return 当前聚焦搜索结果的高亮颜色。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.取当前聚焦搜索结果的高亮颜色(): Int = this.getFocusedSearchResultHighlightColor()

//==============================================================
/**
 * 描述：追加文本
 * @param 文本 文本。
 */
fun TextView.追加(文本: CharSequence) = this.append(文本)
/**
 * 描述：追加文本
 * @param 文本 文本。
 * @param 开始 开始。
 * @param 结束 结束。
 */
fun TextView.追加(文本: CharSequence, 开始: Int, 结束: Int) = this.append(文本, 开始, 结束)

//==============================================================
/**
 * 描述：置可绘制对象热点
 * @param x x。
 * @param y y。
 */
fun TextView.可绘制对象热点已改变(x: Float,y: Float) = this.drawableHotspotChanged(x,y)

//==============================================================

/**
 * 描述：保存实例状态回调
 * @return 保存实例状态回调。
 */
fun TextView.保存实例状态回调(): Parcelable? = this.onSaveInstanceState()

//==============================================================
/**
 * 描述：恢复实例状态
 * @param 保存 保存。
 */
fun TextView.恢复实例状态(保存: Parcelable) = this.onRestoreInstanceState(保存)

//==============================================================
/**
 * 描述：置冻结文本
 * @param 冻结文本 冻结文本。
 */
fun TextView.置冻结文本(冻结文本: Boolean) = this.setFreezesText(冻结文本)

/**
 * 描述：取冻结文本
 * @return 冻结文本。
 */
fun TextView.取冻结文本(): Boolean = this.getFreezesText()

//==============================================================

/**
 * 描述：置可编辑工厂
 * @param 工厂 可编辑工厂。
 */
fun TextView.置可编辑工厂(工厂: Editable.Factory) = this.setEditableFactory(工厂)

//==============================================================
/**
 * 描述：置可标记工厂
 * @param 工厂 可标记工厂。
 */
fun TextView.置可标记工厂(工厂: Spannable.Factory) = this.setSpannableFactory(工厂)

//==============================================================

/**
 * 描述：置文本
 * @param 文本 显示的文本。
 */
fun TextView.置文本(文本: CharSequence) = this.setText(文本)

//==============================================================
/**
 * 描述：置文本并保留状态
 * @param 文本 显示的文本。
 */
fun TextView.置文本并保留状态(文本: CharSequence) = this.setTextKeepState(文本)

//==============================================================
/**
 * 描述：置文本
 * @param 文本 显示的文本。
 * @param 类型 文本类型。
 */
fun TextView.置文本(文本: CharSequence, 类型: TextView.BufferType) = this.setText(文本,类型)

/**
 * 描述：置文本
 * @param 文本 显示的文本。
 * @param 开始 开始。
 * @param 长度 长度。
 */
fun TextView.置文本(文本: CharArray, 开始: Int, 长度: Int) = this.setText(文本,开始,长度)

//==============================================================
/**
 * 描述：置文本并保留状态
 * @param 文本 显示的文本。
 * @param 类型 文本类型。
 */
fun TextView.置文本并保留状态(文本: CharSequence, 类型: TextView.BufferType) =
    this.setTextKeepState(文本,类型)

//==============================================================
/**
 * 描述：置文本
 * @param 资源Id 字符串资源Id。
 */
fun TextView.置文本(@StringRes 资源Id: Int) = this.setText(资源Id)

/**
 * 描述：置文本
 * @param 资源Id 字符串资源Id。
 * @param 类型 文本类型。
 */
fun TextView.置文本(@StringRes 资源Id: Int, 类型: TextView.BufferType) =
    this.setText(资源Id,类型)

//==============================================================
/**
 * 描述：置提示文本
 * @param 提示文本 提示文本。
 */
fun TextView.置提示文本(提示文本: CharSequence) = this.setHint(提示文本)

//==============================================================
/**
 * 描述：置提示文本
 * @param 资源Id 字符串资源Id。
 */
fun TextView.置提示文本(@StringRes 资源Id: Int) = this.setHint(资源Id)

//==============================================================

/**
 * 描述：取提示文本
 * @return 提示文本。
 */
fun TextView.取提示文本(): CharSequence? = this.getHint()

//==============================================================

/**
 * 描述：取是否为单行
 * @return 是否为单行。
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.是否为单行(): Boolean = this.isSingleLine()

//==============================================================
/**
 * 描述：置输入类型
 * @param 类型 输入类型。
 */
fun TextView.置输入类型(类型: Int) = this.setInputType(类型)

//==============================================================

/**
 * 描述：置原始输入类型
 * @param 类型 原始输入类型。
 */
fun TextView.置原始输入类型(类型: Int) = this.setRawInputType(类型)

//==============================================================
/**
 * 描述：取自动填充提示
 * @return 自动填充提示。
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动填充提示(): Array<String>? = this.getAutofillHints()

//==============================================================

/**
 * 描述：取输入类型
 * @return 输入类型。
 */
fun TextView.取输入类型(): Int = this.getInputType()

//==============================================================
/**
 * 描述：置输入法选项
 * @param 选项 输入法选项。
 */
fun TextView.置输入法选项(选项: Int) = this.setImeOptions(选项)

/**
 * 描述：取输入法选项
 * @return 输入法选项。
 */
fun TextView.取输入法选项(): Int = this.getImeOptions()

//==============================================================
/**
 * 描述：置输入法动作标签
 * @param 标签 输入法动作标签。
 * @param 动作Id 输入法动作Id。
 */
fun TextView.置输入法动作标签(标签: CharSequence, 动作Id: Int) =
    this.setImeActionLabel(标签,动作Id)
/**
 * 描述：取输入法动作标签
 * @param 动作Id 输入法动作Id。
 * @return 输入法动作标签。
 */
fun TextView.取输入法动作标签(): CharSequence? = this.getImeActionLabel()

/**
 * 描述：取输入法动作Id
 * @return 输入法动作Id。
 */
fun TextView.取输入法动作Id(): Int = this.getImeActionId()

//==============================================================
/**
 * 描述：置编辑器动作监听器
 * @param 监听器 编辑器动作监听器。
 */
fun TextView.置编辑器动作监听器(监听器: TextView.OnEditorActionListener) =
    this.setOnEditorActionListener(监听器)

//==============================================================

/**
 * 描述：编辑器动作回调
 * @param 动作码 动作码。
 * @return 是否处理。
 */
fun TextView.编辑器动作回调(动作码: Int) = this.onEditorAction(动作码)

//==============================================================
/**
 * 描述：置私有输入法选项
 * @param 类型 私有输入法选项类型。
 */
fun TextView.置私有输入法选项(类型: String) = this.setPrivateImeOptions(类型)

/**
 * 描述：取私有输入法选项
 * @return 私有输入法选项。
 */
fun TextView.取私有输入法选项(): String? = this.getPrivateImeOptions()

//==============================================================

 /**
 * 描述：置输入附加信息
 * @param xml资源Id XML资源Id。
 */
fun TextView.置输入附加信息(@XmlRes xml资源Id: Int) = this.setInputExtras(xml资源Id)
/**
 * 描述：取输入附加信息
 * @param 创建 是否创建新的 Bundle。
 * @return 输入附加信息。
 */
fun TextView.取输入附加信息(创建: Boolean) = this.getInputExtras(创建)

//==============================================================
/**
 * 描述：置输入法提示区域设置
 * @param 提示区域设置 输入法提示区域设置。
 */
fun TextView.置输入法提示区域设置(提示区域设置: LocaleList?) = this.setImeHintLocales(提示区域设置)

/**
 * 描述：取输入法提示区域设置
 * @return 输入法提示区域设置。
 */
fun TextView.取输入法提示区域设置(): LocaleList? = this.getImeHintLocales()

//==============================================================

 /**
 * 描述：取错误信息
 * @return 错误信息。
 */
fun TextView.取错误信息() = this.getError()

/**
 * 描述：置错误信息
 * @param 错误信息 错误信息。
 */
fun TextView.置错误信息(错误信息: CharSequence?) = this.setError(错误信息)

//==============================================================

 /**
 * 描述：置输入过滤器
 * @param 过滤器 输入过滤器。
 */
fun TextView.置过滤器(过滤器: Array<InputFilter>) = this.setFilters(过滤器)

/**
 * 描述：取输入过滤器
 * @return 输入过滤器。
 */
fun TextView.取过滤器(): Array<InputFilter>? = this.getFilters()

//==============================================================
/**
 * 描述：绘制前回调
 * @return 是否继续绘制。
 */
fun TextView.绘制前回调(): Boolean = this.onPreDraw()

//==============================================================
/**
 * 描述：屏幕状态已更改回调
 * @param 屏幕状态 屏幕状态。
 */
fun TextView.屏幕状态已更改回调(屏幕状态: Int) = this.onScreenStateChanged(屏幕状态)

//==============================================================
/**
 * 描述：将可绘制对象立即跳转到当前状态
 */
fun TextView.将可绘制对象立即跳转到当前状态() = this.jumpDrawablesToCurrentState()

//==============================================================
/**
 * 描述：使可绘制对象失效
 * @param 绘制对象 可绘制对象。
 */
fun TextView.使可绘制对象失效(绘制对象 : Drawable) = this.invalidateDrawable(绘制对象)

//==============================================================
/**
 * 描述：是否存在重叠渲染
 * @return 是否存在重叠渲染。
 */
fun TextView.是否存在重叠渲染(): Boolean = this.hasOverlappingRendering()

//==============================================================
/**
 * 描述：是否文本可选
 * @return 是否文本可选。
 */

fun TextView.是否文本可选(): Boolean = this.isTextSelectable()

//==============================================================
/**
 * 描述：置文本是否可选
 * @param 可选 是否可选。
 */
fun TextView.置文本是否可选(可选: Boolean) = this.setTextIsSelectable(可选)

//==============================================================

 /**
 * 描述：取当前获得焦点的区域
 * @param 区域 区域。
 */
fun TextView.取焦点区域(区域: Rect) = this.getFocusedRect(区域)

//==============================================================
/**
 * 描述：取行数
 * @return 行数。
 */
fun TextView.取行数(): Int = this.getLineCount()

//==============================================================
/**
 * 描述：取行边界
 * @param 行 行索引。
 * @param 边界 边界。
 * @return 边界。
 */
fun TextView.取行边界(行: Int, 边界: Rect): Int = this.getLineBounds(行, 边界)

//==============================================================

 /**
 * 描述：取基线位置
 * @return 基线位置。
 */
fun TextView.取基线位置(): Int = this.getBaseline()

//==============================================================
/**
 * 描述：解析指针图标回调
 * @param 事件 事件。
 * @param 指针索引 指针索引。
 * @return 指针图标。
 */
fun TextView.解析指针图标回调(事件: MotionEvent ,指针索引: Int): PointerIcon =
    this.onResolvePointerIcon(事件,指针索引)

//==============================================================
/**
 * 描述：在输入法处理前按键回调
 * @param 按键代码 按键代码。
 * @param 事件 事件。
 * @return 是否继续处理。
 */
fun TextView.在输入法处理前按键(按键代码: Int, 事件: KeyEvent): Boolean =
    this.onKeyPreIme(按键代码, 事件)

//==============================================================
/**
 * 描述：按键按下回调
 * @param 按键代码 按键代码。
 * @param 事件 事件。
 * @return 是否继续处理。
 */
fun TextView.按键按下回调(按键代码: Int, 事件: KeyEvent): Boolean =
    this.onKeyDown(按键代码, 事件)

//==============================================================
/**
 * 描述：连续按键回调
 * @param 按键代码 按键代码。
 * @param 重复次数 重复次数。
 * @param 事件 事件。
 * @return 是否继续处理。
 */
fun TextView.连续按键回调(按键代码: Int,重复次数: Int, 事件: KeyEvent): Boolean =
    this.onKeyMultiple(按键代码,重复次数, 事件)

//==============================================================
/**
 * 描述：按键抬起回调
 * @param 按键代码 按键代码。
 * @param 事件 事件。
 * @return 是否继续处理。
 */
fun TextView.按键抬起回调(按键代码: Int, 事件: KeyEvent): Boolean =
    this.onKeyUp(按键代码, 事件)

//==============================================================
/**
 * 描述：检查是否为文本编辑器
 * @return 是否为文本编辑器。
 */
fun TextView.检查是否为文本编辑器(): Boolean = this.onCheckIsTextEditor()

//==============================================================
/**
 * 描述：创建输入连接回调
 * @param 输出属性 输出属性。
 * @return 输入连接。
 */
fun TextView.创建输入连接回调(输出属性: EditorInfo): InputConnection =
    this.onCreateInputConnection(输出属性)

//==============================================================
/**
 * 描述：提取文本回调
 * @param 请求 请求。
 * @param 输出文本 输出文本。
 * @return 是否继续处理。
 */
fun TextView.提取文本(请求: ExtractedTextRequest, 输出文本: ExtractedText): Boolean =
    this.extractText(请求,输出文本)

//==============================================================

 /**
 * 描述：置已提取文本回调
 * @param 文本 文本。
 */
fun TextView.置已提取文本(文本: ExtractedText?) = this.setExtractedText(文本)

//==============================================================

 /**
 * 描述：提交自动补全回调
 * @param 文本 文本。
 */
fun TextView.提交自动补全回调(文本: CompletionInfo) = this.onCommitCompletion(文本)

//==============================================================
/**
 * 描述：提交更正回调
 * @param 信息 信息。
 */
fun TextView.提交更正回调(信息: CorrectionInfo) = this.onCommitCorrection(信息)

//==============================================================
/**
 * 描述：开始批量编辑回调
 */
/**
 * 描述：开始批量编辑回调
 */
fun TextView.开始批量编辑() = this.beginBatchEdit()

//==============================================================

/**
 * 描述：结束批量编辑回调
 */
fun TextView.结束批量编辑() = this.endBatchEdit()

//==============================================================

 /**
 * 描述：开始批量编辑回调
 */
fun TextView.开始批量编辑回调() = this.onBeginBatchEdit()

//==============================================================
/**
 * 描述：结束批量编辑回调
 */
fun TextView.结束批量编辑回调() = this.onEndBatchEdit()

//==============================================================

/**
 * 描述：处理私有输入法命令回调
 * @param 动作 动作。
 * @param 数据 数据。
 * @return 是否继续处理。
 */
fun TextView.处理私有输入法命令回调(动作: String?, 数据: Bundle?) =
    this.onPrivateIMECommand(动作, 数据)

//==============================================================
/**
 * 描述：置包含字体间距回调
 * @param 包含填充 是否包含填充。
 */
fun TextView.置包含字体间距(包含填充: Boolean) = this.setIncludeFontPadding(包含填充)

/**
 * 描述：取包含字体间距回调
 * @return 是否包含填充。
 */
fun TextView.取包含字体间距(): Boolean = this.getIncludeFontPadding()

//==============================================================
/**
 * 描述：将点显示在视图回调
 * @param 偏移 偏移。
 * @return 是否成功。
 */
fun TextView.将点显示在视图(偏移: Int): Boolean = this.bringPointIntoView(偏移)

/**
 * 描述：将点显示在视图回调
 * @param 偏移 偏移。
 * @param 请求不聚焦的矩形 是否请求不聚焦的矩形。
 * @return 是否成功。
 */
@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
fun TextView.将点显示在视图(偏移: Int, 请求不聚焦的矩形: Boolean): Boolean =
    this.bringPointIntoView(偏移, 请求不聚焦的矩形)

//==============================================================

 /**
 * 描述：将光标移动到可见偏移回调
 * @return 是否成功。
 */
fun TextView.将光标移动到可见偏移(): Boolean = this.moveCursorToVisibleOffset()

//==============================================================
/**
 * 描述：计算滚动回调
 */
fun TextView.计算滚动() = this.computeScroll()

//==============================================================
/**
 * 描述：调试回调
 * @param 深度 深度。
 */
fun TextView.调试(深度: Int) = this.debug(深度)

//==============================================================
/**
 * 描述：取选中起始位置回调
 * @return 选中起始位置。
 */
fun TextView.取选中起始位置(): Int = this.getSelectionStart()

//==============================================================
/**
 * 描述：取选中结束位置回调
 * @return 选中结束位置。
 */
fun TextView.取选中结束位置(): Int = this.getSelectionEnd()

//==============================================================
/**
 * 描述：是否有选中内容回调
 * @return 是否有选中内容。
 */
fun TextView.是否有选中内容(): Boolean = this.hasSelection()

//==============================================================
/**
 * 描述：置为单行回调
 */
fun TextView.置为单行() = this.setSingleLine()

//==============================================================

/**
 * 描述：置全大写回调
 * @param 全大写 是否全大写。
 */
fun TextView.置全大写(全大写: Boolean) = this.setAllCaps(全大写)

//==============================================================
/**
 * 描述：是否全大写回调
 * @return 是否全大写。
 */
@RequiresApi(Build.VERSION_CODES.P)
fun TextView.是否全大写(): Boolean = this.isAllCaps()

//==============================================================
/**
 * 描述：置为单行回调
 * @param 单行 是否单行。
 */
fun TextView.置为单行(单行: Boolean) = this.setSingleLine(单行)

//==============================================================

 /**
 * 描述：置省略方式回调
 * @param 位置 位置。
 */
fun TextView.置省略方式(位置: TextUtils.TruncateAt) = this.setEllipsize(位置)

//==============================================================
/**
 * 描述：置跑马灯重复次数回调
 * @param 跑马灯限制 跑马灯限制。
 */
fun TextView.置跑马灯重复次数(跑马灯限制: Int) = this.setMarqueeRepeatLimit(跑马灯限制)
/**
 * 描述：取跑马灯重复次数回调
 * @return 跑马灯重复次数。
 */
fun TextView.取跑马灯重复次数(): Int = this.getMarqueeRepeatLimit()

//==============================================================
/**
 * 描述：取省略方式回调
 * @return 省略方式。
 */
fun TextView.取省略方式(): TextUtils.TruncateAt = this.getEllipsize()

//==============================================================
/**
 * 描述：置获得焦点时全选回调
 * @param 获得焦点时全选 是否获得焦点时全选。
 */
fun TextView.置获得焦点时全选(获得焦点时全选: Boolean) = this.setSelectAllOnFocus(获得焦点时全选)

//==============================================================

 /**
 * 描述：置光标可见回调
 * @param 可见 是否可见。
 */
fun TextView.置光标可见(可见: Boolean) = this.setCursorVisible(可见)

//==============================================================
/**
 * 描述：是否光标可见回调
 * @return 是否可见。
 */

fun TextView.是否光标可见(): Boolean = this.isCursorVisible()

//==============================================================
/**
 * 描述：添加文本变化监听器回调
 * @param 监听器 监听器。
 */
fun TextView.添加文本变化监听器(监听器: TextWatcher) = this.addTextChangedListener(监听器)

//==============================================================
/**
 * 描述：移除文本变化监听器回调
 * @param 监听器 监听器。
 */
fun TextView.移除文本变化监听器(监听器: TextWatcher) = this.removeTextChangedListener(监听器)

//==============================================================
/**
 * 描述：窗口焦点改变回调
 * @param 拥有窗口焦点 是否拥有窗口焦点。
 */
fun TextView.窗口焦点改变回调(拥有窗口焦点: Boolean) = this.onWindowFocusChanged(拥有窗口焦点)

//==============================================================

/**
 * 描述：当可见性被聚合回调
 * @param 是否可见 是否可见。
 */
fun TextView.当可见性被聚合回调(是否可见: Boolean) = this.onVisibilityAggregated(是否可见)

//==============================================================

/**
 * 描述：清除正在输入的文本回调
 */
fun TextView.清除正在输入的文本() = this.clearComposingText()

//==============================================================
/**
 * 描述：置已选中回调
 * @param 已选中 是否已选中。
 */
fun TextView.置已选中(已选中: Boolean) = this.setSelected(已选中)

//==============================================================

 /**
 * 描述：触摸事件触发回调
 * @param 事件 事件。
 * @return 是否消费事件。
 */
fun TextView.触摸事件触发回调(事件: MotionEvent): Boolean = this.onTouchEvent(事件)

//==============================================================
/**
 * 描述：通用动作事件触发回调
 * @param 事件 事件。
 * @return 是否消费事件。
 */
fun TextView.通用动作事件触发回调(事件: MotionEvent): Boolean = this.onGenericMotionEvent(事件)

//==============================================================
/**
 * 描述：显示上下文菜单回调
 * @return 是否消费事件。
 */
fun TextView.显示上下文菜单(): Boolean = this.showContextMenu()

/**
 * 描述：显示上下文菜单回调
 * @param x x坐标。
 * @param y y坐标。
 * @return 是否消费事件。
 */
fun TextView.显示上下文菜单(x:Float, y: Float): Boolean = this.showContextMenu(x, y)

//==============================================================
/**
 * 描述：是否通过触摸获取焦点并选中回调
 * @return 是否通过触摸获取焦点并选中。
 */
fun TextView.是否通过触摸获取焦点并选中(): Boolean = this.didTouchFocusSelect()

//==============================================================

/**
 * 描述：取消长按回调
 */
fun TextView.取消长按() = this.cancelLongPress()

//==============================================================
/**
 * 描述：轨迹球事件触发回调
 * @param 事件 事件。
 * @return 是否消费事件。
 */
fun TextView.轨迹球事件触发(事件: MotionEvent): Boolean = this.onTrackballEvent(事件)

//==============================================================
/**
 * 描述：置滚动器回调
 * @param 滚动器 滚动器。
 */
fun TextView.置滚动器(滚动器: Scroller?) = this.setScroller(滚动器)

//==============================================================
/**
 * 描述：查找视图包含文本回调
 * @param 输出视图 输出视图。
 * @param 已搜索 已搜索。
 * @param 标志 标志。
 */
fun TextView.查找视图包含文本(输出视图: ArrayList<View>, 已搜索: CharSequence, 标志: Int) =
    this.findViewsWithText(输出视图, 已搜索,标志)

//==============================================================
/**
 * 描述：快捷键触发回调
 * @param 按键代码 按键代码。
 * @param 事件 事件。
 * @return 是否消费事件。
 */
fun TextView.快捷键触发回调(按键代码: Int, 事件: KeyEvent): Boolean =
    this.onKeyShortcut(按键代码, 事件)

//==============================================================
/**
 * 描述：是否启用了自动手写输入回调
 * @return 是否启用了自动手写输入。
 */
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun TextView.是否启用了自动手写输入(): Boolean = this.isAutoHandwritingEnabled()

//==============================================================
/**
 * 描述：取无障碍类名回调
 * @return 无障碍类名。
 */
fun TextView.取无障碍类名(): CharSequence = this.getAccessibilityClassName()

//==============================================================
/**
 * 描述：自动填充回调
 * @param 值 值。
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.自动填充(值: AutofillValue?) = this.autofill(值)

//==============================================================
/**
 * 描述：取自动填充类型回调
 * @return 自动填充类型。
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动填充类型(): Int = this.getAutofillType()

//==============================================================
/**
 * 描述：取自动填充值回调
 * @return 自动填充值。
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取自动填充值(): AutofillValue? = this.getAutofillValue()

//==============================================================
/**
 * 描述：向无障碍节点信息添加额外数据回调
 * @param 信息 信息。
 * @param 额外数据键 额外数据键。
 * @param 参数 参数。
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.向无障碍节点信息添加额外数据(信息 : AccessibilityNodeInfo, 额外数据键: String, 参数: Bundle) =
    this.addExtraDataToAccessibilityNodeInfo(信息, 额外数据键, 参数)

//==============================================================
/**
 * 描述：发送未校验的无障碍事件回调
 * @param 事件 事件。
 */
fun TextView.发送未校验的无障碍事件(事件 : AccessibilityEvent) =
    this.sendAccessibilityEventUnchecked(事件)

//==============================================================
/**
 * 描述：是否为输入法目标回调
 * @return 是否为输入法目标。
 */
fun TextView.是否为输入法目标(): Boolean = isInputMethodTarget()

//==============================================================
/**
 * 描述：文本上下文菜单项回调
 * @param id 菜单项ID。
 * @return 是否消费事件。
 */
fun TextView.文本上下文菜单项(id : Int): Boolean = this.onTextContextMenuItem(id)

//==============================================================

/**
 * 描述：执行长按操作回调
 * @return 是否消费事件。
 */
fun TextView.执行长按操作(): Boolean = this.performLongClick()

//==============================================================
/**
 * 描述：是否启用输入建议回调
 * @return 是否启用输入建议。
 */

fun TextView.是否启用输入建议(): Boolean = this.isSuggestionsEnabled()

//==============================================================
/**
 * 描述：置自定义选择操作模式回调
 * @param 回调 回调。
 */
fun TextView.置自定义选择操作模式回调(回调: ActionMode.Callback?) =
    this.setCustomSelectionActionModeCallback(回调)
/**
 * 描述：取自定义选择操作模式回调
 * @return 自定义选择操作模式回调。
 */
fun TextView.取自定义选择操作模式回调(): ActionMode.Callback? =
    this.getCustomSelectionActionModeCallback()

//==============================================================
/**
 * 描述：置自定义插入操作模式回调
 * @param 回调 回调。
 */
fun TextView.置自定义插入操作模式回调(回调: ActionMode.Callback?) =
    this.setCustomInsertionActionModeCallback(回调)

/**
 * 描述：取自定义插入操作模式回调
 * @return 自定义插入操作模式回调。
 */
fun TextView.取自定义插入操作模式回调(): ActionMode.Callback? =
    this.getCustomInsertionActionModeCallback()

//==============================================================

 /**
 * 描述：置文本分类器回调
 * @param 文本分类器 文本分类器。
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.置文本分类器(文本分类器: TextClassifier) = this.setTextClassifier(文本分类器)

/**
 * 描述：取文本分类器回调
 * @return 文本分类器。
 */
@RequiresApi(Build.VERSION_CODES.O)
fun TextView.取文本分类器(): TextClassifier = this.getTextClassifier()

//==============================================================
/**
 * 描述：取偏移量根据坐标回调
 * @param x X坐标。
 * @param y Y坐标。
 * @return 偏移量。
 */
fun TextView.取偏移量根据坐标(x: Float, y: Float): Int = this.getOffsetForPosition(x, y)

//==============================================================
/**
 * 描述：拖拽事件触发回调
 * @param 事件 事件。
 * @return 是否消费事件。
 */
fun TextView.拖拽事件触发(事件: DragEvent): Boolean = this.onDragEvent(事件)

//==============================================================
/**
 * 描述：当RTL属性改变回调
 * @param 布局方向 布局方向。
 */
fun TextView.当RTL属性改变(布局方向: Int) = this.onRtlPropertiesChanged(布局方向)

//==============================================================
/**
 * 描述：取文本方向启发式算法回调
 * @return 文本方向启发式算法。
 */
@RequiresApi(Build.VERSION_CODES.Q)
fun TextView.取文本方向启发式算法(): TextDirectionHeuristic = this.getTextDirectionHeuristic()

//==============================================================

 /**
 * 描述：接收内容回调
 * @param 载荷 载荷。
 * @return 处理后的内容信息。
 */
@RequiresApi(Build.VERSION_CODES.S)
fun TextView.接收内容回调(载荷: ContentInfo): ContentInfo? = this.onReceiveContent(载荷)

//==============================================================
/**
 * 描述：创建视图翻译请求回调
 * @param 支持的格式 支持的格式。
 * @param 请求收集器 请求收集器。
 */
@SuppressLint("WrongConstant")
@RequiresApi(Build.VERSION_CODES.S)
fun TextView.创建视图翻译请求(支持的格式: IntArray, 请求收集器: Consumer<ViewTranslationRequest>) =
    this.onCreateViewTranslationRequest(支持的格式, 请求收集器)

//==============================================================


/**
 * 描述：写入包裹回调
 * @param 输出 输出。
 * @param 标志 标志。
 */
fun TextView.SavedState.写入包裹(输出: Parcel, 标志: Int) = this.writeToParcel(输出, 标志)

/*
 * 描述：到字符串回调
 * @return 字符串。
 */
fun TextView.SavedState.到字符串(): String = this.toString()
