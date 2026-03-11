package 安卓.组件;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * 创建时间：2025年11月18日.
 * <p>
 * 描述：图像视图
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
public class 图像视图 extends ImageView {

    public 图像视图(Context 上下文) {
        super(上下文);
    }

    public 图像视图(Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 图像视图(Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 图像视图(Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }


}
