package 安卓x.应用兼容.组件;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.DialogTitle;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：对话框标题
 * @author dxyc
 */
@SuppressLint("RestrictedApi")
public class 对话框标题 extends DialogTitle {

    public 对话框标题(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 对话框标题(@NonNull Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 对话框标题(@NonNull Context 上下文) {
        super(上下文);
    }


}
