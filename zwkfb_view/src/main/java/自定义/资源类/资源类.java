package 自定义.资源类;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

import androidx.core.content.ContextCompat;

public class 资源类 {


    public static Drawable bitmap转Drawable(Context 上下文, Bitmap 位图) {
        return new BitmapDrawable(上下文.getResources(), 位图);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public static Drawable int转Drawable(Context 上下文, int 资源id) {
        return 上下文.getResources().getDrawable(资源id);
    }

    public static int attr转int(Context 上下文, int attr) {
        int[] attrs = new int[]{attr};
        TypedArray typedArray = 上下文.obtainStyledAttributes(attrs);
        int selectableItemBackground = typedArray.getResourceId(0, 0);
        typedArray.recycle();
        return selectableItemBackground;
    }

    public static ColorStateList attr转颜色(Context 上下文, int attr) {
        TypedValue typedValue = new TypedValue();
        上下文.getTheme().resolveAttribute(attr, typedValue, true);
        int colorPrimary = ContextCompat.getColor(上下文, typedValue.resourceId);
        return ColorStateList.valueOf(colorPrimary);
    }

}
