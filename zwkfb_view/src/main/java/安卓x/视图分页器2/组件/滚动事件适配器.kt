package 安卓x.视图分页器2.组件

import android.annotation.SuppressLint
import android.view.ViewGroup.MarginLayoutParams
import androidx.annotation.IntDef
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import java.util.Locale

/**
 * Translates [RecyclerView.OnScrollListener] events to [视图分页器2.OnPageChangeCallback] events
 * for [ViewPager2]. As part of this process, it keeps track of the current scroll position
 * relative to the pages and exposes this position via ([.getRelativeScrollPosition].
 */
internal class 滚动事件适配器(private val mViewPager: 视图分页器2) : RecyclerView.OnScrollListener() {
    /** @hide
     */
    @Retention(AnnotationRetention.SOURCE)
    @IntDef(
        *[STATE_IDLE, STATE_IN_PROGRESS_MANUAL_DRAG, STATE_IN_PROGRESS_SMOOTH_SCROLL, STATE_IN_PROGRESS_IMMEDIATE_SCROLL, STATE_IN_PROGRESS_FAKE_DRAG]
    )
    private annotation class AdapterState

    private var mCallback: 视图分页器2.OnPageChangeCallback? = null
    private val mRecyclerView: RecyclerView
    private val mLayoutManager: LinearLayoutManager

    // state related fields
    @AdapterState
    private var mAdapterState = 0

    @ViewPager2.ScrollState
    var scrollState: Int = 0
        private set
    private val mScrollValues: ScrollEventValues
    private var mDragStartPosition = 0
    private var mTarget = 0
    private var mDispatchSelected = false
    private var mScrollHappened = false
    private var mDataSetChangeHappened = false

    /**
     * @return `true` if a fake drag is ongoing. Returns `false` from the moment the
     * [ViewPager2.endFakeDrag] is called.
     */
    var isFakeDragging: Boolean = false
        private set

    init {
        mRecyclerView = mViewPager.mRecyclerView!!
        mLayoutManager = mRecyclerView.getLayoutManager() as LinearLayoutManager
        mScrollValues = ScrollEventValues()
        resetState()
    }

    private fun resetState() {
        mAdapterState = STATE_IDLE
        this.scrollState = ViewPager2.SCROLL_STATE_IDLE
        mScrollValues.reset()
        mDragStartPosition = NO_POSITION
        mTarget = NO_POSITION
        mDispatchSelected = false
        mScrollHappened = false
        this.isFakeDragging = false
        mDataSetChangeHappened = false
    }

    /**
     * This method only deals with some cases of [AdapterState] transitions. The rest of
     * the state transition implementation is in the [.onScrolled] method.
     */
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        // User started a drag (not dragging -> dragging)
        if ((mAdapterState != STATE_IN_PROGRESS_MANUAL_DRAG
                    || this.scrollState != ViewPager2.SCROLL_STATE_DRAGGING)
            && newState == RecyclerView.SCROLL_STATE_DRAGGING
        ) {
            startDrag(false)
            return
        }

        // Drag is released, RecyclerView is snapping to page (dragging -> settling)
        // Note that mAdapterState is not updated, to remember we were dragging when settling
        if (this.isInAnyDraggingState && newState == RecyclerView.SCROLL_STATE_SETTLING) {
            // Only go through the settling phase if the drag actually moved the page
            if (mScrollHappened) {
                dispatchStateChanged(ViewPager2.SCROLL_STATE_SETTLING)
                // Determine target page and dispatch onPageSelected on next scroll event
                mDispatchSelected = true
            }
            return
        }

