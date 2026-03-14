package 自定义.对话框类

import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import zwkfb.view.R
import 商业.谷歌.安卓.材质.底部面板.底部面板对话框碎片
import 自定义.状态栏类.状态栏沉浸式
import 自定义.状态栏类.状态栏沉浸式类
import 自定义.系统类.屏幕类
import 自定义.系统类.系统类

class 材质底部信息对话框 : 底部面板对话框碎片(){

    private var 根布局: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = true  // 保留实例
        根布局 = inflater.inflate(R.layout.dialog_czdbxxdhk, container, false)
        return 根布局
    }

    //======================================================================
    //======================================================================

    private var 滑动条: CardView? = null
    private var 标题栏: TextView? = null
    private var 内容栏: AppCompatTextView? = null

    //======================================================================

    private var 横底部按钮框: LinearLayout? = null
    private var 横底部忽略按钮: MaterialButton? = null
    private var 横底部取消按钮: MaterialButton? = null
    private var 横底部确定按钮: MaterialButton? = null

    //======================================================================

    private var 竖底部按钮框: LinearLayout? = null
    private var 竖底部忽略按钮: MaterialButton? = null
    private var 竖底部取消按钮: MaterialButton? = null
    private var 竖底部确定按钮: MaterialButton? = null

    //======================================================================

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 初始化所有视图引用
        滑动条 = 根布局?.findViewById(R.id.hdt)
        标题栏 = 根布局?.findViewById(R.id.btl)
        内容栏 = 根布局?.findViewById(R.id.nr)

        // 横向按钮布局
        横底部按钮框 = 根布局?.findViewById(R.id.hank)
        横底部忽略按钮 = 根布局?.findViewById(R.id.h_hlan)
        横底部取消按钮 = 根布局?.findViewById(R.id.h_qxan)
        横底部确定按钮 = 根布局?.findViewById(R.id.h_qdan)

        // 纵向按钮布局
        竖底部按钮框 = 根布局?.findViewById(R.id.sank)
        竖底部忽略按钮 = 根布局?.findViewById(R.id.s_hlan)
        竖底部取消按钮 = 根布局?.findViewById(R.id.s_qxan)
        竖底部确定按钮 = 根布局?.findViewById(R.id.s_qdan)

        // 更新UI状态（每次都要执行）
        更新界面状态()

        设置按钮点击事件()

        // 处理状态栏沉浸式
        处理状态栏沉浸式()
    }

    //======================================================================
    //======================================================================

    private var 是否隐藏状态栏导航栏: Boolean = false

    private fun 处理状态栏沉浸式() {
        if (是否隐藏状态栏导航栏) {
            状态栏沉浸式类.隐藏状态栏(requireActivity(), dialog?.window!!)
        }else {
            状态栏沉浸式类.显示状态栏导航栏(requireActivity(), dialog?.window!!)
        }
    }

    //=====================================================================
    //=====================================================================

    private var 是否显示对话框: Boolean = false

    override fun onDestroy() {
        super.onDestroy()
        是否显示对话框 = false
    }

    //=====================================================================
    //=====================================================================

    private var 滑动条可视: Int = 可视
    private var 滑动条宽度: Int = 50
    private var 滑动条高度: Int = 3
    private var 滑动条背景颜色: Int = "#A9A9A9".toColorInt()

    private var 标题: CharSequence? = null
    private var 内容: CharSequence? = null
    private var 内容可选: Boolean = false

    private var 忽略按钮文本: CharSequence? = null
    private var 忽略按钮单击事件: 单击事件? = null

    private var 取消按钮文本: CharSequence? = null
    internal var 取消按钮单击事件: 单击事件? = null

    private var 确定按钮文本: CharSequence? = null
    internal var 确定按钮单击事件: 单击事件? = null

    private fun 更新界面状态() {
        滑动条?.visibility = 滑动条可视
        滑动条?.layoutParams?.width = 屏幕类.dp转px(requireActivity(),滑动条宽度)
        滑动条?.layoutParams?.height = 屏幕类.dp转px(requireActivity(),滑动条高度)
        滑动条?.setCardBackgroundColor(滑动条背景颜色)

        标题栏?.text = 标题
        标题栏?.visibility = if (标题.isNullOrEmpty()) 不可视隐藏 else 可视

        内容栏?.text = 内容
        内容栏?.visibility = if (内容.isNullOrEmpty()) 不可视隐藏 else 可视
        内容栏?.setTextIsSelectable(内容可选)

        // 忽略按钮
        横底部忽略按钮?.text = 忽略按钮文本
        横底部忽略按钮?.visibility = if (忽略按钮文本.isNullOrEmpty()) 不可视隐藏 else 可视

        竖底部忽略按钮?.text = 忽略按钮文本
        竖底部忽略按钮?.visibility = if (忽略按钮文本.isNullOrEmpty()) 不可视隐藏 else 可视

        // 取消按钮
        横底部取消按钮?.text = 取消按钮文本
        横底部取消按钮?.visibility = if (取消按钮文本.isNullOrEmpty()) 不可视隐藏 else 可视

        竖底部取消按钮?.text = 取消按钮文本
        竖底部取消按钮?.visibility = if (取消按钮文本.isNullOrEmpty()) 不可视隐藏 else 可视

        // 确定按钮
        横底部确定按钮?.text = 确定按钮文本
        横底部确定按钮?.visibility = if (确定按钮文本.isNullOrEmpty()) 不可视隐藏 else 可视

        竖底部确定按钮?.text = 确定按钮文本
        竖底部确定按钮?.visibility = if (确定按钮文本.isNullOrEmpty()) 不可视隐藏 else 可视

        // 按钮方向布局
        if (按钮方向 == 横向) {
            横底部按钮框?.visibility = 可视
            竖底部按钮框?.visibility = 不可视隐藏
        } else {
            横底部按钮框?.visibility = 不可视隐藏
            竖底部按钮框?.visibility = 可视
        }
    }

    //=====================================================================
    //=====================================================================

    private fun 设置按钮点击事件() {
        横底部忽略按钮?.setOnClickListener {
            忽略按钮单击事件?.单击事件(requireDialog())
        }

        横底部取消按钮?.setOnClickListener {
            取消按钮单击事件?.单击事件(requireDialog())
        }

        横底部确定按钮?.setOnClickListener {
            确定按钮单击事件?.单击事件(requireDialog())
        }

        竖底部忽略按钮?.setOnClickListener {
            忽略按钮单击事件?.单击事件(requireDialog())
        }

        竖底部取消按钮?.setOnClickListener {
            取消按钮单击事件?.单击事件(requireDialog())
        }

        竖底部确定按钮?.setOnClickListener {
            确定按钮单击事件?.单击事件(requireDialog())
        }
    }

    //=====================================================================
    //=====================================================================

    override fun onResume() {
        super.onResume()
        配置底部面板行为()
    }

    //=====================================================================
    //=====================================================================

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        // 屏幕旋转时更新界面
        更新界面状态()
        处理状态栏沉浸式()
        配置底部面板行为()
    }

    //=====================================================================
    //=====================================================================

    fun 是否显示对话框() : Boolean {
        return 是否显示对话框
    }

    //======================================================================

    fun 隐藏状态栏导航栏(): 材质底部信息对话框 {
        是否隐藏状态栏导航栏 = true
        return this
    }

    fun 显示状态栏导航栏(): 材质底部信息对话框 {
        是否隐藏状态栏导航栏 = false
        return this
    }

    //==============================================================================================
    //==============================================================================================

    fun 置滑动条可视(可视: Int): 材质底部信息对话框 {
        this.滑动条可视 = 可视
        return this
    }

    //==============================================================================================
    //==============================================================================================

    fun 置滑动条宽度(宽度: Int): 材质底部信息对话框 {
        this.滑动条宽度 = 宽度
        return this
    }

    fun 置滑动条高度(高度: Int): 材质底部信息对话框 {
        this.滑动条高度 = 高度
        return this
    }

    fun 置滑动条宽高(宽度: Int, 高度: Int): 材质底部信息对话框 {
        this.滑动条宽度 = 宽度
        this.滑动条高度 = 高度
        return this
    }

    //==============================================================================================
    //==============================================================================================

    fun 置滑动条背景颜色(颜色: Int): 材质底部信息对话框 {
        this.滑动条背景颜色 = 颜色
        return this
    }

    fun 置滑动条背景颜色(颜色: String): 材质底部信息对话框 {
        this.滑动条背景颜色 = 颜色.toColorInt()
        return this
    }

    //==============================================================================================
    //==============================================================================================

    fun 置标题(标题: String): 材质底部信息对话框 {
        this.标题 = 标题
        return this
    }

    //==============================================================================================
    //==============================================================================================

    fun 置内容(内容: String): 材质底部信息对话框 {
        this.内容 = 内容
        return this
    }

    //======================================================================

    fun 置内容可选(可选: Boolean): 材质底部信息对话框 {
        this.内容可选 = 可选
        return this
    }

    //======================================================================

    private var 按钮方向: Int = 0
    fun 置按钮方向(方向: Int): 材质底部信息对话框 {
        this.按钮方向 = 方向
        return this
    }

    //======================================================================

    fun 置忽略按钮单击事件(文本: String, 事件: 单击事件): 材质底部信息对话框 {
        this.忽略按钮文本 = 文本
        this.忽略按钮单击事件 = 事件
        return this
    }

    fun 置忽略按钮单击事件(文本: String, 事件: (Dialog) -> Unit): 材质底部信息对话框 {
        this.忽略按钮文本 = 文本
        this.忽略按钮单击事件 = object : 单击事件 {
            override fun 单击事件(对话框: Dialog) {
                事件(对话框)
            }
        }
        return this
    }

    //======================================================================

    fun 置取消按钮单击事件(文本: CharSequence, 事件: 单击事件): 材质底部信息对话框 {
        this.取消按钮文本 = 文本
        this.取消按钮单击事件 = 事件
        return this
    }

    fun 置取消按钮单击事件(文本: CharSequence, 事件: (Dialog) -> Unit): 材质底部信息对话框 {
        this.取消按钮文本 = 文本
        this.取消按钮单击事件 = object : 单击事件 {
            override fun 单击事件(对话框: Dialog) {
                事件(对话框)
            }
        }
        return this
    }

    //======================================================================

    fun 置确定按钮单击事件(文本: CharSequence, 事件: 单击事件): 材质底部信息对话框 {
        this.确定按钮文本 = 文本
        this.确定按钮单击事件 = 事件
        return this
    }

    fun 置确定按钮单击事件(文本: CharSequence, 事件: (Dialog) -> Unit): 材质底部信息对话框 {
        this.确定按钮文本 = 文本
        this.确定按钮单击事件 = object : 单击事件 {
            override fun 单击事件(对话框: Dialog) {
                事件(对话框)
            }
        }
        return this
    }

    //======================================================================

    interface 单击事件 {
        fun 单击事件(对话框: Dialog)
    }

    //======================================================================

    fun 显示(管理器: FragmentManager, 标记: String) {
        是否显示对话框 = true
        this.show(管理器, 标记)
    }

    fun 显示(事务: FragmentTransaction, 标记: String): Int {
        是否显示对话框 = true
        return this.show(事务, 标记)
    }

    //======================================================================

    private var 底部面板行为: BottomSheetBehavior<*>? = null
    private var 底部面板视图: View? = null

    //======================================================================

    private var 默认配置: Boolean = false
    private var 布局最大高度: Int = -2
    private var 布局折叠高度: Int = 500
    private var 展示方式: Int = BottomSheetBehavior.STATE_EXPANDED
    private var 可拖动: Boolean = true

    //======================================================================

    fun 默认配置(值: Boolean = true): 材质底部信息对话框 {
        this.默认配置 = 值
        return this
    }

    fun 布局最大高度(高度: Int): 材质底部信息对话框 {
        this.布局最大高度 = 高度
        return this
    }

    fun 布局折叠高度(高度: Int): 材质底部信息对话框 {
        this.布局折叠高度 = 高度
        return this
    }

    fun 展示方式(展示方式: Int): 材质底部信息对话框 {
        this.展示方式 = 展示方式
        return this
    }

    fun 可拖动(值: Boolean): 材质底部信息对话框 {
        this.可拖动 = 值
        return this
    }

    private fun 配置底部面板行为() {
        // 获取 BottomSheet 的 FrameLayout
        底部面板视图 = dialog?.findViewById(com.google.android.material.R.id.design_bottom_sheet)
        底部面板行为 = BottomSheetBehavior.from<View>(底部面板视图!!)

        if (默认配置) {
            val 屏幕高度 = 屏幕类.取用户屏幕高度(requireActivity())
            val 状态栏高度 = 状态栏沉浸式.取状态栏高度(requireActivity())
            val 导航栏高度 = 状态栏沉浸式.取导航栏高度(requireActivity())
            val 默认边距 = 10

            底部面板行为!!.apply {
                maxHeight = when {
                    系统类.是否为手机(requireActivity()) -> {
                        when {
                            系统类.是否处于横屏(requireActivity()) -> 屏幕高度 - 状态栏高度 - 默认边距
                            状态栏沉浸式.是否手势(requireActivity()) -> 屏幕高度 - 状态栏高度 - 默认边距
                            else -> 屏幕高度 - 状态栏高度 - 导航栏高度 - 默认边距
                        }
                    }
                    else -> -1
                }
                isDraggable = true
                peekHeight = 屏幕高度 / 2
                skipCollapsed = false
            }
        } else {
            底部面板行为!!.apply {
                maxHeight = 布局最大高度
                state = 展示方式
                isDraggable = 可拖动
                peekHeight = 布局折叠高度
                skipCollapsed = false
            }
        }
    }

    //======================================================================

    companion object {
        const val 可视 = View.VISIBLE // 可视
        const val 不可视 = View.INVISIBLE // 不可视
        const val 不可视隐藏 = View.GONE // 不可视隐藏

        //======================================================================

        const val 横向 = 0 // 横向
        const val 纵向 = 1 // 纵向

    }

}