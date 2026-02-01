@file:Suppress("DEPRECATION")

package 自定义.bar

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.database.ContentObserver
import android.graphics.Color
import android.os.Build
import android.os.Handler
import android.provider.Settings
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.view.Window
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.FloatRange
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import java.lang.ref.WeakReference
import kotlin.math.abs
import androidx.core.graphics.toColorInt

import androidx.core.view.size

/**
 * android 4.4以上沉浸式以及bar的管理
 * Created by gyf on 2017/05/09.
 */
class ImmersionBar {
    private var mActivity: Activity?
    private var mWindow: Window?
    private var mDecorView: ViewGroup? = null
    private var mContentView: ViewGroup? = null
    private var mDialog: Dialog? = null

    /**
     * 获取 bar 参数。
     * @return 条形参数
     */
    var barParams: BarParams? = null
        private set
    private var mConfig: BarConfig? = null

    private val mActivityName: String?
    private var mFragmentName: String? = null
    private val mImmersionBarName: String?

    /**
     * 在Activit里初始化
     * Instantiates a new Immersion bar.
     *
     * @param activity the activity
     */
    private constructor(activity: Activity) {
        val activityWeakReference = WeakReference<Activity?>(activity)
        mActivity = activityWeakReference.get()
        mWindow = mActivity!!.window
        mActivityName = activity.javaClass.getName()
        mImmersionBarName = mActivityName
        initParams()
    }

    /**
     * 在Fragment里初始化
     * Instantiates a new Immersion bar.
     *
     * @param fragment the fragment
     */
    private constructor(fragment: Fragment) : this(fragment.requireActivity(), fragment)

    private constructor(activity: Activity, fragment: Fragment?) {
        requireNotNull(activity) { "Activity不能为空!!!" }
        val activityWeakReference = WeakReference<Activity?>(activity)
        val fragmentWeakReference = WeakReference<Fragment?>(fragment)
        mActivity = activityWeakReference.get()
        mWindow = mActivity!!.window
        mActivityName = mActivity!!.javaClass.getName()
        mFragmentName = mActivityName + "_AND_" + fragmentWeakReference.get()!!.javaClass.getName()
        mImmersionBarName = mFragmentName
        initParams()
    }

    private constructor(dialogFragment: DialogFragment?, dialog: Dialog?) {
        val dialogFragmentWeakReference = WeakReference<DialogFragment?>(dialogFragment)
        val dialogWeakReference = WeakReference<Dialog?>(dialog)
        mActivity = dialogFragmentWeakReference.get()!!.activity
        mDialog = dialogWeakReference.get()
        mWindow = mDialog!!.window
        mActivityName = mActivity!!.javaClass.getName()
        mImmersionBarName =
            mActivityName + "_AND_" + dialogFragmentWeakReference.get()!!.javaClass.getName()
        initParams()
    }

    /**
     * 在Dialog里初始化
     * Instantiates a new Immersion bar.
     *
     * @param activity  the activity
     * @param dialog    the dialog
     * @param dialogTag the dialog tag  dialog标识，不能为空
     */
    private constructor(activity: Activity?, dialog: Dialog?, dialogTag: String?) {
        val activityWeakReference = WeakReference<Activity?>(activity)
        val dialogWeakReference = WeakReference<Dialog?>(dialog)
        mActivity = activityWeakReference.get()
        mDialog = dialogWeakReference.get()
        mWindow = mDialog!!.window
        mActivityName = mActivity!!.javaClass.getName()
        mImmersionBarName = mActivityName + "_AND_" + dialogTag
        initParams()
    }

    /**
     * 初始化沉浸式默认参数
     * Init params.
     */
    private fun initParams() {
        mDecorView = mWindow!!.decorView as ViewGroup
        mContentView = mDecorView!!.findViewById<View?>(android.R.id.content) as ViewGroup?
        mConfig = BarConfig(mActivity!!)
        if (mMap[mImmersionBarName] == null) {
            this.barParams = BarParams()
            if (!isEmpty(mFragmentName)) { //保证一个activity页面有同一个状态栏view和导航栏view
                requireNotNull(mMap[mActivityName]) { "在Fragment里使用时，请先在加载Fragment的Activity里初始化！！！" }
                if (false || OSUtils.isEMUI3_1) {
                    barParams!!.statusBarView = mMap.get(mActivityName)!!.statusBarView
                    barParams!!.navigationBarView = mMap.get(mActivityName)!!.navigationBarView
                }
                barParams!!.keyboardPatch = mMap.get(mActivityName)!!.keyboardPatch
            }
            mMap.put(mImmersionBarName, this.barParams)
        } else {
            this.barParams = mMap[mImmersionBarName]
        }
    }

    /**
     * 透明状态栏，默认透明
     *
     * @return the immersion bar
     */
    fun transparentStatusBar(): ImmersionBar {
        barParams!!.statusBarColor = Color.TRANSPARENT
        return this
    }

    /**
     * 透明导航栏，默认黑色
     *
     * @return the immersion bar
     */
    fun transparentNavigationBar(): ImmersionBar {
        barParams!!.navigationBarColor = Color.TRANSPARENT
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        barParams!!.fullScreen = true
        return this
    }

