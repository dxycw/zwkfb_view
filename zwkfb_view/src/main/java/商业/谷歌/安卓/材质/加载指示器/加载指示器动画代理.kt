package 商业.谷歌.安卓.材质.加载指示器

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.util.Property
import androidx.annotation.VisibleForTesting
import androidx.core.math.MathUtils
import androidx.dynamicanimation.animation.FloatPropertyCompat
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.google.android.material.animation.ArgbEvaluatorCompat

internal class 加载指示器动画代理(var specs: 加载指示器规范) {
    // For animator control.
    private var morphFactorTarget = 0

    // ******************* Getters and setters *******************
    @set:VisibleForTesting
    private var animationFraction = 0f
        set(fraction) {
            field = fraction
            val playtime =
                (field * DURATION_PER_SHAPE_IN_MS).toInt()
            updateIndicatorRotation(playtime)
            if (drawable != null) {
                drawable!!.invalidateSelf()
            }
        }
    private var morphFactor = 0f

    private var animator: ObjectAnimator? = null
    private var springAnimation: SpringAnimation? = null

    var drawable: 加载指示器可绘制对象? = null
    var indicatorState: 加载指示器绘制代理.IndicatorState

    /** Registers the drawable associated to this delegate.  */
    fun registerDrawable(drawable: 加载指示器可绘制对象) {
        this.drawable = drawable
    }

    fun startAnimator() {
        maybeInitializeAnimators()

        resetPropertiesForNewStart()
        springAnimation!!.animateToFinalPosition(morphFactorTarget.toFloat())
        animator!!.start()
    }

    fun cancelAnimatorImmediately() {
        if (animator != null) {
            animator!!.cancel()
        }
        if (springAnimation != null) {
            springAnimation!!.skipToEnd()
        }
    }

    fun invalidateSpecValues() {
        resetPropertiesForNewStart()
    }

    fun resetPropertiesForNewStart() {
        morphFactorTarget = 1
        setMorphFactor(0f)
        indicatorState.color = specs.indicatorColors[0]
    }

    private fun maybeInitializeAnimators() {
        if (springAnimation == null) {
            springAnimation =
                SpringAnimation(this, MORPH_FACTOR)
                    .setSpring(
                        SpringForce()
                            .setStiffness(SPRING_STIFFNESS)
                            .setDampingRatio(SPRING_DAMPING_RATIO)
                    )
                    .setMinimumVisibleChange(0.01f)
        }
        if (animator == null) {
            // Instantiates an animator with the linear interpolator to control the animation progress.
            animator = ObjectAnimator.ofFloat<加载指示器动画代理?>(this, ANIMATION_FRACTION, 0f, 1f)
            animator!!.setDuration(DURATION_PER_SHAPE_IN_MS.toLong())
            animator!!.setInterpolator(null)
            animator!!.setRepeatCount(ValueAnimator.INFINITE)
            animator!!.addListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationRepeat(animation: Animator) {
                        super.onAnimationRepeat(animation)
                        springAnimation!!.animateToFinalPosition((++morphFactorTarget).toFloat())
                    }
                })
        }
    }

    // ******************* Helper methods *******************
    /** Updates the indicator's rotation based on current playtime.  */
    private fun updateIndicatorRotation(playtime: Int) {
        val morphFactorBase = (morphFactorTarget - 1).toFloat()
        val morphFactorPerShape = morphFactor - morphFactorBase
        var timeFactorPerShape: Float = playtime.toFloat() / DURATION_PER_SHAPE_IN_MS
        if (timeFactorPerShape == 1f) {
            // The animation on repeat is called before the playtime restart. So if playtime reaches the
            // end, we take it as restarted as 0.
            timeFactorPerShape = 0f
        }
        // Initial rotation.
        indicatorState.rotationDegree =
            (CONSTANT_ROTATION_PER_SHAPE_DEGREES + EXTRA_ROTATION_PER_SHAPE_DEGREES) * morphFactorBase
        // Constant rotation.
        indicatorState.rotationDegree += CONSTANT_ROTATION_PER_SHAPE_DEGREES * timeFactorPerShape
        // Rotation driven by spring animation.
        indicatorState.rotationDegree += EXTRA_ROTATION_PER_SHAPE_DEGREES * morphFactorPerShape

        indicatorState.rotationDegree %= 360f
    }

    /** Updates the indicator's shape and color driven by spring animation.  */
    private fun updateIndicatorShapeAndColor() {
        indicatorState.morphFraction = morphFactor
        // Updates color.
        val startColorIndex = (morphFactorTarget - 1) % specs.indicatorColors.size
        val endColorIndex = (startColorIndex + 1) % specs.indicatorColors.size
        val startColor = specs.indicatorColors[startColorIndex]
        val endColor = specs.indicatorColors[endColorIndex]
        indicatorState.color =
            ArgbEvaluatorCompat.getInstance()
                .evaluate(
                    MathUtils.clamp(morphFactor - (morphFactorTarget - 1), 0f, 1f),
                    startColor,
                    endColor
                )
    }

    private fun getMorphFactor(): Float {
        return morphFactor
    }

    @VisibleForTesting
    fun setMorphFactor(factor: Float) {
        morphFactor = factor
        updateIndicatorShapeAndColor()
        if (drawable != null) {
            drawable!!.invalidateSelf()
        }
    }

    @VisibleForTesting
    fun setMorphFactorTarget(factorTarget: Int) {
        morphFactorTarget = factorTarget
    }

    init {
        indicatorState = 加载指示器绘制代理.IndicatorState()
    }

    companion object {
        // Constants for animation timing.
        private const val DURATION_PER_SHAPE_IN_MS = 650

        // Constants for animation values.
        private const val CONSTANT_ROTATION_PER_SHAPE_DEGREES = 50
        private const val EXTRA_ROTATION_PER_SHAPE_DEGREES = 90
        private const val SPRING_STIFFNESS = 200f
        private const val SPRING_DAMPING_RATIO = 0.6f

        // ******************* Properties *******************
        private val ANIMATION_FRACTION: Property<加载指示器动画代理?, Float?> =
            object : Property<加载指示器动画代理?, Float?>(Float::class.java, "animationFraction") {
                override fun get(delegate: 加载指示器动画代理?): Float {
                    return delegate!!.animationFraction
                }

                override fun set(delegate: 加载指示器动画代理?, value: Float?) {
                    delegate?.animationFraction = value!!
                }
            }

        private val MORPH_FACTOR: FloatPropertyCompat<加载指示器动画代理?> =
            object : FloatPropertyCompat<加载指示器动画代理?>("morphFactor") {
                override fun getValue(delegate: 加载指示器动画代理?): Float {
                    return delegate!!.getMorphFactor()
                }

                override fun setValue(delegate: 加载指示器动画代理?, value: Float) {
                    delegate!!.setMorphFactor(value)
                }
            }
    }
}
