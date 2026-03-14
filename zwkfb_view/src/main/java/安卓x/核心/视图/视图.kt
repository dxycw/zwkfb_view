package 安卓x.核心.视图

import android.view.View
import androidx.annotation.Px
import androidx.core.view.setPadding



/**
 * 描述：设置内边距。
 * @param 大小 大小。
 */
fun View.置内边距(@Px 大小: Int) = setPadding(大小)