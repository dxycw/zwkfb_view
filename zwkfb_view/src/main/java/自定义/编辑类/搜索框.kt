package 自定义.编辑类

import android.annotation.SuppressLint
import android.app.SearchableInfo
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.appcompat.R
import 安卓x.应用兼容包.组件.搜索视图
import kotlin.let

class 搜索框 : 搜索视图 {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr)

    private var 搜索提示图标: Drawable? = null
    private var 文本视图: TextView? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onLayout(改变: Boolean, 左: Int, 上: Int, 右: Int, 下: Int) {
        super.onLayout(改变, 左, 上, 右, 下)
        try {
            if (文本视图 == null) {
                文本视图 = findViewById(R.id.search_src_text)
                搜索提示图标 = this.context.getDrawable(zwkfb.view.R.drawable.ic_search)
            }
            // 改变字体
            文本视图!!.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14f)
            文本视图!!.gravity = Gravity.CENTER_VERTICAL
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.VANILLA_ICE_CREAM) {
                文本视图!!.isLocalePreferredLineHeightForMinimumUsed = false
            }
            更新查询提示()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getDecoratedHint(提示文本: CharSequence): CharSequence {
        if (搜索提示图标 == null) { return 提示文本 }
        val textSize = 文本视图!!.textSize.toInt()
        搜索提示图标!!.setBounds(0, 0, textSize, textSize)
        val ssb = SpannableStringBuilder("   ")
        ssb.setSpan(CenteredImageSpan(搜索提示图标), 1, 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        ssb.append(提示文本)
        return ssb
    }

    private fun 更新查询提示() {
        文本视图?.let { it.hint = getDecoratedHint(queryHint ?: "") }
    }

    override fun setIconifiedByDefault(iconified: Boolean) {
        super.setIconifiedByDefault(iconified)
        更新查询提示()
    }

    override fun setSearchableInfo(searchable: SearchableInfo?) {
        super.setSearchableInfo(searchable)
        searchable?.let { 更新查询提示() }
    }

    override fun setQueryHint(hint: CharSequence?) {
        super.setQueryHint(hint)
        更新查询提示()
    }

    internal class CenteredImageSpan(可绘制: Drawable?) : ImageSpan(可绘制!!) {
        override fun draw(
            canvas: Canvas, text: CharSequence, start: Int, end: Int, x: Float,
            top: Int, y: Int, bottom: Int, paint: Paint
        ) {
            // image to draw
            val b = drawable
            // font metrics of text to be replaced
            val fm = paint.fontMetricsInt
            val transY = ((y + fm.descent + y + fm.ascent) / 2
                    - b.bounds.bottom / 2)
            canvas.save()
            canvas.translate(x, transY.toFloat())
            b.draw(canvas)
            canvas.restore()
        }
    }
}