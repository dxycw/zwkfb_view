package 自定义.动画类

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.RippleDrawable
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.OvershootInterpolator
import android.view.animation.RotateAnimation
import 安卓.应用.窗口切换动画
import 安卓.视图.置触摸回调监听事件
import 自定义.活动类.启动活动
import androidx.core.graphics.toColorInt
import androidx.core.graphics.drawable.toDrawable


fun 波纹效果圆形(): Drawable {
    // 简化状态列表，只保留按下状态和默认状态
    val 状态 = arrayOf<IntArray?>(
        intArrayOf(android.R.attr.state_pressed),
        intArrayOf()
    )
    // 定义波纹颜色
    val 颜色 = intArrayOf(
        "#9b9b9b".toColorInt(),  // 按下时的颜色（灰色）
        "#303F9F".toColorInt() // 默认颜色（蓝色）
    )
    val 颜色状态列表 = ColorStateList(状态, 颜色)
    return RippleDrawable(颜色状态列表, null, null)
}

fun 波纹效果矩形(): Drawable {
    // 简化状态列表，只保留按下状态和默认状态
    val 状态 = arrayOf<IntArray?>(
        intArrayOf(android.R.attr.state_pressed),
        intArrayOf()
    )
    // 定义波纹颜色
    val 颜色 = intArrayOf(
        "#9b9b9b".toColorInt(),  // 按下时的颜色（灰色）
        "#303F9F".toColorInt() // 默认颜色（蓝色）
    )
    val 颜色状态列表 = ColorStateList(状态, 颜色)

    // 创建蒙版限制波纹范围
    val mask = Color.WHITE.toDrawable()
    return RippleDrawable(颜色状态列表, null, mask)
}

fun 布局单击波纹效果(): Drawable {
    val stateList = arrayOf<IntArray?>(
        intArrayOf(android.R.attr.state_pressed),
        intArrayOf(android.R.attr.state_focused),
        intArrayOf(android.R.attr.state_activated),
        intArrayOf()
    )
    val normalColor = "#303F9F".toColorInt() // 蓝色
    val pressedColor = "#9b9b9b".toColorInt() // 粉色 //FF4081
    val stateColorList = intArrayOf(
        pressedColor,
        pressedColor,
        pressedColor,
        normalColor
    )
    val colorStateList = ColorStateList(stateList, stateColorList)
    return RippleDrawable(colorStateList, null, null)
}

@SuppressLint("ClickableViewAccessibility")
fun 变灰效果(组件: View) {
    val 原始透明度 = floatArrayOf(0f) // 保存原始透明度
    组件.setOnTouchListener(OnTouchListener { v: View?, event: MotionEvent? ->
        when (event!!.getAction()) {
            MotionEvent.ACTION_DOWN -> {
                // 按下时，保存原始透明度，并设置为半透明
                原始透明度[0] = v!!.getAlpha()
                v.setAlpha(0.25f) // 可以根据需要调整透明度
                return@OnTouchListener false // 返回true以表示事件已处理
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                // 松开或取消时，恢复原始透明度
                v!!.setAlpha(原始透明度[0])
                return@OnTouchListener false // 返回true以表示事件已处理
            }
            else -> {
                return@OnTouchListener false // 对于其他事件，返回false
            }
        }
    })
}

//=================================================================================================

fun 组件中心旋转动画(
    v: View,
    动画时间: Long,
    初始角度: Float,
    旋转角度: Float,
    是否停留在执行完的状态: Boolean,
) {
    val rotate = RotateAnimation(
        初始角度, 旋转角度, Animation.RELATIVE_TO_SELF,
        0.5f, Animation.RELATIVE_TO_SELF, 0.5f
    )
    if (动画时间 >= 0) {
        rotate.setDuration(动画时间)
    }
    rotate.setFillAfter(是否停留在执行完的状态)
    v.startAnimation(rotate)
}

//=================================================================================================

// 展开
fun 展开布局(view: View, 动画时间: Long) {
    // 测量视图的高度
    measureViewHeight(view)
    // 设置初始高度为0
    view.getLayoutParams().height = 0
    view.setVisibility(View.VISIBLE)

    // 展开动画
    val valueAnimator = ValueAnimator.ofInt(0, view.getMeasuredHeight())
    valueAnimator.addUpdateListener(AnimatorUpdateListener { animation: ValueAnimator? ->
        val animatedValue = animation!!.getAnimatedValue() as Int
        val layoutParams = view.getLayoutParams()
        layoutParams.height = animatedValue
        view.setLayoutParams(layoutParams)
    })

    valueAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            // 动画结束后恢复视图的高度
            view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT
            view.requestLayout()
        }
    })

    valueAnimator.setDuration(动画时间)
    valueAnimator.start()
}

