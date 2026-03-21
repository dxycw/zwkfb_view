package 安卓.应用;

import android.annotation.SuppressLint;
import android.app.MediaRouteButton;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;

/**
 * 创建时间：2025年11月22日.
 * <p>
 * 描述：媒体路由按钮
 * @author dxyc
 */
public class 媒体路由按钮 extends MediaRouteButton {

    public 媒体路由按钮(Context 上下文) {
        super(上下文);
    }

    public 媒体路由按钮(Context 上下文, AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 媒体路由按钮(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 媒体路由按钮(Context 上下文, AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }

    //============================================================================================

    public int 取路由类型() {
        return this.getRouteTypes();
    }

    public void 置路由类型(int 类型) {
        this.setRouteTypes(类型);
    }

    public void 置扩展设置单击监听器(OnClickListener 监听器){
        this.setExtendedSettingsClickListener(监听器);
    }

    public void 显示对话框() {
        this.showDialog();
    }

    public void 置内容描述(CharSequence 内容描述) {
        this.setContentDescription(内容描述);
    }

    public boolean 执行单击() {
        return this.performClick();
    }


    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        return this.创建可绘制状态(extraSpace);
    }

    protected int[] 创建可绘制状态(int 额外空间) {
        return super.onCreateDrawableState(额外空间);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void drawableStateChanged() {
        this.可绘制状态已更改();
    }

    protected void 可绘制状态已更改() {
        super.drawableStateChanged();
    }


    @SuppressLint("MissingSuperCall")
    @Override
    protected boolean verifyDrawable(@NonNull Drawable who) {
        return this.验证可绘制对象(who);
    }


    protected boolean 验证可绘制对象(@NonNull Drawable 谁) {
        return super.verifyDrawable(谁);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void jumpDrawablesToCurrentState() {
        this.跳转到当前状态的可绘制对象();
    }

    public void 跳转到当前状态的可绘制对象() {
        super.jumpDrawablesToCurrentState();
    }


    public void 置可见性(int 可见性) {
        this.setVisibility(可见性);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onAttachedToWindow() {
        this.附加到窗口();
    }


    public void 附加到窗口() {
        super.onAttachedToWindow();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDetachedFromWindow() {
        this.分离从窗口();
    }

    public void 分离从窗口() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        this.测量(widthMeasureSpec, heightMeasureSpec);
    }


    @SuppressLint("WrongCall")
    protected void 测量(int 宽度测量规格, int 高度测量规格) {
        super.onMeasure(宽度测量规格, 高度测量规格);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.绘制(canvas);
    }

    @SuppressLint("WrongCall")
    protected void 绘制(Canvas 画布) {
        super.onDraw(画布);
    }



}
