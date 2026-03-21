package 安卓.组件;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.os.Build;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.annotation.StringRes;

import 自定义.主题类.主题类;

/**
 * 创建时间：2025年11月24日.
 * <p>
 * 描述：吐司
 * @author dxyc
 */
public class 吐司 extends Toast{

    /**
     * 描述：长度_短
     */
    public static final int 长度_短 = Toast.LENGTH_SHORT;
    /**
     * 描述：长度_长
     */
    public static final int 长度_长 = Toast.LENGTH_LONG;

    //==============================================================================================

    public 吐司(Context 上下文) {
        super(上下文);
    }

    //==============================================================================================

    public void 显示(){
        this.show();
    }

    public void 取消(){
        this.cancel();
    }


    public void 置视图(View 视图){
        this.setView(视图);
    }

    public View 取视图(){
        return this.getView();
    }


    public void 置持续时间(int 持续时间){
        this.setDuration(持续时间);
    }

    public int 取持续时间(){
        return this.getDuration();
    }


    public void 置边距(float 水平边距, float 垂直边距){
        this.setMargin(水平边距, 垂直边距);
    }

    public float 取水平边距(){
        return this.getHorizontalMargin();
    }

    public float 取垂直边距(){
        return this.getVerticalMargin();
    }


    public void 置对齐方式(int 对齐方式, int x偏移, int y偏移){
        this.setGravity(对齐方式, x偏移, y偏移);
    }

    public int 取对齐方式(){
        return this.getGravity();
    }

    public int 取x偏移(){
        return this.getXOffset();
    }

    public int 取y偏移(){
        return this.getYOffset();
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void 添加回调(Callback 回调){
        this.addCallback(回调);
    }

    @RequiresApi(api = Build.VERSION_CODES.R)
    public void 移除回调(Callback 回调){
        this.removeCallback(回调);
    }

    //==============================================================================================

    /**
     * 描述：制作文本
     */
    public static 吐司 制作文本(Context 上下文, CharSequence 文本, int 持续时间) {
        return 制作文本1(上下文, 文本, 持续时间);
    }

    @SuppressLint("ShowToast")
    private static 吐司 制作文本1(Context 上下文, CharSequence 文本, int 持续时间) {
        吐司 结果 = new 吐司(上下文);
        try {
            结果.setText(文本);
            结果.setDuration(持续时间);
        } catch (Exception e) {
            结果.setView(Toast.makeText(上下文, 文本, 持续时间).getView());
        }
        return 结果;
    }

    /**
     * 描述：制作文本
     */
    public static 吐司 制作文本(Context 上下文, int 资源Id, int 持续时间) {
        return 制作文本1(上下文, 上下文.getResources().getText(资源Id), 持续时间);
    }

    //==============================================================================================

    public static 吐司 自定义制作文本(Context 上下文, CharSequence 文本, int 持续时间) {
        return 自定义制作文本1(上下文, 文本, 持续时间);
    }

    private static 吐司 自定义制作文本1(Context 上下文, CharSequence 文本, int 持续时间) {
        吐司 吐司对话框 = new 吐司(上下文);
        线性布局 视图容器 = new 线性布局(上下文);
        视图容器.setGravity(Gravity.CENTER);
        视图容器.setOutlineProvider(new ViewOutlineProvider() {
            @Override
            public void getOutline(View view, Outline outline) {
                outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), 30f); //圆角
            }
        });
        视图容器.setElevation(10f); //阴影
        视图容器.setMinimumHeight(50); //最小高度
        视图容器.setMinimumWidth(100); //最小宽度
        视图容器.setBackgroundColor((!主题类.是否是深色模式(上下文)) ? Color.parseColor("#FFFFFF") : Color.parseColor("#343434"));
        视图容器.setClipToOutline(true); //圆角

        文本视图 视图文本 = new 文本视图(上下文);
        视图文本.setText(文本);  //文本内容
        视图文本.setGravity(Gravity.CENTER);
        视图文本.setTextColor((!主题类.是否是深色模式(上下文)) ? Color.BLACK : Color.WHITE);
        视图文本.setPadding(30, 10, 30, 10); //内边距
        视图文本.setEllipsize(TextUtils.TruncateAt.END);    //显示省略号

        视图容器.addView(视图文本, new ViewGroup.LayoutParams(-1, -1));
        吐司对话框.置视图(视图容器);
        吐司对话框.置持续时间(持续时间);
        return 吐司对话框;
    }

    private static 吐司 自定义制作文本(Context 上下文, int 资源Id, int 持续时间) {
        return 自定义制作文本1(上下文, 上下文.getText(资源Id), 持续时间);
    }

    //==============================================================================================

    public void 置文本(@StringRes int 资源Id){
        this.setText(资源Id);
    }

    public void 置文本(CharSequence 文本){
        this.setText(文本);
    }

    //==============================================================================================

    @RequiresApi(api = Build.VERSION_CODES.R)
    public abstract static class 回调 extends Callback{
        @Override
        public void onToastHidden() {
            this.吐司隐藏回调();
        }

        @Override
        public void onToastShown() {
            this.吐司显示回调();
        }

        public void 吐司显示回调(){
            super.onToastShown();
        }

        public void 吐司隐藏回调(){
            super.onToastHidden();
        }

    }

}
