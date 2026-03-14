package 自定义.状态栏类;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import org.jspecify.annotations.NonNull;

import 安卓x.核心.视图.窗口兼容;
import 自定义.主题类.主题类;
import 自定义.系统类.系统类;

public class 状态栏沉浸式类 {

    public static void 状态栏沉浸设置(Activity 上下文) {
        状态栏沉浸式.初始沉浸式(上下文).透明栏().状态栏深色字体(!主题类.是否是深色模式(上下文))
                .导航栏深色图标(!主题类.是否是深色模式(上下文)).初始化();
        if (系统类.是否为手机(上下文)) {
            显示状态栏导航栏(上下文);
//            if (上下文.是否处于竖屏()) {
//            } else {
//                隐藏状态栏导航栏(上下文, 根视图)
//            }
        }
    }

    //==============================================================================================

    public static void 隐藏状态栏导航栏(Activity 上下文) {
        隐藏状态栏导航栏(上下文, 上下文.getWindow());
    }

    public static void 隐藏状态栏导航栏(Activity 上下文, Window 窗口) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false);

        窗口.setStatusBarColor(Color.TRANSPARENT); // 状态栏透明
        窗口.setNavigationBarColor(Color.TRANSPARENT); // 导航栏透明

        // 获取控制器对象
        WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(窗口, 窗口.getDecorView());
        windowCompat.setAppearanceLightStatusBars(!主题类.是否是深色模式(上下文)); // 状态栏字体颜色
        windowCompat.setAppearanceLightNavigationBars(!主题类.是否是深色模式(上下文)); // 导航栏字体颜色
        windowCompat.hide(WindowInsetsCompat.Type.statusBars()); // 隐藏状态栏
        windowCompat.hide(WindowInsetsCompat.Type.navigationBars()); // 隐藏导航栏
        windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE); // 滑动呼出后再次自动隐藏

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (系统类.是否处于横屏(上下文)){
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                        return insets;
                    }
                });
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
                        v.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom);
                        return insets;
                    }
                });
            }
        }else{
            窗口.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            窗口.decorView.systemUiVisibility = (
//                // 沉浸粘性模式：用户滑动后自动重新隐藏
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        // 隐藏导航栏
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        // 隐藏状态栏
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        // 布局延伸到系统栏下方（防止布局跳动）
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )

            ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                @Override
                public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                    Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                    return insets;
                }
            });
        }
    }

    //==============================================================================================
    //==============================================================================================

    public static void 隐藏状态栏(Activity 上下文) {
        隐藏状态栏(上下文, 上下文.getWindow());
    }

    public static void 隐藏状态栏(Activity 上下文, Window 窗口) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false);

        窗口.setStatusBarColor(Color.TRANSPARENT); // 状态栏透明
        窗口.setNavigationBarColor(Color.TRANSPARENT); // 导航栏透明

        // 获取控制器对象
        WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(窗口, 窗口.getDecorView());
        windowCompat.setAppearanceLightStatusBars(!主题类.是否是深色模式(上下文)); // 状态栏字体颜色
        windowCompat.setAppearanceLightNavigationBars(!主题类.是否是深色模式(上下文)); // 导航栏字体颜色
        windowCompat.hide(WindowInsetsCompat.Type.statusBars()); // 隐藏状态栏
        windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE); // 滑动呼出后再次自动隐藏


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (系统类.是否处于横屏(上下文)){
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                        return insets;
                    }
                });
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
                        v.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom);
                        return insets;
                    }
                });
            }
        }else{
//            窗口.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
//            窗口.decorView.systemUiVisibility = (
//                // 沉浸粘性模式：用户滑动后自动重新隐藏
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        // 隐藏状态栏
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        // 布局延伸到系统栏下方（防止布局跳动）
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )
            窗口.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

            ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                @Override
                public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                    Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                    return insets;
                }
            });
        }
    }


    public static void 隐藏导航栏(Activity 上下文) {
        隐藏导航栏(上下文, 上下文.getWindow());
    }

    public static void 隐藏导航栏(Activity 上下文, Window 窗口) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false);

        窗口.setStatusBarColor(Color.TRANSPARENT); // 状态栏透明
        窗口.setNavigationBarColor(Color.TRANSPARENT); // 导航栏透明

        // 获取控制器对象
        WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(窗口, 窗口.getDecorView());
        windowCompat.setAppearanceLightNavigationBars(!主题类.是否是深色模式(上下文)); // 导航栏字体颜色
        windowCompat.hide(WindowInsetsCompat.Type.navigationBars()); // 隐藏导航栏
        windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE); // 滑动呼出后再次自动隐藏


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (系统类.是否处于横屏(上下文)){
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                        return insets;
                    }
                });
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
                        v.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom);
                        return insets;
                    }
                });
            }
        }else{
            窗口.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//            窗口.decorView.systemUiVisibility = (
//                // 沉浸粘性模式：用户滑动后自动重新隐藏
//                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        // 隐藏导航栏
//                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                        // 隐藏状态栏
//                        or View.SYSTEM_UI_FLAG_FULLSCREEN
//                        // 布局延伸到系统栏下方（防止布局跳动）
//                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//            )

            ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                @Override
                public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                    Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                    return insets;
                }
            });
        }
    }

    //==============================================================================================
    //==============================================================================================

    public static void 显示状态栏导航栏(Activity 上下文) {
        显示状态栏导航栏(上下文, 上下文.getWindow());
    }

    public static void 显示状态栏导航栏(Activity 上下文, Window 窗口) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false);

        窗口.setStatusBarColor(Color.TRANSPARENT); // 状态栏透明
        窗口.setNavigationBarColor(Color.TRANSPARENT); // 导航栏透明

        // 获取控制器对象
        WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(窗口, 窗口.getDecorView());
        windowCompat.setAppearanceLightStatusBars(!主题类.是否是深色模式(上下文)); // 状态栏字体颜色
        windowCompat.setAppearanceLightNavigationBars(!主题类.是否是深色模式(上下文)); // 导航栏字体颜色
        windowCompat.show(WindowInsetsCompat.Type.systemBars()); // 显示状态栏
        windowCompat.show(WindowInsetsCompat.Type.navigationBars()); // 显示导航栏
