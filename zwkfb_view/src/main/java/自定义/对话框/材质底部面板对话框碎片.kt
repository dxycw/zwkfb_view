package 自定义.对话框

import android.app.Activity
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import 自定义.状态栏类.是否是手势
import 自定义.状态栏类.获取导航栏高度
import 自定义.状态栏类.获取状态栏高度
import 自定义.系统类.是否为手机
import 自定义.系统类.是否处于横屏
import 自定义.系统类.获取用户屏幕高度

open class 材质底部面板对话框碎片 : 商业.谷歌.安卓.材质.底部面板.底部面板对话框碎片() {
    private lateinit var 抽屉对话框布局: View
    private lateinit var 视图配置: BottomSheetBehavior<*>
    private var 默认配置: Boolean = false

    private var 预览返回模式: Boolean = false

    private lateinit var 上下文: FragmentActivity
    // 核心变量
    private var 根布局: ViewGroup? = null
    private var 自定义布局ID: Int = -1
    private val 待添加组件队列 = mutableListOf<Pair<View, ViewGroup.LayoutParams?>>() // 恢复组件队列

    // 配置参数
    private var 布局最大高度: Int = -2
    private var 布局折叠高度: Int = 500
    private var 展示方式: Int = BottomSheetBehavior.STATE_EXPANDED
    private var 可拖动: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        上下文 = requireActivity()

        根布局 = when {
            自定义布局ID != -1 -> {
                LayoutInflater.from(上下文).inflate(自定义布局ID, null, false) as? ViewGroup
                    ?: throw IllegalArgumentException("XML 根元素必须是 ViewGroup")
            }
            else -> LinearLayout(context).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                orientation = LinearLayout.VERTICAL
            }
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View { return 根布局!! }

    // 设置 XML 布局
    fun 添加布局(布局资源ID: Int): 材质底部面板对话框碎片 {
        自定义布局ID = 布局资源ID
        return this
    }

    // 增强型添加组件方法
    fun 添加布局(组件: View, 布局参数: ViewGroup.LayoutParams? = null): 材质底部面板对话框碎片 {
        根布局?.let { 布局 ->
            布局参数?.let { 布局.addView(组件, it) } ?: 布局.addView(组件)
        } ?: run { 待添加组件队列.add(组件 to 布局参数) }
        return this
    }

    fun 显示(碎片管理器: FragmentManager, 标记: String) {
        show(碎片管理器, 标记)
    }

    /**
     * 预览返回模式（注意：此功能需要在“AndroidManifest.xml”的application或者activity节点下
     * 添加android:enableOnBackInvokedCallback="true"属性才能使用）
     * @param 值 是否启用预览返回模式
     */
    fun 预览返回模式(值:Boolean = true): 材质底部面板对话框碎片{
        预览返回模式 = 值
        return this
    }

    /**
     * 默认配置 注意：默认配置会覆盖其他配置，如果配置了默认配置，其他配置功能不能用
     */
    open fun 默认配置(值: Boolean = true): 材质底部面板对话框碎片 {
        默认配置 = 值
        return this
    }

    fun 布局最大高度(高度: Int): 材质底部面板对话框碎片 {
        this.布局最大高度 = 高度
        return this
    }

    fun 布局折叠高度(高度: Int): 材质底部面板对话框碎片 {
        this.布局折叠高度 = 高度
        return this
    }

    fun 展示方式(展示方式: Int): 材质底部面板对话框碎片 {
        this.展示方式 = 展示方式
        return this
    }

    fun 可拖动(值: Boolean): 材质底部面板对话框碎片 {
        this.可拖动 = 值
        return this
    }

    fun 隐藏对话框子窗口状态栏(): 材质底部面板对话框碎片 {
        隐藏对话框子窗口状态栏()
        return this
    }

    fun 显示对话框子窗口状态栏(): 材质底部面板对话框碎片 {
        显示对话框子窗口状态栏()
        return this
    }

    fun 修复对话框子窗口导航栏遮盖问题(上下文:Activity): 材质底部面板对话框碎片 {
        修复对话框子窗口导航栏遮盖问题(上下文)
        return this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        抽屉对话框布局 = requireDialog().findViewById(com.google.android.material.R.id.design_bottom_sheet)
        视图配置 = BottomSheetBehavior.from(抽屉对话框布局)

        if (根布局 != null) {
            // 处理缓冲队列
            根布局?.let { 布局 ->
                待添加组件队列.forEach { (组件, 参数) ->
                    参数?.let { 布局.addView(组件, it) } ?: 布局.addView(组件)
                }
                待添加组件队列.clear()
            }
        }

        if (默认配置) {
            材质底部碎片对话框窗口默认配置()
        } else {
            材质底部碎片对话框窗口配置()
        }

    }

    override fun onResume() {
        super.onResume()
        if (默认配置) {
            材质底部碎片对话框窗口默认配置()
        } else {
            材质底部碎片对话框窗口配置()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (默认配置) {
            材质底部碎片对话框窗口默认配置()
        } else {
            材质底部碎片对话框窗口配置()
        }
    }

    private fun 材质底部碎片对话框窗口配置() {
        视图配置.apply {
            maxHeight = 布局最大高度
            state = 展示方式
            isDraggable = 可拖动
            peekHeight = 布局折叠高度
        }
    }

    private fun 材质底部碎片对话框窗口默认配置() {
        val 屏幕高度 = 上下文.获取用户屏幕高度()
        val 状态栏高度 = 获取状态栏高度(上下文)
        val 导航栏高度 = 获取导航栏高度(上下文)
        val 默认边距 = 10

        视图配置.apply {
            maxHeight = when {
                上下文.是否为手机() -> {
                    when {
                        上下文.是否处于横屏() -> 屏幕高度 - 状态栏高度 - 默认边距
                        是否是手势(上下文) -> 屏幕高度 - 状态栏高度 - 默认边距
                        else -> 屏幕高度 - 状态栏高度 - 导航栏高度 - 默认边距 // 修复计算错误
                    }
                }
                else -> -1
            }
            //state = 展示方式
            isDraggable = true
            peekHeight = 屏幕高度 / 2
        }
        if (上下文.是否处于横屏()) {
            隐藏对话框子窗口状态栏()
        }else{
            显示对话框子窗口状态栏()
        }
        修复对话框子窗口导航栏遮盖问题(上下文)
    }

    companion object {
        var 展开 = BottomSheetBehavior.STATE_EXPANDED
        var 收起 = BottomSheetBehavior.STATE_COLLAPSED
        var 中间 = BottomSheetBehavior.STATE_HALF_EXPANDED
        var 完全 = BottomSheetBehavior.STATE_HIDDEN
    }
}