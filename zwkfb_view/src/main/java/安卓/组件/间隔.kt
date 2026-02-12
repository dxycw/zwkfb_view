package 安卓.组件

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import androidx.core.view.isVisible
import kotlin.math.min

/**
 * 创建时间：2025年11月19日.
 *
 * 描述：间隔组件
 *
 * 版本：0.0.8
 * @author dxyc
 */
open class 间隔 : View  {
    constructor(context: Context?) : super(context,null)
    constructor(context: Context?, attrs: AttributeSet?) :
            super(context, attrs,0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr,0)

    constructor(
        context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int,
    ) :super(context, attrs, defStyleAttr, defStyleRes){
        if (isVisible) {
            setVisibility(INVISIBLE)
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
    }

    /**
     * Compare to: [View.getDefaultSize]
     * If mode is AT_MOST, return the child size instead of the parent size
     * (unless it is too big).
     */
    private fun getDefaultSize2(size: Int, measureSpec: Int): Int {
        var result = size
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        when (specMode) {
            MeasureSpec.UNSPECIFIED -> result = size
            MeasureSpec.AT_MOST -> result = min(size, specSize)
            MeasureSpec.EXACTLY -> result = specSize
        }
        return result
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(
            getDefaultSize2(getSuggestedMinimumWidth(), widthMeasureSpec),
            getDefaultSize2(getSuggestedMinimumHeight(), heightMeasureSpec)
        )
    }

}