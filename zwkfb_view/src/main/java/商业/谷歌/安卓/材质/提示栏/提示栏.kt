package 商业.谷歌.安卓.材质.提示栏

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import android.os.Build.VERSION_CODES
import android.text.TextUtils
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityManager
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.Dimension
import androidx.annotation.RestrictTo
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.R
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.SnackbarContentLayout
import com.google.firebase.crashlytics.buildtools.reloc.com.google.errorprone.annotations.CanIgnoreReturnValue

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：提示栏
 *
 * 版本：0.1.2
 * @author dxyc
 */
class 提示栏 private constructor(
    context: Context,
    parent: ViewGroup,
    content: View,
    contentViewCallback: com.google.android.material.snackbar.ContentViewCallback
) : BaseTransientBottomBar<提示栏?>(context, parent, content, contentViewCallback) {
    private val accessibilityManager: AccessibilityManager?
    private var hasAction = false

    class Callback : BaseCallback<提示栏?>() {
        override fun onShown(sb: 提示栏?) {
            // Stub implementation to make API check happy.
        }

        override fun onDismissed(transientBottomBar: 提示栏?, @DismissEvent event: Int) {
            // Stub implementation to make API check happy.
        }

        companion object {
            /** Indicates that the Snackbar was dismissed via a swipe.  */
            val DISMISS_EVENT_SWIPE: Int = BaseCallback.DISMISS_EVENT_SWIPE

            /** Indicates that the Snackbar was dismissed via an action click.  */
            val DISMISS_EVENT_ACTION: Int = BaseCallback.DISMISS_EVENT_ACTION

            /** Indicates that the Snackbar was dismissed via a timeout.  */
            val DISMISS_EVENT_TIMEOUT: Int = BaseCallback.DISMISS_EVENT_TIMEOUT

            /** Indicates that the Snackbar was dismissed via a call to [.dismiss].  */
            val DISMISS_EVENT_MANUAL: Int = BaseCallback.DISMISS_EVENT_MANUAL

            /** Indicates that the Snackbar was dismissed from a new Snackbar being shown.  */
            val DISMISS_EVENT_CONSECUTIVE: Int = BaseCallback.DISMISS_EVENT_CONSECUTIVE
        }
    }

    private var callback: BaseCallback<提示栏?>? = null

    init {
        accessibilityManager =
            parent.getContext()
                .getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?
    }

    // TODO: Delete this once custom Robolectric shadows no longer depend on this method being present
    // (and instead properly utilize BaseTransientBottomBar hierarchy).
    override fun show() {
        super.show()
    }

    // TODO: Delete this once custom Robolectric shadows no longer depend on this method being present
    // (and instead properly utilize BaseTransientBottomBar hierarchy).
    override fun dismiss() {
        super.dismiss()
    }

    // TODO: Delete this once custom Robolectric shadows no longer depend on this method being present
    // (and instead properly utilize BaseTransientBottomBar hierarchy).
    override fun isShown(): Boolean {
        return super.isShown()
    }

    /**
     * Update the text in this [提示栏].
     *
     * @param message The new text for this [BaseTransientBottomBar].
     */
    @CanIgnoreReturnValue
    fun setText(message: CharSequence): 提示栏 {
        this.messageView!!.setText(message)
        return this
    }

    /**
     * Update the text in this [提示栏].
     *
     * @param resId The new text for this [BaseTransientBottomBar].
     */
    @CanIgnoreReturnValue
    fun setText(@StringRes resId: Int): 提示栏 {
        return setText(getContext().getText(resId))
    }

    /**
     * Set the action to be displayed in this [BaseTransientBottomBar].
     *
     * @param resId String resource to display for the action
     * @param listener callback to be invoked when the action is clicked
     */
    @CanIgnoreReturnValue
    fun setAction(@StringRes resId: Int, listener: View.OnClickListener?): 提示栏 {
        return setAction(getContext().getText(resId), listener)
    }

    /**
     * Set the action to be displayed in this [BaseTransientBottomBar].
     *
     * @param text Text to display for the action
     * @param listener callback to be invoked when the action is clicked
     */
    @CanIgnoreReturnValue
    fun setAction(
        text: CharSequence?, listener: View.OnClickListener?
    ): 提示栏 {
        val tv: TextView = this.actionView
        if (TextUtils.isEmpty(text) || listener == null) {
            tv.setVisibility(View.GONE)
            tv.setOnClickListener(null)
            hasAction = false
        } else {
            hasAction = true
            tv.setVisibility(View.VISIBLE)
            tv.setText(text)
            tv.setOnClickListener(
                View.OnClickListener { view: View? ->
                    listener.onClick(view)
                    // Now dismiss the Snackbar
                    dispatchDismiss(BaseCallback.DISMISS_EVENT_ACTION)
                })
        }
        return this
    }

    @Duration
    override fun getDuration(): Int {
        val userSetDuration = super.getDuration()
        if (userSetDuration == LENGTH_INDEFINITE) {
            return LENGTH_INDEFINITE
        }

        if (Build.VERSION.SDK_INT >= VERSION_CODES.Q) {
            val controlsFlag = if (hasAction) AccessibilityManager.FLAG_CONTENT_CONTROLS else 0
            return accessibilityManager!!.getRecommendedTimeoutMillis(
                userSetDuration,
                controlsFlag or AccessibilityManager.FLAG_CONTENT_ICONS or AccessibilityManager.FLAG_CONTENT_TEXT
            )
        }

        // If touch exploration is enabled override duration to give people chance to interact.
        return if (hasAction && accessibilityManager!!.isTouchExplorationEnabled())
            LENGTH_INDEFINITE
        else
            userSetDuration
    }

    /**
     * Sets the text color of the message specified in [.setText] and [ ][.setText].
     */
    @CanIgnoreReturnValue
    fun setTextColor(colors: ColorStateList?): 提示栏 {
        this.messageView!!.setTextColor(colors)
        return this
    }

    /**
     * Sets the text color of the message specified in [.setText] and [ ][.setText].
     */
    @CanIgnoreReturnValue
    fun setTextColor(@ColorInt color: Int): 提示栏 {
        this.messageView!!.setTextColor(color)
        return this
    }

    /**
     * Sets the max line count of the message specified in [.setText] and [ ][.setText].
     */
    @CanIgnoreReturnValue
    fun setTextMaxLines(maxLines: Int): 提示栏 {
        this.messageView!!.setMaxLines(maxLines)
        return this
    }

    /**
     * Sets the text color of the action specified in [.setAction].
     */
    @CanIgnoreReturnValue
    fun setActionTextColor(colors: ColorStateList?): 提示栏 {
        this.actionView.setTextColor(colors)
        return this
    }

    /**
     * Sets the max width of the action to be in the same line as the message. If the width is
     * exceeded the action would go to the next line.
     */
    @SuppressLint("RestrictedApi")
    @CanIgnoreReturnValue
    fun setMaxInlineActionWidth(@Dimension width: Int): 提示栏 {
        this.contentLayout!!.setMaxInlineActionWidth(width)
        return this
    }

    /**
     * Sets the text color of the action specified in [.setAction].
     */
    @CanIgnoreReturnValue
    fun setActionTextColor(@ColorInt color: Int): 提示栏 {
        this.actionView.setTextColor(color)
        return this
    }

    /** Sets the tint color of the background Drawable.  */
    @CanIgnoreReturnValue
    fun setBackgroundTint(@ColorInt color: Int): 提示栏 {
        return setBackgroundTintList(ColorStateList.valueOf(color))
    }

    /** Sets the tint color state list of the background Drawable.  */
    @SuppressLint("RestrictedApi")
    @CanIgnoreReturnValue
    fun setBackgroundTintList(colorStateList: ColorStateList?): 提示栏 {
        view.setBackgroundTintList(colorStateList)
        return this
    }

    @SuppressLint("RestrictedApi")
    @CanIgnoreReturnValue
    fun setBackgroundTintMode(mode: PorterDuff.Mode?): 提示栏 {
        view.setBackgroundTintMode(mode)
        return this
    }

    /**
     * Set a callback to be called when this the visibility of this [提示栏] changes. Note
     * that this method is deprecated and you should use [.addCallback] to add a
     * callback and [.removeCallback] to remove a registered callback.
     *
     * @param callback Callback to notify when transient bottom bar events occur.
     * @see 提示栏.Callback
     *
     * @see .addCallback
     * @see .removeCallback
     */
    @CanIgnoreReturnValue
    @Deprecated(
        """Use {@link #addCallback(BaseCallback)}
      """
    )
    fun setCallback(callback: Callback?): 提示栏 {
        // The logic in this method emulates what we had before support for multiple
        // registered callbacks.
        if (this.callback != null) {
            removeCallback(this.callback)
        }
        if (callback != null) {
            addCallback(callback)
        }
        // Update the deprecated field so that we can remove the passed callback the next
        // time we're called
        this.callback = callback
        return this
    }

    /**
     * @hide Note: this class is here to provide backwards-compatible way for apps written before the
     * existence of the base [BaseTransientBottomBar] class.
     */
    @SuppressLint("RestrictedApi")
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    private class SnackbarLayout : SnackbarBaseLayout {
        constructor(context: Context) : super(context)

        constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            // Work around our backwards-compatible refactoring of Snackbar and inner content
            // being inflated against snackbar's parent (instead of against the snackbar itself).
            // Every child that is width=MATCH_PARENT is remeasured again and given the full width
            // minus the paddings.
            val childCount = getChildCount()
            val availableWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight()
            for (i in 0..<childCount) {
                val child = getChildAt(i)
                if (child.getLayoutParams().width == LayoutParams.MATCH_PARENT) {
                    child.measure(
                        MeasureSpec.makeMeasureSpec(availableWidth, MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(child.getMeasuredHeight(), MeasureSpec.EXACTLY)
                    )
                }
            }
        }
    }

    @get:SuppressLint("RestrictedApi")
    private val messageView: TextView?
        get() = this.contentLayout!!.getMessageView()

    @get:SuppressLint("RestrictedApi")
    private val actionView: Button
        get() = this.contentLayout!!.getActionView()

    @get:SuppressLint("RestrictedApi")
    private val contentLayout: SnackbarContentLayout?
        get() = view.getChildAt(0) as SnackbarContentLayout?

    companion object {
        private val SNACKBAR_BUTTON_STYLE_ATTR = intArrayOf(R.attr.snackbarButtonStyle)
        private val SNACKBAR_CONTENT_STYLE_ATTRS =
            intArrayOf(R.attr.snackbarButtonStyle, R.attr.snackbarTextViewStyle)

        /**
         * Make a Snackbar to display a message
         *
         *
         * Snackbar will try and find a parent view to hold Snackbar's view from the value given to
         * `view`. Snackbar will walk up the view tree trying to find a suitable parent, which is
         * defined as a [CoordinatorLayout] or the window decor's content view, whichever comes
         * first.
         *
         *
         * Having a [CoordinatorLayout] in your view hierarchy allows Snackbar to enable certain
         * features, such as swipe-to-dismiss and automatically moving of widgets.
         *
         * @param view The view to find a parent from. This view is also used to find the anchor view when
         * calling [提示栏.setAnchorView].
         * @param text The text to show. Can be formatted text.
         * @param duration How long to display the message. Can be [.LENGTH_SHORT], [     ][.LENGTH_LONG], [.LENGTH_INDEFINITE], or a custom duration in milliseconds.
         */
        fun make(
            view: View, text: CharSequence, @Duration duration: Int
        ): 提示栏 {
            return makeInternal( /* context= */null, view, text, duration)
        }

        /**
         * Make a Snackbar to display a message
         *
         *
         * Snackbar will try and find a parent view to hold Snackbar's view from the value given to
         * `view`. Snackbar will walk up the view tree trying to find a suitable parent, which is
         * defined as a [CoordinatorLayout] or the window decor's content view, whichever comes
         * first.
         *
         *
         * Having a [CoordinatorLayout] in your view hierarchy allows Snackbar to enable certain
         * features, such as swipe-to-dismiss and automatically moving of widgets.
         *
         * @param context The context to use to create the Snackbar view.
         * @param view The view to find a parent from. This view is also used to find the anchor view when
         * calling [com.google.android.material.snackbar.Snackbar.setAnchorView].
         * @param text The text to show. Can be formatted text.
         * @param duration How long to display the message. Can be [.LENGTH_SHORT], [     ][.LENGTH_LONG], [.LENGTH_INDEFINITE], or a custom duration in milliseconds.
         */
        fun make(
            context: Context,
            view: View,
            text: CharSequence,
            @Duration duration: Int
        ): 提示栏 {
            return makeInternal(context, view, text, duration)
        }

        /**
         * Makes a Snackbar using the given context if non-null, otherwise uses the parent view context.
         */
        @SuppressLint("RestrictedApi")
        private fun makeInternal(
            context: Context?,
            view: View,
            text: CharSequence,
            @Duration duration: Int
        ): 提示栏 {
            var context = context
            val parent: ViewGroup? = findSuitableParent(view)
            requireNotNull(parent) { "No suitable parent found from the given view. Please provide a valid view." }

            if (context == null) {
                context = parent.getContext()
            }

            val inflater = LayoutInflater.from(context)
            val content =
                inflater.inflate(
                    if (hasSnackbarContentStyleAttrs(context))
                        R.layout.mtrl_layout_snackbar_include
                    else
                        R.layout.design_layout_snackbar_include,
                    parent,
                    false
                ) as SnackbarContentLayout
            val snackbar = 提示栏(context, parent, content, content)
            snackbar.setText(text)
            snackbar.setDuration(duration)
            return snackbar
        }

        /**
         * [提示栏]s should still work with AppCompat themes, which don't specify a `snackbarButtonStyle`. This method helps to check if a valid `snackbarButtonStyle` is set
         * within the current context, so that we know whether we can use the attribute.
         *
         */
        @Deprecated("This is for backward compatibility with AppCompat themes.")
        protected fun hasSnackbarButtonStyleAttr(context: Context): Boolean {
            val a = context.obtainStyledAttributes(SNACKBAR_BUTTON_STYLE_ATTR)
            val snackbarButtonStyleResId = a.getResourceId(0, -1)
            a.recycle()
            return snackbarButtonStyleResId != -1
        }

        @SuppressLint("ResourceType")
        private fun hasSnackbarContentStyleAttrs(context: Context): Boolean {
            val a = context.obtainStyledAttributes(SNACKBAR_CONTENT_STYLE_ATTRS)
            val snackbarButtonStyleResId = a.getResourceId(0, -1)
            val snackbarTextViewStyleResId = a.getResourceId(1, -1)
            a.recycle()
            return snackbarButtonStyleResId != -1 && snackbarTextViewStyleResId != -1
        }

        /**
         * Make a Snackbar to display a message.
         *
         *
         * Snackbar will try and find a parent view to hold Snackbar's view from the value given to
         * `view`. Snackbar will walk up the view tree trying to find a suitable parent, which is
         * defined as a [CoordinatorLayout] or the window decor's content view, whichever comes
         * first.
         *
         *
         * Having a [CoordinatorLayout] in your view hierarchy allows Snackbar to enable certain
         * features, such as swipe-to-dismiss and automatically moving of widgets.
         *
         * @param view The view to find a parent from.
         * @param resId The resource id of the string resource to use. Can be formatted text.
         * @param duration How long to display the message. Can be [.LENGTH_SHORT], [     ][.LENGTH_LONG], [.LENGTH_INDEFINITE], or a custom duration in milliseconds.
         */
        fun make(view: View, @StringRes resId: Int, @Duration duration: Int): 提示栏 {
            return make(view, view.getResources().getText(resId), duration)
        }

        private fun findSuitableParent(view: View?): ViewGroup? {
            var view = view
            var fallback: ViewGroup? = null
            do {
                if (view is CoordinatorLayout) {
                    // We've found a CoordinatorLayout, use it
                    return view as ViewGroup
                } else if (view is FrameLayout) {
                    if (view.getId() == android.R.id.content) {
                        // If we've hit the decor content view, then we didn't find a CoL in the
                        // hierarchy, so use it.
                        return view as ViewGroup
                    } else {
                        // It's not the content view but we'll use it as our fallback
                        fallback = view as ViewGroup
                    }
                }

                if (view != null) {
                    // Else, we will loop and crawl up the view hierarchy and try to find a parent
                    val parent = view.getParent()
                    view = if (parent is View) parent as View else null
                }
            } while (view != null)

            // If we reach here then we didn't find a CoL or a suitable content view so we'll fallback
            return fallback
        }
    }
}

