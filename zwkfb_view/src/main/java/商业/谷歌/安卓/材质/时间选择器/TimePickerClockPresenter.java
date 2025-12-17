package 商业.谷歌.安卓.材质.时间选择器;

import com.google.android.material.R;

import static android.view.HapticFeedbackConstants.CLOCK_TICK;
import static android.view.View.GONE;
import static androidx.core.content.ContextCompat.getSystemService;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.MINUTE;

import static 商业.谷歌.安卓.材质.时间选择器.RadialViewGroup.LEVEL_1;
import static 商业.谷歌.安卓.材质.时间选择器.RadialViewGroup.LEVEL_2;
import static 商业.谷歌.安卓.材质.时间选择器.TimeFormat.CLOCK_12H;
import static 商业.谷歌.安卓.材质.时间选择器.TimeFormat.CLOCK_24H;

import android.view.View;
import android.view.accessibility.AccessibilityManager;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;


class TimePickerClockPresenter
        implements ClockHandView.OnRotateListener,
        TimePickerView.OnSelectionChange,
        TimePickerView.OnPeriodChangeListener,
        ClockHandView.OnActionUpListener,
        TimePickerPresenter {

    private static final String[] HOUR_CLOCK_VALUES = {
            "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
    };

    private static final String[] HOUR_CLOCK_24_VALUES = {
            "00", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
            "17", "18", "19", "20", "21", "22", "23"
    };

    private static final String[] MINUTE_CLOCK_VALUES = {
            "00", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"
    };

    private static final int DEGREES_PER_HOUR = 30;
    private static final int DEGREES_PER_MINUTE = 6;

    private final TimePickerView timePickerView;
    private final TimeModel time;
    private float minuteRotation;
    private float hourRotation;

    private boolean broadcasting = false;

    public TimePickerClockPresenter(TimePickerView timePickerView, TimeModel time) {
        this.timePickerView = timePickerView;
        this.time = time;
        initialize();
    }

    @Override
    public void initialize() {
        if (time.format == CLOCK_12H) {
            timePickerView.showToggle();
        }

        timePickerView.addOnRotateListener(this);
        timePickerView.setOnSelectionChangeListener(this);
        timePickerView.setOnPeriodChangeListener(this);
        timePickerView.setOnActionUpListener(this);
        updateValues();
        invalidate();
    }

    @Override
    public void invalidate() {
        hourRotation = getHourRotation();
        minuteRotation = time.minute * DEGREES_PER_MINUTE;
        setSelection(time.selection, false);
        updateTime();
    }

    @Override
    public void show() {
        timePickerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        timePickerView.setVisibility(GONE);
    }

    private String[] getHourClockValues() {
        return time.format == CLOCK_24H ? HOUR_CLOCK_24_VALUES : HOUR_CLOCK_VALUES;
    }

    @Override
    public void onRotate(float rotation, boolean animating) {
        // Do not update the displayed and actual time during an animation
        if (broadcasting || animating) {
            return;
        }

        int prevHour = time.hour;
        int prevMinute = time.minute;
        int rotationInt = Math.round(rotation);
        if (time.selection == MINUTE) {
            int minuteOffset = DEGREES_PER_MINUTE / 2;
            time.setMinute((rotationInt + minuteOffset) / DEGREES_PER_MINUTE);
            minuteRotation = (float) Math.floor(time.minute * DEGREES_PER_MINUTE);
        } else {
            int hourOffset = DEGREES_PER_HOUR / 2;

            int hour = (rotationInt + hourOffset) / DEGREES_PER_HOUR;
            if (time.format == CLOCK_24H) {
                hour %= 12; // To correct hour (12 -> 0) in the 345-360 rotation section.
                if (timePickerView.getCurrentLevel() == LEVEL_2) {
                    hour += 12;
                }
            }

            time.setHour(hour);
            hourRotation = getHourRotation();
        }

        updateTime();
        performHapticFeedback(prevHour, prevMinute);
    }

    private void performHapticFeedback(int prevHour, int prevMinute) {
        if (time.minute != prevMinute || time.hour != prevHour) {
            timePickerView.performHapticFeedback(CLOCK_TICK);
        }
    }

    @Override
    public void onSelectionChanged(int selection) {
        setSelection(selection, true);
    }

    @Override
    public void onPeriodChange(int period) {
        time.setPeriod(period);
    }

    void setSelection(@TimePickerControls.ActiveSelection int selection, boolean animate) {
        boolean isMinute = selection == MINUTE;
        // Don't animate hours since we are going to auto switch to the minute selection.
        timePickerView.setAnimateOnTouchUp(isMinute);
        time.selection = selection;
        timePickerView.setValues(
                isMinute ? MINUTE_CLOCK_VALUES : getHourClockValues(),
                isMinute ? R.string.material_minute_suffix : time.getHourContentDescriptionResId());
        updateCurrentLevel();
        timePickerView.setHandRotation(isMinute ? minuteRotation : hourRotation, animate);
        timePickerView.setActiveSelection(selection);
        timePickerView.setMinuteHourDelegate(
                new ClickActionDelegate(timePickerView.getContext(), R.string.material_hour_selection) {
                    @Override
                    public void onInitializeAccessibilityNodeInfo(
                            View host, AccessibilityNodeInfoCompat info) {
                        super.onInitializeAccessibilityNodeInfo(host, info);
                        info.setContentDescription(
                                host.getResources()
                                        .getString(
                                                time.getHourContentDescriptionResId(),
                                                String.valueOf(time.getHourForDisplay())));
                    }
                });
        timePickerView.setHourClickDelegate(
                new ClickActionDelegate(timePickerView.getContext(), R.string.material_minute_selection) {
                    @Override
                    public void onInitializeAccessibilityNodeInfo(
                            View host, AccessibilityNodeInfoCompat info) {
                        super.onInitializeAccessibilityNodeInfo(host, info);
                        info.setContentDescription(
                                host.getResources()
                                        .getString(R.string.material_minute_suffix, String.valueOf(time.minute)));
                    }
                });
    }

    private void updateCurrentLevel() {
        int currentLevel = LEVEL_1;
        if (time.selection == HOUR && time.format == CLOCK_24H && time.hour >= 12) {
            currentLevel = LEVEL_2;
        }
        timePickerView.setCurrentLevel(currentLevel);
    }

    @Override
    public void onActionUp(float rotation, boolean moveInEventStream) {
        broadcasting = true;
        int prevMinute = time.minute;
        int prevHour = time.hour;
        if (time.selection == HOUR) {
            // Current rotation might be half way to an exact hour position.
            // Snap to the closest hour before animating to the position the minute selection is on.
            timePickerView.setHandRotation(hourRotation, /* animate= */ false);
            // Automatically move to minutes once the user finishes choosing the hour.

            AccessibilityManager am =
                    getSystemService(timePickerView.getContext(), AccessibilityManager.class);
            boolean isExploreByTouchEnabled = am != null && am.isTouchExplorationEnabled();
            if (!isExploreByTouchEnabled) {
                setSelection(MINUTE, /* animate= */ true);
            }
        } else {
            int rotationInt = Math.round(rotation);
            if (!moveInEventStream) {
                // snap minute to 5 minute increment if there was only a touch down/up.
                int newRotation = (rotationInt + 15) / 30;
                time.setMinute(newRotation * 5);
                minuteRotation = time.minute * DEGREES_PER_MINUTE;
            }
            timePickerView.setHandRotation(minuteRotation, /* animate= */ moveInEventStream);
        }
        broadcasting = false;
        updateTime();
        performHapticFeedback(prevHour, prevMinute);
    }

    private void updateTime() {
        timePickerView.updateTime(time.period, time.getHourForDisplay(), time.minute);
    }

    /** Update values with the correct number format */
    private void updateValues() {
        updateValues(HOUR_CLOCK_VALUES, TimeModel.NUMBER_FORMAT);
        updateValues(HOUR_CLOCK_24_VALUES, TimeModel.NUMBER_FORMAT);
        updateValues(MINUTE_CLOCK_VALUES, TimeModel.ZERO_LEADING_NUMBER_FORMAT);
    }

    private void updateValues(String[] values, String format) {
        for (int i = 0; i < values.length; ++i) {
            values[i] = TimeModel.formatText(timePickerView.getResources(), values[i], format);
        }
    }

    private int getHourRotation() {
        return (time.getHourForDisplay() * DEGREES_PER_HOUR) % 360;
    }
}
