package 商业.谷歌.安卓.材质.时间选择器;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** Types of formats for the time picker */
@IntDef({TimeFormat.CLOCK_12H, TimeFormat.CLOCK_24H})
@Retention(RetentionPolicy.SOURCE)
public @interface TimeFormat {

    int CLOCK_12H = 0;

    int CLOCK_24H = 1;
}
