package 商业.谷歌.安卓.材质.时间选择器;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.view.View;
import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class 材质时间选择器 {

    public static final int 输入_模式_时钟 = MaterialTimePicker.INPUT_MODE_CLOCK;
    public static final int 输入_模式_键盘 = MaterialTimePicker.INPUT_MODE_KEYBOARD;

    //===========================================================================================

    private final MaterialTimePicker 材质时间选择器1;

    public 材质时间选择器(){
        材质时间选择器1 = new MaterialTimePicker();
    }

    private 材质时间选择器(MaterialTimePicker picker){
        材质时间选择器1 = picker;
    }

    //===========================================================================================

    /** 返回范围在[0, 59]之间的分钟数。 */
    @IntRange(from = 0, to = 59)
    public int getMinute() {
        return 材质时间选择器1.getMinute();
    }

    /** Sets the minute in the range [0, 59]. */
    public void setMinute(@IntRange(from = 0, to = 59) int minute) {
        材质时间选择器1.setMinute(minute);
    }

    /** Returns the hour of day in the range [0, 23]. */
    @IntRange(from = 0, to = 23)
    public int getHour() {
        return 材质时间选择器1.getHour();
    }

    /** Sets the hour of day in the range [0, 23]. */
    public void setHour(@IntRange(from = 0, to = 23) int hour) {
        材质时间选择器1.setHour(hour);
    }

    @SuppressLint("WrongConstant")
    @MaterialDatePicker.InputMode
    public int getInputMode() {
        return 材质时间选择器1.getInputMode();
    }

    public boolean addOnPositiveButtonClickListener(@NonNull View.OnClickListener listener) {
        return 材质时间选择器1.addOnPositiveButtonClickListener(listener);
    }


    public boolean removeOnPositiveButtonClickListener(@NonNull View.OnClickListener listener) {
        return 材质时间选择器1.removeOnPositiveButtonClickListener(listener);
    }

    /**
     * Removes all listeners added via {@link
     * MaterialTimePicker#addOnPositiveButtonClickListener(View.OnClickListener)}.
     */
    public void clearOnPositiveButtonClickListeners() {
        材质时间选择器1.clearOnPositiveButtonClickListeners();
    }

    /** The supplied listener is called when the user clicks the cancel button. */
    public boolean addOnNegativeButtonClickListener(@NonNull View.OnClickListener listener) {
        return 材质时间选择器1.addOnNegativeButtonClickListener(listener);
    }

    /**
     * Removes a listener previously added via {@link
     * MaterialTimePicker#addOnNegativeButtonClickListener(View.OnClickListener)}.
     */
    public boolean removeOnNegativeButtonClickListener(@NonNull View.OnClickListener listener) {
        return 材质时间选择器1.removeOnNegativeButtonClickListener(listener);
    }

    /**
     * Removes all listeners added via {@link
     * MaterialTimePicker#addOnNegativeButtonClickListener(View.OnClickListener)}.
     */
    public void clearOnNegativeButtonClickListeners() {
        材质时间选择器1.clearOnNegativeButtonClickListeners();
    }

    /**
     * The supplied listener is called when the user cancels the picker via back button or a touch
     * outside the view.
     *
     * <p>It is not called when the user clicks the cancel button. To add a listener for use when the
     * user clicks the cancel button, use {@link
     * MaterialTimePicker#addOnNegativeButtonClickListener(View.OnClickListener)}.
     */
    public boolean addOnCancelListener(@NonNull DialogInterface.OnCancelListener listener) {
        return 材质时间选择器1.addOnCancelListener(listener);
    }

    /**
     * Removes a listener previously added via {@link
     * MaterialTimePicker#addOnCancelListener(DialogInterface.OnCancelListener)}.
     */
    public boolean removeOnCancelListener(@NonNull DialogInterface.OnCancelListener listener) {
        return 材质时间选择器1.removeOnCancelListener(listener);
    }

    /**
     * Removes all listeners added via {@link
     * MaterialTimePicker#addOnCancelListener(DialogInterface.OnCancelListener)}.
     */
    public void clearOnCancelListeners() {
        材质时间选择器1.clearOnCancelListeners();
    }

    /**
     * The supplied listener is called whenever the DialogFragment is dismissed, no matter how it is
     * dismissed.
     */
    public boolean addOnDismissListener(@NonNull DialogInterface.OnDismissListener listener) {
        return 材质时间选择器1.addOnDismissListener(listener);
    }

    /**
     * Removes a listener previously added via {@link
     * MaterialTimePicker#addOnDismissListener(DialogInterface.OnDismissListener)}.
     */
    public boolean removeOnDismissListener(@NonNull DialogInterface.OnDismissListener listener) {
        return 材质时间选择器1.removeOnDismissListener(listener);
    }

    /**
     * Removes all listeners added via {@link
     * MaterialTimePicker#addOnDismissListener(DialogInterface.OnDismissListener)}.
     */
    public void clearOnDismissListeners() {
        材质时间选择器1.clearOnDismissListeners();
    }




    public static final class 构建器{
        private final MaterialTimePicker.Builder 构建器1;
        public 构建器(){
            构建器1 = new MaterialTimePicker.Builder();
        }


        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setInputMode(@MaterialDatePicker.InputMode int inputMode) {
            构建器1.setInputMode(inputMode);
            return this;
        }

        /**
         * Sets the hour with which to start the time picker.
         *
         * @param hour The hour value is independent of the time format ({@link #setTimeFormat(int)}),
         *     and should always be a number in the [0, 23] range.
         */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setHour(@IntRange(from = 0, to = 23) int hour) {
            构建器1.setHour(hour);
            return this;
        }

        /** Sets the minute with which to start the time picker. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setMinute(@IntRange(from = 0, to = 59) int minute) {
            构建器1.setMinute(minute);
            return this;
        }

        /**
         * Sets the time format for the time picker.
         *
         * @param format Either {@code CLOCK_12H} 12 hour format with an AM/PM toggle or {@code
         *     CLOCK_24} 24 hour format without toggle.
         */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setTimeFormat(@TimeFormat int format) {
            构建器1.setTimeFormat(format);
            return this;
        }



        /** Sets the text used to guide the user at the top of the picker. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setTitleText(@StringRes int titleTextResId) {
            构建器1.setTitleText(titleTextResId);
            return this;
        }


        public 构建器 置标题文本(@Nullable CharSequence 字符序列){
            构建器1.setTitleText(字符序列);
            return this;
        }

        /** Sets the text used to guide the user at the top of the picker. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setTitleText(@Nullable CharSequence 字符序列) {
            构建器1.setTitleText(字符序列);
            return this;
        }

        /** Sets the text used in the positive action button. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setPositiveButtonText(@StringRes int positiveButtonTextResId) {
            构建器1.setPositiveButtonText(positiveButtonTextResId);
            return this;
        }

        /** Sets the text used in the positive action button. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setPositiveButtonText(@Nullable CharSequence positiveButtonText) {
            构建器1.setPositiveButtonText(positiveButtonText);
            return this;
        }

        /** Sets the text used in the negative action button. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setNegativeButtonText(@StringRes int negativeButtonTextResId) {
            构建器1.setNegativeButtonText(negativeButtonTextResId);
            return this;
        }

        /** Sets the text used in the negative action button. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setNegativeButtonText(@Nullable CharSequence negativeButtonText) {
            构建器1.setNegativeButtonText(negativeButtonText);
            return this;
        }

        /** Sets the theme for the time picker. */
        @NonNull
        @CanIgnoreReturnValue
        public 构建器 setTheme(@StyleRes int themeResId) {
            构建器1.setTheme(themeResId);
            return this;
        }



        /** 创建 一个 {@link 材质时间选择器} 使用提供的选项。 */
        @NonNull
        public 材质时间选择器 构建() {
            return new 材质时间选择器(构建器1.build());
        }

        /** 创建 一个 {@link 材质时间选择器} 使用提供的选项。 */
        @NonNull
        public MaterialTimePicker build() {
            return 构建器1.build();
        }

    }


    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        材质时间选择器1.show(manager, tag);
    }

    public int show(@NonNull FragmentTransaction transaction, @Nullable String tag) {
        return 材质时间选择器1.show(transaction, tag);
    }

    public void 显示(@NonNull FragmentManager manager, @Nullable String tag) {
        材质时间选择器1.show(manager, tag);
    }

    public int 显示(@NonNull FragmentTransaction transaction, @Nullable String tag) {
        return 材质时间选择器1.show(transaction, tag);
    }

}
