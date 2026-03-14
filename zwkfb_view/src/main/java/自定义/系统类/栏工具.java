package 自定义.系统类;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;

import java.lang.reflect.Method;

/**
 * <pre>
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/9/23
 * desc  : 栏相关工具类
 </pre>
 */
public class 栏工具 {


    static class StatusBarView extends View {
        public StatusBarView(Context context) {
            super(context);
        }

        public StatusBarView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
        }
    }

    private 栏工具() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }


    public static final int DEFAULT_STATUS_BAR_ALPHA = 112;

    /**
     * 设置状态栏颜色
     *
     * @param 活动 需要设置的 activity
     * @param 颜色    状态栏颜色值
     */
    public static void 置颜色(Activity 活动, int 颜色) {
        置颜色(活动, 颜色, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置状态栏颜色
     *
     * @param 活动       需要设置的activity
     * @param 颜色          状态栏颜色值
     * @param 状态栏透明度 状态栏透明度
     */
    public static void 置颜色(Activity 活动, int 颜色, int 状态栏透明度) {
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        活动.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        活动.getWindow().setStatusBarColor(calculateStatusColor(颜色, 状态栏透明度));
    }

    /**
     * 为滑动返回界面设置状态栏颜色
     *
     * @param 活动 需要设置的activity
     * @param 颜色    状态栏颜色值
     */
    public static void setColorForSwipeBack(Activity 活动, int 颜色) {
        setColorForSwipeBack(活动, 颜色, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 为滑动返回界面设置状态栏颜色
     *
     * @param 活动       需要设置的activity
     * @param 颜色          状态栏颜色值
     * @param 状态栏透明度 状态栏透明度
     */
    public static void setColorForSwipeBack(Activity 活动, int 颜色, int 状态栏透明度) {
        ViewGroup contentView = 活动.findViewById(android.R.id.content);
        contentView.setPadding(0, getStatusBarHeight(活动), 0, 0);
        contentView.setBackgroundColor(calculateStatusColor(颜色, 状态栏透明度));
        setTransparentForWindow(活动);
    }

    /**
     * 设置状态栏纯色 不加半透明效果
     *
     * @param 活动 需要设置的 activity
     * @param 颜色    状态栏颜色值
     */
    public static void setColorNoTranslucent(Activity 活动, int 颜色) {
        置颜色(活动, 颜色, 0);
    }


    /**
     * 设置状态栏颜色(5.0以下无半透明效果,不建议使用)
     *
     * @param 活动 需要设置的 activity
     * @param 颜色    状态栏颜色值
     */
    public static void setColorDiff(Activity 活动, int 颜色) {
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 生成一个状态栏大小的矩形
        ViewGroup decorView = (ViewGroup)活动.getWindow().getDecorView();
        int count = decorView.getChildCount();
        if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
            decorView.getChildAt(count - 1).setBackgroundColor(颜色);
        } else {
            StatusBarView statusView = createStatusBarView(活动, 颜色);
            decorView.addView(statusView);
        }
        setRootView(活动);
    }


    /**
     * 使状态栏半透明
     *
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param 活动 需要设置的activity
     */
    public static void setTranslucent(Activity 活动) {
        setTranslucent(活动, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 使状态栏半透明
     *
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param 活动       需要设置的activity
     * @param 状态栏透明度 状态栏透明度
     */
    public static void setTranslucent(Activity 活动, int 状态栏透明度) {
        setTransparent(活动);
        addTranslucentView(活动, 状态栏透明度);
    }

    /**
     * 针对根布局是 CoordinatorLayout, 使状态栏半透明
     *
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param 活动       需要设置的activity
     * @param 状态栏透明度 状态栏透明度
     */
    public static void setTranslucentForCoordinatorLayout(Activity 活动, int 状态栏透明度) {
        transparentStatusBar(活动);
        addTranslucentView(活动, 状态栏透明度);
    }

    /**
     * 设置状态栏全透明
     *
     * @param 活动 需要设置的activity
     */
    public static void setTransparent(Activity 活动) {
        transparentStatusBar(活动);
        setRootView(活动);
    }

    /**
     * 使状态栏透明(5.0以上半透明效果,不建议使用)
     *
     *
     * 适用于图片作为背景的界面,此时需要图片填充到状态栏
     *
     * @param 活动 需要设置的activity
     */
    public static void setTranslucentDiff(Activity 活动) {
        // 设置状态栏透明
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        setRootView(活动);
    }

    /**
     * 为DrawerLayout 布局设置状态栏变色
     *
     * @param 活动     需要设置的activity
     * @param 抽屉布局 DrawerLayout
     * @param 颜色        状态栏颜色值
     */
    public static void setColorForDrawerLayout(Activity 活动, DrawerLayout 抽屉布局, int 颜色) {
        setColorForDrawerLayout(活动, 抽屉布局, 颜色, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 为DrawerLayout 布局设置状态栏颜色,纯色
     *
     * @param 活动     需要设置的activity
     * @param 抽屉布局 DrawerLayout
     * @param 颜色        状态栏颜色值
     */
    public static void setColorNoTranslucentForDrawerLayout(Activity 活动, DrawerLayout 抽屉布局, int 颜色) {
        setColorForDrawerLayout(活动, 抽屉布局, 颜色, 0);
    }

    /**
     * 为DrawerLayout 布局设置状态栏变色
     *
     * @param 活动       需要设置的activity
     * @param 抽屉布局   DrawerLayout
     * @param 颜色          状态栏颜色值
     * @param 状态栏透明度 状态栏透明度
     */
    public static void setColorForDrawerLayout(Activity 活动, DrawerLayout 抽屉布局, int 颜色, int 状态栏透明度) {
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        活动.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        活动.getWindow().setStatusBarColor(Color.TRANSPARENT);
        // 生成一个状态栏大小的矩形
        // 添加 statusBarView 到布局中
        ViewGroup contentLayout = (ViewGroup)抽屉布局.getChildAt(0);
        if (contentLayout != null && contentLayout.getChildAt(0) instanceof StatusBarView) {
            contentLayout.getChildAt(0)
                    .setBackgroundColor(calculateStatusColor(颜色, 状态栏透明度));
        } else {
            StatusBarView statusBarView  = createStatusBarView(活动, 颜色);
            contentLayout.addView(statusBarView, 0);
        }
        // 内容布局不是 LinearLayout 时,设置padding top
        if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
            contentLayout.getChildAt(1)
                    .setPadding(
                            contentLayout.getPaddingLeft(),
                            getStatusBarHeight(活动) + contentLayout.getPaddingTop(),
                            contentLayout.getPaddingRight(),
                            contentLayout.getPaddingBottom()
                    );
        }
        // 设置属性
        ViewGroup drawer = (ViewGroup)抽屉布局.getChildAt(1);
        抽屉布局.setFitsSystemWindows(false);
        contentLayout.setFitsSystemWindows(false);
        contentLayout.setClipToPadding(true);
        drawer.setFitsSystemWindows(false);
        addTranslucentView(活动, 状态栏透明度);
    }

    /**
     * 为DrawerLayout 布局设置状态栏变色(5.0以下无半透明效果,不建议使用)
     *
     * @param 活动     需要设置的activity
     * @param 抽屉布局 DrawerLayout
     * @param 颜色        状态栏颜色值
     */
    public static void setColorForDrawerLayoutDiff(Activity 活动, DrawerLayout 抽屉布局, int 颜色) {
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 生成一个状态栏大小的矩形
        ViewGroup contentLayout = (ViewGroup)抽屉布局.getChildAt(0);
        if (contentLayout != null && contentLayout.getChildAt(0) instanceof StatusBarView) {
            contentLayout.getChildAt(0)
                    .setBackgroundColor(calculateStatusColor(颜色, DEFAULT_STATUS_BAR_ALPHA));
        } else {
            // 添加 statusBarView 到布局中
            StatusBarView statusBarView = createStatusBarView(活动, 颜色);
            contentLayout.addView(statusBarView, 0);
        }
        // 内容布局不是 LinearLayout 时,设置padding top
        if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
            contentLayout.getChildAt(1).setPadding(0, getStatusBarHeight(活动), 0, 0);
        }
        // 设置属性
        ViewGroup drawer = (ViewGroup)抽屉布局.getChildAt(1);
        抽屉布局.setFitsSystemWindows(false);
        contentLayout.setFitsSystemWindows(false);
        contentLayout.setClipToPadding(true);
        drawer.setFitsSystemWindows(false);
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明
     *
     * @param 活动     需要设置的activity
     * @param 抽屉布局 DrawerLayout
     */
    public static void setTranslucentForDrawerLayout(Activity 活动, DrawerLayout 抽屉布局) {
        setTranslucentForDrawerLayout(活动, 抽屉布局, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明
     *
     * @param 活动     需要设置的activity
     * @param 抽屉布局 DrawerLayout
     */
    public static void setTranslucentForDrawerLayout(Activity 活动, DrawerLayout 抽屉布局, int 状态栏透明度) {
        setTransparentForDrawerLayout(活动, 抽屉布局);
        addTranslucentView(活动, 状态栏透明度);
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明
     *
     * @param 活动     需要设置的activity
     * @param 抽屉布局 DrawerLayout
     */
    public static void setTransparentForDrawerLayout(Activity 活动, DrawerLayout 抽屉布局) {
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        活动.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        活动.getWindow().setStatusBarColor(Color.TRANSPARENT);

        ViewGroup contentLayout = (ViewGroup)抽屉布局.getChildAt(0);
        // 内容布局不是 LinearLayout 时,设置padding top
        if (!(contentLayout instanceof LinearLayout) && contentLayout.getChildAt(1) != null) {
            contentLayout.getChildAt(1).setPadding(0, getStatusBarHeight(活动), 0, 0);
        }

        // 设置属性
        ViewGroup drawer = (ViewGroup)抽屉布局.getChildAt(1);
        抽屉布局.setFitsSystemWindows(false);
        contentLayout.setFitsSystemWindows(false);
        contentLayout.setClipToPadding(true);
        drawer.setFitsSystemWindows(false);
    }

    /**
     * 为 DrawerLayout 布局设置状态栏透明(5.0以上半透明效果,不建议使用)
     *
     * @param 活动     需要设置的activity
     * @param 抽屉布局 DrawerLayout
     */
    public static void setTranslucentForDrawerLayoutDiff(Activity 活动, DrawerLayout 抽屉布局) {
        // 设置状态栏透明
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 设置内容布局属性
        ViewGroup contentLayout = (ViewGroup)抽屉布局.getChildAt(0);
        contentLayout.setFitsSystemWindows(true);
        contentLayout.setClipToPadding(true);
        // 设置抽屉布局属性
        ViewGroup vg = (ViewGroup)抽屉布局.getChildAt(1);
        vg.setFitsSystemWindows(false);
        // 设置 抽屉布局 属性
        抽屉布局.setFitsSystemWindows(false);
    }


    /**
     * 为头部是 ImageView 的界面设置状态栏全透明
     *
     * @param 活动       需要设置的activity
     * @param 需要偏移视图 需要向下偏移的 View
     */
    public static void setTransparentForImageView(Activity 活动, View 需要偏移视图) {
        setTranslucentForImageView(活动, 0, 需要偏移视图);
    }

    /**
     * 为头部是 ImageView 的界面设置状态栏透明(使用默认透明度)
     *
     * @param 活动       需要设置的activity
     * @param 需要偏移视图 需要向下偏移的 View
     */
    public static void setTranslucentForImageView(Activity 活动 , View 需要偏移视图) {
        setTranslucentForImageView(活动, DEFAULT_STATUS_BAR_ALPHA, 需要偏移视图);
    }

    /**
     * 为头部是 ImageView 的界面设置状态栏透明
     *
     * @param 活动       需要设置的activity
     * @param 状态栏透明度 状态栏透明度
     * @param 需要偏移视图 需要向下偏移的 View
     */
    public static void setTranslucentForImageView(Activity 活动, int 状态栏透明度, View 需要偏移视图) {
        setTransparentForWindow(活动);
        addTranslucentView(活动, 状态栏透明度);
        if (需要偏移视图 != null) {
            ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams)需要偏移视图.getLayoutParams();
            layoutParams.setMargins(0, getStatusBarHeight(活动), 0, 0);
        }
    }

    /**
     * 为 fragment 头部是 ImageView 的设置状态栏透明
     *
     * @param 活动       fragment 对应的 activity
     * @param 需要偏移视图 需要向下偏移的 View
     */
    public static void setTranslucentForImageViewInFragment(Activity 活动, View 需要偏移视图) {
        setTranslucentForImageViewInFragment(活动, DEFAULT_STATUS_BAR_ALPHA, 需要偏移视图);
    }

    /**
     * 为 fragment 头部是 ImageView 的设置状态栏透明
     *
     * @param 活动       fragment 对应的 activity
     * @param 需要偏移视图 需要向下偏移的 View
     */
    public static void setTransparentForImageViewInFragment(Activity 活动, View 需要偏移视图) {
        setTranslucentForImageViewInFragment(活动, 0, 需要偏移视图);
    }

    /**
     * 为 fragment 头部是 ImageView 的设置状态栏透明
     *
     * @param 活动       fragment 对应的 activity
     * @param 状态栏透明度 状态栏透明度
     * @param 需要偏移视图 需要向下偏移的 View
     */
    public static void setTranslucentForImageViewInFragment(Activity 活动, int 状态栏透明度, View 需要偏移视图) {
        setTranslucentForImageView(活动, 状态栏透明度, 需要偏移视图);
        clearPreviousSetting(活动);
    }

    private static void clearPreviousSetting(Activity 活动) {
        ViewGroup decorView = (ViewGroup)活动.getWindow().getDecorView();
        int count = decorView.getChildCount();
        if (count > 0 && decorView.getChildAt(count - 1) instanceof StatusBarView) {
            decorView.removeViewAt(count - 1);
            ViewGroup rootView =(ViewGroup)((ViewGroup)活动.findViewById(android.R.id.content)).getChildAt(0);
            rootView.setPadding(0, 0, 0, 0);
        }
    }

    /**
     * 添加半透明矩形条
     *
     * @param 活动       需要设置的 activity
     * @param 状态栏透明度 透明值
     */
    private static void addTranslucentView(Activity 活动, int 状态栏透明度) {
        ViewGroup contentView = 活动.findViewById(android.R.id.content);
        if (contentView.getChildCount() > 1) {
            contentView.getChildAt(1).setBackgroundColor(Color.argb(状态栏透明度, 0, 0, 0));
        } else {
            contentView.addView(createTranslucentStatusBarView(活动, 状态栏透明度));
        }
    }

    /**
     * 生成一个和状态栏大小相同的彩色矩形条
     *
     * @param 活动 需要设置的 activity
     * @param 颜色    状态栏颜色值
     * @return 状态栏矩形条
     */
    private static StatusBarView createStatusBarView(Activity 活动, int 颜色)  {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(活动);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(活动));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(颜色);
        return statusBarView;
    }

    /**
     * 生成一个和状态栏大小相同的半透明矩形条
     *
     * @param 活动 需要设置的activity
     * @param 颜色    状态栏颜色值
     * @param 透明度    透明值
     * @return 状态栏矩形条
     */
    private static StatusBarView createStatusBarView(Activity 活动, int 颜色, int 透明度) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(活动);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(活动));
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(calculateStatusColor(颜色, 透明度));
        return statusBarView;
    }

    /**
     * 设置根布局参数
     */
    private static void setRootView(Activity 活动) {
        ViewGroup rootView = (ViewGroup)((ViewGroup)活动.findViewById(android.R.id.content)).getChildAt(0);
        rootView.setFitsSystemWindows(true);
        rootView.setClipToPadding(true);
    }

    /**
     * 设置透明
     */
    private static void setTransparentForWindow(Activity 活动) {
        活动.getWindow().setStatusBarColor(Color.TRANSPARENT);
        活动.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    /**
     * 使状态栏透明
     */
    private static void transparentStatusBar(Activity 活动) {
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        活动.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        活动.getWindow().setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 创建半透明矩形 View
     *
     * @param 透明度 透明值
     * @return 半透明 View
     */
    private static StatusBarView createTranslucentStatusBarView(Activity 活动, int 透明度) {
        // 绘制一个和状态栏一样高的矩形
        StatusBarView statusBarView = new StatusBarView(活动);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                getStatusBarHeight(活动)
        );
        statusBarView.setLayoutParams(params);
        statusBarView.setBackgroundColor(Color.argb(透明度, 0, 0, 0));
        return statusBarView;
    }

    /**
     * 获取状态栏高度
     *
     * @param 上下文 context
     * @return 状态栏高度
     */
    @SuppressLint("InternalInsetResource")
    public static int getStatusBarHeight(Context 上下文) {
        int result = -1;
         int resourceId = 上下文.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = 上下文.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 计算状态栏颜色
     *
     * @param 颜色 color值
     * @param 透明度 alpha值
     * @return 最终的状态栏颜色
     */
    private static int calculateStatusColor(int 颜色, int 透明度) {
        float a = 1 - 透明度 / 255f;
        int red = 颜色 >> 16 & 0xff;
        int green = 颜色 >> 8 & 0xff;
        int blue = 颜色 & 0xff;
        red = (int)(red * a + 0.5);
        green = (int)(green * a + 0.5);
        blue = (int)(blue * a + 0.5);
        return 0xff << 24 | (red << 16) | (green << 8) | blue;
    }



    /*--------------------------------old--------------------------------*/
    /**
     * 设置透明状态栏(api大于19方可使用)
     * <p>
     * 可在Activity的onCreat()中调用
     * <p>
     * 需在顶部控件布局中加入以下属性让内容出现在状态栏之下
     * <p>
     * android:clipToPadding="true"
     * <p>
     * android:fitsSystemWindows="true"
     * @param 活动 activity
     */
    public static void setTransparentStatusBar(Activity 活动) {
        //透明状态栏
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        活动.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }

    /**
     * 隐藏状态栏
     *
     * 也就是设置全屏，一定要在setContentView之前调用，否则报错
     *
     * 此方法Activity可以继承AppCompatActivity
     *
     * 启动的时候状态栏会显示一下再隐藏，比如QQ的欢迎界面
     *
     * 在配置文件中Activity加属性android:theme="@android:style/Theme.NoTitleBar.Fullscreen"
     *
     * 如加了以上配置Activity不能继承AppCompatActivity，会报错
     *
     * @param 活动 activity
     */
    public static void hideStatusBar(Activity 活动) {
        活动.requestWindowFeature(Window.FEATURE_NO_TITLE);
        活动.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    /**
     * 判断状态栏是否存在
     *
     * @param 活动 activity
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    public static boolean isStatusBarExists(Activity 活动) {
        WindowManager.LayoutParams params = 活动.getWindow().getAttributes();
        return (params.flags & WindowManager.LayoutParams.FLAG_FULLSCREEN) != WindowManager.LayoutParams.FLAG_FULLSCREEN;
    }

    /**
     * 获取ActionBar高度
     *
     * @param 活动 activity
     * @return ActionBar高度
     */
    public static int getActionBarHeight(Activity 活动) {
        TypedValue tv = new TypedValue();
        if (活动.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            return TypedValue.complexToDimensionPixelSize(tv.data,活动.getResources().getDisplayMetrics());
        }
        return 0;
    }

    /**
     * 显示通知栏
     *
     * 需添加权限 `<uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>`
     *
     * @param 上下文        上下文
     * @param 是否设置面板 `true`: 打开设置<br></br>`false`: 打开通知
     */
    public static void showNotificationBar(Context 上下文, boolean 是否设置面板) {
        String 方法名 = 是否设置面板? "expandSettingsPanel" : "expandNotificationsPanel";
        invokePanels(上下文, 方法名);
    }

    /**
     * 隐藏通知栏
     * <p>
     * 需添加权限 `<uses-permission android:name="android.permission.EXPAND_STATUS_BAR"/>`
     *
     * @param 上下文 上下文
     */
    public static void hideNotificationBar(Context 上下文) {
        String 方法名 = "collapsePanels";
        invokePanels(上下文, 方法名);
    }

    /**
     * 反射唤醒通知栏
     *
     * @param 上下文    上下文
     * @param 方法名 方法名
     */
    @SuppressLint("WrongConstant")
    private static void invokePanels(Context 上下文, String 方法名) {
        try {
            Object service = 上下文.getSystemService("statusbar");
            Class<?> statusBarManager = Class.forName("android.app.StatusBarManager");
            Method expand = statusBarManager.getMethod(方法名);
            expand.invoke(service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
