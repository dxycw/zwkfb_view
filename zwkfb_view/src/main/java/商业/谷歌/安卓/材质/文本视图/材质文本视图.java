package 商业.谷歌.安卓.材质.文本视图;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.textview.MaterialTextView;

/**
 * 创建时间：2025年11月25日.
 * <p>
 * 描述：材质文本视图
 * @author dxyc
 */
public class 材质文本视图 extends MaterialTextView {

    public 材质文本视图(@NonNull Context 上下文) {
        super(上下文);
    }

    public 材质文本视图(@NonNull Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 材质文本视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 材质文本视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }

    public void 置文本外观(@NonNull Context 上下文, int 资源Id) {
        this.setTextAppearance(上下文, 资源Id);
    }




}
