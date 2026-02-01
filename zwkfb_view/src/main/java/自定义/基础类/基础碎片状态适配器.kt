package 自定义.基础类

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import 安卓x.视图分页器2.适配器.碎片状态适配器

class 基础碎片状态适配器: 碎片状态适配器 {
    private var 数据: List<Fragment>
    constructor(碎片窗口: FragmentActivity, 数据: List<Fragment>) : super(碎片窗口){
        this.数据 = 数据
    }

    override fun 取项目数量(): Int { return 数据.size }

    override fun 创建碎片(索引: Int): Fragment {
        return when(索引){
            in 0 until 数据.size -> 数据[索引]
            else -> 数据[0]
        }
    }

}