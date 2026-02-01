@file:Suppress("DEPRECATION")

package 安卓.应用

import android.app.ProgressDialog
import android.content.Context

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：进度对话框
 *
 * 版本：0.1.5
 * @author dxyc
 */
open class 进度对话框 : ProgressDialog {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, theme: Int) : super(context, theme)
}