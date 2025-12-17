package 自定义.主题配置

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.appcompat.R
import androidx.core.graphics.toColorInt

class 主题存储 private constructor(override val 上下文: Context): 主题存储接口 {

    private val 主题存储配置 = 存储配置(上下文).edit()

    //============修改“强调色”主题颜色========================================
    /**
     * 修改主题的“强调色”
     * @param 颜色 强调色，默认使用应用上下文
     * @return 主题存储
     */
    override fun 强调色(@ColorInt 颜色: Int): 主题存储 {
        主题存储配置.putInt(主题键类.强调色, 颜色)
        return this
    }

    //=================================================================
    /**
     * 使用“主题存储”修改主题后必须调用此方法，否则主题配置不会生效
     * 比如：主题存储.修改主题(this).强调色(color).应用()
     */
    @SuppressLint("CheckResult")
    override fun 应用() {
        主题存储配置.putLong(主题键类.值变更时间戳, System.currentTimeMillis())
            .putBoolean(主题键类.是否完成配置, true)
            .apply()
//        强调色 =
        强调色(上下文)
    }

    companion object {

//        var 强调色 = 强调色()

        //=========用来修改所有主题函数========================================
        /**
          * 修改主题
          * @param 上下文 上下文，默认使用应用上下文
          * @return 主题存储
          */
        fun 修改主题(上下文: Context): 主题存储 {
            return 主题存储(上下文)
        }

        //=================================================================
        /**
          * 获取主题存储配置
          * @param 上下文 上下文，默认使用应用上下文
          * @return 主题存储配置
          */
        @CheckResult
        internal fun 存储配置(上下文: Context): SharedPreferences {
            return 上下文.getSharedPreferences(
                主题键类.主题配置, Context.MODE_PRIVATE
            )
        }

        //=========获取主题颜色===================================
        /**
         * 获取当前主题的“强调色”
         * @param 上下文 上下文，默认使用应用上下文
         * @return 当前主题的“强调色”
         */
        @CheckResult
        @ColorInt
        fun 强调色(上下文: Context): Int {
            return 存储配置(上下文).getInt(
                主题键类.强调色,
                主题工具类.获取颜色(
                    上下文, R.attr.colorAccent,
                    "#263238".toColorInt()
                )
            )
        }
    }
}
