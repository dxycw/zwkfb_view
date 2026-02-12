package 商业.谷歌.安卓.材质.加载指示器

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.annotation.IntRange
import androidx.annotation.RestrictTo
import androidx.annotation.VisibleForTesting
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat
import com.google.android.material.R
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider
import com.google.firebase.crashlytics.buildtools.reloc.com.google.errorprone.annotations.CanIgnoreReturnValue


/** This class draws the graphics for a loading indicator.  */
@SuppressLint("RestrictedApi")
class 加载指示器可绘制对象 internal constructor(
    private val context: Context,
    private val specs: 加载指示器规范,
    internal var drawingDelegate: 加载指示器绘制代理,
    private var animatorDelegate: 加载指示器动画代理
) : Drawable(), Drawable.Callback {

    var animatorDurationScaleProvider: AnimatorDurationScaleProvider? = null

    private var paint: Paint

//    @IntRange(from = 0, to = 255)
    private var alpha: Int = 0

    /**
     * Returns the drawable that will be used when the system animator is disabled.
     *
     * @hide
     */
    /**
     * Sets the drawable that will be used when the system animator is disabled.
     *
     * @hide
     */
    @get:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    @set:VisibleForTesting
    @set:RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    var staticDummyDrawable: Drawable? = null

    init {
        animatorDurationScaleProvider = AnimatorDurationScaleProvider()

        this.paint = Paint()

        animatorDelegate.registerDrawable(this)
        setAlpha(255)
    }

    // ******************* Overridden methods *******************
    override fun getIntrinsicWidth(): Int {
        return drawingDelegate.preferredWidth
    }

    override fun getIntrinsicHeight(): Int {
        return drawingDelegate.preferredHeight
    }

    override fun draw(canvas: Canvas) {
        val clipBounds = Rect()
        val bounds = getBounds()

        if (bounds.isEmpty() || !isVisible() || !canvas.getClipBounds(clipBounds)) {
            // Escape if bounds are empty, clip bounds are empty, or currently hidden.
            return
        }

        if (this.isSystemAnimatorDisabled && staticDummyDrawable != null) {
            staticDummyDrawable!!.setBounds(bounds)
            staticDummyDrawable!!.setTint(specs.indicatorColors[0])
            staticDummyDrawable!!.draw(canvas)
            return
        }

        canvas.save()
        drawingDelegate.adjustCanvas(canvas, bounds)
        drawingDelegate.drawContainer(canvas, paint, specs.containerColor, getAlpha())
        drawingDelegate.drawIndicator(canvas, paint, animatorDelegate.indicatorState, getAlpha())
        canvas.restore()
    }

    @CanIgnoreReturnValue
    override fun setVisible(visible: Boolean, restart: Boolean): Boolean {
        return setVisible(visible, restart,  /* animate= */visible)
    }

    /**
     * Changes the visibility with/without triggering the animation callbacks.
     *
     * @param visible Whether to make the drawable visible.
     * @param restart Whether to force starting the animation from the beginning.
     * @param animate Whether to change the visibility with animation.
     * @return `true`, if the visibility changes or will change after the animation; `false`, otherwise.
     * @see .setVisible
     */
    @CanIgnoreReturnValue
    fun setVisible(visible: Boolean, restart: Boolean, animate: Boolean): Boolean {
        val changed = super.setVisible(visible, restart)
        animatorDelegate.cancelAnimatorImmediately()
        // Restarts the main animator if it's visible and needs to be animated.
        if (visible && animate && !this.isSystemAnimatorDisabled) {
            animatorDelegate.startAnimator()
        }
        return changed
    }

    override fun setAlpha(alpha: Int) {
        if (this.alpha != alpha) {
            this.alpha = alpha
            invalidateSelf()
        }
    }

    override fun getAlpha(): Int {
        return alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.setColorFilter(colorFilter)
        invalidateSelf()
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun invalidateDrawable(drawable: Drawable) {
        val callback = getCallback()
        if (callback != null) {
            callback.invalidateDrawable(this)
        }
    }

    override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {
        val callback = getCallback()
        if (callback != null) {
            callback.scheduleDrawable(this, what, `when`)
        }
    }

    override fun unscheduleDrawable(who: Drawable, what: Runnable) {
        val callback = getCallback()
        if (callback != null) {
            callback.unscheduleDrawable(this, what)
        }
    }

    private val isSystemAnimatorDisabled: Boolean
        // ******************* Utility functions *******************
        get() {
            if (animatorDurationScaleProvider != null) {
                @SuppressLint("RestrictedApi") val systemAnimatorDurationScale =
                    animatorDurationScaleProvider!!.getSystemAnimatorDurationScale(
                        context.getContentResolver()
                    )
                return systemAnimatorDurationScale == 0f
            }
            return false
        }

    // ******************* Setter and getter *******************

    internal fun getAnimatorDelegate(): 加载指示器动画代理 {
        return animatorDelegate
    }

    internal fun setAnimatorDelegate(animatorDelegate: 加载指示器动画代理) {
        this.animatorDelegate = animatorDelegate
        animatorDelegate.registerDrawable(this)
    }

    companion object {
        fun create(context: Context, specs: 加载指示器规范): 加载指示器可绘制对象 {
            val loadingIndicatorDrawable =
                加载指示器可绘制对象(
                    context, specs,
                    加载指示器绘制代理(specs),
                    加载指示器动画代理(specs)
                )
            loadingIndicatorDrawable.staticDummyDrawable = VectorDrawableCompat.create(
                context.getResources(),
                R.drawable.ic_mtrl_arrow_circle,
                null
            )
            return loadingIndicatorDrawable
        }
    }
}
