package 自定义.编辑类

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.SearchView

class 应用兼容搜索视图 :SearchView{
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    //======================================================================

    interface 查询文本回调监听事件{
        fun 查询文本提交回调(查询: String): Boolean
        fun 查询文本改变回调(文本: String): Boolean
    }

    //======================================================================
    private var 查询文本回调监听事件1: 查询文本回调监听事件? = null

    //======================================================================
    fun 置查询文本回调监听事件(事件: 查询文本回调监听事件) {
        setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return 查询文本回调监听事件1?.查询文本提交回调(query) ?: false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return 查询文本回调监听事件1?.查询文本改变回调(newText) ?: false
            }
        })
        查询文本回调监听事件1 = 事件
    }


}