        // Drag is finished (dragging || settling -> idle)
        if (this.isInAnyDraggingState && newState == RecyclerView.SCROLL_STATE_IDLE) {
            var dispatchIdle = false
            updateScrollEventValues()
            if (!mScrollHappened) {
                // Pages didn't move during drag, so either we're at the start or end of the list,
                // or there are no pages at all.
                // In the first case, ViewPager's contract requires at least one scroll event.
                // In the second case, don't send that scroll event
                if (mScrollValues.mPosition != RecyclerView.NO_POSITION) {
                    dispatchScrolled(mScrollValues.mPosition, 0f, 0)
                }
                dispatchIdle = true
            } else if (mScrollValues.mOffsetPx == 0) {
                // Normally we dispatch the selected page and go to idle in onScrolled when
                // mOffsetPx == 0, but in this case the drag was still ongoing when onScrolled was
                // called, so that didn't happen. And since mOffsetPx == 0, there will be no further
                // scroll events, so fire the onPageSelected event and go to idle now.
                // Note that if we _did_ go to idle in that last onScrolled event, this code will
                // not be executed because mAdapterState has been reset to STATE_IDLE.
                dispatchIdle = true
                if (mDragStartPosition != mScrollValues.mPosition) {
                    dispatchSelected(mScrollValues.mPosition)
                }
            }
            if (dispatchIdle) {
                // Normally idle is fired in last onScrolled call, but either onScrolled was never
                // called, or we were still dragging when the last onScrolled was called
                dispatchStateChanged(ViewPager2.SCROLL_STATE_IDLE)
                resetState()
            }
        }

