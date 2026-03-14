package 安卓x.视图分页器2.适配器;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

/**
 * 创建时间：2025年11月26日.
 * <p>
 * 描述：碎片状态适配器
 * @author dxyc
 */
public abstract class 碎片状态适配器 extends FragmentStateAdapter {

    public 碎片状态适配器(@NonNull FragmentActivity 碎片活动) {
        super(碎片活动);
    }

    public 碎片状态适配器(@NonNull Fragment 碎片) {
        super(碎片);
    }

    public 碎片状态适配器(@NonNull FragmentManager 碎片管理器, @NonNull Lifecycle 生命周期) {
        super(碎片管理器, 生命周期);
    }


//    override fun createFragment(position: Int): Fragment {
//        return 创建碎片(position)
//    }
//
//
//    override fun getItemCount(): Int {
//        return 取项目数量()
//    }
//
//    /**
//     * 创建时间：2025年11月27日.
//     *
//     * 描述：创建碎片
//     * @param 索引 索引
//     * @return 碎片
//     */
//    open fun 创建碎片(索引: Int): Fragment = Fragment()
//
//    /**
//     * 创建时间：2025年11月27日.
//     *
//     * 描述：取项目数量
//     * @return 项目数量
//     */
//    open fun 取项目数量(): Int = 0



}
