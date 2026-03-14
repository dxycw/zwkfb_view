package 自定义.主题配置;

import android.content.Context;
import android.content.res.TypedArray;

import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;

public class 主题工具 {

    public static int 取颜色(Context 上下文, @AttrRes int 属性, @ColorInt int 颜色) {
        TypedArray a = 上下文.getTheme().obtainStyledAttributes(new int[]{属性});
        try {
             return a.getColor(0, 颜色);
        } catch (Exception e) {
             return 颜色;
        } finally {
             a.recycle();
        }
    }

}
