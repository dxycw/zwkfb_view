package 安卓.应用

import android.app.Dialog
import android.app.DialogFragment
import android.app.FragmentManager
import android.app.FragmentTransaction
import java.io.FileDescriptor
import java.io.PrintWriter


fun DialogFragment.置样式(样式: Int, 主题: Int) {
    this.setStyle(样式, 主题)
}

fun DialogFragment.显示(管理器: FragmentManager?, 标签: String?) {
    this.show(管理器, 标签)
}

fun DialogFragment.显示(事务: FragmentTransaction?, 标签: String?): Int {
    return this.show(事务, 标签)
}

fun DialogFragment.关闭() {
    this.dismiss()
}

fun DialogFragment.关闭允许状态丢失() {
    this.dismissAllowingStateLoss()
}


fun DialogFragment.取对话框(): Dialog? {
    return this.getDialog()
}

fun DialogFragment.取主题(): Int {
    return this.getTheme()
}


fun DialogFragment.置可取(可取消: Boolean) {
    this.setCancelable(可取消)
}

fun DialogFragment.是否可取消(): Boolean {
    return this.isCancelable()
}


fun DialogFragment.置显示对话框(显示对话框: Boolean) {
    this.setShowsDialog(显示对话框)
}

fun DialogFragment.取显示对话框(): Boolean {
    return this.getShowsDialog()
}

fun DialogFragment.转储(前缀: String?, 文件描述: FileDescriptor?, 写入器: PrintWriter?, 参数: Array<String?>?) {
    this.dump(前缀, 文件描述, 写入器, 参数)
}