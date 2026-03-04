package 商业.谷歌.安卓.材质.时间选择器;

import static 商业.谷歌.安卓.材质.时间选择器.时间格式.CLOCK_12H;
import static 商业.谷歌.安卓.材质.时间选择器.时间格式.CLOCK_24H;
import static java.util.Calendar.AM;
import static java.util.Calendar.HOUR;
import static java.util.Calendar.PM;

import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.IntRange;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;

import java.util.Arrays;

import com.google.android.material.R;

/** The representation of the TimeModel used by TimePicker views. */
class 时间模型 implements Parcelable {

    public static final String ZERO_LEADING_NUMBER_FORMAT = "%02d";
    public static final String NUMBER_FORMAT = "%d";

    @时间格式
    final int format;

    int hour;
    int minute;

    @时间选择器控件.ActiveSelection
    int selection;
    @时间选择器控件.ClockPeriod
    int period;

    public 时间模型() {
        this(CLOCK_12H);
    }

    public 时间模型(@时间格式 int format) {
        this(0, 0, HOUR, format);
    }

    public 时间模型(int hour, int minute, @时间选择器控件.ActiveSelection int selection, @时间格式 int format) {
        this.hour = hour;
        this.minute = minute;
        this.selection = selection;
        this.format = format;
        period = getPeriod(hour);
    }

    protected 时间模型(Parcel in) {
        this(in.readInt(), in.readInt(), in.readInt(), in.readInt());
    }

    /** Set hour respecting the current clock period */
    public void setHourOfDay(int hour) {
        period = getPeriod(hour);
        this.hour = hour;
    }

    @时间选择器控件.ClockPeriod
    private static int getPeriod(int hourOfDay) {
        return hourOfDay >= 12 ? PM : AM;
    }

    /** Set hour respecting the current clock period */
    public void setHour(int hour) {
        if (format == CLOCK_24H) {
            this.hour = hour;
            return;
        }

        this.hour = hour % 12 + (period == PM ? 12 : 0);
    }

    public void setMinute(@IntRange(from = 0, to = 59) int minute) {
        this.minute = minute % 60;
    }

    public int getHourForDisplay() {
        if (format == CLOCK_24H) {
            return hour % 24;
        }

        if (hour % 12 == 0) {
            return 12;
        }

        if (period == PM) {
            return hour - 12;
        }

        return hour;
    }

    @StringRes
    public int getHourContentDescriptionResId() {
        return format == CLOCK_24H ? R.string.material_hour_24h_suffix : R.string.material_hour_suffix;
    }

    @Override
    public int hashCode() {
        Object[] hashedFields = {format, hour, minute, selection};
        return Arrays.hashCode(hashedFields);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof 时间模型)) {
            return false;
        }

        时间模型 that = (时间模型) o;
        return hour == that.hour
                && minute == that.minute
                && format == that.format
                && selection == that.selection;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(hour);
        dest.writeInt(minute);
        dest.writeInt(selection);
        dest.writeInt(format);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<时间模型> CREATOR =
            new Parcelable.Creator<时间模型>() {
                @Override
                public 时间模型 createFromParcel(Parcel in) {
                    return new 时间模型(in);
                }

                @Override
                public 时间模型[] newArray(int size) {
                    return new 时间模型[size];
                }
            };

    public void setPeriod(@时间选择器控件.ClockPeriod int period) {
        if (period != this.period) {
            this.period = period;
            if (hour < 12 && period == PM) {
                hour += 12;
            } else if (hour >= 12 && period == AM) {
                hour -= 12;
            }
        }
    }

    @Nullable
    public static String formatText(Resources resources, CharSequence text) {
        return formatText(resources, text, ZERO_LEADING_NUMBER_FORMAT);
    }

    @Nullable
    public static String formatText(Resources resources, CharSequence text, String format) {
        try {
            return String.format(resources.getConfiguration().locale, format, Integer.parseInt(String.valueOf(text)));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
