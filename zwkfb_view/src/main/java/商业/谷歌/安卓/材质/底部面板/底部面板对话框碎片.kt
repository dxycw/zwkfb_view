package 商业.谷歌.安卓.材质.底部面板

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


//override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//    return this.创建对话框回调(savedInstanceState)
//}
//
//override fun dismiss() {
//    this.关闭()
//}
//
//override fun dismissAllowingStateLoss() {
//    this.关闭允许状态丢失()
//}
//
//open fun 创建对话框回调(已保存实例状态: Bundle?) : Dialog {
//    return super.onCreateDialog(已保存实例状态)
//}
//
//open fun 关闭()  {
//    super.dismiss()
//}
//
//open fun 关闭允许状态丢失()  {
//    super.dismissAllowingStateLoss()
//}


//======================================================================

fun BottomSheetDialogFragment.关闭()  {
    this.dismiss()
}

//======================================================================

fun BottomSheetDialogFragment.关闭允许状态丢失()  {
    this.dismissAllowingStateLoss()
}

//======================================================================