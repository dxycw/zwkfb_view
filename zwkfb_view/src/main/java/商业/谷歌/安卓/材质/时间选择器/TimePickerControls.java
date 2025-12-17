package 商业.谷歌.安卓.材质.时间选择器;

import androidx.annotation.IntDef;
import androidx.annotation.IntRange;
import androidx.annotation.StringRes;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Calendar;

/**
 * An interface for different implementations of the UI components of TimePicker.
 *
 * <p>The UI components expose a ClockFace and an alternative input method.
 */
interface TimePickerControls {

    /** The 12h periods for a 12h time format */
    @IntDef({Calendar.AM, Calendar.PM})
    @Retention(RetentionPolicy.SOURCE)
    @interface ClockPeriod {}

    /** Types of active selection for time picker */
    @IntDef({Calendar.MINUTE, Calendar.HOUR})
    @Retention(RetentionPolicy.SOURCE)
    @interface ActiveSelection {}

    /** Sets the time in millis * */
    void updateTime(@ClockPeriod int period, int hourOfDay, @IntRange(from = 0) int minute);

    /** Set what we need to select. * */
    void setActiveSelection(@ActiveSelection int selection);

    /** Set the values in the clock face. */
    void setValues(String[] clockValues, @StringRes int contentDescription);

    void setHandRotation(float rotation);
}
