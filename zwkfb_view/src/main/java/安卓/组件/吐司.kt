@file:Suppress("DEPRECATION")

package 安卓.组件

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：吐司
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 吐司 :Toast {
    constructor(context: Context?) : super(context)
    companion object{
        /**
         * 创建时间：2025年11月24日.
         *
         * 版本：0.1.0
         *
         * 短
         */
        const val LENGTH_SHORT = Toast.LENGTH_SHORT
        /**
         * 创建时间：2025年11月24日.
         *
         * 版本：0.1.0
         *
         * 长
         */
        const val LENGTH_LONG = Toast.LENGTH_LONG

        /**
         * 创建时间：2025年11月24日.
         *
         * 版本：0.1.0
         *
         * 短
         */
        const val 短 = Toast.LENGTH_SHORT
        /**
         * 创建时间：2025年11月24日.
         *
         * 版本：0.1.0
         *
         * 长
         */
        const val 长 = Toast.LENGTH_LONG

        /**
         * 创建时间：2025年11月24日.
         *
         * 版本：0.1.0
         *
         * 制作文本
         */
        fun 制作文本(上下文: Context, 资源Id: Int, 持续时间: Int) : Toast =
            makeText(上下文,资源Id,持续时间)

        /**
         * 创建时间：2025年11月24日.
         *
         * 版本：0.1.0
         *
         * 制作文本
         */
        fun 制作文本(上下文: Context, 文本: CharSequence, 持续时间: Int) : Toast =
            makeText(上下文,文本,持续时间)


//        fun 自定义信息提示(上下文: Context, 资源Id: Int, 显示时间: Int = 短): Toast {
//            val 吐司对话框 = Toast(上下文)
//            val 视图内容 = 线性布局(上下文).apply {
//                gravity = Gravity.CENTER
//                outlineProvider = object : ViewOutlineProvider() {
//                    override fun getOutline(view: View?, outline: Outline?) {
//                        outline?.setRoundRect(0, 0, view?.width!!,
//                            view.height, 30f) //圆角
//                    }
//                }
//                elevation = 10f //阴影
//                minimumHeight = 50 //最小高度
//                minimumWidth = 100
//                置背景颜色(if(!上下文.是否是深色模式) "#FFFFFF".toColorInt() else "#343434".toColorInt())
//                clipToOutline = true //圆角
//            }
//            val 文本 = 文本视图(上下文).apply {
//                文本 = 资源Id.toString()  //文本内容
//                gravity = Gravity.CENTER
//                置文本颜色(if(!上下文.是否是深色模式) Color.BLACK else Color.WHITE)
//                setPadding(30, 10, 30, 10) //内边距
//                ellipsize = TextUtils.TruncateAt.END  //显示省略号
//            }
//            视图内容.添加视图(文本, 线性布局.布局参数(-1, -1))
//            吐司对话框.置视图(视图内容)
//            吐司对话框.置持续时间(显示时间)
//            return 吐司对话框
//        }
//
//        fun 自定义信息提示(上下文: Context, 内容: String, 显示时间: Int = 短): Toast {
//            val 吐司对话框 = Toast(上下文)
//            val 视图内容 = 线性布局(上下文).apply {
//                gravity = Gravity.CENTER
//                outlineProvider = object : ViewOutlineProvider() {
//                    override fun getOutline(view: View?, outline: Outline?) {
//                        outline?.setRoundRect(0, 0, view?.width!!,
//                            view.height, 30f) //圆角
//                    }
//                }
//                elevation = 10f //阴影
//                minimumHeight = 50 //最小高度
//                minimumWidth = 100
//                置背景颜色(if(!上下文.是否是深色模式) "#FFFFFF".toColorInt() else "#343434".toColorInt())
//                clipToOutline = true //圆角
//            }
//            val 文本 = 文本视图(上下文).apply {
//                文本 = 内容  //文本内容
//                gravity = Gravity.CENTER
//                置文本颜色(if(!上下文.是否是深色模式) Color.BLACK else Color.WHITE)
//                setPadding(30, 10, 30, 10) //内边距
//                ellipsize = TextUtils.TruncateAt.END  //显示省略号
//            }
//            视图内容.添加视图(文本, 线性布局.布局参数(-1, -1))
//            吐司对话框.置视图(视图内容)
//            吐司对话框.置持续时间(显示时间)
//            return 吐司对话框
//        }

    }
}

//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 视图
 */
var Toast.视图: View?
    get() = view
    set(视图) { view = 视图 }

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 取视图
 */
fun Toast.取视图(): View? = getView()

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 置视图
 */
fun Toast.置视图(视图: View) = setView(视图)

//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 置文本
 */
fun Toast.置文本(资源Id: Int) = setText(资源Id)

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 置文本
 */
fun Toast.置文本(文本: CharSequence) = setText(文本)


//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 持续时间
 */
var Toast.持续时间: Int
    get() = duration
    set(持续时间) { duration = 持续时间  }
/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 取持续时间
 */
fun Toast.取持续时间(): Int = getDuration()
/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 置持续时间
 */
fun Toast.置持续时间(持续时间: Int) = setDuration(持续时间)

//======================================================================

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 取消
 */
fun Toast.取消() = cancel()

/**
 * 创建时间：2025年11月24日.
 *
 * 版本：0.1.0
 *
 * 显示
 */
fun Toast.显示() = show()

//======================================================================

