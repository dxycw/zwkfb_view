package 安卓.应用;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.ListView;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：警告对话框
 * @author dxyc
 */
public class 警告对话框 extends AlertDialog {

    protected 警告对话框(Context 上下文) {
        super(上下文);
    }

    protected 警告对话框(Context 上下文, boolean 可取消, OnCancelListener 取消监听器) {
        super(上下文, 可取消, 取消监听器);
    }

    protected 警告对话框(Context 上下文, int 主题资源Id) {
        super(上下文, 主题资源Id);
    }

    //==============================================================================================

//    /**
//     * 描述：取按钮
//     */
//    public Button 取按钮(int 哪个按钮) {
//        return this.getButton(哪个按钮);
//    }
//
//    /**
//     * 描述：取列表视图
//     */
//    public ListView 取列表视图() {
//        return this.getListView();
//    }
//
//    /**
//     * 描述：设置警告对话框标题
//     */
//    public void 置标题(CharSequence 标题) {
//        this.setTitle(标题);
//    }


    public static class 构建器 extends AlertDialog.Builder {

        public 构建器(Context 上下文) {
            super(上下文);
        }

        public 构建器(Context 上下文, int 主题资源Id) {
            super(上下文, 主题资源Id);
        }


    }


}
