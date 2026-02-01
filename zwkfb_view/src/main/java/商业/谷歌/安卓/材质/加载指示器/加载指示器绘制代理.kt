package 商业.谷歌.安卓.材质.加载指示器

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import androidx.annotation.ColorInt
import androidx.annotation.IntRange
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import com.google.android.material.color.MaterialColors
import com.google.android.material.math.MathUtils
import com.google.android.material.shape.MaterialShapes
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min

internal class 加载指示器绘制代理(var specs: 加载指示器规范) {
    val indicatorPath: Path = Path()
    val indicatorPathTransform: Matrix = Matrix()

    val preferredWidth: Int
        /**
         * Returns the preferred width, in pixels, of the drawable based on the drawing type. Returns a
         * negative value if it depends on the [android.view.View].
         */
        get() = max(specs.containerHeight, specs.indicatorSize)

    val preferredHeight: Int
        /**
         * Returns the preferred height, in pixels, of the drawable based on the drawing type. Returns a
         * negative value if it depends on the [android.view.View].
         */
        get() = max(specs.containerWidth, specs.indicatorSize)

    fun adjustCanvas(canvas: Canvas, bounds: Rect) {
        // Moves the origin (0, 0) of the canvas to the center of the container.
        canvas.translate(bounds.centerX().toFloat(), bounds.centerY().toFloat())
        if (specs.scaleToFit) {
            // Scales the actual drawing by the ratio of the given bounds to the preferred size, while
            // keeping the aspect ratio.
            val scaleX = bounds.width().toFloat() / this.preferredWidth
            val scaleY = bounds.height().toFloat() / this.preferredHeight
            val scale = min(scaleX, scaleY)
            canvas.scale(scale, scale)
        }

        // Clip all drawing to the designated area, so it doesn't draw outside of its bounds (which can
        // happen in certain configuration of clipToPadding and clipChildren)
        canvas.clipRect(
            -this.preferredWidth / 2f,
            -this.preferredHeight / 2f,
            this.preferredWidth / 2f,
            this.preferredHeight / 2f
        )

        // Rotates canvas 90 degrees so that 0 degree at the top.
        canvas.rotate(-90f)
    }

    fun drawContainer(
        canvas: Canvas,
        paint: Paint,
        @ColorInt color: Int,
        @IntRange(from = 0, to = 255) drawableAlpha: Int
    ) {
        var color = color
        val radius = min(specs.containerWidth, specs.containerHeight) / 2f
        color = MaterialColors.compositeARGBWithAlpha(color, drawableAlpha)
        paint.setColor(color)
        paint.setStyle(Paint.Style.FILL)
        canvas.drawRoundRect(
            RectF(
                -specs.containerWidth / 2f,
                -specs.containerHeight / 2f,
                specs.containerWidth / 2f,
                specs.containerHeight / 2f
            ),
            radius,
            radius,
            paint
        )
    }

    fun drawIndicator(
        canvas: Canvas,
        paint: Paint,
        indicatorState: IndicatorState,
        @IntRange(from = 0, to = 255) drawableAlpha: Int
    ) {
        val color = MaterialColors.compositeARGBWithAlpha(indicatorState.color, drawableAlpha)
        paint.setColor(color)
        paint.setStyle(Paint.Style.FILL)
        canvas.save()
        canvas.rotate(indicatorState.rotationDegree)
        // Draws the shape morph.
        indicatorPath.rewind()
        val shapeMorphFraction = floor(indicatorState.morphFraction.toDouble()).toInt()
        val fractionAmongAllShapes =
            MathUtils.floorMod(shapeMorphFraction, INDETERMINATE_MORPH_SEQUENCE.size)
        val fractionPerShape = indicatorState.morphFraction - shapeMorphFraction
        INDETERMINATE_MORPH_SEQUENCE[fractionAmongAllShapes]!!.toPath(
            fractionPerShape,
            indicatorPath
        )
        // We need to apply the scaling to the path directly, instead of on the canvas, to avoid the
        // limitation of hardware accelerated rendering.
        indicatorPathTransform.setScale(specs.indicatorSize / 2f, specs.indicatorSize / 2f)
        indicatorPath.transform(indicatorPathTransform)
        canvas.drawPath(indicatorPath, paint)
        canvas.restore()
    }

    class IndicatorState {
        // The color of the indicator without applying the drawable's alpha.
        @ColorInt
        var color: Int = 0

        // The fraction controlling the shape morph.
        var morphFraction: Float = 0f

        // Initial rotation applied on the indicator in degrees.
        var rotationDegree: Float = 0f
    }

    companion object {
        @SuppressLint("RestrictedApi")
        private val INDETERMINATE_SHAPES: Array<RoundedPolygon?> = arrayOf<RoundedPolygon?>(
            MaterialShapes.normalize(MaterialShapes.SOFT_BURST, true, RectF(-1f, -1f, 1f, 1f)),
            MaterialShapes.normalize(MaterialShapes.COOKIE_9, true, RectF(-1f, -1f, 1f, 1f)),
            MaterialShapes.normalize(MaterialShapes.PENTAGON, true, RectF(-1f, -1f, 1f, 1f)),
            MaterialShapes.normalize(MaterialShapes.PILL, true, RectF(-1f, -1f, 1f, 1f)),
            MaterialShapes.normalize(MaterialShapes.SUNNY, true, RectF(-1f, -1f, 1f, 1f)),
            MaterialShapes.normalize(MaterialShapes.COOKIE_4, true, RectF(-1f, -1f, 1f, 1f)),
            MaterialShapes.normalize(MaterialShapes.OVAL, true, RectF(-1f, -1f, 1f, 1f))
        )

        private val INDETERMINATE_MORPH_SEQUENCE = arrayOfNulls<Morph>(INDETERMINATE_SHAPES.size)

        init {
            for (i in INDETERMINATE_SHAPES.indices) {
                INDETERMINATE_MORPH_SEQUENCE[i] =
                    Morph(
                        INDETERMINATE_SHAPES[i]!!,
                        INDETERMINATE_SHAPES[(i + 1) % INDETERMINATE_SHAPES.size]!!
                    )
            }
        }
    }
}
