package 安卓.网页工具;

import android.webkit.DownloadListener;

public interface 下载监听器 extends DownloadListener {
    @Override
    default void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength){
        开始下载回调(url, userAgent, contentDisposition, mimetype, contentLength);
    }

    void 开始下载回调(String 网址, String 用户代理, String 内容处置, String 媒体类型, long 内容长度);
}
