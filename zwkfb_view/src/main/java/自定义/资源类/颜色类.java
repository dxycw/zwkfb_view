package 自定义.资源类;

import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class 颜色类 {

    public static int 白色 = Color.WHITE;
    public static int 黑色 = Color.BLACK;
    public static int 红色 = Color.RED;
    public static int 洋红色 = Color.MAGENTA;
    public static int 绿色 = Color.GREEN;
    public static int 蓝色 = Color.BLUE;
    public static String 橙色 = "#FFA500";
    public static int 灰色 = Color.GRAY;
    public static int 深灰色 = Color.DKGRAY;
    public static int 粉色 = Color.LTGRAY;
    public static int 黄色 = Color.YELLOW;
    public static int 青色 = Color.CYAN;
    public static int 透明 = Color.TRANSPARENT;



    public static int 置RGB颜色(int 红, int 绿, int 蓝){
        return Color.rgb(红, 绿, 蓝);
    }


    public static int 置ARGB颜色(int 透明度, int 红, int 绿, int 蓝){
        return Color.argb(透明度, 红, 绿, 蓝);
    }


    //==================以下函数android 8.0及以上版本===========================================

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int 置RGB颜色(Float 红, Float 绿, Float 蓝){
        return Color.rgb(红, 绿, 蓝);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static int 置ARGB颜色(Float 透明度, Float 红, Float 绿, Float 蓝){
        return Color.argb(透明度, 红, 绿, 蓝);
    }


}
