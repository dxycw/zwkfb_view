package 安卓x.视图分页器2.组件

import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewpager2.widget.ViewPager2

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.2
 * @author dxyc
 */

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
var ViewPager2.适配器: Adapter<*>?
    get() = adapter
    set(适配器) { adapter = 适配器 }

/**
 * 描述：取适配器
 *
 * 版本：0.1.1
 */
fun ViewPager2.取适配器(): Adapter<*>? = getAdapter()
/**
 * 描述：置适配器
 *
 * 版本：0.1.1
 */
fun ViewPager2.置适配器(适配器: Adapter<*>) = setAdapter(适配器)

//====================================================================================



