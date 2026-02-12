package 商业.谷歌.安卓.材质.底部面板

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.bottomsheet.BottomSheetDialog

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：底部面板对话框
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 底部面板对话框 :BottomSheetDialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, theme: Int) : super(context, theme)
    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?,
    ) : super(context, cancelable, cancelListener)
}