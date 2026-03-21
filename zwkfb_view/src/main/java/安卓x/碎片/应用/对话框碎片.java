package 安卓x.碎片.应用;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.DialogFragment;

/**
 * 创建时间：2025年11月27日.
 * <p>
 * 描述：对话框碎片
 * @author dxyc
 */
public class 对话框碎片 extends DialogFragment {

    public 对话框碎片() {
        super();
    }

    public 对话框碎片(int 内容布局Id) {
        super(内容布局Id);
    }


    public void 显示(FragmentManager 管理器, String 标记) {
        this.show(管理器, 标记);
    }

    public int 显示(FragmentTransaction 事务, String 标记) {
        return show(事务, 标记);
    }

}
