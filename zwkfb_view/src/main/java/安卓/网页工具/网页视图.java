package 安卓.网页工具;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.DownloadListener;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/**
 * 创建时间：2025年12月16日.
 * <p>
 * 描述：网页视图
 * @author dxyc
 */
public class 网页视图 extends WebView {

    public 网页视图(@NonNull Context 上下文) {
        super(上下文);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性) {
        super(上下文, 属性);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性) {
        super(上下文, 属性, 默认样式属性);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, boolean 私密浏览) {
        super(上下文, 属性, 默认样式属性, 私密浏览);
    }

    public 网页视图(@NonNull Context 上下文, @Nullable AttributeSet 属性, int 默认样式属性, int 默认样式资源) {
        super(上下文, 属性, 默认样式属性, 默认样式资源);
    }



    public void 置下载监听器(@Nullable DownloadListener 下载监听器) {
        this.setDownloadListener(下载监听器);
    }




}
