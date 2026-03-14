package 自定义.动画类;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.RotateAnimation;

import androidx.annotation.NonNull;

public class 动画类 {


    public static Drawable 波纹效果圆形(){
        // 简化状态列表，只保留按下状态和默认状态
        int[][] 状态 = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{}
        };
        // 定义波纹颜色
        int[] 颜色 = new int[]{
                Color.parseColor("#9b9b9b"), // 按下时的颜色（灰色）
                Color.parseColor("#303F9F")  // 默认颜色（蓝色）
        };
        ColorStateList 颜色状态列表 = new ColorStateList(状态, 颜色);
        return new RippleDrawable(颜色状态列表, null, null);
    }

    public static Drawable 波纹效果矩形(){
        // 简化状态列表，只保留按下状态和默认状态
        int[][] 状态 = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{}
        };
        // 定义波纹颜色
        int[] 颜色 = new int[]{
                Color.parseColor("#9b9b9b"), // 按下时的颜色（灰色）
                Color.parseColor("#303F9F")  // 默认颜色（蓝色）
        };
        ColorStateList 颜色状态列表 = new ColorStateList(状态, 颜色);

        // 创建蒙版限制波纹范围
        ColorDrawable mask = new ColorDrawable(android.graphics.Color.WHITE);
        return new RippleDrawable(颜色状态列表, null, mask);
    }

    public static Drawable 布局单击波纹效果() {
        int[][] stateList = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_focused},
                new int[]{android.R.attr.state_activated},
                new int[]{}
        };
        int normalColor = Color.parseColor("#303F9F"); // 蓝色
        int pressedColor = Color.parseColor("#9b9b9b"); // 粉色 //FF4081
        int[] stateColorList = new int[]{
                pressedColor,
                pressedColor,
                pressedColor,
                normalColor
        };
        ColorStateList colorStateList = new ColorStateList(stateList, stateColorList);
        return new RippleDrawable(colorStateList, null, null);
    }

    @SuppressLint("ClickableViewAccessibility")
    public static void 变灰效果(View 组件){
        final float[] 原始透明度 = {0f}; // 保存原始透明度
        组件.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN -> {
                    // 按下时，保存原始透明度，并设置为半透明
                    原始透明度[0] = v.getAlpha();
                    v.setAlpha(0.25f); // 可以根据需要调整透明度
                    return false; // 返回true以表示事件已处理
                }
                case MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    // 松开或取消时，恢复原始透明度
                    v.setAlpha(原始透明度[0]);
                    return false; // 返回true以表示事件已处理
                }
                default -> {
                    return false; // 对于其他事件，返回false
                }
            }
        });
    }


    public static class 旋转动画{

        public static void 组件中心旋转动画(View v, Long 动画时间, Float 初始角度 , Float 旋转角度, boolean 是否停留在执行完的状态) {
            RotateAnimation rotate = new RotateAnimation(
                    初始角度, 旋转角度, Animation.RELATIVE_TO_SELF,
                    0.5f, Animation.RELATIVE_TO_SELF, 0.5f
            );
            if (动画时间 >= 0) {
                rotate.setDuration(动画时间);
            }
            rotate.setFillAfter(是否停留在执行完的状态);
            v.startAnimation(rotate);
        }

    }


    public static class 展开收起动画{

        // 展开
        public static void 展开布局(View view, Long 动画时间) {
            // 测量视图的高度
            measureViewHeight(view);
            // 设置初始高度为0
            view.getLayoutParams().height = 0;
            view.setVisibility(View.VISIBLE);

            // 展开动画
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, view.getMeasuredHeight());
            valueAnimator.addUpdateListener(animation -> {
                int animatedValue = (int)animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = animatedValue;
                view.setLayoutParams(layoutParams);
            });

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    // 动画结束后恢复视图的高度
                    view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    view.requestLayout();
                }
            });

            valueAnimator.setDuration(动画时间);
            valueAnimator.start();
        }

        // 折叠
        public static void 收起布局(View view, Long 动画时间) {
            // 测量视图的高度
            int initialHeight = measureViewHeight(view);

            ValueAnimator valueAnimator = ValueAnimator.ofInt(initialHeight, 0);
            valueAnimator.addUpdateListener(animation -> {
                view.getLayoutParams().height = (int)animation.getAnimatedValue();
                view.requestLayout();
            });

            valueAnimator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationCancel(Animator animation) {
                    super.onAnimationCancel(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    view.setVisibility(View.GONE);
                    view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    super.onAnimationRepeat(animation);
                }

                @Override
                public void onAnimationStart(Animator animation) {
                    super.onAnimationStart(animation);
                }
            });
            valueAnimator.setDuration(动画时间);
            valueAnimator.start();
        }

        // 辅助方法，用于测量视图的高度
        private static int measureViewHeight(View view) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            view.measure(widthSpec, heightSpec);
            return view.getMeasuredHeight();
        }

        //        private static int 高度 = 0;
        //
        //        //展开
        //        public static void 展开布局(final View view, long 动画时间) {
        //            view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //            //final int targetHeight = view.getMeasuredHeight();
        //
        //            view.getLayoutParams().height = 0;
        //            view.setVisibility(View.VISIBLE);
        //
        //            // 展开动画
        //            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 高度);
        //            valueAnimator.addUpdateListener(animation -> {
        //                int animatedValue = (int) animation.getAnimatedValue();
        //                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        //                layoutParams.height = animatedValue;
        //                view.setLayoutParams(layoutParams);
        //            });
        //            // 设置动画监听器
        //            valueAnimator.addListener(new AnimatorListenerAdapter() {
        //                @Override
        //                public void onAnimationEnd(Animator animation) {
        //                    // 动画结束后恢复视图的高度
        //                    view.getLayoutParams().height = 高度;
        //                    view.requestLayout();
        //                }
        //            });
        //            valueAnimator.setDuration(动画时间);
        //            valueAnimator.start();
        //        }
        //
        //        //折叠
        //        public static void 收起布局(final View view, long 动画时间) {
        //            高度 = view.getMeasuredHeight();
        //            //final int initialHeight = view.getMeasuredHeight();
        //
        //            ValueAnimator valueAnimator = ValueAnimator.ofInt(高度, 0);
        //            valueAnimator.addUpdateListener(animation -> {
        //                view.getLayoutParams().height = (Integer) animation.getAnimatedValue();
        //                view.requestLayout();
        //            });
        //            valueAnimator.addListener(new Animator.AnimatorListener() {
        //                @Override
        //                public void onAnimationStart(@NonNull Animator animation) {
        //                }
        //                @Override
        //                public void onAnimationEnd(@NonNull Animator animation) {
        //                    view.setVisibility(View.GONE);
        //                }
        //                @Override
        //                public void onAnimationCancel(@NonNull Animator animation) {
        //                }
        //                @Override
        //                public void onAnimationRepeat(@NonNull Animator animation) {
        //                }
        //            });
        //            valueAnimator.setDuration(动画时间);
        //            valueAnimator.start();
        //        }

    }



    private static float startY = 0f; // 记录起始Y坐标
    private static final float viewAlpha = 1f; // 初始化透明度为1
    private static final int minDistance = 300; // 触发完全透明的最小滑动距离

    public static void 布局下拉逐渐隐藏动画(Activity 上下文, View 主组件, Class<?> 切换窗口) {
        主组件.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"Recycle", "ClickableViewAccessibility"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 处理不同触摸事件
                switch (event.getAction()) {
                    //按下事件
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getRawY();  // 记录起始Y坐标
                        v.clearAnimation() ;  // 清除已有动画
                        return true;
                    //滑动事件
                    case MotionEvent.ACTION_MOVE:
                        float distance = event.getRawY() - startY; // 计算滑动距离
                        if (distance > 0) { // 只处理下拉操作
                            // 计算进度比例（0-1）
                            float progress = (distance / minDistance);
                            // 同步更新视图属性
                            v.setAlpha(1 - progress);      // 透明度渐变（1→0）
                            v.setScaleX(1 - progress * 0.1f);  // X轴缩小（最大缩小10%）
                            v.setScaleY(1 - progress * 0.1f); // Y轴缩小
                            v.setVisibility(View.VISIBLE); // 保持可见
                        }
                        return true;
                    //抬起事件
                    case MotionEvent.ACTION_UP:
                        if (v.getAlpha() <= 0f) {
                            v.setVisibility(View.GONE);  // 透明度为0时隐藏
                            上下文.startActivity(new Intent(上下文, 切换窗口));
                            上下文.overridePendingTransition(0, 0);//无动画
                        }
                        // 添加属性动画恢复透明度
                        ValueAnimator a = ValueAnimator.ofFloat(viewAlpha, 1f);
                        a.setDuration(300); // 恢复动画时长
                        a.setInterpolator(new AccelerateDecelerateInterpolator()); // 加速减速插值器
                        a.addUpdateListener(animation -> {
                            v.setAlpha((Float) animation.getAnimatedValue()); // 更新透明度
                        });
                        a.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                v.setVisibility(View.VISIBLE); // 保证最终可见
                            }
                        });
                        a.start();
                        v.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300)
                                .setInterpolator(new OvershootInterpolator(1.5f)) // 添加弹性效果
                                .start();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public static void 布局下拉逐渐隐藏动画(Activity 上下文, View 主组件, View 组件, Class<?> 切换窗口) {
        组件.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint({"Recycle", "ClickableViewAccessibility"})
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View v1 = 主组件;
                // 处理不同触摸事件
                switch (event.getAction()) {
                    //按下事件
                    case MotionEvent.ACTION_DOWN:
                        startY = event.getRawY();  // 记录起始Y坐标
                        v1.clearAnimation() ;  // 清除已有动画
                        return true;
                    //滑动事件
                    case MotionEvent.ACTION_MOVE:
                        float distance = event.getRawY() - startY; // 计算滑动距离
                        if (distance > 0) { // 只处理下拉操作
                            // 计算进度比例（0-1）
                            float progress = (distance / minDistance);
                            // 同步更新视图属性
                            v1.setAlpha(1 - progress);      // 透明度渐变（1→0）
                            v1.setScaleX(1 - progress * 0.1f);  // X轴缩小（最大缩小10%）
                            v1.setScaleY(1 - progress * 0.1f); // Y轴缩小
                            v1.setVisibility(View.VISIBLE); // 保持可见
                        }
                        return true;
                    //抬起事件
                    case MotionEvent.ACTION_UP:
                        if (v1.getAlpha() <= 0f) {
                            v1.setVisibility(View.GONE);  // 透明度为0时隐藏
                            上下文.startActivity(new Intent(上下文, 切换窗口));
                            上下文.overridePendingTransition(0, 0);//无动画
                        }
                        // 添加属性动画恢复透明度
                        ValueAnimator a = ValueAnimator.ofFloat(viewAlpha, 1f);
                        a.setDuration(300); // 恢复动画时长
                        a.setInterpolator(new AccelerateDecelerateInterpolator()); // 加速减速插值器
                        a.addUpdateListener(animation -> {
                            v1.setAlpha((Float) animation.getAnimatedValue()); // 更新透明度
                        });
                        a.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                v1.setVisibility(View.VISIBLE); // 保证最终可见
                            }
                        });
                        a.start();
                        v1.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300)
                                .setInterpolator(new OvershootInterpolator(1.5f)) // 添加弹性效果
                                .start();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }


}