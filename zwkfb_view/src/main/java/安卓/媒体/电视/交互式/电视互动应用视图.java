package 安卓.媒体.电视.交互式;

import android.content.Context;
import android.media.tv.interactive.TvInteractiveAppView;
import android.os.Build;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * 创建时间：2025年11月24日.
 * <p>
 * 描述：电视互动应用视图，仅支持Android 13.0及以上版本
 * @author dxyc
 */
@RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
public class 电视互动应用视图 extends TvInteractiveAppView {

    public 电视互动应用视图(@NonNull Context 上下文) {
        super(上下文);
    }

    public 电视互动应用视图(@NonNull Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 电视互动应用视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }


}
