package 自定义.主题配置

import android.content.Context
import androidx.annotation.AttrRes

object 主题工具类 {
    @JvmOverloads
    fun 获取颜色(上下文: Context, @AttrRes attr: Int, fallback: Int = 0): Int {
        val a = 上下文.theme.obtainStyledAttributes(intArrayOf(attr))
        return try {
            a.getColor(0, fallback)
        } catch (e: Exception) {
            fallback
        } finally {
            a.recycle()
        }
    }
}