package 安卓.组件;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * 创建时间：2025年11月24日.
 * <p>
 * 描述：吐司
 * @author dxyc
 */
public class 吐司 extends Toast {

    public 吐司(Context 上下文) {
        super(上下文);
    }

    /**
     * 描述：长度_短
     */
    public static final int 长度_短 = Toast.LENGTH_SHORT;
    /**
     * 描述：长
     */
    public static final int 长度_长 = Toast.LENGTH_LONG;


    /**
     * 描述：制作文本
     */
    @SuppressLint("ShowToast")
    public static 吐司 制作文本(Context 上下文, int 资源Id, int 持续时间) {
        吐司 结果 = new 吐司(上下文);
        结果.setView(Toast.makeText(上下文, 资源Id, 持续时间).getView());
        return 结果;
    }

    /**
     * 描述：制作文本
     */
    @SuppressLint("ShowToast")
    public static 吐司 制作文本(Context 上下文, CharSequence 文本, int 持续时间) {
        吐司 结果 = new 吐司(上下文);
        结果.setView(Toast.makeText(上下文, 文本, 持续时间).getView());
        return 结果;
    }


//        fun 自定义信息提示(上下文: Context, 资源Id: Int, 显示时间: Int = 短): Toast {
//            val 吐司对话框 = Toast(上下文)
//            val 视图内容 = 线性布局(上下文).apply {
//                gravity = Gravity.CENTER
//                outlineProvider = object : ViewOutlineProvider() {
//                    override fun getOutline(view: View?, outline: Outline?) {
//                        outline?.setRoundRect(0, 0, view?.width!!,
//                            view.height, 30f) //圆角
//                    }
//                }
//                elevation = 10f //阴影
//                minimumHeight = 50 //最小高度
//                minimumWidth = 100
//                置背景颜色(if(!上下文.是否是深色模式) "#FFFFFF".toColorInt() else "#343434".toColorInt())
//                clipToOutline = true //圆角
//            }
//            val 文本 = 文本视图(上下文).apply {
//                文本 = 资源Id.toString()  //文本内容
//                gravity = Gravity.CENTER
//                置文本颜色(if(!上下文.是否是深色模式) Color.BLACK else Color.WHITE)
//                setPadding(30, 10, 30, 10) //内边距
//                ellipsize = TextUtils.TruncateAt.END  //显示省略号
//            }
//            视图内容.添加视图(文本, 线性布局.布局参数(-1, -1))
//            吐司对话框.置视图(视图内容)
//            吐司对话框.置持续时间(显示时间)
//            return 吐司对话框
//        }
//
//        fun 自定义信息提示(上下文: Context, 内容: String, 显示时间: Int = 短): Toast {
//            val 吐司对话框 = Toast(上下文)
//            val 视图内容 = 线性布局(上下文).apply {
//                gravity = Gravity.CENTER
//                outlineProvider = object : ViewOutlineProvider() {
//                    override fun getOutline(view: View?, outline: Outline?) {
//                        outline?.setRoundRect(0, 0, view?.width!!,
//                            view.height, 30f) //圆角
//                    }
//                }
//                elevation = 10f //阴影
//                minimumHeight = 50 //最小高度
//                minimumWidth = 100
//                置背景颜色(if(!上下文.是否是深色模式) "#FFFFFF".toColorInt() else "#343434".toColorInt())
//                clipToOutline = true //圆角
//            }
//            val 文本 = 文本视图(上下文).apply {
//                文本 = 内容  //文本内容
//                gravity = Gravity.CENTER
//                置文本颜色(if(!上下文.是否是深色模式) Color.BLACK else Color.WHITE)
//                setPadding(30, 10, 30, 10) //内边距
//                ellipsize = TextUtils.TruncateAt.END  //显示省略号
//            }
//            视图内容.添加视图(文本, 线性布局.布局参数(-1, -1))
//            吐司对话框.置视图(视图内容)
//            吐司对话框.置持续时间(显示时间)
//            return 吐司对话框
//        }


    public void 置文本(int 资源Id){
        this.setText(资源Id);
    }

    public void 置文本(CharSequence 文本){
        this.setText(文本);
    }

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




}
