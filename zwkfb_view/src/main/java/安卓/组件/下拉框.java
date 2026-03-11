package 安卓.组件;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * 创建时间：2025年11月19日.
 * <p>
 * 描述：下拉列表
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
public class 下拉框 extends Spinner {

    public 下拉框(Context 上下文) {
        super(上下文);
    }

    public 下拉框(Context 上下文, AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 下拉框(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 下拉框(Context 上下文, AttributeSet 属性, int 默认样式属性, int 模式) {
        super(上下文, 属性, 默认样式属性, 模式);
    }

    public 下拉框(Context 上下文, AttributeSet 属性, int 默认样式属性, int 默认样式资源, int 模式) {
        super(上下文, 属性, 默认样式属性, 默认样式资源, 模式);
    }

    public 下拉框(Context 上下文, AttributeSet 属性, int 默认样式属性, int 默认样式资源, int 模式, Resources.Theme 弹出主题) {
        super(上下文, 属性, 默认样式属性, 默认样式资源, 模式, 弹出主题);
    }

    public 下拉框(Context 上下文, int 模式) {
        super(上下文, 模式);
    }


}
