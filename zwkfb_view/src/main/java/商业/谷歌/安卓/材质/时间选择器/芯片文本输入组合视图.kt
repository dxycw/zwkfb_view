package 商业.谷歌.安卓.材质.时间选择器

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Build.VERSION_CODES
import android.text.Editable
import android.text.InputFilter
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Checkable
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import com.google.android.material.R
import com.google.android.material.chip.Chip
import com.google.android.material.internal.TextWatcherAdapter
import com.google.android.material.internal.ViewUtils
import com.google.android.material.textfield.TextInputLayout

/**
 * A [Chip] that can switch to a [TextInputLayout] when checked to modify it's content.
 * It keeps the helper text from the TextInput always visible.
 */
class 芯片文本输入组合视图 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), Checkable {
    private val chip: Chip
    val textInput: TextInputLayout
    private val editText: EditText?
    private val watcher: TextWatcher?
    private val label: TextView

    init {
        val inflater = LayoutInflater.from(context)
        chip = inflater.inflate(zwkfb.view.R.layout.material_time_chip1, this, false) as Chip
        chip.setAccessibilityClassName(时间选择器视图.GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME)
        this.textInput =
            inflater.inflate(R.layout.material_time_input, this, false) as TextInputLayout
        editText = textInput.getEditText()
        editText!!.setVisibility(INVISIBLE)
        watcher = TextFormatter()
        editText.addTextChangedListener(watcher)
        updateHintLocales()
        addView(chip)
        addView(this.textInput)
        label = findViewById<TextView>(R.id.material_label)
        editText.setId(generateViewId())
        label.setLabelFor(editText.getId())
        editText.setSaveEnabled(false)
        editText.setLongClickable(false)
    }

    private fun updateHintLocales() {
        if (Build.VERSION.SDK_INT >= VERSION_CODES.N) {
            val configuration = getContext().getResources().getConfiguration()
            val locales = configuration.getLocales()
            editText!!.setImeHintLocales(locales)
        }
    }

    override fun isChecked(): Boolean {
        return chip.isChecked()
    }

    @SuppressLint("RestrictedApi")
    override fun setChecked(checked: Boolean) {
        chip.setChecked(checked)
        editText!!.setVisibility(if (checked) VISIBLE else INVISIBLE)
        // TODO(b/247609386) Should not hide chip, we need the background in M3 (but not M2...).
        // Instead, the text in chip should be hidden.
        chip.setVisibility(if (checked) GONE else VISIBLE)
        if (isChecked()) {
            ViewUtils.requestFocusAndShowKeyboard(
                editText,  /* useWindowInsetsController= */
                false
            )
        }
    }

    override fun toggle() {
        chip.toggle()
    }

    fun setText(text: CharSequence?) {
        val formattedText = formatText(text)
        chip.setText(formattedText)
        if (!TextUtils.isEmpty(formattedText)) {
            editText!!.removeTextChangedListener(watcher)
            editText.setText(formattedText)
            editText.addTextChangedListener(watcher)
        }
    }

    @get:VisibleForTesting
    val chipText: CharSequence?
        get() = chip.getText()

    private fun formatText(text: CharSequence?): String {
        return 时间模型.formatText(getResources(), text)!!
    }

    override fun setOnClickListener(l: OnClickListener?) {
        chip.setOnClickListener(l)
    }

    override fun setTag(key: Int, tag: Any?) {
        chip.setTag(key, tag)
    }

    fun setHelperText(helperText: CharSequence?) {
        label.setText(helperText)
    }

    fun setCursorVisible(visible: Boolean) {
        editText!!.setCursorVisible(visible)
    }

    fun addInputFilter(filter: InputFilter?) {
        val current = editText!!.getFilters()
        val arr = current.copyOf<InputFilter?>(current.size + 1)
        arr[current.size] = filter
        editText.setFilters(arr)
    }

    fun setChipDelegate(clickActionDelegate: AccessibilityDelegateCompat?) {
        ViewCompat.setAccessibilityDelegate(chip, clickActionDelegate)
    }

    @SuppressLint("RestrictedApi")
    private inner class TextFormatter : TextWatcherAdapter() {
        override fun afterTextChanged(editable: Editable) {
            if (TextUtils.isEmpty(editable)) {
                chip.setText(formatText(DEFAULT_TEXT))
                return
            }
            val formattedText = formatText(editable)
            chip.setText(if (TextUtils.isEmpty(formattedText)) formatText(DEFAULT_TEXT) else formattedText)
        }

        private val DEFAULT_TEXT = "00"

    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        updateHintLocales()
    }
}
