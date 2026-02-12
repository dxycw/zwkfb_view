package 自定义.对话框类

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomsheet.BottomSheetBehavior
import 自定义.状态栏类.状态栏沉浸式
import 自定义.系统类.是否为手机
import 自定义.系统类.是否处于横屏
import 自定义.系统类.获取用户屏幕高度

open class 材质底部面板对话框碎片 : 商业.谷歌.安卓.材质.底部面板.底部面板对话框碎片() {

    //=====================================================
    private var 根布局: ViewGroup? = null
    //=====================================================
    private val 待添加组件队列 = mutableListOf<Pair<View, ViewGroup.LayoutParams?>>() // 恢复组件队列
    //=====================================================
    private var 默认配置: Boolean = false
    lateinit var 上下文: FragmentActivity
    //=====================================================

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        上下文 = requireActivity()
        初始视图函数(inflater, container)
        return 根布局!!
    }

    //=====================================================

    private lateinit var 抽屉对话框布局: View
    private lateinit var 视图配置: BottomSheetBehavior<*>

    private var 是否隐藏对话框子窗口状态栏: Boolean = false
    private var 修复对话框子窗口导航栏遮盖问题: Boolean = false

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

        材质底部碎片对话框窗口配置()

        if (是否隐藏对话框子窗口状态栏){
            状态栏沉浸式.隐藏对话框子窗口状态栏(上下文,dialog!!,true)
        }else{
            状态栏沉浸式.显示对话框子窗口状态栏(上下文,dialog!!,true)
        }

        if (修复对话框子窗口导航栏遮盖问题){
            状态栏沉浸式.修复对话框子窗口导航栏遮盖问题(上下文, this)
        }

    }

    open fun 初始视图函数(填充器: LayoutInflater, 容器: ViewGroup?) {}

    //=====================================================

    // 设置 XML 布局
    fun 添加布局(@LayoutRes 布局资源id: Int): 材质底部面板对话框碎片 {
        根布局 = layoutInflater.inflate(布局资源id, null, false) as? ViewGroup
            ?: throw IllegalArgumentException("XML 根元素必须是 ViewGroup")
        return this
    }

    // 增强型添加组件方法
    fun 添加布局(组件: View, 布局参数: ViewGroup.LayoutParams? = null): 材质底部面板对话框碎片 {
        根布局?.let { 布局 ->
            布局参数?.let { 布局.addView(组件, it) } ?: 布局.addView(组件)
        } ?: run { 待添加组件队列.add(组件 to 布局参数) }
        return this
    }

    fun <T:View> 查找视图id(@IdRes id: Int): T?{
        return 根布局?.findViewById<T>(id)
    }

    //=====================================================

    fun 显示(碎片管理器: FragmentManager, 标记: String) {
        this.show(碎片管理器, 标记)
    }

    fun 显示(事务: FragmentTransaction, 标记: String) {
        this.show(事务, 标记)
    }

    //=====================================================

    // 配置参数
    private var 布局最大高度: Int = -2
    private var 布局折叠高度: Int = 500
    private var 展示方式: Int = BottomSheetBehavior.STATE_EXPANDED
    private var 可拖动: Boolean = true

    //=====================================================

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

    /**
     * 显示隐藏对话框子窗口状态栏
     * @param 值 true 显示，false 隐藏
     */
    open fun 隐藏对话框子窗口状态栏(值: Boolean = false): 材质底部面板对话框碎片 {
        是否隐藏对话框子窗口状态栏 = 值
        return this
    }

    open fun 修复对话框子窗口导航栏遮盖问题(): 材质底部面板对话框碎片 {
        修复对话框子窗口导航栏遮盖问题 = true
        return this
    }

    override fun onResume() {
        super.onResume()
        材质底部碎片对话框窗口配置()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        材质底部碎片对话框窗口配置()
    }

    override fun onDestroy() {
        super.onDestroy()
//        if (是否隐藏对话框子窗口状态栏){
//            状态栏沉浸式.显示对话框子窗口状态栏(上下文,true)
//        }else{
//            状态栏沉浸式.显示对话框子窗口状态栏(上下文,true)
//        }
    }

    private fun 材质底部碎片对话框窗口配置() {
        if (默认配置) {
            val 屏幕高度 = 上下文.获取用户屏幕高度()
            val 状态栏高度 = 状态栏沉浸式.获取状态栏高度(上下文)
            val 导航栏高度 = 状态栏沉浸式.获取导航栏高度(上下文)
            val 默认边距 = 10

            视图配置.apply {
                maxHeight = when {
                    上下文.是否为手机() -> {
                        when {
                            上下文.是否处于横屏() -> 屏幕高度 - 状态栏高度 - 默认边距
                            状态栏沉浸式.是否是手势(上下文) -> 屏幕高度 - 状态栏高度 - 默认边距
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
                状态栏沉浸式.隐藏对话框子窗口状态栏(上下文,dialog!!)
            }else{
                状态栏沉浸式.显示对话框子窗口状态栏(上下文,dialog!!)
            }
            状态栏沉浸式.修复对话框子窗口导航栏遮盖问题(上下文,this)
        } else {
            视图配置.apply {
                maxHeight = 布局最大高度
                state = 展示方式
                isDraggable = 可拖动
                peekHeight = 布局折叠高度
            }
        }
    }

    companion object {
        var 展开 = BottomSheetBehavior.STATE_EXPANDED
        var 收起 = BottomSheetBehavior.STATE_COLLAPSED
        var 中间 = BottomSheetBehavior.STATE_HALF_EXPANDED
        var 完全 = BottomSheetBehavior.STATE_HIDDEN
    }

}