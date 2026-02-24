package 安卓x.核心.视图

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.core.view.WindowInsetsControllerCompat
import java.util.Objects

/**
 * Helper for accessing features in [Window].
 */
object 窗口兼容 {
    /**
     * Flag for enabling the Action Bar.
     * This is enabled by default for some devices. The Action Bar
     * replaces the title bar and provides an alternate location
     * for an on-screen menu button on some devices.
     */
    const val 特征_行动_栏: Int = 8

    /**
     * Flag for requesting an Action Bar that overlays window content.
     * Normally an Action Bar will sit in the space above window content, but if this
     * feature is requested along with [.FEATURE_ACTION_BAR] it will be layered over
     * the window content itself. This is useful if you would like your app to have more control
     * over how the Action Bar is displayed, such as letting application content scroll beneath
     * an Action Bar with a transparent background or otherwise displaying a transparent/translucent
     * Action Bar over application content.
     *
     * This mode is especially useful with [ View.SYSTEM_UI_FLAG_FULLSCREEN][View.SYSTEM_UI_FLAG_FULLSCREEN],
     * which allows you to seamlessly hide the action bar in conjunction with other screen decorations.
     */
    const val 特征_行动_栏_覆盖: Int = 9

    /**
     * Flag for specifying the behavior of action modes when an Action Bar is not present.
     * If overlay is enabled, the action mode UI will be allowed to cover existing window content.
     */
    const val 特征_行动_模式_覆盖 : Int = 10

    /**
     * Finds a view that was identified by the `android:id` XML attribute
     * that was processed in , or throws an
     * IllegalArgumentException if the ID is invalid, or there is no matching view in the hierarchy.
     * 
     * 
     * **Note:** In most cases -- depending on compiler support --
     * the resulting view is automatically cast to the target class type. If
     * the target class type is unconstrained, an explicit cast may be
     * necessary.
     * 
     * @param window window in which to find the view.
     * @param id the ID to search for
     * @return a view with given ID
     * @see ViewCompat.requireViewById
     * @see Window.findViewById
     */
    fun <T : View> 要求视图通过Id(窗口: Window, @IdRes id: Int): T {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.requireViewById<T>(窗口, id)
        }

        val view = 窗口.findViewById<T?>(id)
        requireNotNull(view) { "ID does not reference a View inside this Window" }
        return view
    }

    /**
     * Sets whether the decor view should fit root-level content views for
     * [WindowInsetsCompat].
     * 
     * 
     * If set to `false`, the framework will not fit the content view to the insets and will
     * just pass through the [WindowInsetsCompat] to the content view.
     * 
     * 
     * 
     * Please note: using the [View.setSystemUiVisibility] API in your app can
     * conflict with this method. Please discontinue use of [View.setSystemUiVisibility].
     * 
     * 
     * @param 窗口                 The current window.
     * @param 装饰视图适应系统窗口: Whether the decor view should fit root-level content views for
     * insets.
     */
    fun 置装饰视图适应系统窗口(窗口: Window, 装饰视图适应系统窗口: Boolean) {
        if (Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setDecorFitsSystemWindows(窗口, 装饰视图适应系统窗口)
        } else if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setDecorFitsSystemWindows(窗口, 装饰视图适应系统窗口)
        } else {
            Api16Impl.setDecorFitsSystemWindows(窗口, 装饰视图适应系统窗口)
        }
    }

    /**
     * Retrieves the single [WindowInsetsControllerCompat] of the window this view is attached
     * to.
     * 
     * @return The [WindowInsetsControllerCompat] for the window.
     * @see Window.getInsetsController
     */
    fun 取内边距控制器(窗口: Window, 视图: View): WindowInsetsControllerCompat {
        return WindowInsetsControllerCompat(窗口, 视图)
    }

    /**
     * Enables the content of the given [window][Window] to reach the edges of the window
     * without getting inset by system insets, and prevents the framework from placing color views
     * behind system bars.
     * 
     * @param 窗口 the given window.
     */
    fun 启用边缘到边缘(窗口: Window) {
        Objects.requireNonNull<Window>(窗口)

        // This triggers the initialization of the decor view here to prevent the attributes set by
        // this method from getting overwritten by the initialization later.
        窗口.getDecorView()

        置装饰视图适应系统窗口(窗口, false)
        窗口.setStatusBarColor(Color.TRANSPARENT)
        窗口.setNavigationBarColor(Color.TRANSPARENT)
        if (Build.VERSION.SDK_INT >= 28) {
            val newMode = if (Build.VERSION.SDK_INT >= 30)
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
            else
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            val attrs = 窗口.getAttributes()
            if (attrs.layoutInDisplayCutoutMode != newMode) {
                attrs.layoutInDisplayCutoutMode = newMode
                窗口.setAttributes(attrs)
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            窗口.setStatusBarContrastEnforced(false)
            窗口.setNavigationBarContrastEnforced(false)
        }
    }

    internal object Api16Impl {
        fun setDecorFitsSystemWindows(
            window: Window,
            decorFitsSystemWindows: Boolean
        ) {
            val decorFitsFlags = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)

            val decorView = window.getDecorView()
            val sysUiVis = decorView.getSystemUiVisibility()
            decorView.setSystemUiVisibility(
                if (decorFitsSystemWindows)
                    sysUiVis and decorFitsFlags.inv()
                else
                    sysUiVis or decorFitsFlags
            )
        }
    }

    @RequiresApi(30)
    internal object Api30Impl {
        fun setDecorFitsSystemWindows(
            window: Window,
            decorFitsSystemWindows: Boolean
        ) {
            val stableFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE

            val decorView = window.getDecorView()
            val sysUiVis = decorView.getSystemUiVisibility()
            decorView.setSystemUiVisibility(
                if (decorFitsSystemWindows)
                    sysUiVis and stableFlag.inv()
                else
                    sysUiVis or stableFlag
            )
            window.setDecorFitsSystemWindows(decorFitsSystemWindows)
        }
    }

    @RequiresApi(35)
    internal object Api35Impl {
        fun setDecorFitsSystemWindows(
            window: Window,
            decorFitsSystemWindows: Boolean
        ) {
            window.setDecorFitsSystemWindows(decorFitsSystemWindows)
        }
    }

    @RequiresApi(28)
    internal object Api28Impl {
        fun <T : View> requireViewById(window: Window, id: Int): T {
            return window.requireViewById<T>(id)
        }
    }

}
