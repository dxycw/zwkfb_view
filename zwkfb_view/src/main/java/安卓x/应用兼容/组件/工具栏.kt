package 安卓x.应用兼容.组件

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.Toolbar


//======================================================================

/**
 * 描述：置导航单击回调监听事件
 * @param 回调 导航单击回调监听事件
 */
fun Toolbar.置导航单击回调监听事件(回调: View.OnClickListener){
    this.setNavigationOnClickListener(回调)
}
