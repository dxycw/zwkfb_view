package 商业.谷歌.安卓.材质.日期选择器

import android.content.Context
import com.google.android.material.datepicker.MaterialStyledDatePickerDialog

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：材质样式日期选择对话框
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 材质样式日期选择对话框 : MaterialStyledDatePickerDialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(
        context: Context,
        listener: OnDateSetListener?,
        year: Int,
        month: Int,
        dayOfMonth: Int,
    ) : super(context, listener, year, month, dayOfMonth)

    constructor(
        context: Context,
        themeResId: Int,
        listener: OnDateSetListener?,
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int,
    ) : super(context, themeResId, listener, year, monthOfYear, dayOfMonth)
}