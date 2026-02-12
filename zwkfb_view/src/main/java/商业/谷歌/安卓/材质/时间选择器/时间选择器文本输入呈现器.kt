package 商业.谷歌.安卓.材质.时间选择器

import android.annotation.SuppressLint
import android.content.res.Resources
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import com.google.android.material.R
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.internal.TextWatcherAdapter
import com.google.android.material.internal.ViewUtils
import java.util.Calendar

internal class 时间选择器文本输入呈现器(
    private val timePickerView: LinearLayout,
    private val time: 时间模型
) : 时间选择器视图.OnSelectionChange, 时间选择器呈现器 {

    @SuppressLint("RestrictedApi")
    private val minuteTextWatcher: TextWatcher = object : TextWatcherAdapter() {
        override fun afterTextChanged(s: Editable) {
            try {
                if (TextUtils.isEmpty(s)) {
                    time.setMinute(0)
                    return
                }
                val minute = s.toString().toInt()
                time.setMinute(minute)
            } catch (ok: NumberFormatException) {
                // ignore invalid input
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private val hourTextWatcher: TextWatcher = object : TextWatcherAdapter() {
        override fun afterTextChanged(s: Editable) {
            try {
                if (TextUtils.isEmpty(s)) {
                    time.setHour(0)
                    return
                }
                val hour = s.toString().toInt()
                time.setHour(hour)
            } catch (ok: NumberFormatException) {
                // ignore invalid input
            }
        }
    }
    private val minuteTextInput: 芯片文本输入组合视图
    private val hourTextInput: 芯片文本输入组合视图
    private val controller: 时间选择器文本输入按键控制器
    private val hourEditText: EditText?
    private val minuteEditText: EditText?
    private var toggle: MaterialButtonToggleGroup? = null

    init {
        val res = timePickerView.getResources()
        minuteTextInput =
            timePickerView.findViewById<芯片文本输入组合视图>(R.id.material_minute_text_input)
        hourTextInput =
            timePickerView.findViewById<芯片文本输入组合视图>(R.id.material_hour_text_input)
        val minuteLabel = minuteTextInput.findViewById<TextView>(R.id.material_label)
        val hourLabel = hourTextInput.findViewById<TextView>(R.id.material_label)

        minuteLabel.setText(res.getString(R.string.material_timepicker_minute))
        minuteLabel.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO)

        hourLabel.setText(res.getString(R.string.material_timepicker_hour))
        hourLabel.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO)

        minuteTextInput.setTag(R.id.selection_type, Calendar.MINUTE)
        hourTextInput.setTag(R.id.selection_type, Calendar.HOUR)

        if (time.format == 时间格式.CLOCK_12H) {
            setupPeriodToggle()
        }

        val onClickListener: View.OnClickListener =
            View.OnClickListener { v ->
                onSelectionChanged(v.getTag(R.id.selection_type) as Int)
            }

        hourTextInput.setOnClickListener(onClickListener)
        minuteTextInput.setOnClickListener(onClickListener)
        hourTextInput.addInputFilter(time.hourInputValidator)
        minuteTextInput.addInputFilter(time.minuteInputValidator)

        hourEditText = hourTextInput.textInput.getEditText()
        hourEditText!!.setAccessibilityDelegate(
            setTimeUnitAccessiblityLabel(
                timePickerView.getResources(), R.string.material_timepicker_hour
            )
        )
        minuteEditText = minuteTextInput.textInput.getEditText()
        minuteEditText!!.setAccessibilityDelegate(
            setTimeUnitAccessiblityLabel(
                timePickerView.getResources(), R.string.material_timepicker_minute
            )
        )

        controller = 时间选择器文本输入按键控制器(
            hourTextInput, minuteTextInput,
            time
        )
        hourTextInput.setChipDelegate(
            object : 单击动作委托(timePickerView.getContext(), R.string.material_hour_selection) {
                override fun onInitializeAccessibilityNodeInfo(
                    host: View, info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info.setContentDescription(
                        (res.getString(R.string.material_timepicker_hour)
                                + " " // Adds a pause between the hour label and the hour value.
                                + host.getResources()
                            .getString(
                                time.hourContentDescriptionResId,
                                time.hourForDisplay.toString()
                            ))
                    )
                }
            })
        minuteTextInput.setChipDelegate(
            object : 单击动作委托(
                timePickerView.getContext(),
                R.string.material_minute_selection
            ) {
                override fun onInitializeAccessibilityNodeInfo(
                    host: View, info: AccessibilityNodeInfoCompat
                ) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info.setContentDescription(
                        (res.getString(R.string.material_timepicker_minute)
                                + " " // Adds a pause between the minute label and the minute value.
                                + host.getResources()
                            .getString(R.string.material_minute_suffix, time.minute.toString()))
                    )
                }
            })

        initialize()
    }

    private fun setTimeUnitAccessiblityLabel(
        res: Resources, contentDescriptionResId: Int
    ): View.AccessibilityDelegate {
        return object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(v: View, info: AccessibilityNodeInfo) {
                super.onInitializeAccessibilityNodeInfo(v, info)
                info.setText(res.getString(contentDescriptionResId))
            }
        }
    }

    override fun initialize() {
        addTextWatchers()
        setTime(time)
        controller.bind()
    }

    private fun addTextWatchers() {
        hourEditText!!.addTextChangedListener(hourTextWatcher)
        minuteEditText!!.addTextChangedListener(minuteTextWatcher)
    }

    private fun removeTextWatchers() {
        hourEditText!!.removeTextChangedListener(hourTextWatcher)
        minuteEditText!!.removeTextChangedListener(minuteTextWatcher)
    }

    private fun setTime(time: 时间模型) {
        removeTextWatchers()
        val current = timePickerView.getResources().getConfiguration().locale
        val minuteFormatted = String.format(current, "%02d", time.minute)
        val hourFormatted = String.format(current, "%02d", time.hourForDisplay)
        minuteTextInput.setText(minuteFormatted)
        hourTextInput.setText(hourFormatted)
        addTextWatchers()
        updateSelection()
    }

    private fun setupPeriodToggle() {
        toggle = timePickerView.findViewById(R.id.material_clock_period_toggle)

        toggle!!.addOnButtonCheckedListener { group: MaterialButtonToggleGroup?, checkedId: Int, isChecked: Boolean ->
            if (!isChecked) {
                return@addOnButtonCheckedListener
            }
            val period =
                if (checkedId == R.id.material_clock_period_pm_button) Calendar.PM else Calendar.AM
            time.setPeriod(period)
        }

        toggle!!.setVisibility(View.VISIBLE)
        updateSelection()
    }

    private fun updateSelection() {
        if (toggle == null) {
            return
        }

        toggle!!.check(
            if (time.period == Calendar.AM)
                R.id.material_clock_period_am_button
            else
                R.id.material_clock_period_pm_button
        )
    }

    override fun onSelectionChanged(selection: Int) {
        time.selection = selection
        minuteTextInput.setChecked(selection == Calendar.MINUTE)
        hourTextInput.setChecked(selection == Calendar.HOUR)
        updateSelection()
    }

    override fun show() {
        timePickerView.setVisibility(View.VISIBLE)
        onSelectionChanged(time.selection)
    }

    @SuppressLint("RestrictedApi")
    override fun hide() {
        val currentFocus = timePickerView.getFocusedChild()
        if (currentFocus != null) {
            ViewUtils.hideKeyboard(currentFocus,  /* useWindowInsetsController= */false)
        }

        timePickerView.setVisibility(View.GONE)
    }

    override fun invalidate() {
        setTime(time)
    }

    fun resetChecked() {
        minuteTextInput.setChecked(time.selection == Calendar.MINUTE)
        hourTextInput.setChecked(time.selection == Calendar.HOUR)
    }

    fun clearCheck() {
        minuteTextInput.setChecked(false)
        hourTextInput.setChecked(false)
    }
}
