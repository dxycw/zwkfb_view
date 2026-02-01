package 商业.谷歌.安卓.材质.加载指示器

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import android.util.AttributeSet
import android.util.TypedValue
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.Px
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import com.google.android.material.R
import com.google.android.material.color.MaterialColors

/**
 * This class contains the parameters for drawing a loading indicator. The parameters reflect the
 * attributes defined in `R.styleable.LoadingIndicator`.
 */
class 加载指示器规范 {
    @JvmField
    var scaleToFit: Boolean = false

    @JvmField
    @Px
    var indicatorSize: Int = 0

    @JvmField
    @Px
    var containerWidth: Int = 0

    @JvmField
    @Px
    var containerHeight: Int = 0
    @JvmField
    var indicatorColors: IntArray = IntArray(0)

    @JvmField
    @ColorInt
    var containerColor: Int = 0

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, R.attr.loadingIndicatorStyle)

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int
    ) : this(context, attrs, defStyleAttr, 加载指示器.DEF_STYLE_RES)


    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet?,
        @AttrRes defStyleAttr: Int,
        @StyleRes defStyleRes: Int
    ) {

        // Loads default resources.
        @Px val defaultShapeSize =
            context.getResources().getDimensionPixelSize(R.dimen.m3_loading_indicator_shape_size)
        @Px val defaultContainerSize =
            context.getResources().getDimensionPixelSize(R.dimen.m3_loading_indicator_container_size)

        // Loads attributes.
        context.withStyledAttributes(attrs, R.styleable.LoadingIndicator, defStyleAttr, defStyleRes){
            indicatorSize =
                getDimensionPixelSize(R.styleable.LoadingIndicator_indicatorSize, defaultShapeSize)
            containerWidth =
                getDimensionPixelSize(
                    R.styleable.LoadingIndicator_containerWidth,
                    defaultContainerSize
                )
            containerHeight =
                getDimensionPixelSize(
                    R.styleable.LoadingIndicator_containerHeight,
                    defaultContainerSize
                )
            loadIndicatorColors(context, this)
            containerColor = getColor(R.styleable.LoadingIndicator_containerColor, Color.TRANSPARENT)
        }

    }

    private fun loadIndicatorColors(context: Context, typedArray: TypedArray) {
        if (!typedArray.hasValue(R.styleable.LoadingIndicator_indicatorColor)) {
            // Uses theme primary color for indicator if not provided in the attribute set.
            indicatorColors =
                intArrayOf(
                    MaterialColors.getColor(context, androidx.appcompat.R.attr.colorPrimary, -1)
                )
            return
        }

        val indicatorColorValue =
            typedArray.peekValue(R.styleable.LoadingIndicator_indicatorColor)

        if (indicatorColorValue.type != TypedValue.TYPE_REFERENCE) {
            indicatorColors =
                intArrayOf(typedArray.getColor(R.styleable.LoadingIndicator_indicatorColor, -1))
            return
        }

        indicatorColors =
            context
                .getResources()
                .getIntArray(
                    typedArray.getResourceId(
                        R.styleable.LoadingIndicator_indicatorColor,
                        -1
                    )
                )
        require(indicatorColors.size != 0) { "indicatorColors cannot be empty when indicatorColor is not used." }
    }

    /**
     * Sets the scale specs to fit the given bound of the [加载指示器可绘制对象].
     *
     * @param scaleToFit The new scaleToFit value.
     */
    fun setScaleToFit(scaleToFit: Boolean) {
        this.scaleToFit = scaleToFit
    }
}
