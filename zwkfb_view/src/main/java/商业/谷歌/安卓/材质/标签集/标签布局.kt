package 商业.谷歌.安卓.材质.标签集

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewpager2.widget.ViewPager2



/**
 * 创建时间：2025年11月24日.
 *
 * 描述：标签布局
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 标签布局 : TabLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    /**
     * 创建时间：2025年11月24日.
     *
     * 描述：标签视图
     *
     * 版本：0.1.0
     */
    inner class 标签视图 : TabView {
        constructor(context: Context) : super(context)
    }

}


//==========================================================================

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置标签文本颜色
 *
 * 版本：0.1.2
 * @param 正常颜色 正常状态下的文本颜色
 * @param 选中颜色 选中状态下的文本颜色
 */
fun com.google.android.material.tabs.TabLayout.置标签文本颜色(正常颜色: Int, 选中颜色: Int) =
    this.setTabTextColors(正常颜色, 选中颜色)

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置选中标签指示器颜色
 *
 * 版本：0.1.2
 * @param 选中颜色 选中状态下的指示器颜色
 */
fun com.google.android.material.tabs.TabLayout.置选中标签指示器颜色(选中颜色: Int) =
    this.setSelectedTabIndicatorColor(选中颜色)


//======================================================================

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：是否标签指示器全宽
 *
 * 版本：0.1.2
 */
var com.google.android.material.tabs.TabLayout.标签指示器全宽: Boolean
    get() = this.isTabIndicatorFullWidth
    set(标签指示器全宽) {
        this.setTabIndicatorFullWidth(标签指示器全宽)
    }

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：是否标签指示器全宽
 *
 * 版本：0.1.2
 */
fun com.google.android.material.tabs.TabLayout.是否标签指示器全宽(): Boolean =
    this.isTabIndicatorFullWidth

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置标签指示器全宽
 *
 * 版本：0.1.2
 */
fun com.google.android.material.tabs.TabLayout.置标签指示器全宽(标签指示器全宽: Boolean) =
    this.setTabIndicatorFullWidth(标签指示器全宽)

//==========================================================================

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：标签文本
 *
 * 版本：0.1.2
 */
var com.google.android.material.tabs.TabLayout.Tab.文本
    get() = text
    set(文本) { text = 文本 }

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：取文本
 *
 * 版本：0.1.2
 */
fun com.google.android.material.tabs.TabLayout.Tab.取文本(): CharSequence? =
   this.getText()

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun com.google.android.material.tabs.TabLayout.Tab.置文本(文本: CharSequence) =
    this.setText(文本)


//====================================================================================




//==========================================================================

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置标签文本颜色
 *
 * 版本：0.1.2
 * @param 正常颜色 正常状态下的文本颜色
 * @param 选中颜色 选中状态下的文本颜色
 */
fun TabLayout.置标签文本颜色(正常颜色: Int, 选中颜色: Int) =
    this.setTabTextColors(正常颜色, 选中颜色)

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置选中标签指示器颜色
 *
 * 版本：0.1.2
 * @param 选中颜色 选中状态下的指示器颜色
 */
fun TabLayout.置选中标签指示器颜色(选中颜色: Int) =
    this.setSelectedTabIndicatorColor(选中颜色)

//======================================================================

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：是否标签指示器全宽
 *
 * 版本：0.1.2
 */
var TabLayout.标签指示器全宽: Boolean
    get() = this.isTabIndicatorFullWidth
    set(标签指示器全宽) {
        this.setTabIndicatorFullWidth(标签指示器全宽)
    }

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：是否标签指示器全宽
 *
 * 版本：0.1.2
 */
fun TabLayout.是否标签指示器全宽(): Boolean = this.isTabIndicatorFullWidth

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置标签指示器全宽
 *
 * 版本：0.1.2
 */
fun TabLayout.置标签指示器全宽(标签指示器全宽: Boolean) =
    this.setTabIndicatorFullWidth(标签指示器全宽)

//==========================================================================
/**
 * 创建时间：2025年11月27日.
 *
 * 描述：标签文本
 *
 * 版本：0.1.2
 */
var TabLayout.Tab.文本
    get() = text
    set(文本) { text = 文本 }

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：取文本
 *
 * 版本：0.1.2
 */
fun TabLayout.Tab.取文本(): CharSequence? =
    this.getText()

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：置文本
 *
 * 版本：0.1.2
 */
fun TabLayout.Tab.置文本(文本: CharSequence) =
    this.setText(文本)


//====================================================================================
