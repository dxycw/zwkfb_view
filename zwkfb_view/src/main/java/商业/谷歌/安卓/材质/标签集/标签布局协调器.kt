package 商业.谷歌.安卓.材质.标签集

import androidx.viewpager2.widget.ViewPager2

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：标签布局协调器
 *
 * 版本：0.1.1
 * @author dxyc
 */
open class 标签布局协调器 : TabLayoutMediator{
    constructor(
        tabLayout: TabLayout,
        viewPager: ViewPager2,
        tabConfigurationStrategy: TabConfigurationStrategy,
    ) : super(tabLayout, viewPager, tabConfigurationStrategy)

    constructor(
        tabLayout: TabLayout,
        viewPager: ViewPager2,
        autoRefresh: Boolean,
        tabConfigurationStrategy: TabConfigurationStrategy,
    ) : super(tabLayout, viewPager, autoRefresh, tabConfigurationStrategy)

    constructor(
        tabLayout: TabLayout,
        viewPager: ViewPager2,
        autoRefresh: Boolean,
        smoothScroll: Boolean,
        tabConfigurationStrategy: TabConfigurationStrategy,
    ) : super(tabLayout, viewPager, autoRefresh, smoothScroll, tabConfigurationStrategy)
}

//============================================================================

/**
 * 绑定标签布局协调器
 *
 * 版本：0.1.1
 */
fun com.google.android.material.tabs.TabLayoutMediator.绑定() =
    this.attach()


//============================================================================

/**
 * 绑定标签布局协调器
 *
 * 版本：0.1.1
 */
fun TabLayoutMediator.绑定() =
    this.attach()

