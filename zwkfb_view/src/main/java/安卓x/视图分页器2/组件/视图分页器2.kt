package 安卓x.视图分页器2.组件

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Build
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.ClassLoaderCreator
import android.util.AttributeSet
import android.util.SparseArray
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.Px
import androidx.annotation.RequiresApi
import androidx.annotation.RestrictTo
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.CollectionInfoCompat
import androidx.core.view.accessibility.AccessibilityViewCommand
import androidx.core.view.accessibility.AccessibilityViewCommand.CommandArguments
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.viewpager2.R
import androidx.viewpager2.adapter.StatefulAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：视图分页器2
 *
 * 版本：0.1.0
 * @author dxyc
 */
/**
 * ViewPager2 replaces [androidx.viewpager.widget.ViewPager], addressing most of its
 * predecessor’s pain-points, including right-to-left layout support, vertical orientation,
 * modifiable Fragment collections, etc.
 *
 * @see androidx.viewpager.widget.ViewPager
 */
class 视图分页器2 : ViewGroup {
    /** @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP_PREFIX)
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(*intArrayOf(ORIENTATION_HORIZONTAL, ORIENTATION_VERTICAL))
    annotation class Orientation

    /** @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP_PREFIX)
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(*[SCROLL_STATE_IDLE, SCROLL_STATE_DRAGGING, SCROLL_STATE_SETTLING])
    annotation class ScrollState

    /** @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP_PREFIX)
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(*intArrayOf(OFFSCREEN_PAGE_LIMIT_DEFAULT))
    @IntRange(from = 1)
    annotation class OffscreenPageLimit

    // reused in layout(...)
    private val mTmpContainerRect = Rect()
    private val mTmpChildRect = Rect()

    private val mExternalPageChangeCallbacks = 组合页面改变回调(3)

    var mCurrentItem: Int = 0
    var mCurrentItemDirty: Boolean = false
    private val mCurrentItemDataSetChangeObserver: AdapterDataObserver =
        object : DataSetChangeObserver() {
            override fun onChanged() {
                mCurrentItemDirty = true
                mScrollEventAdapter!!.notifyDataSetChangeHappened()
            }
        }

    private var mLayoutManager: LinearLayoutManager? = null
    private var mPendingCurrentItem = RecyclerView.NO_POSITION
    private var mPendingAdapterState: Parcelable? = null
    @JvmField
    var mRecyclerView: RecyclerView? = null
    private var mPagerSnapHelper: PagerSnapHelper? = null
    private var mScrollEventAdapter: 滚动事件适配器? = null
    private var mPageChangeEventDispatcher: 组合页面改变回调? = null
    private var mFakeDragger: 模拟拖动? = null
    private var mPageTransformerAdapter: 页面转换器适配器? = null
    private var mSavedItemAnimator: ItemAnimator? = null
    private var mSavedItemAnimatorPresent = false
    private var mUserInputEnabled = true

    @OffscreenPageLimit
    private var mOffscreenPageLimit: Int = OFFSCREEN_PAGE_LIMIT_DEFAULT
    private var mAccessibilityProvider: AccessibilityProvider? =
        null // to avoid creation of a synthetic accessor

    constructor(context: Context) : super(context) {
        initialize(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initialize(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initialize(context, attrs)
    }

    @RequiresApi(21)
    constructor(
        context: Context, attrs: AttributeSet?, defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initialize(context, attrs)
    }

    private fun initialize(context: Context, attrs: AttributeSet?) {
        mAccessibilityProvider = if (sFeatureEnhancedA11yEnabled)
            PageAwareAccessibilityProvider()
        else
            BasicAccessibilityProvider()

        mRecyclerView = RecyclerViewImpl(context)
        mRecyclerView!!.setId(ViewCompat.generateViewId())
        mRecyclerView!!.setDescendantFocusability(FOCUS_BEFORE_DESCENDANTS)

        mLayoutManager = LinearLayoutManagerImpl(context)
        mRecyclerView!!.setLayoutManager(mLayoutManager)
        mRecyclerView!!.setScrollingTouchSlop(RecyclerView.TOUCH_SLOP_PAGING)
        setOrientation(context, attrs)

        mRecyclerView!!.setLayoutParams(
            LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        )
        mRecyclerView!!.addOnChildAttachStateChangeListener(enforceChildFillListener())

        // Create ScrollEventAdapter before attaching PagerSnapHelper to RecyclerView, because the
        // attach process calls PagerSnapHelperImpl.findSnapView, which uses the mScrollEventAdapter
        mScrollEventAdapter = 滚动事件适配器(this)
        // Create FakeDrag before attaching PagerSnapHelper, same reason as above
        mFakeDragger = 模拟拖动(this, mScrollEventAdapter!!, mRecyclerView!!)
        mPagerSnapHelper = PagerSnapHelperImpl()
        mPagerSnapHelper!!.attachToRecyclerView(mRecyclerView)
        // Add mScrollEventAdapter after attaching mPagerSnapHelper to mRecyclerView, because we
        // don't want to respond on the events sent out during the attach process
        mRecyclerView!!.addOnScrollListener(mScrollEventAdapter!!)

        mPageChangeEventDispatcher = 组合页面改变回调(3)
        mScrollEventAdapter!!.setOnPageChangeCallback(mPageChangeEventDispatcher)

        // Callback that updates mCurrentItem after swipes. Also triggered in other cases, but in
        // all those cases mCurrentItem will only be overwritten with the same value.
        val currentItemUpdater: OnPageChangeCallback = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (mCurrentItem != position) {
                    mCurrentItem = position
                    mAccessibilityProvider!!.onSetNewCurrentItem()
                }
            }

            override fun onPageScrollStateChanged(newState: Int) {
                if (newState == SCROLL_STATE_IDLE) {
                    updateCurrentItem()
                }
            }
        }

        // Prevents focus from remaining on a no-longer visible page
        val focusClearer: OnPageChangeCallback = object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                clearFocus()
                if (hasFocus()) { // if clear focus did not succeed
                    mRecyclerView!!.requestFocus(FOCUS_FORWARD)
                }
            }
        }

        // Add currentItemUpdater before mExternalPageChangeCallbacks, because we need to update
        // internal state first
        mPageChangeEventDispatcher!!.addOnPageChangeCallback(currentItemUpdater)
        mPageChangeEventDispatcher!!.addOnPageChangeCallback(focusClearer)
        // Allow a11y to register its listeners after currentItemUpdater (so it has the
        // right data). TODO: replace ordering comments with a test.
        mAccessibilityProvider!!.onInitialize(mPageChangeEventDispatcher!!, mRecyclerView!!)
        mPageChangeEventDispatcher!!.addOnPageChangeCallback(mExternalPageChangeCallbacks)

        // Add mPageTransformerAdapter after mExternalPageChangeCallbacks, because page transform
        // events must be fired after scroll events
        mPageTransformerAdapter = 页面转换器适配器(mLayoutManager!!)
        mPageChangeEventDispatcher!!.addOnPageChangeCallback(mPageTransformerAdapter)

        attachViewToParent(mRecyclerView, 0, mRecyclerView!!.getLayoutParams())
    }

    /**
     * A lot of places in code rely on an assumption that the page fills the whole ViewPager2.
     *
     * TODO(b/70666617) Allow page width different than width/height 100%/100%
     */
    private fun enforceChildFillListener(): OnChildAttachStateChangeListener {
        return object : OnChildAttachStateChangeListener {
            override fun onChildViewAttachedToWindow(view: View) {
                val layoutParams =
                    view.getLayoutParams() as RecyclerView.LayoutParams
                check(
                    !(layoutParams.width != LayoutParams.MATCH_PARENT
                            || layoutParams.height != LayoutParams.MATCH_PARENT)
                ) { "Pages must fill the whole ViewPager2 (use match_parent)" }
            }

            override fun onChildViewDetachedFromWindow(view: View) {
                // nothing
            }
        }
    }

