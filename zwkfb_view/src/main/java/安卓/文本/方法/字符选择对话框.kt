package 安卓.文本.方法

import android.content.Context
import android.text.Editable
import android.text.method.CharacterPickerDialog
import android.view.View

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：字符选择对话框
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 字符选择对话框 : CharacterPickerDialog {
    constructor(
        context: Context?,
        view: View?,
        text: Editable?,
        options: String?,
        insert: Boolean,
    ) : super(context, view, text, options, insert)
}