package 安卓.应用;

import android.app.DatePickerDialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：日期选择对话框
 * @author dxyc
 */
public class 日期选择对话框 extends DatePickerDialog {

    public 日期选择对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 日期选择对话框(@NonNull Context 上下文, @Nullable OnDateSetListener 监听器, int 年, int 月, int 月中天) {
        super(上下文, 监听器, 年, 月, 月中天);
    }

    public 日期选择对话框(@NonNull Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }

    public 日期选择对话框(@NonNull Context 上下文, int 主题资源Id, @Nullable OnDateSetListener 监听器, int 年, int 年中月, int 月中天) {
        super(上下文, 主题资源Id, 监听器, 年, 年中月, 月中天);
    }



}