    @RequiresApi(23)
    override fun getAccessibilityClassName(): CharSequence? {
        if (mAccessibilityProvider!!.handlesGetAccessibilityClassName()) {
            return mAccessibilityProvider!!.onGetAccessibilityClassName()
        }
        return super.getAccessibilityClassName()
    }

    private fun setOrientation(context: Context, attrs: AttributeSet?) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.ViewPager2)
        if (Build.VERSION.SDK_INT >= 29) {
            saveAttributeDataForStyleable(context, R.styleable.ViewPager2, attrs, a, 0, 0)
        }
        try {
            this.orientation = a.getInt(
                R.styleable.ViewPager2_android_orientation,
                ORIENTATION_HORIZONTAL
            )
        } finally {
            a.recycle()
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        val ss = SavedState(superState)

        ss.mRecyclerViewId = mRecyclerView!!.getId()
        ss.mCurrentItem =
            if (mPendingCurrentItem == RecyclerView.NO_POSITION) mCurrentItem else mPendingCurrentItem

        if (mPendingAdapterState != null) {
            ss.mAdapterState = mPendingAdapterState
        } else {
            val adapter = mRecyclerView!!.getAdapter()
            if (adapter is StatefulAdapter) {
                ss.mAdapterState = (adapter as StatefulAdapter).saveState()
            }
        }

        return ss
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }

        val ss = state
        super.onRestoreInstanceState(ss.getSuperState())
        mPendingCurrentItem = ss.mCurrentItem
        mPendingAdapterState = ss.mAdapterState
    }

    private fun restorePendingState() {
        if (mPendingCurrentItem == RecyclerView.NO_POSITION) {
            // No state to restore, or state is already restored
            return
        }
        val adapter = this.adapter
        if (adapter == null) {
            return
        }
        if (mPendingAdapterState != null) {
            if (adapter is StatefulAdapter) {
                (adapter as StatefulAdapter).restoreState(mPendingAdapterState!!)
            }
            mPendingAdapterState = null
        }
        // Now we have an adapter, we can clamp the pending current item and set it
        mCurrentItem = max(0, min(mPendingCurrentItem, adapter.getItemCount() - 1))
        mPendingCurrentItem = RecyclerView.NO_POSITION
        mRecyclerView!!.scrollToPosition(mCurrentItem)
        mAccessibilityProvider!!.onRestorePendingState()
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable?>) {
        // RecyclerView changed an id, so we need to reflect that in the saved state
        val state = container.get(getId())
        if (state is SavedState) {
            val previousRvId = state.mRecyclerViewId
            val currentRvId = mRecyclerView!!.getId()
            container.put(currentRvId, container.get(previousRvId))
            container.remove(previousRvId)
        }

        super.dispatchRestoreInstanceState(container)

        // State of ViewPager2 and its child (RecyclerView) has been restored now
        restorePendingState()
    }

    internal class SavedState : BaseSavedState {
        var mRecyclerViewId: Int = 0
        var mCurrentItem: Int = 0
        var mAdapterState: Parcelable? = null

        @RequiresApi(24)
        constructor(source: Parcel, loader: ClassLoader?) : super(source, loader) {
            readValues(source, loader)
        }

        constructor(source: Parcel) : super(source) {
            readValues(source, null)
        }

        constructor(superState: Parcelable?) : super(superState)

        private fun readValues(source: Parcel, loader: ClassLoader?) {
            mRecyclerViewId = source.readInt()
            mCurrentItem = source.readInt()
            mAdapterState = source.readParcelable<Parcelable?>(loader)
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(mRecyclerViewId)
            out.writeInt(mCurrentItem)
            out.writeParcelable(mAdapterState, flags)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState?> =
                object : ClassLoaderCreator<SavedState?> {
                    override fun createFromParcel(
                        source: Parcel,
                        loader: ClassLoader?
                    ): SavedState {
                        return if (Build.VERSION.SDK_INT >= 24)
                            SavedState(source, loader)
                        else
                            SavedState(source)
                    }

                    override fun createFromParcel(source: Parcel): SavedState {
                        return createFromParcel(source, null)
                    }

                    override fun newArray(size: Int): Array<SavedState?> {
                        return arrayOfNulls<SavedState>(size)
                    }
                }
        }
    }

    private fun registerCurrentItemDataSetTracker(adapter: RecyclerView.Adapter<*>?) {
        if (adapter != null) {
            adapter.registerAdapterDataObserver(mCurrentItemDataSetChangeObserver)
        }
    }

    private fun unregisterCurrentItemDataSetTracker(adapter: RecyclerView.Adapter<*>?) {
        if (adapter != null) {
            adapter.unregisterAdapterDataObserver(mCurrentItemDataSetChangeObserver)
        }
    }

    var adapter: RecyclerView.Adapter<*>?
        get() = mRecyclerView!!.getAdapter()
        /**
         *
         * Set a new adapter to provide page views on demand.
         *
         *
         * If you're planning to use [Fragments][androidx.fragment.app.Fragment] as pages,
         * implement [FragmentStateAdapter][androidx.viewpager2.adapter.FragmentStateAdapter]. If
         * your pages are Views, implement [Adapter] as usual.
         *
         *
         * If your pages contain LayoutTransitions, then those LayoutTransitions *must* have
         * `animateParentHierarchy` set to `false`. Note that if you have a ViewGroup with
         * `animateLayoutChanges="true"` in your layout xml file, a LayoutTransition is added
         * automatically to that ViewGroup. You will need to manually call [ ][android.animation.LayoutTransition.setAnimateParentHierarchy] on that ViewGroup after you inflated
         * the xml layout, like this:
         *
         * <pre>
         * View view = layoutInflater.inflate(R.layout.page, parent, false);
         * ViewGroup viewGroup = view.findViewById(R.id.animated_viewgroup);
         * viewGroup.getLayoutTransition().setAnimateParentHierarchy(false);
        </pre> *
         *
         * @param adapter The adapter to use, or `null` to remove the current adapter
         * @see androidx.viewpager2.adapter.FragmentStateAdapter
         *
         * @see RecyclerView.setAdapter
         */
        set(adapter) {
            val currentAdapter = mRecyclerView!!.getAdapter()
            mAccessibilityProvider!!.onDetachAdapter(currentAdapter)
            unregisterCurrentItemDataSetTracker(currentAdapter)
            mRecyclerView!!.setAdapter(adapter)
            mCurrentItem = 0
            restorePendingState()
            mAccessibilityProvider!!.onAttachAdapter(adapter)
            registerCurrentItemDataSetTracker(adapter)
        }

    override fun onViewAdded(child: View?) {
        // TODO(b/70666620): consider adding a support for Decor views
        throw IllegalStateException(
            javaClass.getSimpleName() + " does not support direct child views"
        )
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // TODO(b/70666622): consider margin support
        // TODO(b/70666626): consider delegating all this to RecyclerView
        // TODO(b/70666625): write automated tests for this

        measureChild(mRecyclerView, widthMeasureSpec, heightMeasureSpec)
        var width = mRecyclerView!!.getMeasuredWidth()
        var height = mRecyclerView!!.getMeasuredHeight()
        val childState = mRecyclerView!!.getMeasuredState()

        width += getPaddingLeft() + getPaddingRight()
        height += getPaddingTop() + getPaddingBottom()

        width = max(width, getSuggestedMinimumWidth())
        height = max(height, getSuggestedMinimumHeight())

        setMeasuredDimension(
            resolveSizeAndState(width, widthMeasureSpec, childState),
            resolveSizeAndState(
                height, heightMeasureSpec,
                childState shl MEASURED_HEIGHT_STATE_SHIFT
            )
        )
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        val width = mRecyclerView!!.getMeasuredWidth()
        val height = mRecyclerView!!.getMeasuredHeight()

        // TODO(b/70666626): consider delegating padding handling to the RecyclerView to avoid
        // an unnatural page transition effect: http://shortn/_Vnug3yZpQT
        mTmpContainerRect.left = getPaddingLeft()
        mTmpContainerRect.right = r - l - getPaddingRight()
        mTmpContainerRect.top = getPaddingTop()
        mTmpContainerRect.bottom = b - t - getPaddingBottom()

        Gravity.apply(Gravity.TOP or Gravity.START, width, height, mTmpContainerRect, mTmpChildRect)
        mRecyclerView!!.layout(
            mTmpChildRect.left, mTmpChildRect.top, mTmpChildRect.right,
            mTmpChildRect.bottom
        )

        if (mCurrentItemDirty) {
            updateCurrentItem()
        }
    }

    /** Updates [.mCurrentItem] based on what is currently visible in the viewport.  */
    @SuppressLint("WrongConstant")
    fun updateCurrentItem() {
        checkNotNull(mPagerSnapHelper) { "Design assumption violated." }

        val snapView = mPagerSnapHelper!!.findSnapView(mLayoutManager)
        if (snapView == null) {
            return  // nothing we can do
        }
        val snapPosition = mLayoutManager!!.getPosition(snapView)

        if (snapPosition != mCurrentItem && this.scrollState == SCROLL_STATE_IDLE) {
            /** TODO: revisit if push to [滚动事件适配器] / separate component  */
            mPageChangeEventDispatcher!!.onPageSelected(snapPosition)
        }

        mCurrentItemDirty = false
    }

    @get:SuppressLint("WrongConstant")
    val pageSize: Int
        get() {
            val rv = mRecyclerView!!
            return if (this.orientation == ORIENTATION_HORIZONTAL)
                rv.getWidth() - rv.getPaddingLeft() - rv.getPaddingRight()
            else
                rv.getHeight() - rv.getPaddingTop() - rv.getPaddingBottom()
        }

    @get:ViewPager2.Orientation
    @get:SuppressLint("WrongConstant")
    var orientation: Int
        get() = mLayoutManager!!.getOrientation()
        /**
         * Sets the orientation of the ViewPager2.
         *
         * @param orientation [.ORIENTATION_HORIZONTAL] or [.ORIENTATION_VERTICAL]
         */
        set(orientation) {
            mLayoutManager!!.setOrientation(orientation)
            mAccessibilityProvider!!.onSetOrientation()
        }

    val isRtl: Boolean
        get() = mLayoutManager!!.getLayoutDirection() == ViewCompat.LAYOUT_DIRECTION_RTL

    /**
     * Set the currently selected page. If `smoothScroll = true`, will perform a smooth
     * animation from the current item to the new item. Silently ignored if the adapter is not set
     * or empty. Clamps item to the bounds of the adapter.
     *
     * @param item Item index to select
     * @param smoothScroll True to smoothly scroll to the new item, false to transition immediately
     */
    fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        check(!this.isFakeDragging) {
            ("Cannot change current item when ViewPager2 is fake "
                    + "dragging")
        }
        setCurrentItemInternal(item, smoothScroll)
    }

    fun setCurrentItemInternal(item: Int, smoothScroll: Boolean) {
        // 1. Preprocessing (check state, validate item, decide if update is necessary, etc)

        var item = item
        val adapter = this.adapter
        if (adapter == null) {
            // Update the pending current item if we're still waiting for the adapter
            if (mPendingCurrentItem != RecyclerView.NO_POSITION) {
                mPendingCurrentItem = max(item, 0)
            }
            return
        }
        if (adapter.getItemCount() <= 0) {
            // Adapter is empty
            return
        }
        item = max(item, 0)
        item = min(item, adapter.getItemCount() - 1)

        if (item == mCurrentItem && mScrollEventAdapter!!.isIdle) {
            // Already at the correct page
            return
        }
        if (item == mCurrentItem && smoothScroll) {
            // Already scrolling to the correct page, but not yet there. Only handle instant scrolls
            // because then we need to interrupt the current smooth scroll.
            return
        }

        // 2. Update the item internally
        var previousItem = mCurrentItem.toDouble()
        mCurrentItem = item
        mAccessibilityProvider!!.onSetNewCurrentItem()

        if (!mScrollEventAdapter!!.isIdle) {
            // Scroll in progress, overwrite previousItem with actual current position
            previousItem = mScrollEventAdapter!!.relativeScrollPosition
        }

        // 3. Perform the necessary scroll actions on RecyclerView
        mScrollEventAdapter!!.notifyProgrammaticScroll(item, smoothScroll)
        if (!smoothScroll) {
            mRecyclerView!!.scrollToPosition(item)
            return
        }

        // For smooth scroll, pre-jump to nearby item for long jumps.
        if (abs(item - previousItem) > 3) {
            mRecyclerView!!.scrollToPosition(if (item > previousItem) item - 3 else item + 3)
            // TODO(b/114361680): call smoothScrollToPosition synchronously (blocked by b/114019007)
            mRecyclerView!!.post(SmoothScrollToPosition(item, mRecyclerView!!))
        } else {
            mRecyclerView!!.smoothScrollToPosition(item)
        }
    }

    var currentItem: Int
        /**
         * Returns the currently selected page. If no page can sensibly be selected because there is no
         * adapter or the adapter is empty, returns 0.
         *
         * @return Currently selected page
         */
        get() = mCurrentItem
        /**
         * Set the currently selected page. If the ViewPager has already been through its first
         * layout with its current adapter there will be a smooth animated transition between
         * the current item and the specified item. Silently ignored if the adapter is not set or
         * empty. Clamps item to the bounds of the adapter.
         *
         * TODO(b/123069219): verify first layout behavior
         *
         * @param item Item index to select
         */
        set(item) {
            setCurrentItem(item, true)
        }

    @get:ScrollState
    @get:SuppressLint("WrongConstant")
    val scrollState: Int
        /**
         * Returns the current scroll state of the ViewPager2. Returned value is one of can be one of
         * [.SCROLL_STATE_IDLE], [.SCROLL_STATE_DRAGGING] or [.SCROLL_STATE_SETTLING].
         *
         * @return The scroll state that was last dispatched to [         ][ViewPager2.OnPageChangeCallback.onPageScrollStateChanged]
         */
        get() = mScrollEventAdapter!!.scrollState

    /**
     * Start a fake drag of the pager.
     *
     *
     * A fake drag can be useful if you want to synchronize the motion of the ViewPager2 with the
     * touch scrolling of another view, while still letting the ViewPager2 control the snapping
     * motion and fling behavior. (e.g. parallax-scrolling tabs.) Call [.fakeDragBy] to
     * simulate the actual drag motion. Call [.endFakeDrag] to complete the fake drag and
     * fling as necessary.
     *
     *
     * A fake drag can be interrupted by a real drag. From that point on, all calls to `fakeDragBy` and `endFakeDrag` will be ignored until the next fake drag is started by
     * calling `beginFakeDrag`. If you need the ViewPager2 to ignore touch events and other
     * user input during a fake drag, use [.setUserInputEnabled]. If a real or fake
     * drag is already in progress, this method will return `false`.
     *
     * @return `true` if the fake drag began successfully, `false` if it could not be
     * started
     *
     * @see .fakeDragBy
     * @see .endFakeDrag
     * @see .isFakeDragging
     */
    fun beginFakeDrag(): Boolean {
        return mFakeDragger!!.beginFakeDrag()
    }

    /**
     * Fake drag by an offset in pixels. You must have called [.beginFakeDrag] first. Drag
     * happens in the direction of the orientation. Positive offsets will drag to the previous page,
     * negative values to the next page, with one exception: if layout direction is set to RTL and
     * the ViewPager2's orientation is horizontal, then the behavior will be inverted. This matches
     * the deltas of touch events that would cause the same real drag.
     *
     *
     * If the pager is not in the fake dragging state anymore, it ignores this call and returns
     * `false`.
     *
     * @param offsetPxFloat Offset in pixels to drag by
     * @return `true` if the fake drag was executed. If `false` is returned, it means
     * there was no fake drag to end.
     *
     * @see .beginFakeDrag
     * @see .endFakeDrag
     * @see .isFakeDragging
     */
    fun fakeDragBy(@SuppressLint("SupportAnnotationUsage") @Px offsetPxFloat: Float): Boolean {
        return mFakeDragger!!.fakeDragBy(offsetPxFloat)
    }

    /**
     * End a fake drag of the pager.
     *
     * @return `true` if the fake drag was ended. If `false` is returned, it means there
     * was no fake drag to end.
     *
     * @see .beginFakeDrag
     * @see .fakeDragBy
     * @see .isFakeDragging
     */
    fun endFakeDrag(): Boolean {
        return mFakeDragger!!.endFakeDrag()
    }

    val isFakeDragging: Boolean
        /**
         * Returns `true` if a fake drag is in progress.
         *
         * @return `true` if currently in a fake drag, `false` otherwise.
         * @see .beginFakeDrag
         * @see .fakeDragBy
         * @see .endFakeDrag
         */
        get() = mFakeDragger!!.isFakeDragging

    /**
     * Snaps the ViewPager2 to the closest page
     */
    fun snapToPage() {
        // Method copied from PagerSnapHelper#snapToTargetExistingView
        // When fixing something here, make sure to update that method as well
        val view = mPagerSnapHelper!!.findSnapView(mLayoutManager)
        if (view == null) {
            return
        }
        val snapDistance = mPagerSnapHelper!!.calculateDistanceToFinalSnap(mLayoutManager!!, view)
        if (snapDistance!![0] != 0 || snapDistance[1] != 0) {
            mRecyclerView!!.smoothScrollBy(snapDistance[0], snapDistance[1])
        }
    }

    var isUserInputEnabled: Boolean
        /**
         * Returns if user initiated scrolling between pages is enabled. Enabled by default.
         *
         * @return `true` if users can scroll the ViewPager2, `false` otherwise
         * @see .setUserInputEnabled
         */
        get() = mUserInputEnabled
        /**
         * Enable or disable user initiated scrolling. This includes touch input (scroll and fling
         * gestures) and accessibility input. Disabling keyboard input is not yet supported. When user
         * initiated scrolling is disabled, programmatic scrolls through [setCurrentItem][.setCurrentItem] still work. By default, user initiated scrolling is enabled.
         *
         * @param enabled `true` to allow user initiated scrolling, `false` to block user
         * initiated scrolling
         * @see .isUserInputEnabled
         */
        set(enabled) {
            mUserInputEnabled = enabled
            mAccessibilityProvider!!.onSetUserInputEnabled()
        }

    @get:OffscreenPageLimit
    var offscreenPageLimit: Int
        /**
         * Returns the number of pages that will be retained to either side of the current page in the
         * view hierarchy in an idle state. Defaults to [.OFFSCREEN_PAGE_LIMIT_DEFAULT].
         *
         * @return How many pages will be kept offscreen on either side
         * @see .setOffscreenPageLimit
         */
        get() = mOffscreenPageLimit
        /**
         *
         * Set the number of pages that should be retained to either side of the currently visible
         * page(s). Pages beyond this limit will be recreated from the adapter when needed. Set this to
         * [.OFFSCREEN_PAGE_LIMIT_DEFAULT] to use RecyclerView's caching strategy. The given value
         * must either be larger than 0, or `#OFFSCREEN_PAGE_LIMIT_DEFAULT`.
         *
         *
         * Pages within `limit` pages away from the current page are created and added to the
         * view hierarchy, even though they are not visible on the screen. Pages outside this limit will
         * be removed from the view hierarchy, but the `ViewHolder`s will be recycled as usual by
         * [RecyclerView].
         *
         *
         * This is offered as an optimization. If you know in advance the number of pages you will
         * need to support or have lazy-loading mechanisms in place on your pages, tweaking this setting
         * can have benefits in perceived smoothness of paging animations and interaction. If you have a
         * small number of pages (3-4) that you can keep active all at once, less time will be spent in
         * layout for newly created view subtrees as the user pages back and forth.
         *
         *
         * You should keep this limit low, especially if your pages have complex layouts. By default
         * it is set to `OFFSCREEN_PAGE_LIMIT_DEFAULT`.
         *
         * @param limit How many pages will be kept offscreen on either side. Valid values are all
         * values `>= 1` and [.OFFSCREEN_PAGE_LIMIT_DEFAULT]
         * @throws IllegalArgumentException If the given limit is invalid
         * @see .getOffscreenPageLimit
         */
        set(limit) {
            require(!(limit < 1 && limit != OFFSCREEN_PAGE_LIMIT_DEFAULT)) { "Offscreen page limit must be OFFSCREEN_PAGE_LIMIT_DEFAULT or a number > 0" }
            mOffscreenPageLimit = limit
            // Trigger layout so prefetch happens through getExtraLayoutSize()
            mRecyclerView!!.requestLayout()
        }

    override fun canScrollHorizontally(direction: Int): Boolean {
        return mRecyclerView!!.canScrollHorizontally(direction)
    }

    override fun canScrollVertically(direction: Int): Boolean {
        return mRecyclerView!!.canScrollVertically(direction)
    }

    /**
     * Add a callback that will be invoked whenever the page changes or is incrementally
     * scrolled. See [ViewPager2.OnPageChangeCallback].
     *
     *
     * Components that add a callback should take care to remove it when finished.
     *
     * @param callback callback to add
     */
    fun registerOnPageChangeCallback(callback: OnPageChangeCallback) {
        mExternalPageChangeCallbacks.addOnPageChangeCallback(callback)
    }

    /**
     * Remove a callback that was previously added via
     * [.registerOnPageChangeCallback].
     *
     * @param callback callback to remove
     */
    fun unregisterOnPageChangeCallback(callback: OnPageChangeCallback) {
        mExternalPageChangeCallbacks.removeOnPageChangeCallback(callback)
    }

    /**
     * Sets a [PageTransformer] that will be called for each attached page whenever the
     * scroll position is changed. This allows the application to apply custom property
     * transformations to each page, overriding the default sliding behavior.
     *
     *
     * Note: setting a [PageTransformer] disables data-set change animations to prevent
     * conflicts between the two animation systems. Setting a `null` transformer will restore
     * data-set change animations.
     *
     * @param transformer PageTransformer that will modify each page's animation properties
     *
     * @see MarginPageTransformer
     *
     * @see CompositePageTransformer
     */
    fun setPageTransformer(transformer: PageTransformer?) {
        if (transformer != null) {
            if (!mSavedItemAnimatorPresent) {
                mSavedItemAnimator = mRecyclerView!!.getItemAnimator()
                mSavedItemAnimatorPresent = true
            }
            mRecyclerView!!.setItemAnimator(null)
        } else {
            if (mSavedItemAnimatorPresent) {
                mRecyclerView!!.setItemAnimator(mSavedItemAnimator)
                mSavedItemAnimator = null
                mSavedItemAnimatorPresent = false
            }
        }

        // TODO: add support for reverseDrawingOrder: b/112892792
        // TODO: add support for pageLayerType: b/112893074
        if (transformer === mPageTransformerAdapter!!.pageTransformer) {
            return
        }
        mPageTransformerAdapter!!.pageTransformer = transformer
        requestTransform()
    }

    /**
     * Trigger a call to the registered [PageTransformer]'s [ ][PageTransformer.transformPage] method. Call this when something
     * has changed which has invalidated the transformations defined by the `PageTransformer`
     * that did not trigger a page scroll.
     */
    fun requestTransform() {
        if (mPageTransformerAdapter!!.pageTransformer == null) {
            return
        }
        val relativePosition = mScrollEventAdapter!!.relativeScrollPosition
        val position = relativePosition.toInt()
        val positionOffset = (relativePosition - position).toFloat()
        val positionOffsetPx = Math.round(this.pageSize * positionOffset)
        mPageTransformerAdapter!!.onPageScrolled(position, positionOffset, positionOffsetPx)
    }

    @RequiresApi(17)
    override fun setLayoutDirection(layoutDirection: Int) {
        super.setLayoutDirection(layoutDirection)
        mAccessibilityProvider!!.onSetLayoutDirection()
    }

    override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo?) {
        super.onInitializeAccessibilityNodeInfo(info)
        mAccessibilityProvider!!.onInitializeAccessibilityNodeInfo(info)
    }

    @RequiresApi(16)
    override fun performAccessibilityAction(action: Int, arguments: Bundle?): Boolean {
        if (mAccessibilityProvider!!.handlesPerformAccessibilityAction(action, arguments)) {
            return mAccessibilityProvider!!.onPerformAccessibilityAction(action, arguments)
        }
        return super.performAccessibilityAction(action, arguments)
    }

    /**
     * Slightly modified RecyclerView to get ViewPager behavior in accessibility and to
     * enable/disable user scrolling.
     */
    private inner class RecyclerViewImpl(context: Context) : RecyclerView(context) {
        @RequiresApi(23)
        override fun getAccessibilityClassName(): CharSequence? {
            if (mAccessibilityProvider!!.handlesRvGetAccessibilityClassName()) {
                return mAccessibilityProvider!!.onRvGetAccessibilityClassName()
            }
            return super.getAccessibilityClassName()
        }

        override fun onInitializeAccessibilityEvent(event: AccessibilityEvent) {
            super.onInitializeAccessibilityEvent(event)
            event.setFromIndex(mCurrentItem)
            event.setToIndex(mCurrentItem)
            mAccessibilityProvider!!.onRvInitializeAccessibilityEvent(event)
        }

        @SuppressLint("ClickableViewAccessibility")
        override fun onTouchEvent(event: MotionEvent?): Boolean {
            return isUserInputEnabled && super.onTouchEvent(event)
        }

        override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
            return isUserInputEnabled && super.onInterceptTouchEvent(ev)
        }
    }

    private inner class LinearLayoutManagerImpl(context: Context?) : LinearLayoutManager(context) {
        override fun performAccessibilityAction(
            recycler: Recycler,
            state: RecyclerView.State, action: Int, args: Bundle?
        ): Boolean {
            if (mAccessibilityProvider!!.handlesLmPerformAccessibilityAction(action)) {
                return mAccessibilityProvider!!.onLmPerformAccessibilityAction(action)
            }
            return super.performAccessibilityAction(recycler, state, action, args)
        }

        override fun onInitializeAccessibilityNodeInfo(
            recycler: Recycler,
            state: RecyclerView.State, info: AccessibilityNodeInfoCompat
        ) {
            super.onInitializeAccessibilityNodeInfo(recycler, state, info)
            mAccessibilityProvider!!.onLmInitializeAccessibilityNodeInfo(info)
        }

        override fun calculateExtraLayoutSpace(
            state: RecyclerView.State,
            extraLayoutSpace: IntArray
        ) {
            val pageLimit: Int = offscreenPageLimit
            if (pageLimit == OFFSCREEN_PAGE_LIMIT_DEFAULT) {
                // Only do custom prefetching of offscreen pages if requested
                super.calculateExtraLayoutSpace(state, extraLayoutSpace)
                return
            }
            val offscreenSpace: Int = pageSize * pageLimit
            extraLayoutSpace[0] = offscreenSpace
            extraLayoutSpace[1] = offscreenSpace
        }

        override fun requestChildRectangleOnScreen(
            parent: RecyclerView,
            child: View, rect: Rect, immediate: Boolean,
            focusedChildVisible: Boolean
        ): Boolean {
            return false // users should use setCurrentItem instead
        }
    }

    private inner class PagerSnapHelperImpl : PagerSnapHelper() {
        override fun findSnapView(layoutManager: RecyclerView.LayoutManager?): View? {
            // When interrupting a smooth scroll with a fake drag, we stop RecyclerView's scroll
            // animation, which fires a scroll state change to IDLE. PagerSnapHelper then kicks in
            // to snap to a page, which we need to prevent here.
            // Simplifying that case: during a fake drag, no snapping should occur.
            return if (isFakeDragging) null else super.findSnapView(layoutManager)
        }
    }

    private class SmoothScrollToPosition // to avoid a synthetic accessor
        (private val mPosition: Int, private val mRecyclerView: RecyclerView) : Runnable {
        override fun run() {
            mRecyclerView.smoothScrollToPosition(mPosition)
        }
    }

    /**
     * Callback interface for responding to changing state of the selected page.
     */
    abstract class OnPageChangeCallback {
        /**
         * This method will be invoked when the current page is scrolled, either as part
         * of a programmatically initiated smooth scroll or a user initiated touch scroll.
         *
         * @param position Position index of the first page currently being displayed.
         * Page position+1 will be visible if positionOffset is nonzero.
         * @param positionOffset Value from [0, 1) indicating the offset from the page at position.
         * @param positionOffsetPixels Value in pixels indicating the offset from position.
         */
        open fun onPageScrolled(
            position: Int, positionOffset: Float,
            @Px positionOffsetPixels: Int
        ) {
        }

        /**
         * This method will be invoked when a new page becomes selected. Animation is not
         * necessarily complete.
         *
         * @param position Position index of the new selected page.
         */
        open fun onPageSelected(position: Int) {
        }

        /**
         * Called when the scroll state changes. Useful for discovering when the user begins
         * dragging, when a fake drag is started, when the pager is automatically settling to the
         * current page, or when it is fully stopped/idle. `state` can be one of [ ][.SCROLL_STATE_IDLE], [.SCROLL_STATE_DRAGGING] or [.SCROLL_STATE_SETTLING].
         */
        open fun onPageScrollStateChanged(@ViewPager2.ScrollState state: Int) {
        }
    }

    /**
     * A PageTransformer is invoked whenever a visible/attached page is scrolled.
     * This offers an opportunity for the application to apply a custom transformation
     * to the page views using animation properties.
     */
    interface PageTransformer {
        /**
         * Apply a property transformation to the given page.
         *
         * @param page Apply the transformation to this page
         * @param position Position of page relative to the current front-and-center
         * position of the pager. 0 is front and center. 1 is one full
         * page position to the right, and -2 is two pages to the left.
         * Minimum / maximum observed values depend on how many pages we keep
         * attached, which depends on offscreenPageLimit.
         *
         * @see .setOffscreenPageLimit
         */
        fun transformPage(page: View, position: Float)
    }

    /**
     * Add an [ItemDecoration] to this ViewPager2. Item decorations can
     * affect both measurement and drawing of individual item views.
     *
     *
     * Item decorations are ordered. Decorations placed earlier in the list will
     * be run/queried/drawn first for their effects on item views. Padding added to views
     * will be nested; a padding added by an earlier decoration will mean further
     * item decorations in the list will be asked to draw/pad within the previous decoration's
     * given area.
     *
     * @param decor Decoration to add
     */
    fun addItemDecoration(decor: ItemDecoration) {
        mRecyclerView!!.addItemDecoration(decor)
    }

    /**
     * Add an [ItemDecoration] to this ViewPager2. Item decorations can
     * affect both measurement and drawing of individual item views.
     *
     *
     * Item decorations are ordered. Decorations placed earlier in the list will
     * be run/queried/drawn first for their effects on item views. Padding added to views
     * will be nested; a padding added by an earlier decoration will mean further
     * item decorations in the list will be asked to draw/pad within the previous decoration's
     * given area.
     *
     * @param decor Decoration to add
     * @param index Position in the decoration chain to insert this decoration at. If this value
     * is negative the decoration will be added at the end.
     * @throws IndexOutOfBoundsException on indexes larger than [.getItemDecorationCount]
     */
    fun addItemDecoration(decor: ItemDecoration, index: Int) {
        mRecyclerView!!.addItemDecoration(decor, index)
    }

    /**
     * Returns an [ItemDecoration] previously added to this ViewPager2.
     *
     * @param index The index position of the desired ItemDecoration.
     * @return the ItemDecoration at index position
     * @throws IndexOutOfBoundsException on invalid index
     */
    fun getItemDecorationAt(index: Int): ItemDecoration {
        return mRecyclerView!!.getItemDecorationAt(index)
    }

    val itemDecorationCount: Int
        /**
         * Returns the number of [ItemDecoration] currently added to this ViewPager2.
         *
         * @return number of ItemDecorations currently added added to this ViewPager2.
         */
        get() = mRecyclerView!!.getItemDecorationCount()

    /**
     * Invalidates all ItemDecorations. If ViewPager2 has item decorations, calling this method
     * will trigger a [.requestLayout] call.
     */
    fun invalidateItemDecorations() {
        mRecyclerView!!.invalidateItemDecorations()
    }

    /**
     * Removes the [ItemDecoration] associated with the supplied index position.
     *
     * @param index The index position of the ItemDecoration to be removed.
     * @throws IndexOutOfBoundsException on invalid index
     */
    fun removeItemDecorationAt(index: Int) {
        mRecyclerView!!.removeItemDecorationAt(index)
    }

    /**
     * Remove an [ItemDecoration] from this ViewPager2.
     *
     *
     * The given decoration will no longer impact the measurement and drawing of
     * item views.
     *
     * @param decor Decoration to remove
     * @see .addItemDecoration
     */
    fun removeItemDecoration(decor: ItemDecoration) {
        mRecyclerView!!.removeItemDecoration(decor)
    }

    // TODO(b/141956012): Suppressed during upgrade to AGP 3.6.
    internal abstract class AccessibilityProvider {
        open fun onInitialize(
            pageChangeEventDispatcher: 组合页面改变回调,
            recyclerView: RecyclerView
        ) {
        }

        open fun handlesGetAccessibilityClassName(): Boolean {
            return false
        }

        open fun onGetAccessibilityClassName(): String? {
            throw IllegalStateException("Not implemented.")
        }

        open fun onRestorePendingState() {
        }

        open fun onAttachAdapter(newAdapter: RecyclerView.Adapter<*>?) {
        }

        open fun onDetachAdapter(oldAdapter: RecyclerView.Adapter<*>?) {
        }

        open fun onSetOrientation() {
        }

        open fun onSetNewCurrentItem() {
        }

        open fun onSetUserInputEnabled() {
        }

        open fun onSetLayoutDirection() {
        }

        open fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo?) {
        }

        open fun handlesPerformAccessibilityAction(action: Int, arguments: Bundle?): Boolean {
            return false
        }

        open fun onPerformAccessibilityAction(action: Int, arguments: Bundle?): Boolean {
            throw IllegalStateException("Not implemented.")
        }

        open fun onRvInitializeAccessibilityEvent(event: AccessibilityEvent) {
        }

        open fun handlesLmPerformAccessibilityAction(action: Int): Boolean {
            return false
        }

        open fun onLmPerformAccessibilityAction(action: Int): Boolean {
            throw IllegalStateException("Not implemented.")
        }

        open fun onLmInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfoCompat) {
        }

        open fun handlesRvGetAccessibilityClassName(): Boolean {
            return false
        }

        open fun onRvGetAccessibilityClassName(): CharSequence? {
            throw IllegalStateException("Not implemented.")
        }
    }

    internal inner class BasicAccessibilityProvider : AccessibilityProvider() {
        public override fun handlesLmPerformAccessibilityAction(action: Int): Boolean {
            return (action == AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD
                    || action == AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD)
                    && !isUserInputEnabled
        }

        public override fun onLmPerformAccessibilityAction(action: Int): Boolean {
            check(handlesLmPerformAccessibilityAction(action))
            return false
        }

        public override fun onLmInitializeAccessibilityNodeInfo(
            info: AccessibilityNodeInfoCompat
        ) {
            if (!isUserInputEnabled) {
                info.removeAction(AccessibilityActionCompat.ACTION_SCROLL_BACKWARD)
                info.removeAction(AccessibilityActionCompat.ACTION_SCROLL_FORWARD)
                info.setScrollable(false)
            }
        }

        public override fun handlesRvGetAccessibilityClassName(): Boolean {
            return true
        }

        public override fun onRvGetAccessibilityClassName(): CharSequence {
            check(handlesRvGetAccessibilityClassName())
            return "androidx.viewpager.widget.ViewPager"
        }
    }

    internal inner class PageAwareAccessibilityProvider : AccessibilityProvider() {
        private val mActionPageForward: AccessibilityViewCommand =
            object : AccessibilityViewCommand {
                override fun perform(
                    view: View,
                    arguments: CommandArguments?
                ): Boolean {
                    val viewPager = view as 视图分页器2
                    setCurrentItemFromAccessibilityCommand(viewPager.currentItem + 1)
                    return true
                }
            }

        private val mActionPageBackward: AccessibilityViewCommand =
            object : AccessibilityViewCommand {
                override fun perform(
                    view: View,
                    arguments: CommandArguments?
                ): Boolean {
                    val viewPager = view as 视图分页器2
                    setCurrentItemFromAccessibilityCommand(viewPager.currentItem - 1)
                    return true
                }
            }

        private var mAdapterDataObserver: AdapterDataObserver? = null

        public override fun onInitialize(
            pageChangeEventDispatcher: 组合页面改变回调,
            recyclerView: RecyclerView
        ) {
            ViewCompat.setImportantForAccessibility(
                recyclerView,
                ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_NO
            )

            mAdapterDataObserver = object : DataSetChangeObserver() {
                override fun onChanged() {
                    updatePageAccessibilityActions()
                }
            }

            if (ViewCompat.getImportantForAccessibility(this@视图分页器2)
                == ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_AUTO
            ) {
                ViewCompat.setImportantForAccessibility(
                    this@视图分页器2,
                    ViewCompat.IMPORTANT_FOR_ACCESSIBILITY_YES
                )
            }
        }

        public override fun handlesGetAccessibilityClassName(): Boolean {
            return true
        }

        public override fun onGetAccessibilityClassName(): String {
            check(handlesGetAccessibilityClassName())
            return "androidx.viewpager.widget.ViewPager"
        }

        public override fun onRestorePendingState() {
            updatePageAccessibilityActions()
        }

        public override fun onAttachAdapter(newAdapter: RecyclerView.Adapter<*>?) {
            updatePageAccessibilityActions()
            if (newAdapter != null) {
                newAdapter.registerAdapterDataObserver(mAdapterDataObserver!!)
            }
        }

        public override fun onDetachAdapter(oldAdapter: RecyclerView.Adapter<*>?) {
            if (oldAdapter != null) {
                oldAdapter.unregisterAdapterDataObserver(mAdapterDataObserver!!)
            }
        }

        public override fun onSetOrientation() {
            updatePageAccessibilityActions()
        }

        public override fun onSetNewCurrentItem() {
            updatePageAccessibilityActions()
        }

        public override fun onSetUserInputEnabled() {
            updatePageAccessibilityActions()
            if (Build.VERSION.SDK_INT < 21) {
                sendAccessibilityEvent(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED)
            }
        }

        public override fun onSetLayoutDirection() {
            updatePageAccessibilityActions()
        }

        public override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo?) {
            addCollectionInfo(info!!)
            if (Build.VERSION.SDK_INT >= 16) {
                addScrollActions(info)
            }
        }

        public override fun handlesPerformAccessibilityAction(
            action: Int,
            arguments: Bundle?
        ): Boolean {
            return action == AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD
                    || action == AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD
        }

        public override fun onPerformAccessibilityAction(action: Int, arguments: Bundle?): Boolean {
            check(handlesPerformAccessibilityAction(action, arguments))

            val nextItem: Int = if (action == AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD)
                currentItem - 1
            else
                currentItem + 1
            setCurrentItemFromAccessibilityCommand(nextItem)
            return true
        }

        public override fun onRvInitializeAccessibilityEvent(event: AccessibilityEvent) {
            event.setSource(this@视图分页器2)
            event.setClassName(onGetAccessibilityClassName())
        }

        /**
         * Sets the current item without checking if a fake drag is ongoing. Only call this method
         * from within an accessibility command or other forms of user input. Call is ignored if
         * [user input is disabled][.isUserInputEnabled].
         */
        fun setCurrentItemFromAccessibilityCommand(item: Int) {
            if (isUserInputEnabled) {
                setCurrentItemInternal(item, true)
            }
        }

        /**
         * Update the ViewPager2's available page accessibility actions. These are updated in
         * response to page, adapter, and orientation changes. Compatible with API >= 21.
         */
        @SuppressLint("WrongConstant")
        fun updatePageAccessibilityActions() {
            val viewPager = this@视图分页器2

            @SuppressLint("InlinedApi") val actionIdPageLeft =
                android.R.id.accessibilityActionPageLeft
            @SuppressLint("InlinedApi") val actionIdPageRight =
                android.R.id.accessibilityActionPageRight
            @SuppressLint("InlinedApi") val actionIdPageUp = android.R.id.accessibilityActionPageUp
            @SuppressLint("InlinedApi") val actionIdPageDown =
                android.R.id.accessibilityActionPageDown

            ViewCompat.removeAccessibilityAction(viewPager, actionIdPageLeft)
            ViewCompat.removeAccessibilityAction(viewPager, actionIdPageRight)
            ViewCompat.removeAccessibilityAction(viewPager, actionIdPageUp)
            ViewCompat.removeAccessibilityAction(viewPager, actionIdPageDown)

            if (adapter == null) {
                return
            }

            val itemCount: Int = adapter!!.getItemCount()
            if (itemCount == 0) {
                return
            }

            if (!isUserInputEnabled) {
                return
            }

            if (orientation == ORIENTATION_HORIZONTAL) {
                val isLayoutRtl: Boolean = isRtl
                val actionIdPageForward = if (isLayoutRtl) actionIdPageLeft else actionIdPageRight
                val actionIdPageBackward = if (isLayoutRtl) actionIdPageRight else actionIdPageLeft

                if (mCurrentItem < itemCount - 1) {
                    ViewCompat.replaceAccessibilityAction(
                        viewPager,
                        AccessibilityActionCompat(actionIdPageForward, null), null,
                        mActionPageForward
                    )
                }
                if (mCurrentItem > 0) {
                    ViewCompat.replaceAccessibilityAction(
                        viewPager,
                        AccessibilityActionCompat(actionIdPageBackward, null), null,
                        mActionPageBackward
                    )
                }
            } else {
                if (mCurrentItem < itemCount - 1) {
                    ViewCompat.replaceAccessibilityAction(
                        viewPager,
                        AccessibilityActionCompat(actionIdPageDown, null), null,
                        mActionPageForward
                    )
                }
                if (mCurrentItem > 0) {
                    ViewCompat.replaceAccessibilityAction(
                        viewPager,
                        AccessibilityActionCompat(actionIdPageUp, null), null,
                        mActionPageBackward
                    )
                }
            }
        }

        @SuppressLint("WrongConstant")
        private fun addCollectionInfo(info: AccessibilityNodeInfo) {
            var rowCount = 0
            var colCount = 0
            if (adapter != null) {
                if (orientation == ORIENTATION_VERTICAL) {
                    rowCount = adapter!!.getItemCount()
                } else {
                    colCount = adapter!!.getItemCount()
                }
            }
            val nodeInfoCompat = AccessibilityNodeInfoCompat.wrap(info)
            val collectionInfo =
                CollectionInfoCompat.obtain(
                    rowCount, colCount,  /* hierarchical= */
                    false,
                    CollectionInfoCompat.SELECTION_MODE_NONE
                )
            nodeInfoCompat.setCollectionInfo(collectionInfo)
        }

        private fun addScrollActions(info: AccessibilityNodeInfo) {
            val adapter: RecyclerView.Adapter<*>? = adapter
            if (adapter == null) {
                return
            }
            val itemCount = adapter.getItemCount()
            if (itemCount == 0 || !isUserInputEnabled) {
                return
            }
            if (mCurrentItem > 0) {
                info.addAction(AccessibilityNodeInfoCompat.ACTION_SCROLL_BACKWARD)
            }
            if (mCurrentItem < itemCount - 1) {
                info.addAction(AccessibilityNodeInfoCompat.ACTION_SCROLL_FORWARD)
            }
            info.setScrollable(true)
        }
    }

    /**
     * Simplified [RecyclerView.AdapterDataObserver] for clients interested in any data-set
     * changes regardless of their nature.
     */
    private abstract class DataSetChangeObserver : AdapterDataObserver() {
        abstract override fun onChanged()

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            onChanged()
        }

        override fun onItemRangeChanged(
            positionStart: Int, itemCount: Int,
            payload: Any?
        ) {
            onChanged()
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            onChanged()
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            onChanged()
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            onChanged()
        }
    }

    companion object {
        const val ORIENTATION_HORIZONTAL: Int = RecyclerView.HORIZONTAL
        const val ORIENTATION_VERTICAL: Int = RecyclerView.VERTICAL

        /**
         * Indicates that the ViewPager2 is in an idle, settled state. The current page
         * is fully in view and no animation is in progress.
         */
        const val SCROLL_STATE_IDLE: Int = 0

        /**
         * Indicates that the ViewPager2 is currently being dragged by the user, or programmatically
         * via fake drag functionality.
         */
        const val SCROLL_STATE_DRAGGING: Int = 1

        /**
         * Indicates that the ViewPager2 is in the process of settling to a final position.
         */
        const val SCROLL_STATE_SETTLING: Int = 2

        /**
         * Value to indicate that the default caching mechanism of RecyclerView should be used instead
         * of explicitly prefetch and retain pages to either side of the current page.
         * @see .setOffscreenPageLimit
         */
        const val OFFSCREEN_PAGE_LIMIT_DEFAULT: Int = -1

        /** Feature flag while stabilizing enhanced a11y  */
        const val sFeatureEnhancedA11yEnabled: Boolean = true
    }
}



//====================================================================================

/**
 * 描述：方向
 *
 * 版本：0.1.1
 */
var ViewPager2.方向: Int
    get() = orientation
    set(方向) { orientation = 方向 }

/**
 * 描述：取方向
 *
 * 版本：0.1.1
 */
fun ViewPager2.取方向(): Int = getOrientation()
/**
 * 描述：置方向
 *
 * 版本：0.1.1
 */
fun ViewPager2.置方向(方向: Int) = setOrientation(方向)

//====================================================================================

/**
 * 描述：适配器
 *
 * 版本：0.1.1
 */
var ViewPager2.适配器: RecyclerView.Adapter<*>?
    get() = adapter
    set(适配器) { adapter = 适配器 }

/**
 * 描述：取适配器
 *
 * 版本：0.1.1
 */
fun ViewPager2.取适配器(): RecyclerView.Adapter<*>? = getAdapter()
/**
 * 描述：置适配器
 *
 * 版本：0.1.1
 */
fun ViewPager2.置适配器(适配器: RecyclerView.Adapter<*>) = setAdapter(适配器)

//====================================================================================



