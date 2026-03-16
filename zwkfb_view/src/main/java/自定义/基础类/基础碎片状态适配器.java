package 自定义.基础类;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import java.util.ArrayList;

import 安卓x.视图分页器2.适配器.碎片状态适配器;

public class 基础碎片状态适配器 extends 碎片状态适配器 {

    public 基础碎片状态适配器(@NonNull FragmentActivity 碎片活动) {
        super(碎片活动);
    }

    public 基础碎片状态适配器(@NonNull Fragment 碎片) {
        super(碎片);
    }

    public 基础碎片状态适配器(@NonNull FragmentManager 碎片管理器, @NonNull Lifecycle 生命周期) {
        super(碎片管理器, 生命周期);
    }



    private ArrayList<Fragment> 数据 = new ArrayList<>();

    public 基础碎片状态适配器(@NonNull FragmentActivity 碎片活动, ArrayList<Fragment> 数据){
        super(碎片活动);
        this.数据 = 数据;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (数据 != null){
            if (position >= 0 && position < 数据.size()){
                return 数据.get(position);
            }else {
                return 数据.get(0);
            }
        }
        return new Fragment();
    }


    @Override
    public int getItemCount() {
        return 数据.size();
    }



}
