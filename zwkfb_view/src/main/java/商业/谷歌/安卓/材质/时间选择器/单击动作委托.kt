package 商业.谷歌.安卓.材质.时间选择器

import android.content.Context
import android.view.View
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat


internal open class 单击动作委托(context: Context, resId: Int) : AccessibilityDelegateCompat() {
    private val clickAction: AccessibilityActionCompat

    init {
        clickAction =
            AccessibilityActionCompat(
                AccessibilityNodeInfoCompat.ACTION_CLICK, context.getString(resId)
            )
    }

    override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(host, info)
        info.addAction(clickAction)
    }
}
