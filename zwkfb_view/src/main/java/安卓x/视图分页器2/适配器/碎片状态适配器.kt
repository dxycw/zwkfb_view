package 安卓x.视图分页器2.适配器

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：碎片状态适配器
 *
 * 版本：0.1.1
 * @author dxyc
 */
open class 碎片状态适配器 : FragmentStateAdapter {
    constructor(fragmentActivity: FragmentActivity) : super(fragmentActivity)
    constructor(fragment: Fragment) : super(fragment)
    constructor(fragmentManager: FragmentManager, lifecycle: Lifecycle) : super(
        fragmentManager,
        lifecycle
    )

    override fun createFragment(position: Int): Fragment {
        return 创建碎片(position)
    }


    override fun getItemCount(): Int {
        return 取项目数量()
    }

    /**
     * 创建时间：2025年11月27日.
     *
     * 描述：创建碎片
     *
     * 版本：0.1.3
     * @param 索引 索引
     * @return 碎片
     */
    open fun 创建碎片(索引: Int): Fragment = Fragment()

    /**
     * 创建时间：2025年11月27日.
     *
     * 描述：取项目数量
     *
     * 版本：0.1.3
     * @return 项目数量
     */
    open fun 取项目数量(): Int = 0


}