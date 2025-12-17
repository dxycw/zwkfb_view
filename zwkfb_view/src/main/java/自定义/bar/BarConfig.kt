@file:Suppress("DEPRECATION", "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package 自定义.bar

import android.R
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import android.util.TypedValue
import kotlin.math.min

/**
 * Created by geyifeng on 2017/5/11.
 */
internal class BarConfig(activity: Activity) {
    /**
     * Get the height of the system status bar.
     *
     * @return The height of the status bar (in pixels).
     */
    val statusBarHeight: Int

    /**
     * Get the height of the action bar.
     *
     * @return The height of the action bar (in pixels).
     */
    val actionBarHeight: Int
    private val mHasNavigationBar: Boolean

    /**
     * Get the height of the system navigation bar.
     *
     * @return The height of the navigation bar (in pixels). If the device does not have
     * soft navigation keys, this will always return 0.
     */
    val navigationBarHeight: Int

    /**
     * Get the width of the system navigation bar when it is placed vertically on the screen.
     *
     * @return The width of the navigation bar (in pixels). If the device does not have
     * soft navigation keys, this will always return 0.
     */
    val navigationBarWidth: Int
    private val mInPortrait: Boolean
    private val mSmallestWidthDp: Float


    init {
        val res = activity.resources
        mInPortrait = (res.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
        mSmallestWidthDp = getSmallestWidthDp(activity)
        this.statusBarHeight = getInternalDimensionSize(res, STATUS_BAR_HEIGHT_RES_NAME)
        this.actionBarHeight = getActionBarHeight(activity)
        this.navigationBarHeight = getNavigationBarHeight(activity)
        this.navigationBarWidth = getNavigationBarWidth(activity)
        mHasNavigationBar = (this.navigationBarHeight > 0) and checkDeviceHasNavigationBar(activity)
    }

    private fun getActionBarHeight(context: Context): Int {
        val result: Int
        val tv = TypedValue()
        context.theme.resolveAttribute(R.attr.actionBarSize, tv, true)
        result = TypedValue.complexToDimensionPixelSize(tv.data,
            context.resources.displayMetrics
        )
        return result
    }

    private fun getNavigationBarHeight(context: Context): Int {
        val res = context.resources
        val result = 0
        if (hasNavBar(context as Activity)) {
            val key = if (mInPortrait) {
                NAV_BAR_HEIGHT_RES_NAME
            } else {
                NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME
            }
            return getInternalDimensionSize(res, key)
        }
        return result
    }

    private fun getNavigationBarWidth(context: Context): Int {
        val res = context.resources
        val result = 0
        if (hasNavBar(context as Activity)) {
            return getInternalDimensionSize(res, NAV_BAR_WIDTH_RES_NAME)
        }
        return result
    }

    @SuppressLint("PrivateApi")
    private fun getInternalDimensionSize(res: Resources, key: String): Int {
        var result = 0
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object`: Any = clazz.newInstance()
            val resourceId = clazz.getField(key).get(`object`).toString().toInt()
            if (resourceId > 0) result = res.getDimensionPixelSize(resourceId)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return result
    }

    @SuppressLint("NewApi")
    private fun getSmallestWidthDp(activity: Activity): Float {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getRealMetrics(metrics)
        val widthDp = metrics.widthPixels / metrics.density
        val heightDp = metrics.heightPixels / metrics.density
        return min(widthDp.toDouble(), heightDp.toDouble()).toFloat()
    }

    val isNavigationAtBottom: Boolean
        /**
         * Should a navigation bar appear at the bottom of the screen in the current
         * device configuration? A navigation bar may appear on the right side of
         * the screen in certain configurations.
         *
         * @return True if navigation should appear at the bottom of the screen, False otherwise.
         */
        get() = (mSmallestWidthDp >= 600 || mInPortrait)

    /**
     * Does this device have a system navigation bar?
     *
     * @return True if this device uses soft key navigation, False otherwise.
     */
    fun hasNavigtionBar(): Boolean {
        return mHasNavigationBar
    }

    fun checkDeviceHasNavigationBar(activity: Activity): Boolean {
        val display = activity.windowManager.defaultDisplay
        val size = Point()
        val realSize = Point()
        display.getSize(size)
        display.getRealSize(realSize)
        return realSize.y != size.y
    }

    companion object {
        private const val STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height"
        private const val NAV_BAR_HEIGHT_RES_NAME = "navigation_bar_height"
        private const val NAV_BAR_HEIGHT_LANDSCAPE_RES_NAME = "navigation_bar_height_landscape"
        private const val NAV_BAR_WIDTH_RES_NAME = "navigation_bar_width"

        private fun hasNavBar(activity: Activity): Boolean {
            val windowManager = activity.windowManager
            val d = windowManager.defaultDisplay

            val realDisplayMetrics = DisplayMetrics()
            d.getRealMetrics(realDisplayMetrics)

            val realHeight = realDisplayMetrics.heightPixels
            val realWidth = realDisplayMetrics.widthPixels

            val displayMetrics = DisplayMetrics()
            d.getMetrics(displayMetrics)

            val displayHeight = displayMetrics.heightPixels
            val displayWidth = displayMetrics.widthPixels

            return (realWidth - displayWidth) > 0 || (realHeight - displayHeight) > 0
        }
    }
}