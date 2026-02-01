package 商业.谷歌.安卓.材质.时间选择器;

import com.google.android.material.R;

import static android.text.TextUtils.isEmpty;

import static 商业.谷歌.安卓.材质.时间选择器.TimePickerView.GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.LocaleList;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.chip.Chip;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.textfield.TextInputLayout;


import java.util.Arrays;

/**
 * A {@link Chip} that can switch to a {@link TextInputLayout} when checked to modify it's content.
 * It keeps the helper text from the TextInput always visible.
 */
public class ChipTextInputComboView extends FrameLayout implements Checkable {

    private final Chip chip;
    private final TextInputLayout textInputLayout;
    private final EditText editText;
    private TextWatcher watcher;
    private TextView label;

    public ChipTextInputComboView(@NonNull Context context) {
        this(context, null);
    }

    public ChipTextInputComboView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChipTextInputComboView(
            @NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        chip = (Chip) inflater.inflate(zwkfb.view.R.layout.material_time_chip, this, false);
        chip.setAccessibilityClassName(GENERIC_VIEW_ACCESSIBILITY_CLASS_NAME);
        textInputLayout = (TextInputLayout) inflater.inflate(R.layout.material_time_input, this, false);
        editText = textInputLayout.getEditText();
        editText.setVisibility(INVISIBLE);
        watcher = new TextFormatter();
        editText.addTextChangedListener(watcher);
        updateHintLocales();
        addView(chip);
        addView(textInputLayout);
        label = findViewById(R.id.material_label);
        editText.setId(View.generateViewId());
        label.setLabelFor(editText.getId());
        editText.setSaveEnabled(false);
        editText.setLongClickable(false);
    }

    private void updateHintLocales() {
        if (VERSION.SDK_INT >= VERSION_CODES.N) {
            Configuration configuration = getContext().getResources().getConfiguration();
            final LocaleList locales = configuration.getLocales();
            editText.setImeHintLocales(locales);
        }
    }

    @Override
    public boolean isChecked() {
        return chip.isChecked();
    }

    @Override
    public void setChecked(boolean checked) {
        chip.setChecked(checked);
        editText.setVisibility(checked ? VISIBLE : INVISIBLE);
        // TODO(b/247609386) Should not hide chip, we need the background in M3 (but not M2...).
        // Instead, the text in chip should be hidden.
        chip.setVisibility(checked ? GONE : VISIBLE);
        if (isChecked()) {
            ViewUtils.requestFocusAndShowKeyboard(editText, /* useWindowInsetsController= */ false);
        }
    }

    @Override
    public void toggle() {
        chip.toggle();
    }

    public void setText(CharSequence text) {
        String formattedText = formatText(text);
        chip.setText(formattedText);
        if (!isEmpty(formattedText)) {
            editText.removeTextChangedListener(watcher);
            editText.setText(formattedText);
            editText.addTextChangedListener(watcher);
        }
    }

    @VisibleForTesting
    CharSequence getChipText() {
        return chip.getText();
    }

    private String formatText(CharSequence text) {
        return TimeModel.formatText(getResources(), text);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        chip.setOnClickListener(l);
    }

    @Override
    public void setTag(int key, Object tag) {
        chip.setTag(key, tag);
    }

    public void setHelperText(CharSequence helperText) {
        label.setText(helperText);
    }

    public void setCursorVisible(boolean visible) {
        editText.setCursorVisible(visible);
    }

    public void addInputFilter(InputFilter filter) {
        InputFilter[] current = editText.getFilters();
        InputFilter[] arr = Arrays.copyOf(current, current.length + 1);
        arr[current.length] = filter;
        editText.setFilters(arr);
    }

    public TextInputLayout getTextInput() {
        return textInputLayout;
    }

    public void setChipDelegate(AccessibilityDelegateCompat clickActionDelegate) {
        ViewCompat.setAccessibilityDelegate(chip, clickActionDelegate);
    }

    private class TextFormatter extends TextWatcherAdapter {

        private static final String DEFAULT_TEXT = "00";

        @Override
        public void afterTextChanged(Editable editable) {
            if (isEmpty(editable)) {
                chip.setText(formatText(DEFAULT_TEXT));
                return;
            }
            String formattedText = formatText(editable);
            chip.setText(isEmpty(formattedText) ? formatText(DEFAULT_TEXT) : formattedText);
        }
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateHintLocales();
    }
}
