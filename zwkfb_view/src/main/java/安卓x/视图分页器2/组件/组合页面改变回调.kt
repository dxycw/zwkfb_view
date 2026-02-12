package 安卓x.视图分页器2.组件

import androidx.annotation.Px

/**
 * Dispatches [视图分页器2.OnPageChangeCallback] events to subscribers.
 */
internal class 组合页面改变回调(initialCapacity: Int) : 视图分页器2.OnPageChangeCallback() {
    private val mCallbacks: MutableList<视图分页器2.OnPageChangeCallback>

    init {
        mCallbacks = ArrayList<视图分页器2.OnPageChangeCallback>(initialCapacity)
    }

    /**
     * Adds the given callback to the list of subscribers
     */
    fun addOnPageChangeCallback(callback: 视图分页器2.OnPageChangeCallback?) {
        mCallbacks.add(callback!!)
    }

    /**
     * Removes the given callback from the list of subscribers
     */
    fun removeOnPageChangeCallback(callback: 视图分页器2.OnPageChangeCallback?) {
        mCallbacks.remove(callback)
    }

    /**
     * @see 视图分页器2.OnPageChangeCallback.onPageScrolled
     */
    override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        @Px positionOffsetPixels: Int
    ) {
        try {
            for (callback in mCallbacks) {
                callback.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
        } catch (ex: ConcurrentModificationException) {
            throwCallbackListModifiedWhileInUse(ex)
        }
    }

    /**
     * @see 视图分页器2.OnPageChangeCallback.onPageSelected
     */
    override fun onPageSelected(position: Int) {
        try {
            for (callback in mCallbacks) {
                callback.onPageSelected(position)
            }
        } catch (ex: ConcurrentModificationException) {
            throwCallbackListModifiedWhileInUse(ex)
        }
    }

    /**
     * @see 视图分页器2.OnPageChangeCallback.onPageScrollStateChanged
     */
    override fun onPageScrollStateChanged(@视图分页器2.ScrollState state: Int) {
        try {
            for (callback in mCallbacks) {
                callback.onPageScrollStateChanged(state)
            }
        } catch (ex: ConcurrentModificationException) {
            throwCallbackListModifiedWhileInUse(ex)
        }
    }

    private fun throwCallbackListModifiedWhileInUse(parent: ConcurrentModificationException?) {
        throw IllegalStateException(
            "Adding and removing callbacks during dispatch to callbacks is not supported",
            parent
        )
    }
}
