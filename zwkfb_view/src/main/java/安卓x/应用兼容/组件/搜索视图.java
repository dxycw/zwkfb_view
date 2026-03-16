package 安卓x.应用兼容.组件;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.SearchView;

/**
 * 创建时间：2025年11月22日.
 * <p>
 * 描述：搜索视图
 * @author dxyc
 */
public class 搜索视图 extends SearchView {

    public 搜索视图(Context 上下文) {
        super(上下文);
    }

    public 搜索视图(Context 上下文, AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 搜索视图(Context 上下文, AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    //======================================================================

    public interface 查询文本回调监听事件{
        boolean 查询文本提交回调(String 查询);
        boolean 查询文本改变回调(String 文本);
    }

    //======================================================================
//    private 查询文本回调监听事件 查询文本回调监听事件1 = null;

    //======================================================================
    public void 置查询文本回调监听事件(查询文本回调监听事件 事件) {
        setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                查询文本回调监听事件1.查询文本提交回调(query);
                return 事件.查询文本提交回调(query);
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                查询文本回调监听事件1.查询文本改变回调(newText);
                return 事件.查询文本改变回调(newText);
            }
        });
//        查询文本回调监听事件1 = 事件;
    }


}
