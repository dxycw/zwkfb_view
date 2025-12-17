package 自定义.列表类

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zwkfb.view.R
import 安卓x.回收视图.组件.回收视图
import 自定义.动画类.动画类
import 自定义.系统类.dp转px
import 自定义.系统类.dp转sp
import 自定义.系统类.是否是深色模式
import 自定义.资源类.attr转int
import 自定义.资源类.int转Drawable


class 图片高级列表框 : 回收视图 {
    var 布局参数: GridLayoutManager? = null
    private var 适配器: 适配器? = null

    var 列数: Int = 3

    constructor(上下文: Context) : super(上下文) {
        init(上下文)
    }

    constructor(上下文: Context, attrs: AttributeSet?) : super(上下文, attrs) {
        init(上下文)
    }

    constructor(上下文: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        上下文,
        attrs,
        defStyleAttr
    ) {
        init(上下文)
    }

    private fun init(上下文: Context) {
        Companion.上下文 = 上下文

        this.适配器 = 适配器()
        布局参数 = GridLayoutManager(上下文, 列数)
        this.setLayoutManager(布局参数) //列表布局,垂直,必须写
    }

    fun 置列数(列数: Int) {
        this.列数 = 列数
        布局参数!!.setSpanCount(列数)
        this.setLayoutManager(布局参数) //列表
    }

    fun 置图标宽高(图标宽度: Int, 图标高度: Int) {
        适配器!!.图标宽度 = 图标宽度
        适配器!!.图标高度 = 图标高度
        适配器!!.刷新()
    }

    fun 置项目单击效果(效果: Int) {
        if (效果 == 图片文字变灰效果) {
            适配器!!.是否图片文字变灰效果 = true
        } else if (效果 == 整体变灰效果) {
            适配器!!.是否图片文字变灰效果 = false
        }
        适配器!!.刷新()
    }

    fun 添加项目(图标: Drawable?, 标题: String?) {
        适配器?.图标?.add(图标)
        适配器?.标题?.add(标题)
        适配器!!.刷新()
    }

    fun 添加项目(图标: Int, 标题: String?) {
        适配器?.图标?.add(上下文!!.int转Drawable(图标))
        适配器?.标题?.add(标题)
        适配器!!.刷新()
    }

    fun 置项目标题单行(标题单行: Boolean) {
        适配器?.标题单行 = 标题单行
        适配器!!.刷新()
    }

    fun 置背景颜色(颜色: Int) {
        适配器?.背景颜色 = 颜色
        适配器!!.刷新()
    }

    fun 置项目标题字体大小(字体大小: Int) {
        适配器?.标题字体大小 = 字体大小
        适配器!!.刷新()
    }

    fun 置项目标题字体颜色(颜色: Int) {
        适配器?.标题字体颜色 = 颜色
        适配器!!.刷新()
    }

    /**
     * 以下为事件方法
     */
    // 设置单击项目事件
    fun 置单击项目事件(项目单击事件: (位置: Int) -> Unit) {
        适配器?.事件 = object : 项目单击事件 {
            override fun 列表项目单击事件(位置: Int) {
                项目单击事件(位置)
            }
        }
    }


    //RecyclerView.Adapter<ViewHolder>
    private class 适配器 : Adapter<ViewHolder?>() {
        var 事件: 项目单击事件? = null // 定义接口
        val 图标: ArrayList<Drawable?> = ArrayList<Drawable?>()

        var 图标宽度: Int = 50
        var 图标高度: Int = 50

        var 是否图片文字变灰效果: Boolean = false

        val 标题: ArrayList<String?> = ArrayList<String?>()

        var 标题单行 = false
        var 背景颜色 = Color.TRANSPARENT
        var 标题字体大小 = 12
        var 标题字体颜色 = -1

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val 视图 = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tpgjlbk, parent, false)
            return ViewHolder(视图)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //
            val 项目图标 = 图标[position]
            holder.列表图标.setImageDrawable(项目图标)
            val layoutParams = LinearLayout.LayoutParams(holder.列表图标.layoutParams)
            layoutParams.width = 上下文!!.dp转px(图标宽度)
            layoutParams.height = 上下文!!.dp转px( 图标高度)
            holder.列表图标.setLayoutParams(layoutParams)

            val 项目文本 = 标题[position]
            holder.列表标题.text = 项目文本
            holder.列表标题.textSize = 上下文!!.dp转sp(标题字体大小).toFloat()
            holder.列表标题.setSingleLine(标题单行)
            if (标题字体颜色 == -1) {
                holder.列表标题.setTextColor(if (上下文!!.是否是深色模式) Color.WHITE else Color.BLACK)
            } else {
                holder.列表标题.setTextColor(标题字体颜色)
            }

            if (是否图片文字变灰效果) {
                动画类.变灰效果(holder.itemView)
            } else {
                holder.列表框.setBackgroundResource(
                    上下文!!.attr转int(android.R.attr.selectableItemBackground)
                )
            }
            holder.itemView.setBackgroundColor(背景颜色)
            holder.itemView.setOnClickListener(OnClickListener { v: View? ->
                if (事件 != null) {
                    事件!!.列表项目单击事件(holder.getAdapterPosition())
                }
            })
        }

        override fun getItemCount(): Int {
            return 标题.size
        }

        @SuppressLint("NotifyDataSetChanged")
        fun 刷新() {
            notifyDataSetChanged()
        }
    }

    private class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var 列表框: LinearLayout
        var 列表图标: ImageView
        var 列表标题: TextView

        init {
            列表框 = itemView.findViewById<LinearLayout>(R.id.lbk)
            列表图标 = itemView.findViewById<ImageView>(R.id.lbtb)
            列表标题 = itemView.findViewById<TextView>(R.id.lbbt)
        }
    }

    interface 项目单击事件 {
        fun 列表项目单击事件(项目序数: Int)
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var 上下文: Context? = null
        var 图片文字变灰效果: Int = 0
        var 整体变灰效果: Int = 1
    }
}
