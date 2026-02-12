package 安卓.开放图形库

import android.content.Context
import android.opengl.GLSurfaceView
import android.util.AttributeSet

/**
 * 创建时间：2025年11月24日.
 *
 * 描述：图形库表面视图
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 图形库表面视图 : GLSurfaceView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
}