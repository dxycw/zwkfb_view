package 安卓.应用

import android.app.DatePickerDialog
import android.content.Context

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：日期选择对话框
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 日期选择对话框 :DatePickerDialog {
    constructor(context: Context) : super(context)
    constructor(
        context: Context,
        listener: OnDateSetListener?,
        year: Int,
        month: Int,
        dayOfMonth: Int,
    ) : super(context, listener, year, month, dayOfMonth)

    constructor(context: Context, themeResId: Int) : super(context, themeResId)
    constructor(
        context: Context,
        themeResId: Int,
        listener: OnDateSetListener?,
        year: Int,
        monthOfYear: Int,
        dayOfMonth: Int,
    ) : super(context, themeResId, listener, year, monthOfYear, dayOfMonth)
}