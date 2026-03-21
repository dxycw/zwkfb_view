package 安卓.应用

import android.app.ListFragment
import android.view.View
import android.widget.ListAdapter
import android.widget.ListView


fun ListFragment.列表项目单击回调(列表视图: ListView?, 视图: View?, 位置: Int, id: Long) {
    this.onListItemClick(列表视图, 视图, 位置, id)
}

fun ListFragment.置列表适配器(适配器: ListAdapter?) {
    this.setListAdapter(适配器)
}

fun ListFragment.置选择(位置: Int) {
    this.setSelection(位置)
}

fun ListFragment.取选择项目位置(): Int {
    return this.getSelectedItemPosition()
}


fun ListFragment.取选择项目Id(): Long {
    return this.getSelectedItemId()
}

fun ListFragment.取列表视图(): ListView? {
    return this.getListView()
}


fun ListFragment.置为空文本(文本: CharSequence?) {
    this.setEmptyText(文本)
}

fun ListFragment.置列表显示(显示: Boolean) {
    this.setListShown(显示)
}

fun ListFragment.置列表显示无动画(显示: Boolean) {
    this.setListShownNoAnimation(显示)
}


fun ListFragment.取列表适配器(): ListAdapter? {
    return this.getListAdapter()
}