package 安卓.应用;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 创建时间：2025年11月23日.
 * <p>
 * 描述：应用程序
 * @author dxyc
 */
public class 应用程序 extends Application {
    public 应用程序() {
        super();
    }

    //=========================================================================================

    public interface 活动生命周期回调 extends ActivityLifecycleCallbacks {

        @Override
        default void onActivityPreCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            ActivityLifecycleCallbacks.super.onActivityPreCreated(activity, savedInstanceState);
            this.活动预创建(activity, savedInstanceState);
        }

        default void 活动预创建(@NonNull Activity 活动, @Nullable Bundle 已保存实例状态) {}

        //=======================================================================================

        @Override
        default void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState){
            this.活动已创建(activity, savedInstanceState);
        }

        void 活动已创建(@NonNull Activity 活动, @Nullable Bundle 已保存实例状态);

        //=======================================================================================

        @Override
        default void onActivityPostCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
            ActivityLifecycleCallbacks.super.onActivityPostCreated(activity, savedInstanceState);
            this.活动创建后(activity, savedInstanceState);
        }

        default void 活动创建后(@NonNull Activity 活动, @Nullable Bundle 已保存实例状态) {}

        //=======================================================================================

        @Override
        default void onActivityPreStarted(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPreStarted(activity);
            this.活动预启动(activity);
        }

        default void 活动预启动(@NonNull Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityStarted(@NonNull Activity activity){
            this.活动已启动(activity);
        }


        void 活动已启动(@NonNull Activity 活动);

        //=======================================================================================

        @Override
        default void onActivityPostStarted(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPostStarted(activity);
            this.活动启动后(activity);
        }

        default void 活动启动后(@NonNull Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityPreResumed(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPreResumed(activity);
            this.活动预恢复(activity);
        }

        default void 活动预恢复(Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityResumed(@NonNull Activity activity){
            this.活动已恢复(activity);
        }

        void 活动已恢复(@NonNull Activity 活动);

        //=======================================================================================

        @Override
        default void onActivityPostResumed(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPostResumed(activity);
            this.活动恢复后(activity);
        }

        default void 活动恢复后(Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityPrePaused(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPrePaused(activity);
            this.活动预暂停(activity);
        }

        default void 活动预暂停(@NonNull Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityPaused(@NonNull Activity activity){
            this.活动已暂停(activity);
        }

        void 活动已暂停(@NonNull Activity 活动);

        //=======================================================================================

        @Override
        default void onActivityPostPaused(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPostPaused(activity);
            this.活动暂停后(activity);
        }

        default void 活动暂停后(@NonNull Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityPreStopped(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPreStopped(activity);
            this.活动预停止(activity);
        }

        default void 活动预停止(@NonNull Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityStopped(@NonNull Activity activity){
            this.活动已停止(activity);
        }

        void 活动已停止(@NonNull Activity 活动);

        //=======================================================================================

        @Override
        default void onActivityPostStopped(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPostStopped(activity);
            this.活动停止后(activity);
        }

        default void 活动停止后(@NonNull Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityPreSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            ActivityLifecycleCallbacks.super.onActivityPreSaveInstanceState(activity, outState);
            this.活动预保存实例状态(activity, outState);
        }

        default void 活动预保存实例状态(@NonNull Activity 活动, @NonNull Bundle 输出状态) {}

        //=======================================================================================

        @Override
        default void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState){
            this.活动已保存实例状态(activity, outState);
        }

        void 活动已保存实例状态(@NonNull Activity 活动, @NonNull Bundle 输出状态);

        //=======================================================================================

        @Override
        default void onActivityPostSaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
            ActivityLifecycleCallbacks.super.onActivityPostSaveInstanceState(activity, outState);
            this.活动保存实例状态后(activity, outState);
        }

        default void 活动保存实例状态后(@NonNull Activity 活动, @NonNull Bundle 输出状态) {}

        //=======================================================================================

        @Override
        default void onActivityPreDestroyed(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPreDestroyed(activity);
            this.活动预销毁(activity);
        }

        default void 活动预销毁(@NonNull Activity 活动) {}

        //=======================================================================================

        @Override
        default void onActivityDestroyed(@NonNull Activity activity) {
            this.活动已销毁(activity);
        }

        void 活动已销毁(@NonNull Activity 活动);

        //=======================================================================================

        @Override
        default void onActivityPostDestroyed(@NonNull Activity activity) {
            ActivityLifecycleCallbacks.super.onActivityPostDestroyed(activity);
            this.活动销毁后(activity);
        }

        default void 活动销毁后(@NonNull Activity 活动) {}

    }

    public interface 提供辅助数据监听器 extends OnProvideAssistDataListener {
        @Override
        default void onProvideAssistData(Activity activity, Bundle data){
            this.提供辅助数据(activity, data);
        }

        void 提供辅助数据(Activity 活动, Bundle 数据);

    }

    //=========================================================================================


    @Override
    public void onCreate() {
        super.onCreate();
        this.创建();
    }

    public void 创建() {}


    @Override
    public void onTerminate() {
        super.onTerminate();
        this.终止();
    }

    public void 终止() {}


    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.配置改变(newConfig);
    }

    public void 配置改变(Configuration 新配置) {}


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.内存不足();
    }

    public void 内存不足() {}


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        this.内存回收(level);
    }

    public void 内存回收(int 等级) {}


    public void 注册组件回调(ComponentCallbacks 回调) {
        this.registerComponentCallbacks(回调);
    }

    public void 取消注册组件回调(ComponentCallbacks 回调) {
        this.unregisterComponentCallbacks(回调);
    }

    public void 注册活动生命周期回调(ActivityLifecycleCallbacks 回调) {
        this.registerActivityLifecycleCallbacks(回调);
    }

    public void 取消注册活动生命周期回调(ActivityLifecycleCallbacks 回调) {
        this.unregisterActivityLifecycleCallbacks(回调);
    }

    public void 注册提供辅助数据监听器(OnProvideAssistDataListener 回调) {
        this.registerOnProvideAssistDataListener(回调);
    }

    public void 取消注册提供辅助数据监听器(OnProvideAssistDataListener 回调) {
        this.unregisterOnProvideAssistDataListener(回调);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public static String 取进程名() {
        return Application.getProcessName();
    }


}
