package 安卓x.应用兼容.应用;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialog;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：应用兼容对话框
 * @author dxyc
 */
public class 应用兼容对话框 extends AppCompatDialog {

    public 应用兼容对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 应用兼容对话框(@NonNull Context 上下文, int 主题) {
        super(上下文, 主题);
    }

    public 应用兼容对话框(@NonNull Context 上下文, boolean 可取消, @Nullable OnCancelListener 取消监听器) {
        super(上下文, 可取消, 取消监听器);
    }


}
