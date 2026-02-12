package 自定义.主题配置

import android.content.Context
import androidx.annotation.ColorInt

internal interface 主题存储接口 {

    val 上下文 : Context

    fun 强调色(@ColorInt 颜色: Int): 主题存储
    fun 应用()
}