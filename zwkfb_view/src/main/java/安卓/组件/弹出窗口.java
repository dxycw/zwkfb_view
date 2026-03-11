package 安卓.组件;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupWindow;

/**
 * 创建时间：2025年11月24日.
 * <p>
 * 描述：高级吐司
 * @author dxyc
 */
public class 弹出窗口 extends PopupWindow {

    public 弹出窗口() {
        super();
    }

    public 弹出窗口(Context 上下文) {
        super(上下文);
    }

    public 弹出窗口(Context 上下文, AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 弹出窗口(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 弹出窗口(Context 上下文, AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }

    public 弹出窗口(View 内容视图) {
        super(内容视图);
    }

    public 弹出窗口(View 内容视图, int 宽度, int 高度) {
        super(内容视图, 宽度, 高度);
    }

    public 弹出窗口(View 内容视图, int 宽度, int 高度, boolean 可聚焦) {
        super(内容视图, 宽度, 高度, 可聚焦);
    }

    public 弹出窗口(int 宽度, int 高度) {
        super(宽度, 高度);
    }


}
