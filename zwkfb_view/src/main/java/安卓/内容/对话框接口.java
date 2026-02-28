package 安卓.内容;

import android.content.DialogInterface;
import android.view.KeyEvent;

/**
 * 创建时间：2025年12月4日.
 * <p>
 * 描述：对话框接口
 * @author dxyc
 */
public interface 对话框接口 extends DialogInterface {

    /** 正按钮的标识符。 */
    int 按钮_确定 = DialogInterface.BUTTON_POSITIVE;

    /** 否定按钮的标识符。 */
    int 按钮_取消 = DialogInterface.BUTTON_NEGATIVE;

    /** 中性按钮的标识符。 */
    int 按钮_忽略 = DialogInterface.BUTTON_NEUTRAL;

    /** @deprecated 使用 {@link #按钮_确定} */
    @Deprecated
    int 按钮1 = DialogInterface.BUTTON1;

    /** @deprecated 使用 {@link #按钮_取消} */
    @Deprecated
    int 按钮2 = DialogInterface.BUTTON2;

    /** @deprecated 使用 {@link #按钮_忽略} */
    @Deprecated
    int 按钮3 = DialogInterface.BUTTON3;


    @Override
    default void cancel(){
        取消();
    }

    @Override
    default void dismiss(){
        关闭();
    }

    default void 取消(){}

    default void 关闭(){}


    interface 取消监听器回调 extends DialogInterface.OnCancelListener{
        @Override
        default void onCancel(DialogInterface dialog){
            取消回调(dialog);
        }
        void 取消回调(DialogInterface 对话框);
    }

    interface 关闭监听器回调 extends DialogInterface.OnDismissListener{
        @Override
        default void onDismiss(DialogInterface dialog){
            关闭回调(dialog);
        }

        void 关闭回调(DialogInterface 对话框);
    }

    interface 显示监听器回调 extends DialogInterface.OnShowListener{
        @Override
        default void onShow(DialogInterface dialog){
            显示回调(dialog);
        }

        void 显示回调(DialogInterface 对话框);
    }

    interface 单击监听器回调 extends DialogInterface.OnClickListener{
        @Override
        default void onClick(DialogInterface dialog, int which){
            单击回调(dialog, which);
        }
        void 单击回调(DialogInterface 对话框, int 哪一个);
    }

    interface 多选单击监听器回调 extends DialogInterface.OnMultiChoiceClickListener{
        @Override
        default void onClick(DialogInterface dialog, int which, boolean isChecked){
            单击回调(dialog, which, isChecked);
        }

        void 单击回调(DialogInterface 对话框, int 哪一个, boolean 是否选中);
    }

    interface 按键监听器回调 extends DialogInterface.OnKeyListener{
        @Override
        default boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event){
            return 按键回调(dialog, keyCode, event);
        }

        boolean 按键回调(DialogInterface 对话框, int 键码, KeyEvent 事件);
    }

}
