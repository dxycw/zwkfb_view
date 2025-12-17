package 安卓x.应用兼容包.应用

import android.content.Context
import android.content.DialogInterface
import android.content.DialogInterface.OnMultiChoiceClickListener
import android.database.Cursor
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Handler
import android.os.Message
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.view.Window
import android.view.WindowManager
import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckedTextView
import android.widget.CursorAdapter
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import android.widget.TextView
import androidx.appcompat.R
import androidx.appcompat.app.AppCompatDialog
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import java.lang.ref.WeakReference

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：对话框控制器
 *
 * 版本：0.0.9
 * @author dxyc
 */
internal class 对话框控制器(
    private val mContext: Context,
    val mDialog: AppCompatDialog,
    private val mWindow: Window
) {
    private val mButtonIconDimen: Int

    private var mTitle: CharSequence? = null
    private var mMessage: CharSequence? = null
    var listView: ListView? = null
    private var mView: View? = null

    private var mViewLayoutResId = 0

    private var mViewSpacingLeft = 0
    private var mViewSpacingTop = 0
    private var mViewSpacingRight = 0
    private var mViewSpacingBottom = 0
    private var mViewSpacingSpecified = false

    var mButtonPositive: Button? = null
    private var mButtonPositiveText: CharSequence? = null
    var mButtonPositiveMessage: Message? = null
    private var mButtonPositiveIcon: Drawable? = null

    var mButtonNegative: Button? = null
    private var mButtonNegativeText: CharSequence? = null
    var mButtonNegativeMessage: Message? = null
    private var mButtonNegativeIcon: Drawable? = null

    var mButtonNeutral: Button? = null
    private var mButtonNeutralText: CharSequence? = null
    var mButtonNeutralMessage: Message? = null
    private var mButtonNeutralIcon: Drawable? = null

    var mScrollView: NestedScrollView? = null

    private var mIconId = 0
    private var mIcon: Drawable? = null

    private var mIconView: ImageView? = null
    private var mTitleView: TextView? = null
    private var mMessageView: TextView? = null
    private var mCustomTitleView: View? = null

    var mAdapter: ListAdapter? = null

    var mCheckedItem: Int = -1

    private val mAlertDialogLayout: Int
    private val mButtonPanelSideLayout: Int
    var mListLayout: Int
    var mMultiChoiceItemLayout: Int
    var mSingleChoiceItemLayout: Int
    var mListItemLayout: Int

    private val mShowTitle: Boolean

    private var mButtonPanelLayoutHint = 0

    var mHandler: Handler

    private val mButtonHandler: View.OnClickListener = View.OnClickListener { v ->
        val m = if (v === mButtonPositive && mButtonPositiveMessage != null) {
            Message.obtain(mButtonPositiveMessage)
        } else if (v === mButtonNegative && mButtonNegativeMessage != null) {
            Message.obtain(mButtonNegativeMessage)
        } else if (v === mButtonNeutral && mButtonNeutralMessage != null) {
            Message.obtain(mButtonNeutralMessage)
        } else {
            null
        }

        m?.sendToTarget()

        mHandler.obtainMessage(ButtonHandler.MSG_DISMISS_DIALOG, mDialog)
            .sendToTarget()
    }

    private class ButtonHandler(dialog: DialogInterface?) : Handler() {
        private val mDialog: WeakReference<DialogInterface?>

        init {
            mDialog = WeakReference<DialogInterface?>(dialog)
        }

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                DialogInterface.BUTTON_POSITIVE, DialogInterface.BUTTON_NEGATIVE, DialogInterface.BUTTON_NEUTRAL -> (msg.obj as DialogInterface.OnClickListener).onClick(
                    mDialog.get(),
                    msg.what
                )

                MSG_DISMISS_DIALOG -> (msg.obj as DialogInterface).dismiss()
            }
        }

        companion object {
            // Button clicks have Message.what as the BUTTON{1,2,3} constant
            const val MSG_DISMISS_DIALOG = 1
        }
    }

    init {
        mHandler = ButtonHandler(mDialog)

        val a = mContext.obtainStyledAttributes(
            null, R.styleable.AlertDialog,
            R.attr.alertDialogStyle, 0
        )

        mAlertDialogLayout = a.getResourceId(R.styleable.AlertDialog_android_layout, 0)
        mButtonPanelSideLayout = a.getResourceId(R.styleable.AlertDialog_buttonPanelSideLayout, 0)

        mListLayout = a.getResourceId(R.styleable.AlertDialog_listLayout, 0)
        mMultiChoiceItemLayout = a.getResourceId(R.styleable.AlertDialog_multiChoiceItemLayout, 0)
        mSingleChoiceItemLayout = a
            .getResourceId(R.styleable.AlertDialog_singleChoiceItemLayout, 0)
        mListItemLayout = a.getResourceId(R.styleable.AlertDialog_listItemLayout, 0)
        mShowTitle = a.getBoolean(R.styleable.AlertDialog_showTitle, true)
        mButtonIconDimen = a.getDimensionPixelSize(R.styleable.AlertDialog_buttonIconDimen, 0)

        a.recycle()

        /* We use a custom title so never request a window title */
        mDialog.supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }

    fun installContent() {
        val contentView = selectContentView()
        mDialog.setContentView(contentView)
        setupView()
    }

    private fun selectContentView(): Int {
        if (mButtonPanelSideLayout == 0) {
            return mAlertDialogLayout
        }
        if (mButtonPanelLayoutHint == 1) {
            return mButtonPanelSideLayout
        }
        return mAlertDialogLayout
    }

    fun setTitle(title: CharSequence?) {
        mTitle = title
        if (mTitleView != null) {
            mTitleView!!.setText(title)
        }
    }

    /**
     * @see AlertDialog.Builder.setCustomTitle
     */
    fun setCustomTitle(customTitleView: View?) {
        mCustomTitleView = customTitleView
    }

    fun setMessage(message: CharSequence?) {
        mMessage = message
        if (mMessageView != null) {
            mMessageView!!.setText(message)
        }
    }

    /**
     * Set the view resource to display in the dialog.
     */
    fun setView(layoutResId: Int) {
        mView = null
        mViewLayoutResId = layoutResId
        mViewSpacingSpecified = false
    }

    /**
     * Set the view to display in the dialog.
     */
    fun setView(view: View?) {
        mView = view
        mViewLayoutResId = 0
        mViewSpacingSpecified = false
    }

    /**
     * Set the view to display in the dialog along with the spacing around that view
     */
    fun setView(
        view: View?, viewSpacingLeft: Int, viewSpacingTop: Int, viewSpacingRight: Int,
        viewSpacingBottom: Int
    ) {
        mView = view
        mViewLayoutResId = 0
        mViewSpacingSpecified = true
        mViewSpacingLeft = viewSpacingLeft
        mViewSpacingTop = viewSpacingTop
        mViewSpacingRight = viewSpacingRight
        mViewSpacingBottom = viewSpacingBottom
    }

    /**
     * Sets a hint for the best button panel layout.
     */
    fun setButtonPanelLayoutHint(layoutHint: Int) {
        mButtonPanelLayoutHint = layoutHint
    }

    /**
     * Sets an icon, a click listener or a message to be sent when the button is clicked.
     * You only need to pass one of `icon`, `listener` or `msg`.
     *
     * @param whichButton Which button, can be one of
     * [DialogInterface.BUTTON_POSITIVE],
     * [DialogInterface.BUTTON_NEGATIVE], or
     * [DialogInterface.BUTTON_NEUTRAL]
     * @param text        The text to display in positive button.
     * @param listener    The [DialogInterface.OnClickListener] to use.
     * @param msg         The [Message] to be sent when clicked.
     * @param icon        The (@link Drawable) to be used as an icon for the button.
     */
    fun setButton(
        whichButton: Int, text: CharSequence?,
        listener: DialogInterface.OnClickListener?, msg: Message?, icon: Drawable?
    ) {
        var msg = msg
        if (msg == null && listener != null) {
            msg = mHandler.obtainMessage(whichButton, listener)
        }

        when (whichButton) {
            DialogInterface.BUTTON_POSITIVE -> {
                mButtonPositiveText = text
                mButtonPositiveMessage = msg
                mButtonPositiveIcon = icon
            }

            DialogInterface.BUTTON_NEGATIVE -> {
                mButtonNegativeText = text
                mButtonNegativeMessage = msg
                mButtonNegativeIcon = icon
            }

            DialogInterface.BUTTON_NEUTRAL -> {
                mButtonNeutralText = text
                mButtonNeutralMessage = msg
                mButtonNeutralIcon = icon
            }

            else -> throw IllegalArgumentException("Button does not exist")
        }
    }

    /**
     * Specifies the icon to display next to the alert title.
     *
     * @param resId the resource identifier of the drawable to use as the icon,
     * or 0 for no icon
     */
    fun setIcon(resId: Int) {
        mIcon = null
        mIconId = resId

        if (mIconView != null) {
            if (resId != 0) {
                mIconView!!.setVisibility(View.VISIBLE)
                mIconView!!.setImageResource(mIconId)
            } else {
                mIconView!!.setVisibility(View.GONE)
            }
        }
    }

    /**
     * Specifies the icon to display next to the alert title.
     *
     * @param icon the drawable to use as the icon or null for no icon
     */
    fun setIcon(icon: Drawable?) {
        mIcon = icon
        mIconId = 0

        if (mIconView != null) {
            if (icon != null) {
                mIconView!!.setVisibility(View.VISIBLE)
                mIconView!!.setImageDrawable(icon)
            } else {
                mIconView!!.setVisibility(View.GONE)
            }
        }
    }

    /**
     * @param attrId the attributeId of the theme-specific drawable
     * to resolve the resourceId for.
     *
     * @return resId the resourceId of the theme-specific drawable
     */
    fun getIconAttributeResId(attrId: Int): Int {
        val out = TypedValue()
        mContext.getTheme().resolveAttribute(attrId, out, true)
        return out.resourceId
    }

    fun getButton(whichButton: Int): Button? {
        when (whichButton) {
            DialogInterface.BUTTON_POSITIVE -> return mButtonPositive
            DialogInterface.BUTTON_NEGATIVE -> return mButtonNegative
            DialogInterface.BUTTON_NEUTRAL -> return mButtonNeutral
            else -> return null
        }
    }

    fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return mScrollView != null && mScrollView!!.executeKeyEvent(event)
    }

    fun onKeyUp(keyCode: Int, event: KeyEvent): Boolean {
        return mScrollView != null && mScrollView!!.executeKeyEvent(event)
    }

    /**
     * Resolves whether a custom or default panel should be used. Removes the
     * default panel if a custom panel should be used. If the resolved panel is
     * a view stub, inflates before returning.
     *
     * @param customPanel the custom panel
     * @param defaultPanel the default panel
     * @return the panel to use
     */
    private fun resolvePanel(customPanel: View?, defaultPanel: View?): ViewGroup? {
        var customPanel = customPanel
        var defaultPanel = defaultPanel
        if (customPanel == null) {
            // Inflate the default panel, if needed.
            if (defaultPanel is ViewStub) {
                defaultPanel = defaultPanel.inflate()
            }

            return defaultPanel as ViewGroup?
        }

        // Remove the default panel entirely.
        if (defaultPanel != null) {
            val parent = defaultPanel.getParent()
            if (parent is ViewGroup) {
                parent.removeView(defaultPanel)
            }
        }

        // Inflate the custom panel, if needed.
        if (customPanel is ViewStub) {
            customPanel = customPanel.inflate()
        }

        return customPanel as ViewGroup?
    }

    private fun setupView() {
        val parentPanel = mWindow.findViewById<View>(R.id.parentPanel)
        val defaultTopPanel = parentPanel.findViewById<View?>(R.id.topPanel)
        val defaultContentPanel = parentPanel.findViewById<View?>(R.id.contentPanel)
        val defaultButtonPanel = parentPanel.findViewById<View?>(R.id.buttonPanel)

        // Install custom content before setting up the title or buttons so
        // that we can handle panel overrides.
        val customPanel = parentPanel.findViewById<View?>(R.id.customPanel) as ViewGroup
        setupCustomContent(customPanel)

        val customTopPanel = customPanel.findViewById<View?>(R.id.topPanel)
        val customContentPanel = customPanel.findViewById<View?>(R.id.contentPanel)
        val customButtonPanel = customPanel.findViewById<View?>(R.id.buttonPanel)

        // Resolve the correct panels and remove the defaults, if needed.
        val topPanel = resolvePanel(customTopPanel, defaultTopPanel)
        val contentPanel = resolvePanel(customContentPanel, defaultContentPanel)
        val buttonPanel = resolvePanel(customButtonPanel, defaultButtonPanel)

        setupContent(contentPanel!!)
        setupButtons(buttonPanel!!)
        setupTitle(topPanel!!)

        val hasCustomPanel = customPanel != null
                && customPanel.getVisibility() != View.GONE
        val hasTopPanel = topPanel != null
                && topPanel.getVisibility() != View.GONE
        val hasButtonPanel = buttonPanel != null
                && buttonPanel.getVisibility() != View.GONE

        // Only display the text spacer if we don't have buttons.
        if (!hasButtonPanel) {
            if (contentPanel != null) {
                val spacer = contentPanel.findViewById<View?>(R.id.textSpacerNoButtons)
                if (spacer != null) {
                    spacer.setVisibility(View.VISIBLE)
                }
            }
        }

        if (hasTopPanel) {
            // Only clip scrolling content to padding if we have a title.
            if (mScrollView != null) {
                mScrollView!!.setClipToPadding(true)
            }

            // Only show the divider if we have a title.
            var divider: View? = null
            if (mMessage != null || this.listView != null) {
                divider = topPanel.findViewById<View?>(R.id.titleDividerNoCustom)
            }

            if (divider != null) {
                divider.setVisibility(View.VISIBLE)
            }
        } else {
            if (contentPanel != null) {
                val spacer = contentPanel.findViewById<View?>(R.id.textSpacerNoTitle)
                if (spacer != null) {
                    spacer.setVisibility(View.VISIBLE)
                }
            }
        }

        if (this.listView is 回收列表视图) {
            (this.listView as 回收列表视图).setHasDecor(hasTopPanel, hasButtonPanel)
        }

        // Update scroll indicators as needed.
        if (!hasCustomPanel) {
            val content: View? = if (this.listView != null) this.listView else mScrollView
            if (content != null) {
                val indicators = ((if (hasTopPanel) ViewCompat.SCROLL_INDICATOR_TOP else 0)
                        or (if (hasButtonPanel) ViewCompat.SCROLL_INDICATOR_BOTTOM else 0))
                setScrollIndicators(
                    contentPanel, content, indicators,
                    ViewCompat.SCROLL_INDICATOR_TOP or ViewCompat.SCROLL_INDICATOR_BOTTOM
                )
            }
        }

        val listView = this.listView
        if (listView != null && mAdapter != null) {
            listView.setAdapter(mAdapter)
            val checkedItem = mCheckedItem
            if (checkedItem > -1) {
                listView.setItemChecked(checkedItem, true)
                listView.setSelection(checkedItem)
            }
        }
    }

    private fun setScrollIndicators(
        contentPanel: ViewGroup, content: View,
        indicators: Int, mask: Int
    ) {
        // Set up scroll indicators (if present).
        var indicatorUp = mWindow.findViewById<View?>(R.id.scrollIndicatorUp)
        var indicatorDown = mWindow.findViewById<View?>(R.id.scrollIndicatorDown)

        if (Build.VERSION.SDK_INT >= 23) {
            // We're on Marshmallow so can rely on the View APIs
            ViewCompat.setScrollIndicators(content, indicators, mask)
            // We can also remove the compat indicator views
            if (indicatorUp != null) {
                contentPanel.removeView(indicatorUp)
            }
            if (indicatorDown != null) {
                contentPanel.removeView(indicatorDown)
            }
        } else {
            // First, remove the indicator views if we're not set to use them
            if (indicatorUp != null && (indicators and ViewCompat.SCROLL_INDICATOR_TOP) == 0) {
                contentPanel.removeView(indicatorUp)
                indicatorUp = null
            }
            if (indicatorDown != null && (indicators and ViewCompat.SCROLL_INDICATOR_BOTTOM) == 0) {
                contentPanel.removeView(indicatorDown)
                indicatorDown = null
            }

            if (indicatorUp != null || indicatorDown != null) {
                val top = indicatorUp
                val bottom = indicatorDown

                if (mMessage != null) {
                    // We're just showing the ScrollView, set up listener.
                    mScrollView!!.setOnScrollChangeListener(
                        object : NestedScrollView.OnScrollChangeListener {
                            override fun onScrollChange(
                                v: NestedScrollView, scrollX: Int,
                                scrollY: Int,
                                oldScrollX: Int, oldScrollY: Int
                            ) {
                                manageScrollIndicators(v, top, bottom)
                            }
                        })
                    // Set up the indicators following layout.
                    mScrollView!!.post(object : Runnable {
                        override fun run() {
                            Companion.manageScrollIndicators(mScrollView!!, top, bottom)
                        }
                    })
                } else if (this.listView != null) {
                    // We're just showing the AbsListView, set up listener.
                    listView!!.setOnScrollListener(object : AbsListView.OnScrollListener {
                        override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {}

                        override fun onScroll(
                            v: AbsListView, firstVisibleItem: Int,
                            visibleItemCount: Int, totalItemCount: Int
                        ) {
                            manageScrollIndicators(v, top, bottom)
                        }
                    })
                    // Set up the indicators following layout.
                    listView!!.post(object : Runnable {
                        override fun run() {
                            manageScrollIndicators(
                                listView!!, top, bottom
                            )
                        }
                    })
                } else {
                    // We don't have any content to scroll, remove the indicators.
                    if (top != null) {
                        contentPanel.removeView(top)
                    }
                    if (bottom != null) {
                        contentPanel.removeView(bottom)
                    }
                }
            }
        }
    }

    private fun setupCustomContent(customPanel: ViewGroup) {
        val customView: View?
        if (mView != null) {
            customView = mView
        } else if (mViewLayoutResId != 0) {
            val inflater = LayoutInflater.from(mContext)
            customView = inflater.inflate(mViewLayoutResId, customPanel, false)
        } else {
            customView = null
        }

        val hasCustomView = customView != null
        if (!hasCustomView || !canTextInput(customView)) {
            mWindow.setFlags(
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
                WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
            )
        }

        if (hasCustomView) {
            val custom = mWindow.findViewById<View?>(R.id.custom) as FrameLayout
            custom.addView(
                customView,
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            )

            if (mViewSpacingSpecified) {
                custom.setPadding(
                    mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom
                )
            }

            if (this.listView != null) {
                (customPanel.getLayoutParams() as LinearLayoutCompat.LayoutParams).weight = 0f
            }
        } else {
            customPanel.setVisibility(View.GONE)
        }
    }

    private fun setupTitle(topPanel: ViewGroup) {
        if (mCustomTitleView != null) {
            // Add the custom title view directly to the topPanel layout
            val lp = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
            )

            topPanel.addView(mCustomTitleView, 0, lp)

            // Hide the title template
            val titleTemplate = mWindow.findViewById<View>(R.id.title_template)
            titleTemplate.setVisibility(View.GONE)
        } else {
            mIconView = mWindow.findViewById<View?>(android.R.id.icon) as ImageView?

            val hasTextTitle = !TextUtils.isEmpty(mTitle)
            if (hasTextTitle && mShowTitle) {
                // Display the title if a title is supplied, else hide it.
                mTitleView = mWindow.findViewById<View?>(R.id.alertTitle) as TextView?
                mTitleView!!.setText(mTitle)

                // Do this last so that if the user has supplied any icons we
                // use them instead of the default ones. If the user has
                // specified 0 then make it disappear.
                if (mIconId != 0) {
                    mIconView!!.setImageResource(mIconId)
                } else if (mIcon != null) {
                    mIconView!!.setImageDrawable(mIcon)
                } else {
                    // Apply the padding from the icon to ensure the title is
                    // aligned correctly.
                    mTitleView!!.setPadding(
                        mIconView!!.getPaddingLeft(),
                        mIconView!!.getPaddingTop(),
                        mIconView!!.getPaddingRight(),
                        mIconView!!.getPaddingBottom()
                    )
                    mIconView!!.setVisibility(View.GONE)
                }
            } else {
                // Hide the title template
                val titleTemplate = mWindow.findViewById<View>(R.id.title_template)
                titleTemplate.setVisibility(View.GONE)
                mIconView!!.setVisibility(View.GONE)
                topPanel.setVisibility(View.GONE)
            }
        }
    }

    private fun setupContent(contentPanel: ViewGroup) {
        mScrollView = mWindow.findViewById<View?>(R.id.scrollView) as NestedScrollView?
        mScrollView!!.setFocusable(false)
        mScrollView!!.setNestedScrollingEnabled(false)

        // Special case for users that only want to display a String
        mMessageView = contentPanel.findViewById<View?>(android.R.id.message) as TextView?
        if (mMessageView == null) {
            return
        }

        if (mMessage != null) {
            mMessageView!!.setText(mMessage)
        } else {
            mMessageView!!.setVisibility(View.GONE)
            mScrollView!!.removeView(mMessageView)

            if (this.listView != null) {
                val scrollParent = mScrollView!!.getParent() as ViewGroup
                val childIndex = scrollParent.indexOfChild(mScrollView)
                scrollParent.removeViewAt(childIndex)
                scrollParent.addView(
                    this.listView, childIndex,
                    ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )
            } else {
                contentPanel.setVisibility(View.GONE)
            }
        }
    }

    private fun setupButtons(buttonPanel: ViewGroup) {
        val BIT_BUTTON_POSITIVE = 1
        val BIT_BUTTON_NEGATIVE = 2
        val BIT_BUTTON_NEUTRAL = 4
        var whichButtons = 0
        mButtonPositive = buttonPanel.findViewById<View?>(android.R.id.button1) as Button
        mButtonPositive!!.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonPositiveText) && mButtonPositiveIcon == null) {
            mButtonPositive!!.setVisibility(View.GONE)
        } else {
            mButtonPositive!!.setText(mButtonPositiveText)
            if (mButtonPositiveIcon != null) {
                mButtonPositiveIcon!!.setBounds(0, 0, mButtonIconDimen, mButtonIconDimen)
                mButtonPositive!!.setCompoundDrawables(mButtonPositiveIcon, null, null, null)
            }
            mButtonPositive!!.setVisibility(View.VISIBLE)
            whichButtons = whichButtons or BIT_BUTTON_POSITIVE
        }

        mButtonNegative = buttonPanel.findViewById<Button>(android.R.id.button2)
        mButtonNegative!!.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonNegativeText) && mButtonNegativeIcon == null) {
            mButtonNegative!!.setVisibility(View.GONE)
        } else {
            mButtonNegative!!.setText(mButtonNegativeText)
            if (mButtonNegativeIcon != null) {
                mButtonNegativeIcon!!.setBounds(0, 0, mButtonIconDimen, mButtonIconDimen)
                mButtonNegative!!.setCompoundDrawables(mButtonNegativeIcon, null, null, null)
            }
            mButtonNegative!!.setVisibility(View.VISIBLE)
            whichButtons = whichButtons or BIT_BUTTON_NEGATIVE
        }

        mButtonNeutral = buttonPanel.findViewById<View?>(android.R.id.button3) as Button
        mButtonNeutral!!.setOnClickListener(mButtonHandler)

        if (TextUtils.isEmpty(mButtonNeutralText) && mButtonNeutralIcon == null) {
            mButtonNeutral!!.setVisibility(View.GONE)
        } else {
            mButtonNeutral!!.setText(mButtonNeutralText)
            if (mButtonNeutralIcon != null) {
                mButtonNeutralIcon!!.setBounds(0, 0, mButtonIconDimen, mButtonIconDimen)
                mButtonNeutral!!.setCompoundDrawables(mButtonNeutralIcon, null, null, null)
            }
            mButtonNeutral!!.setVisibility(View.VISIBLE)
            whichButtons = whichButtons or BIT_BUTTON_NEUTRAL
        }

        if (shouldCenterSingleButton(mContext)) {
            /*
             * If we only have 1 button it should be centered on the layout and
             * expand to fill 50% of the available space.
             */
            if (whichButtons == BIT_BUTTON_POSITIVE) {
                centerButton(mButtonPositive!!)
            } else if (whichButtons == BIT_BUTTON_NEGATIVE) {
                centerButton(mButtonNegative!!)
            } else if (whichButtons == BIT_BUTTON_NEUTRAL) {
                centerButton(mButtonNeutral!!)
            }
        }

        val hasButtons = whichButtons != 0
        if (!hasButtons) {
            buttonPanel.setVisibility(View.GONE)
        }
    }

    private fun centerButton(button: Button) {
        val params = button.getLayoutParams() as LinearLayout.LayoutParams
        params.gravity = Gravity.CENTER_HORIZONTAL
        params.weight = 0.5f
        button.setLayoutParams(params)
    }

    /**
     * 创建时间：2025年11月23日.
     *
     * 描述：回收列表视图
     *
     * 版本：0.0.9
     * @author dxyc
     */
    open class 回收列表视图 @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
    ) : ListView(context, attrs) {
        private val mPaddingTopNoTitle: Int
        private val mPaddingBottomNoButtons: Int

        init {
            val ta = context.obtainStyledAttributes(
                attrs, R.styleable.RecycleListView
            )
            mPaddingBottomNoButtons = ta.getDimensionPixelOffset(
                R.styleable.RecycleListView_paddingBottomNoButtons, -1
            )
            mPaddingTopNoTitle = ta.getDimensionPixelOffset(
                R.styleable.RecycleListView_paddingTopNoTitle, -1
            )
        }

        fun setHasDecor(hasTitle: Boolean, hasButtons: Boolean) {
            if (!hasButtons || !hasTitle) {
                val paddingLeft = getPaddingLeft()
                val paddingTop = if (hasTitle) getPaddingTop() else mPaddingTopNoTitle
                val paddingRight = getPaddingRight()
                val paddingBottom = if (hasButtons) getPaddingBottom() else mPaddingBottomNoButtons
                setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom)
            }
        }
    }

    class AlertParams(val mContext: Context) {
        val mInflater: LayoutInflater

        var mIconId: Int = 0
        var mIcon: Drawable? = null
        var mIconAttrId: Int = 0
        var mTitle: CharSequence? = null
        var mCustomTitleView: View? = null
        var mMessage: CharSequence? = null
        var mPositiveButtonText: CharSequence? = null
        var mPositiveButtonIcon: Drawable? = null
        var mPositiveButtonListener: DialogInterface.OnClickListener? = null
        var mNegativeButtonText: CharSequence? = null
        var mNegativeButtonIcon: Drawable? = null
        var mNegativeButtonListener: DialogInterface.OnClickListener? = null
        var mNeutralButtonText: CharSequence? = null
        var mNeutralButtonIcon: Drawable? = null
        var mNeutralButtonListener: DialogInterface.OnClickListener? = null
        var mCancelable: Boolean = true
        var mOnCancelListener: DialogInterface.OnCancelListener? = null
        var mOnDismissListener: DialogInterface.OnDismissListener? = null
        var mOnKeyListener: DialogInterface.OnKeyListener? = null
        var mItems: Array<CharSequence?>? = null
        var mAdapter: ListAdapter? = null
        var mOnClickListener: DialogInterface.OnClickListener? = null
        var mViewLayoutResId: Int = 0
        var mView: View? = null
        var mViewSpacingLeft: Int = 0
        var mViewSpacingTop: Int = 0
        var mViewSpacingRight: Int = 0
        var mViewSpacingBottom: Int = 0
        var mViewSpacingSpecified: Boolean = false
        var mCheckedItems: BooleanArray? = null
        var mIsMultiChoice: Boolean = false
        var mIsSingleChoice: Boolean = false
        var mCheckedItem: Int = -1
        var mOnCheckboxClickListener: OnMultiChoiceClickListener? = null
        var mCursor: Cursor? = null
        var mLabelColumn: String? = null
        var mIsCheckedColumn: String? = null
        var mForceInverseBackground: Boolean = false
        var mOnItemSelectedListener: AdapterView.OnItemSelectedListener? = null
        var mOnPrepareListViewListener: OnPrepareListViewListener? = null
        var mRecycleOnMeasure: Boolean = true

        /**
         * Interface definition for a callback to be invoked before the ListView
         * will be bound to an adapter.
         */
        interface OnPrepareListViewListener {
            /**
             * Called before the ListView is bound to an adapter.
             * @param listView The ListView that will be shown in the dialog.
             */
            fun onPrepareListView(listView: ListView?)
        }

        init {
            mInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        fun apply(dialog: 对话框控制器) {
            if (mCustomTitleView != null) {
                dialog.setCustomTitle(mCustomTitleView)
            } else {
                if (mTitle != null) {
                    dialog.setTitle(mTitle)
                }
                if (mIcon != null) {
                    dialog.setIcon(mIcon)
                }
                if (mIconId != 0) {
                    dialog.setIcon(mIconId)
                }
                if (mIconAttrId != 0) {
                    dialog.setIcon(dialog.getIconAttributeResId(mIconAttrId))
                }
            }
            if (mMessage != null) {
                dialog.setMessage(mMessage)
            }
            if (mPositiveButtonText != null || mPositiveButtonIcon != null) {
                dialog.setButton(
                    DialogInterface.BUTTON_POSITIVE, mPositiveButtonText,
                    mPositiveButtonListener, null, mPositiveButtonIcon
                )
            }
            if (mNegativeButtonText != null || mNegativeButtonIcon != null) {
                dialog.setButton(
                    DialogInterface.BUTTON_NEGATIVE, mNegativeButtonText,
                    mNegativeButtonListener, null, mNegativeButtonIcon
                )
            }
            if (mNeutralButtonText != null || mNeutralButtonIcon != null) {
                dialog.setButton(
                    DialogInterface.BUTTON_NEUTRAL, mNeutralButtonText,
                    mNeutralButtonListener, null, mNeutralButtonIcon
                )
            }
            // For a list, the client can either supply an array of items or an
            // adapter or a cursor
            if ((mItems != null) || (mCursor != null) || (mAdapter != null)) {
                createListView(dialog)
            }
            if (mView != null) {
                if (mViewSpacingSpecified) {
                    dialog.setView(
                        mView, mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight,
                        mViewSpacingBottom
                    )
                } else {
                    dialog.setView(mView)
                }
            } else if (mViewLayoutResId != 0) {
                dialog.setView(mViewLayoutResId)
            }

            /*
            dialog.setCancelable(mCancelable);
            dialog.setOnCancelListener(mOnCancelListener);
            if (mOnKeyListener != null) {
                dialog.setOnKeyListener(mOnKeyListener);
            }
            */
        }

        private fun createListView(dialog: 对话框控制器) {
            val listView =
                mInflater.inflate(dialog.mListLayout, null) as 回收列表视图
            val adapter: ListAdapter?

            if (mIsMultiChoice) {
                if (mCursor == null) {
                    adapter = object : ArrayAdapter<CharSequence?>(
                        mContext, dialog.mMultiChoiceItemLayout, android.R.id.text1, mItems!!
                    ) {
                        override fun getView(
                            position: Int,
                            convertView: View?,
                            parent: ViewGroup
                        ): View {
                            val view = super.getView(position, convertView, parent)
                            if (mCheckedItems != null) {
                                val isItemChecked = mCheckedItems!![position]
                                if (isItemChecked) {
                                    listView.setItemChecked(position, true)
                                }
                            }
                            return view
                        }
                    }
                } else {
                    adapter = object : CursorAdapter(mContext, mCursor, false) {
                        private var mLabelIndex = 0
                        private var mIsCheckedIndex = 0

                        init {
                            val cursor = getCursor()
                            mLabelIndex = cursor.getColumnIndexOrThrow(mLabelColumn)
                            mIsCheckedIndex = cursor.getColumnIndexOrThrow(mIsCheckedColumn)
                        }

                        override fun bindView(view: View, context: Context?, cursor: Cursor) {
                            val text = view.findViewById<View?>(
                                android.R.id.text1
                            ) as CheckedTextView
                            text.setText(cursor.getString(mLabelIndex))
                            listView.setItemChecked(
                                cursor.getPosition(),
                                cursor.getInt(mIsCheckedIndex) == 1
                            )
                        }

                        override fun newView(
                            context: Context?,
                            cursor: Cursor?,
                            parent: ViewGroup?
                        ): View? {
                            return mInflater.inflate(
                                dialog.mMultiChoiceItemLayout,
                                parent, false
                            )
                        }
                    }
                }
            } else {
                val layout: Int
                if (mIsSingleChoice) {
                    layout = dialog.mSingleChoiceItemLayout
                } else {
                    layout = dialog.mListItemLayout
                }

                if (mCursor != null) {
                    adapter = SimpleCursorAdapter(
                        mContext, layout, mCursor,
                        arrayOf<String?>(mLabelColumn), intArrayOf(android.R.id.text1)
                    )
                } else if (mAdapter != null) {
                    adapter = mAdapter
                } else {
                    adapter = 对话框控制器.CheckedItemAdapter(
                        mContext,
                        layout,
                        android.R.id.text1,
                        mItems!!
                    )
                }
            }

            if (mOnPrepareListViewListener != null) {
                mOnPrepareListViewListener!!.onPrepareListView(listView)
            }

            /* Don't directly set the adapter on the ListView as we might
             * want to add a footer to the ListView later.
             */
            dialog.mAdapter = adapter
            dialog.mCheckedItem = mCheckedItem

            if (mOnClickListener != null) {
                listView.setOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        v: View?,
                        position: Int,
                        id: Long
                    ) {
                        mOnClickListener!!.onClick(dialog.mDialog, position)
                        if (!mIsSingleChoice) {
                            dialog.mDialog.dismiss()
                        }
                    }
                })
            } else if (mOnCheckboxClickListener != null) {
                listView.setOnItemClickListener(object : OnItemClickListener {
                    override fun onItemClick(
                        parent: AdapterView<*>?,
                        v: View?,
                        position: Int,
                        id: Long
                    ) {
                        if (mCheckedItems != null) {
                            mCheckedItems!![position] = listView.isItemChecked(position)
                        }
                        mOnCheckboxClickListener!!.onClick(
                            dialog.mDialog, position, listView.isItemChecked(position)
                        )
                    }
                })
            }

            // Attach a given OnItemSelectedListener to the ListView
            if (mOnItemSelectedListener != null) {
                listView.setOnItemSelectedListener(mOnItemSelectedListener)
            }

            if (mIsSingleChoice) {
                listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE)
            } else if (mIsMultiChoice) {
                listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE)
            }
            dialog.listView = listView
        }
    }

    private class CheckedItemAdapter(
        context: Context, resource: Int, textViewResourceId: Int,
        objects: Array<CharSequence?>
    ) : ArrayAdapter<CharSequence?>(context, resource, textViewResourceId, objects) {
        override fun hasStableIds(): Boolean {
            return true
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }
    }

    companion object {
        private fun shouldCenterSingleButton(context: Context): Boolean {
            val outValue = TypedValue()
            context.getTheme().resolveAttribute(R.attr.alertDialogCenterButtons, outValue, true)
            return outValue.data != 0
        }

        fun canTextInput(v: View): Boolean {
            var v = v
            if (v.onCheckIsTextEditor()) {
                return true
            }

            if (v !is ViewGroup) {
                return false
            }

            val vg = v
            var i = vg.getChildCount()
            while (i > 0) {
                i--
                v = vg.getChildAt(i)
                if (canTextInput(v)) {
                    return true
                }
            }

            return false
        }

        fun manageScrollIndicators(v: View, upIndicator: View?, downIndicator: View?) {
            if (upIndicator != null) {
                upIndicator.setVisibility(
                    if (v.canScrollVertically(-1)) View.VISIBLE else View.INVISIBLE
                )
            }
            if (downIndicator != null) {
                downIndicator.setVisibility(
                    if (v.canScrollVertically(1)) View.VISIBLE else View.INVISIBLE
                )
            }
        }
    }
}
