@file:Suppress("DEPRECATION")

package 自定义.bar

import android.database.ContentObserver
import android.graphics.Color
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.FloatRange

/**
 * 沉浸式参数信息
 * Created by geyifeng on 2017/5/9.
 */
class BarParams : Cloneable {
    @JvmField
    @ColorInt
    var statusBarColor: Int = Color.TRANSPARENT //状态栏颜色

    @JvmField
    @ColorInt
    var navigationBarColor: Int = Color.BLACK //导航栏颜色

    @JvmField
    @FloatRange(from = 0.0, to = 1.0)
    var statusBarAlpha: Float = 0.0f //状态栏透明度
    @JvmField
    var fullScreen: Boolean = false //有导航栏的情况，全屏显示
    @JvmField
    var fullScreenTemp: Boolean = false
    @JvmField
    var barHide: BarHide = BarHide.FLAG_SHOW_BAR //隐藏Bar
    @JvmField
    var darkFont: Boolean = false //状态栏字体深色与亮色标志位
    @JvmField
    var navigationBarDarkFont: Boolean = false //导航栏字体深色与亮色标志位
    @JvmField
    var statusBarFlag: Boolean = true //是否可以修改状态栏颜色

    @JvmField
    @ColorInt
    var statusBarColorTransform: Int = Color.BLACK //状态栏变换后的颜色

    @JvmField
    @ColorInt
    var navigationBarColorTransform: Int = Color.BLACK //导航栏变换后的颜色
    @JvmField
    var viewMap: MutableMap<View?, MutableMap<Int?, Int?>?> =
        HashMap<View?, MutableMap<Int?, Int?>?>() //支持view变色

    @JvmField
    @FloatRange(from = 0.0, to = 1.0)
    var viewAlpha: Float = 0.0f
    @JvmField
    var fits: Boolean = false //解决标题栏与状态栏重叠问题

    @JvmField
    @ColorInt
    var statusBarColorContentView: Int = Color.TRANSPARENT

    @JvmField
    @ColorInt
    var statusBarColorContentViewTransform: Int = Color.BLACK

    @JvmField
    @FloatRange(from = 0.0, to = 1.0)
    var statusBarContentViewAlpha: Float = 0.0f
    @JvmField
    var navigationBarColorTemp: Int = navigationBarColor
    @JvmField
    var statusBarView: View? = null //4.4自定义一个状态栏
    @JvmField
    var navigationBarView: View? = null //4.4自定义一个导航栏
    @JvmField
    var statusBarViewByHeight: View? = null //解决标题栏与状态栏重叠问题

    @JvmField
    @ColorInt
    var flymeOSStatusBarFontColor: Int = 0 //flymeOS状态栏字体变色
    @JvmField
    var isSupportActionBar: Boolean = false //结合actionBar使用
    @JvmField
    var titleBarView: View? = null //标题栏view
    @JvmField
    var titleBarHeight: Int = 0 //标题栏的高度
    @JvmField
    var titleBarPaddingTopHeight: Int = 0 //标题栏的paddingTop高度
    @JvmField
    var titleBarViewMarginTop: View? = null //使用margin来修正标题栏位置
    @JvmField
    var titleBarViewMarginTopFlag: Boolean = false //标题栏标识，保证只执行一次
    @JvmField
    var keyboardEnable: Boolean = false //解决软键盘与输入框冲突问题
    @JvmField
    var keyboardMode: Int = (WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
            or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE) //软键盘属性
    @JvmField
    var navigationBarEnable: Boolean = true //是否能修改导航栏颜色
    @JvmField
    var navigationBarWithKitkatEnable: Boolean = true //是否能修改4.4手机导航栏颜色

    @Deprecated("")
    var fixMarginAtBottom: Boolean = false //解决出现底部多余导航栏高度，默认为false
    @JvmField
    var systemWindows: Boolean = false //也没是否使用fitsSystemWindows属性
    @JvmField
    var keyboardPatch: KeyboardPatch? = null //软键盘监听类
    @JvmField
    var onKeyboardListener: OnKeyboardListener? = null //软键盘监听类
    @JvmField
    var navigationStatusObserver: ContentObserver? = null //emui3.1监听器
    @JvmField
    var navigationBarDivider: Boolean = true //显示导航栏Divider

    @JvmField
    @FloatRange(from = 0.0, to = 1.0)
    var navigationBarAlpha: Float = 0.0f //导航栏透明度

    public override fun clone(): BarParams {
        var barParams: BarParams? = null
        try {
            barParams = super.clone() as BarParams
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return barParams!!
    }
}
