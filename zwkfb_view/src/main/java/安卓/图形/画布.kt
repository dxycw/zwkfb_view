package 安卓.图形

import android.graphics.Bitmap
import android.graphics.Canvas

/**
 * 创建时间：2025年11月23日.
 *
 * 描述：画布
 *
 * 版本：0.1.0
 * @author dxyc
 */
open class 画布 : Canvas {
    constructor() : super()
    constructor(bitmap: Bitmap) : super(bitmap)
}