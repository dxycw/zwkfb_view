package 自定义.主题配置

import android.content.Context

val Context.强调色: Int
    get() = 主题存储.强调色(this)
