package 安卓.应用;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：对话框
 * @author dxyc
 */

public class 对话框 extends Dialog {

    public 对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 对话框(@NonNull Context 上下文, boolean 可取消, @Nullable OnCancelListener 取消监听器) {
        super(上下文, 可取消, 取消监听器);
    }

    public 对话框(@NonNull Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }


}
