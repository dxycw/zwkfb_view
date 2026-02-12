package 商业.谷歌.安卓.材质.时间选择器

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.Checkable
import androidx.annotation.StringRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import com.google.android.material.R
import com.google.android.material.button.MaterialButtonToggleGroup
import com.google.android.material.chip.Chip
import java.util.Calendar

/**
 * The main view to display a time picker.
 *
 *
 * A time picker prompts the user to choose the time of day.
 *
 *
 * For more information, see the [component
 * developer guidance](https://github.com/material-components/material-components-android/blob/master/docs/components/TimePicker.md) and [design
 * guidelines](https://material.io/components/time-pickers/overview).
 */
internal class 时间选择器视图 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), 时间选择器控制器 {
    internal interface OnPeriodChangeListener {
        fun onPeriodChange(@时间选择器控制器.ClockPeriod period: Int)
    }

    internal interface OnSelectionChange {
        fun onSelectionChanged(@时间选择器控制器.ActiveSelection selection: Int)
    }

    internal interface OnDoubleTapListener {
        fun onDoubleTap()
    }

    private val minuteView: Chip
    private val hourView: Chip

    private val clockHandView: 时钟指针视图
    private val clockFace: 时钟表盘视图
    private val toggle: MaterialButtonToggleGroup

    private val selectionListener: OnClickListener = OnClickListener { v ->
        if (onSelectionChangeListener != null) {
            onSelectionChangeListener!!.onSelectionChanged(v.getTag(R.id.selection_type) as Int)
        }
    }

    private var onPeriodChangeListener: OnPeriodChangeListener? = null
    private var onSelectionChangeListener: OnSelectionChange? = null
    private var onDoubleTapListener: OnDoubleTapListener? = null

    init {
        LayoutInflater.from(context).inflate(zwkfb.view.R.layout.material_timepicker1, this)
        clockFace = findViewById(R.id.material_clock_face)
        toggle = findViewById(R.id.material_clock_period_toggle)

        toggle.addOnButtonCheckedListener { group: MaterialButtonToggleGroup?, checkedId: Int, isChecked: Boolean ->
            if (!isChecked) {
                return@addOnButtonCheckedListener
            }
            if (onPeriodChangeListener != null) {
                val period =
                    if (checkedId == R.id.material_clock_period_pm_button) Calendar.PM else Calendar.AM
                onPeriodChangeListener!!.onPeriodChange(period)
            }
        }

        minuteView = findViewById(R.id.material_minute_tv)
        hourView = findViewById(R.id.material_hour_tv)
        clockHandView = findViewById(R.id.material_clock_hand)

        setupDoubleTap()

        setUpDisplay()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupDoubleTap() {
        val gestureDetector =
            GestureDetector(
                getContext(),
                object : SimpleOnGestureListener() {
                    override fun onDoubleTap(e: MotionEvent): Boolean {
                        val listener = onDoubleTapListener
                        if (listener != null) {
                            listener.onDoubleTap()
                            return true
                        }
                        return false
                    }
                })

        val onTouchListener: OnTouchListener =
            object : OnTouchListener {
                override fun onTouch(v: View, event: MotionEvent): Boolean {
                    if ((v as Checkable).isChecked()) {
                        return gestureDetector.onTouchEvent(event)
                    }

                    return false
                }
            }

        minuteView.setOnTouchListener(onTouchListener)
        hourView.setOnTouchListener(onTouchListener)
    }

    fun setMinuteHourDelegate(clickActionDelegate: AccessibilityDelegateCompat?) {
        ViewCompat.setAccessibilityDelegate(hourView, clickActionDelegate)
    }

    fun setHourClickDelegate(clickActionDelegate: AccessibilityDelegateCompat?) {
        ViewCompat.setAccessibilityDelegate(minuteView, clickActionDelegate)
    }

    private fun setUpDisplay() {
        minuteView.setTag(R.id.selection_type, Calendar.MINUTE)
        hourView.setTag(R.id.selection_type, Calendar.HOUR)

        minuteView.setOnClickListener(selectionListener)
        hourView.setOnClickListener(selectionListener)

        minuteView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME)
        hourView.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME)
    }

    override fun setValues(values: Array<String?>?, @StringRes contentDescription: Int) {
        clockFace.setValues(values!!, contentDescription)
    }

    override fun setHandRotation(rotation: Float) {
        clockHandView.setHandRotation(rotation)
    }

    fun setHandRotation(rotation: Float, animate: Boolean) {
        clockHandView.setHandRotation(rotation, animate)
    }

    fun setAnimateOnTouchUp(animating: Boolean) {
        clockHandView.setAnimateOnTouchUp(animating)
    }

    @SuppressLint("DefaultLocale")
    override fun updateTime(
        @时间选择器控制器.ClockPeriod period: Int,
        hourOfDay: Int,
        minute: Int
    ) {
        val checkedId = if (period == Calendar.PM)
            R.id.material_clock_period_pm_button
        else
            R.id.material_clock_period_am_button
        toggle.check(checkedId)
        val current = getResources().getConfiguration().locale
        val minuteFormatted = String.format(current, 时间模型.ZERO_LEADING_NUMBER_FORMAT, minute)
        val hourFormatted = String.format(current, 时间模型.ZERO_LEADING_NUMBER_FORMAT, hourOfDay)
        if (!TextUtils.equals(minuteView.getText(), minuteFormatted)) {
            minuteView.setText(minuteFormatted)
        }
        if (!TextUtils.equals(hourView.getText(), hourFormatted)) {
            hourView.setText(hourFormatted)
        }
    }

    override fun setActiveSelection(@时间选择器控制器.ActiveSelection selection: Int) {
        updateSelection(minuteView, selection == Calendar.MINUTE)
        updateSelection(hourView, selection == Calendar.HOUR)
    }

    private fun updateSelection(chip: Chip, isSelected: Boolean) {
        chip.setChecked(isSelected)
        chip.setAccessibilityLiveRegion(
            if (isSelected)
                ACCESSIBILITY_LIVE_REGION_ASSERTIVE
            else
                ACCESSIBILITY_LIVE_REGION_NONE
        )
    }

    fun addOnRotateListener(onRotateListener: 时钟指针视图.OnRotateListener?) {
        clockHandView.addOnRotateListener(onRotateListener)
    }

    fun setOnActionUpListener(onActionUpListener: 时钟指针视图.OnActionUpListener?) {
        clockHandView.setOnActionUpListener(onActionUpListener)
    }

    fun setOnPeriodChangeListener(onPeriodChangeListener: OnPeriodChangeListener?) {
        this.onPeriodChangeListener = onPeriodChangeListener
    }

    fun setOnSelectionChangeListener(onSelectionChangeListener: OnSelectionChange?) {
        this.onSelectionChangeListener = onSelectionChangeListener
    }

    fun setOnDoubleTapListener(listener: OnDoubleTapListener?) {
        this.onDoubleTapListener = listener
    }

    fun showToggle() {
        toggle.setVisibility(VISIBLE)
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (changedView === this && visibility == VISIBLE) {
            hourView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
        }
    }

    @get:径向视图组.Level
    var currentLevel: Int
        get() = clockFace.currentLevel
        set(level) {
            clockFace.currentLevel = level
        }

    companion object {
        const val GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME: String = "android.view.View"
    }
}
