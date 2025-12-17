package 自定义.列表类

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import zwkfb.view.R
import 安卓x.回收视图.组件.回收视图


class 文本高级列表框 : 回收视图 {
    var 上下文: Context? = null
    var 布局参数: LinearLayoutManager? = null
    var 分割线: DividerItemDecoration? = null
    private var 适配器: 适配器? = null

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
        this.上下文 = 上下文
        this.适配器 = 适配器()
        布局参数 = LinearLayoutManager(上下文, LinearLayoutManager.VERTICAL, false)
        this.setLayoutManager(布局参数) //列表布局,垂直,必须写
        分割线 = DividerItemDecoration(上下文, 布局参数!!.orientation)
    }


    fun 添加项目(标题: String?) {
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

    fun 置分割线可视(值: Boolean) {
        if (值) {
            if (this.itemDecorationCount == 0) {
                this.addItemDecoration(分割线!!)
            }
        } else {
            this.removeItemDecoration(分割线!!)
        }
    }

    /**
     * 以下为事件方法
     */
    fun 置单击项目事件(单击项目事件: 项目单击事件?) {
        适配器?.事件 = 单击项目事件
    }


    //RecyclerView.Adapter<ViewHolder>
    private class 适配器 : Adapter<ViewHolder?>() {
        var 事件: 项目单击事件? = null // 定义接口
        var 标题: ArrayList<String?>

        var 标题单行 = false
        var 背景颜色 = Color.TRANSPARENT
        var 标题字体大小 = 16
        var 标题字体颜色 = Color.BLACK

        init {
            标题 = ArrayList<String?>()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val 视图 = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_wbgjlbk, parent, false)
            return ViewHolder(视图)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val 项目文本 = 标题[position]
            holder.列表标题.text = 项目文本
            holder.列表标题.textSize = 标题字体大小.toFloat()
            holder.列表标题.setSingleLine(标题单行)
            holder.列表标题.setTextColor(标题字体颜色)
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

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var 列表标题: TextView

        init {
            列表标题 = itemView.findViewById<TextView>(R.id.lbbt)
        }
    }

    interface 项目单击事件 {
        fun 列表项目单击事件(项目序数: Int)
    }
}
