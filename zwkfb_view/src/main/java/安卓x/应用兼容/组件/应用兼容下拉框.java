package 安卓x.应用兼容.组件;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;

/**
 * 创建时间：2025年11月22日.
 * <p>
 * 描述：应用兼容下拉框
 * @author dxyc
 */
public class 应用兼容下拉框 extends AppCompatSpinner {

    public 应用兼容下拉框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 应用兼容下拉框(@NonNull Context 上下文, int 模式) {
        super(上下文, 模式);
    }

    public 应用兼容下拉框(@NonNull Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 应用兼容下拉框(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 应用兼容下拉框(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, int 模式) {
        super(上下文, 属性, 默认样式属性, 模式);
    }

    public 应用兼容下拉框(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, int 模式, Resources.Theme 弹出主题) {
        super(上下文, 属性, 默认样式属性, 模式, 弹出主题);
    }

}
