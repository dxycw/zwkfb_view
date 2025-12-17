package 安卓.视图

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews.RemoteView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.withStyledAttributes
import zwkfb.view.R
import java.lang.ref.WeakReference

/**
 * 创建时间：2025年11月20日.
 *
 * 描述：视图存根
 *
 * 版本：0.0.8
 * @author dxyc
 */
@RemoteView
final class 视图存根 : View {

    @get:IdRes
    private var mInflatedId: Int = 0
    @get:LayoutRes
    private var mLayoutResource: Int = 0

    private var mInflatedViewRef: WeakReference<View?>? = null
    private var mInflater: LayoutInflater? = null
    private var mInflateListener: OnInflateListener? = null

    constructor(context: Context) : this(context,0)

    constructor(context: Context, @LayoutRes layoutResource: Int = 0) : this(context, null) {
        this.mLayoutResource = layoutResource
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context, attrs, defStyleAttr,0)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context) {

        val a = context.obtainStyledAttributes(
            attrs, R.styleable.视图存根
        )
        mInflatedId = a.getResourceId(R.styleable.视图存根_android_inflatedId, NO_ID)
        mLayoutResource = a.getResourceId(R.styleable.视图存根_android_layout, 0)
        setId(a.getResourceId(R.styleable.视图存根_android_id, NO_ID))
        a.recycle()


        setVisibility(GONE)
        setWillNotDraw(true)
    }

    @IdRes
    fun getInflatedId(): Int {
        return mInflatedId
    }

    fun setInflatedId(@IdRes inflatedId: Int) {
        mInflatedId = inflatedId
    }

    /** @hide
     */
    fun setInflatedIdAsync(@IdRes inflatedId: Int): Runnable? {
        this.mInflatedId = inflatedId
        return null
    }

    @LayoutRes
    fun getLayoutResource(): Int {
        return mLayoutResource
    }

    fun setLayoutResource(@LayoutRes layoutResource: Int) {
        mLayoutResource = layoutResource
    }

    /** @hide
     */
    fun setLayoutResourceAsync(@LayoutRes layoutResource: Int): Runnable? {
        this.mLayoutResource = layoutResource
        return null
    }

    /**
     * Set [LayoutInflater] to use in [.inflate], or `null`
     * to use the default.
     */
    fun setLayoutInflater(inflater: LayoutInflater?) {
        mInflater = inflater
    }

    /**
     * Get current [LayoutInflater] used in [.inflate].
     */
    fun getLayoutInflater(): LayoutInflater? {
        return mInflater
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(0, 0)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }

    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
    }

    /**
     * When visibility is set to [.VISIBLE] or [.INVISIBLE],
     * [.inflate] is invoked and this StubbedView is replaced in its parent
     * by the inflated layout resource. After that calls to this function are passed
     * through to the inflated view.
     *
     * @param visibility One of [.VISIBLE], [.INVISIBLE], or [.GONE].
     *
     * @see .inflate
     */
    override fun setVisibility(visibility: Int) {
        if (mInflatedViewRef != null) {
            val view = mInflatedViewRef!!.get()
            if (view != null) {
                view.setVisibility(visibility)
            } else {
                throw IllegalStateException("setVisibility called on un-referenced view")
            }
        } else {
            super.setVisibility(visibility)
            if (visibility == VISIBLE || visibility == INVISIBLE) {
                inflate()
            }
        }
    }

    /** @hide
     */
    fun setVisibilityAsync(visibility: Int): Runnable? {
        if (visibility == VISIBLE || visibility == INVISIBLE) {
            val parent = getParent() as ViewGroup?
            return ViewReplaceRunnable(inflateViewNoAdd(parent))
        } else {
            return null
        }
    }

    private fun inflateViewNoAdd(parent: ViewGroup?): View {
        val factory: LayoutInflater?
        if (this.mInflater != null) {
            factory = this.mInflater
        } else {
            factory = LayoutInflater.from(getContext())
        }
        val view = factory!!.inflate(this.mLayoutResource, parent, false)

        if (this.mInflatedId != NO_ID) {
            view.setId(this.mInflatedId)
        }
        return view
    }

    private fun replaceSelfWithView(view: View?, parent: ViewGroup) {
        val index = parent.indexOfChild(this)
        parent.removeViewInLayout(this)

        val layoutParams = getLayoutParams()
        if (layoutParams != null) {
            parent.addView(view, index, layoutParams)
        } else {
            parent.addView(view, index)
        }
    }

    /**
     * Inflates the layout resource identified by [.getLayoutResource]
     * and replaces this StubbedView in its parent by the inflated layout resource.
     *
     * @return The inflated layout resource.
     */
    fun inflate(): View {
        val viewParent = getParent()

        if (viewParent != null && viewParent is ViewGroup) {
            if (this.mLayoutResource != 0) {
                val parent = viewParent
                val view = inflateViewNoAdd(parent)
                replaceSelfWithView(view, parent)

                mInflatedViewRef = WeakReference<View?>(view)
                if (mInflateListener != null) {
                    mInflateListener!!.onInflate(this, view)
                }

                return view
            } else {
                throw IllegalArgumentException("ViewStub must have a valid layoutResource：$mLayoutResource")
            }
        } else {
            throw IllegalStateException("ViewStub must have a non-null ViewGroup viewParent")
        }
    }

    /**
     * Specifies the inflate listener to be notified after this ViewStub successfully
     * inflated its layout resource.
     *
     * @param inflateListener The OnInflateListener to notify of successful inflation.
     *
     * @see android.view.ViewStub.OnInflateListener
     */
    fun setOnInflateListener(inflateListener: OnInflateListener?) {
        mInflateListener = inflateListener
    }

    /**
     * Listener used to receive a notification after a ViewStub has successfully
     * inflated its layout resource.
     *
     * @see android.view.ViewStub.setOnInflateListener
     */
    interface OnInflateListener {
        /**
         * Invoked after a ViewStub successfully inflated its layout resource.
         * This method is invoked after the inflated view was added to the
         * hierarchy but before the layout pass.
         *
         * @param stub The ViewStub that initiated the inflation.
         * @param inflated The inflated View.
         */
        fun onInflate(stub: 视图存根?, inflated: View?)
    }

    /** @hide
     */
    inner class ViewReplaceRunnable : Runnable {
        private var view: View? = null

        constructor(view: View?) { this.view = view }

        override fun run() {
            replaceSelfWithView(view, (getParent() as ViewGroup?)!!)
        }
    }
}

