package 安卓.视图;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * 创建时间：2025年11月26日.
 * <p>
 * 描述：布局解析器
 * @author dxyc
 */
public abstract class 布局解析器 extends LayoutInflater {

    protected 布局解析器(Context 上下文) {
        super(上下文);
    }

    protected 布局解析器(LayoutInflater 原始的, Context 新上下文) {
        super(原始的, 新上下文);
    }


}
