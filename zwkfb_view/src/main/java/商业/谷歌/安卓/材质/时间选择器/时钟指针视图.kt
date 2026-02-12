package 商业.谷歌.安卓.材质.时间选择器

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Pair
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.annotation.Dimension
import androidx.annotation.FloatRange
import androidx.annotation.Px
import com.google.android.material.R
import com.google.android.material.animation.AnimationUtils
import com.google.android.material.internal.ViewUtils
import com.google.android.material.math.MathUtils
import com.google.android.material.motion.MotionUtils
import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

/** A Class to draw the hand on a Clock face.  */
@SuppressLint("RestrictedApi")
internal class 时钟指针视图 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.materialClockStyle
) : View(context, attrs, defStyleAttr) {
    private val animationDuration: Int
    private val animationInterpolator: TimeInterpolator
    private val rotationAnimator = ValueAnimator()
    private var animatingOnTouchUp = false
    private var downX = 0f
    private var downY = 0f
    private var isInTapRegion = false
    private val scaledTouchSlop: Int
    private var isMultiLevel = false

    /** A listener whenever the hand is rotated.  */
    interface OnRotateListener {
        fun onRotate(@FloatRange(from = 0.0, to = 360.0) rotation: Float, animating: Boolean)
    }

    /** A listener called whenever the hand is released, after a touch event stream.  */
    interface OnActionUpListener {
        fun onActionUp(
            @FloatRange(from = 0.0, to = 360.0) rotation: Float,
            moveInEventStream: Boolean
        )
    }

    private val listeners: MutableList<OnRotateListener> = ArrayList<OnRotateListener>()

    /** Returns the current radius of the selector  */
    @JvmField
    val selectorRadius: Int
    private val centerDotRadius: Float
    private val paint = Paint()

    /** Returns the current bounds of the selector, relative to the this view.  */
    // Since the selector moves, overlapping views may need information about
    // its current position
    val currentSelectorBox: RectF = RectF()

    @Px
    private val selectorStrokeWidth: Int

    private var originalDeg = 0f

    private var changedDuringTouch = false
    private var onActionUpListener: OnActionUpListener? = null

    private var degRad = 0.0
    private var circleRadius: Int

    @径向视图组.Level
    private var currentLevel = 径向视图组.LEVEL_1

    init {
        val a =
            context.obtainStyledAttributes(
                attrs,
                R.styleable.ClockHandView,
                defStyleAttr,
                R.style.Widget_MaterialComponents_TimePicker_Clock
            )

        animationDuration =
            MotionUtils.resolveThemeDuration(
                context, R.attr.motionDurationLong2, DEFAULT_ANIMATION_DURATION
            )
        animationInterpolator =
            MotionUtils.resolveThemeInterpolator(
                context,
                R.attr.motionEasingEmphasizedInterpolator,
                AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR
            )
        circleRadius = a.getDimensionPixelSize(R.styleable.ClockHandView_materialCircleRadius, 0)
        selectorRadius = a.getDimensionPixelSize(R.styleable.ClockHandView_selectorSize, 0)
        val res = getResources()
        selectorStrokeWidth = res.getDimensionPixelSize(R.dimen.material_clock_hand_stroke_width)
        centerDotRadius =
            res.getDimensionPixelSize(R.dimen.material_clock_hand_center_dot_radius).toFloat()
        val selectorColor = a.getColor(R.styleable.ClockHandView_clockHandColor, 0)
        paint.setAntiAlias(true)
        paint.setColor(selectorColor)
        setHandRotation(0f)

        scaledTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop()
        setImportantForAccessibility(IMPORTANT_FOR_ACCESSIBILITY_NO)
        a.recycle()

        initRotationAnimator()
    }

    private fun initRotationAnimator() {
        rotationAnimator.addUpdateListener(
            AnimatorUpdateListener { animation: ValueAnimator? ->
                val animatedValue = animation!!.getAnimatedValue() as Float
                setHandRotationInternal(animatedValue, true)
            })

        rotationAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationCancel(animation: Animator) {
                animation.end()
            }
        })
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (!rotationAnimator.isRunning()) {
            // Refresh selector position.
            setHandRotation(originalDeg)
        }
    }

    fun setHandRotation(@FloatRange(from = 0.0, to = 360.0) degrees: Float) {
        setHandRotation(degrees, false)
    }

    fun setHandRotation(@FloatRange(from = 0.0, to = 360.0) degrees: Float, animate: Boolean) {
        rotationAnimator.cancel()

        if (!animate) {
            setHandRotationInternal(degrees, false)
            return
        }

        val animationValues = getValuesForAnimation(degrees)
        rotationAnimator.setFloatValues(animationValues.first!!, animationValues.second!!)
        rotationAnimator.setDuration(animationDuration.toLong())
        rotationAnimator.setInterpolator(animationInterpolator)
        rotationAnimator.start()
    }

    private fun getValuesForAnimation(degrees: Float): Pair<Float?, Float?> {
        var degrees = degrees
        var currentDegrees = this.getHandRotation()
        // 12 O'clock is located at 0 degrees, so if we rotate from
        // 330 (11 O'clock) degrees to 0 degrees it would do almost a full rotation.
        // Same to rotate from 0 to 330. Adjust adding a full rotation for both cases. So it animates
        // between 330 and 360 or 360 and 330 respectively.
        if (abs(currentDegrees - degrees) > 180) {
            if (currentDegrees > 180 && degrees < 180) {
                degrees += 360f
            }

            if (currentDegrees < 180 && degrees > 180) {
                currentDegrees += 360f
            }
        }

        return Pair<Float?, Float?>(currentDegrees, degrees)
    }

    private fun setHandRotationInternal(
        @FloatRange(from = 0.0, to = 360.0) degrees: Float, animate: Boolean
    ) {
        var degrees = degrees
        degrees = degrees % 360
        originalDeg = degrees
        // Subtract 90f so that 0 degrees is at number 12.
        val angDeg = originalDeg - 90f

        degRad = Math.toRadians(angDeg.toDouble())
        val yCenter = getHeight() / 2
        val xCenter = getWidth() / 2
        val leveledCircleRadius = getLeveledCircleRadius(currentLevel)
        val selCenterX = xCenter + leveledCircleRadius * cos(degRad).toFloat()
        val selCenterY = yCenter + leveledCircleRadius * sin(degRad).toFloat()
        currentSelectorBox.set(
            selCenterX - selectorRadius,
            selCenterY - selectorRadius,
            selCenterX + selectorRadius,
            selCenterY + selectorRadius
        )

        for (listener in listeners) {
            listener.onRotate(degrees, animate)
        }

        invalidate()
    }

    fun setAnimateOnTouchUp(animating: Boolean) {
        animatingOnTouchUp = animating
    }

    fun addOnRotateListener(listener: OnRotateListener?) {
        listeners.add(listener!!)
    }

    fun setOnActionUpListener(listener: OnActionUpListener?) {
        this.onActionUpListener = listener
    }

