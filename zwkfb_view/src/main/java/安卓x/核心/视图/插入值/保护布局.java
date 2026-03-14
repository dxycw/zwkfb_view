package 安卓x.核心.视图.插入值;

import android.content.Context;
import android.util.AttributeSet;

import androidx.core.view.insets.Protection;
import androidx.core.view.insets.ProtectionLayout;

import org.jspecify.annotations.NonNull;

import java.util.List;

/**
 * 创建时间：2025年11月22日.
 * <p>
 * 描述：保护布局
 * @author dxyc
 */
public class 保护布局 extends ProtectionLayout {

    public 保护布局(Context 上下文) {
        super(上下文);
    }

    public 保护布局(Context 上下文, AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 保护布局(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 保护布局(Context 上下文, AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }

    public 保护布局(@NonNull Context 上下文, @NonNull List<Protection> 保护) {
        super(上下文, 保护);
    }

}
