package 自定义.状态栏类;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;
import android.view.WindowManager;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.BarParams;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.NavigationBarType;
import com.gyf.immersionbar.NotchCallback;
import com.gyf.immersionbar.OnBarListener;
import com.gyf.immersionbar.OnKeyboardListener;
import com.gyf.immersionbar.OnNavigationBarListener;

import java.util.Objects;

import 安卓.操作系统.构建;
import 自定义.主题类.主题类;

public class 状态栏沉浸式 {

    @SuppressLint("StaticFieldLeak")
    private static ImmersionBar 沉浸式配置;

    public static 状态栏沉浸式 初始沉浸式(Activity 活动) {
        沉浸式配置 = ImmersionBar.with(活动);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(Activity 活动, boolean 是否仅) {
        沉浸式配置 = ImmersionBar.with(活动, 是否仅);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(Fragment 碎片) {
        沉浸式配置 = ImmersionBar.with(碎片);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(Fragment 碎片, boolean 是否仅) {
        沉浸式配置 = ImmersionBar.with(碎片, 是否仅);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(android.app.Fragment 碎片) {
        沉浸式配置 = ImmersionBar.with(碎片);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(android.app.Fragment 碎片, boolean 是否仅) {
        沉浸式配置 = ImmersionBar.with(碎片, 是否仅);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(DialogFragment 对话框碎片) {
        沉浸式配置 = ImmersionBar.with(对话框碎片);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(DialogFragment 对话框碎片, boolean 是否仅) {
        沉浸式配置 = ImmersionBar.with(对话框碎片, 是否仅);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(android.app.DialogFragment 对话框碎片) {
        沉浸式配置 = ImmersionBar.with(对话框碎片);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(android.app.DialogFragment 对话框碎片, boolean 是否仅) {
        沉浸式配置 = ImmersionBar.with(对话框碎片, 是否仅);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(Activity 活动, Dialog 对话框) {
        沉浸式配置 = ImmersionBar.with(活动, 对话框);
        return new 状态栏沉浸式();
    }

    public static 状态栏沉浸式 初始沉浸式(Activity 活动, Dialog 对话框, boolean 是否仅) {
        沉浸式配置 = ImmersionBar.with(活动, 对话框, 是否仅);
        return new 状态栏沉浸式();
    }

    //==============================================================================================

    //以下方法可以直接调用
    public static void 销毁(Fragment 碎片) {
        ImmersionBar.destroy(碎片);
    }

    public static void 销毁(Fragment 碎片, boolean 是否仅) {
        ImmersionBar.destroy(碎片,  是否仅);
    }

    public static void 销毁(android.app.Fragment 碎片) {
        ImmersionBar.destroy(碎片);
    }

    public static void 销毁(android.app.Fragment 碎片, boolean 是否仅) {
        ImmersionBar.destroy(碎片,  是否仅);
    }

    public static void 销毁(Activity 活动, Dialog 对话框) {
        ImmersionBar.destroy(活动, 对话框);
    }

    public static void 销毁(Activity 活动, Dialog 对话框, boolean 是否仅) {
        ImmersionBar.destroy(活动, 对话框, 是否仅);
    }

    //==============================================================================================

    public void 初始化(){
        沉浸式配置.init();
    }

    public void 运行(){
        沉浸式配置.run();
    }

    public 状态栏沉浸式 导航栏改变回调(boolean 显示, NavigationBarType 类型) {
        沉浸式配置.onNavigationBarChange(显示, 类型);
        return this;
    }

    /**
     * 获取栏参数
     * @return 返回当前状态栏参数
     */
    public BarParams 取栏参数() {
        return 沉浸式配置.getBarParams();
    }

    //==============================================================================================

    public static boolean 是否支持状态栏深色字体() {
        return ImmersionBar.isSupportStatusBarDarkFont();
    }

    public static boolean 是否支持导航栏图标深色() {
        return ImmersionBar.isSupportNavigationIconDark();
    }

    public static void 置标题栏(final Activity 活动, int 固定高度, View... 视图) {
        ImmersionBar.setTitleBar(活动, 固定高度, 视图);
    }

    public static void 置标题栏(final Activity 活动, View... 视图) {
        ImmersionBar.setTitleBar(活动, 视图);
    }

    public static void 置标题栏(Fragment 碎片, int 固定高度, View... 视图) {
        ImmersionBar.setTitleBar(碎片, 固定高度, 视图);
    }

    public static void 置标题栏(Fragment 碎片, View... 视图) {
        ImmersionBar.setTitleBar(碎片, 视图);
    }

    public static void 置标题栏(android.app.Fragment 碎片, int 固定高度, View... 视图) {
        ImmersionBar.setTitleBar(碎片, 固定高度, 视图);
    }

    public static void 置标题栏(android.app.Fragment 碎片, View... 视图) {
        ImmersionBar.setTitleBar(碎片, 视图);
    }

    public static void 置标题栏边距上(Activity 活动, int 固定高度, View... 视图) {
        ImmersionBar.setTitleBarMarginTop(活动, 固定高度, 视图);
    }

    /**
     * 为标题栏marginTop增加状态栏的高度
     * Sets title bar margin top.
     *
     * @param 活动 the activity
     * @param 视图     the view
     */
    public static void 置标题栏边距上(Activity 活动, View... 视图) {
        ImmersionBar.setTitleBarMarginTop(活动, 视图);
    }

    public static void 置标题栏边距上(Fragment 碎片, int 固定高度, View... 视图) {
        ImmersionBar.setTitleBarMarginTop(碎片, 固定高度, 视图);
    }

    public static void 置标题栏边距上(Fragment 碎片, View... 视图) {
        ImmersionBar.setTitleBarMarginTop(碎片, 视图);
    }

    public static void 置标题栏边距上(android.app.Fragment 碎片, int 固定高度, View... 视图) {
        ImmersionBar.setTitleBarMarginTop(碎片, 固定高度, 视图);
    }

    public static void 置标题栏边距上(android.app.Fragment 碎片, View... 视图) {
        ImmersionBar.setTitleBarMarginTop(碎片, 视图);
    }


    public static void 置状态栏视图(Activity 活动, int 固定高度, View... 视图) {
        ImmersionBar.setStatusBarView(活动, 固定高度, 视图);
    }

    /**
     * 单独在标题栏的位置增加view，高度为状态栏的高度
     * Sets status bar view.
     *
     * @param 活动 the activity
     * @param 视图     the view
     */
    public static void 置状态栏视图(Activity 活动, View... 视图) {
        ImmersionBar.setStatusBarView(活动, 视图);
    }

    public static void 置状态栏视图(Fragment 碎片, int 固定高度, View... 视图) {
        ImmersionBar.setStatusBarView(碎片, 固定高度, 视图);
    }

    public static void 置状态栏视图(Fragment 碎片, View... 视图) {
        ImmersionBar.setStatusBarView(碎片, 视图);
    }

    public static void 置状态栏视图(android.app.Fragment 碎片, int 固定高度, View... 视图) {
        ImmersionBar.setStatusBarView(碎片, 固定高度, 视图);
    }

    public static void 置状态栏视图(android.app.Fragment 碎片, View... 视图) {
        ImmersionBar.setStatusBarView(碎片, 视图);
    }


    /**
     * 调用系统view的setFitsSystemWindows方法
     * Sets fits system windows.
     *
     * @param 活动        the activity
     * @param 应用系统适配 the apply system fits
     */
    public static void 置适应系统窗口(Activity 活动, boolean 应用系统适配) {
        ImmersionBar.setFitsSystemWindows(活动, 应用系统适配);
    }

    public static void 置适应系统窗口(Activity 活动) {
        ImmersionBar.setFitsSystemWindows(活动);
    }

    public static void 置适应系统窗口(Fragment 碎片, boolean 应用系统适配) {
        ImmersionBar.setFitsSystemWindows(碎片, 应用系统适配);
    }

    public static void 置适应系统窗口(Fragment 碎片) {
        ImmersionBar.setFitsSystemWindows(碎片);
    }

    public static void 置适应系统窗口(android.app.Fragment 碎片, boolean 应用系统适配) {
        ImmersionBar.setFitsSystemWindows(碎片, 应用系统适配);
    }

    public static void 置适应系统窗口(android.app.Fragment 碎片) {
        ImmersionBar.setFitsSystemWindows(碎片);
    }

    /**
     * 检查布局根节点是否使用了android:fitsSystemWindows="true"属性
     * Check fits system windows boolean.
     *
     * @param 视图 the view
     * @return the boolean
     */
    public static boolean 检查是否适合系统窗口(View 视图) {
        return ImmersionBar.checkFitsSystemWindows(视图);
    }


    /**
     * Has navigtion bar boolean.
     * 判断是否存在导航栏
     *
     * @param 活动 the activity
     * @return the boolean
     */
    public static boolean 有导航栏(@NonNull Activity 活动) {
        return ImmersionBar.hasNavigationBar(活动);
    }

    public static boolean 有导航栏(@NonNull Fragment 碎片) {
        return ImmersionBar.hasNavigationBar(碎片);
    }

    public static boolean 有导航栏(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.hasNavigationBar(碎片);
    }

    public static boolean 有导航栏(@NonNull Context 上下文) {
        return ImmersionBar.hasNavigationBar(上下文);
    }


    /**
     * Gets navigation bar height.
     * 获得导航栏的高度
     *
     * @param 活动 the activity
     * @return the navigation bar height
     */
    public static int 取导航栏高度(@NonNull Activity 活动) {
        return ImmersionBar.getNavigationBarHeight(活动);
    }

    public static int 取导航栏高度(@NonNull Fragment 碎片) {
        return ImmersionBar.getNavigationBarHeight(碎片);
    }

    public static int 取导航栏高度(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.getNavigationBarHeight(碎片);
    }

    public static int 取导航栏高度(@NonNull Context 上下文) {
        return ImmersionBar.getNavigationBarHeight(上下文);
    }


    /**
     * Gets navigation bar width.
     * 获得导航栏的宽度
     *
     * @param 活动 the activity
     * @return the navigation bar width
     */
    public static int 取导航栏宽度(@NonNull Activity 活动){
        return ImmersionBar.getNavigationBarWidth(活动);
    }

    public static int 取导航栏宽度(@NonNull Fragment 碎片) {
        return ImmersionBar.getNavigationBarWidth(碎片);
    }

    public static int 取导航栏宽度(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.getNavigationBarWidth(碎片);
    }

    public static int 取导航栏宽度(@NonNull Context 上下文) {
        return ImmersionBar.getNavigationBarWidth(上下文);
    }

    /**
     * Is navigation at bottom boolean.
     * 判断导航栏是否在底部
     *
     * @param 活动 the 活动
     * @return the boolean
     */
    public static boolean 是否导航栏在底(@NonNull Activity 活动) {
        return ImmersionBar.isNavigationAtBottom(活动);
    }

    public static boolean 是否导航栏在底(@NonNull Fragment 碎片) {
        return ImmersionBar.isNavigationAtBottom(碎片);
    }

    public static boolean 是否导航栏在底(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.isNavigationAtBottom(碎片);
    }



    /**
     * Gets status bar height.
     * 或得状态栏的高度
     *
     * @param 活动 the activity
     * @return the status bar height
     */
    public static int 取状态栏高度(@NonNull Activity 活动) {
        return ImmersionBar.getStatusBarHeight(活动);
    }

    public static int 取状态栏高度(@NonNull Fragment 碎片) {
        return ImmersionBar.getStatusBarHeight(碎片);
    }

    public static int 取状态栏高度(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.getStatusBarHeight(碎片);
    }

    public static int 取状态栏高度(@NonNull Context 上下文) {
        return ImmersionBar.getStatusBarHeight(上下文);
    }


    /**
     * Gets action bar height.
     * 或得ActionBar得高度
     *
     * @param 活动 the activity
     * @return the action bar height
     */
    public static int 取操作栏高度(@NonNull Activity 活动) {
        return ImmersionBar.getActionBarHeight(活动);
    }

    public static int 取操作栏高度(@NonNull Fragment 碎片) {
        return ImmersionBar.getActionBarHeight(碎片);
    }

    public static int 取操作栏高度(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.getActionBarHeight(碎片);
    }



    /**
     * 是否是刘海屏
     * Has notch screen boolean.
     * e.g:getWindow().getDecorView().post(() -> ImmersionBar.hasNotchScreen(this));
     *
     * @param 活动 the activity
     * @return the boolean
     */
    public static boolean 有刘海屏(@NonNull Activity 活动) {
        return ImmersionBar.hasNotchScreen(活动);
    }

    public static boolean 有刘海屏(@NonNull Fragment 碎片) {
        return ImmersionBar.hasNotchScreen(碎片);
    }

    public static boolean 有刘海屏(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.hasNotchScreen(碎片);
    }

    /**
     * 是否是刘海屏
     * Has notch screen boolean.
     *
     * @param 视图 the view
     * @return the boolean
     */
    public static boolean 有刘海屏(@NonNull View 视图) {
        return ImmersionBar.hasNotchScreen(视图);
    }


    /**
     * 刘海屏高度
     * Notch height int.
     * e.g: getWindow().getDecorView().post(() -> ImmersionBar.getNotchHeight(this));
     *
     * @param 活动 the activity
     * @return the int
     */
    public static int 取刘海高度(@NonNull Activity 活动) {
        return ImmersionBar.getNotchHeight(活动);
    }

    public static int 取刘海高度(@NonNull Fragment 碎片) {
        return ImmersionBar.getNotchHeight(碎片);
    }

    public static int 取刘海高度(@NonNull android.app.Fragment 碎片) {
        return ImmersionBar.getNotchHeight(碎片);
    }

    public static void 取刘海高度(@NonNull Activity 活动, NotchCallback 回调) {
        ImmersionBar.getNotchHeight(活动, 回调);
    }

    public static void 取刘海高度(@NonNull Fragment 碎片, NotchCallback 回调) {
        ImmersionBar.getNotchHeight(碎片, 回调);
    }

    public static void 取刘海高度(@NonNull android.app.Fragment 碎片, NotchCallback callback) {
        ImmersionBar.getNotchHeight(碎片, callback);
    }


    /**
     * 隐藏状态栏
     * Hide status bar.
     *
     * @param 窗口 the window
     */
    public static void 隐藏状态栏(@NonNull Window 窗口) {
        ImmersionBar.hideStatusBar(窗口);
    }

    /**
     * 显示状态栏
     * Show status bar.
     *
     * @param 窗口 the window
     */
    public static void 显示状态栏(@NonNull Window 窗口) {
        ImmersionBar.showStatusBar(窗口);
    }



    /**
     * 是否是手势
     *
     * @param 上下文 Context
     * @return the boolean
     */
    public static boolean 是否手势(Context 上下文) {
        return ImmersionBar.isGesture(上下文);
    }

    /**
     * 是否是手势
     *
     * @param 碎片 Fragment
     * @return the boolean
     */
    public static boolean 是否手势(Fragment 碎片) {
        return ImmersionBar.isGesture(碎片);
    }

    /**
     * 是否是手势
     *
     * @param 碎片 android.app.Fragment
     * @return the boolean
     */
    public static boolean 是否手势(android.app.Fragment 碎片) {
        return ImmersionBar.isGesture(碎片);
    }

    //==============================================================================================

    /**
     * 透明状态栏，默认透明
     *
     * @return the immersion bar
     */
    public 状态栏沉浸式 透明状态栏() {
        沉浸式配置.transparentStatusBar();
        return this;
    }

    /**
     * 透明导航栏，默认黑色
     *
     * @return the immersion bar
     */
    public 状态栏沉浸式 透明导航栏() {
        沉浸式配置.transparentNavigationBar();
        return this;
    }

    /**
     * 透明状态栏和导航栏
     *
     * @return the immersion bar
     */
    public 状态栏沉浸式 透明栏() {
        沉浸式配置.transparentBar();
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色(@ColorRes int 状态栏颜色) {
        沉浸式配置.statusBarColor(状态栏颜色);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色 状态栏颜色，资源文件（R.color.xxx）
     * @param 透明度          the alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色(@ColorRes int 状态栏颜色, @FloatRange(from = 0f, to = 1f) float 透明度) {
        沉浸式配置.statusBarColor(状态栏颜色, 透明度);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色          状态栏颜色，资源文件（R.color.xxx）
     * @param 状态栏颜色转换 the status bar color transform 状态栏变换后的颜色
     * @param 透明度                   the alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色(@ColorRes int 状态栏颜色, @ColorRes int 状态栏颜色转换, @FloatRange(from = 0f, to = 1f) float 透明度) {
        沉浸式配置.statusBarColor(状态栏颜色, 状态栏颜色转换, 透明度);
        return this;
    }

    /**
     * 状态栏颜色
     * Status bar color int immersion bar.
     *
     * @param 状态栏颜色 the status bar color
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色(String 状态栏颜色) {
        沉浸式配置.statusBarColor(状态栏颜色);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色 状态栏颜色
     * @param 透明度          the alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色(String 状态栏颜色, @FloatRange(from = 0f, to = 1f) float 透明度) {
        沉浸式配置.statusBarColor(状态栏颜色, 透明度);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色          状态栏颜色
     * @param 状态栏颜色转换 the status bar color transform 状态栏变换后的颜色
     * @param 透明度                   the alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色(String 状态栏颜色, String 状态栏颜色转换, @FloatRange(from = 0f, to = 1f) float 透明度) {
        沉浸式配置.statusBarColor(状态栏颜色, 状态栏颜色转换, 透明度);
        return this;
    }




    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色 状态栏颜色，资源文件（R.color.xxx）
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色整型(@ColorInt int 状态栏颜色) {
        沉浸式配置.statusBarColorInt(状态栏颜色);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色 状态栏颜色，资源文件（R.color.xxx）
     * @param 透明度          the alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色整型(@ColorInt int 状态栏颜色, @FloatRange(from = 0f, to = 1f) float 透明度) {
        沉浸式配置.statusBarColorInt(状态栏颜色, 透明度);
        return this;
    }

    /**
     * 状态栏颜色
     *
     * @param 状态栏颜色          状态栏颜色，资源文件（R.color.xxx）
     * @param 状态栏颜色转换 the status bar color transform 状态栏变换后的颜色
     * @param 透明度                   the alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色整型(@ColorInt int 状态栏颜色, @ColorInt int 状态栏颜色转换, @FloatRange(from = 0f, to = 1f) float 透明度) {
        沉浸式配置.statusBarColorInt(状态栏颜色, 状态栏颜色转换, 透明度);
        return this;
    }



    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色 the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色(@ColorRes int 导航栏颜色) {
        沉浸式配置.navigationBarColor(导航栏颜色);
        return this;
    }

    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色 the navigation bar color 导航栏颜色
     * @param 导航透明度    the navigation alpha 透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色(@ColorRes int 导航栏颜色, @FloatRange(from = 0f, to = 1f) float 导航透明度) {
        沉浸式配置.navigationBarColor(导航栏颜色, 导航透明度);
        return this;
    }

    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色          the navigation bar color 导航栏颜色
     * @param 导航栏颜色转换 the navigation bar color transform  导航栏变色后的颜色
     * @param 导航透明度             the navigation alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色(@ColorRes int 导航栏颜色, @ColorRes int 导航栏颜色转换, @FloatRange(from = 0f, to = 1f) float 导航透明度) {
        沉浸式配置.navigationBarColor(导航栏颜色, 导航栏颜色转换, 导航透明度);
        return this;
    }

    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色 the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色(String 导航栏颜色) {
        沉浸式配置.navigationBarColor(导航栏颜色);
        return this;
    }

    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色 the navigation bar color 导航栏颜色
     * @param 导航透明度    the navigation alpha 透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色(String 导航栏颜色, @FloatRange(from = 0f, to = 1f) float 导航透明度) {
        沉浸式配置.navigationBarColor(导航栏颜色, 导航透明度);
        return this;
    }

    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色          the navigation bar color 导航栏颜色
     * @param 导航栏颜色转换 the navigation bar color transform  导航栏变色后的颜色
     * @param 导航透明度             the navigation alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色(String 导航栏颜色, String 导航栏颜色转换, @FloatRange(from = 0f, to = 1f) float 导航透明度) {
        沉浸式配置.navigationBarColor(导航栏颜色, 导航栏颜色转换, 导航透明度);
        return this;
    }



    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色 the navigation bar color 导航栏颜色
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色整型(@ColorInt int 导航栏颜色) {
        沉浸式配置.navigationBarColorInt(导航栏颜色);
        return this;
    }

    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色 the navigation bar color 导航栏颜色
     * @param 导航透明度    the navigation alpha 透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色整型(@ColorInt int 导航栏颜色, @FloatRange(from = 0f, to = 1f) float 导航透明度) {
        沉浸式配置.navigationBarColorInt(导航栏颜色, 导航透明度);
        return this;
    }

    /**
     * 导航栏颜色
     *
     * @param 导航栏颜色          the navigation bar color 导航栏颜色
     * @param 导航栏颜色转换 the navigation bar color transform  导航栏变色后的颜色
     * @param 导航透明度             the navigation alpha  透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色整型(@ColorInt int 导航栏颜色, @ColorInt int 导航栏颜色转换, @FloatRange(from = 0f, to = 1f) float 导航透明度) {
        沉浸式配置.navigationBarColorInt(导航栏颜色, 导航栏颜色转换, 导航透明度);
        return this;
    }





    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色 the bar color
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色(@ColorRes int 栏颜色) {
        沉浸式配置.barColor(栏颜色);
        return this;
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色 the bar color
     * @param 栏透明度 the bar alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色(@ColorRes int 栏颜色, @FloatRange(from = 0f, to = 1f) float 栏透明度) {
        沉浸式配置.barColor(栏颜色, 栏透明度);
        return this;
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色          the bar color
     * @param 栏颜色转换 the bar color transform
     * @param 栏透明度          the bar alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色(@ColorRes int 栏颜色, @ColorRes int 栏颜色转换, @FloatRange(from = 0f, to = 1f) float 栏透明度) {
        沉浸式配置.barColor(栏颜色, 栏颜色转换, 栏透明度);
        return this;
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色 the bar color
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色(String 栏颜色) {
        沉浸式配置.barColor(栏颜色);
        return this;
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色 the bar color
     * @param 栏透明度 the bar alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色(String 栏颜色, @FloatRange(from = 0f, to = 1f) float 栏透明度) {
        沉浸式配置.barColor(栏颜色, 栏透明度);
        return this;
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色          the bar color
     * @param 栏颜色转换 the bar color transform
     * @param 栏透明度          the bar alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色(String 栏颜色, String 栏颜色转换, @FloatRange(from = 0f, to = 1f) float 栏透明度) {
        沉浸式配置.barColor(栏颜色, 栏颜色转换, 栏透明度);
        return this;
    }



    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色 the bar color
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色整型(@ColorInt int 栏颜色) {
        沉浸式配置.barColorInt(栏颜色);
        return this;
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色 the bar color
     * @param 栏透明度 the bar alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色整型(@ColorInt int 栏颜色, @FloatRange(from = 0f, to = 1f) float 栏透明度) {
        沉浸式配置.barColorInt(栏颜色, 栏透明度);
        return this;
    }

    /**
     * 状态栏和导航栏颜色
     *
     * @param 栏颜色          the bar color
     * @param 栏颜色转换 the bar color transform
     * @param 栏透明度          the bar alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色整型(@ColorInt int 栏颜色, @ColorInt int 栏颜色转换, @FloatRange(from = 0f, to = 1f) float 栏透明度) {
        沉浸式配置.barColorInt(栏颜色, 栏颜色转换, 栏透明度);
        return this;
    }



    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param 状态栏颜色转换 the status bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色转换(@ColorRes int 状态栏颜色转换) {
        沉浸式配置.statusBarColorTransform(状态栏颜色转换);
        return this;
    }

    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param 状态栏颜色转换 the status bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色转换(String 状态栏颜色转换) {
        沉浸式配置.statusBarColorTransform(状态栏颜色转换);
        return this;
    }

    /**
     * 状态栏根据透明度最后变换成的颜色
     *
     * @param 状态栏颜色转换 the status bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色转换整型(@ColorInt int 状态栏颜色转换) {
        沉浸式配置.statusBarColorTransformInt(状态栏颜色转换);
        return this;
    }


    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param 导航栏颜色转换 the m navigation bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色转换(@ColorRes int 导航栏颜色转换) {
        沉浸式配置.navigationBarColorTransform(导航栏颜色转换);
        return this;
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param 导航栏颜色转换 the m navigation bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色转换(String 导航栏颜色转换) {
        沉浸式配置.navigationBarColorTransform(导航栏颜色转换);
        return this;
    }

    /**
     * 导航栏根据透明度最后变换成的颜色
     *
     * @param 导航栏颜色转换 the m navigation bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏颜色转换整型(@ColorInt int 导航栏颜色转换) {
        沉浸式配置.navigationBarColorTransformInt(导航栏颜色转换);
        return this;
    }



    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param 栏颜色转换 the bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色转换(@ColorRes int 栏颜色转换) {
        沉浸式配置.barColorTransform(栏颜色转换);
        return this;
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param 栏颜色转换 the bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色转换(String 栏颜色转换) {
        沉浸式配置.barColorTransform(栏颜色转换);
        return this;
    }

    /**
     * 状态栏和导航栏根据透明度最后变换成的颜色
     *
     * @param 栏颜色转换 the bar color transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏颜色转换整型(@ColorInt int 栏颜色转换) {
        沉浸式配置.barColorTransformInt(栏颜色转换);
        return this;
    }





    /**
     * Add 颜色变换支持View
     *
     * @param 视图 the view
     * @return the immersion bar
     */
    public 状态栏沉浸式 添加视图支持转换颜色(View 视图) {
        沉浸式配置.addViewSupportTransformColor(视图);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param 视图                    the view
     * @param 视图颜色转换后 the view color after transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 添加视图支持转换颜色(View 视图, @ColorRes int 视图颜色转换后) {
        沉浸式配置.addViewSupportTransformColor(视图, 视图颜色转换后);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param 视图                     the view
     * @param 视图颜色转换前 the view color before transform
     * @param 视图颜色转换后  the view color after transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 添加视图支持转换颜色(View 视图, @ColorRes int 视图颜色转换前, @ColorRes int 视图颜色转换后) {
        沉浸式配置.addViewSupportTransformColor(视图, 视图颜色转换前, 视图颜色转换后);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param 视图                    the view
     * @param 视图颜色转换后 the view color after transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 添加视图支持转换颜色(View 视图, String 视图颜色转换后) {
        沉浸式配置.addViewSupportTransformColor(视图, 视图颜色转换后);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param 视图                     the view
     * @param 视图颜色转换前 the view color before transform
     * @param 视图颜色转换后  the view color after transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 添加视图支持转换颜色(View 视图, String 视图颜色转换前, String 视图颜色转换后) {
        沉浸式配置.addViewSupportTransformColor(视图,视图颜色转换前, 视图颜色转换后);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param 视图                    the view
     * @param 视图颜色转换后 the view color after transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 添加视图支持转换颜色整型(View 视图, @ColorInt int 视图颜色转换后) {
        沉浸式配置.addViewSupportTransformColorInt(视图, 视图颜色转换后);
        return this;
    }

    /**
     * Add 颜色变换支持View
     *
     * @param 视图                     the view
     * @param 视图颜色转换前 the view color before transform
     * @param 视图颜色转换后  the view color after transform
     * @return the immersion bar
     */
    public 状态栏沉浸式 添加视图支持转换颜色整型(View 视图, @ColorInt int 视图颜色转换前, @ColorInt int 视图颜色转换后) {
        沉浸式配置.addViewSupportTransformColorInt(视图, 视图颜色转换前, 视图颜色转换后);
        return this;
    }



    /**
     * view透明度
     * View alpha immersion bar.
     *
     * @param 视图透明度 the view alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 视图透明度(@FloatRange(from = 0f, to = 1f) float 视图透明度) {
        沉浸式配置.viewAlpha(视图透明度);
        return this;
    }


    /**
     * Remove support view immersion bar.
     *
     * @param 视图 the view
     * @return the immersion bar
     */
    public 状态栏沉浸式 移除支持视图(View 视图) {
        沉浸式配置.removeSupportView(视图);
        return this;
    }

    /**
     * Remove support all view immersion bar.
     *
     * @return the immersion bar
     */
    public 状态栏沉浸式 移除支持所有视图() {
        沉浸式配置.removeSupportAllView();
        return this;
    }

    /**
     * 有导航栏的情况下，Activity是否全屏显示
     *
     * @param 是否全屏 the is full screen
     * @return the immersion bar
     */
    public 状态栏沉浸式 全屏(boolean 是否全屏) {
        沉浸式配置.fullScreen(是否全屏);
        return this;
    }


    /**
     * 状态栏透明度
     *
     * @param 状态栏透明度 the status alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏透明度(@FloatRange(from = 0f, to = 1f) float 状态栏透明度) {
        沉浸式配置.statusBarAlpha(状态栏透明度);
        return this;
    }

    /**
     * 导航栏透明度
     *
     * @param 导航栏透明度 the navigation alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏透明度(@FloatRange(from = 0f, to = 1f) float 导航栏透明度) {
        沉浸式配置.navigationBarAlpha(导航栏透明度);
        return this;
    }

    /**
     * 状态栏和导航栏透明度
     *
     * @param 栏透明度 the bar alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏透明度(@FloatRange(from = 0f, to = 1f) float 栏透明度) {
        沉浸式配置.barAlpha(栏透明度);
        return this;
    }


    /**
     * 是否启用 自动根据StatusBar和NavigationBar颜色调整深色模式与亮色模式
     *
     * @param 是否启用 true启用 默认false
     * @return the immersion bar
     */
    public 状态栏沉浸式 自动深色模式启用(boolean 是否启用) {
        沉浸式配置.autoDarkModeEnable(是否启用);
        return this;
    }

    /**
     * 是否启用自动根据StatusBar和NavigationBar颜色调整深色模式与亮色模式
     * Auto dark mode enable immersion bar.
     *
     * @param 是否启用          the is enable
     * @param 自动深色模式透明度 the auto dark mode alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 自动深色模式启用(boolean 是否启用, @FloatRange(from = 0f, to = 1f) float 自动深色模式透明度) {
        沉浸式配置.autoDarkModeEnable(是否启用, 自动深色模式透明度);
        return this;
    }

    /**
     * 是否启用自动根据StatusBar颜色调整深色模式与亮色模式
     * Auto status bar dark mode enable immersion bar.
     *
     * @param 是否启用 the is enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 自动状态栏深色模式启用(boolean 是否启用) {
        沉浸式配置.autoStatusBarDarkModeEnable(是否启用);
        return this;
    }

    /**
     * 是否启用自动根据StatusBar颜色调整深色模式与亮色模式
     * Auto status bar dark mode enable immersion bar.
     *
     * @param 是否启用          the is enable
     * @param 自动深色模式透明度 the auto dark mode alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 自动状态栏深色模式启用(boolean 是否启用, @FloatRange(from = 0f, to = 1f) float 自动深色模式透明度) {
        沉浸式配置.autoStatusBarDarkModeEnable(是否启用,自动深色模式透明度);
        return this;
    }


    /**
     * 是否启用自动根据StatusBar颜色调整深色模式与亮色模式
     * Auto navigation bar dark mode enable immersion bar.
     *
     * @param 是否启用 the is enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 自动导航栏深色模式启用(boolean 是否启用) {
        沉浸式配置.autoNavigationBarDarkModeEnable(是否启用);
        return this;
    }

    /**
     * 是否启用自动根据NavigationBar颜色调整深色模式与亮色模式
     * Auto navigation bar dark mode enable immersion bar.
     *
     * @param 是否启用          the is enable
     * @param 自动深色模式透明度 the auto dark mode alpha
     * @return the immersion bar
     */
    public 状态栏沉浸式 自动导航栏深色模式启用(boolean 是否启用, @FloatRange(from = 0f, to = 1f) float 自动深色模式透明度) {
        沉浸式配置.autoNavigationBarDarkModeEnable(是否启用, 自动深色模式透明度);
        return this;
    }

    /**
     * 状态栏字体深色或亮色
     *
     * @param 是否深色字体 true 深色
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏深色字体(boolean 是否深色字体) {
        沉浸式配置.statusBarDarkFont(是否深色字体);
        return this;
    }

    /**
     * 状态栏字体深色或亮色，判断设备支不支持状态栏变色来设置状态栏透明度
     * Status bar dark font immersion bar.
     *
     * @param 是否深色字体  the is dark font
     * @param 状态栏透明度 the status alpha 如果不支持状态栏字体变色可以使用statusAlpha来指定状态栏透明度，比如白色状态栏的时候可以用到
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏深色字体(boolean 是否深色字体, @FloatRange(from = 0f, to = 1f) float 状态栏透明度) {
        沉浸式配置.statusBarDarkFont(是否深色字体, 状态栏透明度);
        return this;
    }


    /**
     * 导航栏图标深色或亮色，只支持android o以上版本
     * Navigation bar dark icon immersion bar.
     *
     * @param 是否深色图标 the is dark icon
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏深色图标(boolean 是否深色图标) {
        沉浸式配置.navigationBarDarkIcon(是否深色图标);
        return this;
    }

    /**
     * 导航栏图标深色或亮色，只支持android o以上版本，判断设备支不支持导航栏图标变色来设置导航栏透明度
     * Navigation bar dark icon immersion bar.
     *
     * @param 是否深色图标      the is dark icon
     * @param 导航栏透明度 the navigation alpha 如果不支持导航栏图标变色可以使用navigationAlpha来指定导航栏透明度，比如白色导航栏的时候可以用到
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏深色图标(boolean 是否深色图标, @FloatRange(from = 0f, to = 1f) float 导航栏透明度) {
        沉浸式配置.navigationBarDarkIcon(是否深色图标, 导航栏透明度);
        return this;
    }


    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flyme操作系统状态栏字体颜色 the flyme os status bar font color
     * @return the immersion bar
     */
    public 状态栏沉浸式 flyme操作系统状态栏字体颜色(@ColorRes int flyme操作系统状态栏字体颜色) {
        沉浸式配置.flymeOSStatusBarFontColor(flyme操作系统状态栏字体颜色);
        return this;
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flyme操作系统状态栏字体颜色 the flyme os status bar font color
     * @return the immersion bar
     */
    public 状态栏沉浸式 flyme操作系统状态栏字体颜色(String flyme操作系统状态栏字体颜色) {
        沉浸式配置.flymeOSStatusBarFontColor(flyme操作系统状态栏字体颜色);
        return this;
    }

    /**
     * 修改 Flyme OS系统手机状态栏字体颜色，优先级高于statusBarDarkFont(boolean isDarkFont)方法
     * Flyme os status bar font color immersion bar.
     *
     * @param flyme操作系统状态栏字体颜色 the flyme os status bar font color
     * @return the immersion bar
     */
    public 状态栏沉浸式 flyme操作系统状态栏字体颜色整型(@ColorInt int flyme操作系统状态栏字体颜色) {
        沉浸式配置.flymeOSStatusBarFontColorInt(flyme操作系统状态栏字体颜色);
        return this;
    }


    /**
     * 隐藏导航栏或状态栏
     *
     * @param 栏隐藏 the bar hide
     * @return the immersion bar
     */
    public 状态栏沉浸式 隐藏栏(BarHide 栏隐藏) {
        沉浸式配置.hideBar(栏隐藏);
        return this;
    }

    //    fun 显示状态栏导航栏(): 状态栏沉浸式 {
//        沉浸式配置.hideBar(BarHide.FLAG_SHOW_BAR) //显示状态栏和导航栏
//        return this
//    }
//
//    fun 隐藏状态栏导航栏(): 状态栏沉浸式 {
//        沉浸式配置.hideBar(BarHide.FLAG_HIDE_BAR) //隐藏状态栏和导航栏
//        return this
//    }
//
//    //========================================================================
//
//    fun 隐藏状态栏(): 状态栏沉浸式 {
//        沉浸式配置.hideBar(BarHide.FLAG_HIDE_STATUS_BAR) //隐藏状态栏
//        return this
//    }
//
//    fun 隐藏导航栏(): 状态栏沉浸式 {
//        沉浸式配置.hideBar(BarHide.FLAG_HIDE_NAVIGATION_BAR) //隐藏导航栏
//        return this
//    }


    /**
     * 解决布局与状态栏重叠问题，该方法将调用系统view的setFitsSystemWindows方法，一旦window已经focus在设置为false将不会生效，
     * 默认不会使用该方法，如果是渐变色状态栏和顶部图片请不要调用此方法或者设置为false
     * Apply system fits immersion bar.
     *
     * @param 应用系统适配 the apply system fits
     * @return the immersion bar
     */
    public 状态栏沉浸式 应用系统适配(boolean 应用系统适配) {
        沉浸式配置.applySystemFits(应用系统适配);
        return this;
    }




    /**
     * 解决布局与状态栏重叠问题
     *
     * @param 适配 the fits
     * @return the immersion bar
     */
    public 状态栏沉浸式 适配系统窗口(boolean 适配) {
        沉浸式配置.fitsSystemWindows(适配);
        return this;
    }

    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows immersion bar.
     *
     * @param 适配         the fits
     * @param 内容颜色 the content color 整体界面背景色
     * @return the immersion bar
     */
    public 状态栏沉浸式 适配系统窗口(boolean 适配, @ColorRes int 内容颜色) {
        沉浸式配置.fitsSystemWindows(适配, 内容颜色);
        return this;
    }

    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows immersion bar.
     *
     * @param 适配                  the fits
     * @param 内容颜色          the content color 整体界面背景色
     * @param 内容颜色转换 the content color transform  整体界面变换后的背景色
     * @param 内容透明度          the content alpha 整体界面透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 适配系统窗口(boolean 适配, @ColorRes int 内容颜色, @ColorRes int 内容颜色转换, @FloatRange(from = 0f, to = 1f) float 内容透明度) {
        沉浸式配置.fitsSystemWindows(适配, 内容颜色,内容颜色转换,内容透明度);
        return this;
    }




    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows int immersion bar.
     *
     * @param 适配         the fits
     * @param 内容颜色 the content color 整体界面背景色
     * @return the immersion bar
     */
    public 状态栏沉浸式 适配系统窗口整型(boolean 适配, @ColorInt int 内容颜色) {
        沉浸式配置.fitsSystemWindowsInt(适配, 内容颜色);
        return this;
    }

    /**
     * 解决布局与状态栏重叠问题，支持侧滑返回
     * Fits system windows int immersion bar.
     *
     * @param 适配                  the fits
     * @param 内容颜色          the content color 整体界面背景色
     * @param 内容颜色转换 the content color transform 整体界面变换后的背景色
     * @param 内容透明度          the content alpha 整体界面透明度
     * @return the immersion bar
     */
    public 状态栏沉浸式 适配系统窗口整型(boolean 适配, @ColorInt int 内容颜色, @ColorInt int 内容颜色转换, @FloatRange(from = 0f, to = 1f) float 内容透明度) {
        沉浸式配置.fitsSystemWindowsInt(适配, 内容颜色, 内容颜色转换, 内容透明度);
        return this;
    }





    /**
     * 是否可以修复状态栏与布局重叠，默认为true，只适合ImmersionBar#statusBarView，ImmersionBar#titleBar，ImmersionBar#titleBarMarginTop
     * Fits layout overlap enable immersion bar.
     *
     * @param 适配布局重叠启用 the fits layout overlap enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 适配布局重叠启用(boolean 适配布局重叠启用) {
        沉浸式配置.fitsLayoutOverlapEnable(适配布局重叠启用);
        return this;
    }




    /**
     * 通过状态栏高度动态设置状态栏布局
     *
     * @param 视图 the view
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏视图(View 视图) {
        沉浸式配置.statusBarView(视图);
        return this;
    }

    /**
     * 通过状态栏高度动态设置状态栏布局,只能在Activity中使用
     *
     * @param 视图Id the view id
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏视图(@IdRes int 视图Id) {
        沉浸式配置.statusBarView(视图Id);
        return this;
    }

    /**
     * 通过状态栏高度动态设置状态栏布局
     * Status bar view immersion bar.
     *
     * @param 视图Id   the view id
     * @param 根视图 the root view
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏视图(@IdRes int 视图Id, View 根视图) {
        沉浸式配置.statusBarView(视图Id, 根视图);
        return this;
    }




    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param 视图 the view
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏(View 视图) {
        沉浸式配置.titleBar(视图);
        return this;
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法
     * Title bar immersion bar.
     *
     * @param 视图                          the view
     * @param 状态栏颜色转换启用 the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏(View 视图, boolean 状态栏颜色转换启用) {
        沉浸式配置.titleBar(视图, 状态栏颜色转换启用);
        return this;
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，只支持Activity
     * Title bar immersion bar.
     *
     * @param 视图Id the view id
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏(@IdRes int 视图Id) {
        沉浸式配置.titleBar(视图Id);
        return this;
    }

    /**
     * Title bar immersion bar.
     *
     * @param 视图Id                        the view id
     * @param 状态栏颜色转换启用 the status bar flag
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏(@IdRes int 视图Id, boolean 状态栏颜色转换启用) {
        沉浸式配置.titleBar(视图Id, 状态栏颜色转换启用);
        return this;
    }

    /**
     * Title bar immersion bar.
     *
     * @param 视图Id   the view id
     * @param 根视图 the root view
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏(@IdRes int 视图Id, View 根视图) {
        沉浸式配置.titleBar(视图Id, 根视图);
        return this;
    }

    /**
     * 解决状态栏与布局顶部重叠又多了种方法，支持任何view
     * Title bar immersion bar.
     *
     * @param 视图Id                        the view id
     * @param 根视图                      the root view
     * @param 状态栏颜色转换启用 the status bar flag 默认为true false表示状态栏不支持变色，true表示状态栏支持变色
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏(@IdRes int 视图Id, View 根视图, boolean 状态栏颜色转换启用) {
        沉浸式配置.titleBar(视图Id, 根视图, 状态栏颜色转换启用);
        return this;
    }



    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param 视图Id the view id   标题栏资源id
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏边距上(@IdRes int 视图Id) {
        沉浸式配置.titleBarMarginTop(视图Id);
        return this;
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param 视图Id   the view id  标题栏资源id
     * @param 根视图 the root view  布局view
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏边距上(@IdRes int 视图Id, View 根视图) {
        沉浸式配置.titleBarMarginTop(视图Id,根视图);
        return this;
    }

    /**
     * 绘制标题栏距离顶部的高度为状态栏的高度
     * Title bar margin top immersion bar.
     *
     * @param 视图 the view  要改变的标题栏view
     * @return the immersion bar
     */
    public 状态栏沉浸式 标题栏边距上(View 视图) {
        沉浸式配置.titleBarMarginTop(视图);
        return this;
    }


    /**
     * 支持有actionBar的界面,调用该方法，布局讲从actionBar下面开始绘制
     * Support action bar immersion bar.
     *
     * @param 是否支持操作栏 the is support action bar
     * @return the immersion bar
     */
    public 状态栏沉浸式 支持操作栏(boolean 是否支持操作栏) {
        沉浸式配置.supportActionBar(是否支持操作栏);
        return this;
    }

    /**
     * Status bar color transform enable immersion bar.
     *
     * @param 状态栏颜色转换启用 the status bar flag
     * @return the immersion bar
     */
    public 状态栏沉浸式 状态栏颜色转换启用(boolean 状态栏颜色转换启用) {
        沉浸式配置.statusBarColorTransformEnable(状态栏颜色转换启用);
        return this;
    }



    /**
     * 一键重置所有参数
     * Reset immersion bar.
     *
     * @return the immersion bar
     */
    public 状态栏沉浸式 重置() {
        沉浸式配置.reset();
        return this;
    }



    /**
     * 给某个页面设置tag来标识这页bar的属性.
     * Add tag bar tag.
     *
     * @param 标签 the tag
     * @return the bar tag
     */
    public 状态栏沉浸式 添加标签(String 标签) {
        沉浸式配置.addTag(标签);
        return this;
    }

    /**
     * 根据tag恢复到某次调用时的参数
     * Recover immersion bar.
     *
     * @param 标签 the tag
     * @return the immersion bar
     */
    public 状态栏沉浸式 取标签(String 标签) {
        沉浸式配置.getTag(标签);
        return this;
    }




    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     * Keyboard enable immersion bar.
     *
     * @param 启用 the enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 键盘启用(boolean 启用) {
        沉浸式配置.keyboardEnable(启用);
        return this;
    }

    /**
     * 解决软键盘与底部输入框冲突问题 ，默认是false
     *
     * @param 启用       the enable
     * @param 键盘模式 the keyboard mode
     * @return the immersion bar
     */
    public 状态栏沉浸式 键盘启用(boolean 启用, int 键盘模式) {
        沉浸式配置.keyboardEnable(启用,键盘模式);
        return this;
    }


    /**
     * 修改键盘模式
     * Keyboard mode immersion bar.
     *
     * @param 键盘模式 the keyboard mode
     * @return the immersion bar
     */
    public 状态栏沉浸式 键盘模式(int 键盘模式) {
        沉浸式配置.keyboardMode(键盘模式);
        return this;
    }




    /**
     * 软键盘弹出关闭的回调监听
     * Sets on keyboard listener.
     *
     * @param 键盘监听器 the on keyboard listener
     * @return the on keyboard listener
     */
    public 状态栏沉浸式 置键盘监听器(@Nullable OnKeyboardListener 键盘监听器) {
        沉浸式配置.setOnKeyboardListener(键盘监听器);
        return this;
    }

    /**
     * 导航栏显示隐藏监听器
     * Sets on navigation bar listener.
     *
     * @param 导航栏监听器 the on navigation bar listener
     * @return the on navigation bar listener
     */
    public 状态栏沉浸式 置导航栏监听器(OnNavigationBarListener 导航栏监听器) {
        沉浸式配置.setOnNavigationBarListener(导航栏监听器);
        return this;
    }


    /**
     * Bar监听，第一次调用和横竖屏切换都会触发此方法，比如可以解决横竖屏切换，横屏情况下，刘海屏遮挡布局的问题
     * Sets on bar listener.
     *
     * @param 栏监听器 the on bar listener
     * @return the on bar listener
     */
    public 状态栏沉浸式 置栏监听器(OnBarListener 栏监听器) {
        沉浸式配置.setOnBarListener(栏监听器);
        return this;
    }




    /**
     * 是否可以修改导航栏颜色，默认为true
     * 优先级 navigationBarEnable  > navigationBarWithKitkatEnable > navigationBarWithEMUI3Enable
     * Navigation bar enable immersion bar.
     *
     * @param 导航栏启用 the enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏启用(boolean 导航栏启用) {
        沉浸式配置.navigationBarEnable(导航栏启用);
        return this;
    }


    /**
     * 是否可以修改4.4设备导航栏颜色，默认为true
     * 优先级 navigationBarEnable  > navigationBarWithKitkatEnable > navigationBarWithEMUI3Enable
     *
     * @param 导航栏兼容KitKat启用 the navigation bar with kitkat enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏兼容KitKat启用(boolean 导航栏兼容KitKat启用) {
        沉浸式配置.navigationBarWithKitkatEnable(导航栏兼容KitKat启用);
        return this;
    }


    /**
     * 是否能修改华为emui3.1导航栏颜色，默认为true，
     * 优先级 navigationBarEnable  > navigationBarWithKitkatEnable > navigationBarWithEMUI3Enable
     * Navigation bar with emui 3 enable immersion bar.
     *
     * @param 导航栏兼容EMUI3启用 the navigation bar with emui 3 1 enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 导航栏兼容EMUI3启用(boolean 导航栏兼容EMUI3启用) {
        //是否可以修改emui3系列手机导航栏
        沉浸式配置.navigationBarWithEMUI3Enable(导航栏兼容EMUI3启用);
        return this;
    }

    /**
     * 是否可以使用沉浸式，如果已经是true了，在改为false，之前沉浸式效果不会消失，之后设置的沉浸式效果也不会生效
     * Bar enable immersion bar.
     *
     * @param 栏启用 the bar enable
     * @return the immersion bar
     */
    public 状态栏沉浸式 栏启用(boolean 栏启用) {
        沉浸式配置.barEnable(栏启用);
        return this;
    }


    //==============================================================================================
    //==============================================================================================

    @SuppressLint("StaticFieldLeak")
    private static View 根视图;

    @SuppressLint("ResourceType")
    public static void 是否隐藏状态栏导航栏(Activity 上下文, boolean 是否隐藏, boolean 自动深色, boolean 深色颜色) {
        // 获取根视图
        根视图 = 上下文.findViewById(android.R.id.content);
        状态栏沉浸式 沉浸设置 = 状态栏沉浸式.初始沉浸式(上下文);
        if (是否隐藏) {
            沉浸设置.隐藏栏(BarHide.FLAG_HIDE_BAR);
            沉浸设置.全屏(true);
            沉浸设置.透明栏();
            沉浸设置.状态栏深色字体(自动深色);
            沉浸设置.导航栏深色图标(自动深色);
            沉浸设置.初始化();

            if (状态栏沉浸式.是否手势(上下文)) { //有
                //隐藏状态栏和导航栏
                上下文.getWindow().getDecorView().setSystemUiVisibility(
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE // 让内容在状态栏和导航栏之间留白
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION // 让内容显示在状态栏和导航栏之间
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 让内容显示在状态栏和导航栏后面
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
                        //| View.SYSTEM_UI_FLAG_FULLSCREEN // 隐藏状态栏
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
                    ); // 保持沉浸模式，即使用户交互也不会退出
            }
            上下文.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            // 设置 OnApplyWindowInsetsListener
            根视图.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @NonNull
                @Override
                public WindowInsets onApplyWindowInsets(@NonNull View v, @NonNull WindowInsets insets) {
                    // 处理底部导航栏的内边距
                    v.setPadding(0, 0, 0, 0);
                    insets.consumeSystemWindowInsets();
                    return insets;
                }
            });
        } else {
            沉浸设置.隐藏栏(BarHide.FLAG_SHOW_BAR);
            if (状态栏沉浸式.是否手势(上下文)) { //有
                if (自动深色) {
                    沉浸设置.状态栏深色字体(!主题类.是否是深色模式(上下文));
//                    沉浸设置.导航栏深色图标(!主题类.是否是深色模式(上下文));
                    if (Objects.equals(Build.MANUFACTURER, "Xiaomi")) { //制造商
                        沉浸设置.透明栏();
                        沉浸设置.导航栏深色图标(!主题类.是否是深色模式(上下文));
                    } else {
                        if (主题类.是否是深色模式(上下文)) {
                            沉浸设置.导航栏颜色("#000000");
                        } else {
                            沉浸设置.导航栏颜色("#ffffff");
                        }
                    }
                } else {
                    沉浸设置.状态栏深色字体(深色颜色);
                    if (深色颜色) {
                        沉浸设置.导航栏颜色(Color.WHITE);
                    } else {
                        沉浸设置.导航栏颜色(Color.BLACK);
                    }
                }
            } else { //没有
                if (自动深色) {
                    沉浸设置.状态栏深色字体(!主题类.是否是深色模式(上下文));
                    沉浸设置.导航栏深色图标(!主题类.是否是深色模式(上下文));
                } else {
                    沉浸设置.状态栏深色字体(深色颜色);
                    沉浸设置.导航栏深色图标(深色颜色);
                }
            }
            沉浸设置.初始化();

            // 设置 OnApplyWindowInsetsListener
            根视图.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
                @NonNull
                @Override
                public WindowInsets onApplyWindowInsets(@NonNull View v, @NonNull WindowInsets insets) {
                    // 处理底部导航栏的内边距
                    v.setPadding(0, insets.getSystemWindowInsetTop(), 0, insets.getSystemWindowInsetBottom());
                    insets.consumeSystemWindowInsets();
                    return insets;
                }
            });
        }
    }


    public static void 刷新是否隐藏状态栏导航栏布局(){
        ViewCompat.requestApplyInsets(根视图);
    }

    public static void 修复对话框子窗口导航栏遮盖问题(Activity 上下文, Fragment 对话框子窗口上下文) {
        状态栏沉浸式 沉浸设置 = 状态栏沉浸式.初始沉浸式(对话框子窗口上下文);
        沉浸设置.透明栏();
        if (状态栏沉浸式.是否手势(上下文)) { //有
            if (Objects.equals(Build.MANUFACTURER, "Xiaomi")) {
                沉浸设置.导航栏深色图标(!主题类.是否是深色模式(上下文));
            } else {
                if (主题类.是否是深色模式(上下文)) {
                    沉浸设置.导航栏颜色("#000000");
                } else {
                    沉浸设置.导航栏颜色("#ffffff");
                }
            }
        } else { //没有
            沉浸设置.导航栏深色图标(!主题类.是否是深色模式(上下文)); // 设置导航栏图标为深色
        }
        沉浸设置.初始化();
    }

    //==============================================================================================
    //==============================================================================================


    public static void 隐藏对话框子窗口状态栏(Activity 活动, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(活动).透明栏().隐藏栏(BarHide.FLAG_HIDE_STATUS_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 显示对话框子窗口状态栏(Activity 活动, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(活动).透明栏().隐藏栏(BarHide.FLAG_SHOW_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 隐藏对话框子窗口状态栏(Fragment 碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(碎片).透明栏().隐藏栏(BarHide.FLAG_HIDE_STATUS_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 显示对话框子窗口状态栏(Fragment 碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(碎片).透明栏().隐藏栏(BarHide.FLAG_SHOW_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 隐藏对话框子窗口状态栏(android.app.Fragment 碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(碎片).透明栏().隐藏栏(BarHide.FLAG_HIDE_STATUS_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 显示对话框子窗口状态栏(android.app.Fragment 碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(碎片).透明栏().隐藏栏(BarHide.FLAG_SHOW_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 隐藏对话框子窗口状态栏(DialogFragment 对话框碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(对话框碎片).透明栏().隐藏栏(BarHide.FLAG_HIDE_STATUS_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 显示对话框子窗口状态栏(DialogFragment 对话框碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(对话框碎片).透明栏().隐藏栏(BarHide.FLAG_SHOW_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 隐藏对话框子窗口状态栏(android.app.DialogFragment 对话框碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(对话框碎片).透明栏().隐藏栏(BarHide.FLAG_HIDE_STATUS_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 显示对话框子窗口状态栏(android.app.DialogFragment 对话框碎片, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(对话框碎片).透明栏().隐藏栏(BarHide.FLAG_SHOW_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 隐藏对话框子窗口状态栏(Activity 活动, Dialog 对话框, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(活动, 对话框).透明栏().隐藏栏(BarHide.FLAG_HIDE_STATUS_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }

    public static void 显示对话框子窗口状态栏(Activity 活动, Dialog 对话框, boolean 状态栏字体图标自动深色模式) {
        状态栏沉浸式.初始沉浸式(活动, 对话框).透明栏().隐藏栏(BarHide.FLAG_SHOW_BAR).状态栏深色字体(状态栏字体图标自动深色模式).初始化();
    }


}
