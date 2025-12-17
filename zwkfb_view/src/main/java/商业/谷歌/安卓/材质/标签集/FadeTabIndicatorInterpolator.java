package 商业.谷歌.安卓.材质.标签集;

import static com.google.android.material.animation.AnimationUtils.lerp;

import android.annotation.SuppressLint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;


class FadeTabIndicatorInterpolator extends TabIndicatorInterpolator {

    // When the indicator will disappear from the current tab and begin to reappear at the newly
    // selected tab.
    private static final float FADE_THRESHOLD = 0.5F;


    @SuppressLint("RestrictedApi")
    void updateIndicatorForOffset(
            TabLayout tabLayout,
            View startTitle,
            View endTitle,
            float offset,
            @NonNull Drawable indicator) {
        View tab = offset < FADE_THRESHOLD ? startTitle : endTitle;
        RectF bounds = calculateIndicatorWidthForTab(tabLayout, tab);
         float alpha = offset < FADE_THRESHOLD
                ? lerp(1F, 0F, 0F, FADE_THRESHOLD, offset)
                : lerp(0F, 1F, FADE_THRESHOLD, 1F, offset);

        indicator.setBounds(
                (int) bounds.left,
                indicator.getBounds().top,
                (int) bounds.right,
                indicator.getBounds().bottom
        );
        indicator.setAlpha((int) (alpha * 255F));
    }
}
