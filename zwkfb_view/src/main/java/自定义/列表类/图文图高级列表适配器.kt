package 自定义.列表类

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import zwkfb.view.R
import 自定义.系统类.dp转px


class 图文图高级列表适配器(var 上下文: Activity) : RecyclerView.Adapter<图文图高级列表适配器.ViewHolder?>() {
    private var mListener: OnItemClickListener? = null // 定义接口
    private var mlongListener: OnItemLongClickListener? = null // 定义接口
    private val 图标: ArrayList<Int>
    private val 标题: ArrayList<String?>
    private val 按钮: ArrayList<Int>

    fun 添加项目(图标: Int, 标题: String?, 按钮: Int) {
        this.图标.add(图标)
        this.标题.add(标题)
        this.按钮.add(按钮)
        刷新布局()
    }

    fun 删除项目(项目序数: Int) {
        this.图标.removeAt(项目序数)
        this.标题.removeAt(项目序数)
        this.按钮.removeAt(项目序数)
        刷新布局()
    }

    fun 清空项目() {
        this.图标.clear()
        this.标题.clear()
        this.按钮.clear()
        刷新布局()
    }

    fun 获取项目标题(项目序数: Int): String? {
        return if (项目序数 >= 0 && 项目序数 < 标题.size) {
            标题[项目序数]
        } else {
            标题[if (项目序数 < 0) 0 else 标题.size - 1]
        }
    }

    fun 获取项目总数(): Int {
        return 标题.size
    }

    fun 获取项目标题(): ArrayList<String?> {
        return 标题
    }

    var 标题单行: Boolean = false

    fun 置标题单行(值: Boolean) {
        标题单行 = 值
        刷新布局()
    }

    var 图标宽度: Int = 25
    var 图标高度: Int = 25
    fun 置图标尺寸(宽度: Int, 高度: Int) {
        this.图标宽度 = 宽度
        this.图标高度 = 高度
        刷新布局()
    }


    var 按钮宽度: Int = 25
    var 按钮高度: Int = 25

    init {
        this.图标 = ArrayList<Int>()
        this.标题 = ArrayList<String?>()
        this.按钮 = ArrayList<Int>()
    }

    fun 置按钮尺寸(宽度: Int, 高度: Int) {
        this.按钮宽度 = 宽度
        this.按钮高度 = 高度
        刷新布局()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun 刷新布局() {
        notifyDataSetChanged()
    }


    fun 置单击项目事件(单击项目事件: OnItemClickListener?) {
        this.mListener = 单击项目事件
    }

    fun 置长按项目事件(长按项目事件: OnItemLongClickListener?) {
        this.mlongListener = 长按项目事件
    }

    // 接口定义
    interface OnItemClickListener {
        fun 列表项目单击事件(项目序数: Int)
    }

    interface OnItemLongClickListener {
        fun 列表项目长按事件(项目序数: Int)
    }

    // 创建ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // 加载布局
        val 布局 = LayoutInflater.from(parent.context).inflate(R.layout.item_twtgjlbspq, parent, false)
        return ViewHolder(布局) // 返回ViewHolder对象
    }

    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val 项目图标 = 图标[position]
        holder.列表图标.setImageResource(项目图标)

        val 项目文本 = 标题[position]
        holder.列表标题.text = 项目文本
        holder.列表标题.textSize = 16f
        holder.列表标题.setSingleLine(标题单行)

        val 项目按钮 = 按钮[position]
        holder.列表按钮.setImageResource(项目按钮)
        val layoutParams = LinearLayout.LayoutParams(holder.列表按钮.layoutParams)
        layoutParams.width = 上下文.dp转px(按钮宽度)
        layoutParams.height = 上下文.dp转px(按钮高度)
        layoutParams.setMarginStart(上下文.dp转px(15))
        layoutParams.setMarginEnd(上下文.dp转px(15))
        holder.列表按钮.setLayoutParams(layoutParams)

        holder.itemView.setOnClickListener(View.OnClickListener { v: View? ->
            if (mListener != null) {
                mListener!!.列表项目单击事件(holder.getAdapterPosition())
            }
        })

        holder.itemView.setOnLongClickListener(View.OnLongClickListener { v: View? ->
            if (mlongListener != null) {
                mlongListener!!.列表项目长按事件(holder.getAdapterPosition())
            }
            true
        })
    }

    override fun getItemCount(): Int {
        return 标题.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var 列表图标: ImageView
        var 列表标题: TextView
        var 列表按钮: ImageView

        init {
            列表图标 = itemView.findViewById<ImageView>(R.id.lbtb)
            列表标题 = itemView.findViewById<TextView>(R.id.lbbt)
            列表按钮 = itemView.findViewById<ImageView>(R.id.lbann)
        }
    }
}
