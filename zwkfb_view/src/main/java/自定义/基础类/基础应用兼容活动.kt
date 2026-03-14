package 自定义.基础类

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.core.graphics.toColorInt
import androidx.fragment.app.FragmentActivity
import androidx.viewbinding.ViewBinding
import 安卓.应用.置内容视图
import 安卓.应用.重建
import 安卓x.应用兼容.应用.应用兼容活动
import 安卓x.活动.启用边缘到边缘
import 自定义.主题配置.主题存储


abstract class 基础应用兼容活动<T: ViewBinding> : 应用兼容活动() {

    open val 上下文: Context get() = this

    open val 活动: Activity get() = this
    open val 碎片活动: FragmentActivity get() = this

    protected abstract val 视图组件: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        启用边缘到边缘()
        置内容视图(视图组件.root)
        活动创建完成(savedInstanceState)
    }

    open fun 活动创建完成(保存数据状态: Bundle?){}

    //=========================================================================

    fun 主题强调色颜色切换(颜色: String, 信息: String) {
        主题存储.初始主题(上下文).置强调色(颜色.toColorInt()).应用()
        this.重建()
        Toast.makeText(上下文, 信息, Toast.LENGTH_SHORT).show()
    }

}
