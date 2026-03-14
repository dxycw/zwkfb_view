package 安卓x.视图分页器.组件

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import 安卓x.视图分页器.组件.适配器



//====================================================================

/**
 * 版本：0.1.0
 *
 * 描述：适配器
 * @param 适配器
 * @return 适配器
 */
var ViewPager.适配器: PagerAdapter?
    get() = adapter
    set(适配器) { adapter = 适配器 }

/**
 * 版本：0.1.0
 *
 * 描述：适配器
 * @return 适配器
 */
fun ViewPager.取适配器(): PagerAdapter? {
    return getAdapter()
}

/**
 * 版本：0.1.0
 *
 * 描述：适配器
 * @param 适配器
 */
fun ViewPager.置适配器(适配器: PagerAdapter) {
    setAdapter(适配器)
}

//======================================================================
