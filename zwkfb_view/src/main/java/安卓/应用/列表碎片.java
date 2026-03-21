package 安卓.应用;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 创建时间：2025年12月2日.
 * <p>
 * 描述：列表碎片
 * @author dxyc
 */
public class 列表碎片 extends ListFragment {

    public 列表碎片() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.创建视图(inflater, container, savedInstanceState);
    }

    public View 创建视图(LayoutInflater 填充器, ViewGroup 容器, Bundle 已保存实例状态) {
        return super.onCreateView(填充器, 容器, 已保存实例状态);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.视图创建(view, savedInstanceState);
    }

    public void 视图创建(View 视图, Bundle 已保存实例状态) {
        super.onViewCreated(视图, 已保存实例状态);
    }


    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroyView() {
        this.销毁视图();
    }

    public void 销毁视图() {
        super.onDestroyView();
    }

    public void 列表项目单击回调(ListView 列表视图, View 视图, int 位置, long id) {
        this.onListItemClick(列表视图, 视图, 位置, id);
    }

    public void 置列表适配器(ListAdapter 适配器) {
        this.setListAdapter(适配器);
    }

    public void 置选择(int 位置) {
        this.setSelection(位置);
    }



    public int 取选择项目位置() {
        return this.getSelectedItemPosition();
    }


    public long 取选择项目Id() {
        return this.getSelectedItemId();
    }

    public ListView 取列表视图() {
        return this.getListView();
    }


    public void 置为空文本(CharSequence 文本) {
        this.setEmptyText(文本);
    }

    public void 置列表显示(boolean 显示) {
        this.setListShown(显示);
    }

    public void 置列表显示无动画(boolean 显示) {
        this.setListShownNoAnimation(显示);
    }


    public ListAdapter 取列表适配器() {
        return this.getListAdapter();
    }


}
