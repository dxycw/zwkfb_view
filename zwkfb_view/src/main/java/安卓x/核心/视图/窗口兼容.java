package 安卓x.核心.视图;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.IdRes;
import androidx.annotation.RequiresApi;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import org.jspecify.annotations.NonNull;

import java.util.Objects;


/**
 * Helper for accessing features in {@link Window}.
 */
public final class 窗口兼容 { // WindowCompat
    /**
     * Flag for enabling the Action Bar.
     * This is enabled by default for some devices. The Action Bar
     * replaces the title bar and provides an alternate location
     * for an on-screen menu button on some devices.
     */
    public static final int 特征_行动_栏 = 8;

    /**
     * Flag for requesting an Action Bar that overlays window content.
     * Normally an Action Bar will sit in the space above window content, but if this
     * feature is requested along with {@link #特征_行动_栏} it will be layered over
     * the window content itself. This is useful if you would like your app to have more control
     * over how the Action Bar is displayed, such as letting application content scroll beneath
     * an Action Bar with a transparent background or otherwise displaying a transparent/translucent
     * Action Bar over application content.
     *
     * <p>This mode is especially useful with {@link View#SYSTEM_UI_FLAG_FULLSCREEN
     * View.SYSTEM_UI_FLAG_FULLSCREEN}, which allows you to seamlessly hide the
     * action bar in conjunction with other screen decorations.
     *
     * <p>As of {@link Build.VERSION_CODES#JELLY_BEAN}, when an
     * ActionBar is in this mode it will adjust the insets provided to
     * { View.fitSystemWindows(Rect)}
     * to include the content covered by the action bar, so you can do layout within
     * that space.
     */
    public static final int 特征_行动_栏_覆盖 = 9;

    /**
     * Flag for specifying the behavior of action modes when an Action Bar is not present.
     * If overlay is enabled, the action mode UI will be allowed to cover existing window content.
     */
    public static final int 特征_行动_模式_覆盖 = 10;

    private 窗口兼容() {}

    /**
     * Finds a view that was identified by the {@code android:id} XML attribute
     * that was processed in , or throws an
     * IllegalArgumentException if the ID is invalid, or there is no matching view in the hierarchy.
     * <p>
     * <strong>Note:</strong> In most cases -- depending on compiler support --
     * the resulting view is automatically cast to the target class type. If
     * the target class type is unconstrained, an explicit cast may be
     * necessary.
     *
     * @param window window in which to find the view.
     * @param id the ID to search for
     * @return a view with given ID
     * @see ViewCompat#requireViewById(View, int)
     * @see Window#findViewById(int)
     */
    @SuppressWarnings("TypeParameterUnusedInFormals")
    public static <T extends View> @NonNull T 要求视图通过Id(@NonNull Window window, @IdRes int id) {
        if (Build.VERSION.SDK_INT >= 28) {
            return Api28Impl.requireViewById(window, id);
        }

        T view = window.findViewById(id);
        if (view == null) {
            throw new IllegalArgumentException("ID does not reference a View inside this Window");
        }
        return view;
    }

    /**
     * Sets whether the decor view should fit root-level content views for
     * {@link WindowInsetsCompat}.
     * <p>
     * If set to {@code false}, the framework will not fit the content view to the insets and will
     * just pass through the {@link WindowInsetsCompat} to the content view.
     * </p>
     * <p>
     * Please note: using the {@link View#setSystemUiVisibility(int)} API in your app can
     * conflict with this method. Please discontinue use of {@link View#setSystemUiVisibility(int)}.
     * </p>
     *
     * @param 窗口                 The current window.
     * @param 装饰视图适应系统窗口 Whether the decor view should fit root-level content views for
     *                               insets.
     */
    public static void 置装饰视图适应系统窗口(@NonNull Window 窗口, final boolean 装饰视图适应系统窗口) {
        if (Build.VERSION.SDK_INT >= 35) {
            Api35Impl.setDecorFitsSystemWindows(窗口, 装饰视图适应系统窗口);
        } else if (Build.VERSION.SDK_INT >= 30) {
            Api30Impl.setDecorFitsSystemWindows(窗口, 装饰视图适应系统窗口);
        } else {
            Api16Impl.setDecorFitsSystemWindows(窗口, 装饰视图适应系统窗口);
        }
    }

    /**
     * Retrieves the single {@link WindowInsetsControllerCompat} of the window this view is attached
     * to.
     *
     * @return The {@link WindowInsetsControllerCompat} for the window.
     * @see Window#getInsetsController()
     */
    public static @NonNull WindowInsetsControllerCompat 取内边距控制器(@NonNull Window 窗口, @NonNull View 视图) {
        return new WindowInsetsControllerCompat(窗口, 视图);
    }

    /**
     * Enables the content of the given {@link Window window} to reach the edges of the window
     * without getting inset by system insets, and prevents the framework from placing color views
     * behind system bars.
     *
     * @param window the given window.
     */
    public static void 启用边缘到边缘(@NonNull Window window) {
        Objects.requireNonNull(window);

        // This triggers the initialization of the decor view here to prevent the attributes set by
        // this method from getting overwritten by the initialization later.
        window.getDecorView();

        置装饰视图适应系统窗口(window, false);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
        if (Build.VERSION.SDK_INT >= 28) {
            final int newMode = Build.VERSION.SDK_INT >= 30
                    ? WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
                    : WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            final WindowManager.LayoutParams attrs = window.getAttributes();
            if (attrs.layoutInDisplayCutoutMode != newMode) {
                attrs.layoutInDisplayCutoutMode = newMode;
                window.setAttributes(attrs);
            }
        }
        if (Build.VERSION.SDK_INT >= 29) {
            window.setStatusBarContrastEnforced(false);
            window.setNavigationBarContrastEnforced(false);
        }
    }

    static class Api16Impl {
        private Api16Impl() {
            // This class is not instantiable.
        }

        static void setDecorFitsSystemWindows(@NonNull Window window,
                                              final boolean decorFitsSystemWindows) {
            final int decorFitsFlags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

            final View decorView = window.getDecorView();
            final int sysUiVis = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(decorFitsSystemWindows
                    ? sysUiVis & ~decorFitsFlags
                    : sysUiVis | decorFitsFlags);
        }
    }

    @RequiresApi(30)
    static class Api30Impl {
        private Api30Impl() {
            // This class is not instantiable.
        }

        static void setDecorFitsSystemWindows(@NonNull Window window,
                                              final boolean decorFitsSystemWindows) {
            final int stableFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

            final View decorView = window.getDecorView();
            final int sysUiVis = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(decorFitsSystemWindows
                    ? sysUiVis & ~stableFlag
                    : sysUiVis | stableFlag);
            window.setDecorFitsSystemWindows(decorFitsSystemWindows);
        }
    }

    @RequiresApi(35)
    static class Api35Impl {
        private Api35Impl() {
            // This class is not instantiable.
        }

        static void setDecorFitsSystemWindows(@NonNull Window window,
                                              final boolean decorFitsSystemWindows) {
            window.setDecorFitsSystemWindows(decorFitsSystemWindows);
        }
    }

    @RequiresApi(28)
    static class Api28Impl {
        private Api28Impl() {
            // This class is not instantiable.
        }

        @SuppressWarnings({"unchecked", "TypeParameterUnusedInFormals"})
        static <T> T requireViewById(Window window, int id) {
            return (T) window.requireViewById(id);
        }
    }
}
