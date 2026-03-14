package 安卓.视图;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * 创建时间：2025年11月18日.
 * <p>
 * 描述：视图组
 * @author dxyc
 */
public abstract class 视图组 extends ViewGroup {

    public 视图组(Context 上下文) {
        super(上下文);
    }

    public 视图组(Context 上下文, AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 视图组(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 视图组(Context 上下文, AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }

//    /**
//     * 版本：0.1.0
//     *
//     * 布局参数。
//     */
//    class 布局参数 : ViewGroup.LayoutParams {
//        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs)
//        constructor(source: LayoutParams?) : super(source)
//        constructor(width: Int, height: Int) : super(width, height)
//    }
//
//    /**
//     * 版本：0.1.0
//     *
//     * 外边框布局参数。
//     */
//    class 外边框布局参数 : ViewGroup.MarginLayoutParams {
//        constructor(c: Context?, attrs: AttributeSet?) : super(c, attrs)
//        constructor(source: LayoutParams?) : super(source)
//        constructor(source: MarginLayoutParams?) : super(source)
//        constructor(width: Int, height: Int) : super(width, height)
//    }


}
