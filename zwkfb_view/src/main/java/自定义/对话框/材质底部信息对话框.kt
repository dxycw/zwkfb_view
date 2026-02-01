package 自定义.对话框

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColorInt
import androidx.fragment.app.FragmentActivity
import com.google.android.material.button.MaterialButton
import zwkfb.view.databinding.DialogCzdbxxdhkBinding
import 自定义.系统类.dp转px

class 材质底部信息对话框 : 材质底部面板对话框碎片() {
    companion object{
        var 可视 = View.VISIBLE // 可视
        var 不可视 = View.INVISIBLE // 不可视
        var 不可视隐藏 = View.GONE // 不可视隐藏

        var 横向 = 0 // 横向
        var 纵向 = 1 // 纵向
    }

    private lateinit var 上下文: FragmentActivity
    private lateinit var 底部信息对话框布局: DialogCzdbxxdhkBinding
    private var 滑动条: CardView? = null
    private var 标题栏: TextView? = null
    private var 内容栏: AppCompatTextView? = null

    private var 按钮方向:Int = 0

    private var 横底部按钮框: LinearLayout? = null
    private var 横底部忽略按钮: MaterialButton? = null
    private var 横底部取消按钮: MaterialButton? = null
    private var 横底部确定按钮: MaterialButton? = null

    private var 竖底部按钮框: LinearLayout? = null
    private var 竖底部忽略按钮: MaterialButton? = null
    private var 竖底部取消按钮: MaterialButton? = null
    private var 竖底部确定按钮: MaterialButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        上下文 = requireActivity()
        底部信息对话框布局 = DialogCzdbxxdhkBinding.inflate(layoutInflater)

