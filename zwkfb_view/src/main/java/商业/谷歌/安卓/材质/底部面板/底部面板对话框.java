package 商业.谷歌.安卓.材质.底部面板;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetDialog;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：底部面板对话框
 * @author dxyc
 */
public class 底部面板对话框 extends BottomSheetDialog {

    public 底部面板对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    public 底部面板对话框(@NonNull Context 上下文, int 主题) {
        super(上下文, 主题);
    }

    public 底部面板对话框(@NonNull Context 上下文, boolean 可取消, OnCancelListener 取消监听器) {
        super(上下文, 可取消, 取消监听器);
    }


}
