package 商业.谷歌.安卓.材质.日期选择器;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.datepicker.MaterialStyledDatePickerDialog;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：材质样式日期选择对话框
 * @author dxyc
 */
@SuppressLint("RestrictedApi")
public class 材质样式日期选择对话框 extends MaterialStyledDatePickerDialog {

    public 材质样式日期选择对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 材质样式日期选择对话框(@NonNull Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }

    public 材质样式日期选择对话框(@NonNull Context 上下文, @Nullable OnDateSetListener 监听器, int 年, int 月, int 月中日) {
        super(上下文, 监听器, 年, 月, 月中日);
    }

    public 材质样式日期选择对话框(@NonNull Context 上下文, int 主题资源Id, @Nullable OnDateSetListener 监听器, int 年, int 年中月, int 月中日) {
        super(上下文, 主题资源Id, 监听器, 年, 年中月, 月中日);
    }


}