        初始代码()
        return 底部信息对话框布局.root
    }

    private var 滑动条可视: Int = 可视
    private var 滑动条宽度: Int = 50
    private var 滑动条高度: Int = 3
    private var 滑动条背景颜色: Int = "#A9A9A9".toColorInt()

    private var 标题: CharSequence? = null
    private var 内容: CharSequence? = null
    private var 内容可选: Boolean = false

    private var 忽略按钮文本: CharSequence? = null
    private var 取消按钮文本: CharSequence? = null
    private var 确定按钮文本: CharSequence? = null

    private var 底部忽略按钮单击事件: 点击事件? = null
    private var 底部取消按钮单击事件: 点击事件? = null
    private var 底部确定按钮单击事件: 点击事件? = null
    fun 初始代码() {
        滑动条 = 底部信息对话框布局.hdt
        滑动条?.visibility = 滑动条可视
        滑动条?.layoutParams?.width = 上下文.dp转px(滑动条宽度)
        滑动条?.layoutParams?.height = 上下文.dp转px(滑动条高度)
        滑动条?.setCardBackgroundColor(滑动条背景颜色)
        标题栏 = 底部信息对话框布局.btl
        内容栏 = 底部信息对话框布局.nr
        标题栏?.text = 标题
        if (标题 == null || 标题 == "") {
            标题栏?.visibility = 不可视隐藏
        } else {
            标题栏?.visibility = 可视
        }
        内容栏?.text = 内容
        if (内容 == null || 内容 == "") {
            内容栏?.visibility = 不可视隐藏
        } else {
            内容栏?.visibility = 可视
        }
        内容栏?.setTextIsSelectable(内容可选)

        横底部按钮框 = 底部信息对话框布局.hank
        横底部忽略按钮 = 底部信息对话框布局.hHlan
        横底部忽略按钮?.text = 忽略按钮文本
        if (忽略按钮文本 == null || 忽略按钮文本 == "") {
            横底部忽略按钮?.visibility = 不可视隐藏
        } else {
            横底部忽略按钮?.visibility = 可视
        }
        this.横底部忽略按钮?.setOnClickListener{ view ->
            if (底部忽略按钮单击事件 != null) {
                底部忽略按钮单击事件?.点击事件(requireDialog())
            }
        }

        横底部取消按钮 = 底部信息对话框布局.hQxan
        横底部取消按钮?.text = 取消按钮文本
        if (取消按钮文本 == null || 取消按钮文本 == "") {
            横底部取消按钮?.visibility = 不可视隐藏
        } else {
            横底部取消按钮?.visibility = 可视
        }
        this.横底部取消按钮?.setOnClickListener{ view ->
            if (底部取消按钮单击事件 != null) {
                底部取消按钮单击事件?.点击事件(requireDialog())
            }
        }

        横底部确定按钮 = 底部信息对话框布局.hQdan
        横底部确定按钮?.text = 确定按钮文本
        if (确定按钮文本 == null || 确定按钮文本 == "") {
            横底部确定按钮?.visibility = 不可视隐藏
        } else {
            横底部确定按钮?.visibility = 可视
        }
        this.横底部确定按钮?.setOnClickListener{ view ->
            if (底部确定按钮单击事件 != null) {
                底部确定按钮单击事件?.点击事件(requireDialog())
            }
        }


        竖底部按钮框 = 底部信息对话框布局.sank
        竖底部忽略按钮 = 底部信息对话框布局.sHlan
        竖底部忽略按钮?.text = 忽略按钮文本
        if (忽略按钮文本 == null || 忽略按钮文本 == "") {
            竖底部忽略按钮?.visibility = 不可视隐藏
        } else {
            竖底部忽略按钮?.visibility = 可视
        }
        this.竖底部忽略按钮?.setOnClickListener{ view ->
            if (底部忽略按钮单击事件 != null) {
                底部忽略按钮单击事件?.点击事件(requireDialog())
            }
        }

        竖底部取消按钮 = 底部信息对话框布局.sQxan
        竖底部取消按钮?.text = 取消按钮文本
        if (取消按钮文本 == null || 取消按钮文本 == "") {
            竖底部取消按钮?.visibility = 不可视隐藏
        } else {
            竖底部取消按钮?.visibility = 可视
        }
        this.竖底部取消按钮?.setOnClickListener{ view ->
            if (底部取消按钮单击事件 != null) {
                底部取消按钮单击事件?.点击事件(requireDialog())
            }
        }

        竖底部确定按钮 = 底部信息对话框布局.sQdan
        竖底部确定按钮?.text = 确定按钮文本
        if (确定按钮文本 == null || 确定按钮文本 == "") {
            竖底部确定按钮?.visibility = 不可视隐藏
        } else {
            竖底部确定按钮?.visibility = 可视
        }
        this.竖底部确定按钮?.setOnClickListener{ view ->
            if (底部确定按钮单击事件 != null) {
                底部确定按钮单击事件?.点击事件(requireDialog())
            }
        }

        if (按钮方向 == 横向){
            横底部按钮框?.visibility = 可视
            竖底部按钮框?.visibility = 不可视隐藏
        }else{
            横底部按钮框?.visibility = 不可视隐藏
            竖底部按钮框?.visibility = 可视
        }

    }

    fun 置滑动条可视(可视:Int): 材质底部信息对话框{
        this.滑动条可视 = 可视
        return this
    }

    fun 置滑动条宽度(宽度:Int): 材质底部信息对话框{
        this.滑动条宽度 = 宽度
        return this
    }

    fun 置滑动条高度(高度:Int): 材质底部信息对话框{
        this.滑动条高度 = 高度
        return this
    }

    fun 置滑动条宽高(宽度:Int,高度:Int): 材质底部信息对话框{
        this.滑动条宽度 = 宽度
        this.滑动条高度 = 高度
        return this
    }

    fun 置滑动条背景颜色(颜色:Int): 材质底部信息对话框{
        this.滑动条背景颜色 = 颜色
        return this
    }

    fun 置滑动条背景颜色(颜色: String): 材质底部信息对话框{
        this.滑动条背景颜色 = 颜色.toColorInt()
        return this
    }

    fun 置标题(标题:CharSequence): 材质底部信息对话框{
        this.标题 = 标题
        标题栏?.visibility = 可视
        return this
    }

    fun 置内容(内容:CharSequence): 材质底部信息对话框{
        this.内容 = 内容
        内容栏?.visibility = 可视
        return this
    }

    fun 置内容可选(可选:Boolean): 材质底部信息对话框{
        this.内容可选 = 可选
        return this
    }

    fun 置按钮方向(方向:Int): 材质底部信息对话框{
        按钮方向 = 方向
        return this
    }

    fun 置忽略按钮单击事件(文本: CharSequence, 单击事件: (Dialog) -> Unit): 材质底部信息对话框{
        this.忽略按钮文本 = 文本
        this.底部忽略按钮单击事件 = object : 点击事件 {
            override fun 点击事件(对话框: Dialog) {
                单击事件(对话框)
            }
        }
        if (按钮方向 == 横向) {
            横底部忽略按钮?.visibility = 可视
        }else{
            竖底部忽略按钮?.visibility = 可视
        }
        return this
    }

    fun 置取消按钮单击事件(文本: CharSequence, 单击事件: (Dialog) -> Unit): 材质底部信息对话框{
        this.取消按钮文本 = 文本
        this.底部取消按钮单击事件 = object : 点击事件 {
            override fun 点击事件(对话框: Dialog) {
                单击事件(对话框)
            }
        }
        if (按钮方向 == 横向) {
            横底部取消按钮?.visibility = 可视
        }else{
            竖底部取消按钮?.visibility = 可视
        }

        return this
    }

    fun 置确定按钮单击事件(文本: CharSequence, 单击事件: (Dialog) -> Unit): 材质底部信息对话框 {
        this.确定按钮文本 = 文本
        this.底部确定按钮单击事件 = object : 点击事件 {
            override fun 点击事件(对话框: Dialog) {
                单击事件(对话框)
            }
        }
        if (按钮方向 == 横向) {
            横底部确定按钮?.visibility = 可视
        }else{
            竖底部确定按钮?.visibility = 可视
        }
        return this
    }

    interface 点击事件{
        fun 点击事件(对话框: Dialog)
    }

    override fun 默认配置(值: Boolean): 材质底部信息对话框{
        super.默认配置(值)
        return this
    }

}