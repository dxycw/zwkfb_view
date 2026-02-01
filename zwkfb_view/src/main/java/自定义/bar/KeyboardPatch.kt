@file:Suppress("DEPRECATION")

package 自定义.bar

import android.R
import android.app.Activity
import android.app.Dialog
import android.graphics.Rect
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * 解决底部输入框和软键盘的问题
 * Created by geyifeng on 2017/5/17.
 */
class KeyboardPatch {
    private val mActivity: Activity
    private val mWindow: Window?
    private val mDecorView: View
    private val mContentView: View
    private var mChildView: View? = null

    private var mBarParams: BarParams? = null

    private var paddingLeft = 0
    private var paddingTop = 0
    private var paddingRight = 0
    private var paddingBottom = 0

    private var keyboardHeightPrevious = 0
    private var statusBarHeight = 0
    private var actionBarHeight = 0
    private var navigationBarHeight = 0
    private var navigationAtBottom = false

    private constructor(
        activity: Activity,
        contentView: View = (activity.window.decorView.findViewById<View?>(R.id.content) as FrameLayout).getChildAt(0)
    ) : this(activity, null, "", contentView)

    private constructor(
        activity: Activity,
        dialog: Dialog?,
        tag: String,
        contentView: View? = dialog!!.window!!
            .findViewById<View?>(R.id.content)
    ) {
        this.mActivity = activity
        this.mWindow = if (dialog != null) dialog.window else activity.window
        this.mDecorView = mWindow!!.decorView
        this.mContentView = contentView ?: mWindow.decorView.findViewById<View?>(R.id.content)
        this.mBarParams = if (dialog != null)
            ImmersionBar.with(activity, dialog, tag).barParams
        else
            ImmersionBar.with(activity).barParams
        requireNotNull(mBarParams) { "先使用ImmersionBar初始化" }
    }

    private constructor(activity: Activity?, window: Window?) {
        this.mActivity = activity!!
        this.mWindow = window
        this.mDecorView = mWindow!!.decorView
        val frameLayout = mDecorView.findViewById<View?>(R.id.content) as FrameLayout
        this.mChildView = frameLayout.getChildAt(0)
        this.mContentView = (if (mChildView != null) mChildView else frameLayout)!!

        this.paddingLeft = mContentView.getPaddingLeft()
        this.paddingTop = mContentView.paddingTop
        this.paddingRight = mContentView.getPaddingRight()
        this.paddingBottom = mContentView.paddingBottom

        val barConfig = BarConfig(mActivity)
        this.statusBarHeight = barConfig.statusBarHeight
        this.navigationBarHeight = barConfig.navigationBarHeight
        this.actionBarHeight = barConfig.actionBarHeight
        navigationAtBottom = barConfig.isNavigationAtBottom
    }

    fun setBarParams(barParams: BarParams?) {
        this.mBarParams = barParams
    }

    /**
     * 监听layout变化
     */
    @JvmOverloads
    fun enable(
        mode: Int = (WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    ) {
        mWindow!!.setSoftInputMode(mode)
        //当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时，所要调用的回调函数的接口类
        mDecorView.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    /**
     * 取消监听
     */
    @JvmOverloads
    fun disable(
        mode: Int = (WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    ) {
        mWindow!!.setSoftInputMode(mode)
        mDecorView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener)
    }

    private val onGlobalLayoutListener: OnGlobalLayoutListener = object : OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            //如果布局根节点使用了android:fitsSystemWindows="true"属性或者导航栏不在底部，无需处理
            if (!navigationAtBottom) return
            val r = Rect()
            mDecorView.getWindowVisibleDisplayFrame(r) //获取当前窗口可视区域大小
            var diff: Int
            val keyboardHeight: Int
            var isPopup = false
            if (mBarParams!!.systemWindows) {
                keyboardHeight = mContentView.height - r.bottom - navigationBarHeight
                if (mBarParams!!.onKeyboardListener != null) {
                    if (keyboardHeight > navigationBarHeight) isPopup = true
                    mBarParams!!.onKeyboardListener!!.onKeyboardChange(isPopup, keyboardHeight)
                }
                return
            }
            if (mChildView != null) {
                diff = if (mBarParams!!.isSupportActionBar) mContentView.height + statusBarHeight + actionBarHeight - r.bottom
                else if (mBarParams!!.fits) mContentView.height + statusBarHeight - r.bottom
                else mContentView.height - r.bottom
                keyboardHeight = if (mBarParams!!.fullScreen) diff - navigationBarHeight
                else diff
                if (mBarParams!!.fullScreen && diff == navigationBarHeight) {
                    diff -= navigationBarHeight
                }
                if (keyboardHeight != keyboardHeightPrevious) {
                    mContentView.setPadding(
                        paddingLeft,
                        paddingTop,
                        paddingRight,
                        diff + paddingBottom
                    )
                    keyboardHeightPrevious = keyboardHeight
                    if (mBarParams!!.onKeyboardListener != null) {
                        if (keyboardHeight > navigationBarHeight) isPopup = true
                        mBarParams!!.onKeyboardListener!!.onKeyboardChange(isPopup, keyboardHeight)
                    }
                }
            } else {
                diff = mContentView.height - r.bottom

                if (mBarParams!!.navigationBarEnable && mBarParams!!.navigationBarWithKitkatEnable) {
                    keyboardHeight = if (false || OSUtils.isEMUI3_1) {
                        diff - navigationBarHeight
                    } else {
                        if (!mBarParams!!.fullScreen) diff
                        else diff - navigationBarHeight
                    }
                    if (mBarParams!!.fullScreen && diff == navigationBarHeight) diff -= navigationBarHeight
                } else keyboardHeight = diff
                if (keyboardHeight != keyboardHeightPrevious) {
                    if (mBarParams!!.isSupportActionBar) {
                        mContentView.setPadding(0, statusBarHeight + actionBarHeight, 0, diff)
                    } else if (mBarParams!!.fits) {
                        mContentView.setPadding(0, statusBarHeight, 0, diff)
                    } else mContentView.setPadding(0, 0, 0, diff)
                    keyboardHeightPrevious = keyboardHeight
                    if (mBarParams!!.onKeyboardListener != null) {
                        if (keyboardHeight > navigationBarHeight) isPopup = true
                        mBarParams!!.onKeyboardListener!!.onKeyboardChange(isPopup, keyboardHeight)
                    }
                }
            }
        }
    }

    companion object {
        fun patch(activity: Activity): KeyboardPatch {
            return KeyboardPatch(activity)
        }

        fun patch(activity: Activity, contentView: View): KeyboardPatch {
            return KeyboardPatch(activity, contentView)
        }

        fun patch(activity: Activity, dialog: Dialog, tag: String): KeyboardPatch {
            return KeyboardPatch(activity, dialog, tag)
        }

        fun patch(
            activity: Activity,
            dialog: Dialog?,
            tag: String,
            contentView: View?
        ): KeyboardPatch {
            return KeyboardPatch(activity, dialog, tag, contentView)
        }

        fun patch(activity: Activity?, window: Window?): KeyboardPatch {
            return KeyboardPatch(activity, window)
        }
    }
}
