package 安卓.组件;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.widget.TextViewCompat;

/**
 * 创建时间：2025年11月18日.
 * <p>
 * 描述：文本视图
 * @author dxyc
 */
@SuppressLint("AppCompatCustomView")
public class 文本视图 extends TextView {
    public 文本视图(Context 上下文) {
        super(上下文);
    }

    public 文本视图(Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 文本视图(Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 文本视图(Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }

//    private var 文本 : CharSequence = ""
//    private fun init(attrs: AttributeSet?) {
//        val a = context.obtainStyledAttributes(attrs,R.styleable.文本视图)
//        文本 = a.getString(R.styleable.文本视图_文本) ?: ""
//        a.recycle()
//
//        setText(this.文本)
//
//    }

    @RequiresApi(Build.VERSION_CODES.O)
    public static final int 自动_大小_文本_类型_无 = TextView.AUTO_SIZE_TEXT_TYPE_NONE;

    @RequiresApi(Build.VERSION_CODES.O)
    public static final int 自动_大小_文本_类型_一致 = TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM;

    //===========================================================================================

    public interface 编辑器动作监听器 extends OnEditorActionListener {
        @Override
        default boolean onEditorAction(TextView v, int actionId, KeyEvent event){
            return 编辑器动作(v, actionId, event);
        }
        boolean 编辑器动作(TextView v, int 动作id, KeyEvent 事件);
    }

    //===========================================================================================


    @RequiresApi(Build.VERSION_CODES.O)
    public void 置自动大小文本类型为默认(@TextViewCompat.AutoSizeTextType int 自动大小文本类型){
        this.setAutoSizeTextTypeWithDefaults(自动大小文本类型);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void 置自动大小文本类型带配置统一(int 自动大小最小文本大小, int 自动大小最大文本大小, int 自动大小步长粒度, int 单位) {
        this.setAutoSizeTextTypeUniformWithConfiguration(自动大小最小文本大小, 自动大小最大文本大小, 自动大小步长粒度, 单位);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public void 置自动大小文本类型统一预设大小(int[] 预设大小, int 单位) {
        this.setAutoSizeTextTypeUniformWithPresetSizes(预设大小, 单位);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    public int 取自动大小文本类型(){
        return this.getAutoSizeTextType();
    }



}
