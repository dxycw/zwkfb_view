package 安卓x.卡片视图.组件

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView


//======================================================================

/**
 * 版本：0.1.0
 *
 * 描述：圆角
 * @param 圆角
 * @return 圆角
 */
var CardView.圆角: Float
    get() = radius
    set(圆角) { radius = 圆角 }

/**
 * 版本：0.1.0
 *
 * 描述：圆角
 * @return 圆角
 */
fun CardView.取圆角(): Float {
    return getRadius()
}

/**
 * 版本：0.1.0
 *
 * 描述：圆角
 * @param 圆角
 */
fun CardView.置圆角(圆角: Float) {
    setRadius(圆角)
}

//======================================================================
