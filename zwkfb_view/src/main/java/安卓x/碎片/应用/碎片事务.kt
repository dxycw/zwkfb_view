@file:Suppress("DEPRECATION")

package 安卓x.碎片.应用

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

/**
 * 创建时间：2025年11月26日.
 *
 * 描述：碎片事务
 *
 * 版本：0.1.1
 * @author dxyc
 */
open class 碎片事务 :FragmentTransaction{
    constructor() : super()

    override fun commit(): Int {
        TODO("Not yet implemented")
    }

    override fun commitAllowingStateLoss(): Int {
        TODO("Not yet implemented")
    }

    override fun commitNow() {
        TODO("Not yet implemented")
    }

    override fun commitNowAllowingStateLoss() {
        TODO("Not yet implemented")
    }
}

//=======================================================================

/**
 * 描述：替换
 *
 * 版本：0.1.1
 */
fun FragmentTransaction.替换(容器视图id: Int, 碎片: Fragment): FragmentTransaction =
    this.replace(容器视图id, 碎片)

/**
 * 描述：添加
 *
 * 版本：0.1.1
 */
fun FragmentTransaction.添加(容器视图id: Int, 碎片: Fragment): FragmentTransaction =
    this.add(容器视图id, 碎片)

/**
 * 描述：移除
 *
 * 版本：0.1.1
 */
fun FragmentTransaction.移除(碎片: Fragment): FragmentTransaction =
    this.remove(碎片)

/**
 * 描述：显示
 *
 * 版本：0.1.1
 */
fun FragmentTransaction.显示(碎片: Fragment): FragmentTransaction =
    this.show(碎片)

/**
 * 描述：隐藏
 *
 * 版本：0.1.1
 */
fun FragmentTransaction.隐藏(碎片: Fragment): FragmentTransaction =
    this.hide(碎片)

/**
 * 描述：添加回退栈
 *
 * 版本：0.1.1
 */
fun FragmentTransaction.添加回退栈(回退栈名称: String): FragmentTransaction =
    this.addToBackStack(回退栈名称)

/**
 * 描述：提交
 *
 * 版本：0.1.1
 */
fun FragmentTransaction.提交(): Int =
    this.commit()


//=======================================================================