    /**
     * 透明状态栏和导航栏
     *
     * @return the immersion bar
     */
    fun transparentBar(): ImmersionBar {
        barParams!!.statusBarColor = Color.TRANSPARENT
        barParams!!.navigationBarColor = Color.TRANSPARENT
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        barParams!!.fullScreen = true
        return this
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    fun statusBarColor(@ColorRes statusBarColor: Int): ImmersionBar {
        return this.statusBarColorInt(ContextCompat.getColor(mActivity!!, statusBarColor))
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param alpha          the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        @ColorRes statusBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(ContextCompat.getColor(mActivity!!, statusBarColor), alpha)
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor          状态栏颜色，资源文件（R.color.xxx）
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha                   the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        @ColorRes statusBarColor: Int,
        @ColorRes statusBarColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(
            ContextCompat.getColor(mActivity!!, statusBarColor),
            ContextCompat.getColor(mActivity!!, statusBarColorTransform),
            alpha
        )
    }

    /**
     * 状态栏颜色
     * Status bar color int immersion bar.
     *
     * @param statusBarColor the status bar color
     * @return the immersion bar
     */
    fun statusBarColor(statusBarColor: String?): ImmersionBar {
        return this.statusBarColorInt(statusBarColor!!.toColorInt())
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色
     * @param alpha          the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        statusBarColor: String?,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(statusBarColor!!.toColorInt(), alpha)
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor          状态栏颜色
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha                   the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColor(
        statusBarColor: String?,
        statusBarColorTransform: String?,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        return this.statusBarColorInt(
            statusBarColor!!.toColorInt(),
            statusBarColorTransform!!.toColorInt(),
            alpha
        )
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    fun statusBarColorInt(@ColorInt statusBarColor: Int): ImmersionBar {
        barParams!!.statusBarColor = statusBarColor
        return this
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor 状态栏颜色，资源文件（R.color.xxx）
     * @param alpha          the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColorInt(
        @ColorInt statusBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = statusBarColor
        barParams!!.statusBarAlpha = alpha
        return this
    }

    /**
     * 状态栏颜色
     *
     * @param statusBarColor          状态栏颜色，资源文件（R.color.xxx）
     * @param statusBarColorTransform the status bar color transform 状态栏变换后的颜色
     * @param alpha                   the alpha  透明度
     * @return the immersion bar
     */
    fun statusBarColorInt(
        @ColorInt statusBarColor: Int,
        @ColorInt statusBarColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) alpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = statusBarColor
        barParams!!.statusBarColorTransform = statusBarColorTransform
        barParams!!.statusBarAlpha = alpha
        return this
    }

    /**
     * 隐藏导航栏分线
     *
     * @return the immersion bar
     */
    fun hideBarDivider(): ImmersionBar {
        barParams!!.navigationBarDivider = false
        return this
    }

    fun showBarDivider(): ImmersionBar {
        barParams!!.navigationBarDivider = true
        return this
    }

    /**
     * 显示导航栏分线
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    fun navigationBarColor(@ColorRes navigationBarColor: Int): ImmersionBar {
        return this.navigationBarColorInt(ContextCompat.getColor(mActivity!!, navigationBarColor))
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @param navigationAlpha    the navigation alpha 透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        @ColorRes navigationBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(
            ContextCompat.getColor(mActivity!!, navigationBarColor),
            navigationAlpha
        )
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor          the navigation bar color 导航栏颜色
     * @param navigationBarColorTransform the navigation bar color transform  导航栏变色后的颜色
     * @param navigationAlpha             the navigation alpha  透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        @ColorRes navigationBarColor: Int,
        @ColorRes navigationBarColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(
            ContextCompat.getColor(mActivity!!, navigationBarColor),
            ContextCompat.getColor(mActivity!!, navigationBarColorTransform), navigationAlpha
        )
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    fun navigationBarColor(navigationBarColor: String?): ImmersionBar {
        return this.navigationBarColorInt(navigationBarColor!!.toColorInt())
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @param navigationAlpha    the navigation alpha 透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        navigationBarColor: String?,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(navigationBarColor!!.toColorInt(), navigationAlpha)
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor          the navigation bar color 导航栏颜色
     * @param navigationBarColorTransform the navigation bar color transform  导航栏变色后的颜色
     * @param navigationAlpha             the navigation alpha  透明度
     * @return the immersion bar
     */
    fun navigationBarColor(
        navigationBarColor: String?,
        navigationBarColorTransform: String?,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        return this.navigationBarColorInt(
            navigationBarColor!!.toColorInt(),
            navigationBarColorTransform!!.toColorInt(), navigationAlpha
        )
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    fun navigationBarColorInt(@ColorInt navigationBarColor: Int): ImmersionBar {
        barParams!!.navigationBarColor = navigationBarColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor the navigation bar color 导航栏颜色
     * @param navigationAlpha    the navigation alpha 透明度
     * @return the immersion bar
     */
    fun navigationBarColorInt(
        @ColorInt navigationBarColor: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        barParams!!.navigationBarColor = navigationBarColor
        barParams!!.navigationBarAlpha = navigationAlpha
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 导航栏颜色
     *
     * @param navigationBarColor          the navigation bar color 导航栏颜色
     * @param navigationBarColorTransform the navigation bar color transform  导航栏变色后的颜色
     * @param navigationAlpha             the navigation alpha  透明度
     * @return the immersion bar
     */
    fun navigationBarColorInt(
        @ColorInt navigationBarColor: Int,
        @ColorInt navigationBarColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float
    ): ImmersionBar {
        barParams!!.navigationBarColor = navigationBarColor
        barParams!!.navigationBarColorTransform = navigationBarColorTransform
        barParams!!.navigationBarAlpha = navigationAlpha
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @return the immersion bar
     */
    fun barColor(@ColorRes barColor: Int): ImmersionBar {
        return this.barColorInt(ContextCompat.getColor(mActivity!!, barColor))
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    fun barColor(
        @ColorRes barColor: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        return this.barColorInt(ContextCompat.getColor(mActivity!!, barColor), barColor.toFloat())
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor          the bar color
     * @param barColorTransform the bar color transform
     * @param barAlpha          the bar alpha
     * @return the immersion bar
     */
    fun barColor(
        @ColorRes barColor: Int,
        @ColorRes barColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        return this.barColorInt(
            ContextCompat.getColor(mActivity!!, barColor),
            ContextCompat.getColor(mActivity!!, barColorTransform), barAlpha
        )
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @return the immersion bar
     */
    fun barColor(barColor: String?): ImmersionBar {
        return this.barColorInt(barColor!!.toColorInt())
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    @SuppressLint("UseKtx")
    fun barColor(barColor: String?, @FloatRange(from = 0.0 ,to = 1.0) barAlpha: Float): ImmersionBar {
        return this.barColorInt(Color.parseColor(barColor), barAlpha)
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor          the bar color
     * @param barColorTransform the bar color transform
     * @param barAlpha          the bar alpha
     * @return the immersion bar
     */
    fun barColor(
        barColor: String?,
        barColorTransform: String?,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        return this.barColorInt(
            barColor!!.toColorInt(),
            barColorTransform!!.toColorInt(),
            barAlpha
        )
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @return the immersion bar
     */
    fun barColorInt(@ColorInt barColor: Int): ImmersionBar {
        barParams!!.statusBarColor = barColor
        barParams!!.navigationBarColor = barColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        return this
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor the bar color
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    fun barColorInt(
        @ColorInt barColor: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = barColor
        barParams!!.navigationBarColor = barColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor
        barParams!!.statusBarAlpha = barAlpha
        barParams!!.navigationBarAlpha = barAlpha
        return this
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param barColor          the bar color
     * @param barColorTransform the bar color transform
     * @param barAlpha          the bar alpha
     * @return the immersion bar
     */
    fun barColorInt(
        @ColorInt barColor: Int,
        @ColorInt barColorTransform: Int,
        @FloatRange(from = 0.0, to = 1.0) barAlpha: Float
    ): ImmersionBar {
        barParams!!.statusBarColor = barColor
        barParams!!.navigationBarColor = barColor
        barParams!!.navigationBarColorTemp = barParams!!.navigationBarColor

        barParams!!.statusBarColorTransform = barColorTransform
        barParams!!.navigationBarColorTransform = barColorTransform

        barParams!!.statusBarAlpha = barAlpha
        barParams!!.navigationBarAlpha = barAlpha
        return this
    }


    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param statusBarColorTransform the status bar color transform
     * @return the immersion bar
     */
    fun statusBarColorTransform(@ColorRes statusBarColorTransform: Int): ImmersionBar {
        return this.statusBarColorTransformInt(
            ContextCompat.getColor(
                mActivity!!,
                statusBarColorTransform
            )
        )
    }

    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param statusBarColorTransform the status bar color transform
     * @return the immersion bar
     */
    fun statusBarColorTransform(statusBarColorTransform: String?): ImmersionBar {
        return this.statusBarColorTransformInt(statusBarColorTransform!!.toColorInt())
    }

    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param statusBarColorTransform the status bar color transform
     * @return the immersion bar
     */
    fun statusBarColorTransformInt(@ColorInt statusBarColorTransform: Int): ImmersionBar {
        barParams!!.statusBarColorTransform = statusBarColorTransform
        return this
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param navigationBarColorTransform the m navigation bar color transform
     * @return the immersion bar
     */
    fun navigationBarColorTransform(@ColorRes navigationBarColorTransform: Int): ImmersionBar {
        return this.navigationBarColorTransformInt(
            ContextCompat.getColor(
                mActivity!!,
                navigationBarColorTransform
            )
        )
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param navigationBarColorTransform the m navigation bar color transform
     * @return the immersion bar
     */
    fun navigationBarColorTransform(navigationBarColorTransform: String?): ImmersionBar {
        return this.navigationBarColorTransformInt(navigationBarColorTransform!!.toColorInt())
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param navigationBarColorTransform the m navigation bar color transform
     * @return the immersion bar
     */
    fun navigationBarColorTransformInt(@ColorInt navigationBarColorTransform: Int): ImmersionBar {
        barParams!!.navigationBarColorTransform = navigationBarColorTransform
        return this
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param barColorTransform the bar color transform
     * @return the immersion bar
     */
    fun barColorTransform(@ColorRes barColorTransform: Int): ImmersionBar {
        return this.barColorTransformInt(ContextCompat.getColor(mActivity!!, barColorTransform))
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param barColorTransform the bar color transform
     * @return the immersion bar
     */
    fun barColorTransform(barColorTransform: String?): ImmersionBar {
        return this.barColorTransformInt(barColorTransform!!.toColorInt())
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param barColorTransform the bar color transform
     * @return the immersion bar
     */
    fun barColorTransformInt(@ColorInt barColorTransform: Int): ImmersionBar {
        barParams!!.statusBarColorTransform = barColorTransform
        barParams!!.navigationBarColorTransform = barColorTransform
        return this
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view the view
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(view: View): ImmersionBar {
        return this.addViewSupportTransformColorInt(view, barParams!!.statusBarColorTransform)
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                    the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(
        view: View,
        @ColorRes viewColorAfterTransform: Int
    ): ImmersionBar {
        return this.addViewSupportTransformColorInt(
            view,
            ContextCompat.getColor(mActivity!!, viewColorAfterTransform)
        )
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                     the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform  the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(
        view: View, @ColorRes viewColorBeforeTransform: Int,
        @ColorRes viewColorAfterTransform: Int
    ): ImmersionBar {
        return this.addViewSupportTransformColorInt(
            view,
            ContextCompat.getColor(mActivity!!, viewColorBeforeTransform),
            ContextCompat.getColor(mActivity!!, viewColorAfterTransform)
        )
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                    the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(view: View, viewColorAfterTransform: String?): ImmersionBar {
        return this.addViewSupportTransformColorInt(view, viewColorAfterTransform!!.toColorInt())
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                     the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform  the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColor(
        view: View, viewColorBeforeTransform: String?,
        viewColorAfterTransform: String?
    ): ImmersionBar {
        return this.addViewSupportTransformColorInt(
            view,
            viewColorBeforeTransform!!.toColorInt(),
            viewColorAfterTransform!!.toColorInt()
        )
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                    the view
     * @param viewColorAfterTransform the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColorInt(
        view: View,
        @ColorInt viewColorAfterTransform: Int
    ): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        val map: MutableMap<Int?, Int?> = HashMap<Int?, Int?>()
        map.put(barParams!!.statusBarColor, viewColorAfterTransform)
        barParams!!.viewMap.put(view, map)
        return this
    }

    /**
     * Add 颜色变换支持View
     *
     * @param view                     the view
     * @param viewColorBeforeTransform the view color before transform
     * @param viewColorAfterTransform  the view color after transform
     * @return the immersion bar
     */
    fun addViewSupportTransformColorInt(
        view: View, @ColorInt viewColorBeforeTransform: Int,
        @ColorInt viewColorAfterTransform: Int
    ): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        @SuppressLint("UseSparseArrays") val map: MutableMap<Int?, Int?> = HashMap<Int?, Int?>()
        map.put(viewColorBeforeTransform, viewColorAfterTransform)
        barParams!!.viewMap.put(view, map)
        return this
    }

    /**
     * view透明度
     * View alpha immersion bar.
     *
     * @param viewAlpha the view alpha
     * @return the immersion bar
     */
    fun viewAlpha(@FloatRange(from = 0.0, to = 1.0) viewAlpha: Float): ImmersionBar {
        barParams!!.viewAlpha = viewAlpha
        return this
    }

    /**
     * Remove support view immersion bar.
     *
     * @param view the view
     * @return the immersion bar
     */
    fun removeSupportView(view: View): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        val map = barParams!!.viewMap[view]
        if (map!!.isNotEmpty()) {
            barParams!!.viewMap.remove(view)
        }
        return this
    }

    /**
     * Remove support all view immersion bar.
     *
     * @return the immersion bar
     */
    fun removeSupportAllView(): ImmersionBar {
        if (barParams!!.viewMap.isNotEmpty()) {
            barParams!!.viewMap.clear()
        }
        return this
    }

    /**
     * 有导航栏的情况下，Activity是否全屏显示
     *
     * @param isFullScreen the is full screen
     * @return the immersion bar
     */
    fun fullScreen(isFullScreen: Boolean): ImmersionBar {
        barParams!!.fullScreen = isFullScreen
        return this
    }

    /**
     * 状态栏透明度
     *
     * @param statusAlpha the status alpha
     * @return the immersion bar
     */
    fun statusBarAlpha(@FloatRange(from = 0.0, to = 1.0) statusAlpha: Float): ImmersionBar {
        barParams!!.statusBarAlpha = statusAlpha
        return this
    }

    /**
     * 导航栏透明度
     *
     * @param navigationAlpha the navigation alpha
     * @return the immersion bar
     */
    fun navigationBarAlpha(@FloatRange(from = 0.0, to = 1.0) navigationAlpha: Float): ImmersionBar {
        barParams!!.navigationBarAlpha = navigationAlpha
        return this
    }

    /**
     * 状态栏和导航栏透明度
     *
     * @param barAlpha the bar alpha
     * @return the immersion bar
     */
    fun barAlpha(@FloatRange(from = 0.0, to = 1.0) barAlpha: Float): ImmersionBar {
        barParams!!.statusBarAlpha = barAlpha
        barParams!!.navigationBarAlpha = barAlpha
        return this
    }

    /**
     * 状态栏字体深色或亮色，判断设备支不支持状态栏变色来设置状态栏透明度
     * Status bar dark font immersion bar.
     *
     * @param isDarkFont  the is dark font
     * @param statusAlpha the status alpha 如果不支持状态栏字体变色可以使用statusAlpha来指定状态栏透明度，比如白色状态栏的时候可以用到
     * @return the immersion bar
     */
    /**
     * 状态栏字体深色或亮色
     *
     * @param isDarkFont true 深色
     * @return the immersion bar
     */
    @JvmOverloads
    fun statusBarDarkFont(
        isDarkFont: Boolean,
        @FloatRange(from = 0.0, to = 1.0) statusAlpha: Float = 0f
    ): ImmersionBar {
        barParams!!.darkFont = isDarkFont
        if (!isDarkFont) barParams!!.flymeOSStatusBarFontColor = 0
        if (isSupportStatusBarDarkFont) {
            barParams!!.statusBarAlpha = 0f
        } else {
            barParams!!.statusBarAlpha = statusAlpha
        }
        return this
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flymeOSStatusBarFontColor the flyme os status bar font color
     * @return the immersion bar
     */
    fun flymeOSStatusBarFontColor(@ColorRes flymeOSStatusBarFontColor: Int): ImmersionBar {
        barParams!!.flymeOSStatusBarFontColor =
            ContextCompat.getColor(mActivity!!, flymeOSStatusBarFontColor)
        return this
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flymeOSStatusBarFontColor the flyme os status bar font color
     * @return the immersion bar
     */
    fun flymeOSStatusBarFontColor(flymeOSStatusBarFontColor: String?): ImmersionBar {
        barParams!!.flymeOSStatusBarFontColor = flymeOSStatusBarFontColor!!.toColorInt()
        return this
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flymeOSStatusBarFontColor the flyme os status bar font color
     * @return the immersion bar
     */
    fun flymeOSStatusBarFontColorInt(@ColorInt flymeOSStatusBarFontColor: Int): ImmersionBar {
        barParams!!.flymeOSStatusBarFontColor = flymeOSStatusBarFontColor
        return this
    }

    /**
     * 导航栏字体深色或亮色，判断设备支不支持状态栏变色来设置状态栏透明度
     * Status bar dark font immersion bar.
     *
     * @param isDarkFont  the is dark font
     * @param statusAlpha the status alpha 如果不支持状态栏字体变色可以使用statusAlpha来指定状态栏透明度，比如白色状态栏的时候可以用到
     * @return the immersion bar
     */
    /**
     * 导航栏字体深色或亮色
     * Status bar dark font immersion bar.
     *
     * @param isDarkFont the is dark font
     * @return the immersion bar
     */
    @JvmOverloads
    fun navigationBarDarkFont(
        isDarkFont: Boolean,
        @FloatRange(from = 0.0, to = 1.0) statusAlpha: Float = 0.3f
    ): ImmersionBar {
        barParams!!.navigationBarDarkFont = isDarkFont
        if (canNavigationBarDarkFont()) {
            barParams!!.navigationBarAlpha = 0f
        } else {
            barParams!!.navigationBarAlpha = statusAlpha
        }
        return this
    }

    /**
     * 隐藏导航栏或状态栏
     *
     * @param barHide the bar hide
     * @return the immersion bar
     */
    fun hideBar(barHide: BarHide): ImmersionBar {
        barParams!!.barHide = barHide
        if (false || OSUtils.isEMUI3_1) {
            if ((barParams!!.barHide == BarHide.FLAG_HIDE_NAVIGATION_BAR) ||
                (barParams!!.barHide == BarHide.FLAG_HIDE_BAR)
            ) {
                barParams!!.navigationBarColor = Color.TRANSPARENT
                barParams!!.fullScreenTemp = true
            } else {
                barParams!!.navigationBarColor = barParams!!.navigationBarColorTemp
                barParams!!.fullScreenTemp = false
            }
        }
        return this
    }

    /**
     * 解决布局与状态栏重叠问题
     *
     * @param fits the fits
     * @return the immersion bar
     */
    fun fitsSystemWindows(fits: Boolean): ImmersionBar {
        barParams!!.fits = fits
        return this
    }

    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows immersion bar.
     *
     * @param fits                               the fits
     * @param statusBarColorContentView          the status bar color content view 状态栏颜色
     * @param statusBarColorContentViewTransform the status bar color content view transform  状态栏变色后的颜色
     * @param statusBarContentViewAlpha          the status bar content view alpha  透明度
     * @return the immersion bar
     */
    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows immersion bar.
     *
     * @param fits                      the fits
     * @param statusBarColorContentView the status bar color content view  状态栏颜色
     * @return the immersion bar
     */
    @JvmOverloads
    fun fitsSystemWindows(
        fits: Boolean,
        @ColorRes statusBarColorContentView: Int,
        @ColorRes statusBarColorContentViewTransform: Int = Color.BLACK,
        @FloatRange(from = 0.0, to = 1.0) statusBarContentViewAlpha: Float = 0f
    ): ImmersionBar {
        barParams!!.fits = fits
        barParams!!.statusBarColorContentView =
            ContextCompat.getColor(mActivity!!, statusBarColorContentView)
        barParams!!.statusBarColorContentViewTransform =
            ContextCompat.getColor(mActivity!!, statusBarColorContentViewTransform)
        barParams!!.statusBarContentViewAlpha = statusBarContentViewAlpha
        barParams!!.statusBarColorContentView =
            ContextCompat.getColor(mActivity!!, statusBarColorContentView)
        mContentView!!.setBackgroundColor(
            ColorUtils.blendARGB(
                barParams!!.statusBarColorContentView,
                barParams!!.statusBarColorContentViewTransform,
                barParams!!.statusBarContentViewAlpha
            )
        )
        return this
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     *
     * @param view the view
     * @return the immersion bar
     */
    fun statusBarView(view: View): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        barParams!!.statusBarViewByHeight = view
        return this
    }

    /**
     * 通过状态栏高度动态设置状态栏布局,只能在Activity中使用
     *
     * @param viewId the view id
     * @return the immersion bar
     */
    fun statusBarView(@IdRes viewId: Int): ImmersionBar {
        val view = mActivity!!.findViewById<View>(viewId)
        requireNotNull(view) { "未找到viewId" }
        return statusBarView(view)
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     * Status bar view immersion bar.
     *
     * @param viewId   the view id
     * @param rootView the root view
     * @return the immersion bar
     */
    fun statusBarView(@IdRes viewId: Int, rootView: View): ImmersionBar {
        val view = rootView.findViewById<View>(viewId)
        requireNotNull(view) { "未找到viewId" }
        return statusBarView(view)
    }

    /**
     * 支持有actionBar的界面,调用该方法，布局讲从actionBar下面开始绘制
     * Support action bar immersion bar.
     *
     * @param isSupportActionBar the is support action bar
     * @return the immersion bar
     */
    fun supportActionBar(isSupportActionBar: Boolean): ImmersionBar {
        barParams!!.isSupportActionBar = isSupportActionBar
        return this
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param view the view
     * @return the immersion bar
     */
    fun titleBar(view: View): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        return titleBar(view, true)
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param view          the view
     * @param statusBarFlag the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    fun titleBar(view: View, statusBarFlag: Boolean): ImmersionBar {
        requireNotNull(view) { "View参数不能为空" }
        barParams!!.titleBarView = view
        barParams!!.statusBarFlag = statusBarFlag
        setTitleBar()
        return this
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，只支持Activity
     * Title bar immersion bar.
     *
     * @param viewId the view id
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int): ImmersionBar {
        val view = mActivity!!.findViewById<View>(viewId)
        requireNotNull(view) { "参数错误" }
        return titleBar(view, true)
    }

    /**
     * Title bar immersion bar.
     *
     * @param viewId        the view id
     * @param statusBarFlag the status bar flag
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int, statusBarFlag: Boolean): ImmersionBar {
        val view = mActivity!!.findViewById<View>(viewId)
        requireNotNull(view) { "参数错误" }
        return titleBar(view, statusBarFlag)
    }

    /**
     * Title bar immersion bar.
     *
     * @param viewId   the view id
     * @param rootView the root view
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int, rootView: View): ImmersionBar {
        val view = rootView.findViewById<View>(viewId)
        requireNotNull(view) { "参数错误" }
        return titleBar(view, true)
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，支持任何view
     * Title bar immersion bar.
     *
     * @param viewId        the view id
     * @param rootView      the root view
     * @param statusBarFlag the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    fun titleBar(@IdRes viewId: Int, rootView: View, statusBarFlag: Boolean): ImmersionBar {
        val view = rootView.findViewById<View>(viewId)
        requireNotNull(view) { "参数错误" }
        return titleBar(view, statusBarFlag)
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param viewId the view id   标题栏资源id
     * @return the immersion bar
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun titleBarMarginTop(@IdRes viewId: Int): ImmersionBar {
        return titleBarMarginTop(mActivity!!.findViewById<View?>(viewId))
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param viewId   the view id  标题栏资源id
     * @param rootView the root view  布局view
     * @return the immersion bar
     */
    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun titleBarMarginTop(@IdRes viewId: Int, rootView: View): ImmersionBar {
        return titleBarMarginTop(rootView.findViewById<View?>(viewId))
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param view the view  要改变的标题栏view
     * @return the immersion bar
     */
    fun titleBarMarginTop(view: View): ImmersionBar {
        requireNotNull(view) { "参数错误" }
        barParams!!.titleBarViewMarginTop = view
        if (!barParams!!.titleBarViewMarginTopFlag) setTitleBarMarginTop()
        return this
    }

    /**
     * Status bar color transform enable immersion bar.
     *
     * @param statusBarFlag the status bar flag
     * @return the immersion bar
     */
    fun statusBarColorTransformEnable(statusBarFlag: Boolean): ImmersionBar {
        barParams!!.statusBarFlag = statusBarFlag
        return this
    }

    /**
     * 一键重置所有参数
     * Reset immersion bar.
     *
     * @return the immersion bar
     */
    fun reset(): ImmersionBar {
        val barParamsTemp = this.barParams
        this.barParams = BarParams()
        if (false || OSUtils.isEMUI3_1) {
            barParams!!.statusBarView = barParamsTemp!!.statusBarView
            barParams!!.navigationBarView = barParamsTemp.navigationBarView
        }
        barParams!!.keyboardPatch = barParamsTemp!!.keyboardPatch
        mMap.put(mImmersionBarName, this.barParams)
        return this
    }

    /**
     * 给某个页面设置tag来标识这页bar的属性.
     * Add tag bar tag.
     *
     * @param tag the tag
     * @return the bar tag
     */
    fun addTag(tag: String?): ImmersionBar {
        var tag = tag
        tag = mActivityName + "_TAG_" + tag
        if (!isEmpty(tag)) {
            val barParams = barParams!!.clone()
            mTagMap.put(tag, barParams)
            var tagList: ArrayList<String?>? = mTagKeyMap[mActivityName]
            if (tagList != null) {
                if (!tagList.contains(tag)) tagList.add(tag)
            } else {
                tagList = ArrayList<String?>()
                tagList.add(tag)
            }
            mTagKeyMap.put(mActivityName, tagList)
        }
        return this
    }

    /**
     * 根据tag恢复到某次调用时的参数
     * Recover immersion bar.
     *
     * @param tag the tag
     * @return the immersion bar
     */
    fun getTag(tag: String?): ImmersionBar {
        if (!isEmpty(tag)) {
            val barParams: BarParams? = mTagMap[mActivityName + "_TAG_" + tag]
            if (barParams != null) {
                this.barParams = barParams.clone()
            }
        }
        return this
    }

    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     *
     * @param enable       the enable
     * @param keyboardMode the keyboard mode
     * @return the immersion bar
     */
    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     * Keyboard enable immersion bar.
     *
     * @param enable the enable
     * @return the immersion bar
     */
    @JvmOverloads
    fun keyboardEnable(
        enable: Boolean, keyboardMode: Int = (WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                or WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    ): ImmersionBar {
        barParams!!.keyboardEnable = enable
        barParams!!.keyboardMode = keyboardMode
        return this
    }

    /**
     * 修改键盘模式
     * Keyboard mode immersion bar.
     *
     * @param keyboardMode the keyboard mode
     * @return the immersion bar
     */
    fun keyboardMode(keyboardMode: Int): ImmersionBar {
        barParams!!.keyboardMode = keyboardMode
        return this
    }

    /**
     * 软键盘弹出关闭的回调监听
     * Sets on keyboard listener.
     *
     * @param onKeyboardListener the on keyboard listener
     * @return the on keyboard listener
     */
    fun setOnKeyboardListener(onKeyboardListener: OnKeyboardListener?): ImmersionBar {
        if (barParams!!.onKeyboardListener == null) barParams!!.onKeyboardListener =
            onKeyboardListener
        return this
    }

    /**
     * 是否可以修改导航栏颜色，默认为true
     * Navigation bar enable immersion bar.
     *
     * @param navigationBarEnable the enable
     * @return the immersion bar
     */
    fun navigationBarEnable(navigationBarEnable: Boolean): ImmersionBar {
        barParams!!.navigationBarEnable = navigationBarEnable
        return this
    }

    /**
     * 是否可以修改4.4设备导航栏颜色，默认为true
     *
     * @param navigationBarWithKitkatEnable the navigation bar with kitkat enable
     * @return the immersion bar
     */
    fun navigationBarWithKitkatEnable(navigationBarWithKitkatEnable: Boolean): ImmersionBar {
        barParams!!.navigationBarWithKitkatEnable = navigationBarWithKitkatEnable
        return this
    }

    /**
     * 通过上面配置后初始化后方可成功调用
     */
    fun init() {
        mMap.put(mImmersionBarName, this.barParams)
        initBar() //初始化沉浸式
        setStatusBarView() //通过状态栏高度动态设置状态栏布局
        transformView() //变色view
        keyboardEnable() //解决软键盘与底部输入框冲突问题
        registerEMUI3_x() //解决华为emui3.1或者3.0导航栏手动隐藏的问题
    }

    /**
     * 当Activity/Fragment/Dialog关闭的时候调用
     */
    fun destroy() {
        unRegisterEMUI3_x()
        if (barParams!!.keyboardPatch != null) {
            barParams!!.keyboardPatch!!.disable(barParams!!.keyboardMode) //取消监听
            barParams!!.keyboardPatch = null
        }
        if (mDecorView != null) mDecorView = null
        if (mContentView != null) mContentView = null
        if (mConfig != null) mConfig = null
        if (mWindow != null) mWindow = null
        if (mDialog != null) mDialog = null
        if (mActivity != null) mActivity = null
        if (!isEmpty(mImmersionBarName)) {
            if (this.barParams != null) this.barParams = null
            val tagList: ArrayList<String?>? = mTagKeyMap[mActivityName]
            if (tagList != null && tagList.isNotEmpty()) {
                for (tag in tagList) {
                    mTagMap.remove(tag)
                }
                mTagKeyMap.remove(mActivityName)
            }
            mMap.remove(mImmersionBarName)
        }
    }

    /**
     * 初始化状态栏和导航栏
     */
    private fun initBar() {
        var uiFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE //防止系统栏隐藏时内容区域大小发生变化
        if (true && !OSUtils.isEMUI3_1) {
            uiFlags = initBarAboveLOLLIPOP(uiFlags) //初始化5.0以上，包含5.0
            uiFlags = setStatusBarDarkFont(uiFlags) //android 6.0以上设置状态栏字体为暗色
            uiFlags = setNavigationBarLightFont(uiFlags)
            supportActionBar()
        } else {
            initBarBelowLOLLIPOP() //初始化5.0以下，4.4以上沉浸式
            solveNavigation() //解决android4.4有导航栏的情况下，activity底部被导航栏遮挡的问题和android 5.0以下解决状态栏和布局重叠问题
        }
        uiFlags = hideBar(uiFlags) //隐藏状态栏或者导航栏
        mWindow!!.decorView.systemUiVisibility = uiFlags
        hideStatusBar(barParams!!.barHide == BarHide.FLAG_HIDE_STATUS_BAR || barParams!!.barHide == BarHide.FLAG_HIDE_BAR)
        if (OSUtils.isMIUI6Later) setMIUIStatusBarDarkFont(
            mWindow,
            barParams!!.darkFont
        ) //修改miui状态栏字体颜色

        if (OSUtils.isFlymeOS4Later) {          // 修改Flyme OS状态栏字体颜色
            if (barParams!!.flymeOSStatusBarFontColor != 0) {
                FlymeOSStatusBarFontUtils.setStatusBarDarkIcon(
                    mActivity!!,
                    barParams!!.flymeOSStatusBarFontColor
                )
            } else {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) FlymeOSStatusBarFontUtils.setStatusBarDarkIcon(
                    mActivity!!, barParams!!.darkFont
                )
            }
        }
    }

    private fun hideStatusBar(enable: Boolean) {
        if (enable) { //隐藏状态栏
            val lp = mWindow!!.attributes
            lp.flags = lp.flags or WindowManager.LayoutParams.FLAG_FULLSCREEN
            mWindow!!.setAttributes(lp)
        } else { //显示状态栏
            val attr = mWindow!!.attributes
            attr.flags = attr.flags and (WindowManager.LayoutParams.FLAG_FULLSCREEN.inv())
            mWindow!!.setAttributes(attr)
        }
    }

    /**
     * 初始化android 5.0以上状态栏和导航栏
     *
     * @param uiFlags the ui flags
     * @return the int
     */
    private fun initBarAboveLOLLIPOP(uiFlags: Int): Int {
        var uiFlags = uiFlags
        uiFlags =
            uiFlags or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN //Activity全屏显示，但状态栏不会被隐藏覆盖，状态栏依然可见，Activity顶端布局部分会被状态栏遮住。
        if (barParams!!.fullScreen && barParams!!.navigationBarEnable) {
            uiFlags =
                uiFlags or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //Activity全屏显示，但导航栏不会被隐藏覆盖，导航栏依然可见，Activity底部布局部分会被导航栏遮住。
        }
        mWindow!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (mConfig!!.hasNavigtionBar()) {  //判断是否存在导航栏
            mWindow!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
        }

        mWindow!!.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS) //需要设置这个才能设置状态栏颜色
        if (barParams!!.statusBarFlag) mWindow!!.statusBarColor = ColorUtils.blendARGB(
            barParams!!.statusBarColor,
            barParams!!.statusBarColorTransform, barParams!!.statusBarAlpha
        ) //设置状态栏颜色
        else mWindow!!.statusBarColor = ColorUtils.blendARGB(
            barParams!!.statusBarColor,
            Color.TRANSPARENT, barParams!!.statusBarAlpha
        ) //设置状态栏颜色

        if (barParams!!.navigationBarEnable) {
            mWindow!!.navigationBarColor = ColorUtils.blendARGB(
                barParams!!.navigationBarColor,
                barParams!!.navigationBarColorTransform, barParams!!.navigationBarAlpha
            ) //设置导航栏颜色
            if (Build.VERSION.SDK_INT >= 28 && !barParams!!.navigationBarDivider) mWindow!!.setNavigationBarDividerColor(
                Color.TRANSPARENT
            )
        }
        return uiFlags
    }

    /**
     * 初始化android 4.4和emui3.1状态栏和导航栏
     */
    private fun initBarBelowLOLLIPOP() {
        mWindow!!.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) //透明状态栏
        setupStatusBarView() //创建一个假的状态栏
        if (mConfig!!.hasNavigtionBar()) {  //判断是否存在导航栏，是否禁止设置导航栏
            if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) mWindow!!.addFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            ) //透明导航栏，设置这个，如果有导航栏，底部布局会被导航栏遮住
            else mWindow!!.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            setupNavBarView() //创建一个假的导航栏
        }
    }

    /**
     * 设置一个可以自定义颜色的状态栏
     */
    private fun setupStatusBarView() {
        if (barParams!!.statusBarView == null) {
            barParams!!.statusBarView = View(mActivity)
        }
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            mConfig!!.statusBarHeight
        )
        params.gravity = Gravity.TOP
        barParams!!.statusBarView!!.setLayoutParams(params)
        if (barParams!!.statusBarFlag) barParams!!.statusBarView!!.setBackgroundColor(
            ColorUtils.blendARGB(
                barParams!!.statusBarColor,
                barParams!!.statusBarColorTransform, barParams!!.statusBarAlpha
            )
        )
        else barParams!!.statusBarView!!.setBackgroundColor(
            ColorUtils.blendARGB(
                barParams!!.statusBarColor,
                Color.TRANSPARENT, barParams!!.statusBarAlpha
            )
        )
        barParams!!.statusBarView!!.visibility = View.VISIBLE
        val viewGroup = barParams!!.statusBarView!!.parent as ViewGroup?
        viewGroup?.removeView(barParams!!.statusBarView)
        mDecorView!!.addView(barParams!!.statusBarView)
    }

    /**
     * 设置一个可以自定义颜色的导航栏
     */
    private fun setupNavBarView() {
        if (barParams!!.navigationBarView == null) {
            barParams!!.navigationBarView = View(mActivity)
        }
        val params: FrameLayout.LayoutParams?
        if (mConfig!!.isNavigationAtBottom) {
            params = FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                mConfig!!.navigationBarHeight
            )
            params.gravity = Gravity.BOTTOM
        } else {
            params = FrameLayout.LayoutParams(
                mConfig!!.navigationBarWidth,
                FrameLayout.LayoutParams.MATCH_PARENT
            )
            params.gravity = Gravity.END
        }
        barParams!!.navigationBarView!!.setLayoutParams(params)
        if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) {
            if (!barParams!!.fullScreen && (barParams!!.navigationBarColorTransform == Color.TRANSPARENT)) {
                barParams!!.navigationBarView!!.setBackgroundColor(
                    ColorUtils.blendARGB(
                        barParams!!.navigationBarColor,
                        Color.BLACK, barParams!!.navigationBarAlpha
                    )
                )
            } else {
                barParams!!.navigationBarView!!.setBackgroundColor(
                    ColorUtils.blendARGB(
                        barParams!!.navigationBarColor,
                        barParams!!.navigationBarColorTransform, barParams!!.navigationBarAlpha
                    )
                )
            }
        } else barParams!!.navigationBarView!!.setBackgroundColor(Color.TRANSPARENT)
        barParams!!.navigationBarView!!.visibility = View.VISIBLE
        val viewGroup = barParams!!.navigationBarView!!.parent as ViewGroup?
        viewGroup?.removeView(barParams!!.navigationBarView)
        mDecorView!!.addView(barParams!!.navigationBarView)
    }

    /**
     * 解决安卓4.4和EMUI3.1导航栏与状态栏的问题，以及系统属性fitsSystemWindows的坑
     */
    private fun solveNavigation() {
        var i = 0
        val count = mContentView!!.size
        while (i < count) {
            val childView = mContentView!!.getChildAt(i)
            if (childView is ViewGroup) {
                if (childView is DrawerLayout) {
                    val childAt1 = childView.getChildAt(0)
                    if (childAt1 != null) {
                        barParams!!.systemWindows = childAt1.fitsSystemWindows
                        if (barParams!!.systemWindows) {
                            mContentView!!.setPadding(0, 0, 0, 0)
                            return
                        }
                    }
                } else {
                    barParams!!.systemWindows = childView.fitsSystemWindows
                    if (barParams!!.systemWindows) {
                        mContentView!!.setPadding(0, 0, 0, 0)
                        return
                    }
                }
            }

            i++
        }
        // 解决android4.4有导航栏的情况下，activity底部被导航栏遮挡的问题
        if (mConfig!!.hasNavigtionBar() && !barParams!!.fullScreenTemp && !barParams!!.fullScreen) {
            if (mConfig!!.isNavigationAtBottom) { //判断导航栏是否在底部
                if (!barParams!!.isSupportActionBar) { //判断是否支持actionBar
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0, mConfig!!.statusBarHeight,
                            0, mConfig!!.navigationBarHeight
                        ) //有导航栏，获得rootView的根节点，然后设置距离底部的padding值为导航栏的高度值
                        else mContentView!!.setPadding(0, 0, 0, mConfig!!.navigationBarHeight)
                    } else {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0,
                            mConfig!!.statusBarHeight,
                            0,
                            0
                        )
                        else mContentView!!.setPadding(0, 0, 0, 0)
                    }
                } else {
                    //支持有actionBar的界面
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) mContentView!!.setPadding(
                        0, mConfig!!.statusBarHeight +
                                mConfig!!.actionBarHeight + 10, 0, mConfig!!.navigationBarHeight
                    )
                    else mContentView!!.setPadding(
                        0, mConfig!!.statusBarHeight +
                                mConfig!!.actionBarHeight + 10, 0, 0
                    )
                }
            } else {
                if (!barParams!!.isSupportActionBar) {
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0, mConfig!!.statusBarHeight,
                            mConfig!!.navigationBarWidth, 0
                        ) //不在底部，设置距离右边的padding值为导航栏的宽度值
                        else mContentView!!.setPadding(0, 0, mConfig!!.navigationBarWidth, 0)
                    } else {
                        if (barParams!!.fits) mContentView!!.setPadding(
                            0,
                            mConfig!!.statusBarHeight,
                            0,
                            0
                        )
                        else mContentView!!.setPadding(0, 0, 0, 0)
                    }
                } else {
                    //支持有actionBar的界面
                    if (barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable) mContentView!!.setPadding(
                        0, mConfig!!.statusBarHeight +
                                mConfig!!.actionBarHeight + 10, mConfig!!.navigationBarWidth, 0
                    )
                    else mContentView!!.setPadding(
                        0, mConfig!!.statusBarHeight +
                                mConfig!!.actionBarHeight + 10, 0, 0
                    )
                }
            }
        } else {
            if (!barParams!!.isSupportActionBar) {
                if (barParams!!.fits) mContentView!!.setPadding(0, mConfig!!.statusBarHeight, 0, 0)
                else mContentView!!.setPadding(0, 0, 0, 0)
            } else {
                //支持有actionBar的界面
                mContentView!!.setPadding(
                    0,
                    mConfig!!.statusBarHeight + mConfig!!.actionBarHeight + 10,
                    0,
                    0
                )
            }
        }
    }

    /**
     * 注册emui3.x导航栏监听函数
     * Register emui 3 x.
     */
    private fun registerEMUI3_x() {
        if ((OSUtils.isEMUI3_1 || OSUtils.isEMUI3_0) && mConfig!!.hasNavigtionBar()
            && barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable
        ) {
            if (barParams!!.navigationStatusObserver == null && barParams!!.navigationBarView != null) {
                barParams!!.navigationStatusObserver = object : ContentObserver(Handler()) {
                    override fun onChange(selfChange: Boolean) {
                        if (mActivity != null && mActivity!!.contentResolver != null) {
                            val navigationBarIsMin = Settings.System.getInt(
                                mActivity!!.contentResolver,
                                NAVIGATIONBAR_IS_MIN, 0
                            )
                            if (navigationBarIsMin == 1) {
                                //导航键隐藏了
                                barParams!!.navigationBarView!!.visibility = View.GONE
                                mContentView!!.setPadding(0, mContentView!!.paddingTop, 0, 0)
                            } else {
                                //导航键显示了
                                barParams!!.navigationBarView!!.visibility = View.VISIBLE
                                if (!barParams!!.systemWindows) {
                                    if (mConfig!!.isNavigationAtBottom) mContentView!!.setPadding(
                                        0,
                                        mContentView!!.paddingTop,
                                        0,
                                        mConfig!!.navigationBarHeight
                                    )
                                    else mContentView!!.setPadding(
                                        0,
                                        mContentView!!.paddingTop,
                                        mConfig!!.navigationBarWidth,
                                        0
                                    )
                                } else mContentView!!.setPadding(
                                    0,
                                    mContentView!!.paddingTop,
                                    0,
                                    0
                                )
                            }
                        }
                    }
                }
                if (mActivity != null && mActivity!!.contentResolver != null && barParams!!.navigationStatusObserver != null) {
                    mActivity!!.contentResolver.registerContentObserver(
                        Settings.System.getUriFor(
                            NAVIGATIONBAR_IS_MIN
                        ), true, barParams!!.navigationStatusObserver!!
                    )
                }
            }
        }
    }

    /**
     * 取消注册emui3.x导航栏监听函数
     * Un register emui 3 x.
     */
    private fun unRegisterEMUI3_x() {
        if ((OSUtils.isEMUI3_1 || OSUtils.isEMUI3_0) && mConfig!!.hasNavigtionBar()
            && barParams!!.navigationBarEnable && barParams!!.navigationBarWithKitkatEnable
        ) {
            if (mActivity != null && mActivity!!.contentResolver != null && barParams!!.navigationStatusObserver != null && barParams!!.navigationBarView != null) mActivity!!.getContentResolver()
                .unregisterContentObserver(
                    barParams!!.navigationStatusObserver!!
                )
        }
    }

    /**
     * Hide bar.
     * 隐藏或显示状态栏和导航栏。
     *
     * @param uiFlags the ui flags
     * @return the int
     */
    @SuppressLint("ObsoleteSdkInt")
    private fun hideBar(uiFlags: Int): Int {
        var uiFlags = uiFlags
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            uiFlags = when (barParams!!.barHide) {
                BarHide.FLAG_HIDE_BAR -> uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.INVISIBLE)

                BarHide.FLAG_HIDE_STATUS_BAR -> uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.INVISIBLE)

                BarHide.FLAG_HIDE_NAVIGATION_BAR -> uiFlags or (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)

                BarHide.FLAG_SHOW_BAR -> uiFlags or View.SYSTEM_UI_FLAG_VISIBLE
            }
        }
        return uiFlags or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
    }

    /**
     * Sets status bar dark font.
     * 设置状态栏字体颜色，android6.0以上
     */
    private fun setStatusBarDarkFont(uiFlags: Int): Int {
        return if (true && barParams!!.darkFont) {
            uiFlags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            uiFlags
        }
    }

    /**
     * 设置暗色导航栏按钮
     */
    private fun setNavigationBarLightFont(uiFlags: Int): Int {
        return if (canNavigationBarDarkFont() && barParams!!.navigationBarDarkFont) {
            uiFlags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            uiFlags
        }
    }

    /**
     * 变色view
     *
     *
     * Transform view.
     */
    private fun transformView() {
        if (barParams!!.viewMap.isNotEmpty()) {
            val entrySet = barParams!!.viewMap.entries
            for (entry in entrySet) {
                val view = entry.key
                val map: MutableMap<Int?, Int?> = entry.value!!
                var colorBefore = barParams!!.statusBarColor
                var colorAfter = barParams!!.statusBarColorTransform
                for (integerEntry in map.entries) {
                    colorBefore = integerEntry.key!!
                    colorAfter = integerEntry.value!!
                }
                if (view != null) {
                    if (abs((barParams!!.viewAlpha - 0.0f).toDouble()) == 0.0) view.setBackgroundColor(
                        ColorUtils.blendARGB(colorBefore, colorAfter, barParams!!.statusBarAlpha)
                    )
                    else view.setBackgroundColor(
                        ColorUtils.blendARGB(
                            colorBefore,
                            colorAfter,
                            barParams!!.viewAlpha
                        )
                    )
                }
            }
        }
    }


    /**
     * 通过状态栏高度动态设置状态栏布局
     */
    private fun setStatusBarView() {
        if (true && barParams!!.statusBarViewByHeight != null) {
            val params = barParams!!.statusBarViewByHeight!!.layoutParams
            params.height = mConfig!!.statusBarHeight
            barParams!!.statusBarViewByHeight!!.setLayoutParams(params)
        }
    }

    /**
     * 重新绘制标题栏高度，解决状态栏与顶部重叠问题
     * Sets title bar.
     */
    private fun setTitleBar() {
        if (true && barParams!!.titleBarView != null) {
            val layoutParams = barParams!!.titleBarView!!.layoutParams
            if (layoutParams.height == ViewGroup.LayoutParams.WRAP_CONTENT ||
                layoutParams.height == ViewGroup.LayoutParams.MATCH_PARENT
            ) {
                barParams!!.titleBarView!!.getViewTreeObserver()
                    .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            barParams!!.titleBarView!!.getViewTreeObserver()
                                .removeOnGlobalLayoutListener(this)
                            if (barParams!!.titleBarHeight == 0) barParams!!.titleBarHeight =
                                barParams!!.titleBarView!!.height + mConfig!!.statusBarHeight
                            if (barParams!!.titleBarPaddingTopHeight == 0) barParams!!.titleBarPaddingTopHeight =
                                (barParams!!.titleBarView!!.paddingTop
                                        + mConfig!!.statusBarHeight)
                            layoutParams.height = barParams!!.titleBarHeight
                            barParams!!.titleBarView!!.setPadding(
                                barParams!!.titleBarView!!.getPaddingLeft(),
                                barParams!!.titleBarPaddingTopHeight,
                                barParams!!.titleBarView!!.getPaddingRight(),
                                barParams!!.titleBarView!!.paddingBottom
                            )
                            barParams!!.titleBarView!!.setLayoutParams(layoutParams)
                        }
                    })
            } else {
                if (barParams!!.titleBarHeight == 0) barParams!!.titleBarHeight =
                    layoutParams.height + mConfig!!.statusBarHeight
                if (barParams!!.titleBarPaddingTopHeight == 0) barParams!!.titleBarPaddingTopHeight =
                    (barParams!!.titleBarView!!.paddingTop
                            + mConfig!!.statusBarHeight)
                layoutParams.height = barParams!!.titleBarHeight
                barParams!!.titleBarView!!.setPadding(
                    barParams!!.titleBarView!!.getPaddingLeft(),
                    barParams!!.titleBarPaddingTopHeight,
                    barParams!!.titleBarView!!.getPaddingRight(),
                    barParams!!.titleBarView!!.paddingBottom
                )
                barParams!!.titleBarView!!.setLayoutParams(layoutParams)
            }
        }
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Sets title bar margin top.
     */
    private fun setTitleBarMarginTop() {
        val layoutParams =
            barParams!!.titleBarViewMarginTop!!.getLayoutParams() as MarginLayoutParams
        layoutParams.setMargins(
            layoutParams.leftMargin,
            layoutParams.topMargin + mConfig!!.statusBarHeight,
            layoutParams.rightMargin,
            layoutParams.bottomMargin
        )
        barParams!!.titleBarViewMarginTopFlag = true
    }

    /**
     * 支持actionBar的界面
     * Support action bar.
     */
    private fun supportActionBar() {
        if (true && !OSUtils.isEMUI3_1) {
            var i = 0
            val count = mContentView!!.size
            while (i < count) {
                val childView = mContentView!!.getChildAt(i)
                if (childView is ViewGroup) {
                    barParams!!.systemWindows = childView.fitsSystemWindows
                    if (barParams!!.systemWindows) {
                        mContentView!!.setPadding(0, 0, 0, 0)
                        return
                    }
                }
                i++
            }
            if (barParams!!.isSupportActionBar) {
                mContentView!!.setPadding(
                    0,
                    mConfig!!.statusBarHeight + mConfig!!.actionBarHeight,
                    0,
                    0
                )
            } else {
                if (barParams!!.fits) mContentView!!.setPadding(0, mConfig!!.statusBarHeight, 0, 0)
                else mContentView!!.setPadding(0, 0, 0, 0)
            }
        }
    }

    /**
     * 解决底部输入框与软键盘问题
     * Keyboard enable.
     */
    private fun keyboardEnable() {
        if (barParams!!.keyboardPatch == null) {
            barParams!!.keyboardPatch = KeyboardPatch.patch(mActivity, mWindow)
        }
        barParams!!.keyboardPatch!!.setBarParams(this.barParams)
        if (barParams!!.keyboardEnable) {  //解决软键盘与底部输入框冲突问题
            barParams!!.keyboardPatch!!.enable(barParams!!.keyboardMode)
        } else {
            barParams!!.keyboardPatch!!.disable(barParams!!.keyboardMode)
        }
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @return boolean 成功执行返回true
     */
    private fun setMIUIStatusBarDarkFont(window: Window?, darkFont: Boolean) {
        if (window != null) {
            val clazz: Class<*> = window.javaClass
            try {
                val darkModeFlag: Int
                @SuppressLint("PrivateApi") val layoutParams =
                    Class.forName("android.view.MiuiWindowManager\$LayoutParams")
                val field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE")
                darkModeFlag = field.getInt(layoutParams)
                val extraFlagField = clazz.getMethod(
                    "setExtraFlags",
                    Int::class.javaPrimitiveType,
                    Int::class.javaPrimitiveType
                )
                if (darkFont) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag) //状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag) //清除黑色字体
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 获取 bar 参数。
     * @param tag 标签
     * @return BarParams
     */
    fun getTagBarParams(tag: String?): BarParams? {
        var barParams: BarParams? = null
        if (!isEmpty(tag)) {
            barParams = mTagMap[mActivityName + "_TAG_" + tag]
        }
        return barParams
    }

    companion object {
        private val mMap: MutableMap<String?, BarParams?> = HashMap<String?, BarParams?>()
        private val mTagMap: MutableMap<String?, BarParams?> = HashMap<String?, BarParams?>()
        private val mTagKeyMap: MutableMap<String?, ArrayList<String?>?> =
            HashMap<String?, ArrayList<String?>?>()

        private const val NAVIGATIONBAR_IS_MIN = "navigationbar_is_min"

        /**
         * 初始化Activity
         * With immersion bar.
         *
         * @param activity the activity
         * @return the immersion bar
         */
        @JvmStatic
        fun with(activity: Activity): ImmersionBar {
            requireNotNull(activity) { "Activity不能为null" }
            return ImmersionBar(activity)
        }

        /**
         * 调用该方法必须保证加载Fragment的Activity先初始化,已过时，使用with(Activity activity, Fragment fragment)方法
         * With immersion bar.
         *
         * @param fragment the fragment
         * @return the immersion bar
         */
        fun with(fragment: Fragment): ImmersionBar {
            requireNotNull(fragment) { "Fragment不能为null" }
            return ImmersionBar(fragment)
        }

        fun with(activity: Activity, fragment: Fragment): ImmersionBar {
            requireNotNull(activity) { "Activity不能为null" }
            requireNotNull(fragment) { "Fragment不能为null" }
            return ImmersionBar(activity, fragment)
        }

        fun with(dialogFragment: DialogFragment, dialog: Dialog): ImmersionBar {
            requireNotNull(dialogFragment) { "DialogFragment不能为null" }
            requireNotNull(dialog) { "Dialog不能为null" }
            return ImmersionBar(dialogFragment, dialog)
        }

        /**
         * 在dialog里使用
         * With immersion bar.
         *
         * @param activity  the activity
         * @param dialog    the dialog
         * @param dialogTag the dialog tag
         * @return the immersion bar
         */
        @JvmStatic
        fun with(activity: Activity, dialog: Dialog, dialogTag: String): ImmersionBar {
            requireNotNull(activity) { "Activity不能为null" }
            requireNotNull(dialog) { "Dialog不能为null" }
            require(!isEmpty(dialogTag)) { "tag不能为null或空" }
            return ImmersionBar(activity, dialog, dialogTag)
        }

        /**
         * 是否可以设置暗色导航栏按钮
         */
        fun canNavigationBarDarkFont(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        }

        /**
         * 单独设置标题栏的高度
         * Sets title bar.
         *
         * @param activity the activity
         * @param view     the view
         */
        fun setTitleBar(activity: Activity, view: View) {
            val lp = view.layoutParams
            if (lp.height == ViewGroup.LayoutParams.WRAP_CONTENT) {  //解决状态栏高度为warp_content或match_parent问题
                view.getViewTreeObserver()
                    .addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                        override fun onGlobalLayout() {
                            view.getViewTreeObserver().removeOnGlobalLayoutListener(this)
                            lp.height = view.height + getStatusBarHeight(activity)
                            view.setPadding(
                                view.getPaddingLeft(),
                                view.paddingTop + getStatusBarHeight(activity),
                                view.getPaddingRight(),
                                view.paddingBottom
                            )
                        }
                    })
            } else {
                lp.height += getStatusBarHeight(activity)
                view.setPadding(
                    view.getPaddingLeft(), view.paddingTop + getStatusBarHeight(activity),
                    view.getPaddingRight(), view.paddingBottom
                )
            }
        }

        /**
         * 单独在标题栏的位置增加view，高度为状态栏的高度
         * Sets status bar view.
         *
         * @param activity the activity
         * @param view     the view
         */
        fun setStatusBarView(activity: Activity, view: View) {
            val params = view.layoutParams
            params.height = getStatusBarHeight(activity)
            view.setLayoutParams(params)
        }

        /**
         * 设置标题栏MarginTop值为导航栏的高度
         * Sets title bar margin top.
         *
         * @param activity the activity
         * @param view     the view
         */
        fun setTitleBarMarginTop(activity: Activity, view: View) {
            val layoutParams = view.layoutParams as MarginLayoutParams
            layoutParams.setMargins(
                layoutParams.leftMargin,
                layoutParams.topMargin + getStatusBarHeight(activity),
                layoutParams.rightMargin,
                layoutParams.bottomMargin
            )
        }

        /**
         * 解决顶部与布局重叠问题
         * Sets fits system windows.
         *
         * @param activity the activity
         */
        fun setFitsSystemWindows(activity: Activity) {
            val parent = activity.findViewById<View?>(android.R.id.content) as ViewGroup
            var i = 0
            val count = parent.size
            while (i < count) {
                val childView = parent.getChildAt(i)
                if (childView is ViewGroup) {
                    childView.fitsSystemWindows = true
                    childView.clipToPadding = true
                }
                i++
            }
        }

        /**
         * Has navigtion bar boolean.
         * 判断是否存在导航栏
         *
         * @param activity the activity
         * @return the boolean
         */
        fun hasNavigationBar(activity: Activity): Boolean {
            val config = BarConfig(activity)
            return config.hasNavigtionBar()
        }

        /**
         * Gets navigation bar height.
         * 获得导航栏的高度
         *
         * @param activity the activity
         * @return the navigation bar height
         */
        fun getNavigationBarHeight(activity: Activity): Int {
            val config = BarConfig(activity)
            return config.navigationBarHeight
        }

        /**
         * 获取导航栏宽度，获得导航栏的宽度
         * @param activity 上下文
         * @return 导航栏宽度
         */
        fun getNavigationBarWidth(activity: Activity): Int {
            val config = BarConfig(activity)
            return config.navigationBarWidth
        }

        /**
         * 底部导航是布尔值，判断导航栏是否在底部
         * @param activity 上下文
         * @return boolean
         */
        fun isNavigationAtBottom(activity: Activity): Boolean {
            val config = BarConfig(activity)
            return config.isNavigationAtBottom
        }

        /**
         * 获取状态栏高度，或得状态栏的高度
         * @param activity 活动
         * @return 状态栏高度
         */
        fun getStatusBarHeight(activity: Activity): Int {
            val config = BarConfig(activity)
            return config.statusBarHeight
        }

        /**
         * 获取操作栏高度，或得ActionBar得高度
         * @param activity 活动
         * @return 操作栏高度
         */
        fun getActionBarHeight(activity: Activity): Int {
            val config = BarConfig(activity)
            return config.actionBarHeight
        }

        val isSupportStatusBarDarkFont: Boolean
            /**
             * 判断手机支不支持状态栏字体变色，是否支持状态栏深色字体布尔值。
             * @return boolean
             */
            get() = OSUtils.isMIUI6Later || OSUtils.isFlymeOS4Later
                    || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

        /**
         * 隐藏状态栏
         * @param window 窗口
         */
        fun hideStatusBar(window: Window) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        /**
         * 导航栏增加padding
         * @param activity 上下文
         * @param view 视图
         */
        fun navigationBarPadding(activity: Activity, view: View) {
            if (hasNavigationBar(activity)) {
                view.setPadding(0, 0, 0, getNavigationBarHeight(activity))
            }
        }

        /**
         * 判断字符串是否为空
         * @param str 文本
         * @return boolean
         */
        private fun isEmpty(str: String?): Boolean {
            return str == null || str.trim { it <= ' ' }.isEmpty() //str.trim().length() == 0;
        }
    }
}
