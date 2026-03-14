package 安卓x.回收视图.组件;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.SortedList;

/**
 * 创建时间：2025年12月12日.
 * <p>
 * 描述：排序列表
 * @author dxyc
 */
public class 排序列表<T> extends SortedList<T> {

    public 排序列表(@NonNull Class<T> 类, @NonNull Callback<T> 回调) {
        super(类, 回调);
    }

    public 排序列表(@NonNull Class<T> 类, @NonNull Callback<T> 回调, int 初始容量) {
        super(类, 回调, 初始容量);
    }


}
