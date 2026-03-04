package 商业.谷歌.安卓.材质.加载指示器;

import com.google.android.material.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import com.google.android.material.progressindicator.AnimatorDurationScaleProvider;
import com.google.errorprone.annotations.CanIgnoreReturnValue;


/** This class draws the graphics for a loading indicator. */
public final class 加载指示器可绘制对象 extends Drawable implements Drawable.Callback {
    @SuppressLint("RestrictedApi")
    AnimatorDurationScaleProvider animatorDurationScaleProvider;

    @NonNull
    private final Context context;
    @NonNull private final 加载指示器规范 specs;
    @NonNull private 加载指示器绘制代理 drawingDelegate;
    @NonNull private 加载指示器动画代理 animatorDelegate;

    @NonNull
    Paint paint;

    @IntRange(from = 0, to = 255)
    int alpha;

    private Drawable staticDummyDrawable;

    @NonNull
    public static 加载指示器可绘制对象 create(
            @NonNull Context context, @NonNull 加载指示器规范 specs) {
        加载指示器可绘制对象 loadingIndicatorDrawable = new 加载指示器可绘制对象(context, specs,
                        new 加载指示器绘制代理(specs),
                        new 加载指示器动画代理(specs));
        loadingIndicatorDrawable.setStaticDummyDrawable(
                VectorDrawableCompat.create(context.getResources(), R.drawable.ic_mtrl_arrow_circle, null));
        return loadingIndicatorDrawable;
    }

    @SuppressLint("RestrictedApi")
    加载指示器可绘制对象(
            @NonNull Context context,
            @NonNull 加载指示器规范 specs,
            @NonNull 加载指示器绘制代理 drawingDelegate,
            @NonNull 加载指示器动画代理 animatorDelegate) {
        this.context = context;
        this.specs = specs;
        this.drawingDelegate = drawingDelegate;
        this.animatorDelegate = animatorDelegate;
        animatorDurationScaleProvider = new AnimatorDurationScaleProvider();

        this.paint = new Paint();

        animatorDelegate.registerDrawable(this);
        setAlpha(255);
    }

    // ******************* Overridden methods *******************

    @Override
    public int getIntrinsicWidth() {
        return drawingDelegate.getPreferredWidth();
    }

    @Override
    public int getIntrinsicHeight() {
        return drawingDelegate.getPreferredHeight();
    }

    @Override
    public void draw(@NonNull Canvas canvas) {
        Rect clipBounds = new Rect();
        Rect bounds = getBounds();

        if (bounds.isEmpty() || !isVisible() || !canvas.getClipBounds(clipBounds)) {
            // Escape if bounds are empty, clip bounds are empty, or currently hidden.
            return;
        }

        if (isSystemAnimatorDisabled() && staticDummyDrawable != null) {
            staticDummyDrawable.setBounds(bounds);
            staticDummyDrawable.setTint(specs.indicatorColors[0]);
            staticDummyDrawable.draw(canvas);
            return;
        }

        canvas.save();
        drawingDelegate.adjustCanvas(canvas, bounds);
        drawingDelegate.drawContainer(canvas, paint, specs.containerColor, getAlpha());
        drawingDelegate.drawIndicator(canvas, paint, animatorDelegate.indicatorState, getAlpha());
        canvas.restore();
    }

    @CanIgnoreReturnValue
    @Override
    public boolean setVisible(boolean visible, boolean restart) {
        return setVisible(visible, restart, /* animate= */ visible);
    }

    /**
     * Changes the visibility with/without triggering the animation callbacks.
     *
     * @param visible Whether to make the drawable visible.
     * @param restart Whether to force starting the animation from the beginning.
     * @param animate Whether to change the visibility with animation.
     * @return {@code true}, if the visibility changes or will change after the animation; {@code
     *     false}, otherwise.
     * @see #setVisible(boolean, boolean, boolean)
     */
    @CanIgnoreReturnValue
    public boolean setVisible(boolean visible, boolean restart, boolean animate) {
        boolean changed = super.setVisible(visible, restart);
        animatorDelegate.cancelAnimatorImmediately();
        // Restarts the main animator if it's visible and needs to be animated.
        if (visible && animate && !isSystemAnimatorDisabled()) {
            animatorDelegate.startAnimator();
        }
        return changed;
    }

    @Override
    public void setAlpha(int alpha) {
        if (this.alpha != alpha) {
            this.alpha = alpha;
            invalidateSelf();
        }
    }

    @Override
    public int getAlpha() {
        return alpha;
    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        paint.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }

    @Override
    public void invalidateDrawable(@NonNull Drawable drawable) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.invalidateDrawable(this);
        }
    }

    @Override
    public void scheduleDrawable(@NonNull Drawable who, @NonNull Runnable what, long when) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.scheduleDrawable(this, what, when);
        }
    }

    @Override
    public void unscheduleDrawable(@NonNull Drawable who, @NonNull Runnable what) {
        Drawable.Callback callback = getCallback();
        if (callback != null) {
            callback.unscheduleDrawable(this, what);
        }
    }

    // ******************* Utility functions *******************

    private boolean isSystemAnimatorDisabled() {
        if (animatorDurationScaleProvider != null) {
            @SuppressLint("RestrictedApi") float systemAnimatorDurationScale =
                    animatorDurationScaleProvider.getSystemAnimatorDurationScale(
                            context.getContentResolver());
            return systemAnimatorDurationScale == 0;
        }
        return false;
    }

    // ******************* Setter and getter *******************

    /**
     * Returns the drawable that will be used when the system animator is disabled.
     *
     * @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    @Nullable
    public Drawable getStaticDummyDrawable() {
        return staticDummyDrawable;
    }

    /**
     * Sets the drawable that will be used when the system animator is disabled.
     *
     * @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    @VisibleForTesting
    public void setStaticDummyDrawable(@Nullable Drawable staticDummyDrawable) {
        this.staticDummyDrawable = staticDummyDrawable;
    }

    @NonNull
    加载指示器动画代理 getAnimatorDelegate() {
        return animatorDelegate;
    }

    void setAnimatorDelegate(@NonNull 加载指示器动画代理 animatorDelegate) {
        this.animatorDelegate = animatorDelegate;
        animatorDelegate.registerDrawable(this);
    }

    @NonNull
    加载指示器绘制代理 getDrawingDelegate() {
        return drawingDelegate;
    }

    void setDrawingDelegate(@NonNull 加载指示器绘制代理 drawingDelegate) {
        this.drawingDelegate = drawingDelegate;
    }
}
