package 商业.谷歌.安卓.材质.时间选择器

import android.text.InputFilter
import android.text.Spanned

/** A [InputFilter] that prevents a value bigger that `max` from being entered  */
internal class 最大输入验证器(var max: Int) : InputFilter {
    override fun filter(
        source: CharSequence, start: Int, end: Int, dest: Spanned, dstart: Int, dend: Int
    ): CharSequence? {
        try {
            val builder = StringBuilder(dest)
            builder.replace(dstart, dend, source.subSequence(start, end).toString())
            val newVal = builder.toString()
            val input = newVal.toInt()
            if (input <= max) {
                return null
            }
        } catch (ok: NumberFormatException) {
            // Just ignored if we couldn't parse the number
        }
        return ""
    }
}
