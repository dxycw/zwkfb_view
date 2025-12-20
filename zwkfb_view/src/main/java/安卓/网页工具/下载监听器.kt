package 安卓.网页工具

import android.webkit.DownloadListener

interface 下载监听器 : DownloadListener {
    override fun onDownloadStart(
        url: String?, userAgent: String?, contentDisposition: String?,
        mimetype: String?, contentLength: Long,
    ) { 开始下载回调(url, userAgent, contentDisposition, mimetype, contentLength) }

    open fun 开始下载回调(
        url: String?, userAgent: String?, contentDisposition: String?,
        mimetype: String?, contentLength: Long
    ) {}
}