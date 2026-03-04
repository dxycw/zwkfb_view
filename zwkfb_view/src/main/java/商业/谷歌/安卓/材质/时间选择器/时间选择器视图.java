package 商业.谷歌.安卓.材质.时间选择器;

import com.google.android.material.R;

import 商业.谷歌.安卓.材质.时间选择器.径向视图组.Level;

import static java.util.Calendar.AM;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MINUTE;
import static java.util.Calendar.PM;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Checkable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.chip.Chip;

import java.util.Locale;


/**
 * The main view to display a time picker.
 *
 * <p>A time picker prompts the user to choose the time of day.
 *
 * <p>For more information, see the <a
 * href="https://github.com/material-components/material-components-android/blob/master/docs/components/TimePicker.md">component
 * developer guidance</a> and <a href="https://material.io/components/time-pickers/overview">design
 * guidelines</a>.
 */
class 时间选择器视图 extends ConstraintLayout implements 时间选择器控件 {

    interface OnPeriodChangeListener {
        void onPeriodChange(@ClockPeriod int period);
    }

    interface OnSelectionChange {
        void onSelectionChanged(@ActiveSelection int selection);
    }

    interface OnDoubleTapListener {
        void onDoubleTap();
    }

    static final String GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME = "android.view.View";

    private final Chip minuteView;
    private final Chip hourView;

    private final 时钟指针视图 clockHandView;
    private final 时钟表盘视图 clockFace;
    private final MaterialButtonToggleGroup toggle;

    private final OnClickListener selectionListener =
            new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onSelectionChangeListener != null) {
                        onSelectionChangeListener.onSelectionChanged((int) v.getTag(R.id.selection_type));
                    }
                }
            };

    private 时间选择器视图.OnPeriodChangeListener onPeriodChangeListener;
    private 时间选择器视图.OnSelectionChange onSelectionChangeListener;
    private 时间选择器视图.OnDoubleTapListener onDoubleTapListener;

    public 时间选择器视图(Context context) {
        this(context, null);
    }

    public 时间选择器视图(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public 时间选择器视图(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(context).inflate(R.layout.material_timepicker, this);
        clockFace = findViewById(R.id.material_clock_face);
        toggle = findViewById(R.id.material_clock_period_toggle);

        toggle.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (!isChecked) {
                return;
            }

            if (onPeriodChangeListener != null) {
                int period = checkedId == R.id.material_clock_period_pm_button ? PM : AM;
                onPeriodChangeListener.onPeriodChange(period);
            }
        });

        minuteView = findViewById(R.id.material_minute_tv);
        hourView = findViewById(R.id.material_hour_tv);
        clockHandView = findViewById(R.id.material_clock_hand);

        clockFace.setOnEnterKeyPressedListener(
                () -> {
                    if (hourView.isChecked() && onSelectionChangeListener != null) {
                        onSelectionChangeListener.onSelectionChanged(MINUTE);
                    }
                });

        setupDoubleTap();

        setUpDisplay();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setupDoubleTap() {
        final GestureDetector gestureDetector =
                new GestureDetector(
                        getContext(),
                        new GestureDetector.SimpleOnGestureListener() {
                            @Override
                            public boolean onDoubleTap(MotionEvent e) {
                                final 时间选择器视图.OnDoubleTapListener listener = onDoubleTapListener;
                                if (listener != null) {
                                    listener.onDoubleTap();
                                    return true;
                                }
                                return false;
                            }
                        });

        OnTouchListener onTouchListener =
                new OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (((Checkable) v).isChecked()) {
                            return gestureDetector.onTouchEvent(event);
                        }

                        return false;
                    }
                };

        minuteView.setOnTouchListener(onTouchListener);
        hourView.setOnTouchListener(onTouchListener);
    }

    public void setMinuteHourDelegate(AccessibilityDelegateCompat clickActionDelegate) {
        ViewCompat.setAccessibilityDelegate(hourView, clickActionDelegate);
    }

    public void setHourClickDelegate(AccessibilityDelegateCompat clickActionDelegate) {
        ViewCompat.setAccessibilityDelegate(minuteView, clickActionDelegate);
    }

    private void setUpDisplay() {
        minuteView.setTag(R.id.selection_type, MINUTE);
        hourView.setTag(R.id.selection_type, HOUR);

        minuteView.setOnClickListener(selectionListener);
        hourView.setOnClickListener(selectionListener);

        minuteView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
        hourView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
    }

    @Override
    public void setValues(String[] values, @StringRes int contentDescription) {
        clockFace.setValues(values, contentDescription);
    }

    @Override
    public void setHandRotation(float rotation) {
        clockHandView.setHandRotation(rotation);
    }

    public void setHandRotation(float rotation, boolean animate) {
        clockHandView.setHandRotation(rotation, animate);
    }

    public void setAnimateOnTouchUp(boolean animating) {
        clockHandView.setAnimateOnTouchUp(animating);
    }

    @Override
    @SuppressLint("DefaultLocale")
    public void updateTime(@ClockPeriod int period, int hourOfDay, int minute) {
        int checkedId = period == PM
                ? R.id.material_clock_period_pm_button
                : R.id.material_clock_period_am_button;
        toggle.check(checkedId);
        Locale current = getResources().getConfiguration().locale;
        String minuteFormatted = String.format(current, 时间模型.ZERO_LEADING_NUMBER_FORMAT, minute);
        String hourFormatted = String.format(current, 时间模型.ZERO_LEADING_NUMBER_FORMAT, hourOfDay);
        if (!TextUtils.equals(minuteView.getText(), minuteFormatted)) {
            minuteView.setText(minuteFormatted);
        }
        if (!TextUtils.equals(hourView.getText(), hourFormatted)) {
            hourView.setText(hourFormatted);
        }
    }

    @Override
    public void setActiveSelection(@ActiveSelection int selection) {
        updateSelection(minuteView, selection == MINUTE);
        updateSelection(hourView, selection == HOUR);
    }

    private void updateSelection(Chip chip, boolean isSelected) {
        chip.setChecked(isSelected);
        chip.setAccessibilityLiveRegion(isSelected
                ? View.ACCESSIBILITY_LIVE_REGION_ASSERTIVE
                : View.ACCESSIBILITY_LIVE_REGION_NONE);
    }

    public void addOnRotateListener(时钟指针视图.OnRotateListener onRotateListener) {
        clockHandView.addOnRotateListener(onRotateListener);
    }

    public void setOnActionUpListener(时钟指针视图.OnActionUpListener onActionUpListener) {
        clockHandView.setOnActionUpListener(onActionUpListener);
    }

    void setOnPeriodChangeListener(时间选择器视图.OnPeriodChangeListener onPeriodChangeListener) {
        this.onPeriodChangeListener = onPeriodChangeListener;
    }

    void setOnSelectionChangeListener(时间选择器视图.OnSelectionChange onSelectionChangeListener) {
        this.onSelectionChangeListener = onSelectionChangeListener;
    }

    void setOnDoubleTapListener(@Nullable 时间选择器视图.OnDoubleTapListener listener) {
        this.onDoubleTapListener = listener;
    }

    public void showToggle() {
        toggle.setVisibility(VISIBLE);
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (changedView == this && visibility == VISIBLE) {
            hourView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
        }
    }

    @Level
    int getCurrentLevel() {
        return clockFace.getCurrentLevel();
    }

    void setCurrentLevel(@Level int level) {
        clockFace.setCurrentLevel(level);
    }
}
