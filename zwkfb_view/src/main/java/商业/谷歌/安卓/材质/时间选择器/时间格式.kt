package 商业.谷歌.安卓.材质.时间选择器

import androidx.annotation.IntDef

/** Types of formats for the time picker  */
@IntDef(*[时间格式.CLOCK_12H, 时间格式.CLOCK_24H])
@Retention(AnnotationRetention.SOURCE)
annotation class 时间格式 {
    companion object {
        const val CLOCK_12H: Int = 0

        const val CLOCK_24H: Int = 1
    }
}