//    @get:FloatRange(from = 0.0, to = 360.0)
//    var handRotation: Float
//        get() = originalDeg
//        set(degrees) {
//            setHandRotation(degrees, false)
//        }

    @FloatRange(from = 0.0, to = 360.0)
    fun getHandRotation(): Float {
        return originalDeg
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawSelector(canvas)
    }

    private fun drawSelector(canvas: Canvas) {
        val yCenter = getHeight() / 2
        val xCenter = getWidth() / 2

        // Calculate the current radius at which to place the selection circle.
        val leveledCircleRadius = getLeveledCircleRadius(currentLevel)
        val selCenterX = xCenter + leveledCircleRadius * cos(degRad).toFloat()
        val selCenterY = yCenter + leveledCircleRadius * sin(degRad).toFloat()

        // Draw the selection circle.
        paint.setStrokeWidth(0f)
        canvas.drawCircle(selCenterX, selCenterY, selectorRadius.toFloat(), paint)

        // Shorten the line to only go from the edge of the center dot to the
        // edge of the selection circle.
        val sin = sin(degRad)
        val cos = cos(degRad)
        val lineLength = (leveledCircleRadius - selectorRadius).toFloat()
        val linePointX = (xCenter + (lineLength * cos).toInt()).toFloat()
        val linePointY = (yCenter + (lineLength * sin).toInt()).toFloat()

        // Draw the line.
        paint.setStrokeWidth(selectorStrokeWidth.toFloat())
        canvas.drawLine(xCenter.toFloat(), yCenter.toFloat(), linePointX, linePointY, paint)
        canvas.drawCircle(xCenter.toFloat(), yCenter.toFloat(), centerDotRadius, paint)
    }

    /**
     * Set the size of the of the circle. This is the radius from the center of this view to the outer
     * edge of the selector.
     */
    fun setCircleRadius(@Dimension circleRadius: Int) {
        this.circleRadius = circleRadius
        invalidate()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.getActionMasked()
        var forceSelection = false
        var actionDown = false
        var actionUp = false
        val x = event.getX()
        val y = event.getY()
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                downX = x
                downY = y
                isInTapRegion = true
                // This is a new event stream.
                changedDuringTouch = false
                actionDown = true
            }

            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                val deltaX = (x - downX).toInt()
                val deltaY = (y - downY).toInt()
                val distance = (deltaX * deltaX) + (deltaY * deltaY)
                isInTapRegion = distance > scaledTouchSlop
                // If we saw a down/up pair without the value changing, assume
                // this is a single-tap selection and force a change.
                if (changedDuringTouch) {
                    forceSelection = true
                }
                actionUp = action == MotionEvent.ACTION_UP
                if (isMultiLevel) {
                    adjustLevel(x, y)
                }
            }

            else -> {}
        }

        changedDuringTouch =
            changedDuringTouch or handleTouchInput(x, y, forceSelection, actionDown, actionUp)
        if (changedDuringTouch && actionUp && onActionUpListener != null) {
            onActionUpListener!!.onActionUp(
                getDegreesFromXY(
                    x,
                    y
                ).toFloat(),  /* moveInEventStream= */isInTapRegion
            )
        }

        return true
    }

    private fun adjustLevel(x: Float, y: Float) {
        val xCenter = getWidth() / 2
        val yCenter = getHeight() / 2
        val selectionRadius = MathUtils.dist(xCenter.toFloat(), yCenter.toFloat(), x, y)
        val level2CircleRadius = getLeveledCircleRadius(径向视图组.Companion.LEVEL_2)
        val buffer = ViewUtils.dpToPx(getContext(), 12)
        currentLevel =
            if (selectionRadius <= level2CircleRadius + buffer) 径向视图组.Companion.LEVEL_2 else 径向视图组.Companion.LEVEL_1
    }

    private fun handleTouchInput(
        x: Float, y: Float, forceSelection: Boolean, touchDown: Boolean, actionUp: Boolean
    ): Boolean {
        val degrees = getDegreesFromXY(x, y)
        val valueChanged = this.getHandRotation() != degrees.toFloat()
        if (touchDown && valueChanged) {
            return true
        }

        if (valueChanged || forceSelection) {
            setHandRotation(degrees.toFloat(), actionUp && animatingOnTouchUp)
            return true
        }

        return false
    }

    private fun getDegreesFromXY(x: Float, y: Float): Int {
        val xCenter = getWidth() / 2
        val yCenter = getHeight() / 2
        val dX = (x - xCenter).toDouble()
        val dY = (y - yCenter).toDouble()
        var degrees = Math.toDegrees(atan2(dY, dX)).toInt() + 90
        if (degrees < 0) {
            degrees += 360
        }
        return degrees
    }

    @径向视图组.Level
    fun getCurrentLevel(): Int {
        return currentLevel
    }

    fun setCurrentLevel(@径向视图组.Level level: Int) {
        currentLevel = level
        invalidate()
    }

    fun setMultiLevel(isMultiLevel: Boolean) {
        if (this.isMultiLevel && !isMultiLevel) {
            currentLevel = 径向视图组.Companion.LEVEL_1 // reset
        }
        this.isMultiLevel = isMultiLevel
        invalidate()
    }

    @Dimension
    private fun getLeveledCircleRadius(@径向视图组.Level level: Int): Int {
        return if (level == 径向视图组.Companion.LEVEL_2) Math.round(circleRadius * 径向视图组.Companion.LEVEL_RADIUS_RATIO) else circleRadius
    }

    companion object {
        private const val DEFAULT_ANIMATION_DURATION = 200
    }
}
