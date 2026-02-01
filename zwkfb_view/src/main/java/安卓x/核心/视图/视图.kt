package 安卓x.核心.视图

import android.view.View
import androidx.annotation.Px
import androidx.core.view.setPadding


/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 * @author dxyc
 */


/**
 * 版本：0.1.0
 *
 * 描述：设置内边距。
 * @param 大小 大小。
 */
fun View.置内边距(@Px 大小: Int) = setPadding(大小)