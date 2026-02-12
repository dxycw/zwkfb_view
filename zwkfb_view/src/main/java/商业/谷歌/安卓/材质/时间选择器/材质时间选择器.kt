package 商业.谷歌.安卓.材质.时间选择器

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.TextUtils
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import android.view.Window
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.RestrictTo
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.annotation.VisibleForTesting
import androidx.core.view.accessibility.AccessibilityEventCompat
import androidx.fragment.app.DialogFragment
import com.google.android.material.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.resources.MaterialAttributes
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.errorprone.annotations.CanIgnoreReturnValue

/**
 * A [Dialog] with a clock display and a clock face to choose the time.
 *
 *
 * For more information, see the [component
 * developer guidance](https://github.com/material-components/material-components-android/blob/master/docs/components/TimePicker.md) and [design
 * guidelines](https://material.io/components/time-pickers/overview).
 */
class 材质时间选择器 : DialogFragment(), 时间选择器视图.OnDoubleTapListener {
    private val positiveButtonListeners = LinkedHashSet<View.OnClickListener>()
    private val negativeButtonListeners = LinkedHashSet<View.OnClickListener>()
    private val cancelListeners = LinkedHashSet<DialogInterface.OnCancelListener>()
    private val dismissListeners = LinkedHashSet<DialogInterface.OnDismissListener>()

    private var timePickerView: 时间选择器视图? = null
    private var textInputStub: ViewStub? = null

    private var timePickerClockPresenter: 时间选择器时钟呈现器? = null

    private var timePickerTextInputPresenter: 时间选择器文本输入呈现器? = null
    private var activePresenter: 时间选择器呈现器? = null

    @DrawableRes
    private var keyboardIcon = 0

    @DrawableRes
    private var clockIcon = 0

    @StringRes
    private var titleResId = 0
    private var titleText: CharSequence? = null

    @StringRes
    private var positiveButtonTextResId = 0
    private var positiveButtonText: CharSequence? = null

    @StringRes
    private var negativeButtonTextResId = 0
    private var negativeButtonText: CharSequence? = null

    /** Values supported for the input type of the dialog.  */
    @IntDef(*intArrayOf(INPUT_MODE_CLOCK, INPUT_MODE_KEYBOARD))
    @Retention(AnnotationRetention.SOURCE)
    internal annotation class InputMode

    private var modeButton: MaterialButton? = null
    private var cancelButton: Button? = null

    @get:InputMode
    @InputMode
    var inputMode: Int = INPUT_MODE_CLOCK
        private set

    private var time: 时间模型? = null

    private var overrideThemeResId = 0

    @get:IntRange(from = 0, to = 59)
    var minute: Int
        /** Returns the minute in the range [0, 59].  */
        get() = time!!.minute
        /** Sets the minute in the range [0, 59].  */
        set(minute) {
            time!!.setMinute(minute)
            if (activePresenter != null) {
                activePresenter!!.invalidate()
            }
        }

    @get:IntRange(from = 0, to = 23)
    var hour: Int
        /** Returns the hour of day in the range [0, 23].  */
        get() = time!!.hour % 24
        /** Sets the hour of day in the range [0, 23].  */
        set(hour) {
            time!!.setHour(hour)
            if (activePresenter != null) {
                activePresenter!!.invalidate()
            }
        }