        if (mAdapterState == STATE_IN_PROGRESS_SMOOTH_SCROLL && newState == RecyclerView.SCROLL_STATE_IDLE && mDataSetChangeHappened) {
            updateScrollEventValues()
            if (mScrollValues.mOffsetPx == 0) {
                if (mTarget != mScrollValues.mPosition) {
                    dispatchSelected(
                        if (mScrollValues.mPosition == NO_POSITION) 0 else mScrollValues.mPosition
                    )
                }
                dispatchStateChanged(ViewPager2.SCROLL_STATE_IDLE)
                resetState()
            }
        }
    }

    /**
     * This method only deals with some cases of [AdapterState] transitions. The rest of
     * the state transition implementation is in the [.onScrollStateChanged] method.
     */
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        mScrollHappened = true
        updateScrollEventValues()

        if (mDispatchSelected) {
            // Drag started settling, need to calculate target page and dispatch onPageSelected now
            mDispatchSelected = false
            val scrollingForward = dy > 0 || (dy == 0 && dx < 0 == mViewPager.isRtl)

            // "&& values.mOffsetPx != 0": filters special case where we're scrolling forward and
            // the first scroll event after settling already got us at the target
            mTarget = if (scrollingForward && mScrollValues.mOffsetPx != 0)
                mScrollValues.mPosition + 1
            else
                mScrollValues.mPosition
            if (mDragStartPosition != mTarget) {
                dispatchSelected(mTarget)
            }
        } else if (mAdapterState == STATE_IDLE) {
            // onScrolled while IDLE means RV has just been populated after an adapter has been set.
            // Contract requires us to fire onPageSelected as well.
            val position = mScrollValues.mPosition
            // Contract forbids us to send position = -1 though
            dispatchSelected(if (position == NO_POSITION) 0 else position)
        }

        // If position = -1, there are no items. Contract says to send position = 0 instead.
        dispatchScrolled(
            if (mScrollValues.mPosition == NO_POSITION) 0 else mScrollValues.mPosition,
            mScrollValues.mOffset, mScrollValues.mOffsetPx
        )

        // Dispatch idle in onScrolled instead of in onScrollStateChanged because RecyclerView
        // doesn't send IDLE event when using setCurrentItem(x, false)
        if ((mScrollValues.mPosition == mTarget || mTarget == NO_POSITION)
            && mScrollValues.mOffsetPx == 0 && (this.scrollState != ViewPager2.SCROLL_STATE_DRAGGING)
        ) {
            // When the target page is reached and the user is not dragging anymore, we're settled,
            // so go to idle.
            // Special case and a bit of a hack when mTarget == NO_POSITION: RecyclerView is being
            // initialized and fires a single scroll event. This flags mScrollHappened, so we need
            // to reset our state. However, we don't want to dispatch idle. But that won't happen;
            // because we were already idle.
            dispatchStateChanged(ViewPager2.SCROLL_STATE_IDLE)
            resetState()
        }
    }

    /**
     * Calculates the current position and the offset (as a percentage and in pixels) of that
     * position from the center.
     */
    private fun updateScrollEventValues() {
        val values = mScrollValues

        values.mPosition = mLayoutManager.findFirstVisibleItemPosition()
        if (values.mPosition == RecyclerView.NO_POSITION) {
            values.reset()
            return
        }
        val firstVisibleView = mLayoutManager.findViewByPosition(values.mPosition)
        if (firstVisibleView == null) {
            values.reset()
            return
        }

        var leftDecorations = mLayoutManager.getLeftDecorationWidth(firstVisibleView)
        var rightDecorations = mLayoutManager.getRightDecorationWidth(firstVisibleView)
        var topDecorations = mLayoutManager.getTopDecorationHeight(firstVisibleView)
        var bottomDecorations = mLayoutManager.getBottomDecorationHeight(firstVisibleView)

        val params = firstVisibleView.getLayoutParams()
        if (params is MarginLayoutParams) {
            val margin = params
            leftDecorations += margin.leftMargin
            rightDecorations += margin.rightMargin
            topDecorations += margin.topMargin
            bottomDecorations += margin.bottomMargin
        }

        val decoratedHeight = firstVisibleView.getHeight() + topDecorations + bottomDecorations
        val decoratedWidth = firstVisibleView.getWidth() + leftDecorations + rightDecorations

        @SuppressLint("WrongConstant") val isHorizontal =
            mLayoutManager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL
        var start: Int
        val sizePx: Int
        if (isHorizontal) {
            sizePx = decoratedWidth
            start = firstVisibleView.getLeft() - leftDecorations - mRecyclerView.getPaddingLeft()
            if (mViewPager.isRtl) {
                start = -start
            }
        } else {
            sizePx = decoratedHeight
            start = firstVisibleView.getTop() - topDecorations - mRecyclerView.getPaddingTop()
        }

        values.mOffsetPx = -start
        if (values.mOffsetPx < 0) {
            // We're in an error state. Figure out if this might have been caused
            // by animateLayoutChanges and throw a descriptive exception if so
            check(!动画布局变化检测器(mLayoutManager).mayHaveInterferingAnimations()) {
                ("Page(s) contain a ViewGroup with a "
                        + "LayoutTransition (or animateLayoutChanges=\"true\"), which interferes "
                        + "with the scrolling animation. Make sure to call getLayoutTransition()"
                        + ".setAnimateParentHierarchy(false) on all ViewGroups with a "
                        + "LayoutTransition before an animation is started.")
            }

            // Throw a generic exception otherwise
            throw IllegalStateException(
                String.format(
                    Locale.US, "Page can only be offset by a "
                            + "positive amount, not by %d", values.mOffsetPx
                )
            )
        }
        values.mOffset = if (sizePx == 0) 0f else values.mOffsetPx.toFloat() / sizePx
    }

    private fun startDrag(isFakeDrag: Boolean) {
        this.isFakeDragging = isFakeDrag
        mAdapterState =
            if (isFakeDrag) STATE_IN_PROGRESS_FAKE_DRAG else STATE_IN_PROGRESS_MANUAL_DRAG
        if (mTarget != NO_POSITION) {
            // Target was set means we were settling to that target
            // Update "drag start page" to reflect the page that ViewPager2 thinks it is at
            mDragStartPosition = mTarget
            // Reset target because drags have no target until released
            mTarget = NO_POSITION
        } else if (mDragStartPosition == NO_POSITION) {
            // ViewPager2 was at rest, set "drag start page" to current page
            mDragStartPosition = this.position
        }
        dispatchStateChanged(ViewPager2.SCROLL_STATE_DRAGGING)
    }

    fun notifyDataSetChangeHappened() {
        mDataSetChangeHappened = true
    }

    /**
     * Let the adapter know a programmatic scroll was initiated.
     */
    fun notifyProgrammaticScroll(target: Int, smooth: Boolean) {
        mAdapterState = if (smooth)
            STATE_IN_PROGRESS_SMOOTH_SCROLL
        else
            STATE_IN_PROGRESS_IMMEDIATE_SCROLL
        // mFakeDragging is true when a fake drag is interrupted by an a11y command
        // set it to false so endFakeDrag won't fling the RecyclerView
        this.isFakeDragging = false
        val hasNewTarget = mTarget != target
        mTarget = target
        dispatchStateChanged(ViewPager2.SCROLL_STATE_SETTLING)
        if (hasNewTarget) {
            dispatchSelected(target)
        }
    }

    /**
     * Let the adapter know that a fake drag has started.
     */
    fun notifyBeginFakeDrag() {
        mAdapterState = STATE_IN_PROGRESS_FAKE_DRAG
        startDrag(true)
    }

    /**
     * Let the adapter know that a fake drag has ended.
     */
    fun notifyEndFakeDrag() {
        if (this.isDragging && !this.isFakeDragging) {
            // Real drag has already taken over, no need to post process the fake drag
            return
        }
        this.isFakeDragging = false
        updateScrollEventValues()
        if (mScrollValues.mOffsetPx == 0) {
            // We're snapped, so dispatch an IDLE event
            if (mScrollValues.mPosition != mDragStartPosition) {
                dispatchSelected(mScrollValues.mPosition)
            }
            dispatchStateChanged(ViewPager2.SCROLL_STATE_IDLE)
            resetState()
        } else {
            // We're not snapped, so dispatch a SETTLING event
            dispatchStateChanged(ViewPager2.SCROLL_STATE_SETTLING)
        }
    }

    fun setOnPageChangeCallback(callback: 视图分页器2.OnPageChangeCallback?) {
        mCallback = callback
    }

    val isIdle: Boolean
        /**
         * @return `true` if there is no known scroll in progress
         */
        get() = this.scrollState == ViewPager2.SCROLL_STATE_IDLE

    val isDragging: Boolean
        /**
         * @return `true` if the ViewPager2 is being dragged. Returns `false` from the
         * moment the ViewPager2 starts settling or goes idle.
         */
        get() = this.scrollState == ViewPager2.SCROLL_STATE_DRAGGING

    private val isInAnyDraggingState: Boolean
        /**
         * Checks if the adapter state (not the scroll state) is in the manual or fake dragging state.
         * @return `true` if [.mAdapterState] is either [         ][.STATE_IN_PROGRESS_MANUAL_DRAG] or [.STATE_IN_PROGRESS_FAKE_DRAG]
         */
        get() = mAdapterState == STATE_IN_PROGRESS_MANUAL_DRAG
                || mAdapterState == STATE_IN_PROGRESS_FAKE_DRAG

    val relativeScrollPosition: Double
        /**
         * Calculates the scroll position of the currently visible item of the ViewPager relative to its
         * width. Calculated by adding the fraction by which the first visible item is off screen to its
         * adapter position. E.g., if the ViewPager is currently scrolling from the second to the third
         * page, the returned value will be between 1 and 2. Thus, non-integral values mean that the
         * the ViewPager is settling towards its [current item][视图分页器2.getCurrentItem], or
         * the user may be dragging it.
         *
         * @return The current scroll position of the ViewPager, relative to its width
         */
        get() {
            updateScrollEventValues()
            return mScrollValues.mPosition + mScrollValues.mOffset.toDouble()
        }

    private fun dispatchStateChanged(@ViewPager2.ScrollState state: Int) {
        // Callback contract for immediate-scroll requires not having state change notifications,
        // but only when there was no smooth scroll in progress.
        // By putting a suppress statement in here (rather than next to dispatch calls) we are
        // simplifying the code of the class and enforcing the contract in one place.
        if (mAdapterState == STATE_IN_PROGRESS_IMMEDIATE_SCROLL
            && this.scrollState == ViewPager2.SCROLL_STATE_IDLE
        ) {
            return
        }
        if (this.scrollState == state) {
            return
        }

        this.scrollState = state
        if (mCallback != null) {
            mCallback!!.onPageScrollStateChanged(state)
        }
    }

    private fun dispatchSelected(target: Int) {
        if (mCallback != null) {
            mCallback!!.onPageSelected(target)
        }
    }

    private fun dispatchScrolled(position: Int, offset: Float, offsetPx: Int) {
        if (mCallback != null) {
            mCallback!!.onPageScrolled(position, offset, offsetPx)
        }
    }

    private val position: Int
        get() = mLayoutManager.findFirstVisibleItemPosition()

    private class ScrollEventValues  // to avoid a synthetic accessor
    {
        var mPosition: Int = 0
        var mOffset: Float = 0f
        var mOffsetPx: Int = 0

        fun reset() {
            mPosition = RecyclerView.NO_POSITION
            mOffset = 0f
            mOffsetPx = 0
        }
    }

    companion object {
        private const val STATE_IDLE = 0
        private const val STATE_IN_PROGRESS_MANUAL_DRAG = 1
        private const val STATE_IN_PROGRESS_SMOOTH_SCROLL = 2
        private const val STATE_IN_PROGRESS_IMMEDIATE_SCROLL = 3
        private const val STATE_IN_PROGRESS_FAKE_DRAG = 4

        private val NO_POSITION = -1
    }
}
