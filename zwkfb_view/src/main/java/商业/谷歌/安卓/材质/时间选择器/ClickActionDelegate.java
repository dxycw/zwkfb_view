package 商业.谷歌.安卓.材质.时间选择器;


import android.content.Context;
import android.view.View;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat;

class ClickActionDelegate extends AccessibilityDelegateCompat {
    private final AccessibilityActionCompat clickAction;

    public ClickActionDelegate(Context context, int resId) {
        clickAction =
                new AccessibilityActionCompat(
                        AccessibilityNodeInfoCompat.ACTION_CLICK, context.getString(resId));
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
        super.onInitializeAccessibilityNodeInfo(host, info);
        info.addAction(clickAction);
    }
}
