package 商业.谷歌.安卓.材质.时间选择器

import android.content.res.Resources
import android.os.Parcel
import android.os.Parcelable
import androidx.annotation.IntRange
import androidx.annotation.StringRes
import com.google.android.material.R
import java.util.Calendar

/** The representation of the TimeModel used by TimePicker views.  */
internal class 时间模型(
    var hour: Int,
    var minute: Int,
    @field:时间选择器控制器.ActiveSelection @param:时间选择器控制器.ActiveSelection var selection: Int,
    @field:时间格式 @param:时间格式 val format: Int
) : Parcelable {
    val minuteInputValidator: 最大输入验证器
    val hourInputValidator: 最大输入验证器

    @时间选择器控制器.ClockPeriod
    var period: Int = 0

    @JvmOverloads
    constructor(@时间格式 format: Int = 时间格式.CLOCK_12H) : this(0, 0, Calendar.HOUR, format)

    protected constructor(`in`: Parcel) : this(
        `in`.readInt(),
        `in`.readInt(),
        `in`.readInt(),
        `in`.readInt()
    )

    /** Set hour respecting the current clock period  */
    fun setHourOfDay(hour: Int) {
        period = getPeriod(hour)
        this.hour = hour
    }

    /** Set hour respecting the current clock period  */
    @JvmName("setHourInternal")
    fun setHour(hour: Int) {
        if (format == 时间格式.CLOCK_24H) {
            this.hour = hour
            return
        }

        this.hour = hour % 12 + (if (period == Calendar.PM) 12 else 0)
    }

    @JvmName("setMinuteValue")
    fun setMinute(@IntRange(from = 0, to = 59) minute: Int) {
        this.minute = minute % 60
    }

    val hourForDisplay: Int
        get() {
            if (format == 时间格式.CLOCK_24H) {
                return hour % 24
            }

            if (hour % 12 == 0) {
                return 12
            }

            if (period == Calendar.PM) {
                return hour - 12
            }

            return hour
        }

    @get:StringRes
    val hourContentDescriptionResId: Int
        get() = if (format == 时间格式.CLOCK_24H) R.string.material_hour_24h_suffix else R.string.material_hour_suffix

    override fun hashCode(): Int {
        val hashedFields = arrayOf<Any?>(format, hour, minute, selection)
        return hashedFields.contentHashCode()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }

        if (o !is 时间模型) {
            return false
        }

        val that = o
        return hour == that.hour && minute == that.minute && format == that.format && selection == that.selection
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeInt(hour)
        dest.writeInt(minute)
        dest.writeInt(selection)
        dest.writeInt(format)
    }

    init {
        period = getPeriod(hour)
        minuteInputValidator = 最大输入验证器(59)
        hourInputValidator = 最大输入验证器(if (format == 时间格式.CLOCK_24H) 23 else 12)
    }

    @JvmName("setPeriodInternal")
    fun setPeriod(@时间选择器控制器.ClockPeriod period: Int) {
        if (period != this.period) {
            this.period = period
            if (hour < 12 && period == Calendar.PM) {
                hour += 12
            } else if (hour >= 12 && period == Calendar.AM) {
                hour -= 12
            }
        }
    }

    companion object {
        const val ZERO_LEADING_NUMBER_FORMAT: String = "%02d"
        const val NUMBER_FORMAT: String = "%d"

        @时间选择器控制器.ClockPeriod
        private fun getPeriod(hourOfDay: Int): Int {
            return if (hourOfDay >= 12) Calendar.PM else Calendar.AM
        }

        @JvmField
        @Suppress("unused")
        val CREATOR: Parcelable.Creator<时间模型?> = object : Parcelable.Creator<时间模型?> {
            override fun createFromParcel(`in`: Parcel): 时间模型 {
                return 时间模型(`in`)
            }

            override fun newArray(size: Int): Array<时间模型?> {
                return arrayOfNulls<时间模型>(size)
            }
        }

        @JvmOverloads
        fun formatText(
            resources: Resources,
            text: CharSequence?,
            format: String = ZERO_LEADING_NUMBER_FORMAT
        ): String? {
            try {
                return String.format(
                    resources.getConfiguration().locale, format, text.toString().toInt()
                )
            } catch (e: NumberFormatException) {
                return null
            }
        }
    }
}
