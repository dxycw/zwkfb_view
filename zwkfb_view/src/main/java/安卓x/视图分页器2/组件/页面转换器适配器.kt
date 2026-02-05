package 安卓x.视图分页器2.组件

import androidx.recyclerview.widget.LinearLayoutManager
import java.util.Locale

/**
 * Translates [视图分页器2.OnPageChangeCallback] events to [视图分页器2.PageTransformer] events.
 */
internal class 页面转换器适配器(
// TODO: add support for reverseDrawingOrder: b/112892792
    private val mLayoutManager: LinearLayoutManager
) : 视图分页器2.OnPageChangeCallback() {
    // TODO: add support for pageLayerType: b/112893074

    /**
     * Sets the PageTransformer. The page transformer will be called for each attached page whenever
     * the scroll position is changed.
     *
     * @param transformer The PageTransformer
     */
    var pageTransformer: 视图分页器2.PageTransformer? = null

    public override fun onPageScrolled(
        position: Int,
        positionOffset: Float,
        positionOffsetPixels: Int
    ) {
        if (this.pageTransformer == null) {
            return
        }

        val transformOffset = -positionOffset
        for (i in 0..<mLayoutManager.getChildCount()) {
            val view = mLayoutManager.getChildAt(i)
            checkNotNull(view) {
                String.format(
                    Locale.US,
                    "LayoutManager returned a null child at pos %d/%d while transforming pages",
                    i, mLayoutManager.getChildCount()
                )
            }
            val currPos = mLayoutManager.getPosition(view)
            val viewOffset = transformOffset + (currPos - position)
            pageTransformer!!.transformPage(view, viewOffset)
        }
    }

    public override fun onPageSelected(position: Int) {
    }

    public override fun onPageScrollStateChanged(state: Int) {
    }
}