//==================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：持续时间
 *
 * 版本：0.1.2
 */
var com.google.android.material.snackbar.Snackbar.持续时间 : Int
    get() = duration
    set(值){ duration = 值 }

/**
 * 创建时间：2025年11月27日
 *
 * 描述：取持续时间
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.取持续时间() : Int =
    this.getDuration()
/**
 * 创建时间：2025年11月27日
 *
 * 描述：置持续时间
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置持续时间(持续时间: Int) =
    this.setDuration(持续时间)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置文本(资源Id: Int) : com.google.android.material.snackbar.Snackbar =
    this.setText(资源Id)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置文本(文本: CharSequence) : com.google.android.material.snackbar.Snackbar =
    this.setText(文本)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本颜色
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置文本颜色(颜色: Int) : com.google.android.material.snackbar.Snackbar =
    this.setTextColor(颜色)
/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本颜色
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置文本颜色(颜色: ColorStateList) : com.google.android.material.snackbar.Snackbar =
    this.setTextColor(颜色)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置背景色调(颜色: Int) : com.google.android.material.snackbar.Snackbar =
    this.setBackgroundTint(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调列表
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置背景色调列表(颜色: ColorStateList) : com.google.android.material.snackbar.Snackbar =
    this.setBackgroundTintList(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调模式
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置背景色调模式(模式: PorterDuff.Mode) : com.google.android.material.snackbar.Snackbar =
    this.setBackgroundTintMode(模式)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：显示
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.显示() = this.show()

/**
 * 创建时间：2025年11月27日
 *
 * 描述：关闭
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.关闭() = this.dismiss()

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：是否可见
 *
 * 版本：0.1.2
 */
