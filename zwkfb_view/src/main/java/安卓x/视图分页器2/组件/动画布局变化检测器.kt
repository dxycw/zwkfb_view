package 安卓x.视图分页器2.组件

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import java.util.Arrays

/**
 * Class used to detect if there are gaps between pages and if any of the pages contain a running
 * change-transition in case we detected an illegal state in the [滚动事件适配器].
 *
 * This is an approximation of the detection and could potentially lead to misleading advice. If we
 * hit problems with it, remove the detection and replace with a suggestive error message instead,
 * like "Negative page offset encountered. Did you setAnimateParentHierarchy(false) to all your
 * LayoutTransitions?".
 */
internal class 动画布局变化检测器(private val mLayoutManager: LinearLayoutManager) {
    fun mayHaveInterferingAnimations(): Boolean {
        // Two conditions need to be satisfied:
        // 1) the pages are not laid out contiguously (i.e., there are gaps between them)
        // 2) there is a ViewGroup with a LayoutTransition that isChangingLayout()
        return (!arePagesLaidOutContiguously() || mLayoutManager.getChildCount() <= 1)
                && hasRunningChangingLayoutTransition()
    }

    private fun arePagesLaidOutContiguously(): Boolean {
        // Collect view positions
        val childCount = mLayoutManager.getChildCount()
        if (childCount == 0) {
            return true
        }

        @SuppressLint("WrongConstant") val isHorizontal =
            mLayoutManager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL
        val bounds = Array<IntArray?>(childCount) { IntArray(2) }
        for (i in 0..<childCount) {
            val view = mLayoutManager.getChildAt(i)
            checkNotNull(view) { "null view contained in the view hierarchy" }
            val layoutParams = view.getLayoutParams()
            val margin: MarginLayoutParams?
            if (layoutParams is MarginLayoutParams) {
                margin = layoutParams
            } else {
                margin = ZERO_MARGIN_LAYOUT_PARAMS
            }
            bounds[i]!![0] = if (isHorizontal)
                view.getLeft() - margin.leftMargin
            else
                view.getTop() - margin.topMargin
            bounds[i]!![1] = if (isHorizontal)
                view.getRight() + margin.rightMargin
            else
                view.getBottom() + margin.bottomMargin
        }

        // Sort them
        Arrays.sort<IntArray?>(bounds, Comparator<IntArray?> { lhs, rhs -> lhs!![0] - rhs!![0] })

        // Check for inconsistencies
        for (i in 1..<childCount) {
            if (bounds[i - 1]!![1] != bounds[i]!![0]) {
                return false
            }
        }

        // Check that the pages fill the whole screen
        val pageSize = bounds[0]!![1] - bounds[0]!![0]
        if (bounds[0]!![0] > 0 || bounds[childCount - 1]!![1] < pageSize) {
            return false
        }
        return true
    }

    private fun hasRunningChangingLayoutTransition(): Boolean {
        val childCount = mLayoutManager.getChildCount()
        for (i in 0..<childCount) {
            if (hasRunningChangingLayoutTransition(mLayoutManager.getChildAt(i))) {
                return true
            }
        }
        return false
    }

    companion object {
        private val ZERO_MARGIN_LAYOUT_PARAMS: MarginLayoutParams

        init {
            ZERO_MARGIN_LAYOUT_PARAMS = MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            ZERO_MARGIN_LAYOUT_PARAMS.setMargins(0, 0, 0, 0)
        }

        private fun hasRunningChangingLayoutTransition(view: View?): Boolean {
            if (view is ViewGroup) {
                val viewGroup = view
                val layoutTransition = viewGroup.getLayoutTransition()
                if (layoutTransition != null && layoutTransition.isChangingLayout()) {
                    return true
                }
                val childCount = viewGroup.getChildCount()
                for (i in 0..<childCount) {
                    if (hasRunningChangingLayoutTransition(viewGroup.getChildAt(i))) {
                        return true
                    }
                }
            }
            return false
        }
    }
}