//        windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE); // 滑动呼出后再次自动隐藏

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (系统类.是否处于横屏(上下文)) {
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBar.left, 状态栏沉浸式.取状态栏高度(上下文), systemBar.right, systemBar.bottom);
                        return insets;
                    }
                });
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
                        v.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom);
                        return insets;
                    }
                });
            }
        }else{
            ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                @Override
                public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                    Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                    return insets;
                }
            });
        }

    }

    //==============================================================================================
    //==============================================================================================

    public static void 显示状态栏(Activity 上下文) {
        显示状态栏(上下文, 上下文.getWindow());
    }

    public static void 显示状态栏(Activity 上下文, Window 窗口) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false);

        窗口.setStatusBarColor(Color.TRANSPARENT); // 状态栏透明
        窗口.setNavigationBarColor(Color.TRANSPARENT); // 导航栏透明

        // 获取控制器对象
        WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(窗口, 窗口.getDecorView());
        windowCompat.setAppearanceLightStatusBars(!主题类.是否是深色模式(上下文)); // 状态栏字体颜色
        windowCompat.setAppearanceLightNavigationBars(!主题类.是否是深色模式(上下文)); // 导航栏字体颜色
        windowCompat.show(WindowInsetsCompat.Type.statusBars()); // 显示状态栏
        windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE); // 滑动呼出后再次自动隐藏

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (系统类.是否处于横屏(上下文)) {
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBar.left, 状态栏沉浸式.取状态栏高度(上下文), systemBar.right, systemBar.bottom);
                        return insets;
                    }
                });
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
                        v.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom);
                        return insets;
                    }
                });
            }
        }else{
            ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                @Override
                public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                    Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                    return insets;
                }
            });
        }

    }


    public static void 显示导航栏(Activity 上下文) {
        显示导航栏(上下文, 上下文.getWindow());
    }

    public static void 显示导航栏(Activity 上下文, Window 窗口) {
        窗口兼容.置装饰视图适应系统窗口(窗口, false);

        窗口.setStatusBarColor(Color.TRANSPARENT); // 状态栏透明
        窗口.setNavigationBarColor(Color.TRANSPARENT); // 导航栏透明

        // 获取控制器对象
        WindowInsetsControllerCompat windowCompat = WindowCompat.getInsetsController(窗口, 窗口.getDecorView());
        windowCompat.setAppearanceLightStatusBars(!主题类.是否是深色模式(上下文)); // 状态栏字体颜色
        windowCompat.setAppearanceLightNavigationBars(!主题类.是否是深色模式(上下文)); // 导航栏字体颜色
        windowCompat.show(WindowInsetsCompat.Type.navigationBars()); // 显示状态栏
        windowCompat.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE); // 滑动呼出后再次自动隐藏


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (系统类.是否处于横屏(上下文)) {
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                        v.setPadding(systemBar.left, 状态栏沉浸式.取状态栏高度(上下文), systemBar.right, systemBar.bottom);
                        return insets;
                    }
                });
            }else{
                ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                    @Override
                    public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                        Insets displayCutout = insets.getInsets(WindowInsetsCompat.Type.displayCutout());
                        v.setPadding(displayCutout.left, displayCutout.top, displayCutout.right, displayCutout.bottom);
                        return insets;
                    }
                });
            }
        }else{
            ViewCompat.setOnApplyWindowInsetsListener(窗口.getDecorView(), new OnApplyWindowInsetsListener() {
                @Override
                public @NonNull WindowInsetsCompat onApplyWindowInsets(@NonNull View v, @NonNull WindowInsetsCompat insets) {
                    Insets systemBar = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBar.left, systemBar.top, systemBar.right, systemBar.bottom);
                    return insets;
                }
            });
        }

    }

    //==============================================================================================
    //==============================================================================================

    public static void 置状态栏颜色(Activity 上下文, int 颜色) {
        上下文.getWindow().setStatusBarColor(颜色);
    }

    //=============================================================

    public static void 置状态栏字体颜色为深色模式(Activity 上下文, boolean 值) {
        View decorView = 上下文.getWindow().getDecorView();
        if (值) {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    //=============================================================

    public static void 置导航栏颜色(Activity 上下文, int 颜色) {
        上下文.getWindow().setNavigationBarColor(颜色);
    }

    //=============================================================

    public static void 置导航栏图标颜色为深色模式(Activity 上下文, boolean 值) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            View decorView = 上下文.getWindow().getDecorView();
            if (值) {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            } else {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
            }
        }
    }

    //=============================================================

    @SuppressLint({"DiscouragedApi","InternalInsetResource"})
    public static int 用资源文件获取导航栏高度(Context 上下文) {
        boolean hasMenuKey = ViewConfiguration.get(上下文).hasPermanentMenuKey();
        if (!hasMenuKey) {
            Resources resources = 上下文.getResources();
            int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
            if (resourceId > 0) {
                return resources.getDimensionPixelSize(resourceId);
            }
        }
        return 0;
    }

    //=============================================================

    public static int 用DisplayCutout获取导航栏高度(Activity 上下文) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            WindowInsets windowInsets = 上下文.getWindow().getDecorView().getRootWindowInsets();
            if (windowInsets != null) {
                return windowInsets.getSystemWindowInsetBottom();
            }
        }
        return 0;
    }

    //=============================================================

    public static int 获取导航栏高度(View 根视图) {
        final int[] 导航栏高度 = {0};
        根视图.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                根视图.getWindowVisibleDisplayFrame(rect);
                int screenHeight = 根视图.getRootView().getHeight();
                int navigationBarHeight = screenHeight - rect.bottom;
                // 移除监听器
                根视图.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                // 使用 navigationBarHeight
                导航栏高度[0] = navigationBarHeight;
            }
        });
        return 导航栏高度[0];
    }

    //=============================================================

    @SuppressLint("CommitPrefEdits")
    public static int 获取导航栏高度(Context 上下文) {
        //PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences prefs = 上下文.getSharedPreferences("preferences", Context.MODE_PRIVATE);
        var height = prefs.getInt("navigation_bar_height", -1);
        if (height == -1) {
            height = 用资源文件获取导航栏高度(上下文);
            prefs.edit().putInt("navigation_bar_height", height);
        }
        return height;
    }



}