val com.google.android.material.snackbar.Snackbar.是否可见 : Boolean get() = isShown

/**
 * 创建时间：2025年11月27日
 *
 * 描述：是否可见
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.是否可见() : Boolean = this.isShown()

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置动作(资源Id: Int, 事件: View.OnClickListener) : com.google.android.material.snackbar.Snackbar {
    return setAction(资源Id, 事件)
}

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置动作(文本: CharSequence, 事件: View.OnClickListener) : com.google.android.material.snackbar.Snackbar {
    return setAction(文本, 事件)
}

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作文本颜色
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置动作文本颜色(颜色: Int) : com.google.android.material.snackbar.Snackbar {
    return setActionTextColor(颜色)
}

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作文本颜色
 *
 * 版本：0.1.2
 */
fun com.google.android.material.snackbar.Snackbar.置动作文本颜色(颜色: ColorStateList) : com.google.android.material.snackbar.Snackbar =
    this.setActionTextColor(颜色)










//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：持续时间
 *
 * 版本：0.1.2
 */
var Snackbar.持续时间 : Int
    get() = duration
    set(值){ duration = 值 }

/**
 * 创建时间：2025年11月27日
 *
 * 描述：持续时间
 *
 * 版本：0.1.2
 */
fun Snackbar.取持续时间() : Int = getDuration()

