package 商业.谷歌.安卓.材质.时间选择器

import androidx.annotation.IntDef
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import java.util.Calendar

/**
 * An interface for different implementations of the UI components of TimePicker.
 *
 *
 * The UI components expose a ClockFace and an alternative input method.
 */
internal interface 时间选择器控制器 {
    /** The 12h periods for a 12h time format  */
    @IntDef(*[Calendar.AM, Calendar.PM])
    @Retention(AnnotationRetention.SOURCE)
    annotation class ClockPeriod

    /** Types of active selection for time picker  */
    @IntDef(*[Calendar.MINUTE, Calendar.HOUR])
    @Retention(AnnotationRetention.SOURCE)
    annotation class ActiveSelection

    /** Sets the time in millis *  */
    fun updateTime(@ClockPeriod period: Int, hourOfDay: Int, @IntRange(from = 0) minute: Int)

    /** Set what we need to select. *  */
    fun setActiveSelection(@ActiveSelection selection: Int)

    /** Set the values in the clock face.  */
    fun setValues(clockValues: Array<String?>?, @StringRes contentDescription: Int)

    fun setHandRotation(rotation: Float)
}
