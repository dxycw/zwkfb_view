package 商业.谷歌.安卓.材质.时间选择器

import android.view.HapticFeedbackConstants
import android.view.View
import android.view.accessibility.AccessibilityManager
import androidx.core.content.ContextCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.google.android.material.R
import java.util.Calendar
import kotlin.math.floor

internal class 时间选择器时钟呈现器
    (private val timePickerView: 时间选择器视图, private val time: 时间模型) :
    时钟指针视图.OnRotateListener, 时间选择器视图.OnSelectionChange,
    时间选择器视图.OnPeriodChangeListener, 时钟指针视图.OnActionUpListener, 时间选择器呈现器 {
    private var minuteRotation = 0f
    private var hourRotation = 0f

    private var broadcasting = false

    init {
        initialize()
    }

    override fun initialize() {
        if (time.format == 时间格式.CLOCK_12H) {
            timePickerView.showToggle()
        }

        timePickerView.addOnRotateListener(this)
        timePickerView.setOnSelectionChangeListener(this)
        timePickerView.setOnPeriodChangeListener(this)
        timePickerView.setOnActionUpListener(this)
        updateValues()
        invalidate()
    }

    override fun invalidate() {
        hourRotation = getHourRotation().toFloat()
        minuteRotation = (time.minute * DEGREES_PER_MINUTE).toFloat()
        setSelection(time.selection, false)
        updateTime()
    }

    override fun show() {
        timePickerView.setVisibility(View.VISIBLE)
    }

    override fun hide() {
        timePickerView.setVisibility(View.GONE)
    }

    private val hourClockValues: Array<String?>?
        get() = if (time.format == 时间格式.CLOCK_24H) HOUR_CLOCK_24_VALUES else HOUR_CLOCK_VALUES

    override fun onRotate(rotation: Float, animating: Boolean) {
        // Do not update the displayed and actual time during an animation
        if (broadcasting || animating) {
            return
        }

        val prevHour = time.hour
        val prevMinute = time.minute
        val rotationInt = Math.round(rotation)
        if (time.selection == Calendar.MINUTE) {
            val minuteOffset: Int = DEGREES_PER_MINUTE / 2
            time.setMinute((rotationInt + minuteOffset) / DEGREES_PER_MINUTE)
            minuteRotation = floor((time.minute * DEGREES_PER_MINUTE).toDouble()).toFloat()
        } else {
            val hourOffset: Int = DEGREES_PER_HOUR / 2

            var hour: Int = (rotationInt + hourOffset) / DEGREES_PER_HOUR
            if (time.format == 时间格式.CLOCK_24H) {
                hour %= 12 // To correct hour (12 -> 0) in the 345-360 rotation section.
                if (timePickerView.currentLevel == 径向视图组.LEVEL_2) {
                    hour += 12
                }
            }

            time.setHour(hour)
            hourRotation = getHourRotation().toFloat()
        }

        updateTime()
        performHapticFeedback(prevHour, prevMinute)
    }

    private fun performHapticFeedback(prevHour: Int, prevMinute: Int) {
        if (time.minute != prevMinute || time.hour != prevHour) {
            timePickerView.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
        }
    }

    override fun onSelectionChanged(selection: Int) {
        setSelection(selection, true)
    }

    override fun onPeriodChange(period: Int) {
        time.setPeriod(period)
    }

    fun setSelection(@时间选择器控制器.ActiveSelection selection: Int, animate: Boolean) {
        val isMinute = selection == Calendar.MINUTE
        // Don't animate hours since we are going to auto switch to the minute selection.
        timePickerView.setAnimateOnTouchUp(isMinute)
        time.selection = selection
        timePickerView.setValues(
            if (isMinute) MINUTE_CLOCK_VALUES else this.hourClockValues,
            if (isMinute) R.string.material_minute_suffix else time.hourContentDescriptionResId
        )
        updateCurrentLevel()
        timePickerView.setHandRotation(if (isMinute) minuteRotation else hourRotation, animate)
        timePickerView.setActiveSelection(selection)
        timePickerView.setMinuteHourDelegate(
            object :
                单击动作委托(timePickerView.getContext(), R.string.material_hour_selection) {
                override fun onInitializeAccessibilityNodeInfo(
                    host: View, info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info.setContentDescription(
                        host.getResources()
                            .getString(
                                time.hourContentDescriptionResId,
                                time.hourForDisplay.toString()
                            )
                    )
                }
            })
        timePickerView.setHourClickDelegate(
            object : 单击动作委托(
                timePickerView.getContext(),
                R.string.material_minute_selection
            ) {
                override fun onInitializeAccessibilityNodeInfo(
                    host: View, info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info.setContentDescription(
                        host.getResources()
                            .getString(R.string.material_minute_suffix, time.minute.toString())
                    )
                }
            })
    }

    private fun updateCurrentLevel() {
        var currentLevel = 径向视图组.LEVEL_1
        if (time.selection == Calendar.HOUR && time.format == 时间格式.CLOCK_24H && time.hour >= 12) {
            currentLevel = 径向视图组.LEVEL_2
        }
        timePickerView.currentLevel = currentLevel
    }

    override fun onActionUp(rotation: Float, moveInEventStream: Boolean) {
        broadcasting = true
        val prevMinute = time.minute
        val prevHour = time.hour
        if (time.selection == Calendar.HOUR) {
            // Current rotation might be half way to an exact hour position.
            // Snap to the closest hour before animating to the position the minute selection is on.
            timePickerView.setHandRotation(hourRotation,  /* animate= */false)

            // Automatically move to minutes once the user finishes choosing the hour.
            val am =
                ContextCompat.getSystemService<AccessibilityManager?>(
                    timePickerView.getContext(),
                    AccessibilityManager::class.java
                )
            val isExploreByTouchEnabled = am != null && am.isTouchExplorationEnabled()
            if (!isExploreByTouchEnabled) {
                setSelection(Calendar.MINUTE,  /* animate= */true)
            }
        } else {
            val rotationInt = Math.round(rotation)
            if (!moveInEventStream) {
                // snap minute to 5 minute increment if there was only a touch down/up.
                val newRotation = (rotationInt + 15) / 30
                time.setMinute(newRotation * 5)
                minuteRotation = (time.minute * DEGREES_PER_MINUTE).toFloat()
            }
            timePickerView.setHandRotation(minuteRotation,  /* animate= */moveInEventStream)
        }
        broadcasting = false
        updateTime()
        performHapticFeedback(prevHour, prevMinute)
    }

    private fun updateTime() {
        timePickerView.updateTime(time.period, time.hourForDisplay, time.minute)
    }

    /** Update values with the correct number format  */
    private fun updateValues() {
        updateValues(HOUR_CLOCK_VALUES, 时间模型.NUMBER_FORMAT)
        updateValues(HOUR_CLOCK_24_VALUES, 时间模型.NUMBER_FORMAT)
        updateValues(MINUTE_CLOCK_VALUES, 时间模型.ZERO_LEADING_NUMBER_FORMAT)
    }

    private fun updateValues(values: Array<String?>, format: String?) {
        for (i in values.indices) {
            values[i] = 时间模型.formatText(timePickerView.getResources(), values[i], format!!)
        }
    }

    private fun getHourRotation(): Int {
        return (time.hourForDisplay * DEGREES_PER_HOUR) % 360
    }

    companion object {
        private val HOUR_CLOCK_VALUES = arrayOf<String?>(
            "12", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"
        )

        private val HOUR_CLOCK_24_VALUES = arrayOf<String?>(
            "00",
            "1",
            "2",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23"
        )

        private val MINUTE_CLOCK_VALUES = arrayOf<String?>(
            "00", "5", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"
        )

        private const val DEGREES_PER_HOUR = 30
        private const val DEGREES_PER_MINUTE = 6
    }
}