/**
 * 创建时间：2025年11月27日
 *
 * 描述：持续时间
 *
 * 版本：0.1.2
 */
fun Snackbar.置持续时间(持续时间: Int) { setDuration(持续时间) }

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun Snackbar.置文本(资源Id: Int) : Snackbar = setText(资源Id)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun Snackbar.置文本(文本: CharSequence) : Snackbar = setText(文本)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本颜色
 *
 * 版本：0.1.2
 */
fun Snackbar.置文本颜色(颜色: Int) : Snackbar = setTextColor(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本颜色
 *
 * 版本：0.1.2
 */
fun Snackbar.置文本颜色(颜色: ColorStateList) : Snackbar = setTextColor(颜色)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调
 *
 * 版本：0.1.2
 */
fun Snackbar.置背景色调(颜色: Int) : Snackbar = setBackgroundTint(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调列表
 *
 * 版本：0.1.2
 */
fun Snackbar.置背景色调列表(颜色: ColorStateList) : Snackbar = setBackgroundTintList(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调模式
 *
 * 版本：0.1.2
 */
fun Snackbar.置背景色调模式(模式: PorterDuff.Mode) : Snackbar = setBackgroundTintMode(模式)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：显示
 *
 * 版本：0.1.2
 */
fun Snackbar.显示() { show() }

/**
 * 创建时间：2025年11月27日
 *
 * 描述：关闭
 *
 * 版本：0.1.2
 */
fun Snackbar.关闭() { dismiss() }

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：是否可见
 *
 * 版本：0.1.2
 */
val Snackbar.是否可见 : Boolean get() = isShown

/**
 * 创建时间：2025年11月27日
 *
 * 描述：是否可见
 *
 * 版本：0.1.2
 */
fun Snackbar.是否可见() : Boolean = isShown()

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作
 *
 * 版本：0.1.2
 */
fun Snackbar.置动作(资源Id: Int, 事件: View.OnClickListener) : Snackbar {
    return setAction(资源Id, 事件)
}

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作
 *
 * 版本：0.1.2
 */
fun Snackbar.置动作(文本: CharSequence, 事件: View.OnClickListener) : Snackbar{
    return setAction(文本, 事件)
}

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作文本颜色
 *
 * 版本：0.1.2
 */
fun Snackbar.置动作文本颜色(颜色: Int) : Snackbar{
    return setActionTextColor(颜色)
}

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作文本颜色
 *
 * 版本：0.1.2
 */
fun Snackbar.置动作文本颜色(颜色: ColorStateList) : Snackbar{
    return setActionTextColor(颜色)
}








//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：持续时间
 *
 * 版本：0.1.2
 */
var 提示栏.持续时间 : Int
    get() = duration
    set(值){ duration = 值 }

/**
 * 创建时间：2025年11月27日
 *
 * 描述：持续时间
 *
 * 版本：0.1.2
 */
fun 提示栏.取持续时间() : Int = getDuration()

/**
 * 创建时间：2025年11月27日
 *
 * 描述：持续时间
 *
 * 版本：0.1.2
 */
fun 提示栏.置持续时间(持续时间: Int) { setDuration(持续时间) }

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun 提示栏.置文本(资源Id: Int) : 提示栏 = setText(资源Id)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun 提示栏.置文本(文本: CharSequence) : 提示栏 = setText(文本)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本颜色
 *
 * 版本：0.1.2
 */
fun 提示栏.置文本颜色(颜色: Int) : 提示栏 = setTextColor(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置文本颜色
 *
 * 版本：0.1.2
 */
fun 提示栏.置文本颜色(颜色: ColorStateList) : 提示栏 = setTextColor(颜色)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调
 *
 * 版本：0.1.2
 */
fun 提示栏.置背景色调(颜色: Int) : 提示栏 = setBackgroundTint(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调列表
 *
 * 版本：0.1.2
 */
fun 提示栏.置背景色调列表(颜色: ColorStateList) : 提示栏 = setBackgroundTintList(颜色)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置背景色调模式
 *
 * 版本：0.1.2
 */
fun 提示栏.置背景色调模式(模式: PorterDuff.Mode) : 提示栏 = setBackgroundTintMode(模式)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：显示
 *
 * 版本：0.1.2
 */
fun 提示栏.显示() = this.show()

/**
 * 创建时间：2025年11月27日
 *
 * 描述：关闭
 *
 * 版本：0.1.2
 */
fun 提示栏.关闭() = this.dismiss()

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：是否可见
 *
 * 版本：0.1.2
 */
val 提示栏.是否可见 : Boolean get() = this.isShown

/**
 * 创建时间：2025年11月27日
 *
 * 描述：是否可见
 *
 * 版本：0.1.2
 */
fun 提示栏.是否可见() : Boolean = this.isShown()

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作
 *
 * 版本：0.1.2
 */
fun 提示栏.置动作(资源Id: Int, 事件: View.OnClickListener) : 提示栏 =
    this.setAction(资源Id, 事件)

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作
 *
 * 版本：0.1.2
 */
fun 提示栏.置动作(文本: CharSequence, 事件: View.OnClickListener) : 提示栏 =
    this.setAction(文本, 事件)

//======================================================================

/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作文本颜色
 *
 * 版本：0.1.2
 */
fun 提示栏.置动作文本颜色(颜色: Int) : 提示栏 = this.setActionTextColor(颜色)


/**
 * 创建时间：2025年11月27日
 *
 * 描述：置动作文本颜色
 *
 * 版本：0.1.2
 */
fun 提示栏.置动作文本颜色(颜色: ColorStateList) : 提示栏 = this.setActionTextColor(颜色)
