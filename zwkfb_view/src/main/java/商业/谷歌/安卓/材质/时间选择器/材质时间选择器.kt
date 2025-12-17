package 商业.谷歌.安卓.材质.时间选择器

/**
 * 创建时间：2025年12月2日.
 *
 * 描述：材质时间选择器
 *
 * 版本：0.1.5
 * @author dxyc
 */
class 材质时间选择器 {

    open class 构建器 : MaterialTimePicker.Builder{
        constructor()

        fun 构建(): MaterialTimePicker = this.build()

    }
}

//fun MaterialTimePicker.Builder.构建(): MaterialTimePicker = this.build()
//
//fun MaterialTimePicker.显示(管理器: FragmentManager, 标签: String){
//    this.show(管理器, 标签)
//}
//
//fun MaterialTimePicker.显示(事务: FragmentTransaction, 标签: String){
//    this.show(事务, 标签)
//}
//
//fun MaterialTimePicker.立即显示(管理器: FragmentManager, 标签: String){
//    this.showNow(管理器, 标签)
//}
