package 自定义.布局类

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.TranslateAnimation
import androidx.core.content.withStyledAttributes
import androidx.core.view.isNotEmpty
import zwkfb.view.R
import 安卓.组件.框架布局
import kotlin.math.abs

/**
 * ================================
 *
 * Des: 阻尼回弹 DampingReboundFrameLayout
 *
 * Created by kele on 2021/2/22.
 *
 * E-mail:984127585@qq.com
 *
 * ================================
 */
class 回弹框架布局 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : 框架布局(context, attrs, defStyleAttr) {
    private var mPrinceView: View? = null // 太子View
    private var mInitTop = 0
    private var mInitBottom = 0
    private var mInitLeft = 0
    private var mInitRight = 0 // 太子View初始时上下坐标位置(相对父View,

    // 即当前ReboundEffectsView)
    private var isEndwiseSlide = false // 是否纵向滑动
    private var mVariableY = 0f // 手指上下滑动Y坐标变化前的Y坐标值
    private var mVariableX = 0f // 手指上下滑动X坐标变化前的X坐标值

    private var orientation: Int? = 0 //0:竖向滚动 1：横向滚动

    init {
        this.isClickable = true
        context.withStyledAttributes(attrs, R.styleable.回弹框架布局) {
            orientation = getInt(R.styleable.回弹框架布局_滚动方向, 0)
            recycle()
        }
    }

    fun 置滚动方向(orientation: Int) {
        this.orientation = orientation
    }

    /**
     * Touch事件
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(e: MotionEvent): Boolean {
        if (null != mPrinceView) {
            when (e.action) {
                MotionEvent.ACTION_DOWN -> onActionDown(e)
                MotionEvent.ACTION_MOVE -> return onActionMove(e)
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> onActionUp() // 当ACTION_UP一样处理
            }
        }
        return super.onTouchEvent(e)
    }

    /**
     * 手指按下事件
     */
    private fun onActionDown(e: MotionEvent) {
        mVariableY = e.y
        mVariableX = e.x
        /**
         * 保存mPrinceView的初始上下高度位置
         */
        mInitTop = mPrinceView!!.top
        mInitBottom = mPrinceView!!.bottom
        mInitLeft = mPrinceView!!.left
        mInitRight = mPrinceView!!.right
    }

    /**
     * 手指滑动事件
     */
    private fun onActionMove(e: MotionEvent): Boolean {
        val nowY = e.y
        val diffY = (nowY - mVariableY) / 2
        if (orientation == 0 && abs(diffY.toDouble()) > 0) { // 上下滑动
            // 移动太子View的上下位置
            mPrinceView!!.layout(
                mPrinceView!!.left, mPrinceView!!.top + diffY.toInt(),
                mPrinceView!!.right, mPrinceView!!.bottom + diffY.toInt()
            )
            mVariableY = nowY
            isEndwiseSlide = true
            return true // 消费touch事件
        }

        val nowX = e.x
        val diffX = (nowX - mVariableX) / 5 //除数越大可以滑动的距离越短
        if (orientation == 1 && abs(diffX.toDouble()) > 0) { // 左右滑动
            // 移动太子View的左右位置
            mPrinceView!!.layout(
                mPrinceView!!.left + diffX.toInt(), mPrinceView!!.top,
                mPrinceView!!.right + diffX.toInt(), mPrinceView!!.bottom
            )
            mVariableX = nowX
            isEndwiseSlide = true
            return true // 消费touch事件
        }
        return super.onTouchEvent(e)
    }

    /**
     * 手指释放事件
     */
    private fun onActionUp() {
        if (isEndwiseSlide) { // 是否为纵向滑动事件
            // 是纵向滑动事件，需要给太子View重置位置
            if (orientation == 0) {
                resetPrinceViewV()
            } else if (orientation == 1) {
                resetPrinceViewH()
            }
            isEndwiseSlide = false
        }
    }

    /**
     * 回弹，重置太子View初始的位置
     */
    private fun resetPrinceViewV() {
        val ta = TranslateAnimation(0f, 0f, (mPrinceView!!.top - mInitTop).toFloat(), 0f)
        ta.setDuration(600)
        mPrinceView!!.startAnimation(ta)
        mPrinceView!!.layout(mPrinceView!!.left, mInitTop, mPrinceView!!.right, mInitBottom)
    }

    private fun resetPrinceViewH() {
        val ta = TranslateAnimation((mPrinceView!!.left - mInitLeft).toFloat(), 0f, 0f, 0f)
        ta.setDuration(600)
        mPrinceView!!.startAnimation(ta)
        mPrinceView!!.layout(mInitLeft, mPrinceView!!.top, mInitRight, mPrinceView!!.bottom)
    }

    /**
     * XML布局完成加载
     */
    override fun onFinishInflate() {
        super.onFinishInflate()
        if (isNotEmpty()) {
            mPrinceView = getChildAt(0) // 获得子View，太子View
        }
    }
}