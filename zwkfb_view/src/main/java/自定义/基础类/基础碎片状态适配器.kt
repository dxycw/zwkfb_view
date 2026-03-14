package 自定义.基础类

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import 安卓x.视图分页器2.适配器.碎片状态适配器

class 基础碎片状态适配器: 碎片状态适配器 {
    private var 数据: List<Fragment?> = listOf(null)


    constructor(碎片活动: FragmentActivity) : super(碎片活动)
    constructor(碎片: Fragment) : super(碎片)
    constructor(碎片管理器: FragmentManager, 生命周期: Lifecycle) : super(碎片管理器, 生命周期)

    constructor(碎片活动: FragmentActivity, 数据: List<Fragment>) : super(碎片活动){
        this.数据 = 数据
    }


    override fun createFragment(p0: Int): Fragment {
        return 创建碎片(p0)!!
    }

    override fun getItemCount(): Int {
        return 取项目数量()
    }


    open fun 取项目数量(): Int { return 数据.size }

    open fun 创建碎片(索引: Int): Fragment? {
        return when(索引){
            in 0 until 数据.size -> 数据[索引]
            else -> 数据[0]
        }
    }

}