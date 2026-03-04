package 商业.谷歌.安卓.材质.提示栏;

import com.google.android.material.R;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.snackbar.ContentViewCallback;


/** @hide */
@RestrictTo(LIBRARY_GROUP)
public class 提示栏内容布局 extends LinearLayout implements ContentViewCallback {
    private TextView messageView;
    private Button actionView;
    @Nullable
    private Button closeView;
    private final TimeInterpolator contentInterpolator;

    private int maxInlineActionWidth;

    public 提示栏内容布局(@NonNull Context context) {
        this(context, null);
    }

    @SuppressLint("RestrictedApi")
    public 提示栏内容布局(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        contentInterpolator = MotionUtils.resolveThemeInterpolator(context,
                R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        messageView = findViewById(R.id.snackbar_text);
        actionView = findViewById(R.id.snackbar_action);
        closeView = findViewById(R.id.mtrl_snackbar_close);
    }

    public TextView getMessageView() {
        return messageView;
    }

    public Button getActionView() {
        return actionView;
    }

    @Nullable
    public Button getCloseView() {
        return closeView;
    }

    void updateActionTextColorAlphaIfNeeded(float actionTextColorAlpha) {
        if (actionTextColorAlpha != 1) {
            int originalActionTextColor = actionView.getCurrentTextColor();
            int colorSurface = MaterialColors.getColor(this, R.attr.colorSurface);
            int actionTextColor =
                    MaterialColors.layer(colorSurface, originalActionTextColor, actionTextColorAlpha);
            actionView.setTextColor(actionTextColor);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (getOrientation() == VERTICAL) {
            // The layout is by default HORIZONTAL. We only change it to VERTICAL when the action view
            // is too wide and ellipsizes the message text. When the condition is met, we should keep the
            // layout as VERTICAL.
            return;
        }

        final int multiLineVPadding =
                getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical_2lines);
        final int singleLineVPadding =
                getResources().getDimensionPixelSize(R.dimen.design_snackbar_padding_vertical);
        final Layout messageLayout = messageView.getLayout();
        final boolean isMultiLine = messageLayout != null && messageLayout.getLineCount() > 1;

        boolean remeasure = false;
        if (isMultiLine
                && maxInlineActionWidth > 0
                && actionView.getMeasuredWidth() > maxInlineActionWidth) {
            if (updateViewsWithinLayout(
                    VERTICAL, multiLineVPadding, multiLineVPadding - singleLineVPadding)) {
                remeasure = true;
            }
        } else {
            final int messagePadding = isMultiLine ? multiLineVPadding : singleLineVPadding;
            if (updateViewsWithinLayout(HORIZONTAL, messagePadding, messagePadding)) {
                remeasure = true;
            }
        }

        if (remeasure) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    private boolean updateViewsWithinLayout(
            final int orientation, final int messagePadTop, final int messagePadBottom) {
        boolean changed = false;
        if (orientation != getOrientation()) {
            setOrientation(orientation);
            changed = true;
        }
        if (messageView.getPaddingTop() != messagePadTop
                || messageView.getPaddingBottom() != messagePadBottom) {
            updateTopBottomPadding(messageView, messagePadTop, messagePadBottom);
            changed = true;
        }
        return changed;
    }

    private static void updateTopBottomPadding(
            @NonNull View view, int topPadding, int bottomPadding) {
        if (view.isPaddingRelative()) {
            view.setPaddingRelative(
                    view.getPaddingStart(),
                    topPadding,
                    view.getPaddingEnd(),
                    bottomPadding);
        } else {
            view.setPadding(view.getPaddingLeft(), topPadding, view.getPaddingRight(), bottomPadding);
        }
    }

    @Override
    public void animateContentIn(int delay, int duration) {
        messageView.setAlpha(0f);
        messageView.animate().alpha(1f).setDuration(duration).
                setInterpolator(contentInterpolator).setStartDelay(delay).start();

        if (actionView.getVisibility() == VISIBLE) {
            actionView.setAlpha(0f);
            actionView.animate().alpha(1f).setDuration(duration).
                    setInterpolator(contentInterpolator).setStartDelay(delay).start();
        }
    }

    @Override
    public void animateContentOut(int delay, int duration) {
        messageView.setAlpha(1f);
        messageView.animate().alpha(0f).setDuration(duration).
                setInterpolator(contentInterpolator).setStartDelay(delay).start();

        if (actionView.getVisibility() == VISIBLE) {
            actionView.setAlpha(1f);
            actionView.animate().alpha(0f).setDuration(duration).
                    setInterpolator(contentInterpolator).setStartDelay(delay).start();
        }
    }

    public void setMaxInlineActionWidth(int width) {
        maxInlineActionWidth = width;
    }
}
