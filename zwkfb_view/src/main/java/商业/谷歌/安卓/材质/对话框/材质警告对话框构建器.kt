package 商业.谷歌.安卓.材质.对话框

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * 创建时间：2025年11月27日.
 *
 * 描述：材质警告对话框构建器
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 材质警告对话框构建器 :MaterialAlertDialogBuilder{
    constructor(context: Context) : super(context)
    constructor(context: Context, overrideThemeResId: Int) : super(context, overrideThemeResId)
}