// 折叠
fun 收起布局(view: View, 动画时间: Long) {
    // 测量视图的高度
    val initialHeight = measureViewHeight(view)

    val valueAnimator = ValueAnimator.ofInt(initialHeight, 0)
    valueAnimator.addUpdateListener(AnimatorUpdateListener { animation: ValueAnimator? ->
        view.getLayoutParams().height = animation!!.getAnimatedValue() as Int
        view.requestLayout()
    })

    valueAnimator.addListener(object : AnimatorListenerAdapter() {
        override fun onAnimationCancel(animation: Animator) {
            super.onAnimationCancel(animation)
        }

        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            view.setVisibility(View.GONE)
            view.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT
        }

        override fun onAnimationRepeat(animation: Animator) {
            super.onAnimationRepeat(animation)
        }

        override fun onAnimationStart(animation: Animator) {
            super.onAnimationStart(animation)
        }
    })
    valueAnimator.setDuration(动画时间)
    valueAnimator.start()
}

// 辅助方法，用于测量视图的高度
private fun measureViewHeight(view: View): Int {
    val widthSpec = View.MeasureSpec.makeMeasureSpec(view.getWidth(), View.MeasureSpec.EXACTLY)
    val heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    view.measure(widthSpec, heightSpec)
    return view.getMeasuredHeight()
}


//=================================================================================================

private var startY = 0f // 记录起始Y坐标
private var viewAlpha = 1f // 初始化透明度为1
private const val minDistance = 300 // 触发完全透明的最小滑动距离

@SuppressLint("ClickableViewAccessibility")
fun Activity.布局下拉逐渐隐藏动画(主组件: View, 切换窗口: Class<out Activity>) {
    主组件.置触摸回调监听事件 { v, event ->
        // 处理不同触摸事件
        when (event.action) {
            //按下事件
            MotionEvent.ACTION_DOWN -> {
                startY = event.rawY  // 记录起始Y坐标
                v.clearAnimation()   // 清除已有动画
                true
            }
            //滑动事件
            MotionEvent.ACTION_MOVE -> {
                val distance = event.rawY - startY // 计算滑动距离
                if (distance > 0) { // 只处理下拉操作
                    // 计算进度比例（0-1）
                    val progress = (distance / minDistance).coerceIn(0f, 1f)
                    // 同步更新视图属性
                    v.alpha = 1 - progress       // 透明度渐变（1→0）
                    v.scaleX = 1 - progress * 0.1f // X轴缩小（最大缩小10%）
                    v.scaleY = 1 - progress * 0.1f // Y轴缩小
                    v.visibility = View.VISIBLE    // 保持可见
                }
                true
            }
            //抬起事件
            MotionEvent.ACTION_UP -> {
                if (v.alpha <= 0f) {
                    v.visibility = View.GONE // 透明度为0时隐藏
                    启动活动(切换窗口)
                    窗口切换动画(0, 0)//无动画
                }
                // 添加属性动画恢复透明度
                ValueAnimator.ofFloat(viewAlpha, 1f).apply {
                    duration = 300 // 恢复动画时长
                    interpolator = AccelerateDecelerateInterpolator()
                    addUpdateListener {
                        v.alpha = it.animatedValue as Float // 更新透明度
                    }
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            v.visibility = View.VISIBLE // 保证最终可见
                        }
                    })
                }.start()
                v.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300)
                    .setInterpolator(OvershootInterpolator(1.5f)) // 添加弹性效果
                    .start()
                true
            }
            else -> false
        }
    }
}

@SuppressLint("ClickableViewAccessibility")
fun Activity.布局下拉逐渐隐藏动画(主组件: View, 组件: View, 切换窗口: Class<out Activity>) {
    组件.置触摸回调监听事件 { _, event ->
        val v = 主组件
        // 处理不同触摸事件
        when (event.action) {
            //按下事件
            MotionEvent.ACTION_DOWN -> {
                startY = event.rawY  // 记录起始Y坐标
                v.clearAnimation()   // 清除已有动画
                false // 消费事件
            }
            //滑动事件
            MotionEvent.ACTION_MOVE -> {
                val distance = event.rawY - startY // 计算滑动距离
                if (distance > 0) { // 只处理下拉操作
                    // 计算进度比例（0-1）
                    val progress = (distance / minDistance).coerceIn(0f, 1f)
                    // 同步更新视图属性
                    v.alpha = 1 - progress         // 透明度渐变（1→0）
                    v.scaleX = 1 - progress * 0.1f // X轴缩小（最大缩小10%）
                    v.scaleY = 1 - progress * 0.1f // Y轴缩小
                    v.visibility = View.VISIBLE    // 保持可见
                }
                false
            }
            //抬起事件
            MotionEvent.ACTION_UP -> {
                if (v.alpha <= 0f) {
                    v.visibility = View.GONE // 透明度为0时隐藏
                    启动活动(切换窗口)
                    窗口切换动画(0, 0)//无动画
                }
                // 添加属性动画恢复透明度
                ValueAnimator.ofFloat(viewAlpha, 1f).apply {
                    duration = 300 // 恢复动画时长
                    interpolator = AccelerateDecelerateInterpolator()
                    addUpdateListener {
                        v.alpha = it.animatedValue as Float // 更新透明度
                    }
                    addListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            v.visibility = View.VISIBLE // 保证最终可见
                        }
                    })
                }.start()
                v.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(300)
                    .setInterpolator(OvershootInterpolator(1.5f)) // 添加弹性效果
                    .start()
                false
            }
            else -> false
        }
    }
}