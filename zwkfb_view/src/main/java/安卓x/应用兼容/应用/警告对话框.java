package 安卓x.应用兼容.应用;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：警告对话框
 * @author dxyc
 */
public class 警告对话框 extends AlertDialog {

    protected 警告对话框(@NonNull Context 上下文) {
        super(上下文);
    }

    protected 警告对话框(@NonNull Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }

    protected 警告对话框(@NonNull Context 上下文, boolean 可取消, @Nullable OnCancelListener 取消监听器) {
        super(上下文, 可取消, 取消监听器);
    }

    /**
     * 描述：构建器
     */
    public static class 构建器 extends AlertDialog.Builder{

        public 构建器(@NonNull Context 上下文) {
            super(上下文);
        }

        public 构建器(@NonNull Context 上下文, int 主题资源Id) {
            super(上下文, 主题资源Id);
        }


    }








}
