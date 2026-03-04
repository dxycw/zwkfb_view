package 商业.谷歌.安卓.材质.时间选择器;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/** Types of formats for the time picker */
@IntDef({时间格式.CLOCK_12H, 时间格式.CLOCK_24H})
@Retention(RetentionPolicy.SOURCE)
public @interface 时间格式 {

    int CLOCK_12H = 0;

    int CLOCK_24H = 1;
}
