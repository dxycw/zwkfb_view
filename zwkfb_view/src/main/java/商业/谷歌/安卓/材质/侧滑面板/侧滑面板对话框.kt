package 商业.谷歌.安卓.材质.侧滑面板

import android.content.Context
import com.google.android.material.sidesheet.SideSheetDialog

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：侧滑面板对话框
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 侧滑面板对话框 : SideSheetDialog {
    constructor(context: Context) : super(context)
    constructor(context: Context, theme: Int) : super(context, theme)
}