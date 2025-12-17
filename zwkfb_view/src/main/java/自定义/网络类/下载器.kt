@file:Suppress("DEPRECATION")

package 自定义.网络类

import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import android.webkit.URLUtil
import androidx.core.net.toUri
import 安卓.组件.吐司
import 安卓.组件.显示
import java.net.URLDecoder

object 下载器 {

    @JvmStatic
    fun 浏览器文件下载(上下文: Activity, 网址: String?, ua: String, 内容处理: String, 文件类型: String) {
        try {
            val request = DownloadManager.Request(网址?.toUri())
            request.allowScanningByMediaScanner()
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            val 文件名 = URLUtil.guessFileName(网址, 内容处理, 文件类型)//"app.apk"
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, 文件名)
            val downloadManager = 上下文.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
            吐司.制作文本(上下文,"开始下载", 吐司.短).显示()
        } catch (e: Exception) {
            吐司.制作文本(上下文, "下载失败：$e", 吐司.短).显示()
        }

    }

//    @JvmStatic
//    fun 浏览器文件下载2(上下文: Activity, 网络请求: WebResponse) {
//        try {
//            // 获取下载 URL 和文件名
//            val 下载链接 = 网络请求.uri
//            val fileName = generateFileName(网络请求.headers)
//
//            // 创建下载请求
//            val request = DownloadManager.Request(下载链接.toUri()).apply {
//                // 设置文件名和 MIME 类型
//                setTitle(fileName)
//                setDescription("正在下载文件")
//                setMimeType(网络请求.headers["Content-Type"] ?: "application/octet-stream")
//
//                // 设置通知可见性
//                setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
//
//                // 设置下载文件的存放位置
//                setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
//            }
//
//            // 获取 DownloadManager 实例
//            val downloadManager = 上下文.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
//            // 开始下载
//            val downloadId = downloadManager.enqueue(request)
//            吐司.制作文本(上下文, "开始下载：$fileName", 吐司.短).显示()
//        } catch (e: Exception) {
//            吐司.制作文本(上下文, "下载失败：$e", 吐司.短).显示()
//        }
//    }

    /**
     * 根据响应头和 URL 生成文件名
     */
    private fun generateFileName(headers: Map<String, String>): String {
        // 如果没有从 URL 中获取到文件名，则从 Content-Disposition 中提取
        val contentDisposition = headers["Content-Disposition"] ?: ""
        val fileNameFromHeader = getContentDispositionFileName(contentDisposition)
        if (fileNameFromHeader.isNotEmpty()) {
            return fileNameFromHeader
        }

        // 如果仍然没有文件名，则根据 MIME 类型生成默认文件名
        val mimeType = headers["Content-Type"] ?: "application/octet-stream"
        return when {
            mimeType.startsWith("image/jpeg") -> "image_${System.currentTimeMillis()}.jpg"
            mimeType.startsWith("image/png") -> "image_${System.currentTimeMillis()}.png"
            mimeType.startsWith("image/gif") -> "image_${System.currentTimeMillis()}.gif"
            mimeType.startsWith("image/bmp") -> "image_${System.currentTimeMillis()}.bmp"
            mimeType.startsWith("video/mp4") -> "video_${System.currentTimeMillis()}.mp4"
            mimeType.startsWith("video/3gpp") -> "video_${System.currentTimeMillis()}.3gp"
            mimeType.startsWith("audio/mpeg") -> "audio_${System.currentTimeMillis()}.mp3"
            mimeType.startsWith("audio/wav") -> "audio_${System.currentTimeMillis()}.wav"
            mimeType.startsWith("text/plain") -> "text_${System.currentTimeMillis()}.txt"
            mimeType.startsWith("text/html") -> "text_${System.currentTimeMillis()}.html"
            mimeType.startsWith("application/pdf") -> "document_${System.currentTimeMillis()}.pdf"
            mimeType.startsWith("application/vnd.android.package-archive") -> "app_${System.currentTimeMillis()}.apk"
            mimeType.startsWith("binary/octet-stream") -> "app_${System.currentTimeMillis()}.apk"
            mimeType.startsWith("application/octet-stream") -> "app_${System.currentTimeMillis()}.apk"
            else -> "file_${System.currentTimeMillis()}.${getMimeTypeExtension(mimeType)}"
        }
    }

    /**
     * 从 Content-Disposition 中提取文件名
     */
    private fun getContentDispositionFileName(contentDisposition: String): String {
        val fileNamePattern = "filename\\*=.*?utf-8''(.*?)".toRegex()
        val matchResult = fileNamePattern.find(contentDisposition)
        if (matchResult != null) {
            return URLDecoder.decode(matchResult.groupValues[1], "UTF-8")
        }

        val simpleFileNamePattern = "filename\\s*=\\s*\"?(.*?)\"\\s*$".toRegex()
        val simpleMatchResult = simpleFileNamePattern.find(contentDisposition)
        return simpleMatchResult?.groupValues?.get(1) ?: ""
    }

    /**
     * 根据 MIME 类型获取文件扩展名
     */
    private fun getMimeTypeExtension(mimeType: String): String {
        return when {
            mimeType.startsWith("image/jpeg") -> "jpg"
            mimeType.startsWith("image/png") -> "png"
            mimeType.startsWith("image/gif") -> "gif"
            mimeType.startsWith("image/bmp") -> "bmp"
            mimeType.startsWith("video/mp4") -> "mp4"
            mimeType.startsWith("video/3gpp") -> "3gp"
            mimeType.startsWith("audio/mpeg") -> "mp3"
            mimeType.startsWith("audio/wav") -> "wav"
            mimeType.startsWith("text/plain") -> "txt"
            mimeType.startsWith("text/html") -> "html"
            mimeType.startsWith("application/pdf") -> "pdf"
            mimeType.startsWith("application/vnd.android.package-archive") -> "apk"
            mimeType.startsWith("binary/octet-stream") -> ".apk"
            mimeType.startsWith("application/octet-stream") -> ".apk"
            mimeType.startsWith("application/zip") -> "zip"
            mimeType.startsWith("application/x-rar-compressed") -> "rar"
            else -> "bin"
        }
    }

}