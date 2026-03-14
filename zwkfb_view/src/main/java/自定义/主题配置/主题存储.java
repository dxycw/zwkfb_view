package 自定义.主题配置;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.annotation.ColorInt;
import androidx.appcompat.R;

public class 主题存储 {
    private final Context 上下文;
    private final SharedPreferences.Editor 主题配置存储;

    public 主题存储(Context 上下文) {
        this.上下文 = 上下文;
        主题配置存储 = 存储主题配置(上下文).edit();
    }

    static class 主题键类{
        public static final String 主题配置 = "主题配置";
        public static final String  是否完成配置 = "是否完成配置";
        public static final String  值变更时间戳 = "值变更时间戳";

        public static final String  强调色 = "强调色";
        public static final String  背景色 = "背景色";
    }



    //==============================================================================================
    /**
     * 修改主题的“强调色”
     * @param 颜色 强调色，默认使用应用上下文
     * @return 主题存储
     */
    public 主题存储 置强调色(int 颜色) {
        主题配置存储.putInt(主题键类.强调色, 颜色);
        return this;
    }

    /**
     * 修改主题的“背景色”
     * @param 颜色 背景色，默认使用应用上下文
     * @return 主题存储
     */
    public 主题存储 置背景色(int 颜色) {
        主题配置存储.putInt(主题键类.背景色, 颜色);
        return this;
    }


    /**
     * 使用“主题存储”修改主题后必须调用此方法，否则主题配置不会生效
     * 比如：主题存储.修改主题(this).强调色(color).应用()
     */
    public void 应用() {
        主题配置存储.putLong(主题键类.值变更时间戳, System.currentTimeMillis())
                .putBoolean(主题键类.是否完成配置, true)
                .apply();
    }

    //==============================================================================================
    /**
     * 初始主题
     * @param 上下文 上下文，默认使用应用上下文
     * @return 主题存储
     */
    public static 主题存储 初始主题(Context 上下文) {
        return new 主题存储(上下文);
    }

    //==============================================================================================
    /**
     * 获取存储主题配置
     * @param 上下文 上下文，默认使用应用上下文
     * @return 存储主题配置
     */
    static SharedPreferences 存储主题配置(Context 上下文) {
        return 上下文.getSharedPreferences(主题键类.主题配置, Context.MODE_PRIVATE);
    }

    /**
     * 获取主题配置存储
     * @param 上下文 上下文，默认使用应用上下文
     * @return 主题配置存储
     */
    static SharedPreferences.Editor 主题配置存储(Context 上下文) {
        return 上下文.getSharedPreferences(主题键类.主题配置, Context.MODE_PRIVATE).edit();
    }

    //=========获取主题颜色===========================================================================
    /**
     * 获取当前主题的“强调色”
     * @param 上下文 上下文，默认使用应用上下文
     * @return 当前主题的“强调色”
     */
    @ColorInt
    static int 强调色(Context 上下文) {
        return 存储主题配置(上下文).getInt(主题键类.强调色, 主题工具.取颜色(上下文, R.attr.colorAccent,
                Color.parseColor("#263238")));
    }

    /**
     * 获取当前主题的“背景色”
     * @param 上下文 上下文，默认使用应用上下文
     * @return 当前主题的“背景色”
     */
    @ColorInt
    static int 背景色(Context 上下文) {
        return 存储主题配置(上下文).getInt(主题键类.背景色, 主题工具.取颜色(上下文, R.attr.background,
                Color.parseColor("#263238")));
    }

}
