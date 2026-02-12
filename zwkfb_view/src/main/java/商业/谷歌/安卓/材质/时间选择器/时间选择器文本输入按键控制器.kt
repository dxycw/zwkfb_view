package 商业.谷歌.安卓.材质.时间选择器

import android.text.TextUtils
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener
import java.util.Calendar

/**
 * A class for the keyboard logic when the TimePicker is in `TimeFormat.KEYBOARD`
 *
 *
 * Controls part of the input validation and focus switching.
 */
internal class 时间选择器文本输入按键控制器(
    private val hourLayoutComboView: 芯片文本输入组合视图,
    private val minuteLayoutComboView: 芯片文本输入组合视图,
    private val time: 时间模型
) : OnEditorActionListener, View.OnKeyListener {
    private var keyListenerRunning = false

    /** Prepare Text inputs to receive key events and IME actions.  */
    fun bind() {
        val hourLayout = hourLayoutComboView.textInput
        val minuteLayout = minuteLayoutComboView.textInput
        val hourEditText = hourLayout.getEditText()
        val minuteEditText = minuteLayout.getEditText()

        hourEditText!!.setImeOptions(EditorInfo.IME_ACTION_NEXT or EditorInfo.IME_FLAG_NO_EXTRACT_UI)
        minuteEditText!!.setImeOptions(EditorInfo.IME_ACTION_DONE or EditorInfo.IME_FLAG_NO_EXTRACT_UI)

        hourEditText.setOnEditorActionListener(this)
        hourEditText.setOnKeyListener(this)
        minuteEditText.setOnKeyListener(this)
    }

    private fun moveSelection(@时间选择器控制器.ActiveSelection selection: Int) {
        minuteLayoutComboView.setChecked(selection == Calendar.MINUTE)
        hourLayoutComboView.setChecked(selection == Calendar.HOUR)
        time.selection = selection
    }

    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        val actionNext = actionId == EditorInfo.IME_ACTION_NEXT
        if (actionNext) {
            moveSelection(Calendar.MINUTE)
        }

        return actionNext
    }

    override fun onKey(view: View?, keyCode: Int, event: KeyEvent): Boolean {
        if (keyListenerRunning) {
            return false
        }

        keyListenerRunning = true
        val editText = view as EditText
        val ret =
            if (time.selection == Calendar.MINUTE)
                onMinuteKeyPress(keyCode, event, editText)
            else
                onHourKeyPress(keyCode, event, editText)
        keyListenerRunning = false
        return ret
    }

    private fun onMinuteKeyPress(keyCode: Int, event: KeyEvent, editText: EditText): Boolean {
        val switchFocus =
            keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN && TextUtils.isEmpty(
                editText.getText()
            )
        if (switchFocus) {
            moveSelection(Calendar.HOUR)
            return true
        }

        if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            clearPrefilledText(editText)
        }

        return false
    }

    private fun onHourKeyPress(keyCode: Int, event: KeyEvent, editText: EditText): Boolean {
        val text = editText.getText()
        if (text == null) {
            return false
        }

        // Auto-switch focus when 2 numbers are successfully entered
        val switchFocus =
            keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9 && event.getAction() == KeyEvent.ACTION_UP && editText.getSelectionStart() == 2 && text.length == 2
        if (switchFocus) {
            moveSelection(Calendar.MINUTE)
            return true
        }

        if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
            clearPrefilledText(editText)
        }

        return false
    }

    // Improve UX by auto-clearing existing text when entering new time
    private fun clearPrefilledText(editText: EditText) {
        if (editText.getSelectionStart() == 0 && editText.length() == 2) {
            editText.getText().clear()
        }
    }
}