    override fun onCreateDialog(bundle: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), this.themeResId)
        val context = dialog.getContext()

        val background =
            MaterialShapeDrawable(
                context,
                null,
                R.attr.materialTimePickerStyle,
                R.style.Widget_MaterialComponents_TimePicker
            )

        val a =
            context.obtainStyledAttributes(
                null,
                R.styleable.MaterialTimePicker,
                R.attr.materialTimePickerStyle,
                R.style.Widget_MaterialComponents_TimePicker
            )

        clockIcon = a.getResourceId(R.styleable.MaterialTimePicker_clockIcon, 0)
        keyboardIcon = a.getResourceId(R.styleable.MaterialTimePicker_keyboardIcon, 0)
        val backgroundColor = a.getColor(R.styleable.MaterialTimePicker_backgroundTint, 0)

        a.recycle()

        background.initializeElevationOverlay(context)
        background.setFillColor(ColorStateList.valueOf(backgroundColor))
        val window = dialog.getWindow()
        window!!.setBackgroundDrawable(background)
        window.requestFeature(Window.FEATURE_NO_TITLE)
        // On some Android APIs the dialog won't wrap content by default. Explicitly update here.
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        // This has to be done after requestFeature() is called on API <= 23.
        background.setElevation(window.getDecorView().getElevation())

        return dialog
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        restoreState(if (bundle == null) getArguments() else bundle)
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        bundle.putParcelable(TIME_MODEL_EXTRA, time)
        bundle.putInt(INPUT_MODE_EXTRA, inputMode)
        bundle.putInt(TITLE_RES_EXTRA, titleResId)
        bundle.putCharSequence(TITLE_TEXT_EXTRA, titleText)
        bundle.putInt(POSITIVE_BUTTON_TEXT_RES_EXTRA, positiveButtonTextResId)
        bundle.putCharSequence(POSITIVE_BUTTON_TEXT_EXTRA, positiveButtonText)
        bundle.putInt(NEGATIVE_BUTTON_TEXT_RES_EXTRA, negativeButtonTextResId)
        bundle.putCharSequence(NEGATIVE_BUTTON_TEXT_EXTRA, negativeButtonText)
        bundle.putInt(OVERRIDE_THEME_RES_ID, overrideThemeResId)
    }

    private fun restoreState(bundle: Bundle?) {
        if (bundle == null) {
            return
        }

        time = bundle.getParcelable(TIME_MODEL_EXTRA)
        if (time == null) {
            time = 时间模型()
        }
        val defaultInputMode: Int =
            if (time!!.format == 时间格式.CLOCK_24H) INPUT_MODE_KEYBOARD else INPUT_MODE_CLOCK
        inputMode = bundle.getInt(INPUT_MODE_EXTRA, defaultInputMode)
        titleResId = bundle.getInt(TITLE_RES_EXTRA, 0)
        titleText = bundle.getCharSequence(TITLE_TEXT_EXTRA)
        positiveButtonTextResId = bundle.getInt(POSITIVE_BUTTON_TEXT_RES_EXTRA, 0)
        positiveButtonText = bundle.getCharSequence(POSITIVE_BUTTON_TEXT_EXTRA)
        negativeButtonTextResId = bundle.getInt(NEGATIVE_BUTTON_TEXT_RES_EXTRA, 0)
        negativeButtonText = bundle.getCharSequence(NEGATIVE_BUTTON_TEXT_EXTRA)
        overrideThemeResId = bundle.getInt(OVERRIDE_THEME_RES_ID, 0)
    }

    override fun onCreateView(
        layoutInflater: LayoutInflater,
        viewGroup: ViewGroup?,
        bundle: Bundle?
    ): View {
        val root = layoutInflater.inflate(
            zwkfb.view.R.layout.material_timepicker_dialog1, viewGroup
        ) as ViewGroup
        timePickerView = root.findViewById(R.id.material_timepicker_view)
        timePickerView!!.setOnDoubleTapListener(this)
        textInputStub = root.findViewById(R.id.material_textinput_timepicker)
        modeButton = root.findViewById(R.id.material_timepicker_mode_button)
        val headerTitle = root.findViewById<TextView>(R.id.header_title)

        if (titleResId != 0) {
            headerTitle.setText(titleResId)
        } else if (!TextUtils.isEmpty(titleText)) {
            headerTitle.setText(titleText)
        }

        updateInputMode(modeButton)
        val okButton = root.findViewById<Button>(R.id.material_timepicker_ok_button)
        okButton.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    for (listener in positiveButtonListeners) {
                        listener.onClick(v)
                    }
                    dismiss()
                }
            })
        if (positiveButtonTextResId != 0) {
            okButton.setText(positiveButtonTextResId)
        } else if (!TextUtils.isEmpty(positiveButtonText)) {
            okButton.setText(positiveButtonText)
        }

        cancelButton = root.findViewById(R.id.material_timepicker_cancel_button)
        cancelButton!!.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(v: View?) {
                    for (listener in negativeButtonListeners) {
                        listener.onClick(v)
                    }
                    dismiss()
                }
            })
        if (negativeButtonTextResId != 0) {
            cancelButton!!.setText(negativeButtonTextResId)
        } else if (!TextUtils.isEmpty(negativeButtonText)) {
            cancelButton!!.setText(negativeButtonText)
        }

        updateCancelButtonVisibility()

        modeButton!!.setOnClickListener {
            inputMode =
                if (inputMode == INPUT_MODE_CLOCK) INPUT_MODE_KEYBOARD else INPUT_MODE_CLOCK
            updateInputMode(modeButton)
        }

        return root
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {
        super.onViewCreated(view, bundle)
        // TODO(b/246354286): Investigate issue with keyboard not showing on Android 12+
        if (activePresenter is 时间选择器文本输入呈现器) {
            view.postDelayed(
                Runnable {
                    if (activePresenter is 时间选择器文本输入呈现器) {
                        (activePresenter as 时间选择器文本输入呈现器).resetChecked()
                    }
                },
                100
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activePresenter = null
        timePickerClockPresenter = null
        timePickerTextInputPresenter = null
        if (timePickerView != null) {
            timePickerView!!.setOnDoubleTapListener(null)
            timePickerView = null
        }
    }

    override fun onCancel(dialogInterface: DialogInterface) {
        for (listener in cancelListeners) {
            listener.onCancel(dialogInterface)
        }
        super.onCancel(dialogInterface)
    }

    override fun onDismiss(dialogInterface: DialogInterface) {
        for (listener in dismissListeners) {
            listener.onDismiss(dialogInterface)
        }

        super.onDismiss(dialogInterface)
    }

    override fun setCancelable(cancelable: Boolean) {
        super.setCancelable(cancelable)
        updateCancelButtonVisibility()
    }

    /** @hide
     */
    @RestrictTo(RestrictTo.Scope.LIBRARY_GROUP)
    override fun onDoubleTap() {
        inputMode = INPUT_MODE_KEYBOARD
        updateInputMode(modeButton)
        timePickerTextInputPresenter!!.resetChecked()
    }

    private fun updateInputMode(modeButton: MaterialButton?) {
        if (modeButton == null || timePickerView == null || textInputStub == null) {
            return
        }

        if (activePresenter != null) {
            activePresenter!!.hide()
        }

        activePresenter =
            initializeOrRetrieveActivePresenterForMode(inputMode, timePickerView!!, textInputStub!!)
        activePresenter!!.show()
        activePresenter!!.invalidate()
        val buttonData = dataForMode(inputMode)
        modeButton.setIconResource(buttonData.first!!)
        modeButton.setContentDescription(getResources().getString(buttonData.second!!))
        modeButton.sendAccessibilityEvent(
            AccessibilityEventCompat.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION
        )
    }

    private fun updateCancelButtonVisibility() {
        if (cancelButton != null) {
            cancelButton!!.setVisibility(if (isCancelable()) View.VISIBLE else View.GONE)
        }
    }

    private fun initializeOrRetrieveActivePresenterForMode(
        mode: Int, timePickerView: 时间选择器视图, textInputStub: ViewStub
    ): 时间选择器呈现器? {
        if (mode == INPUT_MODE_CLOCK) {
            timePickerClockPresenter =
                if (timePickerClockPresenter == null)
                    时间选择器时钟呈现器(timePickerView, time!!)
                else
                    timePickerClockPresenter

            return timePickerClockPresenter
        }

        if (timePickerTextInputPresenter == null) {
            val textInputView = textInputStub.inflate() as LinearLayout
            timePickerTextInputPresenter = 时间选择器文本输入呈现器(textInputView, time!!)
        }

        timePickerTextInputPresenter!!.clearCheck()

        return timePickerTextInputPresenter
    }

    private fun dataForMode(@InputMode mode: Int): Pair<Int?, Int?> {
        when (mode) {
            INPUT_MODE_KEYBOARD -> return Pair<Int?, Int?>(
                clockIcon,
                R.string.material_timepicker_clock_mode_description
            )

            INPUT_MODE_CLOCK -> return Pair<Int?, Int?>(
                keyboardIcon,
                R.string.material_timepicker_text_input_mode_description
            )
        }

        throw IllegalArgumentException("no icon for mode: " + mode)
    }

    @VisibleForTesting
    internal fun setActivePresenter(presenter: 时间选择器呈现器?) {
        activePresenter = presenter
    }

    /** The supplied listener is called when the user confirms a valid selection.  */
    fun addOnPositiveButtonClickListener(listener: View.OnClickListener): Boolean {
        return positiveButtonListeners.add(listener)
    }

    /**
     * Removes a listener previously added via [ ][材质时间选择器.addOnPositiveButtonClickListener].
     */
    fun removeOnPositiveButtonClickListener(listener: View.OnClickListener): Boolean {
        return positiveButtonListeners.remove(listener)
    }

    /**
     * Removes all listeners added via [ ][材质时间选择器.addOnPositiveButtonClickListener].
     */
    fun clearOnPositiveButtonClickListeners() {
        positiveButtonListeners.clear()
    }

    /** The supplied listener is called when the user clicks the cancel button.  */
    fun addOnNegativeButtonClickListener(listener: View.OnClickListener): Boolean {
        return negativeButtonListeners.add(listener)
    }

    /**
     * Removes a listener previously added via [ ][材质时间选择器.addOnNegativeButtonClickListener].
     */
    fun removeOnNegativeButtonClickListener(listener: View.OnClickListener): Boolean {
        return negativeButtonListeners.remove(listener)
    }

    /**
     * Removes all listeners added via [ ][材质时间选择器.addOnNegativeButtonClickListener].
     */
    fun clearOnNegativeButtonClickListeners() {
        negativeButtonListeners.clear()
    }

    /**
     * The supplied listener is called when the user cancels the picker via back button or a touch
     * outside the view.
     *
     *
     * It is not called when the user clicks the cancel button. To add a listener for use when the
     * user clicks the cancel button, use [ ][材质时间选择器.addOnNegativeButtonClickListener].
     */
    fun addOnCancelListener(listener: DialogInterface.OnCancelListener): Boolean {
        return cancelListeners.add(listener)
    }

    /**
     * Removes a listener previously added via [ ][材质时间选择器.addOnCancelListener].
     */
    fun removeOnCancelListener(listener: DialogInterface.OnCancelListener): Boolean {
        return cancelListeners.remove(listener)
    }

    /**
     * Removes all listeners added via [ ][材质时间选择器.addOnCancelListener].
     */
    fun clearOnCancelListeners() {
        cancelListeners.clear()
    }

    /**
     * The supplied listener is called whenever the DialogFragment is dismissed, no matter how it is
     * dismissed.
     */
    fun addOnDismissListener(listener: DialogInterface.OnDismissListener): Boolean {
        return dismissListeners.add(listener)
    }

    /**
     * Removes a listener previously added via [ ][材质时间选择器.addOnDismissListener].
     */
    fun removeOnDismissListener(listener: DialogInterface.OnDismissListener): Boolean {
        return dismissListeners.remove(listener)
    }

    /**
     * Removes all listeners added via [ ][材质时间选择器.addOnDismissListener].
     */
    fun clearOnDismissListeners() {
        dismissListeners.clear()
    }

    @get:SuppressLint("RestrictedApi")
    private val themeResId: Int
        get() {
            if (overrideThemeResId != 0) {
                return overrideThemeResId
            }
            val value =
                MaterialAttributes.resolve(requireContext(), R.attr.materialTimePickerTheme)
            return if (value == null) 0 else value.data
        }

    /** Used to create [材质时间选择器] instances.  */
    class Builder {
        // final
        internal var time = 时间模型()

        internal var inputMode: Int? = null

        @StringRes
        internal var titleTextResId = 0
        internal var titleText: CharSequence? = null

        @StringRes
        internal var positiveButtonTextResId = 0
        internal var positiveButtonText: CharSequence? = null

        @StringRes
        internal var negativeButtonTextResId = 0
        internal var negativeButtonText: CharSequence? = null
        internal var overrideThemeResId = 0

        /** Sets the input mode with which to start the time picker.  */
        @CanIgnoreReturnValue
        fun setInputMode(@InputMode inputMode: Int): Builder {
            this.inputMode = inputMode
            return this
        }

        /**
         * Sets the hour with which to start the time picker.
         *
         * @param hour The hour value is independent of the time format ([.setTimeFormat]),
         * and should always be a number in the [0, 23] range.
         */
        @CanIgnoreReturnValue
        fun setHour(@IntRange(from = 0, to = 23) hour: Int): Builder {
            time.setHourOfDay(hour)
            return this
        }

        /** Sets the minute with which to start the time picker.  */
        @CanIgnoreReturnValue
        fun setMinute(@IntRange(from = 0, to = 59) minute: Int): Builder {
            time.setMinute(minute)
            return this
        }

        /**
         * Sets the time format for the time picker.
         *
         * @param format Either `CLOCK_12H` 12 hour format with an AM/PM toggle or `CLOCK_24` 24 hour format without toggle.
         */
        @CanIgnoreReturnValue
        fun setTimeFormat(@时间格式 format: Int): Builder {
            val hour = time.hour
            val minute = time.minute
            time = 时间模型(format)
            time.setMinute(minute)
            time.setHourOfDay(hour)
            return this
        }

        /** Sets the text used to guide the user at the top of the picker.  */
        @CanIgnoreReturnValue
        fun setTitleText(@StringRes titleTextResId: Int): Builder {
            this.titleTextResId = titleTextResId
            return this
        }

        /** Sets the text used to guide the user at the top of the picker.  */
        @CanIgnoreReturnValue
        fun setTitleText(charSequence: CharSequence?): Builder {
            this.titleText = charSequence
            return this
        }

        /** Sets the text used in the positive action button.  */
        @CanIgnoreReturnValue
        fun setPositiveButtonText(@StringRes positiveButtonTextResId: Int): Builder {
            this.positiveButtonTextResId = positiveButtonTextResId
            return this
        }

        /** Sets the text used in the positive action button.  */
        @CanIgnoreReturnValue
        fun setPositiveButtonText(positiveButtonText: CharSequence?): Builder {
            this.positiveButtonText = positiveButtonText
            return this
        }

        /** Sets the text used in the negative action button.  */
        @CanIgnoreReturnValue
        fun setNegativeButtonText(@StringRes negativeButtonTextResId: Int): Builder {
            this.negativeButtonTextResId = negativeButtonTextResId
            return this
        }

        /** Sets the text used in the negative action button.  */
        @CanIgnoreReturnValue
        fun setNegativeButtonText(negativeButtonText: CharSequence?): Builder {
            this.negativeButtonText = negativeButtonText
            return this
        }

        /** Sets the theme for the time picker.  */
        @CanIgnoreReturnValue
        fun setTheme(@StyleRes themeResId: Int): Builder {
            this.overrideThemeResId = themeResId
            return this
        }

        /** Creates a [材质时间选择器] with the provided options.  */
        fun build(): 材质时间选择器 {
            return newInstance(this)
        }
    }

    companion object {
        const val INPUT_MODE_CLOCK: Int = 0
        const val INPUT_MODE_KEYBOARD: Int = 1

        const val TIME_MODEL_EXTRA: String = "TIME_PICKER_TIME_MODEL"
        const val INPUT_MODE_EXTRA: String = "TIME_PICKER_INPUT_MODE"
        const val TITLE_RES_EXTRA: String = "TIME_PICKER_TITLE_RES"
        const val TITLE_TEXT_EXTRA: String = "TIME_PICKER_TITLE_TEXT"
        const val POSITIVE_BUTTON_TEXT_RES_EXTRA: String = "TIME_PICKER_POSITIVE_BUTTON_TEXT_RES"
        const val POSITIVE_BUTTON_TEXT_EXTRA: String = "TIME_PICKER_POSITIVE_BUTTON_TEXT"
        const val NEGATIVE_BUTTON_TEXT_RES_EXTRA: String = "TIME_PICKER_NEGATIVE_BUTTON_TEXT_RES"
        const val NEGATIVE_BUTTON_TEXT_EXTRA: String = "TIME_PICKER_NEGATIVE_BUTTON_TEXT"
        const val OVERRIDE_THEME_RES_ID: String = "TIME_PICKER_OVERRIDE_THEME_RES_ID"

        private fun newInstance(options: Builder): 材质时间选择器 {
            val fragment = 材质时间选择器()
            val args = Bundle()
            args.putParcelable(TIME_MODEL_EXTRA, options.time)
            if (options.inputMode != null) {
                args.putInt(INPUT_MODE_EXTRA, options.inputMode!!)
            }
            args.putInt(TITLE_RES_EXTRA, options.titleTextResId)
            if (options.titleText != null) {
                args.putCharSequence(TITLE_TEXT_EXTRA, options.titleText)
            }
            args.putInt(POSITIVE_BUTTON_TEXT_RES_EXTRA, options.positiveButtonTextResId)
            if (options.positiveButtonText != null) {
                args.putCharSequence(POSITIVE_BUTTON_TEXT_EXTRA, options.positiveButtonText)
            }
            args.putInt(NEGATIVE_BUTTON_TEXT_RES_EXTRA, options.negativeButtonTextResId)
            if (options.negativeButtonText != null) {
                args.putCharSequence(NEGATIVE_BUTTON_TEXT_EXTRA, options.negativeButtonText)
            }
            args.putInt(OVERRIDE_THEME_RES_ID, options.overrideThemeResId)

            fragment.setArguments(args)
            return fragment
        }
    }
}
