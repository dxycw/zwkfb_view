package 商业.谷歌.安卓.材质.时间选择器;

import android.content.Context;
import android.view.View;

import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

class 单击操作委托 extends AccessibilityDelegateCompat {
    private final AccessibilityNodeInfoCompat.AccessibilityActionCompat clickAction;

    public 单击操作委托(Context context, int resId) {
        clickAction =
                new AccessibilityNodeInfoCompat.AccessibilityActionCompat(
                        AccessibilityNodeInfoCompat.ACTION_CLICK, context.getString(resId));
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
        super.onInitializeAccessibilityNodeInfo(host, info);
        info.addAction(clickAction);
    }
}
