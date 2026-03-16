package 自定义.布局类;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;

import zwkfb.view.R;

/**
 * ================================
 * Des: 阻尼回弹 DampingReboundFrameLayout
 * Created by kele on 2021/2/22.
 * E-mail:984127585@qq.com
 * ================================
 */
public class 阻尼回弹框架布局 extends FrameLayout {

    private View mPrinceView;// 太子View
    private int mInitTop, mInitBottom, mInitLeft, mInitRight;// 太子View初始时上下坐标位置(相对父View,
    // 即当前ReboundEffectsView)
    private boolean isEndwiseSlide;// 是否纵向滑动
    private float mVariableY;// 手指上下滑动Y坐标变化前的Y坐标值
    private float mVariableX;// 手指上下滑动X坐标变化前的X坐标值

    private int orientation;//1:竖向滚动 2：横向滚动

    public 阻尼回弹框架布局(Context context) {
        this(context, null);
    }

    public 阻尼回弹框架布局(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public 阻尼回弹框架布局(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setClickable(true);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.DampingReboundFrameLayout);
        orientation = ta.getInt(R.styleable.DampingReboundFrameLayout_orientation, 1);
        ta.recycle();
    }

    /**
     * Touch事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (null != mPrinceView) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    onActionDown(e);
                    break;
                case MotionEvent.ACTION_MOVE:
                    return onActionMove(e);
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    onActionUp(e);// 当ACTION_UP一样处理
                    break;
            }
        }
        return super.onTouchEvent(e);
    }

    /**
     * 手指按下事件
     */
    private void onActionDown(MotionEvent e) {
        mVariableY = e.getY();
        mVariableX = e.getX();
        /**
         * 保存mPrinceView的初始上下高度位置
         */
        mInitTop = mPrinceView.getTop();
        mInitBottom = mPrinceView.getBottom();
        mInitLeft = mPrinceView.getLeft();
        mInitRight = mPrinceView.getRight();
    }

    /**
     * 手指滑动事件
     */
    private boolean onActionMove(MotionEvent e) {
        float nowY = e.getY();
        float diffY = (nowY - mVariableY) / 2;
        if (orientation == 1 && Math.abs(diffY) > 0) {// 上下滑动
            // 移动太子View的上下位置
            mPrinceView.layout(mPrinceView.getLeft(), mPrinceView.getTop() + (int) diffY,
                    mPrinceView.getRight(), mPrinceView.getBottom() + (int) diffY);
            mVariableY = nowY;
            isEndwiseSlide = true;
            return true;// 消费touch事件
        }

        float nowX = e.getX();
        float diffX = (nowX - mVariableX) / 5;//除数越大可以滑动的距离越短
        if (orientation == 2 && Math.abs(diffX) > 0) {// 左右滑动
            // 移动太子View的左右位置
            mPrinceView.layout(mPrinceView.getLeft() + (int) diffX, mPrinceView.getTop(),
                    mPrinceView.getRight() + (int) diffX, mPrinceView.getBottom());
            mVariableX = nowX;
            isEndwiseSlide = true;
            return true;// 消费touch事件
        }
        return super.onTouchEvent(e);
    }

    /**
     * 手指释放事件
     */
    private void onActionUp(MotionEvent e) {
        if (isEndwiseSlide) {// 是否为纵向滑动事件
            // 是纵向滑动事件，需要给太子View重置位置
            if (orientation==1){
                resetPrinceViewV();
            }else if (orientation==2){
                resetPrinceViewH();
            }
            isEndwiseSlide = false;
        }
    }

    /**
     * 回弹，重置太子View初始的位置
     */
    private void resetPrinceViewV() {
        TranslateAnimation ta = new TranslateAnimation(0, 0, mPrinceView.getTop() - mInitTop, 0);
        ta.setDuration(600);
        mPrinceView.startAnimation(ta);
        mPrinceView.layout(mPrinceView.getLeft(), mInitTop, mPrinceView.getRight(), mInitBottom);
    }

    private void resetPrinceViewH() {
        TranslateAnimation ta = new TranslateAnimation(mPrinceView.getLeft() - mInitLeft, 0, 0, 0);
        ta.setDuration(600);
        mPrinceView.startAnimation(ta);
        mPrinceView.layout(mInitLeft, mPrinceView.getTop(), mInitRight, mPrinceView.getBottom());
    }

    /**
     * XML布局完成加载
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            mPrinceView = getChildAt(0);// 获得子View，太子View
        }
    }
}