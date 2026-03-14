package 自定义.基础类

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import 安卓x.碎片.应用.碎片


abstract class 基础碎片<T: ViewBinding> : 碎片(){

    open val 上下文: Context get() = requireContext()

    open val 活动: Activity get() = requireActivity()
    open val 碎片活动: FragmentManager get() = requireFragmentManager()

    protected abstract val 视图组件: T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        碎片创建完成()
        return 视图组件.root
    }


    open fun 碎片创建完成() {}

}