package 自定义.列表类

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.setMargins
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.R
import com.google.android.material.button.MaterialButton
import 安卓x.回收视图.组件.回收视图
import 自定义.动画类.动画类
import 自定义.系统类.dp转px
import 自定义.资源类.attr转int
import 自定义.资源类.int转Drawable

class 图文文高级列表 @JvmOverloads constructor(
    val 上下文: Context,
    val attrs: AttributeSet?,
    val defStyleAttr: Int = 0,
) : 回收视图(上下文, attrs, defStyleAttr) {

    private var 适配器: 图文文高级列表适配器? = null

    init {
        this.适配器 = 图文文高级列表适配器(上下文)
        this.setAdapter(适配器)
        this.setLayoutManager(GridLayoutManager(上下文, 1)) //列表布局,垂直,必须写
    }

    fun 添加项目(图标: Drawable? = null, 标题: String?, 按钮: String? = null) {
        适配器?.图标?.add(图标)
        适配器?.标题?.add(标题)
        适配器?.按钮?.add(按钮)
        适配器!!.刷新()
    }

    fun 添加项目(图标: Int = -1, 标题: String?, 按钮: String? = null) {
        适配器?.图标?.add(if (图标 == -1) null else 上下文.int转Drawable(图标))
        适配器?.标题?.add(标题)
        适配器?.按钮?.add(按钮)
        适配器!!.刷新()
    }


    fun 删除项目(项目序数: Int) {
        适配器?.图标?.removeAt(项目序数)
        适配器?.标题?.removeAt(项目序数)
        适配器?.按钮?.removeAt(项目序数)
        适配器!!.刷新()
    }

    fun 清空项目() {
        适配器?.图标?.clear()
        适配器?.标题?.clear()
        适配器?.按钮?.clear()
        适配器!!.刷新()
    }

    fun 获取项目标题(项目序数: Int): String? {
        return if (项目序数 >= 0 && 项目序数 < 适配器?.标题?.size!!) {
            适配器?.标题[项目序数]
        } else {
            适配器?.标题[if (项目序数 < 0) 0 else 适配器?.标题?.size!! - 1]
        }
    }

    fun 获取项目总数(): Int {
        return 适配器?.标题!!.size
    }

    fun 获取项目标题(): ArrayList<String?> {
        return 适配器?.标题!!
    }



    fun 置标题单行(值: Boolean) {
        适配器?.标题单行 = 值
        适配器?.刷新()
    }


    fun 置图标宽高(宽度: Int, 高度: Int) {
        适配器?.图标宽度 = 宽度
        适配器?.图标高度 = 高度
        适配器?.刷新()
    }

    fun 置按钮宽高(宽度: Int, 高度: Int) {
        适配器?.按钮宽度 = 宽度
        适配器?.按钮高度 = 高度
        适配器?.刷新()
    }


    fun 置单击项目事件(项目单击事件: (项目序数: Int) -> Unit) {
        适配器?.单击事件 = object : 项目单击事件 {
            override fun 列表项目单击事件(项目序数: Int) {
                项目单击事件(项目序数)
            }
        }
    }

    fun 置单击项目事件(单击项目事件: 项目单击事件?) {
        适配器?.单击事件 = 单击项目事件
    }

    fun 置长按项目事件(项目长按事件: (项目序数: Int) -> Unit) {
        适配器?.长按事件 = object : 项目长按事件 {
            override fun 列表项目长按事件(项目序数: Int) {
                项目长按事件(项目序数)
            }
        }
    }

    fun 置长按项目事件(长按项目事件: 项目长按事件?) {
        适配器?.长按事件 = 长按项目事件
    }






    fun 置单击按钮事件(项目单击事件: (项目序数: Int) -> Unit) {
        适配器?.单击按钮事件 = object : 项目单击事件 {
            override fun 列表项目单击事件(项目序数: Int) {
                项目单击事件(项目序数)
            }
        }
    }

    fun 置单击按钮事件(单击项目事件: 项目单击事件?) {
        适配器?.单击按钮事件 = 单击项目事件
    }

    fun 置长按按钮事件(项目长按事件: (项目序数: Int) -> Unit) {
        适配器?.长按按钮事件 = object : 项目长按事件 {
            override fun 列表项目长按事件(项目序数: Int) {
                项目长按事件(项目序数)
            }
        }
    }

    fun 置长按按钮事件(长按项目事件: 项目长按事件?) {
        适配器?.长按按钮事件 = 长按项目事件
    }


    private class 图文文高级列表适配器(val 上下文: Context) : Adapter<viewHolder>(){

        var 单击事件: 项目单击事件? = null // 定义接口
        var 长按事件: 项目长按事件? = null // 定义接口

        var 单击按钮事件: 项目单击事件? = null // 定义接口
        var 长按按钮事件: 项目长按事件? = null // 定义接口

        val 图标 = ArrayList<Drawable?>()
        val 标题 = ArrayList<String?>()
        val 按钮 = ArrayList<String?>()

        var 标题单行: Boolean = false

        var 图标宽度: Int = 25
        var 图标高度: Int = 25

        var 按钮宽度: Int = 25
        var 按钮高度: Int = 25

        // 创建ViewHolder
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
            val 布局 = LinearLayout(上下文).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            }
            return viewHolder(布局) // 返回ViewHolder对象
        }

        @SuppressLint("NewApi")
        override fun onBindViewHolder(holder: viewHolder, position: Int) {

            val 项目图标 = 图标[position]
            val 项目文本 = 标题[position]
            val 项目按钮 = 按钮[position]

            if (项目图标 != null){
                holder.列表图标.visibility = VISIBLE
                holder.列表图标.setImageDrawable(项目图标)
                val 图标布局参数 = LinearLayout.LayoutParams(holder.列表图标.layoutParams)
                图标布局参数.width = 上下文.dp转px(图标宽度)
                图标布局参数.height = 上下文.dp转px( 图标高度)
                图标布局参数.setMargins(上下文.dp转px(15))
                holder.列表图标.setLayoutParams(图标布局参数)
            }else{
                holder.列表图标.visibility = GONE
            }

            holder.列表标题.text = 项目文本
            holder.列表标题.textSize = 16f
            holder.列表标题.setSingleLine(标题单行)
            val 标题布局参数 = LinearLayout.LayoutParams(holder.列表标题.layoutParams)
            标题布局参数.weight = 1f
            标题布局参数.width = LinearLayout.LayoutParams.MATCH_PARENT
            标题布局参数.height = LinearLayout.LayoutParams.WRAP_CONTENT
            if (项目图标 == null) {
                标题布局参数.setMargins(上下文.dp转px(15))
            }
            holder.列表标题.setLayoutParams(标题布局参数)


            if (项目按钮 != null){
                holder.列表按钮.visibility = VISIBLE
                holder.列表按钮.text = 项目按钮
                holder.列表按钮.textSize = 12f
                val 按钮布局参数 = LinearLayout.LayoutParams(holder.列表按钮.layoutParams)
//                按钮布局参数.width = 上下文.dp转px(80)
                按钮布局参数.height = 上下文.dp转px(40)
                按钮布局参数.marginEnd = 上下文.dp转px(15)
                holder.列表按钮.setLayoutParams(按钮布局参数)
                动画类.变灰效果(holder.列表按钮)
                holder.列表按钮.setOnClickListener { v: View? ->
                    if (单击按钮事件 != null) {
                        单击按钮事件!!.列表项目单击事件(holder.getAdapterPosition())
                    }
                }
                holder.列表按钮.setOnLongClickListener { v: View? ->
                    if (长按按钮事件 != null) {
                        长按按钮事件!!.列表项目长按事件(holder.getAdapterPosition())
                    }
                    true
                }
            }else{
                holder.列表按钮.visibility = GONE
            }

            holder.itemView.setOnClickListener { v: View? ->
                if (单击事件 != null) {
                    单击事件!!.列表项目单击事件(holder.getAdapterPosition())
                }
            }
            holder.itemView.setOnLongClickListener { v: View? ->
                if (长按事件 != null) {
                    长按事件!!.列表项目长按事件(holder.getAdapterPosition())
                }
                true
            }

        }

        override fun getItemCount(): Int {
            return 标题.size
        }

        @SuppressLint("NotifyDataSetChanged")
        fun 刷新() {
            this.notifyDataSetChanged()
        }

    }

    private class viewHolder(项目视图: ViewGroup) : ViewHolder(项目视图) {

        var 列表框 = LinearLayout(项目视图.context).apply {
            layoutParams = LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.HORIZONTAL
            setBackgroundResource(
                项目视图.context.attr转int(android.R.attr.selectableItemBackground)
            )
            gravity = Gravity.CENTER
        }

        var 列表图标 = ImageView(项目视图.context).apply {
            val 图标布局参数 = LinearLayout.LayoutParams(
                项目视图.context.dp转px(25),
                项目视图.context.dp转px(25)
            )
            图标布局参数.setMargins(项目视图.context.dp转px(15))
            layoutParams = 图标布局参数
        }
        var 列表标题 = TextView(项目视图.context).apply {
            val 标题布局参数 = LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT,1f
            )
            layoutParams = 标题布局参数

            text = "标题"
        }
        var 列表按钮 = MaterialButton(项目视图.context,null, R.attr.materialButtonOutlinedStyle).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            gravity = Gravity.CENTER

            text = "按钮标题"
        }

        init {
            列表框.addView(列表图标)
            列表框.addView(列表标题)
            列表框.addView(列表按钮)
            项目视图.addView(列表框)
        }

    }

    // 接口定义
    interface 项目单击事件 {
        fun 列表项目单击事件(项目序数: Int)
    }

    interface 项目长按事件 {
        fun 列表项目长按事件(项目序数: Int)
    }

}
