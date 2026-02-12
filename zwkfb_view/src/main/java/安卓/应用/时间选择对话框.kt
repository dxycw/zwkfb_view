package 安卓.应用

import android.app.TimePickerDialog
import android.content.Context

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：时间选择对话框
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 时间选择对话框 : TimePickerDialog {
    constructor(
        context: Context?,
        listener: OnTimeSetListener?,
        hourOfDay: Int,
        minute: Int,
        is24HourView: Boolean,
    ) : super(context, listener, hourOfDay, minute, is24HourView)

    constructor(
        context: Context?,
        themeResId: Int,
        listener: OnTimeSetListener?,
        hourOfDay: Int,
        minute: Int,
        is24HourView: Boolean,
    ) : super(context, themeResId, listener, hourOfDay, minute, is24HourView)
}